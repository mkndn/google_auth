package com.google.common.logging.proto2api;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Logrecord$ThrowableBlockProto extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final Logrecord$ThrowableBlockProto DEFAULT_INSTANCE;
    public static final int HADMESSAGE_FIELD_NUMBER = 12;
    public static final int MESSAGEHASH_FIELD_NUMBER = 3;
    public static final int MESSAGE_FIELD_NUMBER = 2;
    public static final int ORIGINALCLASS_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int STACKTRACEELEMENT_FIELD_NUMBER = 4;
    private int bitField0_;
    private long messageHash_;
    private byte memoizedIsInitialized = 2;
    private String originalClass_ = "";
    private String message_ = "";
    private Internal.ProtobufList stackTraceElement_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StackTraceElement extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final int CLASSLOADERNAME_FIELD_NUMBER = 9;
        public static final int DECLARINGCLASS_FIELD_NUMBER = 5;
        private static final StackTraceElement DEFAULT_INSTANCE;
        public static final int FILENAME_FIELD_NUMBER = 7;
        public static final int LINENUMBER_FIELD_NUMBER = 8;
        public static final int METHODNAME_FIELD_NUMBER = 6;
        public static final int MODULENAME_FIELD_NUMBER = 10;
        public static final int MODULEVERSION_FIELD_NUMBER = 11;
        private static volatile Parser PARSER;
        private int bitField0_;
        private int lineNumber_;
        private byte memoizedIsInitialized = 2;
        private String declaringClass_ = "";
        private String methodName_ = "";
        private String fileName_ = "";
        private String classLoaderName_ = "";
        private String moduleName_ = "";
        private String moduleVersion_ = "";

        static {
            StackTraceElement stackTraceElement = new StackTraceElement();
            DEFAULT_INSTANCE = stackTraceElement;
            GeneratedMessageLite.registerDefaultInstance(StackTraceElement.class, stackTraceElement);
        }

        private StackTraceElement() {
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeclaringClass(String str) {
            str.getClass();
            this.bitField0_ |= 1;
            this.declaringClass_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFileName(String str) {
            str.getClass();
            this.bitField0_ |= 4;
            this.fileName_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLineNumber(int i) {
            this.bitField0_ |= 8;
            this.lineNumber_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMethodName(String str) {
            str.getClass();
            this.bitField0_ |= 2;
            this.methodName_ = str;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (Logrecord$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new StackTraceElement();
                case 2:
                    return new Builder(null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0005\b\u0004\u0000\u0000\u0003\u0005ᔈ\u0000\u0006ᔈ\u0001\u0007ဈ\u0002\bᔄ\u0003", new Object[]{"bitField0_", "declaringClass_", "methodName_", "fileName_", "lineNumber_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (StackTraceElement.class) {
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
        public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
            private Builder() {
                super(StackTraceElement.DEFAULT_INSTANCE);
            }

            public Builder setDeclaringClass(String str) {
                copyOnWrite();
                ((StackTraceElement) this.instance).setDeclaringClass(str);
                return this;
            }

            public Builder setFileName(String str) {
                copyOnWrite();
                ((StackTraceElement) this.instance).setFileName(str);
                return this;
            }

            public Builder setLineNumber(int i) {
                copyOnWrite();
                ((StackTraceElement) this.instance).setLineNumber(i);
                return this;
            }

            public Builder setMethodName(String str) {
                copyOnWrite();
                ((StackTraceElement) this.instance).setMethodName(str);
                return this;
            }

            /* synthetic */ Builder(Logrecord$1 logrecord$1) {
                this();
            }
        }
    }

    static {
        Logrecord$ThrowableBlockProto logrecord$ThrowableBlockProto = new Logrecord$ThrowableBlockProto();
        DEFAULT_INSTANCE = logrecord$ThrowableBlockProto;
        GeneratedMessageLite.registerDefaultInstance(Logrecord$ThrowableBlockProto.class, logrecord$ThrowableBlockProto);
    }

    private Logrecord$ThrowableBlockProto() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addStackTraceElement(StackTraceElement stackTraceElement) {
        stackTraceElement.getClass();
        ensureStackTraceElementIsMutable();
        this.stackTraceElement_.add(stackTraceElement);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMessage() {
        this.bitField0_ &= -3;
        this.message_ = getDefaultInstance().getMessage();
    }

    private void ensureStackTraceElementIsMutable() {
        Internal.ProtobufList protobufList = this.stackTraceElement_;
        if (!protobufList.isModifiable()) {
            this.stackTraceElement_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public static Logrecord$ThrowableBlockProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMessage(String str) {
        str.getClass();
        this.bitField0_ |= 2;
        this.message_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMessageHash(long j) {
        this.bitField0_ |= 4;
        this.messageHash_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOriginalClass(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.originalClass_ = str;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Logrecord$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Logrecord$ThrowableBlockProto();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0002\u0001ᔈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004б", new Object[]{"bitField0_", "originalClass_", "message_", "messageHash_", "stackTraceElement_", StackTraceElement.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Logrecord$ThrowableBlockProto.class) {
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

    public String getMessage() {
        return this.message_;
    }

    public boolean hasMessage() {
        return (this.bitField0_ & 2) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(Logrecord$ThrowableBlockProto.DEFAULT_INSTANCE);
        }

        public Builder addStackTraceElement(StackTraceElement.Builder builder) {
            copyOnWrite();
            ((Logrecord$ThrowableBlockProto) this.instance).addStackTraceElement((StackTraceElement) builder.build());
            return this;
        }

        public Builder clearMessage() {
            copyOnWrite();
            ((Logrecord$ThrowableBlockProto) this.instance).clearMessage();
            return this;
        }

        public String getMessage() {
            return ((Logrecord$ThrowableBlockProto) this.instance).getMessage();
        }

        public Builder setMessage(String str) {
            copyOnWrite();
            ((Logrecord$ThrowableBlockProto) this.instance).setMessage(str);
            return this;
        }

        public Builder setMessageHash(long j) {
            copyOnWrite();
            ((Logrecord$ThrowableBlockProto) this.instance).setMessageHash(j);
            return this;
        }

        public Builder setOriginalClass(String str) {
            copyOnWrite();
            ((Logrecord$ThrowableBlockProto) this.instance).setOriginalClass(str);
            return this;
        }

        /* synthetic */ Builder(Logrecord$1 logrecord$1) {
            this();
        }
    }
}
