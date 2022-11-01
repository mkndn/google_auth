package com.google.android.libraries.consentverifier;

import com.google.android.libraries.consentverifier.consents.ConsentSupplier;
import com.google.android.libraries.consentverifier.consents.ConsentSuppliers;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CollectionBasisManager {
    private static final CollectionBasisManager instance = new CollectionBasisManager();
    private ConsentSupplier consentSupplier = ConsentSuppliers.getDefaultSupplier();

    private CollectionBasisManager() {
    }

    public static CollectionBasisManager getInstance() {
        return instance;
    }

    public boolean verifyConsent(AndroidPrivacyAnnotationsEnums$CollectionUseCase androidPrivacyAnnotationsEnums$CollectionUseCase, CollectionBasisContext collectionBasisContext) {
        return this.consentSupplier.getConsent(androidPrivacyAnnotationsEnums$CollectionUseCase, collectionBasisContext).verify();
    }
}
