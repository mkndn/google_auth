package com.google.android.libraries.performance.primes.transmitter;

import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricSnapshot extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    private static final MetricSnapshot DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int SYSTEM_HEALTH_METRIC_FIELD_NUMBER = 1;
    private int bitField0_;
    private byte memoizedIsInitialized = 2;
    private SystemHealthProto$SystemHealthMetric systemHealthMetric_;

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.transmitter.MetricSnapshot$1  reason: invalid class name */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    static {
        MetricSnapshot metricSnapshot = new MetricSnapshot();
        DEFAULT_INSTANCE = metricSnapshot;
        GeneratedMessageLite.registerDefaultInstance(MetricSnapshot.class, metricSnapshot);
    }

    private MetricSnapshot() {
    }

    public static MetricSnapshot getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static MetricSnapshot parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (MetricSnapshot) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemHealthMetric(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric) {
        systemHealthProto$SystemHealthMetric.getClass();
        this.systemHealthMetric_ = systemHealthProto$SystemHealthMetric;
        this.bitField0_ |= 1;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new MetricSnapshot();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0001\u0001ᐉ\u0000", new Object[]{"bitField0_", "systemHealthMetric_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (MetricSnapshot.class) {
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

    public SystemHealthProto$SystemHealthMetric getSystemHealthMetric() {
        SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = this.systemHealthMetric_;
        return systemHealthProto$SystemHealthMetric == null ? SystemHealthProto$SystemHealthMetric.getDefaultInstance() : systemHealthProto$SystemHealthMetric;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.ExtendableBuilder implements MessageLiteOrBuilder {
        private Builder() {
            super(MetricSnapshot.DEFAULT_INSTANCE);
        }

        public Builder setSystemHealthMetric(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric) {
            copyOnWrite();
            ((MetricSnapshot) this.instance).setSystemHealthMetric(systemHealthProto$SystemHealthMetric);
            return this;
        }

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }
    }
}
