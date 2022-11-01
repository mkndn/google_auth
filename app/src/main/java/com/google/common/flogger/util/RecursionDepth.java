package com.google.common.flogger.util;

import java.io.Closeable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RecursionDepth implements Closeable {
    private static final ThreadLocal holder = new ThreadLocal() { // from class: com.google.common.flogger.util.RecursionDepth.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public RecursionDepth initialValue() {
            return new RecursionDepth();
        }
    };
    private int value = 0;

    public static RecursionDepth enterLogStatement() {
        RecursionDepth recursionDepth = (RecursionDepth) holder.get();
        int i = recursionDepth.value + 1;
        recursionDepth.value = i;
        if (i == 0) {
            throw new AssertionError("Overflow of RecursionDepth (possible error in core library)");
        }
        return recursionDepth;
    }

    public static int getCurrentDepth() {
        return ((RecursionDepth) holder.get()).value;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        int i = this.value;
        if (i > 0) {
            this.value = i - 1;
            return;
        }
        throw new AssertionError("Mismatched calls to RecursionDepth (possible error in core library)");
    }

    public int getValue() {
        return this.value;
    }
}
