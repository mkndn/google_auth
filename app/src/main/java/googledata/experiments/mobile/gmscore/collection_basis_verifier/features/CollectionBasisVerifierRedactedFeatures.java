package googledata.experiments.mobile.gmscore.collection_basis_verifier.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionBasisVerifierRedactedFeatures implements Supplier {
    private static CollectionBasisVerifierRedactedFeatures INSTANCE = new CollectionBasisVerifierRedactedFeatures();
    private final Supplier supplier;

    public CollectionBasisVerifierRedactedFeatures() {
        this(Suppliers.ofInstance(new CollectionBasisVerifierRedactedFeaturesFlagsImpl()));
    }

    public static boolean compiled() {
        return INSTANCE.get().compiled();
    }

    public static boolean overrideEnableAllFeatures() {
        return INSTANCE.get().overrideEnableAllFeatures();
    }

    public static boolean overrideEnableLogging() {
        return INSTANCE.get().overrideEnableLogging();
    }

    public static boolean overrideEnableLoggingFieldNotAnnotated() {
        return INSTANCE.get().overrideEnableLoggingFieldNotAnnotated();
    }

    public static boolean overrideEnableLoggingUcNeverCollect() {
        return INSTANCE.get().overrideEnableLoggingUcNeverCollect();
    }

    public static boolean overrideEnableUsingLogVerifierResult() {
        return INSTANCE.get().overrideEnableUsingLogVerifierResult();
    }

    public static boolean reportFailuresToLogcat() {
        return INSTANCE.get().reportFailuresToLogcat();
    }

    @Override // com.google.common.base.Supplier
    public CollectionBasisVerifierRedactedFeaturesFlags get() {
        return (CollectionBasisVerifierRedactedFeaturesFlags) this.supplier.get();
    }

    public CollectionBasisVerifierRedactedFeatures(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
