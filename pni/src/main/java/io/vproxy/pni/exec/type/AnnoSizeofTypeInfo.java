package io.vproxy.pni.exec.type;

public class AnnoSizeofTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoSizeofTypeInfo() {
        super("io.vproxy.pni.annotation.Sizeof", "io/vproxy/pni/annotation/Sizeof", "Lio/vproxy/pni/annotation/Sizeof;");
    }

    private static final AnnoSizeofTypeInfo INSTANCE = new AnnoSizeofTypeInfo();

    public static AnnoSizeofTypeInfo get() {
        return INSTANCE;
    }
}
