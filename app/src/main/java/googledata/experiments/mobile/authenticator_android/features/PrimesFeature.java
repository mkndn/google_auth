package googledata.experiments.mobile.authenticator_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesFeature implements Supplier {
    private static PrimesFeature INSTANCE = new PrimesFeature();
    private final Supplier supplier;

    public PrimesFeature() {
        this(Suppliers.ofInstance(new PrimesFeatureFlagsImpl()));
    }

    public static boolean enableBatteryMetric() {
        return INSTANCE.get().enableBatteryMetric();
    }

    public static boolean enableCrashMetric() {
        return INSTANCE.get().enableCrashMetric();
    }

    public static boolean enableMemoryMetric() {
        return INSTANCE.get().enableMemoryMetric();
    }

    public static boolean enableNetworkMetric() {
        return INSTANCE.get().enableNetworkMetric();
    }

    public static boolean enablePackageMetric() {
        return INSTANCE.get().enablePackageMetric();
    }

    public static boolean enableTimerMetric() {
        return INSTANCE.get().enableTimerMetric();
    }

    @Override // com.google.common.base.Supplier
    public PrimesFeatureFlags get() {
        return (PrimesFeatureFlags) this.supplier.get();
    }

    public PrimesFeature(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
