package com.google.android.libraries.consentverifier.consents;

import com.google.android.libraries.consentverifier.CollectionBasisContext;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasis;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ConsentRetriever {
    Consent getConsentMapping(AndroidPrivacyAnnotationsEnums$CollectionBasis androidPrivacyAnnotationsEnums$CollectionBasis, CollectionBasisContext collectionBasisContext);
}
