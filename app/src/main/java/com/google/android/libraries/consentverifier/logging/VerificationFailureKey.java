package com.google.android.libraries.consentverifier.logging;

import com.google.protos.collection_basis_verifier.logging.VerificationFailureEnum$VerificationFailure;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class VerificationFailureKey {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static VerificationFailureKey create(Long l, VerificationFailureEnum$VerificationFailure verificationFailureEnum$VerificationFailure) {
        return new AutoValue_VerificationFailureKey(l, verificationFailureEnum$VerificationFailure);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Long protoId();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract VerificationFailureEnum$VerificationFailure verificationFailure();
}
