package com.google.wireless.android.play.playlog.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClientAnalytics$LogEvent extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static final int APPLIED_SAMPLING_RATE_FIELD_NUMBER = 30;
    public static final int APP_USAGE_1P_FIELD_NUMBER = 9;
    public static final int BOOT_COUNT_FIELD_NUMBER = 22;
    public static final int CLIENT_VE_FIELD_NUMBER = 18;
    public static final int CLIENT_VE_JS_FIELD_NUMBER = 24;
    public static final int COMPONENT_ID_FIELD_NUMBER = 26;
    private static final ClientAnalytics$LogEvent DEFAULT_INSTANCE;
    public static final int EVENT_CODE_FIELD_NUMBER = 11;
    public static final int EVENT_FLOW_ID_FIELD_NUMBER = 12;
    public static final int EVENT_TIME_MS_FIELD_NUMBER = 1;
    public static final int EVENT_UPTIME_MS_FIELD_NUMBER = 17;
    public static final int EXPERIMENT_IDS_FIELD_NUMBER = 16;
    public static final int EXP_FIELD_NUMBER = 7;
    public static final int GENERIC_DIMENSIONS_FIELD_NUMBER = 27;
    public static final int INTERNAL_EVENT_FIELD_NUMBER = 19;
    public static final int IN_DIRECT_BOOT_MODE_FIELD_NUMBER = 25;
    public static final int IS_USER_INITIATED_FIELD_NUMBER = 10;
    public static final int NETWORK_CONNECTION_INFO_FIELD_NUMBER = 23;
    private static volatile Parser PARSER = null;
    public static final int SEQUENCE_POSITION_FIELD_NUMBER = 21;
    public static final int SOURCE_EXTENSION_FIELD_NUMBER = 6;
    public static final int SOURCE_EXTENSION_JSON_FIELD_NUMBER = 13;
    public static final int SOURCE_EXTENSION_JSON_PROTO3_FIELD_NUMBER = 29;
    public static final int SOURCE_EXTENSION_JS_FIELD_NUMBER = 8;
    public static final int STORE_FIELD_NUMBER = 4;
    public static final int TAG_FIELD_NUMBER = 2;
    public static final int TEST_CODE_FIELD_NUMBER = 20;
    public static final int TEST_ID_FIELD_NUMBER = 14;
    public static final int TIMEZONE_OFFSET_SECONDS_FIELD_NUMBER = 15;
    public static final int VALUE_FIELD_NUMBER = 3;
    public static final int ZWIEBACK_COOKIE_OVERRIDE_FIELD_NUMBER = 28;
    private int bitField0_;
    private int eventCode_;
    private long eventTimeMs_;
    private long eventUptimeMs_;
    private boolean inDirectBootMode_;
    private int genericDimensionsMemoizedSerializedSize = -1;
    private byte memoizedIsInitialized = 2;
    private String tag_ = "";
    private String componentId_ = "";
    private Internal.ProtobufList value_ = emptyProtobufList();
    private ByteString store_ = ByteString.EMPTY;
    private ByteString sourceExtension_ = ByteString.EMPTY;
    private String sourceExtensionJs_ = "";
    private String sourceExtensionJson_ = "";
    private String sourceExtensionJsonProto3_ = "";
    private String testId_ = "";
    private long timezoneOffsetSeconds_ = 180000;
    private ByteString clientVe_ = ByteString.EMPTY;
    private String clientVeJs_ = "";
    private Internal.IntList testCode_ = emptyIntList();
    private Internal.IntList genericDimensions_ = emptyIntList();
    private String zwiebackCookieOverride_ = "";

    static {
        ClientAnalytics$LogEvent clientAnalytics$LogEvent = new ClientAnalytics$LogEvent();
        DEFAULT_INSTANCE = clientAnalytics$LogEvent;
        GeneratedMessageLite.registerDefaultInstance(ClientAnalytics$LogEvent.class, clientAnalytics$LogEvent);
    }

    private ClientAnalytics$LogEvent() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setClientVe(ByteString byteString) {
        byteString.getClass();
        this.bitField0_ |= 262144;
        this.clientVe_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEventCode(int i) {
        this.bitField0_ |= 16;
        this.eventCode_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEventTimeMs(long j) {
        this.bitField0_ |= 1;
        this.eventTimeMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEventUptimeMs(long j) {
        this.bitField0_ |= 2;
        this.eventUptimeMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setInDirectBootMode(boolean z) {
        this.bitField0_ |= 8388608;
        this.inDirectBootMode_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSourceExtension(ByteString byteString) {
        byteString.getClass();
        this.bitField0_ |= 1024;
        this.sourceExtension_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTimezoneOffsetSeconds(long j) {
        this.bitField0_ |= 65536;
        this.timezoneOffsetSeconds_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setZwiebackCookieOverride(String str) {
        str.getClass();
        this.bitField0_ |= 16777216;
        this.zwiebackCookieOverride_ = str;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (ClientAnalytics$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ClientAnalytics$LogEvent();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\b\u0000\u0001\u0001\u001c\b\u0000\u0000\u0000\u0001ဂ\u0000\u0006ည\n\u000bင\u0004\u000fတ\u0010\u0011ဂ\u0001\u0012ည\u0012\u0019ဇ\u0017\u001cဈ\u0018", new Object[]{"bitField0_", "eventTimeMs_", "sourceExtension_", "eventCode_", "timezoneOffsetSeconds_", "eventUptimeMs_", "clientVe_", "inDirectBootMode_", "zwiebackCookieOverride_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ClientAnalytics$LogEvent.class) {
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

    public ByteString getClientVe() {
        return this.clientVe_;
    }

    public int getEventCode() {
        return this.eventCode_;
    }

    public long getEventTimeMs() {
        return this.eventTimeMs_;
    }

    public ByteString getSourceExtension() {
        return this.sourceExtension_;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.ExtendableBuilder implements MessageLiteOrBuilder {
        private Builder() {
            super(ClientAnalytics$LogEvent.DEFAULT_INSTANCE);
        }

        public int getEventCode() {
            return ((ClientAnalytics$LogEvent) this.instance).getEventCode();
        }

        public long getEventTimeMs() {
            return ((ClientAnalytics$LogEvent) this.instance).getEventTimeMs();
        }

        public Builder setClientVe(ByteString byteString) {
            copyOnWrite();
            ((ClientAnalytics$LogEvent) this.instance).setClientVe(byteString);
            return this;
        }

        public Builder setEventCode(int i) {
            copyOnWrite();
            ((ClientAnalytics$LogEvent) this.instance).setEventCode(i);
            return this;
        }

        public Builder setEventTimeMs(long j) {
            copyOnWrite();
            ((ClientAnalytics$LogEvent) this.instance).setEventTimeMs(j);
            return this;
        }

        public Builder setEventUptimeMs(long j) {
            copyOnWrite();
            ((ClientAnalytics$LogEvent) this.instance).setEventUptimeMs(j);
            return this;
        }

        public Builder setInDirectBootMode(boolean z) {
            copyOnWrite();
            ((ClientAnalytics$LogEvent) this.instance).setInDirectBootMode(z);
            return this;
        }

        public Builder setSourceExtension(ByteString byteString) {
            copyOnWrite();
            ((ClientAnalytics$LogEvent) this.instance).setSourceExtension(byteString);
            return this;
        }

        public Builder setTimezoneOffsetSeconds(long j) {
            copyOnWrite();
            ((ClientAnalytics$LogEvent) this.instance).setTimezoneOffsetSeconds(j);
            return this;
        }

        public Builder setZwiebackCookieOverride(String str) {
            copyOnWrite();
            ((ClientAnalytics$LogEvent) this.instance).setZwiebackCookieOverride(str);
            return this;
        }

        /* synthetic */ Builder(ClientAnalytics$1 clientAnalytics$1) {
            this();
        }
    }
}
