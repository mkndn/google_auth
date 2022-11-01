package com.google.android.libraries.consentverifier.consents;

import com.google.android.libraries.consentverifier.CollectionBasisContext;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ConsentSupplier {
    Consent getConsent(AndroidPrivacyAnnotationsEnums$CollectionUseCase androidPrivacyAnnotationsEnums$CollectionUseCase, CollectionBasisContext collectionBasisContext);
}
