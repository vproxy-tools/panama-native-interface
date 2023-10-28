package io.vproxy.pni.exec.type;

public class AnnoBitTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoBitTypeInfo() {
        super("io.vproxy.pni.annotation.Bit", "io/vproxy/pni/annotation/Bit", "Lio/vproxy/pni/annotation/Bit;");
    }

    private static final AnnoBitTypeInfo INSTANCE = new AnnoBitTypeInfo();

    public static AnnoBitTypeInfo get() {
        return INSTANCE;
    }
}
