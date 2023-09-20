package io.vproxy.pni.perf;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(java.util.concurrent.TimeUnit.NANOSECONDS)
public class FieldAccess {
    private static final MemoryLayout ALIGNED_LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a"),
        MemoryLayout.sequenceLayout(1, ValueLayout.JAVA_BYTE),
        ValueLayout.JAVA_SHORT.withName("b"),
        MemoryLayout.sequenceLayout(4, ValueLayout.JAVA_BYTE),
        ValueLayout.JAVA_LONG.withName("c")
    );
    // private static final VarHandle ALIGNED_A = ALIGNED_LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("a"));
    private static final VarHandle ALIGNED_B = ALIGNED_LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    );
    private static final VarHandle ALIGNED_C = ALIGNED_LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("c")
    );

    private static final MemoryLayout MANUALLY_ALIGNED_LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a"),
        MemoryLayout.sequenceLayout(1, ValueLayout.JAVA_BYTE),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("b"),
        MemoryLayout.sequenceLayout(4, ValueLayout.JAVA_BYTE),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("c")
    );
    // private static final VarHandle MANUALLY_ALIGNED_A = MANUALLY_ALIGNED_LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("a"));
    private static final VarHandle MANUALLY_ALIGNED_B = MANUALLY_ALIGNED_LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    );
    private static final VarHandle MANUALLY_ALIGNED_C = MANUALLY_ALIGNED_LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("c")
    );

    private static final MemoryLayout UNALIGNED_LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a"),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("b"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("c")
    );
    // private static final VarHandle UNALIGNED_A = UNALIGNED_LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("a"));
    private static final VarHandle UNALIGNED_B = UNALIGNED_LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    );
    private static final VarHandle UNALIGNED_C = UNALIGNED_LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("c")
    );

    private Arena arena;
    private MemorySegment aligned;
    private MemorySegment manuallyAligned;
    private MemorySegment unaligned;

    private MemorySegment alignedSizeAlloc;
    private MemorySegment manuallyAlignedSizeAlloc;
    private MemorySegment unalignedSizeAlloc;

    @Setup
    public void setUp() {
        arena = Arena.ofConfined();
        aligned = arena.allocate(ALIGNED_LAYOUT);
        manuallyAligned = arena.allocate(MANUALLY_ALIGNED_LAYOUT);
        unaligned = arena.allocate(UNALIGNED_LAYOUT);

        alignedSizeAlloc = arena.allocate(ALIGNED_LAYOUT.byteSize());
        manuallyAlignedSizeAlloc = arena.allocate(MANUALLY_ALIGNED_LAYOUT.byteSize());
        unalignedSizeAlloc = arena.allocate(UNALIGNED_LAYOUT.byteSize());
    }

    @TearDown
    public void tearDown() {
        arena.close();
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void alignedShort(Blackhole bh) {
        ALIGNED_B.set(aligned, (short) 0x1234);
        var res = (short) ALIGNED_B.get(aligned);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void manuallyAlignedShort(Blackhole bh) {
        MANUALLY_ALIGNED_B.set(manuallyAligned, (short) 0x1234);
        var res = (short) MANUALLY_ALIGNED_B.get(manuallyAligned);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void unalignedShort(Blackhole bh) {
        UNALIGNED_B.set(unaligned, (short) 0x1234);
        var res = (short) UNALIGNED_B.get(unaligned);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void alignedShortUseSizeToAllocate(Blackhole bh) {
        ALIGNED_B.set(alignedSizeAlloc, (short) 0x1234);
        var res = (short) ALIGNED_B.get(alignedSizeAlloc);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void manuallyAlignedShortUseSizeToAllocate(Blackhole bh) {
        MANUALLY_ALIGNED_B.set(manuallyAlignedSizeAlloc, (short) 0x1234);
        var res = (short) MANUALLY_ALIGNED_B.get(manuallyAlignedSizeAlloc);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void unalignedShortUseSizeToAllocate(Blackhole bh) {
        UNALIGNED_B.set(unalignedSizeAlloc, (short) 0x1234);
        var res = (short) UNALIGNED_B.get(unalignedSizeAlloc);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void alignedLong(Blackhole bh) {
        ALIGNED_C.set(aligned, 0x123456789abcL);
        var res = (long) ALIGNED_C.get(aligned);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void manuallyAlignedLong(Blackhole bh) {
        MANUALLY_ALIGNED_C.set(manuallyAligned, 0x123456789abcL);
        var res = (long) MANUALLY_ALIGNED_C.get(manuallyAligned);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void unalignedLong(Blackhole bh) {
        UNALIGNED_C.set(unaligned, 0x123456789abcL);
        var res = (long) UNALIGNED_C.get(unaligned);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void alignedLongUseSizeToAllocate(Blackhole bh) {
        ALIGNED_C.set(alignedSizeAlloc, 0x123456789abcL);
        var res = (long) ALIGNED_C.get(alignedSizeAlloc);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void manuallyAlignedLongUseSizeToAllocate(Blackhole bh) {
        MANUALLY_ALIGNED_C.set(manuallyAlignedSizeAlloc, 0x123456789abcL);
        var res = (long) MANUALLY_ALIGNED_C.get(manuallyAlignedSizeAlloc);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void unalignedLongUseSizeToAllocate(Blackhole bh) {
        UNALIGNED_C.set(unalignedSizeAlloc, 0x123456789abcL);
        var res = (long) UNALIGNED_C.get(unalignedSizeAlloc);
        bh.consume(res);
    }
}
