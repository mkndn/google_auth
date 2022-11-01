package com.google.android.libraries.consentverifier.logging;

import com.google.protos.collection_basis_verifier.logging.VerificationFailureEnum$VerificationFailure;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_VerificationFailureKey extends VerificationFailureKey {
    private final Long protoId;
    private final VerificationFailureEnum$VerificationFailure verificationFailure;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_VerificationFailureKey(Long l, VerificationFailureEnum$VerificationFailure verificationFailureEnum$VerificationFailure) {
        if (l == null) {
            throw new NullPointerException("Null protoId");
        }
        this.protoId = l;
        if (verificationFailureEnum$VerificationFailure == null) {
            throw new NullPointerException("Null verificationFailure");
        }
        this.verificationFailure = verificationFailureEnum$VerificationFailure;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof VerificationFailureKey) {
            VerificationFailureKey verificationFailureKey = (VerificationFailureKey) obj;
            return this.protoId.equals(verificationFailureKey.protoId()) && this.verificationFailure.equals(verificationFailureKey.verificationFailure());
        }
        return false;
    }

    @Override // com.google.android.libraries.consentverifier.logging.VerificationFailureKey
    Long protoId() {
        return this.protoId;
    }

    public String toString() {
        Long l = this.protoId;
        return "VerificationFailureKey{protoId=" + l + ", verificationFailure=" + String.valueOf(this.verificationFailure) + "}";
    }

    @Override // com.google.android.libraries.consentverifier.logging.VerificationFailureKey
    VerificationFailureEnum$VerificationFailure verificationFailure() {
        return this.verificationFailure;
    }

    public int hashCode() {
        return ((this.protoId.hashCode() ^ 1000003) * 1000003) ^ this.verificationFailure.hashCode();
    }
}
