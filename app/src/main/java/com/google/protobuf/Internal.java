package com.google.protobuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Internal {
    public static final byte[] EMPTY_BYTE_ARRAY;
    public static final ByteBuffer EMPTY_BYTE_BUFFER;
    public static final CodedInputStream EMPTY_CODED_INPUT_STREAM;
    static final Charset US_ASCII = Charset.forName("US-ASCII");
    static final Charset UTF_8 = Charset.forName("UTF-8");
    static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface BooleanList extends ProtobufList {
        @Override // 
        BooleanList mutableCopyWithCapacity(int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface DoubleList extends ProtobufList {
        @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
        DoubleList mutableCopyWithCapacity(int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface EnumLite {
        int getNumber();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface EnumLiteMap {
        EnumLite findValueByNumber(int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface EnumVerifier {
        boolean isInRange(int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface FloatList extends ProtobufList {
        @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
        FloatList mutableCopyWithCapacity(int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface IntList extends ProtobufList {
        void addInt(int i);

        int getInt(int i);

        @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
        IntList mutableCopyWithCapacity(int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ListAdapter extends AbstractList {
        private final Converter converter;
        private final List fromList;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public interface Converter {
            Object convert(Object obj);
        }

        public ListAdapter(List list, Converter converter) {
            this.fromList = list;
            this.converter = converter;
        }

        @Override // java.util.AbstractList, java.util.List
        public Object get(int i) {
            return this.converter.convert(this.fromList.get(i));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.fromList.size();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface LongList extends ProtobufList {
        void addLong(long j);

        @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
        LongList mutableCopyWithCapacity(int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ProtobufList extends List, RandomAccess {
        boolean isModifiable();

        void makeImmutable();

        ProtobufList mutableCopyWithCapacity(int i);
    }

    static {
        byte[] bArr = new byte[0];
        EMPTY_BYTE_ARRAY = bArr;
        EMPTY_BYTE_BUFFER = ByteBuffer.wrap(bArr);
        EMPTY_CODED_INPUT_STREAM = CodedInputStream.newInstance(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object checkNotNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    public static int hashBoolean(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int hashCode(byte[] bArr) {
        return hashCode(bArr, 0, bArr.length);
    }

    public static int hashLong(long j) {
        return (int) (j ^ (j >>> 32));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isProto1Group(MessageLite messageLite) {
        return (messageLite instanceof AbstractMutableMessageLite) && ((AbstractMutableMessageLite) messageLite).isProto1Group();
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return Utf8.isValidUtf8(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object mergeMessage(Object obj, Object obj2) {
        return ((MessageLite) obj).toBuilder().mergeFrom((MessageLite) obj2).buildPartial();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int partialHash(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    public static String toStringUtf8(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    static int hashCode(byte[] bArr, int i, int i2) {
        int partialHash = partialHash(i2, bArr, i, i2);
        if (partialHash == 0) {
            return 1;
        }
        return partialHash;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object checkNotNull(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
        return obj;
    }
}
