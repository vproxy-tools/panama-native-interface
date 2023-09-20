package io.vproxy.pni.exec;

import io.vproxy.pni.exec.generator.*;
import io.vproxy.pni.exec.internal.*;

import java.io.File;

public class Generator {
    private final CompilerOptions opts;

    public Generator(CompilerOptions opts) {
        this.opts = opts;
        opts.validate();
        opts.initDefaultValues();
    }

    public void generate() {
        var classReaders = new JavaReader(opts.getClasspath()).read(opts);
        var classes = new ASTReader(classReaders).read(opts);
        for (var cls : classes) {
            var generate = opts.getFilters().isEmpty();
            for (var f : opts.getFilters()) {
                if (f.matcher(cls.fullName()).find()) {
                    if (opts.isVerbose()) {
                        System.out.println(cls.fullName() + " matches filtering rule " + f);
                    }
                    generate = true;
                    break;
                }
            }
            if (!generate) {
                continue;
            }

            new JavaFileGenerator(cls).flush(new File(opts.getJavaOutputBaseDirectory()), opts);
            new CFileGenerator(cls).flush(new File(opts.getCOutputDirectory()), opts);
            new CImplFileGenerator(cls).flush(new File(opts.getCOutputDirectory()), opts);
            new CExtraFileGenerator(cls).flush(new File(opts.getCOutputDirectory()), opts);
            new CUpcallImplFileGenerator(cls).flush(new File(opts.getCOutputDirectory()), opts);
        }
    }
}
