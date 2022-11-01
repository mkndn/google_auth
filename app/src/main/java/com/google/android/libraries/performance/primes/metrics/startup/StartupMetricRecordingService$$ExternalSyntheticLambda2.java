package com.google.android.libraries.performance.primes.metrics.startup;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class StartupMetricRecordingService$$ExternalSyntheticLambda2 implements StartupMetricExtensionProvider {
    public static final /* synthetic */ StartupMetricRecordingService$$ExternalSyntheticLambda2 INSTANCE = new StartupMetricRecordingService$$ExternalSyntheticLambda2();

    private /* synthetic */ StartupMetricRecordingService$$ExternalSyntheticLambda2() {
    }

    @Override // com.google.android.libraries.performance.primes.metrics.startup.StartupMetricExtensionProvider
    public final ListenableFuture getMetricExtension() {
        ListenableFuture immediateFuture;
        immediateFuture = Futures.immediateFuture(Optional.absent());
        return immediateFuture;
    }
}
