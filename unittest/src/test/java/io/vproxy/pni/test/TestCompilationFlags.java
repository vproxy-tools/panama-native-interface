package io.vproxy.pni.test;

import io.vproxy.pni.exec.CompilationFlag;
import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.ast.AstClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCompilationFlags {
    @Test
    public void templateTypeName() {
        var cls = new AstClass(new CompilerOptions()
            .setCompilationFlag(CompilationFlag.TYPE_NAME_PREFIX, "XXX"));
        cls.name = "a/b/XXXHello";
        assertEquals("Hello", cls.simpleName());
        assertEquals("a.b.Hello", cls.fullName());
        assertEquals("a_b_Hello", cls.underlinedName());

        cls.name = "a/b/Hello";
        assertEquals("XXXHello", cls.simpleName());
        assertEquals("a.b.XXXHello", cls.fullName());
        assertEquals("a_b_XXXHello", cls.underlinedName());
    }

    @Test
    public void noTemplateTypeName() {
        var cls = new AstClass(new CompilerOptions()
            .setCompilationFlag(CompilationFlag.TYPE_NAME_PREFIX, ""));
        cls.name = "a/b/Hello";
        assertEquals("Hello", cls.simpleName());
        assertEquals("a.b.Hello", cls.fullName());
        assertEquals("a_b_Hello", cls.underlinedName());
    }
}
