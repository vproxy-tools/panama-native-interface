package io.vproxy.pni.exec.type;

public class VoidRefTypeInfo extends NoGenReferenceTypeInfo {
    private VoidRefTypeInfo() {
        super("java.lang.Void", "java/lang/Void", "Ljava/lang/Void;");
    }

    private static final VoidRefTypeInfo INSTANCE = new VoidRefTypeInfo();

    public static VoidRefTypeInfo get() {
        return INSTANCE;
    }
}
