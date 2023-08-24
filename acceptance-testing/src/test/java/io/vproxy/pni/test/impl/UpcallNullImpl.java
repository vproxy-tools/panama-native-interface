package io.vproxy.pni.test.impl;

import io.vproxy.pni.CallSite;
import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.PNIString;
import io.vproxy.pni.array.*;
import io.vproxy.pni.test.Null;
import io.vproxy.pni.test.ObjectStruct;
import io.vproxy.pni.test.UpcallNull;

import java.lang.foreign.MemorySegment;
import java.nio.ByteBuffer;

public class UpcallNullImpl implements UpcallNull.Interface {
    private UpcallNullImpl() {
    }

    private static final UpcallNullImpl IMPL = new UpcallNullImpl();

    public static UpcallNullImpl get() {
        return IMPL;
    }

    @Override
    public boolean testParam(ObjectStruct o, PNIString str, MemorySegment seg, ByteBuffer buf, MemorySegment byteArr, BoolArray boolArr, CharArray charArr, FloatArray floatArr, DoubleArray doubleArr, IntArray intArr, LongArray longArr, ShortArray shortArr, ObjectStruct.Array oArr, Object ref, CallSite<Null> func, CallSite<Void> funcVoid, CallSite<Object> funcRef) {
        return o == null && str == null && seg == null && buf == null && byteArr == null && boolArr == null && charArr == null && floatArr == null && doubleArr == null && intArr == null && longArr == null && shortArr == null && oArr == null && ref == null && func == null && funcVoid == null && funcRef == null;
    }

    @Override
    public boolean testParamRaw(PNIRef<Object> ref, PNIFunc<Null> func, PNIFunc<Void> funcVoid, PNIFunc<Object> funcRef) {
        return ref == null && func == null && funcVoid == null && funcRef == null;
    }

    @Override
    public ObjectStruct returnO(ObjectStruct return_) {
        return null;
    }

    @Override
    public PNIString returnStr() {
        return null;
    }

    @Override
    public MemorySegment returnSeg() {
        return null;
    }

    @Override
    public ByteBuffer returnBuf() {
        return null;
    }

    @Override
    public MemorySegment returnByteArr() {
        return null;
    }

    @Override
    public BoolArray returnBoolArr() {
        return null;
    }

    @Override
    public CharArray returnCharArr() {
        return null;
    }

    @Override
    public FloatArray returnFloatArr() {
        return null;
    }

    @Override
    public DoubleArray returnDoubleArr() {
        return null;
    }

    @Override
    public IntArray returnIntArr() {
        return null;
    }

    @Override
    public LongArray returnLongArr() {
        return null;
    }

    @Override
    public ShortArray returnShortArr() {
        return null;
    }

    @Override
    public ObjectStruct.Array returnOArr() {
        return null;
    }

    @Override
    public PNIRef<Object> returnRef() {
        return null;
    }

    @Override
    public PNIFunc<Null> returnFunc() {
        return null;
    }

    @Override
    public PNIFunc<Void> returnFuncVoid() {
        return null;
    }

    @Override
    public PNIFunc<Object> returnFuncRef() {
        return null;
    }
}
