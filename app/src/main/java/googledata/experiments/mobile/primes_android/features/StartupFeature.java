package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StartupFeature implements Supplier {
    private static StartupFeature INSTANCE = new StartupFeature();
    private final Supplier supplier;

    public StartupFeature() {
        this(Suppliers.ofInstance(new StartupFeatureFlagsImpl()));
    }

    public static boolean enableStartupBaselineDiscarding(Context context) {
        return INSTANCE.get().enableStartupBaselineDiscarding(context);
    }

    public static long firstDrawType(Context context) {
        return INSTANCE.get().firstDrawType(context);
    }

    public static SystemHealthProto$SamplingParameters startupSamplingParameters(Context context) {
        return INSTANCE.get().startupSamplingParameters(context);
    }

    @Override // com.google.common.base.Supplier
    public StartupFeatureFlags get() {
        return (StartupFeatureFlags) this.supplier.get();
    }

    public StartupFeature(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
