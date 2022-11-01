package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.util.Log;
import com.google.android.gms.common.GoogleCertificates;
import com.google.android.gms.common.internal.Preconditions;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleSignatureVerifier {
    private static GoogleSignatureVerifier sInstance;
    private final Context mContext;

    public GoogleSignatureVerifier(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static GoogleSignatureVerifier getInstance(Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (sInstance == null) {
                GoogleCertificates.init(context);
                sInstance = new GoogleSignatureVerifier(context);
            }
        }
        return sInstance;
    }

    private static boolean isAppInSystemImage(PackageInfo packageInfo) {
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        return (applicationInfo == null || (applicationInfo.flags & AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SYSTEM_HEALTH_FUNCTIONAL_DEBUGGING_WW_VALUE) == 0) ? false : true;
    }

    private static boolean shouldPerformSystemImageCheck(PackageInfo packageInfo) {
        return packageInfo != null && ("com.android.vending".equals(packageInfo.packageName) || "com.google.android.gms".equals(packageInfo.packageName));
    }

    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (isGooglePublicSignedPackage(packageInfo, false)) {
            return true;
        }
        if (isGooglePublicSignedPackage(packageInfo, true)) {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
        return false;
    }

    GoogleCertificates.CertData verifySignature(PackageInfo packageInfo, GoogleCertificates.CertData... certDataArr) {
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        GoogleCertificates.FullCertData fullCertData = new GoogleCertificates.FullCertData(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < certDataArr.length; i++) {
            if (certDataArr[i].equals(fullCertData)) {
                return certDataArr[i];
            }
        }
        return null;
    }

    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo, boolean z) {
        if (z && shouldPerformSystemImageCheck(packageInfo)) {
            z = isAppInSystemImage(packageInfo);
        }
        if (packageInfo != null && packageInfo.signatures != null) {
            if ((z ? verifySignature(packageInfo, GoogleCertificates.VALID_PUBLIC_SIGNATURES.KEYS) : verifySignature(packageInfo, GoogleCertificates.VALID_PUBLIC_SIGNATURES.KEYS[0])) != null) {
                return true;
            }
        }
        return false;
    }
}
