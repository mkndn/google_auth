package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.android.libraries.performance.primes.metrics.jank.PerfettoTraceConfigurations$JankPerfettoConfigurations;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface JankFeatureFlags {
    boolean enableAlwaysOnJankRecording(Context context);

    PerfettoTraceConfigurations$JankPerfettoConfigurations jankPerfettoConfigurations(Context context);

    SystemHealthProto$SamplingParameters jankSamplingParameters(Context context);

    boolean registerFrameMetricsListenerInOnCreate(Context context);
}
