package googledata.experiments.mobile.primes_android.features;

import android.content.Context;
import com.google.android.libraries.performance.primes.metrics.crash.CrashRecordingTimeouts;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashFeature implements Supplier {
    private static CrashFeature INSTANCE = new CrashFeature();
    private final Supplier supplier;

    public CrashFeature() {
        this(Suppliers.ofInstance(new CrashFeatureFlagsImpl()));
    }

    public static CrashRecordingTimeouts recordingTimeouts(Context context) {
        return INSTANCE.get().recordingTimeouts(context);
    }

    @Override // com.google.common.base.Supplier
    public CrashFeatureFlags get() {
        return (CrashFeatureFlags) this.supplier.get();
    }

    public CrashFeature(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
