package com.google.android.libraries.phenotype.client.api;

import com.google.android.gms.common.api.ApiException;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ThinPhenotypeClient$$ExternalSyntheticLambda1 implements AsyncFunction {
    public static final /* synthetic */ ThinPhenotypeClient$$ExternalSyntheticLambda1 INSTANCE = new ThinPhenotypeClient$$ExternalSyntheticLambda1();

    private /* synthetic */ ThinPhenotypeClient$$ExternalSyntheticLambda1() {
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final ListenableFuture apply(Object obj) {
        return ThinPhenotypeClient.lambda$toListenableFuture$0((ApiException) obj);
    }
}
