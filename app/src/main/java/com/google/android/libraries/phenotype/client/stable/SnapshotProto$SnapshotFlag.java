package com.google.android.libraries.phenotype.client.stable;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SnapshotProto$SnapshotFlag extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int BOOLEAN_VALUE_FIELD_NUMBER = 3;
    public static final int BYTES_VALUE_FIELD_NUMBER = 6;
    private static final SnapshotProto$SnapshotFlag DEFAULT_INSTANCE;
    public static final int DOUBLE_VALUE_FIELD_NUMBER = 4;
    public static final int LONG_VALUE_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int STRING_VALUE_FIELD_NUMBER = 5;
    private int bitField0_;
    private Object value_;
    private int valueCase_ = 0;
    private String name_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum ValueCase {
        LONG_VALUE(2),
        BOOLEAN_VALUE(3),
        DOUBLE_VALUE(4),
        STRING_VALUE(5),
        BYTES_VALUE(6),
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
                default:
                    return null;
                case 2:
                    return LONG_VALUE;
                case 3:
                    return BOOLEAN_VALUE;
                case 4:
                    return DOUBLE_VALUE;
                case 5:
                    return STRING_VALUE;
                case 6:
                    return BYTES_VALUE;
            }
        }
    }

    static {
        SnapshotProto$SnapshotFlag snapshotProto$SnapshotFlag = new SnapshotProto$SnapshotFlag();
        DEFAULT_INSTANCE = snapshotProto$SnapshotFlag;
        GeneratedMessageLite.registerDefaultInstance(SnapshotProto$SnapshotFlag.class, snapshotProto$SnapshotFlag);
    }

    private SnapshotProto$SnapshotFlag() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBooleanValue(boolean z) {
        this.valueCase_ = 3;
        this.value_ = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBytesValue(ByteString byteString) {
        byteString.getClass();
        this.valueCase_ = 6;
        this.value_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDoubleValue(double d) {
        this.valueCase_ = 4;
        this.value_ = Double.valueOf(d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLongValue(long j) {
        this.valueCase_ = 2;
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
        this.valueCase_ = 5;
        this.value_ = str;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (SnapshotProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SnapshotProto$SnapshotFlag();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0001\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဵ\u0000\u0003်\u0000\u0004ဳ\u0000\u0005ျ\u0000\u0006ွ\u0000", new Object[]{"value_", "valueCase_", "bitField0_", "name_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SnapshotProto$SnapshotFlag.class) {
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

    public boolean getBooleanValue() {
        if (this.valueCase_ == 3) {
            return ((Boolean) this.value_).booleanValue();
        }
        return false;
    }

    public ByteString getBytesValue() {
        if (this.valueCase_ == 6) {
            return (ByteString) this.value_;
        }
        return ByteString.EMPTY;
    }

    public double getDoubleValue() {
        if (this.valueCase_ == 4) {
            return ((Double) this.value_).doubleValue();
        }
        return 0.0d;
    }

    public long getLongValue() {
        if (this.valueCase_ == 2) {
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
            super(SnapshotProto$SnapshotFlag.DEFAULT_INSTANCE);
        }

        public Builder setBooleanValue(boolean z) {
            copyOnWrite();
            ((SnapshotProto$SnapshotFlag) this.instance).setBooleanValue(z);
            return this;
        }

        public Builder setBytesValue(ByteString byteString) {
            copyOnWrite();
            ((SnapshotProto$SnapshotFlag) this.instance).setBytesValue(byteString);
            return this;
        }

        public Builder setDoubleValue(double d) {
            copyOnWrite();
            ((SnapshotProto$SnapshotFlag) this.instance).setDoubleValue(d);
            return this;
        }

        public Builder setLongValue(long j) {
            copyOnWrite();
            ((SnapshotProto$SnapshotFlag) this.instance).setLongValue(j);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((SnapshotProto$SnapshotFlag) this.instance).setName(str);
            return this;
        }

        public Builder setStringValue(String str) {
            copyOnWrite();
            ((SnapshotProto$SnapshotFlag) this.instance).setStringValue(str);
            return this;
        }

        /* synthetic */ Builder(SnapshotProto$1 snapshotProto$1) {
            this();
        }
    }

    public String getStringValue() {
        return this.valueCase_ == 5 ? (String) this.value_ : "";
    }
}
