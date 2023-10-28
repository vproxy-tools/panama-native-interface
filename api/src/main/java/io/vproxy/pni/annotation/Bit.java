package io.vproxy.pni.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bit {
    Field[] value();

    @Target({})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Field {
        String name();

        int bits();
    }
}
