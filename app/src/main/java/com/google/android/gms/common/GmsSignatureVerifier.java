package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.common.GoogleSourceStampsResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.common.collect.ImmutableList;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import java.util.HashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GmsSignatureVerifier {
    private static final PackageCertificateInfo GMS_PACKAGE_CERT_INFO = PackageCertificateInfo.builder().setPackageName("com.google.android.gms").setMinimumStampedVersionNumber(204200000).setOrderedTestCerts(ImmutableList.of((Object) GoogleCertificates.APP_DEBUG_CERT.getBytes(), (Object) GoogleCertificates.GMS_2020_DEBUG_CERT.getBytes())).setOrderedProdCerts(ImmutableList.of((Object) GoogleCertificates.APP_RELEASE_CERT.getBytes(), (Object) GoogleCertificates.GMS_2020_RELEASE_CERT.getBytes())).build();
    private static final PackageCertificateInfo PLAY_STORE_PACKAGE_CERT_INFO = PackageCertificateInfo.builder().setPackageName("com.android.vending").setMinimumStampedVersionNumber(82240000).setOrderedTestCerts(ImmutableList.of((Object) GoogleCertificates.APP_DEBUG_CERT.getBytes())).setOrderedProdCerts(ImmutableList.of((Object) GoogleCertificates.APP_RELEASE_CERT.getBytes())).build();
    private static final HashMap lastResultByPackage = new HashMap();
    private final Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class CachedResult {
        private final Result result;
        private final long versionCode;

        private CachedResult(long j, Result result) {
            this.versionCode = j;
            this.result = result;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Result {
        private final boolean isAllowed;
        private final SignatureStatus signatureStatus;
        private final GoogleSourceStampsResult.Status stampStatus;

        private Result(boolean z, SignatureStatus signatureStatus, GoogleSourceStampsResult.Status status) {
            this.isAllowed = z;
            this.signatureStatus = signatureStatus;
            this.stampStatus = status;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Result allowed(GoogleSourceStampsResult.Status status) {
            return new Result(true, SignatureStatus.DEFAULT, status);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Result keyNotRecognized() {
            return new Result(false, SignatureStatus.NOT_RECOGNIZED, GoogleSourceStampsResult.Status.DEFAULT);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Result stampFailureNotAllowed(GoogleSourceStampsResult.Status status) {
            return new Result(false, SignatureStatus.DEFAULT, status);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Result testKeySignedAllowed() {
            return new Result(true, SignatureStatus.TEST_KEY_SIGNED_NO_STAMP_CHECK, GoogleSourceStampsResult.Status.STAMP_NOT_NEEDED);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Result testKeySignedNotAllowed() {
            return new Result(false, SignatureStatus.TEST_KEY_SIGNED_BUT_NOT_ALLOWED, GoogleSourceStampsResult.Status.DEFAULT);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Result testKeySignedNotInSystemImage() {
            return new Result(false, SignatureStatus.TEST_KEY_SIGNED_NOT_IN_SYSTEM_IMAGE, GoogleSourceStampsResult.Status.DEFAULT);
        }

        public SignatureStatus getSignatureStatus() {
            return this.signatureStatus;
        }

        public GoogleSourceStampsResult.Status getStampStatus() {
            return this.stampStatus;
        }

        public boolean isAllowed() {
            return this.isAllowed;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum SignatureStatus {
        DEFAULT,
        NOT_RECOGNIZED,
        TEST_KEY_SIGNED_BUT_NOT_ALLOWED,
        TEST_KEY_SIGNED_NO_STAMP_CHECK,
        TEST_KEY_SIGNED_NOT_IN_SYSTEM_IMAGE
    }

    public GmsSignatureVerifier(Context context) {
        Preconditions.checkNotNull(context);
        this.context = context.getApplicationContext();
    }

    private Result isPackageSignedAndStamped(PackageCertificateInfo packageCertificateInfo, boolean z) {
        CachedResult cachedResult;
        Result allowed;
        String packageName = packageCertificateInfo.getPackageName();
        if (!AndroidUtilsLight.isPackageSignatureValid(this.context, packageName, packageCertificateInfo.getOrderedProdCerts())) {
            if (!AndroidUtilsLight.isPackageSignatureValid(this.context, packageName, packageCertificateInfo.getOrderedTestCerts())) {
                return Result.keyNotRecognized();
            }
            if (!z) {
                return Result.testKeySignedNotAllowed();
            }
            if (!isAppInSystemImage(this.context, packageName)) {
                return Result.testKeySignedNotInSystemImage();
            }
            return Result.testKeySignedAllowed();
        }
        long apkVersionCode = getApkVersionCode(this.context, packageName);
        HashMap hashMap = lastResultByPackage;
        synchronized (hashMap) {
            cachedResult = (CachedResult) hashMap.get(packageName);
        }
        if (cachedResult != null && apkVersionCode == cachedResult.versionCode) {
            return cachedResult.result;
        }
        GoogleSourceStampsResult isPackageSourceStamped = getGoogleSourceStampsChecker(packageName, packageCertificateInfo.getMinimumStampedVersionNumber()).isPackageSourceStamped();
        if (!isPackageSourceStamped.isAllowed()) {
            Log.w("GmsSignatureVerifier", "Stamp verification of " + packageName + " failed. Error message: " + isPackageSourceStamped.getErrorMessage());
            allowed = Result.stampFailureNotAllowed(isPackageSourceStamped.getStatus());
        } else {
            allowed = Result.allowed(isPackageSourceStamped.getStatus());
        }
        synchronized (hashMap) {
            hashMap.put(packageName, new CachedResult(apkVersionCode, allowed));
        }
        return allowed;
    }

    GoogleSourceStampsChecker getGoogleSourceStampsChecker(String str, long j) {
        return new GoogleSourceStampsChecker(this.context, str, j);
    }

    public Result isGmsSignedAndStamped(boolean z) {
        return isPackageSignedAndStamped(GMS_PACKAGE_CERT_INFO, z);
    }

    public Result isPlayStoreSignedAndStamped(boolean z) {
        return isPackageSignedAndStamped(PLAY_STORE_PACKAGE_CERT_INFO, z);
    }

    private static long getApkVersionCode(Context context, String str) {
        PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 0);
        if (packageInfo == null) {
            return Long.MAX_VALUE;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            return packageInfo.getLongVersionCode();
        }
        return packageInfo.versionCode;
    }

    private boolean isAppInSystemImage(Context context, String str) {
        ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
        return (applicationInfo == null || (applicationInfo.flags & AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SYSTEM_HEALTH_FUNCTIONAL_DEBUGGING_WW_VALUE) == 0) ? false : true;
    }
}
