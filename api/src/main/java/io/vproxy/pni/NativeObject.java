package io.vproxy.pni;

import java.lang.foreign.MemorySegment;
import java.util.Set;

public interface NativeObject {
    MemorySegment MEMORY();

    @Override
    String toString();

    void toString(StringBuilder sb, int indent, Set<NativeObjectTuple> visited, boolean corrupted);
}
