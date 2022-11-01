package com.google.android.libraries.consentverifier.consents;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AlwaysAcceptedConsent implements Consent {
    private static final AlwaysAcceptedConsent instance = new AlwaysAcceptedConsent();

    private AlwaysAcceptedConsent() {
    }

    public static AlwaysAcceptedConsent getInstance() {
        return instance;
    }

    @Override // com.google.android.libraries.consentverifier.consents.Consent
    public boolean verify() {
        return true;
    }
}
