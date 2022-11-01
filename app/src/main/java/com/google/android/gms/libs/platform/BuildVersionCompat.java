package com.google.android.gms.libs.platform;

import android.os.Build;

/* compiled from: PG */
/* loaded from: classes.dex */
final class BuildVersionCompat {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAtLeastM() {
        return Build.VERSION.SDK_INT >= 23;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAtLeastS() {
        return Build.VERSION.SDK_INT >= 31;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAtLeastT() {
        return Build.VERSION.SDK_INT >= 33 || Build.VERSION.CODENAME.charAt(0) == 'T';
    }
}
