package io.vproxy.pni.test.impl;

import io.vproxy.pni.*;
import io.vproxy.pni.array.*;
import io.vproxy.pni.test.ObjectStruct;
import io.vproxy.pni.test.Upcall;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.util.List;

public class UpcallImpl implements Upcall.Interface {
    private UpcallImpl() {
    }

    private static final UpcallImpl IMPL = new UpcallImpl();

    public static UpcallImpl get() {
        return IMPL;
    }

    public byte b;
    public byte ub;
    public boolean z;
    public char c;
    public double d;
    public float f;
    public int i;
    public int ui;
    public long j;
    public long uj;
    public short s;
    public short us;

    @Override
    public void primaryParams(byte b, byte ub, boolean z, char c, double d, float f, int i, int ui, long j, long uj, short s, short us) {
        this.b = b;
        this.ub = ub;
        this.z = z;
        this.c = c;
        this.d = d;
        this.f = f;
        this.i = i;
        this.ui = ui;
        this.j = j;
        this.uj = uj;
        this.s = s;
        this.us = us;
    }

    @Override
    public byte returnByte() {
        return 21;
    }

    @Override
    public boolean returnBool() {
        return true;
    }

    @Override
    public char returnChar() {
        return 'a';
    }

    @Override
    public double returnDouble() {
        return 12.8;
    }

    @Override
    public float returnFloat() {
        return 6.4f;
    }

    @Override
    public int returnInt() {
        return 22;
    }

    @Override
    public long returnLong() {
        return 23;
    }

    @Override
    public short returnShort() {
        return 24;
    }

    public byte[] ab;
    public byte[] aub;
    public boolean[] az;
    public char[] ac;
    public double[] ad;
    public float[] af;
    public int[] ai;
    public int[] aui;
    public long[] aj;
    public long[] auj;
    public short[] as;
    public short[] aus;

    @Override
    public void primaryArrayParams(MemorySegment b, MemorySegment ub, BoolArray z, CharArray c, DoubleArray d, FloatArray f, IntArray i, IntArray ui, LongArray j, LongArray uj, ShortArray s, ShortArray us) {
        ab = new byte[(int) b.byteSize()];
        MemorySegment.ofArray(ab).copyFrom(b);
        aub = new byte[(int) ub.byteSize()];
        MemorySegment.ofArray(aub).copyFrom(ub);
        az = new boolean[(int) z.length()];
        for (int index = 0; index < az.length; ++index) {
            az[index] = z.get(index);
        }
        ac = new char[(int) c.length()];
        for (int index = 0; index < ac.length; ++index) {
            ac[index] = c.get(index);
        }
        ad = new double[(int) d.length()];
        for (int index = 0; index < ad.length; ++index) {
            ad[index] = d.get(index);
        }
        af = new float[(int) f.length()];
        for (int index = 0; index < af.length; ++index) {
            af[index] = f.get(index);
        }
        ai = new int[(int) i.length()];
        for (int index = 0; index < ai.length; ++index) {
            ai[index] = i.get(index);
        }
        aui = new int[(int) ui.length()];
        for (int index = 0; index < aui.length; ++index) {
            aui[index] = ui.get(index);
        }
        aj = new long[(int) j.length()];
        for (int index = 0; index < aj.length; ++index) {
            aj[index] = j.get(index);
        }
        auj = new long[(int) uj.length()];
        for (int index = 0; index < auj.length; ++index) {
            auj[index] = uj.get(index);
        }
        as = new short[(int) s.length()];
        for (int index = 0; index < as.length; ++index) {
            as[index] = s.get(index);
        }
        aus = new short[(int) us.length()];
        for (int index = 0; index < aus.length; ++index) {
            aus[index] = us.get(index);
        }
    }

    private final Allocator allocator = Allocator.of(Arena.ofShared());

    private final MemorySegment byteArray = allocator.allocate(3);

    @Override
    public MemorySegment returnByteArray() {
        byteArray.set(ValueLayout.JAVA_BYTE, 0, (byte) 11);
        byteArray.set(ValueLayout.JAVA_BYTE, 1, (byte) 22);
        byteArray.set(ValueLayout.JAVA_BYTE, 2, (byte) 33);
        return byteArray;
    }

    private final BoolArray boolArray = new BoolArray(allocator, 4);

    @Override
    public BoolArray returnBoolArray() {
        boolArray.set(0, true);
        boolArray.set(1, false);
        boolArray.set(2, true);
        boolArray.set(3, false);
        return boolArray;
    }

    private final CharArray charArray = new CharArray(allocator, 5);

    @Override
    public CharArray returnCharArray() {
        charArray.set(0, 'm');
        charArray.set(1, 'n');
        charArray.set(2, 'o');
        charArray.set(3, 'p');
        charArray.set(4, 'q');
        return charArray;
    }

    private final DoubleArray doubleArray = new DoubleArray(allocator, 6);

    @Override
    public DoubleArray returnDoubleArray() {
        for (int i = 0; i < doubleArray.length(); ++i) {
            doubleArray.set(i, 0.2 * Math.pow(2, i));
        }
        return doubleArray;
    }

    private final FloatArray floatArray = new FloatArray(allocator, 7);

    @Override
    public FloatArray returnFloatArray() {
        for (int i = 0; i < floatArray.length(); ++i) {
            floatArray.set(i, (float) (12.8 * Math.pow(2, i)));
        }
        return floatArray;
    }

    private final IntArray intArray = new IntArray(allocator, 8);

