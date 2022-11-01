package com.google.protobuf;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WireFormat {
    public static final int WIRETYPE_END_GROUP = 4;
    public static final int WIRETYPE_FIXED32 = 5;
    public static final int WIRETYPE_FIXED64 = 1;
    public static final int WIRETYPE_LENGTH_DELIMITED = 2;
    public static final int WIRETYPE_START_GROUP = 3;
    public static final int WIRETYPE_VARINT = 0;
    static final int MESSAGE_SET_ITEM_TAG = makeTag(1, 3);
    static final int MESSAGE_SET_ITEM_END_TAG = makeTag(1, 4);
    static final int MESSAGE_SET_TYPE_ID_TAG = makeTag(2, 0);
    static final int MESSAGE_SET_MESSAGE_TAG = makeTag(3, 2);

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class FieldType {
        private final JavaType javaType;
        private final int wireType;
        public static final FieldType DOUBLE = new FieldType("DOUBLE", 0, JavaType.DOUBLE, 1);
        public static final FieldType FLOAT = new FieldType("FLOAT", 1, JavaType.FLOAT, 5);
        public static final FieldType INT64 = new FieldType("INT64", 2, JavaType.LONG, 0);
        public static final FieldType UINT64 = new FieldType("UINT64", 3, JavaType.LONG, 0);
        public static final FieldType INT32 = new FieldType("INT32", 4, JavaType.INT, 0);
        public static final FieldType FIXED64 = new FieldType("FIXED64", 5, JavaType.LONG, 1);
        public static final FieldType FIXED32 = new FieldType("FIXED32", 6, JavaType.INT, 5);
        public static final FieldType BOOL = new FieldType("BOOL", 7, JavaType.BOOLEAN, 0);
        public static final FieldType STRING = new AnonymousClass1("STRING", 8, JavaType.STRING, 2);
        public static final FieldType GROUP = new AnonymousClass2("GROUP", 9, JavaType.MESSAGE, 3);
        public static final FieldType MESSAGE = new AnonymousClass3("MESSAGE", 10, JavaType.MESSAGE, 2);
        public static final FieldType BYTES = new AnonymousClass4("BYTES", 11, JavaType.BYTE_STRING, 2);
        public static final FieldType UINT32 = new FieldType("UINT32", 12, JavaType.INT, 0);
        public static final FieldType ENUM = new FieldType("ENUM", 13, JavaType.ENUM, 0);
        public static final FieldType SFIXED32 = new FieldType("SFIXED32", 14, JavaType.INT, 5);
        public static final FieldType SFIXED64 = new FieldType("SFIXED64", 15, JavaType.LONG, 1);
        public static final FieldType SINT32 = new FieldType("SINT32", 16, JavaType.INT, 0);
        public static final FieldType SINT64 = new FieldType("SINT64", 17, JavaType.LONG, 0);
        private static final /* synthetic */ FieldType[] $VALUES = $values();

        /* compiled from: PG */
        /* renamed from: com.google.protobuf.WireFormat$FieldType$1  reason: invalid class name */
        /* loaded from: classes.dex */
        enum AnonymousClass1 extends FieldType {
            private AnonymousClass1(String str, int i, JavaType javaType, int i2) {
                super(str, i, javaType, i2);
            }
        }

        /* compiled from: PG */
        /* renamed from: com.google.protobuf.WireFormat$FieldType$2  reason: invalid class name */
        /* loaded from: classes.dex */
        enum AnonymousClass2 extends FieldType {
            private AnonymousClass2(String str, int i, JavaType javaType, int i2) {
                super(str, i, javaType, i2);
            }
        }

        /* compiled from: PG */
        /* renamed from: com.google.protobuf.WireFormat$FieldType$3  reason: invalid class name */
        /* loaded from: classes.dex */
        enum AnonymousClass3 extends FieldType {
            private AnonymousClass3(String str, int i, JavaType javaType, int i2) {
                super(str, i, javaType, i2);
            }
        }

        /* compiled from: PG */
        /* renamed from: com.google.protobuf.WireFormat$FieldType$4  reason: invalid class name */
        /* loaded from: classes.dex */
        enum AnonymousClass4 extends FieldType {
            private AnonymousClass4(String str, int i, JavaType javaType, int i2) {
                super(str, i, javaType, i2);
            }
        }

        private static /* synthetic */ FieldType[] $values() {
            return new FieldType[]{DOUBLE, FLOAT, INT64, UINT64, INT32, FIXED64, FIXED32, BOOL, STRING, GROUP, MESSAGE, BYTES, UINT32, ENUM, SFIXED32, SFIXED64, SINT32, SINT64};
        }

        private FieldType(String str, int i, JavaType javaType, int i2) {
            this.javaType = javaType;
            this.wireType = i2;
        }

        public static FieldType[] values() {
            return (FieldType[]) $VALUES.clone();
        }

        public JavaType getJavaType() {
            return this.javaType;
        }

        public int getWireType() {
            return this.wireType;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum JavaType {
        INT(0),
        LONG(0L),
        FLOAT(Float.valueOf(0.0f)),
        DOUBLE(Double.valueOf(0.0d)),
        BOOLEAN(false),
        STRING(""),
        BYTE_STRING(ByteString.EMPTY),
        ENUM(null),
        MESSAGE(null);
        
        private final Object defaultDefault;

        JavaType(Object obj) {
            this.defaultDefault = obj;
        }
    }

    public static int getTagFieldNumber(int i) {
        return i >>> 3;
    }

    public static int getTagWireType(int i) {
        return i & 7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int makeTag(int i, int i2) {
        return (i << 3) | i2;
    }
}
