package io.vproxy.pni.test;

import io.vproxy.pni.ObjectHolder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class TestObjectHolder {
    @Test
    public void storeFast() {
        var holder = new ObjectHolder<Integer>(32);
        var indexes = new ArrayList<Long>();
        for (int i = 0; i < 32; ++i) {
            indexes.add(holder.store(i));
        }
        assertEquals(32, indexes.size());
        for (int i = 0; i < 32; ++i) {
            assertEquals(i + 1, (long) indexes.get(i));
        }
    }

    @Test
    public void storeStorage() {
        var holder = new ObjectHolder<Integer>(32);
        var indexes = new ArrayList<Long>();
        for (int i = 0; i < 48; ++i) {
            indexes.add(holder.store(i));
        }
        assertEquals(48, indexes.size());
        for (int i = 0; i < 48; ++i) {
            assertEquals(i + 1, (long) indexes.get(i));
        }
    }

    @Test
    public void removeFast() {
        var holder = new ObjectHolder<Integer>(32);
        var indexes = new ArrayList<Long>();
        for (int i = 0; i < 32; ++i) {
            indexes.add(holder.store(i));
        }
        assertEquals(32, holder.size());
        for (int i = 0; i < indexes.size(); ++i) {
            holder.remove(indexes.get(i));
            assertEquals(32 - i - 1, holder.size());
        }
        assertEquals(0, holder.size());
    }

    @Test
    public void removeStorage() {
        var holder = new ObjectHolder<Integer>(32);
        var indexes = new ArrayList<Long>();
        for (int i = 0; i < 48; ++i) {
            indexes.add(holder.store(i));
        }
        assertEquals(48, holder.size());
        for (int i = 32; i < indexes.size(); ++i) {
            holder.remove(indexes.get(i));
            assertEquals(48 - i - 1 + 32, holder.size());
        }
        assertEquals(32, holder.size());
    }

    @Test
    public void getFast() {
        var holder = new ObjectHolder<Integer>(32);
        var indexes = new ArrayList<Long>();
        for (int i = 0; i < 32; ++i) {
            indexes.add(holder.store(i));
        }
        assertEquals(32, holder.size());
        for (int i = 0; i < indexes.size(); ++i) {
            var n = holder.get(indexes.get(i));
            assertEquals(i, (int) n);
        }
    }

    @Test
    public void getStorage() {
        var holder = new ObjectHolder<Integer>(32);
        var indexes = new ArrayList<Long>();
        for (int i = 0; i < 48; ++i) {
            indexes.add(holder.store(i));
        }
        assertEquals(48, holder.size());
        for (int i = 32; i < indexes.size(); ++i) {
            var n = holder.get(indexes.get(i));
            assertEquals(i, (int) n);
        }
    }

    @Test
    public void multiThread() throws Exception {
        var holder = new ObjectHolder<Integer>(32);
        var errors = new AtomicInteger(0);
        var threads = new ArrayList<Thread>();
        for (int i = 0; i < 128; ++i) {
            var t = new Thread(() -> {
                var start = System.currentTimeMillis();
                while (System.currentTimeMillis() - start < 4_000) {
                    var n = ThreadLocalRandom.current().nextInt();
                    var index = holder.store(n);
                    Thread.yield();
                    Thread.yield();
                    var nn = holder.get(index);
                    if (n != nn) {
                        errors.incrementAndGet();
                    }
                    Thread.yield();
                    Thread.yield();
                    nn = holder.remove(index);
                    if (n != nn) {
                        errors.incrementAndGet();
                    }
                }
            });
            t.start();
            threads.add(t);
        }
        for (var thread : threads) {
            thread.join();
        }
        assertEquals(0, errors.get());
    }
}
