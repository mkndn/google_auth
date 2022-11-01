package com.google.common.flogger.util;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ThrowableStackGetter implements StackGetter {
    @Override // com.google.common.flogger.util.StackGetter
    public StackTraceElement callerOf(Class cls, int i) {
        Checks.checkArgument(i >= 0, "skipFrames must be >= 0");
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int findCallerIndex = findCallerIndex(stackTrace, cls, i + 1);
        if (findCallerIndex != -1) {
            return stackTrace[findCallerIndex];
        }
        return null;
    }

    @Override // com.google.common.flogger.util.StackGetter
    public StackTraceElement[] getStackForCaller(Class cls, int i, int i2) {
        Checks.checkArgument(i == -1 || i > 0, "maxDepth must be > 0 or -1");
        Checks.checkArgument(i2 >= 0, "skipFrames must be >= 0");
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int findCallerIndex = findCallerIndex(stackTrace, cls, i2 + 1);
        if (findCallerIndex == -1) {
            return new StackTraceElement[0];
        }
        int length = stackTrace.length - findCallerIndex;
        if (i <= 0 || i >= length) {
            i = length;
        }
        StackTraceElement[] stackTraceElementArr = new StackTraceElement[i];
        System.arraycopy(stackTrace, findCallerIndex, stackTraceElementArr, 0, i);
        return stackTraceElementArr;
    }

    private int findCallerIndex(StackTraceElement[] stackTraceElementArr, Class cls, int i) {
        String name = cls.getName();
        boolean z = false;
        while (i < stackTraceElementArr.length) {
            if (stackTraceElementArr[i].getClassName().equals(name)) {
                z = true;
            } else if (z) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
