package com.google.android.libraries.performance.primes.metrics.core.perfetto;

import com.google.common.base.Ticker;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PerfettoTrigger_Factory implements Factory {
    private final Provider tickerProvider;

    public PerfettoTrigger_Factory(Provider provider) {
        this.tickerProvider = provider;
    }

    public static PerfettoTrigger_Factory create(Provider provider) {
        return new PerfettoTrigger_Factory(provider);
    }

    public static PerfettoTrigger newInstance(Ticker ticker) {
        return new PerfettoTrigger(ticker);
    }

    @Override // javax.inject.Provider
    public PerfettoTrigger get() {
        return newInstance((Ticker) this.tickerProvider.get());
    }
}
