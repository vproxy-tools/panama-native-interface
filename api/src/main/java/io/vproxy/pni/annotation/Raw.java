package io.vproxy.pni.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.lang.foreign.MemorySegment;

/**
 * Annotate the data type to be converted to its raw form.
 * <ul>
 * <li>This annotation currently only applies to {@link ByteBuffer} parameters,
 * which will be converted to {@link MemorySegment}.
 * This has the same effect as setting {@link ByteBuffer#position()} to 0
 * and {@link ByteBuffer#limit()} to {@link ByteBuffer#capacity()},
 * without actually modifying the buffer.</li>
 * </ul>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Raw {
}
