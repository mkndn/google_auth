package com.google.android.libraries.performance.primes.transmitter;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ZwiebackCookieOverrideProvider$$ExternalSyntheticLambda0 implements ZwiebackCookieOverrideProvider {
    public static final /* synthetic */ ZwiebackCookieOverrideProvider$$ExternalSyntheticLambda0 INSTANCE = new ZwiebackCookieOverrideProvider$$ExternalSyntheticLambda0();

    private /* synthetic */ ZwiebackCookieOverrideProvider$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.android.libraries.performance.primes.transmitter.ZwiebackCookieOverrideProvider
    public final ListenableFuture getZwiebackCookieOverride() {
        ListenableFuture immediateFuture;
        immediateFuture = Futures.immediateFuture(Optional.absent());
        return immediateFuture;
    }
}
