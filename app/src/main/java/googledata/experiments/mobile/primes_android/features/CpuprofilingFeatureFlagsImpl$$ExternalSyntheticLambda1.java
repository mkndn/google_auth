package googledata.experiments.mobile.primes_android.features;

import com.google.android.libraries.performance.primes.metrics.cpuprofiling.v2.ProfilerConfigurations$Config;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class CpuprofilingFeatureFlagsImpl$$ExternalSyntheticLambda1 implements ProcessStablePhenotypeFlagFactory.Converter {
    public static final /* synthetic */ CpuprofilingFeatureFlagsImpl$$ExternalSyntheticLambda1 INSTANCE = new CpuprofilingFeatureFlagsImpl$$ExternalSyntheticLambda1();

    private /* synthetic */ CpuprofilingFeatureFlagsImpl$$ExternalSyntheticLambda1() {
    }

    @Override // com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory.Converter
    public final Object convert(Object obj) {
        return ProfilerConfigurations$Config.parseFrom((byte[]) obj);
    }
}
