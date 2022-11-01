package com.google.android.libraries.phenotype.client.lockdown.resources;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableSetMultimap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class FlagExemptionResource$$ExternalSyntheticLambda0 implements Supplier {
    public static final /* synthetic */ FlagExemptionResource$$ExternalSyntheticLambda0 INSTANCE = new FlagExemptionResource$$ExternalSyntheticLambda0();

    private /* synthetic */ FlagExemptionResource$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        ImmutableSetMultimap exemptions;
        exemptions = FlagExemptionResource.getExemptions();
        return exemptions;
    }
}
