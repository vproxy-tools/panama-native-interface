package io.vproxy.pni.exec.ast;

import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.WarnType;
import io.vproxy.pni.exec.internal.PNILogger;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.type.*;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

import static io.vproxy.pni.exec.internal.Consts.*;

public class AstClass {
    public boolean isInterface;
    public String name;
    public String superName;
    public final List<AstAnno> annos = new ArrayList<>();
    public final List<AstField> fields = new ArrayList<>();
    public final List<AstMethod> methods = new ArrayList<>();

    public TypeInfo superTypeRef;
    public long headPadding = 0;
    public long extraHeadPadding = 0;

    public AstClass(ClassNode classNode) {
        isInterface = (classNode.access & Opcodes.ACC_INTERFACE) == Opcodes.ACC_INTERFACE;
        this.name = classNode.name;
        this.superName = classNode.superName;
        if ("java/lang/Object".equals(classNode.superName)) {
            this.superName = null;
        }
        Utils.readAnnotations(annos, classNode.visibleAnnotations);
        for (var f : classNode.fields) {
            if ((f.access & Opcodes.ACC_STATIC) == Opcodes.ACC_STATIC) {
                continue;
            }
            this.fields.add(new AstField(f));
        }
        for (var m : classNode.methods) {
            if (m.name.equals("<init>") || m.name.equals("<cinit>")) {
                continue;
            }
            if ((m.access & Opcodes.ACC_STATIC) == Opcodes.ACC_STATIC) {
                continue;
            }
            this.methods.add(new AstMethod(m));
        }
    }

    public AstClass() {
        // for unit testing only
    }

