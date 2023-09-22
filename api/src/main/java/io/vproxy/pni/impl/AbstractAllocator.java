package io.vproxy.pni.impl;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIRef;

public abstract class AbstractAllocator implements Allocator {
    private PNIRef<Allocator> ref = null;

    @Override
    public PNIRef<Allocator> ref() {
        if (ref != null)
            return ref;
        synchronized (this) {
            if (ref != null)
                return ref;
            ref = PNIRef.of(this);
        }
        return ref;
    }

    @Override
    public void close() {
        PNIRef<Allocator> ref;
        synchronized (this) {
            ref = this.ref;
            if (ref == null)
                return;
            this.ref = null;
        }
        ref.close();
    }
}
