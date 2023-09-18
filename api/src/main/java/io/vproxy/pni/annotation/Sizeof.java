package io.vproxy.pni.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify the minimum size of the type.<br>
 * This is useful for {@link PointerOnly} types,
 * and <code>skip=true</code> types when only part of fields are specified in Java.<br>
 * Note: You CANNOT use a {@link Sizeof} class for a non-pointer field unless it's in a union or is the last field in a struct.<br>
 * If <code>class A</code> has a non-pointer field whose class is annotated with {@link Sizeof},
 * then <code>class A</code> must be annotated with {@link Sizeof} as well.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sizeof {
    /**
     * @return name of a type or a statement.<br>
     * A standalone c file will be automatically generated
     * to retrieve the size of the specified type.
     */
    String value();

    String[] include() default {};
}
