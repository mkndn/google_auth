package com.google.android.libraries.phenotype.client.stable;

import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.phenotype.client.SafeHashMap;
import com.google.android.libraries.phenotype.client.stable.FlagStore;
import com.google.android.libraries.phenotype.client.stable.PhenotypeStickyAccount;
import com.google.android.libraries.phenotype.client.stable.PhenotypeUpdateBroadcastReceiver;
import com.google.common.base.Pair;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class FlagStore {
    private static final Registry SHARED_REGISTRY = new Registry();
    final String account;
    protected final boolean accountScoped;
    protected final boolean canInvalidate;
    final String configPackage;
    final PhenotypeContext context;
    private final Set logSourceNames;
    private final boolean stickyAccountSupport;
    final ProcessStableFlagCache cache = new ProcessStableFlagCache(new Supplier() { // from class: com.google.android.libraries.phenotype.client.stable.FlagStore$$ExternalSyntheticLambda0
        @Override // com.google.common.base.Supplier
        public final Object get() {
            return FlagStore.this.readFlags();
        }
    });
    final PackageVersionCache packageVersionCache = new PackageVersionCache();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface FlagStoreSupplier {
        FlagStore apply(PhenotypeContext phenotypeContext, String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, Set set);
    }

    public FlagStore(PhenotypeContext phenotypeContext, String str, String str2, boolean z, boolean z2, boolean z3, Set set) {
        this.context = phenotypeContext;
        this.configPackage = str;
        this.account = str2;
        this.stickyAccountSupport = z;
        this.canInvalidate = z2;
        this.accountScoped = z3;
        this.logSourceNames = set;
    }

    public static Registry getRegistry() {
        return SHARED_REGISTRY;
    }

    public abstract ListenableFuture commitToSnapshot();

    public final String getAccountForQuery() {
        if (!this.stickyAccountSupport) {
            return this.account;
        }
        return PhenotypeStickyAccount.getAccount(this.context.getContext(), this.configPackage);
    }

    public final Object getFlag(String str) {
        return this.cache.get(str);
    }

    public final Set getLogSourceNames() {
        return this.logSourceNames;
    }

    public final PackageVersionCache getPackageVersionCache() {
        return this.packageVersionCache;
    }

    public abstract void handleFlagUpdates();

    public abstract Map readFlags();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Registry {
        private final SafeHashMap instancesByAccountPackage;

        private Registry() {
            this.instancesByAccountPackage = SafeHashMap.newSafeHashMap();
        }

        public static /* synthetic */ FlagStore lambda$register$0(FlagStoreSupplier flagStoreSupplier, PhenotypeContext phenotypeContext, String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, Set set) {
            return flagStoreSupplier.apply(phenotypeContext, str, str2, z, z2, z3, z4, set);
        }

        public static /* synthetic */ void lambda$register$1(FlagStore flagStore, String str) {
            flagStore.handleFlagUpdates();
        }

        public FlagStore register(PhenotypeContext phenotypeContext, String str, String str2, boolean z, Supplier supplier) {
            Pair of = Pair.of(str, str2);
            final FlagStore flagStore = (FlagStore) this.instancesByAccountPackage.get(of);
            if (flagStore == null) {
                flagStore = (FlagStore) supplier.get();
                FlagStore flagStore2 = (FlagStore) this.instancesByAccountPackage.putIfAbsent(of, flagStore);
                if (flagStore2 != null) {
                    flagStore = flagStore2;
                } else {
                    PhenotypeUpdateBroadcastReceiver.registerCallback(phenotypeContext.getContext(), of, new PhenotypeUpdateBroadcastReceiver.Callback() { // from class: com.google.android.libraries.phenotype.client.stable.FlagStore$Registry$$ExternalSyntheticLambda0
                        @Override // com.google.android.libraries.phenotype.client.stable.PhenotypeUpdateBroadcastReceiver.Callback
                        public final void onUpdateReceived(String str3) {
                            FlagStore.Registry.lambda$register$1(FlagStore.this, str3);
                        }
                    });
                    if (z) {
                        PhenotypeStickyAccount.registerListener(str, new PhenotypeStickyAccount.Listener() { // from class: com.google.android.libraries.phenotype.client.stable.FlagStore$Registry$$ExternalSyntheticLambda1
                        });
                    } else {
                        flagStore.getClass();
                        PhenotypeAccountStore.registerCommitter(of, new Supplier() { // from class: com.google.android.libraries.phenotype.client.stable.FlagStore$Registry$$ExternalSyntheticLambda2
                            @Override // com.google.common.base.Supplier
                            public final Object get() {
                                return FlagStore.this.commitToSnapshot();
                            }
                        });
                    }
                }
            }
            Preconditions.checkArgument(flagStore.stickyAccountSupport == z, "Package %s cannot be registered both with and without stickyAccountSupport", str);
            return flagStore;
        }

        public FlagStore register(final PhenotypeContext phenotypeContext, final String str, final String str2, final boolean z, final boolean z2, final boolean z3, final boolean z4, final Set set, final FlagStoreSupplier flagStoreSupplier) {
            if (!str2.isEmpty() && z) {
                throw new IllegalStateException("Configuration for " + str + " can not enable_sticky_account");
            }
            return register(phenotypeContext, str, str2, z, new Supplier() { // from class: com.google.android.libraries.phenotype.client.stable.FlagStore$Registry$$ExternalSyntheticLambda3
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return FlagStore.Registry.lambda$register$0(FlagStore.FlagStoreSupplier.this, phenotypeContext, str, str2, z, z2, z3, z4, set);
                }
            });
        }
    }
}
