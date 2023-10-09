package io.vproxy.pni.exec;

import io.vproxy.pni.exec.internal.Utils;

import java.util.function.Function;
import java.util.function.Predicate;

public class CompilationFlag<T> {
    public static final CompilationFlag<String> GRAAL_NATIVE_IMAGE_FEATURE = new CompilationFlag<>(
        "graal-native-image-feature", "GraalNativeImageFeature",
        Utils::isValidClassName, s -> s);
    public static final CompilationFlag<Void> GRAAL_C_ENTRYPOINT_LITERAL_UPCALL = new CompilationFlag<>(
        "graal-c-entrypoint-literal-upcall", "",
        String::isBlank, s -> null);

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
        GRAAL_C_ENTRYPOINT_LITERAL_UPCALL,
    };

    public static CompilationFlag<?>[] values() {
        return VALUES;
    }
}
