package com.google.android.libraries.performance.primes;

import android.os.Build;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Primes {
    private static final Primes DEFAULT_PRIMES;
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/Primes");
    private static volatile Primes primes;
    private static volatile boolean warningNotYetLogged;
    private final PrimesApi primesApi;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface PrimesProvider {
        Primes providePrimes();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Primes(PrimesApi primesApi) {
        this.primesApi = (PrimesApi) Preconditions.checkNotNull(primesApi);
    }

    public static synchronized Primes initialize(PrimesProvider primesProvider) {
        Primes primes2;
        synchronized (Primes.class) {
            if (isInitialized()) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/Primes", "initialize", 112, "Primes.java")).log("Primes.initialize() is called more than once. This call will be ignored.");
            } else {
                if (!ThreadUtil.isMainThread()) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/Primes", "initialize", 116, "Primes.java")).log("Primes.initialize() should only be called from the main thread.");
                }
                primes = primesProvider.providePrimes();
                if (TestingInstrumentationHolder.instance != null) {
                    TestingInstrumentationHolder.instance.initializePrimes();
                }
            }
            primes2 = primes;
        }
        return primes2;
    }

    public static boolean isInitialized() {
        return primes != DEFAULT_PRIMES;
    }

    public static boolean isPrimesSupported() {
        if (Build.VERSION.SDK_INT < 16) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/Primes", "isPrimesSupported", 854, "Primes.java")).log("Primes calls will be ignored. API's < 16 are not supported.");
            return false;
        }
        return true;
    }

    public void startCrashMonitor() {
        this.primesApi.startCrashMonitor();
    }

    public void startMemoryMonitor() {
        this.primesApi.startMemoryMonitor();
    }

    static {
        Primes primes2 = new Primes(new NoopPrimesApi());
        DEFAULT_PRIMES = primes2;
        warningNotYetLogged = true;
        primes = primes2;
    }
}
