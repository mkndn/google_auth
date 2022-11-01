package com.google.android.libraries.performance.primes.metrics.jank;

import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.libraries.clock.Clock;
import com.google.common.flogger.GoogleLogger;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import javax.inject.Inject;
import logs.proto.wireless.performance.mobile.SystemHealthProto$HistogramBucket;
import logs.proto.wireless.performance.mobile.SystemHealthProto$JankMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FrameTimeHistogram {
    private final Clock clock;
    private int droppedReportCount;
    private int framesMissingRefreshRateBasedDrawDeadline;
    private int jankyFrameCount;
    private int maxFrameDurationMs;
    private final long recordingStartTimeMs;
    private int renderedFrameCount;
    private int totalDurationOfFramesMissingRefreshRateDeadlineMs;
    private int totalFrameDurationMs;
    private int totalJankyFrameDurationMs;
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/jank/FrameTimeHistogram");
    private static final int[] BUCKETS_BOUNDS = {0, 4, 8, 10, 12, 14, 16, 18, 20, 25, 30, 40, 50, 60, 70, 80, 90, 100, AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_1P_APP_FUNCTIONAL_DEBUGGING_VALUE, 200, AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLAY_CONSOLE_MEASURING_USER_ENGAGEMENT_VALUE, AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_SUPERSORT_PRODUCT_IMPROVEMENT_VALUE, 500, DeviceProperties.SEVEN_INCH_TABLET_MINIMUM_SCREEN_WIDTH_DP, 700, 800, 900, 1000};
    private static final int[] NEGATIVE_SLACK_BUCKET_BOUNDS = {Integer.MIN_VALUE, -200, -150, -100, -90, -80, -70, -60, -50, -40, -30, -25, -20, -18, -16, -14, -12, -10, -8, -6, -4, -2, 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 25, 30, 40, 50, 60, 70, 80, 90, 100, AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_1P_APP_FUNCTIONAL_DEBUGGING_VALUE, 200, AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLAY_CONSOLE_MEASURING_USER_ENGAGEMENT_VALUE, AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_SUPERSORT_PRODUCT_IMPROVEMENT_VALUE, 500, DeviceProperties.SEVEN_INCH_TABLET_MINIMUM_SCREEN_WIDTH_DP, 700, 800, 900, 1000};
    private static final int[] FINER_BUCKETS_BOUNDS = {0, 4, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 31, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 60, 70, 80, 90, 100, AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_1P_APP_FUNCTIONAL_DEBUGGING_VALUE, 200, AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLAY_CONSOLE_MEASURING_USER_ENGAGEMENT_VALUE, AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_SUPERSORT_PRODUCT_IMPROVEMENT_VALUE, 500, DeviceProperties.SEVEN_INCH_TABLET_MINIMUM_SCREEN_WIDTH_DP, 700, 800, 900, 1000};
    private final int[] buckets = new int[BUCKETS_BOUNDS.length];
    private final int[] slackBuckets = new int[NEGATIVE_SLACK_BUCKET_BOUNDS.length];
    private int maxSlackTimeMs = Integer.MIN_VALUE;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public FrameTimeHistogram(Clock clock) {
        this.clock = clock;
        this.recordingStartTimeMs = clock.elapsedRealtime();
    }

    static int indexForFrameTime(int i) {
        if (i <= 20) {
            if (i >= 8) {
                return (i / 2) - 2;
            }
            return i / 4;
        } else if (i <= 30) {
            return (i / 5) + 4;
        } else {
            if (i <= 100) {
                return (i / 10) + 7;
            }
            if (i <= 200) {
                return (i / 50) + 15;
            }
            if (i <= 1000) {
                return (i / 100) + 17;
            }
            return 27;
        }
    }

    static int indexForNegativeSlackTime(int i) {
        if (i < 20) {
            if (i >= -20) {
                return ((i + 20) / 2) + 12;
            }
            if (i >= -30) {
                return ((i + 30) / 5) + 10;
            }
            if (i >= -100) {
                return ((i + 100) / 10) + 3;
            }
            if (i >= -200) {
                return ((i + 200) / 50) + 1;
            }
            return 0;
        } else if (i < 30) {
            return ((i - 20) / 5) + 32;
        } else {
            if (i < 100) {
                return ((i - 30) / 10) + 34;
            }
            if (i < 200) {
                return ((i - 50) / 100) + 41;
            }
            if (i < 1000) {
                return ((i - 200) / 100) + 43;
            }
            return NEGATIVE_SLACK_BUCKET_BOUNDS.length - 1;
        }
    }

    private static SystemHealthProto$PackedHistogram toPackedHistogram(int[] iArr, int[] iArr2, int i) {
        SystemHealthProto$PackedHistogram.Builder newBuilder = SystemHealthProto$PackedHistogram.newBuilder();
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] <= i) {
                int i3 = iArr2[i2];
                if (i3 > 0 || (i2 > 0 && iArr2[i2 - 1] > 0)) {
                    newBuilder.addPopulation(i3).addLowerBound(iArr[i2]);
                }
            } else {
                return (SystemHealthProto$PackedHistogram) newBuilder.addPopulation(0).addLowerBound(i + 1).build();
            }
        }
        if (iArr2[iArr2.length - 1] > 0) {
            newBuilder.addLowerBound(i + 1).addPopulation(0);
        }
        return (SystemHealthProto$PackedHistogram) newBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addFrame(int i, int i2, int i3, int i4) {
        if (i < 0) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/FrameTimeHistogram", "addFrame", 78, "FrameTimeHistogram.java")).log("Invalid frame time: %d", i);
            this.droppedReportCount++;
            return;
        }
        this.renderedFrameCount++;
        if (i4 > 0) {
            int i5 = i - i4;
            if (this.maxSlackTimeMs < i5) {
                this.maxSlackTimeMs = i5;
            }
            int[] iArr = this.slackBuckets;
            int indexForNegativeSlackTime = indexForNegativeSlackTime(i5);
            iArr[indexForNegativeSlackTime] = iArr[indexForNegativeSlackTime] + 1;
            if (i > i4) {
                this.jankyFrameCount++;
                this.totalJankyFrameDurationMs += i;
            }
            if (i > i2) {
                this.framesMissingRefreshRateBasedDrawDeadline++;
                this.totalDurationOfFramesMissingRefreshRateDeadlineMs += i;
            }
        } else if (i > i2) {
            this.jankyFrameCount++;
            this.totalJankyFrameDurationMs += i;
        }
        int[] iArr2 = this.buckets;
        int indexForFrameTime = indexForFrameTime(i);
        iArr2[indexForFrameTime] = iArr2[indexForFrameTime] + 1;
        this.droppedReportCount += i3;
        if (this.maxFrameDurationMs < i) {
            this.maxFrameDurationMs = i;
        }
        this.totalFrameDurationMs += i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getDroppedReportCount() {
        return this.droppedReportCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getJankyFrameCount() {
        return this.jankyFrameCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMaxFrameDurationMs() {
        return this.maxFrameDurationMs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SystemHealthProto$JankMetric getMetric() {
        SystemHealthProto$JankMetric.Builder maxFrameRenderTimeMs = SystemHealthProto$JankMetric.newBuilder().setRecordingDurationMs(((int) (this.clock.elapsedRealtime() - this.recordingStartTimeMs)) + 1).setJankyFrameCount(this.jankyFrameCount).setRenderedFrameCount(this.renderedFrameCount).setDroppedReportCount(this.droppedReportCount).setJankyDurationMs(this.totalJankyFrameDurationMs).setDurationMs(this.totalFrameDurationMs).setMaxFrameRenderTimeMs(this.maxFrameDurationMs);
        int i = this.maxSlackTimeMs;
        if (i != Integer.MIN_VALUE) {
            maxFrameRenderTimeMs.setSlackTimeHistogram(toPackedHistogram(NEGATIVE_SLACK_BUCKET_BOUNDS, this.slackBuckets, i)).setFramesMissingRefreshRateBasedDrawDeadline(this.framesMissingRefreshRateBasedDrawDeadline).setDurationOfFramesMissingRefreshRateBasedDeadlineMs(this.totalDurationOfFramesMissingRefreshRateDeadlineMs);
        }
        int i2 = 0;
        while (true) {
            int[] iArr = BUCKETS_BOUNDS;
            if (i2 < iArr.length) {
                if (this.buckets[i2] > 0) {
                    SystemHealthProto$HistogramBucket.Builder min = SystemHealthProto$HistogramBucket.newBuilder().setCount(this.buckets[i2]).setMin(iArr[i2]);
                    int i3 = i2 + 1;
                    if (i3 < iArr.length) {
                        min.setMax(iArr[i3] - 1);
                    }
                    maxFrameRenderTimeMs.addFrameTimeHistogram(min);
                }
                i2++;
            } else {
                return (SystemHealthProto$JankMetric) maxFrameRenderTimeMs.build();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRenderedFrameCount() {
        return this.renderedFrameCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTotalFrameDurationMs() {
        return this.totalFrameDurationMs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTotalJankyFrameDurationMs() {
        return this.totalJankyFrameDurationMs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int totalFrameDurationMs() {
        return this.totalFrameDurationMs;
    }
}
