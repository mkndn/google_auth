package com.google.android.libraries.consentverifier.initializer;

import android.util.Log;
import com.google.android.gms.phenotype.Phenotype;
import com.google.android.gms.phenotype.PhenotypeClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.consentverifier.CollectionBasisContext;
import com.google.android.libraries.consentverifier.CollectionBasisUtil;
import com.google.android.libraries.consentverifier.logging.AppInfoHelper;
import com.google.android.libraries.consentverifier.threading.ExecutorProvider;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.phenotype.client.PhenotypeFlagInitializer;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Initializer {
    private static final String[] LOG_SOURCES = {"COLLECTION_BASIS_VERIFIER"};
    private static boolean flagInitialized = false;
    private static final Object initializerLock = new Object();

    private static void commitFlags(CollectionBasisContext collectionBasisContext, AppInfoHelper appInfoHelper) {
        final PhenotypeClient phenotype = Phenotype.getInstance(collectionBasisContext.context());
        final String str = "com.google.android.libraries.consentverifier#" + collectionBasisContext.context().getPackageName();
        Task register = phenotype.register(str, appInfoHelper.getVersionCode(collectionBasisContext.context()), LOG_SOURCES, null);
        final Executor executor = ExecutorProvider.getExecutor(collectionBasisContext);
        try {
            register.addOnSuccessListener(executor, new OnSuccessListener() { // from class: com.google.android.libraries.consentverifier.initializer.Initializer$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    Initializer.lambda$commitFlags$1(PhenotypeClient.this, str, executor, (Void) obj);
                }
            }).addOnFailureListener(executor, new OnFailureListener() { // from class: com.google.android.libraries.consentverifier.initializer.Initializer$$ExternalSyntheticLambda2
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    Initializer.lambda$commitFlags$2(str, exc);
                }
            });
        } catch (RejectedExecutionException e) {
            Log.w("CBVerifier", String.format("Execution failure when updating phenotypeflags for %s. %s", str, e));
        }
    }

    public static /* synthetic */ void lambda$commitFlags$0(String str, Exception exc) {
        Log.w("CBVerifier", String.format("Committing phenotypeflags for %s failed. %s", str, exc));
    }

    public static /* synthetic */ void lambda$commitFlags$2(String str, Exception exc) {
        Log.w("CBVerifier", String.format("Fail to register phenotypeflags for %s. %s", str, exc));
    }

    public void maybeInit(CollectionBasisContext collectionBasisContext, AppInfoHelper appInfoHelper) {
        if (!flagInitialized) {
            synchronized (initializerLock) {
                if (!flagInitialized) {
                    flagInitialized = true;
                    PhenotypeContext.setContext(collectionBasisContext.context());
                    PhenotypeFlagInitializer.maybeInit(collectionBasisContext.context());
                    if (!CollectionBasisUtil.isRunningInGmsCore(collectionBasisContext.context())) {
                        commitFlags(collectionBasisContext, appInfoHelper);
                    }
                }
            }
        }
    }

    public static /* synthetic */ void lambda$commitFlags$1(PhenotypeClient phenotypeClient, final String str, Executor executor, Void r3) {
        phenotypeClient.commitToCurrentConfiguration(str, "").addOnFailureListener(executor, new OnFailureListener() { // from class: com.google.android.libraries.consentverifier.initializer.Initializer$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                Initializer.lambda$commitFlags$0(str, exc);
            }
        });
    }
}
