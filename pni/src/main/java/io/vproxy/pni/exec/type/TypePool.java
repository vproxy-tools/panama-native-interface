package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.ast.AstClass;

import java.util.HashMap;
import java.util.Map;

public class TypePool {
    private final Map<String, TypeInfo> types = new HashMap<>();

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
        record(AnnoCriticalTypeInfo.get());
        record(AnnoFunctionTypeInfo.get());
        record(AnnoImplTypeInfo.get());
        record(AnnoIncludeTypeInfo.get());
        record(AnnoLenTypeInfo.get());
        record(AnnoNameTypeInfo.get());
        record(AnnoPointerTypeInfo.get());
        record(AnnoRawTypeInfo.get());
        record(AnnoStructTypeInfo.get());
        record(AnnoTrivialTypeInfo.get());
        record(AnnoUnionTypeInfo.get());
        record(AnnoUnsignedTypeInfo.get());

        record(CallSiteTypeInfo.get());

        record(MemorySegmentTypeInfo.get());
        record(ByteBufferTypeInfo.get());
        record(StringTypeInfo.get());

        record(VoidTypeInfo.get());
        record(VoidRefTypeInfo.get());
    }

    private void record(TypeInfo info) {
        types.put(info.internalName(), info);
        types.put(info.desc(), info);
    }

    public void record(AstClass cls) {
        var info = new ClassTypeInfo(cls);
        record(info);
    }

    public TypeInfo find(String internalNameOrDesc) {
        var typeInfo = types.get(internalNameOrDesc);
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
