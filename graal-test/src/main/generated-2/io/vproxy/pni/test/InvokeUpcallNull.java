package io.vproxy.pni.test;

import io.vproxy.pni.*;
import io.vproxy.pni.hack.*;
import io.vproxy.pni.array.*;
import java.lang.foreign.*;
import java.lang.invoke.*;
import java.nio.ByteBuffer;
import io.vproxy.pni.graal.*;
import org.graalvm.nativeimage.*;
import org.graalvm.nativeimage.c.function.*;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.graalvm.word.WordFactory;

public class InvokeUpcallNull {
    private InvokeUpcallNull() {
    }

    private static final InvokeUpcallNull INSTANCE = new InvokeUpcallNull();

    public static InvokeUpcallNull get() {
        return INSTANCE;
    }

    private static final MethodHandle testParamMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_testParam");

    public boolean testParam() {
        boolean RESULT;
        try {
            RESULT = (boolean) testParamMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle testParamRawMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_testParamRaw");

    public boolean testParamRaw() {
        boolean RESULT;
        try {
            RESULT = (boolean) testParamRawMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnOMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnO");

    public boolean returnO() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnOMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnStrMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnStr");

    public boolean returnStr() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnStrMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnSegMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnSeg");

    public boolean returnSeg() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnSegMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnBufMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnBuf");

    public boolean returnBuf() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnBufMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnByteArrMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnByteArr");

    public boolean returnByteArr() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnByteArrMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnBoolArrMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnBoolArr");

    public boolean returnBoolArr() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnBoolArrMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnCharArrMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnCharArr");

    public boolean returnCharArr() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnCharArrMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnFloatArrMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFloatArr");

    public boolean returnFloatArr() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnFloatArrMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnDoubleArrMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnDoubleArr");

    public boolean returnDoubleArr() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnDoubleArrMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnIntArrMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnIntArr");

    public boolean returnIntArr() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnIntArrMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnLongArrMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnLongArr");

    public boolean returnLongArr() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnLongArrMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnShortArrMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnShortArr");

    public boolean returnShortArr() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnShortArrMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnPArrMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnPArr");

    public boolean returnPArr() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnPArrMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnOArrMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnOArr");

    public boolean returnOArr() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnOArrMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnRefMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnRef");

    public boolean returnRef() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnRefMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnFuncMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFunc");

    public boolean returnFunc() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnFuncMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnFuncVoidMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFuncVoid");

    public boolean returnFuncVoid() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnFuncVoidMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }

    private static final MethodHandle returnFuncRefMH = PanamaUtils.lookupPNICriticalFunction(new PNILinkOptions(), boolean.class, "JavaCritical_io_vproxy_pni_test_InvokeUpcallNull_returnFuncRef");

    public boolean returnFuncRef() {
        boolean RESULT;
        try {
            RESULT = (boolean) returnFuncRefMH.invokeExact();
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        return RESULT;
    }
}
// metadata.generator-version: pni test
// sha256:7edf3d24d8df2187439105dbc9991c41ecccdf5b11113830a4af58154ebac536
