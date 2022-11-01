package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface NetworkFeatureFlags {
    SystemHealthProto$SamplingParameters networkSamplingParameters(Context context);
}
