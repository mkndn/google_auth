package com.google.android.libraries.performance.primes.metrics.trace;

import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import dagger.Lazy;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TraceMetricServiceImpl$$ExternalSyntheticLambda3 implements Lazy {
    public static final /* synthetic */ TraceMetricServiceImpl$$ExternalSyntheticLambda3 INSTANCE = new TraceMetricServiceImpl$$ExternalSyntheticLambda3();

    private /* synthetic */ TraceMetricServiceImpl$$ExternalSyntheticLambda3() {
    }

    @Override // dagger.Lazy
    public final Object get() {
        MetricConfigurations build;
        build = TraceConfigurations.newBuilder().setEnabled(true).build();
        return build;
    }
}
