package io.vproxy.pni.test;

import io.vproxy.pni.exec.ast.BitFieldInfo;
import io.vproxy.pni.exec.internal.PointerInfo;
import io.vproxy.pni.exec.internal.VarOpts;
import io.vproxy.pni.exec.type.ArrayTypeInfo;
import io.vproxy.pni.exec.type.ByteTypeInfo;
import io.vproxy.pni.exec.type.VoidTypeInfo;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestCorner {
    @Test
    public void typeInfoToString() {
        var info = ByteTypeInfo.get();
        assertEquals("byte", info.toString());
    }

    private static VarOpts emptyParamVarOpts() {
        return VarOpts.of(false, PointerInfo.ofMethod(false), -1);
    }

    private VarOpts emptyFieldVarOpts() {
        return VarOpts.of(false, PointerInfo.ofField(false), -1);
    }

    private VarOpts fieldVarOptsWithLen(int len) {
        return VarOpts.of(false, PointerInfo.ofField(false), len);
    }

    @Test
    public void voidTypeInfo() {
        var v = VoidTypeInfo.get();
        assertEquals("void", v.name());
        assertEquals("void", v.internalName());
        assertEquals("V", v.desc());
        assertEquals("void", v.nativeEnvType(emptyParamVarOpts()));
        assertEquals("void", v.nativeReturnType(emptyParamVarOpts()));
        Utils.checkUnsupported(() -> v.nativeMemorySize(emptyParamVarOpts()));
        Utils.checkUnsupported(() -> v.nativeMemoryAlign(emptyParamVarOpts()));
        Utils.checkUnsupported(() -> v.memoryLayoutForField(emptyParamVarOpts()));
        assertEquals("void", v.javaTypeForField(emptyParamVarOpts()));
        assertEquals("void", v.javaTypeForUpcallParam(emptyParamVarOpts()));
        Utils.checkUnsupported(() -> v.generateGetterSetter(new StringBuilder(), 0, "a", emptyParamVarOpts()));
        Utils.checkUnsupported(() -> v.generateConstructor(new StringBuilder(), 0, "a", emptyParamVarOpts()));
        assertEquals("void.class", v.methodHandleType(emptyParamVarOpts()));
        assertEquals("void.class", v.methodHandleTypeForUpcall(emptyParamVarOpts()));
        Utils.checkUnsupported(() -> v.convertParamToInvokeExactArgument("a", emptyParamVarOpts()));
        {
            var sb = new StringBuilder();
            v.convertInvokeExactReturnValueToJava(sb, 0, emptyParamVarOpts());
            assertTrue(sb.isEmpty());
        }
        Utils.checkUnsupported(() -> v.convertToUpcallArgument("a", emptyParamVarOpts()));
        Utils.checkUnsupported(() -> v.convertFromUpcallReturn(new StringBuilder(), 0, emptyParamVarOpts()));
        Utils.checkUnsupported(() -> v.javaToString(new StringBuilder(), 0, "", fieldVarOptsWithLen(-1)));
    }

    @Test
    public void invalidArray() {
        var errors = new ArrayList<String>();
        var arr = new ArrayTypeInfo(VoidTypeInfo.get());
        arr.checkType(errors, "?", emptyParamVarOpts(), false);
        assertEquals(1, errors.size());
        assertEquals("?: void[] is not supported, only primitive and custom types can be used with array", errors.get(0));
        try {
            arr.generateConstructor(new StringBuilder(), 0, "a", fieldVarOptsWithLen(3));
            fail();
        } catch (RuntimeException e) {
            assertEquals("unable to handle array with element type void", e.getMessage());
        }
        try {
            arr.javaTypeForField(fieldVarOptsWithLen(3));
            fail();
        } catch (RuntimeException e) {
            assertEquals("unable to handle array with element type void", e.getMessage());
        }
        try {
            arr.convertToUpcallArgument("a", fieldVarOptsWithLen(3));
            fail();
        } catch (RuntimeException e) {
            assertEquals("unable to handle array with element type void", e.getMessage());
        }
    }

    @Test
    public void bitField() {
        var info = Utils.generalClsTypeInfo();
        Utils.checkUnsupported(() -> info.generateBitFieldGetterSetter(new StringBuilder(), 0, "a", new BitFieldInfo("x", 5, 3), emptyFieldVarOpts()));
    }
}
