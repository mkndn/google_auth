package com.google.android.libraries.phenotype.client.stable;

import com.google.common.base.Supplier;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PhenotypeProcessReaper$$ExternalSyntheticLambda1 implements Supplier {
    public static final /* synthetic */ PhenotypeProcessReaper$$ExternalSyntheticLambda1 INSTANCE = new PhenotypeProcessReaper$$ExternalSyntheticLambda1();

    private /* synthetic */ PhenotypeProcessReaper$$ExternalSyntheticLambda1() {
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        boolean isKillable;
        isKillable = PhenotypeProcessReaper.isKillable();
        return Boolean.valueOf(isKillable);
    }
}
