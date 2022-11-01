package com.google.android.libraries.consentverifier.logging;

import android.content.Context;
import android.content.pm.PackageManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AppInfoHelper {
    private Integer versionCode;

    private void populateValues(Context context) {
        try {
            this.versionCode = Integer.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            this.versionCode = -1;
        }
    }

    public int getVersionCode(Context context) {
        if (this.versionCode == null) {
            populateValues(context);
        }
        return this.versionCode.intValue();
    }
}
