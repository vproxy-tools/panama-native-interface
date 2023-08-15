package io.vproxy.pni;

import io.vproxy.pni.unsafe.SunUnsafe;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;

public class PNIRef<T> {
    private static final Arena UPCALL_STUB_ARENA = Arena.ofShared(); // should not be released
    private static final MemorySegment UPCALL_STUB_RELEASE;

    static {
        MethodHandle releaseMethodHandle;
        try {
            releaseMethodHandle = MethodHandles.lookup()
                .findStatic(PNIRef.class,
                    "release",
                    MethodType.methodType(void.class, long.class));
        } catch (Throwable e) {
            throw new RuntimeException(e); // should not happen
        }

        UPCALL_STUB_RELEASE = Linker.nativeLinker().upcallStub(
            releaseMethodHandle,
            FunctionDescriptor.ofVoid(ValueLayout.JAVA_LONG),
            UPCALL_STUB_ARENA);
    }

    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_LONG_UNALIGNED.withName("index"),
        ValueLayout.ADDRESS_UNALIGNED.withName("release"),
        MemoryLayout.unionLayout(
            ValueLayout.ADDRESS_UNALIGNED.withName("userdata"),
            ValueLayout.JAVA_LONG_UNALIGNED.withName("udata64")
        ).withName("union0")
    );
    public final MemorySegment MEMORY;

    private final long index;
    private final T ref;

    private PNIRef(T ref) {
        // ref is nullable
        MEMORY = SunUnsafe.allocateMemory(LAYOUT.byteSize());
        this.ref = ref;

        long index = holder.store(this);
        this.index = index;
        indexVH.set(MEMORY, index);

        releaseVH.set(MEMORY, UPCALL_STUB_RELEASE);
    }

    public static <T> PNIRef<T> of(T ref) {
        return new PNIRef<>(ref);
    }

    public static <T> PNIRef<T> of(MemorySegment seg) {
        return new PNIRef<>(seg);
    }

    private PNIRef(MemorySegment MEMORY) {
        this.MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.index = 0;
        this.ref = null;
    }

    private static final ObjectHolder<PNIRef<?>> holder;

    static {
        int len = 4096;
        final String KEY = "io.vproxy.pni.PNIRef.holder.fastStorage.length";
        var strLen = System.getProperty(KEY, "4096");
        try {
            len = Integer.parseInt(strLen);
        } catch (NumberFormatException e) {
            System.out.println("[PNI][WARN][PNIRef#cinit] invalid " + KEY + ": not a number " + strLen);
        }
        if (len < 0) {
            System.out.println("[PNI][WARN][PNIRef#cinit] invalid " + KEY + ": value should >= 0: " + len + ", the value is modified to 0");
            len = 0;
        }
        holder = new ObjectHolder<>(len) {
            @Override
            protected long getObjectIndex(PNIRef<?> obj) {
                return obj.index;
            }
        };
    }

    public static long currentRefStorageSize() {
        return holder.size();
    }

    private static final VarHandle indexVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("index")
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

    public T getRef() {
        if (this.ref != null) {
            return this.ref;
        }

        return getRef(MEMORY);
    }

    public static <T> T getRef(MemorySegment seg) {
        seg = seg.reinterpret(LAYOUT.byteSize());
        var index = (long) indexVH.get(seg);
        PNIRef<?> ref = holder.get(index);
        if (ref == null) {
            throw new NullPointerException("index = " + index);
        }
        //noinspection unchecked
        return (T) ref.ref;
    }

    private static void release(long index) {
        var removed = holder.remove(index);
        if (removed == null) {
            System.out.println("[PNI][WARN][PNIRef#release] PNIRef not found: index: " + index);
            return;
        }
        SunUnsafe.freeMemory(removed.MEMORY.address());
    }

    public void close() {
        release(getIndex());
    }

    public static class Func<T> extends PNIFunc<T> {
        private Func(CallSite<T> func) {
            super(func);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static <T> Func<T> of(CallSite<T> func) {
            return new Func<>(func);
        }

        public static <T> Func<T> of(MemorySegment MEMORY) {
            return new Func<>(MEMORY);
        }

        @Override
        protected T construct(MemorySegment seg) {
            return getRef(seg);
        }
    }
}
