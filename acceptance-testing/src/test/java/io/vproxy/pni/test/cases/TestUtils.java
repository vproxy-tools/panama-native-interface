package io.vproxy.pni.test.cases;

import org.graalvm.nativeimage.ImageInfo;

public class TestUtils {
    private TestUtils() {
    }

    public static boolean skipCase() {
        if (ImageInfo.inImageCode()) {
            System.out.println("TEST SKIPPED -- native image");
            printStack();
            return true;
        }
        return false;
    }

    private static void printStack() {
        var stack = Thread.currentThread().getStackTrace();
        if (stack != null && stack.length >= 3) {
            for (int i = 2; i < stack.length && i < 5; i++) {
                var s = stack[i];
                System.out.println("\t" + s);
            }
        }
    }

    public static void loadLib() {
        try {
            System.loadLibrary("pnitest");
        } catch (Throwable t) {
            System.loadLibrary("graaltest");
        }
    }
}
