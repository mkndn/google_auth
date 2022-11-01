package com.google.android.libraries.stitch.util;

import android.util.Log;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemProperties {
    private static final Method sGetBooleanMethod;
    private static final Method sGetIntMethod;
    private static final Method sGetLongMethod;
    private static final Method sGetStringMethod;

    public static String getString(String str, String str2) {
        try {
            String str3 = (String) sGetStringMethod.invoke(null, str, str2);
            if (str2 == null) {
                if ("".equals(str3)) {
                    return null;
                }
            }
            return str3;
        } catch (Exception e) {
            Log.e("SystemProperties", "get error", e);
            return str2;
        }
    }

    static {
        Method method;
        Method method2;
        Method method3;
        Class<?> cls;
        Method method4 = null;
        try {
            cls = Class.forName("android.os.SystemProperties");
            method = cls.getMethod("get", String.class, String.class);
            try {
                method2 = cls.getMethod("getInt", String.class, Integer.TYPE);
                try {
                    method3 = cls.getMethod("getLong", String.class, Long.TYPE);
                } catch (Exception e) {
                    e = e;
                    method3 = null;
                } catch (Throwable th) {
                    th = th;
                    method3 = null;
                    sGetStringMethod = method;
                    sGetIntMethod = method2;
                    sGetLongMethod = method3;
                    sGetBooleanMethod = null;
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                method2 = null;
                method3 = method2;
                e.printStackTrace();
                sGetStringMethod = method;
                sGetIntMethod = method2;
                sGetLongMethod = method3;
                sGetBooleanMethod = method4;
            } catch (Throwable th2) {
                th = th2;
                method2 = null;
                method3 = method2;
                sGetStringMethod = method;
                sGetIntMethod = method2;
                sGetLongMethod = method3;
                sGetBooleanMethod = null;
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            method = null;
            method2 = null;
        } catch (Throwable th3) {
            th = th3;
            method = null;
            method2 = null;
        }
        try {
            try {
                method4 = cls.getMethod("getBoolean", String.class, Boolean.TYPE);
            } catch (Throwable th4) {
                th = th4;
                sGetStringMethod = method;
                sGetIntMethod = method2;
                sGetLongMethod = method3;
                sGetBooleanMethod = null;
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
            sGetStringMethod = method;
            sGetIntMethod = method2;
            sGetLongMethod = method3;
            sGetBooleanMethod = method4;
        }
        sGetStringMethod = method;
        sGetIntMethod = method2;
        sGetLongMethod = method3;
        sGetBooleanMethod = method4;
    }
}
