package io.vproxy.pni.test;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PooledAllocator;
import io.vproxy.pni.impl.*;
import io.vproxy.pni.unsafe.SunUnsafe;
import org.junit.Test;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.ValueLayout;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestAllocator {
    @Test
    public void unsafe() {
        try (var allocator = Allocator.ofUnsafe()) {
            var mem = allocator.allocate(0);
            assertEquals(0, mem.address());
            assertEquals(0, mem.byteSize());
            assertSame(MemorySegment.NULL, mem);

            mem = allocator.allocate(123);
            assertEquals(123, mem.byteSize());

            mem = allocator.allocate(8);
            assertEquals(8, mem.byteSize());

            mem = allocator.allocate(16);
            assertEquals(16, mem.byteSize());

            mem = allocator.allocate(23);
            assertEquals(23, mem.byteSize());

            // should exceed initial capacity
            mem = allocator.allocate(29);
            assertEquals(29, mem.byteSize());
        }
    }

    @Test
    public void wrapString() {
        try (var allocator = Allocator.ofConfined()) {
            var mem = allocator.wrapString("abc");
            assertEquals(4, mem.byteSize());
        }
    }

    private Arena getArenaFromArenaAllocator(Allocator allocator) throws Exception {
        var f = ArenaAllocator.class.getDeclaredField("arena");
        f.setAccessible(true);
        return (Arena) f.get(allocator);
    }

    @Test
    public void ofArena() throws Exception {
        var arena = Arena.ofConfined();
        try (var allocator = Allocator.of(arena)) {
            assertTrue(allocator instanceof ArenaAllocator);
            assertSame(arena, getArenaFromArenaAllocator(allocator));
        }
    }

    @Test
    public void ofArenaAlign() {
        var arena = Arena.ofConfined();
        try (var allocator = Allocator.of(arena)) {
            for (int i = 0; i < 1000; ++i) {
                var mem = allocator.allocate(1, 128);
                assertEquals(0, mem.address() % 128);
            }
        }
    }

    @Test
    public void ofConfined() throws Exception {
        try (var allocator = Allocator.ofConfined()) {
            assertTrue(allocator instanceof ArenaAllocator);
            try (var a = Arena.ofConfined()) {
                assertSame(a.getClass(), getArenaFromArenaAllocator(allocator).getClass());
            }
        }
    }

    @Test
    public void ofShared() throws Exception {
        try (var allocator = Allocator.ofShared()) {
            assertTrue(allocator instanceof ArenaAllocator);
            try (var a = Arena.ofShared()) {
                assertSame(a.getClass(), getArenaFromArenaAllocator(allocator).getClass());
            }
        }
    }

    private Arena getArenaFromNoCloseArenaAllocator(Allocator allocator) throws Exception {
        var f = NoCloseArenaAllocator.class.getDeclaredField("arena");
        f.setAccessible(true);
        return (Arena) f.get(allocator);
    }

    @Test
    public void ofAuto() throws Exception {
        try (var allocator = Allocator.ofAuto()) {
            assertTrue(allocator instanceof NoCloseArenaAllocator);
            var a = Arena.ofAuto();
            assertSame(a.getClass(), getArenaFromNoCloseArenaAllocator(allocator).getClass());

            var mem = allocator.allocate(123);
            assertEquals(123, mem.byteSize());
        }
    }

    @Test
    public void ofAutoAlign() {
        try (var allocator = Allocator.ofAuto()) {
            for (var i = 0; i < 1000; ++i) {
                var mem = allocator.allocate(1, 128);
                assertEquals(0, mem.address() % 128);
            }
        }
    }

    @Test
    public void global() throws Exception {
        try (var allocator = Allocator.global()) {
            assertTrue(allocator instanceof NoCloseArenaAllocator);
            assertSame(allocator, Allocator.global());
            assertSame(Arena.global(), getArenaFromNoCloseArenaAllocator(allocator));
        }
    }

    @Test
    public void ofSegmentAllocator() {
        try (var allocator = Allocator.ofConfined()) {
            try (var allocator2 = Allocator.of(SegmentAllocator.slicingAllocator(allocator.allocate(1024)))) {
                assertTrue(allocator2 instanceof SegmentAllocatorAllocator);
                var mem = allocator2.allocate(512);
                assertEquals(512, mem.byteSize());
                mem = allocator2.allocate(512);
                assertEquals(512, mem.byteSize());
            }
        }
    }

    @Test
    public void ofSegmentAllocatorAlign() {
        try (var allocator = Allocator.ofConfined()) {
            try (var allocator2 = Allocator.of(SegmentAllocator.slicingAllocator(allocator.allocate(1048576)))) {
                for (int i = 0; i < 100; ++i) {
                    var mem = allocator2.allocate(1, 128);
                    assertEquals(0, mem.address() % 128);
                }
            }
        }
    }

    @Test
    public void ofPooled() throws Exception {
        try (var allocator = Allocator.ofPooled()) {
            assertTrue(allocator instanceof ArenaAllocator);
            try (var a = Arena.ofConfined()) {
                assertSame(a.getClass(), getArenaFromArenaAllocator(allocator).getClass());
            }
        }
    }

    @Test
    public void ofUnsafe() {
        try (var allocator = Allocator.ofUnsafe()) {
            assertTrue(allocator instanceof UnsafeAllocator);
        }
    }

    @Test
    public void ofUnsafeAlign() {
        try (var allocator = Allocator.ofUnsafe()) {
            for (int i = 0; i < 1000; ++i) {
                var mem = allocator.allocate(1, 128);
                assertEquals(0, mem.address() % 128);
            }
        }
    }

    @Test
    public void ofAllocateAndForgetUnsafe() {
        try (var allocator = Allocator.ofAllocateAndForgetUnsafe()) {
            assertTrue(allocator instanceof AllocateAndForgetUnsafeAllocator);
            var mem = allocator.allocate(123);
            assertEquals(123, mem.byteSize());
            Allocator.Unsafe.free(mem);

            mem = allocator.allocate(0);
            assertEquals(0, mem.address());
            assertEquals(0, mem.byteSize());
        }
    }

    @Test
    public void ofAllocateAndForgetUnsafeAlign() {
        var addrs = new ArrayList<Long>();
        try (var allocator = Allocator.ofAllocateAndForgetUnsafe()) {
            var mem = allocator.allocate(1, 128);
            addrs.add(mem.address());
            assertEquals(0, mem.address() % 128);
        } finally {
            for (var a : addrs) {
                SunUnsafe.freeMemory(a);
            }
        }
    }

    @Test
    public void ofDummy() {
        try (var allocator = Allocator.ofDummy()) {
            assertTrue(allocator instanceof DummyAllocator);
            assertEquals(0, allocator.allocate(0).byteSize());
            assertEquals(0, allocator.allocate(123).byteSize());
        }
    }

    @Test
    public void ofDummyAlign() {
        try (var allocator = Allocator.ofDummy()) {
            assertEquals(MemorySegment.NULL, allocator.allocate(1, 128));
        }
    }

    @Test
    public void ref() {
        try (var allocator = Allocator.ofConfined()) {
            var ref = allocator.ref();
            assertSame(ref, allocator.ref());
        }
    }

    @Test
    public void meta() {
        try (var allocator = Allocator.ofConfined().withMetadata(8, info -> info.meta().set(ValueLayout.JAVA_LONG, 0, info.size()))) {
            var m = allocator.allocate(0);
            assertNotEquals(0, m.address());
            var sz = MemorySegment.ofAddress(m.address() - 8).reinterpret(8).get(ValueLayout.JAVA_LONG, 0);
            assertEquals(0, sz);

            m = allocator.allocate(123);
            assertNotEquals(0, m.address());
            sz = MemorySegment.ofAddress(m.address() - 8).reinterpret(8).get(ValueLayout.JAVA_LONG, 0);
            assertEquals(123, sz);
        }

        try (var allocator = Allocator.ofConfined().withMetadata(0, info -> assertEquals(0, info.meta().address()))) {
            var m = allocator.allocate(0);
            assertNotEquals(0, m.address());

            m = allocator.allocate(123);
            assertNotEquals(0, m.address());
        }

        try (var allocator = Allocator.ofConfined().withMetadata(32, info -> info.meta().set(ValueLayout.JAVA_LONG, 16, info.size()))) {
            var m = allocator.allocate(123);
            var sz = MemorySegment.ofAddress(m.address() - 16).reinterpret(8).get(ValueLayout.JAVA_LONG, 0);
            assertEquals(123, sz);
        }
    }

    @Test
    public void metaAlign() {
        try (var allocator = Allocator.ofConfined().withMetadata(32, info -> info.meta().set(ValueLayout.JAVA_LONG, 16, info.size()))) {
            for (int i = 0; i < 1000; ++i) {
                var mem = allocator.allocate(1, 128);
                var sz = MemorySegment.ofAddress(mem.address() - 128 + 16).reinterpret(8).get(ValueLayout.JAVA_LONG, 0);
                assertEquals(1, sz);
                assertEquals(0, mem.address() % 128);
            }
        }
        try (var allocator = Allocator.ofConfined().withMetadata(256, info -> info.meta().set(ValueLayout.JAVA_LONG, 24, info.size()))) {
            for (int i = 0; i < 1000; ++i) {
                var m = allocator.allocate(123, 128);
                var sz = MemorySegment.ofAddress(m.address() - 256 + 24).reinterpret(8).get(ValueLayout.JAVA_LONG, 0);
                assertEquals(123, sz);
                assertEquals(0, m.address() % 128);
            }
        }
    }

    @Test
    public void ofConcurrentPooled() throws Exception {
        try (var allocator = PooledAllocator.ofConcurrentPooled()) {
            assertTrue(allocator instanceof ArenaAllocator);
            try (var a = Arena.ofShared()) {
                assertSame(a.getClass(), getArenaFromArenaAllocator(allocator).getClass());
            }
        }
    }

    @Test
    public void ofUnsafePooled() {
        try (var allocator = PooledAllocator.ofUnsafePooled()) {
            assertTrue(allocator instanceof UnsafeAllocator);
        }
    }

    @Test
    public void setPooledAllocatorProvider() throws Exception {
        var old = PooledAllocator.getPooledAllocatorProvider();
        PooledAllocator.setPooledAllocatorProvider(Allocator::ofShared);
        try (var allocator = PooledAllocator.ofPooled()) {
            assertTrue(allocator instanceof ArenaAllocator);
            try (var a = Arena.ofShared()) {
                assertSame(a.getClass(), getArenaFromArenaAllocator(allocator).getClass());
            }
        }
        PooledAllocator.setPooledAllocatorProvider(old);
    }

    @Test
    public void setConcurrentPooledAllocatorProvider() throws Exception {
        var old = PooledAllocator.getConcurrentPooledAllocatorProvider();
        PooledAllocator.setConcurrentPooledAllocatorProvider(Allocator::ofConfined);
        try (var allocator = PooledAllocator.ofConcurrentPooled()) {
            assertTrue(allocator instanceof ArenaAllocator);
            try (var a = Arena.ofConfined()) {
                assertSame(a.getClass(), getArenaFromArenaAllocator(allocator).getClass());
            }
        }
        PooledAllocator.setConcurrentPooledAllocatorProvider(old);
    }

    @Test
    public void setUnsafePooledAllocatorProvider() throws Exception {
        var old = PooledAllocator.getUnsafePooledAllocatorProvider();
        PooledAllocator.setUnsafePooledAllocatorProvider(Allocator::ofConfined);
        try (var allocator = PooledAllocator.ofUnsafePooled()) {
            assertTrue(allocator instanceof ArenaAllocator);
            try (var a = Arena.ofConfined()) {
                assertSame(a.getClass(), getArenaFromArenaAllocator(allocator).getClass());
            }
        }
        PooledAllocator.setUnsafePooledAllocatorProvider(old);
    }
}
