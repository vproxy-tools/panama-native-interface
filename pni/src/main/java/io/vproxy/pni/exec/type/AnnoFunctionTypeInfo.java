package io.vproxy.pni.exec.type;

public class AnnoFunctionTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoFunctionTypeInfo() {
        super("io.vproxy.pni.annotation.Function", "io/vproxy/pni/annotation/Function", "Lio/vproxy/pni/annotation/Function;");
    }

    private static final AnnoFunctionTypeInfo INSTANCE = new AnnoFunctionTypeInfo();

    public static AnnoFunctionTypeInfo get() {
        return INSTANCE;
    }
}
