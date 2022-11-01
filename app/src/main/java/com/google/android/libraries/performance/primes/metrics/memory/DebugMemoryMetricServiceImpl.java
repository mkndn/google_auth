package com.google.android.libraries.performance.primes.metrics.memory;

import com.google.android.libraries.clock.Clock;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
final class DebugMemoryMetricServiceImpl extends DebugMemoryMetricService {
    private final Clock clock;
    private final Provider configProvider;
    private final Executor deferredExecutor;
    private final MemoryMetricService memoryMetricService;
    private long timestampLastRecordingMs = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public DebugMemoryMetricServiceImpl(Provider provider, Executor executor, MemoryMetricService memoryMetricService, Clock clock) {
        this.configProvider = provider;
        this.deferredExecutor = executor;
        this.memoryMetricService = memoryMetricService;
        this.clock = clock;
    }
}
