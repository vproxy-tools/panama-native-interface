package io.vproxy.pni.exec;

import io.vproxy.pni.exec.internal.Utils;

import java.io.File;
import java.util.function.Function;
import java.util.function.Predicate;

public class CompilationFlag<T> {
    public static final CompilationFlag<String> GRAAL_NATIVE_IMAGE_FEATURE = new CompilationFlag<>(
        "graal-native-image-feature", "GraalNativeImageFeature",
        Utils::isValidClassName, s -> s);
    public static final CompilationFlag<Void> GRAAL_C_ENTRYPOINT_LITERAL_UPCALL = new CompilationFlag<>(
        "graal-c-entrypoint-literal-upcall", "",
        String::isBlank, s -> null);
    public static final CompilationFlag<File> RELEASE_PNI_H_FILE = new CompilationFlag<>(
        "release-pni-h-file", "",
        fun(s -> {
            if (s.isEmpty()) return null;
            if (new File(s).isDirectory()) return null;
            else return s + " does not exist or is not a directory";
        }), s -> s.isEmpty() ? null : new File(s));
    public static final CompilationFlag<File> RELEASE_PNI_C_FILE = new CompilationFlag<>(
        "release-pni-c-file", "",
        fun(s -> {
            if (s.isEmpty()) return null;
            if (new File(s).isDirectory()) return null;
            else return s + " does not exist or is not a directory";
        }), s -> s.isEmpty() ? null : new File(s));
    public static final CompilationFlag<File> RELEASE_JNI_H_MOCK_FILE = new CompilationFlag<>(
        "release-jni-h-mock-file", "",
        fun(s -> {
            if (s.isEmpty()) return null;
            if (new File(s).isDirectory()) return null;
            else return s + " does not exist or is not a directory";
        }), s -> s.isEmpty() ? null : new File(s));
    public static final CompilationFlag<String> TYPE_NAME_PREFIX = new CompilationFlag<>(
        "type-name-prefix", "",
        pred(s -> Utils.isValidName(s, false)), s -> s
    );
    public static final CompilationFlag<Boolean> ALWAYS_ALIGNED = new CompilationFlag<>(
        "always-aligned", "true",
        pred(s -> "true".equals(s) || "false".equals(s)), "true"::equals
    );

    public final String name;
    public final String defaultValue;
    public final Function<String, String> validate;
    public final Function<String, T> convert;

    public CompilationFlag(String name, String defaultValue, Predicate<String> validate, Function<String, T> convert) {
        this(name, defaultValue, (Function<String, String>) s -> {
            if (validate.test(s)) return null; // means no error
            else return ""; // means empty error message
        }, convert);
    }

    public CompilationFlag(String name, String defaultValue, Function<String, String> validate, Function<String, T> convert) {
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
        RELEASE_PNI_H_FILE,
        RELEASE_PNI_C_FILE,
        RELEASE_JNI_H_MOCK_FILE,
        TYPE_NAME_PREFIX,
        ALWAYS_ALIGNED,
    };

    public static CompilationFlag<?>[] values() {
        return VALUES;
    }

    // syntax helpers
    private static Predicate<String> pred(Predicate<String> f) {
        return f;
    }

    private static Function<String, String> fun(Function<String, String> f) {
        return f;
    }
}
