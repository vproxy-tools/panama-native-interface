package io.vproxy.pni.exec.type;

public class AnnoRawTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoRawTypeInfo() {
        super("io.vproxy.pni.annotation.Raw", "io/vproxy/pni/annotation/Raw", "Lio/vproxy/pni/annotation/Raw;");
    }

    private static final AnnoRawTypeInfo INSTANCE = new AnnoRawTypeInfo();

    public static AnnoRawTypeInfo get() {
        return INSTANCE;
    }
}
