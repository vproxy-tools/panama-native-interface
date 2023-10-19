package io.vproxy.pni.exec.type;

public class AnnoNoAllocTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoNoAllocTypeInfo() {
        super("io.vproxy.pni.annotation.NoAlloc", "io/vproxy/pni/annotation/NoAlloc", "Lio/vproxy/pni/annotation/NoAlloc;");
    }

    private static final AnnoNoAllocTypeInfo INSTANCE = new AnnoNoAllocTypeInfo();

    public static AnnoNoAllocTypeInfo get() {
        return INSTANCE;
    }
}
