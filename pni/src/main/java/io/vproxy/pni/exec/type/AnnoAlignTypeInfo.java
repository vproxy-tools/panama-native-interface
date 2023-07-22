package io.vproxy.pni.exec.type;

public class AnnoAlignTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoAlignTypeInfo() {
        super("io.vproxy.pni.annotation.Align", "io/vproxy/pni/annotation/Align", "Lio/vproxy/pni/annotation/Align;");
    }

    private static final AnnoAlignTypeInfo INSTANCE = new AnnoAlignTypeInfo();

    public static AnnoAlignTypeInfo get() {
        return INSTANCE;
    }
}
