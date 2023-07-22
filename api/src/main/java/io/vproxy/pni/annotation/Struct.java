package io.vproxy.pni.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Generate a C struct for the class.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Struct {
    /**
     * @return Use typedef for the struct.
     */
    boolean typedef() default true;

    /**
     * @return Skip generating the type. This is useful if it's defined in another C header file.
     */
    boolean skip() default false;
}
