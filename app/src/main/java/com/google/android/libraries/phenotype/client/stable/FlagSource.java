package com.google.android.libraries.phenotype.client.stable;

import com.google.android.libraries.phenotype.client.PhenotypeContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface FlagSource {
    Object get(PhenotypeContext phenotypeContext, String str, String str2, String str3);

    PackageVersionCache getVersionCache(PhenotypeContext phenotypeContext, String str, String str2);
}
