package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import android.util.Base64;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableList;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryFeatureFlagsImpl implements MemoryFeatureFlags {
    public static final ProcessStablePhenotypeFlag enableProcStatusMemoryMetrics;
    public static final ProcessStablePhenotypeFlag memorySamplingParameters;

    static {
        ProcessStablePhenotypeFlagFactory directBootAware = new ProcessStablePhenotypeFlagFactory("com.google.android.libraries.performance.primes").withLogSourceNames(ImmutableList.of((Object) "CLIENT_LOGGING_PROD")).autoSubpackage().directBootAware();
        enableProcStatusMemoryMetrics = directBootAware.createFlagRestricted("5", true);
        try {
            memorySamplingParameters = directBootAware.createFlagRestricted("8", SystemHealthProto$SamplingParameters.parseFrom(Base64.decode("EOgHGAQ", 3)), MemoryFeatureFlagsImpl$$ExternalSyntheticLambda0.INSTANCE);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Override // googledata.experiments.mobile.primes_android.features.MemoryFeatureFlags
    public SystemHealthProto$SamplingParameters memorySamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) memorySamplingParameters.get(context);
    }
}
