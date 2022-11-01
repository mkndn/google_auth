package com.google.android.libraries.phenotype.client;

import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executors;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PhenotypeContext$$ExternalSyntheticLambda3 implements Supplier {
    public static final /* synthetic */ PhenotypeContext$$ExternalSyntheticLambda3 INSTANCE = new PhenotypeContext$$ExternalSyntheticLambda3();

    private /* synthetic */ PhenotypeContext$$ExternalSyntheticLambda3() {
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        ListeningScheduledExecutorService listeningDecorator;
        listeningDecorator = MoreExecutors.listeningDecorator(Executors.newSingleThreadScheduledExecutor(PhenotypeContext$$ExternalSyntheticLambda2.INSTANCE));
        return listeningDecorator;
    }
}
