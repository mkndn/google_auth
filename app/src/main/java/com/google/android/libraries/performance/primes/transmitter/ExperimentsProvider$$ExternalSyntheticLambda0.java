package com.google.android.libraries.performance.primes.transmitter;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ExperimentsProvider$$ExternalSyntheticLambda0 implements ExperimentsProvider {
    public static final /* synthetic */ ExperimentsProvider$$ExternalSyntheticLambda0 INSTANCE = new ExperimentsProvider$$ExternalSyntheticLambda0();

    private /* synthetic */ ExperimentsProvider$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.android.libraries.performance.primes.transmitter.ExperimentsProvider
    public final ListenableFuture getExperimentIds() {
        ListenableFuture immediateFuture;
        immediateFuture = Futures.immediateFuture(ImmutableList.of());
        return immediateFuture;
    }
}
