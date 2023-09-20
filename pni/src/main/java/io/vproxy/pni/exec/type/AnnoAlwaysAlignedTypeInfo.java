package io.vproxy.pni.exec.type;

public class AnnoAlwaysAlignedTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoAlwaysAlignedTypeInfo() {
        super("io.vproxy.pni.annotation.AlwaysAligned", "io/vproxy/pni/annotation/AlwaysAligned", "Lio/vproxy/pni/annotation/AlwaysAligned;");
    }

    private static final AnnoAlwaysAlignedTypeInfo INSTANCE = new AnnoAlwaysAlignedTypeInfo();

    public static AnnoAlwaysAlignedTypeInfo get() {
        return INSTANCE;
    }
}
