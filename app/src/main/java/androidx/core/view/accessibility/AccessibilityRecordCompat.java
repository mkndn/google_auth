package androidx.core.view.accessibility;

import android.os.Build;
import android.view.accessibility.AccessibilityRecord;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AccessibilityRecordCompat {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class Api15Impl {
        static int getMaxScrollX(AccessibilityRecord accessibilityRecord) {
            return accessibilityRecord.getMaxScrollX();
        }

        static int getMaxScrollY(AccessibilityRecord accessibilityRecord) {
            return accessibilityRecord.getMaxScrollY();
        }

        static void setMaxScrollX(AccessibilityRecord accessibilityRecord, int i) {
            accessibilityRecord.setMaxScrollX(i);
        }

        static void setMaxScrollY(AccessibilityRecord accessibilityRecord, int i) {
            accessibilityRecord.setMaxScrollY(i);
        }
    }

    public static void setMaxScrollX(AccessibilityRecord accessibilityRecord, int i) {
        if (Build.VERSION.SDK_INT >= 15) {
            Api15Impl.setMaxScrollX(accessibilityRecord, i);
        }
    }

    public static void setMaxScrollY(AccessibilityRecord accessibilityRecord, int i) {
        if (Build.VERSION.SDK_INT >= 15) {
            Api15Impl.setMaxScrollY(accessibilityRecord, i);
        }
    }

    @Deprecated
    public boolean equals(Object obj) {
        throw null;
    }

    @Deprecated
    public int hashCode() {
        throw null;
    }
}
