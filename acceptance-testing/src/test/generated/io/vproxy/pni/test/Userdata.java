package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class Userdata {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_INT_UNALIGNED.withName("x"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        ValueLayout.JAVA_LONG_UNALIGNED.withName("y"),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("z"),
        MemoryLayout.sequenceLayout(6L, ValueLayout.JAVA_BYTE) /* padding */
    );
    public final MemorySegment MEMORY;

    private static final VarHandle xVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("x")
    );

    public int getX() {
        return (int) xVH.get(MEMORY);
    }

    public void setX(int x) {
        xVH.set(MEMORY, x);
    }

    private static final VarHandle yVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("y")
    );

    public long getY() {
        return (long) yVH.get(MEMORY);
    }

    public void setY(long y) {
        yVH.set(MEMORY, y);
    }

    private static final VarHandle zVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("z")
    );

    public short getZ() {
        return (short) zVH.get(MEMORY);
    }

    public void setZ(short z) {
        zVH.set(MEMORY, z);
    }

    public Userdata(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
        OFFSET += 6; /* padding */
    }

    public Userdata(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<Userdata> {
        public Array(MemorySegment buf) {
            super(buf, Userdata.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(Userdata.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected Userdata construct(MemorySegment seg) {
            return new Userdata(seg);
        }

        @Override
        protected MemorySegment getSegment(Userdata value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<Userdata> {
        private Func(io.vproxy.pni.CallSite<Userdata> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<Userdata> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<Userdata> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<Userdata> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected Userdata construct(MemorySegment seg) {
            return new Userdata(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:9de1c0e8bcacfbe9dbaac5be1d9ba8197a926da3e289c6170f381078a903e127
