package com.google.android.gms.feedback.internal.common;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Stopwatch {
    public static final long TICK_NOT_SET = -1;
    long initialTick;
    long startTick;

    public Stopwatch() {
        this(-1L);
    }

    public long elapsedMillis() {
        Preconditions.checkArgument(this.startTick != -1);
        return TimeUnit.NANOSECONDS.toMillis(getTick() - this.startTick);
    }

    public long getTick() {
        long j = this.initialTick;
        if (j == -1) {
            return System.nanoTime();
        }
        this.initialTick = -1L;
        return j;
    }

    public Stopwatch start() {
        this.startTick = getTick();
        return this;
    }

    public Stopwatch(long j) {
        this.initialTick = j;
        this.startTick = -1L;
    }
}
