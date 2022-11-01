package com.google.android.libraries.performance.primes;

import android.content.Context;
import com.google.android.libraries.concurrent.ExecutorDecorator_Factory;
import com.google.android.libraries.performance.primes.ProdInternalComponent;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_AppExitCollectionEnabledFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_AppExitReasonsToReportFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_BatterySamplingParametersFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_CpuProfilingSamplingParametersFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_EnableAlwaysOnJankRecordingFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_EnableStartupBaselineDiscardingFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_FirstDrawTypeFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_JankPerfettoConfigurationsFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_JankSamplingParametersFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_MemorySamplingParametersFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_NetworkSamplingParametersFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_RecordingTimeoutsFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_RegisterFrameMetricsListenerInOnCreateFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_StartupSamplingParametersFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_StorageSamplingParametersFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_TimerSamplingParametersFactory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_TraceSamplingParametersFactory;
import com.google.android.libraries.performance.primes.flogger.RecentLogs_Factory;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor_Factory;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleTracker_Callbacks_Factory;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleTracker_Factory;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryCapture_Factory;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.battery.PrimesBatteryDaggerModule_BatteryServiceFactory;
import com.google.android.libraries.performance.primes.metrics.battery.PrimesBatteryDaggerModule_MetricServiceFactory;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage_Factory;
import com.google.android.libraries.performance.primes.metrics.battery.SystemHealthCapture_Factory;
import com.google.android.libraries.performance.primes.metrics.core.MetricDispatcher_Factory;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory_Factory;
import com.google.android.libraries.performance.primes.metrics.core.MetricStamper_Factory;
import com.google.android.libraries.performance.primes.metrics.core.PrimesCoreMetricDaggerModule_ProvideVersionNameFactory;
import com.google.android.libraries.performance.primes.metrics.core.perfetto.PerfettoTrigger_Factory;
import com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingServiceScheduler_Factory;
import com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingService_Factory;
import com.google.android.libraries.performance.primes.metrics.cpuprofiling.PrimesCpuProfilingDaggerModule_CpuProfilingServiceFactory;
import com.google.android.libraries.performance.primes.metrics.crash.CrashMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.crash.PrimesCrashDaggerModule_CrashServiceFactory;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitInfoCaptureImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.PrimesApplicationExitDaggerModule_ApplicationExitMetricServiceFactory;
import com.google.android.libraries.performance.primes.metrics.jank.ActivityLevelJankMonitor_Factory;
import com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.jank.FrameTimeHistogram_Factory;
import com.google.android.libraries.performance.primes.metrics.jank.PrimesJankDaggerModule_HandlerForFrameMetricsFactory;
import com.google.android.libraries.performance.primes.metrics.jank.PrimesJankDaggerModule_JankServiceFactory;
import com.google.android.libraries.performance.primes.metrics.jank.PrimesJankDaggerModule_MetricServiceFactory;
import com.google.android.libraries.performance.primes.metrics.jank.PrimesJankDaggerModule_RegisterFrameMetricsListenerInOnCreateFactory;
import com.google.android.libraries.performance.primes.metrics.memory.DebugMemoryMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor_Factory;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryUsageCapture_Factory;
import com.google.android.libraries.performance.primes.metrics.network.NetworkMetricCollector_Factory;
import com.google.android.libraries.performance.primes.metrics.network.NetworkMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.network.PrimesNetworkDaggerModule_NetworkMetricServiceFactory;
import com.google.android.libraries.performance.primes.metrics.startup.PrimesStartupDaggerModule_ProvideStartupConfigurationsFactory;
import com.google.android.libraries.performance.primes.metrics.startup.StartupMetricRecordingService_Factory;
import com.google.android.libraries.performance.primes.metrics.startup.StartupMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.storage.PrimesStorageDaggerModule_MetricServiceFactory;
import com.google.android.libraries.performance.primes.metrics.storage.PrimesStorageDaggerModule_StorageMetricServiceFactory;
import com.google.android.libraries.performance.primes.metrics.storage.StorageMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.timer.PrimesTimerDaggerModule_MetricServiceFactory;
import com.google.android.libraries.performance.primes.metrics.timer.PrimesTimerDaggerModule_ProvideCustomDurationMetricServiceFactory;
import com.google.android.libraries.performance.primes.metrics.timer.PrimesTimerDaggerModule_TimerServiceFactory;
import com.google.android.libraries.performance.primes.metrics.timer.TimerMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.timer.TimerMetricServiceWithTracingImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.trace.PrimesTraceDaggerModule_MetricServiceFactory;
import com.google.android.libraries.performance.primes.metrics.trace.PrimesTraceDaggerModule_TimerMetricServiceSupportFactory;
import com.google.android.libraries.performance.primes.metrics.trace.PrimesTraceDaggerModule_TraceServiceFactory;
import com.google.android.libraries.performance.primes.metrics.trace.TraceMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.persistent.PersistentStorage_Factory;
import com.google.android.libraries.performance.primes.sampling.PersistentRateLimiting_Factory;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySamplerFactory_Factory;
import com.google.android.libraries.performance.primes.sampling.ProdSamplingModule_EnableSamplingFactory;
import com.google.android.libraries.performance.primes.sampling.SamplerFactory_Factory;
import com.google.android.libraries.performance.primes.sampling.SamplingModule_ProvideHistogramFactory;
import com.google.android.libraries.performance.primes.sampling.SamplingStrategy_Factory_Factory;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import dagger.internal.SetFactory;
import dagger.internal.SingleCheck;
import javax.inject.Provider;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class DaggerProdInternalComponent {
    private static final Provider ABSENT_GUAVA_OPTIONAL_PROVIDER = InstanceFactory.create(Optional.absent());

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Builder implements ProdInternalComponent.Builder {
        private Context setApplicationContext;
        private Optional setApplicationExitConfigurationsProvider;
        private Optional setBatteryConfigurationsProvider;
        private Optional setCpuProfilingConfigurationsProvider;
        private Optional setCrashConfigurationsProvider;
        private Optional setDebugMemoryConfigurationsProvider;
        private Optional setGlobalConfigurationsProvider;
        private Optional setJankConfigurationsProvider;
        private Optional setMemoryConfigurationsProvider;
        private Supplier setMetricTransmittersSupplier;
        private Optional setMonitorAllActivitiesProvider;
        private Optional setNetworkConfigurationsProvider;
        private Optional setSharedPreferencesSupplier;
        private Optional setStorageConfigurationsProvider;
        private Optional setThreadsConfigurations;
        private Optional setTikTokTraceConfigurationsProvider;
        private Optional setTimerConfigurationsProvider;
        private Optional setTraceConfigurationsProvider;

        private Builder() {
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setApplicationContext(Context context) {
            this.setApplicationContext = (Context) Preconditions.checkNotNull(context);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setApplicationExitConfigurationsProvider(Optional optional) {
            this.setApplicationExitConfigurationsProvider = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setBatteryConfigurationsProvider(Optional optional) {
            this.setBatteryConfigurationsProvider = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setCpuProfilingConfigurationsProvider(Optional optional) {
            this.setCpuProfilingConfigurationsProvider = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setCrashConfigurationsProvider(Optional optional) {
            this.setCrashConfigurationsProvider = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setDebugMemoryConfigurationsProvider(Optional optional) {
            this.setDebugMemoryConfigurationsProvider = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setGlobalConfigurationsProvider(Optional optional) {
            this.setGlobalConfigurationsProvider = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setJankConfigurationsProvider(Optional optional) {
            this.setJankConfigurationsProvider = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setMemoryConfigurationsProvider(Optional optional) {
            this.setMemoryConfigurationsProvider = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setMetricTransmittersSupplier(Supplier supplier) {
            this.setMetricTransmittersSupplier = (Supplier) Preconditions.checkNotNull(supplier);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setMonitorAllActivitiesProvider(Optional optional) {
            this.setMonitorAllActivitiesProvider = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setNetworkConfigurationsProvider(Optional optional) {
            this.setNetworkConfigurationsProvider = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setSharedPreferencesSupplier(Optional optional) {
            this.setSharedPreferencesSupplier = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setStorageConfigurationsProvider(Optional optional) {
            this.setStorageConfigurationsProvider = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setThreadsConfigurations(Optional optional) {
            this.setThreadsConfigurations = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setTikTokTraceConfigurationsProvider(Optional optional) {
            this.setTikTokTraceConfigurationsProvider = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setTimerConfigurationsProvider(Optional optional) {
            this.setTimerConfigurationsProvider = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public Builder setTraceConfigurationsProvider(Optional optional) {
            this.setTraceConfigurationsProvider = (Optional) Preconditions.checkNotNull(optional);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.InternalComponent.Builder
        public ProdInternalComponent build() {
            Preconditions.checkBuilderRequirement(this.setApplicationContext, Context.class);
            Preconditions.checkBuilderRequirement(this.setMetricTransmittersSupplier, Supplier.class);
            Preconditions.checkBuilderRequirement(this.setMemoryConfigurationsProvider, Optional.class);
            Preconditions.checkBuilderRequirement(this.setDebugMemoryConfigurationsProvider, Optional.class);
            Preconditions.checkBuilderRequirement(this.setGlobalConfigurationsProvider, Optional.class);
            Preconditions.checkBuilderRequirement(this.setTimerConfigurationsProvider, Optional.class);
            Preconditions.checkBuilderRequirement(this.setCrashConfigurationsProvider, Optional.class);
            Preconditions.checkBuilderRequirement(this.setApplicationExitConfigurationsProvider, Optional.class);
            Preconditions.checkBuilderRequirement(this.setNetworkConfigurationsProvider, Optional.class);
            Preconditions.checkBuilderRequirement(this.setStorageConfigurationsProvider, Optional.class);
            Preconditions.checkBuilderRequirement(this.setJankConfigurationsProvider, Optional.class);
            Preconditions.checkBuilderRequirement(this.setTikTokTraceConfigurationsProvider, Optional.class);
            Preconditions.checkBuilderRequirement(this.setTraceConfigurationsProvider, Optional.class);
            Preconditions.checkBuilderRequirement(this.setBatteryConfigurationsProvider, Optional.class);
            Preconditions.checkBuilderRequirement(this.setCpuProfilingConfigurationsProvider, Optional.class);
            Preconditions.checkBuilderRequirement(this.setSharedPreferencesSupplier, Optional.class);
            Preconditions.checkBuilderRequirement(this.setMonitorAllActivitiesProvider, Optional.class);
            Preconditions.checkBuilderRequirement(this.setThreadsConfigurations, Optional.class);
            return new ProdInternalComponentImpl(this.setApplicationContext, this.setMetricTransmittersSupplier, this.setMemoryConfigurationsProvider, this.setDebugMemoryConfigurationsProvider, this.setGlobalConfigurationsProvider, this.setTimerConfigurationsProvider, this.setCrashConfigurationsProvider, this.setApplicationExitConfigurationsProvider, this.setNetworkConfigurationsProvider, this.setStorageConfigurationsProvider, this.setJankConfigurationsProvider, this.setTikTokTraceConfigurationsProvider, this.setTraceConfigurationsProvider, this.setBatteryConfigurationsProvider, this.setCpuProfilingConfigurationsProvider, this.setSharedPreferencesSupplier, this.setMonitorAllActivitiesProvider, this.setThreadsConfigurations);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProdInternalComponentImpl implements ProdInternalComponent {
        private Provider activityLevelJankMonitorProvider;
        private Provider allowPhenotypeDuringInitializationOptionalOfBooleanProvider;
        private Provider appExitCollectionEnabledProvider;
        private Provider appExitReasonsToReportProvider;
        private Provider appLifecycleMonitorProvider;
        private Provider appLifecycleTrackerProvider;
        private Provider applicationExitInfoCaptureImplProvider;
        private Provider applicationExitMetricServiceImplProvider;
        private Provider applicationExitMetricServiceProvider;
        private Provider batteryCaptureProvider;
        private Provider batteryMetricServiceImplProvider;
        private Provider batterySamplingParametersProvider;
        private Provider batteryServiceProvider;
        private Provider callbacksProvider;
        private Provider cpuProfilingSamplingParametersProvider;
        private Provider cpuProfilingServiceProvider;
        private Provider cpuProfilingServiceProvider2;
        private Provider cpuProfilingServiceSchedulerProvider;
        private Provider crashMetricServiceImplProvider;
        private Provider crashOnBadPrimesConfigurationProvider;
        private Provider crashServiceProvider;
        private Provider debugMemoryMetricServiceImplProvider;
        private Provider deferrableExecutorProvider;
        private Provider enableAlwaysOnJankRecordingProvider;
        private Provider enableStartupBaselineDiscardingProvider;
        private Provider enableUnifiedInitOptionalOfBooleanProvider;
        private Provider executorDecoratorProvider;
        private Provider factoryProvider;
        private Provider firstDrawTypeProvider;
        private Provider frameMetricServiceImplProvider;
        private Provider frameTimeHistogramProvider;
        private Provider handlerForFrameMetricsProvider;
        private Provider jankPerfettoConfigurationsProvider;
        private Provider jankSamplingParametersProvider;
        private Provider jankServiceProvider;
        private Provider lightweightExecutorOptionalOfLooperProvider;
        private Provider memoryMetricMonitorProvider;
        private Provider memoryMetricServiceImplProvider;
        private Provider memorySamplingParametersProvider;
        private Provider memoryUsageCaptureProvider;
        private Provider metricDispatcherProvider;
        private Provider metricRecorderFactoryProvider;
        private Provider metricServiceProvider;
        private Provider metricServiceProvider2;
        private Provider metricServiceProvider3;
        private Provider metricServiceProvider4;
        private Provider metricServiceProvider5;
        private Provider metricStamperProvider;
        private Provider networkMetricCollectorProvider;
        private Provider networkMetricServiceImplProvider;
        private Provider networkMetricServiceProvider;
        private Provider networkSamplingParametersProvider;
        private Provider optionalOfClockProvider;
        private Provider optionalOfInteractionContextProvider;
        private Provider optionalOfInternalExecutorDecoratorProvider;
        private Provider optionalOfProviderOfNativeCrashHandlerProvider;
        private Provider optionalOfStartupConfigurationsProvider;
        private Provider perfettoTriggerProvider;
        private Provider persistentRateLimitingProvider;
        private Provider persistentStorageProvider;
        private Provider primesApiImplProvider;
        private Provider probabilitySamplerFactoryProvider;
        private final ProdInternalComponentImpl prodInternalComponentImpl;
        private Provider provideApplicationExitConfigurationsProvider;
        private Provider provideBatteryConfigurationsProvider;
        private Provider provideClockProvider;
        private Provider provideCpuProfilingConfigurationsProvider;
        private Provider provideCrashConfigurationsProvider;
        private Provider provideCustomDurationMetricServiceProvider;
        private Provider provideDebugMemoryConfigurationsProvider;
        private Provider provideDeferrableExecutorProvider;
        private Provider provideGlobalConfigurationsProvider;
        private Provider provideHistogramProvider;
        private Provider provideJankConfigurationsProvider;
        private Provider provideListeningScheduledExecutorServiceProvider;
        private Provider provideMemoryConfigurationsProvider;
        private Provider provideMetricTransmittersProvider;
        private Provider provideNetworkConfigurationsProvider;
        private Provider provideSharedPreferencesProvider;
        private Provider provideStartupConfigurationsProvider;
        private Provider provideStorageConfigurationsProvider;
        private Provider provideThreadConfigurationsProvider;
        private Provider provideTikTokTraceConfigurationsProvider;
        private Provider provideTimerConfigurationsProvider;
        private Provider provideTraceConfigurationsProvider;
        private Provider provideVersionNameProvider;
        private Provider randomProvider;
        private Provider recentLogsProvider;
        private Provider recordingTimeoutsProvider;
        private Provider registerFrameMetricsListenerInOnCreateProvider;
        private Provider registerFrameMetricsListenerInOnCreateProvider2;
        private Provider samplerFactoryProvider;
        private Provider setApplicationContextProvider;
        private Provider setApplicationExitConfigurationsProvider;
        private Provider setBatteryConfigurationsProvider;
        private Provider setCpuProfilingConfigurationsProvider;
        private Provider setCrashConfigurationsProvider;
        private Provider setDebugMemoryConfigurationsProvider;
        private Provider setGlobalConfigurationsProvider;
        private Provider setJankConfigurationsProvider;
        private Provider setMemoryConfigurationsProvider;
        private Provider setMetricTransmittersSupplierProvider;
        private Provider setMonitorAllActivitiesProvider;
        private Provider setNetworkConfigurationsProvider;
        private Provider setOfMetricServiceProvider;
        private Provider setOfMetricTransmitterProvider;
        private Provider setSharedPreferencesSupplierProvider;
        private Provider setStorageConfigurationsProvider;
        private Provider setThreadsConfigurationsProvider;
        private Provider setTikTokTraceConfigurationsProvider;
        private Provider setTimerConfigurationsProvider;
        private Provider setTraceConfigurationsProvider;
        private Provider shutdownProvider;
        private Provider startupMetricRecordingServiceProvider;
        private Provider startupMetricServiceImplProvider;
        private Provider startupSamplingParametersProvider;
        private Provider statsStorageProvider;
        private Provider storageMetricServiceImplProvider;
        private Provider storageMetricServiceProvider;
        private Provider storageSamplingParametersProvider;
        private Provider systemHealthCaptureProvider;
        private Provider tickerProvider;
        private Provider timerMetricServiceImplProvider;
        private Provider timerMetricServiceSupportProvider;
        private Provider timerMetricServiceWithTracingImplProvider;
        private Provider timerSamplingParametersProvider;
        private Provider timerServiceProvider;
        private Provider traceMetricServiceImplProvider;
        private Provider traceSamplingParametersProvider;
        private Provider traceServiceProvider;

        private ProdInternalComponentImpl(Context context, Supplier supplier, Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, Optional optional6, Optional optional7, Optional optional8, Optional optional9, Optional optional10, Optional optional11, Optional optional12, Optional optional13, Optional optional14, Optional optional15, Optional optional16) {
            this.prodInternalComponentImpl = this;
            initialize(context, supplier, optional, optional2, optional3, optional4, optional5, optional6, optional7, optional8, optional9, optional10, optional11, optional12, optional13, optional14, optional15, optional16);
            initialize2(context, supplier, optional, optional2, optional3, optional4, optional5, optional6, optional7, optional8, optional9, optional10, optional11, optional12, optional13, optional14, optional15, optional16);
        }

        private void initialize(Context context, Supplier supplier, Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, Optional optional6, Optional optional7, Optional optional8, Optional optional9, Optional optional10, Optional optional11, Optional optional12, Optional optional13, Optional optional14, Optional optional15, Optional optional16) {
            this.setApplicationContextProvider = InstanceFactory.create(context);
            Factory create = InstanceFactory.create(optional16);
            this.setThreadsConfigurationsProvider = create;
            this.provideThreadConfigurationsProvider = DoubleCheck.provider(ConfigurationsModule_ProvideThreadConfigurationsFactory.create(create));
            Provider absentGuavaOptionalProvider = DaggerProdInternalComponent.absentGuavaOptionalProvider();
            this.optionalOfInternalExecutorDecoratorProvider = absentGuavaOptionalProvider;
            ExecutorDecorator_Factory create2 = ExecutorDecorator_Factory.create(absentGuavaOptionalProvider);
            this.executorDecoratorProvider = create2;
            this.provideListeningScheduledExecutorServiceProvider = DoubleCheck.provider(PrimesExecutorsModule_ProvideListeningScheduledExecutorServiceFactory.create(this.provideThreadConfigurationsProvider, create2));
            this.shutdownProvider = DoubleCheck.provider(Shutdown_Factory.create());
            Factory create3 = InstanceFactory.create(supplier);
            this.setMetricTransmittersSupplierProvider = create3;
            this.provideMetricTransmittersProvider = DoubleCheck.provider(ProdInternalComponent_NonDaggerInternalModule_ProvideMetricTransmittersFactory.create(create3));
            SetFactory build = SetFactory.builder(0, 1).addCollectionProvider(this.provideMetricTransmittersProvider).build();
            this.setOfMetricTransmitterProvider = build;
            this.metricDispatcherProvider = SingleCheck.provider(MetricDispatcher_Factory.create(build));
            Factory create4 = InstanceFactory.create(optional3);
            this.setGlobalConfigurationsProvider = create4;
            this.provideGlobalConfigurationsProvider = ConfigurationsModule_ProvideGlobalConfigurationsFactory.create(create4);
            Provider provider = DoubleCheck.provider(PrimesCoreMetricDaggerModule_ProvideVersionNameFactory.create(this.setApplicationContextProvider));
            this.provideVersionNameProvider = provider;
            this.metricStamperProvider = DoubleCheck.provider(MetricStamper_Factory.create(this.setApplicationContextProvider, this.provideGlobalConfigurationsProvider, provider));
            Provider provider2 = SingleCheck.provider(CrashOnBadPrimesConfiguration_Factory.create(this.setApplicationContextProvider));
            this.crashOnBadPrimesConfigurationProvider = provider2;
            Provider provider3 = DoubleCheck.provider(AppLifecycleTracker_Callbacks_Factory.create(provider2));
            this.callbacksProvider = provider3;
            Provider provider4 = DoubleCheck.provider(AppLifecycleTracker_Factory.create(provider3));
            this.appLifecycleTrackerProvider = provider4;
            Provider provider5 = DoubleCheck.provider(AppLifecycleMonitor_Factory.create(this.setApplicationContextProvider, provider4));
            this.appLifecycleMonitorProvider = provider5;
            Provider provider6 = DoubleCheck.provider(DeferrableExecutor_Factory.create(this.provideListeningScheduledExecutorServiceProvider, provider5));
            this.deferrableExecutorProvider = provider6;
            this.provideDeferrableExecutorProvider = DoubleCheck.provider(PrimesExecutorsModule_ProvideDeferrableExecutorFactory.create(provider6, this.provideListeningScheduledExecutorServiceProvider, this.provideThreadConfigurationsProvider));
            Provider absentGuavaOptionalProvider2 = DaggerProdInternalComponent.absentGuavaOptionalProvider();
            this.optionalOfClockProvider = absentGuavaOptionalProvider2;
            Provider provider7 = SingleCheck.provider(PrimesClockModule_ProvideClockFactory.create(absentGuavaOptionalProvider2));
            this.provideClockProvider = provider7;
            Provider provider8 = SingleCheck.provider(InternalModule_RandomFactory.create(provider7));
            this.randomProvider = provider8;
            Provider provider9 = DoubleCheck.provider(SamplingModule_ProvideHistogramFactory.create(provider8));
            this.provideHistogramProvider = provider9;
            SamplingStrategy_Factory_Factory create5 = SamplingStrategy_Factory_Factory.create(this.randomProvider, provider9, this.provideClockProvider);
            this.factoryProvider = create5;
            this.samplerFactoryProvider = SamplerFactory_Factory.create(this.setApplicationContextProvider, this.provideDeferrableExecutorProvider, create5, ProdSamplingModule_EnableSamplingFactory.create());
            this.recentLogsProvider = DoubleCheck.provider(RecentLogs_Factory.create());
            Provider absentGuavaOptionalProvider3 = DaggerProdInternalComponent.absentGuavaOptionalProvider();
            this.optionalOfInteractionContextProvider = absentGuavaOptionalProvider3;
            this.metricRecorderFactoryProvider = MetricRecorderFactory_Factory.create(this.metricDispatcherProvider, this.metricStamperProvider, this.shutdownProvider, this.samplerFactoryProvider, this.provideGlobalConfigurationsProvider, this.recentLogsProvider, absentGuavaOptionalProvider3);
            this.applicationExitInfoCaptureImplProvider = ApplicationExitInfoCaptureImpl_Factory.create(this.setApplicationContextProvider);
            Factory create6 = InstanceFactory.create(optional14);
            this.setSharedPreferencesSupplierProvider = create6;
            this.provideSharedPreferencesProvider = DoubleCheck.provider(ConfigurationsModule_ProvideSharedPreferencesFactory.create(this.setApplicationContextProvider, create6));
            Factory create7 = InstanceFactory.create(optional6);
            this.setApplicationExitConfigurationsProvider = create7;
            this.provideApplicationExitConfigurationsProvider = ConfigurationsModule_ProvideApplicationExitConfigurationsFactory.create(create7);
            this.appExitCollectionEnabledProvider = DoubleCheck.provider(PhenotypeFlagsModule_AppExitCollectionEnabledFactory.create(this.setApplicationContextProvider));
            Provider provider10 = DoubleCheck.provider(PhenotypeFlagsModule_AppExitReasonsToReportFactory.create(this.setApplicationContextProvider));
            this.appExitReasonsToReportProvider = provider10;
            Provider provider11 = DoubleCheck.provider(ApplicationExitMetricServiceImpl_Factory.create(this.metricRecorderFactoryProvider, this.setApplicationContextProvider, this.provideDeferrableExecutorProvider, this.applicationExitInfoCaptureImplProvider, this.provideSharedPreferencesProvider, this.provideApplicationExitConfigurationsProvider, this.appExitCollectionEnabledProvider, provider10));
            this.applicationExitMetricServiceImplProvider = provider11;
            this.applicationExitMetricServiceProvider = PrimesApplicationExitDaggerModule_ApplicationExitMetricServiceFactory.create(provider11);
            Factory create8 = InstanceFactory.create(optional12);
            this.setBatteryConfigurationsProvider = create8;
            this.provideBatteryConfigurationsProvider = ConfigurationsModule_ProvideBatteryConfigurationsFactory.create(create8);
            PersistentStorage_Factory create9 = PersistentStorage_Factory.create(this.setApplicationContextProvider, this.provideSharedPreferencesProvider);
            this.persistentStorageProvider = create9;
            this.statsStorageProvider = StatsStorage_Factory.create(create9);
            SystemHealthCapture_Factory create10 = SystemHealthCapture_Factory.create(this.setApplicationContextProvider);
            this.systemHealthCaptureProvider = create10;
            this.batteryCaptureProvider = BatteryCapture_Factory.create(this.provideVersionNameProvider, create10, this.provideClockProvider, this.provideBatteryConfigurationsProvider);
            Provider provider12 = DoubleCheck.provider(PhenotypeFlagsModule_BatterySamplingParametersFactory.create(this.setApplicationContextProvider));
            this.batterySamplingParametersProvider = provider12;
            Provider provider13 = DoubleCheck.provider(BatteryMetricServiceImpl_Factory.create(this.metricRecorderFactoryProvider, this.setApplicationContextProvider, this.appLifecycleMonitorProvider, this.provideListeningScheduledExecutorServiceProvider, this.provideBatteryConfigurationsProvider, this.statsStorageProvider, this.batteryCaptureProvider, provider12, this.provideDeferrableExecutorProvider));
            this.batteryMetricServiceImplProvider = provider13;
            this.batteryServiceProvider = PrimesBatteryDaggerModule_BatteryServiceFactory.create(this.setBatteryConfigurationsProvider, provider13);
            Factory create11 = InstanceFactory.create(optional9);
            this.setJankConfigurationsProvider = create11;
            this.provideJankConfigurationsProvider = ConfigurationsModule_ProvideJankConfigurationsFactory.create(create11);
            this.frameMetricServiceImplProvider = new DelegateFactory();
            this.enableAlwaysOnJankRecordingProvider = DoubleCheck.provider(PhenotypeFlagsModule_EnableAlwaysOnJankRecordingFactory.create(this.setApplicationContextProvider));
            Factory create12 = InstanceFactory.create(optional15);
            this.setMonitorAllActivitiesProvider = create12;
            this.activityLevelJankMonitorProvider = DoubleCheck.provider(ActivityLevelJankMonitor_Factory.create(this.frameMetricServiceImplProvider, this.enableAlwaysOnJankRecordingProvider, create12, this.provideDeferrableExecutorProvider));
            this.frameTimeHistogramProvider = FrameTimeHistogram_Factory.create(this.provideClockProvider);
            this.jankSamplingParametersProvider = DoubleCheck.provider(PhenotypeFlagsModule_JankSamplingParametersFactory.create(this.setApplicationContextProvider));
            Provider absentGuavaOptionalProvider4 = DaggerProdInternalComponent.absentGuavaOptionalProvider();
            this.lightweightExecutorOptionalOfLooperProvider = absentGuavaOptionalProvider4;
            this.handlerForFrameMetricsProvider = DoubleCheck.provider(PrimesJankDaggerModule_HandlerForFrameMetricsFactory.create(absentGuavaOptionalProvider4));
            Provider provider14 = SingleCheck.provider(PrimesClockModule_TickerFactory.create(this.provideClockProvider));
            this.tickerProvider = provider14;
            this.perfettoTriggerProvider = DoubleCheck.provider(PerfettoTrigger_Factory.create(provider14));
            this.jankPerfettoConfigurationsProvider = DoubleCheck.provider(PhenotypeFlagsModule_JankPerfettoConfigurationsFactory.create(this.setApplicationContextProvider));
            this.allowPhenotypeDuringInitializationOptionalOfBooleanProvider = DaggerProdInternalComponent.absentGuavaOptionalProvider();
            Provider provider15 = DoubleCheck.provider(PhenotypeFlagsModule_RegisterFrameMetricsListenerInOnCreateFactory.create(this.setApplicationContextProvider));
            this.registerFrameMetricsListenerInOnCreateProvider = provider15;
            PrimesJankDaggerModule_RegisterFrameMetricsListenerInOnCreateFactory create13 = PrimesJankDaggerModule_RegisterFrameMetricsListenerInOnCreateFactory.create(this.allowPhenotypeDuringInitializationOptionalOfBooleanProvider, provider15);
            this.registerFrameMetricsListenerInOnCreateProvider2 = create13;
            DelegateFactory.setDelegate(this.frameMetricServiceImplProvider, DoubleCheck.provider(FrameMetricServiceImpl_Factory.create(this.metricRecorderFactoryProvider, this.setApplicationContextProvider, this.appLifecycleMonitorProvider, this.provideJankConfigurationsProvider, this.activityLevelJankMonitorProvider, this.frameTimeHistogramProvider, this.jankSamplingParametersProvider, this.provideDeferrableExecutorProvider, this.handlerForFrameMetricsProvider, this.perfettoTriggerProvider, this.jankPerfettoConfigurationsProvider, create13)));
            this.jankServiceProvider = PrimesJankDaggerModule_JankServiceFactory.create(this.setJankConfigurationsProvider, this.frameMetricServiceImplProvider);
            Factory create14 = InstanceFactory.create(optional5);
            this.setCrashConfigurationsProvider = create14;
            this.provideCrashConfigurationsProvider = ConfigurationsModule_ProvideCrashConfigurationsFactory.create(create14);
            this.optionalOfProviderOfNativeCrashHandlerProvider = DaggerProdInternalComponent.absentGuavaOptionalProvider();
            this.probabilitySamplerFactoryProvider = ProbabilitySamplerFactory_Factory.create(this.randomProvider);
            this.enableUnifiedInitOptionalOfBooleanProvider = DaggerProdInternalComponent.absentGuavaOptionalProvider();
            this.recordingTimeoutsProvider = SingleCheck.provider(PhenotypeFlagsModule_RecordingTimeoutsFactory.create(this.setApplicationContextProvider));
            Provider provider16 = DoubleCheck.provider(CrashMetricServiceImpl_Factory.create(this.metricRecorderFactoryProvider, this.setApplicationContextProvider, this.provideDeferrableExecutorProvider, this.provideCrashConfigurationsProvider, this.optionalOfProviderOfNativeCrashHandlerProvider, this.appLifecycleMonitorProvider, this.probabilitySamplerFactoryProvider, SetFactory.empty(), this.enableUnifiedInitOptionalOfBooleanProvider, this.recordingTimeoutsProvider));
            this.crashMetricServiceImplProvider = provider16;
            this.crashServiceProvider = PrimesCrashDaggerModule_CrashServiceFactory.create(this.setCrashConfigurationsProvider, provider16);
            Factory create15 = InstanceFactory.create(optional7);
            this.setNetworkConfigurationsProvider = create15;
            ConfigurationsModule_ProvideNetworkConfigurationsFactory create16 = ConfigurationsModule_ProvideNetworkConfigurationsFactory.create(create15);
            this.provideNetworkConfigurationsProvider = create16;
            this.networkMetricCollectorProvider = NetworkMetricCollector_Factory.create(create16);
            Provider provider17 = DoubleCheck.provider(PhenotypeFlagsModule_NetworkSamplingParametersFactory.create(this.setApplicationContextProvider));
            this.networkSamplingParametersProvider = provider17;
            Provider provider18 = DoubleCheck.provider(NetworkMetricServiceImpl_Factory.create(this.metricRecorderFactoryProvider, this.setApplicationContextProvider, this.appLifecycleMonitorProvider, this.provideListeningScheduledExecutorServiceProvider, this.provideNetworkConfigurationsProvider, this.networkMetricCollectorProvider, provider17, this.provideDeferrableExecutorProvider));
            this.networkMetricServiceImplProvider = provider18;
            this.networkMetricServiceProvider = PrimesNetworkDaggerModule_NetworkMetricServiceFactory.create(this.setNetworkConfigurationsProvider, provider18);
            Factory create17 = InstanceFactory.create(optional13);
            this.setCpuProfilingConfigurationsProvider = create17;
            this.provideCpuProfilingConfigurationsProvider = ConfigurationsModule_ProvideCpuProfilingConfigurationsFactory.create(create17);
            this.cpuProfilingSamplingParametersProvider = DoubleCheck.provider(PhenotypeFlagsModule_CpuProfilingSamplingParametersFactory.create(this.setApplicationContextProvider));
            CpuProfilingServiceScheduler_Factory create18 = CpuProfilingServiceScheduler_Factory.create(this.provideClockProvider, this.provideCpuProfilingConfigurationsProvider, this.setApplicationContextProvider);
            this.cpuProfilingServiceSchedulerProvider = create18;
            Provider provider19 = DoubleCheck.provider(CpuProfilingService_Factory.create(this.metricRecorderFactoryProvider, this.setApplicationContextProvider, this.provideListeningScheduledExecutorServiceProvider, this.provideCpuProfilingConfigurationsProvider, this.cpuProfilingSamplingParametersProvider, this.provideClockProvider, create18));
            this.cpuProfilingServiceProvider = provider19;
            this.cpuProfilingServiceProvider2 = PrimesCpuProfilingDaggerModule_CpuProfilingServiceFactory.create(this.setCpuProfilingConfigurationsProvider, provider19);
            Factory create19 = InstanceFactory.create(optional8);
            this.setStorageConfigurationsProvider = create19;
            this.provideStorageConfigurationsProvider = ConfigurationsModule_ProvideStorageConfigurationsFactory.create(create19);
            this.persistentRateLimitingProvider = PersistentRateLimiting_Factory.create(this.setApplicationContextProvider, this.provideClockProvider, this.provideSharedPreferencesProvider);
            Provider provider20 = DoubleCheck.provider(PhenotypeFlagsModule_StorageSamplingParametersFactory.create(this.setApplicationContextProvider));
            this.storageSamplingParametersProvider = provider20;
            Provider provider21 = DoubleCheck.provider(StorageMetricServiceImpl_Factory.create(this.metricRecorderFactoryProvider, this.setApplicationContextProvider, this.appLifecycleMonitorProvider, this.provideDeferrableExecutorProvider, this.provideStorageConfigurationsProvider, this.persistentRateLimitingProvider, provider20));
            this.storageMetricServiceImplProvider = provider21;
            this.storageMetricServiceProvider = PrimesStorageDaggerModule_StorageMetricServiceFactory.create(this.setStorageConfigurationsProvider, provider21);
            this.setTimerConfigurationsProvider = InstanceFactory.create(optional4);
            this.setTraceConfigurationsProvider = InstanceFactory.create(optional11);
            Factory create20 = InstanceFactory.create(optional10);
            this.setTikTokTraceConfigurationsProvider = create20;
            this.provideTikTokTraceConfigurationsProvider = ConfigurationsModule_ProvideTikTokTraceConfigurationsFactory.create(create20);
            this.provideTraceConfigurationsProvider = ConfigurationsModule_ProvideTraceConfigurationsFactory.create(this.setTraceConfigurationsProvider);
            Provider provider22 = DoubleCheck.provider(PhenotypeFlagsModule_TraceSamplingParametersFactory.create(this.setApplicationContextProvider));
            this.traceSamplingParametersProvider = provider22;
            Provider provider23 = DoubleCheck.provider(TraceMetricServiceImpl_Factory.create(this.metricRecorderFactoryProvider, this.provideListeningScheduledExecutorServiceProvider, this.provideTikTokTraceConfigurationsProvider, this.provideTraceConfigurationsProvider, provider22, this.probabilitySamplerFactoryProvider));
            this.traceMetricServiceImplProvider = provider23;
            this.timerMetricServiceSupportProvider = PrimesTraceDaggerModule_TimerMetricServiceSupportFactory.create(this.setTraceConfigurationsProvider, this.setTikTokTraceConfigurationsProvider, provider23);
        }

        private void initialize2(Context context, Supplier supplier, Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, Optional optional6, Optional optional7, Optional optional8, Optional optional9, Optional optional10, Optional optional11, Optional optional12, Optional optional13, Optional optional14, Optional optional15, Optional optional16) {
            this.provideTimerConfigurationsProvider = ConfigurationsModule_ProvideTimerConfigurationsFactory.create(this.setTimerConfigurationsProvider);
            Provider provider = DoubleCheck.provider(PhenotypeFlagsModule_TimerSamplingParametersFactory.create(this.setApplicationContextProvider));
            this.timerSamplingParametersProvider = provider;
            Provider provider2 = DoubleCheck.provider(TimerMetricServiceImpl_Factory.create(this.metricRecorderFactoryProvider, this.provideDeferrableExecutorProvider, this.provideTimerConfigurationsProvider, provider, this.probabilitySamplerFactoryProvider));
            this.timerMetricServiceImplProvider = provider2;
            Provider provider3 = DoubleCheck.provider(TimerMetricServiceWithTracingImpl_Factory.create(provider2, this.timerMetricServiceSupportProvider));
            this.timerMetricServiceWithTracingImplProvider = provider3;
            this.timerServiceProvider = PrimesTimerDaggerModule_TimerServiceFactory.create(this.setTimerConfigurationsProvider, this.setTraceConfigurationsProvider, this.timerMetricServiceSupportProvider, this.timerMetricServiceImplProvider, provider3);
            this.traceServiceProvider = PrimesTraceDaggerModule_TraceServiceFactory.create(this.setTraceConfigurationsProvider, this.setTikTokTraceConfigurationsProvider, this.traceMetricServiceImplProvider);
            this.memoryMetricMonitorProvider = DoubleCheck.provider(MemoryMetricMonitor_Factory.create(this.appLifecycleMonitorProvider, this.provideListeningScheduledExecutorServiceProvider));
            Factory create = InstanceFactory.create(optional);
            this.setMemoryConfigurationsProvider = create;
            ProdMemoryConfigModule_ProvideMemoryConfigurationsFactory create2 = ProdMemoryConfigModule_ProvideMemoryConfigurationsFactory.create(create);
            this.provideMemoryConfigurationsProvider = create2;
            this.memoryUsageCaptureProvider = MemoryUsageCapture_Factory.create(create2, this.setApplicationContextProvider);
            Provider provider4 = DoubleCheck.provider(PhenotypeFlagsModule_MemorySamplingParametersFactory.create(this.setApplicationContextProvider));
            this.memorySamplingParametersProvider = provider4;
            this.memoryMetricServiceImplProvider = DoubleCheck.provider(MemoryMetricServiceImpl_Factory.create(this.metricRecorderFactoryProvider, this.provideClockProvider, this.setApplicationContextProvider, this.memoryMetricMonitorProvider, this.provideListeningScheduledExecutorServiceProvider, this.provideMemoryConfigurationsProvider, this.memoryUsageCaptureProvider, this.shutdownProvider, provider4, this.provideDeferrableExecutorProvider, this.enableUnifiedInitOptionalOfBooleanProvider));
            Provider absentGuavaOptionalProvider = DaggerProdInternalComponent.absentGuavaOptionalProvider();
            this.optionalOfStartupConfigurationsProvider = absentGuavaOptionalProvider;
            this.provideStartupConfigurationsProvider = PrimesStartupDaggerModule_ProvideStartupConfigurationsFactory.create(absentGuavaOptionalProvider);
            Provider provider5 = DoubleCheck.provider(PhenotypeFlagsModule_StartupSamplingParametersFactory.create(this.setApplicationContextProvider));
            this.startupSamplingParametersProvider = provider5;
            this.startupMetricRecordingServiceProvider = DoubleCheck.provider(StartupMetricRecordingService_Factory.create(this.metricRecorderFactoryProvider, this.provideListeningScheduledExecutorServiceProvider, this.provideDeferrableExecutorProvider, this.provideStartupConfigurationsProvider, provider5));
            this.enableStartupBaselineDiscardingProvider = DoubleCheck.provider(PhenotypeFlagsModule_EnableStartupBaselineDiscardingFactory.create(this.setApplicationContextProvider));
            Provider provider6 = DoubleCheck.provider(PhenotypeFlagsModule_FirstDrawTypeFactory.create(this.setApplicationContextProvider));
            this.firstDrawTypeProvider = provider6;
            this.startupMetricServiceImplProvider = StartupMetricServiceImpl_Factory.create(this.appLifecycleMonitorProvider, this.startupMetricRecordingServiceProvider, this.enableStartupBaselineDiscardingProvider, provider6);
            this.setOfMetricServiceProvider = SetFactory.builder(2, 9).addCollectionProvider(this.applicationExitMetricServiceProvider).addCollectionProvider(this.batteryServiceProvider).addCollectionProvider(this.jankServiceProvider).addCollectionProvider(this.crashServiceProvider).addCollectionProvider(this.networkMetricServiceProvider).addCollectionProvider(this.cpuProfilingServiceProvider2).addCollectionProvider(this.storageMetricServiceProvider).addCollectionProvider(this.timerServiceProvider).addCollectionProvider(this.traceServiceProvider).addProvider(this.memoryMetricServiceImplProvider).addProvider(this.startupMetricServiceImplProvider).build();
            this.metricServiceProvider = PrimesBatteryDaggerModule_MetricServiceFactory.create(this.setBatteryConfigurationsProvider, this.batteryMetricServiceImplProvider);
            this.metricServiceProvider2 = PrimesJankDaggerModule_MetricServiceFactory.create(this.setJankConfigurationsProvider, this.frameMetricServiceImplProvider);
            Factory create3 = InstanceFactory.create(optional2);
            this.setDebugMemoryConfigurationsProvider = create3;
            ConfigurationsModule_ProvideDebugMemoryConfigurationsFactory create4 = ConfigurationsModule_ProvideDebugMemoryConfigurationsFactory.create(create3);
            this.provideDebugMemoryConfigurationsProvider = create4;
            this.debugMemoryMetricServiceImplProvider = DoubleCheck.provider(DebugMemoryMetricServiceImpl_Factory.create(create4, this.provideDeferrableExecutorProvider, this.memoryMetricServiceImplProvider, this.provideClockProvider));
            this.metricServiceProvider3 = PrimesStorageDaggerModule_MetricServiceFactory.create(this.setStorageConfigurationsProvider, this.storageMetricServiceImplProvider);
            this.metricServiceProvider4 = PrimesTimerDaggerModule_MetricServiceFactory.create(this.setTimerConfigurationsProvider, this.timerMetricServiceSupportProvider, this.timerMetricServiceWithTracingImplProvider, this.timerMetricServiceImplProvider);
            this.metricServiceProvider5 = PrimesTraceDaggerModule_MetricServiceFactory.create(this.setTraceConfigurationsProvider, this.setTikTokTraceConfigurationsProvider, this.traceMetricServiceImplProvider);
            PrimesTimerDaggerModule_ProvideCustomDurationMetricServiceFactory create5 = PrimesTimerDaggerModule_ProvideCustomDurationMetricServiceFactory.create(this.setTimerConfigurationsProvider, this.timerMetricServiceSupportProvider, this.timerMetricServiceWithTracingImplProvider, this.timerMetricServiceImplProvider);
            this.provideCustomDurationMetricServiceProvider = create5;
            Provider provider7 = this.setApplicationContextProvider;
            Provider provider8 = this.provideListeningScheduledExecutorServiceProvider;
            Provider provider9 = this.shutdownProvider;
            Provider provider10 = this.setOfMetricServiceProvider;
            Provider provider11 = this.setOfMetricTransmitterProvider;
            Provider provider12 = this.provideNetworkConfigurationsProvider;
            Provider provider13 = this.metricServiceProvider;
            Provider provider14 = this.crashMetricServiceImplProvider;
            Provider provider15 = this.metricServiceProvider2;
            Provider provider16 = this.memoryMetricServiceImplProvider;
            this.primesApiImplProvider = PrimesApiImpl_Factory.create(provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider16, this.debugMemoryMetricServiceImplProvider, provider16, this.networkMetricServiceImplProvider, this.metricServiceProvider3, this.metricServiceProvider4, this.metricServiceProvider5, create5, this.startupMetricServiceImplProvider, this.enableUnifiedInitOptionalOfBooleanProvider, this.crashOnBadPrimesConfigurationProvider);
        }

        private PrimesApi primesInternalPrimesApi() {
            return LegacyPrimesApiModule_ProvidePrimesApiFactory.providePrimesApi(this.primesApiImplProvider);
        }

        @Override // com.google.android.libraries.performance.primes.Primes.PrimesProvider
        public Primes providePrimes() {
            return LegacyPrimesApiModule_ProvidePrimesFactory.providePrimes(primesInternalPrimesApi());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Provider absentGuavaOptionalProvider() {
        return ABSENT_GUAVA_OPTIONAL_PROVIDER;
    }

    public static ProdInternalComponent.Builder builder() {
        return new Builder();
    }
}
