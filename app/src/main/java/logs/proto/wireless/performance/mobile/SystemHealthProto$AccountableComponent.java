package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$AccountableComponent extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int CUSTOM_NAME_FIELD_NUMBER = 1;
    private static final SystemHealthProto$AccountableComponent DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private int bitField0_;
    private String customName_ = "";

    static {
        SystemHealthProto$AccountableComponent systemHealthProto$AccountableComponent = new SystemHealthProto$AccountableComponent();
        DEFAULT_INSTANCE = systemHealthProto$AccountableComponent;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$AccountableComponent.class, systemHealthProto$AccountableComponent);
    }

    private SystemHealthProto$AccountableComponent() {
    }

    public static SystemHealthProto$AccountableComponent getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCustomName(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.customName_ = str;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SystemHealthProto$AccountableComponent();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"bitField0_", "customName_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$AccountableComponent.class) {
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

    public String getCustomName() {
        return this.customName_;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(SystemHealthProto$AccountableComponent.DEFAULT_INSTANCE);
        }

        public String getCustomName() {
            return ((SystemHealthProto$AccountableComponent) this.instance).getCustomName();
        }

        public Builder setCustomName(String str) {
            copyOnWrite();
            ((SystemHealthProto$AccountableComponent) this.instance).setCustomName(str);
            return this;
        }

        /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
            this();
        }
    }
}
