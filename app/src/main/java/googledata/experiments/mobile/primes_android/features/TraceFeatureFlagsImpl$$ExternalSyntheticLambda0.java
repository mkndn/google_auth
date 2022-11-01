package googledata.experiments.mobile.primes_android.features;

import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TraceFeatureFlagsImpl$$ExternalSyntheticLambda0 implements ProcessStablePhenotypeFlagFactory.Converter {
    public static final /* synthetic */ TraceFeatureFlagsImpl$$ExternalSyntheticLambda0 INSTANCE = new TraceFeatureFlagsImpl$$ExternalSyntheticLambda0();

    private /* synthetic */ TraceFeatureFlagsImpl$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory.Converter
    public final Object convert(Object obj) {
        return SystemHealthProto$SamplingParameters.parseFrom((byte[]) obj);
    }
}
