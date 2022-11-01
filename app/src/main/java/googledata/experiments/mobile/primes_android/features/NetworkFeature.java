package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkFeature implements Supplier {
    private static NetworkFeature INSTANCE = new NetworkFeature();
    private final Supplier supplier;

    public NetworkFeature() {
        this(Suppliers.ofInstance(new NetworkFeatureFlagsImpl()));
    }

    public static SystemHealthProto$SamplingParameters networkSamplingParameters(Context context) {
        return INSTANCE.get().networkSamplingParameters(context);
    }

    @Override // com.google.common.base.Supplier
    public NetworkFeatureFlags get() {
        return (NetworkFeatureFlags) this.supplier.get();
    }

    public NetworkFeature(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
