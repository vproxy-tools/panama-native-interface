package io.vproxy.pni.sample;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class UserData {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
        ValueLayout.ADDRESS.withName("userdata"),
        ValueLayout.JAVA_LONG.withName("udata64")
    );
    public final MemorySegment MEMORY;

    private static final VarHandle userdataVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("userdata")
    );

    public MemorySegment getUserdata() {
        var SEG = (MemorySegment) userdataVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return SEG;
    }

    public void setUserdata(MemorySegment userdata) {
        if (userdata == null) {
            userdataVH.set(MEMORY, MemorySegment.NULL);
        } else {
            userdataVH.set(MEMORY, userdata);
        }
    }

    private static final VarHandle udata64VH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("udata64")
    );

    public long getUdata64() {
        return (long) udata64VH.get(MEMORY);
    }

    public void setUdata64(long udata64) {
        udata64VH.set(MEMORY, udata64);
    }

    public UserData(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        OFFSET = 0;
        OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
        OFFSET = 0;
    }

    public UserData(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT.byteSize()));
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new java.util.HashSet<>(), true);
        return sb.toString();
    }

    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(getClass(), MEMORY.address()))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        CORRUPTED_MEMORY = true;
        SB.append("UserData(\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("userdata => ");
            SB.append(PanamaUtils.memorySegmentToString(getUserdata()));
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("udata64 => ");
            SB.append(getUdata64());
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append(")@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<UserData> {
        public Array(MemorySegment buf) {
            super(buf, UserData.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            this(allocator.allocate(UserData.LAYOUT.byteSize() * len));
        }

        public Array(PNIBuf buf) {
            this(buf.get());
        }

        @Override
        protected void elementToString(io.vproxy.pni.sample.UserData ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "UserData.Array";
        }

        @Override
        protected UserData construct(MemorySegment seg) {
            return new UserData(seg);
        }

        @Override
        protected MemorySegment getSegment(UserData value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<UserData> {
        private Func(io.vproxy.pni.CallSite<UserData> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<UserData> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<UserData> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<UserData> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "UserData.Func";
        }

        @Override
        protected UserData construct(MemorySegment seg) {
            return new UserData(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:de3efba06987629ec1cf2c744898966b41811b205273c248bff2bc861a622b7d
