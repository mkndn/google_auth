package googledata.experiments.mobile.gmscore.collection_basis_verifier.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionBasisVerifierFeatures implements Supplier {
    private static CollectionBasisVerifierFeatures INSTANCE = new CollectionBasisVerifierFeatures();
    private final Supplier supplier;

    public CollectionBasisVerifierFeatures() {
        this(Suppliers.ofInstance(new CollectionBasisVerifierFeaturesFlagsImpl()));
    }

    public static boolean enableAllFeatures() {
        return INSTANCE.get().enableAllFeatures();
    }

    public static boolean enableLogging() {
        return INSTANCE.get().enableLogging();
    }

    public static boolean enableLoggingFieldNotAnnotated() {
        return INSTANCE.get().enableLoggingFieldNotAnnotated();
    }

    public static boolean enableLoggingUcNeverCollect() {
        return INSTANCE.get().enableLoggingUcNeverCollect();
    }

    public static boolean enableUsingLogVerifierResult() {
        return INSTANCE.get().enableUsingLogVerifierResult();
    }

    public static long failureLogCooldownPeriodMs() {
        return INSTANCE.get().failureLogCooldownPeriodMs();
    }

    public static long maxStackTraceSize() {
        return INSTANCE.get().maxStackTraceSize();
    }

    public static long minAppVersionCodeToLog() {
        return INSTANCE.get().minAppVersionCodeToLog();
    }

    @Override // com.google.common.base.Supplier
    public CollectionBasisVerifierFeaturesFlags get() {
        return (CollectionBasisVerifierFeaturesFlags) this.supplier.get();
    }

    public CollectionBasisVerifierFeatures(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
