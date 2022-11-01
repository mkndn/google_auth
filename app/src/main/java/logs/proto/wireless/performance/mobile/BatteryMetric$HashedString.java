package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$HashedString extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final BatteryMetric$HashedString DEFAULT_INSTANCE;
    public static final int HASH_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int UNHASHED_NAME_FIELD_NUMBER = 2;
    private int bitField0_;
    private long hash_;
    private String unhashedName_ = "";

    static {
        BatteryMetric$HashedString batteryMetric$HashedString = new BatteryMetric$HashedString();
        DEFAULT_INSTANCE = batteryMetric$HashedString;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$HashedString.class, batteryMetric$HashedString);
    }

    private BatteryMetric$HashedString() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUnhashedName() {
        this.bitField0_ &= -3;
        this.unhashedName_ = getDefaultInstance().getUnhashedName();
    }

    public static BatteryMetric$HashedString getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHash(long j) {
        this.bitField0_ |= 1;
        this.hash_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUnhashedName(String str) {
        str.getClass();
        this.bitField0_ |= 2;
        this.unhashedName_ = str;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (BatteryMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new BatteryMetric$HashedString();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001စ\u0000\u0002ဈ\u0001", new Object[]{"bitField0_", "hash_", "unhashedName_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$HashedString.class) {
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

    public long getHash() {
        return this.hash_;
    }

    public String getUnhashedName() {
        return this.unhashedName_;
    }

    public boolean hasHash() {
        return (this.bitField0_ & 1) != 0;
    }

    public boolean hasUnhashedName() {
        return (this.bitField0_ & 2) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(BatteryMetric$HashedString.DEFAULT_INSTANCE);
        }

        public Builder clearUnhashedName() {
            copyOnWrite();
            ((BatteryMetric$HashedString) this.instance).clearUnhashedName();
            return this;
        }

        public long getHash() {
            return ((BatteryMetric$HashedString) this.instance).getHash();
        }

        public String getUnhashedName() {
            return ((BatteryMetric$HashedString) this.instance).getUnhashedName();
        }

        public Builder setHash(long j) {
            copyOnWrite();
            ((BatteryMetric$HashedString) this.instance).setHash(j);
            return this;
        }

        public Builder setUnhashedName(String str) {
            copyOnWrite();
            ((BatteryMetric$HashedString) this.instance).setUnhashedName(str);
            return this;
        }

        /* synthetic */ Builder(BatteryMetric$1 batteryMetric$1) {
            this();
        }
    }
}
