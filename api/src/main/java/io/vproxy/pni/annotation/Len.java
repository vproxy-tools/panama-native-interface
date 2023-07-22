package io.vproxy.pni.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Only used for array types. Define the length of the array,
 * so that it will be considered as embedded into the struct.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Len {
    /**
     * @return 0 or positive values to define the length of the array, negative values to disable effect of this annotation
     */
    long value();
}
