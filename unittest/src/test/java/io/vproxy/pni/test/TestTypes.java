package io.vproxy.pni.test;

import io.vproxy.pni.exec.ast.AstTypeDesc;
import io.vproxy.pni.exec.internal.AllocationForParam;
import io.vproxy.pni.exec.internal.AllocationForReturnedValue;
import io.vproxy.pni.exec.internal.PointerInfo;
import io.vproxy.pni.exec.internal.VarOpts;
import io.vproxy.pni.exec.type.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TestTypes {
    private final List<String> errors = new ArrayList<>();

    private void checkError(Runnable r, String... errs) {
        r.run();
        assertEquals(String.join("\n", errors), errs.length, errors.size());
        assertEquals(Arrays.asList(errs), errors);
        errors.clear();
    }

    private void checkUnsupported(Runnable r, String msg) {
        try {
            r.run();
            fail("no UnsupportedOperationException thrown");
        } catch (UnsupportedOperationException e) {
            if (msg != null) {
                assertEquals(msg, e.getMessage());
            }
        }
    }

    private static final int UNSIGNED = 0x01;
    private static final int POINTER = 0x02;
    private static final int LEN = 0x04;
    private static final int CRITICAL = 0x08;
    private static final int RAW = 0x10;

    private VarOpts fieldVarOpts(int flags) {
        return VarOpts.of(
            (flags & UNSIGNED) == UNSIGNED,
            PointerInfo.ofField((flags & POINTER) == POINTER),
            (flags & LEN) == LEN ? 3 : -1);
    }

    private VarOpts paramVarOpts(int flags) {
        return VarOpts.of(
            (flags & UNSIGNED) == UNSIGNED,
            PointerInfo.ofMethod((flags & POINTER) == POINTER),
            (flags & LEN) == LEN ? 3 : -1,
            (flags & RAW) == RAW);
    }

    private VarOpts returnVarOpts(int flags) {
        return VarOpts.ofReturn((flags & CRITICAL) == CRITICAL);
    }

    private void checkTypeField(TypeInfo info, int flags) {
        checkError(() -> info.checkType(errors, "?", fieldVarOpts(0), false));
        if ((flags & UNSIGNED) == UNSIGNED) {
            checkError(() -> info.checkType(errors, "?", fieldVarOpts(UNSIGNED), false));
        } else {
            checkError(() -> info.checkType(errors, "?", fieldVarOpts(UNSIGNED), false), "?: " + info.name() + " cannot be marked with @Unsigned");
        }
        if ((flags & POINTER) == POINTER) {
            checkError(() -> info.checkType(errors, "?", fieldVarOpts(POINTER), false));
        } else {
            checkError(() -> info.checkType(errors, "?", fieldVarOpts(POINTER), false), "?: " + info.name() + " cannot be marked with @Pointer");
        }
        if ((flags & LEN) == LEN) {
            checkError(() -> info.checkType(errors, "?", fieldVarOpts(LEN), false));
        } else {
            checkError(() -> info.checkType(errors, "?", fieldVarOpts(LEN), false), "?: " + info.name() + " cannot be marked with @Len");
        }
        info.checkType(errors, "?", fieldVarOpts(POINTER | LEN), false);
        assertFalse(errors.isEmpty());
        var last = errors.get(errors.size() - 1);
        assertEquals("?: " + info.name() + " cannot be marked with @Pointer because it is marked with @Len", last);
        errors.clear();
    }

    private void checkTypeParam(TypeInfo info, int flags) {
        checkError(() -> info.checkType(errors, "?", paramVarOpts(0), false));
        if ((flags & UNSIGNED) == UNSIGNED) {
            checkError(() -> info.checkType(errors, "?", paramVarOpts(UNSIGNED), false));
        } else {
            checkError(() -> info.checkType(errors, "?", paramVarOpts(UNSIGNED), false), "?: " + info.name() + " cannot be marked with @Unsigned");
        }
        if ((flags & POINTER) == POINTER) {
            checkError(() -> info.checkType(errors, "?", paramVarOpts(POINTER), false));
        } else {
            checkError(() -> info.checkType(errors, "?", paramVarOpts(POINTER), false), "?: " + info.name() + " cannot be marked with @Pointer");
        }
        if ((flags & LEN) == LEN) {
            checkError(() -> info.checkType(errors, "?", paramVarOpts(LEN), false),
                "?: " + info.name() + " cannot be marked with @Len because it is pointer by default");
        } else {
            checkError(() -> info.checkType(errors, "?", paramVarOpts(LEN), false),
                "?: " + info.name() + " cannot be marked with @Len",
                "?: " + info.name() + " cannot be marked with @Len because it is pointer by default");
        }
        if ((flags & RAW) == RAW) {
            checkError(() -> info.checkType(errors, "?", paramVarOpts(RAW), false));
        } else {
            checkError(() -> info.checkType(errors, "?", paramVarOpts(RAW), false), "?: " + info.name() + " cannot be marked with @Raw");
        }
        info.checkType(errors, "?", paramVarOpts(POINTER | LEN), false);
        assertFalse(errors.isEmpty());
        var last = errors.get(errors.size() - 1);
        assertEquals("?: " + info.name() + " cannot be marked with @Pointer because it is marked with @Len", last);
        errors.clear();
    }

    @Test
    public void byteTypeInfo() {
        var info = ByteTypeInfo.get();
        assertEquals("byte", info.name());
        assertEquals("byte", info.internalName());
        assertEquals("B", info.desc());
        checkTypeField(info, UNSIGNED);
        checkTypeParam(info, UNSIGNED);
        assertEquals("byte", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("int8_t a", info.nativeType("a", fieldVarOpts(0)));
        assertEquals("uint8_t a", info.nativeType("a", fieldVarOpts(UNSIGNED)));
        assertEquals("int8_t a", info.nativeParamType("a", paramVarOpts(0)));
        assertEquals("uint8_t a", info.nativeParamType("a", paramVarOpts(UNSIGNED)));
        assertEquals("int8_t", info.nativeReturnType(returnVarOpts(0)));
        assertEquals(1, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(1, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals("ValueLayout.JAVA_BYTE", info.memoryLayoutForField(fieldVarOpts(0)));
        assertEquals("byte", info.javaTypeForField(fieldVarOpts(0)));
        assertEquals("byte", info.javaTypeForParam(paramVarOpts(0)));
        assertEquals("byte", info.javaTypeForReturn(returnVarOpts(0)));
        assertEquals(
            """
                private static final VarHandle aVH = LAYOUT.varHandle(
                    MemoryLayout.PathElement.groupElement("a")
                );
                                
                public byte getA() {
                    return (byte) aVH.get(MEMORY);
                }
                                
                public void setA(byte a) {
                    aVH.set(MEMORY, a);
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                OFFSET += ValueLayout.JAVA_BYTE.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals("byte.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("a", info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals(AllocationForReturnedValue.noAllocationRequired(),
            info.allocationInfoForReturnValue(returnVarOpts(0)));
        assertEquals(
            """
                return ENV.returnByte();
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals(
            """
                return RESULT;
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(paramVarOpts(0)));
    }

    @Test
    public void boolTypeInfo() {
        var info = BooleanTypeInfo.get();
        assertEquals("boolean", info.name());
        assertEquals("boolean", info.internalName());
        assertEquals("Z", info.desc());
        checkTypeField(info, 0);
        checkTypeParam(info, 0);
        assertEquals("bool", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("uint8_t a", info.nativeType("a", fieldVarOpts(0)));
        assertEquals("uint8_t a", info.nativeParamType("a", paramVarOpts(0)));
        assertEquals("uint8_t", info.nativeReturnType(returnVarOpts(0)));
        assertEquals(1, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(1, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals("ValueLayout.JAVA_BOOLEAN", info.memoryLayoutForField(fieldVarOpts(0)));
        assertEquals("boolean", info.javaTypeForField(fieldVarOpts(0)));
        assertEquals("boolean", info.javaTypeForParam(paramVarOpts(0)));
        assertEquals("boolean", info.javaTypeForReturn(returnVarOpts(0)));
        assertEquals(
            """
                private static final VarHandle aVH = LAYOUT.varHandle(
                    MemoryLayout.PathElement.groupElement("a")
                );
                                
                public boolean getA() {
                    return (boolean) aVH.get(MEMORY);
                }
                                
                public void setA(boolean a) {
                    aVH.set(MEMORY, a);
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                OFFSET += ValueLayout.JAVA_BOOLEAN.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals("boolean.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("a", info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals(AllocationForReturnedValue.noAllocationRequired(),
            info.allocationInfoForReturnValue(returnVarOpts(0)));
        assertEquals(
            """
                return ENV.returnBool();
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals(
            """
                return RESULT;
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(paramVarOpts(0)));
    }

    @Test
    public void charTypeInfo() {
        var info = CharTypeInfo.get();
        assertEquals("char", info.name());
        assertEquals("char", info.internalName());
        assertEquals("C", info.desc());
        checkTypeField(info, 0);
        checkTypeParam(info, 0);
        assertEquals("char", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("uint16_t a", info.nativeType("a", fieldVarOpts(0)));
        assertEquals("uint16_t a", info.nativeParamType("a", paramVarOpts(0)));
        assertEquals("uint16_t", info.nativeReturnType(returnVarOpts(0)));
        assertEquals(2, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(2, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals("ValueLayout.JAVA_CHAR_UNALIGNED", info.memoryLayoutForField(fieldVarOpts(0)));
        assertEquals("char", info.javaTypeForField(fieldVarOpts(0)));
        assertEquals("char", info.javaTypeForParam(paramVarOpts(0)));
        assertEquals("char", info.javaTypeForReturn(returnVarOpts(0)));
        assertEquals(
            """
                private static final VarHandle aVH = LAYOUT.varHandle(
                    MemoryLayout.PathElement.groupElement("a")
                );
                                
                public char getA() {
                    return (char) aVH.get(MEMORY);
                }
                                
                public void setA(char a) {
                    aVH.set(MEMORY, a);
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                OFFSET += ValueLayout.JAVA_CHAR_UNALIGNED.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals("char.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("a", info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals(AllocationForReturnedValue.noAllocationRequired(),
            info.allocationInfoForReturnValue(returnVarOpts(0)));
        assertEquals(
            """
                return ENV.returnChar();
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals(
            """
                return RESULT;
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(paramVarOpts(0)));
    }

    @Test
    public void doubleTypeInfo() {
        var info = DoubleTypeInfo.get();
        assertEquals("double", info.name());
        assertEquals("double", info.internalName());
        assertEquals("D", info.desc());
        checkTypeField(info, 0);
        checkTypeParam(info, 0);
        assertEquals("double", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("double a", info.nativeType("a", fieldVarOpts(0)));
        assertEquals("double a", info.nativeParamType("a", paramVarOpts(0)));
        assertEquals("double", info.nativeReturnType(returnVarOpts(0)));
        assertEquals(8, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(8, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals("ValueLayout.JAVA_DOUBLE_UNALIGNED", info.memoryLayoutForField(fieldVarOpts(0)));
        assertEquals("double", info.javaTypeForField(fieldVarOpts(0)));
        assertEquals("double", info.javaTypeForParam(paramVarOpts(0)));
        assertEquals("double", info.javaTypeForReturn(returnVarOpts(0)));
        assertEquals(
            """
                private static final VarHandle aVH = LAYOUT.varHandle(
                    MemoryLayout.PathElement.groupElement("a")
                );
                                
                public double getA() {
                    return (double) aVH.get(MEMORY);
                }
                                
                public void setA(double a) {
                    aVH.set(MEMORY, a);
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                OFFSET += ValueLayout.JAVA_DOUBLE_UNALIGNED.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals("double.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("a", info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals(AllocationForReturnedValue.noAllocationRequired(),
            info.allocationInfoForReturnValue(returnVarOpts(0)));
        assertEquals(
            """
                return ENV.returnDouble();
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals(
            """
                return RESULT;
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(paramVarOpts(0)));
    }

    @Test
    public void floatTypeInfo() {
        var info = FloatTypeInfo.get();
        assertEquals("float", info.name());
        assertEquals("float", info.internalName());
        assertEquals("F", info.desc());
        checkTypeField(info, 0);
        checkTypeParam(info, 0);
        assertEquals("float", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("float a", info.nativeType("a", fieldVarOpts(0)));
        assertEquals("float a", info.nativeParamType("a", paramVarOpts(0)));
        assertEquals("float", info.nativeReturnType(returnVarOpts(0)));
        assertEquals(4, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(4, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals("ValueLayout.JAVA_FLOAT_UNALIGNED", info.memoryLayoutForField(fieldVarOpts(0)));
        assertEquals("float", info.javaTypeForField(fieldVarOpts(0)));
        assertEquals("float", info.javaTypeForParam(paramVarOpts(0)));
        assertEquals("float", info.javaTypeForReturn(returnVarOpts(0)));
        assertEquals(
            """
                private static final VarHandle aVH = LAYOUT.varHandle(
                    MemoryLayout.PathElement.groupElement("a")
                );
                                
                public float getA() {
                    return (float) aVH.get(MEMORY);
                }
                                
                public void setA(float a) {
                    aVH.set(MEMORY, a);
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                OFFSET += ValueLayout.JAVA_FLOAT_UNALIGNED.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals("float.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("a", info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals(AllocationForReturnedValue.noAllocationRequired(),
            info.allocationInfoForReturnValue(returnVarOpts(0)));
        assertEquals(
            """
                return ENV.returnFloat();
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals(
            """
                return RESULT;
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(paramVarOpts(0)));
    }

    @Test
    public void intTypeInfo() {
        var info = IntTypeInfo.get();
        assertEquals("int", info.name());
        assertEquals("int", info.internalName());
        assertEquals("I", info.desc());
        checkTypeField(info, UNSIGNED);
        checkTypeParam(info, UNSIGNED);
        assertEquals("int", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("int32_t a", info.nativeType("a", fieldVarOpts(0)));
        assertEquals("uint32_t a", info.nativeType("a", fieldVarOpts(UNSIGNED)));
        assertEquals("int32_t a", info.nativeParamType("a", paramVarOpts(0)));
        assertEquals("uint32_t a", info.nativeParamType("a", paramVarOpts(UNSIGNED)));
        assertEquals("int32_t", info.nativeReturnType(returnVarOpts(0)));
        assertEquals(4, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(4, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals("ValueLayout.JAVA_INT_UNALIGNED", info.memoryLayoutForField(fieldVarOpts(0)));
        assertEquals("int", info.javaTypeForField(fieldVarOpts(0)));
        assertEquals("int", info.javaTypeForParam(paramVarOpts(0)));
        assertEquals("int", info.javaTypeForReturn(returnVarOpts(0)));
        assertEquals(
            """
                private static final VarHandle aVH = LAYOUT.varHandle(
                    MemoryLayout.PathElement.groupElement("a")
                );
                                
                public int getA() {
                    return (int) aVH.get(MEMORY);
                }
                                
                public void setA(int a) {
                    aVH.set(MEMORY, a);
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                OFFSET += ValueLayout.JAVA_INT_UNALIGNED.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals("int.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("a", info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals(AllocationForReturnedValue.noAllocationRequired(),
            info.allocationInfoForReturnValue(returnVarOpts(0)));
        assertEquals(
            """
                return ENV.returnInt();
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals(
            """
                return RESULT;
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(paramVarOpts(0)));
    }

    @Test
    public void longTypeInfo() {
        var info = LongTypeInfo.get();
        assertEquals("long", info.name());
        assertEquals("long", info.internalName());
        assertEquals("J", info.desc());
        checkTypeField(info, UNSIGNED);
        checkTypeParam(info, UNSIGNED);
        assertEquals("long", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("int64_t a", info.nativeType("a", fieldVarOpts(0)));
        assertEquals("uint64_t a", info.nativeType("a", fieldVarOpts(UNSIGNED)));
        assertEquals("int64_t a", info.nativeParamType("a", paramVarOpts(0)));
        assertEquals("uint64_t a", info.nativeParamType("a", paramVarOpts(UNSIGNED)));
        assertEquals("int64_t", info.nativeReturnType(returnVarOpts(0)));
        assertEquals(8, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(8, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals("ValueLayout.JAVA_LONG_UNALIGNED", info.memoryLayoutForField(fieldVarOpts(0)));
        assertEquals("long", info.javaTypeForField(fieldVarOpts(0)));
        assertEquals("long", info.javaTypeForParam(paramVarOpts(0)));
        assertEquals("long", info.javaTypeForReturn(returnVarOpts(0)));
        assertEquals(
            """
                private static final VarHandle aVH = LAYOUT.varHandle(
                    MemoryLayout.PathElement.groupElement("a")
                );
                                
                public long getA() {
                    return (long) aVH.get(MEMORY);
                }
                                
                public void setA(long a) {
                    aVH.set(MEMORY, a);
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                OFFSET += ValueLayout.JAVA_LONG_UNALIGNED.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals("long.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("a", info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals(AllocationForReturnedValue.noAllocationRequired(),
            info.allocationInfoForReturnValue(returnVarOpts(0)));
        assertEquals(
            """
                return ENV.returnLong();
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals(
            """
                return RESULT;
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(paramVarOpts(0)));
    }

    @Test
    public void shortTypeInfo() {
        var info = ShortTypeInfo.get();
        assertEquals("short", info.name());
        assertEquals("short", info.internalName());
        assertEquals("S", info.desc());
        checkTypeField(info, UNSIGNED);
        checkTypeParam(info, UNSIGNED);
        assertEquals("short", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("int16_t a", info.nativeType("a", fieldVarOpts(0)));
        assertEquals("uint16_t a", info.nativeType("a", fieldVarOpts(UNSIGNED)));
        assertEquals("int16_t a", info.nativeParamType("a", paramVarOpts(0)));
        assertEquals("uint16_t a", info.nativeParamType("a", paramVarOpts(UNSIGNED)));
        assertEquals("int16_t", info.nativeReturnType(returnVarOpts(0)));
        assertEquals(2, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(2, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals("ValueLayout.JAVA_SHORT_UNALIGNED", info.memoryLayoutForField(fieldVarOpts(0)));
        assertEquals("short", info.javaTypeForField(fieldVarOpts(0)));
        assertEquals("short", info.javaTypeForParam(paramVarOpts(0)));
        assertEquals("short", info.javaTypeForReturn(returnVarOpts(0)));
        assertEquals(
            """
                private static final VarHandle aVH = LAYOUT.varHandle(
                    MemoryLayout.PathElement.groupElement("a")
                );
                                
                public short getA() {
                    return (short) aVH.get(MEMORY);
                }
                                
                public void setA(short a) {
                    aVH.set(MEMORY, a);
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                OFFSET += ValueLayout.JAVA_SHORT_UNALIGNED.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals("short.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("a", info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals(AllocationForReturnedValue.noAllocationRequired(),
            info.allocationInfoForReturnValue(returnVarOpts(0)));
        assertEquals(
            """
                return ENV.returnShort();
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals(
            """
                return RESULT;
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(paramVarOpts(0)));
    }

    @Test
    public void stringTypeInfo() {
        var info = StringTypeInfo.get();
        assertEquals("java.lang.String", info.name());
        assertEquals("java/lang/String", info.internalName());
        assertEquals("Ljava/lang/String;", info.desc());
        checkTypeField(info, LEN);
        checkTypeParam(info, LEN);
        assertEquals("string", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("char * a", info.nativeType("a", fieldVarOpts(0)));
        assertEquals("char a[3]", info.nativeType("a", fieldVarOpts(LEN)));
        assertEquals("char * a", info.nativeParamType("a", paramVarOpts(0)));
        assertEquals("char *", info.nativeReturnType(returnVarOpts(0)));
        assertEquals(8, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(3, info.nativeMemorySize(fieldVarOpts(LEN)));
        assertEquals(8, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals(1, info.nativeMemoryAlign(fieldVarOpts(LEN)));
        assertEquals("ValueLayout.ADDRESS_UNALIGNED", info.memoryLayoutForField(fieldVarOpts(0)));
        assertEquals("MemoryLayout.sequenceLayout(3L, ValueLayout.JAVA_BYTE)", info.memoryLayoutForField(fieldVarOpts(LEN)));
        assertEquals("PNIString", info.javaTypeForField(fieldVarOpts(0)));
        assertEquals("PNIString", info.javaTypeForParam(paramVarOpts(0)));
        assertEquals("PNIString", info.javaTypeForReturn(returnVarOpts(0)));
        assertEquals(
            """
                private static final VarHandle aVH = LAYOUT.varHandle(
                    MemoryLayout.PathElement.groupElement("a")
                );
                                
                public PNIString getA() {
                    var SEG = (MemorySegment) aVH.get(MEMORY);
                    if (SEG.address() == 0) return null;
                    return new PNIString(SEG);
                }
                                
                public void setA(String a, Allocator ALLOCATOR) {
                    this.setA(new PNIString(ALLOCATOR, a));
                }
                                
                public void setA(PNIString a) {
                    if (a == null) {
                        aVH.set(MEMORY, MemorySegment.NULL);
                    } else {
                        aVH.set(MEMORY, a.MEMORY);
                    }
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                private final MemorySegment a;
                                
                public String getA() {
                    return a.getUtf8String(0);
                }
                                
                public void setA(String a) {
                    this.a.setUtf8String(0, a);
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(LEN))));
        assertEquals(
            """
                OFFSET += 8;
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                this.a = MEMORY.asSlice(OFFSET, 3);
                OFFSET += 3;
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(LEN))));
        assertEquals("String.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("(MemorySegment) (a == null ? MemorySegment.NULL : a.MEMORY)",
            info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals(AllocationForReturnedValue.noAllocationRequired(),
            info.allocationInfoForReturnValue(returnVarOpts(0)));
        assertEquals(
            """
                var RESULT = ENV.returnPointer();
                return RESULT == null ? null : new PNIString(RESULT);
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals(
            """
                return RESULT.address() == 0 ? null : new PNIString(RESULT);
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(paramVarOpts(0)));
    }

    @Test
    public void memorySegment() {
        var info = MemorySegmentTypeInfo.get();
        assertEquals("java.lang.foreign.MemorySegment", info.name());
        assertEquals("java/lang/foreign/MemorySegment", info.internalName());
        assertEquals("Ljava/lang/foreign/MemorySegment;", info.desc());
        checkTypeField(info, 0);
        checkTypeParam(info, 0);
        assertEquals("pointer", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("void * a", info.nativeType("a", fieldVarOpts(0)));
        assertEquals("void * a", info.nativeParamType("a", paramVarOpts(0)));
        assertEquals("void *", info.nativeReturnType(returnVarOpts(0)));
        assertEquals(8, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(8, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals("ValueLayout.ADDRESS_UNALIGNED", info.memoryLayoutForField(fieldVarOpts(0)));
        assertEquals("MemorySegment", info.javaTypeForField(fieldVarOpts(0)));
        assertEquals("MemorySegment", info.javaTypeForParam(paramVarOpts(0)));
        assertEquals("MemorySegment", info.javaTypeForReturn(returnVarOpts(0)));
        assertEquals(
            """
                private static final VarHandle aVH = LAYOUT.varHandle(
                    MemoryLayout.PathElement.groupElement("a")
                );
                                
                public MemorySegment getA() {
                    var SEG = (MemorySegment) aVH.get(MEMORY);
                    if (SEG.address() == 0) return null;
                    return SEG;
                }
                                
                public void setA(MemorySegment a) {
                    if (a == null) {
                        aVH.set(MEMORY, MemorySegment.NULL);
                    } else {
                        aVH.set(MEMORY, a);
                    }
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals("MemorySegment.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("(MemorySegment) (a == null ? MemorySegment.NULL : a)",
            info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals(AllocationForReturnedValue.noAllocationRequired(),
            info.allocationInfoForReturnValue(returnVarOpts(0)));
        assertEquals(
            """
                return ENV.returnPointer();
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals(
            """
                if (RESULT.address() == 0) return null;
                return RESULT;
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(paramVarOpts(0)));
    }

    @Test
    public void byteBuffer() {
        var info = ByteBufferTypeInfo.get();
        assertEquals("java.nio.ByteBuffer", info.name());
        assertEquals("java/nio/ByteBuffer", info.internalName());
        assertEquals("Ljava/nio/ByteBuffer;", info.desc());
        checkTypeField(info, 0);
        checkTypeParam(info, RAW);
        assertEquals("buf", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("PNIBuf a", info.nativeType("a", fieldVarOpts(0)));
        assertEquals("PNIBuf * a", info.nativeParamType("a", paramVarOpts(0)));
        assertEquals("void * a", info.nativeParamType("a", paramVarOpts(RAW)));
        assertEquals("PNIBuf *", info.nativeReturnType(returnVarOpts(0)));
        assertEquals(16, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(8, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals("PNIBuf.LAYOUT", info.memoryLayoutForField(fieldVarOpts(0)));
        assertEquals("ByteBuffer", info.javaTypeForField(fieldVarOpts(0)));
        assertEquals("ByteBuffer", info.javaTypeForParam(paramVarOpts(0)));
        assertEquals("ByteBuffer", info.javaTypeForReturn(returnVarOpts(0)));
        assertEquals(
            """
                private final PNIBuf a;
                                
                public ByteBuffer getA() {
                    var SEG = this.a.get();
                    if (SEG == null) return null;
                    return SEG.asByteBuffer();
                }
                                
                public void setA(ByteBuffer a) {
                    if (a == null) {
                        this.a.setToNull();
                    } else {
                        this.a.set(a);
                    }
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                this.a = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
                OFFSET += PNIBuf.LAYOUT.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals("PNIBuf.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("ByteBuffer.class", info.methodHandleType(paramVarOpts(RAW)));
        assertEquals("PanamaUtils.format(a)",
            info.convertParamToInvokeExactArgument("a", paramVarOpts(RAW)));
        assertEquals("PanamaUtils.format(a, POOLED)",
            info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals(AllocationForReturnedValue.noAllocationRequired(),
            info.allocationInfoForReturnValue(returnVarOpts(0)));
        assertEquals(AllocationForReturnedValue.ofPooledAllocator("PNIBuf.LAYOUT.byteSize()"),
            info.allocationInfoForReturnValue(returnVarOpts(CRITICAL)));
        assertEquals(
            """
                var RES_SEG = ENV.returnBuf();
                return RES_SEG.toByteBuffer();
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals(
            """
                if (RESULT.address() == 0) return null;
                var RES_SEG = new PNIBuf(RESULT);
                return RES_SEG.toByteBuffer();
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(AllocationForParam.ofPooledAllocator(),
            info.allocationInfoForParam(paramVarOpts(POINTER)));
        assertEquals(AllocationForParam.ofPooledAllocator(),
            info.allocationInfoForParam(paramVarOpts(0)));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(paramVarOpts(RAW)));
    }

    @Test
    public void cls() {
        var info = Utils.generalClsTypeInfo();
        assertEquals("a/b/PNICls", info.getClazz().name);
        assertEquals("a.b.PNICls", info.name());
        assertEquals("a/b/PNICls", info.internalName());
        assertEquals("La/b/PNICls;", info.desc());
        checkTypeField(info, POINTER);
        checkTypeParam(info, POINTER);
        {
            info.getClazz().isInterface = true;
            checkError(() -> info.checkType(errors, "?", fieldVarOpts(0), false), "?: unable to use interface type: a.b.PNICls");
            info.getClazz().isInterface = false;
        }
        assertEquals("Cls", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("Cls a", info.nativeType("a", fieldVarOpts(0)));
        assertEquals("Cls * a", info.nativeType("a", fieldVarOpts(POINTER)));
        assertEquals("Cls * a", info.nativeParamType("a", paramVarOpts(0)));
        assertEquals("Cls *", info.nativeReturnType(returnVarOpts(0)));
        assertEquals(4, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(8, info.nativeMemorySize(fieldVarOpts(POINTER)));
        assertEquals(2, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals("a.b.Cls.LAYOUT", info.memoryLayoutForField(fieldVarOpts(0)));
        assertEquals("ValueLayout.ADDRESS_UNALIGNED", info.memoryLayoutForField(fieldVarOpts(POINTER)));
        assertEquals("a.b.Cls", info.javaTypeForField(fieldVarOpts(0)));
        assertEquals("a.b.Cls", info.javaTypeForParam(paramVarOpts(0)));
        assertEquals("a.b.Cls", info.javaTypeForReturn(returnVarOpts(0)));
        assertEquals(
            """
                private final a.b.Cls a;
                                
                public a.b.Cls getA() {
                    return this.a;
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                private static final VarHandle aVH = LAYOUT.varHandle(
                    MemoryLayout.PathElement.groupElement("a")
                );
                                
                public a.b.Cls getA() {
                    var SEG = (MemorySegment) aVH.get(MEMORY);
                    if (SEG.address() == 0) return null;
                    return new a.b.Cls(SEG);
                }
                                
                public void setA(a.b.Cls a) {
                    if (a == null) {
                        aVH.set(MEMORY, MemorySegment.NULL);
                    } else {
                        aVH.set(MEMORY, a.MEMORY);
                    }
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(POINTER))));
        assertEquals(
            """
                this.a = new a.b.Cls(MEMORY.asSlice(OFFSET, a.b.Cls.LAYOUT.byteSize()));
                OFFSET += a.b.Cls.LAYOUT.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                OFFSET += 8;
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(POINTER))));
        assertEquals("a.b.Cls.LAYOUT.getClass()", info.methodHandleType(paramVarOpts(0)));
        assertEquals("(MemorySegment) (a == null ? MemorySegment.NULL : a.MEMORY)",
            info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals(AllocationForReturnedValue.ofExtraAllocator("a.b.Cls.LAYOUT.byteSize()"),
            info.allocationInfoForReturnValue(returnVarOpts(0)));
        assertEquals(
            """
                var RESULT = ENV.returnPointer();
                return RESULT == null ? null : new a.b.Cls(RESULT);
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals(
            """
                if (RESULT.address() == 0) return null;
                return RESULT == null ? null : new a.b.Cls(RESULT);
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(
            """
                if (RESULT.address() == 0) return null;
                return RESULT == null ? null : new a.b.Cls(RESULT);
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(paramVarOpts(0)));
    }

    @Test
    public void byteArray() {
        var info = new ArrayTypeInfo(ByteTypeInfo.get());
        assertEquals(ByteTypeInfo.get(), info.getElementType());
        assertEquals("byte[]", info.name());
        assertEquals("byte[]", info.internalName());
        assertEquals("[B", info.desc());
        checkTypeField(info, UNSIGNED | POINTER | LEN);
        checkTypeParam(info, UNSIGNED | POINTER | LEN | RAW);
        assertEquals("buf", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("PNIBuf a", info.nativeType("a", fieldVarOpts(0)));
        assertEquals("PNIBuf", info.nativeType(null, fieldVarOpts(0)));
        assertEquals("int8_t a[3]", info.nativeType("a", fieldVarOpts(LEN)));
        assertEquals("uint8_t a[3]", info.nativeType("a", fieldVarOpts(UNSIGNED | LEN)));
        assertEquals("PNIBuf * a", info.nativeParamType("a", paramVarOpts(0)));
        assertEquals("void * a", info.nativeParamType("a", paramVarOpts(RAW)));
        assertEquals("uint8_t * a", info.nativeParamType("a", paramVarOpts(UNSIGNED | RAW)));
        assertEquals("PNIBuf *", info.nativeReturnType(returnVarOpts(0)));
        assertEquals(16, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(3, info.nativeMemorySize(fieldVarOpts(LEN)));
        assertEquals(8, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals(1, info.nativeMemoryAlign(fieldVarOpts(LEN)));
        assertEquals("PNIBuf.LAYOUT", info.memoryLayoutForField(fieldVarOpts(0)));
        assertEquals("MemoryLayout.sequenceLayout(3L, ValueLayout.JAVA_BYTE)", info.memoryLayoutForField(fieldVarOpts(LEN)));
        assertEquals("MemorySegment", info.javaTypeForField(fieldVarOpts(0)));
        assertEquals("MemorySegment", info.javaTypeForParam(paramVarOpts(0)));
        assertEquals("MemorySegment", info.javaTypeForReturn(returnVarOpts(0)));
        assertEquals(
            """
                private final PNIBuf a;
                                
                public MemorySegment getA() {
                    var SEG = this.a.get();
                    if (SEG == null) return null;
                    return SEG;
                }
                                
                public void setA(MemorySegment a) {
                    if (a == null) {
                        this.a.setToNull();
                    } else {
                        this.a.set(a);
                    }
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                private final MemorySegment a;
                                
                public MemorySegment getA() {
                    return this.a;
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(LEN))));
        assertEquals(
            """
                this.a = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
                OFFSET += PNIBuf.LAYOUT.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                this.a = MEMORY.asSlice(OFFSET, 3);
                OFFSET += 3;
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(LEN))));
        assertEquals("PNIBuf.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("MemorySegment.class", info.methodHandleType(paramVarOpts(RAW)));
        assertEquals("PNIBuf.memoryOf(POOLED, a)",
            info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals("(MemorySegment) (a == null ? MemorySegment.NULL : a)",
            info.convertParamToInvokeExactArgument("a", paramVarOpts(RAW)));
        assertEquals(AllocationForReturnedValue.noAllocationRequired(),
            info.allocationInfoForReturnValue(returnVarOpts(0)));
        assertEquals(AllocationForReturnedValue.ofPooledAllocator("PNIBuf.LAYOUT.byteSize()"),
            info.allocationInfoForReturnValue(returnVarOpts(CRITICAL)));
        assertEquals(
            """
                var RES_SEG = ENV.returnBuf();
                if (RES_SEG.isNull()) return null;
                return RES_SEG.get();
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals(
            """
                if (RESULT.address() == 0) return null;
                var RES_SEG = new PNIBuf(RESULT);
                if (RES_SEG.isNull()) return null;
                return RES_SEG.get();
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(paramVarOpts(RAW)));
        assertEquals(AllocationForParam.ofPooledAllocator(),
            info.allocationInfoForParam(paramVarOpts(0)));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(fieldVarOpts(LEN)));
    }

    @Test
    public void primitiveArray() {
        var info = new ArrayTypeInfo(FloatTypeInfo.get());
        assertEquals(FloatTypeInfo.get(), info.getElementType());
        assertEquals("float[]", info.name());
        assertEquals("float[]", info.internalName());
        assertEquals("[F", info.desc());
        checkTypeField(info, POINTER | LEN);
        checkTypeParam(info, POINTER | LEN | RAW);
        assertEquals("buf", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("PNIBuf a", info.nativeType("a", fieldVarOpts(0)));
        assertEquals("PNIBuf", info.nativeType(null, fieldVarOpts(0)));
        assertEquals("float a[3]", info.nativeType("a", fieldVarOpts(LEN)));
        assertEquals("PNIBuf * a", info.nativeParamType("a", paramVarOpts(0)));
        assertEquals("float * a", info.nativeParamType("a", paramVarOpts(RAW)));
        assertEquals("PNIBuf *", info.nativeReturnType(returnVarOpts(0)));
        assertEquals(16, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(12, info.nativeMemorySize(fieldVarOpts(LEN)));
        assertEquals(8, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals(4, info.nativeMemoryAlign(fieldVarOpts(LEN)));
        assertEquals("PNIBuf.LAYOUT", info.memoryLayoutForField(fieldVarOpts(0)));
        assertEquals("MemoryLayout.sequenceLayout(3L, ValueLayout.JAVA_FLOAT_UNALIGNED)", info.memoryLayoutForField(fieldVarOpts(LEN)));
        assertEquals("FloatArray", info.javaTypeForField(fieldVarOpts(0)));
        assertEquals("FloatArray", info.javaTypeForParam(paramVarOpts(0)));
        assertEquals("FloatArray", info.javaTypeForReturn(returnVarOpts(0)));
        assertEquals(
            """
                private final PNIBuf a;
                                
                public FloatArray getA() {
                    var SEG = this.a.get();
                    if (SEG == null) return null;
                    return new FloatArray(SEG);
                }
                                
                public void setA(FloatArray a) {
                    if (a == null) {
                        this.a.setToNull();
                    } else {
                        this.a.set(a.MEMORY);
                    }
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                private final FloatArray a;
                                
                public FloatArray getA() {
                    return this.a;
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(LEN))));
        assertEquals(
            """
                this.a = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
                OFFSET += PNIBuf.LAYOUT.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                this.a = new FloatArray(MEMORY.asSlice(OFFSET, 3 * ValueLayout.JAVA_FLOAT_UNALIGNED.byteSize()));
                OFFSET += 3 * ValueLayout.JAVA_FLOAT_UNALIGNED.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(LEN))));
        assertEquals("PNIBuf.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("MemorySegment.class", info.methodHandleType(paramVarOpts(RAW)));
        assertEquals("PNIBuf.memoryOf(POOLED, a)",
            info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals("(MemorySegment) (a == null ? MemorySegment.NULL : a.MEMORY)",
            info.convertParamToInvokeExactArgument("a", paramVarOpts(RAW)));
        assertEquals(AllocationForReturnedValue.noAllocationRequired(),
            info.allocationInfoForReturnValue(returnVarOpts(0)));
        assertEquals(AllocationForReturnedValue.ofPooledAllocator("PNIBuf.LAYOUT.byteSize()"),
            info.allocationInfoForReturnValue(returnVarOpts(CRITICAL)));
        assertEquals(
            """
                var RES_SEG = ENV.returnBuf();
                if (RES_SEG.isNull()) return null;
                return new FloatArray(RES_SEG);
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals(
            """
                if (RESULT.address() == 0) return null;
                var RES_SEG = new PNIBuf(RESULT);
                if (RES_SEG.isNull()) return null;
                return new FloatArray(RES_SEG);
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(paramVarOpts(RAW)));
        assertEquals(AllocationForParam.ofPooledAllocator(),
            info.allocationInfoForParam(paramVarOpts(0)));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(fieldVarOpts(LEN)));
    }

    @Test
    public void classArray() {
        var cls = Utils.generalClsTypeInfo();
        var info = new ArrayTypeInfo(cls);
        assertEquals(cls, info.getElementType());
        assertEquals("a.b.PNICls[]", info.name());
        assertEquals("a/b/PNICls[]", info.internalName());
        assertEquals("[La/b/PNICls;", info.desc());
        checkTypeField(info, POINTER | LEN);
        checkTypeParam(info, POINTER | LEN | RAW);
        assertEquals("buf", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("PNIBuf a", info.nativeType("a", fieldVarOpts(0)));
        assertEquals("PNIBuf", info.nativeType(null, fieldVarOpts(0)));
        assertEquals("Cls a[3]", info.nativeType("a", fieldVarOpts(LEN)));
        assertEquals("PNIBuf * a", info.nativeParamType("a", paramVarOpts(0)));
        assertEquals("Cls * a", info.nativeParamType("a", paramVarOpts(RAW)));
        assertEquals("PNIBuf *", info.nativeReturnType(returnVarOpts(0)));
        assertEquals(16, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(12, info.nativeMemorySize(fieldVarOpts(LEN)));
        assertEquals(8, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals(2, info.nativeMemoryAlign(fieldVarOpts(LEN)));
        assertEquals("PNIBuf.LAYOUT", info.memoryLayoutForField(fieldVarOpts(0)));
        assertEquals("MemoryLayout.sequenceLayout(3L, a.b.Cls.LAYOUT)", info.memoryLayoutForField(fieldVarOpts(LEN)));
        assertEquals("a.b.Cls.Array", info.javaTypeForField(fieldVarOpts(0)));
        assertEquals("a.b.Cls.Array", info.javaTypeForParam(paramVarOpts(0)));
        assertEquals("a.b.Cls.Array", info.javaTypeForReturn(returnVarOpts(0)));
        assertEquals(
            """
                private final PNIBuf a;
                                
                public a.b.Cls.Array getA() {
                    var SEG = this.a.get();
                    if (SEG == null) return null;
                    return new a.b.Cls.Array(SEG);
                }
                                
                public void setA(a.b.Cls.Array a) {
                    if (a == null) {
                        this.a.setToNull();
                    } else {
                        this.a.set(a.MEMORY);
                    }
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                private final a.b.Cls.Array a;
                                
                public a.b.Cls.Array getA() {
                    return this.a;
                }
                """, Utils.sbHelper(sb -> info.generateGetterSetter(sb, 0, "a", fieldVarOpts(LEN))));
        assertEquals(
            """
                this.a = new PNIBuf(MEMORY.asSlice(OFFSET, PNIBuf.LAYOUT.byteSize()));
                OFFSET += PNIBuf.LAYOUT.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals(
            """
                this.a = new a.b.Cls.Array(MEMORY.asSlice(OFFSET, 3 * a.b.Cls.LAYOUT.byteSize()));
                OFFSET += 3 * a.b.Cls.LAYOUT.byteSize();
                """, Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(LEN))));
        assertEquals("PNIBuf.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("MemorySegment.class", info.methodHandleType(paramVarOpts(RAW)));
        assertEquals("PNIBuf.memoryOf(POOLED, a)",
            info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals("(MemorySegment) (a == null ? MemorySegment.NULL : a.MEMORY)",
            info.convertParamToInvokeExactArgument("a", paramVarOpts(RAW)));
        assertEquals(AllocationForReturnedValue.noAllocationRequired(),
            info.allocationInfoForReturnValue(returnVarOpts(0)));
        assertEquals(AllocationForReturnedValue.ofPooledAllocator("PNIBuf.LAYOUT.byteSize()"),
            info.allocationInfoForReturnValue(returnVarOpts(CRITICAL)));
        assertEquals(
            """
                var RES_SEG = ENV.returnBuf();
                if (RES_SEG.isNull()) return null;
                return new a.b.Cls.Array(RES_SEG);
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals(
            """
                if (RESULT.address() == 0) return null;
                var RES_SEG = new PNIBuf(RESULT);
                if (RES_SEG.isNull()) return null;
                return new a.b.Cls.Array(RES_SEG);
                """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(paramVarOpts(RAW)));
        assertEquals(AllocationForParam.ofPooledAllocator(),
            info.allocationInfoForParam(paramVarOpts(0)));
        assertEquals(AllocationForParam.noAllocationRequired(),
            info.allocationInfoForParam(fieldVarOpts(LEN)));
    }

    @Test
    public void arrayTypes() {
        var arr = new ArrayTypeInfo(ByteTypeInfo.get());
        assertEquals("void * a", arr.nativeParamType("a", paramVarOpts(RAW)));
        assertEquals("uint8_t * a", arr.nativeParamType("a", paramVarOpts(RAW | UNSIGNED)));
        assertEquals("MemorySegment", arr.javaTypeForField(fieldVarOpts(0)));

        arr = new ArrayTypeInfo(BooleanTypeInfo.get());
        assertEquals("uint8_t * a", arr.nativeParamType("a", paramVarOpts(RAW)));
        assertEquals("BoolArray", arr.javaTypeForField(fieldVarOpts(0)));

        arr = new ArrayTypeInfo(CharTypeInfo.get());
        assertEquals("uint16_t * a", arr.nativeParamType("a", paramVarOpts(RAW)));
        assertEquals("CharArray", arr.javaTypeForField(fieldVarOpts(0)));

        arr = new ArrayTypeInfo(FloatTypeInfo.get());
        assertEquals("float * a", arr.nativeParamType("a", paramVarOpts(RAW)));
        assertEquals("FloatArray", arr.javaTypeForField(fieldVarOpts(0)));

        arr = new ArrayTypeInfo(DoubleTypeInfo.get());
        assertEquals("double * a", arr.nativeParamType("a", paramVarOpts(RAW)));
        assertEquals("DoubleArray", arr.javaTypeForField(fieldVarOpts(0)));

        arr = new ArrayTypeInfo(IntTypeInfo.get());
        assertEquals("int32_t * a", arr.nativeParamType("a", paramVarOpts(RAW)));
        assertEquals("uint32_t * a", arr.nativeParamType("a", paramVarOpts(RAW | UNSIGNED)));
        assertEquals("IntArray", arr.javaTypeForField(fieldVarOpts(0)));

        arr = new ArrayTypeInfo(LongTypeInfo.get());
        assertEquals("int64_t * a", arr.nativeParamType("a", paramVarOpts(RAW)));
        assertEquals("uint64_t * a", arr.nativeParamType("a", paramVarOpts(RAW | UNSIGNED)));
        assertEquals("LongArray", arr.javaTypeForField(fieldVarOpts(0)));

        arr = new ArrayTypeInfo(ShortTypeInfo.get());
        assertEquals("int16_t * a", arr.nativeParamType("a", paramVarOpts(RAW)));
        assertEquals("uint16_t * a", arr.nativeParamType("a", paramVarOpts(RAW | UNSIGNED)));
        assertEquals("ShortArray", arr.javaTypeForField(fieldVarOpts(0)));
    }

    @Test
    public void ref() {
        var info = PNIRefTypeInfo.get();
        assertEquals("io.vproxy.pni.PNIRef", info.name());
        assertEquals("io/vproxy/pni/PNIRef", info.internalName());
        assertEquals("Lio/vproxy/pni/PNIRef;", info.desc());

        checkError(() -> info.checkType(errors, "?", fieldVarOpts(0), false), "?: cannot use raw type of PNIRef");
        assertEquals("ref", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("PNIRef *", info.nativeType(null, fieldVarOpts(0)));
        assertEquals("PNIRef * ref", info.nativeType("ref", fieldVarOpts(0)));
        assertEquals(8, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(8, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals("ValueLayout.ADDRESS_UNALIGNED", info.memoryLayoutForField(fieldVarOpts(0)));
        checkUnsupported(() -> info.javaTypeForField(fieldVarOpts(0)), "implemented in subclass");
        checkUnsupported(() -> info.generateGetterSetter(new StringBuilder(), 0, "a", fieldVarOpts(0)), "implemented in subclass");
        assertEquals("OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();\n",
            Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals("PNIRef.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("PNIRef.class", info.methodHandleType(paramVarOpts(RAW)));
        assertEquals("(MemorySegment) (a == null ? MemorySegment.NULL : PNIRef.of(a).MEMORY)",
            info.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals("(MemorySegment) (a == null ? MemorySegment.NULL : a.MEMORY)",
            info.convertParamToInvokeExactArgument("a", paramVarOpts(RAW)));
        assertEquals("""
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            return PNIRef.of(RESULT);
            """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals("""
            if (RESULT.address() == 0) return null;
            return PNIRef.of(RESULT);
            """, Utils.sbHelper(sb -> info.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));

        var gInfo = new PNIRefGenericTypeInfo(List.of(new AstTypeDesc("Ljava/lang/Object;")));
        checkTypeField(gInfo, 0);
        checkTypeParam(gInfo, RAW);
        {
            var i = new PNIRefGenericTypeInfo(List.of(new AstTypeDesc("Ljava/lang/Object;"), new AstTypeDesc("Ljava/lang/Object;")));
            checkError(() -> i.checkType(errors, "?", fieldVarOpts(0), false), "?: PNIRef should have exactly one generic param: [java.lang.Object, java.lang.Object]");
        }
        assertEquals("java.lang.Object", gInfo.getGenericTypeString(0));
        assertEquals("PNIRef<java.lang.Object>", gInfo.javaTypeForField(fieldVarOpts(0)));
        assertEquals("java.lang.Object", gInfo.javaTypeForParam(paramVarOpts(0)));
        assertEquals("PNIRef<java.lang.Object>", gInfo.javaTypeForParam(paramVarOpts(RAW)));
        {
            var i = new PNIRefGenericTypeInfo(List.of(new AstTypeDesc("*")));
            assertEquals("Object", i.javaTypeForParam(paramVarOpts(0)));
            assertEquals("PNIRef<?>", i.javaTypeForParam(paramVarOpts(RAW)));

            i = new PNIRefGenericTypeInfo(List.of(new AstTypeDesc("+Ljava/lang/Number;")));
            assertEquals("java.lang.Number", i.javaTypeForParam(paramVarOpts(0)));
            assertEquals("PNIRef<? extends java.lang.Number>", i.javaTypeForParam(paramVarOpts(RAW)));

            i = new PNIRefGenericTypeInfo(List.of(new AstTypeDesc("-Ljava/lang/Number;")));
            assertEquals("Object", i.javaTypeForParam(paramVarOpts(0)));
            assertEquals("PNIRef<? super java.lang.Number>", i.javaTypeForParam(paramVarOpts(RAW)));
        }
        assertEquals("PNIRef<java.lang.Object>", gInfo.javaTypeForReturn(returnVarOpts(0)));
        assertEquals("""
            private static final VarHandle aVH = LAYOUT.varHandle(
                MemoryLayout.PathElement.groupElement("a")
            );
                        
            public PNIRef<java.lang.Object> getA() {
                var SEG = (MemorySegment) aVH.get(MEMORY);
                if (SEG.address() == 0) return null;
                return PNIRef.of(SEG);
            }
                        
            public void setA(PNIRef<java.lang.Object> a) {
                if (a == null) {
                    aVH.set(MEMORY, MemorySegment.NULL);
                } else {
                    aVH.set(MEMORY, a.MEMORY);
                }
            }
            """, Utils.sbHelper(sb -> gInfo.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
    }

    @Test
    public void func() {
        var info = PNIFuncTypeInfo.get();
        assertEquals("io.vproxy.pni.PNIFunc", info.name());
        assertEquals("io/vproxy/pni/PNIFunc", info.internalName());
        assertEquals("Lio/vproxy/pni/PNIFunc;", info.desc());

        checkError(() -> info.checkType(errors, "?", fieldVarOpts(0), false), "?: cannot use raw type of PNIFunc");
        assertEquals("func", info.nativeEnvType(returnVarOpts(0)));
        assertEquals("PNIFunc *", info.nativeType(null, fieldVarOpts(0)));
        assertEquals("PNIFunc * func", info.nativeType("func", fieldVarOpts(0)));
        assertEquals(8, info.nativeMemorySize(fieldVarOpts(0)));
        assertEquals(8, info.nativeMemoryAlign(fieldVarOpts(0)));
        assertEquals("ValueLayout.ADDRESS_UNALIGNED", info.memoryLayoutForField(fieldVarOpts(0)));
        checkUnsupported(() -> info.javaTypeForField(fieldVarOpts(0)), "implemented in subclass");
        checkUnsupported(() -> info.generateGetterSetter(new StringBuilder(), 0, "a", fieldVarOpts(0)), "implemented in subclass");
        assertEquals("OFFSET += ValueLayout.ADDRESS_UNALIGNED.byteSize();\n",
            Utils.sbHelper(sb -> info.generateConstructor(sb, 0, "a", fieldVarOpts(0))));
        assertEquals("io.vproxy.pni.CallSite.class", info.methodHandleType(paramVarOpts(0)));
        assertEquals("PNIFunc.class", info.methodHandleType(paramVarOpts(RAW)));

        var clsInfo = Utils.generalClsTypeInfo();
        var gInfo = new PNIFuncGenericTypeInfo(List.of(new AstTypeDesc("La/b/PNICls;")), List.of(clsInfo));

        assertEquals("(MemorySegment) (a == null ? MemorySegment.NULL : a.b.Cls.Func.of(a).MEMORY)",
            gInfo.convertParamToInvokeExactArgument("a", paramVarOpts(0)));
        assertEquals("(MemorySegment) (a == null ? MemorySegment.NULL : a.MEMORY)",
            gInfo.convertParamToInvokeExactArgument("a", paramVarOpts(RAW)));
        checkUnsupported(() -> info.convertInvokeExactReturnValueToJava(new StringBuilder(), 0, returnVarOpts(0)), "implemented in subclass");

        checkTypeField(gInfo, RAW);
        checkTypeParam(gInfo, RAW);
        {
            var i = new PNIFuncGenericTypeInfo(
                List.of(new AstTypeDesc("La/b/PNICls;"), new AstTypeDesc("La/b/PNICls;")),
                List.of(clsInfo, clsInfo)
            );
            checkError(() -> i.checkType(errors, "?", fieldVarOpts(0), false), "?: PNIFunc should have exactly one generic param: [a.b.PNICls, a.b.PNICls]");
        }
        {
            clsInfo.getClazz().isInterface = true;
            checkError(() -> gInfo.checkType(errors, "?", fieldVarOpts(0), false), "?#<0>: unable to use interface type: a.b.PNICls");
            clsInfo.getClazz().isInterface = false;
        }
        {
            var i = new PNIFuncGenericTypeInfo(
                List.of(new AstTypeDesc("Ljava/lang/Exception;")),
                Collections.singletonList(new BuiltInExceptionTypeInfo(
                    "java.lang.Exception", "java/lang/Exception", "Ljava/lang/Exception;"
                ))
            );
            checkError(() -> i.checkType(errors, "?", fieldVarOpts(0), false),
                "?#<0>: PNIFunc can only take Struct/Union or PNIRef or java.lang.Void as its argument");
        }
        {
            var i = new PNIFuncGenericTypeInfo(
                List.of(new AstTypeDesc("Ljava/lang/Object;")),
                Collections.singletonList(null)
            );
            checkError(() -> i.checkType(errors, "?", fieldVarOpts(0), false),
                "?#<0>: cannot find generic param: java.lang.Object");
        }
        assertEquals("PNIFunc<a.b.Cls>", gInfo.javaTypeForField(fieldVarOpts(0)));
        var objectRef = new PNIRefGenericTypeInfo(
            List.of(new AstTypeDesc("Ljava/lang/Object;"))
        );
        var rInfo = new PNIFuncGenericTypeInfo(
            List.of(new AstTypeDesc("Lio/vproxy/pni/PNIFunc;", List.of(new AstTypeDesc("Ljava/lang/Object;")))),
            List.of(objectRef)
        );
        assertEquals("PNIFunc<java.lang.Object>", rInfo.javaTypeForField(fieldVarOpts(0)));
        {
            var refTypeInfo = PNIRefTypeInfo.get();
            var i = new PNIFuncGenericTypeInfo(
                List.of(new AstTypeDesc("Lio/vproxy/PNIFunc")),
                List.of(refTypeInfo)
            );
            checkUnsupported(() -> i.javaTypeForField(fieldVarOpts(0)), "should not reach here");
        }
        assertEquals("PNIFunc<a.b.Cls>", gInfo.javaTypeForField(fieldVarOpts(0)));
        var vInfo = new PNIFuncGenericTypeInfo(
            List.of(new AstTypeDesc("Ljava/lang/Void;")),
            List.of(VoidRefTypeInfo.get())
        );
        assertEquals("""
            private static final VarHandle aVH = LAYOUT.varHandle(
                MemoryLayout.PathElement.groupElement("a")
            );
                        
            public PNIFunc<Void> getA() {
                var SEG = (MemorySegment) aVH.get(MEMORY);
                if (SEG.address() == 0) return null;
                return PNIFunc.VoidFunc.of(SEG);
            }
                        
            public void setA(PNIFunc<Void> a) {
                if (a == null) {
                    aVH.set(MEMORY, MemorySegment.NULL);
                } else {
                    aVH.set(MEMORY, a.MEMORY);
                }
            }
            """, Utils.sbHelper(sb -> vInfo.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        assertEquals("""
            private static final VarHandle aVH = LAYOUT.varHandle(
                MemoryLayout.PathElement.groupElement("a")
            );
                        
            public PNIFunc<java.lang.Object> getA() {
                var SEG = (MemorySegment) aVH.get(MEMORY);
                if (SEG.address() == 0) return null;
                return PNIRef.Func.of(SEG);
            }
                        
            public void setA(PNIFunc<java.lang.Object> a) {
                if (a == null) {
                    aVH.set(MEMORY, MemorySegment.NULL);
                } else {
                    aVH.set(MEMORY, a.MEMORY);
                }
            }
            """, Utils.sbHelper(sb -> rInfo.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));
        {
            var refTypeInfo = PNIRefTypeInfo.get();
            var i = new PNIFuncGenericTypeInfo(
                List.of(new AstTypeDesc("Lio/vproxy/PNIFunc")),
                List.of(refTypeInfo)
            );
            checkUnsupported(() -> i.generateGetterSetter(new StringBuilder(), 0, "a", fieldVarOpts(0)), "should not reach here");
        }
        assertEquals("""
            private static final VarHandle aVH = LAYOUT.varHandle(
                MemoryLayout.PathElement.groupElement("a")
            );
                        
            public PNIFunc<a.b.Cls> getA() {
                var SEG = (MemorySegment) aVH.get(MEMORY);
                if (SEG.address() == 0) return null;
                return a.b.Cls.Func.of(SEG);
            }
                        
            public void setA(PNIFunc<a.b.Cls> a) {
                if (a == null) {
                    aVH.set(MEMORY, MemorySegment.NULL);
                } else {
                    aVH.set(MEMORY, a.MEMORY);
                }
            }
            """, Utils.sbHelper(sb -> gInfo.generateGetterSetter(sb, 0, "a", fieldVarOpts(0))));

        assertEquals("""
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            return PNIFunc.VoidFunc.of(RESULT);
            """, Utils.sbHelper(sb -> vInfo.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals("""
            if (RESULT.address() == 0) return null;
            return PNIFunc.VoidFunc.of(RESULT);
            """, Utils.sbHelper(sb -> vInfo.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals("""
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            return a.b.Cls.Func.of(RESULT);
            """, Utils.sbHelper(sb -> gInfo.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals("""
            if (RESULT.address() == 0) return null;
            return a.b.Cls.Func.of(RESULT);
            """, Utils.sbHelper(sb -> gInfo.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
        assertEquals("""
            var RESULT = ENV.returnPointer();
            if (RESULT == null) return null;
            return PNIRef.Func.of(RESULT);
            """, Utils.sbHelper(sb -> rInfo.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(0))));
        assertEquals("""
            if (RESULT.address() == 0) return null;
            return PNIRef.Func.of(RESULT);
            """, Utils.sbHelper(sb -> rInfo.convertInvokeExactReturnValueToJava(sb, 0, returnVarOpts(CRITICAL))));
    }
}
