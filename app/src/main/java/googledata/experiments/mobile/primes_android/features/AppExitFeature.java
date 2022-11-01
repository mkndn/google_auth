package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import logs.proto.wireless.performance.mobile.ApplicationExitReasons;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppExitFeature implements Supplier {
    private static AppExitFeature INSTANCE = new AppExitFeature();
    private final Supplier supplier;

    public AppExitFeature() {
        this(Suppliers.ofInstance(new AppExitFeatureFlagsImpl()));
    }

    public static boolean appExitCollectionEnabled(Context context) {
        return INSTANCE.get().appExitCollectionEnabled(context);
    }

    public static ApplicationExitReasons appExitReasonsToReport(Context context) {
        return INSTANCE.get().appExitReasonsToReport(context);
    }

    @Override // com.google.common.base.Supplier
    public AppExitFeatureFlags get() {
        return (AppExitFeatureFlags) this.supplier.get();
    }

    public AppExitFeature(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
