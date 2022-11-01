package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.ApplicationExitInfo;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApplicationExitReasons extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final ApplicationExitReasons DEFAULT_INSTANCE;
    public static final int EXIT_REASON_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private static final Internal.ListAdapter.Converter exitReason_converter_ = new Internal.ListAdapter.Converter() { // from class: logs.proto.wireless.performance.mobile.ApplicationExitReasons.1
        @Override // com.google.protobuf.Internal.ListAdapter.Converter
        public ApplicationExitInfo.Reason convert(Integer num) {
            ApplicationExitInfo.Reason forNumber = ApplicationExitInfo.Reason.forNumber(num.intValue());
            return forNumber == null ? ApplicationExitInfo.Reason.REASON_UNSET : forNumber;
        }
    };
    private Internal.IntList exitReason_ = emptyIntList();

    /* compiled from: PG */
    /* renamed from: logs.proto.wireless.performance.mobile.ApplicationExitReasons$2  reason: invalid class name */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass2 {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(ApplicationExitReasons.DEFAULT_INSTANCE);
        }
    }

    static {
        ApplicationExitReasons applicationExitReasons = new ApplicationExitReasons();
        DEFAULT_INSTANCE = applicationExitReasons;
        GeneratedMessageLite.registerDefaultInstance(ApplicationExitReasons.class, applicationExitReasons);
    }

    private ApplicationExitReasons() {
    }

    public static ApplicationExitReasons parseFrom(byte[] bArr) {
        return (ApplicationExitReasons) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass2.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ApplicationExitReasons();
            case 2:
                return new Builder();
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001e", new Object[]{"exitReason_", ApplicationExitInfo.Reason.internalGetVerifier()});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ApplicationExitReasons.class) {
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

    public ApplicationExitInfo.Reason getExitReason(int i) {
        ApplicationExitInfo.Reason forNumber = ApplicationExitInfo.Reason.forNumber(this.exitReason_.getInt(i));
        return forNumber == null ? ApplicationExitInfo.Reason.REASON_UNSET : forNumber;
    }

    public int getExitReasonCount() {
        return this.exitReason_.size();
    }
}
