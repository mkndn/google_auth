package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryFeature implements Supplier {
    private static MemoryFeature INSTANCE = new MemoryFeature();
    private final Supplier supplier;

    public MemoryFeature() {
        this(Suppliers.ofInstance(new MemoryFeatureFlagsImpl()));
    }

    public static SystemHealthProto$SamplingParameters memorySamplingParameters(Context context) {
        return INSTANCE.get().memorySamplingParameters(context);
    }

    @Override // com.google.common.base.Supplier
    public MemoryFeatureFlags get() {
        return (MemoryFeatureFlags) this.supplier.get();
    }

    public MemoryFeature(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
