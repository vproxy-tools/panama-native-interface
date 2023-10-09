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

public class Generic {
    private Generic() {
    }

    private static final Generic INSTANCE = new Generic();

    public static Generic get() {
        return INSTANCE;
    }

    private static final MethodHandle simpleMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_simple", PNIRef.class /* o */);

    public void simple(PNIEnv ENV, java.lang.String o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) simpleMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle simpleArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_simpleArr", PNIRef.class /* o */);

    public void simpleArr(PNIEnv ENV, java.lang.String[] o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) simpleArrMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle genericMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_generic", PNIRef.class /* o */);

    public void generic(PNIEnv ENV, java.util.List<java.lang.String> o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) genericMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle genericArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_genericArr", PNIRef.class /* o */);

    public void genericArr(PNIEnv ENV, java.util.List<java.lang.String>[] o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) genericArrMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle extMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_ext", PNIRef.class /* o */);

    public void ext(PNIEnv ENV, java.util.List<? extends java.lang.Number> o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) extMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle extArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_extArr", PNIRef.class /* o */);

    public void extArr(PNIEnv ENV, java.util.List<? extends java.lang.Number>[] o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) extArrMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle supMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_sup", PNIRef.class /* o */);

    public void sup(PNIEnv ENV, java.util.List<? super java.lang.Number> o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) supMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle supArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_supArr", PNIRef.class /* o */);

    public void supArr(PNIEnv ENV, java.util.List<? super java.lang.Number>[] o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) supArrMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle defMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_def", PNIRef.class /* o */);

    public <T extends java.lang.Object> void def(PNIEnv ENV, T o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) defMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle defArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_defArr", PNIRef.class /* o */);

    public <T extends java.lang.Object> void defArr(PNIEnv ENV, T[] o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) defArrMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle upperMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_upper", PNIRef.class /* o */);

    public <T extends java.lang.Number> void upper(PNIEnv ENV, T o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) upperMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle upperArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_upperArr", PNIRef.class /* o */);

    public <T extends java.lang.Number> void upperArr(PNIEnv ENV, T[] o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) upperArrMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle extendsArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_extendsArr", PNIRef.class /* o */);

    public void extendsArr(PNIEnv ENV, java.util.List<? extends java.lang.Number[]> o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) extendsArrMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle extendsArrArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_extendsArrArr", PNIRef.class /* o */);

    public void extendsArrArr(PNIEnv ENV, java.util.List<? extends java.lang.Number[]>[] o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) extendsArrArrMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle defGenericMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_defGeneric", PNIRef.class /* o */);

    public <T extends java.util.List<java.lang.Number>> void defGeneric(PNIEnv ENV, T o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) defGenericMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle defGenericExtendsMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_defGenericExtends", PNIRef.class /* o */);

    public <T extends java.util.List<java.lang.Number>> void defGenericExtends(PNIEnv ENV, T o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) defGenericExtendsMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle defGenericExtendsRawMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_defGenericExtendsRaw", PNIRef.class /* o */);

    public <T extends java.util.List<java.lang.Number>> void defGenericExtendsRaw(PNIEnv ENV, PNIRef<? extends T> o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) defGenericExtendsRawMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : o.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle defGenericArrayExtendsMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_defGenericArrayExtends", PNIRef.class /* o */);

    public <T extends java.util.List<java.lang.Number>> void defGenericArrayExtends(PNIEnv ENV, T[] o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) defGenericArrayExtendsMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle defGenericArrayExtendsRawMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_defGenericArrayExtendsRaw", PNIRef.class /* o */);

    public <T extends java.util.List<java.lang.Number>> void defGenericArrayExtendsRaw(PNIEnv ENV, PNIRef<? extends T[]> o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) defGenericArrayExtendsRawMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : o.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle defGenericSuperMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_defGenericSuper", PNIRef.class /* o */);

    public <T extends java.util.List<java.lang.Number>> void defGenericSuper(PNIEnv ENV, Object o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) defGenericSuperMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle defGenericSuperRawMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_defGenericSuperRaw", PNIRef.class /* o */);

    public <T extends java.util.List<java.lang.Number>> void defGenericSuperRaw(PNIEnv ENV, PNIRef<? super T> o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) defGenericSuperRawMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : o.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle defGenericArraySuperMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_defGenericArraySuper", PNIRef.class /* o */);

    public <T extends java.util.List<java.lang.Number>> void defGenericArraySuper(PNIEnv ENV, Object o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) defGenericArraySuperMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle defGenericArraySuperRawMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_defGenericArraySuperRaw", PNIRef.class /* o */);

    public <T extends java.util.List<java.lang.Number>> void defGenericArraySuperRaw(PNIEnv ENV, PNIRef<? super T[]> o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) defGenericArraySuperRawMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : o.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle returnSimpleMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnSimple");

