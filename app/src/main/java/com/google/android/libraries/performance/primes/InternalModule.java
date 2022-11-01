package com.google.android.libraries.performance.primes;

import com.google.android.libraries.clock.Clock;
import java.util.Random;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class InternalModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Random random(Clock clock) {
        return new Random(clock.elapsedRealtime());
    }
}
