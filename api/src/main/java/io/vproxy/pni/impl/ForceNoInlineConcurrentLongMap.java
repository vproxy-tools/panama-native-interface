package io.vproxy.pni.impl;

import java.util.concurrent.ConcurrentMap;

public class ForceNoInlineConcurrentLongMap<V> {
    private final ConcurrentMap<Long, V> map;

    public ForceNoInlineConcurrentLongMap(ConcurrentMap<Long, V> map) {
        this.map = map;
    }

    public V get(long key) {
        return map.get(key);
    }
}
