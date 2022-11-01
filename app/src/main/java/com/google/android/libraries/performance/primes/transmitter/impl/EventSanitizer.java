package com.google.android.libraries.performance.primes.transmitter.impl;

import android.text.TextUtils;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.logging.proto2api.Logrecord$ThrowableBlockProto;
import com.google.common.logging.proto2api.Logrecord$ThrowableProto;
import com.google.devrel.primes.hashing.Hashing;
import com.google.protobuf.MessageLite;
import java.util.List;
import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryStatsDiff;
import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryUsageMetric;
import logs.proto.wireless.performance.mobile.NetworkMetric$NetworkEventUsage;
import logs.proto.wireless.performance.mobile.NetworkMetric$NetworkUsageMetric;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$PrimesTrace;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$Span;
import logs.proto.wireless.performance.mobile.SystemHealthProto$CrashMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackageMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EventSanitizer {
    private static final Splitter SLASH_SPLITTER = Splitter.on('/').omitEmptyStrings();
    static final MetricNameAccess SHM_METRIC_NAME_ACCESS = new MetricNameAccess() { // from class: com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.1
        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public String getConstantName(SystemHealthProto$SystemHealthMetric.Builder builder) {
            return builder.getConstantEventName();
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public String getCustomName(SystemHealthProto$SystemHealthMetric.Builder builder) {
            return builder.getCustomEventName();
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public void setCustomName(SystemHealthProto$SystemHealthMetric.Builder builder, String str) {
            if (str != null) {
                builder.setCustomEventName(str);
            } else {
                builder.clearCustomEventName();
            }
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public void setHashedName(SystemHealthProto$SystemHealthMetric.Builder builder, Long l) {
            if (l != null) {
                builder.setHashedCustomEventName(l.longValue());
            } else {
                builder.clearHashedCustomEventName();
            }
        }
    };
    static final MetricNameAccess BATTERY_METRIC_NAME_ACCESS = new MetricNameAccess() { // from class: com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.2
        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public String getConstantName(BatteryMetric$BatteryStatsDiff.Builder builder) {
            return builder.getStartConstantEventName();
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public String getCustomName(BatteryMetric$BatteryStatsDiff.Builder builder) {
            return builder.getStartCustomEventName();
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public void setCustomName(BatteryMetric$BatteryStatsDiff.Builder builder, String str) {
            if (str != null) {
                builder.setStartCustomEventName(str);
            } else {
                builder.clearStartCustomEventName();
            }
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public void setHashedName(BatteryMetric$BatteryStatsDiff.Builder builder, Long l) {
            if (l != null) {
                builder.setStartHashedCustomEventName(l.longValue());
            } else {
                builder.clearStartHashedCustomEventName();
            }
        }
    };
    static final MetricNameAccess SPAN_METRIC_NAME_ACCESS = new MetricNameAccess() { // from class: com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.3
        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public String getConstantName(PrimesTraceOuterClass$Span.Builder builder) {
            return builder.getConstantName();
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public String getCustomName(PrimesTraceOuterClass$Span.Builder builder) {
            return builder.getName();
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public void setCustomName(PrimesTraceOuterClass$Span.Builder builder, String str) {
            if (str == null) {
                builder.clearName();
            } else {
                builder.setName(str);
            }
        }

        @Override // com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer.MetricNameAccess
        public void setHashedName(PrimesTraceOuterClass$Span.Builder builder, Long l) {
            if (l == null) {
                builder.clearHashedName();
            } else {
                builder.setHashedName(l.longValue());
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface MetricNameAccess {
        String getConstantName(MessageLite.Builder builder);

        String getCustomName(MessageLite.Builder builder);

        void setCustomName(MessageLite.Builder builder, String str);

        void setHashedName(MessageLite.Builder builder, Long l);
    }

    private static void convertBatteryUsageMetricNames(SystemHealthProto$SystemHealthMetric.Builder builder) {
        if (!builder.getBatteryUsageMetric().hasBatteryStatsDiff()) {
            return;
        }
        BatteryMetric$BatteryStatsDiff.Builder builder2 = (BatteryMetric$BatteryStatsDiff.Builder) builder.getBatteryUsageMetric().getBatteryStatsDiff().toBuilder();
        ensureNoPiiName(BATTERY_METRIC_NAME_ACCESS, builder2);
        builder.setBatteryUsageMetric(((BatteryMetric$BatteryUsageMetric.Builder) builder.getBatteryUsageMetric().toBuilder()).setBatteryStatsDiff(builder2));
    }

    private static void convertCrashMetricNames(SystemHealthProto$SystemHealthMetric.Builder builder) {
        if (!builder.getCrashMetric().hasException()) {
            return;
        }
        Logrecord$ThrowableProto.Builder builder2 = (Logrecord$ThrowableProto.Builder) builder.getCrashMetric().getException().toBuilder();
        Logrecord$ThrowableBlockProto.Builder builder3 = (Logrecord$ThrowableBlockProto.Builder) builder2.getOutermost().toBuilder();
        String message = builder3.getMessage();
        if (!message.isEmpty()) {
            builder3.setMessageHash(Hashing.hash(message).longValue());
            builder3.clearMessage();
        }
        builder2.setOutermost((Logrecord$ThrowableBlockProto) builder3.build());
        List<Logrecord$ThrowableBlockProto> causesList = builder2.getCausesList();
        builder2.clearCauses();
        for (Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto : causesList) {
            Logrecord$ThrowableBlockProto.Builder builder4 = (Logrecord$ThrowableBlockProto.Builder) logrecord$ThrowableBlockProto.toBuilder();
            String message2 = builder4.getMessage();
            if (!message2.isEmpty()) {
                builder4.setMessageHash(Hashing.hash(message2).longValue());
                builder4.clearMessage();
            }
            builder2.addCauses((Logrecord$ThrowableBlockProto) builder4.build());
        }
        builder.setCrashMetric((SystemHealthProto$CrashMetric) ((SystemHealthProto$CrashMetric.Builder) builder.getCrashMetric().toBuilder()).setException((Logrecord$ThrowableProto) builder2.build()).build());
    }

    private static void convertMetricSpecificFields(SystemHealthProto$SystemHealthMetric.Builder builder) {
        convertBatteryUsageMetricNames(builder);
        convertCrashMetricNames(builder);
        convertPackageDirectoryNames(builder);
        convertRpcPathNames(builder);
        convertSpanNames(builder);
    }

    private static void convertPackageDirectoryNames(SystemHealthProto$SystemHealthMetric.Builder builder) {
        if (builder.getPackageMetric().getDirStatsCount() == 0) {
            return;
        }
        SystemHealthProto$PackageMetric.Builder builder2 = (SystemHealthProto$PackageMetric.Builder) builder.getPackageMetric().toBuilder();
        for (int i = 0; i < builder2.getDirStatsCount(); i++) {
            SystemHealthProto$PackageMetric.DirStats.Builder builder3 = (SystemHealthProto$PackageMetric.DirStats.Builder) builder2.getDirStats(i).toBuilder();
            if (!builder3.getDirPath().isEmpty()) {
                builder3.clearHashedDirPath().addAllHashedDirPath(hashTokens(builder3.getDirPath()));
            }
            builder3.clearDirPath();
            builder2.setDirStats(i, builder3);
        }
        builder.setPackageMetric(builder2);
    }

    private static void convertRpcPathNames(SystemHealthProto$SystemHealthMetric.Builder builder) {
        if (builder.getNetworkUsageMetric().getNetworkEventUsageCount() == 0) {
            return;
        }
        NetworkMetric$NetworkUsageMetric.Builder builder2 = (NetworkMetric$NetworkUsageMetric.Builder) builder.getNetworkUsageMetric().toBuilder();
        for (int i = 0; i < builder2.getNetworkEventUsageCount(); i++) {
            NetworkMetric$NetworkEventUsage.Builder builder3 = (NetworkMetric$NetworkEventUsage.Builder) builder2.getNetworkEventUsage(i).toBuilder();
            if (!builder3.getRpcPath().isEmpty()) {
                builder3.clearHashedRpcPath().addAllHashedRpcPath(hashTokens(builder3.getRpcPath()));
            }
            builder3.clearRpcPath();
            builder2.setNetworkEventUsage(i, builder3);
        }
        builder.setNetworkUsageMetric(builder2);
    }

    private static void convertSpanNames(SystemHealthProto$SystemHealthMetric.Builder builder) {
        if (builder.getPrimesTrace().getSpansCount() == 0) {
            return;
        }
        PrimesTraceOuterClass$PrimesTrace.Builder builder2 = (PrimesTraceOuterClass$PrimesTrace.Builder) builder.getPrimesTrace().toBuilder();
        for (int i = 0; i < builder2.getSpansCount(); i++) {
            PrimesTraceOuterClass$Span.Builder builder3 = (PrimesTraceOuterClass$Span.Builder) builder2.getSpans(i).toBuilder();
            ensureNoPiiName(SPAN_METRIC_NAME_ACCESS, builder3);
            builder2.setSpans(i, builder3);
        }
        builder.setPrimesTrace(builder2);
    }

    private static void convertTopLevelFields(SystemHealthProto$SystemHealthMetric.Builder builder) {
        ensureNoPiiName(SHM_METRIC_NAME_ACCESS, builder);
    }

    static void ensureNoPiiName(MetricNameAccess metricNameAccess, MessageLite.Builder builder) {
        String constantName = metricNameAccess.getConstantName(builder);
        String customName = metricNameAccess.getCustomName(builder);
        if (TextUtils.isEmpty(constantName) && !TextUtils.isEmpty(customName)) {
            metricNameAccess.setHashedName(builder, Hashing.hash(customName));
        } else {
            metricNameAccess.setHashedName(builder, null);
        }
        metricNameAccess.setCustomName(builder, null);
    }

    private static List hashTokens(String str) {
        return Lists.transform(SLASH_SPLITTER.splitToList(str), EventSanitizer$$ExternalSyntheticLambda0.INSTANCE);
    }

    private static void sanitize(SystemHealthProto$SystemHealthMetric.Builder builder) {
        convertTopLevelFields(builder);
        convertMetricSpecificFields(builder);
    }

    public static SystemHealthProto$SystemHealthMetric sanitize(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric) {
        SystemHealthProto$SystemHealthMetric.Builder builder = (SystemHealthProto$SystemHealthMetric.Builder) systemHealthProto$SystemHealthMetric.toBuilder();
        sanitize(builder);
        return (SystemHealthProto$SystemHealthMetric) builder.build();
    }
}
