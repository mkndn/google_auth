package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryFeature implements Supplier {
    private static BatteryFeature INSTANCE = new BatteryFeature();
    private final Supplier supplier;

    public BatteryFeature() {
        this(Suppliers.ofInstance(new BatteryFeatureFlagsImpl()));
    }

    public static SystemHealthProto$SamplingParameters batterySamplingParameters(Context context) {
        return INSTANCE.get().batterySamplingParameters(context);
    }

    @Override // com.google.common.base.Supplier
    public BatteryFeatureFlags get() {
        return (BatteryFeatureFlags) this.supplier.get();
    }

    public BatteryFeature(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
