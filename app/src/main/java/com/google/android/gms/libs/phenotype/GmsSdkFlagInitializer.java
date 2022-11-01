package com.google.android.gms.libs.phenotype;

import android.content.Context;
import com.google.android.libraries.phenotype.client.PhenotypeFlag;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GmsSdkFlagInitializer {
    public static void initPhenotypeForSdk(Context context) {
        PhenotypeFlag.maybeInit(context);
    }
}
