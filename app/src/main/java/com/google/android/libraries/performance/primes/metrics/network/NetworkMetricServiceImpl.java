package com.google.android.libraries.performance.primes.metrics.network;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.google.android.libraries.performance.primes.PrimesExecutors;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class NetworkMetricServiceImpl extends NetworkMetricService implements AppLifecycleListener.OnAppToBackground, MetricService {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/network/NetworkMetricServiceImpl");
    private final Application application;
    private final Lazy configsProvider;
    private final ListeningScheduledExecutorService executorService;
    private final Lazy metricCollector;
    private final MetricRecorder metricRecorder;
    private final Object lock = new Object();
    private final ArrayList batchedMetric = new ArrayList(0);
    private final AtomicInteger pendingRecords = new AtomicInteger();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public NetworkMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, Context context, AppLifecycleMonitor appLifecycleMonitor, ListeningScheduledExecutorService listeningScheduledExecutorService, Lazy lazy, Lazy lazy2, Provider provider, Executor executor) {
        this.metricRecorder = metricRecorderFactory.create(executor, lazy, provider);
        this.application = (Application) context;
        this.executorService = listeningScheduledExecutorService;
        this.configsProvider = lazy;
        this.metricCollector = lazy2;
        appLifecycleMonitor.register(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendPendingEvents$1$com-google-android-libraries-performance-primes-metrics-network-NetworkMetricServiceImpl  reason: not valid java name */
    public /* synthetic */ ListenableFuture m347x1caafd2e(NetworkEvent[] networkEventArr) {
        return this.metricRecorder.recordMetric(Metric.newBuilder().setMetric(((NetworkMetricCollector) this.metricCollector.get()).getMetric(networkEventArr)).build());
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnAppToBackground
    public void onAppToBackground(Activity activity) {
        PrimesExecutors.logFailures(sendPendingEvents());
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public /* synthetic */ void onApplicationStartup() {
        MetricService.CC.$default$onApplicationStartup(this);
    }

    public ListenableFuture sendPendingEvents() {
        final NetworkEvent[] networkEventArr;
        if (this.pendingRecords.get() > 0) {
            return Futures.scheduleAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.network.NetworkMetricServiceImpl$$ExternalSyntheticLambda0
                @Override // com.google.common.util.concurrent.AsyncCallable
                public final ListenableFuture call() {
                    return NetworkMetricServiceImpl.this.sendPendingEvents();
                }
            }, 1L, TimeUnit.SECONDS, this.executorService);
        }
        synchronized (this.lock) {
            if (!this.batchedMetric.isEmpty()) {
                ArrayList arrayList = this.batchedMetric;
                networkEventArr = (NetworkEvent[]) arrayList.toArray(new NetworkEvent[arrayList.size()]);
                this.batchedMetric.clear();
            } else {
                networkEventArr = null;
            }
        }
        if (networkEventArr == null) {
            return Futures.immediateVoidFuture();
        }
        return Futures.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.network.NetworkMetricServiceImpl$$ExternalSyntheticLambda1
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return NetworkMetricServiceImpl.this.m347x1caafd2e(networkEventArr);
            }
        }, this.executorService);
    }
}
