package com.google.common.logging.proto2api;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Logrecord$LogRecordProto extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final Logrecord$LogRecordProto DEFAULT_INSTANCE;
    public static final int DEOBFUSCATED_IOS_FRAMES_FIELD_NUMBER = 15;
    public static final int EVENTID_FIELD_NUMBER = 1;
    public static final int FILE_NAME_FIELD_NUMBER = 14;
    public static final int IOSCALLSTACKSYMBOLS_FIELD_NUMBER = 10;
    public static final int IOSCODEMODULES_FIELD_NUMBER = 11;
    public static final int LEVEL_FIELD_NUMBER = 3;
    public static final int LINE_NUMBER_FIELD_NUMBER = 13;
    public static final int MESSAGEFORMAT_FIELD_NUMBER = 12;
    public static final int MESSAGEHASH_FIELD_NUMBER = 9;
    public static final int MESSAGE_FIELD_NUMBER = 6;
    public static final int PARAMETER_FIELD_NUMBER = 7;
    private static volatile Parser PARSER = null;
    public static final int SOURCECLASSNAME_FIELD_NUMBER = 4;
    public static final int SOURCEMETHODNAME_FIELD_NUMBER = 5;
    public static final int THREADNAME_FIELD_NUMBER = 2;
    public static final int THROWN_FIELD_NUMBER = 8;
    private int bitField0_;
    private Eventid$EventIdMessage eventId_;
    private int level_;
    private Logrecord$ThrowableProto thrown_;
    private byte memoizedIsInitialized = 2;
    private String threadName_ = "";
    private String sourceClassName_ = "";
    private String sourceMethodName_ = "";
    private String fileName_ = "";
    private String messageFormat_ = "";
    private String message_ = "";
    private Internal.ProtobufList parameter_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList iosCallStackSymbols_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList iosCodeModules_ = emptyProtobufList();
    private Internal.ProtobufList deobfuscatedIosFrames_ = emptyProtobufList();

    static {
        Logrecord$LogRecordProto logrecord$LogRecordProto = new Logrecord$LogRecordProto();
        DEFAULT_INSTANCE = logrecord$LogRecordProto;
        GeneratedMessageLite.registerDefaultInstance(Logrecord$LogRecordProto.class, logrecord$LogRecordProto);
    }

    private Logrecord$LogRecordProto() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Logrecord$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Logrecord$LogRecordProto();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\b\u0004\u0000\u0000\u0004\u0001ᔉ\u0000\u0002ᔈ\u0001\u0003ᔄ\u0002\bᐉ\n", new Object[]{"bitField0_", "eventId_", "threadName_", "level_", "thrown_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Logrecord$LogRecordProto.class) {
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
            super(Logrecord$LogRecordProto.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(Logrecord$1 logrecord$1) {
            this();
        }
    }
}
