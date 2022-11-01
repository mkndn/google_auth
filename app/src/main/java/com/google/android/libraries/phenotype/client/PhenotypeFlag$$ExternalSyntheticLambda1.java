package com.google.android.libraries.phenotype.client;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PhenotypeFlag$$ExternalSyntheticLambda1 implements Runnable {
    public static final /* synthetic */ PhenotypeFlag$$ExternalSyntheticLambda1 INSTANCE = new PhenotypeFlag$$ExternalSyntheticLambda1();

    private /* synthetic */ PhenotypeFlag$$ExternalSyntheticLambda1() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        PhenotypeFlag.invalidateProcessCache();
    }
}
