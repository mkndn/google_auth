package com.google.android.gms.clearcut.internal;

import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FarmHashFingerprint64 {
    private static long fingerprint(byte[] bArr, int i, int i2) {
        if (i2 <= 32) {
            if (i2 <= 16) {
                return hashLength0to16(bArr, i, i2);
            }
            return hashLength17to32(bArr, i, i2);
        } else if (i2 <= 64) {
            return hashLength33To64(bArr, i, i2);
        } else {
            return hashLength65Plus(bArr, i, i2);
        }
    }

    public static long hashBytes(byte[] bArr) {
        return hashBytes(bArr, 0, bArr.length);
    }

    private static long hashLength0to16(byte[] bArr, int i, int i2) {
        if (i2 >= 8) {
            long j = (i2 + i2) - 7286425919675154353L;
            long load64 = load64(bArr, i) - 7286425919675154353L;
            long load642 = load64(bArr, (i + i2) - 8);
            return hashLength16((Long.rotateRight(load642, 37) * j) + load64, (Long.rotateRight(load64, 25) + load642) * j, j);
        } else if (i2 >= 4) {
            return hashLength16(i2 + ((load32(bArr, i) & 4294967295L) << 3), load32(bArr, (i + i2) - 4) & 4294967295L, (i2 + i2) - 7286425919675154353L);
        } else {
            if (i2 > 0) {
                return shiftMix(((((bArr[i + (i2 - 1)] & 255) << 2) + i2) * (-4348849565147123417L)) ^ (((bArr[i] & UnsignedBytes.MAX_VALUE) + ((bArr[i + (i2 >> 1)] & UnsignedBytes.MAX_VALUE) << 8)) * (-7286425919675154353L))) * (-7286425919675154353L);
            }
            return -7286425919675154353L;
        }
    }

    private static long hashLength16(long j, long j2, long j3) {
        long j4 = (j ^ j2) * j3;
        long j5 = ((j4 ^ (j4 >>> 47)) ^ j2) * j3;
        return (j5 ^ (j5 >>> 47)) * j3;
    }

    private static long hashLength17to32(byte[] bArr, int i, int i2) {
        long j = (i2 + i2) - 7286425919675154353L;
        long load64 = load64(bArr, i) * (-5435081209227447693L);
        long load642 = load64(bArr, i + 8);
        int i3 = i + i2;
        long load643 = load64(bArr, i3 - 8) * j;
        return hashLength16((load64(bArr, i3 - 16) * (-7286425919675154353L)) + Long.rotateRight(load64 + load642, 43) + Long.rotateRight(load643, 30), load64 + Long.rotateRight(load642 - 7286425919675154353L, 18) + load643, j);
    }

    private static long hashLength33To64(byte[] bArr, int i, int i2) {
        long j = (i2 + i2) - 7286425919675154353L;
        long load64 = load64(bArr, i) * (-7286425919675154353L);
        long load642 = load64(bArr, i + 8);
        int i3 = i + i2;
        long load643 = load64(bArr, i3 - 8) * j;
        long rotateRight = Long.rotateRight(load64 + load642, 43) + Long.rotateRight(load643, 30) + (load64(bArr, i3 - 16) * (-7286425919675154353L));
        long hashLength16 = hashLength16(rotateRight, load643 + Long.rotateRight(load642 - 7286425919675154353L, 18) + load64, j);
        long load644 = load64(bArr, i + 16) * j;
        long load645 = load64(bArr, i + 24);
        long load646 = (rotateRight + load64(bArr, i3 - 32)) * j;
        return hashLength16(((hashLength16 + load64(bArr, i3 - 24)) * j) + Long.rotateRight(load644 + load645, 43) + Long.rotateRight(load646, 30), load644 + Long.rotateRight(load645 + load64, 18) + load646, j);
    }

    private static int load32(byte[] bArr, int i) {
        return ((bArr[i + 3] & UnsignedBytes.MAX_VALUE) << 24) | (bArr[i] & UnsignedBytes.MAX_VALUE) | ((bArr[i + 1] & UnsignedBytes.MAX_VALUE) << 8) | ((bArr[i + 2] & UnsignedBytes.MAX_VALUE) << 16);
    }

    private static long load64(byte[] bArr, int i) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr, i, 8);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        return wrap.getLong();
    }

    private static long shiftMix(long j) {
        return j ^ (j >>> 47);
    }

    private static void weakHashLength32WithSeeds(byte[] bArr, int i, long j, long j2, long[] jArr) {
        long load64 = load64(bArr, i);
        long load642 = load64(bArr, i + 8);
        long load643 = load64(bArr, i + 16);
        long load644 = load64(bArr, i + 24);
        long j3 = j + load64;
        long rotateRight = Long.rotateRight(j2 + j3 + load644, 21);
        long j4 = load642 + j3 + load643;
        long rotateRight2 = Long.rotateRight(j4, 44);
        jArr[0] = j4 + load644;
        jArr[1] = rotateRight + rotateRight2 + j3;
    }

    private static long hashBytes(byte[] bArr, int i, int i2) {
        int i3;
        if (i >= 0 && (i3 = i + i2) >= i && i3 <= bArr.length) {
            return fingerprint(bArr, i, i2);
        }
        throw new IndexOutOfBoundsException("Out of bound index with offput: " + i + " and length: " + i2);
    }

    private static long hashLength65Plus(byte[] bArr, int i, int i2) {
        long shiftMix = shiftMix(-7956866745689871395L) * (-7286425919675154353L);
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        long load64 = load64(bArr, i) + 95310865018149119L;
        int i3 = i2 - 1;
        int i4 = i + ((i3 / 64) * 64);
        int i5 = i3 & 63;
        int i6 = (i4 + i5) - 63;
        long j = 2480279821605975764L;
        int i7 = i;
        while (true) {
            long rotateRight = Long.rotateRight(load64 + j + jArr[0] + load64(bArr, i7 + 8), 37);
            long rotateRight2 = Long.rotateRight(j + jArr[1] + load64(bArr, i7 + 48), 42);
            long j2 = (rotateRight * (-5435081209227447693L)) ^ jArr2[1];
            long load642 = (rotateRight2 * (-5435081209227447693L)) + jArr[0] + load64(bArr, i7 + 40);
            long rotateRight3 = Long.rotateRight(shiftMix + jArr2[0], 33) * (-5435081209227447693L);
            weakHashLength32WithSeeds(bArr, i7, jArr[1] * (-5435081209227447693L), j2 + jArr2[0], jArr);
            weakHashLength32WithSeeds(bArr, i7 + 32, rotateRight3 + jArr2[1], load642 + load64(bArr, i7 + 16), jArr2);
            i7 += 64;
            if (i7 != i4) {
                shiftMix = j2;
                j = load642;
                load64 = rotateRight3;
            } else {
                long j3 = j2 & 255;
                long j4 = (j3 + j3) - 5435081209227447693L;
                long j5 = jArr2[0] + i5;
                jArr2[0] = j5;
                long j6 = jArr[0] + j5;
                jArr[0] = j6;
                jArr2[0] = jArr2[0] + j6;
                long rotateRight4 = Long.rotateRight(rotateRight3 + load642 + jArr[0] + load64(bArr, i6 + 8), 37);
                long rotateRight5 = Long.rotateRight(load642 + jArr[1] + load64(bArr, i6 + 48), 42);
                long j7 = (rotateRight4 * j4) ^ (jArr2[1] * 9);
                long load643 = (rotateRight5 * j4) + (jArr[0] * 9) + load64(bArr, i6 + 40);
                long rotateRight6 = Long.rotateRight(j2 + jArr2[0], 33) * j4;
                weakHashLength32WithSeeds(bArr, i6, jArr[1] * j4, j7 + jArr2[0], jArr);
                weakHashLength32WithSeeds(bArr, i6 + 32, rotateRight6 + jArr2[1], load64(bArr, i6 + 16) + load643, jArr2);
                return hashLength16(hashLength16(jArr[0], jArr2[0], j4) + (shiftMix(load643) * (-4348849565147123417L)) + j7, hashLength16(jArr[1], jArr2[1], j4) + rotateRight6, j4);
            }
        }
    }
}
