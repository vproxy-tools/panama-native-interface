package io.vproxy.pni.perf;

import io.vproxy.pni.hack.VarHandleW;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

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
    // private static final VarHandleW ALIGNED_A = VarHandleW.of(ALIGNED_LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("a")));
    private static final VarHandleW ALIGNED_B = VarHandleW.of(ALIGNED_LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    ));
    private static final VarHandleW ALIGNED_C = VarHandleW.of(ALIGNED_LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("c")
    ));

    private static final MemoryLayout MANUALLY_ALIGNED_LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a"),
        MemoryLayout.sequenceLayout(1, ValueLayout.JAVA_BYTE),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("b"),
        MemoryLayout.sequenceLayout(4, ValueLayout.JAVA_BYTE),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("c")
    );
    // private static final VarHandleW MANUALLY_ALIGNED_A = VarHandleW.of(MANUALLY_ALIGNED_LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("a")));
    private static final VarHandleW MANUALLY_ALIGNED_B = VarHandleW.of(MANUALLY_ALIGNED_LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    ));
    private static final VarHandleW MANUALLY_ALIGNED_C = VarHandleW.of(MANUALLY_ALIGNED_LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("c")
    ));

    private static final MemoryLayout UNALIGNED_LAYOUT = MemoryLayout.structLayout(
        ValueLayout.JAVA_BYTE.withName("a"),
        ValueLayout.JAVA_SHORT_UNALIGNED.withName("b"),
        ValueLayout.JAVA_LONG_UNALIGNED.withName("c")
    );
    // private static final VarHandleW UNALIGNED_A = VarHandleW.of(UNALIGNED_LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("a")));
    private static final VarHandleW UNALIGNED_B = VarHandleW.of(UNALIGNED_LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("b")
    ));
    private static final VarHandleW UNALIGNED_C = VarHandleW.of(UNALIGNED_LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("c")
    ));

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
        var res = ALIGNED_B.getShort(aligned);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void manuallyAlignedShort(Blackhole bh) {
        MANUALLY_ALIGNED_B.set(manuallyAligned, (short) 0x1234);
        var res = MANUALLY_ALIGNED_B.getShort(manuallyAligned);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void unalignedShort(Blackhole bh) {
        UNALIGNED_B.set(unaligned, (short) 0x1234);
        var res = UNALIGNED_B.getShort(unaligned);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void alignedShortUseSizeToAllocate(Blackhole bh) {
        ALIGNED_B.set(alignedSizeAlloc, (short) 0x1234);
        var res = ALIGNED_B.getShort(alignedSizeAlloc);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void manuallyAlignedShortUseSizeToAllocate(Blackhole bh) {
        MANUALLY_ALIGNED_B.set(manuallyAlignedSizeAlloc, (short) 0x1234);
        var res = MANUALLY_ALIGNED_B.getShort(manuallyAlignedSizeAlloc);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void unalignedShortUseSizeToAllocate(Blackhole bh) {
        UNALIGNED_B.set(unalignedSizeAlloc, (short) 0x1234);
        var res = UNALIGNED_B.getShort(unalignedSizeAlloc);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void alignedLong(Blackhole bh) {
        ALIGNED_C.set(aligned, 0x123456789abcL);
        var res = ALIGNED_C.getLong(aligned);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void manuallyAlignedLong(Blackhole bh) {
        MANUALLY_ALIGNED_C.set(manuallyAligned, 0x123456789abcL);
        var res = MANUALLY_ALIGNED_C.getLong(manuallyAligned);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void unalignedLong(Blackhole bh) {
        UNALIGNED_C.set(unaligned, 0x123456789abcL);
        var res = UNALIGNED_C.getLong(unaligned);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void alignedLongUseSizeToAllocate(Blackhole bh) {
        ALIGNED_C.set(alignedSizeAlloc, 0x123456789abcL);
        var res = ALIGNED_C.getLong(alignedSizeAlloc);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void manuallyAlignedLongUseSizeToAllocate(Blackhole bh) {
        MANUALLY_ALIGNED_C.set(manuallyAlignedSizeAlloc, 0x123456789abcL);
        var res = MANUALLY_ALIGNED_C.getLong(manuallyAlignedSizeAlloc);
        bh.consume(res);
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5)
    @Measurement(iterations = 4, time = 5)
    @Fork(value = 1, warmups = 0, jvmArgsPrepend = {"--enable-preview", "--enable-native-access=ALL-UNNAMED"})
    public void unalignedLongUseSizeToAllocate(Blackhole bh) {
        UNALIGNED_C.set(unalignedSizeAlloc, 0x123456789abcL);
        var res = UNALIGNED_C.getLong(unalignedSizeAlloc);
        bh.consume(res);
    }
}
