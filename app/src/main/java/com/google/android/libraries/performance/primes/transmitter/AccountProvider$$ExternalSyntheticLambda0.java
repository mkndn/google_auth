package com.google.android.libraries.performance.primes.transmitter;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AccountProvider$$ExternalSyntheticLambda0 implements AccountProvider {
    public static final /* synthetic */ AccountProvider$$ExternalSyntheticLambda0 INSTANCE = new AccountProvider$$ExternalSyntheticLambda0();

    private /* synthetic */ AccountProvider$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.android.libraries.performance.primes.transmitter.AccountProvider
    public final ListenableFuture getAccountName() {
        ListenableFuture immediateFuture;
        immediateFuture = Futures.immediateFuture(Optional.absent());
        return immediateFuture;
    }
}
