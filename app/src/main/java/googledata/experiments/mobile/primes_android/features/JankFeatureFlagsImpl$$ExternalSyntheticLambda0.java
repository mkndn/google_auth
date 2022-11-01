package googledata.experiments.mobile.primes_android.features;

import com.google.android.libraries.performance.primes.metrics.jank.PerfettoTraceConfigurations$JankPerfettoConfigurations;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class JankFeatureFlagsImpl$$ExternalSyntheticLambda0 implements ProcessStablePhenotypeFlagFactory.Converter {
    public static final /* synthetic */ JankFeatureFlagsImpl$$ExternalSyntheticLambda0 INSTANCE = new JankFeatureFlagsImpl$$ExternalSyntheticLambda0();

    private /* synthetic */ JankFeatureFlagsImpl$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory.Converter
    public final Object convert(Object obj) {
        return PerfettoTraceConfigurations$JankPerfettoConfigurations.parseFrom((byte[]) obj);
    }
}
