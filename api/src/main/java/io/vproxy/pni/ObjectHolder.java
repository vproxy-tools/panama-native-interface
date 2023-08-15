package io.vproxy.pni;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class ObjectHolder<T> {
    private final AtomicLong indexCounter = new AtomicLong();
    private final ConcurrentHashMap<Long, T> storage = new ConcurrentHashMap<>();
    private final Object[] fastStorage;
    private final ReadWriteLock fastStorageLock = new ReentrantReadWriteLock();

    public ObjectHolder(int fastStorageSize) {
        fastStorage = new Object[fastStorageSize];
    }

    abstract protected long getObjectIndex(T obj);

    public long store(T obj) {
        var index = indexCounter.incrementAndGet();
        while (index == 0) {
            index = indexCounter.incrementAndGet();
        }
        storage.put(index, obj);
        if (fastStorage.length != 0) {
            var arrIdx = (int) (Math.abs(index) % fastStorage.length);
            var wlock = fastStorageLock.writeLock();
            wlock.lock();
            fastStorage[arrIdx] = obj;
            wlock.unlock();
        }
        return index;
    }

    private Object tryToFindFuncFast(long index) {
        if (fastStorage.length == 0) {
            return null;
        }
        int arrIdx = (int) (Math.abs(index) % fastStorage.length);
        var rlock = fastStorageLock.readLock();
        rlock.lock();
        var obj = fastStorage[arrIdx];
        if (obj == null) {
            rlock.unlock();
            return null;
        }
        //noinspection unchecked
        long objIdx = getObjectIndex((T) obj);
        assert objIdx != 0;
        if (objIdx != index) {
            obj = null;
        }
        rlock.unlock();
        return obj;
    }

    public T get(long index) {
        Object obj = tryToFindFuncFast(index);
        if (obj == null) {
            obj = storage.get(index);
        }
        // nullable
        //noinspection unchecked
        return (T) obj;
    }

    public T remove(long index) {
        if (fastStorage.length != 0) {
            int arrIdx = (int) (Math.abs(index) % fastStorage.length);
            var wlock = fastStorageLock.writeLock();
            wlock.lock();
            var obj = fastStorage[arrIdx];
            //noinspection unchecked
            if (obj != null && getObjectIndex((T) obj) == index) {
                fastStorage[arrIdx] = null;
            }
            wlock.unlock();
        }
        return storage.remove(index);
    }

    public int size() {
        return storage.size();
    }
}
