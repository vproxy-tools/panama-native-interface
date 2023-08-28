package io.vproxy.pni.exec.ast;

public class BitFieldInfo {
    public final String name;
    public final int offset;
    public final int bit;

    public BitFieldInfo(String name, int offset, int bit) {
        this.name = name;
        this.offset = offset;
        this.bit = bit;
    }
}
