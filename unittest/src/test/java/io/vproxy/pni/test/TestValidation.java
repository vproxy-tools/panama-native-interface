package io.vproxy.pni.test;

import io.vproxy.pni.exec.ast.AstAnno;
import io.vproxy.pni.exec.ast.AstClass;
import io.vproxy.pni.exec.ast.AstField;
import io.vproxy.pni.exec.type.AnnoPointerOnlyTypeInfo;
import io.vproxy.pni.exec.type.AnnoStructTypeInfo;
import io.vproxy.pni.exec.type.CharTypeInfo;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestValidation {
    @Test
    public void pointerOnly() {
        var astClass = new AstClass();
        astClass.name = "a/b/PNICls";
        astClass.annos.add(new AstAnno() {{
            typeRef = AnnoStructTypeInfo.get();
        }});
        astClass.annos.add(new AstAnno() {{
            typeRef = AnnoPointerOnlyTypeInfo.get();
        }});
        astClass.fields.add(new AstField() {{
            typeRef = CharTypeInfo.get();
        }});
        var errors = new ArrayList<String>();
        astClass.validate(errors);

        assertEquals(1, errors.size());
        assertEquals("class(a/b/PNICls): cannot define fields in this type because it is marked with @PointerOnly", errors.get(0));
    }
}
