package com.android.apksig.internal.apk.stamp;

import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.internal.apk.ApkSigResult;
import com.android.apksig.internal.apk.ApkSignerInfo;
import com.android.apksig.internal.apk.ApkSigningBlockUtilsLite;
import com.android.apksig.internal.apk.ContentDigestAlgorithm;
import com.android.apksig.internal.util.Pair;
import com.android.apksig.util.DataSource;
import com.android.apksig.zip.ZipSections;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class V2SourceStampVerifier {
    private static List getApkDigests(Map map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            arrayList.add(Pair.of(Integer.valueOf(((ContentDigestAlgorithm) entry.getKey()).getId()), (byte[]) entry.getValue()));
        }
        Collections.sort(arrayList, new Comparator() { // from class: com.android.apksig.internal.apk.stamp.V2SourceStampVerifier.1
            @Override // java.util.Comparator
            public int compare(Pair pair, Pair pair2) {
                return ((Integer) pair.getFirst()).intValue() - ((Integer) pair2.getFirst()).intValue();
            }
        });
        return arrayList;
    }

    private static Map getSignatureSchemeDigests(Map map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            hashMap.put((Integer) entry.getKey(), ApkSigningBlockUtilsLite.encodeAsSequenceOfLengthPrefixedPairsOfIntAndLengthPrefixedBytes(getApkDigests((Map) entry.getValue())));
        }
        return hashMap;
    }

    public static ApkSigResult verify(DataSource dataSource, ZipSections zipSections, byte[] bArr, Map map, int i, int i2) {
        ApkSigResult apkSigResult = new ApkSigResult(0);
        verify(ApkSigningBlockUtilsLite.findSignature(dataSource, zipSections, 1845461005).signatureBlock, bArr, map, i, i2, apkSigResult);
        return apkSigResult;
    }

    private static void verify(ByteBuffer byteBuffer, byte[] bArr, Map map, int i, int i2, ApkSigResult apkSigResult) {
        ApkSignerInfo apkSignerInfo = new ApkSignerInfo();
        apkSigResult.mSigners.add(apkSignerInfo);
        try {
            SourceStampVerifier.verifyV2SourceStamp(ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer), CertificateFactory.getInstance("X.509"), apkSignerInfo, getSignatureSchemeDigests(map), bArr, i, i2);
            apkSigResult.verified = (apkSigResult.containsErrors() || apkSigResult.containsWarnings()) ? false : true;
        } catch (ApkFormatException e) {
            apkSignerInfo.addWarning(20, new Object[0]);
        } catch (BufferUnderflowException e2) {
            apkSignerInfo.addWarning(20, new Object[0]);
        } catch (CertificateException e3) {
            throw new IllegalStateException("Failed to obtain X.509 CertificateFactory", e3);
        }
    }
}
