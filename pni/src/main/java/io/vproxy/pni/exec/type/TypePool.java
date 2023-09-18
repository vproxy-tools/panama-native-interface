package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.ast.AstClass;
import io.vproxy.pni.exec.ast.AstTypeDesc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypePool {
    private final Map<String, TypeInfo> internalNameTypes = new HashMap<>();
    private final Map<String, TypeInfo> descTypes = new HashMap<>();

    public TypePool() {
        record(ByteTypeInfo.get());
        record(CharTypeInfo.get());
        record(DoubleTypeInfo.get());
        record(FloatTypeInfo.get());
        record(IntTypeInfo.get());
        record(LongTypeInfo.get());
        record(ShortTypeInfo.get());
        record(BooleanTypeInfo.get());

        record(AnnoAlignTypeInfo.get());
        record(AnnoBitFieldTypeInfo.get());
        record(AnnoCriticalTypeInfo.get());
        record(AnnoFunctionTypeInfo.get());
        record(AnnoImplTypeInfo.get());
        record(AnnoIncludeTypeInfo.get());
        record(AnnoLenTypeInfo.get());
        record(AnnoNameTypeInfo.get());
        record(AnnoPointerOnlyTypeInfo.get());
        record(AnnoPointerTypeInfo.get());
        record(AnnoRawTypeInfo.get());
        record(AnnoSizeofTypeInfo.get());
        record(AnnoStructTypeInfo.get());
        record(AnnoTrivialTypeInfo.get());
        record(AnnoUnionTypeInfo.get());
        record(AnnoUnsignedTypeInfo.get());
        record(AnnoUpcallTypeInfo.get());

        record(PNIFuncTypeInfo.get());
        record(PNIRefTypeInfo.get());

        record(MemorySegmentTypeInfo.get());
        record(ByteBufferTypeInfo.get());
        record(StringTypeInfo.get());

        record(VoidTypeInfo.get());
        record(VoidRefTypeInfo.get());
    }

    private void record(TypeInfo info) {
        internalNameTypes.put(info.internalName(), info);
        descTypes.put(info.desc(), info);
    }

    public void record(AstClass cls) {
        var info = new ClassTypeInfo(cls);
        record(info);
    }

    private TypeInfo findInMaps(String internalNameOrDesc) {
        if (internalNameOrDesc.contains("<")) {
            internalNameOrDesc = internalNameOrDesc.substring(0, internalNameOrDesc.indexOf("<")) + ";";
        }
        var typeInfo = internalNameTypes.get(internalNameOrDesc);
        if (typeInfo != null) return typeInfo;
        return descTypes.get(internalNameOrDesc);
    }

    public TypeInfo find(String internalNameOrDesc) {
        var typeInfo = findInMaps(internalNameOrDesc);
        if (typeInfo != null) {
            return typeInfo;
        }
        if (internalNameOrDesc.startsWith("[")) {
            var elem = find(internalNameOrDesc.substring(1));
            if (elem == null) {
                return null;
            }
            var arrType = new ArrayTypeInfo(elem);
            record(arrType);
            return arrType;
        }
        return tryToFindException(internalNameOrDesc);
    }

    public TypeInfo find(AstTypeDesc desc) {
        var typeInfo = find(desc.desc);
        // typeInfo might be null
        if (typeInfo instanceof PNIRefTypeInfo) {
            typeInfo = new PNIRefGenericTypeInfo(desc.genericTypes);
        } else if (typeInfo instanceof PNIFuncTypeInfo) {
            typeInfo = new PNIFuncGenericTypeInfo(desc.genericTypes, findAll(desc.genericTypes));
        }
        desc.typeRef = typeInfo;
        findAll(desc.genericTypes);
        return typeInfo;
    }

    private List<TypeInfo> findAll(List<AstTypeDesc> types) {
        var ret = new ArrayList<TypeInfo>();
        for (var t : types) {
            ret.add(find(t));
        }
        return ret;
    }

    private TypeInfo tryToFindException(String name) {
        if (name.startsWith("[")) { // array
            return null;
        }
        if (name.startsWith("L")) {
            name = name.substring(1, name.length() - 1);
        }
        name = name.replace('/', '.');
        Class<?> cls;
        try {
            cls = Class.forName(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
        if (Throwable.class.isAssignableFrom(cls)) {
            var typeInfo = new BuiltInExceptionTypeInfo(name,
                name.replace('.', '/'),
                "L" + name.replace('.', '/') + ";");
            record(typeInfo);
            return typeInfo;
        }
        return null;
    }
}
