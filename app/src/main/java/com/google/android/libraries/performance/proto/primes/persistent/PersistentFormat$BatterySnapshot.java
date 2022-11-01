package com.google.android.libraries.performance.proto.primes.persistent;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.BatteryMetric$UidHealthProto;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PersistentFormat$BatterySnapshot extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int CURRENT_TIME_FIELD_NUMBER = 3;
    public static final int CUSTOM_EVENT_NAME_FIELD_NUMBER = 7;
    private static final PersistentFormat$BatterySnapshot DEFAULT_INSTANCE;
    public static final int ELAPSED_TIME_FIELD_NUMBER = 2;
    public static final int IS_EVENT_NAME_CONSTANT_FIELD_NUMBER = 8;
    public static final int METRIC_EXTENSION_FIELD_NUMBER = 9;
    private static volatile Parser PARSER = null;
    public static final int PRIMES_VERSION_FIELD_NUMBER = 4;
    public static final int SAMPLE_INFO_FIELD_NUMBER = 6;
    public static final int UID_HEALTH_PROTO_FIELD_NUMBER = 1;
    public static final int VERSION_NAME_HASH_FIELD_NUMBER = 5;
    private int bitField0_;
    private long currentTime_;
    private long elapsedTime_;
    private ExtensionMetric$MetricExtension metricExtension_;
    private long primesVersion_;
    private int sampleInfo_;
    private BatteryMetric$UidHealthProto uidHealthProto_;
    private long versionNameHash_;
    private byte memoizedIsInitialized = 2;
    private String customEventName_ = "";

    static {
        PersistentFormat$BatterySnapshot persistentFormat$BatterySnapshot = new PersistentFormat$BatterySnapshot();
        DEFAULT_INSTANCE = persistentFormat$BatterySnapshot;
        GeneratedMessageLite.registerDefaultInstance(PersistentFormat$BatterySnapshot.class, persistentFormat$BatterySnapshot);
    }

    private PersistentFormat$BatterySnapshot() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Parser parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentTime(long j) {
        this.bitField0_ |= 4;
        this.currentTime_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCustomEventName(String str) {
        str.getClass();
        this.bitField0_ |= 64;
        this.customEventName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setElapsedTime(long j) {
        this.bitField0_ |= 2;
        this.elapsedTime_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetricExtension(ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
        extensionMetric$MetricExtension.getClass();
        this.metricExtension_ = extensionMetric$MetricExtension;
        this.bitField0_ |= 256;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPrimesVersion(long j) {
        this.bitField0_ |= 8;
        this.primesVersion_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSampleInfo(int i) {
        this.bitField0_ |= 32;
        this.sampleInfo_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUidHealthProto(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto) {
        batteryMetric$UidHealthProto.getClass();
        this.uidHealthProto_ = batteryMetric$UidHealthProto;
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVersionNameHash(long j) {
        this.bitField0_ |= 16;
        this.versionNameHash_ = j;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (PersistentFormat$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new PersistentFormat$BatterySnapshot();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\b\u0000\u0001\u0001\t\b\u0000\u0000\u0001\u0001ဉ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005စ\u0004\u0006င\u0005\u0007ဈ\u0006\tᐉ\b", new Object[]{"bitField0_", "uidHealthProto_", "elapsedTime_", "currentTime_", "primesVersion_", "versionNameHash_", "sampleInfo_", "customEventName_", "metricExtension_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PersistentFormat$BatterySnapshot.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return Byte.valueOf(this.memoizedIsInitialized);
            case 7:
                this.memoizedIsInitialized = obj == null ? (byte) 0 : (byte) 1;
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public long getCurrentTime() {
        return this.currentTime_;
    }

    public String getCustomEventName() {
        return this.customEventName_;
    }

    public long getElapsedTime() {
        return this.elapsedTime_;
    }

    public ExtensionMetric$MetricExtension getMetricExtension() {
        ExtensionMetric$MetricExtension extensionMetric$MetricExtension = this.metricExtension_;
        return extensionMetric$MetricExtension == null ? ExtensionMetric$MetricExtension.getDefaultInstance() : extensionMetric$MetricExtension;
    }

    public long getPrimesVersion() {
        return this.primesVersion_;
    }

    public int getSampleInfo() {
        return this.sampleInfo_;
    }

    public BatteryMetric$UidHealthProto getUidHealthProto() {
        BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = this.uidHealthProto_;
        return batteryMetric$UidHealthProto == null ? BatteryMetric$UidHealthProto.getDefaultInstance() : batteryMetric$UidHealthProto;
    }

    public long getVersionNameHash() {
        return this.versionNameHash_;
    }

    public boolean hasCurrentTime() {
        return (this.bitField0_ & 4) != 0;
    }

    public boolean hasCustomEventName() {
        return (this.bitField0_ & 64) != 0;
    }

    public boolean hasElapsedTime() {
        return (this.bitField0_ & 2) != 0;
    }

    public boolean hasMetricExtension() {
        return (this.bitField0_ & 256) != 0;
    }

    public boolean hasPrimesVersion() {
        return (this.bitField0_ & 8) != 0;
    }

    public boolean hasSampleInfo() {
        return (this.bitField0_ & 32) != 0;
    }

    public boolean hasVersionNameHash() {
        return (this.bitField0_ & 16) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(PersistentFormat$BatterySnapshot.DEFAULT_INSTANCE);
        }

        public Builder setCurrentTime(long j) {
            copyOnWrite();
            ((PersistentFormat$BatterySnapshot) this.instance).setCurrentTime(j);
            return this;
        }

        public Builder setCustomEventName(String str) {
            copyOnWrite();
            ((PersistentFormat$BatterySnapshot) this.instance).setCustomEventName(str);
            return this;
        }

        public Builder setElapsedTime(long j) {
            copyOnWrite();
            ((PersistentFormat$BatterySnapshot) this.instance).setElapsedTime(j);
            return this;
        }

        public Builder setMetricExtension(ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
            copyOnWrite();
            ((PersistentFormat$BatterySnapshot) this.instance).setMetricExtension(extensionMetric$MetricExtension);
            return this;
        }

        public Builder setPrimesVersion(long j) {
            copyOnWrite();
            ((PersistentFormat$BatterySnapshot) this.instance).setPrimesVersion(j);
            return this;
        }

        public Builder setSampleInfo(int i) {
            copyOnWrite();
            ((PersistentFormat$BatterySnapshot) this.instance).setSampleInfo(i);
            return this;
        }

        public Builder setUidHealthProto(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto) {
            copyOnWrite();
            ((PersistentFormat$BatterySnapshot) this.instance).setUidHealthProto(batteryMetric$UidHealthProto);
            return this;
        }

        public Builder setVersionNameHash(long j) {
            copyOnWrite();
            ((PersistentFormat$BatterySnapshot) this.instance).setVersionNameHash(j);
            return this;
        }

        /* synthetic */ Builder(PersistentFormat$1 persistentFormat$1) {
            this();
        }
    }
}
