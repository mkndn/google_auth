package com.google.android.libraries.performance.primes.metrics.startup;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class StartupMetricRecordingService$$ExternalSyntheticLambda1 implements StartupMetricCustomTimestampProvider {
    public static final /* synthetic */ StartupMetricRecordingService$$ExternalSyntheticLambda1 INSTANCE = new StartupMetricRecordingService$$ExternalSyntheticLambda1();

    private /* synthetic */ StartupMetricRecordingService$$ExternalSyntheticLambda1() {
    }

    @Override // com.google.android.libraries.performance.primes.metrics.startup.StartupMetricCustomTimestampProvider
    public final ListenableFuture getCustomTimestamps() {
        ListenableFuture immediateFuture;
        immediateFuture = Futures.immediateFuture(Optional.absent());
        return immediateFuture;
    }
}
