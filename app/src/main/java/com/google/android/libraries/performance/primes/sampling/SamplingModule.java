package com.google.android.libraries.performance.primes.sampling;

import java.util.Random;
import javax.inject.Singleton;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class SamplingModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static ApproximateHistogram provideHistogram(Random random) {
        return new ApproximateHistogram(random, 14400000L);
    }
}
