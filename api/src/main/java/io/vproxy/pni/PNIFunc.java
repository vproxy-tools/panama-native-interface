package io.vproxy.pni;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

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
    private final Arena arena;
    public final MemorySegment MEMORY;
    private final CallSite<T> func;

    protected PNIFunc(CallSite<T> func) {
        Objects.requireNonNull(func);

        arena = Arena.ofShared();
        MEMORY = arena.allocate(LAYOUT.byteSize());
        this.func = func;

        long index = indexCounter.incrementAndGet();
        indexVH.set(MEMORY, index);
        funcStorage.put(index, this);

        funcVH.set(MEMORY, UPCALL_STUB_CALL);
        releaseVH.set(MEMORY, UPCALL_STUB_RELEASE);
    }

    private static final AtomicLong indexCounter = new AtomicLong();
    private static final ConcurrentHashMap<Long, PNIFunc<?>> funcStorage = new ConcurrentHashMap<>();

    private static final VarHandle indexVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("index")
    );
    private static final VarHandle funcVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("func")
    );
    private static final VarHandle releaseVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("release")
    );

    abstract protected T construct(MemorySegment seg);

    abstract protected MemorySegment getSegment(T t);

    private static int call(long index, MemorySegment data) {
        var func = funcStorage.get(index);
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
        //noinspection unchecked,rawtypes
        return ((CallSite) func.func).call(o);
    }

    private static void release(long index) {
        var func = funcStorage.remove(index);
        if (func == null) {
            System.out.println("[PNI][WARN][PNIFunc#release] PNIFunc not found: index: " + index);
        }
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