    public void ref(TypePool pool) {
        if (superName != null) {
            superTypeRef = pool.find(superName);
        }
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

    public void validate(CompilerOptions opts, List<String> errors) {
        var path = "class(" + name + ")";
        if (superName != null && superTypeRef == null) {
            errors.add(path + ": unable to find typeRef: " + superName);
        }
        if (superTypeRef != null) {
            if (!(superTypeRef instanceof ClassTypeInfo)) {
                errors.add(path + ": superType(" + superTypeRef + ") is not a user defined class");
            } else {
                var superCls = ((ClassTypeInfo) superTypeRef).getClazz();
                if (!superCls.isStruct()) {
                    errors.add(path + ": superType(" + superTypeRef + ") is not a struct");
                }
                if (superCls.getSizeof() != null) {
                    errors.add(path + ": superType(" + superTypeRef + ") cannot be annotated with @Sizeof");
                }
            }
            if (!isStruct()) {
                errors.add(path + ": cannot extend from other types because it's not a struct");
            }
        }
        for (var a : annos) {
            a.validate(path, errors);
        }
        for (var f : fields) {
            f.validate(opts, path, errors);
        }
        for (var m : methods) {
            m.validate(path, errors, isUpcall());
        }

        if (isPointerOnly()) {
            if (!fields.isEmpty()) {
                errors.add(path + ": cannot define fields in this type because it is marked with @PointerOnly");
            }
        }

        var align = getAlign();
        if (align > 1 && (align & align - 1) != 0) {
            PNILogger.warn(errors, path, annos, opts, WarnType.ALIGNMENT_NOT_POWER_OF_2, "alignment is not power of 2");
        }

        var names = new HashSet<String>();
        AstField lastSizeofField = null;
        for (var f : fields) {
            if (!names.add(f.nativeName())) {
                var err = path + ": two or more fields have the same native name " + f.nativeName();
                if (!errors.contains(err)) {
                    errors.add(err);
                }
            }
            if (lastSizeofField != null && !isUnion()) {
                errors.add(path + "#field(" + lastSizeofField.name + "): class of the field is annotated with @Sizeof, but is not the last field");
            }
            if (!f.pointerInfo().isPointer() && f.typeRef instanceof ClassTypeInfo) {
                var cls = ((ClassTypeInfo) f.typeRef).getClazz();
                if (cls.getSizeof() != null) {
                    lastSizeofField = f;
                }
            }
        }
        if (lastSizeofField != null && getSizeof() == null) {
            errors.add(path + ": has a field whose class is annotated with @Sizeof, but this class is not");
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
        var hasFunction = isFunction();
        var hasUpcall = isUpcall();
        if (hasStruct && hasUnion) {
            errors.add(path + ": is annotated with both @Struct and @Union");
        }
        if (hasStruct && hasFunction) {
            errors.add(path + ": is annotated with both @Struct and @Function");
        }
        if (hasStruct && hasUpcall) {
            errors.add(path + ": is annotated with both @Struct and @Upcall");
        }
        if (hasUnion && hasFunction) {
            errors.add(path + ": is annotated with both @Union and @Function");
        }
        if (hasUnion && hasUpcall) {
            errors.add(path + ": is annotated with both @Union and @Upcall");
        }
        if (hasFunction && hasUpcall) {
            errors.add(path + ": is annotated with both @Function and @Upcall");
        }
        if (hasFunction && !isInterface) {
            errors.add(path + ": is annotated with @Function but is not an interface");
        }
        if (hasUpcall && !isInterface) {
            errors.add(path + ": is annotated with @Upcall but is not an interface");
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
            if (f.typeRef instanceof ClassTypeInfo) {
                var classTypeInfo = (ClassTypeInfo) f.typeRef;
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
            if (f.typeRef instanceof ClassTypeInfo) {
                var classTypeInfo = (ClassTypeInfo) f.typeRef;
                var c = classTypeInfo.getClazz();
                validateDependency(c, errors, path + " -> " + f.name, classes);
            }
        }
    }

    public void validateAlignment(List<String> errors) {
        if (!isStruct()) {
            return;
        }
        var path = "class(" + name + ")";
        long sum = 0;
        if (superTypeRef != null) {
            sum = ((ClassTypeInfo) superTypeRef).getClazz().getNativeMemorySize();
        }
        if (headPadding > 0) {
            sum += headPadding;
        }
        for (var f : fields) {
            f.validateAlignment(errors, path, sum, isAlwaysAligned(), isAlignPacked());
            sum += f.getNativeMemorySize();
            sum += f.padding;
        }
        if (isAlwaysAligned() && largestAlignmentBytes() > 1 && getSizeof() == null) {
            if (sum % largestAlignmentBytes() != 0) {
                errors.add(path + ": struct trailing padding is not aligned properly");
            }
        }
    }

    public boolean isAligned() {
        if (isUnion()) {
            for (var f : fields) {
                if (!f.isAligned(0)) {
                    return false;
                }
            }
            return true;
        }

        long sum = 0;
        if (superTypeRef != null) {
            var cls = ((ClassTypeInfo) superTypeRef).getClazz();
            if (!cls.isAligned()) {
                return false;
            }
            sum = cls.getNativeMemorySize();
        }
        if (headPadding > 0) {
            sum += headPadding;
        }
        for (var f : fields) {
            if (!f.isAligned(sum)) {
                return false;
            }
            sum += f.getNativeMemorySize();
            sum += f.padding;
        }
        if (largestRawAlignmentBytes() > 1 && getSizeof() == null) {
            return sum % largestRawAlignmentBytes() == 0;
        }
        return true;
    }

    public boolean isStruct() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(StructClassName));
    }

    public boolean isUnion() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(UnionClassName));
    }

    public boolean isFunction() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(FunctionClassName));
    }

    public boolean isUpcall() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(UpcallClassName));
    }

    public boolean isPointerOnly() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(PointerOnlyClassName));
    }

    public String getSizeof() {
        var opt = annos.stream().filter(a -> a.typeRef != null && a.typeRef.name().equals(SizeofClassName)).findFirst();
        if (opt.isEmpty()) {
            return null;
        }
        var anno = opt.get();
        var valueOpt = anno.values.stream().filter(v -> v.name.equals("value")).findFirst();
        if (valueOpt.isEmpty()) {
            return null;
        }
        var v = valueOpt.get();
        if (v.value instanceof String) {
            return (String) v.value;
        }
        return null;
    }

    public List<String> getSizeofInclude() {
        return Utils.getStringListFromAnno(annos, SizeofClassName, "include");
    }

    public boolean isUnionEmbed() {
        if (!isUnion()) {
            return false;
        }
        //noinspection OptionalGetWithoutIsPresent
        var anno = annos.stream().filter(a -> a.typeRef != null && a.typeRef.name().equals(UnionClassName)).findFirst().get();
        var opt = anno.values.stream().filter(v -> v.name.equals("embedded")).findFirst();
        if (opt.isEmpty()) {
            return false;
        }
        var v = opt.get();
        if (v.value instanceof Boolean) {
            return (Boolean) v.value;
        }
        return false;
    }

    public boolean isSkip() {
        var annoOpt = annos.stream().filter(a -> a.typeRef != null &&
                                                 (a.typeRef.name().equals(StructClassName) || a.typeRef.name().equals(UnionClassName)))
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
        if (v.value instanceof Boolean) {
            return (Boolean) v.value;
        }
        return false;
    }

    public long getAlign() {
        return Utils.getAlign(annos);
    }

    public boolean isAlignPacked() {
        return Utils.getAlignPacked(annos);
    }

    public boolean isAlwaysAligned() {
        return annos.stream().anyMatch(a -> a.typeRef != null && a.typeRef.name().equals(AlwaysAlignedClassName));
    }

    public List<String> extraInclude() {
        return Utils.getStringListFromAnno(annos, IncludeClassName, "value");
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

        var packed = isAlignPacked();
        long total = 0;
        if (superTypeRef != null) {
            var superCls = ((ClassTypeInfo) superTypeRef).getClazz();
            total = superCls.getNativeMemorySize();

            // must handle first field manually, to satisfy the following algorithm
            if (!fields.isEmpty()) {
                var first = fields.get(0);
                var align = first.getAlignmentBytes(packed);
                var rawAlign = first.getRawAlignmentBytes();
                if (align > 1) {
                    if (total % align != 0) {
                        total += assignPaddings(total, n -> headPadding = n, n -> extraHeadPadding = n, align, rawAlign);
                    }
                }
            }
        }
        AstField lastField = null;
        for (var f : fields) {
            var size = f.getNativeMemorySize();
            var align = f.getAlignmentBytes(packed);
            var rawAlign = f.getRawAlignmentBytes();
            if (align > 1) {
                if (total % align != 0) {
                    assert lastField != null; // the first field is already handled
                    total += assignPaddings(total, lastField, align, rawAlign);
                }
            }
            total += size;
            lastField = f;
        }
        if (lastField != null && !lastField.typeOfTheFieldIsAnnotatedWithSizeof()) {
            var n = largestAlignmentBytes();
            var rawAlign = largestRawAlignmentBytes();
            var annoAlign = getAlign();
            if (n < annoAlign) {
                n = annoAlign;
            }
            if (n > 1 && total % n != 0) {
                total += assignPaddings(total, lastField, n, rawAlign);
            }
        }
        __calculatedNativeMemorySize = total;
        return total;
    }

    private long assignPaddings(long total, AstField lastField, long align, long rawAlign) {
        return assignPaddings(total, n -> lastField.padding = n, n -> lastField.extraPadding = n, align, rawAlign);
    }

    private long assignPaddings(long total, Consumer<Long> paddingSetter, Consumer<Long> extraPaddingSetter, long align, long rawAlign) {
        var padding = align - (total % align);
        paddingSetter.accept(padding);
        if (total % rawAlign != 0) {
            extraPaddingSetter.accept(padding - (rawAlign - (total % rawAlign)));
        } else {
            extraPaddingSetter.accept(padding);
        }
        return padding;
    }

    public long largestAlignmentBytes() {
        var packed = isAlignPacked();
        long max = 0;
        for (var f : fields) {
            var a = f.getAlignmentBytes(packed);
            if (max < a) {
                max = a;
            }
        }
        var align = getAlign();
        if (!packed && max < align) {
            max = align;
        }
        if (superTypeRef != null) {
            var superAlign = ((ClassTypeInfo) superTypeRef).getClazz().largestAlignmentBytes();
            if (superAlign > max) {
                max = superAlign;
            }
        }
        return max;
    }

    public long largestRawAlignmentBytes() {
        long max = 0;
        for (var f : fields) {
            var a = f.getRawAlignmentBytes();
            if (max < a) {
                max = a;
            }
        }
        return max;
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
        sb.append(" ").append(name);
        if (superTypeRef != null) {
            sb.append(" extends ").append(((ClassTypeInfo) superTypeRef).getClazz().name);
        }
        sb.append(" {\n");
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
                         (a.typeRef.name().equals(StructClassName) || a.typeRef.name().equals(UnionClassName))
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
        if (!(v.value instanceof Boolean)) {
            return true;
        }
        return (Boolean) v.value;
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

    @SuppressWarnings("RedundantIfStatement")
    public boolean needToGenerateTypeDefinition() {
        if (isSkip()) return false;
        if (isUnionEmbed()) return false;
        if (isInterface) return false;
        return true;
    }

    public boolean needToGenerateExpand() {
        return needToGenerateTypeDefinition() || isSkip();
    }
}
