package com.google.android.libraries.consentverifier.consents;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.privacy.annotations.mappings.CollectionBasisConfigurations;
import com.google.android.libraries.consentverifier.CollectionBasisContext;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasis;
import java.util.Collection;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ConsentRetrieverImpl implements ConsentRetriever {
    private static final ConsentRetrieverImpl instance = new ConsentRetrieverImpl();
    private final CollectionBasisResolverHolders[] collectionBasisToResolversMap;

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.consentverifier.consents.ConsentRetrieverImpl$1  reason: invalid class name */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis;

        static {
            int[] iArr = new int[AndroidPrivacyAnnotationsEnums$CollectionBasis.values().length];
            $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis = iArr;
            try {
                iArr[AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis[AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis[AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PLAY_TOS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis[AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GLOBAL_LOCATION.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis[AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_LOCATION_REPORTING_DEVICE_LEVEL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis[AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_LOCATION_ACCURACY.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis[AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_WIFI_SCANNING.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis[AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GLOBAL_WIFI.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis[AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_WEB_AND_APP_ACTIVITY.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis[AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GLOBAL_BLUETOOTH.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis[AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_BLUETOOTH_SCANNING.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis[AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_UNICORN_SUPERVISION.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis[AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis[AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_SUPPLEMENTAL_WEB_AND_APP_ACTIVITY_DEVICE_LEVEL.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class CollectionBasisResolverHolders {
        volatile ImmutableList resolverHolders;

        CollectionBasisResolverHolders() {
        }
    }

    private ConsentRetrieverImpl() {
        AndroidPrivacyAnnotationsEnums$CollectionBasis[] values;
        int i = 0;
        for (AndroidPrivacyAnnotationsEnums$CollectionBasis androidPrivacyAnnotationsEnums$CollectionBasis : AndroidPrivacyAnnotationsEnums$CollectionBasis.values()) {
            i = Math.max(i, androidPrivacyAnnotationsEnums$CollectionBasis.getNumber());
        }
        this.collectionBasisToResolversMap = new CollectionBasisResolverHolders[i + 1];
        for (AndroidPrivacyAnnotationsEnums$CollectionBasis androidPrivacyAnnotationsEnums$CollectionBasis2 : AndroidPrivacyAnnotationsEnums$CollectionBasis.values()) {
            if (CollectionBasisConfigurations.isInProductBasis(androidPrivacyAnnotationsEnums$CollectionBasis2)) {
                this.collectionBasisToResolversMap[androidPrivacyAnnotationsEnums$CollectionBasis2.getNumber()] = new CollectionBasisResolverHolders();
                this.collectionBasisToResolversMap[androidPrivacyAnnotationsEnums$CollectionBasis2.getNumber()].resolverHolders = ImmutableList.of();
            }
        }
    }

    private static boolean deviceHasGooglerAccount(Context context) {
        Account[] accountsByType;
        try {
            for (Account account : AccountManager.get(context).getAccountsByType("com.google")) {
                if (account.name != null && account.type != null && account.name.endsWith("@google.com")) {
                    return true;
                }
            }
        } catch (SecurityException e) {
        }
        return false;
    }

    public static ConsentRetriever getInstance() {
        return instance;
    }

    private Consent getRuntimeResolvedConsent(AndroidPrivacyAnnotationsEnums$CollectionBasis androidPrivacyAnnotationsEnums$CollectionBasis, CollectionBasisContext collectionBasisContext) {
        if (this.collectionBasisToResolversMap[androidPrivacyAnnotationsEnums$CollectionBasis.getNumber()] == null) {
            return null;
        }
        UnmodifiableIterator it = this.collectionBasisToResolversMap[androidPrivacyAnnotationsEnums$CollectionBasis.getNumber()].resolverHolders.iterator();
        while (it.hasNext()) {
            CollectionBasisResolverHolder collectionBasisResolverHolder = (CollectionBasisResolverHolder) it.next();
            Optional accountNames = collectionBasisResolverHolder.conditions().accountNames();
            if (accountNames.isPresent() && !((ImmutableList) accountNames.get()).isEmpty()) {
                Optional accountNames2 = collectionBasisContext.accountNames();
                if (accountNames2.isPresent() && !((ImmutableList) accountNames2.get()).isEmpty() && ((ImmutableList) accountNames.get()).containsAll((Collection) accountNames2.get())) {
                    return collectionBasisResolverHolder.resolver();
                }
            } else {
                return collectionBasisResolverHolder.resolver();
            }
        }
        return null;
    }

    @Override // com.google.android.libraries.consentverifier.consents.ConsentRetriever
    public Consent getConsentMapping(AndroidPrivacyAnnotationsEnums$CollectionBasis androidPrivacyAnnotationsEnums$CollectionBasis, CollectionBasisContext collectionBasisContext) {
        switch (AnonymousClass1.$SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionBasis[androidPrivacyAnnotationsEnums$CollectionBasis.ordinal()]) {
            case 1:
                return AlwaysRejectedConsent.getInstance();
            case 2:
                return AlwaysAcceptedConsent.getInstance();
            case 3:
                return AlwaysAcceptedConsent.getInstance();
            case 4:
                return AlwaysAcceptedConsent.getInstance();
            case 5:
                return AlwaysAcceptedConsent.getInstance();
            case 6:
                return AlwaysAcceptedConsent.getInstance();
            case 7:
                return AlwaysAcceptedConsent.getInstance();
            case 8:
                return AlwaysAcceptedConsent.getInstance();
            case 9:
                return AlwaysAcceptedConsent.getInstance();
            case 10:
                return AlwaysAcceptedConsent.getInstance();
            case 11:
                return AlwaysAcceptedConsent.getInstance();
            case 12:
                return AlwaysAcceptedConsent.getInstance();
            case 13:
                if (collectionBasisContext.googlerOverridesCheckbox() && deviceHasGooglerAccount(collectionBasisContext.context())) {
                    return AlwaysAcceptedConsent.getInstance();
                }
                return DeviceUsageAndDiagnosticsConsent.getConsentFrom(collectionBasisContext, new ConsentUtils(collectionBasisContext.context().getApplicationContext()));
            case 14:
                return AlwaysRejectedConsent.getInstance();
            default:
                Consent runtimeResolvedConsent = getRuntimeResolvedConsent(androidPrivacyAnnotationsEnums$CollectionBasis, collectionBasisContext);
                if (runtimeResolvedConsent != null) {
                    return runtimeResolvedConsent;
                }
                return AlwaysRejectedConsent.getInstance();
        }
    }
}
