package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import android.util.Base64;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableList;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimerFeatureFlagsImpl implements TimerFeatureFlags {
    public static final ProcessStablePhenotypeFlag timerSamplingParameters;

    static {
        try {
            timerSamplingParameters = new ProcessStablePhenotypeFlagFactory("com.google.android.libraries.performance.primes").withLogSourceNames(ImmutableList.of((Object) "CLIENT_LOGGING_PROD")).autoSubpackage().directBootAware().createFlagRestricted("9", SystemHealthProto$SamplingParameters.parseFrom(Base64.decode("EOgHGAQ", 3)), TimerFeatureFlagsImpl$$ExternalSyntheticLambda0.INSTANCE);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Override // googledata.experiments.mobile.primes_android.features.TimerFeatureFlags
    public SystemHealthProto$SamplingParameters timerSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) timerSamplingParameters.get(context);
    }
}
