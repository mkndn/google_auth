package googledata.experiments.mobile.gmscore.feedback.features;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Y2019W24Bugfixes implements Supplier {
    private static Y2019W24Bugfixes INSTANCE = new Y2019W24Bugfixes();
    private final Supplier supplier;

    public Y2019W24Bugfixes() {
        this(Suppliers.ofInstance(new Y2019W24BugfixesFlagsImpl()));
    }

    public static boolean throwOnSetScreenshotButNoPiiAllowed() {
        return INSTANCE.get().throwOnSetScreenshotButNoPiiAllowed();
    }

    @Override // com.google.common.base.Supplier
    public Y2019W24BugfixesFlags get() {
        return (Y2019W24BugfixesFlags) this.supplier.get();
    }

    public Y2019W24Bugfixes(Supplier supplier) {
        this.supplier = Suppliers.memoize(supplier);
    }
}
