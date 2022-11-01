package com.google.android.libraries.performance.primes.transmitter;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface AccountProvider {
    public static final AccountProvider NOOP_PROVIDER = AccountProvider$$ExternalSyntheticLambda0.INSTANCE;

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.transmitter.AccountProvider$-CC  reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        static {
            AccountProvider accountProvider = AccountProvider.NOOP_PROVIDER;
        }
    }

    ListenableFuture getAccountName();
}
