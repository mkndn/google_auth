package com.android.apksig.internal.apk;

import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.apk.ApkSigningBlockNotFoundException;
import com.android.apksig.apk.ApkUtilsLite;
import com.android.apksig.internal.util.Pair;
import com.android.apksig.util.DataSource;
import com.android.apksig.zip.ZipSections;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ApkSigningBlockUtilsLite {
    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.android.apksig.internal.apk.ApkSigningBlockUtilsLite$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$apksig$internal$apk$ContentDigestAlgorithm;

        static {
            int[] iArr = new int[ContentDigestAlgorithm.values().length];
            $SwitchMap$com$android$apksig$internal$apk$ContentDigestAlgorithm = iArr;
            try {
                iArr[ContentDigestAlgorithm.CHUNKED_SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$apksig$internal$apk$ContentDigestAlgorithm[ContentDigestAlgorithm.CHUNKED_SHA512.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$apksig$internal$apk$ContentDigestAlgorithm[ContentDigestAlgorithm.VERITY_CHUNKED_SHA256.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public static void checkByteOrderLittleEndian(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }

    private static int compareContentDigestAlgorithm(ContentDigestAlgorithm contentDigestAlgorithm, ContentDigestAlgorithm contentDigestAlgorithm2) {
        switch (AnonymousClass1.$SwitchMap$com$android$apksig$internal$apk$ContentDigestAlgorithm[contentDigestAlgorithm.ordinal()]) {
            case 1:
                switch (AnonymousClass1.$SwitchMap$com$android$apksig$internal$apk$ContentDigestAlgorithm[contentDigestAlgorithm2.ordinal()]) {
                    case 1:
                        return 0;
                    case 2:
                    case 3:
                        return -1;
                    default:
                        throw new IllegalArgumentException("Unknown alg2: " + String.valueOf(contentDigestAlgorithm2));
                }
            case 2:
                switch (AnonymousClass1.$SwitchMap$com$android$apksig$internal$apk$ContentDigestAlgorithm[contentDigestAlgorithm2.ordinal()]) {
                    case 1:
                    case 3:
                        return 1;
                    case 2:
                        return 0;
                    default:
                        throw new IllegalArgumentException("Unknown alg2: " + String.valueOf(contentDigestAlgorithm2));
                }
            case 3:
                switch (AnonymousClass1.$SwitchMap$com$android$apksig$internal$apk$ContentDigestAlgorithm[contentDigestAlgorithm2.ordinal()]) {
                    case 1:
                        return 1;
                    case 2:
                        return -1;
                    case 3:
                        return 0;
                    default:
                        throw new IllegalArgumentException("Unknown alg2: " + String.valueOf(contentDigestAlgorithm2));
                }
            default:
                throw new IllegalArgumentException("Unknown alg1: " + String.valueOf(contentDigestAlgorithm));
        }
    }

    public static int compareSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm, SignatureAlgorithm signatureAlgorithm2) {
        return compareContentDigestAlgorithm(signatureAlgorithm.getContentDigestAlgorithm(), signatureAlgorithm2.getContentDigestAlgorithm());
    }

    public static ByteBuffer findApkSignatureSchemeBlock(ByteBuffer byteBuffer, int i) {
        checkByteOrderLittleEndian(byteBuffer);
        ByteBuffer sliceFromTo = sliceFromTo(byteBuffer, 8, byteBuffer.capacity() - 24);
        int i2 = 0;
        while (sliceFromTo.hasRemaining()) {
            i2++;
            if (sliceFromTo.remaining() >= 8) {
                long j = sliceFromTo.getLong();
                if (j >= 4 && j <= 2147483647L) {
                    int i3 = (int) j;
                    int position = sliceFromTo.position() + i3;
                    if (i3 <= sliceFromTo.remaining()) {
                        if (sliceFromTo.getInt() != i) {
                            ByteBuffer byteBuffer2 = (ByteBuffer) sliceFromTo.position(position);
                        } else {
                            return getByteBuffer(sliceFromTo, i3 - 4);
                        }
                    } else {
                        throw new SignatureNotFoundException("APK Signing Block entry #" + i2 + " size out of range: " + i3 + ", available: " + sliceFromTo.remaining());
                    }
                } else {
                    throw new SignatureNotFoundException("APK Signing Block entry #" + i2 + " size out of range: " + j);
                }
            } else {
                throw new SignatureNotFoundException("Insufficient data to read size of APK Signing Block entry #" + i2);
            }
        }
        throw new SignatureNotFoundException("No APK Signature Scheme block in APK Signing Block with ID: " + i);
    }

    public static SignatureInfo findSignature(DataSource dataSource, ZipSections zipSections, int i) {
        try {
            ApkUtilsLite.ApkSigningBlock findApkSigningBlock = ApkUtilsLite.findApkSigningBlock(dataSource, zipSections);
            long startOffset = findApkSigningBlock.getStartOffset();
            DataSource contents = findApkSigningBlock.getContents();
            ByteBuffer byteBuffer = contents.getByteBuffer(0L, (int) contents.size());
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            return new SignatureInfo(findApkSignatureSchemeBlock(byteBuffer, i), startOffset, zipSections.getZipCentralDirectoryOffset(), zipSections.getZipEndOfCentralDirectoryOffset(), zipSections.getZipEndOfCentralDirectory());
        } catch (ApkSigningBlockNotFoundException e) {
            throw new SignatureNotFoundException(e.getMessage(), e);
        }
    }

    private static ByteBuffer getByteBuffer(ByteBuffer byteBuffer, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("size: " + i);
        }
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        int i2 = i + position;
        if (i2 >= position && i2 <= limit) {
            ByteBuffer byteBuffer2 = (ByteBuffer) byteBuffer.limit(i2);
            try {
                ByteBuffer slice = byteBuffer.slice();
                slice.order(byteBuffer.order());
                ByteBuffer byteBuffer3 = (ByteBuffer) byteBuffer.position(i2);
                return slice;
            } finally {
                ByteBuffer byteBuffer4 = (ByteBuffer) byteBuffer.limit(limit);
            }
        }
        throw new BufferUnderflowException();
    }

    public static ByteBuffer getLengthPrefixedSlice(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < 4) {
            throw new ApkFormatException("Remaining buffer too short to contain length of length-prefixed field. Remaining: " + byteBuffer.remaining());
        }
        int i = byteBuffer.getInt();
        if (i < 0) {
            throw new IllegalArgumentException("Negative length");
        }
        if (i > byteBuffer.remaining()) {
            throw new ApkFormatException("Length-prefixed field longer than remaining buffer. Field length: " + i + ", remaining: " + byteBuffer.remaining());
        }
        return getByteBuffer(byteBuffer, i);
    }

    public static List getSignaturesToVerify(List list, int i, int i2, boolean z) {
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        int i3 = Integer.MAX_VALUE;
        while (it.hasNext()) {
            ApkSupportedSignature apkSupportedSignature = (ApkSupportedSignature) it.next();
            SignatureAlgorithm signatureAlgorithm = apkSupportedSignature.algorithm;
            int jcaSigAlgMinSdkVersion = z ? signatureAlgorithm.getJcaSigAlgMinSdkVersion() : signatureAlgorithm.getMinSdkVersion();
            if (jcaSigAlgMinSdkVersion <= i2) {
                if (jcaSigAlgMinSdkVersion < i3) {
                    i3 = jcaSigAlgMinSdkVersion;
                }
                ApkSupportedSignature apkSupportedSignature2 = (ApkSupportedSignature) hashMap.get(Integer.valueOf(jcaSigAlgMinSdkVersion));
                if (apkSupportedSignature2 == null || compareSignatureAlgorithm(signatureAlgorithm, apkSupportedSignature2.algorithm) > 0) {
                    hashMap.put(Integer.valueOf(jcaSigAlgMinSdkVersion), apkSupportedSignature);
                }
            }
        }
        if (i < i3) {
            throw new NoApkSupportedSignaturesException("Minimum provided signature version " + i3 + " > minSdkVersion " + i);
        }
        if (hashMap.isEmpty()) {
            throw new NoApkSupportedSignaturesException("No supported signature");
        }
        ArrayList arrayList = new ArrayList(hashMap.values());
        Collections.sort(arrayList, ApkSigningBlockUtilsLite$$ExternalSyntheticLambda1.INSTANCE);
        return arrayList;
    }

    public static byte[] readLengthPrefixedByteArray(ByteBuffer byteBuffer) {
        int i = byteBuffer.getInt();
        if (i < 0) {
            throw new ApkFormatException("Negative length");
        }
        if (i > byteBuffer.remaining()) {
            throw new ApkFormatException("Underflow while reading length-prefixed value. Length: " + i + ", available: " + byteBuffer.remaining());
        }
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return bArr;
    }

    private static ByteBuffer sliceFromTo(ByteBuffer byteBuffer, int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("start: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("end < start: " + i2 + " < " + i);
        }
        int capacity = byteBuffer.capacity();
        if (i2 > byteBuffer.capacity()) {
            throw new IllegalArgumentException("end > capacity: " + i2 + " > " + capacity);
        }
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        try {
            ByteBuffer byteBuffer2 = (ByteBuffer) byteBuffer.position(0);
            ByteBuffer byteBuffer3 = (ByteBuffer) byteBuffer.limit(i2);
            ByteBuffer byteBuffer4 = (ByteBuffer) byteBuffer.position(i);
            ByteBuffer slice = byteBuffer.slice();
            slice.order(byteBuffer.order());
            return slice;
        } finally {
            ByteBuffer byteBuffer5 = (ByteBuffer) byteBuffer.position(0);
            ByteBuffer byteBuffer6 = (ByteBuffer) byteBuffer.limit(limit);
            ByteBuffer byteBuffer7 = (ByteBuffer) byteBuffer.position(position);
        }
    }

    public static String toHex(byte[] bArr) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder(length + length);
        for (byte b : bArr) {
            char[] cArr = HEX_DIGITS;
            sb.append(cArr[(b & UnsignedBytes.MAX_VALUE) >>> 4]).append(cArr[b & Ascii.SI]);
        }
        return sb.toString();
    }

    public static byte[] encodeAsSequenceOfLengthPrefixedPairsOfIntAndLengthPrefixedBytes(List list) {
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += ((byte[]) ((Pair) it.next()).getSecond()).length + 12;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            Pair pair = (Pair) it2.next();
            byte[] bArr = (byte[]) pair.getSecond();
            allocate.putInt(bArr.length + 8);
            allocate.putInt(((Integer) pair.getFirst()).intValue());
            allocate.putInt(bArr.length);
            allocate.put(bArr);
        }
        return allocate.array();
    }
}
