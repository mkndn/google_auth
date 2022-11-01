package googledata.experiments.mobile.primes_android.features;

import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class JankFeatureFlagsImpl$$ExternalSyntheticLambda1 implements ProcessStablePhenotypeFlagFactory.Converter {
    public static final /* synthetic */ JankFeatureFlagsImpl$$ExternalSyntheticLambda1 INSTANCE = new JankFeatureFlagsImpl$$ExternalSyntheticLambda1();

    private /* synthetic */ JankFeatureFlagsImpl$$ExternalSyntheticLambda1() {
    }

    @Override // com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory.Converter
    public final Object convert(Object obj) {
        return SystemHealthProto$SamplingParameters.parseFrom((byte[]) obj);
    }
}
