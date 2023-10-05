package io.vproxy.pni.impl;

public class Utils {
    private Utils() {
    }

    public static boolean isPowerOf2(int n) {
        return (n & (n - 1)) == 0;
    }

    public static boolean isPowerOf2(long n) {
        return (n & (n - 1)) == 0;
    }

    public static int smallestPositivePowerOf2GE(int n) {
        if (n <= 0) {
            return 0;
        }
        if (isPowerOf2(n)) {
            return n;
        }
        n = 1 << (32 - Integer.numberOfLeadingZeros(n));
        if (n < 0) {
            return 0x40000000;
        }
        return n;
    }
}
