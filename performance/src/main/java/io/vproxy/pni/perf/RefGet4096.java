package io.vproxy.pni.perf;

import io.vproxy.pni.PNIRef;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(java.util.concurrent.TimeUnit.NANOSECONDS)
public class RefGet4096 {
    @SuppressWarnings("unchecked")
    private final PNIRef<Object>[] refs = new PNIRef[4096];

    @Setup
    public void setUp() {
        for (int i = 0; i < refs.length; ++i) {
            var o = new Object();
            var ref = PNIRef.of(o);
            refs[i] = PNIRef.of(ref.MEMORY);
        }
    }

    @TearDown
    public void tearDown() {
        for (var ref : refs) {
            ref.close();
        }
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void get0(Blackhole bh) {
        var ref = PNIRef.of(refs[0].MEMORY);
        bh.consume(ref.getRef());
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void getAll4096(Blackhole bh) {
        for (int i = 0; i < 4096; ++i) {
            var ref = PNIRef.of(refs[i].MEMORY);
            bh.consume(ref.getRef());
        }
    }
}
