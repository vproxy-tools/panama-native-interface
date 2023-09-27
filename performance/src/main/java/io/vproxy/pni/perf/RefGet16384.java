package io.vproxy.pni.perf;

import io.vproxy.pni.PNIRef;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(java.util.concurrent.TimeUnit.NANOSECONDS)
public class RefGet16384 {
    @SuppressWarnings("unchecked")
    private final PNIRef<Object>[] refs = new PNIRef[16384];

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
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED", "-XX:CompileCommand=dontinline,io.vproxy.pni.impl.ForceNoInlineConcurrentLongMap::*"})
    public void get0DontInline(Blackhole bh) {
        _get0(bh);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void get0(Blackhole bh) {
        _get0(bh);
    }

    private void _get0(Blackhole bh) {
        var ref = PNIRef.of(refs[0].MEMORY);
        bh.consume(ref.getRef());
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED", "-XX:CompileCommand=dontinline,io.vproxy.pni.impl.ForceNoInlineConcurrentLongMap::*"})
    public void get4096DontInline(Blackhole bh) {
        _get4096(bh);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void get4096(Blackhole bh) {
        _get4096(bh);
    }

    private void _get4096(Blackhole bh) {
        var ref = PNIRef.of(refs[4096].MEMORY);
        bh.consume(ref.getRef());
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED", "-XX:CompileCommand=dontinline,io.vproxy.pni.impl.ForceNoInlineConcurrentLongMap::*"})
    public void getAll0To4096DontInline(Blackhole bh) {
        _getAll0To4096(bh);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void getAll0To4096(Blackhole bh) {
        _getAll0To4096(bh);
    }

    private void _getAll0To4096(Blackhole bh) {
        for (int i = 0; i < 4096; ++i) {
            var ref = PNIRef.of(refs[i].MEMORY);
            bh.consume(ref.getRef());
        }
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED", "-XX:CompileCommand=dontinline,io.vproxy.pni.impl.ForceNoInlineConcurrentLongMap::*"})
    public void getAll4096To8192DontInline(Blackhole bh) {
        _getAll4096To8192(bh);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void getAll4096To8192(Blackhole bh) {
        _getAll4096To8192(bh);
    }

    private void _getAll4096To8192(Blackhole bh) {
        for (int i = 4096; i < 8192; ++i) {
            var ref = PNIRef.of(refs[i].MEMORY);
            bh.consume(ref.getRef());
        }
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED", "-XX:CompileCommand=dontinline,io.vproxy.pni.impl.ForceNoInlineConcurrentLongMap::*"})
    public void getAll16384DontInline(Blackhole bh) {
        _getAll16384(bh);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void getAll16384(Blackhole bh) {
        _getAll16384(bh);
    }

    private void _getAll16384(Blackhole bh) {
        for (int i = 0; i < 16384; ++i) {
            var ref = PNIRef.of(refs[i].MEMORY);
            bh.consume(ref.getRef());
        }
    }
}
