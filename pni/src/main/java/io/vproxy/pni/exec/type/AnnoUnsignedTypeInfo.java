package io.vproxy.pni.exec.type;

public class AnnoUnsignedTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoUnsignedTypeInfo() {
        super("io.vproxy.pni.annotation.Unsigned", "io/vproxy/pni/annotation/Unsigned", "Lio/vproxy/pni/annotation/Unsigned;");
    }

    private static final AnnoUnsignedTypeInfo INSTANCE = new AnnoUnsignedTypeInfo();

    public static AnnoUnsignedTypeInfo get() {
        return INSTANCE;
    }
}
