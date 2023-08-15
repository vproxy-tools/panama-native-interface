package io.vproxy.pni.exec.ast;

public class AstGenericDef {
    public String name;
    public AstTypeDesc desc;

    public AstGenericDef(String name, AstTypeDesc desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return name + " extends " + desc;
    }
}
