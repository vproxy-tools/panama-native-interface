package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.NoAlloc;
import io.vproxy.pni.test.NoAllocUpcall;
import io.vproxy.pni.test.ObjectStruct;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestNoAlloc {
    private static final ObjectStruct objStruct = new ObjectStruct(Allocator.ofAuto());

    @BeforeClass
    public static void beforeClass() {
        //noinspection Convert2Lambda
        NoAllocUpcall.setImpl(new NoAllocUpcall.Interface() {
            @Override
            public ObjectStruct execNoAlloc() {
                return objStruct;
            }
        });
    }

    @Test
    public void invoke() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var s = NoAlloc.get().execNoAlloc(env);
            var bytes = s.getLenStr().getBytes();
            var n = bytes[0];

            for (int i = 0; i < 10; ++i) {
                var nn = NoAlloc.get().execNoAlloc(env).getLenStr().getBytes()[0];
                assertEquals(n + i + 1, nn);
            }
        }
    }

    @Test
    public void upcall() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var o = NoAlloc.get().invokeUpcall(env);
            assertEquals(o.MEMORY.address(), objStruct.MEMORY.address());
        }
    }
}
