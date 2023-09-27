package io.vproxy.pni.test;

import io.vproxy.pni.ObjectHolder;
import io.vproxy.pni.PNIRef;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;

public class TestRef {
    private final List<PNIRef<Integer>> refs = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        var holderF = PNIRef.class.getDeclaredField("holder");
        holderF.setAccessible(true);
        //noinspection rawtypes
        var holder = (ObjectHolder) holderF.get(null);
        var counterF = ObjectHolder.class.getDeclaredField("indexCounter");
        counterF.setAccessible(true);
        var counter = (AtomicLong) counterF.get(holder);
        counter.set(0);
    }

    @After
    public void tearDown() {
        for (var ref : refs) {
            ref.close();
        }
        assertEquals(0, PNIRef.currentRefStorageSize());
        refs.clear();
    }

    @Test
    public void ref4096() {
        for (int i = 0; i < 4096; ++i) {
            var ref = PNIRef.of(i);
            refs.add(ref);
        }
        for (int i = 0; i < 4096; ++i) {
            var ref = PNIRef.of(refs.get(i).MEMORY);
            assertEquals(i, ref.getRef());
        }
        assertEquals(4096, PNIRef.currentRefStorageSize());
    }

    @Test
    public void ref16384() {
        for (int i = 0; i < 16384; ++i) {
            var ref = PNIRef.of(i);
            refs.add(ref);
        }
        for (int i = 0; i < 4096; ++i) {
            var ref = PNIRef.of(refs.get(i).MEMORY);
            assertEquals(i, ref.getRef());
        }
        for (int i = 4096; i < 16384; ++i) {
            var ref = PNIRef.of(refs.get(i).MEMORY);
            assertEquals(i, ref.getRef());
        }
        assertEquals(16384, PNIRef.currentRefStorageSize());
    }
}
