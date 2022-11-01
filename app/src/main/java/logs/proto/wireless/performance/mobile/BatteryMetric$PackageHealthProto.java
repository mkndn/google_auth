package logs.proto.wireless.performance.mobile;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$PackageHealthProto extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final BatteryMetric$PackageHealthProto DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int STATS_SERVICES_FIELD_NUMBER = 1;
    public static final int WAKEUP_ALARMS_COUNT_FIELD_NUMBER = 2;
    private int bitField0_;
    private BatteryMetric$HashedString name_;
    private Internal.ProtobufList statsServices_ = emptyProtobufList();
    private Internal.ProtobufList wakeupAlarmsCount_ = emptyProtobufList();

    static {
        BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto = new BatteryMetric$PackageHealthProto();
        DEFAULT_INSTANCE = batteryMetric$PackageHealthProto;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$PackageHealthProto.class, batteryMetric$PackageHealthProto);
    }

    private BatteryMetric$PackageHealthProto() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllStatsServices(Iterable iterable) {
        ensureStatsServicesIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.statsServices_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllWakeupAlarmsCount(Iterable iterable) {
        ensureWakeupAlarmsCountIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.wakeupAlarmsCount_);
    }

    private void ensureStatsServicesIsMutable() {
        Internal.ProtobufList protobufList = this.statsServices_;
        if (!protobufList.isModifiable()) {
            this.statsServices_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    private void ensureWakeupAlarmsCountIsMutable() {
        Internal.ProtobufList protobufList = this.wakeupAlarmsCount_;
        if (!protobufList.isModifiable()) {
            this.wakeupAlarmsCount_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(BatteryMetric$HashedString batteryMetric$HashedString) {
        batteryMetric$HashedString.getClass();
        this.name_ = batteryMetric$HashedString;
        this.bitField0_ |= 1;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (BatteryMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new BatteryMetric$PackageHealthProto();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001\u001b\u0002\u001b\u0003ဉ\u0000", new Object[]{"bitField0_", "statsServices_", BatteryMetric$ServiceHealthProto.class, "wakeupAlarmsCount_", BatteryMetric$Counter.class, "name_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$PackageHealthProto.class) {
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

    public BatteryMetric$HashedString getName() {
        BatteryMetric$HashedString batteryMetric$HashedString = this.name_;
        return batteryMetric$HashedString == null ? BatteryMetric$HashedString.getDefaultInstance() : batteryMetric$HashedString;
    }

    public int getStatsServicesCount() {
        return this.statsServices_.size();
    }

    public List getStatsServicesList() {
        return this.statsServices_;
    }

    public int getWakeupAlarmsCountCount() {
        return this.wakeupAlarmsCount_.size();
    }

    public List getWakeupAlarmsCountList() {
        return this.wakeupAlarmsCount_;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(BatteryMetric$PackageHealthProto.DEFAULT_INSTANCE);
        }

        public Builder addAllStatsServices(Iterable iterable) {
            copyOnWrite();
            ((BatteryMetric$PackageHealthProto) this.instance).addAllStatsServices(iterable);
            return this;
        }

        public Builder addAllWakeupAlarmsCount(Iterable iterable) {
            copyOnWrite();
            ((BatteryMetric$PackageHealthProto) this.instance).addAllWakeupAlarmsCount(iterable);
            return this;
        }

        public Builder setName(BatteryMetric$HashedString batteryMetric$HashedString) {
            copyOnWrite();
            ((BatteryMetric$PackageHealthProto) this.instance).setName(batteryMetric$HashedString);
            return this;
        }

        /* synthetic */ Builder(BatteryMetric$1 batteryMetric$1) {
            this();
        }
    }
}
