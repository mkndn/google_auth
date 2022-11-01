package com.google.android.libraries.performance.primes.transmitter;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ZwiebackCookieOverrideProvider {
    public static final ZwiebackCookieOverrideProvider NOOP_PROVIDER = ZwiebackCookieOverrideProvider$$ExternalSyntheticLambda0.INSTANCE;

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.transmitter.ZwiebackCookieOverrideProvider$-CC  reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        static {
            ZwiebackCookieOverrideProvider zwiebackCookieOverrideProvider = ZwiebackCookieOverrideProvider.NOOP_PROVIDER;
        }
    }

    ListenableFuture getZwiebackCookieOverride();
}
