package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import android.util.Base64;
import com.google.android.libraries.performance.primes.metrics.cpuprofiling.v2.ProfilerConfigurations$Config;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableList;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuprofilingFeatureFlagsImpl implements CpuprofilingFeatureFlags {
    public static final ProcessStablePhenotypeFlag cpuprofilingSamplingParameters;
    public static final ProcessStablePhenotypeFlag earlyPhenotypeProfilingV2Configurations;
    public static final ProcessStablePhenotypeFlag profilingV2Configurations;

    static {
        ProcessStablePhenotypeFlagFactory directBootAware = new ProcessStablePhenotypeFlagFactory("com.google.android.libraries.performance.primes").withLogSourceNames(ImmutableList.of((Object) "CLIENT_LOGGING_PROD")).autoSubpackage().directBootAware();
        try {
            cpuprofilingSamplingParameters = directBootAware.createFlagRestricted("15", SystemHealthProto$SamplingParameters.parseFrom(Base64.decode("EAAYAg", 3)), CpuprofilingFeatureFlagsImpl$$ExternalSyntheticLambda0.INSTANCE);
            try {
                earlyPhenotypeProfilingV2Configurations = directBootAware.createFlagRestricted("45357002", ProfilerConfigurations$Config.parseFrom(Base64.decode("CAASABgAIAAoADAAOABAAA", 3)), CpuprofilingFeatureFlagsImpl$$ExternalSyntheticLambda1.INSTANCE);
                try {
                    profilingV2Configurations = directBootAware.createFlagRestricted("45355611", ProfilerConfigurations$Config.parseFrom(Base64.decode("CAASABgAIAAoADAAOABAAA", 3)), CpuprofilingFeatureFlagsImpl$$ExternalSyntheticLambda1.INSTANCE);
                } catch (Exception e) {
                    throw new AssertionError(e);
                }
            } catch (Exception e2) {
                throw new AssertionError(e2);
            }
        } catch (Exception e3) {
            throw new AssertionError(e3);
        }
    }

    @Override // googledata.experiments.mobile.primes_android.features.CpuprofilingFeatureFlags
    public SystemHealthProto$SamplingParameters cpuprofilingSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) cpuprofilingSamplingParameters.get(context);
    }
}
