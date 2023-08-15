package io.vproxy.pni.sample;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class MBuf {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.ADDRESS_UNALIGNED.withName("bufAddr"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("pktLen"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("pktOff"),
        ValueLayout.JAVA_INT_UNALIGNED.withName("bufLen"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        io.vproxy.pni.sample.UserData.LAYOUT.withName("userdata")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle bufAddrVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("bufAddr")
    );

    public MemorySegment getBufAddr() {
        var SEG = (MemorySegment) bufAddrVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return SEG;
    }

    public void setBufAddr(MemorySegment bufAddr) {
        if (bufAddr == null) {
            bufAddrVH.set(MEMORY, MemorySegment.NULL);
        } else {
            bufAddrVH.set(MEMORY, bufAddr);
        }
    }

    private static final VarHandle pktLenVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("pktLen")
    );

    public int getPktLen() {
        return (int) pktLenVH.get(MEMORY);
    }

    public void setPktLen(int pktLen) {
        pktLenVH.set(MEMORY, pktLen);
    }

    private static final VarHandle pktOffVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("pktOff")
    );

    public int getPktOff() {
        return (int) pktOffVH.get(MEMORY);
    }

    public void setPktOff(int pktOff) {
        pktOffVH.set(MEMORY, pktOff);
    }

    private static final VarHandle bufLenVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("bufLen")
    );

    public int getBufLen() {
        return (int) bufLenVH.get(MEMORY);
    }

    public void setBufLen(int bufLen) {
        bufLenVH.set(MEMORY, bufLen);
    }

    private final io.vproxy.pni.sample.UserData userdata;

    public io.vproxy.pni.sample.UserData getUserdata() {
        return this.userdata;
    }

    public MBuf(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
        OFFSET += 4; /* padding */
        this.userdata = new io.vproxy.pni.sample.UserData(MEMORY.asSlice(OFFSET, io.vproxy.pni.sample.UserData.LAYOUT.byteSize()));
        OFFSET += io.vproxy.pni.sample.UserData.LAYOUT.byteSize();
    }

    public MBuf(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    public static class Array extends RefArray<MBuf> {
        public Array(MemorySegment buf) {
            super(buf, MBuf.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(MBuf.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected MBuf construct(MemorySegment seg) {
            return new MBuf(seg);
        }

        @Override
        protected MemorySegment getSegment(MBuf value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<MBuf> {
        private Func(io.vproxy.pni.CallSite<MBuf> func) {
            super(func);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<MBuf> func) {
            return new Func(func);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected MBuf construct(MemorySegment seg) {
            return new MBuf(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:9be78ea65bbdd6f1d264bcf2a0a502b33c56af3b8684c25245acd47326adcecb
