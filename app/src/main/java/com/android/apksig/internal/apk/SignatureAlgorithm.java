package com.android.apksig.internal.apk;

import com.android.apksig.internal.util.Pair;
import com.google.android.apps.authenticator.util.CryptoUtils;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum SignatureAlgorithm {
    RSA_PSS_WITH_SHA256(AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMM_PUBLIC_PHOTO_VALUE, ContentDigestAlgorithm.CHUNKED_SHA256, "RSA", Pair.of("SHA256withRSA/PSS", new PSSParameterSpec(CryptoUtils.DIGEST_SHA_256, "MGF1", MGF1ParameterSpec.SHA256, 32, 1)), 24, 23),
    RSA_PSS_WITH_SHA512(AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMM_PUBLIC_VIDEO_VALUE, ContentDigestAlgorithm.CHUNKED_SHA512, "RSA", Pair.of("SHA512withRSA/PSS", new PSSParameterSpec(CryptoUtils.DIGEST_SHA_512, "MGF1", MGF1ParameterSpec.SHA512, 64, 1)), 24, 23),
    RSA_PKCS1_V1_5_WITH_SHA256(AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMM_USER_INITIATED_FEEDBACK_VALUE, ContentDigestAlgorithm.CHUNKED_SHA256, "RSA", Pair.of("SHA256withRSA", null), 24, 1),
    RSA_PKCS1_V1_5_WITH_SHA512(AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MARKETING_ENGAGEMENT_GROWTH_COMMS_VALUE, ContentDigestAlgorithm.CHUNKED_SHA512, "RSA", Pair.of("SHA512withRSA", null), 24, 1),
    ECDSA_WITH_SHA256(513, ContentDigestAlgorithm.CHUNKED_SHA256, "EC", Pair.of("SHA256withECDSA", null), 24, 11),
    ECDSA_WITH_SHA512(514, ContentDigestAlgorithm.CHUNKED_SHA512, "EC", Pair.of("SHA512withECDSA", null), 24, 11),
    DSA_WITH_SHA256(769, ContentDigestAlgorithm.CHUNKED_SHA256, "DSA", Pair.of("SHA256withDSA", null), 24, 1),
    DETDSA_WITH_SHA256(769, ContentDigestAlgorithm.CHUNKED_SHA256, "DSA", Pair.of("SHA256withDetDSA", null), 24, 1),
    VERITY_RSA_PKCS1_V1_5_WITH_SHA256(1057, ContentDigestAlgorithm.VERITY_CHUNKED_SHA256, "RSA", Pair.of("SHA256withRSA", null), 28, 1),
    VERITY_ECDSA_WITH_SHA256(1059, ContentDigestAlgorithm.VERITY_CHUNKED_SHA256, "EC", Pair.of("SHA256withECDSA", null), 28, 11),
    VERITY_DSA_WITH_SHA256(1061, ContentDigestAlgorithm.VERITY_CHUNKED_SHA256, "DSA", Pair.of("SHA256withDSA", null), 28, 1);
    
    private final ContentDigestAlgorithm mContentDigestAlgorithm;
    private final int mId;
    private final String mJcaKeyAlgorithm;
    private final int mJcaSigAlgMinSdkVersion;
    private final Pair mJcaSignatureAlgAndParams;
    private final int mMinSdkVersion;

    SignatureAlgorithm(int i, ContentDigestAlgorithm contentDigestAlgorithm, String str, Pair pair, int i2, int i3) {
        this.mId = i;
        this.mContentDigestAlgorithm = contentDigestAlgorithm;
        this.mJcaKeyAlgorithm = str;
        this.mJcaSignatureAlgAndParams = pair;
        this.mMinSdkVersion = i2;
        this.mJcaSigAlgMinSdkVersion = i3;
    }

    public static SignatureAlgorithm findById(int i) {
        SignatureAlgorithm[] values;
        for (SignatureAlgorithm signatureAlgorithm : values()) {
            if (signatureAlgorithm.getId() == i) {
                return signatureAlgorithm;
            }
        }
        return null;
    }

    public ContentDigestAlgorithm getContentDigestAlgorithm() {
        return this.mContentDigestAlgorithm;
    }

    public int getId() {
        return this.mId;
    }

    public int getJcaSigAlgMinSdkVersion() {
        return this.mJcaSigAlgMinSdkVersion;
    }

    public Pair getJcaSignatureAlgorithmAndParams() {
        return this.mJcaSignatureAlgAndParams;
    }

    public int getMinSdkVersion() {
        return this.mMinSdkVersion;
    }
}
