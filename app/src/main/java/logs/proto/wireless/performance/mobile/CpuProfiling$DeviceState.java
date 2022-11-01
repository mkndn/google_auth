package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuProfiling$DeviceState extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int BLUETOOTH_ON_FIELD_NUMBER = 4;
    public static final int CHARGING_FIELD_NUMBER = 2;
    private static final CpuProfiling$DeviceState DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int SCREEN_ON_FIELD_NUMBER = 1;
    public static final int WIFI_ON_FIELD_NUMBER = 3;
    private int bitField0_;
    private boolean bluetoothOn_;
    private boolean charging_;
    private boolean screenOn_;
    private boolean wifiOn_;

    static {
        CpuProfiling$DeviceState cpuProfiling$DeviceState = new CpuProfiling$DeviceState();
        DEFAULT_INSTANCE = cpuProfiling$DeviceState;
        GeneratedMessageLite.registerDefaultInstance(CpuProfiling$DeviceState.class, cpuProfiling$DeviceState);
    }

    private CpuProfiling$DeviceState() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBluetoothOn(boolean z) {
        this.bitField0_ |= 8;
        this.bluetoothOn_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCharging(boolean z) {
        this.bitField0_ |= 2;
        this.charging_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setScreenOn(boolean z) {
        this.bitField0_ |= 1;
        this.screenOn_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWifiOn(boolean z) {
        this.bitField0_ |= 4;
        this.wifiOn_ = z;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (CpuProfiling$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new CpuProfiling$DeviceState();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003", new Object[]{"bitField0_", "screenOn_", "charging_", "wifiOn_", "bluetoothOn_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (CpuProfiling$DeviceState.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(CpuProfiling$DeviceState.DEFAULT_INSTANCE);
        }

        public Builder setBluetoothOn(boolean z) {
            copyOnWrite();
            ((CpuProfiling$DeviceState) this.instance).setBluetoothOn(z);
            return this;
        }

        public Builder setCharging(boolean z) {
            copyOnWrite();
            ((CpuProfiling$DeviceState) this.instance).setCharging(z);
            return this;
        }

        public Builder setScreenOn(boolean z) {
            copyOnWrite();
            ((CpuProfiling$DeviceState) this.instance).setScreenOn(z);
            return this;
        }

        public Builder setWifiOn(boolean z) {
            copyOnWrite();
            ((CpuProfiling$DeviceState) this.instance).setWifiOn(z);
            return this;
        }

        /* synthetic */ Builder(CpuProfiling$1 cpuProfiling$1) {
            this();
        }
    }
}
