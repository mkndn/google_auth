package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.util.List;
import logs.proto.wireless.performance.mobile.SystemHealthProto$HistogramBucket;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$JankMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final SystemHealthProto$JankMetric DEFAULT_INSTANCE;
    public static final int DEVICE_REFRESH_RATE_FIELD_NUMBER = 6;
    public static final int DROPPED_REPORT_COUNT_FIELD_NUMBER = 7;
    public static final int DURATION_MS_FIELD_NUMBER = 4;
    public static final int DURATION_OF_FRAMES_MISSING_REFRESH_RATE_BASED_DEADLINE_MS_FIELD_NUMBER = 12;
    public static final int FRAMES_MISSING_REFRESH_RATE_BASED_DRAW_DEADLINE_FIELD_NUMBER = 11;
    public static final int FRAME_TIME_HISTOGRAM_FIELD_NUMBER = 5;
    public static final int JANKY_DURATION_MS_FIELD_NUMBER = 9;
    public static final int JANKY_FRAME_COUNT_FIELD_NUMBER = 1;
    public static final int MAX_FRAME_RENDER_TIME_MS_FIELD_NUMBER = 3;
    public static final int PACKED_FRAME_TIME_HISTOGRAM_FIELD_NUMBER = 10;
    private static volatile Parser PARSER = null;
    public static final int RECORDING_DURATION_MS_FIELD_NUMBER = 8;
    public static final int RENDERED_FRAME_COUNT_FIELD_NUMBER = 2;
    public static final int SLACK_TIME_HISTOGRAM_FIELD_NUMBER = 13;
    private int bitField0_;
    private int deviceRefreshRate_;
    private int droppedReportCount_;
    private int durationMs_;
    private int durationOfFramesMissingRefreshRateBasedDeadlineMs_;
    private Internal.ProtobufList frameTimeHistogram_ = emptyProtobufList();
    private int framesMissingRefreshRateBasedDrawDeadline_;
    private int jankyDurationMs_;
    private int jankyFrameCount_;
    private int maxFrameRenderTimeMs_;
    private SystemHealthProto$PackedHistogram packedFrameTimeHistogram_;
    private int recordingDurationMs_;
    private int renderedFrameCount_;
    private SystemHealthProto$PackedHistogram slackTimeHistogram_;

    static {
        SystemHealthProto$JankMetric systemHealthProto$JankMetric = new SystemHealthProto$JankMetric();
        DEFAULT_INSTANCE = systemHealthProto$JankMetric;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$JankMetric.class, systemHealthProto$JankMetric);
    }

    private SystemHealthProto$JankMetric() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFrameTimeHistogram(SystemHealthProto$HistogramBucket systemHealthProto$HistogramBucket) {
        systemHealthProto$HistogramBucket.getClass();
        ensureFrameTimeHistogramIsMutable();
        this.frameTimeHistogram_.add(systemHealthProto$HistogramBucket);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFrameTimeHistogram() {
        this.frameTimeHistogram_ = emptyProtobufList();
    }

    private void ensureFrameTimeHistogramIsMutable() {
        Internal.ProtobufList protobufList = this.frameTimeHistogram_;
        if (!protobufList.isModifiable()) {
            this.frameTimeHistogram_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public static SystemHealthProto$JankMetric getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeviceRefreshRate(int i) {
        this.bitField0_ |= 256;
        this.deviceRefreshRate_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDroppedReportCount(int i) {
        this.bitField0_ |= 4;
        this.droppedReportCount_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDurationMs(int i) {
        this.bitField0_ |= 64;
        this.durationMs_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDurationOfFramesMissingRefreshRateBasedDeadlineMs(int i) {
        this.bitField0_ |= 1024;
        this.durationOfFramesMissingRefreshRateBasedDeadlineMs_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFramesMissingRefreshRateBasedDrawDeadline(int i) {
        this.bitField0_ |= 512;
        this.framesMissingRefreshRateBasedDrawDeadline_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setJankyDurationMs(int i) {
        this.bitField0_ |= 32;
        this.jankyDurationMs_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setJankyFrameCount(int i) {
        this.bitField0_ |= 1;
        this.jankyFrameCount_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMaxFrameRenderTimeMs(int i) {
        this.bitField0_ |= 8;
        this.maxFrameRenderTimeMs_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPackedFrameTimeHistogram(SystemHealthProto$PackedHistogram systemHealthProto$PackedHistogram) {
        systemHealthProto$PackedHistogram.getClass();
        this.packedFrameTimeHistogram_ = systemHealthProto$PackedHistogram;
        this.bitField0_ |= 128;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRecordingDurationMs(int i) {
        this.bitField0_ |= 16;
        this.recordingDurationMs_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRenderedFrameCount(int i) {
        this.bitField0_ |= 2;
        this.renderedFrameCount_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSlackTimeHistogram(SystemHealthProto$PackedHistogram systemHealthProto$PackedHistogram) {
        systemHealthProto$PackedHistogram.getClass();
        this.slackTimeHistogram_ = systemHealthProto$PackedHistogram;
        this.bitField0_ |= 2048;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SystemHealthProto$JankMetric();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\r\u0000\u0001\u0001\r\r\u0000\u0001\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0003\u0004င\u0006\u0005\u001b\u0006င\b\u0007င\u0002\bင\u0004\tင\u0005\nဉ\u0007\u000bင\t\fင\n\rဉ\u000b", new Object[]{"bitField0_", "jankyFrameCount_", "renderedFrameCount_", "maxFrameRenderTimeMs_", "durationMs_", "frameTimeHistogram_", SystemHealthProto$HistogramBucket.class, "deviceRefreshRate_", "droppedReportCount_", "recordingDurationMs_", "jankyDurationMs_", "packedFrameTimeHistogram_", "framesMissingRefreshRateBasedDrawDeadline_", "durationOfFramesMissingRefreshRateBasedDeadlineMs_", "slackTimeHistogram_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$JankMetric.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public List getFrameTimeHistogramList() {
        return this.frameTimeHistogram_;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(SystemHealthProto$JankMetric.DEFAULT_INSTANCE);
        }

        public Builder addFrameTimeHistogram(SystemHealthProto$HistogramBucket.Builder builder) {
            copyOnWrite();
            ((SystemHealthProto$JankMetric) this.instance).addFrameTimeHistogram((SystemHealthProto$HistogramBucket) builder.build());
            return this;
        }

        public Builder clearFrameTimeHistogram() {
            copyOnWrite();
            ((SystemHealthProto$JankMetric) this.instance).clearFrameTimeHistogram();
            return this;
        }

        public Builder setDeviceRefreshRate(int i) {
            copyOnWrite();
            ((SystemHealthProto$JankMetric) this.instance).setDeviceRefreshRate(i);
            return this;
        }

        public Builder setDroppedReportCount(int i) {
            copyOnWrite();
            ((SystemHealthProto$JankMetric) this.instance).setDroppedReportCount(i);
            return this;
        }

        @Deprecated
        public Builder setDurationMs(int i) {
            copyOnWrite();
            ((SystemHealthProto$JankMetric) this.instance).setDurationMs(i);
            return this;
        }

        public Builder setDurationOfFramesMissingRefreshRateBasedDeadlineMs(int i) {
            copyOnWrite();
            ((SystemHealthProto$JankMetric) this.instance).setDurationOfFramesMissingRefreshRateBasedDeadlineMs(i);
            return this;
        }

        public Builder setFramesMissingRefreshRateBasedDrawDeadline(int i) {
            copyOnWrite();
            ((SystemHealthProto$JankMetric) this.instance).setFramesMissingRefreshRateBasedDrawDeadline(i);
            return this;
        }

        public Builder setJankyDurationMs(int i) {
            copyOnWrite();
            ((SystemHealthProto$JankMetric) this.instance).setJankyDurationMs(i);
            return this;
        }

        public Builder setJankyFrameCount(int i) {
            copyOnWrite();
            ((SystemHealthProto$JankMetric) this.instance).setJankyFrameCount(i);
            return this;
        }

        public Builder setMaxFrameRenderTimeMs(int i) {
            copyOnWrite();
            ((SystemHealthProto$JankMetric) this.instance).setMaxFrameRenderTimeMs(i);
            return this;
        }

        public Builder setPackedFrameTimeHistogram(SystemHealthProto$PackedHistogram.Builder builder) {
            copyOnWrite();
            ((SystemHealthProto$JankMetric) this.instance).setPackedFrameTimeHistogram((SystemHealthProto$PackedHistogram) builder.build());
            return this;
        }

        public Builder setRecordingDurationMs(int i) {
            copyOnWrite();
            ((SystemHealthProto$JankMetric) this.instance).setRecordingDurationMs(i);
            return this;
        }

        public Builder setRenderedFrameCount(int i) {
            copyOnWrite();
            ((SystemHealthProto$JankMetric) this.instance).setRenderedFrameCount(i);
            return this;
        }

        public Builder setSlackTimeHistogram(SystemHealthProto$PackedHistogram systemHealthProto$PackedHistogram) {
            copyOnWrite();
            ((SystemHealthProto$JankMetric) this.instance).setSlackTimeHistogram(systemHealthProto$PackedHistogram);
            return this;
        }

        /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
            this();
        }
    }
}
