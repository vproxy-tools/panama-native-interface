package io.vproxy.pni.exec.type;

public class AnnoCriticalTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoCriticalTypeInfo() {
        super("io.vproxy.pni.annotation.Critical", "io/vproxy/pni/annotation/Critical", "Lio/vproxy/pni/annotation/Critical;");
    }

    private static final AnnoCriticalTypeInfo INSTANCE = new AnnoCriticalTypeInfo();

    public static AnnoCriticalTypeInfo get() {
        return INSTANCE;
    }
}
