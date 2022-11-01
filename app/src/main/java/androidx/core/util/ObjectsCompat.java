package androidx.core.util;

import android.os.Build;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ObjectsCompat {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class Api19Impl {
        static boolean equals(Object obj, Object obj2) {
            return ObjectsCompat$Api19Impl$$ExternalSyntheticBackport0.m(obj, obj2);
        }

        static int hash(Object... objArr) {
            return Arrays.hashCode(objArr);
        }
    }

    public static boolean equals(Object obj, Object obj2) {
        if (Build.VERSION.SDK_INT >= 19) {
            return Api19Impl.equals(obj, obj2);
        }
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int hash(Object... objArr) {
        if (Build.VERSION.SDK_INT >= 19) {
            return Api19Impl.hash(objArr);
        }
        return Arrays.hashCode(objArr);
    }

    public static Object requireNonNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    public static Object requireNonNull(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
        return obj;
    }
}
