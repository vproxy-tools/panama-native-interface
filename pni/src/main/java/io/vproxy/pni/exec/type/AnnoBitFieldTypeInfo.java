package io.vproxy.pni.exec.type;

public class AnnoBitFieldTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoBitFieldTypeInfo() {
        super("io.vproxy.pni.annotation.BitField", "io/vproxy/pni/annotation/BitField", "Lio/vproxy/pni/annotation/BitField;");
    }

    private static final AnnoBitFieldTypeInfo INSTANCE = new AnnoBitFieldTypeInfo();

    public static AnnoBitFieldTypeInfo get() {
        return INSTANCE;
    }
}
