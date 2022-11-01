package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArrayUtils {
    public static boolean contains(int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(Object[] objArr, Object obj) {
        int length = objArr != null ? objArr.length : 0;
        for (int i = 0; i < length; i++) {
            if (Objects.equal(objArr[i], obj)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean contains(Object[] objArr, Object obj) {
        return indexOf(objArr, obj) >= 0;
    }
}
