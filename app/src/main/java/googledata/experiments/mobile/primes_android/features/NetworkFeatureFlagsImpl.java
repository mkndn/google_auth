package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import android.util.Base64;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableList;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkFeatureFlagsImpl implements NetworkFeatureFlags {
    public static final ProcessStablePhenotypeFlag networkSamplingParameters;

    static {
        try {
            networkSamplingParameters = new ProcessStablePhenotypeFlagFactory("com.google.android.libraries.performance.primes").withLogSourceNames(ImmutableList.of((Object) "CLIENT_LOGGING_PROD")).autoSubpackage().directBootAware().createFlagRestricted("12", SystemHealthProto$SamplingParameters.parseFrom(Base64.decode("EAAYAg", 3)), NetworkFeatureFlagsImpl$$ExternalSyntheticLambda0.INSTANCE);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Override // googledata.experiments.mobile.primes_android.features.NetworkFeatureFlags
    public SystemHealthProto$SamplingParameters networkSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) networkSamplingParameters.get(context);
    }
}
