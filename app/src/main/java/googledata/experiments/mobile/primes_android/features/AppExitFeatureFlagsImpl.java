package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import android.util.Base64;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableList;
import logs.proto.wireless.performance.mobile.ApplicationExitReasons;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppExitFeatureFlagsImpl implements AppExitFeatureFlags {
    public static final ProcessStablePhenotypeFlag appExitCollectionEnabled;
    public static final ProcessStablePhenotypeFlag appExitReasonsToReport;

    static {
        ProcessStablePhenotypeFlagFactory directBootAware = new ProcessStablePhenotypeFlagFactory("com.google.android.libraries.performance.primes").withLogSourceNames(ImmutableList.of((Object) "CLIENT_LOGGING_PROD")).autoSubpackage().directBootAware();
        appExitCollectionEnabled = directBootAware.createFlagRestricted("45352228", true);
        try {
            appExitReasonsToReport = directBootAware.createFlagRestricted("45352241", ApplicationExitReasons.parseFrom(Base64.decode("CAYIBAgFCAM", 3)), AppExitFeatureFlagsImpl$$ExternalSyntheticLambda0.INSTANCE);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Override // googledata.experiments.mobile.primes_android.features.AppExitFeatureFlags
    public boolean appExitCollectionEnabled(Context context) {
        return ((Boolean) appExitCollectionEnabled.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.AppExitFeatureFlags
    public ApplicationExitReasons appExitReasonsToReport(Context context) {
        return (ApplicationExitReasons) appExitReasonsToReport.get(context);
    }
}
