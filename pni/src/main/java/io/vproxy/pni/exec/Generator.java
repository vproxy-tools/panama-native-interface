package io.vproxy.pni.exec;

import io.vproxy.pni.exec.ast.AstClass;
import io.vproxy.pni.exec.generator.*;
import io.vproxy.pni.exec.internal.*;

import java.io.File;
import java.util.List;

public class Generator {
    private final CompilerOptions opts;

    public Generator(CompilerOptions opts) {
        this.opts = opts;
    }

    public void generate() {
        opts.validate();
        opts.initDefaultValues();

        var classReaders = new JavaReader(opts.getClasspath(), opts).read();
        var classes = new ASTReader(classReaders, opts).read();
        lastClasses = classes;
        for (var cls : classes) {
            var generate = opts.getFilters().isEmpty();
            for (var f : opts.getFilters()) {
                if (f.matcher(cls.fullName()).find()) {
                    PNILogger.debug(opts, cls.fullName() + " matches filtering rule " + f);
                    generate = true;
                    break;
                }
            }
            if (!generate) {
                PNILogger.debug(opts, cls.fullName() + " does not match any filtering rule");
                continue;
            }

            new JavaFileGenerator(cls, opts).flush(new File(opts.getJavaOutputBaseDirectory()));
            new CFileGenerator(cls, opts).flush(new File(opts.getCOutputDirectory()));
            new CImplFileGenerator(cls, opts).flush(new File(opts.getCOutputDirectory()));
            new CExtraFileGenerator(cls, opts).flush(new File(opts.getCOutputDirectory()));
            new CUpcallImplFileGenerator(cls, opts).flush(new File(opts.getCOutputDirectory()));
        }
    }

    // only for testing, will use reflect to retrieve
    @SuppressWarnings({"unused", "FieldCanBeLocal"}) private List<AstClass> lastClasses;
}
