package org.joda.time.format;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FormatUtils {
    private static final double LOG_10 = Math.log(10.0d);

    public static void appendPaddedInteger(StringBuffer stringBuffer, int i, int i2) {
        int log;
        if (i < 0) {
            stringBuffer.append('-');
            if (i != Integer.MIN_VALUE) {
                i = -i;
            } else {
                while (i2 > 10) {
                    stringBuffer.append('0');
                    i2--;
                }
                stringBuffer.append("2147483648");
                return;
            }
        }
        if (i < 10) {
            while (i2 > 1) {
                stringBuffer.append('0');
                i2--;
            }
            stringBuffer.append((char) (i + 48));
        } else if (i < 100) {
            while (i2 > 2) {
                stringBuffer.append('0');
                i2--;
            }
            int i3 = ((i + 1) * 13421772) >> 27;
            stringBuffer.append((char) (i3 + 48));
            stringBuffer.append((char) (((i - (i3 << 3)) - (i3 + i3)) + 48));
        } else {
            if (i >= 1000) {
                log = i < 10000 ? 4 : ((int) (Math.log(i) / LOG_10)) + 1;
            } else {
                log = 3;
            }
            while (i2 > log) {
                stringBuffer.append('0');
                i2--;
            }
            stringBuffer.append(Integer.toString(i));
        }
    }

    public static void appendUnpaddedInteger(StringBuffer stringBuffer, int i) {
        if (i < 0) {
            stringBuffer.append('-');
            if (i != Integer.MIN_VALUE) {
                i = -i;
            } else {
                stringBuffer.append("2147483648");
                return;
            }
        }
        if (i < 10) {
            stringBuffer.append((char) (i + 48));
        } else if (i < 100) {
            int i2 = ((i + 1) * 13421772) >> 27;
            stringBuffer.append((char) (i2 + 48));
            stringBuffer.append((char) (((i - (i2 << 3)) - (i2 + i2)) + 48));
        } else {
            stringBuffer.append(Integer.toString(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String createErrorMessage(String str, int i) {
        String concat;
        int i2 = i + 32;
        if (str.length() > i2 + 3) {
            concat = str.substring(0, i2).concat("...");
        } else {
            concat = str;
        }
        if (i <= 0) {
            return new StringBuilder(String.valueOf(concat).length() + 18).append("Invalid format: \"").append(concat).append('\"').toString();
        }
        if (i >= str.length()) {
            return new StringBuilder(String.valueOf(concat).length() + 31).append("Invalid format: \"").append(concat).append("\" is too short").toString();
        }
        String substring = concat.substring(i);
        return new StringBuilder(String.valueOf(concat).length() + 37 + String.valueOf(substring).length()).append("Invalid format: \"").append(concat).append("\" is malformed at \"").append(substring).append('\"').toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int parseTwoDigits(String str, int i) {
        int charAt = str.charAt(i) - '0';
        return (((charAt << 3) + (charAt + charAt)) + str.charAt(i + 1)) - 48;
    }
}
