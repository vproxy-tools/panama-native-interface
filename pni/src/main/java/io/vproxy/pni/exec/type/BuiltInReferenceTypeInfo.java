package io.vproxy.pni.exec.type;

public abstract class BuiltInReferenceTypeInfo extends TypeInfo {
    private final String name;
    private final String internalName;
    private final String desc;

    public BuiltInReferenceTypeInfo(String name, String internalName, String desc) {
        this.name = name;
        this.internalName = internalName;
        this.desc = desc;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String internalName() {
        return internalName;
    }

    @Override
    public String desc() {
        return desc;
    }
}
