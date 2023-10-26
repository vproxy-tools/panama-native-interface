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

public class NativeCheck {
    private NativeCheck() {
    }

    private static final NativeCheck INSTANCE = new NativeCheck();

    public static NativeCheck get() {
        return INSTANCE;
    }

    private static final MethodHandle checkUserdataForRefMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_NativeCheck_checkUserdataForRef", PNIRef.class /* ref */, MemorySegment.class /* x */, MemorySegment.class /* y */, MemorySegment.class /* z */);

    public void checkUserdataForRef(PNIEnv ENV, PNIRef<java.util.List<java.lang.Integer>> ref, IntArray x, LongArray y, ShortArray z) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) checkUserdataForRefMH.invokeExact(ENV.MEMORY, (MemorySegment) (ref == null ? MemorySegment.NULL : ref.MEMORY), (MemorySegment) (x == null ? MemorySegment.NULL : x.MEMORY), (MemorySegment) (y == null ? MemorySegment.NULL : y.MEMORY), (MemorySegment) (z == null ? MemorySegment.NULL : z.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }

    private static final MethodHandle checkUserdataForFuncMH = PanamaUtils.lookupPNIFunction(new PNILinkOptions(), "Java_io_vproxy_pni_test_NativeCheck_checkUserdataForFunc", PNIFunc.class /* func */, MemorySegment.class /* x */, MemorySegment.class /* y */, MemorySegment.class /* z */);

    public void checkUserdataForFunc(PNIEnv ENV, PNIFunc<Void> func, IntArray x, LongArray y, ShortArray z) {
        ENV.reset();
        int ERR;
        try {
            ERR = (int) checkUserdataForFuncMH.invokeExact(ENV.MEMORY, (MemorySegment) (func == null ? MemorySegment.NULL : func.MEMORY), (MemorySegment) (x == null ? MemorySegment.NULL : x.MEMORY), (MemorySegment) (y == null ? MemorySegment.NULL : y.MEMORY), (MemorySegment) (z == null ? MemorySegment.NULL : z.MEMORY));
        } catch (Throwable THROWABLE) {
            throw PanamaUtils.convertInvokeExactException(THROWABLE);
        }
        if (ERR != 0) {
            ENV.throwLast();
        }
    }
}
// metadata.generator-version: pni test
// sha256:941a6260ad186cbcf5e2bdc27f3dd1798bd9cda1737d7103452bcf20c9731871
