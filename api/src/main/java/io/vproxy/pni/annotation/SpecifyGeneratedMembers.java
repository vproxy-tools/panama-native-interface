package io.vproxy.pni.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to manually specify which
 * members (fields/methods) should be generated.<br/>
 * This is useful for some JVM languages such as Kotlin,
 * which automatically generates methods or even fields.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpecifyGeneratedMembers {
}
