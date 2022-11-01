package com.android.apksig.apk;

import com.android.apksig.internal.util.Pair;
import com.android.apksig.internal.zip.ZipUtils;
import com.android.apksig.util.DataSource;
import com.android.apksig.zip.ZipFormatException;
import com.android.apksig.zip.ZipSections;
import com.google.android.apps.authenticator.util.CryptoUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ApkUtilsLite {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ApkSigningBlock {
        private final DataSource mContents;
        private final long mStartOffsetInApk;

        public ApkSigningBlock(long j, DataSource dataSource) {
            this.mStartOffsetInApk = j;
            this.mContents = dataSource;
        }

        public DataSource getContents() {
            return this.mContents;
        }

        public long getStartOffset() {
            return this.mStartOffsetInApk;
        }
    }

    public static byte[] computeSha256DigestBytes(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CryptoUtils.DIGEST_SHA_256);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 is not found", e);
        }
    }

    public static ApkSigningBlock findApkSigningBlock(DataSource dataSource, ZipSections zipSections) {
        long zipCentralDirectoryOffset = zipSections.getZipCentralDirectoryOffset();
        long zipCentralDirectorySizeBytes = zipSections.getZipCentralDirectorySizeBytes() + zipCentralDirectoryOffset;
        long zipEndOfCentralDirectoryOffset = zipSections.getZipEndOfCentralDirectoryOffset();
        if (zipCentralDirectorySizeBytes != zipEndOfCentralDirectoryOffset) {
            throw new ApkSigningBlockNotFoundException("ZIP Central Directory is not immediately followed by End of Central Directory. CD end: " + zipCentralDirectorySizeBytes + ", EoCD start: " + zipEndOfCentralDirectoryOffset);
        }
        if (zipCentralDirectoryOffset < 32) {
            throw new ApkSigningBlockNotFoundException("APK too small for APK Signing Block. ZIP Central Directory offset: " + zipCentralDirectoryOffset);
        }
        ByteBuffer byteBuffer = dataSource.getByteBuffer((-24) + zipCentralDirectoryOffset, 24);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        if (byteBuffer.getLong(8) == 2334950737559900225L && byteBuffer.getLong(16) == 3617552046287187010L) {
            long j = byteBuffer.getLong(0);
            if (j >= byteBuffer.capacity() && j <= 2147483639) {
                long j2 = (int) (8 + j);
                long j3 = zipCentralDirectoryOffset - j2;
                if (j3 < 0) {
                    throw new ApkSigningBlockNotFoundException("APK Signing Block offset out of range: " + j3);
                }
                ByteBuffer byteBuffer2 = dataSource.getByteBuffer(j3, 8);
                byteBuffer2.order(ByteOrder.LITTLE_ENDIAN);
                long j4 = byteBuffer2.getLong(0);
                if (j4 != j) {
                    throw new ApkSigningBlockNotFoundException("APK Signing Block sizes in header and footer do not match: " + j4 + " vs " + j);
                }
                return new ApkSigningBlock(j3, dataSource.slice(j3, j2));
            }
            throw new ApkSigningBlockNotFoundException("APK Signing Block size out of range: " + j);
        }
        throw new ApkSigningBlockNotFoundException("No APK Signing Block before ZIP Central Directory");
    }

    public static ZipSections findZipSections(DataSource dataSource) {
        Pair findZipEndOfCentralDirectoryRecord = ZipUtils.findZipEndOfCentralDirectoryRecord(dataSource);
        if (findZipEndOfCentralDirectoryRecord == null) {
            throw new ZipFormatException("ZIP End of Central Directory record not found");
        }
        ByteBuffer byteBuffer = (ByteBuffer) findZipEndOfCentralDirectoryRecord.getFirst();
        long longValue = ((Long) findZipEndOfCentralDirectoryRecord.getSecond()).longValue();
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        long zipEocdCentralDirectoryOffset = ZipUtils.getZipEocdCentralDirectoryOffset(byteBuffer);
        if (zipEocdCentralDirectoryOffset > longValue) {
            throw new ZipFormatException("ZIP Central Directory start offset out of range: " + zipEocdCentralDirectoryOffset + ". ZIP End of Central Directory offset: " + longValue);
        }
        long zipEocdCentralDirectorySizeBytes = ZipUtils.getZipEocdCentralDirectorySizeBytes(byteBuffer);
        long j = zipEocdCentralDirectoryOffset + zipEocdCentralDirectorySizeBytes;
        if (j > longValue) {
            throw new ZipFormatException("ZIP Central Directory overlaps with End of Central Directory. CD end: " + j + ", EoCD start: " + longValue);
        }
        return new ZipSections(zipEocdCentralDirectoryOffset, zipEocdCentralDirectorySizeBytes, ZipUtils.getZipEocdCentralDirectoryTotalRecordCount(byteBuffer), longValue, byteBuffer);
    }
}
