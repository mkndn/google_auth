package com.google.android.gms.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.google.android.gms.common.GmsSignatureVerifier;
import com.google.android.gms.common.GoogleSourceStampsResult;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.android.gms.common.internal.MetadataValueReader;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.GmsVersionParser;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GooglePlayServicesUtilLight {
    public static final int MAX_ATTEMPTS_NO_SUCH_ALGORITHM = 2;
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = getGooglePlayServicesVersionCode();
    public static boolean sIsTestMode = false;
    public static boolean sTestIsUserBuild = false;
    private static boolean sIsTestKeys = false;
    static boolean sCachedFieldsPopulated = false;
    static final AtomicBoolean sCanceledAvailabilityNotification = new AtomicBoolean();
    private static final AtomicBoolean sUsingApkIndependentContext = new AtomicBoolean();
    private static boolean explicitlyVerifySourceStamp = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.gms.common.GooglePlayServicesUtilLight$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$gms$common$GmsSignatureVerifier$SignatureStatus;
        static final /* synthetic */ int[] $SwitchMap$com$google$android$gms$common$GoogleSourceStampsResult$Status;

        static {
            int[] iArr = new int[GoogleSourceStampsResult.Status.values().length];
            $SwitchMap$com$google$android$gms$common$GoogleSourceStampsResult$Status = iArr;
            try {
                iArr[GoogleSourceStampsResult.Status.NO_STAMP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$gms$common$GoogleSourceStampsResult$Status[GoogleSourceStampsResult.Status.UNKNOWN_STAMP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$gms$common$GoogleSourceStampsResult$Status[GoogleSourceStampsResult.Status.CANNOT_VERIFY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$gms$common$GoogleSourceStampsResult$Status[GoogleSourceStampsResult.Status.MULTIPLE_SIGNERS_INVALID.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$gms$common$GoogleSourceStampsResult$Status[GoogleSourceStampsResult.Status.SIGNING_CERT_MISMATCH.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$android$gms$common$GoogleSourceStampsResult$Status[GoogleSourceStampsResult.Status.GENERIC_ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$android$gms$common$GoogleSourceStampsResult$Status[GoogleSourceStampsResult.Status.APK_NOT_SIGNED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            int[] iArr2 = new int[GmsSignatureVerifier.SignatureStatus.values().length];
            $SwitchMap$com$google$android$gms$common$GmsSignatureVerifier$SignatureStatus = iArr2;
            try {
                iArr2[GmsSignatureVerifier.SignatureStatus.TEST_KEY_SIGNED_NO_STAMP_CHECK.ordinal()] = 1;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$android$gms$common$GmsSignatureVerifier$SignatureStatus[GmsSignatureVerifier.SignatureStatus.NOT_RECOGNIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$android$gms$common$GmsSignatureVerifier$SignatureStatus[GmsSignatureVerifier.SignatureStatus.TEST_KEY_SIGNED_NOT_IN_SYSTEM_IMAGE.ordinal()] = 3;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class CannotVerifyStampException extends Exception {
        private CannotVerifyStampException() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class CertMismatchStampException extends Exception {
        private CertMismatchStampException() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class GenericErrorStampException extends Exception {
        private GenericErrorStampException() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class MultipleSignersStampException extends Exception {
        private MultipleSignersStampException() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class NoStampException extends Exception {
        private NoStampException() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class NotRecognizedSignatureException extends Exception {
        private NotRecognizedSignatureException() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class NotSignedStampException extends Exception {
        private NotSignedStampException() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class TestKeySignedNoStampSignatureException extends Exception {
        private TestKeySignedNoStampSignatureException() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class TestKeySignedNotInSystemImageSignatureException extends Exception {
        private TestKeySignedNotInSystemImageSignatureException() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class UnknownStampException extends Exception {
        private UnknownStampException() {
        }
    }

    static int checkPackages(Context context, boolean z, int i) {
        return checkPackages(context, z, i, null);
    }

    @Deprecated
    public static void ensurePlayServicesAvailable(Context context, int i) {
        int isGooglePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, i);
        if (isGooglePlayServicesAvailable != 0) {
            Intent errorResolutionIntent = GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(context, isGooglePlayServicesAvailable, "e");
            Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + isGooglePlayServicesAvailable);
            if (errorResolutionIntent == null) {
                throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
            }
            throw new GooglePlayServicesRepairableException(isGooglePlayServicesAvailable, "Google Play Services not available", errorResolutionIntent);
        }
    }

    @Deprecated
    public static int getApkVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }

    @Deprecated
    public static String getErrorString(int i) {
        return ConnectionResult.getStatusString(i);
    }

    private static int getGooglePlayServicesVersionCode() {
        return BuildConstants.JAR_BUILD_VERSION_CODE;
    }

    public static Resources getRemoteResource(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static boolean honorsDebugCertificates(Context context) {
        return isTestKeysBuild(context) || !isUserBuildDevice();
    }

    private static void initializeCachedFields(Context context) {
        try {
            try {
                PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo("com.google.android.gms", 64);
                GoogleSignatureVerifier googleSignatureVerifier = GoogleSignatureVerifier.getInstance(context);
                if (packageInfo != null && !googleSignatureVerifier.isGooglePublicSignedPackage(packageInfo, false) && googleSignatureVerifier.isGooglePublicSignedPackage(packageInfo, true)) {
                    sIsTestKeys = true;
                } else {
                    sIsTestKeys = false;
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", e);
            }
        } finally {
            sCachedFieldsPopulated = true;
        }
    }

    private static void internalLogFailureResultToDropbox(Context context, GmsSignatureVerifier.Result result) {
        if (result.isAllowed()) {
            return;
        }
        switch (AnonymousClass1.$SwitchMap$com$google$android$gms$common$GmsSignatureVerifier$SignatureStatus[result.getSignatureStatus().ordinal()]) {
            case 1:
                uploadPackageSideExceptionToDropbox(context, new TestKeySignedNoStampSignatureException());
                break;
            case 2:
                uploadPackageSideExceptionToDropbox(context, new NotRecognizedSignatureException());
                break;
            case 3:
                uploadPackageSideExceptionToDropbox(context, new TestKeySignedNotInSystemImageSignatureException());
                break;
        }
        switch (AnonymousClass1.$SwitchMap$com$google$android$gms$common$GoogleSourceStampsResult$Status[result.getStampStatus().ordinal()]) {
            case 1:
                uploadPackageSideExceptionToDropbox(context, new NoStampException());
                return;
            case 2:
                uploadPackageSideExceptionToDropbox(context, new UnknownStampException());
                return;
            case 3:
                uploadPackageSideExceptionToDropbox(context, new CannotVerifyStampException());
                return;
            case 4:
                uploadPackageSideExceptionToDropbox(context, new MultipleSignersStampException());
                return;
            case 5:
                uploadPackageSideExceptionToDropbox(context, new CertMismatchStampException());
                return;
            case 6:
                uploadPackageSideExceptionToDropbox(context, new GenericErrorStampException());
                return;
            case 7:
                uploadPackageSideExceptionToDropbox(context, new NotSignedStampException());
                return;
            default:
                return;
        }
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context, int i) {
        boolean z = false;
        if (BuildConstants.IS_PACKAGE_SIDE) {
            return 0;
        }
        try {
            context.getResources().getString(R$string.common_google_play_services_unknown_issue);
        } catch (Throwable th) {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if (!"com.google.android.gms".equals(context.getPackageName())) {
            verifyVersionDeclaredInAppMetadata(context);
        }
        if (!DeviceProperties.isWearableWithoutPlayStore(context) && !DeviceProperties.isIoT(context)) {
            z = true;
        }
        if (verifySourceStamp()) {
            return checkPackages(context, z, i, new GmsSignatureVerifier(context));
        }
        return checkPackages(context, z, i);
    }

    @Deprecated
    public static boolean isPlayServicesPossiblyUpdating(Context context, int i) {
        if (i == 18) {
            return true;
        }
        if (i == 1) {
            return isUninstalledAppPossiblyUpdating(context, "com.google.android.gms");
        }
        return false;
    }

    public static boolean isRestrictedUserProfile(Context context) {
        Bundle applicationRestrictions;
        return PlatformVersion.isAtLeastJellyBeanMR2() && (applicationRestrictions = ((UserManager) Preconditions.checkNotNull(context.getSystemService("user"))).getApplicationRestrictions(context.getPackageName())) != null && "true".equals(applicationRestrictions.getString("restricted_profile"));
    }

    public static boolean isTestKeysBuild(Context context) {
        maybeInitializeCachedFields(context);
        return sIsTestKeys;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isUninstalledAppPossiblyUpdating(Context context, String str) {
        boolean equals = str.equals("com.google.android.gms");
        if (equals && ClientLibraryUtils.isPackageSide()) {
            return false;
        }
        if (PlatformVersion.isAtLeastLollipop()) {
            try {
                for (PackageInstaller.SessionInfo sessionInfo : context.getPackageManager().getPackageInstaller().getAllSessions()) {
                    if (str.equals(sessionInfo.getAppPackageName())) {
                        return true;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            if (equals) {
                return applicationInfo.enabled;
            }
            return applicationInfo.enabled && !isRestrictedUserProfile(context);
        } catch (PackageManager.NameNotFoundException e2) {
            return false;
        }
    }

    @Deprecated
    public static boolean isUserBuildDevice() {
        return DeviceProperties.isUserBuild();
    }

    @Deprecated
    public static boolean isUserRecoverableError(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 9:
                return true;
            default:
                return false;
        }
    }

    private static void logErrorMessage(String str, String str2, GmsSignatureVerifier.Result result) {
        if (result.getSignatureStatus() == GmsSignatureVerifier.SignatureStatus.NOT_RECOGNIZED) {
            Log.w("GooglePlayServicesUtil", str + " requires " + str2 + ", but its signature is invalid.");
        } else if (result.getStampStatus() != GoogleSourceStampsResult.Status.NO_STAMP && result.getStampStatus() != GoogleSourceStampsResult.Status.UNKNOWN_STAMP) {
            String valueOf = String.valueOf(result.getSignatureStatus());
            Log.w("GooglePlayServicesUtil", str + " requires " + str2 + ", but there is a problem with its signature or stamp. SignatureStatus: " + valueOf + " StampStatus: " + String.valueOf(result.getStampStatus()));
        } else {
            Log.w("GooglePlayServicesUtil", str + " requires " + str2 + ", but its source stamp is invalid.");
        }
    }

    private static void maybeInitializeCachedFields(Context context) {
        if (!sCachedFieldsPopulated) {
            initializeCachedFields(context);
        }
    }

    private static void uploadPackageSideExceptionToDropbox(Context context, Exception exc) {
        if (ClientLibraryUtils.isPackageSide()) {
            return;
        }
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 3; i++) {
            secureRandom.nextDouble();
        }
        if (secureRandom.nextDouble() < 0.01d) {
            CrashUtils.addDynamiteErrorToDropBox(context, exc);
        }
    }

    private static boolean verifySourceStamp() {
        return explicitlyVerifySourceStamp;
    }

    private static void verifyVersionDeclaredInAppMetadata(Context context) {
        if (sUsingApkIndependentContext.get()) {
            return;
        }
        int googlePlayServicesVersion = MetadataValueReader.getGooglePlayServicesVersion(context);
        if (googlePlayServicesVersion == 0) {
            throw new GooglePlayServicesMissingManifestValueException();
        }
        if (googlePlayServicesVersion != GOOGLE_PLAY_SERVICES_VERSION_CODE) {
            throw new GooglePlayServicesIncorrectManifestValueException(googlePlayServicesVersion);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0183  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static int checkPackages(Context context, boolean z, int i, GmsSignatureVerifier gmsSignatureVerifier) {
        PackageInfo packageInfo;
        boolean z2;
        Preconditions.checkArgument(i >= 0);
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        if (z) {
            try {
                packageInfo = packageManager.getPackageInfo("com.android.vending", 8256);
            } catch (PackageManager.NameNotFoundException e) {
                Log.w("GooglePlayServicesUtil", packageName + " requires the Google Play Store, but it is missing.");
                return 9;
            }
        } else {
            packageInfo = null;
        }
        try {
            PackageInfo packageInfo2 = packageManager.getPackageInfo("com.google.android.gms", 64);
            if (gmsSignatureVerifier != null) {
                try {
                    try {
                        GmsSignatureVerifier.Result isGmsSignedAndStamped = gmsSignatureVerifier.isGmsSignedAndStamped(true);
                        if (!isGmsSignedAndStamped.isAllowed()) {
                            logErrorMessage(packageName, "com.google.android.gms", isGmsSignedAndStamped);
                            internalLogFailureResultToDropbox(context, isGmsSignedAndStamped);
                            if (sIsTestMode) {
                                return 9;
                            }
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                    } catch (PackageManager.NameNotFoundException e2) {
                        Log.w("GooglePlayServicesUtil", packageName + " requires Google Play services, but they are missing.");
                        uploadPackageSideExceptionToDropbox(context, e2);
                        if (sIsTestMode) {
                            return 1;
                        }
                        z2 = true;
                    }
                    if (!z2 && z) {
                        try {
                            GmsSignatureVerifier.Result isPlayStoreSignedAndStamped = gmsSignatureVerifier.isPlayStoreSignedAndStamped(true);
                            if (!isPlayStoreSignedAndStamped.isAllowed()) {
                                logErrorMessage(packageName, "com.android.vending", isPlayStoreSignedAndStamped);
                                internalLogFailureResultToDropbox(context, isPlayStoreSignedAndStamped);
                                if (sIsTestMode) {
                                    return 9;
                                }
                                z2 = true;
                            }
                        } catch (PackageManager.NameNotFoundException e3) {
                            Log.w("GooglePlayServicesUtil", packageName + " requires the Google Play Store, but it is missing.");
                            uploadPackageSideExceptionToDropbox(context, e3);
                            if (sIsTestMode) {
                                return 9;
                            }
                            z2 = true;
                        }
                    }
                } catch (RuntimeException e4) {
                    if (ClientLibraryUtils.isPackageSide()) {
                        throw e4;
                    }
                    uploadPackageSideExceptionToDropbox(context, e4);
                }
                if (z2) {
                    GoogleSignatureVerifier googleSignatureVerifier = GoogleSignatureVerifier.getInstance(context);
                    if (!googleSignatureVerifier.isGooglePublicSignedPackage(packageInfo2, true)) {
                        Log.w("GooglePlayServicesUtil", packageName + " requires Google Play services, but their signature is invalid.");
                        return 9;
                    } else if (z && !googleSignatureVerifier.isGooglePublicSignedPackage((PackageInfo) Preconditions.checkNotNull(packageInfo), true)) {
                        Log.w("GooglePlayServicesUtil", packageName + " requires Google Play Store, but its signature is invalid.");
                        return 9;
                    }
                }
                if (!z && packageInfo != null && !packageInfo.signatures[0].equals(packageInfo2.signatures[0])) {
                    Log.w("GooglePlayServicesUtil", packageName + " requires Google Play Store, but its signature doesn't match that of Google Play services.");
                    return 9;
                } else if (GmsVersionParser.parseBuildVersion(packageInfo2.versionCode) >= GmsVersionParser.parseBuildVersion(i)) {
                    Log.w("GooglePlayServicesUtil", "Google Play services out of date for " + packageName + ".  Requires " + i + " but found " + packageInfo2.versionCode);
                    return 2;
                } else {
                    ApplicationInfo applicationInfo = packageInfo2.applicationInfo;
                    if (applicationInfo == null) {
                        try {
                            applicationInfo = packageManager.getApplicationInfo("com.google.android.gms", 0);
                        } catch (PackageManager.NameNotFoundException e5) {
                            Log.wtf("GooglePlayServicesUtil", packageName + " requires Google Play services, but they're missing when getting application info.", e5);
                            return 1;
                        }
                    }
                    return !applicationInfo.enabled ? 3 : 0;
                }
            }
            z2 = true;
            if (z2) {
            }
            if (!z) {
            }
            if (GmsVersionParser.parseBuildVersion(packageInfo2.versionCode) >= GmsVersionParser.parseBuildVersion(i)) {
            }
        } catch (PackageManager.NameNotFoundException e6) {
            Log.w("GooglePlayServicesUtil", packageName + " requires Google Play services, but they are missing.");
            return 1;
        }
    }
}
