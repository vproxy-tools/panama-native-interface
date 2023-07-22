package io.vproxy.pni.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Generate a c union for the class.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Union {
    /**
     * @return Use typedef for the union.
     */
    boolean typedef() default true;

    /**
     * @return Do not generate a union type, simply embed it into another struct.
     */
    boolean embedded() default false;

    /**
     * @return Skip generating the type. This is useful if it's defined in another C header file.
     */
    boolean skip() default false;
}
