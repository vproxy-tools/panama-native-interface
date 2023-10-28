package io.vproxy.pni.exec.ast;

import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.WarnType;
import io.vproxy.pni.exec.internal.PNILogger;
import io.vproxy.pni.exec.internal.PointerInfo;
import io.vproxy.pni.exec.internal.Utils;
import io.vproxy.pni.exec.internal.VarOpts;
import io.vproxy.pni.exec.type.*;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.FieldNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AstField {
    public final List<AstAnno> annos = new ArrayList<>();
    public String name;
    public AstTypeDesc type;

    public TypeInfo typeRef;
    public long padding = 0;
    public long extraPadding = 0;

    public AstField(FieldNode f) {
        Utils.readAnnotations(annos, f.visibleAnnotations);
        this.name = f.name;
        this.type = Utils.extractDesc(f.signature == null ? f.desc : f.signature).get(0);
    }

    public AstField() {
        // for unit testing only
    }

    public void ref(TypePool pool) {
        typeRef = pool.find(type);
        for (var a : annos) {
            a.ref(pool);
        }
    }

    public void validate(CompilerOptions opts, String path, List<String> errors) {
        path = path + "#field(" + name + ")";
        if (typeRef == null) {
            errors.add(path + ": unable to find typeRef: " + type);
        } else {
            typeRef.checkType(errors, path, varOpts(), false);
        }
        for (var a : annos) {
            a.validate(path, errors);
        }
        var name = Utils.getName(annos);
        if (name != null) {
            if (!Utils.isValidName(name, false)) {
                errors.add(path + ": invalid @Name(" + name + ")");
            }
        }
        var bitfieldAnnoOpt = annos.stream().filter(a -> a.typeRef instanceof AnnoBitTypeInfo).findFirst();
        if (bitfieldAnnoOpt.isPresent()) {
            var infoLs = validateAndGetBitFieldInfo(path, errors);
            if (infoLs != null) {
                int totalSize = 0;
                for (var info : infoLs) {
                    if (!Utils.isValidName(info.name, false)) {
                        errors.add(path + ": invalid @Bit.Field name: " + info.name);
                    }
                    if (info.bit < 1) {
                        errors.add(path + ": invalid @Bit.Field bit for " + info.name + ": " + info.bit);
                    }
                    totalSize += info.bit;
                }
                int allowedSize = 0;
                if (typeRef != null) {
                    if (typeRef instanceof IntTypeInfo) {
                        allowedSize = 32;
                    } else if (typeRef instanceof LongTypeInfo) {
                        allowedSize = 64;
                    } else if (typeRef instanceof ShortTypeInfo) {
                        allowedSize = 16;
                    } else if (typeRef instanceof ByteTypeInfo) {
                        allowedSize = 8;
                    } else {
                        errors.add(path + ": cannot use @Bit on " + typeRef.name() + " fields");
                    }
                }
                if (allowedSize > 0 && totalSize > allowedSize) {
                    errors.add(path + ": invalid @Bit.Field, bit size (" + totalSize + ") is larger than the field (" + allowedSize + ")");
                }
            }
        }

        var align = getAlign();
        if (align > 1 && (align & align - 1) != 0) {
            PNILogger.warn(errors, path, annos, opts, WarnType.ALIGNMENT_NOT_POWER_OF_2, "alignment is not power of 2");
        }
    }

    public void validateAlignment(List<String> errors, String path, long sum, boolean alwaysAligned, boolean packed) {
        path = path + "#field(" + name + ")";

        if (!alwaysAligned && !isAlwaysAligned()) {
            return;
        }
        var align = getAlignmentBytes(packed);
        if (align <= 1) {
            return;
        }
        if (sum % align == 0) {
            return;
        }
        errors.add(path + ": is not aligned properly");
    }

    public boolean isAligned(long offset) {
        var align = typeRef.rawNativeMemoryAlign(varOpts());
        return offset % align == 0;
    }

    public String nativeName() {
        var name = Utils.getName(annos);
        if (name == null)
            return this.name;
        return name;
    }

    public PointerInfo pointerInfo() {
        var has = annos.stream().anyMatch(a -> a.typeRef instanceof AnnoPointerTypeInfo);
        return PointerInfo.ofField(has);
    }

    public boolean isUnsigned() {
        return annos.stream().anyMatch(a -> a.typeRef instanceof AnnoUnsignedTypeInfo);
    }

    public boolean isAlwaysAligned() {
        return annos.stream().anyMatch(a -> a.typeRef instanceof AnnoAlwaysAlignedTypeInfo);
    }

    public long getLen() {
        return Utils.getLen(annos);
    }

    public VarOpts varOpts() {
        return VarOpts.of(isUnsigned(), pointerInfo(), getLen());
    }

    public long getNativeMemorySize() {
        return typeRef.nativeMemorySize(varOpts());
    }

    public long getAlign() {
        return Utils.getAlign(annos);
    }

    public boolean isAlignPacked() {
        return Utils.getAlignPacked(annos);
    }

    public List<BitFieldInfo> getBitFieldInfo() {
        return validateAndGetBitFieldInfo("", new ArrayList<>());
    }

    private List<BitFieldInfo> validateAndGetBitFieldInfo(String path, List<String> errors) {
        path = path + "#anno(io.vproxy.pni.annotation.Bit)";
        var opt = annos.stream().filter(a -> a.typeRef instanceof AnnoBitTypeInfo).findFirst();
        if (opt.isEmpty()) {
            return null;
        }
        var anno = opt.get();
        var valueOpt = anno.values.stream().filter(v -> v.name.equals("value")).findFirst();
        if (valueOpt.isEmpty()) {
            errors.add(path + ": annotation field(value) not specified");
            return null;
        }
        var value = valueOpt.get().value;
        if (!(value instanceof List)) {
            errors.add(path + ": invalid annotation field(value): is not list");
            return null;
        }
        //noinspection rawtypes
        var valueLs = (List) value;
        for (var o : valueLs) {
            if (!(o instanceof AnnotationNode)) {
                errors.add(path + ": invalid annotation field(value): is not annotation list");
                return null;
            }
        }
        //noinspection unchecked
        var annoLs = ((List<AnnotationNode>) valueLs).stream().map(AstAnno::new).collect(Collectors.toList());
        var ret = new ArrayList<BitFieldInfo>();
        int total = 0;
        for (int i = 0; i < annoLs.size(); i++) {
            var o = annoLs.get(i);
            if (!o.type.desc.equals("Lio/vproxy/pni/annotation/Bit$Field;")) {
                errors.add(path + ": invalid annotation field(value[" + i + "]): is not @Bit.Field");
                return null;
            }
            var nameOpt = o.values.stream().filter(v -> v.name.equals("name")).findFirst();
            var bitsOpt = o.values.stream().filter(v -> v.name.equals("bits")).findFirst();
            var boolOpt = o.values.stream().filter(v -> v.name.equals("bool")).findFirst();
            if (nameOpt.isEmpty() || !(nameOpt.get().value instanceof String)) {
                errors.add(path + ": invalid annotation field(value[" + i + "]): invalid name");
                return null;
            }
            if (bitsOpt.isEmpty() || !(bitsOpt.get().value instanceof Integer)) {
                errors.add(path + ": invalid annotation field(value[" + i + "]): invalid bits");
                return null;
            }
            if (boolOpt.isPresent()) {
                if (boolOpt.get().value instanceof Boolean) {
                    var bits = (int) bitsOpt.get().value;
                    if (bits != 1) {
                        errors.add(path + ": invalid annotation field(value[" + i + "]): bool=true but bits=" + bits);
                    }
                } else {
                    errors.add(path + ": invalid annotation field(value[" + i + "]): invalid bool");
                    boolOpt = Optional.empty();
                }
            }
            var name = (String) nameOpt.get().value;
            var bits = (int) bitsOpt.get().value;
            var bool = boolOpt.isPresent() && (boolean) boolOpt.get().value;
            ret.add(new BitFieldInfo(name, total, bits, bool));
            total += bits;
        }
        // check native type
        if (getNativeTypeAnno() != null) {
            errors.add(path + ": cannot customize native type for bit fields");
            return null;
        }
        return ret;
    }

    public boolean typeOfTheFieldIsAnnotatedWithSizeof() {
        if (!(typeRef instanceof ClassTypeInfo))
            return false;
        var cls = ((ClassTypeInfo) typeRef).getClazz();
        return cls.getSizeof() != null;
    }

    public String getNativeTypeAnno() {
        return Utils.getNativeType(annos);
    }

    public long getAlignmentBytes(boolean packed) {
        if (isAlignPacked()) {
            return 0;
        }
        var annoAlign = getAlign();
        if (annoAlign <= 1 && packed) {
            return 0;
        }
        if (packed) {
            return annoAlign;
        }
        long n = typeRef.nativeMemoryAlign(varOpts());
        if (n < annoAlign) {
            n = annoAlign;
        }
        return n;
    }

    public long getRawAlignmentBytes() {
        return typeRef.rawNativeMemoryAlign(varOpts());
    }

    public String getterName() {
        return Utils.getterName(name, typeRef instanceof BooleanTypeInfo);
    }

    public String setterName() {
        return Utils.setterName(name);
    }

    public void toString(StringBuilder sb, int indent) {
        Utils.appendIndent(sb, indent);
        for (var a : annos) {
            a.toString(sb, 0);
            sb.append(" ");
        }
        if (typeRef != null) {
            sb.append(typeRef.name());
        } else {
            sb.append(type);
        }
        sb.append(" ").append(name).append(";");
    }
}
