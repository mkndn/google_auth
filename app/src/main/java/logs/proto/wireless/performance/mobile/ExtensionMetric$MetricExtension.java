package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExtensionMetric$MetricExtension extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    private static final ExtensionMetric$MetricExtension DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private byte memoizedIsInitialized = 2;

    static {
        ExtensionMetric$MetricExtension extensionMetric$MetricExtension = new ExtensionMetric$MetricExtension();
        DEFAULT_INSTANCE = extensionMetric$MetricExtension;
        GeneratedMessageLite.registerDefaultInstance(ExtensionMetric$MetricExtension.class, extensionMetric$MetricExtension);
    }

    private ExtensionMetric$MetricExtension() {
    }

    public static ExtensionMetric$MetricExtension getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (ExtensionMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ExtensionMetric$MetricExtension();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0000", null);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ExtensionMetric$MetricExtension.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.ExtendableBuilder implements MessageLiteOrBuilder {
        private Builder() {
            super(ExtensionMetric$MetricExtension.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(ExtensionMetric$1 extensionMetric$1) {
            this();
        }
    }
}
