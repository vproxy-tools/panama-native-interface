package io.vproxy.pni.exec.type;

import io.vproxy.pni.exec.ast.AstTypeDesc;
import io.vproxy.pni.exec.internal.VarOpts;

import java.util.List;

public class CallSiteGenericTypeInfo extends CallSiteTypeInfo {
    private final List<AstTypeDesc> genericTypes;
    private final List<TypeInfo> genericTypeRefs;

    public CallSiteGenericTypeInfo(List<AstTypeDesc> genericTypes, List<TypeInfo> genericTypeRefs) {
        super();
        this.genericTypes = genericTypes;
        this.genericTypeRefs = genericTypeRefs;
    }

    @Override
    public void checkType(List<String> errors, String path, VarOpts opts, boolean upcall) {
        super.checkType(errors, path, opts, upcall);
        if (genericTypeRefs.size() != 1) {
            errors.add(path + ": CallSite should have exactly one generic param: " + genericTypes);
        } else if (genericTypeRefs.get(0) != null) {
            var ref = genericTypeRefs.get(0);
            if (!(ref instanceof ClassTypeInfo) && !(ref instanceof VoidRefTypeInfo) && !(ref instanceof PNIRefTypeInfo)) {
                errors.add(path + "#<0>: CallSite can only take Struct/Union or PNIRef or java.lang.Void as its argument");
            }
            ref.checkType(errors, path + "#<0>", VarOpts.fieldDefault(), upcall);
        }
        for (int i = 0; i < genericTypeRefs.size(); i++) {
            var r = genericTypeRefs.get(i);
            if (r == null) {
                errors.add(path + "#<" + i + ">: unable to find genericTypeRef: " + genericTypes.get(i));
            }
        }
    }

    @Override
    public String javaTypeForParam(VarOpts opts) {
        if (genericTypeRefs.get(0) instanceof VoidRefTypeInfo) {
            return "io.vproxy.pni.CallSite<Void>";
        } else if (genericTypeRefs.get(0) instanceof PNIRefGenericTypeInfo) {
            var t = (PNIRefGenericTypeInfo) genericTypeRefs.get(0);
            return "io.vproxy.pni.CallSite<" + t.getGenericTypeString(0) + ">";
        } else if (genericTypeRefs.get(0) instanceof PNIRefTypeInfo) {
            // additional check!
            throw new UnsupportedOperationException("should not reach here");
        }
        return "io.vproxy.pni.CallSite<" + ((ClassTypeInfo) genericTypeRefs.get(0)).getClazz().fullName() + ">";
    }

    @Override
    public String convertParamToInvokeExactArgument(String name, VarOpts opts) {
        if (genericTypeRefs.get(0) instanceof VoidRefTypeInfo) {
            return "PNIFunc.VoidFunc.of(" + name + ").MEMORY";
        } else if (genericTypeRefs.get(0) instanceof PNIRefTypeInfo) {
            return "PNIRef.Func.of(" + name + ").MEMORY";
        }
        return ((ClassTypeInfo) genericTypeRefs.get(0)).getClazz().fullName() + ".Func.of(" + name + ").MEMORY";
    }

    @Override
    public String convertToUpcallArgument(String name, VarOpts opts) {
        if (genericTypeRefs.get(0) instanceof VoidRefTypeInfo) {
            return "(" + name + ".address() == 0 ? null : PNIFunc.VoidFunc.of(" + name + ").getCallSite())";
        } else if (genericTypeRefs.get(0) instanceof PNIRefTypeInfo) {
            return "(" + name + ".address() == 0 ? null : (io.vproxy.pni.CallSite) PNIRef.Func.of(" + name + ").getCallSite())";
        }
        return "(" + name + ".address() == 0 ? null : " +
               ((ClassTypeInfo) genericTypeRefs.get(0)).getClazz().fullName() + ".Func.of(" + name + ").getCallSite())";
    }
}
