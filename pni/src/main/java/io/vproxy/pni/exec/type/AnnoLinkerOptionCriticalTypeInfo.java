package io.vproxy.pni.exec.type;

public class AnnoLinkerOptionCriticalTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoLinkerOptionCriticalTypeInfo() {
        super("io.vproxy.pni.annotation.LinkerOption$Critical", "io/vproxy/pni/annotation/LinkerOption$Critical", "Lio/vproxy/pni/annotation/LinkerOption$Critical;");
    }

    private static final AnnoLinkerOptionCriticalTypeInfo INSTANCE = new AnnoLinkerOptionCriticalTypeInfo();

    public static AnnoLinkerOptionCriticalTypeInfo get() {
        return INSTANCE;
    }
}
