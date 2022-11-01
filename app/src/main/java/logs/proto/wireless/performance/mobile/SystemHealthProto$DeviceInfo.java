package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$DeviceInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int AVAILABLE_DISK_SIZE_KB_FIELD_NUMBER = 1;
    private static final SystemHealthProto$DeviceInfo DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int TOTAL_DISK_SIZE_KB_FIELD_NUMBER = 2;
    private long availableDiskSizeKb_;
    private int bitField0_;
    private long totalDiskSizeKb_;

    static {
        SystemHealthProto$DeviceInfo systemHealthProto$DeviceInfo = new SystemHealthProto$DeviceInfo();
        DEFAULT_INSTANCE = systemHealthProto$DeviceInfo;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$DeviceInfo.class, systemHealthProto$DeviceInfo);
    }

    private SystemHealthProto$DeviceInfo() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAvailableDiskSizeKb(long j) {
        this.bitField0_ |= 1;
        this.availableDiskSizeKb_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTotalDiskSizeKb(long j) {
        this.bitField0_ |= 2;
        this.totalDiskSizeKb_ = j;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SystemHealthProto$DeviceInfo();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001", new Object[]{"bitField0_", "availableDiskSizeKb_", "totalDiskSizeKb_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$DeviceInfo.class) {
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
            super(SystemHealthProto$DeviceInfo.DEFAULT_INSTANCE);
        }

        public Builder setAvailableDiskSizeKb(long j) {
            copyOnWrite();
            ((SystemHealthProto$DeviceInfo) this.instance).setAvailableDiskSizeKb(j);
            return this;
        }

        public Builder setTotalDiskSizeKb(long j) {
            copyOnWrite();
            ((SystemHealthProto$DeviceInfo) this.instance).setTotalDiskSizeKb(j);
            return this;
        }

        /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
            this();
        }
    }
}
