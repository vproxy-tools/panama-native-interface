package io.vproxy.pni.exec.type;

public class AnnoNameTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoNameTypeInfo() {
        super("io.vproxy.pni.annotation.Name", "io/vproxy/pni/annotation/Name", "Lio/vproxy/pni/annotation/Name;");
    }

    private static final AnnoNameTypeInfo INSTANCE = new AnnoNameTypeInfo();

    public static AnnoNameTypeInfo get() {
        return INSTANCE;
    }
}
