package io.vproxy.pni.exec.type;

public class AnnoPointerOnlyTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoPointerOnlyTypeInfo() {
        super("io.vproxy.pni.annotation.PointerOnly", "io/vproxy/pni/annotation/PointerOnly", "Lio/vproxy/pni/annotation/PointerOnly;");
    }

    private static final AnnoPointerOnlyTypeInfo INSTANCE = new AnnoPointerOnlyTypeInfo();

    public static AnnoPointerOnlyTypeInfo get() {
        return INSTANCE;
    }
}
