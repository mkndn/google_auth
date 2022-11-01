package com.google.common.flogger.backend.android;

import android.os.Build;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogUtils {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum AndroidLevel {
        ASSERT(7),
        ERROR(6),
        WARN(5),
        INFO(4),
        DEBUG(3),
        VERBOSE(2);
        
        private final int value;

        AndroidLevel(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static AndroidLevel getAndroidLevel(Level level) {
        int intValue = level.intValue();
        if (intValue >= Level.SEVERE.intValue()) {
            return AndroidLevel.ERROR;
        }
        if (intValue >= Level.WARNING.intValue()) {
            return AndroidLevel.WARN;
        }
        if (intValue >= Level.INFO.intValue()) {
            return AndroidLevel.INFO;
        }
        if (intValue >= Level.FINE.intValue()) {
            return AndroidLevel.DEBUG;
        }
        return AndroidLevel.VERBOSE;
    }

    private static int getLastSeparatorIndex(String str) {
        for (int length = str.length() - 1; length >= 0; length--) {
            char charAt = str.charAt(length);
            if (charAt == '.' || charAt == '$') {
                return length;
            }
        }
        return -1;
    }

    public static String getValidTag(String str, String str2, boolean z) {
        String truncateToSimpleClassNameIfTooLong = truncateToSimpleClassNameIfTooLong(str, str2);
        return (z || Build.VERSION.SDK_INT < 26) ? truncateTag(truncateToSimpleClassNameIfTooLong) : truncateToSimpleClassNameIfTooLong;
    }

    public static String truncateTag(String str) {
        return str.substring(0, Math.min(str.length(), 23));
    }

    public static String truncateToSimpleClassNameIfTooLong(String str, String str2) {
        if (str.length() + str2.length() > 23) {
            str2 = str2.substring(getLastSeparatorIndex(str2) + 1);
        }
        return str + str2;
    }
}
