package io.vproxy.pni.exec.ast;

public class AstAnnoValue {
    public String name;
    public Object value;

    public AstAnnoValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public void toString(StringBuilder sb) {
        sb.append(name).append("=").append(value);
    }
}
