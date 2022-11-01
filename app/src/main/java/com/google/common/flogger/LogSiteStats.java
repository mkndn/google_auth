package com.google.common.flogger;

import com.google.common.flogger.backend.Metadata;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogSiteStats {
    private static final LogSiteMap map = new LogSiteMap() { // from class: com.google.common.flogger.LogSiteStats.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.flogger.LogSiteMap
        public LogSiteStats initialValue() {
            return new LogSiteStats();
        }
    };
    private final AtomicLong invocationCount = new AtomicLong();
    private final AtomicLong lastTimestampNanos = new AtomicLong();
    private final AtomicInteger skippedLogStatements = new AtomicInteger();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RateLimitPeriod {
        /* JADX INFO: Access modifiers changed from: private */
        public void setSkipCount(int i) {
            throw null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long toNanos() {
            throw null;
        }

        public boolean equals(Object obj) {
            throw null;
        }

        public int hashCode() {
            throw null;
        }

        public String toString() {
            throw null;
        }
    }

    LogSiteStats() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LogSiteStats getStatsForKey(LogSiteKey logSiteKey, Metadata metadata) {
        return (LogSiteStats) map.get(logSiteKey, metadata);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean checkLastTimestamp(long j, RateLimitPeriod rateLimitPeriod) {
        long j2 = this.lastTimestampNanos.get();
        long nanos = rateLimitPeriod.toNanos() + j2;
        if (nanos >= 0 && ((j >= nanos || j2 == 0) && this.lastTimestampNanos.compareAndSet(j2, j))) {
            rateLimitPeriod.setSkipCount(this.skippedLogStatements.getAndSet(0));
            return true;
        }
        this.skippedLogStatements.incrementAndGet();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean incrementAndCheckInvocationCount(int i) {
        return this.invocationCount.getAndIncrement() % ((long) i) == 0;
    }
}
