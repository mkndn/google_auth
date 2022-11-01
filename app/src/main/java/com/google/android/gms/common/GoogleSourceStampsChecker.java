package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.util.Log;
import com.android.apksig.SourceStampVerifier;
import com.google.android.gms.common.GoogleSourceStamps;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleSourceStampsChecker {
    private final Context context;
    private final long minPackageVersionWithStamp;
    private final String packageName;
    private SourceStampVerifier testingSourceStampVerifier;

    public GoogleSourceStampsChecker(Context context, String str, long j) {
        this.context = context;
        this.packageName = str;
        this.minPackageVersionWithStamp = j;
    }

    private static byte[] certificateToBytes(X509Certificate x509Certificate) {
        try {
            return x509Certificate.getEncoded();
        } catch (CertificateEncodingException e) {
            Log.w("GoogleSourceStampsChkr", "Unable to encode certificate", e);
            return null;
        }
    }

    private static boolean doesStampMatch(ImmutableList immutableList, ImmutableList immutableList2) {
        UnmodifiableIterator it = immutableList.iterator();
        while (it.hasNext()) {
            byte[] certificateToBytes = certificateToBytes((X509Certificate) it.next());
            if (certificateToBytes == null) {
                Log.w("GoogleSourceStampsChkr", "Unable to encode stamp from X509Certificate");
            } else {
                UnmodifiableIterator it2 = immutableList2.iterator();
                while (it2.hasNext()) {
                    if (Arrays.equals(certificateToBytes, ((GoogleSourceStamps.SourceStamp) it2.next()).getBytes())) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    private ImmutableList getAllStampCertificates(SourceStampVerifier.Result.SourceStampInfo sourceStampInfo) {
        if (sourceStampInfo.getCertificatesInLineage() != null && !sourceStampInfo.getCertificatesInLineage().isEmpty()) {
            return ImmutableList.copyOf((Collection) sourceStampInfo.getCertificatesInLineage());
        }
        if (sourceStampInfo.getCertificate() != null) {
            return ImmutableList.of((Object) sourceStampInfo.getCertificate());
        }
        return ImmutableList.of();
    }

    private static GoogleSourceStampsResult verifyCertsMatch(SourceStampVerifier.Result result, byte[] bArr, ImmutableList immutableList) {
        SourceStampVerifier.Result.SignerInfo signerInfo;
        if (bArr == null) {
            return GoogleSourceStampsResult.genericError("Cannot retrieve certificate from platform.", immutableList);
        }
        if (result.getV2SchemeSigners().size() <= 1 && result.getV3SchemeSigners().size() <= 1) {
            if (!result.getV3SchemeSigners().isEmpty()) {
                signerInfo = (SourceStampVerifier.Result.SignerInfo) result.getV3SchemeSigners().get(0);
            } else if (!result.getV2SchemeSigners().isEmpty()) {
                signerInfo = (SourceStampVerifier.Result.SignerInfo) result.getV2SchemeSigners().get(0);
            } else if (!result.getV1SchemeSigners().isEmpty()) {
                signerInfo = (SourceStampVerifier.Result.SignerInfo) result.getV1SchemeSigners().get(0);
            } else {
                return GoogleSourceStampsResult.apkNotSigned(immutableList);
            }
            byte[] certificateToBytes = certificateToBytes(signerInfo.getSigningCertificate());
            if (certificateToBytes == null) {
                return GoogleSourceStampsResult.genericError("Signing cert cannot be encoded", immutableList);
            }
            if (Arrays.equals(certificateToBytes, bArr)) {
                return GoogleSourceStampsResult.allowed(immutableList);
            }
            return GoogleSourceStampsResult.signingCertMismatch(immutableList);
        }
        return GoogleSourceStampsResult.multipleSignersInvalid(immutableList);
    }

    public GoogleSourceStampsResult isPackageSourceStamped() {
        if (getApkVersionCode(this.context, this.packageName) < this.minPackageVersionWithStamp) {
            return GoogleSourceStampsResult.versionTooLowStampNotChecked();
        }
        SourceStampVerifier sourceStampVerifier = this.testingSourceStampVerifier;
        if (sourceStampVerifier == null) {
            sourceStampVerifier = getSourceStampVerifierForPackage(this.context, this.packageName);
        }
        SourceStampVerifier.Result verifySourceStamp = sourceStampVerifier.verifySourceStamp();
        if (!verifySourceStamp.isVerified()) {
            return GoogleSourceStampsResult.cannotVerify();
        }
        ImmutableList allStampCertificates = getAllStampCertificates(verifySourceStamp.getSourceStampInfo());
        if (!doesStampMatch(allStampCertificates, GoogleSourceStamps.SOURCE_STAMP_LIST)) {
            return GoogleSourceStampsResult.unknownStamp(allStampCertificates);
        }
        return verifyCertsMatch(verifySourceStamp, AndroidUtilsLight.getNewestAvailablePackageCertificateRawBytes(this.context, this.packageName), allStampCertificates);
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

    private static SourceStampVerifier getSourceStampVerifierForPackage(Context context, String str) {
        return new SourceStampVerifier.Builder(new File(Wrappers.packageManager(context).getApplicationInfo(str, 0).sourceDir)).setMinCheckedPlatformVersion(Build.VERSION.SDK_INT).setMaxCheckedPlatformVersion(Build.VERSION.SDK_INT).build();
    }
}
