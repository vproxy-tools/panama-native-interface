package io.vproxy.pni;

public interface PooledAllocatorProvider {
    Allocator create();
}
