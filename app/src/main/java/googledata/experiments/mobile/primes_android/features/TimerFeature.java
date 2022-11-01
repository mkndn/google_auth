package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimerFeature implements Supplier {
    private static TimerFeature INSTANCE = new TimerFeature();
    private final Supplier supplier;

    public TimerFeature() {
        this(Suppliers.ofInstance(new TimerFeatureFlagsImpl()));
    }

    public static SystemHealthProto$SamplingParameters timerSamplingParameters(Context context) {
        return INSTANCE.get().timerSamplingParameters(context);
    }

    @Override // com.google.common.base.Supplier
    public TimerFeatureFlags get() {
        return (TimerFeatureFlags) this.supplier.get();
    }

    public TimerFeature(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
