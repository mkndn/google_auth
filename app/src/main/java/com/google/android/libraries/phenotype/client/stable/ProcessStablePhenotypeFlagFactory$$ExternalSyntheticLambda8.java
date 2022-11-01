package com.google.android.libraries.phenotype.client.stable;

import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda8 implements ProcessStablePhenotypeFlagFactory.Converter {
    public static final /* synthetic */ ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda8 INSTANCE = new ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda8();

    private /* synthetic */ ProcessStablePhenotypeFlagFactory$$ExternalSyntheticLambda8() {
    }

    @Override // com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory.Converter
    public final Object convert(Object obj) {
        return Long.valueOf(Long.parseLong((String) obj));
    }
}
