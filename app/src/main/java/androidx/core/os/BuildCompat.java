package androidx.core.os;

import android.os.Build;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BuildCompat {
    protected static boolean isAtLeastPreReleaseCodename(String str, String str2) {
        return !"REL".equals(str2) && str2.toUpperCase(Locale.ROOT).compareTo(str.toUpperCase(Locale.ROOT)) >= 0;
    }

    @Deprecated
    public static boolean isAtLeastR() {
        return Build.VERSION.SDK_INT >= 30;
    }

    public static boolean isAtLeastT() {
        if (Build.VERSION.SDK_INT < 33 && (Build.VERSION.SDK_INT < 32 || !isAtLeastPreReleaseCodename("Tiramisu", Build.VERSION.CODENAME))) {
            return false;
        }
        return true;
    }
}
