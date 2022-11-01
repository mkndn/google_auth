package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.android.libraries.performance.primes.metrics.jank.PerfettoTraceConfigurations$JankPerfettoConfigurations;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JankFeature implements Supplier {
    private static JankFeature INSTANCE = new JankFeature();
    private final Supplier supplier;

    public JankFeature() {
        this(Suppliers.ofInstance(new JankFeatureFlagsImpl()));
    }

    public static boolean enableAlwaysOnJankRecording(Context context) {
        return INSTANCE.get().enableAlwaysOnJankRecording(context);
    }

    public static PerfettoTraceConfigurations$JankPerfettoConfigurations jankPerfettoConfigurations(Context context) {
        return INSTANCE.get().jankPerfettoConfigurations(context);
    }

    public static SystemHealthProto$SamplingParameters jankSamplingParameters(Context context) {
        return INSTANCE.get().jankSamplingParameters(context);
    }

    public static boolean registerFrameMetricsListenerInOnCreate(Context context) {
        return INSTANCE.get().registerFrameMetricsListenerInOnCreate(context);
    }

    @Override // com.google.common.base.Supplier
    public JankFeatureFlags get() {
        return (JankFeatureFlags) this.supplier.get();
    }

    public JankFeature(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
