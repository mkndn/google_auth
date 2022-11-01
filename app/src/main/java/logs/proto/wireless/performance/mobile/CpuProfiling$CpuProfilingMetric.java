package logs.proto.wireless.performance.mobile;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuProfiling$CpuProfilingMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final CpuProfiling$CpuProfilingMetric DEFAULT_INSTANCE;
    public static final int DEVICE_METADATA_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int SAMPLES_PER_EPOCH_FIELD_NUMBER = 3;
    public static final int SAMPLE_BUFFER_SIZE_FIELD_NUMBER = 7;
    public static final int SAMPLE_DURATION_ACTUAL_FIELD_NUMBER = 5;
    public static final int SAMPLE_DURATION_SCHEDULED_FIELD_NUMBER = 4;
    public static final int SAMPLE_FREQUENCY_FIELD_NUMBER = 6;
    public static final int TRACE_BLOB_FIELD_NUMBER = 1;
    private int bitField0_;
    private CpuProfiling$DeviceMetadata deviceMetadata_;
    private int sampleBufferSize_;
    private int sampleDurationActual_;
    private int sampleDurationScheduled_;
    private int sampleFrequency_;
    private double samplesPerEpoch_;
    private ByteString traceBlob_ = ByteString.EMPTY;

    static {
        CpuProfiling$CpuProfilingMetric cpuProfiling$CpuProfilingMetric = new CpuProfiling$CpuProfilingMetric();
        DEFAULT_INSTANCE = cpuProfiling$CpuProfilingMetric;
        GeneratedMessageLite.registerDefaultInstance(CpuProfiling$CpuProfilingMetric.class, cpuProfiling$CpuProfilingMetric);
    }

    private CpuProfiling$CpuProfilingMetric() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeviceMetadata(CpuProfiling$DeviceMetadata cpuProfiling$DeviceMetadata) {
        cpuProfiling$DeviceMetadata.getClass();
        this.deviceMetadata_ = cpuProfiling$DeviceMetadata;
        this.bitField0_ |= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSampleBufferSize(int i) {
        this.bitField0_ |= 64;
        this.sampleBufferSize_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSampleDurationActual(int i) {
        this.bitField0_ |= 16;
        this.sampleDurationActual_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSampleDurationScheduled(int i) {
        this.bitField0_ |= 8;
        this.sampleDurationScheduled_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSampleFrequency(int i) {
        this.bitField0_ |= 32;
        this.sampleFrequency_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSamplesPerEpoch(double d) {
        this.bitField0_ |= 4;
        this.samplesPerEpoch_ = d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTraceBlob(ByteString byteString) {
        byteString.getClass();
        this.bitField0_ |= 1;
        this.traceBlob_ = byteString;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (CpuProfiling$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new CpuProfiling$CpuProfilingMetric();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ည\u0000\u0002ဉ\u0001\u0003က\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005\u0007င\u0006", new Object[]{"bitField0_", "traceBlob_", "deviceMetadata_", "samplesPerEpoch_", "sampleDurationScheduled_", "sampleDurationActual_", "sampleFrequency_", "sampleBufferSize_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (CpuProfiling$CpuProfilingMetric.class) {
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

    public ByteString getTraceBlob() {
        return this.traceBlob_;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(CpuProfiling$CpuProfilingMetric.DEFAULT_INSTANCE);
        }

        public ByteString getTraceBlob() {
            return ((CpuProfiling$CpuProfilingMetric) this.instance).getTraceBlob();
        }

        public Builder setDeviceMetadata(CpuProfiling$DeviceMetadata cpuProfiling$DeviceMetadata) {
            copyOnWrite();
            ((CpuProfiling$CpuProfilingMetric) this.instance).setDeviceMetadata(cpuProfiling$DeviceMetadata);
            return this;
        }

        public Builder setSampleBufferSize(int i) {
            copyOnWrite();
            ((CpuProfiling$CpuProfilingMetric) this.instance).setSampleBufferSize(i);
            return this;
        }

        public Builder setSampleDurationActual(int i) {
            copyOnWrite();
            ((CpuProfiling$CpuProfilingMetric) this.instance).setSampleDurationActual(i);
            return this;
        }

        public Builder setSampleDurationScheduled(int i) {
            copyOnWrite();
            ((CpuProfiling$CpuProfilingMetric) this.instance).setSampleDurationScheduled(i);
            return this;
        }

        public Builder setSampleFrequency(int i) {
            copyOnWrite();
            ((CpuProfiling$CpuProfilingMetric) this.instance).setSampleFrequency(i);
            return this;
        }

        public Builder setSamplesPerEpoch(double d) {
            copyOnWrite();
            ((CpuProfiling$CpuProfilingMetric) this.instance).setSamplesPerEpoch(d);
            return this;
        }

        public Builder setTraceBlob(ByteString byteString) {
            copyOnWrite();
            ((CpuProfiling$CpuProfilingMetric) this.instance).setTraceBlob(byteString);
            return this;
        }

        /* synthetic */ Builder(CpuProfiling$1 cpuProfiling$1) {
            this();
        }
    }
}
