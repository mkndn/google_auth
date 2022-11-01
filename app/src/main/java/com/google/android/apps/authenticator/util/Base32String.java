package com.google.android.apps.authenticator.util;

import com.google.common.collect.Maps;
import com.google.common.primitives.UnsignedBytes;
import java.util.Locale;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Base32String {
    private static final Map CHAR_MAP;
    private static final char[] DIGITS;
    private static final int MASK;
    private static final String SEPARATOR = "-";
    private static final int SHIFT;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class DecodingException extends Exception {
        public DecodingException(String str) {
            super(str);
        }
    }

    static {
        char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toCharArray();
        DIGITS = charArray;
        MASK = charArray.length - 1;
        SHIFT = Integer.numberOfTrailingZeros(charArray.length);
        CHAR_MAP = Maps.newHashMapWithExpectedSize(charArray.length);
        int i = 0;
        while (true) {
            char[] cArr = DIGITS;
            if (i < cArr.length) {
                CHAR_MAP.put(Character.valueOf(cArr[i]), Integer.valueOf(i));
                i++;
            } else {
                return;
            }
        }
    }

    private Base32String() {
    }

    public static byte[] decode(String str) {
        char[] charArray;
        String upperCase = str.trim().replaceAll(SEPARATOR, "").replaceAll(" ", "").replaceFirst("[=]*$", "").toUpperCase(Locale.US);
        if (upperCase.length() == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[(upperCase.length() * SHIFT) / 8];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (char c : upperCase.toCharArray()) {
            Map map = CHAR_MAP;
            if (map.containsKey(Character.valueOf(c))) {
                int i4 = SHIFT;
                i = (i << i4) | (((Integer) map.get(Character.valueOf(c))).intValue() & MASK);
                i2 += i4;
                if (i2 >= 8) {
                    i2 -= 8;
                    bArr[i3] = (byte) (i >> i2);
                    i3++;
                }
            } else {
                throw new DecodingException("Illegal character: " + c);
            }
        }
        return bArr;
    }

    public static String encode(byte[] bArr) {
        int length = bArr.length;
        if (length == 0) {
            return "";
        }
        if (length >= 268435456) {
            throw new IllegalArgumentException();
        }
        int i = SHIFT;
        StringBuilder sb = new StringBuilder((((length * 8) + i) - 1) / i);
        byte b = bArr[0];
        int i2 = 1;
        int i3 = 8;
        while (true) {
            if (i3 > 0 || i2 < length) {
                int i4 = SHIFT;
                if (i3 < i4) {
                    if (i2 < length) {
                        b = (b << 8) | (bArr[i2] & UnsignedBytes.MAX_VALUE);
                        i3 += 8;
                        i2++;
                    } else {
                        int i5 = i4 - i3;
                        b <<= i5;
                        i3 += i5;
                    }
                }
                sb.append(DIGITS[(b >> (i3 - i4)) & MASK]);
                i3 -= i4;
            } else {
                return sb.toString();
            }
        }
    }

    public static String tryNormalizeEncoded(String str) {
        try {
            return encode(decode(str));
        } catch (DecodingException e) {
            return str;
        }
    }
}
