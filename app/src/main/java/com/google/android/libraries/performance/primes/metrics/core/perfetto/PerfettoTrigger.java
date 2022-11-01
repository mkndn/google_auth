package com.google.android.libraries.performance.primes.metrics.core.perfetto;

import com.google.common.base.Function;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class PerfettoTrigger {
    static final long AT_MOST_TRIGGER_ONCE_EVERY_X_MS = TimeUnit.MINUTES.toMillis(1);
    private volatile Process lastTriggerProcess;
    private final Stopwatch stopwatch;
    private volatile boolean hasFailed = false;
    public Function newPerfettoProcessForTriggerName = new Function() { // from class: com.google.android.libraries.performance.primes.metrics.core.perfetto.PerfettoTrigger$$ExternalSyntheticLambda0
        @Override // com.google.common.base.Function
        public final Object apply(Object obj) {
            return PerfettoTrigger.this.m310x9ff5f038((String) obj);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public PerfettoTrigger(Ticker ticker) {
        this.stopwatch = Stopwatch.createUnstarted(ticker);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-google-android-libraries-performance-primes-metrics-core-perfetto-PerfettoTrigger  reason: not valid java name */
    public /* synthetic */ Process m310x9ff5f038(String str) {
        try {
            return new ProcessBuilder("/system/bin/trigger_perfetto", str).start();
        } catch (IOException e) {
            this.hasFailed = true;
            return null;
        }
    }

    public void trigger(String str) {
        if (str.isEmpty()) {
            return;
        }
        if (this.lastTriggerProcess != null) {
            try {
                if (this.lastTriggerProcess.exitValue() != 0) {
                    this.hasFailed = true;
                    this.lastTriggerProcess = null;
                }
            } catch (IllegalThreadStateException e) {
                return;
            }
        }
        if (this.hasFailed) {
            return;
        }
        synchronized (this) {
            if (!this.stopwatch.isRunning() || this.stopwatch.elapsed(TimeUnit.MILLISECONDS) >= AT_MOST_TRIGGER_ONCE_EVERY_X_MS) {
                this.stopwatch.reset();
                this.stopwatch.start();
                this.lastTriggerProcess = (Process) this.newPerfettoProcessForTriggerName.apply(str);
            }
        }
    }
}
