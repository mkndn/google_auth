package com.google.android.libraries.performance.primes;

import com.google.common.flogger.GoogleLogger;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;

/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class Shutdown {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/Shutdown");
    final List listeners = new ArrayList();
    private volatile boolean shutdown;

    public final boolean isShutdown() {
        return this.shutdown;
    }

    public final synchronized void shutdown() {
        if (!this.shutdown) {
            this.shutdown = true;
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/Shutdown", "shutdown", 37, "Shutdown.java")).log("Shutdown ...");
            synchronized (this.listeners) {
                for (ShutdownListener shutdownListener : this.listeners) {
                    try {
                        shutdownListener.onShutdown();
                    } catch (RuntimeException e) {
                        ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/Shutdown", "shutdown", 43, "Shutdown.java")).log("ShutdownListener crashed");
                    }
                }
                this.listeners.clear();
                ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/Shutdown", "shutdown", 47, "Shutdown.java")).log("All ShutdownListeners notified.");
            }
        }
    }
}
