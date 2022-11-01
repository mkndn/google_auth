package com.google.android.libraries.consentverifier.consents;

import android.privacy.annotations.mappings.UseCaseMappings;
import com.google.android.libraries.consentverifier.CollectionBasisContext;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasisSpec;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ConsentSuppliers {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class DefaultConsentSupplier implements ConsentSupplier {
        private static final DefaultConsentSupplier instance = new DefaultConsentSupplier(ConsentRetrieverImpl.getInstance());
        private final ConsentRetriever consentRetriever;

        private DefaultConsentSupplier(ConsentRetriever consentRetriever) {
            this.consentRetriever = consentRetriever;
        }

        public static ConsentSupplier getInstance() {
            return instance;
        }

        @Override // com.google.android.libraries.consentverifier.consents.ConsentSupplier
        public Consent getConsent(AndroidPrivacyAnnotationsEnums$CollectionUseCase androidPrivacyAnnotationsEnums$CollectionUseCase, CollectionBasisContext collectionBasisContext) {
            AndroidPrivacyAnnotationsEnums$CollectionBasisSpec collectionBasisSpecs = UseCaseMappings.getCollectionBasisSpecs(androidPrivacyAnnotationsEnums$CollectionUseCase);
            if (collectionBasisSpecs.hasBasis()) {
                return this.consentRetriever.getConsentMapping(collectionBasisSpecs.getBasis(), collectionBasisContext);
            }
            return ExpressionConsent.getConsentFrom(this.consentRetriever, collectionBasisSpecs, collectionBasisContext);
        }
    }

    public static ConsentSupplier getDefaultSupplier() {
        return DefaultConsentSupplier.getInstance();
    }
}
