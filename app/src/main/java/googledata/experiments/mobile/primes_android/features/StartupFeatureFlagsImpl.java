package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import android.util.Base64;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableList;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StartupFeatureFlagsImpl implements StartupFeatureFlags {
    public static final ProcessStablePhenotypeFlag enableOnDrawBasedFirstDrawMeasurement;
    public static final ProcessStablePhenotypeFlag enableStartupBaselineCompression;
    public static final ProcessStablePhenotypeFlag enableStartupBaselineDiscarding;
    public static final ProcessStablePhenotypeFlag firstDrawType;
    public static final ProcessStablePhenotypeFlag startupSamplingParameters;

    static {
        ProcessStablePhenotypeFlagFactory directBootAware = new ProcessStablePhenotypeFlagFactory("com.google.android.libraries.performance.primes").withLogSourceNames(ImmutableList.of((Object) "CLIENT_LOGGING_PROD")).autoSubpackage().directBootAware();
        enableOnDrawBasedFirstDrawMeasurement = directBootAware.createFlagRestricted("45350020", false);
        enableStartupBaselineCompression = directBootAware.createFlagRestricted("2", true);
        enableStartupBaselineDiscarding = directBootAware.createFlagRestricted("3", false);
        firstDrawType = directBootAware.createFlagRestricted("45357887", 1L);
        try {
            startupSamplingParameters = directBootAware.createFlagRestricted("19", SystemHealthProto$SamplingParameters.parseFrom(Base64.decode("EAAYAg", 3)), StartupFeatureFlagsImpl$$ExternalSyntheticLambda0.INSTANCE);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Override // googledata.experiments.mobile.primes_android.features.StartupFeatureFlags
    public boolean enableStartupBaselineDiscarding(Context context) {
        return ((Boolean) enableStartupBaselineDiscarding.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.StartupFeatureFlags
    public long firstDrawType(Context context) {
        return ((Long) firstDrawType.get(context)).longValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.StartupFeatureFlags
    public SystemHealthProto$SamplingParameters startupSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) startupSamplingParameters.get(context);
    }
}
