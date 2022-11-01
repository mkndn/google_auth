package com.google.android.gms.clearcut.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public class UnsignedMod {
    public static long mod(long j, long j2) {
        if (j >= 0) {
            return j % j2;
        }
        return (((Long.MAX_VALUE % j2) + 1) + ((j & Long.MAX_VALUE) % j2)) % j2;
    }
}
