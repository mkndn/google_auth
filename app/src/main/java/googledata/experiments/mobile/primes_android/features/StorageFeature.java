package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StorageFeature implements Supplier {
    private static StorageFeature INSTANCE = new StorageFeature();
    private final Supplier supplier;

    public StorageFeature() {
        this(Suppliers.ofInstance(new StorageFeatureFlagsImpl()));
    }

    public static SystemHealthProto$SamplingParameters storageSamplingParameters(Context context) {
        return INSTANCE.get().storageSamplingParameters(context);
    }

    @Override // com.google.common.base.Supplier
    public StorageFeatureFlags get() {
        return (StorageFeatureFlags) this.supplier.get();
    }

    public StorageFeature(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
