package io.vproxy.pni.graal.test;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.CallSite;
import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.graal.GraalUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

public class TestUpcall {
    private static Allocator allocator;

    @BeforeClass
    public static void beforeClass() {
        allocator = Allocator.ofUnsafe();
        Upcall.setImpl(new Upcall.Interface() {
            @Override
            public void doNothingUpcall() {
                System.out.println("into doNothingUpcall!!");
            }

            @Override
            public int intUpcall(int a) {
                return a;
            }

            @Override
            public int refUpcall(Integer ref) {
                return ref;
            }

            @Override
            public int funcUpcall(CallSite<Void> func) {
                return func.call(null);
            }

            @Override
            public MemorySegment returnSegUpcall() {
                var seg = allocator.allocate(8);
                seg.set(ValueLayout.JAVA_LONG, 0, 0xaabbccddeeffL);
                return seg;
            }
        });
    }

    @AfterClass
    public static void afterClass() {
        var allocator = TestUpcall.allocator;
        TestUpcall.allocator = null;
        if (allocator != null) {
            allocator.close();
        }
    }

    @Before
    public void setUp() {
        GraalUtils.setThread();
    }

    @Test
    public void testDoNothing() {
        Invoke.get().invokeDoNothingUpcall(Upcall.doNothingUpcall);
    }

    @Test
    public void testInUpcall() {
        for (int i = 0; i < 1000; ++i) {
            var n = ThreadLocalRandom.current().nextInt();
            var res = Invoke.get().invokeIntUpcall(Upcall.intUpcall, n);
            assertEquals(n, res);
        }
    }

    @Test
    public void refUpcall() {
        var count = PNIRef.currentRefStorageSize();
        for (int i = 0; i < 1000; ++i) {
            var n = ThreadLocalRandom.current().nextInt();
            var res = Invoke.get().invokeRefUpcall(Upcall.refUpcall, n);
            assertEquals(n, res);
        }
        assertEquals(count, PNIRef.currentRefStorageSize());
    }

    @Test
    public void funcUpcall() {
        var count = PNIFunc.currentFuncStorageSize();
        for (int i = 0; i < 1000; ++i) {
            var n = ThreadLocalRandom.current().nextInt();
            var res = Invoke.get().invokeFuncUpcall(Upcall.funcUpcall, v -> n);
            assertEquals(n, res);
        }
        assertEquals(count, PNIFunc.currentFuncStorageSize());
    }

    @Test
    public void returnSegUpcall() {
        var seg = Invoke.get().invokeReturnSegUpcall(Upcall.returnSegUpcall);
        var n = seg.reinterpret(8).get(ValueLayout.JAVA_LONG, 0);
        assertEquals(0xaabbccddeeffL, n);
    }
}
