package io.vproxy.pni;

import io.vproxy.pni.unsafe.SunUnsafe;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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

    private static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_LONG_UNALIGNED.withName("index"),
        ValueLayout.ADDRESS_UNALIGNED.withName("func"),
        ValueLayout.ADDRESS_UNALIGNED.withName("release"),
        ValueLayout.ADDRESS_UNALIGNED.withName("userdata")
    );
    public final MemorySegment MEMORY;
    private final long index;
    private final CallSite<T> func;

    protected PNIFunc(CallSite<T> func) {
        Objects.requireNonNull(func);

        MEMORY = SunUnsafe.allocateMemory(LAYOUT.byteSize());
        this.func = func;

        var index = indexCounter.incrementAndGet();
        while (index == 0) {
            index = indexCounter.incrementAndGet();
        }
        this.index = index;
        indexVH.set(MEMORY, index);
        funcStorage.put(index, this);
        if (funcFastStorage.length != 0) {
            var arrIdx = (int) (index % funcFastStorage.length);
            var wlock = funcFastStorageLock.writeLock();
            wlock.lock();
            funcFastStorage[arrIdx] = this;
            wlock.unlock();
        }

        funcVH.set(MEMORY, UPCALL_STUB_CALL);
        releaseVH.set(MEMORY, UPCALL_STUB_RELEASE);
    }

    private static final AtomicLong indexCounter = new AtomicLong();
    private static final ConcurrentHashMap<Long, PNIFunc<?>> funcStorage = new ConcurrentHashMap<>();
    private static final PNIFunc<?>[] funcFastStorage;
    private static final ReadWriteLock funcFastStorageLock = new ReentrantReadWriteLock();

    static {
        int len = 4096;
        final String KEY = "io.vproxy.pni.PNIFunc.funcFastStorage.length";
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
        funcFastStorage = new PNIFunc[len];
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
        MemoryLayout.PathElement.groupElement("userdata")
    );

    public MemorySegment getUserdata() {
        return (MemorySegment) userdataVH.get(MEMORY);
    }

    public void setUserdata(MemorySegment userdata) {
        userdataVH.set(MEMORY, userdata);
    }

    abstract protected T construct(MemorySegment seg);

    abstract protected MemorySegment getSegment(T t);

    private static PNIFunc<?> tryToFindFuncFast(long index) {
        if (funcFastStorage.length == 0) {
            return null;
        }
        int arrIdx = (int) (index % funcFastStorage.length);
        var rlock = funcFastStorageLock.readLock();
        rlock.lock();
        var func = funcFastStorage[arrIdx];
        if (func == null) {
            rlock.unlock();
            return null;
        }
        if (func.index != index) {
            func = null;
        }
        rlock.unlock();
        return func;
    }

    private static int call(long index, MemorySegment data) {
        PNIFunc<?> func = tryToFindFuncFast(index);
        if (func == null) {
            func = funcStorage.get(index);
        }
        if (func == null) {
            System.out.println("[PNI][WARN][PNIFunc#call] PNIFunc not found: index: " + index + ", data: " + data.address());
            return -1_000_000;
        }
        Object o;
        if (data.address() == 0) {
            o = null;
        } else {
            o = func.construct(data);
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
        if (funcFastStorage.length != 0) {
            int arrIdx = (int) (index % funcFastStorage.length);
            var wlock = funcFastStorageLock.writeLock();
            wlock.lock();
            var func = funcFastStorage[arrIdx];
            if (func != null && func.index == index) {
                funcFastStorage[arrIdx] = null;
            }
            wlock.unlock();
        }
        var func = funcStorage.remove(index);
        if (func == null) {
            System.out.println("[PNI][WARN][PNIFunc#release] PNIFunc not found: index: " + index);
            return;
        }
        SunUnsafe.freeMemory(func.MEMORY.address());
    }

    public static class VoidFunc extends PNIFunc<Void> {
        private VoidFunc(CallSite<Void> func) {
            super(func);
        }

        public static VoidFunc of(CallSite<Void> func) {
            return new VoidFunc(func);
        }

        @Override
        protected Void construct(MemorySegment seg) {
            return null;
        }

        @Override
        protected MemorySegment getSegment(Void v) {
            return MemorySegment.NULL;
        }
    }
}
