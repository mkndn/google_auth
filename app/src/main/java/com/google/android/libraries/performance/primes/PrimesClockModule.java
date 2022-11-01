package com.google.android.libraries.performance.primes;

import com.google.android.libraries.clock.Clock;
import com.google.android.libraries.clock.impl.SystemClockImpl;
import com.google.common.base.Optional;
import com.google.common.base.Ticker;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrimesClockModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Clock provideClock(Optional optional) {
        return (Clock) optional.or(new SystemClockImpl());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Ticker ticker(final Clock clock) {
        return new Ticker() { // from class: com.google.android.libraries.performance.primes.PrimesClockModule.1
            @Override // com.google.common.base.Ticker
            public long read() {
                return Clock.this.elapsedRealtimeNanos();
            }
        };
    }
}