    public PNIRef<java.lang.String> returnSimple(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnSimpleMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnSimpleArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnSimpleArr");

    public PNIRef<java.lang.String[]> returnSimpleArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnSimpleArrMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnGenericMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnGeneric");

    public PNIRef<java.util.List<java.lang.String>> returnGeneric(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnGenericMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnGenericArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnGenericArr");

    public PNIRef<java.util.List<java.lang.String>[]> returnGenericArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnGenericArrMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnExtMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnExt");

    public PNIRef<java.util.List<? extends java.lang.Number>> returnExt(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnExtMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnExtArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnExtArr");

    public PNIRef<java.util.List<? extends java.lang.Number>[]> returnExtArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnExtArrMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnSupMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnSup");

    public PNIRef<java.util.List<? super java.lang.Number>> returnSup(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnSupMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnSupArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnSupArr");

    public PNIRef<java.util.List<? super java.lang.Number>[]> returnSupArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnSupArrMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnDefMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnDef");

    public <T extends java.lang.Object> PNIRef<T> returnDef(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnDefMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnDefArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnDefArr");

    public <T extends java.lang.Object> PNIRef<T[]> returnDefArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnDefArrMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnUpperMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnUpper");

    public <T extends java.lang.Number> PNIRef<T> returnUpper(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnUpperMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnUpperArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnUpperArr");

    public <T extends java.lang.Number> PNIRef<T[]> returnUpperArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnUpperArrMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnExtendsArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnExtendsArr");

    public PNIRef<java.util.List<? extends java.lang.Number[]>> returnExtendsArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnExtendsArrMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnExtendsArrArrMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnExtendsArrArr");

    public PNIRef<java.util.List<? extends java.lang.Number[]>[]> returnExtendsArrArr(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnExtendsArrArrMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnDefGenericMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnDefGeneric");

    public <T extends java.util.List<java.lang.Number>> PNIRef<T> returnDefGeneric(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnDefGenericMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnDefGenericExtendsMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnDefGenericExtends");

    public <T extends java.util.List<java.lang.Number>> PNIRef<? extends T> returnDefGenericExtends(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnDefGenericExtendsMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnDefGenericArrayExtendsMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnDefGenericArrayExtends");

    public <T extends java.util.List<java.lang.Number>> PNIRef<? extends T[]> returnDefGenericArrayExtends(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnDefGenericArrayExtendsMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnDefGenericSuperMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnDefGenericSuper");

    public <T extends java.util.List<java.lang.Number>> PNIRef<? super T> returnDefGenericSuper(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnDefGenericSuperMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle returnDefGenericArraySuperMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_returnDefGenericArraySuper");

    public <T extends java.util.List<java.lang.Number>> PNIRef<? super T[]> returnDefGenericArraySuper(PNIEnv ENV) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) returnDefGenericArraySuperMH.invokeExact(ENV.MEMORY);
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle combinedMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_combined", PNIRef.class /* t */, PNIRef.class /* tArr */, PNIRef.class /* ls */, PNIRef.class /* arrLs */, PNIRef.class /* arrLs2 */, PNIRef.class /* lsArr */, PNIRef.class /* arrLsArr */, PNIRef.class /* arrLsArr2 */, PNIRef.class /* map */, PNIRef.class /* arrMap */, PNIRef.class /* mapArr */, PNIRef.class /* arrMapArr */, PNIRef.class /* arrMapArr2 */);

    public <R extends java.lang.Object, T extends java.lang.Number, U extends java.util.List<? super java.lang.String>, V extends java.lang.Object> PNIRef<? super R> combined(PNIEnv ENV, T t, T[] tArr, java.util.List<? super U> ls, java.util.List<? super U[]> arrLs, java.util.List<U[]> arrLs2, java.util.List<? super U>[] lsArr, java.util.List<? super U[]>[] arrLsArr, java.util.List<U[]>[] arrLsArr2, java.util.Map<? super U, ? extends V> map, java.util.Map<? extends U[], ? extends V[]> arrMap, java.util.Map<? super U, ? extends V>[] mapArr, java.util.Map<? super U[], ? super V[]>[] arrMapArr, java.util.Map<U[], V[]>[] arrMapArr2) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) combinedMH.invokeExact(ENV.MEMORY, (MemorySegment) (t == null ? MemorySegment.NULL : PNIRef.of(t).MEMORY), (MemorySegment) (tArr == null ? MemorySegment.NULL : PNIRef.of(tArr).MEMORY), (MemorySegment) (ls == null ? MemorySegment.NULL : PNIRef.of(ls).MEMORY), (MemorySegment) (arrLs == null ? MemorySegment.NULL : PNIRef.of(arrLs).MEMORY), (MemorySegment) (arrLs2 == null ? MemorySegment.NULL : PNIRef.of(arrLs2).MEMORY), (MemorySegment) (lsArr == null ? MemorySegment.NULL : PNIRef.of(lsArr).MEMORY), (MemorySegment) (arrLsArr == null ? MemorySegment.NULL : PNIRef.of(arrLsArr).MEMORY), (MemorySegment) (arrLsArr2 == null ? MemorySegment.NULL : PNIRef.of(arrLsArr2).MEMORY), (MemorySegment) (map == null ? MemorySegment.NULL : PNIRef.of(map).MEMORY), (MemorySegment) (arrMap == null ? MemorySegment.NULL : PNIRef.of(arrMap).MEMORY), (MemorySegment) (mapArr == null ? MemorySegment.NULL : PNIRef.of(mapArr).MEMORY), (MemorySegment) (arrMapArr == null ? MemorySegment.NULL : PNIRef.of(arrMapArr).MEMORY), (MemorySegment) (arrMapArr2 == null ? MemorySegment.NULL : PNIRef.of(arrMapArr2).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
        var RESULT = ENV.returnPointer();
        if (RESULT == null) return null;
        return PNIRef.of(RESULT);
    }

    private static final MethodHandle wildcardMH = PanamaUtils.lookupPNIFunction(false, "Java_io_vproxy_pni_test_Generic_wildcard", PNIRef.class /* o */);

    public void wildcard(PNIEnv ENV, Object o) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) wildcardMH.invokeExact(ENV.MEMORY, (MemorySegment) (o == null ? MemorySegment.NULL : PNIRef.of(o).MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }
}
// metadata.generator-version: pni test
// sha256:79dc52ef4890b9ba24a64ee1db3212e0cd6642d29ab60e8dfbd54bfc6677e95f
