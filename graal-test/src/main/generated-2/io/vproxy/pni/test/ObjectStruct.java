package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;
import io.vproxy.pni.graal.*;
import org.graalvm.nativeimage.*;
import org.graalvm.nativeimage.c.function.*;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.WordFactory;

public class ObjectStruct extends AbstractNativeObject implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        ValueLayout.ADDRESS_UNALIGNED.withName("str"),
        MemoryLayout.sequenceLayout(16L, ValueLayout.JAVA_BYTE).withName("lenStr"),
        ValueLayout.ADDRESS_UNALIGNED.withName("seg"),
        PNIBuf.LAYOUT.withName("buf")
    );
    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private static final VarHandle strVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("str")
    );

    public PNIString getStr() {
        var SEG = (MemorySegment) strVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return new PNIString(SEG);
    }

    public void setStr(String str, Allocator ALLOCATOR) {
        this.setStr(new PNIString(ALLOCATOR, str));
    }

    public void setStr(PNIString str) {
        if (str == null) {
            strVH.set(MEMORY, MemorySegment.NULL);
        } else {
            strVH.set(MEMORY, str.MEMORY);
        }
    }

    private final MemorySegment lenStr;

    public String getLenStr() {
        return lenStr.getUtf8String(0);
    }

    public void setLenStr(String lenStr) {
        PanamaHack.setUtf8String(this.lenStr, 0, lenStr);
    }

    private static final VarHandle segVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("seg")
    );

    public MemorySegment getSeg() {
        var SEG = (MemorySegment) segVH.get(MEMORY);
        if (SEG.address() == 0) return null;
        return SEG;
    }

    public void setSeg(MemorySegment seg) {
        if (seg == null) {
            segVH.set(MEMORY, MemorySegment.NULL);
        } else {
            segVH.set(MEMORY, seg);
        }
    }

    private final PNIBuf buf;

    public ByteBuffer getBuf() {
        var SEG = this.buf.get();
        if (SEG == null) return null;
        return SEG.asByteBuffer();
    }

    public void setBuf(ByteBuffer buf) {
        if (buf == null) {
            this.buf.setToNull();
        } else {
            this.buf.set(buf);
        }
    }

    public ObjectStruct(MemorySegment MEMORY) {
        MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());
        this.MEMORY = MEMORY;
        long OFFSET = 0;
        OFFSET += 8;
        this.lenStr = MEMORY.asSlice(OFFSET, 16);
        OFFSET += 16;
        OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
        this.buf = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
        OFFSET += PNIBuf.LAYOUT.byteSize();
    }

    public ObjectStruct(Allocator ALLOCATOR) {
        this(ALLOCATOR.allocate(LAYOUT));
    }

    private static final MethodHandle func1MH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_ObjectStruct_func1", MemorySegment.class /* self */, String.class /* str */, String.class /* str2 */, MemorySegment.class /* seg */, PNIBuf.class /* buf */);

    public void func1(PNIEnv ENV, PNIString str, PNIString str2, MemorySegment seg, ByteBuffer buf) {
        ENV.reset();
        try (var POOLED = Allocator.ofPooled()) {
            int ERR;
            try {
                ERR = (int) func1MH.invokeExact(ENV.MEMORY, MEMORY, (MemorySegment) (str == null ? MemorySegment.NULL : str.MEMORY), (MemorySegment) (str2 == null ? MemorySegment.NULL : str2.MEMORY), (MemorySegment) (seg == null ? MemorySegment.NULL : seg), PanamaUtils.format(buf, POOLED));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (ERR != 0) {
                ENV.throwLast();
            }
        }
    }

    private static final MethodHandle func1CriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), void.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_func1Critical", MemorySegment.class /* self */, String.class /* str */, String.class /* str2 */, MemorySegment.class /* seg */, PNIBuf.class /* buf */);

    public void func1Critical(PNIString str, PNIString str2, MemorySegment seg, ByteBuffer buf) {
        try (var POOLED = Allocator.ofPooled()) {
            try {
                func1CriticalMH.invokeExact(MEMORY, (MemorySegment) (str == null ? MemorySegment.NULL : str.MEMORY), (MemorySegment) (str2 == null ? MemorySegment.NULL : str2.MEMORY), (MemorySegment) (seg == null ? MemorySegment.NULL : seg), PanamaUtils.format(buf, POOLED));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
        }
    }

    private static final MethodHandle retrieveStrMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_ObjectStruct_retrieveStr", MemorySegment.class /* self */);

    public PNIString retrieveStr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveStrMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new PNIString(RESULT);
    }

    private static final MethodHandle retrieveStrCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), String.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveStrCritical", MemorySegment.class /* self */);

    public PNIString retrieveStrCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) retrieveStrCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT.address() == 0 ? null : new PNIString(RESULT);
    }

    private static final MethodHandle retrieveLenStrMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_ObjectStruct_retrieveLenStr", MemorySegment.class /* self */);

    public PNIString retrieveLenStr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveLenStrMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        return RESULT == null ? null : new PNIString(RESULT);
    }

    private static final MethodHandle retrieveLenStrCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), String.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveLenStrCritical", MemorySegment.class /* self */);

    public PNIString retrieveLenStrCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) retrieveLenStrCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT.address() == 0 ? null : new PNIString(RESULT);
    }

    private static final MethodHandle retrieveSegMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_ObjectStruct_retrieveSeg", MemorySegment.class /* self */);

    public MemorySegment retrieveSeg(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveSegMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnPointer();
    }

    private static final MethodHandle retrieveSegCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), MemorySegment.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveSegCritical", MemorySegment.class /* self */);

    public MemorySegment retrieveSegCritical() {
        MemorySegment RESULT;
        try {
            RESULT = (MemorySegment) retrieveSegCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (RESULT.address() == 0) return null;
        return RESULT;
    }

    private static final MethodHandle retrieveBufMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_ObjectStruct_retrieveBuf", MemorySegment.class /* self */);

    public ByteBuffer retrieveBuf(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) retrieveBufMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RES_SEG = ENV.returnBuf();
        return RES_SEG.toByteBuffer();
    }

    private static final MethodHandle retrieveBufCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), PNIBuf.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_retrieveBufCritical", MemorySegment.class /* self */, MemorySegment.class /* return */);

    public ByteBuffer retrieveBufCritical() {
        try (var POOLED = Allocator.ofPooled()) {
            MemorySegment RESULT;
            try {
                RESULT = (MemorySegment) retrieveBufCriticalMH.invokeExact(MEMORY, POOLED.allocate(PNIBuf.LAYOUT));
            } catch (Throwable THROWABLE) {
                throw PanamaUtils.convertInvokeExactException(THROWABLE);
            }
            if (RESULT.address() == 0) return null;
            var RES_SEG = new PNIBuf(RESULT);
            return RES_SEG.toByteBuffer();
        }
    }

    private static final MethodHandle checkPointerSetToNonNullMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNonNull", MemorySegment.class /* self */);

    public boolean checkPointerSetToNonNull(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) checkPointerSetToNonNullMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private static final MethodHandle checkPointerSetToNonNullCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNonNullCritical", MemorySegment.class /* self */);

    public boolean checkPointerSetToNonNullCritical() {
        boolean RESULT;
        try {
            RESULT = (boolean) checkPointerSetToNonNullCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle checkPointerSetToNullMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNull", MemorySegment.class /* self */);

    public boolean checkPointerSetToNull(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) checkPointerSetToNullMH.invokeExact(ENV.MEMORY, MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        return ENV.returnBool();
    }

    private static final MethodHandle checkPointerSetToNullCriticalMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_ObjectStruct_checkPointerSetToNullCritical", MemorySegment.class /* self */);

    public boolean checkPointerSetToNullCritical() {
        boolean RESULT;
        try {
            RESULT = (boolean) checkPointerSetToNullCriticalMH.invokeExact(MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    @Override
    public void toString(StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
        if (!VISITED.add(new NativeObjectTuple(this))) {
            SB.append("<...>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        SB.append("ObjectStruct{\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("str => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else PanamaUtils.nativeObjectToString(getStr(), SB, INDENT + 4, VISITED, CORRUPTED_MEMORY);
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("lenStr => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else SB.append(getLenStr());
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("seg => ");
            SB.append(PanamaUtils.memorySegmentToString(getSeg()));
        }
        SB.append(",\n");
        {
            SB.append(" ".repeat(INDENT + 4)).append("buf => ");
            if (CORRUPTED_MEMORY) SB.append("<?>");
            else SB.append(PanamaUtils.byteBufferToString(getBuf()));
        }
        SB.append("\n");
        SB.append(" ".repeat(INDENT)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }

    public static class Array extends RefArray<ObjectStruct> {
        public Array(MemorySegment buf) {
            super(buf, ObjectStruct.LAYOUT);
        }

        public Array(Allocator allocator, long len) {
            super(allocator, ObjectStruct.LAYOUT, len);
        }

        public Array(PNIBuf buf) {
            super(buf, ObjectStruct.LAYOUT);
        }

        @Override
        protected void elementToString(io.vproxy.pni.test.ObjectStruct ELEM, StringBuilder SB, int INDENT, java.util.Set<NativeObjectTuple> VISITED, boolean CORRUPTED_MEMORY) {
            ELEM.toString(SB, INDENT, VISITED, CORRUPTED_MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ObjectStruct.Array";
        }

        @Override
        protected ObjectStruct construct(MemorySegment seg) {
            return new ObjectStruct(seg);
        }

        @Override
        protected MemorySegment getSegment(ObjectStruct value) {
            return value.MEMORY;
        }
    }

    public static class Func extends PNIFunc<ObjectStruct> {
        private Func(io.vproxy.pni.CallSite<ObjectStruct> func) {
            super(func);
        }

        private Func(io.vproxy.pni.CallSite<ObjectStruct> func, Options opts) {
            super(func, opts);
        }

        private Func(MemorySegment MEMORY) {
            super(MEMORY);
        }

        public static Func of(io.vproxy.pni.CallSite<ObjectStruct> func) {
            return new Func(func);
        }

        public static Func of(io.vproxy.pni.CallSite<ObjectStruct> func, Options opts) {
            return new Func(func, opts);
        }

        public static Func of(MemorySegment MEMORY) {
            return new Func(MEMORY);
        }

        @Override
        protected String toStringTypeName() {
            return "ObjectStruct.Func";
        }

        @Override
        protected ObjectStruct construct(MemorySegment seg) {
            return new ObjectStruct(seg);
        }
    }
}
// metadata.generator-version: pni test
// sha256:f3147df3b1236b08efbfd3088fb01b5da740f4e68329ec1dd307c210e2efdbc9
