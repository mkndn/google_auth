package googledata.experiments.mobile.primes_android.features;

import com.google.android.libraries.performance.primes.metrics.crash.CrashRecordingTimeouts;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class CrashFeatureFlagsImpl$$ExternalSyntheticLambda1 implements ProcessStablePhenotypeFlagFactory.Converter {
    public static final /* synthetic */ CrashFeatureFlagsImpl$$ExternalSyntheticLambda1 INSTANCE = new CrashFeatureFlagsImpl$$ExternalSyntheticLambda1();

    private /* synthetic */ CrashFeatureFlagsImpl$$ExternalSyntheticLambda1() {
    }

    @Override // com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory.Converter
    public final Object convert(Object obj) {
        return CrashRecordingTimeouts.parseFrom((byte[]) obj);
    }
}
