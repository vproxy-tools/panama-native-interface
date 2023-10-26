package io.vproxy.pni.exec.type;

public class AnnoGenerateMemberTypeInfo extends BuiltInAnnoTypeInfo {
    private AnnoGenerateMemberTypeInfo() {
        super("io.vproxy.pni.annotation.GenerateMember", "io/vproxy/pni/annotation/GenerateMember", "Lio/vproxy/pni/annotation/GenerateMember;");
    }

    private static final AnnoGenerateMemberTypeInfo INSTANCE = new AnnoGenerateMemberTypeInfo();

    public static AnnoGenerateMemberTypeInfo get() {
        return INSTANCE;
    }
}
