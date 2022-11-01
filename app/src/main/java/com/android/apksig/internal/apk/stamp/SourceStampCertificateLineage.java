package com.android.apksig.internal.apk.stamp;

import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.internal.apk.ApkSigningBlockUtilsLite;
import com.android.apksig.internal.apk.SignatureAlgorithm;
import com.android.apksig.internal.util.GuaranteedEncodedFormX509Certificate;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
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
import java.util.HashSet;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SourceStampCertificateLineage {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SigningCertificateNode {
        public int flags;
        public final SignatureAlgorithm parentSigAlgorithm;
        public SignatureAlgorithm sigAlgorithm;
        public final byte[] signature;
        public final X509Certificate signingCert;

        public SigningCertificateNode(X509Certificate x509Certificate, SignatureAlgorithm signatureAlgorithm, SignatureAlgorithm signatureAlgorithm2, byte[] bArr, int i) {
            this.signingCert = x509Certificate;
            this.parentSigAlgorithm = signatureAlgorithm;
            this.sigAlgorithm = signatureAlgorithm2;
            this.signature = bArr;
            this.flags = i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof SigningCertificateNode) {
                SigningCertificateNode signingCertificateNode = (SigningCertificateNode) obj;
                return this.signingCert.equals(signingCertificateNode.signingCert) && this.parentSigAlgorithm == signingCertificateNode.parentSigAlgorithm && this.sigAlgorithm == signingCertificateNode.sigAlgorithm && Arrays.equals(this.signature, signingCertificateNode.signature) && this.flags == signingCertificateNode.flags;
            }
            return false;
        }

        public int hashCode() {
            X509Certificate x509Certificate = this.signingCert;
            int hashCode = ((x509Certificate == null ? 0 : x509Certificate.hashCode()) + 31) * 31;
            SignatureAlgorithm signatureAlgorithm = this.parentSigAlgorithm;
            int hashCode2 = (hashCode + (signatureAlgorithm == null ? 0 : signatureAlgorithm.hashCode())) * 31;
            SignatureAlgorithm signatureAlgorithm2 = this.sigAlgorithm;
            return ((((hashCode2 + (signatureAlgorithm2 != null ? signatureAlgorithm2.hashCode() : 0)) * 31) + Arrays.hashCode(this.signature)) * 31) + this.flags;
        }
    }

    public static List readSigningCertificateLineage(ByteBuffer byteBuffer) {
        String str;
        String str2;
        Throwable th;
        CertificateException certificateException;
        String str3 = " when parsing SourceStampCertificateLineage object";
        ArrayList arrayList = new ArrayList();
        GuaranteedEncodedFormX509Certificate guaranteedEncodedFormX509Certificate = null;
        if (byteBuffer == null || !byteBuffer.hasRemaining()) {
            return null;
        }
        ApkSigningBlockUtilsLite.checkByteOrderLittleEndian(byteBuffer);
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            int i = 0;
            try {
                try {
                    if (byteBuffer.getInt() != 1) {
                        str = " when parsing SourceStampCertificateLineage object";
                        try {
                            throw new IllegalArgumentException("Encoded SigningCertificateLineage has a version different than any of which we are aware");
                        } catch (InvalidAlgorithmParameterException e) {
                            e = e;
                            th = e;
                            str2 = str;
                            throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                        } catch (InvalidKeyException e2) {
                            e = e2;
                            th = e;
                            str2 = str;
                            throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                        } catch (NoSuchAlgorithmException e3) {
                            e = e3;
                            th = e;
                            str2 = str;
                            throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                        } catch (SignatureException e4) {
                            e = e4;
                            th = e;
                            str2 = str;
                            throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                        } catch (CertificateException e5) {
                            e = e5;
                            certificateException = e;
                            throw new SecurityException("Failed to decode certificate #" + i + str, certificateException);
                        }
                    }
                    HashSet hashSet = new HashSet();
                    int i2 = 0;
                    while (byteBuffer.hasRemaining()) {
                        try {
                            i++;
                            try {
                                ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(byteBuffer);
                                ByteBuffer lengthPrefixedSlice2 = ApkSigningBlockUtilsLite.getLengthPrefixedSlice(lengthPrefixedSlice);
                                int i3 = lengthPrefixedSlice.getInt();
                                int i4 = lengthPrefixedSlice.getInt();
                                SignatureAlgorithm findById = SignatureAlgorithm.findById(i2);
                                byte[] readLengthPrefixedByteArray = ApkSigningBlockUtilsLite.readLengthPrefixedByteArray(lengthPrefixedSlice);
                                if (guaranteedEncodedFormX509Certificate != null) {
                                    String str4 = (String) findById.getJcaSignatureAlgorithmAndParams().getFirst();
                                    AlgorithmParameterSpec algorithmParameterSpec = (AlgorithmParameterSpec) findById.getJcaSignatureAlgorithmAndParams().getSecond();
                                    PublicKey publicKey = guaranteedEncodedFormX509Certificate.getPublicKey();
                                    str = str3;
                                    try {
                                        Signature signature = Signature.getInstance(str4);
                                        signature.initVerify(publicKey);
                                        if (algorithmParameterSpec != null) {
                                            signature.setParameter(algorithmParameterSpec);
                                        }
                                        signature.update(lengthPrefixedSlice2);
                                        if (!signature.verify(readLengthPrefixedByteArray)) {
                                            throw new SecurityException("Unable to verify signature of certificate #" + i + " using " + str4 + " when verifying SourceStampCertificateLineage object");
                                        }
                                    } catch (InvalidAlgorithmParameterException e6) {
                                        e = e6;
                                        th = e;
                                        str2 = str;
                                        throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                                    } catch (InvalidKeyException e7) {
                                        e = e7;
                                        th = e;
                                        str2 = str;
                                        throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                                    } catch (NoSuchAlgorithmException e8) {
                                        e = e8;
                                        th = e;
                                        str2 = str;
                                        throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                                    } catch (SignatureException e9) {
                                        e = e9;
                                        th = e;
                                        str2 = str;
                                        throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                                    } catch (CertificateException e10) {
                                        e = e10;
                                        certificateException = e;
                                        throw new SecurityException("Failed to decode certificate #" + i + str, certificateException);
                                    }
                                } else {
                                    str = str3;
                                }
                                ByteBuffer byteBuffer2 = (ByteBuffer) lengthPrefixedSlice2.rewind();
                                byte[] readLengthPrefixedByteArray2 = ApkSigningBlockUtilsLite.readLengthPrefixedByteArray(lengthPrefixedSlice2);
                                int i5 = lengthPrefixedSlice2.getInt();
                                if (guaranteedEncodedFormX509Certificate != null && i2 != i5) {
                                    throw new SecurityException("Signing algorithm ID mismatch for certificate #" + String.valueOf(lengthPrefixedSlice) + " when verifying SourceStampCertificateLineage object");
                                }
                                GuaranteedEncodedFormX509Certificate guaranteedEncodedFormX509Certificate2 = new GuaranteedEncodedFormX509Certificate((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(readLengthPrefixedByteArray2)), readLengthPrefixedByteArray2);
                                if (hashSet.contains(guaranteedEncodedFormX509Certificate2)) {
                                    throw new SecurityException("Encountered duplicate entries in SigningCertificateLineage at certificate #" + i + ".  All signing certificates should be unique");
                                }
                                hashSet.add(guaranteedEncodedFormX509Certificate2);
                                arrayList.add(new SigningCertificateNode(guaranteedEncodedFormX509Certificate2, SignatureAlgorithm.findById(i5), SignatureAlgorithm.findById(i4), readLengthPrefixedByteArray, i3));
                                guaranteedEncodedFormX509Certificate = guaranteedEncodedFormX509Certificate2;
                                i2 = i4;
                                str3 = str;
                            } catch (InvalidAlgorithmParameterException e11) {
                                e = e11;
                                str = str3;
                                th = e;
                                str2 = str;
                                throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                            } catch (InvalidKeyException e12) {
                                e = e12;
                                str = str3;
                                th = e;
                                str2 = str;
                                throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                            } catch (NoSuchAlgorithmException e13) {
                                e = e13;
                                str = str3;
                                th = e;
                                str2 = str;
                                throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                            } catch (SignatureException e14) {
                                e = e14;
                                str = str3;
                                th = e;
                                str2 = str;
                                throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                            } catch (CertificateException e15) {
                                e = e15;
                                str = str3;
                            }
                        } catch (InvalidAlgorithmParameterException e16) {
                            e = e16;
                            str = str3;
                            th = e;
                            str2 = str;
                            throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                        } catch (InvalidKeyException e17) {
                            e = e17;
                            str = str3;
                            th = e;
                            str2 = str;
                            throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                        } catch (NoSuchAlgorithmException e18) {
                            e = e18;
                            str = str3;
                            th = e;
                            str2 = str;
                            throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                        } catch (SignatureException e19) {
                            e = e19;
                            str = str3;
                            th = e;
                            str2 = str;
                            throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                        } catch (CertificateException e20) {
                            str = str3;
                            certificateException = e20;
                            throw new SecurityException("Failed to decode certificate #" + i + str, certificateException);
                        }
                    }
                    return arrayList;
                } catch (InvalidAlgorithmParameterException e21) {
                    e = e21;
                    str2 = " when parsing SourceStampCertificateLineage object";
                    th = e;
                    throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                } catch (InvalidKeyException e22) {
                    e = e22;
                    str2 = " when parsing SourceStampCertificateLineage object";
                    th = e;
                    throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                } catch (NoSuchAlgorithmException e23) {
                    e = e23;
                    str2 = " when parsing SourceStampCertificateLineage object";
                    th = e;
                    throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                } catch (SignatureException e24) {
                    e = e24;
                    str2 = " when parsing SourceStampCertificateLineage object";
                    th = e;
                    throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
                } catch (CertificateException e25) {
                    e = e25;
                    str = " when parsing SourceStampCertificateLineage object";
                }
                throw new SecurityException("Failed to verify signature over signed data for certificate #" + i + str2, th);
            } catch (ApkFormatException | BufferUnderflowException e26) {
                throw new IOException("Failed to parse SourceStampCertificateLineage object", e26);
            }
        } catch (CertificateException e27) {
            throw new IllegalStateException("Failed to obtain X.509 CertificateFactory", e27);
        }
    }
}
