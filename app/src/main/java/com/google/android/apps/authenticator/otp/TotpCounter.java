package com.google.android.apps.authenticator.otp;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TotpCounter {
    private final long mStartTime;
    private final long mTimeStep;

    public TotpCounter(long j) {
        this(j, 0L);
    }

    private static void assertValidTime(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Negative time: " + j);
        }
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public long getTimeStep() {
        return this.mTimeStep;
    }

    public long getValueAtTime(long j) {
        assertValidTime(j);
        long j2 = j - this.mStartTime;
        if (j2 >= 0) {
            return j2 / this.mTimeStep;
        }
        long j3 = this.mTimeStep;
        return (j2 - ((-1) + j3)) / j3;
    }

    public long getValueStartTime(long j) {
        return this.mStartTime + (j * this.mTimeStep);
    }

    public TotpCounter(long j, long j2) {
        if (j < 1) {
            throw new IllegalArgumentException("Time step must be positive: " + j);
        }
        assertValidTime(j2);
        this.mTimeStep = j;
        this.mStartTime = j2;
    }
}
