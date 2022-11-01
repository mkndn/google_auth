package googledata.experiments.mobile.authenticator_android.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HideCodesFeature implements Supplier {
    private static HideCodesFeature INSTANCE = new HideCodesFeature();
    private final Supplier supplier;

    public HideCodesFeature() {
        this(Suppliers.ofInstance(new HideCodesFeatureFlagsImpl()));
    }

    public static boolean enableHideCodes() {
        return INSTANCE.get().enableHideCodes();
    }

    @Override // com.google.common.base.Supplier
    public HideCodesFeatureFlags get() {
        return (HideCodesFeatureFlags) this.supplier.get();
    }

    public HideCodesFeature(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
