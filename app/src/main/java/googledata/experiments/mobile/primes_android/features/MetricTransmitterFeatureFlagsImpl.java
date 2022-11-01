package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricTransmitterFeatureFlagsImpl implements MetricTransmitterFeatureFlags {
    public static final ProcessStablePhenotypeFlag convertPendingResultOnBinderThread;
    public static final ProcessStablePhenotypeFlag disableClearcutLoggingInTests;
    public static final ProcessStablePhenotypeFlag enableDelphiCollectionBasisLogVerifier;
    public static final ProcessStablePhenotypeFlag usePackedHistogramEncodingInClearcut;

    static {
        ProcessStablePhenotypeFlagFactory directBootAware = new ProcessStablePhenotypeFlagFactory("com.google.android.libraries.performance.primes").withLogSourceNames(ImmutableList.of((Object) "CLIENT_LOGGING_PROD")).autoSubpackage().directBootAware();
        convertPendingResultOnBinderThread = directBootAware.createFlagRestricted("45370766", true);
        disableClearcutLoggingInTests = directBootAware.createFlagRestricted("45359218", true);
        enableDelphiCollectionBasisLogVerifier = directBootAware.createFlagRestricted("45359255", false);
        usePackedHistogramEncodingInClearcut = directBootAware.createFlagRestricted("36", true);
    }

    @Override // googledata.experiments.mobile.primes_android.features.MetricTransmitterFeatureFlags
    public boolean disableClearcutLoggingInTests(Context context) {
        return ((Boolean) disableClearcutLoggingInTests.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.MetricTransmitterFeatureFlags
    public boolean enableDelphiCollectionBasisLogVerifier(Context context) {
        return ((Boolean) enableDelphiCollectionBasisLogVerifier.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.MetricTransmitterFeatureFlags
    public boolean usePackedHistogramEncodingInClearcut(Context context) {
        return ((Boolean) usePackedHistogramEncodingInClearcut.get(context)).booleanValue();
    }
}
