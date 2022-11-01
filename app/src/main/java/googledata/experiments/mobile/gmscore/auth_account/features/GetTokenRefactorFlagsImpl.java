package googledata.experiments.mobile.gmscore.auth_account.features;

import android.util.Base64;
import com.google.android.libraries.phenotype.client.PhenotypeConstants;
import com.google.android.libraries.phenotype.client.PhenotypeFlag;
import com.google.protos.experiments.proto.TypedFeatures$StringListParam;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GetTokenRefactorFlagsImpl implements GetTokenRefactorFlags {
    public static final PhenotypeFlag accountDataServiceSamplePercentage;
    public static final PhenotypeFlag accountDataServiceTokenapiUsable;
    public static final PhenotypeFlag accountManagerTimeoutSeconds;
    public static final PhenotypeFlag androidIdShift;
    public static final PhenotypeFlag authenticatorLogicImproved;
    public static final PhenotypeFlag blockedPackages;
    public static final PhenotypeFlag chimeraGetTokenEvolved;
    public static final PhenotypeFlag clearTokenTimeoutSeconds;
    public static final PhenotypeFlag defaultTaskTimeoutSeconds;
    public static final PhenotypeFlag gaulAccountsApiEvolved;
    public static final PhenotypeFlag gaulTokenApiEvolved;
    public static final PhenotypeFlag getTokenTimeoutSeconds;
    public static final PhenotypeFlag gmsAccountAuthenticatorEvolved;
    public static final PhenotypeFlag gmsAccountAuthenticatorSamplePercentage;

    static {
        PhenotypeFlag.Factory disableBypassPhenotypeForDebug = new PhenotypeFlag.Factory(PhenotypeConstants.getContentProviderUri("com.google.android.gms.auth_account")).skipGservices().disableBypassPhenotypeForDebug();
        accountDataServiceSamplePercentage = disableBypassPhenotypeForDebug.createFlagRestricted("getTokenRefactor__account_data_service_sample_percentage", 0.0d);
        accountDataServiceTokenapiUsable = disableBypassPhenotypeForDebug.createFlagRestricted("getTokenRefactor__account_data_service_tokenAPI_usable", true);
        accountManagerTimeoutSeconds = disableBypassPhenotypeForDebug.createFlagRestricted("getTokenRefactor__account_manager_timeout_seconds", 20L);
        androidIdShift = disableBypassPhenotypeForDebug.createFlagRestricted("getTokenRefactor__android_id_shift", 0L);
        authenticatorLogicImproved = disableBypassPhenotypeForDebug.createFlagRestricted("getTokenRefactor__authenticator_logic_improved", false);
        try {
            blockedPackages = disableBypassPhenotypeForDebug.createFlagRestricted("getTokenRefactor__blocked_packages", TypedFeatures$StringListParam.parseFrom(Base64.decode("ChNjb20uYW5kcm9pZC52ZW5kaW5nCiBjb20uZ29vZ2xlLmFuZHJvaWQuYXBwcy5tZWV0aW5ncwohY29tLmdvb2dsZS5hbmRyb2lkLmFwcHMubWVzc2FnaW5n", 3)), GetTokenRefactorFlagsImpl$$ExternalSyntheticLambda0.INSTANCE);
            chimeraGetTokenEvolved = disableBypassPhenotypeForDebug.createFlagRestricted("getTokenRefactor__chimera_get_token_evolved", true);
            clearTokenTimeoutSeconds = disableBypassPhenotypeForDebug.createFlagRestricted("getTokenRefactor__clear_token_timeout_seconds", 20L);
            defaultTaskTimeoutSeconds = disableBypassPhenotypeForDebug.createFlagRestricted("getTokenRefactor__default_task_timeout_seconds", 20L);
            gaulAccountsApiEvolved = disableBypassPhenotypeForDebug.createFlagRestricted("getTokenRefactor__gaul_accounts_api_evolved", false);
            gaulTokenApiEvolved = disableBypassPhenotypeForDebug.createFlagRestricted("getTokenRefactor__gaul_token_api_evolved", false);
            getTokenTimeoutSeconds = disableBypassPhenotypeForDebug.createFlagRestricted("getTokenRefactor__get_token_timeout_seconds", 120L);
            gmsAccountAuthenticatorEvolved = disableBypassPhenotypeForDebug.createFlagRestricted("getTokenRefactor__gms_account_authenticator_evolved", true);
            gmsAccountAuthenticatorSamplePercentage = disableBypassPhenotypeForDebug.createFlagRestricted("getTokenRefactor__gms_account_authenticator_sample_percentage", 0.0d);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Override // googledata.experiments.mobile.gmscore.auth_account.features.GetTokenRefactorFlags
    public TypedFeatures$StringListParam blockedPackages() {
        return (TypedFeatures$StringListParam) blockedPackages.get();
    }

    @Override // googledata.experiments.mobile.gmscore.auth_account.features.GetTokenRefactorFlags
    public boolean gaulTokenApiEvolved() {
        return ((Boolean) gaulTokenApiEvolved.get()).booleanValue();
    }
}
