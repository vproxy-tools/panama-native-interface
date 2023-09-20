package io.vproxy.pni.exec.internal;

import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.ast.AstClass;
import io.vproxy.pni.exec.type.TypePool;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;
import java.util.List;

public class ASTReader {
    private final List<ClassReader> classReaders;
    private final TypePool pool = new TypePool();

    private final List<AstClass> classes = new ArrayList<>();

    public ASTReader(List<ClassReader> classReaders) {
        this.classReaders = classReaders;
    }

    public List<AstClass> read(CompilerOptions opts) {
        // load all classes which requires handling
        for (var r : classReaders) {
            var classNode = new ClassNode();
            r.accept(classNode, ClassReader.SKIP_FRAMES | ClassReader.SKIP_CODE);
            var astClass = new AstClass(classNode);
            if (!requiresHandling(astClass)) {
                if (opts.isVerbose()) {
                    System.out.println("skipping " + astClass.name);
                }
                continue;
            }
            classes.add(astClass);
            if (!astClass.isInterface) {
                pool.record(astClass);
            }
        }
        // make all classes reference to each other
        for (var cls : classes) {
            cls.ref(pool);
        }

        // verbose
        if (opts.isVerbose()) {
            for (var cls : classes) {
                System.out.println("-----BEGIN CLASS-----");
                System.out.println(cls);
                System.out.println("-----END CLASS-----");
            }
        }

        // validate
        var errors = new ArrayList<String>();
        for (var cls : classes) {
            cls.validate(errors);
        }
        if (!errors.isEmpty()) {
            throw new RuntimeException("Error!\n" + String.join("\n", errors));
        }
        for (var cls : classes) {
            cls.validateDependency(errors);
        }
        if (!errors.isEmpty()) {
            throw new RuntimeException("Error!\n" + String.join("\n", errors));
        }
        for (var cls : classes) {
            cls.getNativeMemorySize(); // ensure padding calculated
        }
        for (var cls : classes) {
            cls.validateAlignment(errors);
        }
        if (!errors.isEmpty()) {
            throw new RuntimeException("Error!\n" + String.join("\n", errors));
        }
        return classes;
    }

    private boolean requiresHandling(AstClass astClass) {
        for (var a : astClass.annos) {
            if (a.type.desc.startsWith("Lio/vproxy/pni/annotation/")) {
                var n = a.type.desc.substring("Lio/vproxy/pni/annotation/".length(), a.type.desc.length() - 1);
                if (n.equals("Function") || n.equals("Struct") || n.equals("Union") || n.equals("Upcall")) {
                    return true;
                }
            }
        }
        return false;
    }
}
