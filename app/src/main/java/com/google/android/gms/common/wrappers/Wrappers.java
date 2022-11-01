package com.google.android.gms.common.wrappers;

import android.content.Context;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Wrappers {
    private static Wrappers sWrappers = new Wrappers();
    private PackageManagerWrapper mPackageManagerWrapper = null;

    public static PackageManagerWrapper packageManager(Context context) {
        return sWrappers.getPackageManagerWrapper(context);
    }

    public synchronized PackageManagerWrapper getPackageManagerWrapper(Context context) {
        if (this.mPackageManagerWrapper == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.mPackageManagerWrapper = new PackageManagerWrapper(context);
        }
        return this.mPackageManagerWrapper;
    }
}
