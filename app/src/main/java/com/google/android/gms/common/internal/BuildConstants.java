package com.google.android.gms.common.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BuildConstants {
    public static final int JAR_BUILD_VERSION_CODE = 222806000;
    public static final long APK_BUILD_TIMESTAMP = timestamp();
    public static final int APK_BUILD_VERSION_CODE = jarBuildVersionCode();
    public static final String APK_BUILD_VERSION_NAME = jarBuildVersionName();
    public static final boolean IS_PACKAGE_SIDE = GmsPackageSideConstants.isPackageSide();
    public static final boolean IS_ESPRESSO_TEST = isEspressoTest();
    public static final boolean APK_IS_PROD_APK = apkIsProdApk();
    public static final boolean APK_IS_PROD_LMP_APK = apkIsProdLmpApk();
    public static final boolean APK_IS_PROD_MNC_APK = apkIsProdMncApk();
    public static final boolean APK_IS_PROD_PI_APK = apkIsProdPiApk();
    public static final boolean APK_IS_PROD_GO_APK = apkIsProdGoApk();
    public static final boolean APK_IS_PROD_GO_PRE_R_APK = apkIsProdGoPreRApk();
    public static final boolean APK_IS_PROD_GO_R_APK = apkIsProdGoRApk();
    public static final boolean APK_IS_PROD_GO_S_APK = apkIsProdGoSApk();
    public static final boolean APK_IS_PROD_NEXT_APK = apkIsProdNextApk();
    public static final boolean APK_IS_PROD_RVC_APK = apkIsProdRvcApk();
    public static final boolean APK_IS_PROD_SC_APK = apkIsProdScApk();
    public static final boolean APK_IS_TV_APK = apkIsTvApk();
    public static final boolean APK_IS_TV_PRE_R_APK = apkIsTvPreRApk();
    public static final boolean APK_IS_TV_R_APK = apkIsTvRApk();
    public static final boolean APK_IS_WEARABLE_APK = apkIsWearableApk();
    public static final boolean APK_IS_AUTO_APK = apkIsAuto();
    public static final boolean APK_IS_BSTAR_APK = apkIsBStar();
    public static final boolean APK_IS_DEBUG_APK = apkIsDebugApk();

    private static final boolean apkIsAuto() {
        return false;
    }

    private static final boolean apkIsBStar() {
        return false;
    }

    private static final boolean apkIsDebugApk() {
        return false;
    }

    private static final boolean apkIsProdApk() {
        return false;
    }

    private static final boolean apkIsProdGoApk() {
        return false;
    }

    private static final boolean apkIsProdGoPreRApk() {
        return false;
    }

    private static final boolean apkIsProdGoRApk() {
        return false;
    }

    private static final boolean apkIsProdGoSApk() {
        return false;
    }

    private static final boolean apkIsProdLmpApk() {
        return true;
    }

    private static final boolean apkIsProdMncApk() {
        return false;
    }

    private static final boolean apkIsProdNextApk() {
        return false;
    }

    private static final boolean apkIsProdPiApk() {
        return false;
    }

    private static final boolean apkIsProdRvcApk() {
        return false;
    }

    private static final boolean apkIsProdScApk() {
        return false;
    }

    private static final boolean apkIsTvApk() {
        return false;
    }

    private static final boolean apkIsTvPreRApk() {
        return false;
    }

    private static final boolean apkIsTvRApk() {
        return false;
    }

    private static final boolean apkIsWearableApk() {
        return false;
    }

    private static final boolean isEspressoTest() {
        return false;
    }

    private static final int jarBuildVersionCode() {
        return JAR_BUILD_VERSION_CODE;
    }

    private static final String jarBuildVersionName() {
        return "22.28.06-000";
    }

    private static final long timestamp() {
        return 1657263600000L;
    }
}
