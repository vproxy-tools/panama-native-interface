package io.vproxy.pni.test;

import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.WarnType;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestWarningFlags {
    @Test
    public void defaultOpts() {
        var opts = new Utils.CompilerOptions();
        //noinspection PointlessBitwiseExpression
        long warn = 0
                    | WarnType.INVALID_CLASSPATH_FILE.flag
                    | WarnType.ALIGNMENT_NOT_POWER_OF_2.flag
                    | 0;
        //noinspection PointlessBitwiseExpression
        long warnAsErr = 0
                         | WarnType.INVALID_CLASSPATH_FILE.flag
                         | 0;
        assertEquals(warn, opts.getWarningFlags());
        assertEquals(warnAsErr, opts.getWarningAsErrorFlags());
    }

    @Test
    public void invalidClassPathFile() throws Exception {
        var content = // language="java"
            """
                package test;
                import io.vproxy.pni.annotation.*;
                @Struct
                class PNIA {
                    int a;
                }
                """;
        var cp = "wfjioaweofiaweoifjaoiwejfoijoijwov";
        cp = File.separator + cp;

        var asts = Utils.load(List.of(
                new JavaFile()
                    .setName("test/A.java")
                    .setContent(content)
            ), new Utils.CompilerOptions()
                .setClasspath(List.of(cp))
                .unsetWarningAsErrorBits(WarnType.INVALID_CLASSPATH_FILE.flag)
        ).classes();
        assertEquals(1, asts.size());
        assertEquals("test/PNIA", asts.get(0).name);

        try {
            Utils.load(List.of(
                new JavaFile()
                    .setName("test/A.java")
                    .setContent(content)
            ), new Utils.CompilerOptions().setClasspath(List.of(cp)));
            fail();
        } catch (Exception e) {
            assertEquals("classpath file not found: " + cp, e.getMessage());
        }
    }

    @Test
    public void alignmentNotPowerOf2() throws Exception {
        var content = // language="java"
            """
                package test;
                import io.vproxy.pni.annotation.*;
                @Struct
                @Align(28)
                class PNIA {
                    @Align(12)
                    int a;
                }
                """;

        var asts = Utils.load(List.of(
                new JavaFile()
                    .setName("test/A.java")
                    .setContent(content)
            ), new Utils.CompilerOptions()
        ).classes();
        assertEquals(1, asts.size());
        assertEquals("test/PNIA", asts.get(0).name);

        try {
            Utils.load(List.of(
                    new JavaFile()
                        .setName("test/A.java")
                        .setContent(content)
                ), new Utils.CompilerOptions()
                    .setWarningAsErrorBits(WarnType.ALIGNMENT_NOT_POWER_OF_2.flag)
            );
            fail();
        } catch (Exception e) {
            assertEquals("""
                Error!
                class(test/PNIA)#field(a): alignment is not power of 2 [-Werror=alignment-not-power-of-2]
                class(test/PNIA): alignment is not power of 2 [-Werror=alignment-not-power-of-2]
                """.trim(), e.getMessage());
        }
    }
}
