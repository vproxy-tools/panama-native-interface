package io.vproxy.pni.exec.type;

public class AnnoIncludeTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoIncludeTypeInfo() {
        super("io.vproxy.pni.annotation.Include", "io/vproxy/pni/annotation/Include", "Lio/vproxy/pni/annotation/Include;");
    }

    private static final AnnoIncludeTypeInfo INSTANCE = new AnnoIncludeTypeInfo();

    public static AnnoIncludeTypeInfo get() {
        return INSTANCE;
    }
}
