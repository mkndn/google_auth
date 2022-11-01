package com.google.common.flogger.backend;

import com.google.common.flogger.LogSite;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MessageUtils {
    static final Locale FORMAT_LOCALE = Locale.ROOT;

    private static void appendHex(StringBuilder sb, long j, boolean z) {
        if (j == 0) {
            sb.append("0");
            return;
        }
        String str = z ? "0123456789ABCDEF" : "0123456789abcdef";
        for (int numberOfLeadingZeros = (63 - Long.numberOfLeadingZeros(j)) & (-4); numberOfLeadingZeros >= 0; numberOfLeadingZeros -= 4) {
            sb.append(str.charAt((int) ((j >>> numberOfLeadingZeros) & 15)));
        }
    }

    public static boolean appendLogSite(LogSite logSite, StringBuilder sb) {
        if (logSite == LogSite.INVALID) {
            return false;
        }
        sb.append(logSite.getClassName()).append('.').append(logSite.getMethodName()).append(':').append(logSite.getLineNumber());
        return true;
    }

    private static String formatErrorMessageFor(Object obj, String str) {
        return "{" + obj.getClass().getName() + "@" + System.identityHashCode(obj) + ": " + str + "}";
    }

    private static String getErrorString(Object obj, RuntimeException runtimeException) {
        String simpleName;
        try {
            simpleName = runtimeException.toString();
        } catch (RuntimeException e) {
            simpleName = e.getClass().getSimpleName();
        }
        return formatErrorMessageFor(obj, simpleName);
    }

    public static void safeFormatTo(Formattable formattable, StringBuilder sb, FormatOptions formatOptions) {
        int flags = formatOptions.getFlags() & AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GBOARD_PRODUCT_IMPROVEMENT_VALUE;
        if (flags != 0) {
            flags = ((flags & 32) != 0 ? 1 : 0) | ((flags & 128) != 0 ? 2 : 0) | ((flags & 2) != 0 ? 4 : 0);
        }
        int length = sb.length();
        Formatter formatter = new Formatter(sb, FORMAT_LOCALE);
        try {
            formattable.formatTo(formatter, flags, formatOptions.getWidth(), formatOptions.getPrecision());
        } catch (RuntimeException e) {
            sb.setLength(length);
            try {
                formatter.out().append(getErrorString(formattable, e));
            } catch (IOException e2) {
            }
        }
    }

    public static String safeToString(Object obj) {
        try {
            return toNonNullString(obj);
        } catch (RuntimeException e) {
            return getErrorString(obj, e);
        }
    }

    private static String toNonNullString(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (!obj.getClass().isArray()) {
            String obj2 = obj.toString();
            return obj2 != null ? obj2 : formatErrorMessageFor(obj, "toString() returned null");
        } else if (obj instanceof int[]) {
            return Arrays.toString((int[]) obj);
        } else {
            if (obj instanceof long[]) {
                return Arrays.toString((long[]) obj);
            }
            if (obj instanceof byte[]) {
                return Arrays.toString((byte[]) obj);
            }
            if (obj instanceof char[]) {
                return Arrays.toString((char[]) obj);
            }
            if (obj instanceof short[]) {
                return Arrays.toString((short[]) obj);
            }
            if (obj instanceof float[]) {
                return Arrays.toString((float[]) obj);
            }
            if (obj instanceof double[]) {
                return Arrays.toString((double[]) obj);
            }
            if (obj instanceof boolean[]) {
                return Arrays.toString((boolean[]) obj);
            }
            return Arrays.toString((Object[]) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void appendHex(StringBuilder sb, Number number, FormatOptions formatOptions) {
        boolean shouldUpperCase = formatOptions.shouldUpperCase();
        long longValue = number.longValue();
        if (number instanceof Long) {
            appendHex(sb, longValue, shouldUpperCase);
        } else if (number instanceof Integer) {
            appendHex(sb, longValue & 4294967295L, shouldUpperCase);
        } else if (number instanceof Byte) {
            appendHex(sb, longValue & 255, shouldUpperCase);
        } else if (number instanceof Short) {
            appendHex(sb, longValue & 65535, shouldUpperCase);
        } else if (number instanceof BigInteger) {
            String bigInteger = ((BigInteger) number).toString(16);
            if (shouldUpperCase) {
                bigInteger = bigInteger.toUpperCase(FORMAT_LOCALE);
            }
            sb.append(bigInteger);
        } else {
            throw new IllegalStateException("unsupported number type: " + number.getClass());
        }
    }
}
