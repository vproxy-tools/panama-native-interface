package io.vproxy.pni.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Make the field or argument to be unsigned.
 * <br/>Only effective for integer types.
 */
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unsigned {
}
