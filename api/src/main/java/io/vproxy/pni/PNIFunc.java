package io.vproxy.pni;

import io.vproxy.pni.unsafe.SunUnsafe;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.util.Objects;

public abstract class PNIFunc<T> {
    private static final Arena UPCALL_STUB_ARENA = Arena.ofShared(); // should not be released
    private static final MemorySegment UPCALL_STUB_CALL;
    private static final MemorySegment UPCALL_STUB_RELEASE;

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

        UPCALL_STUB_CALL = Linker.nativeLinker().upcallStub(
            callMethodHandle,
            FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_LONG, ValueLayout.ADDRESS),
            UPCALL_STUB_ARENA);
        UPCALL_STUB_RELEASE = Linker.nativeLinker().upcallStub(
            releaseMethodHandle,
            FunctionDescriptor.ofVoid(ValueLayout.JAVA_LONG),
            UPCALL_STUB_ARENA);
    }

    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_LONG_UNALIGNED.withName("index"),
        ValueLayout.ADDRESS_UNALIGNED.withName("func"),
        ValueLayout.ADDRESS_UNALIGNED.withName("release"),
        MemoryLayout.unionLayout(
            ValueLayout.ADDRESS_UNALIGNED.withName("userdata"),
            ValueLayout.JAVA_LONG_UNALIGNED.withName("udata64")
        ).withName("union0")
    );
    public final MemorySegment MEMORY;
    private final long index;
    private final CallSite<T> func;

    protected PNIFunc(CallSite<T> func) {
        Objects.requireNonNull(func);

        MEMORY = SunUnsafe.allocateMemory(LAYOUT.byteSize());
        this.func = func;

        var index = holder.store(this);
        this.index = index;
        indexVH.set(MEMORY, index);

        funcVH.set(MEMORY, UPCALL_STUB_CALL);
        releaseVH.set(MEMORY, UPCALL_STUB_RELEASE);
    }

    protected PNIFunc(MemorySegment MEMORY) {
        this.MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.index = 0;
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
        holder = new ObjectHolder<>(len);
    }

    public static long currentFuncStorageSize() {
        return holder.size();
    }

    private static final VarHandle indexVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("index")
    );
    private static final VarHandle funcVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("func")
    );
    private static final VarHandle releaseVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("release")
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

    private static int call(long index, MemorySegment data) {
        PNIFunc<?> func = holder.get(index);
        if (func == null) {
            System.out.println("[PNI][WARN][PNIFunc#call] PNIFunc not found: index: " + index + ", data: " + data.address());
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

    private static void release(long index) {
        var func = holder.remove(index);
        if (func == null) {
            System.out.println("[PNI][WARN][PNIFunc#release] PNIFunc not found: index: " + index);
            return;
        }
        SunUnsafe.freeMemory(func.MEMORY.address());
    }

    public void close() {
        release(getIndex());
    }

    public static class VoidFunc extends PNIFunc<Void> {
        private VoidFunc(CallSite<Void> func) {
            super(func);
        }

        private VoidFunc(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static VoidFunc of(CallSite<Void> func) {
            return new VoidFunc(func);
        }

        public static VoidFunc of(MemorySegment MEMORY) {
            return new VoidFunc(MEMORY);
        }

        @Override
        protected Void construct(MemorySegment seg) {
            return null;
        }
    }
}
