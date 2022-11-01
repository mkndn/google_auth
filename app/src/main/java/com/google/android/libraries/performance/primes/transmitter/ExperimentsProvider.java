package com.google.android.libraries.performance.primes.transmitter;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ExperimentsProvider {
    public static final ExperimentsProvider NOOP_PROVIDER = ExperimentsProvider$$ExternalSyntheticLambda0.INSTANCE;

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.transmitter.ExperimentsProvider$-CC  reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        static {
            ExperimentsProvider experimentsProvider = ExperimentsProvider.NOOP_PROVIDER;
        }
    }

    ListenableFuture getExperimentIds();
}
