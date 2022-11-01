package com.google.android.libraries.performance.primes.metrics.jank;

import java.util.List;
import logs.proto.wireless.performance.mobile.SystemHealthProto$HistogramBucket;
import logs.proto.wireless.performance.mobile.SystemHealthProto$JankMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JankMetricReencoder {
    public static SystemHealthProto$JankMetric packJankHistogram(SystemHealthProto$JankMetric systemHealthProto$JankMetric) {
        int max;
        List<SystemHealthProto$HistogramBucket> frameTimeHistogramList = systemHealthProto$JankMetric.getFrameTimeHistogramList();
        if (frameTimeHistogramList.isEmpty()) {
            return systemHealthProto$JankMetric;
        }
        SystemHealthProto$PackedHistogram.Builder newBuilder = SystemHealthProto$PackedHistogram.newBuilder();
        SystemHealthProto$HistogramBucket systemHealthProto$HistogramBucket = null;
        for (SystemHealthProto$HistogramBucket systemHealthProto$HistogramBucket2 : frameTimeHistogramList) {
            if (systemHealthProto$HistogramBucket != null && (max = systemHealthProto$HistogramBucket.getMax() + 1) != systemHealthProto$HistogramBucket2.getMin()) {
                newBuilder.addPopulation(0);
                newBuilder.addLowerBound(max);
            }
            newBuilder.addPopulation(systemHealthProto$HistogramBucket2.getCount());
            newBuilder.addLowerBound(systemHealthProto$HistogramBucket2.getMin());
            systemHealthProto$HistogramBucket = systemHealthProto$HistogramBucket2;
        }
        if (systemHealthProto$HistogramBucket != null && systemHealthProto$HistogramBucket.hasMax()) {
            int max2 = systemHealthProto$HistogramBucket.getMax();
            newBuilder.addPopulation(0);
            newBuilder.addLowerBound(max2 + 1);
        }
        return (SystemHealthProto$JankMetric) ((SystemHealthProto$JankMetric.Builder) systemHealthProto$JankMetric.toBuilder()).clearFrameTimeHistogram().setPackedFrameTimeHistogram(newBuilder).build();
    }
}
