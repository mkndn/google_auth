package com.google.android.libraries.phenotype.client.stable;

import android.os.StrictMode;
import android.util.Log;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.phenotype.client.api.PhenotypeRuntimeException;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MobStoreFlagStore extends FlagStore {
    private volatile String retrievedSnapshotToken;
    private final SnapshotHandler snapshotHandler;

    public static /* synthetic */ void $r8$lambda$ubHIUbowM_rdo6QZjg43EpahbXE(MobStoreFlagStore mobStoreFlagStore) {
        mobStoreFlagStore.storeAccount();
    }

    public MobStoreFlagStore(PhenotypeContext phenotypeContext, String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, Set set) {
        super(phenotypeContext, str, str2, z, z3, z4, set);
        this.snapshotHandler = new SnapshotHandler(phenotypeContext, str, str2, z2);
    }

    public void storeAccount() {
        if (this.account.equals("")) {
            return;
        }
        final ListenableFuture addAccount = PhenotypeAccountStore.addAccount(this.context, this.configPackage, this.account);
        addAccount.addListener(new Runnable() { // from class: com.google.android.libraries.phenotype.client.stable.MobStoreFlagStore$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                MobStoreFlagStore.this.m375x34ddd914(addAccount);
            }
        }, this.context.getExecutor());
    }

    @Override // com.google.android.libraries.phenotype.client.stable.FlagStore
    public ListenableFuture commitToSnapshot() {
        if (this.retrievedSnapshotToken.isEmpty()) {
            return Futures.immediateVoidFuture();
        }
        return Futures.catchingAsync(this.context.getPhenotypeClient().commitToConfiguration(this.retrievedSnapshotToken), PhenotypeRuntimeException.class, new AsyncFunction() { // from class: com.google.android.libraries.phenotype.client.stable.MobStoreFlagStore$$ExternalSyntheticLambda2
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return MobStoreFlagStore.this.m372x38b0cbc9((PhenotypeRuntimeException) obj);
            }
        }, this.context.getExecutor());
    }

    @Override // com.google.android.libraries.phenotype.client.stable.FlagStore
    public void handleFlagUpdates() {
        final ListenableFuture latestSnapshot = this.snapshotHandler.getLatestSnapshot(getAccountForQuery());
        final SnapshotHandler snapshotHandler = this.snapshotHandler;
        snapshotHandler.getClass();
        Futures.transformAsync(latestSnapshot, new AsyncFunction() { // from class: com.google.android.libraries.phenotype.client.stable.MobStoreFlagStore$$ExternalSyntheticLambda0
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return SnapshotHandler.this.updateStoredSnapshot((SnapshotProto$Snapshot) obj);
            }
        }, this.context.getExecutor()).addListener(new Runnable() { // from class: com.google.android.libraries.phenotype.client.stable.MobStoreFlagStore$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                MobStoreFlagStore.this.m374x21a640a1(latestSnapshot);
            }
        }, this.context.getExecutor());
    }

    /* renamed from: lambda$commitToSnapshot$1$com-google-android-libraries-phenotype-client-stable-MobStoreFlagStore */
    public /* synthetic */ ListenableFuture m372x38b0cbc9(PhenotypeRuntimeException phenotypeRuntimeException) {
        if (phenotypeRuntimeException.getErrorCode() == 29501) {
            Log.w("MobStoreFlagStore", "Failed to commit due to stale snapshot for " + this.configPackage + ", triggering flag update.", phenotypeRuntimeException);
            handleFlagUpdates();
        }
        return Futures.immediateFailedFuture(phenotypeRuntimeException);
    }

    /* renamed from: lambda$handleFlagUpdates$2$com-google-android-libraries-phenotype-client-stable-MobStoreFlagStore */
    public /* synthetic */ Void m373x9f5b8bc2(Throwable th) {
        Log.w("MobStoreFlagStore", "Failed to commit to updated flags for " + this.configPackage, th);
        return null;
    }

    /* renamed from: lambda$handleFlagUpdates$3$com-google-android-libraries-phenotype-client-stable-MobStoreFlagStore */
    public /* synthetic */ void m374x21a640a1(ListenableFuture listenableFuture) {
        try {
            SnapshotProto$Snapshot snapshotProto$Snapshot = (SnapshotProto$Snapshot) Futures.getDone(listenableFuture);
            if (this.cache.applyIfCompatible(SnapshotHandler.snapshotToMap(snapshotProto$Snapshot), this.canInvalidate)) {
                getPackageVersionCache().increment();
                if (this.canInvalidate) {
                    Futures.catching(this.context.getPhenotypeClient().commitToConfiguration(snapshotProto$Snapshot.getSnapshotToken()), Throwable.class, new Function() { // from class: com.google.android.libraries.phenotype.client.stable.MobStoreFlagStore$$ExternalSyntheticLambda7
                        @Override // com.google.common.base.Function
                        public final Object apply(Object obj) {
                            return MobStoreFlagStore.this.m373x9f5b8bc2((Throwable) obj);
                        }
                    }, this.context.getExecutor());
                }
            } else if (this.context.getProcessReaper() != null) {
                this.context.getProcessReaper().scheduleReap();
            }
        } catch (CancellationException | ExecutionException e) {
            Log.w("MobStoreFlagStore", "Unable to update local snapshot for " + this.configPackage + ", may result in stale flags.", e);
        }
    }

    /* renamed from: lambda$storeAccount$0$com-google-android-libraries-phenotype-client-stable-MobStoreFlagStore */
    public /* synthetic */ void m375x34ddd914(ListenableFuture listenableFuture) {
        try {
            Futures.getDone(listenableFuture);
        } catch (Exception e) {
            Log.w("MobStoreFlagStore", "Failed to store account on flag read for: " + this.configPackage + " which may lead to stale flags.", e);
        }
    }

    @Override // com.google.android.libraries.phenotype.client.stable.FlagStore
    public ImmutableMap readFlags() {
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        SnapshotProto$Snapshot storedSnapshot = this.snapshotHandler.getStoredSnapshot();
        StrictMode.setThreadPolicy(allowThreadDiskWrites);
        if (!this.canInvalidate && storedSnapshot.getSnapshotToken().isEmpty()) {
            this.context.getExecutor().execute(new Runnable() { // from class: com.google.android.libraries.phenotype.client.stable.MobStoreFlagStore$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    MobStoreFlagStore.this.handleFlagUpdates();
                }
            });
            return ImmutableMap.of();
        }
        this.retrievedSnapshotToken = storedSnapshot.getSnapshotToken();
        this.context.getExecutor().execute(new Runnable() { // from class: com.google.android.libraries.phenotype.client.stable.MobStoreFlagStore$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                MobStoreFlagStore.this.commitToSnapshot();
            }
        });
        DefaultExperimentTokenDecorator.get().addToken(storedSnapshot.getExperimentToken(), getLogSourceNames(), this.account, this.configPackage);
        this.context.getExecutor().execute(new Runnable() { // from class: com.google.android.libraries.phenotype.client.stable.MobStoreFlagStore$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                MobStoreFlagStore.$r8$lambda$ubHIUbowM_rdo6QZjg43EpahbXE(MobStoreFlagStore.this);
            }
        });
        return SnapshotHandler.snapshotToMap(storedSnapshot);
    }
}
