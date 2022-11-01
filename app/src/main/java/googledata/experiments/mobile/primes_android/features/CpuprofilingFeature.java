package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuprofilingFeature implements Supplier {
    private static CpuprofilingFeature INSTANCE = new CpuprofilingFeature();
    private final Supplier supplier;

    public CpuprofilingFeature() {
        this(Suppliers.ofInstance(new CpuprofilingFeatureFlagsImpl()));
    }

    public static SystemHealthProto$SamplingParameters cpuprofilingSamplingParameters(Context context) {
        return INSTANCE.get().cpuprofilingSamplingParameters(context);
    }

    @Override // com.google.common.base.Supplier
    public CpuprofilingFeatureFlags get() {
        return (CpuprofilingFeatureFlags) this.supplier.get();
    }

    public CpuprofilingFeature(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
