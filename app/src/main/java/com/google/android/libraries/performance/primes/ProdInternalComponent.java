package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.InternalComponent;
import com.google.common.base.Supplier;
import java.util.Set;
import javax.inject.Singleton;

/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
interface ProdInternalComponent extends InternalComponent {

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.ProdInternalComponent$-CC  reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        public static Builder builder() {
            return DaggerProdInternalComponent.builder();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Builder extends InternalComponent.Builder {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NonDaggerInternalModule {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Singleton
        public static Set provideMetricTransmitters(Supplier supplier) {
            return (Set) supplier.get();
        }
    }
}
