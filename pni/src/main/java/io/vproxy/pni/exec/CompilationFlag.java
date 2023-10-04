package io.vproxy.pni.exec;

import io.vproxy.pni.exec.internal.Utils;

import java.util.function.Function;
import java.util.function.Predicate;

public class CompilationFlag<T> {
    public static final CompilationFlag<String> GRAAL_NATIVE_IMAGE_FEATURE = new CompilationFlag<>(
        "graal-native-image-feature", "GraalNativeImageFeature",
        Utils::isValidClassName, s -> s);

    public final String name;
    public final String defaultValue;
    public final Predicate<String> validate;
    public final Function<String, T> convert;

    private CompilationFlag(String name, String defaultValue, Predicate<String> validate, Function<String, T> convert) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.validate = validate;
        this.convert = convert;
    }

    public static CompilationFlag<?> searchForCompilationFlagByName(String name) {
        for (var v : values()) {
            if (v.name.equals(name)) {
                return v;
            }
        }
        return null;
    }

    private static final CompilationFlag<?>[] VALUES = new CompilationFlag[]{
        GRAAL_NATIVE_IMAGE_FEATURE,
    };

    public static CompilationFlag<?>[] values() {
        return VALUES;
    }
}
