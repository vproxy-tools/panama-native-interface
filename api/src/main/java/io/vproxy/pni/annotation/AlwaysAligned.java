package io.vproxy.pni.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Assumes that this type/field will always be accessed with alignment.<br/>
 * This annotation affects to generate aligned or unaligned layout.<br/>
 * e.g.: <ul>
 *     <li>{@link java.lang.foreign.ValueLayout#JAVA_INT_UNALIGNED}</li>
 *     <li>{@link java.lang.foreign.ValueLayout#JAVA_INT}</li>
 * </ul>
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AlwaysAligned {
}
