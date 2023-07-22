package io.vproxy.pni.exec.type;

public class AnnoLenTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoLenTypeInfo() {
        super("io.vproxy.pni.annotation.Len", "io/vproxy/pni/annotation/Len", "Lio/vproxy/pni/annotation/Len;");
    }

    private static final AnnoLenTypeInfo INSTANCE = new AnnoLenTypeInfo();

    public static AnnoLenTypeInfo get() {
        return INSTANCE;
    }
}
