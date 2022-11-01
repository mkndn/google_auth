package com.google.android.libraries.performance.primes.sampling;

import java.util.Random;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApproximateHistogram {
    public static final int DEFAULT_SCALE_DOWN_INTERVAL_MILLIS = 14400000;
    private final short[] histogramForFirstByte = new short[256];
    private final short[] histogramForSecondByte = new short[256];
    private long lastScaleDownTimeMillis = 0;
    private final int multiplier;
    private final long scaleDownIntervalMillis;

    public ApproximateHistogram(Random random, long j) {
        this.multiplier = generateMultiplier(random);
        this.scaleDownIntervalMillis = j;
    }

    static int generateMultiplier(Random random) {
        return (random.nextInt() & (-131075)) | 65537;
    }

    private void scaleDown(long j) {
        long max = Math.max(j, 15L);
        for (int i = 0; i < 256; i++) {
            short[] sArr = this.histogramForFirstByte;
            int i2 = (int) max;
            short s = (short) (sArr[i] >> i2);
            sArr[i] = s;
            this.histogramForSecondByte[i] = (short) (s >> i2);
        }
    }

    private int updateEntry(String str, int i, int i2) {
        int hashCode = str.hashCode() * this.multiplier;
        int charAt = ((hashCode >>> 24) + (str.isEmpty() ? (char) 0 : str.charAt(0))) & 255;
        int length = ((hashCode >>> 16) + str.length()) & 255;
        int min = Math.min((int) this.histogramForFirstByte[charAt], (int) this.histogramForSecondByte[length]);
        int i3 = (i * min) + i2;
        short min2 = (short) Math.min(32767, i3);
        short[] sArr = this.histogramForFirstByte;
        if (sArr[charAt] == min) {
            sArr[charAt] = min2;
        }
        short[] sArr2 = this.histogramForSecondByte;
        if (sArr2[length] == min) {
            sArr2[length] = min2;
        }
        return i3;
    }

    public int addAndScale(String str, long j, int i) {
        long j2 = this.lastScaleDownTimeMillis;
        long j3 = this.scaleDownIntervalMillis;
        if (j - j2 >= j3) {
            long j4 = (j - j2) / j3;
            scaleDown(j4);
            this.lastScaleDownTimeMillis += j4 * this.scaleDownIntervalMillis;
        }
        return updateEntry(str, 1, i);
    }
}
