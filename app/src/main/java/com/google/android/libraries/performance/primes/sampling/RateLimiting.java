package com.google.android.libraries.performance.primes.sampling;

import com.google.android.libraries.clock.Clock;
import com.google.android.libraries.clock.impl.SystemClockImpl;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RateLimiting {
    private static final Clock CLOCK = new SystemClockImpl();
    private final Clock clock;
    private final Provider rateLimit;
    private final Object mutex = new Object();
    private int eventCount = 0;
    private long firstEventInLastSecond = 0;

    RateLimiting(Provider provider, Clock clock) {
        this.rateLimit = provider;
        this.clock = clock;
    }

    public static RateLimiting dynamic(Provider provider) {
        return new RateLimiting(provider, CLOCK);
    }

    public static RateLimiting fixed(final int i) {
        return dynamic(new Provider() { // from class: com.google.android.libraries.performance.primes.sampling.RateLimiting$$ExternalSyntheticLambda0
            @Override // javax.inject.Provider
            public final Object get() {
                return RateLimiting.lambda$fixed$0(i);
            }
        });
    }

    public static /* synthetic */ Integer lambda$fixed$0(int i) {
        return Integer.valueOf(i);
    }

    public static RateLimiting none() {
        return fixed(Integer.MAX_VALUE);
    }

    public void incrementEventCount() {
        long elapsedRealtime = this.clock.elapsedRealtime();
        synchronized (this.mutex) {
            this.eventCount++;
            if (elapsedRealtime - this.firstEventInLastSecond > 1000) {
                this.eventCount = 0;
                this.firstEventInLastSecond = elapsedRealtime;
            }
        }
    }

    public boolean isRateLimitExceeded() {
        return isRateLimitExceeded(((Integer) this.rateLimit.get()).intValue());
    }

    private boolean isRateLimitExceeded(int i) {
        if (i == 0) {
            return true;
        }
        if (i == Integer.MAX_VALUE) {
            return false;
        }
        synchronized (this.mutex) {
            if (this.eventCount < i) {
                return false;
            }
            return this.clock.elapsedRealtime() - this.firstEventInLastSecond <= 1000;
        }
    }
}
