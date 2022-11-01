package com.google.android.libraries.performance.primes.metrics.cpuprofiling.v2;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProfilerConfigurations$Config extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int BUFFER_SIZE_BYTES_FIELD_NUMBER = 7;
    private static final ProfilerConfigurations$Config DEFAULT_INSTANCE;
    public static final int ENABLED_FIELD_NUMBER = 1;
    public static final int FILENAME_FIELD_NUMBER = 2;
    public static final int INTERVAL_US_FIELD_NUMBER = 8;
    private static volatile Parser PARSER = null;
    public static final int START_DELAY_INTERVAL_MS_FIELD_NUMBER = 4;
    public static final int START_DELAY_MIN_MS_FIELD_NUMBER = 3;
    public static final int STOP_DELAY_INTERVAL_MS_FIELD_NUMBER = 6;
    public static final int STOP_DELAY_MIN_MS_FIELD_NUMBER = 5;
    private String filename_ = "";

    static {
        ProfilerConfigurations$Config profilerConfigurations$Config = new ProfilerConfigurations$Config();
        DEFAULT_INSTANCE = profilerConfigurations$Config;
        GeneratedMessageLite.registerDefaultInstance(ProfilerConfigurations$Config.class, profilerConfigurations$Config);
    }

    private ProfilerConfigurations$Config() {
    }

    public static ProfilerConfigurations$Config parseFrom(byte[] bArr) {
        return (ProfilerConfigurations$Config) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (ProfilerConfigurations$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ProfilerConfigurations$Config();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0000", null);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ProfilerConfigurations$Config.class) {
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
            super(ProfilerConfigurations$Config.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(ProfilerConfigurations$1 profilerConfigurations$1) {
            this();
        }
    }
}
