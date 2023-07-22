package io.vproxy.pni.exec.type;

public class AnnoPointerTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoPointerTypeInfo() {
        super("io.vproxy.pni.annotation.Pointer", "io/vproxy/pni/annotation/Pointer", "Lio/vproxy/pni/annotation/Pointer;");
    }

    private static final AnnoPointerTypeInfo INSTANCE = new AnnoPointerTypeInfo();

    public static AnnoPointerTypeInfo get() {
        return INSTANCE;
    }
}
