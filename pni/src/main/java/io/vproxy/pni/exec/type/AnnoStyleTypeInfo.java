package io.vproxy.pni.exec.type;

public class AnnoStyleTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoStyleTypeInfo() {
        super("io.vproxy.pni.annotation.Style", "io/vproxy/pni/annotation/Style", "Lio/vproxy/pni/annotation/Style;");
    }

    private static final AnnoStyleTypeInfo INSTANCE = new AnnoStyleTypeInfo();

    public static AnnoStyleTypeInfo get() {
        return INSTANCE;
    }
}
