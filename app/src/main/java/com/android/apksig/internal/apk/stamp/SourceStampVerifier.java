package com.android.apksig.internal.apk.stamp;

import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.internal.apk.ApkSignerInfo;
import com.android.apksig.internal.apk.ApkSigningBlockUtilsLite;
import com.android.apksig.internal.apk.ApkSupportedSignature;
import com.android.apksig.internal.apk.NoApkSupportedSignaturesException;
import com.android.apksig.internal.apk.SignatureAlgorithm;
import com.android.apksig.internal.apk.stamp.SourceStampCertificateLineage;
import com.android.apksig.internal.util.ByteBufferUtils;
import com.android.apksig.internal.util.GuaranteedEncodedFormX509Certificate;
import com.google.android.apps.authenticator.util.CryptoUtils;
import java.io.ByteArrayInputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
class SourceStampVerifier {
    private static void parseStampAttributes(ByteBuffer byteBuffer, X509Certificate x509Certificate, ApkSignerInfo apkSignerInfo) {
        ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
        int i = 0;
        while (lengthPrefixedSlice.hasRemaining()) {
            i++;
            try {
                ByteBuffer lengthPrefixedSlice2 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice);
                int i2 = lengthPrefixedSlice2.getInt();
                byte[] byteArray = ByteBufferUtils.toByteArray(lengthPrefixedSlice2);
                if (i2 == -1654455305) {
                    readStampCertificateLineage(byteArray, x509Certificate, apkSignerInfo);
                } else if (i2 == -465807034) {
                    long j = ByteBuffer.wrap(byteArray).order(ByteOrder.LITTLE_ENDIAN).getLong();
                    if (j > 0) {
                        apkSignerInfo.timestamp = j;
                    } else {
                        apkSignerInfo.addWarning(38, Long.valueOf(j));
                    }
                } else {
                    apkSignerInfo.addWarning(32, Integer.valueOf(i2));
                }
            } catch (ApkFormatException | BufferUnderflowException e) {
                apkSignerInfo.addWarning(31, Integer.valueOf(i));
                return;
            }
        }
    }

    private static X509Certificate verifySourceStampCertificate(ByteBuffer byteBuffer, CertificateFactory certificateFactory, byte[] bArr, ApkSignerInfo apkSignerInfo) {
        byte[] readLengthPrefixedByteArray = ApkSigningBlockUtilsLite.readLengthPrefixedByteArray(byteBuffer);
        try {
            GuaranteedEncodedFormX509Certificate guaranteedEncodedFormX509Certificate = new GuaranteedEncodedFormX509Certificate((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(readLengthPrefixedByteArray)), readLengthPrefixedByteArray);
            apkSignerInfo.certs.add(guaranteedEncodedFormX509Certificate);
            MessageDigest messageDigest = MessageDigest.getInstance(CryptoUtils.DIGEST_SHA_256);
            messageDigest.update(readLengthPrefixedByteArray);
            byte[] digest = messageDigest.digest();
            if (!Arrays.equals(bArr, digest)) {
                apkSignerInfo.addWarning(27, ApkSigningBlockUtilsLite.toHex(digest), ApkSigningBlockUtilsLite.toHex(bArr));
                return null;
            }
            return guaranteedEncodedFormX509Certificate;
        } catch (CertificateException e) {
            apkSignerInfo.addWarning(18, e);
            return null;
        }
    }

    private static void readStampCertificateLineage(byte[] bArr, X509Certificate x509Certificate, ApkSignerInfo apkSignerInfo) {
        try {
            List readSigningCertificateLineage = SourceStampCertificateLineage.readSigningCertificateLineage(ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN));
            for (int i = 0; i < readSigningCertificateLineage.size(); i++) {
                apkSignerInfo.certificateLineage.add(((SourceStampCertificateLineage.SigningCertificateNode) readSigningCertificateLineage.get(i)).signingCert);
            }
            if (!x509Certificate.equals(apkSignerInfo.certificateLineage.get(apkSignerInfo.certificateLineage.size() - 1))) {
                apkSignerInfo.addWarning(34, new Object[0]);
            }
        } catch (IllegalArgumentException e) {
            apkSignerInfo.addWarning(34, new Object[0]);
        } catch (SecurityException e2) {
            apkSignerInfo.addWarning(35, new Object[0]);
        } catch (Exception e3) {
            apkSignerInfo.addWarning(33, new Object[0]);
        }
    }

    private static void verifySourceStampSignature(byte[] bArr, int i, int i2, X509Certificate x509Certificate, ByteBuffer byteBuffer, ApkSignerInfo apkSignerInfo) {
        ArrayList<ApkSupportedSignature> arrayList = new ArrayList(1);
        int i3 = 0;
        while (byteBuffer.hasRemaining()) {
            i3++;
            try {
                ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
                int i4 = lengthPrefixedSlice.getInt();
                byte[] readLengthPrefixedByteArray = ApkSigningBlockUtilsLite.readLengthPrefixedByteArray(lengthPrefixedSlice);
                SignatureAlgorithm findById = SignatureAlgorithm.findById(i4);
                if (findById == null) {
                    apkSignerInfo.addWarning(19, Integer.valueOf(i4));
                } else {
                    arrayList.add(new ApkSupportedSignature(findById, readLengthPrefixedByteArray));
                }
            } catch (ApkFormatException | BufferUnderflowException e) {
                apkSignerInfo.addWarning(20, Integer.valueOf(i3));
                return;
            }
        }
        if (arrayList.isEmpty()) {
            apkSignerInfo.addWarning(17, new Object[0]);
            return;
        }
        try {
            for (ApkSupportedSignature apkSupportedSignature : ApkSigningBlockUtilsLite.getSignaturesToVerify(arrayList, i, i2, true)) {
                SignatureAlgorithm signatureAlgorithm = apkSupportedSignature.algorithm;
                String str = (String) signatureAlgorithm.getJcaSignatureAlgorithmAndParams().getFirst();
                AlgorithmParameterSpec algorithmParameterSpec = (AlgorithmParameterSpec) signatureAlgorithm.getJcaSignatureAlgorithmAndParams().getSecond();
                PublicKey publicKey = x509Certificate.getPublicKey();
                try {
                    Signature signature = Signature.getInstance(str);
                    signature.initVerify(publicKey);
                    if (algorithmParameterSpec != null) {
                        signature.setParameter(algorithmParameterSpec);
                    }
                    signature.update(bArr);
                    if (!signature.verify(apkSupportedSignature.signature)) {
                        apkSignerInfo.addWarning(21, signatureAlgorithm);
                        return;
                    }
                } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException e2) {
                    apkSignerInfo.addWarning(22, signatureAlgorithm, e2);
                    return;
                }
            }
        } catch (NoApkSupportedSignaturesException e3) {
            StringBuilder sb = new StringBuilder();
            for (ApkSupportedSignature apkSupportedSignature2 : arrayList) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(apkSupportedSignature2.algorithm);
            }
            apkSignerInfo.addWarning(26, sb.toString(), e3);
        }
    }

    public static void verifyV2SourceStamp(ByteBuffer byteBuffer, CertificateFactory certificateFactory, ApkSignerInfo apkSignerInfo, Map map, byte[] bArr, int i, int i2) {
        X509Certificate verifySourceStampCertificate = verifySourceStampCertificate(byteBuffer, certificateFactory, bArr, apkSignerInfo);
        if (!apkSignerInfo.containsWarnings() && !apkSignerInfo.containsErrors()) {
            ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
            HashMap hashMap = new HashMap();
            while (lengthPrefixedSlice.hasRemaining()) {
                ByteBuffer lengthPrefixedSlice2 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice);
                int i3 = lengthPrefixedSlice2.getInt();
                hashMap.put(Integer.valueOf(i3), ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice2));
            }
            for (Map.Entry entry : map.entrySet()) {
                if (((Integer) entry.getKey()).intValue() != 31) {
                    if (hashMap.containsKey(entry.getKey())) {
                        verifySourceStampSignature((byte[]) entry.getValue(), i, i2, verifySourceStampCertificate, (ByteBuffer) hashMap.get(entry.getKey()), apkSignerInfo);
                        if (apkSignerInfo.containsWarnings() || apkSignerInfo.containsErrors()) {
                            return;
                        }
                    } else {
                        apkSignerInfo.addWarning(17, new Object[0]);
                        return;
                    }
                }
            }
            if (byteBuffer.hasRemaining()) {
                ByteBuffer lengthPrefixedSlice3 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
                ByteBuffer lengthPrefixedSlice4 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
                byte[] bArr2 = new byte[lengthPrefixedSlice3.remaining()];
                lengthPrefixedSlice3.get(bArr2);
                ByteBuffer byteBuffer2 = (ByteBuffer) lengthPrefixedSlice3.flip();
                verifySourceStampSignature(bArr2, i, i2, verifySourceStampCertificate, lengthPrefixedSlice4, apkSignerInfo);
                if (!apkSignerInfo.containsErrors() && !apkSignerInfo.containsWarnings()) {
                    parseStampAttributes(lengthPrefixedSlice3, verifySourceStampCertificate, apkSignerInfo);
                }
            }
        }
    }
}
