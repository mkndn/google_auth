package com.google.android.libraries.consentverifier.threading;

import com.google.android.libraries.consentverifier.CollectionBasisContext;
import com.google.android.libraries.consentverifier.CollectionBasisUtil;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExecutorProvider {
    public static Executor getExecutor(CollectionBasisContext collectionBasisContext) {
        if (collectionBasisContext.executor().isPresent()) {
            return (Executor) collectionBasisContext.executor().get();
        }
        if (CollectionBasisUtil.isRunningInGmsCore(collectionBasisContext.context())) {
            return GmscoreExecutorFactory.newBestEffortExecutor();
        }
        return AppExecutorFactory.newBestEffortExecutor();
    }
}
