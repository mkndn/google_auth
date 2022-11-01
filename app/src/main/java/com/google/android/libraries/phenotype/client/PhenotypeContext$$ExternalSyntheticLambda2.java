package com.google.android.libraries.phenotype.client;

import java.util.concurrent.ThreadFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PhenotypeContext$$ExternalSyntheticLambda2 implements ThreadFactory {
    public static final /* synthetic */ PhenotypeContext$$ExternalSyntheticLambda2 INSTANCE = new PhenotypeContext$$ExternalSyntheticLambda2();

    private /* synthetic */ PhenotypeContext$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return PhenotypeContext.lambda$static$0(runnable);
    }
}
