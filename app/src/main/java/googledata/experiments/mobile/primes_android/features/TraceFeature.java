package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TraceFeature implements Supplier {
    private static TraceFeature INSTANCE = new TraceFeature();
    private final Supplier supplier;

    public TraceFeature() {
        this(Suppliers.ofInstance(new TraceFeatureFlagsImpl()));
    }

    public static SystemHealthProto$SamplingParameters traceSamplingParameters(Context context) {
        return INSTANCE.get().traceSamplingParameters(context);
    }

    @Override // com.google.common.base.Supplier
    public TraceFeatureFlags get() {
        return (TraceFeatureFlags) this.supplier.get();
    }

    public TraceFeature(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
