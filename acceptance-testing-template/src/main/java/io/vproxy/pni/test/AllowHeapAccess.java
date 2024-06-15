package io.vproxy.pni.test;

import io.vproxy.pni.annotation.*;

import java.lang.foreign.MemorySegment;

@Downcall
interface PNIAllowHeapAccess {
    @Impl(
        // language="c"
        c = """
            env->return_ = *((int*)mem);
            return 0;
            """
    )
    @LinkerOption.Critical(allowHeapAccess = true)
    int intValue(MemorySegment mem);

    @Impl(
        c = """
            return *((int*)mem);
            """
    )
    @LinkerOption.Critical(allowHeapAccess = true)
    @Style(Styles.critical)
    int intValueCritical(MemorySegment mem);
}
