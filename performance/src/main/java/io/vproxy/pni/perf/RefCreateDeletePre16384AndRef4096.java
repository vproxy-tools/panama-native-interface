package io.vproxy.pni.perf;

import io.vproxy.pni.PNIRef;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(java.util.concurrent.TimeUnit.NANOSECONDS)
public class RefCreateDeletePre16384AndRef4096 {
    private final List<PNIRef<Object>> refs = new ArrayList<>();

    @Setup
    public void setUp() {
        {
            var refs = new ArrayList<PNIRef<Object>>();
            for (int i = 0; i < 16384; ++i) {
                refs.add(PNIRef.of(new Object()));
            }
            for (var ref : refs) {
                ref.close();
            }
        }
        for (int i = 0; i < 4096; ++i) {
            refs.add(PNIRef.of(new Object()));
        }
    }

    @TearDown
    public void tearDown() {
        for (var ref : refs) {
            ref.close();
        }
    }

    private static final Object o = new Object();

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void refAndClose() {
        var ref = PNIRef.of(o);
        ref.close();
    }
}
