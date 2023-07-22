package io.vproxy.pni.exec.type;

public class AnnoTrivialTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoTrivialTypeInfo() {
        super("io.vproxy.pni.annotation.Trivial", "io/vproxy/pni/annotation/Trivial", "Lio/vproxy/pni/annotation/Trivial;");
    }

    private static final AnnoTrivialTypeInfo INSTANCE = new AnnoTrivialTypeInfo();

    public static AnnoTrivialTypeInfo get() {
        return INSTANCE;
    }
}
