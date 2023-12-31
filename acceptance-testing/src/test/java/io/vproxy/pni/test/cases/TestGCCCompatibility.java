package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.test.*;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

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

    private long generateBitField(long bitLimit) {
        long n = ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
        long mask = (1L << bitLimit) - 1;
        return n & mask;
    }

    @Test
    public void bitfieldCWriteJavaRead() {
        for (int index = 0; index < 100; ++index) {
            try (var allocator = Allocator.ofConfined()) {
                var s = new GCCompatibilityBitField(allocator);
                byte a = (byte) generateBitField(1);
                byte a2 = (byte) generateBitField(1);
                byte b = (byte) generateBitField(3);
                byte b2 = (byte) generateBitField(3);
                short c = (short) generateBitField(2);
                short c2 = (short) generateBitField(2);
                short d = (short) generateBitField(3);
                short d2 = (short) generateBitField(3);
                short e = (short) generateBitField(4);
                short e2 = (short) generateBitField(4);
                int f = (int) generateBitField(5);
                int f2 = (int) generateBitField(5);
                int g = (int) generateBitField(6);
                int g2 = (int) generateBitField(6);
                int h = (int) generateBitField(7);
                int h2 = (int) generateBitField(7);
                int i = (int) generateBitField(8);
                int i2 = (int) generateBitField(8);
                long j = generateBitField(1);
                long j2 = generateBitField(1);
                long k = generateBitField(2);
                long k2 = generateBitField(2);
                long l = generateBitField(22);
                long l2 = generateBitField(22);
                long m = generateBitField(33);
                long m2 = generateBitField(33);
                long n = generateBitField(1);
                long n2 = generateBitField(1);
                s.set(a, a2, b, b2, c, c2, d, d2, e, e2, f, f2, g, g2, h, h2, i, i2, j, j2, k, k2, l, l2, m, m2, n, n2);
                assertEquals("a", a, s.getA());
                assertEquals("a2", a2 == 1, s.isA2());
                assertEquals("b", b, s.getB());
                assertEquals("b2", b2, s.getB2());
                assertEquals("c", c, s.getC());
                assertEquals("c2", c2, s.getC2());
                assertEquals("d", d, s.getD());
                assertEquals("d2", d2, s.getD2());
                assertEquals("e", e, s.getE());
                assertEquals("e2", e2, s.getE2());
                assertEquals("f", f, s.getF());
                assertEquals("f2", f2, s.getF2());
                assertEquals("g", g, s.getG());
                assertEquals("g2", g2, s.getG2());
                assertEquals("h", h, s.getH());
                assertEquals("h2", h2, s.getH2());
                assertEquals("i", i, s.getI());
                assertEquals("i2", i2, s.getI2());
                assertEquals("j", j, s.getJ());
                assertEquals("j2", j2, s.getJ2());
                assertEquals("k", k, s.getK());
                assertEquals("k2", k2, s.getK2());
                assertEquals("l", l, s.getL());
                assertEquals("l2", l2, s.getL2());
                assertEquals("m", m, s.getM());
                assertEquals("m2", m2, s.getM2());
                assertEquals("n", n, s.getN());
                assertEquals("n2", n2 == 1, s.isN2());
            }
        }
    }

    @Test
    public void bitfieldJavaWriteCRead() {
        for (int index = 0; index < 100; ++index) {
            try (var allocator = Allocator.ofConfined()) {
                var s = new GCCompatibilityBitField(allocator);
                byte a = (byte) generateBitField(1);
                byte a2 = (byte) generateBitField(1);
                byte b = (byte) generateBitField(3);
                byte b2 = (byte) generateBitField(3);
                short c = (short) generateBitField(2);
                short c2 = (short) generateBitField(2);
                short d = (short) generateBitField(3);
                short d2 = (short) generateBitField(3);
                short e = (short) generateBitField(4);
                short e2 = (short) generateBitField(4);
                int f = (int) generateBitField(5);
                int f2 = (int) generateBitField(5);
                int g = (int) generateBitField(6);
                int g2 = (int) generateBitField(6);
                int h = (int) generateBitField(7);
                int h2 = (int) generateBitField(7);
                int i = (int) generateBitField(8);
                int i2 = (int) generateBitField(8);
                long j = generateBitField(1);
                long j2 = generateBitField(1);
                long k = generateBitField(2);
                long k2 = generateBitField(2);
                long l = generateBitField(22);
                long l2 = generateBitField(22);
                long m = generateBitField(33);
                long m2 = generateBitField(33);
                long n = generateBitField(1);
                long n2 = generateBitField(1);
                s.setA(a);
                s.setA2(a2 == 1);
                s.setB(b);
                s.setB2(b2);
                s.setC(c);
                s.setC2(c2);
                s.setD(d);
                s.setD2(d2);
                s.setE(e);
                s.setE2(e2);
                s.setF(f);
                s.setF2(f2);
                s.setG(g);
                s.setG2(g2);
                s.setH(h);
                s.setH2(h2);
                s.setI(i);
                s.setI2(i2);
                s.setJ(j);
                s.setJ2(j2);
                s.setK(k);
                s.setK2(k2);
                s.setL(l);
                s.setL2(l2);
                s.setM(m);
                s.setM2(m2);
                s.setN(n);
                s.setN2(n2 == 1);
                assertEquals("a", a, s.a());
                assertEquals("a2", a2, s.a2());
                assertEquals("b", b, s.b());
                assertEquals("b2", b2, s.b2());
                assertEquals("c", c, s.c());
                assertEquals("c2", c2, s.c2());
                assertEquals("d", d, s.d());
                assertEquals("d2", d2, s.d2());
                assertEquals("e", e, s.e());
                assertEquals("e2", e2, s.e2());
                assertEquals("f", f, s.f());
                assertEquals("f2", f2, s.f2());
                assertEquals("g", g, s.g());
                assertEquals("g2", g2, s.g2());
                assertEquals("h", h, s.h());
                assertEquals("h2", h2, s.h2());
                assertEquals("i", i, s.i());
                assertEquals("i2", i2, s.i2());
                assertEquals("j", j, s.j());
                assertEquals("j2", j2, s.j2());
                assertEquals("k", k, s.k());
                assertEquals("k2", k2, s.k2());
                assertEquals("l", l, s.l());
                assertEquals("l2", l2, s.l2());
                assertEquals("m", m, s.m());
                assertEquals("m2", m2, s.m2());
                assertEquals("n", n, s.n());
                assertEquals("n2", n2, s.n2());
            }
        }
    }

    @Test
    public void generatedBitfieldCWriteJavaRead() {
        for (int index = 0; index < 100; ++index) {
            try (var allocator = Allocator.ofConfined()) {
                var s = new BitField(allocator);
                byte a = (byte) generateBitField(1);
                byte a2 = (byte) generateBitField(1);
                byte b = (byte) generateBitField(3);
                byte b2 = (byte) generateBitField(3);
                short c = (short) generateBitField(2);
                short c2 = (short) generateBitField(2);
                short d = (short) generateBitField(3);
                short d2 = (short) generateBitField(3);
                short e = (short) generateBitField(4);
                short e2 = (short) generateBitField(4);
                int f = (int) generateBitField(5);
                int f2 = (int) generateBitField(5);
                int g = (int) generateBitField(6);
                int g2 = (int) generateBitField(6);
                int h = (int) generateBitField(7);
                int h2 = (int) generateBitField(7);
                int i = (int) generateBitField(8);
                int i2 = (int) generateBitField(8);
                long j = generateBitField(1);
                long j2 = generateBitField(1);
                long k = generateBitField(2);
                long k2 = generateBitField(2);
                long l = generateBitField(22);
                long l2 = generateBitField(22);
                long m = generateBitField(33);
                long m2 = generateBitField(33);
                long n = generateBitField(1);
                long n2 = generateBitField(1);
                s.set(a, a2, b, b2, c, c2, d, d2, e, e2, f, f2, g, g2, h, h2, i, i2, j, j2, k, k2, l, l2, m, m2, n, n2);
                assertEquals("a", a, s.getA());
                assertEquals("a2", a2 == 1, s.isA2());
                assertEquals("b", b, s.getB());
                assertEquals("b2", b2, s.getB2());
                assertEquals("c", c, s.getC());
                assertEquals("c2", c2, s.getC2());
                assertEquals("d", d, s.getD());
                assertEquals("d2", d2, s.getD2());
                assertEquals("e", e, s.getE());
                assertEquals("e2", e2, s.getE2());
                assertEquals("f", f, s.getF());
                assertEquals("f2", f2, s.getF2());
                assertEquals("g", g, s.getG());
                assertEquals("g2", g2, s.getG2());
                assertEquals("h", h, s.getH());
                assertEquals("h2", h2, s.getH2());
                assertEquals("i", i, s.getI());
                assertEquals("i2", i2, s.getI2());
                assertEquals("j", j, s.getJ());
                assertEquals("j2", j2, s.getJ2());
                assertEquals("k", k, s.getK());
                assertEquals("k2", k2, s.getK2());
                assertEquals("l", l, s.getL());
                assertEquals("l2", l2, s.getL2());
                assertEquals("m", m, s.getM());
                assertEquals("m2", m2, s.getM2());
                assertEquals("n", n, s.getN());
                assertEquals("n2", n2 == 1, s.isN2());
            }
        }
    }

    @Test
    public void generatedBitfieldJavaWriteCRead() {
        for (int index = 0; index < 100; ++index) {
            try (var allocator = Allocator.ofConfined()) {
                var s = new BitField(allocator);
                byte a = (byte) generateBitField(1);
                byte a2 = (byte) generateBitField(1);
                byte b = (byte) generateBitField(3);
                byte b2 = (byte) generateBitField(3);
                short c = (short) generateBitField(2);
                short c2 = (short) generateBitField(2);
                short d = (short) generateBitField(3);
                short d2 = (short) generateBitField(3);
                short e = (short) generateBitField(4);
                short e2 = (short) generateBitField(4);
                int f = (int) generateBitField(5);
                int f2 = (int) generateBitField(5);
                int g = (int) generateBitField(6);
                int g2 = (int) generateBitField(6);
                int h = (int) generateBitField(7);
                int h2 = (int) generateBitField(7);
                int i = (int) generateBitField(8);
                int i2 = (int) generateBitField(8);
                long j = generateBitField(1);
                long j2 = generateBitField(1);
                long k = generateBitField(2);
                long k2 = generateBitField(2);
                long l = generateBitField(22);
                long l2 = generateBitField(22);
                long m = generateBitField(33);
                long m2 = generateBitField(33);
                long n = generateBitField(1);
                long n2 = generateBitField(1);
                s.setA(a);
                s.setA2(a2 == 1);
                s.setB(b);
                s.setB2(b2);
                s.setC(c);
                s.setC2(c2);
                s.setD(d);
                s.setD2(d2);
                s.setE(e);
                s.setE2(e2);
                s.setF(f);
                s.setF2(f2);
                s.setG(g);
                s.setG2(g2);
                s.setH(h);
                s.setH2(h2);
                s.setI(i);
                s.setI2(i2);
                s.setJ(j);
                s.setJ2(j2);
                s.setK(k);
                s.setK2(k2);
                s.setL(l);
                s.setL2(l2);
                s.setM(m);
                s.setM2(m2);
                s.setN(n);
                s.setN2(n2 == 1);
                assertEquals("a", a, s.a());
                assertEquals("a2", a2, s.a2());
                assertEquals("b", b, s.b());
                assertEquals("b2", b2, s.b2());
                assertEquals("c", c, s.c());
                assertEquals("c2", c2, s.c2());
                assertEquals("d", d, s.d());
                assertEquals("d2", d2, s.d2());
                assertEquals("e", e, s.e());
                assertEquals("e2", e2, s.e2());
                assertEquals("f", f, s.f());
                assertEquals("f2", f2, s.f2());
                assertEquals("g", g, s.g());
                assertEquals("g2", g2, s.g2());
                assertEquals("h", h, s.h());
                assertEquals("h2", h2, s.h2());
                assertEquals("i", i, s.i());
                assertEquals("i2", i2, s.i2());
                assertEquals("j", j, s.j());
                assertEquals("j2", j2, s.j2());
                assertEquals("k", k, s.k());
                assertEquals("k2", k2, s.k2());
                assertEquals("l", l, s.l());
                assertEquals("l2", l2, s.l2());
                assertEquals("m", m, s.m());
                assertEquals("m2", m2, s.m2());
                assertEquals("n", n, s.n());
                assertEquals("n2", n2, s.n2());
            }
        }
    }

    @Test
    public void toStringTest() {
        try (var allocator = Allocator.ofConfined()) {
            var b = new BitField(allocator);
            b.set(
                (byte) 0b1,
                (byte) 0b1,
                (byte) 0b101,
                (byte) 0b110,
                (short) 0b11,
                (short) 0b10,
                (short) 0b011,
                (short) 0b100,
                (short) 0b1010,
                (short) 0b1011,
                0b10101,
                0b01010,
                0b110011,
                0b101101,
                0b1010101,
                0b0101010,
                0b11000011,
                0b10111101,
                0b1L,
                0b1L,
                0b01L,
                0b10L,
                12345L,
                23456L,
                45678L,
                56789L,
                0,
                1
            );
            assertEquals("BitField{\n" +
                         "    field01 => " + 0b101_1 + " {\n" +
                         "        a:1 => " + 0b1 + ",\n" +
                         "        b:3 => " + 0b101 + "\n" +
                         "    },\n" +
                         "    field02 => " + 0b110_1 + " {\n" +
                         "        a2:1 => true,\n" +
                         "        b2:3 => " + 0b110 + "\n" +
                         "    },\n" +
                         "    sep01 => null,\n" +
                         "    field03 => " + 0b1010_011_11 + " {\n" +
                         "        c:2 => " + 0b11 + ",\n" +
                         "        d:3 => " + 0b011 + ",\n" +
                         "        e:4 => " + 0b1010 + "\n" +
                         "    },\n" +
                         "    field04 => " + 0b1011_100_10 + " {\n" +
                         "        c2:2 => " + 0b10 + ",\n" +
                         "        d2:3 => " + 0b100 + ",\n" +
                         "        e2:4 => " + 0b1011 + "\n" +
                         "    },\n" +
                         "    sep02 => null,\n" +
                         "    field05 => " + 0b11000011_1010101_110011_10101 + " {\n" +
                         "        f:5 => " + 0b10101 + ",\n" +
                         "        g:6 => " + 0b110011 + ",\n" +
                         "        h:7 => " + 0b1010101 + ",\n" +
                         "        i:8 => " + 0b11000011 + "\n" +
                         "    },\n" +
                         "    field06 => " + 0b10111101_0101010_101101_01010 + " {\n" +
                         "        f2:5 => " + 0b01010 + ",\n" +
                         "        g2:6 => " + 0b101101 + ",\n" +
                         "        h2:7 => " + 0b0101010 + ",\n" +
                         "        i2:8 => " + 0b10111101 + "\n" +
                         "    },\n" +
                         "    sep03 => null,\n" +
                         "    field07 => " + 0b1011001001101110_00000000_11000000111001_01_1L + " {\n" +
                         "        j:1 => " + 0b1 + ",\n" +
                         "        k:2 => " + 0b01 + ",\n" +
                         "        l:22 => 12345,\n" +
                         "        m:33 => 45678,\n" +
                         "        n:1 => 0\n" +
                         "    },\n" +
                         "    field08 => " + 0b1_00000000000000000_1101110111010101_0000000_101101110100000_10_1L + " {\n" +
                         "        j2:1 => " + 0b1 + ",\n" +
                         "        k2:2 => " + 0b10 + ",\n" +
                         "        l2:22 => 23456,\n" +
                         "        m2:33 => 56789,\n" +
                         "        n2:1 => true\n" +
                         "    }\n" +
                         "}@" + Long.toString(b.MEMORY.address(), 16),
                b.toString());
        }
    }
}
