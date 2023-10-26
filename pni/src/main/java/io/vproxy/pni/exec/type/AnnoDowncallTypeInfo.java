package io.vproxy.pni.exec.type;

public class AnnoDowncallTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoDowncallTypeInfo() {
        super("io.vproxy.pni.annotation.Downcall", "io/vproxy/pni/annotation/Downcall", "Lio/vproxy/pni/annotation/Downcall;");
    }

    private static final AnnoDowncallTypeInfo INSTANCE = new AnnoDowncallTypeInfo();

    public static AnnoDowncallTypeInfo get() {
        return INSTANCE;
    }
}
