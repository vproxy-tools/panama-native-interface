package io.vproxy.pni.impl;

public class Utils {
    private Utils() {
    }

    public static int smallestPositivePowerOf2GE(int n) {
        if (n <= 0) {
            return 0;
        }
        if ((n & (n - 1)) == 0) {
            return n;
        }
        n = 1 << (32 - Integer.numberOfLeadingZeros(n));
        if (n < 0) {
            return 0x40000000;
        }
        return n;
    }
}
