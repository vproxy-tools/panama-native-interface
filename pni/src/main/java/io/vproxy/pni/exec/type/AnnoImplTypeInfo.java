package io.vproxy.pni.exec.type;

public class AnnoImplTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoImplTypeInfo() {
        super("io.vproxy.pni.annotation.Impl", "io/vproxy/pni/annotation/Impl", "Lio/vproxy/pni/annotation/Impl;");
    }

    private static final AnnoImplTypeInfo INSTANCE = new AnnoImplTypeInfo();

    public static AnnoImplTypeInfo get() {
        return INSTANCE;
    }
}
