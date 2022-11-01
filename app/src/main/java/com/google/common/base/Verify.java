package com.google.common.base;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Verify {
    public static Object verifyNotNull(Object obj) {
        return verifyNotNull(obj, "expected a non-null reference", new Object[0]);
    }

    public static Object verifyNotNull(Object obj, String str, Object... objArr) {
        if (obj == null) {
            throw new VerifyException(Strings.lenientFormat(str, objArr));
        }
        return obj;
    }
}
