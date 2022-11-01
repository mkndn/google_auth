package com.google.android.libraries.performance.primes.sampling;

import com.google.android.libraries.clock.Clock;
import com.google.common.base.Strings;
import java.util.Random;
import javax.inject.Inject;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class SamplingStrategy {
    protected static final long MAX_PERMILLE = 1000;
    private final SystemHealthProto$SamplingParameters samplingParameters;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.sampling.SamplingStrategy$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$logs$proto$wireless$performance$mobile$SystemHealthProto$SamplingParameters$SamplingStrategy;

        static {
            int[] iArr = new int[SystemHealthProto$SamplingParameters.SamplingStrategy.values().length];
            $SwitchMap$logs$proto$wireless$performance$mobile$SystemHealthProto$SamplingParameters$SamplingStrategy = iArr;
            try {
                iArr[SystemHealthProto$SamplingParameters.SamplingStrategy.SAMPLING_STRATEGY_FLOOR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$logs$proto$wireless$performance$mobile$SystemHealthProto$SamplingParameters$SamplingStrategy[SystemHealthProto$SamplingParameters.SamplingStrategy.SAMPLING_STRATEGY_PROCESS_LEVEL_PROBABILITY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$logs$proto$wireless$performance$mobile$SystemHealthProto$SamplingParameters$SamplingStrategy[SystemHealthProto$SamplingParameters.SamplingStrategy.SAMPLING_STRATEGY_DYNAMIC_EVENT_PROBABILITY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$logs$proto$wireless$performance$mobile$SystemHealthProto$SamplingParameters$SamplingStrategy[SystemHealthProto$SamplingParameters.SamplingStrategy.SAMPLING_STRATEGY_FIXED_EVENT_PROBABILITY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$logs$proto$wireless$performance$mobile$SystemHealthProto$SamplingParameters$SamplingStrategy[SystemHealthProto$SamplingParameters.SamplingStrategy.SAMPLING_STRATEGY_ALWAYS_ON.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$logs$proto$wireless$performance$mobile$SystemHealthProto$SamplingParameters$SamplingStrategy[SystemHealthProto$SamplingParameters.SamplingStrategy.UNKNOWN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BasicSamplingStrategy extends SamplingStrategy {
        private final boolean shouldSample;

        BasicSamplingStrategy(SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters, boolean z) {
            super(systemHealthProto$SamplingParameters);
            this.shouldSample = z;
        }

        private SystemHealthProto$SamplingParameters samplingParametersIfEnabled(Long l) {
            if (this.shouldSample) {
                return getSamplingParameters(l);
            }
            return getSamplingOffParameters();
        }

        @Override // com.google.android.libraries.performance.primes.sampling.SamplingStrategy
        public boolean getMetricServiceEnabled() {
            return this.shouldSample;
        }

        @Override // com.google.android.libraries.performance.primes.sampling.SamplingStrategy
        public SystemHealthProto$SamplingParameters getSamplingParametersIfShouldRecord(Long l) {
            return samplingParametersIfEnabled(l);
        }

        @Override // com.google.android.libraries.performance.primes.sampling.SamplingStrategy
        public long getSamplingRatePermilleIfShouldSample(String str) {
            SystemHealthProto$SamplingParameters samplingParametersIfEnabled = samplingParametersIfEnabled(null);
            if (samplingParametersIfEnabled.equals(SystemHealthProto$SamplingParameters.getDefaultInstance())) {
                return 1000L;
            }
            return samplingParametersIfEnabled.getSampleRatePermille();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DynamicEventProbabilitySamplingStrategy extends SamplingStrategy {
        private final long baseProbability;
        private final Clock clock;
        private final ApproximateHistogram histogram;
        private final Random random;

        DynamicEventProbabilitySamplingStrategy(SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters, Random random, ApproximateHistogram approximateHistogram, Clock clock) {
            super(systemHealthProto$SamplingParameters);
            this.random = random;
            this.baseProbability = systemHealthProto$SamplingParameters.getSampleRatePermille();
            this.histogram = approximateHistogram;
            this.clock = clock;
        }

        private long getSamplingRate(String str) {
            double d;
            int addAndScale = this.histogram.addAndScale(str, this.clock.elapsedRealtime(), 1);
            double sqrt = addAndScale < 50 ? Math.sqrt(addAndScale) : addAndScale;
            Double.isNaN(this.baseProbability);
            return (int) (d / sqrt);
        }

        @Override // com.google.android.libraries.performance.primes.sampling.SamplingStrategy
        public boolean getMetricServiceEnabled() {
            return this.baseProbability > 0;
        }

        @Override // com.google.android.libraries.performance.primes.sampling.SamplingStrategy
        public SystemHealthProto$SamplingParameters getSamplingParametersIfShouldRecord(Long l) {
            if (getMetricServiceEnabled()) {
                return getSamplingParameters(l);
            }
            return getSamplingOffParameters();
        }

        @Override // com.google.android.libraries.performance.primes.sampling.SamplingStrategy
        public long getSamplingRatePermilleIfShouldSample(String str) {
            long samplingRate = Strings.isNullOrEmpty(str) ? this.baseProbability : getSamplingRate(str);
            if (getSamplingDecisionFromSamplingRatePermille(samplingRate, this.random)) {
                return samplingRate;
            }
            return -1L;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Factory {
        static final SamplingStrategy DEFAULT_UNKNOWN_SAMPLING_STRATEGY = new BasicSamplingStrategy(SystemHealthProto$SamplingParameters.getDefaultInstance(), true);
        private final ApproximateHistogram approximateHistogram;
        private final Clock clock;
        private final Random random;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Inject
        public Factory(Random random, ApproximateHistogram approximateHistogram, Clock clock) {
            this.random = random;
            this.clock = clock;
            this.approximateHistogram = approximateHistogram;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public SamplingStrategy create(SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters) {
            switch (AnonymousClass1.$SwitchMap$logs$proto$wireless$performance$mobile$SystemHealthProto$SamplingParameters$SamplingStrategy[systemHealthProto$SamplingParameters.getSamplingStrategy().ordinal()]) {
                case 1:
                    return new BasicSamplingStrategy(systemHealthProto$SamplingParameters, systemHealthProto$SamplingParameters.getSampleRatePermille() == 1000);
                case 2:
                    return new BasicSamplingStrategy(systemHealthProto$SamplingParameters, this.random.nextDouble() * 1000.0d < ((double) systemHealthProto$SamplingParameters.getSampleRatePermille()));
                case 3:
                    return new DynamicEventProbabilitySamplingStrategy(systemHealthProto$SamplingParameters, this.random, this.approximateHistogram, this.clock);
                case 4:
                    systemHealthProto$SamplingParameters = SystemHealthProto$SamplingParameters.getDefaultInstance();
                    break;
            }
            return new BasicSamplingStrategy(systemHealthProto$SamplingParameters, true);
        }
    }

    private SamplingStrategy(SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters) {
        this.samplingParameters = systemHealthProto$SamplingParameters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean getMetricServiceEnabled();

    public boolean getSamplingDecisionFromSamplingRatePermille(long j, Random random) {
        return random.nextDouble() * 1000.0d < ((double) j);
    }

    public SystemHealthProto$SamplingParameters getSamplingOffParameters() {
        return (SystemHealthProto$SamplingParameters) ((SystemHealthProto$SamplingParameters.Builder) getSamplingParameters(null).toBuilder()).setSampleRatePermille(-1L).build();
    }

    public SystemHealthProto$SamplingParameters getSamplingParameters(Long l) {
        if (this.samplingParameters.getSamplingStrategy() != SystemHealthProto$SamplingParameters.SamplingStrategy.SAMPLING_STRATEGY_DYNAMIC_EVENT_PROBABILITY) {
            return this.samplingParameters;
        }
        if (l != null && l.longValue() != this.samplingParameters.getSampleRatePermille()) {
            return (SystemHealthProto$SamplingParameters) SystemHealthProto$SamplingParameters.newBuilder().setSamplingStrategy(this.samplingParameters.getSamplingStrategy()).setSampleRatePermille(l.longValue()).build();
        }
        return this.samplingParameters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract SystemHealthProto$SamplingParameters getSamplingParametersIfShouldRecord(Long l);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract long getSamplingRatePermilleIfShouldSample(String str);
}
