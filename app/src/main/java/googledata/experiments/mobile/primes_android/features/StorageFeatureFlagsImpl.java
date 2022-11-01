package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import android.util.Base64;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableList;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StorageFeatureFlagsImpl implements StorageFeatureFlags {
    public static final ProcessStablePhenotypeFlag storageSamplingParameters;
    public static final ProcessStablePhenotypeFlag useSinglePassTraversal;

    static {
        ProcessStablePhenotypeFlagFactory directBootAware = new ProcessStablePhenotypeFlagFactory("com.google.android.libraries.performance.primes").withLogSourceNames(ImmutableList.of((Object) "CLIENT_LOGGING_PROD")).autoSubpackage().directBootAware();
        try {
            storageSamplingParameters = directBootAware.createFlagRestricted("17", SystemHealthProto$SamplingParameters.parseFrom(Base64.decode("EOgHGAM", 3)), StorageFeatureFlagsImpl$$ExternalSyntheticLambda0.INSTANCE);
            useSinglePassTraversal = directBootAware.createFlagRestricted("38", true);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    @Override // googledata.experiments.mobile.primes_android.features.StorageFeatureFlags
    public SystemHealthProto$SamplingParameters storageSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) storageSamplingParameters.get(context);
    }
}
