package com.google.protobuf;

import com.google.android.apps.authenticator.testability.android.os.PowerManager;
import com.google.protobuf.ArrayDecoders;
import com.google.protobuf.ByteString;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.WireFormat;
import com.google.protobuf.Writer;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class MessageSchema implements Schema {
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final boolean proto3;
    private final int repeatedFieldOffsetStart;
    private final UnknownFieldSchema unknownFieldSchema;
    private final boolean useCachedSizeField;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.protobuf.MessageSchema$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    private MessageSchema(int[] iArr, Object[] objArr, int i, int i2, MessageLite messageLite, boolean z, boolean z2, int[] iArr2, int i3, int i4, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema unknownFieldSchema, ExtensionSchema extensionSchema, MapFieldSchema mapFieldSchema) {
        this.buffer = iArr;
        this.objects = objArr;
        this.minFieldNumber = i;
        this.maxFieldNumber = i2;
        this.lite = messageLite instanceof GeneratedMessageLite;
        this.proto3 = z;
        this.hasExtensions = extensionSchema != null && extensionSchema.hasExtensions(messageLite);
        this.useCachedSizeField = z2;
        this.intArray = iArr2;
        this.checkInitializedCount = i3;
        this.repeatedFieldOffsetStart = i4;
        this.newInstanceSchema = newInstanceSchema;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSchema;
        this.extensionSchema = extensionSchema;
        this.defaultInstance = messageLite;
        this.mapFieldSchema = mapFieldSchema;
    }

    private boolean arePresentForEquals(Object obj, Object obj2, int i) {
        return isFieldPresent(obj, i) == isFieldPresent(obj2, i);
    }

    private static boolean booleanAt(Object obj, long j) {
        return UnsafeUtil.getBoolean(obj, j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v6, types: [int] */
    private int decodeMapEntry(byte[] bArr, int i, int i2, MapEntryLite.Metadata metadata, Map map, ArrayDecoders.Registers registers) {
        int i3;
        int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
        int i4 = registers.int1;
        if (i4 >= 0 && i4 <= i2 - decodeVarint32) {
            int i5 = decodeVarint32 + i4;
            Object obj = metadata.defaultKey;
            Object obj2 = metadata.defaultValue;
            while (decodeVarint32 < i5) {
                int i6 = decodeVarint32 + 1;
                byte b = bArr[decodeVarint32];
                if (b < 0) {
                    i3 = ArrayDecoders.decodeVarint32(b, bArr, i6, registers);
                    b = registers.int1;
                } else {
                    i3 = i6;
                }
                int i7 = b & 7;
                switch (b >>> 3) {
                    case 1:
                        if (i7 != metadata.keyType.getWireType()) {
                            break;
                        } else {
                            decodeVarint32 = decodeMapEntryValue(bArr, i3, i2, metadata.keyType, null, registers);
                            obj = registers.object1;
                            continue;
                        }
                    case 2:
                        if (i7 != metadata.valueType.getWireType()) {
                            break;
                        } else {
                            decodeVarint32 = decodeMapEntryValue(bArr, i3, i2, metadata.valueType, metadata.defaultValue.getClass(), registers);
                            obj2 = registers.object1;
                            continue;
                        }
                }
                decodeVarint32 = ArrayDecoders.skipField(b, bArr, i3, i2, registers);
            }
            if (decodeVarint32 != i5) {
                throw InvalidProtocolBufferException.parseFailure();
            }
            map.put(obj, obj2);
            return i5;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    private int decodeMapEntryValue(byte[] bArr, int i, int i2, WireFormat.FieldType fieldType, Class cls, ArrayDecoders.Registers registers) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                int decodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return decodeVarint64;
            case 2:
                return ArrayDecoders.decodeBytes(bArr, i, registers);
            case 3:
                registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(bArr, i));
                return i + 8;
            case 4:
            case 5:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(bArr, i));
                return i + 4;
            case 6:
            case 7:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(bArr, i));
                return i + 8;
            case 8:
                registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return decodeVarint32;
            case 12:
            case 13:
                int decodeVarint642 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return decodeVarint642;
            case 14:
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor(cls), bArr, i, i2, registers);
            case 15:
                int decodeVarint322 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                return decodeVarint322;
            case 16:
                int decodeVarint643 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                return decodeVarint643;
            case 17:
                return ArrayDecoders.decodeStringRequireUtf8(bArr, i, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static double doubleAt(Object obj, long j) {
        return UnsafeUtil.getDouble(obj, j);
    }

    private final Object filterMapUnknownEnumValues(Object obj, int i, Object obj2, UnknownFieldSchema unknownFieldSchema) {
        int numberAt = numberAt(i);
        Object object = UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i)));
        if (object == null) {
            return obj2;
        }
        Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(i);
        if (enumFieldVerifier == null) {
            return obj2;
        }
        return filterUnknownEnumMap(i, numberAt, this.mapFieldSchema.forMutableMapData(object), enumFieldVerifier, obj2, unknownFieldSchema);
    }

    private final Object filterUnknownEnumMap(int i, int i2, Map map, Internal.EnumVerifier enumVerifier, Object obj, UnknownFieldSchema unknownFieldSchema) {
        MapEntryLite.Metadata forMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i));
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!enumVerifier.isInRange(((Integer) entry.getValue()).intValue())) {
                if (obj == null) {
                    obj = unknownFieldSchema.newBuilder();
                }
                ByteString.CodedBuilder newCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(forMapMetadata, entry.getKey(), entry.getValue()));
                try {
                    MapEntryLite.writeTo(newCodedBuilder.getCodedOutput(), forMapMetadata, entry.getKey(), entry.getValue());
                    unknownFieldSchema.addLengthDelimited(obj, i2, newCodedBuilder.build());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return obj;
    }

    private static float floatAt(Object obj, long j) {
        return UnsafeUtil.getFloat(obj, j);
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int i) {
        int i2 = i / 3;
        return (Internal.EnumVerifier) this.objects[i2 + i2 + 1];
    }

    private Object getMapFieldDefaultEntry(int i) {
        int i2 = i / 3;
        return this.objects[i2 + i2];
    }

    private Schema getMessageFieldSchema(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        Schema schema = (Schema) this.objects[i3];
        if (schema != null) {
            return schema;
        }
        Schema schemaFor = Protobuf.getInstance().schemaFor((Class) this.objects[i3 + 1]);
        this.objects[i3] = schemaFor;
        return schemaFor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UnknownFieldSetLite getMutableUnknownFields(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite == UnknownFieldSetLite.getDefaultInstance()) {
            UnknownFieldSetLite newInstance = UnknownFieldSetLite.newInstance();
            generatedMessageLite.unknownFields = newInstance;
            return newInstance;
        }
        return unknownFieldSetLite;
    }

    private int getSerializedSizeProto3(Object obj) {
        int i;
        Unsafe unsafe = UNSAFE;
        int i2 = 0;
        for (int i3 = 0; i3 < this.buffer.length; i3 += 3) {
            int typeAndOffsetAt = typeAndOffsetAt(i3);
            int type = type(typeAndOffsetAt);
            int numberAt = numberAt(i3);
            long offset = offset(typeAndOffsetAt);
            if (type >= FieldType.DOUBLE_LIST_PACKED.id() && type <= FieldType.SINT64_LIST_PACKED.id()) {
                i = this.buffer[i3 + 2] & 1048575;
            } else {
                i = 0;
            }
            switch (type) {
                case 0:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeInt64Size(numberAt, UnsafeUtil.getLong(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeUInt64Size(numberAt, UnsafeUtil.getLong(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeInt32Size(numberAt, UnsafeUtil.getInt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeFixed32Size(numberAt, 0);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeBoolSize(numberAt, true);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (isFieldPresent(obj, i3)) {
                        Object object = UnsafeUtil.getObject(obj, offset);
                        if (object instanceof ByteString) {
                            i2 += CodedOutputStream.computeBytesSize(numberAt, (ByteString) object);
                            break;
                        } else {
                            i2 += CodedOutputStream.computeStringSize(numberAt, (String) object);
                            break;
                        }
                    } else {
                        break;
                    }
                case 9:
                    if (isFieldPresent(obj, i3)) {
                        i2 += SchemaUtil.computeSizeMessage(numberAt, UnsafeUtil.getObject(obj, offset), getMessageFieldSchema(i3));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeBytesSize(numberAt, (ByteString) UnsafeUtil.getObject(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeUInt32Size(numberAt, UnsafeUtil.getInt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeEnumSize(numberAt, UnsafeUtil.getInt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeSFixed32Size(numberAt, 0);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeSInt32Size(numberAt, UnsafeUtil.getInt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeSInt64Size(numberAt, UnsafeUtil.getLong(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (isFieldPresent(obj, i3)) {
                        i2 += CodedOutputStream.computeGroupSize(numberAt, (MessageLite) UnsafeUtil.getObject(obj, offset), getMessageFieldSchema(i3));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    i2 += SchemaUtil.computeSizeFixed64List(numberAt, listAt(obj, offset), false);
                    break;
                case 19:
                    i2 += SchemaUtil.computeSizeFixed32List(numberAt, listAt(obj, offset), false);
                    break;
                case 20:
                    i2 += SchemaUtil.computeSizeInt64List(numberAt, listAt(obj, offset), false);
                    break;
                case 21:
                    i2 += SchemaUtil.computeSizeUInt64List(numberAt, listAt(obj, offset), false);
                    break;
                case 22:
                    i2 += SchemaUtil.computeSizeInt32List(numberAt, listAt(obj, offset), false);
                    break;
                case 23:
                    i2 += SchemaUtil.computeSizeFixed64List(numberAt, listAt(obj, offset), false);
                    break;
                case 24:
                    i2 += SchemaUtil.computeSizeFixed32List(numberAt, listAt(obj, offset), false);
                    break;
                case 25:
                    i2 += SchemaUtil.computeSizeBoolList(numberAt, listAt(obj, offset), false);
                    break;
                case 26:
                    i2 += SchemaUtil.computeSizeStringList(numberAt, listAt(obj, offset));
                    break;
                case 27:
                    i2 += SchemaUtil.computeSizeMessageList(numberAt, listAt(obj, offset), getMessageFieldSchema(i3));
                    break;
                case 28:
                    i2 += SchemaUtil.computeSizeByteStringList(numberAt, listAt(obj, offset));
                    break;
                case 29:
                    i2 += SchemaUtil.computeSizeUInt32List(numberAt, listAt(obj, offset), false);
                    break;
                case 30:
                    i2 += SchemaUtil.computeSizeEnumList(numberAt, listAt(obj, offset), false);
                    break;
                case 31:
                    i2 += SchemaUtil.computeSizeFixed32List(numberAt, listAt(obj, offset), false);
                    break;
                case 32:
                    i2 += SchemaUtil.computeSizeFixed64List(numberAt, listAt(obj, offset), false);
                    break;
                case 33:
                    i2 += SchemaUtil.computeSizeSInt32List(numberAt, listAt(obj, offset), false);
                    break;
                case 34:
                    i2 += SchemaUtil.computeSizeSInt64List(numberAt, listAt(obj, offset), false);
                    break;
                case 35:
                    int computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeFixed64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeFixed64ListNoTag);
                        }
                        i2 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag) + computeSizeFixed64ListNoTag;
                        break;
                    }
                case 36:
                    int computeSizeFixed32ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeFixed32ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeFixed32ListNoTag);
                        }
                        i2 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed32ListNoTag) + computeSizeFixed32ListNoTag;
                        break;
                    }
                case 37:
                    int computeSizeInt64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeInt64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeInt64ListNoTag);
                        }
                        i2 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeInt64ListNoTag) + computeSizeInt64ListNoTag;
                        break;
                    }
                case 38:
                    int computeSizeUInt64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeUInt64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeUInt64ListNoTag);
                        }
                        i2 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeUInt64ListNoTag) + computeSizeUInt64ListNoTag;
                        break;
                    }
                case 39:
                    int computeSizeInt32ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeInt32ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeInt32ListNoTag);
                        }
                        i2 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeInt32ListNoTag) + computeSizeInt32ListNoTag;
                        break;
                    }
                case 40:
                    int computeSizeFixed64ListNoTag2 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeFixed64ListNoTag2 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeFixed64ListNoTag2);
                        }
                        i2 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag2) + computeSizeFixed64ListNoTag2;
                        break;
                    }
                case 41:
                    int computeSizeFixed32ListNoTag2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeFixed32ListNoTag2 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeFixed32ListNoTag2);
                        }
                        i2 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed32ListNoTag2) + computeSizeFixed32ListNoTag2;
                        break;
                    }
                case 42:
                    int computeSizeBoolListNoTag = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeBoolListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeBoolListNoTag);
                        }
                        i2 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeBoolListNoTag) + computeSizeBoolListNoTag;
                        break;
                    }
                case 43:
                    int computeSizeUInt32ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeUInt32ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeUInt32ListNoTag);
                        }
                        i2 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeUInt32ListNoTag) + computeSizeUInt32ListNoTag;
                        break;
                    }
                case 44:
                    int computeSizeEnumListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeEnumListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeEnumListNoTag);
                        }
                        i2 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeEnumListNoTag) + computeSizeEnumListNoTag;
                        break;
                    }
                case 45:
                    int computeSizeFixed32ListNoTag3 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeFixed32ListNoTag3 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeFixed32ListNoTag3);
                        }
                        i2 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed32ListNoTag3) + computeSizeFixed32ListNoTag3;
                        break;
                    }
                case 46:
                    int computeSizeFixed64ListNoTag3 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeFixed64ListNoTag3 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeFixed64ListNoTag3);
                        }
                        i2 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag3) + computeSizeFixed64ListNoTag3;
                        break;
                    }
                case 47:
                    int computeSizeSInt32ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeSInt32ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeSInt32ListNoTag);
                        }
                        i2 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeSInt32ListNoTag) + computeSizeSInt32ListNoTag;
                        break;
                    }
                case 48:
                    int computeSizeSInt64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeSInt64ListNoTag <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeSInt64ListNoTag);
                        }
                        i2 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeSInt64ListNoTag) + computeSizeSInt64ListNoTag;
                        break;
                    }
                case 49:
                    i2 += SchemaUtil.computeSizeGroupList(numberAt, listAt(obj, offset), getMessageFieldSchema(i3));
                    break;
                case 50:
                    i2 += this.mapFieldSchema.getSerializedSize(numberAt, UnsafeUtil.getObject(obj, offset), getMapFieldDefaultEntry(i3));
                    break;
                case 51:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeInt64Size(numberAt, oneofLongAt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeUInt64Size(numberAt, oneofLongAt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeInt32Size(numberAt, oneofIntAt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeFixed32Size(numberAt, 0);
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeBoolSize(numberAt, true);
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        Object object2 = UnsafeUtil.getObject(obj, offset);
                        if (object2 instanceof ByteString) {
                            i2 += CodedOutputStream.computeBytesSize(numberAt, (ByteString) object2);
                            break;
                        } else {
                            i2 += CodedOutputStream.computeStringSize(numberAt, (String) object2);
                            break;
                        }
                    } else {
                        break;
                    }
                case 60:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += SchemaUtil.computeSizeMessage(numberAt, UnsafeUtil.getObject(obj, offset), getMessageFieldSchema(i3));
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeBytesSize(numberAt, (ByteString) UnsafeUtil.getObject(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeUInt32Size(numberAt, oneofIntAt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeEnumSize(numberAt, oneofIntAt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeSFixed32Size(numberAt, 0);
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeSInt32Size(numberAt, oneofIntAt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeSInt64Size(numberAt, oneofLongAt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(obj, numberAt, i3)) {
                        i2 += CodedOutputStream.computeGroupSize(numberAt, (MessageLite) UnsafeUtil.getObject(obj, offset), getMessageFieldSchema(i3));
                        break;
                    } else {
                        break;
                    }
            }
        }
        return i2 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, obj);
    }

    private int getUnknownFieldsSerializedSize(UnknownFieldSchema unknownFieldSchema, Object obj) {
        return unknownFieldSchema.getSerializedSize(unknownFieldSchema.getFromMessage(obj));
    }

    private static int intAt(Object obj, long j) {
        return UnsafeUtil.getInt(obj, j);
    }

    private static boolean isEnforceUtf8(int i) {
        return (i & PowerManager.ON_AFTER_RELEASE) != 0;
    }

    private boolean isFieldPresent(Object obj, int i) {
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i);
        long j = 1048575 & presenceMaskAndOffsetAt;
        if (j != 1048575) {
            return (UnsafeUtil.getInt(obj, j) & (1 << (presenceMaskAndOffsetAt >>> 20))) != 0;
        }
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                return Double.doubleToRawLongBits(UnsafeUtil.getDouble(obj, offset)) != 0;
            case 1:
                return Float.floatToRawIntBits(UnsafeUtil.getFloat(obj, offset)) != 0;
            case 2:
                return UnsafeUtil.getLong(obj, offset) != 0;
            case 3:
                return UnsafeUtil.getLong(obj, offset) != 0;
            case 4:
                return UnsafeUtil.getInt(obj, offset) != 0;
            case 5:
                return UnsafeUtil.getLong(obj, offset) != 0;
            case 6:
                return UnsafeUtil.getInt(obj, offset) != 0;
            case 7:
                return UnsafeUtil.getBoolean(obj, offset);
            case 8:
                Object object = UnsafeUtil.getObject(obj, offset);
                if (object instanceof String) {
                    return !((String) object).isEmpty();
                }
                if (object instanceof ByteString) {
                    return !ByteString.EMPTY.equals(object);
                }
                throw new IllegalArgumentException();
            case 9:
                return UnsafeUtil.getObject(obj, offset) != null;
            case 10:
                return !ByteString.EMPTY.equals(UnsafeUtil.getObject(obj, offset));
            case 11:
                return UnsafeUtil.getInt(obj, offset) != 0;
            case 12:
                return UnsafeUtil.getInt(obj, offset) != 0;
            case 13:
                return UnsafeUtil.getInt(obj, offset) != 0;
            case 14:
                return UnsafeUtil.getLong(obj, offset) != 0;
            case 15:
                return UnsafeUtil.getInt(obj, offset) != 0;
            case 16:
                return UnsafeUtil.getLong(obj, offset) != 0;
            case 17:
                return UnsafeUtil.getObject(obj, offset) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private boolean isListInitialized(Object obj, int i, int i2) {
        List list = (List) UnsafeUtil.getObject(obj, offset(i));
        if (list.isEmpty()) {
            return true;
        }
        Schema messageFieldSchema = getMessageFieldSchema(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (!messageFieldSchema.isInitialized(list.get(i3))) {
                return false;
            }
        }
        return true;
    }

    private boolean isMapInitialized(Object obj, int i, int i2) {
        Map forMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(obj, offset(i)));
        if (forMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        Schema schema = null;
        for (Object obj2 : forMapData.values()) {
            if (schema == null) {
                schema = Protobuf.getInstance().schemaFor((Class) obj2.getClass());
            }
            if (!schema.isInitialized(obj2)) {
                return false;
            }
        }
        return true;
    }

    private boolean isOneofCaseEqual(Object obj, Object obj2, int i) {
        long presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i) & 1048575;
        return UnsafeUtil.getInt(obj, presenceMaskAndOffsetAt) == UnsafeUtil.getInt(obj2, presenceMaskAndOffsetAt);
    }

    private boolean isOneofPresent(Object obj, int i, int i2) {
        return UnsafeUtil.getInt(obj, (long) (presenceMaskAndOffsetAt(i2) & 1048575)) == i;
    }

    private static boolean isRequired(int i) {
        return (i & PowerManager.ACQUIRE_CAUSES_WAKEUP) != 0;
    }

    private static List listAt(Object obj, long j) {
        return (List) UnsafeUtil.getObject(obj, j);
    }

    private static long longAt(Object obj, long j) {
        return UnsafeUtil.getLong(obj, j);
    }

    private final void mergeMap(Object obj, int i, Object obj2, ExtensionRegistryLite extensionRegistryLite, Reader reader) {
        long offset = offset(typeAndOffsetAt(i));
        Object object = UnsafeUtil.getObject(obj, offset);
        if (object == null) {
            object = this.mapFieldSchema.newMapField(obj2);
            UnsafeUtil.putObject(obj, offset, object);
        } else if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(obj2);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            UnsafeUtil.putObject(obj, offset, newMapField);
            object = newMapField;
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(object), this.mapFieldSchema.forMapMetadata(obj2), extensionRegistryLite);
    }

    private void mergeMessage(Object obj, Object obj2, int i) {
        long offset = offset(typeAndOffsetAt(i));
        if (!isFieldPresent(obj2, i)) {
            return;
        }
        Object object = UnsafeUtil.getObject(obj, offset);
        Object object2 = UnsafeUtil.getObject(obj2, offset);
        if (object != null && object2 != null) {
            UnsafeUtil.putObject(obj, offset, Internal.mergeMessage(object, object2));
            setFieldPresent(obj, i);
        } else if (object2 != null) {
            UnsafeUtil.putObject(obj, offset, object2);
            setFieldPresent(obj, i);
        }
    }

    private void mergeOneofMessage(Object obj, Object obj2, int i) {
        Object obj3;
        int typeAndOffsetAt = typeAndOffsetAt(i);
        int numberAt = numberAt(i);
        long offset = offset(typeAndOffsetAt);
        if (!isOneofPresent(obj2, numberAt, i)) {
            return;
        }
        if (isOneofPresent(obj, numberAt, i)) {
            obj3 = UnsafeUtil.getObject(obj, offset);
        } else {
            obj3 = null;
        }
        Object object = UnsafeUtil.getObject(obj2, offset);
        if (obj3 != null && object != null) {
            UnsafeUtil.putObject(obj, offset, Internal.mergeMessage(obj3, object));
            setOneofPresent(obj, numberAt, i);
        } else if (object != null) {
            UnsafeUtil.putObject(obj, offset, object);
            setOneofPresent(obj, numberAt, i);
        }
    }

    private void mergeSingleField(Object obj, Object obj2, int i) {
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        int numberAt = numberAt(i);
        switch (type(typeAndOffsetAt)) {
            case 0:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putDouble(obj, offset, UnsafeUtil.getDouble(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 1:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putFloat(obj, offset, UnsafeUtil.getFloat(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 2:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putLong(obj, offset, UnsafeUtil.getLong(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 3:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putLong(obj, offset, UnsafeUtil.getLong(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 4:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 5:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putLong(obj, offset, UnsafeUtil.getLong(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 6:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 7:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putBoolean(obj, offset, UnsafeUtil.getBoolean(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 8:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putObject(obj, offset, UnsafeUtil.getObject(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 9:
                mergeMessage(obj, obj2, i);
                return;
            case 10:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putObject(obj, offset, UnsafeUtil.getObject(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 11:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 12:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 13:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 14:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putLong(obj, offset, UnsafeUtil.getLong(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 15:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 16:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putLong(obj, offset, UnsafeUtil.getLong(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 17:
                mergeMessage(obj, obj2, i);
                return;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                this.listFieldSchema.mergeListsAt(obj, obj2, offset);
                return;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, obj, obj2, offset);
                return;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (isOneofPresent(obj2, numberAt, i)) {
                    UnsafeUtil.putObject(obj, offset, UnsafeUtil.getObject(obj2, offset));
                    setOneofPresent(obj, numberAt, i);
                    return;
                }
                return;
            case 60:
                mergeOneofMessage(obj, obj2, i);
                return;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (isOneofPresent(obj2, numberAt, i)) {
                    UnsafeUtil.putObject(obj, offset, UnsafeUtil.getObject(obj2, offset));
                    setOneofPresent(obj, numberAt, i);
                    return;
                }
                return;
            case 68:
                mergeOneofMessage(obj, obj2, i);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MessageSchema newSchema(Class cls, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema unknownFieldSchema, ExtensionSchema extensionSchema, MapFieldSchema mapFieldSchema) {
        if (messageInfo instanceof RawMessageInfo) {
            return newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
        }
        return newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    static MessageSchema newSchemaForMessageInfo(StructuralMessageInfo structuralMessageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema unknownFieldSchema, ExtensionSchema extensionSchema, MapFieldSchema mapFieldSchema) {
        int fieldNumber;
        int fieldNumber2;
        int i;
        boolean z = structuralMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        FieldInfo[] fields = structuralMessageInfo.getFields();
        if (fields.length != 0) {
            fieldNumber = fields[0].getFieldNumber();
            fieldNumber2 = fields[fields.length - 1].getFieldNumber();
        } else {
            fieldNumber = 0;
            fieldNumber2 = 0;
        }
        int length = fields.length;
        int[] iArr = new int[length * 3];
        Object[] objArr = new Object[length + length];
        int i2 = 0;
        int i3 = 0;
        for (FieldInfo fieldInfo : fields) {
            if (fieldInfo.getType() == FieldType.MAP) {
                i2++;
            } else if (fieldInfo.getType().id() >= 18 && fieldInfo.getType().id() <= 49) {
                i3++;
            }
        }
        int[] iArr2 = i2 > 0 ? new int[i2] : null;
        int[] iArr3 = i3 > 0 ? new int[i3] : null;
        int[] checkInitialized = structuralMessageInfo.getCheckInitialized();
        if (checkInitialized == null) {
            checkInitialized = EMPTY_INT_ARRAY;
        }
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i4 < fields.length) {
            FieldInfo fieldInfo2 = fields[i4];
            int fieldNumber3 = fieldInfo2.getFieldNumber();
            storeFieldData(fieldInfo2, iArr, i5, objArr);
            if (i6 < checkInitialized.length && checkInitialized[i6] == fieldNumber3) {
                checkInitialized[i6] = i5;
                i6++;
            }
            if (fieldInfo2.getType() == FieldType.MAP) {
                iArr2[i7] = i5;
                i7++;
                i = i5;
            } else if (fieldInfo2.getType().id() < 18 || fieldInfo2.getType().id() > 49) {
                i = i5;
            } else {
                i = i5;
                iArr3[i8] = (int) UnsafeUtil.objectFieldOffset(fieldInfo2.getField());
                i8++;
            }
            i4++;
            i5 = i + 3;
        }
        if (iArr2 == null) {
            iArr2 = EMPTY_INT_ARRAY;
        }
        if (iArr3 == null) {
            iArr3 = EMPTY_INT_ARRAY;
        }
        int[] iArr4 = new int[checkInitialized.length + iArr2.length + iArr3.length];
        System.arraycopy(checkInitialized, 0, iArr4, 0, checkInitialized.length);
        System.arraycopy(iArr2, 0, iArr4, checkInitialized.length, iArr2.length);
        System.arraycopy(iArr3, 0, iArr4, checkInitialized.length + iArr2.length, iArr3.length);
        return new MessageSchema(iArr, objArr, fieldNumber, fieldNumber2, structuralMessageInfo.getDefaultInstance(), z, true, iArr4, checkInitialized.length, checkInitialized.length + iArr2.length, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Removed duplicated region for block: B:133:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0292  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static MessageSchema newSchemaForRawMessageInfo(RawMessageInfo rawMessageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema unknownFieldSchema, ExtensionSchema extensionSchema, MapFieldSchema mapFieldSchema) {
        int i;
        int charAt;
        int charAt2;
        int charAt3;
        int charAt4;
        int charAt5;
        int[] iArr;
        int i2;
        int i3;
        int i4;
        char charAt6;
        int i5;
        char charAt7;
        int i6;
        char charAt8;
        int i7;
        char charAt9;
        int i8;
        char charAt10;
        int i9;
        char charAt11;
        int i10;
        char charAt12;
        int i11;
        char charAt13;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int objectFieldOffset;
        String str;
        boolean z;
        Object[] objArr;
        int i18;
        int i19;
        int i20;
        Field reflectField;
        char charAt14;
        int i21;
        Object obj;
        Field reflectField2;
        Object obj2;
        Field reflectField3;
        int i22;
        char charAt15;
        int i23;
        char charAt16;
        int i24;
        char charAt17;
        int i25;
        char charAt18;
        boolean z2 = rawMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        String stringInfo = rawMessageInfo.getStringInfo();
        int length = stringInfo.length();
        char c = 55296;
        if (stringInfo.charAt(0) >= 55296) {
            int i26 = 1;
            while (true) {
                i = i26 + 1;
                if (stringInfo.charAt(i26) < 55296) {
                    break;
                }
                i26 = i;
            }
        } else {
            i = 1;
        }
        int i27 = i + 1;
        int charAt19 = stringInfo.charAt(i);
        if (charAt19 >= 55296) {
            int i28 = charAt19 & 8191;
            int i29 = 13;
            while (true) {
                i25 = i27 + 1;
                charAt18 = stringInfo.charAt(i27);
                if (charAt18 < 55296) {
                    break;
                }
                i28 |= (charAt18 & 8191) << i29;
                i29 += 13;
                i27 = i25;
            }
            charAt19 = i28 | (charAt18 << i29);
            i27 = i25;
        }
        if (charAt19 == 0) {
            iArr = EMPTY_INT_ARRAY;
            i3 = 0;
            charAt = 0;
            charAt2 = 0;
            charAt3 = 0;
            charAt4 = 0;
            charAt5 = 0;
            i2 = 0;
        } else {
            int i30 = i27 + 1;
            int charAt20 = stringInfo.charAt(i27);
            if (charAt20 >= 55296) {
                int i31 = charAt20 & 8191;
                int i32 = 13;
                while (true) {
                    i11 = i30 + 1;
                    charAt13 = stringInfo.charAt(i30);
                    if (charAt13 < 55296) {
                        break;
                    }
                    i31 |= (charAt13 & 8191) << i32;
                    i32 += 13;
                    i30 = i11;
                }
                charAt20 = i31 | (charAt13 << i32);
                i30 = i11;
            }
            int i33 = i30 + 1;
            int charAt21 = stringInfo.charAt(i30);
            if (charAt21 >= 55296) {
                int i34 = charAt21 & 8191;
                int i35 = 13;
                while (true) {
                    i10 = i33 + 1;
                    charAt12 = stringInfo.charAt(i33);
                    if (charAt12 < 55296) {
                        break;
                    }
                    i34 |= (charAt12 & 8191) << i35;
                    i35 += 13;
                    i33 = i10;
                }
                charAt21 = i34 | (charAt12 << i35);
                i33 = i10;
            }
            int i36 = i33 + 1;
            charAt = stringInfo.charAt(i33);
            if (charAt >= 55296) {
                int i37 = charAt & 8191;
                int i38 = 13;
                while (true) {
                    i9 = i36 + 1;
                    charAt11 = stringInfo.charAt(i36);
                    if (charAt11 < 55296) {
                        break;
                    }
                    i37 |= (charAt11 & 8191) << i38;
                    i38 += 13;
                    i36 = i9;
                }
                charAt = i37 | (charAt11 << i38);
                i36 = i9;
            }
            int i39 = i36 + 1;
            charAt2 = stringInfo.charAt(i36);
            if (charAt2 >= 55296) {
                int i40 = charAt2 & 8191;
                int i41 = 13;
                while (true) {
                    i8 = i39 + 1;
                    charAt10 = stringInfo.charAt(i39);
                    if (charAt10 < 55296) {
                        break;
                    }
                    i40 |= (charAt10 & 8191) << i41;
                    i41 += 13;
                    i39 = i8;
                }
                charAt2 = i40 | (charAt10 << i41);
                i39 = i8;
            }
            int i42 = i39 + 1;
            charAt3 = stringInfo.charAt(i39);
            if (charAt3 >= 55296) {
                int i43 = charAt3 & 8191;
                int i44 = 13;
                while (true) {
                    i7 = i42 + 1;
                    charAt9 = stringInfo.charAt(i42);
                    if (charAt9 < 55296) {
                        break;
                    }
                    i43 |= (charAt9 & 8191) << i44;
                    i44 += 13;
                    i42 = i7;
                }
                charAt3 = i43 | (charAt9 << i44);
                i42 = i7;
            }
            int i45 = i42 + 1;
            charAt4 = stringInfo.charAt(i42);
            if (charAt4 >= 55296) {
                int i46 = charAt4 & 8191;
                int i47 = 13;
                while (true) {
                    i6 = i45 + 1;
                    charAt8 = stringInfo.charAt(i45);
                    if (charAt8 < 55296) {
                        break;
                    }
                    i46 |= (charAt8 & 8191) << i47;
                    i47 += 13;
                    i45 = i6;
                }
                charAt4 = i46 | (charAt8 << i47);
                i45 = i6;
            }
            int i48 = i45 + 1;
            int charAt22 = stringInfo.charAt(i45);
            if (charAt22 >= 55296) {
                int i49 = charAt22 & 8191;
                int i50 = 13;
                while (true) {
                    i5 = i48 + 1;
                    charAt7 = stringInfo.charAt(i48);
                    if (charAt7 < 55296) {
                        break;
                    }
                    i49 |= (charAt7 & 8191) << i50;
                    i50 += 13;
                    i48 = i5;
                }
                charAt22 = i49 | (charAt7 << i50);
                i48 = i5;
            }
            int i51 = i48 + 1;
            charAt5 = stringInfo.charAt(i48);
            if (charAt5 >= 55296) {
                int i52 = charAt5 & 8191;
                int i53 = 13;
                while (true) {
                    i4 = i51 + 1;
                    charAt6 = stringInfo.charAt(i51);
                    if (charAt6 < 55296) {
                        break;
                    }
                    i52 |= (charAt6 & 8191) << i53;
                    i53 += 13;
                    i51 = i4;
                }
                charAt5 = i52 | (charAt6 << i53);
                i51 = i4;
            }
            iArr = new int[charAt5 + charAt4 + charAt22];
            i2 = charAt20 + charAt20 + charAt21;
            i3 = charAt20;
            i27 = i51;
        }
        Unsafe unsafe = UNSAFE;
        Object[] objects = rawMessageInfo.getObjects();
        Class<?> cls = rawMessageInfo.getDefaultInstance().getClass();
        int[] iArr2 = new int[charAt3 * 3];
        Object[] objArr2 = new Object[charAt3 + charAt3];
        int i54 = charAt5 + charAt4;
        int i55 = charAt5;
        int i56 = i54;
        int i57 = 0;
        int i58 = 0;
        while (i27 < length) {
            int i59 = i27 + 1;
            int charAt23 = stringInfo.charAt(i27);
            if (charAt23 >= c) {
                int i60 = charAt23 & 8191;
                int i61 = i59;
                int i62 = 13;
                while (true) {
                    i24 = i61 + 1;
                    charAt17 = stringInfo.charAt(i61);
                    if (charAt17 < c) {
                        break;
                    }
                    i60 |= (charAt17 & 8191) << i62;
                    i62 += 13;
                    i61 = i24;
                }
                charAt23 = i60 | (charAt17 << i62);
                i12 = i24;
            } else {
                i12 = i59;
            }
            int i63 = i12 + 1;
            int charAt24 = stringInfo.charAt(i12);
            if (charAt24 >= c) {
                int i64 = charAt24 & 8191;
                int i65 = i63;
                int i66 = 13;
                while (true) {
                    i23 = i65 + 1;
                    charAt16 = stringInfo.charAt(i65);
                    i13 = length;
                    if (charAt16 < 55296) {
                        break;
                    }
                    i64 |= (charAt16 & 8191) << i66;
                    i66 += 13;
                    i65 = i23;
                    length = i13;
                }
                charAt24 = i64 | (charAt16 << i66);
                i14 = i23;
            } else {
                i13 = length;
                i14 = i63;
            }
            int i67 = charAt24 & 255;
            int i68 = charAt5;
            if ((charAt24 & 1024) != 0) {
                iArr[i57] = i58;
                i57++;
            }
            if (i67 >= 51) {
                int i69 = i14 + 1;
                int charAt25 = stringInfo.charAt(i14);
                i17 = i57;
                char c2 = 55296;
                if (charAt25 >= 55296) {
                    int i70 = charAt25 & 8191;
                    int i71 = 13;
                    while (true) {
                        i22 = i69 + 1;
                        charAt15 = stringInfo.charAt(i69);
                        if (charAt15 < c2) {
                            break;
                        }
                        i70 |= (charAt15 & 8191) << i71;
                        i71 += 13;
                        i69 = i22;
                        c2 = 55296;
                    }
                    charAt25 = i70 | (charAt15 << i71);
                    i69 = i22;
                }
                int i72 = i67 - 51;
                int i73 = i69;
                if (i72 == 9 || i72 == 17) {
                    int i74 = i58 / 3;
                    i21 = i2 + 1;
                    objArr2[i74 + i74 + 1] = objects[i2];
                } else {
                    if (i72 == 12 && !z2) {
                        int i75 = i58 / 3;
                        i21 = i2 + 1;
                        objArr2[i75 + i75 + 1] = objects[i2];
                    }
                    int i76 = charAt25 + charAt25;
                    obj = objects[i76];
                    if (obj instanceof Field) {
                        reflectField2 = reflectField(cls, (String) obj);
                        objects[i76] = reflectField2;
                    } else {
                        reflectField2 = (Field) obj;
                    }
                    i15 = charAt;
                    i16 = charAt2;
                    int objectFieldOffset2 = (int) unsafe.objectFieldOffset(reflectField2);
                    int i77 = i76 + 1;
                    obj2 = objects[i77];
                    if (obj2 instanceof Field) {
                        reflectField3 = reflectField(cls, (String) obj2);
                        objects[i77] = reflectField3;
                    } else {
                        reflectField3 = (Field) obj2;
                    }
                    int objectFieldOffset3 = (int) unsafe.objectFieldOffset(reflectField3);
                    str = stringInfo;
                    z = z2;
                    objArr = objArr2;
                    i19 = i73;
                    i18 = objectFieldOffset3;
                    objectFieldOffset = objectFieldOffset2;
                    i20 = 0;
                }
                i2 = i21;
                int i762 = charAt25 + charAt25;
                obj = objects[i762];
                if (obj instanceof Field) {
                }
                i15 = charAt;
                i16 = charAt2;
                int objectFieldOffset22 = (int) unsafe.objectFieldOffset(reflectField2);
                int i772 = i762 + 1;
                obj2 = objects[i772];
                if (obj2 instanceof Field) {
                }
                int objectFieldOffset32 = (int) unsafe.objectFieldOffset(reflectField3);
                str = stringInfo;
                z = z2;
                objArr = objArr2;
                i19 = i73;
                i18 = objectFieldOffset32;
                objectFieldOffset = objectFieldOffset22;
                i20 = 0;
            } else {
                i15 = charAt;
                i16 = charAt2;
                i17 = i57;
                int i78 = i2 + 1;
                Field reflectField4 = reflectField(cls, (String) objects[i2]);
                if (i67 == 9 || i67 == 17) {
                    int i79 = i58 / 3;
                    objArr2[i79 + i79 + 1] = reflectField4.getType();
                } else if (i67 == 27 || i67 == 49) {
                    int i80 = i58 / 3;
                    objArr2[i80 + i80 + 1] = objects[i78];
                    i78++;
                } else if (i67 == 12 || i67 == 30 || i67 == 44) {
                    if (!z2) {
                        int i81 = i58 / 3;
                        objArr2[i81 + i81 + 1] = objects[i78];
                        i78++;
                    }
                } else if (i67 == 50) {
                    int i82 = i55 + 1;
                    iArr[i55] = i58;
                    int i83 = i58 / 3;
                    int i84 = i78 + 1;
                    int i85 = i83 + i83;
                    objArr2[i85] = objects[i78];
                    if ((charAt24 & 2048) != 0) {
                        i78 = i84 + 1;
                        objArr2[i85 + 1] = objects[i84];
                        i55 = i82;
                    } else {
                        i55 = i82;
                        i78 = i84;
                    }
                }
                int i86 = i78;
                objectFieldOffset = (int) unsafe.objectFieldOffset(reflectField4);
                if (!((charAt24 & 4096) == 4096) || i67 > 17) {
                    str = stringInfo;
                    z = z2;
                    objArr = objArr2;
                    i18 = 1048575;
                    i19 = i14;
                    i20 = 0;
                } else {
                    int i87 = i14 + 1;
                    int charAt26 = stringInfo.charAt(i14);
                    if (charAt26 >= 55296) {
                        int i88 = charAt26 & 8191;
                        int i89 = 13;
                        while (true) {
                            i19 = i87 + 1;
                            charAt14 = stringInfo.charAt(i87);
                            if (charAt14 < 55296) {
                                break;
                            }
                            i88 |= (charAt14 & 8191) << i89;
                            i89 += 13;
                            i87 = i19;
                        }
                        charAt26 = i88 | (charAt14 << i89);
                    } else {
                        i19 = i87;
                    }
                    int i90 = i3 + i3 + (charAt26 / 32);
                    Object obj3 = objects[i90];
                    str = stringInfo;
                    if (obj3 instanceof Field) {
                        reflectField = (Field) obj3;
                    } else {
                        reflectField = reflectField(cls, (String) obj3);
                        objects[i90] = reflectField;
                    }
                    z = z2;
                    objArr = objArr2;
                    i20 = charAt26 % 32;
                    i18 = (int) unsafe.objectFieldOffset(reflectField);
                }
                if (i67 < 18 || i67 > 49) {
                    i2 = i86;
                } else {
                    iArr[i56] = objectFieldOffset;
                    i56++;
                    i2 = i86;
                }
            }
            int i91 = i58 + 1;
            iArr2[i58] = charAt23;
            int i92 = i91 + 1;
            iArr2[i91] = ((charAt24 & 256) != 0 ? PowerManager.ACQUIRE_CAUSES_WAKEUP : 0) | ((charAt24 & 512) != 0 ? PowerManager.ON_AFTER_RELEASE : 0) | (i67 << 20) | objectFieldOffset;
            i58 = i92 + 1;
            iArr2[i92] = (i20 << 20) | i18;
            z2 = z;
            objArr2 = objArr;
            charAt = i15;
            charAt5 = i68;
            i57 = i17;
            length = i13;
            i27 = i19;
            stringInfo = str;
            charAt2 = i16;
            c = 55296;
        }
        return new MessageSchema(iArr2, objArr2, charAt, charAt2, rawMessageInfo.getDefaultInstance(), z2, false, iArr, charAt5, i54, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    private int numberAt(int i) {
        return this.buffer[i];
    }

    private static long offset(int i) {
        return i & 1048575;
    }

    private static boolean oneofBooleanAt(Object obj, long j) {
        return ((Boolean) UnsafeUtil.getObject(obj, j)).booleanValue();
    }

    private static double oneofDoubleAt(Object obj, long j) {
        return ((Double) UnsafeUtil.getObject(obj, j)).doubleValue();
    }

    private static float oneofFloatAt(Object obj, long j) {
        return ((Float) UnsafeUtil.getObject(obj, j)).floatValue();
    }

    private static int oneofIntAt(Object obj, long j) {
        return ((Integer) UnsafeUtil.getObject(obj, j)).intValue();
    }

    private static long oneofLongAt(Object obj, long j) {
        return ((Long) UnsafeUtil.getObject(obj, j)).longValue();
    }

    private int parseMapField(Object obj, byte[] bArr, int i, int i2, int i3, long j, ArrayDecoders.Registers registers) {
        Unsafe unsafe = UNSAFE;
        Object mapFieldDefaultEntry = getMapFieldDefaultEntry(i3);
        Object object = unsafe.getObject(obj, j);
        if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(mapFieldDefaultEntry);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            unsafe.putObject(obj, j, newMapField);
            object = newMapField;
        }
        return decodeMapEntry(bArr, i, i2, this.mapFieldSchema.forMapMetadata(mapFieldDefaultEntry), this.mapFieldSchema.forMutableMapData(object), registers);
    }

    private int parseOneofField(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, ArrayDecoders.Registers registers) {
        Unsafe unsafe = UNSAFE;
        long j2 = this.buffer[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(ArrayDecoders.decodeDouble(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(ArrayDecoders.decodeFloat(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 53:
            case 54:
                if (i5 == 0) {
                    int decodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                    unsafe.putObject(obj, j, Long.valueOf(registers.long1));
                    unsafe.putInt(obj, j2, i4);
                    return decodeVarint64;
                }
                break;
            case 55:
            case 62:
                if (i5 == 0) {
                    int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    unsafe.putObject(obj, j, Integer.valueOf(registers.int1));
                    unsafe.putInt(obj, j2, i4);
                    return decodeVarint32;
                }
                break;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(ArrayDecoders.decodeFixed64(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 8;
                }
                break;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(ArrayDecoders.decodeFixed32(bArr, i)));
                    unsafe.putInt(obj, j2, i4);
                    return i + 4;
                }
                break;
            case 58:
                if (i5 == 0) {
                    int decodeVarint642 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                    unsafe.putObject(obj, j, Boolean.valueOf(registers.long1 != 0));
                    unsafe.putInt(obj, j2, i4);
                    return decodeVarint642;
                }
                break;
            case 59:
                if (i5 == 2) {
                    int decodeVarint322 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    int i9 = registers.int1;
                    if (i9 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else if ((i6 & PowerManager.ON_AFTER_RELEASE) != 0 && !Utf8.isValidUtf8(bArr, decodeVarint322, decodeVarint322 + i9)) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    } else {
                        unsafe.putObject(obj, j, new String(bArr, decodeVarint322, i9, Internal.UTF_8));
                        decodeVarint322 += i9;
                    }
                    unsafe.putInt(obj, j2, i4);
                    return decodeVarint322;
                }
                break;
            case 60:
                if (i5 == 2) {
                    int decodeMessageField = ArrayDecoders.decodeMessageField(getMessageFieldSchema(i8), bArr, i, i2, registers);
                    Object object = unsafe.getInt(obj, j2) == i4 ? unsafe.getObject(obj, j) : null;
                    if (object == null) {
                        unsafe.putObject(obj, j, registers.object1);
                    } else {
                        unsafe.putObject(obj, j, Internal.mergeMessage(object, registers.object1));
                    }
                    unsafe.putInt(obj, j2, i4);
                    return decodeMessageField;
                }
                break;
            case 61:
                if (i5 == 2) {
                    int decodeBytes = ArrayDecoders.decodeBytes(bArr, i, registers);
                    unsafe.putObject(obj, j, registers.object1);
                    unsafe.putInt(obj, j2, i4);
                    return decodeBytes;
                }
                break;
            case 63:
                if (i5 == 0) {
                    int decodeVarint323 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    int i10 = registers.int1;
                    Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(i8);
                    if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i10)) {
                        unsafe.putObject(obj, j, Integer.valueOf(i10));
                        unsafe.putInt(obj, j2, i4);
                    } else {
                        getMutableUnknownFields(obj).storeField(i3, Long.valueOf(i10));
                    }
                    return decodeVarint323;
                }
                break;
            case 66:
                if (i5 == 0) {
                    int decodeVarint324 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                    unsafe.putObject(obj, j, Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1)));
                    unsafe.putInt(obj, j2, i4);
                    return decodeVarint324;
                }
                break;
            case 67:
                if (i5 == 0) {
                    int decodeVarint643 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                    unsafe.putObject(obj, j, Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1)));
                    unsafe.putInt(obj, j2, i4);
                    return decodeVarint643;
                }
                break;
            case 68:
                if (i5 == 3) {
                    int decodeGroupField = ArrayDecoders.decodeGroupField(getMessageFieldSchema(i8), bArr, i, i2, (i3 & (-8)) | 4, registers);
                    Object object2 = unsafe.getInt(obj, j2) == i4 ? unsafe.getObject(obj, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(obj, j, registers.object1);
                    } else {
                        unsafe.putObject(obj, j, Internal.mergeMessage(object2, registers.object1));
                    }
                    unsafe.putInt(obj, j2, i4);
                    return decodeGroupField;
                }
                break;
        }
        return i;
    }

    private int parseProto3Message(Object obj, byte[] bArr, int i, int i2, ArrayDecoders.Registers registers) {
        byte b;
        int i3;
        int i4;
        int i5;
        Unsafe unsafe;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        MessageSchema messageSchema = this;
        Object obj2 = obj;
        byte[] bArr2 = bArr;
        int i12 = i2;
        ArrayDecoders.Registers registers2 = registers;
        Unsafe unsafe2 = UNSAFE;
        int i13 = -1;
        int i14 = i;
        int i15 = -1;
        int i16 = 0;
        int i17 = 0;
        int i18 = 1048575;
        while (i14 < i12) {
            int i19 = i14 + 1;
            byte b2 = bArr2[i14];
            if (b2 < 0) {
                i3 = ArrayDecoders.decodeVarint32(b2, bArr2, i19, registers2);
                b = registers2.int1;
            } else {
                b = b2;
                i3 = i19;
            }
            int i20 = b >>> 3;
            int i21 = b & 7;
            int positionForFieldNumber = i20 > i15 ? messageSchema.positionForFieldNumber(i20, i16 / 3) : messageSchema.positionForFieldNumber(i20);
            if (positionForFieldNumber == i13) {
                i4 = i3;
                i5 = i20;
                unsafe = unsafe2;
                i6 = 0;
            } else {
                int i22 = messageSchema.buffer[positionForFieldNumber + 1];
                int type = type(i22);
                long offset = offset(i22);
                if (type <= 17) {
                    int i23 = messageSchema.buffer[positionForFieldNumber + 2];
                    int i24 = 1 << (i23 >>> 20);
                    int i25 = i23 & 1048575;
                    if (i25 != i18) {
                        if (i18 != 1048575) {
                            unsafe2.putInt(obj2, i18, i17);
                        }
                        if (i25 != 1048575) {
                            i17 = unsafe2.getInt(obj2, i25);
                        }
                        i18 = i25;
                    }
                    switch (type) {
                        case 0:
                            i5 = i20;
                            registers2 = registers;
                            i7 = positionForFieldNumber;
                            i8 = i17;
                            if (i21 != 1) {
                                i17 = i8;
                                i4 = i3;
                                i6 = i7;
                                unsafe = unsafe2;
                                break;
                            } else {
                                UnsafeUtil.putDouble(obj2, offset, ArrayDecoders.decodeDouble(bArr2, i3));
                                i14 = i3 + 8;
                                i17 = i8 | i24;
                                i16 = i7;
                                i15 = i5;
                                i13 = -1;
                                break;
                            }
                        case 1:
                            i5 = i20;
                            registers2 = registers;
                            i7 = positionForFieldNumber;
                            i8 = i17;
                            if (i21 != 5) {
                                i17 = i8;
                                i4 = i3;
                                i6 = i7;
                                unsafe = unsafe2;
                                break;
                            } else {
                                UnsafeUtil.putFloat(obj2, offset, ArrayDecoders.decodeFloat(bArr2, i3));
                                i14 = i3 + 4;
                                i17 = i8 | i24;
                                i16 = i7;
                                i15 = i5;
                                i13 = -1;
                                break;
                            }
                        case 2:
                        case 3:
                            i5 = i20;
                            registers2 = registers;
                            i7 = positionForFieldNumber;
                            i8 = i17;
                            if (i21 != 0) {
                                i17 = i8;
                                i4 = i3;
                                i6 = i7;
                                unsafe = unsafe2;
                                break;
                            } else {
                                int decodeVarint64 = ArrayDecoders.decodeVarint64(bArr2, i3, registers2);
                                unsafe2.putLong(obj, offset, registers2.long1);
                                i17 = i8 | i24;
                                i16 = i7;
                                i14 = decodeVarint64;
                                i15 = i5;
                                i13 = -1;
                                break;
                            }
                        case 4:
                        case 11:
                            i5 = i20;
                            registers2 = registers;
                            i7 = positionForFieldNumber;
                            i8 = i17;
                            if (i21 != 0) {
                                i17 = i8;
                                i4 = i3;
                                i6 = i7;
                                unsafe = unsafe2;
                                break;
                            } else {
                                i14 = ArrayDecoders.decodeVarint32(bArr2, i3, registers2);
                                unsafe2.putInt(obj2, offset, registers2.int1);
                                i17 = i8 | i24;
                                i16 = i7;
                                i15 = i5;
                                i13 = -1;
                                break;
                            }
                        case 5:
                        case 14:
                            i5 = i20;
                            registers2 = registers;
                            i7 = positionForFieldNumber;
                            i8 = i17;
                            if (i21 != 1) {
                                i17 = i8;
                                i4 = i3;
                                i6 = i7;
                                unsafe = unsafe2;
                                break;
                            } else {
                                unsafe2.putLong(obj, offset, ArrayDecoders.decodeFixed64(bArr2, i3));
                                i14 = i3 + 8;
                                i17 = i8 | i24;
                                i16 = i7;
                                i15 = i5;
                                i13 = -1;
                                break;
                            }
                        case 6:
                        case 13:
                            i5 = i20;
                            registers2 = registers;
                            i7 = positionForFieldNumber;
                            i8 = i17;
                            if (i21 != 5) {
                                i17 = i8;
                                i4 = i3;
                                i6 = i7;
                                unsafe = unsafe2;
                                break;
                            } else {
                                unsafe2.putInt(obj2, offset, ArrayDecoders.decodeFixed32(bArr2, i3));
                                i14 = i3 + 4;
                                i17 = i8 | i24;
                                i16 = i7;
                                i15 = i5;
                                i13 = -1;
                                break;
                            }
                        case 7:
                            i5 = i20;
                            registers2 = registers;
                            i7 = positionForFieldNumber;
                            i8 = i17;
                            if (i21 != 0) {
                                i17 = i8;
                                i4 = i3;
                                i6 = i7;
                                unsafe = unsafe2;
                                break;
                            } else {
                                int decodeVarint642 = ArrayDecoders.decodeVarint64(bArr2, i3, registers2);
                                UnsafeUtil.putBoolean(obj2, offset, registers2.long1 != 0);
                                i17 = i8 | i24;
                                i14 = decodeVarint642;
                                i16 = i7;
                                i15 = i5;
                                i13 = -1;
                                break;
                            }
                        case 8:
                            i5 = i20;
                            registers2 = registers;
                            i7 = positionForFieldNumber;
                            i8 = i17;
                            if (i21 != 2) {
                                i17 = i8;
                                i4 = i3;
                                i6 = i7;
                                unsafe = unsafe2;
                                break;
                            } else {
                                i14 = (536870912 & i22) == 0 ? ArrayDecoders.decodeString(bArr2, i3, registers2) : ArrayDecoders.decodeStringRequireUtf8(bArr2, i3, registers2);
                                unsafe2.putObject(obj2, offset, registers2.object1);
                                i17 = i8 | i24;
                                i16 = i7;
                                i15 = i5;
                                i13 = -1;
                                break;
                            }
                        case 9:
                            i5 = i20;
                            registers2 = registers;
                            i7 = positionForFieldNumber;
                            if (i21 != 2) {
                                i8 = i17;
                                i17 = i8;
                                i4 = i3;
                                i6 = i7;
                                unsafe = unsafe2;
                                break;
                            } else {
                                int decodeMessageField = ArrayDecoders.decodeMessageField(messageSchema.getMessageFieldSchema(i7), bArr2, i3, i12, registers2);
                                Object object = unsafe2.getObject(obj2, offset);
                                if (object == null) {
                                    unsafe2.putObject(obj2, offset, registers2.object1);
                                } else {
                                    unsafe2.putObject(obj2, offset, Internal.mergeMessage(object, registers2.object1));
                                }
                                i17 |= i24;
                                i14 = decodeMessageField;
                                i16 = i7;
                                i15 = i5;
                                i13 = -1;
                                break;
                            }
                        case 10:
                            i5 = i20;
                            registers2 = registers;
                            i7 = positionForFieldNumber;
                            if (i21 != 2) {
                                i8 = i17;
                                i17 = i8;
                                i4 = i3;
                                i6 = i7;
                                unsafe = unsafe2;
                                break;
                            } else {
                                int decodeBytes = ArrayDecoders.decodeBytes(bArr2, i3, registers2);
                                unsafe2.putObject(obj2, offset, registers2.object1);
                                i17 |= i24;
                                i14 = decodeBytes;
                                i16 = i7;
                                i15 = i5;
                                i13 = -1;
                                break;
                            }
                        case 12:
                            i5 = i20;
                            registers2 = registers;
                            i7 = positionForFieldNumber;
                            if (i21 != 0) {
                                i8 = i17;
                                i17 = i8;
                                i4 = i3;
                                i6 = i7;
                                unsafe = unsafe2;
                                break;
                            } else {
                                int decodeVarint32 = ArrayDecoders.decodeVarint32(bArr2, i3, registers2);
                                unsafe2.putInt(obj2, offset, registers2.int1);
                                i17 |= i24;
                                i14 = decodeVarint32;
                                i16 = i7;
                                i15 = i5;
                                i13 = -1;
                                break;
                            }
                        case 15:
                            i5 = i20;
                            registers2 = registers;
                            i7 = positionForFieldNumber;
                            if (i21 != 0) {
                                i8 = i17;
                                i17 = i8;
                                i4 = i3;
                                i6 = i7;
                                unsafe = unsafe2;
                                break;
                            } else {
                                i14 = ArrayDecoders.decodeVarint32(bArr2, i3, registers2);
                                unsafe2.putInt(obj2, offset, CodedInputStream.decodeZigZag32(registers2.int1));
                                i17 |= i24;
                                i16 = i7;
                                i15 = i5;
                                i13 = -1;
                                break;
                            }
                        case 16:
                            if (i21 != 0) {
                                i5 = i20;
                                i7 = positionForFieldNumber;
                                i8 = i17;
                                i17 = i8;
                                i4 = i3;
                                i6 = i7;
                                unsafe = unsafe2;
                                break;
                            } else {
                                registers2 = registers;
                                int decodeVarint643 = ArrayDecoders.decodeVarint64(bArr2, i3, registers2);
                                unsafe2.putLong(obj, offset, CodedInputStream.decodeZigZag64(registers2.long1));
                                i17 |= i24;
                                i16 = positionForFieldNumber;
                                i14 = decodeVarint643;
                                i15 = i20;
                                i13 = -1;
                                break;
                            }
                        default:
                            i5 = i20;
                            i7 = positionForFieldNumber;
                            i8 = i17;
                            i17 = i8;
                            i4 = i3;
                            i6 = i7;
                            unsafe = unsafe2;
                            break;
                    }
                } else {
                    i5 = i20;
                    int i26 = positionForFieldNumber;
                    int i27 = i17;
                    registers2 = registers;
                    if (type == 27) {
                        if (i21 == 2) {
                            Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe2.getObject(obj2, offset);
                            if (!protobufList.isModifiable()) {
                                int size = protobufList.size();
                                protobufList = protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size + size);
                                unsafe2.putObject(obj2, offset, protobufList);
                            }
                            i14 = ArrayDecoders.decodeMessageList(messageSchema.getMessageFieldSchema(i26), b, bArr, i3, i2, protobufList, registers);
                            i16 = i26;
                            i17 = i27;
                            i15 = i5;
                            i13 = -1;
                            messageSchema = this;
                        } else {
                            i9 = i18;
                            i6 = i26;
                            unsafe = unsafe2;
                            i10 = i27;
                            i11 = i3;
                            i4 = i11;
                            i17 = i10;
                            i18 = i9;
                        }
                    } else if (type <= 49) {
                        i10 = i27;
                        int i28 = i3;
                        i9 = i18;
                        i6 = i26;
                        unsafe = unsafe2;
                        i14 = parseRepeatedField(obj, bArr, i3, i2, b, i5, i21, i26, i22, type, offset, registers);
                        if (i14 != i28) {
                            messageSchema = this;
                            obj2 = obj;
                            bArr2 = bArr;
                            i12 = i2;
                            registers2 = registers;
                            i15 = i5;
                            i16 = i6;
                            i17 = i10;
                            i18 = i9;
                            unsafe2 = unsafe;
                            i13 = -1;
                        } else {
                            i4 = i14;
                            i17 = i10;
                            i18 = i9;
                        }
                    } else {
                        i9 = i18;
                        i6 = i26;
                        unsafe = unsafe2;
                        i10 = i27;
                        i11 = i3;
                        if (type != 50) {
                            i14 = parseOneofField(obj, bArr, i11, i2, b, i5, i21, i22, type, offset, i6, registers);
                            if (i14 != i11) {
                                messageSchema = this;
                                obj2 = obj;
                                bArr2 = bArr;
                                i12 = i2;
                                registers2 = registers;
                                i15 = i5;
                                i16 = i6;
                                i17 = i10;
                                i18 = i9;
                                unsafe2 = unsafe;
                                i13 = -1;
                            } else {
                                i4 = i14;
                                i17 = i10;
                                i18 = i9;
                            }
                        } else if (i21 == 2) {
                            i14 = parseMapField(obj, bArr, i11, i2, i6, offset, registers);
                            if (i14 != i11) {
                                messageSchema = this;
                                obj2 = obj;
                                bArr2 = bArr;
                                i12 = i2;
                                registers2 = registers;
                                i15 = i5;
                                i16 = i6;
                                i17 = i10;
                                i18 = i9;
                                unsafe2 = unsafe;
                                i13 = -1;
                            } else {
                                i4 = i14;
                                i17 = i10;
                                i18 = i9;
                            }
                        } else {
                            i4 = i11;
                            i17 = i10;
                            i18 = i9;
                        }
                    }
                }
            }
            i14 = ArrayDecoders.decodeUnknownField(b, bArr, i4, i2, getMutableUnknownFields(obj), registers);
            messageSchema = this;
            obj2 = obj;
            bArr2 = bArr;
            i12 = i2;
            registers2 = registers;
            i15 = i5;
            i16 = i6;
            unsafe2 = unsafe;
            i13 = -1;
        }
        int i29 = i17;
        Unsafe unsafe3 = unsafe2;
        if (i18 != 1048575) {
            unsafe3.putInt(obj, i18, i29);
        }
        if (i14 == i2) {
            return i14;
        }
        throw InvalidProtocolBufferException.parseFailure();
    }

    private int parseRepeatedField(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, ArrayDecoders.Registers registers) {
        int decodeVarint32List;
        Unsafe unsafe = UNSAFE;
        Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe.getObject(obj, j2);
        if (!protobufList.isModifiable()) {
            int size = protobufList.size();
            protobufList = protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size + size);
            unsafe.putObject(obj, j2, protobufList);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedDoubleList(bArr, i, protobufList, registers);
                }
                if (i5 == 1) {
                    return ArrayDecoders.decodeDoubleList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 19:
            case 36:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedFloatList(bArr, i, protobufList, registers);
                }
                if (i5 == 5) {
                    return ArrayDecoders.decodeFloatList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedVarint64List(bArr, i, protobufList, registers);
                }
                if (i5 == 0) {
                    return ArrayDecoders.decodeVarint64List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedVarint32List(bArr, i, protobufList, registers);
                }
                if (i5 == 0) {
                    return ArrayDecoders.decodeVarint32List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedFixed64List(bArr, i, protobufList, registers);
                }
                if (i5 == 1) {
                    return ArrayDecoders.decodeFixed64List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedFixed32List(bArr, i, protobufList, registers);
                }
                if (i5 == 5) {
                    return ArrayDecoders.decodeFixed32List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 25:
            case 42:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedBoolList(bArr, i, protobufList, registers);
                }
                if (i5 == 0) {
                    return ArrayDecoders.decodeBoolList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 26:
                if (i5 == 2) {
                    return (j & 536870912) == 0 ? ArrayDecoders.decodeStringList(i3, bArr, i, i2, protobufList, registers) : ArrayDecoders.decodeStringListRequireUtf8(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 27:
                if (i5 == 2) {
                    return ArrayDecoders.decodeMessageList(getMessageFieldSchema(i6), i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 28:
                if (i5 == 2) {
                    return ArrayDecoders.decodeBytesList(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 30:
            case 44:
                if (i5 == 2) {
                    decodeVarint32List = ArrayDecoders.decodePackedVarint32List(bArr, i, protobufList, registers);
                } else if (i5 == 0) {
                    decodeVarint32List = ArrayDecoders.decodeVarint32List(i3, bArr, i, i2, protobufList, registers);
                }
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
                UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
                if (unknownFieldSetLite == UnknownFieldSetLite.getDefaultInstance()) {
                    unknownFieldSetLite = null;
                }
                UnknownFieldSetLite unknownFieldSetLite2 = (UnknownFieldSetLite) SchemaUtil.filterUnknownEnumList(i4, protobufList, getEnumFieldVerifier(i6), unknownFieldSetLite, this.unknownFieldSchema);
                if (unknownFieldSetLite2 == null) {
                    return decodeVarint32List;
                }
                generatedMessageLite.unknownFields = unknownFieldSetLite2;
                return decodeVarint32List;
            case 33:
            case 47:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedSInt32List(bArr, i, protobufList, registers);
                }
                if (i5 == 0) {
                    return ArrayDecoders.decodeSInt32List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 34:
            case 48:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedSInt64List(bArr, i, protobufList, registers);
                }
                if (i5 == 0) {
                    return ArrayDecoders.decodeSInt64List(i3, bArr, i, i2, protobufList, registers);
                }
                break;
            case 49:
                if (i5 == 3) {
                    return ArrayDecoders.decodeGroupList(getMessageFieldSchema(i6), i3, bArr, i, i2, protobufList, registers);
                }
                break;
        }
        return i;
    }

    private int positionForFieldNumber(int i) {
        if (i < this.minFieldNumber || i > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i, 0);
    }

    private int presenceMaskAndOffsetAt(int i) {
        return this.buffer[i + 2];
    }

    private void readGroupList(Object obj, long j, Reader reader, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        reader.readGroupList(this.listFieldSchema.mutableListAt(obj, j), schema, extensionRegistryLite);
    }

    private void readMessageList(Object obj, int i, Reader reader, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        reader.readMessageList(this.listFieldSchema.mutableListAt(obj, offset(i)), schema, extensionRegistryLite);
    }

    private void readString(Object obj, int i, Reader reader) {
        if (isEnforceUtf8(i)) {
            UnsafeUtil.putObject(obj, offset(i), reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(obj, offset(i), reader.readString());
        } else {
            UnsafeUtil.putObject(obj, offset(i), reader.readBytes());
        }
    }

    private void readStringList(Object obj, int i, Reader reader) {
        if (isEnforceUtf8(i)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(obj, offset(i)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(obj, offset(i)));
        }
    }

    private static Field reflectField(Class cls, String str) {
        Field[] declaredFields;
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e) {
            for (Field field : cls.getDeclaredFields()) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private void setFieldPresent(Object obj, int i) {
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i);
        long j = 1048575 & presenceMaskAndOffsetAt;
        if (j == 1048575) {
            return;
        }
        UnsafeUtil.putInt(obj, j, (1 << (presenceMaskAndOffsetAt >>> 20)) | UnsafeUtil.getInt(obj, j));
    }

    private void setOneofPresent(Object obj, int i, int i2) {
        UnsafeUtil.putInt(obj, presenceMaskAndOffsetAt(i2) & 1048575, i);
    }

    private int slowPositionForFieldNumber(int i, int i2) {
        int length = (this.buffer.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int numberAt = numberAt(i4);
            if (i != numberAt) {
                if (i < numberAt) {
                    length = i3 - 1;
                } else {
                    i2 = i3 + 1;
                }
            } else {
                return i4;
            }
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void storeFieldData(FieldInfo fieldInfo, int[] iArr, int i, Object[] objArr) {
        int objectFieldOffset;
        int id;
        int objectFieldOffset2;
        int i2;
        OneofInfo oneof = fieldInfo.getOneof();
        if (oneof != null) {
            id = fieldInfo.getType().id() + 51;
            objectFieldOffset = (int) UnsafeUtil.objectFieldOffset(oneof.getValueField());
            objectFieldOffset2 = (int) UnsafeUtil.objectFieldOffset(oneof.getCaseField());
        } else {
            FieldType type = fieldInfo.getType();
            objectFieldOffset = (int) UnsafeUtil.objectFieldOffset(fieldInfo.getField());
            id = type.id();
            if (!type.isList() && !type.isMap()) {
                Field presenceField = fieldInfo.getPresenceField();
                objectFieldOffset2 = presenceField == null ? 1048575 : (int) UnsafeUtil.objectFieldOffset(presenceField);
                i2 = Integer.numberOfTrailingZeros(fieldInfo.getPresenceMask());
            } else if (fieldInfo.getCachedSizeField() == null) {
                objectFieldOffset2 = 0;
                i2 = 0;
            } else {
                objectFieldOffset2 = (int) UnsafeUtil.objectFieldOffset(fieldInfo.getCachedSizeField());
            }
            iArr[i] = fieldInfo.getFieldNumber();
            iArr[i + 1] = (fieldInfo.isRequired() ? PowerManager.ACQUIRE_CAUSES_WAKEUP : 0) | (!fieldInfo.isEnforceUtf8() ? PowerManager.ON_AFTER_RELEASE : 0) | (id << 20) | objectFieldOffset;
            iArr[i + 2] = objectFieldOffset2 | (i2 << 20);
            Class messageFieldClass = fieldInfo.getMessageFieldClass();
            if (fieldInfo.getMapDefaultEntry() == null) {
                int i3 = i / 3;
                int i4 = i3 + i3;
                objArr[i4] = fieldInfo.getMapDefaultEntry();
                if (messageFieldClass != null) {
                    objArr[i4 + 1] = messageFieldClass;
                    return;
                } else if (fieldInfo.getEnumVerifier() != null) {
                    objArr[i4 + 1] = fieldInfo.getEnumVerifier();
                    return;
                } else {
                    return;
                }
            } else if (messageFieldClass != null) {
                int i5 = i / 3;
                objArr[i5 + i5 + 1] = messageFieldClass;
                return;
            } else if (fieldInfo.getEnumVerifier() != null) {
                int i6 = i / 3;
                objArr[i6 + i6 + 1] = fieldInfo.getEnumVerifier();
                return;
            } else {
                return;
            }
        }
        i2 = 0;
        iArr[i] = fieldInfo.getFieldNumber();
        if (!fieldInfo.isEnforceUtf8()) {
        }
        iArr[i + 1] = (fieldInfo.isRequired() ? PowerManager.ACQUIRE_CAUSES_WAKEUP : 0) | (!fieldInfo.isEnforceUtf8() ? PowerManager.ON_AFTER_RELEASE : 0) | (id << 20) | objectFieldOffset;
        iArr[i + 2] = objectFieldOffset2 | (i2 << 20);
        Class messageFieldClass2 = fieldInfo.getMessageFieldClass();
        if (fieldInfo.getMapDefaultEntry() == null) {
        }
    }

    private static int type(int i) {
        return (i >>> 20) & 255;
    }

    private int typeAndOffsetAt(int i) {
        return this.buffer[i + 1];
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0590  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInDescendingOrder(Object obj, Writer writer) {
        Iterator it;
        Map.Entry entry;
        int length;
        writeUnknownInMessageTo(this.unknownFieldSchema, obj, writer);
        if (this.hasExtensions) {
            FieldSet extensions = this.extensionSchema.getExtensions(obj);
            if (!extensions.isEmpty()) {
                it = extensions.descendingIterator();
                entry = (Map.Entry) it.next();
                for (length = this.buffer.length - 3; length >= 0; length -= 3) {
                    int typeAndOffsetAt = typeAndOffsetAt(length);
                    int numberAt = numberAt(length);
                    while (entry != null && this.extensionSchema.extensionNumber(entry) > numberAt) {
                        this.extensionSchema.serializeExtension(writer, entry);
                        entry = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    switch (type(typeAndOffsetAt)) {
                        case 0:
                            if (isFieldPresent(obj, length)) {
                                writer.writeDouble(numberAt, doubleAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if (isFieldPresent(obj, length)) {
                                writer.writeFloat(numberAt, floatAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (isFieldPresent(obj, length)) {
                                writer.writeInt64(numberAt, longAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (isFieldPresent(obj, length)) {
                                writer.writeUInt64(numberAt, longAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if (isFieldPresent(obj, length)) {
                                writer.writeInt32(numberAt, intAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if (isFieldPresent(obj, length)) {
                                writer.writeFixed64(numberAt, longAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if (isFieldPresent(obj, length)) {
                                writer.writeFixed32(numberAt, intAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if (isFieldPresent(obj, length)) {
                                writer.writeBool(numberAt, booleanAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 8:
                            if (isFieldPresent(obj, length)) {
                                writeString(numberAt, UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            if (isFieldPresent(obj, length)) {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            } else {
                                break;
                            }
                        case 10:
                            if (isFieldPresent(obj, length)) {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 11:
                            if (isFieldPresent(obj, length)) {
                                writer.writeUInt32(numberAt, intAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            if (isFieldPresent(obj, length)) {
                                writer.writeEnum(numberAt, intAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 13:
                            if (isFieldPresent(obj, length)) {
                                writer.writeSFixed32(numberAt, intAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            if (isFieldPresent(obj, length)) {
                                writer.writeSFixed64(numberAt, longAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 15:
                            if (isFieldPresent(obj, length)) {
                                writer.writeSInt32(numberAt, intAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 16:
                            if (isFieldPresent(obj, length)) {
                                writer.writeSInt64(numberAt, longAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 17:
                            if (isFieldPresent(obj, length)) {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            } else {
                                break;
                            }
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(length));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer);
                            break;
                        case 29:
                            SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 38:
                            SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 42:
                            SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 43:
                            SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 46:
                            SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 47:
                            SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(length), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(length));
                            break;
                        case 50:
                            writeMapHelper(writer, numberAt, UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), length);
                            break;
                        case 51:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeDouble(numberAt, oneofDoubleAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 52:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeFloat(numberAt, oneofFloatAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeInt64(numberAt, oneofLongAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeUInt64(numberAt, oneofLongAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 55:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeInt32(numberAt, oneofIntAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 56:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeFixed64(numberAt, oneofLongAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 57:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeFixed32(numberAt, oneofIntAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 58:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeBool(numberAt, oneofBooleanAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 59:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writeString(numberAt, UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer);
                                break;
                            } else {
                                break;
                            }
                        case 60:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            } else {
                                break;
                            }
                        case 61:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 62:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeUInt32(numberAt, oneofIntAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 63:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeEnum(numberAt, oneofIntAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 64:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeSFixed32(numberAt, oneofIntAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 65:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeSFixed64(numberAt, oneofLongAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 66:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeSInt32(numberAt, oneofIntAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 67:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeSInt64(numberAt, oneofLongAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 68:
                            if (isOneofPresent(obj, numberAt, length)) {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), getMessageFieldSchema(length));
                                break;
                            } else {
                                break;
                            }
                    }
                }
                while (entry != null) {
                    this.extensionSchema.serializeExtension(writer, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
            }
        }
        it = null;
        entry = null;
        while (length >= 0) {
        }
        while (entry != null) {
        }
    }

    private void writeMapHelper(Writer writer, int i, Object obj, int i2) {
        if (obj != null) {
            writer.writeMap(i, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)), this.mapFieldSchema.forMapData(obj));
        }
    }

    private void writeString(int i, Object obj, Writer writer) {
        if (obj instanceof String) {
            writer.writeString(i, (String) obj);
        } else {
            writer.writeBytes(i, (ByteString) obj);
        }
    }

    private void writeUnknownInMessageTo(UnknownFieldSchema unknownFieldSchema, Object obj, Writer writer) {
        unknownFieldSchema.writeTo(unknownFieldSchema.getFromMessage(obj), writer);
    }

    @Override // com.google.protobuf.Schema
    public boolean equals(Object obj, Object obj2) {
        int length = this.buffer.length;
        for (int i = 0; i < length; i += 3) {
            if (!equals(obj, obj2, i)) {
                return false;
            }
        }
        if (!this.unknownFieldSchema.getFromMessage(obj).equals(this.unknownFieldSchema.getFromMessage(obj2))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(obj).equals(this.extensionSchema.getExtensions(obj2));
        }
        return true;
    }

    @Override // com.google.protobuf.Schema
    public int getSerializedSize(Object obj) {
        return this.proto3 ? getSerializedSizeProto3(obj) : getSerializedSizeProto2(obj);
    }

    @Override // com.google.protobuf.Schema
    public void makeImmutable(Object obj) {
        int i;
        int i2 = this.checkInitializedCount;
        while (true) {
            i = this.repeatedFieldOffsetStart;
            if (i2 >= i) {
                break;
            }
            long offset = offset(typeAndOffsetAt(this.intArray[i2]));
            Object object = UnsafeUtil.getObject(obj, offset);
            if (object != null) {
                UnsafeUtil.putObject(obj, offset, this.mapFieldSchema.toImmutable(object));
            }
            i2++;
        }
        int length = this.intArray.length;
        while (i < length) {
            this.listFieldSchema.makeImmutableListAt(obj, this.intArray[i]);
            i++;
        }
        this.unknownFieldSchema.makeImmutable(obj);
        if (this.hasExtensions) {
            this.extensionSchema.makeImmutable(obj);
        }
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(Object obj, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        if (extensionRegistryLite == null) {
            throw new NullPointerException();
        }
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, obj, reader, extensionRegistryLite);
    }

    @Override // com.google.protobuf.Schema
    public Object newInstance() {
        return this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int parseProto2Message(Object obj, byte[] bArr, int i, int i2, int i3, ArrayDecoders.Registers registers) {
        Unsafe unsafe;
        int i4;
        MessageSchema messageSchema;
        Object obj2;
        byte b;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        ArrayDecoders.Registers registers2;
        long j;
        int i11;
        int i12;
        int i13;
        int i14;
        byte[] bArr2;
        byte[] bArr3;
        int i15;
        MessageSchema messageSchema2 = this;
        Object obj3 = obj;
        byte[] bArr4 = bArr;
        int i16 = i2;
        int i17 = i3;
        ArrayDecoders.Registers registers3 = registers;
        Unsafe unsafe2 = UNSAFE;
        int i18 = i;
        int i19 = 0;
        int i20 = -1;
        int i21 = 0;
        int i22 = 0;
        int i23 = 1048575;
        while (true) {
            if (i18 < i16) {
                int i24 = i18 + 1;
                byte b2 = bArr4[i18];
                if (b2 < 0) {
                    int decodeVarint32 = ArrayDecoders.decodeVarint32(b2, bArr4, i24, registers3);
                    b = registers3.int1;
                    i24 = decodeVarint32;
                } else {
                    b = b2;
                }
                int i25 = b >>> 3;
                int i26 = b & 7;
                int positionForFieldNumber = i25 > i20 ? messageSchema2.positionForFieldNumber(i25, i21 / 3) : messageSchema2.positionForFieldNumber(i25);
                if (positionForFieldNumber == -1) {
                    i5 = i25;
                    i6 = i24;
                    i7 = b;
                    i8 = i22;
                    i9 = i23;
                    unsafe = unsafe2;
                    i10 = 0;
                } else {
                    int i27 = messageSchema2.buffer[positionForFieldNumber + 1];
                    int type = type(i27);
                    long offset = offset(i27);
                    int i28 = i24;
                    int i29 = b;
                    if (type <= 17) {
                        int i30 = messageSchema2.buffer[positionForFieldNumber + 2];
                        int i31 = 1 << (i30 >>> 20);
                        int i32 = i30 & 1048575;
                        if (i32 != i23) {
                            if (i23 != 1048575) {
                                j = offset;
                                unsafe2.putInt(obj3, i23, i22);
                            } else {
                                j = offset;
                            }
                            i22 = unsafe2.getInt(obj3, i32);
                            i11 = i32;
                        } else {
                            j = offset;
                            i11 = i23;
                        }
                        int i33 = i22;
                        switch (type) {
                            case 0:
                                i5 = i25;
                                i12 = positionForFieldNumber;
                                i13 = i28;
                                i14 = i29;
                                long j2 = j;
                                if (i26 == 1) {
                                    UnsafeUtil.putDouble(obj3, j2, ArrayDecoders.decodeDouble(bArr, i13));
                                    i18 = i13 + 8;
                                    i22 = i33 | i31;
                                    bArr4 = bArr;
                                    i21 = i12;
                                    i23 = i11;
                                    i19 = i14;
                                    i20 = i5;
                                    i16 = i2;
                                    i17 = i3;
                                    break;
                                } else {
                                    i8 = i33;
                                    i10 = i12;
                                    unsafe = unsafe2;
                                    i9 = i11;
                                    i6 = i13;
                                    i7 = i14;
                                    break;
                                }
                            case 1:
                                i5 = i25;
                                i12 = positionForFieldNumber;
                                i13 = i28;
                                i14 = i29;
                                long j3 = j;
                                if (i26 == 5) {
                                    UnsafeUtil.putFloat(obj3, j3, ArrayDecoders.decodeFloat(bArr, i13));
                                    i18 = i13 + 4;
                                    i22 = i33 | i31;
                                    bArr4 = bArr;
                                    i21 = i12;
                                    i23 = i11;
                                    i19 = i14;
                                    i20 = i5;
                                    i16 = i2;
                                    i17 = i3;
                                    break;
                                } else {
                                    i8 = i33;
                                    i10 = i12;
                                    unsafe = unsafe2;
                                    i9 = i11;
                                    i6 = i13;
                                    i7 = i14;
                                    break;
                                }
                            case 2:
                            case 3:
                                i5 = i25;
                                i12 = positionForFieldNumber;
                                i13 = i28;
                                i14 = i29;
                                long j4 = j;
                                if (i26 == 0) {
                                    int decodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i13, registers3);
                                    bArr4 = bArr;
                                    unsafe2.putLong(obj, j4, registers3.long1);
                                    i22 = i33 | i31;
                                    i21 = i12;
                                    i18 = decodeVarint64;
                                    i23 = i11;
                                    i19 = i14;
                                    i20 = i5;
                                    i16 = i2;
                                    i17 = i3;
                                    break;
                                } else {
                                    i8 = i33;
                                    i10 = i12;
                                    unsafe = unsafe2;
                                    i9 = i11;
                                    i6 = i13;
                                    i7 = i14;
                                    break;
                                }
                            case 4:
                            case 11:
                                i5 = i25;
                                i12 = positionForFieldNumber;
                                i13 = i28;
                                i14 = i29;
                                long j5 = j;
                                if (i26 == 0) {
                                    i18 = ArrayDecoders.decodeVarint32(bArr, i13, registers3);
                                    unsafe2.putInt(obj3, j5, registers3.int1);
                                    i22 = i33 | i31;
                                    bArr4 = bArr;
                                    i21 = i12;
                                    i23 = i11;
                                    i19 = i14;
                                    i20 = i5;
                                    i16 = i2;
                                    i17 = i3;
                                    break;
                                } else {
                                    i8 = i33;
                                    i10 = i12;
                                    unsafe = unsafe2;
                                    i9 = i11;
                                    i6 = i13;
                                    i7 = i14;
                                    break;
                                }
                            case 5:
                            case 14:
                                i5 = i25;
                                i12 = positionForFieldNumber;
                                i13 = i28;
                                i14 = i29;
                                long j6 = j;
                                if (i26 == 1) {
                                    unsafe2.putLong(obj, j6, ArrayDecoders.decodeFixed64(bArr, i13));
                                    i18 = i13 + 8;
                                    i22 = i33 | i31;
                                    i21 = i12;
                                    bArr4 = bArr;
                                    i23 = i11;
                                    i19 = i14;
                                    i20 = i5;
                                    i16 = i2;
                                    i17 = i3;
                                    break;
                                } else {
                                    i8 = i33;
                                    i10 = i12;
                                    unsafe = unsafe2;
                                    i9 = i11;
                                    i6 = i13;
                                    i7 = i14;
                                    break;
                                }
                            case 6:
                            case 13:
                                i5 = i25;
                                i12 = positionForFieldNumber;
                                i13 = i28;
                                i14 = i29;
                                long j7 = j;
                                if (i26 == 5) {
                                    unsafe2.putInt(obj3, j7, ArrayDecoders.decodeFixed32(bArr, i13));
                                    i18 = i13 + 4;
                                    i22 = i33 | i31;
                                    bArr4 = bArr;
                                    i21 = i12;
                                    i23 = i11;
                                    i19 = i14;
                                    i20 = i5;
                                    i16 = i2;
                                    i17 = i3;
                                    break;
                                } else {
                                    i8 = i33;
                                    i10 = i12;
                                    unsafe = unsafe2;
                                    i9 = i11;
                                    i6 = i13;
                                    i7 = i14;
                                    break;
                                }
                            case 7:
                                i5 = i25;
                                i12 = positionForFieldNumber;
                                i13 = i28;
                                i14 = i29;
                                long j8 = j;
                                if (i26 == 0) {
                                    i18 = ArrayDecoders.decodeVarint64(bArr, i13, registers3);
                                    UnsafeUtil.putBoolean(obj3, j8, registers3.long1 != 0);
                                    i22 = i33 | i31;
                                    bArr4 = bArr;
                                    i21 = i12;
                                    i23 = i11;
                                    i20 = i5;
                                    i17 = i3;
                                    i16 = i2;
                                    i19 = i14;
                                    break;
                                } else {
                                    i8 = i33;
                                    i10 = i12;
                                    unsafe = unsafe2;
                                    i9 = i11;
                                    i6 = i13;
                                    i7 = i14;
                                    break;
                                }
                            case 8:
                                bArr2 = bArr;
                                i5 = i25;
                                i12 = positionForFieldNumber;
                                i13 = i28;
                                i14 = i29;
                                long j9 = j;
                                if (i26 == 2) {
                                    i18 = (536870912 & i27) == 0 ? ArrayDecoders.decodeString(bArr2, i13, registers3) : ArrayDecoders.decodeStringRequireUtf8(bArr2, i13, registers3);
                                    unsafe2.putObject(obj3, j9, registers3.object1);
                                    int i34 = i33 | i31;
                                    bArr4 = bArr2;
                                    i21 = i12;
                                    i23 = i11;
                                    i17 = i3;
                                    i22 = i34;
                                    i20 = i5;
                                    i16 = i2;
                                    i19 = i14;
                                    break;
                                } else {
                                    i8 = i33;
                                    i10 = i12;
                                    unsafe = unsafe2;
                                    i9 = i11;
                                    i6 = i13;
                                    i7 = i14;
                                    break;
                                }
                            case 9:
                                bArr2 = bArr;
                                i5 = i25;
                                i12 = positionForFieldNumber;
                                i13 = i28;
                                i14 = i29;
                                long j10 = j;
                                if (i26 == 2) {
                                    i18 = ArrayDecoders.decodeMessageField(messageSchema2.getMessageFieldSchema(i12), bArr2, i13, i2, registers3);
                                    if ((i33 & i31) == 0) {
                                        unsafe2.putObject(obj3, j10, registers3.object1);
                                    } else {
                                        unsafe2.putObject(obj3, j10, Internal.mergeMessage(unsafe2.getObject(obj3, j10), registers3.object1));
                                    }
                                    int i35 = i33 | i31;
                                    bArr4 = bArr2;
                                    i21 = i12;
                                    i23 = i11;
                                    i17 = i3;
                                    i22 = i35;
                                    i20 = i5;
                                    i16 = i2;
                                    i19 = i14;
                                    break;
                                } else {
                                    i8 = i33;
                                    i10 = i12;
                                    unsafe = unsafe2;
                                    i9 = i11;
                                    i6 = i13;
                                    i7 = i14;
                                    break;
                                }
                            case 10:
                                bArr3 = bArr;
                                i5 = i25;
                                i12 = positionForFieldNumber;
                                i13 = i28;
                                i14 = i29;
                                long j11 = j;
                                if (i26 == 2) {
                                    i18 = ArrayDecoders.decodeBytes(bArr3, i13, registers3);
                                    unsafe2.putObject(obj3, j11, registers3.object1);
                                    int i36 = i33 | i31;
                                    bArr4 = bArr3;
                                    i21 = i12;
                                    i23 = i11;
                                    i20 = i5;
                                    i17 = i3;
                                    i22 = i36;
                                    i19 = i14;
                                    i16 = i2;
                                    break;
                                } else {
                                    i8 = i33;
                                    i10 = i12;
                                    unsafe = unsafe2;
                                    i9 = i11;
                                    i6 = i13;
                                    i7 = i14;
                                    break;
                                }
                            case 12:
                                bArr3 = bArr;
                                i5 = i25;
                                i12 = positionForFieldNumber;
                                i13 = i28;
                                i14 = i29;
                                long j12 = j;
                                if (i26 == 0) {
                                    i18 = ArrayDecoders.decodeVarint32(bArr3, i13, registers3);
                                    int i37 = registers3.int1;
                                    Internal.EnumVerifier enumFieldVerifier = messageSchema2.getEnumFieldVerifier(i12);
                                    if (enumFieldVerifier != null && !enumFieldVerifier.isInRange(i37)) {
                                        getMutableUnknownFields(obj).storeField(i14, Long.valueOf(i37));
                                        bArr4 = bArr3;
                                        i22 = i33;
                                        i21 = i12;
                                        i23 = i11;
                                        i19 = i14;
                                        i20 = i5;
                                        i16 = i2;
                                        i17 = i3;
                                        break;
                                    } else {
                                        unsafe2.putInt(obj3, j12, i37);
                                        int i38 = i33 | i31;
                                        bArr4 = bArr3;
                                        i21 = i12;
                                        i23 = i11;
                                        i20 = i5;
                                        i17 = i3;
                                        i22 = i38;
                                        i19 = i14;
                                        i16 = i2;
                                        break;
                                    }
                                } else {
                                    i8 = i33;
                                    i10 = i12;
                                    unsafe = unsafe2;
                                    i9 = i11;
                                    i6 = i13;
                                    i7 = i14;
                                    break;
                                }
                                break;
                            case 15:
                                bArr3 = bArr;
                                i5 = i25;
                                i12 = positionForFieldNumber;
                                i13 = i28;
                                i14 = i29;
                                long j13 = j;
                                if (i26 == 0) {
                                    i18 = ArrayDecoders.decodeVarint32(bArr3, i13, registers3);
                                    unsafe2.putInt(obj3, j13, CodedInputStream.decodeZigZag32(registers3.int1));
                                    int i39 = i33 | i31;
                                    bArr4 = bArr3;
                                    i21 = i12;
                                    i23 = i11;
                                    i20 = i5;
                                    i17 = i3;
                                    i22 = i39;
                                    i19 = i14;
                                    i16 = i2;
                                    break;
                                } else {
                                    i8 = i33;
                                    i10 = i12;
                                    unsafe = unsafe2;
                                    i9 = i11;
                                    i6 = i13;
                                    i7 = i14;
                                    break;
                                }
                            case 16:
                                i5 = i25;
                                i12 = positionForFieldNumber;
                                i13 = i28;
                                long j14 = j;
                                if (i26 == 0) {
                                    int decodeVarint642 = ArrayDecoders.decodeVarint64(bArr, i13, registers3);
                                    unsafe2.putLong(obj, j14, CodedInputStream.decodeZigZag64(registers3.long1));
                                    i22 = i33 | i31;
                                    i21 = i12;
                                    i23 = i11;
                                    i18 = decodeVarint642;
                                    i19 = i29;
                                    i20 = i5;
                                    i16 = i2;
                                    i17 = i3;
                                    bArr4 = bArr;
                                    break;
                                } else {
                                    i14 = i29;
                                    i8 = i33;
                                    i10 = i12;
                                    unsafe = unsafe2;
                                    i9 = i11;
                                    i6 = i13;
                                    i7 = i14;
                                    break;
                                }
                            case 17:
                                if (i26 == 3) {
                                    int i40 = positionForFieldNumber;
                                    i18 = ArrayDecoders.decodeGroupField(messageSchema2.getMessageFieldSchema(positionForFieldNumber), bArr, i28, i2, (i25 << 3) | 4, registers);
                                    if ((i33 & i31) == 0) {
                                        unsafe2.putObject(obj3, j, registers3.object1);
                                    } else {
                                        long j15 = j;
                                        unsafe2.putObject(obj3, j15, Internal.mergeMessage(unsafe2.getObject(obj3, j15), registers3.object1));
                                    }
                                    i22 = i33 | i31;
                                    bArr4 = bArr;
                                    i16 = i2;
                                    i21 = i40;
                                    i19 = i29;
                                    i23 = i11;
                                    i20 = i25;
                                    i17 = i3;
                                    break;
                                } else {
                                    i5 = i25;
                                    i12 = positionForFieldNumber;
                                    i13 = i28;
                                    i14 = i29;
                                    i8 = i33;
                                    i10 = i12;
                                    unsafe = unsafe2;
                                    i9 = i11;
                                    i6 = i13;
                                    i7 = i14;
                                    break;
                                }
                            default:
                                i5 = i25;
                                i12 = positionForFieldNumber;
                                i13 = i28;
                                i14 = i29;
                                i8 = i33;
                                i10 = i12;
                                unsafe = unsafe2;
                                i9 = i11;
                                i6 = i13;
                                i7 = i14;
                                break;
                        }
                    } else {
                        i5 = i25;
                        int i41 = positionForFieldNumber;
                        if (type != 27) {
                            i8 = i22;
                            i9 = i23;
                            if (type <= 49) {
                                unsafe = unsafe2;
                                i10 = i41;
                                i7 = i29;
                                i18 = parseRepeatedField(obj, bArr, i28, i2, i29, i5, i26, i41, i27, type, offset, registers);
                                if (i18 != i28) {
                                    messageSchema2 = this;
                                    obj3 = obj;
                                    bArr4 = bArr;
                                    i16 = i2;
                                    i17 = i3;
                                    registers3 = registers;
                                    i20 = i5;
                                    i21 = i10;
                                    i22 = i8;
                                    i23 = i9;
                                    i19 = i7;
                                    unsafe2 = unsafe;
                                } else {
                                    i6 = i18;
                                }
                            } else {
                                unsafe = unsafe2;
                                i10 = i41;
                                i15 = i28;
                                i7 = i29;
                                if (type != 50) {
                                    i18 = parseOneofField(obj, bArr, i15, i2, i7, i5, i26, i27, type, offset, i10, registers);
                                    if (i18 != i15) {
                                        messageSchema2 = this;
                                        obj3 = obj;
                                        bArr4 = bArr;
                                        i16 = i2;
                                        i17 = i3;
                                        registers3 = registers;
                                        i20 = i5;
                                        i21 = i10;
                                        i22 = i8;
                                        i23 = i9;
                                        i19 = i7;
                                        unsafe2 = unsafe;
                                    } else {
                                        i6 = i18;
                                    }
                                } else if (i26 == 2) {
                                    i18 = parseMapField(obj, bArr, i15, i2, i10, offset, registers);
                                    if (i18 != i15) {
                                        messageSchema2 = this;
                                        obj3 = obj;
                                        bArr4 = bArr;
                                        i16 = i2;
                                        i17 = i3;
                                        registers3 = registers;
                                        i20 = i5;
                                        i21 = i10;
                                        i22 = i8;
                                        i23 = i9;
                                        i19 = i7;
                                        unsafe2 = unsafe;
                                    } else {
                                        i6 = i18;
                                    }
                                } else {
                                    i6 = i15;
                                }
                            }
                        } else if (i26 == 2) {
                            Internal.ProtobufList protobufList = (Internal.ProtobufList) unsafe2.getObject(obj3, offset);
                            if (!protobufList.isModifiable()) {
                                int size = protobufList.size();
                                protobufList = protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size + size);
                                unsafe2.putObject(obj3, offset, protobufList);
                            }
                            i19 = i29;
                            i18 = ArrayDecoders.decodeMessageList(messageSchema2.getMessageFieldSchema(i41), i19, bArr, i28, i2, protobufList, registers);
                            bArr4 = bArr;
                            i21 = i41;
                            i20 = i5;
                            i22 = i22;
                            i23 = i23;
                            i16 = i2;
                            i17 = i3;
                        } else {
                            i8 = i22;
                            i9 = i23;
                            unsafe = unsafe2;
                            i10 = i41;
                            i15 = i28;
                            i7 = i29;
                            i6 = i15;
                        }
                    }
                }
                int i42 = i7;
                if (i42 != i3 || i3 == 0) {
                    if (this.hasExtensions) {
                        registers2 = registers;
                        if (registers2.extensionRegistry != ExtensionRegistryLite.getEmptyRegistry()) {
                            i18 = ArrayDecoders.decodeExtensionOrUnknownField(i42, bArr, i6, i2, obj, this.defaultInstance, this.unknownFieldSchema, registers);
                            obj3 = obj;
                            bArr4 = bArr;
                            i16 = i2;
                            i19 = i42;
                            messageSchema2 = this;
                            registers3 = registers2;
                            i20 = i5;
                            i21 = i10;
                            i22 = i8;
                            i23 = i9;
                            i17 = i3;
                            unsafe2 = unsafe;
                        }
                    } else {
                        registers2 = registers;
                    }
                    i18 = ArrayDecoders.decodeUnknownField(i42, bArr, i6, i2, getMutableUnknownFields(obj), registers);
                    obj3 = obj;
                    bArr4 = bArr;
                    i16 = i2;
                    i19 = i42;
                    messageSchema2 = this;
                    registers3 = registers2;
                    i20 = i5;
                    i21 = i10;
                    i22 = i8;
                    i23 = i9;
                    i17 = i3;
                    unsafe2 = unsafe;
                } else {
                    messageSchema = this;
                    i4 = i3;
                    i18 = i6;
                    i19 = i42;
                    i22 = i8;
                    i23 = i9;
                }
            } else {
                unsafe = unsafe2;
                i4 = i17;
                messageSchema = messageSchema2;
            }
        }
        if (i23 != 1048575) {
            obj2 = obj;
            unsafe.putInt(obj2, i23, i22);
        } else {
            obj2 = obj;
        }
        UnknownFieldSetLite unknownFieldSetLite = null;
        for (int i43 = messageSchema.checkInitializedCount; i43 < messageSchema.repeatedFieldOffsetStart; i43++) {
            unknownFieldSetLite = (UnknownFieldSetLite) messageSchema.filterMapUnknownEnumValues(obj2, messageSchema.intArray[i43], unknownFieldSetLite, messageSchema.unknownFieldSchema);
        }
        if (unknownFieldSetLite != null) {
            messageSchema.unknownFieldSchema.setBuilderToMessage(obj2, unknownFieldSetLite);
        }
        if (i4 == 0) {
            if (i18 != i2) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } else if (i18 > i2 || i19 != i4) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return i18;
    }

    @Override // com.google.protobuf.Schema
    public void writeTo(Object obj, Writer writer) {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(obj, writer);
        } else if (this.proto3) {
            writeFieldsInAscendingOrderProto3(obj, writer);
        } else {
            writeFieldsInAscendingOrderProto2(obj, writer);
        }
    }

    @Override // com.google.protobuf.Schema
    public int hashCode(Object obj) {
        int length = this.buffer.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int typeAndOffsetAt = typeAndOffsetAt(i2);
            int numberAt = numberAt(i2);
            long offset = offset(typeAndOffsetAt);
            switch (type(typeAndOffsetAt)) {
                case 0:
                    i = (i * 53) + Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(obj, offset)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(UnsafeUtil.getFloat(obj, offset));
                    break;
                case 2:
                    i = (i * 53) + Internal.hashLong(UnsafeUtil.getLong(obj, offset));
                    break;
                case 3:
                    i = (i * 53) + Internal.hashLong(UnsafeUtil.getLong(obj, offset));
                    break;
                case 4:
                    i = (i * 53) + UnsafeUtil.getInt(obj, offset);
                    break;
                case 5:
                    i = (i * 53) + Internal.hashLong(UnsafeUtil.getLong(obj, offset));
                    break;
                case 6:
                    i = (i * 53) + UnsafeUtil.getInt(obj, offset);
                    break;
                case 7:
                    i = (i * 53) + Internal.hashBoolean(UnsafeUtil.getBoolean(obj, offset));
                    break;
                case 8:
                    i = (i * 53) + ((String) UnsafeUtil.getObject(obj, offset)).hashCode();
                    break;
                case 9:
                    Object object = UnsafeUtil.getObject(obj, offset);
                    i = (i * 53) + (object != null ? object.hashCode() : 37);
                    break;
                case 10:
                    i = (i * 53) + UnsafeUtil.getObject(obj, offset).hashCode();
                    break;
                case 11:
                    i = (i * 53) + UnsafeUtil.getInt(obj, offset);
                    break;
                case 12:
                    i = (i * 53) + UnsafeUtil.getInt(obj, offset);
                    break;
                case 13:
                    i = (i * 53) + UnsafeUtil.getInt(obj, offset);
                    break;
                case 14:
                    i = (i * 53) + Internal.hashLong(UnsafeUtil.getLong(obj, offset));
                    break;
                case 15:
                    i = (i * 53) + UnsafeUtil.getInt(obj, offset);
                    break;
                case 16:
                    i = (i * 53) + Internal.hashLong(UnsafeUtil.getLong(obj, offset));
                    break;
                case 17:
                    Object object2 = UnsafeUtil.getObject(obj, offset);
                    i = (i * 53) + (object2 != null ? object2.hashCode() : 37);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = (i * 53) + UnsafeUtil.getObject(obj, offset).hashCode();
                    break;
                case 50:
                    i = (i * 53) + UnsafeUtil.getObject(obj, offset).hashCode();
                    break;
                case 51:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(obj, offset)));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + Float.floatToIntBits(oneofFloatAt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + Internal.hashLong(oneofLongAt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + Internal.hashLong(oneofLongAt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + oneofIntAt(obj, offset);
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + Internal.hashLong(oneofLongAt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + oneofIntAt(obj, offset);
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + Internal.hashBoolean(oneofBooleanAt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + ((String) UnsafeUtil.getObject(obj, offset)).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + UnsafeUtil.getObject(obj, offset).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + UnsafeUtil.getObject(obj, offset).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + oneofIntAt(obj, offset);
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + oneofIntAt(obj, offset);
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + oneofIntAt(obj, offset);
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + Internal.hashLong(oneofLongAt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + oneofIntAt(obj, offset);
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + Internal.hashLong(oneofLongAt(obj, offset));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(obj, numberAt, i2)) {
                        i = (i * 53) + UnsafeUtil.getObject(obj, offset).hashCode();
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + this.unknownFieldSchema.getFromMessage(obj).hashCode();
        if (this.hasExtensions) {
            return (hashCode * 53) + this.extensionSchema.getExtensions(obj).hashCode();
        }
        return hashCode;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x007a, code lost:
        r1 = r16.checkInitializedCount;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x007e, code lost:
        if (r1 >= r16.repeatedFieldOffsetStart) goto L339;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0080, code lost:
        r14 = filterMapUnknownEnumValues(r19, r16.intArray[r1], r14, r17);
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x008b, code lost:
        if (r14 == null) goto L343;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008d, code lost:
        r17.setBuilderToMessage(r19, r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0090, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void mergeFromHelper(UnknownFieldSchema unknownFieldSchema, ExtensionSchema extensionSchema, Object obj, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        Throwable th;
        Object obj2 = null;
        FieldSet fieldSet = null;
        while (true) {
            try {
                int fieldNumber = reader.getFieldNumber();
                int positionForFieldNumber = positionForFieldNumber(fieldNumber);
                if (positionForFieldNumber >= 0) {
                    int typeAndOffsetAt = typeAndOffsetAt(positionForFieldNumber);
                    try {
                        switch (type(typeAndOffsetAt)) {
                            case 0:
                                UnsafeUtil.putDouble(obj, offset(typeAndOffsetAt), reader.readDouble());
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 1:
                                UnsafeUtil.putFloat(obj, offset(typeAndOffsetAt), reader.readFloat());
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 2:
                                UnsafeUtil.putLong(obj, offset(typeAndOffsetAt), reader.readInt64());
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 3:
                                UnsafeUtil.putLong(obj, offset(typeAndOffsetAt), reader.readUInt64());
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 4:
                                UnsafeUtil.putInt(obj, offset(typeAndOffsetAt), reader.readInt32());
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 5:
                                UnsafeUtil.putLong(obj, offset(typeAndOffsetAt), reader.readFixed64());
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 6:
                                UnsafeUtil.putInt(obj, offset(typeAndOffsetAt), reader.readFixed32());
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 7:
                                UnsafeUtil.putBoolean(obj, offset(typeAndOffsetAt), reader.readBool());
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 8:
                                readString(obj, typeAndOffsetAt, reader);
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 9:
                                if (isFieldPresent(obj, positionForFieldNumber)) {
                                    UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Internal.mergeMessage(UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite)));
                                    break;
                                } else {
                                    UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite));
                                    setFieldPresent(obj, positionForFieldNumber);
                                    break;
                                }
                            case 10:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), reader.readBytes());
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 11:
                                UnsafeUtil.putInt(obj, offset(typeAndOffsetAt), reader.readUInt32());
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 12:
                                int readEnum = reader.readEnum();
                                Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(positionForFieldNumber);
                                if (enumFieldVerifier != null && !enumFieldVerifier.isInRange(readEnum)) {
                                    obj2 = SchemaUtil.storeUnknownEnum(fieldNumber, readEnum, obj2, unknownFieldSchema);
                                    break;
                                }
                                UnsafeUtil.putInt(obj, offset(typeAndOffsetAt), readEnum);
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 13:
                                UnsafeUtil.putInt(obj, offset(typeAndOffsetAt), reader.readSFixed32());
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 14:
                                UnsafeUtil.putLong(obj, offset(typeAndOffsetAt), reader.readSFixed64());
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 15:
                                UnsafeUtil.putInt(obj, offset(typeAndOffsetAt), reader.readSInt32());
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 16:
                                UnsafeUtil.putLong(obj, offset(typeAndOffsetAt), reader.readSInt64());
                                setFieldPresent(obj, positionForFieldNumber);
                                break;
                            case 17:
                                if (isFieldPresent(obj, positionForFieldNumber)) {
                                    UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Internal.mergeMessage(UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), reader.readGroupBySchemaWithCheck(getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite)));
                                    break;
                                } else {
                                    UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), reader.readGroupBySchemaWithCheck(getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite));
                                    setFieldPresent(obj, positionForFieldNumber);
                                    break;
                                }
                            case 18:
                                reader.readDoubleList(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 19:
                                reader.readFloatList(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 20:
                                reader.readInt64List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 21:
                                reader.readUInt64List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 22:
                                reader.readInt32List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 23:
                                reader.readFixed64List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 24:
                                reader.readFixed32List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 25:
                                reader.readBoolList(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 26:
                                readStringList(obj, typeAndOffsetAt, reader);
                                break;
                            case 27:
                                readMessageList(obj, typeAndOffsetAt, reader, getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite);
                                break;
                            case 28:
                                reader.readBytesList(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 29:
                                reader.readUInt32List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 30:
                                List mutableListAt = this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt));
                                reader.readEnumList(mutableListAt);
                                obj2 = SchemaUtil.filterUnknownEnumList(fieldNumber, mutableListAt, getEnumFieldVerifier(positionForFieldNumber), obj2, unknownFieldSchema);
                                break;
                            case 31:
                                reader.readSFixed32List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 32:
                                reader.readSFixed64List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 33:
                                reader.readSInt32List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 34:
                                reader.readSInt64List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 35:
                                reader.readDoubleList(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 36:
                                reader.readFloatList(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 37:
                                reader.readInt64List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 38:
                                reader.readUInt64List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 39:
                                reader.readInt32List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 40:
                                reader.readFixed64List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 41:
                                reader.readFixed32List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 42:
                                reader.readBoolList(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 43:
                                reader.readUInt32List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 44:
                                List mutableListAt2 = this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt));
                                reader.readEnumList(mutableListAt2);
                                obj2 = SchemaUtil.filterUnknownEnumList(fieldNumber, mutableListAt2, getEnumFieldVerifier(positionForFieldNumber), obj2, unknownFieldSchema);
                                break;
                            case 45:
                                reader.readSFixed32List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 46:
                                reader.readSFixed64List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 47:
                                reader.readSInt32List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 48:
                                reader.readSInt64List(this.listFieldSchema.mutableListAt(obj, offset(typeAndOffsetAt)));
                                break;
                            case 49:
                                readGroupList(obj, offset(typeAndOffsetAt), reader, getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite);
                                break;
                            case 50:
                                mergeMap(obj, positionForFieldNumber, getMapFieldDefaultEntry(positionForFieldNumber), extensionRegistryLite, reader);
                                break;
                            case 51:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Double.valueOf(reader.readDouble()));
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 52:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Float.valueOf(reader.readFloat()));
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 53:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Long.valueOf(reader.readInt64()));
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 54:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Long.valueOf(reader.readUInt64()));
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 55:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Integer.valueOf(reader.readInt32()));
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 56:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Long.valueOf(reader.readFixed64()));
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 57:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Integer.valueOf(reader.readFixed32()));
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 58:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Boolean.valueOf(reader.readBool()));
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 59:
                                readString(obj, typeAndOffsetAt, reader);
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 60:
                                if (isOneofPresent(obj, fieldNumber, positionForFieldNumber)) {
                                    UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Internal.mergeMessage(UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite)));
                                } else {
                                    UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite));
                                    setFieldPresent(obj, positionForFieldNumber);
                                }
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 61:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), reader.readBytes());
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 62:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Integer.valueOf(reader.readUInt32()));
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 63:
                                int readEnum2 = reader.readEnum();
                                Internal.EnumVerifier enumFieldVerifier2 = getEnumFieldVerifier(positionForFieldNumber);
                                if (enumFieldVerifier2 != null && !enumFieldVerifier2.isInRange(readEnum2)) {
                                    obj2 = SchemaUtil.storeUnknownEnum(fieldNumber, readEnum2, obj2, unknownFieldSchema);
                                    break;
                                }
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Integer.valueOf(readEnum2));
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 64:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Integer.valueOf(reader.readSFixed32()));
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 65:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Long.valueOf(reader.readSFixed64()));
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 66:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Integer.valueOf(reader.readSInt32()));
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 67:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), Long.valueOf(reader.readSInt64()));
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            case 68:
                                UnsafeUtil.putObject(obj, offset(typeAndOffsetAt), reader.readGroupBySchemaWithCheck(getMessageFieldSchema(positionForFieldNumber), extensionRegistryLite));
                                setOneofPresent(obj, fieldNumber, positionForFieldNumber);
                                break;
                            default:
                                if (obj2 == null) {
                                    obj2 = unknownFieldSchema.newBuilder();
                                }
                                if (!unknownFieldSchema.mergeOneFieldFrom(obj2, reader)) {
                                    for (int i = this.checkInitializedCount; i < this.repeatedFieldOffsetStart; i++) {
                                        obj2 = filterMapUnknownEnumValues(obj, this.intArray[i], obj2, unknownFieldSchema);
                                    }
                                    if (obj2 != null) {
                                        unknownFieldSchema.setBuilderToMessage(obj, obj2);
                                        return;
                                    }
                                    return;
                                }
                                break;
                        }
                    } catch (InvalidProtocolBufferException.InvalidWireTypeException e) {
                        if (!unknownFieldSchema.shouldDiscardUnknownFields(reader)) {
                            if (obj2 == null) {
                                obj2 = unknownFieldSchema.getBuilderFromMessage(obj);
                            }
                            if (!unknownFieldSchema.mergeOneFieldFrom(obj2, reader)) {
                                for (int i2 = this.checkInitializedCount; i2 < this.repeatedFieldOffsetStart; i2++) {
                                    obj2 = filterMapUnknownEnumValues(obj, this.intArray[i2], obj2, unknownFieldSchema);
                                }
                                if (obj2 != null) {
                                    unknownFieldSchema.setBuilderToMessage(obj, obj2);
                                    return;
                                }
                                return;
                            }
                        } else if (!reader.skipField()) {
                            for (int i3 = this.checkInitializedCount; i3 < this.repeatedFieldOffsetStart; i3++) {
                                obj2 = filterMapUnknownEnumValues(obj, this.intArray[i3], obj2, unknownFieldSchema);
                            }
                            if (obj2 != null) {
                                unknownFieldSchema.setBuilderToMessage(obj, obj2);
                                return;
                            }
                            return;
                        }
                    }
                } else if (fieldNumber == Integer.MAX_VALUE) {
                    for (int i4 = this.checkInitializedCount; i4 < this.repeatedFieldOffsetStart; i4++) {
                        obj2 = filterMapUnknownEnumValues(obj, this.intArray[i4], obj2, unknownFieldSchema);
                    }
                    if (obj2 != null) {
                        unknownFieldSchema.setBuilderToMessage(obj, obj2);
                        return;
                    }
                    return;
                } else {
                    Object findExtensionByNumber = !this.hasExtensions ? null : extensionSchema.findExtensionByNumber(extensionRegistryLite, this.defaultInstance, fieldNumber);
                    if (findExtensionByNumber != null) {
                        if (fieldSet == null) {
                            fieldSet = extensionSchema.getMutableExtensions(obj);
                        }
                        obj2 = extensionSchema.parseExtension(reader, findExtensionByNumber, extensionRegistryLite, fieldSet, obj2, unknownFieldSchema);
                    } else if (!unknownFieldSchema.shouldDiscardUnknownFields(reader)) {
                        if (obj2 == null) {
                            obj2 = unknownFieldSchema.getBuilderFromMessage(obj);
                        }
                        try {
                            if (!unknownFieldSchema.mergeOneFieldFrom(obj2, reader)) {
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } else if (!reader.skipField()) {
                    }
                }
                th = th2;
            } catch (Throwable th3) {
                th = th3;
            }
            for (int i5 = this.checkInitializedCount; i5 < this.repeatedFieldOffsetStart; i5++) {
                obj2 = filterMapUnknownEnumValues(obj, this.intArray[i5], obj2, unknownFieldSchema);
            }
            if (obj2 != null) {
                unknownFieldSchema.setBuilderToMessage(obj, obj2);
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x04f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInAscendingOrderProto2(Object obj, Writer writer) {
        Iterator it;
        Map.Entry entry;
        int length;
        int i;
        int i2;
        if (this.hasExtensions) {
            FieldSet extensions = this.extensionSchema.getExtensions(obj);
            if (!extensions.isEmpty()) {
                it = extensions.iterator();
                entry = (Map.Entry) it.next();
                length = this.buffer.length;
                Unsafe unsafe = UNSAFE;
                int i3 = 1048575;
                i = 0;
                int i4 = 1048575;
                int i5 = 0;
                while (i < length) {
                    int typeAndOffsetAt = typeAndOffsetAt(i);
                    int numberAt = numberAt(i);
                    int type = type(typeAndOffsetAt);
                    if (type <= 17) {
                        int i6 = this.buffer[i + 2];
                        int i7 = i6 & i3;
                        if (i7 != i4) {
                            i5 = unsafe.getInt(obj, i7);
                            i4 = i7;
                        }
                        i2 = 1 << (i6 >>> 20);
                    } else {
                        i2 = 0;
                    }
                    while (entry != null && this.extensionSchema.extensionNumber(entry) <= numberAt) {
                        this.extensionSchema.serializeExtension(writer, entry);
                        entry = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    long offset = offset(typeAndOffsetAt);
                    switch (type) {
                        case 0:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeDouble(numberAt, doubleAt(obj, offset));
                                break;
                            }
                        case 1:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeFloat(numberAt, floatAt(obj, offset));
                                break;
                            }
                        case 2:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeInt64(numberAt, unsafe.getLong(obj, offset));
                                break;
                            }
                        case 3:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeUInt64(numberAt, unsafe.getLong(obj, offset));
                                break;
                            }
                        case 4:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeInt32(numberAt, unsafe.getInt(obj, offset));
                                break;
                            }
                        case 5:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeFixed64(numberAt, unsafe.getLong(obj, offset));
                                break;
                            }
                        case 6:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeFixed32(numberAt, unsafe.getInt(obj, offset));
                                break;
                            }
                        case 7:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeBool(numberAt, booleanAt(obj, offset));
                                break;
                            }
                        case 8:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writeString(numberAt, unsafe.getObject(obj, offset), writer);
                                break;
                            }
                        case 9:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeMessage(numberAt, unsafe.getObject(obj, offset), getMessageFieldSchema(i));
                                break;
                            }
                        case 10:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeBytes(numberAt, (ByteString) unsafe.getObject(obj, offset));
                                break;
                            }
                        case 11:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeUInt32(numberAt, unsafe.getInt(obj, offset));
                                break;
                            }
                        case 12:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeEnum(numberAt, unsafe.getInt(obj, offset));
                                break;
                            }
                        case 13:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeSFixed32(numberAt, unsafe.getInt(obj, offset));
                                break;
                            }
                        case 14:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeSFixed64(numberAt, unsafe.getLong(obj, offset));
                                break;
                            }
                        case 15:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeSInt32(numberAt, unsafe.getInt(obj, offset));
                                break;
                            }
                        case 16:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeSInt64(numberAt, unsafe.getLong(obj, offset));
                                break;
                            }
                        case 17:
                            if ((i2 & i5) == 0) {
                                break;
                            } else {
                                writer.writeGroup(numberAt, unsafe.getObject(obj, offset), getMessageFieldSchema(i));
                                break;
                            }
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(i), (List) unsafe.getObject(obj, offset), writer, false);
                            break;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(i), (List) unsafe.getObject(obj, offset), writer, false);
                            break;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, false);
                            break;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, false);
                            break;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, false);
                            break;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, false);
                            break;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, false);
                            break;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(i), (List) unsafe.getObject(obj, offset), writer, false);
                            break;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(i), (List) unsafe.getObject(obj, offset), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(i), (List) unsafe.getObject(obj, offset), writer, getMessageFieldSchema(i));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(i), (List) unsafe.getObject(obj, offset), writer);
                            break;
                        case 29:
                            SchemaUtil.writeUInt32List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, false);
                            break;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(i), (List) unsafe.getObject(obj, offset), writer, false);
                            break;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, false);
                            break;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, false);
                            break;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, false);
                            break;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, false);
                            break;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(i), (List) unsafe.getObject(obj, offset), writer, true);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(i), (List) unsafe.getObject(obj, offset), writer, true);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, true);
                            break;
                        case 38:
                            SchemaUtil.writeUInt64List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, true);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, true);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, true);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, true);
                            break;
                        case 42:
                            SchemaUtil.writeBoolList(numberAt(i), (List) unsafe.getObject(obj, offset), writer, true);
                            break;
                        case 43:
                            SchemaUtil.writeUInt32List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, true);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(i), (List) unsafe.getObject(obj, offset), writer, true);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, true);
                            break;
                        case 46:
                            SchemaUtil.writeSFixed64List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, true);
                            break;
                        case 47:
                            SchemaUtil.writeSInt32List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, true);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(numberAt(i), (List) unsafe.getObject(obj, offset), writer, true);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(i), (List) unsafe.getObject(obj, offset), writer, getMessageFieldSchema(i));
                            break;
                        case 50:
                            writeMapHelper(writer, numberAt, unsafe.getObject(obj, offset), i);
                            break;
                        case 51:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeDouble(numberAt, oneofDoubleAt(obj, offset));
                                break;
                            }
                        case 52:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeFloat(numberAt, oneofFloatAt(obj, offset));
                                break;
                            }
                        case 53:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeInt64(numberAt, oneofLongAt(obj, offset));
                                break;
                            }
                        case 54:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeUInt64(numberAt, oneofLongAt(obj, offset));
                                break;
                            }
                        case 55:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeInt32(numberAt, oneofIntAt(obj, offset));
                                break;
                            }
                        case 56:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeFixed64(numberAt, oneofLongAt(obj, offset));
                                break;
                            }
                        case 57:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeFixed32(numberAt, oneofIntAt(obj, offset));
                                break;
                            }
                        case 58:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeBool(numberAt, oneofBooleanAt(obj, offset));
                                break;
                            }
                        case 59:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writeString(numberAt, unsafe.getObject(obj, offset), writer);
                                break;
                            }
                        case 60:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeMessage(numberAt, unsafe.getObject(obj, offset), getMessageFieldSchema(i));
                                break;
                            }
                        case 61:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeBytes(numberAt, (ByteString) unsafe.getObject(obj, offset));
                                break;
                            }
                        case 62:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeUInt32(numberAt, oneofIntAt(obj, offset));
                                break;
                            }
                        case 63:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeEnum(numberAt, oneofIntAt(obj, offset));
                                break;
                            }
                        case 64:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeSFixed32(numberAt, oneofIntAt(obj, offset));
                                break;
                            }
                        case 65:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeSFixed64(numberAt, oneofLongAt(obj, offset));
                                break;
                            }
                        case 66:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeSInt32(numberAt, oneofIntAt(obj, offset));
                                break;
                            }
                        case 67:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeSInt64(numberAt, oneofLongAt(obj, offset));
                                break;
                            }
                        case 68:
                            if (!isOneofPresent(obj, numberAt, i)) {
                                break;
                            } else {
                                writer.writeGroup(numberAt, unsafe.getObject(obj, offset), getMessageFieldSchema(i));
                                break;
                            }
                    }
                    i += 3;
                    i3 = 1048575;
                }
                while (entry != null) {
                    this.extensionSchema.serializeExtension(writer, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
                writeUnknownInMessageTo(this.unknownFieldSchema, obj, writer);
            }
        }
        it = null;
        entry = null;
        length = this.buffer.length;
        Unsafe unsafe2 = UNSAFE;
        int i32 = 1048575;
        i = 0;
        int i42 = 1048575;
        int i52 = 0;
        while (i < length) {
        }
        while (entry != null) {
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, obj, writer);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0589  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void writeFieldsInAscendingOrderProto3(Object obj, Writer writer) {
        Iterator it;
        Map.Entry entry;
        int length;
        int i;
        if (this.hasExtensions) {
            FieldSet extensions = this.extensionSchema.getExtensions(obj);
            if (!extensions.isEmpty()) {
                it = extensions.iterator();
                entry = (Map.Entry) it.next();
                length = this.buffer.length;
                for (i = 0; i < length; i += 3) {
                    int typeAndOffsetAt = typeAndOffsetAt(i);
                    int numberAt = numberAt(i);
                    while (entry != null && this.extensionSchema.extensionNumber(entry) <= numberAt) {
                        this.extensionSchema.serializeExtension(writer, entry);
                        entry = it.hasNext() ? (Map.Entry) it.next() : null;
                    }
                    switch (type(typeAndOffsetAt)) {
                        case 0:
                            if (isFieldPresent(obj, i)) {
                                writer.writeDouble(numberAt, doubleAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if (isFieldPresent(obj, i)) {
                                writer.writeFloat(numberAt, floatAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (isFieldPresent(obj, i)) {
                                writer.writeInt64(numberAt, longAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (isFieldPresent(obj, i)) {
                                writer.writeUInt64(numberAt, longAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if (isFieldPresent(obj, i)) {
                                writer.writeInt32(numberAt, intAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if (isFieldPresent(obj, i)) {
                                writer.writeFixed64(numberAt, longAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if (isFieldPresent(obj, i)) {
                                writer.writeFixed32(numberAt, intAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if (isFieldPresent(obj, i)) {
                                writer.writeBool(numberAt, booleanAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 8:
                            if (isFieldPresent(obj, i)) {
                                writeString(numberAt, UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            if (isFieldPresent(obj, i)) {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
                                break;
                            } else {
                                break;
                            }
                        case 10:
                            if (isFieldPresent(obj, i)) {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 11:
                            if (isFieldPresent(obj, i)) {
                                writer.writeUInt32(numberAt, intAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            if (isFieldPresent(obj, i)) {
                                writer.writeEnum(numberAt, intAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 13:
                            if (isFieldPresent(obj, i)) {
                                writer.writeSFixed32(numberAt, intAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            if (isFieldPresent(obj, i)) {
                                writer.writeSFixed64(numberAt, longAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 15:
                            if (isFieldPresent(obj, i)) {
                                writer.writeSInt32(numberAt, intAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 16:
                            if (isFieldPresent(obj, i)) {
                                writer.writeSInt64(numberAt, longAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 17:
                            if (isFieldPresent(obj, i)) {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
                                break;
                            } else {
                                break;
                            }
                        case 18:
                            SchemaUtil.writeDoubleList(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 19:
                            SchemaUtil.writeFloatList(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 20:
                            SchemaUtil.writeInt64List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 21:
                            SchemaUtil.writeUInt64List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 22:
                            SchemaUtil.writeInt32List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 23:
                            SchemaUtil.writeFixed64List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 24:
                            SchemaUtil.writeFixed32List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 25:
                            SchemaUtil.writeBoolList(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 26:
                            SchemaUtil.writeStringList(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer);
                            break;
                        case 27:
                            SchemaUtil.writeMessageList(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(i));
                            break;
                        case 28:
                            SchemaUtil.writeBytesList(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer);
                            break;
                        case 29:
                            SchemaUtil.writeUInt32List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 30:
                            SchemaUtil.writeEnumList(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 31:
                            SchemaUtil.writeSFixed32List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 32:
                            SchemaUtil.writeSFixed64List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 33:
                            SchemaUtil.writeSInt32List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 34:
                            SchemaUtil.writeSInt64List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, false);
                            break;
                        case 35:
                            SchemaUtil.writeDoubleList(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 36:
                            SchemaUtil.writeFloatList(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 37:
                            SchemaUtil.writeInt64List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 38:
                            SchemaUtil.writeUInt64List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 39:
                            SchemaUtil.writeInt32List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 40:
                            SchemaUtil.writeFixed64List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 41:
                            SchemaUtil.writeFixed32List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 42:
                            SchemaUtil.writeBoolList(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 43:
                            SchemaUtil.writeUInt32List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 44:
                            SchemaUtil.writeEnumList(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 45:
                            SchemaUtil.writeSFixed32List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 46:
                            SchemaUtil.writeSFixed64List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 47:
                            SchemaUtil.writeSInt32List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 48:
                            SchemaUtil.writeSInt64List(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, true);
                            break;
                        case 49:
                            SchemaUtil.writeGroupList(numberAt(i), (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer, getMessageFieldSchema(i));
                            break;
                        case 50:
                            writeMapHelper(writer, numberAt, UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), i);
                            break;
                        case 51:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeDouble(numberAt, oneofDoubleAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 52:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeFloat(numberAt, oneofFloatAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeInt64(numberAt, oneofLongAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeUInt64(numberAt, oneofLongAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 55:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeInt32(numberAt, oneofIntAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 56:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeFixed64(numberAt, oneofLongAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 57:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeFixed32(numberAt, oneofIntAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 58:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeBool(numberAt, oneofBooleanAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 59:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writeString(numberAt, UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), writer);
                                break;
                            } else {
                                break;
                            }
                        case 60:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeMessage(numberAt, UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
                                break;
                            } else {
                                break;
                            }
                        case 61:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeBytes(numberAt, (ByteString) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 62:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeUInt32(numberAt, oneofIntAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 63:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeEnum(numberAt, oneofIntAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 64:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeSFixed32(numberAt, oneofIntAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 65:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeSFixed64(numberAt, oneofLongAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 66:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeSInt32(numberAt, oneofIntAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 67:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeSInt64(numberAt, oneofLongAt(obj, offset(typeAndOffsetAt)));
                                break;
                            } else {
                                break;
                            }
                        case 68:
                            if (isOneofPresent(obj, numberAt, i)) {
                                writer.writeGroup(numberAt, UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)), getMessageFieldSchema(i));
                                break;
                            } else {
                                break;
                            }
                    }
                }
                while (entry != null) {
                    this.extensionSchema.serializeExtension(writer, entry);
                    entry = it.hasNext() ? (Map.Entry) it.next() : null;
                }
                writeUnknownInMessageTo(this.unknownFieldSchema, obj, writer);
            }
        }
        it = null;
        entry = null;
        length = this.buffer.length;
        while (i < length) {
        }
        while (entry != null) {
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, obj, writer);
    }

    @Override // com.google.protobuf.Schema
    public final boolean isInitialized(Object obj) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.checkInitializedCount) {
            int i6 = this.intArray[i5];
            int numberAt = numberAt(i6);
            int typeAndOffsetAt = typeAndOffsetAt(i6);
            int i7 = this.buffer[i6 + 2];
            int i8 = i7 & 1048575;
            int i9 = 1 << (i7 >>> 20);
            if (i8 != i3) {
                if (i8 != 1048575) {
                    i4 = UNSAFE.getInt(obj, i8);
                }
                i2 = i4;
                i = i8;
            } else {
                i = i3;
                i2 = i4;
            }
            if (isRequired(typeAndOffsetAt) && !isFieldPresent(obj, i6, i, i2, i9)) {
                return false;
            }
            switch (type(typeAndOffsetAt)) {
                case 9:
                case 17:
                    if (isFieldPresent(obj, i6, i, i2, i9) && !isInitialized(obj, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                        return false;
                    }
                    break;
                case 27:
                case 49:
                    if (isListInitialized(obj, typeAndOffsetAt, i6)) {
                        break;
                    } else {
                        return false;
                    }
                case 50:
                    if (isMapInitialized(obj, typeAndOffsetAt, i6)) {
                        break;
                    } else {
                        return false;
                    }
                case 60:
                case 68:
                    if (isOneofPresent(obj, numberAt, i6) && !isInitialized(obj, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                        return false;
                    }
                    break;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(obj).isInitialized();
    }

    private int positionForFieldNumber(int i, int i2) {
        if (i < this.minFieldNumber || i > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i, i2);
    }

    private int getSerializedSizeProto2(Object obj) {
        int i;
        int i2;
        Unsafe unsafe = UNSAFE;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1048575;
        int i7 = 0;
        while (i4 < this.buffer.length) {
            int typeAndOffsetAt = typeAndOffsetAt(i4);
            int numberAt = numberAt(i4);
            int type = type(typeAndOffsetAt);
            if (type <= 17) {
                i = this.buffer[i4 + 2];
                int i8 = i & i3;
                i2 = 1 << (i >>> 20);
                if (i8 != i6) {
                    i7 = unsafe.getInt(obj, i8);
                    i6 = i8;
                }
            } else {
                i = (!this.useCachedSizeField || type < FieldType.DOUBLE_LIST_PACKED.id() || type > FieldType.SINT64_LIST_PACKED.id()) ? 0 : this.buffer[i4 + 2] & i3;
                i2 = 0;
            }
            long offset = offset(typeAndOffsetAt);
            switch (type) {
                case 0:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        break;
                    }
                case 1:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        break;
                    }
                case 2:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeInt64Size(numberAt, unsafe.getLong(obj, offset));
                        break;
                    }
                case 3:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeUInt64Size(numberAt, unsafe.getLong(obj, offset));
                        break;
                    }
                case 4:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeInt32Size(numberAt, unsafe.getInt(obj, offset));
                        break;
                    }
                case 5:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        break;
                    }
                case 6:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeFixed32Size(numberAt, 0);
                        break;
                    }
                case 7:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeBoolSize(numberAt, true);
                        break;
                    }
                case 8:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj, offset);
                        if (object instanceof ByteString) {
                            i5 += CodedOutputStream.computeBytesSize(numberAt, (ByteString) object);
                            break;
                        } else {
                            i5 += CodedOutputStream.computeStringSize(numberAt, (String) object);
                            break;
                        }
                    }
                case 9:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += SchemaUtil.computeSizeMessage(numberAt, unsafe.getObject(obj, offset), getMessageFieldSchema(i4));
                        break;
                    }
                case 10:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeBytesSize(numberAt, (ByteString) unsafe.getObject(obj, offset));
                        break;
                    }
                case 11:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeUInt32Size(numberAt, unsafe.getInt(obj, offset));
                        break;
                    }
                case 12:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeEnumSize(numberAt, unsafe.getInt(obj, offset));
                        break;
                    }
                case 13:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeSFixed32Size(numberAt, 0);
                        break;
                    }
                case 14:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                        break;
                    }
                case 15:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeSInt32Size(numberAt, unsafe.getInt(obj, offset));
                        break;
                    }
                case 16:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeSInt64Size(numberAt, unsafe.getLong(obj, offset));
                        break;
                    }
                case 17:
                    if ((i7 & i2) == 0) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeGroupSize(numberAt, (MessageLite) unsafe.getObject(obj, offset), getMessageFieldSchema(i4));
                        break;
                    }
                case 18:
                    i5 += SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(obj, offset), false);
                    break;
                case 19:
                    i5 += SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(obj, offset), false);
                    break;
                case 20:
                    i5 += SchemaUtil.computeSizeInt64List(numberAt, (List) unsafe.getObject(obj, offset), false);
                    break;
                case 21:
                    i5 += SchemaUtil.computeSizeUInt64List(numberAt, (List) unsafe.getObject(obj, offset), false);
                    break;
                case 22:
                    i5 += SchemaUtil.computeSizeInt32List(numberAt, (List) unsafe.getObject(obj, offset), false);
                    break;
                case 23:
                    i5 += SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(obj, offset), false);
                    break;
                case 24:
                    i5 += SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(obj, offset), false);
                    break;
                case 25:
                    i5 += SchemaUtil.computeSizeBoolList(numberAt, (List) unsafe.getObject(obj, offset), false);
                    break;
                case 26:
                    i5 += SchemaUtil.computeSizeStringList(numberAt, (List) unsafe.getObject(obj, offset));
                    break;
                case 27:
                    i5 += SchemaUtil.computeSizeMessageList(numberAt, (List) unsafe.getObject(obj, offset), getMessageFieldSchema(i4));
                    break;
                case 28:
                    i5 += SchemaUtil.computeSizeByteStringList(numberAt, (List) unsafe.getObject(obj, offset));
                    break;
                case 29:
                    i5 += SchemaUtil.computeSizeUInt32List(numberAt, (List) unsafe.getObject(obj, offset), false);
                    break;
                case 30:
                    i5 += SchemaUtil.computeSizeEnumList(numberAt, (List) unsafe.getObject(obj, offset), false);
                    break;
                case 31:
                    i5 += SchemaUtil.computeSizeFixed32List(numberAt, (List) unsafe.getObject(obj, offset), false);
                    break;
                case 32:
                    i5 += SchemaUtil.computeSizeFixed64List(numberAt, (List) unsafe.getObject(obj, offset), false);
                    break;
                case 33:
                    i5 += SchemaUtil.computeSizeSInt32List(numberAt, (List) unsafe.getObject(obj, offset), false);
                    break;
                case 34:
                    i5 += SchemaUtil.computeSizeSInt64List(numberAt, (List) unsafe.getObject(obj, offset), false);
                    break;
                case 35:
                    int computeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeFixed64ListNoTag);
                        }
                        i5 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag) + computeSizeFixed64ListNoTag;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    int computeSizeFixed32ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeFixed32ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeFixed32ListNoTag);
                        }
                        i5 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed32ListNoTag) + computeSizeFixed32ListNoTag;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    int computeSizeInt64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeInt64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeInt64ListNoTag);
                        }
                        i5 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeInt64ListNoTag) + computeSizeInt64ListNoTag;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    int computeSizeUInt64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeUInt64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeUInt64ListNoTag);
                        }
                        i5 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeUInt64ListNoTag) + computeSizeUInt64ListNoTag;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    int computeSizeInt32ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeInt32ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeInt32ListNoTag);
                        }
                        i5 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeInt32ListNoTag) + computeSizeInt32ListNoTag;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    int computeSizeFixed64ListNoTag2 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeFixed64ListNoTag2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeFixed64ListNoTag2);
                        }
                        i5 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag2) + computeSizeFixed64ListNoTag2;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    int computeSizeFixed32ListNoTag2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeFixed32ListNoTag2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeFixed32ListNoTag2);
                        }
                        i5 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed32ListNoTag2) + computeSizeFixed32ListNoTag2;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    int computeSizeBoolListNoTag = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeBoolListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeBoolListNoTag);
                        }
                        i5 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeBoolListNoTag) + computeSizeBoolListNoTag;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    int computeSizeUInt32ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeUInt32ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeUInt32ListNoTag);
                        }
                        i5 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeUInt32ListNoTag) + computeSizeUInt32ListNoTag;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    int computeSizeEnumListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeEnumListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeEnumListNoTag);
                        }
                        i5 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeEnumListNoTag) + computeSizeEnumListNoTag;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    int computeSizeFixed32ListNoTag3 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeFixed32ListNoTag3 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeFixed32ListNoTag3);
                        }
                        i5 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed32ListNoTag3) + computeSizeFixed32ListNoTag3;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    int computeSizeFixed64ListNoTag3 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeFixed64ListNoTag3 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeFixed64ListNoTag3);
                        }
                        i5 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeFixed64ListNoTag3) + computeSizeFixed64ListNoTag3;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    int computeSizeSInt32ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeSInt32ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeSInt32ListNoTag);
                        }
                        i5 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeSInt32ListNoTag) + computeSizeSInt32ListNoTag;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    int computeSizeSInt64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (computeSizeSInt64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(obj, i, computeSizeSInt64ListNoTag);
                        }
                        i5 += CodedOutputStream.computeTagSize(numberAt) + CodedOutputStream.computeUInt32SizeNoTag(computeSizeSInt64ListNoTag) + computeSizeSInt64ListNoTag;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    i5 += SchemaUtil.computeSizeGroupList(numberAt, (List) unsafe.getObject(obj, offset), getMessageFieldSchema(i4));
                    break;
                case 50:
                    i5 += this.mapFieldSchema.getSerializedSize(numberAt, unsafe.getObject(obj, offset), getMapFieldDefaultEntry(i4));
                    break;
                case 51:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeDoubleSize(numberAt, 0.0d);
                        break;
                    }
                case 52:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeFloatSize(numberAt, 0.0f);
                        break;
                    }
                case 53:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeInt64Size(numberAt, oneofLongAt(obj, offset));
                        break;
                    }
                case 54:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeUInt64Size(numberAt, oneofLongAt(obj, offset));
                        break;
                    }
                case 55:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeInt32Size(numberAt, oneofIntAt(obj, offset));
                        break;
                    }
                case 56:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeFixed64Size(numberAt, 0L);
                        break;
                    }
                case 57:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeFixed32Size(numberAt, 0);
                        break;
                    }
                case 58:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeBoolSize(numberAt, true);
                        break;
                    }
                case 59:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        Object object2 = unsafe.getObject(obj, offset);
                        if (object2 instanceof ByteString) {
                            i5 += CodedOutputStream.computeBytesSize(numberAt, (ByteString) object2);
                            break;
                        } else {
                            i5 += CodedOutputStream.computeStringSize(numberAt, (String) object2);
                            break;
                        }
                    }
                case 60:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += SchemaUtil.computeSizeMessage(numberAt, unsafe.getObject(obj, offset), getMessageFieldSchema(i4));
                        break;
                    }
                case 61:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeBytesSize(numberAt, (ByteString) unsafe.getObject(obj, offset));
                        break;
                    }
                case 62:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeUInt32Size(numberAt, oneofIntAt(obj, offset));
                        break;
                    }
                case 63:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeEnumSize(numberAt, oneofIntAt(obj, offset));
                        break;
                    }
                case 64:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeSFixed32Size(numberAt, 0);
                        break;
                    }
                case 65:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeSFixed64Size(numberAt, 0L);
                        break;
                    }
                case 66:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeSInt32Size(numberAt, oneofIntAt(obj, offset));
                        break;
                    }
                case 67:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeSInt64Size(numberAt, oneofLongAt(obj, offset));
                        break;
                    }
                case 68:
                    if (!isOneofPresent(obj, numberAt, i4)) {
                        break;
                    } else {
                        i5 += CodedOutputStream.computeGroupSize(numberAt, (MessageLite) unsafe.getObject(obj, offset), getMessageFieldSchema(i4));
                        break;
                    }
            }
            i4 += 3;
            i3 = 1048575;
        }
        int unknownFieldsSerializedSize = i5 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, obj);
        if (this.hasExtensions) {
            return unknownFieldsSerializedSize + this.extensionSchema.getExtensions(obj).getSerializedSize();
        }
        return unknownFieldsSerializedSize;
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(Object obj, Object obj2) {
        if (obj2 != null) {
            for (int i = 0; i < this.buffer.length; i += 3) {
                mergeSingleField(obj, obj2, i);
            }
            SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, obj, obj2);
            if (this.hasExtensions) {
                SchemaUtil.mergeExtensions(this.extensionSchema, obj, obj2);
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(Object obj, byte[] bArr, int i, int i2, ArrayDecoders.Registers registers) {
        if (this.proto3) {
            parseProto3Message(obj, bArr, i, i2, registers);
        } else {
            parseProto2Message(obj, bArr, i, i2, 0, registers);
        }
    }

    private boolean equals(Object obj, Object obj2, int i) {
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                return arePresentForEquals(obj, obj2, i) && Double.doubleToLongBits(UnsafeUtil.getDouble(obj, offset)) == Double.doubleToLongBits(UnsafeUtil.getDouble(obj2, offset));
            case 1:
                return arePresentForEquals(obj, obj2, i) && Float.floatToIntBits(UnsafeUtil.getFloat(obj, offset)) == Float.floatToIntBits(UnsafeUtil.getFloat(obj2, offset));
            case 2:
                return arePresentForEquals(obj, obj2, i) && UnsafeUtil.getLong(obj, offset) == UnsafeUtil.getLong(obj2, offset);
            case 3:
                return arePresentForEquals(obj, obj2, i) && UnsafeUtil.getLong(obj, offset) == UnsafeUtil.getLong(obj2, offset);
            case 4:
                return arePresentForEquals(obj, obj2, i) && UnsafeUtil.getInt(obj, offset) == UnsafeUtil.getInt(obj2, offset);
            case 5:
                return arePresentForEquals(obj, obj2, i) && UnsafeUtil.getLong(obj, offset) == UnsafeUtil.getLong(obj2, offset);
            case 6:
                return arePresentForEquals(obj, obj2, i) && UnsafeUtil.getInt(obj, offset) == UnsafeUtil.getInt(obj2, offset);
            case 7:
                return arePresentForEquals(obj, obj2, i) && UnsafeUtil.getBoolean(obj, offset) == UnsafeUtil.getBoolean(obj2, offset);
            case 8:
                return arePresentForEquals(obj, obj2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(obj, offset), UnsafeUtil.getObject(obj2, offset));
            case 9:
                return arePresentForEquals(obj, obj2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(obj, offset), UnsafeUtil.getObject(obj2, offset));
            case 10:
                return arePresentForEquals(obj, obj2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(obj, offset), UnsafeUtil.getObject(obj2, offset));
            case 11:
                return arePresentForEquals(obj, obj2, i) && UnsafeUtil.getInt(obj, offset) == UnsafeUtil.getInt(obj2, offset);
            case 12:
                return arePresentForEquals(obj, obj2, i) && UnsafeUtil.getInt(obj, offset) == UnsafeUtil.getInt(obj2, offset);
            case 13:
                return arePresentForEquals(obj, obj2, i) && UnsafeUtil.getInt(obj, offset) == UnsafeUtil.getInt(obj2, offset);
            case 14:
                return arePresentForEquals(obj, obj2, i) && UnsafeUtil.getLong(obj, offset) == UnsafeUtil.getLong(obj2, offset);
            case 15:
                return arePresentForEquals(obj, obj2, i) && UnsafeUtil.getInt(obj, offset) == UnsafeUtil.getInt(obj2, offset);
            case 16:
                return arePresentForEquals(obj, obj2, i) && UnsafeUtil.getLong(obj, offset) == UnsafeUtil.getLong(obj2, offset);
            case 17:
                return arePresentForEquals(obj, obj2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(obj, offset), UnsafeUtil.getObject(obj2, offset));
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(obj, offset), UnsafeUtil.getObject(obj2, offset));
            case 50:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(obj, offset), UnsafeUtil.getObject(obj2, offset));
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                return isOneofCaseEqual(obj, obj2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(obj, offset), UnsafeUtil.getObject(obj2, offset));
            default:
                return true;
        }
    }

    private static boolean isInitialized(Object obj, int i, Schema schema) {
        return schema.isInitialized(UnsafeUtil.getObject(obj, offset(i)));
    }

    private boolean isFieldPresent(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return isFieldPresent(obj, i);
        }
        return (i3 & i4) != 0;
    }
}
