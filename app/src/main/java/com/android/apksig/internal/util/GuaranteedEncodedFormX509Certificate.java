package com.android.apksig.internal.util;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GuaranteedEncodedFormX509Certificate extends DelegatingX509Certificate {
    private static final long serialVersionUID = 1;
    private final byte[] mEncodedForm;
    private int mHash;

    public GuaranteedEncodedFormX509Certificate(X509Certificate x509Certificate, byte[] bArr) {
        super(x509Certificate);
        this.mHash = -1;
        this.mEncodedForm = bArr != null ? (byte[]) bArr.clone() : null;
    }

    @Override // java.security.cert.Certificate
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof X509Certificate) {
            try {
                return Arrays.equals(getEncoded(), ((X509Certificate) obj).getEncoded());
            } catch (CertificateEncodingException e) {
                return false;
            }
        }
        return false;
    }

    @Override // com.android.apksig.internal.util.DelegatingX509Certificate, java.security.cert.Certificate
    public byte[] getEncoded() {
        byte[] bArr = this.mEncodedForm;
        if (bArr != null) {
            return (byte[]) bArr.clone();
        }
        return null;
    }

    @Override // java.security.cert.Certificate
    public int hashCode() {
        if (this.mHash == -1) {
            try {
                this.mHash = Arrays.hashCode(getEncoded());
            } catch (CertificateEncodingException e) {
                this.mHash = 0;
            }
        }
        return this.mHash;
    }
}
