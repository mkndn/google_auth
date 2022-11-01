package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.android.gms.common.GooglePlayServicesUtilLight;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DeviceProperties {
    public static final int SEVEN_INCH_TABLET_MINIMUM_SCREEN_WIDTH_DP = 600;
    private static Boolean isAuto;
    private static Boolean isChinaWearable;
    private static Boolean isIoT;
    private static Boolean isWearable;

    public static boolean isAuto(Context context) {
        return isAuto(context.getPackageManager());
    }

    public static boolean isChinaWearable(Context context) {
        if (isChinaWearable == null) {
            isChinaWearable = Boolean.valueOf(PlatformVersion.isAtLeastLollipop() && context.getPackageManager().hasSystemFeature("cn.google"));
        }
        return isChinaWearable.booleanValue();
    }

    private static boolean isChinaWearableWithoutPlayStore(Context context) {
        return isChinaWearable(context) && (!PlatformVersion.isAtLeastO() || PlatformVersion.isAtLeastR());
    }

    public static boolean isIoT(Context context) {
        boolean z;
        if (isIoT == null) {
            if (context.getPackageManager().hasSystemFeature("android.hardware.type.iot") || context.getPackageManager().hasSystemFeature("android.hardware.type.embedded")) {
                z = true;
            } else {
                z = false;
            }
            isIoT = Boolean.valueOf(z);
        }
        return isIoT.booleanValue();
    }

    public static boolean isUserBuild() {
        if (GooglePlayServicesUtilLight.sIsTestMode) {
            return GooglePlayServicesUtilLight.sTestIsUserBuild;
        }
        return "user".equals(Build.TYPE);
    }

    public static boolean isWearable(Context context) {
        return isWearable(context.getPackageManager());
    }

    public static boolean isWearableWithoutPlayStore(Context context) {
        return (isWearable(context) && !PlatformVersion.isAtLeastN()) || isChinaWearableWithoutPlayStore(context);
    }

    public static boolean isAuto(PackageManager packageManager) {
        if (isAuto == null) {
            isAuto = Boolean.valueOf(PlatformVersion.isAtLeastO() && packageManager.hasSystemFeature("android.hardware.type.automotive"));
        }
        return isAuto.booleanValue();
    }

    public static boolean isWearable(PackageManager packageManager) {
        if (isWearable == null) {
            isWearable = Boolean.valueOf(PlatformVersion.isAtLeastKitKatWatch() && packageManager.hasSystemFeature("android.hardware.type.watch"));
        }
        return isWearable.booleanValue();
    }
}
