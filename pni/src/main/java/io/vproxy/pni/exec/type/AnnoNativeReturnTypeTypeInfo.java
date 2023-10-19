package io.vproxy.pni.exec.type;

public class AnnoNativeReturnTypeTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoNativeReturnTypeTypeInfo() {
        super("io.vproxy.pni.annotation.NativeReturnType", "io/vproxy/pni/annotation/NativeReturnType", "Lio/vproxy/pni/annotation/NativeReturnType;");
    }

    private static final AnnoNativeReturnTypeTypeInfo INSTANCE = new AnnoNativeReturnTypeTypeInfo();

    public static AnnoNativeReturnTypeTypeInfo get() {
        return INSTANCE;
    }
}
