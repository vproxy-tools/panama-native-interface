package io.vproxy.pni.graal.test;

import io.vproxy.pni.graal.GraalUtils;
import org.graalvm.nativeimage.CurrentIsolate;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.function.CEntryPointLiteral;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.junit.Test;

import java.lang.foreign.MemorySegment;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unused")
public class TestFunctionPointer {
    @CEntryPoint
    public static int f_sum(IsolateThread THREAD, int a, int b) {
        return a + b;
    }

    private static final CEntryPointLiteral<CFunctionPointer> p_f_sum = GraalUtils.defineCFunctionByName(TestFunctionPointer.class, "f_sum");

    @Test
    public void sum() {
        var fmem = MemorySegment.ofAddress(p_f_sum.getFunctionPointer().rawValue());
        var tmem = MemorySegment.ofAddress(CurrentIsolate.getCurrentThread().rawValue());

        for (int i = 0; i < 1000; ++i) {
            int a = ThreadLocalRandom.current().nextInt();
            int b = ThreadLocalRandom.current().nextInt();
            var res = Invoke.get().invokeSum(fmem, tmem, a, b);
            assertEquals(a + b, res);
        }
    }
}
