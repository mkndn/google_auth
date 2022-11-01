package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import com.google.android.gms.common.config.GservicesValue;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.android.gms.common.wrappers.Wrappers;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ClientLibraryUtils {
    public static final int GMS_CLIENT_VERSION_UNKNOWN = -1;

    public static boolean isPackageSide() {
        return BuildConstants.IS_PACKAGE_SIDE && GservicesValue.isInitialized() && GservicesValue.getSharedUserId() == Process.myUid();
    }

    public static boolean isPackageStopped(Context context, String str) {
        if ("com.google.android.gms".equals(str) && isPackageSide()) {
            return false;
        }
        try {
            return (Wrappers.packageManager(context).getApplicationInfo(str, 0).flags & 2097152) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
