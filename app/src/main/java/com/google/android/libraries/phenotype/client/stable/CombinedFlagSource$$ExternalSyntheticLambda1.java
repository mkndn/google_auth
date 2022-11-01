package com.google.android.libraries.phenotype.client.stable;

import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.phenotype.client.stable.FlagStore;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class CombinedFlagSource$$ExternalSyntheticLambda1 implements FlagStore.FlagStoreSupplier {
    public static final /* synthetic */ CombinedFlagSource$$ExternalSyntheticLambda1 INSTANCE = new CombinedFlagSource$$ExternalSyntheticLambda1();

    private /* synthetic */ CombinedFlagSource$$ExternalSyntheticLambda1() {
    }

    @Override // com.google.android.libraries.phenotype.client.stable.FlagStore.FlagStoreSupplier
    public final FlagStore apply(PhenotypeContext phenotypeContext, String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, Set set) {
        return new MobStoreFlagStore(phenotypeContext, str, str2, z, z2, z3, z4, set);
    }
}
