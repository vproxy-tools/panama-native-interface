package io.vproxy.pni.test;

import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.annotation.Impl;
import io.vproxy.pni.annotation.Raw;
import io.vproxy.pni.annotation.Struct;

import java.util.List;
import java.util.Map;

@Struct
public abstract class PNIRefAndFuncFields {
    PNIRef<Map<String, Integer>> ref;
    PNIRef<List<PNIObjectStruct>> ref2;
    PNIRef<PNIObjectStruct[]> ref3;
    PNIFunc<PNIRef<Map<String, Integer>>> func;
    PNIFunc<PNIObjectStruct> func2;

    @Impl(
        // language="c"
        c = """
            PNIFuncInvoke(self->func, self->ref);
            return 0;
            """
    )
    abstract void call();

    @Impl(
        // language="c"
        c = """
            PNIFuncInvoke(self->func2, o);
            return 0;
            """
    )
    abstract void call2(PNIObjectStruct o);

    @Impl(
        // language="c"
        c = """
            self->ref = ref;
            self->ref2 = ref2;
            self->ref3 = ref3;
            self->func = func;
            self->func2 = func2;
            return 0;
            """
    )
    abstract void set(PNIRef<Map<String, Integer>> ref,
                      PNIRef<List<PNIObjectStruct>> ref2,
                      PNIRef<PNIObjectStruct[]> ref3,
                      PNIFunc<PNIRef<Map<String, Integer>>> func,
                      PNIFunc<PNIObjectStruct> func2);

    @Impl(
        // language="c"
        c = """
            self->ref = ref;
            self->ref2 = ref2;
            self->ref3 = ref3;
            return 0;
            """
    )
    abstract void setRaw(@Raw PNIRef<Map<String, Integer>> ref,
                         @Raw PNIRef<List<PNIObjectStruct>> ref2,
                         @Raw PNIRef<PNIObjectStruct[]> ref3);

    @Impl(
        // language="c"
        c = """
            env->return_ = self->ref;
            return 0;
            """
    )
    abstract PNIRef<Map<String, Integer>> retrieveRef();

    @Impl(
        // language="c"
        c = """
            env->return_ = self->ref2;
            return 0;
            """
    )
    abstract PNIRef<List<PNIObjectStruct>> retrieveRef2();

    @Impl(
        // language="c"
        c = """
            env->return_ = self->ref3;
            return 0;
            """
    )
    abstract PNIRef<PNIObjectStruct[]> retrieveRef3();

    @Impl(
        // language="c"
        c = """
            env->return_ = self->func;
            return 0;
            """
    )
    abstract PNIFunc<PNIRef<Map<String, Integer>>> retrieveFunc();

    @Impl(
        // language="c"
        c = """
            env->return_ = self->func2;
            return 0;
            """
    )
    abstract PNIFunc<PNIObjectStruct> retrieveFunc2();
}
