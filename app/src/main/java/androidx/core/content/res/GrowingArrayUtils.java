package androidx.core.content.res;

import java.lang.reflect.Array;

/* compiled from: PG */
/* loaded from: classes.dex */
final class GrowingArrayUtils {
    public static int[] append(int[] iArr, int i, int i2) {
        if (i + 1 > iArr.length) {
            int[] iArr2 = new int[growSize(i)];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            iArr = iArr2;
        }
        iArr[i] = i2;
        return iArr;
    }

    public static int growSize(int i) {
        if (i <= 4) {
            return 8;
        }
        return i + i;
    }

    public static Object[] append(Object[] objArr, int i, Object obj) {
        if (i + 1 > objArr.length) {
            Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), growSize(i));
            System.arraycopy(objArr, 0, objArr2, 0, i);
            objArr = objArr2;
        }
        objArr[i] = obj;
        return objArr;
    }
}
