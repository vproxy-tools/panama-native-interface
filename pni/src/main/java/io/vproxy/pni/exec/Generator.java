package io.vproxy.pni.exec;

import io.vproxy.pni.exec.ast.AstClass;
import io.vproxy.pni.exec.extension.Extension;
import io.vproxy.pni.exec.extension.ExtensionValidationException;
import io.vproxy.pni.exec.generator.*;
import io.vproxy.pni.exec.internal.*;

import java.util.*;

public class Generator {
    private final Set<Extension> extensions = new HashSet<>();
    private final CompilerOptions opts;

    public Generator(CompilerOptions opts) {
        this.opts = opts;
    }

    public Generator addExtension(Extension gen) {
        extensions.add(gen);
        return this;
    }

    public void generate() {
        opts.validate();
        for (var ext : extensions) {
            try {
                ext.validate(opts);
            } catch (ExtensionValidationException e) {
                throw new RuntimeException(e);
            }
        }
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

            new JavaFileGenerator(cls, opts).flush(opts.getJavaOutputBaseDirectory());
            new CFileGenerator(cls, opts).flush(opts.getCOutputDirectory());
            new CImplFileGenerator(cls, opts).flush(opts.getCOutputDirectory());
            new CExtraFileGenerator(cls, opts).flush(opts.getCOutputDirectory());
            new CUpcallImplFileGenerator(cls, opts).flush(opts.getCOutputDirectory());
            new StaticFileGenerator(opts).flush();
        }
        new GraalNativeImageFeatureFileGenerator(classes, opts).flush(opts.getJavaOutputBaseDirectory());

        for (var ext : extensions) {
            ext.generate(Collections.unmodifiableList(classes), opts);
        }
    }

    // only for testing, will use reflect to retrieve
    @SuppressWarnings({"unused", "FieldCanBeLocal"}) private List<AstClass> lastClasses;
}
