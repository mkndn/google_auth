package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface StartupFeatureFlags {
    boolean enableStartupBaselineDiscarding(Context context);

    long firstDrawType(Context context);

    SystemHealthProto$SamplingParameters startupSamplingParameters(Context context);
}
