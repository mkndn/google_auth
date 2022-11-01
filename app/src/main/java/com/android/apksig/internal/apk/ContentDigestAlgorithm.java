package com.android.apksig.internal.apk;

import com.google.android.apps.authenticator.util.CryptoUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum ContentDigestAlgorithm {
    CHUNKED_SHA256(1, CryptoUtils.DIGEST_SHA_256, 32),
    CHUNKED_SHA512(2, CryptoUtils.DIGEST_SHA_512, 64),
    VERITY_CHUNKED_SHA256(3, CryptoUtils.DIGEST_SHA_256, 32),
    SHA256(4, CryptoUtils.DIGEST_SHA_256, 32);
    
    private final int mChunkDigestOutputSizeBytes;
    private final int mId;
    private final String mJcaMessageDigestAlgorithm;

    ContentDigestAlgorithm(int i, String str, int i2) {
        this.mId = i;
        this.mJcaMessageDigestAlgorithm = str;
        this.mChunkDigestOutputSizeBytes = i2;
    }

    public int getId() {
        return this.mId;
    }
}
