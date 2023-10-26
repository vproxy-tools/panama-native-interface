package io.vproxy.pni.graal.test;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNILinkOptions;
import io.vproxy.pni.graal.GraalUtils;
import org.graalvm.nativeimage.CurrentIsolate;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.function.CEntryPointLiteral;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.type.VoidPointer;
import org.junit.Test;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unused")
public class TestFunctionPointer {
    @CEntryPoint
    public static int f_sum(IsolateThread THREAD, int a, int b) {
        return a + b;
    }

    private static final CEntryPointLiteral<CFunctionPointer> p_f_sum = GraalUtils.defineCFunctionByName(
        new PNILinkOptions(),
        TestFunctionPointer.class, "f_sum");

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

    @CEntryPoint
    public static void f_ptr(IsolateThread THREAD, int a, VoidPointer GRAAL_p) {
        MemorySegment p = MemorySegment.ofAddress(GRAAL_p.rawValue());
        p = p.reinterpret(4);
        p.set(ValueLayout.JAVA_INT, 0, a);
    }

    private static final CEntryPointLiteral<CFunctionPointer> p_f_ptr = GraalUtils.defineCFunction(
        new PNILinkOptions(),
        TestFunctionPointer.class, "f_ptr",
        IsolateThread.class, int.class, VoidPointer.class);

    @Test
    public void ptr() {
        var fmem = MemorySegment.ofAddress(p_f_ptr.getFunctionPointer().rawValue());
        var tmem = MemorySegment.ofAddress(CurrentIsolate.getCurrentThread().rawValue());

        try (var allocator = Allocator.ofConfined()) {
            var p = allocator.allocate(4);
            for (int i = 0; i < 1000; ++i) {
                p.set(ValueLayout.JAVA_INT, 0, 0);

                int a = ThreadLocalRandom.current().nextInt();
                Invoke.get().invokePtr(fmem, tmem, a, p);

                assertEquals(a, p.get(ValueLayout.JAVA_INT, 0));
            }
        }
    }
}
