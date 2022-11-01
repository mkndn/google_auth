package com.google.android.libraries.performance.primes.metrics.cpuprofiling;

import android.content.Context;
import android.provider.Settings;
import com.google.android.libraries.clock.Clock;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import com.google.android.libraries.stitch.util.ThreadUtil;
import dagger.Lazy;
import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuProfilingServiceScheduler {
    static final long AVERAGE_MILLIS_PER_YEAR = TimeUnit.DAYS.toMillis(365) + TimeUnit.HOURS.toMillis(6);
    private final String androidId;
    private final Clock clock;
    private final Lazy configs;
    private final String processName = ProcessStats.getCurrentProcessName();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public CpuProfilingServiceScheduler(Clock clock, Lazy lazy, Context context) {
        this.clock = clock;
        this.androidId = getAndroidId(context);
        this.configs = lazy;
    }

    private static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    private long getFirstDayOfYear() {
        return getFirstDayOfYear(this.clock.currentTimeMillis());
    }

    private int getNumSamples(Random random) {
        double nextDouble = random.nextDouble();
        if (((CpuProfilingConfigurations) this.configs.get()).getSamplesPerEpoch() < 1.0d) {
            return nextDouble < ((CpuProfilingConfigurations) this.configs.get()).getSamplesPerEpoch() ? 1 : 0;
        }
        double samplesPerEpoch = ((CpuProfilingConfigurations) this.configs.get()).getSamplesPerEpoch();
        return (int) Math.min(Math.round((samplesPerEpoch + samplesPerEpoch) * nextDouble), 2147483646L);
    }

    private Random getRandomGenerator(long j) {
        return new Random(Arrays.hashCode(new Object[]{Long.valueOf(j), this.androidId, this.processName}));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Long getNextWindow() {
        ThreadUtil.ensureBackgroundThread();
        return getNextWindow(getFirstDayOfYear());
    }

    private static long getFirstDayOfYear(long j) {
        return j - (j % AVERAGE_MILLIS_PER_YEAR);
    }

    Long getNextWindow(long j) {
        Random randomGenerator = getRandomGenerator(j);
        int numSamples = getNumSamples(randomGenerator);
        long j2 = AVERAGE_MILLIS_PER_YEAR + j;
        int sampleDurationMs = ((CpuProfilingConfigurations) this.configs.get()).getSampleDurationMs();
        long j3 = (j2 - j) - (sampleDurationMs + sampleDurationMs);
        TreeSet treeSet = new TreeSet();
        while (treeSet.size() < numSamples) {
            long abs = (Math.abs(Math.max(randomGenerator.nextLong(), -9223372036854775807L)) % j3) + j;
            long sampleDurationMs2 = ((CpuProfilingConfigurations) this.configs.get()).getSampleDurationMs();
            long j4 = sampleDurationMs2 + sampleDurationMs2;
            if (treeSet.subSet(Long.valueOf(abs - j4), Long.valueOf(j4 + abs)).isEmpty()) {
                treeSet.add(Long.valueOf(abs));
            }
        }
        Long l = (Long) treeSet.ceiling(Long.valueOf(this.clock.currentTimeMillis() + 100));
        if (l != null) {
            return l;
        }
        if (j < this.clock.currentTimeMillis()) {
            return getNextWindow(j2);
        }
        return l;
    }
}
