package com.google.protobuf;

import com.google.common.base.Ascii;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class Utf8 {
    private static final Processor processor;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class DecodeUtil {
        /* JADX INFO: Access modifiers changed from: private */
        public static void handleFourBytes(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) {
            if (!isNotTrailingByte(b2) && (((b << Ascii.FS) + (b2 + 112)) >> 30) == 0 && !isNotTrailingByte(b3) && !isNotTrailingByte(b4)) {
                int trailingByteValue = ((b & 7) << 18) | (trailingByteValue(b2) << 12) | (trailingByteValue(b3) << 6) | trailingByteValue(b4);
                cArr[i] = highSurrogate(trailingByteValue);
                cArr[i + 1] = lowSurrogate(trailingByteValue);
                return;
            }
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleOneByte(byte b, char[] cArr, int i) {
            cArr[i] = (char) b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleThreeBytes(byte b, byte b2, byte b3, char[] cArr, int i) {
            if (!isNotTrailingByte(b2) && ((b != -32 || b2 >= -96) && ((b != -19 || b2 < -96) && !isNotTrailingByte(b3)))) {
                cArr[i] = (char) (((b & Ascii.SI) << 12) | (trailingByteValue(b2) << 6) | trailingByteValue(b3));
                return;
            }
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleTwoBytes(byte b, byte b2, char[] cArr, int i) {
            if (b >= -62 && !isNotTrailingByte(b2)) {
                cArr[i] = (char) (((b & Ascii.US) << 6) | trailingByteValue(b2));
                return;
            }
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        private static char highSurrogate(int i) {
            return (char) ((i >>> 10) + 55232);
        }

        private static boolean isNotTrailingByte(byte b) {
            return b > -65;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isOneByte(byte b) {
            return b >= 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isThreeBytes(byte b) {
            return b < -16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isTwoBytes(byte b) {
            return b < -32;
        }

        private static char lowSurrogate(int i) {
            return (char) ((i & 1023) + 56320);
        }

        private static int trailingByteValue(byte b) {
            return b & 63;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Processor {
        Processor() {
        }

        final String decodeUtf8(ByteBuffer byteBuffer, int i, int i2) {
            if (byteBuffer.hasArray()) {
                return decodeUtf8(byteBuffer.array(), byteBuffer.arrayOffset() + i, i2);
            } else if (byteBuffer.isDirect()) {
                return decodeUtf8Direct(byteBuffer, i, i2);
            } else {
                return decodeUtf8Default(byteBuffer, i, i2);
            }
        }

        abstract String decodeUtf8(byte[] bArr, int i, int i2);

        final String decodeUtf8Default(ByteBuffer byteBuffer, int i, int i2) {
            if ((i | i2 | ((byteBuffer.limit() - i) - i2)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (i < i3) {
                byte b = byteBuffer.get(i);
                if (!DecodeUtil.isOneByte(b)) {
                    break;
                }
                i++;
                DecodeUtil.handleOneByte(b, cArr, i4);
                i4++;
            }
            int i5 = i4;
            while (i < i3) {
                int i6 = i + 1;
                byte b2 = byteBuffer.get(i);
                if (DecodeUtil.isOneByte(b2)) {
                    DecodeUtil.handleOneByte(b2, cArr, i5);
                    i = i6;
                    i5++;
                    while (i < i3) {
                        byte b3 = byteBuffer.get(i);
                        if (!DecodeUtil.isOneByte(b3)) {
                            break;
                        }
                        i++;
                        DecodeUtil.handleOneByte(b3, cArr, i5);
                        i5++;
                    }
                } else if (DecodeUtil.isTwoBytes(b2)) {
                    if (i6 < i3) {
                        DecodeUtil.handleTwoBytes(b2, byteBuffer.get(i6), cArr, i5);
                        i = i6 + 1;
                        i5++;
                    } else {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                } else if (DecodeUtil.isThreeBytes(b2)) {
                    if (i6 < i3 - 1) {
                        int i7 = i6 + 1;
                        DecodeUtil.handleThreeBytes(b2, byteBuffer.get(i6), byteBuffer.get(i7), cArr, i5);
                        i = i7 + 1;
                        i5++;
                    } else {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                } else if (i6 < i3 - 2) {
                    int i8 = i6 + 1;
                    int i9 = i8 + 1;
                    DecodeUtil.handleFourBytes(b2, byteBuffer.get(i6), byteBuffer.get(i8), byteBuffer.get(i9), cArr, i5);
                    i5 += 2;
                    i = i9 + 1;
                } else {
                    throw InvalidProtocolBufferException.invalidUtf8();
                }
            }
            return new String(cArr, 0, i5);
        }

        abstract String decodeUtf8Direct(ByteBuffer byteBuffer, int i, int i2);

        abstract int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2);

        final boolean isValidUtf8(ByteBuffer byteBuffer, int i, int i2) {
            return partialIsValidUtf8(0, byteBuffer, i, i2) == 0;
        }

        final int partialIsValidUtf8(int i, ByteBuffer byteBuffer, int i2, int i3) {
            if (byteBuffer.hasArray()) {
                int arrayOffset = byteBuffer.arrayOffset();
                return partialIsValidUtf8(i, byteBuffer.array(), i2 + arrayOffset, arrayOffset + i3);
            } else if (byteBuffer.isDirect()) {
                return partialIsValidUtf8Direct(i, byteBuffer, i2, i3);
            } else {
                return partialIsValidUtf8Default(i, byteBuffer, i2, i3);
            }
        }

        abstract int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3);

        /* JADX WARN: Code restructure failed: missing block: B:58:0x009a, code lost:
            if (r8.get(r7) <= (-65)) goto L54;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        final int partialIsValidUtf8Default(int i, ByteBuffer byteBuffer, int i2, int i3) {
            byte b;
            int i4;
            if (i != 0) {
                if (i2 >= i3) {
                    return i;
                }
                byte b2 = (byte) i;
                if (b2 < -32) {
                    if (b2 >= -62) {
                        int i5 = i2 + 1;
                        if (byteBuffer.get(i2) <= -65) {
                            i2 = i5;
                        }
                    }
                    return -1;
                } else if (b2 < -16) {
                    byte b3 = (byte) ((i >> 8) ^ (-1));
                    if (b3 == 0) {
                        int i6 = i2 + 1;
                        byte b4 = byteBuffer.get(i2);
                        if (i6 >= i3) {
                            return Utf8.incompleteStateFor(b2, b4);
                        }
                        i2 = i6;
                        b3 = b4;
                    }
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        int i7 = i2 + 1;
                        if (byteBuffer.get(i2) <= -65) {
                            i2 = i7;
                        }
                    }
                    return -1;
                } else {
                    byte b5 = (byte) ((i >> 8) ^ (-1));
                    if (b5 == 0) {
                        i4 = i2 + 1;
                        b5 = byteBuffer.get(i2);
                        if (i4 >= i3) {
                            return Utf8.incompleteStateFor(b2, b5);
                        }
                        b = 0;
                    } else {
                        b = (byte) (i >> 16);
                        i4 = i2;
                    }
                    if (b == 0) {
                        int i8 = i4 + 1;
                        byte b6 = byteBuffer.get(i4);
                        if (i8 >= i3) {
                            return Utf8.incompleteStateFor(b2, b5, b6);
                        }
                        b = b6;
                        i4 = i8;
                    }
                    if (b5 <= -65 && (((b2 << Ascii.FS) + (b5 + 112)) >> 30) == 0 && b <= -65) {
                        i2 = i4 + 1;
                    }
                    return -1;
                }
            }
            return partialIsValidUtf8(byteBuffer, i2, i3);
        }

        abstract int partialIsValidUtf8Direct(int i, ByteBuffer byteBuffer, int i2, int i3);

        final boolean isValidUtf8(byte[] bArr, int i, int i2) {
            return partialIsValidUtf8(0, bArr, i, i2) == 0;
        }

        private static int partialIsValidUtf8(ByteBuffer byteBuffer, int i, int i2) {
            int estimateConsecutiveAscii = i + Utf8.estimateConsecutiveAscii(byteBuffer, i, i2);
            while (estimateConsecutiveAscii < i2) {
                int i3 = estimateConsecutiveAscii + 1;
                byte b = byteBuffer.get(estimateConsecutiveAscii);
                if (b >= 0) {
                    estimateConsecutiveAscii = i3;
                } else if (b < -32) {
                    if (i3 < i2) {
                        if (b < -62 || byteBuffer.get(i3) > -65) {
                            return -1;
                        }
                        estimateConsecutiveAscii = i3 + 1;
                    } else {
                        return b;
                    }
                } else if (b < -16) {
                    if (i3 < i2 - 1) {
                        int i4 = i3 + 1;
                        byte b2 = byteBuffer.get(i3);
                        if (b2 > -65 || ((b == -32 && b2 < -96) || ((b == -19 && b2 >= -96) || byteBuffer.get(i4) > -65))) {
                            return -1;
                        }
                        estimateConsecutiveAscii = i4 + 1;
                    } else {
                        return Utf8.incompleteStateFor(byteBuffer, b, i3, i2 - i3);
                    }
                } else if (i3 < i2 - 2) {
                    int i5 = i3 + 1;
                    byte b3 = byteBuffer.get(i3);
                    if (b3 <= -65 && (((b << Ascii.FS) + (b3 + 112)) >> 30) == 0) {
                        int i6 = i5 + 1;
                        if (byteBuffer.get(i5) <= -65) {
                            int i7 = i6 + 1;
                            if (byteBuffer.get(i6) <= -65) {
                                estimateConsecutiveAscii = i7;
                            }
                        }
                    }
                    return -1;
                } else {
                    return Utf8.incompleteStateFor(byteBuffer, b, i3, i2 - i3);
                }
            }
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int i, int i2) {
            super("Unpaired surrogate at index " + i + " of " + i2);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class UnsafeProcessor extends Processor {
        UnsafeProcessor() {
        }

        static boolean isAvailable() {
            return UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        private static int unsafeIncompleteStateFor(long j, int i, int i2) {
            switch (i2) {
                case 0:
                    return Utf8.incompleteStateFor(i);
                case 1:
                    return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(j));
                case 2:
                    return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(j), UnsafeUtil.getByte(j + 1));
                default:
                    throw new AssertionError();
            }
        }

        @Override // com.google.protobuf.Utf8.Processor
        String decodeUtf8(byte[] bArr, int i, int i2) {
            String str = new String(bArr, i, i2, Internal.UTF_8);
            if (!str.contains("�")) {
                return str;
            }
            if (Arrays.equals(str.getBytes(Internal.UTF_8), Arrays.copyOfRange(bArr, i, i2 + i))) {
                return str;
            }
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        @Override // com.google.protobuf.Utf8.Processor
        String decodeUtf8Direct(ByteBuffer byteBuffer, int i, int i2) {
            if ((i | i2 | ((byteBuffer.limit() - i) - i2)) >= 0) {
                long addressOffset = UnsafeUtil.addressOffset(byteBuffer) + i;
                long j = i2 + addressOffset;
                char[] cArr = new char[i2];
                int i3 = 0;
                while (addressOffset < j) {
                    byte b = UnsafeUtil.getByte(addressOffset);
                    if (!DecodeUtil.isOneByte(b)) {
                        break;
                    }
                    addressOffset++;
                    DecodeUtil.handleOneByte(b, cArr, i3);
                    i3++;
                }
                while (addressOffset < j) {
                    long j2 = addressOffset + 1;
                    byte b2 = UnsafeUtil.getByte(addressOffset);
                    if (DecodeUtil.isOneByte(b2)) {
                        int i4 = i3 + 1;
                        DecodeUtil.handleOneByte(b2, cArr, i3);
                        while (j2 < j) {
                            byte b3 = UnsafeUtil.getByte(j2);
                            if (!DecodeUtil.isOneByte(b3)) {
                                break;
                            }
                            j2++;
                            DecodeUtil.handleOneByte(b3, cArr, i4);
                            i4++;
                        }
                        i3 = i4;
                        addressOffset = j2;
                    } else if (DecodeUtil.isTwoBytes(b2)) {
                        if (j2 >= j) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        addressOffset = j2 + 1;
                        DecodeUtil.handleTwoBytes(b2, UnsafeUtil.getByte(j2), cArr, i3);
                        i3++;
                    } else if (DecodeUtil.isThreeBytes(b2)) {
                        if (j2 >= (-1) + j) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        long j3 = j2 + 1;
                        DecodeUtil.handleThreeBytes(b2, UnsafeUtil.getByte(j2), UnsafeUtil.getByte(j3), cArr, i3);
                        addressOffset = j3 + 1;
                        i3++;
                    } else if (j2 >= (-2) + j) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    } else {
                        long j4 = j2 + 1;
                        long j5 = j4 + 1;
                        DecodeUtil.handleFourBytes(b2, UnsafeUtil.getByte(j2), UnsafeUtil.getByte(j4), UnsafeUtil.getByte(j5), cArr, i3);
                        i3 += 2;
                        addressOffset = j5 + 1;
                    }
                }
                return new String(cArr, 0, i3);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i), Integer.valueOf(i2)));
        }

        @Override // com.google.protobuf.Utf8.Processor
        int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2) {
            char c;
            long j;
            long j2;
            long j3;
            int i3;
            char charAt;
            long j4 = i;
            long j5 = i2 + j4;
            int length = charSequence.length();
            if (length > i2 || bArr.length - i2 < i) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(length - 1) + " at index " + (i + i2));
            }
            int i4 = 0;
            while (true) {
                c = 128;
                j = 1;
                if (i4 >= length || (charAt = charSequence.charAt(i4)) >= 128) {
                    break;
                }
                UnsafeUtil.putByte(bArr, j4, (byte) charAt);
                i4++;
                j4 = 1 + j4;
            }
            if (i4 == length) {
                return (int) j4;
            }
            while (i4 < length) {
                char charAt2 = charSequence.charAt(i4);
                if (charAt2 < c && j4 < j5) {
                    long j6 = j4 + j;
                    UnsafeUtil.putByte(bArr, j4, (byte) charAt2);
                    j3 = j;
                    j2 = j6;
                } else if (charAt2 < 2048 && j4 <= (-2) + j5) {
                    long j7 = j4 + j;
                    UnsafeUtil.putByte(bArr, j4, (byte) ((charAt2 >>> 6) | 960));
                    UnsafeUtil.putByte(bArr, j7, (byte) ((charAt2 & '?') | 128));
                    j2 = j7 + j;
                    j3 = j;
                } else if ((charAt2 < 55296 || charAt2 > 57343) && j4 <= (-3) + j5) {
                    long j8 = j4 + j;
                    UnsafeUtil.putByte(bArr, j4, (byte) ((charAt2 >>> '\f') | 480));
                    long j9 = j8 + j;
                    UnsafeUtil.putByte(bArr, j8, (byte) (((charAt2 >>> 6) & 63) | 128));
                    UnsafeUtil.putByte(bArr, j9, (byte) ((charAt2 & '?') | 128));
                    j2 = j9 + 1;
                    j3 = 1;
                } else if (j4 <= (-4) + j5) {
                    int i5 = i4 + 1;
                    if (i5 != length) {
                        char charAt3 = charSequence.charAt(i5);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            long j10 = j4 + 1;
                            UnsafeUtil.putByte(bArr, j4, (byte) ((codePoint >>> 18) | AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_RESTORE_USER_DATA_VALUE));
                            long j11 = j10 + 1;
                            UnsafeUtil.putByte(bArr, j10, (byte) (((codePoint >>> 12) & 63) | 128));
                            long j12 = j11 + 1;
                            UnsafeUtil.putByte(bArr, j11, (byte) (((codePoint >>> 6) & 63) | 128));
                            j3 = 1;
                            j2 = j12 + 1;
                            UnsafeUtil.putByte(bArr, j12, (byte) ((codePoint & 63) | 128));
                            i4 = i5;
                        } else {
                            i4 = i5;
                        }
                    }
                    throw new UnpairedSurrogateException(i4 - 1, length);
                } else if (charAt2 >= 55296 && charAt2 <= 57343 && ((i3 = i4 + 1) == length || !Character.isSurrogatePair(charAt2, charSequence.charAt(i3)))) {
                    throw new UnpairedSurrogateException(i4, length);
                } else {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + j4);
                }
                i4++;
                c = 128;
                long j13 = j3;
                j4 = j2;
                j = j13;
            }
            return (int) j4;
        }

        @Override // com.google.protobuf.Utf8.Processor
        int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3) {
            byte b = 0;
            if ((i2 | i3 | (bArr.length - i3)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
            }
            long j = i2;
            long j2 = i3;
            if (i != 0) {
                if (j >= j2) {
                    return i;
                }
                byte b2 = (byte) i;
                if (b2 < -32) {
                    if (b2 >= -62) {
                        long j3 = 1 + j;
                        if (UnsafeUtil.getByte(bArr, j) <= -65) {
                            j = j3;
                        }
                    }
                    return -1;
                } else if (b2 < -16) {
                    byte b3 = (byte) ((i >> 8) ^ (-1));
                    if (b3 == 0) {
                        long j4 = j + 1;
                        b3 = UnsafeUtil.getByte(bArr, j);
                        if (j4 >= j2) {
                            return Utf8.incompleteStateFor(b2, b3);
                        }
                        j = j4;
                    }
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        long j5 = j + 1;
                        if (UnsafeUtil.getByte(bArr, j) <= -65) {
                            j = j5;
                        }
                    }
                    return -1;
                } else {
                    byte b4 = (byte) ((i >> 8) ^ (-1));
                    if (b4 == 0) {
                        long j6 = j + 1;
                        b4 = UnsafeUtil.getByte(bArr, j);
                        if (j6 >= j2) {
                            return Utf8.incompleteStateFor(b2, b4);
                        }
                        j = j6;
                    } else {
                        b = (byte) (i >> 16);
                    }
                    if (b == 0) {
                        long j7 = j + 1;
                        b = UnsafeUtil.getByte(bArr, j);
                        if (j7 >= j2) {
                            return Utf8.incompleteStateFor(b2, b4, b);
                        }
                        j = j7;
                    }
                    if (b4 <= -65 && (((b2 << Ascii.FS) + (b4 + 112)) >> 30) == 0 && b <= -65) {
                        long j8 = j + 1;
                        if (UnsafeUtil.getByte(bArr, j) <= -65) {
                            j = j8;
                        }
                    }
                    return -1;
                }
            }
            return partialIsValidUtf8(bArr, j, (int) (j2 - j));
        }

        @Override // com.google.protobuf.Utf8.Processor
        int partialIsValidUtf8Direct(int i, ByteBuffer byteBuffer, int i2, int i3) {
            byte b = 0;
            if ((i2 | i3 | (byteBuffer.limit() - i3)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i2), Integer.valueOf(i3)));
            }
            long addressOffset = UnsafeUtil.addressOffset(byteBuffer) + i2;
            long j = (i3 - i2) + addressOffset;
            if (i != 0) {
                if (addressOffset >= j) {
                    return i;
                }
                byte b2 = (byte) i;
                if (b2 < -32) {
                    if (b2 >= -62) {
                        long j2 = 1 + addressOffset;
                        if (UnsafeUtil.getByte(addressOffset) <= -65) {
                            addressOffset = j2;
                        }
                    }
                    return -1;
                } else if (b2 < -16) {
                    byte b3 = (byte) ((i >> 8) ^ (-1));
                    if (b3 == 0) {
                        long j3 = addressOffset + 1;
                        b3 = UnsafeUtil.getByte(addressOffset);
                        if (j3 >= j) {
                            return Utf8.incompleteStateFor(b2, b3);
                        }
                        addressOffset = j3;
                    }
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        long j4 = addressOffset + 1;
                        if (UnsafeUtil.getByte(addressOffset) <= -65) {
                            addressOffset = j4;
                        }
                    }
                    return -1;
                } else {
                    byte b4 = (byte) ((i >> 8) ^ (-1));
                    if (b4 == 0) {
                        long j5 = addressOffset + 1;
                        b4 = UnsafeUtil.getByte(addressOffset);
                        if (j5 >= j) {
                            return Utf8.incompleteStateFor(b2, b4);
                        }
                        addressOffset = j5;
                    } else {
                        b = (byte) (i >> 16);
                    }
                    if (b == 0) {
                        long j6 = addressOffset + 1;
                        b = UnsafeUtil.getByte(addressOffset);
                        if (j6 >= j) {
                            return Utf8.incompleteStateFor(b2, b4, b);
                        }
                        addressOffset = j6;
                    }
                    if (b4 <= -65 && (((b2 << Ascii.FS) + (b4 + 112)) >> 30) == 0 && b <= -65) {
                        long j7 = addressOffset + 1;
                        if (UnsafeUtil.getByte(addressOffset) <= -65) {
                            addressOffset = j7;
                        }
                    }
                    return -1;
                }
            }
            return partialIsValidUtf8(addressOffset, (int) (j - addressOffset));
        }

        private static int unsafeEstimateConsecutiveAscii(long j, int i) {
            if (i < 16) {
                return 0;
            }
            int i2 = (int) ((-j) & 7);
            int i3 = i2;
            while (i3 > 0) {
                long j2 = 1 + j;
                if (UnsafeUtil.getByte(j) >= 0) {
                    i3--;
                    j = j2;
                } else {
                    return i2 - i3;
                }
            }
            int i4 = i - i2;
            while (i4 >= 8 && (UnsafeUtil.getLong(j) & (-9187201950435737472L)) == 0) {
                j += 8;
                i4 -= 8;
            }
            return i - i4;
        }

        private static int unsafeIncompleteStateFor(byte[] bArr, int i, long j, int i2) {
            switch (i2) {
                case 0:
                    return Utf8.incompleteStateFor(i);
                case 1:
                    return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(bArr, j));
                case 2:
                    return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(bArr, j), UnsafeUtil.getByte(bArr, j + 1));
                default:
                    throw new AssertionError();
            }
        }

        private static int unsafeEstimateConsecutiveAscii(byte[] bArr, long j, int i) {
            int i2 = 0;
            if (i < 16) {
                return 0;
            }
            int i3 = 8 - (((int) j) & 7);
            while (i2 < i3) {
                long j2 = 1 + j;
                if (UnsafeUtil.getByte(bArr, j) >= 0) {
                    i2++;
                    j = j2;
                } else {
                    return i2;
                }
            }
            while (true) {
                int i4 = i2 + 8;
                if (i4 > i || (UnsafeUtil.getLong(bArr, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + j) & (-9187201950435737472L)) != 0) {
                    break;
                }
                j += 8;
                i2 = i4;
            }
            while (i2 < i) {
                long j3 = j + 1;
                if (UnsafeUtil.getByte(bArr, j) >= 0) {
                    i2++;
                    j = j3;
                } else {
                    return i2;
                }
            }
            return i;
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x003c, code lost:
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static int partialIsValidUtf8(long j, int i) {
            long j2;
            int unsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(j, i);
            long j3 = j + unsafeEstimateConsecutiveAscii;
            int i2 = i - unsafeEstimateConsecutiveAscii;
            while (true) {
                byte b = 0;
                while (true) {
                    if (i2 <= 0) {
                        break;
                    }
                    long j4 = j3 + 1;
                    b = UnsafeUtil.getByte(j3);
                    if (b < 0) {
                        j3 = j4;
                        break;
                    }
                    i2--;
                    j3 = j4;
                }
                if (i2 == 0) {
                    return 0;
                }
                int i3 = i2 - 1;
                if (b >= -32) {
                    if (b >= -16) {
                        if (i3 >= 3) {
                            i2 = i3 - 3;
                            long j5 = j3 + 1;
                            byte b2 = UnsafeUtil.getByte(j3);
                            if (b2 > -65 || (((b << Ascii.FS) + (b2 + 112)) >> 30) != 0) {
                                break;
                            }
                            long j6 = j5 + 1;
                            if (UnsafeUtil.getByte(j5) > -65) {
                                break;
                            }
                            j2 = 1 + j6;
                            if (UnsafeUtil.getByte(j6) > -65) {
                                break;
                            }
                        } else {
                            return unsafeIncompleteStateFor(j3, b, i3);
                        }
                    } else if (i3 >= 2) {
                        i2 = i3 - 2;
                        long j7 = j3 + 1;
                        byte b3 = UnsafeUtil.getByte(j3);
                        if (b3 > -65 || ((b == -32 && b3 < -96) || (b == -19 && b3 >= -96))) {
                            break;
                        }
                        j2 = 1 + j7;
                        if (UnsafeUtil.getByte(j7) > -65) {
                            break;
                        }
                    } else {
                        return unsafeIncompleteStateFor(j3, b, i3);
                    }
                } else if (i3 != 0) {
                    i2 = i3 - 1;
                    if (b < -62) {
                        break;
                    }
                    j2 = 1 + j3;
                    if (UnsafeUtil.getByte(j3) > -65) {
                        break;
                    }
                } else {
                    return b;
                }
                j3 = j2;
            }
            return -1;
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x003c, code lost:
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static int partialIsValidUtf8(byte[] bArr, long j, int i) {
            long j2;
            int unsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(bArr, j, i);
            int i2 = i - unsafeEstimateConsecutiveAscii;
            long j3 = j + unsafeEstimateConsecutiveAscii;
            while (true) {
                byte b = 0;
                while (true) {
                    if (i2 <= 0) {
                        break;
                    }
                    long j4 = j3 + 1;
                    b = UnsafeUtil.getByte(bArr, j3);
                    if (b < 0) {
                        j3 = j4;
                        break;
                    }
                    i2--;
                    j3 = j4;
                }
                if (i2 == 0) {
                    return 0;
                }
                int i3 = i2 - 1;
                if (b >= -32) {
                    if (b >= -16) {
                        if (i3 >= 3) {
                            i2 = i3 - 3;
                            long j5 = j3 + 1;
                            byte b2 = UnsafeUtil.getByte(bArr, j3);
                            if (b2 > -65 || (((b << Ascii.FS) + (b2 + 112)) >> 30) != 0) {
                                break;
                            }
                            long j6 = j5 + 1;
                            if (UnsafeUtil.getByte(bArr, j5) > -65) {
                                break;
                            }
                            j2 = 1 + j6;
                            if (UnsafeUtil.getByte(bArr, j6) > -65) {
                                break;
                            }
                        } else {
                            return unsafeIncompleteStateFor(bArr, b, j3, i3);
                        }
                    } else if (i3 >= 2) {
                        i2 = i3 - 2;
                        long j7 = j3 + 1;
                        byte b3 = UnsafeUtil.getByte(bArr, j3);
                        if (b3 > -65 || ((b == -32 && b3 < -96) || (b == -19 && b3 >= -96))) {
                            break;
                        }
                        j2 = 1 + j7;
                        if (UnsafeUtil.getByte(bArr, j7) > -65) {
                            break;
                        }
                    } else {
                        return unsafeIncompleteStateFor(bArr, b, j3, i3);
                    }
                } else if (i3 != 0) {
                    i2 = i3 - 1;
                    if (b < -62) {
                        break;
                    }
                    j2 = 1 + j3;
                    if (UnsafeUtil.getByte(bArr, j3) > -65) {
                        break;
                    }
                } else {
                    return b;
                }
                j3 = j2;
            }
            return -1;
        }
    }

    static {
        Processor safeProcessor;
        if (UnsafeProcessor.isAvailable() && !Android.isOnAndroidDevice()) {
            safeProcessor = new UnsafeProcessor();
        } else {
            safeProcessor = new SafeProcessor();
        }
        processor = safeProcessor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String decodeUtf8(ByteBuffer byteBuffer, int i, int i2) {
        return processor.decodeUtf8(byteBuffer, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int encode(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return processor.encodeUtf8(charSequence, bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int i2 = length;
        while (true) {
            if (i < length) {
                char charAt = charSequence.charAt(i);
                if (charAt < 2048) {
                    i2 += (127 - charAt) >>> 31;
                    i++;
                } else {
                    i2 += encodedLengthGeneral(charSequence, i);
                    break;
                }
            } else {
                break;
            }
        }
        if (i2 < length) {
            throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (i2 + 4294967296L));
        }
        return i2;
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (charAt >= 55296 && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i) >= 65536) {
                        i++;
                    } else {
                        throw new UnpairedSurrogateException(i, length);
                    }
                }
            }
            i++;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidUtf8(ByteBuffer byteBuffer) {
        return processor.isValidUtf8(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int partialIsValidUtf8(int i, ByteBuffer byteBuffer, int i2, int i3) {
        return processor.partialIsValidUtf8(i, byteBuffer, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String decodeUtf8(byte[] bArr, int i, int i2) {
        return processor.decodeUtf8(bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int estimateConsecutiveAscii(ByteBuffer byteBuffer, int i, int i2) {
        int i3 = i2 - 7;
        int i4 = i;
        while (i4 < i3 && (byteBuffer.getLong(i4) & (-9187201950435737472L)) == 0) {
            i4 += 8;
        }
        return i4 - i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidUtf8(byte[] bArr) {
        return processor.isValidUtf8(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3) {
        return processor.partialIsValidUtf8(i, bArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidUtf8(byte[] bArr, int i, int i2) {
        return processor.isValidUtf8(bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(ByteBuffer byteBuffer, int i, int i2, int i3) {
        switch (i3) {
            case 0:
                return incompleteStateFor(i);
            case 1:
                return incompleteStateFor(i, byteBuffer.get(i2));
            case 2:
                return incompleteStateFor(i, byteBuffer.get(i2), byteBuffer.get(i2 + 1));
            default:
                throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        switch (i2 - i) {
            case 0:
                return incompleteStateFor(b);
            case 1:
                return incompleteStateFor(b, bArr[i]);
            case 2:
                return incompleteStateFor(b, bArr[i], bArr[i + 1]);
            default:
                throw new AssertionError();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SafeProcessor extends Processor {
        SafeProcessor() {
        }

        private static int partialIsValidUtf8NonAscii(byte[] bArr, int i, int i2) {
            while (i < i2) {
                int i3 = i + 1;
                byte b = bArr[i];
                if (b >= 0) {
                    i = i3;
                } else if (b < -32) {
                    if (i3 < i2) {
                        if (b >= -62) {
                            i = i3 + 1;
                            if (bArr[i3] > -65) {
                            }
                        }
                        return -1;
                    }
                    return b;
                } else if (b < -16) {
                    if (i3 < i2 - 1) {
                        int i4 = i3 + 1;
                        byte b2 = bArr[i3];
                        if (b2 <= -65 && ((b != -32 || b2 >= -96) && (b != -19 || b2 < -96))) {
                            i = i4 + 1;
                            if (bArr[i4] > -65) {
                            }
                        }
                        return -1;
                    }
                    return Utf8.incompleteStateFor(bArr, i3, i2);
                } else if (i3 < i2 - 2) {
                    int i5 = i3 + 1;
                    byte b3 = bArr[i3];
                    if (b3 <= -65 && (((b << Ascii.FS) + (b3 + 112)) >> 30) == 0) {
                        int i6 = i5 + 1;
                        if (bArr[i5] <= -65) {
                            int i7 = i6 + 1;
                            if (bArr[i6] <= -65) {
                                i = i7;
                            }
                        }
                    }
                    return -1;
                } else {
                    return Utf8.incompleteStateFor(bArr, i3, i2);
                }
            }
            return 0;
        }

        @Override // com.google.protobuf.Utf8.Processor
        String decodeUtf8(byte[] bArr, int i, int i2) {
            if ((i | i2 | ((bArr.length - i) - i2)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (i < i3) {
                byte b = bArr[i];
                if (!DecodeUtil.isOneByte(b)) {
                    break;
                }
                i++;
                DecodeUtil.handleOneByte(b, cArr, i4);
                i4++;
            }
            int i5 = i4;
            while (i < i3) {
                int i6 = i + 1;
                byte b2 = bArr[i];
                if (DecodeUtil.isOneByte(b2)) {
                    DecodeUtil.handleOneByte(b2, cArr, i5);
                    i = i6;
                    i5++;
                    while (i < i3) {
                        byte b3 = bArr[i];
                        if (!DecodeUtil.isOneByte(b3)) {
                            break;
                        }
                        i++;
                        DecodeUtil.handleOneByte(b3, cArr, i5);
                        i5++;
                    }
                } else if (DecodeUtil.isTwoBytes(b2)) {
                    if (i6 < i3) {
                        DecodeUtil.handleTwoBytes(b2, bArr[i6], cArr, i5);
                        i = i6 + 1;
                        i5++;
                    } else {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                } else if (DecodeUtil.isThreeBytes(b2)) {
                    if (i6 < i3 - 1) {
                        int i7 = i6 + 1;
                        DecodeUtil.handleThreeBytes(b2, bArr[i6], bArr[i7], cArr, i5);
                        i = i7 + 1;
                        i5++;
                    } else {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                } else if (i6 < i3 - 2) {
                    int i8 = i6 + 1;
                    int i9 = i8 + 1;
                    DecodeUtil.handleFourBytes(b2, bArr[i6], bArr[i8], bArr[i9], cArr, i5);
                    i5 += 2;
                    i = i9 + 1;
                } else {
                    throw InvalidProtocolBufferException.invalidUtf8();
                }
            }
            return new String(cArr, 0, i5);
        }

        @Override // com.google.protobuf.Utf8.Processor
        String decodeUtf8Direct(ByteBuffer byteBuffer, int i, int i2) {
            return decodeUtf8Default(byteBuffer, i, i2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
            return r10 + r0;
         */
        @Override // com.google.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2) {
            int i3;
            int i4;
            char charAt;
            int length = charSequence.length();
            int i5 = i2 + i;
            int i6 = 0;
            while (i6 < length && (i4 = i6 + i) < i5 && (charAt = charSequence.charAt(i6)) < 128) {
                bArr[i4] = (byte) charAt;
                i6++;
            }
            int i7 = i + i6;
            while (i6 < length) {
                char charAt2 = charSequence.charAt(i6);
                if (charAt2 < 128 && i7 < i5) {
                    bArr[i7] = (byte) charAt2;
                    i7++;
                } else if (charAt2 < 2048 && i7 <= i5 - 2) {
                    int i8 = i7 + 1;
                    bArr[i7] = (byte) ((charAt2 >>> 6) | 960);
                    i7 = i8 + 1;
                    bArr[i8] = (byte) ((charAt2 & '?') | 128);
                } else if ((charAt2 < 55296 || charAt2 > 57343) && i7 <= i5 - 3) {
                    int i9 = i7 + 1;
                    bArr[i7] = (byte) ((charAt2 >>> '\f') | 480);
                    int i10 = i9 + 1;
                    bArr[i9] = (byte) (((charAt2 >>> 6) & 63) | 128);
                    bArr[i10] = (byte) ((charAt2 & '?') | 128);
                    i7 = i10 + 1;
                } else if (i7 <= i5 - 4) {
                    int i11 = i6 + 1;
                    if (i11 != charSequence.length()) {
                        char charAt3 = charSequence.charAt(i11);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            int i12 = i7 + 1;
                            bArr[i7] = (byte) ((codePoint >>> 18) | AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_RESTORE_USER_DATA_VALUE);
                            int i13 = i12 + 1;
                            bArr[i12] = (byte) (((codePoint >>> 12) & 63) | 128);
                            int i14 = i13 + 1;
                            bArr[i13] = (byte) (((codePoint >>> 6) & 63) | 128);
                            i7 = i14 + 1;
                            bArr[i14] = (byte) ((codePoint & 63) | 128);
                            i6 = i11;
                        } else {
                            i6 = i11;
                        }
                    }
                    throw new UnpairedSurrogateException(i6 - 1, length);
                } else if (charAt2 >= 55296 && charAt2 <= 57343 && ((i3 = i6 + 1) == charSequence.length() || !Character.isSurrogatePair(charAt2, charSequence.charAt(i3)))) {
                    throw new UnpairedSurrogateException(i6, length);
                } else {
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i7);
                }
                i6++;
            }
            return i7;
        }

        /* JADX WARN: Code restructure failed: missing block: B:57:0x008d, code lost:
            if (r8[r7] <= (-65)) goto L54;
         */
        @Override // com.google.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3) {
            byte b;
            int i4;
            if (i != 0) {
                if (i2 >= i3) {
                    return i;
                }
                byte b2 = (byte) i;
                if (b2 < -32) {
                    if (b2 >= -62) {
                        int i5 = i2 + 1;
                        if (bArr[i2] <= -65) {
                            i2 = i5;
                        }
                    }
                    return -1;
                } else if (b2 < -16) {
                    byte b3 = (byte) ((i >> 8) ^ (-1));
                    if (b3 == 0) {
                        int i6 = i2 + 1;
                        byte b4 = bArr[i2];
                        if (i6 >= i3) {
                            return Utf8.incompleteStateFor(b2, b4);
                        }
                        i2 = i6;
                        b3 = b4;
                    }
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        int i7 = i2 + 1;
                        if (bArr[i2] <= -65) {
                            i2 = i7;
                        }
                    }
                    return -1;
                } else {
                    byte b5 = (byte) ((i >> 8) ^ (-1));
                    if (b5 == 0) {
                        i4 = i2 + 1;
                        b5 = bArr[i2];
                        if (i4 >= i3) {
                            return Utf8.incompleteStateFor(b2, b5);
                        }
                        b = 0;
                    } else {
                        b = (byte) (i >> 16);
                        i4 = i2;
                    }
                    if (b == 0) {
                        int i8 = i4 + 1;
                        byte b6 = bArr[i4];
                        if (i8 >= i3) {
                            return Utf8.incompleteStateFor(b2, b5, b6);
                        }
                        b = b6;
                        i4 = i8;
                    }
                    if (b5 <= -65 && (((b2 << Ascii.FS) + (b5 + 112)) >> 30) == 0 && b <= -65) {
                        i2 = i4 + 1;
                    }
                    return -1;
                }
            }
            return partialIsValidUtf8(bArr, i2, i3);
        }

        @Override // com.google.protobuf.Utf8.Processor
        int partialIsValidUtf8Direct(int i, ByteBuffer byteBuffer, int i2, int i3) {
            return partialIsValidUtf8Default(i, byteBuffer, i2, i3);
        }

        private static int partialIsValidUtf8(byte[] bArr, int i, int i2) {
            while (i < i2 && bArr[i] >= 0) {
                i++;
            }
            if (i >= i2) {
                return 0;
            }
            return partialIsValidUtf8NonAscii(bArr, i, i2);
        }
    }
}
