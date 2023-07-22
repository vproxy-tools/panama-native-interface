package io.vproxy.pni.exec.type;

public class AnnoUnionTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoUnionTypeInfo() {
        super("io.vproxy.pni.annotation.Union", "io/vproxy/pni/annotation/Union", "Lio/vproxy/pni/annotation/Union;");
    }

    private static final AnnoUnionTypeInfo INSTANCE = new AnnoUnionTypeInfo();

    public static AnnoUnionTypeInfo get() {
        return INSTANCE;
    }
}
