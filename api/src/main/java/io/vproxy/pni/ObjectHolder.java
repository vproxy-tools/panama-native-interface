package io.vproxy.pni;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ObjectHolder<T> {
    private final AtomicLong indexCounter = new AtomicLong();
    private final ConcurrentMap<Long, T> storage = new ConcurrentHashMap<>();
    private final AtomicReferenceArray<Box<T>> fastStorage; // step 1
    private final Lock fastStorageLock = new ReentrantLock();

    private record Box<T>(long index, T obj) {
    }

    public ObjectHolder(int fastStorageSize) {
        fastStorage = new AtomicReferenceArray<>(fastStorageSize);
    }

    public long store(T obj) {
        long index;
        do {
            do {
                index = indexCounter.incrementAndGet();
            } while (index == 0);
        } while (indexIsInUse(index));

        if (fastStorage.length() != 0) {
            var arrIdx = (int) (Math.abs(index) % fastStorage.length());
            fastStorageLock.lock();
            try {
                if (fastStorage.get(arrIdx) == null) {
                    fastStorage.set(arrIdx, new Box<>(index, obj));
                    return index;
                }
            } finally {
                fastStorageLock.unlock();
            }
        }

        storage.put(index, obj);
        return index;
    }

    private boolean indexIsInUse(long index) {
        if (fastStorage.length() != 0) {
            var arrIdx = (int) (Math.abs(index) % fastStorage.length());
            var box = fastStorage.get(arrIdx);
            if (box != null && box.index == index) {
                return true;
            }
        }
        return storage.containsKey(index);
    }

    private T tryToFindFuncFast(long index) {
        if (fastStorage.length() == 0) {
            return null;
        }
        int arrIdx = (int) (Math.abs(index) % fastStorage.length());
        var box = fastStorage.get(arrIdx);
        if (box == null) {
            return null;
        }
        if (box.index == index) {
            return box.obj;
        }
        return null;
    }

    public T get(long index) {
        T obj = tryToFindFuncFast(index);
        if (obj != null) {
            return obj;
        }
        return storage.get(index);
    }

    public T remove(long index) {
        if (fastStorage.length() != 0) {
            int arrIdx = (int) (Math.abs(index) % fastStorage.length());
            fastStorageLock.lock();
            try {
                var box = fastStorage.get(arrIdx);
                if (box != null && box.index == index) {
                    fastStorage.set(arrIdx, null);
                    return box.obj;
                }
            } finally {
                fastStorageLock.unlock();
            }
        }
        return storage.remove(index);
    }

    public int size() {
        // no lock here because it won't hurt for concurrency
        int cnt = 0;
        for (int i = 0; i < fastStorage.length(); ++i) {
            var o = fastStorage.get(i);
            if (o != null) ++cnt;
        }
        return cnt + storage.size();
    }
}
