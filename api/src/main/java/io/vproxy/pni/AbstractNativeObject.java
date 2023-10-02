package io.vproxy.pni;

import java.util.HashSet;

public abstract class AbstractNativeObject implements NativeObject {
    @Override
    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new HashSet<>(), false);
        return sb.toString();
    }
}
