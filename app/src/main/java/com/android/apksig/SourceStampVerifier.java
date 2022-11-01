package com.android.apksig;

import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.apk.ApkUtilsLite;
import com.android.apksig.internal.apk.ApkSigResult;
import com.android.apksig.internal.apk.ApkSignerInfo;
import com.android.apksig.internal.apk.ApkSigningBlockUtilsLite;
import com.android.apksig.internal.apk.ContentDigestAlgorithm;
import com.android.apksig.internal.apk.SignatureAlgorithm;
import com.android.apksig.internal.apk.SignatureInfo;
import com.android.apksig.internal.apk.SignatureNotFoundException;
import com.android.apksig.internal.apk.stamp.V2SourceStampVerifier;
import com.android.apksig.internal.util.GuaranteedEncodedFormX509Certificate;
import com.android.apksig.internal.zip.CentralDirectoryRecord;
import com.android.apksig.internal.zip.LocalFileRecord;
import com.android.apksig.internal.zip.ZipUtils;
import com.android.apksig.util.DataSource;
import com.android.apksig.util.DataSources;
import com.android.apksig.zip.ZipFormatException;
import com.android.apksig.zip.ZipSections;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SourceStampVerifier {
    private final DataSource mApkDataSource;
    private final File mApkFile;
    private final int mMaxSdkVersion;
    private final int mMinSdkVersion;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder {
        private final DataSource mApkDataSource;
        private final File mApkFile;
        private int mMinSdkVersion = 1;
        private int mMaxSdkVersion = Integer.MAX_VALUE;

        public Builder(File file) {
            if (file == null) {
                throw new NullPointerException("apk == null");
            }
            this.mApkFile = file;
            this.mApkDataSource = null;
        }

        public SourceStampVerifier build() {
            return new SourceStampVerifier(this.mApkFile, this.mApkDataSource, this.mMinSdkVersion, this.mMaxSdkVersion);
        }

        public Builder setMaxCheckedPlatformVersion(int i) {
            this.mMaxSdkVersion = i;
            return this;
        }

        public Builder setMinCheckedPlatformVersion(int i) {
            this.mMinSdkVersion = i;
            return this;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Result {
        private final List mAllSchemeSigners;
        private final List mErrors;
        private SourceStampInfo mSourceStampInfo;
        private final List mV1SchemeSigners;
        private final List mV2SchemeSigners;
        private final List mV3SchemeSigners;
        private boolean mVerified;
        private final List mWarnings;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class SignerInfo {
            private X509Certificate mSigningCertificate;
            private final List mErrors = new ArrayList();
            private final List mWarnings = new ArrayList();

            void addVerificationWarning(int i, Object... objArr) {
                this.mWarnings.add(new ApkVerificationIssue(i, objArr));
            }

            public X509Certificate getSigningCertificate() {
                return this.mSigningCertificate;
            }

            void setSigningCertificate(X509Certificate x509Certificate) {
                this.mSigningCertificate = x509Certificate;
            }
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class SourceStampInfo {
            private final List mCertificateLineage;
            private final List mCertificates;
            private final List mErrors;
            private final long mTimestamp;
            private final List mWarnings;

            private SourceStampInfo(ApkSignerInfo apkSignerInfo) {
                ArrayList arrayList = new ArrayList();
                this.mErrors = arrayList;
                ArrayList arrayList2 = new ArrayList();
                this.mWarnings = arrayList2;
                this.mCertificates = apkSignerInfo.certs;
                this.mCertificateLineage = apkSignerInfo.certificateLineage;
                arrayList.addAll(apkSignerInfo.getErrors());
                arrayList2.addAll(apkSignerInfo.getWarnings());
                this.mTimestamp = apkSignerInfo.timestamp;
            }

            public X509Certificate getCertificate() {
                if (this.mCertificates.isEmpty()) {
                    return null;
                }
                return (X509Certificate) this.mCertificates.get(0);
            }

            public List getCertificatesInLineage() {
                return this.mCertificateLineage;
            }
        }

        public Result() {
            ArrayList arrayList = new ArrayList();
            this.mV1SchemeSigners = arrayList;
            ArrayList arrayList2 = new ArrayList();
            this.mV2SchemeSigners = arrayList2;
            ArrayList arrayList3 = new ArrayList();
            this.mV3SchemeSigners = arrayList3;
            this.mAllSchemeSigners = Arrays.asList(arrayList, arrayList2, arrayList3);
            this.mErrors = new ArrayList();
            this.mWarnings = new ArrayList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV1Signer(SignerInfo signerInfo) {
            this.mV1SchemeSigners.add(signerInfo);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV2Signer(SignerInfo signerInfo) {
            this.mV2SchemeSigners.add(signerInfo);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addV3Signer(SignerInfo signerInfo) {
            this.mV3SchemeSigners.add(signerInfo);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFrom(ApkSigResult apkSigResult) {
            switch (apkSigResult.signatureSchemeVersion) {
                case 0:
                    this.mVerified = apkSigResult.verified;
                    if (!apkSigResult.mSigners.isEmpty()) {
                        this.mSourceStampInfo = new SourceStampInfo((ApkSignerInfo) apkSigResult.mSigners.get(0));
                        return;
                    }
                    return;
                default:
                    throw new IllegalArgumentException("Unknown ApkSigResult Signing Block Scheme Id " + apkSigResult.signatureSchemeVersion);
            }
        }

        void addVerificationError(int i, Object... objArr) {
            this.mErrors.add(new ApkVerificationIssue(i, objArr));
        }

        void addVerificationWarning(int i, Object... objArr) {
            this.mWarnings.add(new ApkVerificationIssue(i, objArr));
        }

        public SourceStampInfo getSourceStampInfo() {
            return this.mSourceStampInfo;
        }

        public List getV1SchemeSigners() {
            return this.mV1SchemeSigners;
        }

        public List getV2SchemeSigners() {
            return this.mV2SchemeSigners;
        }

        public List getV3SchemeSigners() {
            return this.mV3SchemeSigners;
        }

        public boolean isVerified() {
            return this.mVerified;
        }
    }

    private SourceStampVerifier(File file, DataSource dataSource, int i, int i2) {
        this.mApkFile = file;
        this.mApkDataSource = dataSource;
        this.mMinSdkVersion = i;
        this.mMaxSdkVersion = i2;
    }

    private static void parseSigner(ByteBuffer byteBuffer, int i, CertificateFactory certificateFactory, Map map, Result.SignerInfo signerInfo) {
        boolean z = i == 2;
        ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
        ByteBuffer lengthPrefixedSlice2 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice);
        ByteBuffer lengthPrefixedSlice3 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice);
        while (lengthPrefixedSlice2.hasRemaining()) {
            try {
                ByteBuffer lengthPrefixedSlice4 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice2);
                int i2 = lengthPrefixedSlice4.getInt();
                byte[] readLengthPrefixedByteArray = ApkSigningBlockUtilsLite.readLengthPrefixedByteArray(lengthPrefixedSlice4);
                SignatureAlgorithm findById = SignatureAlgorithm.findById(i2);
                if (findById != null) {
                    map.put(findById.getContentDigestAlgorithm(), readLengthPrefixedByteArray);
                }
            } catch (ApkFormatException | BufferUnderflowException e) {
                signerInfo.addVerificationWarning(z ? 8 : 16, new Object[0]);
                return;
            }
        }
        if (lengthPrefixedSlice3.hasRemaining()) {
            byte[] readLengthPrefixedByteArray2 = ApkSigningBlockUtilsLite.readLengthPrefixedByteArray(lengthPrefixedSlice3);
            try {
                signerInfo.setSigningCertificate(new GuaranteedEncodedFormX509Certificate((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(readLengthPrefixedByteArray2)), readLengthPrefixedByteArray2));
            } catch (CertificateException e2) {
                signerInfo.addVerificationWarning(z ? 6 : 14, new Object[0]);
                return;
            }
        }
        if (signerInfo.getSigningCertificate() == null) {
            signerInfo.addVerificationWarning(z ? 7 : 15, new Object[0]);
        }
    }

    public static void parseSigners(ByteBuffer byteBuffer, int i, Map map, Result result) {
        int i2 = 1;
        boolean z = i == 2;
        try {
            ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
            if (!lengthPrefixedSlice.hasRemaining()) {
                result.addVerificationWarning(z ? 2 : 10, new Object[0]);
                return;
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                while (lengthPrefixedSlice.hasRemaining()) {
                    Result.SignerInfo signerInfo = new Result.SignerInfo();
                    if (z) {
                        result.addV2Signer(signerInfo);
                    } else {
                        result.addV3Signer(signerInfo);
                    }
                    try {
                        parseSigner(ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice), i, certificateFactory, map, signerInfo);
                    } catch (ApkFormatException | BufferUnderflowException e) {
                        signerInfo.addVerificationWarning(z ? 3 : 11, new Object[0]);
                        return;
                    }
                }
            } catch (CertificateException e2) {
                throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e2);
            }
        } catch (ApkFormatException e3) {
            if (!z) {
                i2 = 9;
            }
            result.addVerificationWarning(i2, new Object[0]);
        }
    }

    public Result verifySourceStamp() {
        return verifySourceStamp(null);
    }

    private static Map getApkContentDigestFromV1SigningScheme(List list, DataSource dataSource, ZipSections zipSections, Result result) {
        ArrayList<CentralDirectoryRecord> arrayList = new ArrayList(1);
        EnumMap enumMap = new EnumMap(ContentDigestAlgorithm.class);
        Iterator it = list.iterator();
        CentralDirectoryRecord centralDirectoryRecord = null;
        while (it.hasNext()) {
            CentralDirectoryRecord centralDirectoryRecord2 = (CentralDirectoryRecord) it.next();
            String name = centralDirectoryRecord2.getName();
            if (name != null) {
                if (centralDirectoryRecord == null && "META-INF/MANIFEST.MF".equals(name)) {
                    centralDirectoryRecord = centralDirectoryRecord2;
                } else if (name.startsWith("META-INF/") && (name.endsWith(".RSA") || name.endsWith(".DSA") || name.endsWith(".EC"))) {
                    arrayList.add(centralDirectoryRecord2);
                }
            }
        }
        if (centralDirectoryRecord == null) {
            return enumMap;
        }
        if (arrayList.isEmpty()) {
            result.addVerificationWarning(36, new Object[0]);
        } else {
            for (CentralDirectoryRecord centralDirectoryRecord3 : arrayList) {
                try {
                    Iterator<? extends Certificate> it2 = CertificateFactory.getInstance("X.509").generateCertificates(new ByteArrayInputStream(LocalFileRecord.getUncompressedData(dataSource, centralDirectoryRecord3, zipSections.getZipCentralDirectoryOffset()))).iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            Certificate next = it2.next();
                            if (next instanceof X509Certificate) {
                                Result.SignerInfo signerInfo = new Result.SignerInfo();
                                signerInfo.setSigningCertificate((X509Certificate) next);
                                result.addV1Signer(signerInfo);
                                break;
                            }
                        }
                    }
                } catch (ZipFormatException e) {
                    throw new ApkFormatException("Failed to read APK", e);
                } catch (CertificateException e2) {
                    result.addVerificationWarning(37, centralDirectoryRecord3.getName(), e2);
                }
            }
        }
        try {
            enumMap.put((EnumMap) ContentDigestAlgorithm.SHA256, (ContentDigestAlgorithm) ApkUtilsLite.computeSha256DigestBytes(LocalFileRecord.getUncompressedData(dataSource, centralDirectoryRecord, zipSections.getZipCentralDirectoryOffset())));
            return enumMap;
        } catch (ZipFormatException e3) {
            throw new ApkFormatException("Failed to read APK", e3);
        }
    }

    private Result verifySourceStamp(DataSource dataSource, String str) {
        SignatureInfo signatureInfo;
        CentralDirectoryRecord centralDirectoryRecord;
        SignatureInfo signatureInfo2;
        boolean z;
        Result result = new Result();
        try {
            try {
                ZipSections findZipSections = ApkUtilsLite.findZipSections(dataSource);
                List parseZipCentralDirectory = ZipUtils.parseZipCentralDirectory(dataSource, findZipSections);
                Iterator it = parseZipCentralDirectory.iterator();
                while (true) {
                    signatureInfo = null;
                    if (!it.hasNext()) {
                        centralDirectoryRecord = null;
                        break;
                    }
                    centralDirectoryRecord = (CentralDirectoryRecord) it.next();
                    if ("stamp-cert-sha256".equals(centralDirectoryRecord.getName())) {
                        break;
                    }
                }
                if (centralDirectoryRecord == null) {
                    try {
                        ApkSigningBlockUtilsLite.findSignature(dataSource, findZipSections, 1845461005);
                        z = true;
                    } catch (SignatureNotFoundException e) {
                        z = false;
                    }
                    result.addVerificationError(z ? 24 : 25, new Object[0]);
                    return result;
                }
                byte[] uncompressedData = LocalFileRecord.getUncompressedData(dataSource, centralDirectoryRecord, findZipSections.getZipCentralDirectoryOffset());
                if (str != null) {
                    String hex = ApkSigningBlockUtilsLite.toHex(uncompressedData);
                    if (!str.equalsIgnoreCase(hex)) {
                        result.addVerificationError(23, hex, str);
                        return result;
                    }
                }
                HashMap hashMap = new HashMap();
                if (this.mMaxSdkVersion >= 28) {
                    try {
                        signatureInfo2 = ApkSigningBlockUtilsLite.findSignature(dataSource, findZipSections, -262969152);
                    } catch (SignatureNotFoundException e2) {
                        signatureInfo2 = null;
                    }
                    if (signatureInfo2 != null) {
                        EnumMap enumMap = new EnumMap(ContentDigestAlgorithm.class);
                        parseSigners(signatureInfo2.signatureBlock, 3, enumMap, result);
                        hashMap.put(3, enumMap);
                    }
                }
                if (this.mMaxSdkVersion >= 24 && (this.mMinSdkVersion < 28 || hashMap.isEmpty())) {
                    try {
                        signatureInfo = ApkSigningBlockUtilsLite.findSignature(dataSource, findZipSections, 1896449818);
                    } catch (SignatureNotFoundException e3) {
                    }
                    if (signatureInfo != null) {
                        EnumMap enumMap2 = new EnumMap(ContentDigestAlgorithm.class);
                        parseSigners(signatureInfo.signatureBlock, 2, enumMap2, result);
                        hashMap.put(2, enumMap2);
                    }
                }
                if (this.mMinSdkVersion < 24 || hashMap.isEmpty()) {
                    hashMap.put(1, getApkContentDigestFromV1SigningScheme(parseZipCentralDirectory, dataSource, findZipSections, result));
                }
                result.mergeFrom(V2SourceStampVerifier.verify(dataSource, findZipSections, uncompressedData, hashMap, this.mMinSdkVersion, this.mMaxSdkVersion));
                return result;
            } catch (ApkFormatException e4) {
                e = e4;
                result.addVerificationError(28, e);
                return result;
            } catch (ZipFormatException e5) {
                e = e5;
                result.addVerificationError(28, e);
                return result;
            } catch (IOException e6) {
                e = e6;
                result.addVerificationError(28, e);
                return result;
            } catch (NoSuchAlgorithmException e7) {
                result.addVerificationError(29, e7);
                return result;
            }
        } catch (SignatureNotFoundException e8) {
            result.addVerificationError(30, new Object[0]);
            return result;
        }
    }

    public Result verifySourceStamp(String str) {
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                DataSource dataSource = this.mApkDataSource;
                if (dataSource == null) {
                    if (this.mApkFile == null) {
                        throw new IllegalStateException("APK not provided");
                    }
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(this.mApkFile, "r");
                    try {
                        dataSource = DataSources.asDataSource(randomAccessFile2, 0L, randomAccessFile2.length());
                        randomAccessFile = randomAccessFile2;
                    } catch (IOException e) {
                        e = e;
                        randomAccessFile = randomAccessFile2;
                        Result result = new Result();
                        result.addVerificationError(29, e);
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e2) {
                            }
                        }
                        return result;
                    } catch (Throwable th) {
                        th = th;
                        randomAccessFile = randomAccessFile2;
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e3) {
                            }
                        }
                        throw th;
                    }
                }
                Result verifySourceStamp = verifySourceStamp(dataSource, str);
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e4) {
                    }
                }
                return verifySourceStamp;
            } catch (IOException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
