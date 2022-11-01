package com.google.android.libraries.performance.primes.transmitter;

import com.google.common.util.concurrent.ListenableFuture;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface MetricTransmitter {
    public static final MetricTransmitter NOOP_TRANSMITTER = MetricTransmitter$$ExternalSyntheticLambda0.INSTANCE;

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.transmitter.MetricTransmitter$-CC  reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        static {
            MetricTransmitter metricTransmitter = MetricTransmitter.NOOP_TRANSMITTER;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Priority {
        public static final int BRELLA_PRIORITY = -10;
        public static final int CLEARCUT_PRIORITY = 9;
        static final Priority DEFAULT = new Priority(0);
        public static final int DEFAULT_PRIORITY = 0;
        private final int priority;

        public Priority(int i) {
            this.priority = i;
        }

        public int getPriority() {
            return this.priority;
        }
    }

    Priority getTransmitterPriority();

    ListenableFuture send(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric);
}
