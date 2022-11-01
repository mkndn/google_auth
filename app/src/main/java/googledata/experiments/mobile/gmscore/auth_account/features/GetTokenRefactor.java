package googledata.experiments.mobile.gmscore.auth_account.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.protos.experiments.proto.TypedFeatures$StringListParam;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GetTokenRefactor implements Supplier {
    private static GetTokenRefactor INSTANCE = new GetTokenRefactor();
    private final Supplier supplier;

    public GetTokenRefactor() {
        this(Suppliers.ofInstance(new GetTokenRefactorFlagsImpl()));
    }

    public static TypedFeatures$StringListParam blockedPackages() {
        return INSTANCE.get().blockedPackages();
    }

    public static boolean gaulTokenApiEvolved() {
        return INSTANCE.get().gaulTokenApiEvolved();
    }

    @Override // com.google.common.base.Supplier
    public GetTokenRefactorFlags get() {
        return (GetTokenRefactorFlags) this.supplier.get();
    }

    public GetTokenRefactor(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
