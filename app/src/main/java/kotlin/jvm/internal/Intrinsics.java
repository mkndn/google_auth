package kotlin.jvm.internal;

import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Intrinsics {
    private Intrinsics() {
    }

    public static boolean areEqual(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static void checkNotNull(Object obj) {
        if (obj == null) {
            throwJavaNpe();
        }
    }

    public static void checkNotNullExpressionValue(Object obj, String str) {
        if (obj == null) {
            throw ((NullPointerException) sanitizeStackTrace(new NullPointerException(str + " must not be null")));
        }
    }

    public static void checkNotNullParameter(Object obj, String str) {
        if (obj == null) {
            throwParameterIsNullNPE(str);
        }
    }

    private static String createParameterIsNullExceptionMessage(String str) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        String className = stackTraceElement.getClassName();
        return "Parameter specified as non-null is null: method " + className + "." + stackTraceElement.getMethodName() + ", parameter " + str;
    }

    private static Throwable sanitizeStackTrace(Throwable th) {
        return sanitizeStackTrace(th, Intrinsics.class.getName());
    }

    public static String stringPlus(String str, Object obj) {
        return str + obj;
    }

    public static void throwJavaNpe() {
        throw ((NullPointerException) sanitizeStackTrace(new NullPointerException()));
    }

    private static void throwParameterIsNullNPE(String str) {
        throw ((NullPointerException) sanitizeStackTrace(new NullPointerException(createParameterIsNullExceptionMessage(str))));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Throwable sanitizeStackTrace(Throwable th, String str) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        th.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i + 1, length));
        return th;
    }

    public static void throwJavaNpe(String str) {
        throw ((NullPointerException) sanitizeStackTrace(new NullPointerException(str)));
    }

    public static void checkNotNull(Object obj, String str) {
        if (obj == null) {
            throwJavaNpe(str);
        }
    }
}