    @Override
    public IntArray returnIntArray() {
        intArray.set(0, 12);
        intArray.set(1, 23);
        intArray.set(2, 34);
        intArray.set(3, 45);
        intArray.set(4, 56);
        intArray.set(5, 67);
        intArray.set(6, 78);
        intArray.set(7, 89);
        return intArray;
    }

    private final LongArray longArray = new LongArray(allocator, 9);

    @Override
    public LongArray returnLongArray() {
        longArray.set(0, 910);
        longArray.set(1, 1011);
        longArray.set(2, 1112);
        longArray.set(3, 1213);
        longArray.set(4, 1314);
        longArray.set(5, 1415);
        longArray.set(6, 1516);
        longArray.set(7, 1617);
        longArray.set(8, 1718);
        return longArray;
    }

    private final ShortArray shortArray = new ShortArray(allocator, 10);

    @Override
    public ShortArray returnShortArray() {
        shortArray.set(0, (short) 1920);
        shortArray.set(1, (short) 2021);
        shortArray.set(2, (short) 2122);
        shortArray.set(3, (short) 2223);
        shortArray.set(4, (short) 2324);
        shortArray.set(5, (short) 2425);
        shortArray.set(6, (short) 2526);
        shortArray.set(7, (short) 2627);
        shortArray.set(8, (short) 2728);
        shortArray.set(9, (short) 2829);
        return shortArray;
    }

    public String objLenStr;

    @Override
    public void objectParams(ObjectStruct o) {
        objLenStr = o.getLenStr();
    }

    @Override
    public ObjectStruct returnObject(ObjectStruct return_) {
        return_.setLenStr("hello");
        return return_;
    }

    public String[] objectRawArray;

    @Override
    public void objectArrayParams(ObjectStruct.Array o) {
        objectRawArray = new String[(int) o.length()];
        for (int i = 0; i < objectRawArray.length; ++i) {
            objectRawArray[i] = o.get(i).getLenStr();
        }
    }

    private final ObjectStruct.Array objectArray = new ObjectStruct.Array(allocator, 3);

    @Override
    public ObjectStruct.Array returnObjectArray() {
        objectArray.get(0).setLenStr("hello");
        objectArray.get(1).setLenStr("world");
        objectArray.get(2).setLenStr("hello world");
        return objectArray;
    }

    public byte[] _buffer;
    public int voidCallSiteRes;
    public int objCallSiteRes;
    public int refCallSiteRes;
    public MemorySegment _mem;
    public int voidFuncRes;
    public int objFuncRes;
    public int refFuncRes;
    public List<Integer> _ref;
    public List<Integer> _rawRef;
    public String _str;

    @Override
    public void otherParams(ByteBuffer buffer, CallSite<Void> voidCallSite, CallSite<ObjectStruct> objCallSite, CallSite<List<String>> refCallSite, MemorySegment mem, PNIFunc<Void> voidFunc, PNIFunc<ObjectStruct> objFunc, PNIFunc<List<String>> refFunc, List<Integer> ref, PNIRef<List<Integer>> rawRef, PNIString str) {
        _buffer = new byte[buffer.limit() - buffer.position()];
        buffer.get(_buffer);
        voidCallSiteRes = voidCallSite.call(null);
        try (var allocator = Allocator.ofConfined()) {
            var o = new ObjectStruct(allocator);
            o.setLenStr("xxx");
            objCallSiteRes = objCallSite.call(o);
        }
        refCallSiteRes = refCallSite.call(List.of("a", "b", "c"));
        _mem = mem;
        voidFuncRes = voidFunc.getCallSite().call(null);
        try (var allocator = Allocator.ofConfined()) {
            var o = new ObjectStruct(allocator);
            o.setLenStr("yyy");
            objFuncRes = objFunc.getCallSite().call(o);
        }
        refFuncRes = refFunc.getCallSite().call(List.of("d", "e", "f"));
        _ref = ref;
        _rawRef = rawRef.getRef();
        _str = str.toString();
    }

    private final ByteBuffer buffer = ByteBuffer.allocateDirect(16);

    @Override
    public ByteBuffer returnBuffer() {
        buffer.position(0).limit(buffer.capacity());
        buffer.put(new byte[]{1, 2, 3, 4, 5, 6}).flip();
        return buffer;
    }

    private final MemorySegment mem = allocator.allocate(15);

    @Override
    public MemorySegment returnMem() {
        mem.setUtf8String(0, "alice-bob-eve");
        return mem;
    }

    public int testReturnVoidFunc;

    @Override
    public PNIFunc<Void> returnVoidFunc() {
        return PNIFunc.VoidFunc.of(v -> {
            ++testReturnVoidFunc;
            return 10086;
        });
    }

    public String testReturnObjFunc;

    @Override
    public PNIFunc<ObjectStruct> returnObjFunc() {
        return ObjectStruct.Func.of(o -> {
            testReturnObjFunc = o.getLenStr();
            return 10087;
        });
    }

    public List<String> testReturnRefFunc;

    @Override
    public PNIFunc<List<String>> returnRefFunc() {
        return PNIRef.Func.of(ls -> {
            testReturnRefFunc = ls;
            return 10088;
        });
    }

    @Override
    public PNIRef<List<Integer>> returnRef() {
        return PNIRef.of(List.of(11, 22, 33));
    }

    private final PNIString str = new PNIString(allocator, "str");

    @Override
    public PNIString returnStr() {
        return str;
    }

    @Override
    public int sum(int a, int b) {
        return a + b;
    }
}
