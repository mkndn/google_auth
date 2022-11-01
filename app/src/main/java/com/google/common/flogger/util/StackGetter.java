package com.google.common.flogger.util;

/* compiled from: PG */
/* loaded from: classes.dex */
interface StackGetter {
    StackTraceElement callerOf(Class cls, int i);

    StackTraceElement[] getStackForCaller(Class cls, int i, int i2);
}
