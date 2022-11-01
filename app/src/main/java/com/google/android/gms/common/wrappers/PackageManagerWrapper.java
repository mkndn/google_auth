package com.google.android.gms.common.wrappers;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import com.google.android.gms.common.util.PlatformVersion;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PackageManagerWrapper {
    protected final Context context;

    public PackageManagerWrapper(Context context) {
        this.context = context;
    }

    public int checkCallingOrSelfPermission(String str) {
        return this.context.checkCallingOrSelfPermission(str);
    }

    public ApplicationInfo getApplicationInfo(String str, int i) {
        return this.context.getPackageManager().getApplicationInfo(str, i);
    }

    public CharSequence getApplicationLabel(String str) {
        return this.context.getPackageManager().getApplicationLabel(this.context.getPackageManager().getApplicationInfo(str, 0));
    }

    public PackageInfo getPackageInfo(String str, int i) {
        return this.context.getPackageManager().getPackageInfo(str, i);
    }

    public boolean uidHasPackageName(int i, String str) {
        if (PlatformVersion.isAtLeastKitKat()) {
            try {
                AppOpsManager appOpsManager = (AppOpsManager) this.context.getSystemService("appops");
                if (appOpsManager == null) {
                    throw new NullPointerException("context.getSystemService(Context.APP_OPS_SERVICE) is null");
                }
                appOpsManager.checkPackage(i, str);
                return true;
            } catch (SecurityException e) {
                return false;
            }
        }
        String[] packagesForUid = this.context.getPackageManager().getPackagesForUid(i);
        if (str != null && packagesForUid != null) {
            for (String str2 : packagesForUid) {
                if (str.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
