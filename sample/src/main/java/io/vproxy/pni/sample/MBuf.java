package io.vproxy.pni.sample;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class MBuf extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.ADDRESS.withName("bufAddr"),
        ValueLayout.JAVA_INT.withName("pktLen"),
        ValueLayout.JAVA_INT.withName("pktOff"),
        ValueLayout.JAVA_INT.withName("bufLen"),
        MemoryLayout.sequenceLayout(4L, ValueLayout.JAVA_BYTE) /* padding */,
        io.vproxy.pni.sample.UserData.LAYOUT.withName("userdata")
    ).withByteAlignment(8);
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

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
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("MBuf{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("bufAddr => ");
            SB.append(PanamaUtils.memorySegmentToString(getBufAddr()));
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("pktLen => ");
            SB.append(getPktLen());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("pktOff => ");
            SB.append(getPktOff());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("bufLen => ");
            SB.append(getBufLen());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("userdata => ");
            PanamaUtils.nativeObjectToString(getUserdata(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<MBuf> {
        public Array(MemorySegment buf) {
            super(buf, MBuf.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, MBuf.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, MBuf.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.sample.MBuf ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "MBuf.Array";
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

        private Func(io.vproxy.pni.CallSite<MBuf> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<MBuf> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<MBuf> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "MBuf.Func";
        }

        @Override
        protected MBuf construct(MemorySegment seg) {
            return new MBuf(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:653a0397152337fce36cd1186a223ec432cede27a5e036a71f0da2ee96ddf30f
