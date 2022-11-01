package com.google.android.libraries.phenotype.client.stable;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.phenotype.client.SafeHashMap;
import com.google.android.libraries.phenotype.client.stable.AccountList;
import com.google.android.libraries.phenotype.client.stable.Accounts;
import com.google.android.libraries.storage.file.backends.AndroidUri;
import com.google.android.libraries.storage.protostore.ProtoDataStore;
import com.google.android.libraries.storage.protostore.ProtoDataStoreConfig;
import com.google.android.libraries.storage.protostore.ProtoDataStoreFactory;
import com.google.android.libraries.storage.protostore.ProtoDataStoreFactoryBuilder;
import com.google.android.libraries.storage.protostore.SingleProcProtoDataStore;
import com.google.android.libraries.storage.protostore.handlers.ReplaceFileIOExceptionHandler;
import com.google.common.base.Function;
import com.google.common.base.Pair;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeAccountStore {
    private static final SafeHashMap accountCommitterByPackage = SafeHashMap.newSafeHashMap();
    private static final ReplaceFileIOExceptionHandler exceptionHandler = new ReplaceFileIOExceptionHandler(Accounts.getDefaultInstance());
    private static final Object FACTORY_LOCK = new Object();
    private static volatile ProtoDataStoreFactory pdsFactory = null;

    public static ListenableFuture addAccount(PhenotypeContext phenotypeContext, final String str, final String str2) {
        return getAccountsStore(phenotypeContext).updateData(new Function() { // from class: com.google.android.libraries.phenotype.client.stable.PhenotypeAccountStore$$ExternalSyntheticLambda6
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return PhenotypeAccountStore.lambda$addAccount$3(str, str2, (Accounts) obj);
            }
        }, phenotypeContext.getExecutor());
    }

    private static ProtoDataStore getAccountsStore(PhenotypeContext phenotypeContext) {
        return getPdsFactory(phenotypeContext).getOrCreate(ProtoDataStoreConfig.builder().setUri(getUri(phenotypeContext.getContext())).setSchema(Accounts.getDefaultInstance()).setHandler(exceptionHandler).setEnableTracing(false).build());
    }

    static ProtoDataStoreFactory getPdsFactory(PhenotypeContext phenotypeContext) {
        ProtoDataStoreFactory protoDataStoreFactory = pdsFactory;
        if (protoDataStoreFactory == null) {
            synchronized (FACTORY_LOCK) {
                protoDataStoreFactory = pdsFactory;
                if (protoDataStoreFactory == null) {
                    ProtoDataStoreFactory build = new ProtoDataStoreFactoryBuilder().setExecutor(phenotypeContext.getExecutor()).setStorage(phenotypeContext.getStorageBackend()).addFactory(SingleProcProtoDataStore.factory()).build();
                    pdsFactory = build;
                    protoDataStoreFactory = build;
                }
            }
        }
        return protoDataStoreFactory;
    }

    private static Uri getUri(Context context) {
        return AndroidUri.builder(context).setModule("phenotype").setRelativePath("all_accounts.pb").build();
    }

    public static /* synthetic */ Accounts lambda$removeAccountGlobally$4(String str, Accounts accounts) {
        Accounts.Builder newBuilder = Accounts.newBuilder();
        for (Map.Entry entry : accounts.getAccountListsMap().entrySet()) {
            AccountList accountList = (AccountList) entry.getValue();
            AccountList.Builder newBuilder2 = AccountList.newBuilder();
            if (!accountList.getLatestAccount().equals(str)) {
                newBuilder2.setLatestAccount(accountList.getLatestAccount());
            }
            for (String str2 : accountList.getValuesList()) {
                if (!str2.equals(str)) {
                    newBuilder2.addValues(str2);
                }
            }
            newBuilder.putAccountLists((String) entry.getKey(), (AccountList) newBuilder2.build());
        }
        return (Accounts) newBuilder.build();
    }

    public static void registerCommitter(Pair pair, Supplier supplier) {
        accountCommitterByPackage.putIfAbsent(pair, supplier);
    }

    public static ListenableFuture removeSnapshotsForAccount(PhenotypeContext phenotypeContext, String str) {
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.add((Object) phenotypeContext.getContext());
        if (DirectBootUtils.supportsDirectBoot()) {
            builder.add((Object) DirectBootUtils.getDeviceProtectedStorageContextOrFallback(phenotypeContext.getContext()));
        }
        UnmodifiableIterator it = builder.build().iterator();
        boolean z = true;
        while (it.hasNext()) {
            File file = new File(String.valueOf(((Context) it.next()).getFilesDir()) + "/phenotype/shared/" + str);
            Log.i("PhenotypeAccountStore", "Removing snapshots for removed user");
            if (file.exists()) {
                z = deleteRecursively(file);
            }
        }
        if (z) {
            return Futures.immediateVoidFuture();
        }
        return Futures.immediateFailedFuture(new IOException("Unable to remove snapshots for removed user"));
    }

    private static boolean deleteRecursively(File file) {
        boolean z;
        if (file.isDirectory()) {
            z = true;
            for (File file2 : file.listFiles()) {
                z = z && deleteRecursively(file2);
            }
        } else {
            z = true;
        }
        return z && file.delete();
    }

    public static /* synthetic */ Accounts lambda$addAccount$3(String str, String str2, Accounts accounts) {
        AccountList.Builder builder = (AccountList.Builder) accounts.getAccountListsOrDefault(str, AccountList.getDefaultInstance()).toBuilder();
        if (!builder.getValuesList().contains(str2)) {
            builder.addValues(str2);
        }
        return (Accounts) ((Accounts.Builder) accounts.toBuilder()).putAccountLists(str, (AccountList) builder.setLatestAccount(str2).build()).build();
    }

    public static ListenableFuture removeAccountGlobally(final PhenotypeContext phenotypeContext, final String str) {
        return FluentFuture.from(getAccountsStore(phenotypeContext).updateData(new Function() { // from class: com.google.android.libraries.phenotype.client.stable.PhenotypeAccountStore$$ExternalSyntheticLambda4
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return PhenotypeAccountStore.lambda$removeAccountGlobally$4(str, (Accounts) obj);
            }
        }, phenotypeContext.getExecutor())).transformAsync(new AsyncFunction() { // from class: com.google.android.libraries.phenotype.client.stable.PhenotypeAccountStore$$ExternalSyntheticLambda5
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                ListenableFuture removeSnapshotsForAccount;
                Void r3 = (Void) obj;
                removeSnapshotsForAccount = PhenotypeAccountStore.removeSnapshotsForAccount(PhenotypeContext.this, str);
                return removeSnapshotsForAccount;
            }
        }, phenotypeContext.getExecutor());
    }
}
