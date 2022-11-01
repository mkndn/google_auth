package com.google.android.libraries.consentverifier.consents;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AlwaysRejectedConsent implements Consent {
    private static final AlwaysRejectedConsent instance = new AlwaysRejectedConsent();

    private AlwaysRejectedConsent() {
    }

    public static AlwaysRejectedConsent getInstance() {
        return instance;
    }

    @Override // com.google.android.libraries.consentverifier.consents.Consent
    public boolean verify() {
        return false;
    }
}
