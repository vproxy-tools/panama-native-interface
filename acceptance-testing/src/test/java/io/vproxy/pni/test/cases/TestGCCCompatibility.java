package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestGCCCompatibility {
    @Test
    public void Packed() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new GCCCompatibilityPacked(allocator);

            s.init(env);

            check(s);

            assertEquals(GCCCompatibilityPacked.LAYOUT.byteSize(), s.size(env));
        }
    }

    private void check(GCCCompatibilityPacked s) {
        assertEquals((byte) 1, s.getB());
        assertEquals((short) 2, s.getS());
        assertEquals(3, s.getN());
        assertEquals(4.0f, s.getF(), 0);
        assertEquals(8.0, s.getD(), 0);
        assertEquals(9L, s.getL());
    }

    @Test
    public void NonPackedContainPacked() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new GCCCompatibilityNonPackedContainPacked(allocator);

            s.init(env);

            assertEquals((byte) 90, s.getB1());
            check(s.getPacked());
            assertEquals(91, s.getN2());

            assertEquals(GCCCompatibilityNonPackedContainPacked.LAYOUT.byteSize(), s.size(env));
        }
    }

    @Test
    public void Normal() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new GCCCompatibilityNormal(allocator);

            s.init(env);

            check(s);

            assertEquals(GCCCompatibilityNormal.LAYOUT.byteSize(), s.size(env));
        }
    }

    private void check(GCCCompatibilityNormal s) {
        assertEquals((byte) 1, s.getB());
        assertEquals((short) 2, s.getS());
        assertEquals(3, s.getN());
        assertEquals(4.0f, s.getF(), 0);
        assertEquals(8.0, s.getD(), 0);
        assertEquals(9L, s.getL());
    }

    @Test
    public void NonPackedContainNonPacked() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new GCCCompatibilityNonPackedContainNonPacked(allocator);

            s.init(env);

            assertEquals((byte) 92, s.getB1());
            check(s.getNormal());
            assertEquals(93, s.getN2());

            assertEquals(GCCCompatibilityNonPackedContainNonPacked.LAYOUT.byteSize(), s.size(env));
        }
    }

    @Test
    public void PackedContainNonPacked() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new GCCCompatibilityPackedContainNonPacked(allocator);

            s.init(env);

            assertEquals((byte) 94, s.getB1());
            check(s.getNormal());
            assertEquals(95, s.getN2());

            assertEquals(GCCCompatibilityPackedContainNonPacked.LAYOUT.byteSize(), s.size(env));
        }
    }

    @Test
    public void AlignField() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new GCCCompatibilityAlignField(allocator);

            s.init(env);

            assertEquals((byte) 1, s.getB1());
            assertEquals((short) 2, s.getS());
            assertEquals(3, s.getN2());

            assertEquals(GCCCompatibilityAlignField.LAYOUT.byteSize(), s.size(env));
        }
    }

    @Test
    public void AlignFieldPacked() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new GCCCompatibilityAlignFieldPacked(allocator);

            s.init(env);

            assertEquals((byte) 96, s.getB1());
            check(s.getPacked());
            assertEquals(97, s.getN2());

            assertEquals(GCCCompatibilityAlignFieldPacked.LAYOUT.byteSize(), s.size(env));
        }
    }

    @Test
    public void PackedAlignField() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new GCCCompatibilityPackedAlignField(allocator);

            s.init(env);

            assertEquals((byte) 4, s.getB1());
            assertEquals((short) 5, s.getS());
            assertEquals(6, s.getN2());

            assertEquals(GCCCompatibilityPackedAlignField.LAYOUT.byteSize(), s.size(env));
        }
    }

    @Test
    public void PackedAlignFieldSmallerAlign() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new GCCCompatibilityPackedAlignFieldSmallerAlign(allocator);

            s.init(env);

            assertEquals((byte) 7, s.getB1());
            assertEquals(8L, s.getL());
            assertEquals(9, s.getN2());

            assertEquals(GCCCompatibilityPackedAlignFieldSmallerAlign.LAYOUT.byteSize(), s.size(env));
        }
    }

    @Test
    public void PackedArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new GCCCompatibilityPackedArray(allocator);

            s.init(env);

            assertEquals((byte) 98, s.getB1());
            check(s.getArray().get(0));
            check(s.getArray().get(1));
            assertEquals(99, s.getN2());

            assertEquals(GCCCompatibilityPackedArray.LAYOUT.byteSize(), s.size(env));
        }
    }

    @Test
    public void NonPackedArray() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new GCCCompatibilityNonPackedArray(allocator);

            s.init(env);

            assertEquals((byte) 100, s.getB1());
            check(s.getArray().get(0));
            check(s.getArray().get(1));
            assertEquals(101, s.getN2());

            assertEquals(GCCCompatibilityNonPackedArray.LAYOUT.byteSize(), s.size(env));
        }
    }

    @Test
    public void ArrayZero() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new GCCCompatibilityArrayZero(allocator);

            s.init(env);

            assertEquals((byte) 102, s.getB1());
            assertEquals(103, s.getN2());

            assertEquals(GCCCompatibilityArrayZero.LAYOUT.byteSize(), s.size(env));
        }
    }

    @Test
    public void NormalContainUnion() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new GCCompatibilityNormalContainUnion(allocator);

            s.initB(env);
            assertEquals((byte) 104, s.getB1());
            assertEquals((byte) 1, s.getUn().getB());
            assertEquals(105, s.getN2());

            s.initS(env);
            assertEquals((byte) 106, s.getB1());
            assertEquals((short) 2, s.getUn().getS());
            assertEquals(107, s.getN2());

            s.initN(env);
            assertEquals((byte) 108, s.getB1());
            assertEquals(3, s.getUn().getN());
            assertEquals(109, s.getN2());

            s.initF(env);
            assertEquals((byte) 110, s.getB1());
            assertEquals(4.0f, s.getUn().getF(), 0);
            assertEquals(111, s.getN2());

            s.initD(env);
            assertEquals((byte) 112, s.getB1());
            assertEquals(8.0, s.getUn().getD(), 0);
            assertEquals(113, s.getN2());

            s.initL(env);
            assertEquals((byte) 114, s.getB1());
            assertEquals(9L, s.getUn().getL());
            assertEquals(115, s.getN2());

            assertEquals(GCCompatibilityNormalContainUnion.LAYOUT.byteSize(), s.size(env));
        }
    }

    @Test
    public void PackedContainUnion() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new GCCompatibilityPackedContainUnion(allocator);

            s.initB(env);
            assertEquals((byte) 116, s.getB1());
            assertEquals((byte) 1, s.getUn().getB());
            assertEquals(117, s.getN2());

            s.initS(env);
            assertEquals((byte) 118, s.getB1());
            assertEquals((short) 2, s.getUn().getS());
            assertEquals(119, s.getN2());

            s.initN(env);
            assertEquals((byte) 120, s.getB1());
            assertEquals(3, s.getUn().getN());
            assertEquals(121, s.getN2());

            s.initF(env);
            assertEquals((byte) 122, s.getB1());
            assertEquals(4.0f, s.getUn().getF(), 0);
            assertEquals(123, s.getN2());

            s.initD(env);
            assertEquals((byte) 124, s.getB1());
            assertEquals(8.0, s.getUn().getD(), 0);
            assertEquals(125, s.getN2());

            s.initL(env);
            assertEquals((byte) 126, s.getB1());
            assertEquals(9L, s.getUn().getL());
            assertEquals(127, s.getN2());

            assertEquals(GCCompatibilityPackedContainUnion.LAYOUT.byteSize(), s.size(env));
        }
    }

    @Test
    public void StructAlign() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            var s = new GCCompatibilityStructAlign(allocator);

            s.init(env);

            assertEquals(1, s.getN());
            assertEquals(2L, s.getL());

            assertEquals(GCCompatibilityStructAlign.LAYOUT.byteSize(), s.size(env));
        }
    }
}
