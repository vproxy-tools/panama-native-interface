package io.vproxy.pni.test;

import io.vproxy.pni.exec.ast.AstAnno;
import io.vproxy.pni.exec.ast.AstClass;
import io.vproxy.pni.exec.ast.AstField;
import io.vproxy.pni.exec.type.*;

import java.util.function.Consumer;

import static org.junit.Assert.fail;

public class Utils {
    private Utils() {
    }

    static void checkUnsupported(Runnable r) {
        try {
            r.run();
            fail();
        } catch (UnsupportedOperationException ignore) {
        }
    }

    static String sbHelper(Consumer<StringBuilder> func) {
        var sb = new StringBuilder();
        func.accept(sb);
        return sb.toString();
    }

    public static ClassTypeInfo generalClsTypeInfo() {
        var astClass = new AstClass();
        astClass.name = "a/b/PNICls";
        astClass.annos.add(new AstAnno() {{
            typeRef = AnnoStructTypeInfo.get();
        }});
        astClass.fields.add(new AstField() {{
            typeRef = CharTypeInfo.get();
        }});
        astClass.fields.add(new AstField() {{
            typeRef = ShortTypeInfo.get();
        }});
        return new ClassTypeInfo(astClass);
    }

    public static ClassTypeInfo emptyClsTypeInfo() {
        var astClass = new AstClass();
        astClass.name = "a/b/PNIEmptyCls";
        astClass.annos.add(new AstAnno() {{
            typeRef = AnnoStructTypeInfo.get();
        }});
        return new ClassTypeInfo(astClass);
    }
}
