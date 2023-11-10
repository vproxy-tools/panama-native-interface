package io.vproxy.pni;

import io.vproxy.pni.impl.Utils;
import io.vproxy.pni.unsafe.SunUnsafe;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class PNIFunc<T> implements NativeObject {
    private static final Arena UPCALL_STUB_ARENA = Arena.ofShared(); // should not be released

    static {
        MethodHandle callMethodHandle;
        MethodHandle releaseMethodHandle;
        try {
            callMethodHandle = MethodHandles.lookup()
                .findStatic(PNIFunc.class,
                    "call",
                    MethodType.methodType(int.class, long.class, MemorySegment.class));
            releaseMethodHandle = MethodHandles.lookup()
                .findStatic(PNIFunc.class,
                    "release",
                    MethodType.methodType(void.class, long.class));
        } catch (Throwable e) {
            throw new RuntimeException(e); // should not happen
        }

        MemorySegment UPCALL_STUB_CALL;
        MemorySegment UPCALL_STUB_RELEASE;

        if (GraalHelper.getInvokeFunc() != null) {
            UPCALL_STUB_CALL = GraalHelper.getInvokeFunc();
        } else {
            UPCALL_STUB_CALL = Linker.nativeLinker().upcallStub(
                callMethodHandle,
                FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS),
                UPCALL_STUB_ARENA);
        }
        if (GraalHelper.getReleaseFunc() != null) {
            UPCALL_STUB_RELEASE = GraalHelper.getReleaseFunc();
        } else {
            UPCALL_STUB_RELEASE = Linker.nativeLinker().upcallStub(
                releaseMethodHandle,
                FunctionDescriptor.ofVoid(ValueLayout.JAVA_LONG),
                UPCALL_STUB_ARENA);
        }

        PanamaUtils.loadLib();

        var SetPNIFuncInvokeFunc = PanamaUtils.lookupPNICriticalFunction(
            new PNILinkOptions().setCritical(true),
            void.class, "SetPNIFuncInvokeFunc", MemorySegment.class);
        var SetPNIFuncReleaseFunc = PanamaUtils.lookupPNICriticalFunction(
            new PNILinkOptions().setCritical(true),
            void.class, "SetPNIFuncReleaseFunc", MemorySegment.class);
        try {
            SetPNIFuncInvokeFunc.invokeExact(UPCALL_STUB_CALL);
            SetPNIFuncReleaseFunc.invokeExact(UPCALL_STUB_RELEASE);
        } catch (Throwable e) {
            throw new RuntimeException("should not happen", e);
        }
    }

    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_LONG_UNALIGNED.withName("index"),
        MemoryLayout.unionLayout(
            ValueLayout.ADDRESS_UNALIGNED.withName("userdata"),
            ValueLayout.JAVA_LONG_UNALIGNED.withName("udata64")
        ).withName("union0")
    );
    public final MemorySegment MEMORY;
    private final CallSite<T> func;

    protected PNIFunc(CallSite<T> func) {
        this(func, new Options());
    }

    protected PNIFunc(CallSite<T> func, Options opts) {
        Objects.requireNonNull(func);
        if (opts.userdataByteSize < 0)
            throw new IllegalArgumentException("userdataMemSize(" + opts.userdataByteSize + ") < 0");

        MEMORY = SunUnsafe.allocateMemory(LAYOUT.byteSize() + opts.userdataByteSize);
        SunUnsafe.setMemory(MEMORY.address(), MEMORY.byteSize(), (byte) 0);
        if (opts.userdataByteSize > 0) {
            var ud = MEMORY.asSlice(LAYOUT.byteSize());
            setUserdata(ud);
        }
        this.func = func;

        var index = holder.store(this);
        indexVH.set(MEMORY, index);
    }

    public static class Options {
        public long userdataByteSize;

        public Options setUserdataByteSize(long userdataByteSize) {
            this.userdataByteSize = userdataByteSize;
            return this;
        }
    }

    protected PNIFunc(MemorySegment MEMORY) {
        this.MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.func = null;
    }

    private static final ObjectHolder<PNIFunc<?>> holder;

    static {
        int len = 4096;
        final String KEY = "io.vproxy.pni.PNIFunc.holder.fastStorage.length";
        var strLen = System.getProperty(KEY, "4096");
        try {
            len = Integer.parseInt(strLen);
        } catch (NumberFormatException e) {
            System.out.println("[PNI][WARN][PNIFunc#cinit] invalid " + KEY + ": not a number " + strLen);
        }
        if (len < 0) {
            System.out.println("[PNI][WARN][PNIFunc#cinit] invalid " + KEY + ": value should >= 0: " + len + ", the value is modified to 0");
            len = 0;
        }
        if ((len & (len - 1)) != 0) {
            int n = Utils.smallestPositivePowerOf2GE(len);
            System.out.println("[PNI][WARN][PNIFunc#cinit] invalid " + KEY + ": not power of 2: " + len + ", the value is modified to " + n);
            len = n;
        }
        holder = new ObjectHolder<>(len);
    }

    public static long currentFuncStorageSize() {
        return holder.size();
    }

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle indexVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("index")
    );
    private static final VarHandle userdataVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("union0"),
        MemoryLayout.PathElement.groupElement("userdata")
    );
    private static final VarHandle udata64VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("union0"),
        MemoryLayout.PathElement.groupElement("udata64")
    );

    public long getIndex() {
        return (long) indexVH.get(MEMORY);
    }

    public MemorySegment getUserdata() {
        return (MemorySegment) userdataVH.get(MEMORY);
    }

    public void setUserdata(MemorySegment userdata) {
        userdataVH.set(MEMORY, userdata);
    }

    public long getUData64() {
        return (long) udata64VH.get(MEMORY);
    }

    public void setUData64(long udata64) {
        udata64VH.set(MEMORY, udata64);
    }

    abstract protected T construct(MemorySegment seg);

    public CallSite<T> getCallSite() {
        if (this.func != null) {
            return this.func;
        }

        var index = getIndex();
        PNIFunc<?> func = holder.get(index);
        if (func == null) {
            throw new NullPointerException("index = " + index);
        }
        //noinspection unchecked
        return (CallSite<T>) func.func;
    }

    public static int call(long index, MemorySegment data) {
        PNIFunc<?> func = holder.get(index);
        if (func == null) {
            System.out.println("[PNI][WARN][PNIFunc#call] PNIFunc not found: index: " + index + ", data: " + data.address());
            Utils.printStacktrace();
            return 0x800000f2;
        }
        Object o;
        if (data.address() == 0) {
            o = null;
        } else {
            try {
                o = func.construct(data);
            } catch (Throwable t) {
                System.out.println("[PNI][ERR ][PNIFunc#call] construct raised an exception, which would crash the VM. " +
                                   "Now the exception is caught and the call returns 0x800000f1 (signed, so it's negative).");
                t.printStackTrace(System.out);
                return 0x800000f1;
            }
        }
        //noinspection rawtypes
        var callsite = ((CallSite) func.func);
        try {
            //noinspection unchecked
            return callsite.call(o);
        } catch (Throwable t) {
            System.out.println("[PNI][ERR ][PNIFunc#call] call raised an exception, which would crash the VM. " +
                               "Now the exception is caught and the call returns 0x800000f1 (signed, so it's negative).");
            t.printStackTrace(System.out);
            return 0x800000f1;
        }
    }

    public static void release(long index) {
        var func = holder.remove(index);
        if (func == null) {
            System.out.println("[PNI][WARN][PNIFunc#release] PNIFunc not found: index: " + index);
            Utils.printStacktrace();
            return;
        }
        SunUnsafe.freeMemory(func.MEMORY.address());
    }

    public void close() {
        release(getIndex());
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new HashSet<>(), false);
        return sb.toString();
    }

    @SuppressWarnings("unused")
    @Override
    public void toString(StringBuilder sb, int indent, Set<NativeObjectTuple> visited, boolean corrupted) {
        if (visited.add(new NativeObjectTuple(this))) {
            sb.append(toStringTypeName()).append("(").append(getCallSite()).append(")");
        } else {
            sb.append("<...>");
        }
        sb.append("@").append(Long.toString(MEMORY.address(), 16));
    }

    protected String toStringTypeName() {
        return "PNIFunc";
    }

    public static class VoidFunc extends PNIFunc<Void> {
        private VoidFunc(CallSite<Void> func) {
            super(func);
        }

        private VoidFunc(CallSite<Void> func, Options opts) {
            super(func, opts);
        }

        private VoidFunc(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static VoidFunc of(CallSite<Void> func) {
            return new VoidFunc(func);
        }

        public static VoidFunc of(CallSite<Void> func, Options opts) {
            return new VoidFunc(func, opts);
        }

        public static VoidFunc of(MemorySegment MEMORY) {
            return new VoidFunc(MEMORY);
        }

        @Override
        protected Void construct(MemorySegment seg) {
            return null;
        }

        @Override
        protected String toStringTypeName() {
            return "PNIFunc.VoidFunc";
        }
    }
}
