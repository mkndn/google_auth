package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import android.util.Base64;
import com.google.android.libraries.performance.primes.metrics.jank.PerfettoTraceConfigurations$JankPerfettoConfigurations;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableList;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JankFeatureFlagsImpl implements JankFeatureFlags {
    public static final ProcessStablePhenotypeFlag enableAlwaysOnJankRecording;
    public static final ProcessStablePhenotypeFlag enableFlushingPerfettoTracesOnJank;
    public static final ProcessStablePhenotypeFlag frameMetricListenerThreadPriority;
    public static final ProcessStablePhenotypeFlag jankPerfettoConfigurations;
    public static final ProcessStablePhenotypeFlag jankSamplingParameters;
    public static final ProcessStablePhenotypeFlag perfettoTriggerNameFormatString;
    public static final ProcessStablePhenotypeFlag registerFrameMetricsListenerInOnCreate;

    static {
        ProcessStablePhenotypeFlagFactory directBootAware = new ProcessStablePhenotypeFlagFactory("com.google.android.libraries.performance.primes").withLogSourceNames(ImmutableList.of((Object) "CLIENT_LOGGING_PROD")).autoSubpackage().directBootAware();
        enableAlwaysOnJankRecording = directBootAware.createFlagRestricted("25", false);
        enableFlushingPerfettoTracesOnJank = directBootAware.createFlagRestricted("34", false);
        frameMetricListenerThreadPriority = directBootAware.createFlagRestricted("45351156", 10L);
        try {
            jankPerfettoConfigurations = directBootAware.createFlagRestricted("40", PerfettoTraceConfigurations$JankPerfettoConfigurations.parseFrom(Base64.decode("Ci1jb20uZ29vZ2xlLmFuZHJvaWQucHJpbWVzLWphbmstJVBBQ0tBR0VfTkFNRSUSIwgCEh9KPCVFVkVOVF9OQU1FJT4jbWlzc2VkQXBwRnJhbWVzEh8IAxIbSjwlRVZFTlRfTkFNRSU+I3RvdGFsRnJhbWVzEiYIBRIiSjwlRVZFTlRfTkFNRSU+I21heEZyYW1lVGltZU1pbGxpcw", 3)), JankFeatureFlagsImpl$$ExternalSyntheticLambda0.INSTANCE);
            try {
                jankSamplingParameters = directBootAware.createFlagRestricted("13", SystemHealthProto$SamplingParameters.parseFrom(Base64.decode("EAAYAg", 3)), JankFeatureFlagsImpl$$ExternalSyntheticLambda1.INSTANCE);
                perfettoTriggerNameFormatString = directBootAware.createFlagRestricted("39", "com.google.android.primes-jank-%PACKAGE_NAME%");
                registerFrameMetricsListenerInOnCreate = directBootAware.createFlagRestricted("45351799", false);
            } catch (Exception e) {
                throw new AssertionError(e);
            }
        } catch (Exception e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // googledata.experiments.mobile.primes_android.features.JankFeatureFlags
    public boolean enableAlwaysOnJankRecording(Context context) {
        return ((Boolean) enableAlwaysOnJankRecording.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.primes_android.features.JankFeatureFlags
    public PerfettoTraceConfigurations$JankPerfettoConfigurations jankPerfettoConfigurations(Context context) {
        return (PerfettoTraceConfigurations$JankPerfettoConfigurations) jankPerfettoConfigurations.get(context);
    }

    @Override // googledata.experiments.mobile.primes_android.features.JankFeatureFlags
    public SystemHealthProto$SamplingParameters jankSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) jankSamplingParameters.get(context);
    }

    @Override // googledata.experiments.mobile.primes_android.features.JankFeatureFlags
    public boolean registerFrameMetricsListenerInOnCreate(Context context) {
        return ((Boolean) registerFrameMetricsListenerInOnCreate.get(context)).booleanValue();
    }
}
