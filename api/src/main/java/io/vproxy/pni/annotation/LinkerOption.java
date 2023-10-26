package io.vproxy.pni.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class LinkerOption {
    private LinkerOption() {
    }

    /**
     * Mark the function to be critical, see Linker.Option.isTrivial in jdk 21,
     * or Linker.Option.critical in jdk 22.
     */
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Critical {
    }
}
