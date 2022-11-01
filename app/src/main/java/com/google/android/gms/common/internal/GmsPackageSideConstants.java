package com.google.android.gms.common.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GmsPackageSideConstants {
    private static boolean isExplicitlyPackageSide = false;
    private static volatile boolean hasBeenRead = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isPackageSide() {
        hasBeenRead = true;
        return isExplicitlyPackageSide;
    }
}
