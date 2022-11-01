package com.google.android.libraries.consentverifier.consents;

import com.google.android.libraries.consentverifier.CollectionBasisContext;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasisSpec;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExpressionConsent implements Consent {
    private final CollectionBasisContext collectionBasisContext;
    private final ConsentRetriever consentRetriever;
    private final AndroidPrivacyAnnotationsEnums$CollectionBasisSpec spec;

    private ExpressionConsent(ConsentRetriever consentRetriever, AndroidPrivacyAnnotationsEnums$CollectionBasisSpec androidPrivacyAnnotationsEnums$CollectionBasisSpec, CollectionBasisContext collectionBasisContext) {
        this.consentRetriever = consentRetriever;
        this.spec = androidPrivacyAnnotationsEnums$CollectionBasisSpec;
        this.collectionBasisContext = collectionBasisContext;
    }

    private Boolean computeExpressionResult() {
        if (this.spec.hasAndSpec()) {
            return Boolean.valueOf(evaluateAndConsentExpression(this.spec.getAndSpec()));
        }
        if (this.spec.hasOrSpec()) {
            return Boolean.valueOf(evaluateOrConsentExpression(this.spec.getOrSpec()));
        }
        return Boolean.valueOf(this.consentRetriever.getConsentMapping(this.spec.getBasis(), this.collectionBasisContext).verify());
    }

    private boolean evaluateAndConsentExpression(AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec androidPrivacyAnnotationsEnums$CollectionBasisAndSpec) {
        for (int i = 0; i < androidPrivacyAnnotationsEnums$CollectionBasisAndSpec.getBasisCount(); i++) {
            if (!this.consentRetriever.getConsentMapping(androidPrivacyAnnotationsEnums$CollectionBasisAndSpec.getBasis(i), this.collectionBasisContext).verify()) {
                return false;
            }
        }
        for (AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec androidPrivacyAnnotationsEnums$CollectionBasisOrSpec : androidPrivacyAnnotationsEnums$CollectionBasisAndSpec.getOrSpecList()) {
            if (!evaluateOrConsentExpression(androidPrivacyAnnotationsEnums$CollectionBasisOrSpec)) {
                return false;
            }
        }
        return true;
    }

    private boolean evaluateOrConsentExpression(AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec androidPrivacyAnnotationsEnums$CollectionBasisOrSpec) {
        for (int i = 0; i < androidPrivacyAnnotationsEnums$CollectionBasisOrSpec.getBasisCount(); i++) {
            if (this.consentRetriever.getConsentMapping(androidPrivacyAnnotationsEnums$CollectionBasisOrSpec.getBasis(i), this.collectionBasisContext).verify()) {
                return true;
            }
        }
        for (AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec androidPrivacyAnnotationsEnums$CollectionBasisAndSpec : androidPrivacyAnnotationsEnums$CollectionBasisOrSpec.getAndSpecList()) {
            if (evaluateAndConsentExpression(androidPrivacyAnnotationsEnums$CollectionBasisAndSpec)) {
                return true;
            }
        }
        return false;
    }

    public static ExpressionConsent getConsentFrom(ConsentRetriever consentRetriever, AndroidPrivacyAnnotationsEnums$CollectionBasisSpec androidPrivacyAnnotationsEnums$CollectionBasisSpec, CollectionBasisContext collectionBasisContext) {
        return new ExpressionConsent(consentRetriever, androidPrivacyAnnotationsEnums$CollectionBasisSpec, collectionBasisContext);
    }

    @Override // com.google.android.libraries.consentverifier.consents.Consent
    public boolean verify() {
        return computeExpressionResult().booleanValue();
    }
}
