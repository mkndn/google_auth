package com.google.android.libraries.phenotype.client.lockdown.resources;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSetMultimap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FlagExemptionResource {
    public static final Supplier exemptions = Suppliers.memoize(FlagExemptionResource$$ExternalSyntheticLambda0.INSTANCE);

    /* JADX INFO: Access modifiers changed from: private */
    public static ImmutableSetMultimap getExemptions() {
        return new ImmutableSetMultimap.Builder().build();
    }
}
