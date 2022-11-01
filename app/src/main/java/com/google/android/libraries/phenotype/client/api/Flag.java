package com.google.android.libraries.phenotype.client.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Flag extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int BOOL_VALUE_FIELD_NUMBER = 2;
    public static final int BYTES_VALUE_FIELD_NUMBER = 5;
    private static final Flag DEFAULT_INSTANCE;
    public static final int DOUBLE_VALUE_FIELD_NUMBER = 3;
    public static final int LONG_VALUE_FIELD_NUMBER = 1;
    public static final int NAME_FIELD_NUMBER = 10;
    private static volatile Parser PARSER = null;
    public static final int STRING_VALUE_FIELD_NUMBER = 4;
    private int bitField0_;
    private Object value_;
    private int valueCase_ = 0;
    private String name_ = "";

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.phenotype.client.api.Flag$1  reason: invalid class name */
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum ValueCase {
        LONG_VALUE(1),
        BOOL_VALUE(2),
        DOUBLE_VALUE(3),
        STRING_VALUE(4),
        BYTES_VALUE(5),
        VALUE_NOT_SET(0);
        
        private final int value;

        ValueCase(int i) {
            this.value = i;
        }

        public static ValueCase forNumber(int i) {
            switch (i) {
                case 0:
                    return VALUE_NOT_SET;
                case 1:
                    return LONG_VALUE;
                case 2:
                    return BOOL_VALUE;
                case 3:
                    return DOUBLE_VALUE;
                case 4:
                    return STRING_VALUE;
                case 5:
                    return BYTES_VALUE;
                default:
                    return null;
            }
        }
    }

    static {
        Flag flag = new Flag();
        DEFAULT_INSTANCE = flag;
        GeneratedMessageLite.registerDefaultInstance(Flag.class, flag);
    }

    private Flag() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBoolValue(boolean z) {
        this.valueCase_ = 2;
        this.value_ = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBytesValue(ByteString byteString) {
        byteString.getClass();
        this.valueCase_ = 5;
        this.value_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDoubleValue(double d) {
        this.valueCase_ = 3;
        this.value_ = Double.valueOf(d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLongValue(long j) {
        this.valueCase_ = 1;
        this.value_ = Long.valueOf(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.name_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStringValue(String str) {
        str.getClass();
        this.valueCase_ = 4;
        this.value_ = str;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Flag();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0001\u0001\u0001\n\u0006\u0000\u0000\u0000\u0001း\u0000\u0002်\u0000\u0003ဳ\u0000\u0004ျ\u0000\u0005ွ\u0000\nဈ\u0000", new Object[]{"value_", "valueCase_", "bitField0_", "name_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Flag.class) {
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

    public boolean getBoolValue() {
        if (this.valueCase_ == 2) {
            return ((Boolean) this.value_).booleanValue();
        }
        return false;
    }

    public ByteString getBytesValue() {
        if (this.valueCase_ == 5) {
            return (ByteString) this.value_;
        }
        return ByteString.EMPTY;
    }

    public double getDoubleValue() {
        if (this.valueCase_ == 3) {
            return ((Double) this.value_).doubleValue();
        }
        return 0.0d;
    }

    public long getLongValue() {
        if (this.valueCase_ == 1) {
            return ((Long) this.value_).longValue();
        }
        return 0L;
    }

    public String getName() {
        return this.name_;
    }

    public ValueCase getValueCase() {
        return ValueCase.forNumber(this.valueCase_);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(Flag.DEFAULT_INSTANCE);
        }

        public Builder setBoolValue(boolean z) {
            copyOnWrite();
            ((Flag) this.instance).setBoolValue(z);
            return this;
        }

        public Builder setBytesValue(ByteString byteString) {
            copyOnWrite();
            ((Flag) this.instance).setBytesValue(byteString);
            return this;
        }

        public Builder setDoubleValue(double d) {
            copyOnWrite();
            ((Flag) this.instance).setDoubleValue(d);
            return this;
        }

        public Builder setLongValue(long j) {
            copyOnWrite();
            ((Flag) this.instance).setLongValue(j);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((Flag) this.instance).setName(str);
            return this;
        }

        public Builder setStringValue(String str) {
            copyOnWrite();
            ((Flag) this.instance).setStringValue(str);
            return this;
        }

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public String getStringValue() {
        return this.valueCase_ == 4 ? (String) this.value_ : "";
    }
}
