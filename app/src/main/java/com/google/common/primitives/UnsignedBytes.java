package com.google.common.primitives;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UnsignedBytes {
    public static final byte MAX_POWER_OF_TWO = Byte.MIN_VALUE;
    public static final byte MAX_VALUE = -1;

    public static int toInt(byte b) {
        return b & MAX_VALUE;
    }
}
