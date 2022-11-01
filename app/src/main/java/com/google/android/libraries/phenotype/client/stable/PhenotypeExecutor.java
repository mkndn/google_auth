package com.google.android.libraries.phenotype.client.stable;

import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.ExecutionException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeExecutor {
    public static void crashOnFailure(final ListenableFuture listenableFuture) {
        listenableFuture.addListener(new Runnable() { // from class: com.google.android.libraries.phenotype.client.stable.PhenotypeExecutor$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PhenotypeExecutor.lambda$crashOnFailure$1(ListenableFuture.this);
            }
        }, MoreExecutors.directExecutor());
    }

    public static /* synthetic */ void lambda$crashOnFailure$0(ExecutionException executionException) {
        throw new RuntimeException(executionException.getCause());
    }

    public static /* synthetic */ void lambda$crashOnFailure$1(ListenableFuture listenableFuture) {
        try {
            Futures.getDone(listenableFuture);
        } catch (ExecutionException e) {
            ThreadUtil.postOnMainThread(new Runnable() { // from class: com.google.android.libraries.phenotype.client.stable.PhenotypeExecutor$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    PhenotypeExecutor.lambda$crashOnFailure$0(e);
                }
            });
        }
    }
}
