package io.vproxy.pni.test;

import io.vproxy.pni.exec.CompilationFlag;
import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.ast.AstClass;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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

    @Test
    public void alwaysAligned() throws Exception {
        // language="java"
        var content = """
            package test;
            import io.vproxy.pni.annotation.*;
            @Struct
            class A {
                int x;
                long y;
            }
            """;
        var res = Utils.load(List.of(new JavaFile()
            .setName("test/A.java")
            .setContent(content)), new CompilerOptions()
            .setCompilationFlag(CompilationFlag.ALWAYS_ALIGNED));
        var gen = res.gen();
        var javaPath = Path.of(gen.toString(), "test", "A.java");
        var javaContent = Files.readAllLines(javaPath);

        var opt = javaContent.stream().filter(line -> line.contains(".withName(\"x\")")).findFirst();
        var l = opt.get().trim();
        assertEquals("ValueLayout.JAVA_INT.withName(\"x\"),", l);

        opt = javaContent.stream().filter(line -> line.contains(".withName(\"y\")")).findFirst();
        l = opt.get().trim();
        assertEquals("ValueLayout.JAVA_LONG.withName(\"y\")", l);
    }

    @Test
    public void defaultNotAlwaysAligned() throws Exception {
        // language="java"
        var content = """
            package test;
            import io.vproxy.pni.annotation.*;
            @Struct
            class A {
                int x;
                long y;
            }
            """;
        var res = Utils.load(List.of(new JavaFile()
            .setName("test/A.java")
            .setContent(content)), new CompilerOptions());
        var gen = res.gen();
        var javaPath = Path.of(gen.toString(), "test", "A.java");
        var javaContent = Files.readAllLines(javaPath);

        var opt = javaContent.stream().filter(line -> line.contains(".withName(\"x\")")).findFirst();
        var l = opt.get().trim();
        assertEquals("ValueLayout.JAVA_INT_UNALIGNED.withName(\"x\"),", l);

        opt = javaContent.stream().filter(line -> line.contains(".withName(\"y\")")).findFirst();
        l = opt.get().trim();
        assertEquals("ValueLayout.JAVA_LONG_UNALIGNED.withName(\"y\")", l);
    }
}
