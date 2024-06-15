package io.vproxy.pni.sample;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import io.vproxy.pni.hack.VarHandleW;

import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class UserData extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
        ValueLayout.ADDRESS.withName("userdata"),
        ValueLayout.JAVA_LONG.withName("udata64")
    ).withByteAlignment(8);
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandleW userdataVH = VarHandleW.of(LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("userdata")
    ));

    public MemorySegment getUserdata() {
        var SEG = userdataVH.getMemorySegment(MEMORY);
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

    private static final VarHandleW udata64VH = VarHandleW.of(LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("udata64")
    ));

    public long getUdata64() {
        return udata64VH.getLong(MEMORY);
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
        this(ALLOCATOR.allocate(LAYOUT));
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
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
            super(allocator, UserData.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, UserData.LAYOUT);
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
// sha256:c8a4c312f33c33a296cef69094e663ff5d9cdaa4f26c9b5812dabc2d21461fe8
