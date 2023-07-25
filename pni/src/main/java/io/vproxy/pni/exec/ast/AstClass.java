package io.vproxy.pni.exec.ast;

import io.vproxy.pni.annotation.*;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.type.ClassTypeInfo;
import io.vproxy.pni.exec.type.TypePool;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AstClass {
    public boolean isInterface;
    public String name;
    public final List<AstAnno> annos = new ArrayList<>();
    public final List<AstField> fields = new ArrayList<>();
    public final List<AstMethod> methods = new ArrayList<>();

    public AstClass(ClassNode classNode) {
        isInterface = (classNode.access & Opcodes.ACC_INTERFACE) == Opcodes.ACC_INTERFACE;
        this.name = classNode.name;
        Utils.readAnnotations(annos, classNode.visibleAnnotations);
        for (var f : classNode.fields) {
            this.fields.add(new AstField(f));
        }
        for (var m : classNode.methods) {
            if (m.name.equals("<init>") || m.name.equals("<cinit>")) {
                continue;
            }
            this.methods.add(new AstMethod(m));
        }
    }

    public void ref(TypePool pool) {
        for (var a : annos) {
            a.ref(pool);
        }
        for (var f : fields) {
            f.ref(pool);
        }
        for (var m : methods) {
            m.ref(pool);
        }
    }

    public void validate(List<String> errors) {
        var path = "class(" + name + ")";
        for (var a : annos) {
            a.validate(path, errors);
        }
        for (var f : fields) {
            f.validate(path, errors);
        }
        for (var m : methods) {
            m.validate(path, errors);
        }

        var names = new HashSet<String>();
        for (var f : fields) {
            if (!names.add(f.nativeName())) {
                var err = path + ": two or more fields have the same native name " + f.nativeName();
                if (!errors.contains(err)) {
                    errors.add(err);
                }
            }
        }
        names.clear();
        if (!methods.isEmpty()) {
            if (isUnionEmbed()) {
                errors.add(path + ": embedded union should not have methods");
            }
        }
        for (var m : methods) {
            if (!names.add(m.nativeName(underlinedName()))) {
                var err = path + ": two or more methods have the same native name " + m.nativeName(underlinedName());
                if (!errors.contains(err)) {
                    errors.add(err);
                }
            }
        }
        var hasStruct = isStruct();
        var hasUnion = isUnion();
        var hasFunction = annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(Function.class.getName()));
        if (hasStruct && hasUnion) {
            errors.add(path + ": is annotated with both @Struct and @Union");
        }
        if (hasStruct && hasFunction) {
            errors.add(path + ": is annotated with both @Struct and @Function");
        }
        if (hasUnion && hasFunction) {
            errors.add(path + ": is annotated with both @Union and @Function");
        }
        if (hasFunction && !isInterface) {
            errors.add(path + ": is annotated with @Function but is not an interface");
        }
        if (hasStruct && isInterface) {
            errors.add(path + ": is annotated with @Struct but is an interface");
        }
        if (hasUnion && isInterface) {
            errors.add(path + ": is annotated with @Union but is an interface");
        }
        var name = Utils.getName(annos);
        if (name != null) {
            if (!Utils.isValidName(name, false)) {
                errors.add(path + ": invalid @Name(" + name + ")");
            }
        }
    }

    public void validateDependency(List<String> errors) {
        for (var f : fields) {
            if (f.pointerInfo().isPointer()) {
                continue;
            }
            if (f.typeRef instanceof ClassTypeInfo classTypeInfo) {
                var cls = classTypeInfo.getClazz();
                validateDependency(cls, errors, name + " -> " + f.name, new HashSet<>());
            }
        }
    }

    private void validateDependency(AstClass cls, List<String> errors, String path, HashSet<AstClass> classes) {
        if (cls == this) {
            errors.add("recursive type dependency: " + path);
            return;
        }
        if (!classes.add(cls)) {
            return;
        }
        for (var f : cls.fields) {
            if (f.pointerInfo().isPointer()) {
                continue;
            }
            if (f.typeRef instanceof ClassTypeInfo classTypeInfo) {
                var c = classTypeInfo.getClazz();
                validateDependency(c, errors, path + " -> " + f.name, classes);
            }
        }
    }

    public boolean isStruct() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(Struct.class.getName()));
    }

    public boolean isUnion() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(Union.class.getName()));
    }

    public boolean isUnionEmbed() {
        if (!isUnion()) {
            return false;
        }
        //noinspection OptionalGetWithoutIsPresent
        var anno = annos.stream().filter(a -> a.typeRef != null && a.typeRef.name().equals(Union.class.getName())).findFirst().get();
        var opt = anno.values.stream().filter(v -> v.name.equals("embedded")).findFirst();
        if (opt.isEmpty()) {
            return false;
        }
        var v = opt.get();
        if (v.value instanceof Boolean b) {
            return b;
        }
        return false;
    }

    public boolean isSkip() {
        var annoOpt = annos.stream().filter(a -> a.typeRef != null &&
                                                 (a.typeRef.name().equals(Struct.class.getName()) || a.typeRef.name().equals(Union.class.getName())))
            .findFirst();
        if (annoOpt.isEmpty()) {
            return false;
        }
        var anno = annoOpt.get();
        var opt = anno.values.stream().filter(v -> v.name.equals("skip")).findFirst();
        if (opt.isEmpty()) {
            return false;
        }
        var v = opt.get();
        if (v.value instanceof Boolean b) {
            return b;
        }
        return false;
    }

    public long getAlign() {
        var opt = annos.stream().filter(a -> a.typeRef != null && a.typeRef.name().equals(Align.class.getName())).findFirst();
        if (opt.isEmpty()) {
            return 8;
        }
        var anno = opt.get();
        var vOpt = anno.values.stream().filter(v -> v.name.equals("value")).findFirst();
        if (vOpt.isEmpty()) {
            return 8;
        }
        var v = vOpt.get().value;
        if (v instanceof Long l) {
            return l;
        }
        return 8;
    }

    public List<String> extraInclude() {
        var opt = annos.stream().filter(a -> a.typeRef != null && a.typeRef.name().equals(Include.class.getName())).findFirst();
        if (opt.isEmpty()) {
            return null;
        }
        var anno = opt.get();
        var vOpt = anno.values.stream().filter(v -> v.name.equals("value")).findFirst();
        if (vOpt.isEmpty()) {
            return null;
        }
        var v = vOpt.get().value;
        //noinspection rawtypes
        if (v instanceof List arr) {
            for (var e : arr) {
                if (!(e instanceof String)) {
                    return null;
                }
            }
            //noinspection unchecked
            return (List<String>) arr;
        }
        return null;
    }

    private long __calculatedNativeMemorySize = -1;

    public long getNativeMemorySize() {
        if (__calculatedNativeMemorySize >= 0) {
            return __calculatedNativeMemorySize;
        }

        if (isUnion()) {
            long max = 0;
            for (var f : fields) {
                var s = f.getNativeMemorySize();
                if (max < s) {
                    max = s;
                }
            }
            __calculatedNativeMemorySize = max;
            return max;
        }

        var align = getAlign();
        if (align <= 1) {
            long total = 0;
            for (var f : fields) {
                total += f.getNativeMemorySize();
            }
            __calculatedNativeMemorySize = total;
            return total;
        }

        long block = 0;
        long total = 0;
        AstField lastField = null;
        for (var f : fields) {
            var size = f.getNativeMemorySize();
            if (size == 0) {
                continue;
            }
            while (true) {
                if (block == 0) {
                    if (size >= align) {
                        total += size - (size % align);
                        block = size % align;
                    } else {
                        block = size;
                    }
                } else {
                    if (block + size > align) {
                        lastField.padding = align - block;
                        total += align;
                        block = 0;
                        continue;
                    }
                    block += size;
                }
                break;
            }
            lastField = f;
        }
        if (block > 0) {
            lastField.padding = align - block;
            total += align;
        }
        __calculatedNativeMemorySize = total;
        return total;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (var anno : annos) {
            anno.toString(sb, 0);
            sb.append("\n");
        }
        if (isInterface) {
            sb.append("interface");
        } else {
            sb.append("class");
        }
        sb.append(" ").append(name).append(" {\n");
        for (var f : fields) {
            f.toString(sb, 4);
            sb.append("\n");
        }
        if (!fields.isEmpty() && !methods.isEmpty()) {
            sb.append("\n");
        }
        for (var m : methods) {
            m.toString(sb, 4);
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

    public String nativeName() {
        var name = Utils.getName(annos);
        if (name == null) {
            return simpleName();
        }
        return name;
    }

    public String nativeTypeName() {
        if (isInterface) {
            return null;
        }
        if (typedef()) {
            return nativeName();
        } else {
            if (isUnion()) {
                return "union " + nativeName();
            } else {
                return "struct " + nativeName();
            }
        }
    }

    public boolean typedef() {
        var opt = annos.stream()
            .filter(a -> a.typeRef != null &&
                         (a.typeRef.name().equals(Struct.class.getName()) || a.typeRef.name().equals(Union.class.getName()))
            ).findFirst();
        if (opt.isEmpty()) {
            return true;
        }
        var a = opt.get();
        var vOpt = a.values.stream().filter(v -> v.name.equals("typedef")).findFirst();
        if (vOpt.isEmpty()) {
            return true;
        }
        var v = vOpt.get();
        if (!(v.value instanceof Boolean vv)) {
            return true;
        }
        return vv;
    }

    public String generateJava() {
        var sb = new StringBuilder();
        if (!packageName().isEmpty()) {
            sb.append("package ").append(packageName()).append(";\n\n");
        }

        //noinspection TextBlockMigration
        sb.append("import io.vproxy.pni.*;\n" +
                  "import io.vproxy.pni.array.*;\n" +
                  "import java.lang.foreign.*;\n" +
                  "import java.lang.invoke.*;\n" +
                  "import java.nio.ByteBuffer;\n");
        sb.append("\n");
        generateJava(sb);
        return sb.toString();
    }

    private void generateJava(StringBuilder sb) {
        sb.append("public class ").append(simpleName()).append(" {\n");
        if (isInterface) {
            sb.append("    private ").append(simpleName()).append("() {\n");
            sb.append("    }\n");
            sb.append("\n");
            sb.append("    private static final ").append(simpleName()).append(" INSTANCE = new ").append(simpleName()).append("();\n");
            sb.append("\n");
            sb.append("    public static ").append(simpleName()).append(" get() {\n");
            sb.append("        return INSTANCE;\n");
            sb.append("    }\n");
        } else {
            sb.append("    public static final MemoryLayout LAYOUT = MemoryLayout.");
            if (isUnion()) {
                sb.append("unionLayout(\n");
            } else {
                sb.append("structLayout(\n");
            }
            var isFirst = true;
            for (var f : fields) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(",\n");
                }
                f.generateJavaLayout(sb, 8);
            }
            sb.append("\n    );\n");
            sb.append("    public final MemorySegment MEMORY;\n");
        }

        if (!isInterface) {
            for (var f : fields) {
                sb.append("\n");
                f.generateJavaGetterSetter(sb, 4);
            }
            sb.append("\n");
            Utils.appendIndent(sb, 4)
                .append("public ").append(simpleName()).append("(MemorySegment MEMORY) {\n");
            Utils.appendIndent(sb, 8)
                .append("MEMORY = MEMORY.reinterpret(LAYOUT.byteSize());\n");
            Utils.appendIndent(sb, 8)
                .append("this.MEMORY = MEMORY;\n");
            Utils.appendIndent(sb, 8)
                .append("long OFFSET = 0;\n");
            for (var f : fields) {
                f.generateJavaConstructor(sb, 8);
                if (isUnion()) {
                    Utils.appendIndent(sb, 8).append("OFFSET = 0;\n");
                }
            }
            Utils.appendIndent(sb, 4).append("}\n");
            sb.append("\n");
            Utils.appendIndent(sb, 4)
                .append("public ").append(simpleName()).append("(Allocator ALLOCATOR) {\n");
            Utils.appendIndent(sb, 8)
                .append("this(ALLOCATOR.allocate(LAYOUT.byteSize()));\n");
            Utils.appendIndent(sb, 4).append("}\n");
        }

        for (var m : methods) {
            sb.append("\n");
            m.generateJava(sb, 4, underlinedName(), !isInterface);
        }

        if (!isInterface) {
            sb.append("\n");
            sb.append("    public static class Array extends RefArray<").append(simpleName()).append("> {\n");
            sb.append("        public Array(MemorySegment buf) {\n");
            sb.append("            super(buf, ").append(simpleName()).append(".LAYOUT);\n");
            sb.append("        }\n");
            sb.append("\n");
            generateConstructAndGetSegment(sb);
            sb.append("    }\n");
            sb.append("\n");
            sb.append("    public static class Func extends PNIFunc<").append(simpleName()).append("> {\n");
            sb.append("        private Func(io.vproxy.pni.CallSite<").append(simpleName()).append("> func) {\n");
            sb.append("            super(func);\n");
            sb.append("        }\n");
            sb.append("\n");
            sb.append("        public static Func of(io.vproxy.pni.CallSite<").append(simpleName()).append("> func) {\n");
            sb.append("            return new Func(func);\n");
            sb.append("        }\n");
            sb.append("\n");
            generateConstructAndGetSegment(sb);
            sb.append("    }\n");
        }

        sb.append("}\n");
    }

    private void generateConstructAndGetSegment(StringBuilder sb) {
        sb.append("        @Override\n");
        sb.append("        protected ").append(simpleName()).append(" construct(MemorySegment seg) {\n");
        sb.append("            return new ").append(simpleName()).append("(seg);\n");
        sb.append("        }\n");
        sb.append("\n");
        sb.append("        @Override\n");
        sb.append("        protected MemorySegment getSegment(").append(simpleName()).append(" value) {\n");
        sb.append("            return value.MEMORY;\n");
        sb.append("        }\n");
    }

    public String fullName() {
        String n = name.replace('/', '.');
        String p;
        if (n.contains(".")) {
            p = n.substring(0, n.lastIndexOf(".") + 1);
        } else {
            p = "";
        }
        return p + simpleName();
    }

    public String simpleName() {
        String n;
        if (name.contains("/")) {
            n = name.substring(name.lastIndexOf("/") + 1);
        } else {
            n = name;
        }
        if (n.startsWith("PNI")) {
            n = n.substring("PNI".length());
        } else {
            n = "PNI" + n;
        }
        return n;
    }

    public String packageName() {
        var ret = name.replace('/', '.');
        if (ret.contains(".")) {
            ret = ret.substring(0, ret.lastIndexOf("."));
        } else {
            return "";
        }
        return ret;
    }

    public String underlinedName() {
        return fullName().replace('.', '_');
    }

    @SuppressWarnings({"TextBlockMigration", "StringConcatenationInsideStringBufferAppend"})
    public String generateC() {
        var sb = new StringBuilder();
        sb.append("/* DO NOT EDIT THIS FILE - it is machine generated */\n" +
                  "/* Header for class " + underlinedName() + " */\n");
        sb.append("#ifndef _Included_").append(underlinedName()).append("\n");
        sb.append("#define _Included_").append(underlinedName()).append("\n");
        sb.append("#ifdef __cplusplus\n" +
                  "extern \"C\" {\n" +
                  "#endif\n");
        if (needToGenerateTypeDefinition()) {
            sb.append("\n");
            // union|struct [PNI_DUMMY_]{nativeName};
            if (isUnion()) {
                sb.append("union ");
            } else {
                sb.append("struct ");
            }
            if (typedef()) {
                sb.append("PNI_DUMMY_");
            }
            sb.append(nativeName()).append(";\n");
            if (typedef()) {
                // typedef union|struct PNI_DUMMY_{nativeName} {nativeName};
                sb.append("typedef ");
                if (isUnion()) {
                    sb.append("union ");
                } else {
                    sb.append("struct ");
                }
                sb.append("PNI_DUMMY_").append(nativeName()).append(" ").append(nativeName()).append(";\n");
            }
        }
        sb.append("\n" +
                  "#ifdef __cplusplus\n" +
                  "}\n" +
                  "#endif\n" +
                  "\n");
        sb.append("#include <jni.h>\n" +
                  "#include <pni.h>\n");
        var extraInclude = extraInclude();
        if (extraInclude != null) {
            for (var i : extraInclude) {
                if (i.startsWith("<") && i.endsWith(">")) {
                    sb.append("#include ").append(i).append("\n");
                } else {
                    sb.append("#include \"").append(i).append("\"\n");
                }
            }
        }
        var includedClasses = new HashSet<AstClass>();
        for (var f : fields) {
            if (f.typeRef instanceof ClassTypeInfo classTypeInfo) {
                var cls = classTypeInfo.getClazz();
                if (includedClasses.add(cls)) {
                    include(sb, cls);
                }
            }
        }
        for (var m : methods) {
            if (m.returnTypeRef instanceof ClassTypeInfo classTypeInfo) {
                var cls = classTypeInfo.getClazz();
                if (includedClasses.add(cls)) {
                    include(sb, cls);
                }
            }
            for (var p : m.params) {
                if (p.typeRef instanceof ClassTypeInfo classTypeInfo) {
                    var cls = classTypeInfo.getClazz();
                    if (includedClasses.add(cls)) {
                        include(sb, cls);
                    }
                }
                for (var g : p.genericTypeRefs) {
                    if (g instanceof ClassTypeInfo classTypeInfo) {
                        var cls = classTypeInfo.getClazz();
                        if (includedClasses.add(cls)) {
                            include(sb, cls);
                        }
                    }
                }
            }
        }
        sb.append("\n" +
                  "#ifdef __cplusplus\n" +
                  "extern \"C\" {\n" +
                  "#endif\n");
        generateC(sb, 0, true);
        sb.append("\n" +
                  "#ifdef __cplusplus\n" +
                  "}\n" +
                  "#endif\n" +
                  "#endif // _Included_").append(underlinedName()).append("\n");
        return sb.toString();
    }

    public String generateCImpl() {
        boolean doGenerate = false;
        for (var m : methods) {
            var s = m.getImplC();
            if (s != null) {
                doGenerate = true;
                break;
            }
        }
        if (!doGenerate) {
            return null;
        }

        var sb = new StringBuilder();
        sb.append("#include \"").append(underlinedName()).append(".h\"\n");
        var imports = new HashSet<String>();
        for (var m : methods) {
            var includeLs = m.getImplInclude();
            if (includeLs == null) {
                continue;
            }
            imports.addAll(includeLs);
        }
        for (var i : imports) {
            if (i.startsWith("<") && i.endsWith(">")) {
                sb.append("#include ").append(i).append("\n");
            } else {
                sb.append("#include \"").append(i).append("\"\n");
            }
        }

        //noinspection TextBlockMigration
        sb.append("\n" +
                  "#ifdef __cplusplus\n" +
                  "extern \"C\" {\n" +
                  "#endif\n");

        for (var m : methods) {
            var impl = m.getImplC();
            if (impl == null) {
                continue;
            }
            sb.append("\n");
            m.generateCImpl(sb, 0, underlinedName(), nativeTypeName(), impl);
        }

        //noinspection TextBlockMigration
        sb.append("\n" +
                  "#ifdef __cplusplus\n" +
                  "}\n" +
                  "#endif\n");

        return sb.toString();
    }

    private void include(StringBuilder sb, AstClass cls) {
        sb.append("#include \"").append(cls.underlinedName()).append(".h\"\n");
    }

    @SuppressWarnings("RedundantIfStatement")
    private boolean needToGenerateTypeDefinition() {
        if (isSkip()) return false;
        if (isUnionEmbed()) return false;
        if (isInterface) return false;
        return true;
    }

    public void generateC(StringBuilder sb, int indent, boolean generateCompleteFile) {
        if (needToGenerateTypeDefinition() || !generateCompleteFile) {
            if (generateCompleteFile) {
                sb.append("\n");
            }
            // for complete:
            // union|struct [PNI_DUMMY_][{nativeName}] { ... } [PNI_PACKED] [nativeName];
            // for incomplete:
            // union { ... };
            if (isUnion()) {
                sb.append("union ");
            } else {
                sb.append("struct ");
            }
            if (generateCompleteFile) {
                if (typedef()) {
                    sb.append("PNI_DUMMY_");
                }
                sb.append(nativeName()).append(" ");
            }
            sb.append("{\n");
            for (var f : fields) {
                f.generateC(sb, indent + 4);
            }
            Utils.appendIndent(sb, indent).append("}");
            if (generateCompleteFile) {
                sb.append(" PNI_PACKED");
            }
            sb.append(";\n");
        }
        if (!generateCompleteFile) {
            return;
        }
        if (!methods.isEmpty()) {
            sb.append("\n");
        }
        for (var m : methods) {
            m.generateC(sb, indent, underlinedName(), nativeTypeName());
        }
    }
}
