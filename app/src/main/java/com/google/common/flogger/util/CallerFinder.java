package com.google.common.flogger.util;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CallerFinder {
    private static final String[] STACK_GETTER_IMPLEMENTATIONS = {"com.google.common.flogger.util.StackWalkerStackGetter", "com.google.common.flogger.util.JavaLangAccessStackGetter"};
    private static final StackGetter STACK_GETTER = getBestStackGetter();

    public static StackTraceElement findCallerOf(Class cls, int i) {
        Checks.checkNotNull(cls, "target");
        if (i < 0) {
            throw new IllegalArgumentException("skip count cannot be negative: " + i);
        }
        return STACK_GETTER.callerOf(cls, i + 1);
    }

    private static StackGetter getBestStackGetter() {
        for (String str : STACK_GETTER_IMPLEMENTATIONS) {
            StackGetter maybeCreateStackGetter = maybeCreateStackGetter(str);
            if (maybeCreateStackGetter != null) {
                return maybeCreateStackGetter;
            }
        }
        return new ThrowableStackGetter();
    }

    public static StackTraceElement[] getStackForCallerOf(Class cls, int i, int i2) {
        Checks.checkNotNull(cls, "target");
        if (i <= 0 && i != -1) {
            throw new IllegalArgumentException("invalid maximum depth: " + i);
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("skip count cannot be negative: " + i2);
        }
        return STACK_GETTER.getStackForCaller(cls, i, i2 + 1);
    }

    private static StackGetter maybeCreateStackGetter(String str) {
        try {
            return (StackGetter) Class.forName(str).asSubclass(StackGetter.class).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }
}
