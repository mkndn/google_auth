package com.google.common.math;

/* compiled from: PG */
/* loaded from: classes.dex */
final class MathPreconditions {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int checkPositive(String str, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(str + " (" + i + ") must be > 0");
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkRoundingUnnecessary(boolean z) {
        if (!z) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }
}
