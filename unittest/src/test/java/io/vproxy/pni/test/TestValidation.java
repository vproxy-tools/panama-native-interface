package io.vproxy.pni.test;

import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.ast.AstAnno;
import io.vproxy.pni.exec.ast.AstClass;
import io.vproxy.pni.exec.ast.AstField;
import io.vproxy.pni.exec.type.*;
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
        astClass.validate(new CompilerOptions(), errors);

        assertEquals(1, errors.size());
        assertEquals("class(a/b/PNICls): cannot define fields in this type because it is marked with @PointerOnly", errors.get(0));
    }

    @Test
    public void recursive() {
        var cls = new AstClass();
        cls.name = "a/b/PNICls";
        var cls2 = new AstClass();
        cls2.name = "a/b/PNICls2";
        cls.fields.add(new AstField() {{
            name = "x";
            typeRef = new ClassTypeInfo(cls2);
        }});
        cls2.fields.add(new AstField() {{
            name = "y";
            typeRef = new ClassTypeInfo(cls);
        }});

        var errors = new ArrayList<String>();
        cls.validateDependency(errors);
        assertEquals(1, errors.size());
        assertEquals("a/b/PNICls -> x -> y: recursive type dependency", errors.get(0));
    }

    @Test
    public void recursiveArray() {
        var cls = new AstClass();
        cls.name = "a/b/PNICls";
        var cls2 = new AstClass();
        cls2.name = "a/b/PNICls2";
        cls.fields.add(new AstField() {{
            name = "x";
            typeRef = new ClassTypeInfo(cls2);
        }});
        cls2.fields.add(new AstField() {{
            name = "y";
            typeRef = new ArrayTypeInfo(new ClassTypeInfo(cls));
        }});

        var errors = new ArrayList<String>();
        cls.validateDependency(errors);
        assertEquals(1, errors.size());
        assertEquals("a/b/PNICls -> x -> y: recursive type dependency", errors.get(0));
    }
}
