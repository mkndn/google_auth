package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.IllegalFieldValueException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FieldUtils {
    public static boolean equals(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    public static int safeAdd(int i, int i2) {
        int i3 = i + i2;
        if ((i ^ i3) < 0 && (i ^ i2) >= 0) {
            throw new ArithmeticException(new StringBuilder(61).append("The calculation caused an overflow: ").append(i).append(" + ").append(i2).toString());
        }
        return i3;
    }

    public static long safeMultiply(long j, int i) {
        switch (i) {
            case -1:
                if (j == Long.MIN_VALUE) {
                    throw new ArithmeticException(new StringBuilder(67).append("Multiplication overflows a long: ").append(j).append(" * ").append(i).toString());
                }
                return -j;
            case 0:
                return 0L;
            case 1:
                return j;
            default:
                long j2 = i;
                long j3 = j * j2;
                if (j3 / j2 != j) {
                    throw new ArithmeticException(new StringBuilder(67).append("Multiplication overflows a long: ").append(j).append(" * ").append(i).toString());
                }
                return j3;
        }
    }

    public static int safeToInt(long j) {
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        throw new ArithmeticException(new StringBuilder(48).append("Value cannot fit in an int: ").append(j).toString());
    }

    public static void verifyValueBounds(DateTimeField dateTimeField, int i, int i2, int i3) {
        if (i >= i2 && i <= i3) {
            return;
        }
        throw new IllegalFieldValueException(dateTimeField.getType(), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
    }

    public static long safeAdd(long j, long j2) {
        long j3 = j + j2;
        if ((j ^ j3) < 0 && (j ^ j2) >= 0) {
            throw new ArithmeticException(new StringBuilder(79).append("The calculation caused an overflow: ").append(j).append(" + ").append(j2).toString());
        }
        return j3;
    }

    public static long safeMultiply(long j, long j2) {
        if (j2 == 1) {
            return j;
        }
        if (j == 1) {
            return j2;
        }
        if (j == 0 || j2 == 0) {
            return 0L;
        }
        long j3 = j * j2;
        if (j3 / j2 == j && ((j != Long.MIN_VALUE || j2 != -1) && (j2 != Long.MIN_VALUE || j != -1))) {
            return j3;
        }
        throw new ArithmeticException(new StringBuilder(76).append("Multiplication overflows a long: ").append(j).append(" * ").append(j2).toString());
    }
}
