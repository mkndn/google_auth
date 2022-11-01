package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricTransmitterFeature implements Supplier {
    private static MetricTransmitterFeature INSTANCE = new MetricTransmitterFeature();
    private final Supplier supplier;

    public MetricTransmitterFeature() {
        this(Suppliers.ofInstance(new MetricTransmitterFeatureFlagsImpl()));
    }

    public static boolean disableClearcutLoggingInTests(Context context) {
        return INSTANCE.get().disableClearcutLoggingInTests(context);
    }

    public static boolean enableDelphiCollectionBasisLogVerifier(Context context) {
        return INSTANCE.get().enableDelphiCollectionBasisLogVerifier(context);
    }

    public static boolean usePackedHistogramEncodingInClearcut(Context context) {
        return INSTANCE.get().usePackedHistogramEncodingInClearcut(context);
    }

    @Override // com.google.common.base.Supplier
    public MetricTransmitterFeatureFlags get() {
        return (MetricTransmitterFeatureFlags) this.supplier.get();
    }

    public MetricTransmitterFeature(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
