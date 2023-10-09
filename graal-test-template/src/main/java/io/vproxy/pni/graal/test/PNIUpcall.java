package io.vproxy.pni.graal.test;

import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.annotation.Upcall;

import java.lang.foreign.MemorySegment;

@Upcall
public interface PNIUpcall {
    void doNothingUpcall();

    int intUpcall(int a);

    int refUpcall(PNIRef<Integer> ref);

    int funcUpcall(PNIFunc<Void> func);

    MemorySegment returnSegUpcall();
}
