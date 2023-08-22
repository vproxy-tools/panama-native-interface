package io.vproxy.pni.exec.type;

public class AnnoUpcallTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoUpcallTypeInfo() {
        super("io.vproxy.pni.annotation.Upcall", "io/vproxy/pni/annotation/Upcall", "Lio/vproxy/pni/annotation/Upcall;");
    }

    private static final AnnoUpcallTypeInfo INSTANCE = new AnnoUpcallTypeInfo();

    public static AnnoUpcallTypeInfo get() {
        return INSTANCE;
    }
}
