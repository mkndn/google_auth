package com.google.android.libraries.phenotype.client.stable;

import com.google.android.libraries.phenotype.client.api.Configurations;
import com.google.common.base.Function;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class SnapshotHandler$$ExternalSyntheticLambda1 implements Function {
    public static final /* synthetic */ SnapshotHandler$$ExternalSyntheticLambda1 INSTANCE = new SnapshotHandler$$ExternalSyntheticLambda1();

    private /* synthetic */ SnapshotHandler$$ExternalSyntheticLambda1() {
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        return SnapshotHandler.configurationToSnapshot((Configurations) obj);
    }
}
