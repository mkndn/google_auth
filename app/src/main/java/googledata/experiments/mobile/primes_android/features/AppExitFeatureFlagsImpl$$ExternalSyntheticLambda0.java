package googledata.experiments.mobile.primes_android.features;

import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import logs.proto.wireless.performance.mobile.ApplicationExitReasons;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AppExitFeatureFlagsImpl$$ExternalSyntheticLambda0 implements ProcessStablePhenotypeFlagFactory.Converter {
    public static final /* synthetic */ AppExitFeatureFlagsImpl$$ExternalSyntheticLambda0 INSTANCE = new AppExitFeatureFlagsImpl$$ExternalSyntheticLambda0();

    private /* synthetic */ AppExitFeatureFlagsImpl$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory.Converter
    public final Object convert(Object obj) {
        return ApplicationExitReasons.parseFrom((byte[]) obj);
    }
}
