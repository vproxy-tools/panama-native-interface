package io.vproxy.pni.test;

import io.vproxy.pni.exec.internal.VarOpts;
import io.vproxy.pni.exec.type.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestTypesNoGen {
    @Test
    public void AnnoAlign() {
        var t = AnnoAlignTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Align", t.name());
        assertEquals("io/vproxy/pni/annotation/Align", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Align;", t.desc());
    }

    @Test
    public void AnnoAlwaysAligned() {
        var t = AnnoAlwaysAlignedTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.AlwaysAligned", t.name());
        assertEquals("io/vproxy/pni/annotation/AlwaysAligned", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/AlwaysAligned;", t.desc());
    }

    @Test
    public void AnnoBitField() {
        var t = AnnoBitFieldTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.BitField", t.name());
        assertEquals("io/vproxy/pni/annotation/BitField", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/BitField;", t.desc());
    }

    @Test
    public void AnnoCritical() {
        var t = AnnoCriticalTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Critical", t.name());
        assertEquals("io/vproxy/pni/annotation/Critical", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Critical;", t.desc());
    }

    @Test
    public void AnnoFunction() {
        var t = AnnoFunctionTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Function", t.name());
        assertEquals("io/vproxy/pni/annotation/Function", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Function;", t.desc());
    }

    @Test
    public void AnnoImpl() {
        var t = AnnoImplTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Impl", t.name());
        assertEquals("io/vproxy/pni/annotation/Impl", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Impl;", t.desc());
    }

    @Test
    public void AnnoInclude() {
        var t = AnnoIncludeTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Include", t.name());
        assertEquals("io/vproxy/pni/annotation/Include", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Include;", t.desc());
    }

    @Test
    public void AnnoLen() {
        var t = AnnoLenTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Len", t.name());
        assertEquals("io/vproxy/pni/annotation/Len", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Len;", t.desc());
    }

    @Test
    public void AnnoName() {
        var t = AnnoNameTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Name", t.name());
        assertEquals("io/vproxy/pni/annotation/Name", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Name;", t.desc());
    }

    @Test
    public void AnnoNativeReturnType() {
        var t = AnnoNativeReturnTypeTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.NativeReturnType", t.name());
        assertEquals("io/vproxy/pni/annotation/NativeReturnType", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/NativeReturnType;", t.desc());
    }

    @Test
    public void AnnoNativeType() {
        var t = AnnoNativeTypeTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.NativeType", t.name());
        assertEquals("io/vproxy/pni/annotation/NativeType", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/NativeType;", t.desc());
    }

    @Test
    public void AnnoNoAllocType() {
        var t = AnnoNoAllocTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.NoAlloc", t.name());
        assertEquals("io/vproxy/pni/annotation/NoAlloc", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/NoAlloc;", t.desc());
    }

    @Test
    public void AnnoPointerOnly() {
        var t = AnnoPointerOnlyTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.PointerOnly", t.name());
        assertEquals("io/vproxy/pni/annotation/PointerOnly", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/PointerOnly;", t.desc());
    }

    @Test
    public void AnnoPointer() {
        var t = AnnoPointerTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Pointer", t.name());
        assertEquals("io/vproxy/pni/annotation/Pointer", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Pointer;", t.desc());
    }

    @Test
    public void AnnoRaw() {
        var t = AnnoRawTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Raw", t.name());
        assertEquals("io/vproxy/pni/annotation/Raw", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Raw;", t.desc());
    }

    @Test
    public void AnnoSizeof() {
        var t = AnnoSizeofTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Sizeof", t.name());
        assertEquals("io/vproxy/pni/annotation/Sizeof", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Sizeof;", t.desc());
    }

    @Test
    public void AnnoStruct() {
        var t = AnnoStructTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Struct", t.name());
        assertEquals("io/vproxy/pni/annotation/Struct", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Struct;", t.desc());
    }

    @Test
    public void AnnoSuppress() {
        var t = AnnoSuppressTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Suppress", t.name());
        assertEquals("io/vproxy/pni/annotation/Suppress", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Suppress;", t.desc());
    }

    @Test
    public void AnnoTrivial() {
        var t = AnnoTrivialTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Trivial", t.name());
        assertEquals("io/vproxy/pni/annotation/Trivial", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Trivial;", t.desc());
    }

    @Test
    public void AnnoUnion() {
        var t = AnnoUnionTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Union", t.name());
        assertEquals("io/vproxy/pni/annotation/Union", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Union;", t.desc());
    }

    @Test
    public void AnnoUnsigned() {
        var t = AnnoUnsignedTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Unsigned", t.name());
        assertEquals("io/vproxy/pni/annotation/Unsigned", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Unsigned;", t.desc());
    }

    @Test
    public void AnnoUpcall() {
        var t = AnnoUpcallTypeInfo.get();
        assertEquals("io.vproxy.pni.annotation.Upcall", t.name());
        assertEquals("io/vproxy/pni/annotation/Upcall", t.internalName());
        assertEquals("Lio/vproxy/pni/annotation/Upcall;", t.desc());
    }

    @Test
    public void BuiltInException() {
        var t = new BuiltInExceptionTypeInfo("java.lang.Exception", "java/lang/Exception", "Ljava/lang/Exception;");
        assertEquals("java.lang.Exception", t.name());
        assertEquals("java/lang/Exception", t.internalName());
        assertEquals("Ljava/lang/Exception;", t.desc());
    }

    private void checkUnsupported(Runnable r) {
        try {
            r.run();
            fail("no UnsupportedOperationException");
        } catch (UnsupportedOperationException ignore) {
        }
    }

    @Test
    public void NoGenReference() {
        var t = new BuiltInExceptionTypeInfo("java.lang.Exception", "java/lang/Exception", "Ljava/lang/Exception;");
        checkUnsupported(() -> t.nativeEnvType(VarOpts.fieldDefault()));
        checkUnsupported(() -> t.nativeType("a", VarOpts.fieldDefault()));
        checkUnsupported(() -> t.nativeMemorySize(VarOpts.fieldDefault()));
        checkUnsupported(() -> t.nativeMemoryAlign(VarOpts.fieldDefault()));
        checkUnsupported(() -> t.memoryLayoutForField(VarOpts.fieldDefault()));
        checkUnsupported(() -> t.javaTypeForField(VarOpts.fieldDefault()));
        checkUnsupported(() -> t.javaTypeForUpcallParam(VarOpts.fieldDefault()));
        checkUnsupported(() -> t.generateGetterSetter(new StringBuilder(), 0, "a", VarOpts.fieldDefault()));
        checkUnsupported(() -> t.generateConstructor(new StringBuilder(), 0, "a", VarOpts.fieldDefault()));
        checkUnsupported(() -> t.methodHandleType(VarOpts.paramDefault()));
        checkUnsupported(() -> t.methodHandleTypeForUpcall(VarOpts.paramDefault()));
        checkUnsupported(() -> t.convertParamToInvokeExactArgument("a", VarOpts.paramDefault()));
        checkUnsupported(() -> t.convertInvokeExactReturnValueToJava(new StringBuilder(), 0, VarOpts.returnDefault()));
        checkUnsupported(() -> t.convertToUpcallArgument("a", VarOpts.paramDefault()));
        checkUnsupported(() -> t.convertFromUpcallReturn(new StringBuilder(), 0, VarOpts.returnDefault()));
        checkUnsupported(() -> t.convertFromUpcallReturnGraal(new StringBuilder(), 0, VarOpts.returnDefault()));
        checkUnsupported(() -> t.javaToString(new StringBuilder(), 0, "", VarOpts.fieldDefault()));
    }
}
