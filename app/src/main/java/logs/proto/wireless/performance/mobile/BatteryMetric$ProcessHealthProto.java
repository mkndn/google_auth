package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$ProcessHealthProto extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int ANR_COUNT_FIELD_NUMBER = 5;
    public static final int CRASHES_COUNT_FIELD_NUMBER = 4;
    private static final BatteryMetric$ProcessHealthProto DEFAULT_INSTANCE;
    public static final int FOREGROUND_MS_FIELD_NUMBER = 6;
    public static final int NAME_FIELD_NUMBER = 7;
    private static volatile Parser PARSER = null;
    public static final int STARTS_COUNT_FIELD_NUMBER = 3;
    public static final int SYSTEM_TIME_MS_FIELD_NUMBER = 2;
    public static final int USER_TIME_MS_FIELD_NUMBER = 1;
    private long anrCount_;
    private int bitField0_;
    private long crashesCount_;
    private long foregroundMs_;
    private BatteryMetric$HashedString name_;
    private long startsCount_;
    private long systemTimeMs_;
    private long userTimeMs_;

    static {
        BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto = new BatteryMetric$ProcessHealthProto();
        DEFAULT_INSTANCE = batteryMetric$ProcessHealthProto;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$ProcessHealthProto.class, batteryMetric$ProcessHealthProto);
    }

    private BatteryMetric$ProcessHealthProto() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAnrCount(long j) {
        this.bitField0_ |= 16;
        this.anrCount_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCrashesCount(long j) {
        this.bitField0_ |= 8;
        this.crashesCount_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setForegroundMs(long j) {
        this.bitField0_ |= 32;
        this.foregroundMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(BatteryMetric$HashedString batteryMetric$HashedString) {
        batteryMetric$HashedString.getClass();
        this.name_ = batteryMetric$HashedString;
        this.bitField0_ |= 64;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartsCount(long j) {
        this.bitField0_ |= 4;
        this.startsCount_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemTimeMs(long j) {
        this.bitField0_ |= 2;
        this.systemTimeMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUserTimeMs(long j) {
        this.bitField0_ |= 1;
        this.userTimeMs_ = j;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (BatteryMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new BatteryMetric$ProcessHealthProto();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဉ\u0006", new Object[]{"bitField0_", "userTimeMs_", "systemTimeMs_", "startsCount_", "crashesCount_", "anrCount_", "foregroundMs_", "name_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$ProcessHealthProto.class) {
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

    public long getAnrCount() {
        return this.anrCount_;
    }

    public long getCrashesCount() {
        return this.crashesCount_;
    }

    public long getForegroundMs() {
        return this.foregroundMs_;
    }

    public BatteryMetric$HashedString getName() {
        BatteryMetric$HashedString batteryMetric$HashedString = this.name_;
        return batteryMetric$HashedString == null ? BatteryMetric$HashedString.getDefaultInstance() : batteryMetric$HashedString;
    }

    public long getStartsCount() {
        return this.startsCount_;
    }

    public long getSystemTimeMs() {
        return this.systemTimeMs_;
    }

    public long getUserTimeMs() {
        return this.userTimeMs_;
    }

    public boolean hasAnrCount() {
        return (this.bitField0_ & 16) != 0;
    }

    public boolean hasCrashesCount() {
        return (this.bitField0_ & 8) != 0;
    }

    public boolean hasForegroundMs() {
        return (this.bitField0_ & 32) != 0;
    }

    public boolean hasStartsCount() {
        return (this.bitField0_ & 4) != 0;
    }

    public boolean hasSystemTimeMs() {
        return (this.bitField0_ & 2) != 0;
    }

    public boolean hasUserTimeMs() {
        return (this.bitField0_ & 1) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(BatteryMetric$ProcessHealthProto.DEFAULT_INSTANCE);
        }

        public Builder setAnrCount(long j) {
            copyOnWrite();
            ((BatteryMetric$ProcessHealthProto) this.instance).setAnrCount(j);
            return this;
        }

        public Builder setCrashesCount(long j) {
            copyOnWrite();
            ((BatteryMetric$ProcessHealthProto) this.instance).setCrashesCount(j);
            return this;
        }

        public Builder setForegroundMs(long j) {
            copyOnWrite();
            ((BatteryMetric$ProcessHealthProto) this.instance).setForegroundMs(j);
            return this;
        }

        public Builder setName(BatteryMetric$HashedString batteryMetric$HashedString) {
            copyOnWrite();
            ((BatteryMetric$ProcessHealthProto) this.instance).setName(batteryMetric$HashedString);
            return this;
        }

        public Builder setStartsCount(long j) {
            copyOnWrite();
            ((BatteryMetric$ProcessHealthProto) this.instance).setStartsCount(j);
            return this;
        }

        public Builder setSystemTimeMs(long j) {
            copyOnWrite();
            ((BatteryMetric$ProcessHealthProto) this.instance).setSystemTimeMs(j);
            return this;
        }

        public Builder setUserTimeMs(long j) {
            copyOnWrite();
            ((BatteryMetric$ProcessHealthProto) this.instance).setUserTimeMs(j);
            return this;
        }

        /* synthetic */ Builder(BatteryMetric$1 batteryMetric$1) {
            this();
        }
    }
}
