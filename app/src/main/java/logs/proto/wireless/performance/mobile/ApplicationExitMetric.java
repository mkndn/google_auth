package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApplicationExitMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int APPLICATION_EXIT_INFO_FIELD_NUMBER = 1;
    private static final ApplicationExitMetric DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int REPORTABLE_REASONS_FIELD_NUMBER = 2;
    public static final int TOTAL_EXITS_FIELD_NUMBER = 3;
    private Internal.ProtobufList applicationExitInfo_ = emptyProtobufList();
    private int bitField0_;
    private ApplicationExitReasons reportableReasons_;
    private int totalExits_;

    /* compiled from: PG */
    /* renamed from: logs.proto.wireless.performance.mobile.ApplicationExitMetric$1  reason: invalid class name */
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
        ApplicationExitMetric applicationExitMetric = new ApplicationExitMetric();
        DEFAULT_INSTANCE = applicationExitMetric;
        GeneratedMessageLite.registerDefaultInstance(ApplicationExitMetric.class, applicationExitMetric);
    }

    private ApplicationExitMetric() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addApplicationExitInfo(ApplicationExitInfo applicationExitInfo) {
        applicationExitInfo.getClass();
        ensureApplicationExitInfoIsMutable();
        this.applicationExitInfo_.add(applicationExitInfo);
    }

    private void ensureApplicationExitInfoIsMutable() {
        Internal.ProtobufList protobufList = this.applicationExitInfo_;
        if (!protobufList.isModifiable()) {
            this.applicationExitInfo_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReportableReasons(ApplicationExitReasons applicationExitReasons) {
        applicationExitReasons.getClass();
        this.reportableReasons_ = applicationExitReasons;
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTotalExits(int i) {
        this.bitField0_ |= 2;
        this.totalExits_ = i;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ApplicationExitMetric();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u001b\u0002ဉ\u0000\u0003င\u0001", new Object[]{"bitField0_", "applicationExitInfo_", ApplicationExitInfo.class, "reportableReasons_", "totalExits_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ApplicationExitMetric.class) {
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
            super(ApplicationExitMetric.DEFAULT_INSTANCE);
        }

        public Builder addApplicationExitInfo(ApplicationExitInfo applicationExitInfo) {
            copyOnWrite();
            ((ApplicationExitMetric) this.instance).addApplicationExitInfo(applicationExitInfo);
            return this;
        }

        public Builder setReportableReasons(ApplicationExitReasons applicationExitReasons) {
            copyOnWrite();
            ((ApplicationExitMetric) this.instance).setReportableReasons(applicationExitReasons);
            return this;
        }

        public Builder setTotalExits(int i) {
            copyOnWrite();
            ((ApplicationExitMetric) this.instance).setTotalExits(i);
            return this;
        }

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }
    }
}
