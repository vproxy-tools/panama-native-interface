package io.vproxy.pni.exec.type;

public class AnnoStructTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoStructTypeInfo() {
        super("io.vproxy.pni.annotation.Struct", "io/vproxy/pni/annotation/Struct", "Lio/vproxy/pni/annotation/Struct;");
    }

    private static final AnnoStructTypeInfo INSTANCE = new AnnoStructTypeInfo();

    public static AnnoStructTypeInfo get() {
        return INSTANCE;
    }
}
