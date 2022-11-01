package com.google.android.apps.authenticator.api;

import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PackageSignatureFingerprintProvider {
    private final MessageDigest mMessageDigestSha256;
    private final PackageManager mPackageManager;

    public PackageSignatureFingerprintProvider(MessageDigest messageDigest, PackageManager packageManager) {
        this.mPackageManager = packageManager;
        this.mMessageDigestSha256 = messageDigest;
    }

    public Set getSigningCertificateFingerprints(String str) {
        Signature[] signatureArr = this.mPackageManager.getPackageInfo(str, 64).signatures;
        HashSet hashSet = new HashSet();
        for (Signature signature : signatureArr) {
            hashSet.add(this.mMessageDigestSha256.digest(signature.toByteArray()));
        }
        return hashSet;
    }
}
