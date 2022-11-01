package com.google.android.libraries.performance.primes.sampling;

import com.google.common.base.Preconditions;
import java.util.Random;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProbabilitySampler {
    private final Random random;
    private final float samplingRate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProbabilitySampler(Random random, float f) {
        Preconditions.checkArgument(f >= 0.0f && f <= 1.0f, "Sampling rate should be a floating number >= 0 and <= 1.");
        this.samplingRate = f;
        this.random = random;
    }

    public boolean isSampleAllowed() {
        return this.random.nextFloat() < this.samplingRate;
    }
}
