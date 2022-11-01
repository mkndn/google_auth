package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AndroidUtilsLight {
    private static volatile int currentVersionCode = -1;

    public static byte[] getNewestAvailablePackageCertificateRawBytes(Context context, String str) {
        if (Build.VERSION.SDK_INT < 28) {
            return getOldestPackageCertificateRawBytes(context, str);
        }
        PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 134217728);
        if (packageInfo == null || packageInfo.signingInfo == null || packageInfo.signingInfo.getApkContentsSigners().length != 1) {
            return null;
        }
        return packageInfo.signingInfo.getApkContentsSigners()[0].toByteArray();
    }

    private static ImmutableList getPackageCertificateHistoryRawBytes(Context context, String str) {
        if (Build.VERSION.SDK_INT < 28) {
            byte[] oldestPackageCertificateRawBytes = getOldestPackageCertificateRawBytes(context, str);
            return oldestPackageCertificateRawBytes != null ? ImmutableList.of((Object) oldestPackageCertificateRawBytes) : ImmutableList.of();
        }
        PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 134217728);
        if (packageInfo == null) {
            return ImmutableList.of();
        }
        SigningInfo signingInfo = packageInfo.signingInfo;
        if (signingInfo != null && !signingInfo.hasMultipleSigners() && signingInfo.getSigningCertificateHistory() != null) {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (Signature signature : signingInfo.getSigningCertificateHistory()) {
                builder.add((Object) signature.toByteArray());
            }
            return builder.build();
        }
        return ImmutableList.of();
    }

    private static byte[] getOldestPackageCertificateRawBytes(Context context, String str) {
        PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
        if (packageInfo.signatures == null || packageInfo.signatures.length != 1) {
            return null;
        }
        return packageInfo.signatures[0].toByteArray();
    }

    public static boolean isPackageSignatureValid(Context context, String str, List list) {
        UnmodifiableIterator it = getPackageCertificateHistoryRawBytes(context, str).reverse().iterator();
        while (it.hasNext()) {
            byte[] bArr = (byte[]) it.next();
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                if (Arrays.equals(bArr, (byte[]) it2.next())) {
                    return true;
                }
            }
        }
        return false;
    }
}
