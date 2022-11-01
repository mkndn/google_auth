package com.google.android.libraries.phenotype.client.stable;

import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda2 implements ProcessStablePhenotypeFlagFactory.Converter {
    public static final /* synthetic */ ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda2 INSTANCE = new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda2();

    private /* synthetic */ ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda2() {
    }

    @Override // com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory.Converter
    public final Object convert(Object obj) {
        return Boolean.valueOf(Boolean.parseBoolean((String) obj));
    }
}
