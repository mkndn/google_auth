package com.google.android.libraries.performance.primes.metrics.core;

import com.google.android.libraries.performance.primes.Shutdown;
import com.google.android.libraries.performance.primes.TestingInstrumentationHolder;
import com.google.android.libraries.performance.primes.flogger.RecentLogs;
import com.google.android.libraries.performance.primes.sampling.Sampler;
import com.google.android.libraries.performance.primes.sampling.SamplerFactory;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.GeneratedMessageLite;
import dagger.Lazy;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$AccountableComponent;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricRecorder {
    private final Executor executor;
    private final Provider globalExtensionProvider;
    private final Optional interactionContextProvider;
    private final MetricDispatcher metricDispatcher;
    private final Provider metricStamperProvider;
    private final RecentLogs recentLogs;
    private final Sampler sampler;
    private final Shutdown shutdown;

    public MetricRecorder(MetricDispatcher metricDispatcher, Provider provider, Shutdown shutdown, SamplerFactory samplerFactory, final Provider provider2, RecentLogs recentLogs, Optional optional, Executor executor, Lazy lazy, Provider provider3) {
        this.metricDispatcher = metricDispatcher;
        this.shutdown = shutdown;
        this.metricStamperProvider = provider;
        this.executor = executor;
        this.globalExtensionProvider = new Provider() { // from class: com.google.android.libraries.performance.primes.metrics.core.MetricRecorder$$ExternalSyntheticLambda1
            @Override // javax.inject.Provider
            public final Object get() {
                return MetricRecorder.lambda$new$0(Provider.this);
            }
        };
        this.sampler = samplerFactory.create(lazy, provider3);
        this.recentLogs = recentLogs;
        this.interactionContextProvider = optional;
    }

    public RecentLogs.TimestampCollection getDebugLogsTime() {
        return this.recentLogs.getTimestamp();
    }

    public SystemHealthProto$SamplingParameters getSamplingParameters(Long l) {
        return this.sampler.getSamplingParameters(l);
    }

    /* renamed from: lambda$recordMetric$1$com-google-android-libraries-performance-primes-metrics-core-MetricRecorder */
    public /* synthetic */ ListenableFuture m308x8d1b11f7(Metric metric) {
        SystemHealthProto$SamplingParameters samplingParametersIfShouldRecord;
        ExtensionMetric$MetricExtension extensionMetric$MetricExtension;
        if (metric.getIsUnsampled()) {
            samplingParametersIfShouldRecord = (SystemHealthProto$SamplingParameters) SystemHealthProto$SamplingParameters.newBuilder().setSamplingStrategy(SystemHealthProto$SamplingParameters.SamplingStrategy.SAMPLING_STRATEGY_ALWAYS_ON).build();
        } else {
            samplingParametersIfShouldRecord = samplingParametersIfShouldRecord(metric.getSampleRatePermille());
        }
        if (samplingParametersIfShouldRecord.getSampleRatePermille() == -1) {
            return Futures.immediateVoidFuture();
        }
        SystemHealthProto$SystemHealthMetric.Builder builder = (SystemHealthProto$SystemHealthMetric.Builder) ((MetricStamper) this.metricStamperProvider.get()).stamp(metric.getMetric()).toBuilder();
        builder.setSamplingParameters(samplingParametersIfShouldRecord);
        RecentLogs.TimestampCollection debugLogsTime = metric.getDebugLogsTime();
        if (debugLogsTime != null) {
            builder.setDebugLogs(this.recentLogs.getSnapshot(debugLogsTime, 100));
        }
        if (this.interactionContextProvider.isPresent()) {
            builder.addAllInteractionContext(((InteractionContextProvider) this.interactionContextProvider.get()).getInteractions());
        }
        String customEventName = metric.getCustomEventName();
        if (metric.getIsEventNameConstant()) {
            if (customEventName != null) {
                builder.setConstantEventName(customEventName);
            } else {
                builder.clearConstantEventName();
            }
        } else if (customEventName != null) {
            builder.setCustomEventName(customEventName);
        } else {
            builder.clearCustomEventName();
        }
        ExtensionMetric$MetricExtension extensionMetric$MetricExtension2 = (ExtensionMetric$MetricExtension) this.globalExtensionProvider.get();
        ExtensionMetric$MetricExtension metricExtension = metric.getMetricExtension();
        if (extensionMetric$MetricExtension2 != null || metricExtension != null) {
            if (extensionMetric$MetricExtension2 != null && metricExtension != null) {
                extensionMetric$MetricExtension = (ExtensionMetric$MetricExtension) ((ExtensionMetric$MetricExtension.Builder) ((ExtensionMetric$MetricExtension.Builder) extensionMetric$MetricExtension2.toBuilder()).mergeFrom((GeneratedMessageLite) metricExtension)).build();
            } else if (extensionMetric$MetricExtension2 != null) {
                extensionMetric$MetricExtension = (ExtensionMetric$MetricExtension) Preconditions.checkNotNull(extensionMetric$MetricExtension2);
            } else {
                extensionMetric$MetricExtension = (ExtensionMetric$MetricExtension) Preconditions.checkNotNull(metricExtension);
            }
            builder.setMetricExtension(extensionMetric$MetricExtension);
        }
        String accountableComponentName = metric.getAccountableComponentName();
        if (accountableComponentName != null) {
            builder.setAccountableComponent(SystemHealthProto$AccountableComponent.newBuilder().setCustomName(accountableComponentName));
        }
        ListenableFuture dispatch = this.metricDispatcher.dispatch((SystemHealthProto$SystemHealthMetric) builder.build());
        this.sampler.incrementEventCount();
        return dispatch;
    }

    public ListenableFuture recordMetric(final Metric metric) {
        if (this.shutdown.isShutdown()) {
            return Futures.immediateCancelledFuture();
        }
        return Futures.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.core.MetricRecorder$$ExternalSyntheticLambda0
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return MetricRecorder.this.m308x8d1b11f7(metric);
            }
        }, this.executor);
    }

    public SystemHealthProto$SamplingParameters samplingParametersIfShouldRecord(Long l) {
        if (TestingInstrumentationHolder.isAppRunningUnderPrimesEndtoendTest()) {
            return this.sampler.getSamplingParameters(l);
        }
        return this.sampler.samplingParametersIfShouldRecord(l);
    }

    public long samplingRatePermilleIfShouldCollect(String str) {
        if (this.shutdown.isShutdown()) {
            return -1L;
        }
        if (TestingInstrumentationHolder.isAppRunningUnderPrimesEndtoendTest()) {
            return getSamplingParameters(null).getSampleRatePermille();
        }
        return this.sampler.samplingRatePermilleIfShouldCollect(str);
    }

    public boolean shouldCollectMetric(String str) {
        return samplingRatePermilleIfShouldCollect(str) != -1;
    }

    public boolean shouldRecordMetric() {
        if (TestingInstrumentationHolder.isAppRunningUnderPrimesEndtoendTest()) {
            return true;
        }
        return this.sampler.shouldRecordMetric();
    }

    public static /* synthetic */ ExtensionMetric$MetricExtension lambda$new$0(Provider provider) {
        if (!((Optional) provider.get()).isPresent() || ((GlobalConfigurations) ((Optional) provider.get()).get()).getExtensionProvider() == null) {
            return null;
        }
        return (ExtensionMetric$MetricExtension) ((Provider) Preconditions.checkNotNull(((GlobalConfigurations) ((Optional) provider.get()).get()).getExtensionProvider())).get();
    }
}
