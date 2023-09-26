package io.vproxy.pni.exec.type;

public class AnnoSuppressTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoSuppressTypeInfo() {
        super("io.vproxy.pni.annotation.Suppress", "io/vproxy/pni/annotation/Suppress", "Lio/vproxy/pni/annotation/Suppress;");
    }

    private static final AnnoSuppressTypeInfo INSTANCE = new AnnoSuppressTypeInfo();

    public static AnnoSuppressTypeInfo get() {
        return INSTANCE;
    }
}
