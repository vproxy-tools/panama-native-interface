package io.vproxy.pni.exec.type;

public class AnnoNativeTypeTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoNativeTypeTypeInfo() {
        super("io.vproxy.pni.annotation.NativeType", "io/vproxy/pni/annotation/NativeType", "Lio/vproxy/pni/annotation/NativeType;");
    }

    private static final AnnoNativeTypeTypeInfo INSTANCE = new AnnoNativeTypeTypeInfo();

    public static AnnoNativeTypeTypeInfo get() {
        return INSTANCE;
    }
}
