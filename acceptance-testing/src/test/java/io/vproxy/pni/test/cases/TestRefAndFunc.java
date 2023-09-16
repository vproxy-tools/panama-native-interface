package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;
import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.array.IntArray;
import io.vproxy.pni.array.LongArray;
import io.vproxy.pni.array.ShortArray;
import io.vproxy.pni.test.NativeCheck;
import io.vproxy.pni.test.ObjectStruct;
import io.vproxy.pni.test.RefAndFuncFields;
import io.vproxy.pni.test.Userdata;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TestRefAndFunc {
    @Test
    public void getset() {
        var sizeR = PNIRef.currentRefStorageSize();
        var sizeF = PNIFunc.currentFuncStorageSize();
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var o = new RefAndFuncFields(allocator);
            assertNull(o.getRef());
            assertNull(o.getRef2());
            assertNull(o.getRef3());
            assertNull(o.getFunc());
            assertNull(o.getFunc2());

            Map<String, Integer> map = new HashMap<>();
            var obj = new ObjectStruct(allocator);
            var objLs = List.of(obj);
            var objArr = new ObjectStruct[]{obj};
            var func = PNIRef.Func.<Map<String, Integer>>of(m -> 0);
            var func2 = ObjectStruct.Func.of(oo -> 0);

            o.set(env, map, objLs, objArr, func, func2);

            assertSame(map, o.getRef().getRef());
            assertSame(objLs, o.getRef2().getRef());
            assertSame(objArr, o.getRef3().getRef());
            assertEquals(func.MEMORY.address(), o.getFunc().MEMORY.address());
            assertEquals(func2.MEMORY.address(), o.getFunc2().MEMORY.address());

            assertEquals(sizeR + 3, PNIRef.currentRefStorageSize());

            o.getRef().close();
            o.getRef2().close();
            o.getRef3().close();

            assertEquals(sizeR, PNIRef.currentRefStorageSize());

            o.setRef(null);
            o.setRef2(null);
            o.setRef3(null);
            o.setFunc(null);
            o.setFunc2(null);
            assertNull(o.getRef());
            assertNull(o.getRef2());
            assertNull(o.getRef3());
            assertNull(o.getFunc());
            assertNull(o.getFunc2());

            var ref = PNIRef.of(map);
            var ref2 = PNIRef.of(objLs);
            var ref3 = PNIRef.of(objArr);
            o.setRef(ref);
            o.setRef2(ref2);
            o.setRef3(ref3);
            o.setFunc(func);
            o.setFunc2(func2);

            assertSame(map, o.getRef().getRef());
            assertSame(objLs, o.getRef2().getRef());
            assertSame(objArr, o.getRef3().getRef());
            assertEquals(func.MEMORY.address(), o.getFunc().MEMORY.address());
            assertEquals(func2.MEMORY.address(), o.getFunc2().MEMORY.address());

            assertEquals(sizeR + 3, PNIRef.currentRefStorageSize());
            assertEquals(sizeF + 2, PNIFunc.currentFuncStorageSize());

            assertEquals(ref.getIndex(), o.retrieveRef(env).getIndex());
            assertEquals(ref2.getIndex(), o.retrieveRef2(env).getIndex());
            assertEquals(ref3.getIndex(), o.retrieveRef3(env).getIndex());
            assertEquals(func.getIndex(), o.retrieveFunc(env).getIndex());
            assertEquals(func2.getIndex(), o.retrieveFunc2(env).getIndex());

            o.getRef().close();
            o.getRef2().close();
            o.getRef3().close();
            func.close();
            func2.close();
        }
        assertEquals(sizeR, PNIRef.currentRefStorageSize());
        assertEquals(sizeF, PNIFunc.currentFuncStorageSize());
    }

    @Test
    public void call() {
        var sizeR = PNIRef.currentRefStorageSize();
        var sizeF = PNIFunc.currentFuncStorageSize();
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var o = new RefAndFuncFields(allocator);
            Map<String, Integer> map = new HashMap<>();
            o.setRef(PNIRef.of(map));
            o.setFunc(PNIRef.Func.of(m -> {
                m.put("a", 1);
                m.put("b", 2);
                return 0;
            }));

            o.call(env);

            assertEquals(Map.of("a", 1, "b", 2), map);

            assertEquals(sizeR + 1, PNIRef.currentRefStorageSize());
            assertEquals(sizeF + 1, PNIFunc.currentFuncStorageSize());

            o.getRef().close();
            o.getFunc().close();
        }
        assertEquals(sizeR, PNIRef.currentRefStorageSize());
        assertEquals(sizeF, PNIFunc.currentFuncStorageSize());
    }

    @Test
    public void call2() {
        var sizeF = PNIFunc.currentFuncStorageSize();
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var o = new RefAndFuncFields(allocator);
            var oo = new ObjectStruct(allocator);
            oo.setLenStr("hello");

            o.setFunc2(ObjectStruct.Func.of(ooo -> {
                ooo.setLenStr("world");
                return 0;
            }));

            o.call2(env, oo);

            assertEquals("world", oo.getLenStr());

            assertEquals(sizeF + 1, PNIFunc.currentFuncStorageSize());
            o.getFunc2().close();
        }
        assertEquals(sizeF, PNIFunc.currentFuncStorageSize());
    }

    @Test
    public void setRaw() {
        var sizeR = PNIRef.currentRefStorageSize();
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var o = new RefAndFuncFields(allocator);
            assertNull(o.getRef());
            assertNull(o.getRef2());
            assertNull(o.getRef3());

            Map<String, Integer> map = new HashMap<>();
            var obj = new ObjectStruct(allocator);
            var objLs = List.of(obj);
            var objArr = new ObjectStruct[]{obj};

            o.setRaw(env, PNIRef.of(map), PNIRef.of(objLs), PNIRef.of(objArr));

            assertSame(map, o.getRef().getRef());
            assertSame(objLs, o.getRef2().getRef());
            assertSame(objArr, o.getRef3().getRef());

            assertEquals(sizeR + 3, PNIRef.currentRefStorageSize());

            o.getRef().close();
            o.getRef2().close();
            o.getRef3().close();
        }
        assertEquals(sizeR, PNIRef.currentRefStorageSize());
    }

    @Test
    public void refUserData() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            List<Integer> ls = new ArrayList<>();
            var ref = PNIRef.of(ls, new PNIRef.Options().setUserdataByteSize(Userdata.LAYOUT.byteSize()));
            var udMem = ref.getUserdata();
            assertNotNull(udMem);

            var ud = new Userdata(udMem);
            assertEquals(0, ud.getX());
            assertEquals(0L, ud.getY());
            assertEquals((short) 0, ud.getZ());

            ud.setX(12);
            ud.setY(34);
            ud.setZ((short) 56);

            var x = new IntArray(allocator, 1);
            var y = new LongArray(allocator, 1);
            var z = new ShortArray(allocator, 1);

            NativeCheck.get().checkUserdataForRef(env, ref, x, y, z);

            assertEquals(12, x.get(0));
            assertEquals(34L, y.get(0));
            assertEquals((short) 56, z.get(0));

            ref.close();
        }
    }

    @Test
    public void funcUserData() {
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            var func = PNIFunc.VoidFunc.of(v -> 0, new PNIFunc.Options().setUserdataByteSize(Userdata.LAYOUT.byteSize()));
            var udMem = func.getUserdata();
            assertNotNull(udMem);

            var ud = new Userdata(udMem);
            assertEquals(0, ud.getX());
            assertEquals(0L, ud.getY());
            assertEquals((short) 0, ud.getZ());

            ud.setX(12);
            ud.setY(34);
            ud.setZ((short) 56);

            var x = new IntArray(allocator, 1);
            var y = new LongArray(allocator, 1);
            var z = new ShortArray(allocator, 1);

            NativeCheck.get().checkUserdataForFunc(env, func, x, y, z);

            assertEquals(12, x.get(0));
            assertEquals(34L, y.get(0));
            assertEquals((short) 56, z.get(0));

            func.close();
        }
    }
}
