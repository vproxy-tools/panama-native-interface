package io.vproxy.pni.sample;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;

public class UserData {
    public static final MemoryLayout LAYOUT = MemoryLayout.unionLayout(
        ValueLayout.ADDRESS_UNALIGNED.withName("userdata"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("udata64")
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

    public static class Array extends RefArray<UserData> {
        public Array(MemorySegment buf) {
            super(buf, UserData.LAYOUT);
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
}
// sha256:9f05988aea67d64459922e3f4df597147817f9947566b84f7e63cf27609e6408
