package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
final class Platform {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isInstanceOfThrowableClass(Throwable th, Class cls) {
        return cls.isInstance(th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void restoreInterruptIfIsInterruptedException(Throwable th) {
        Preconditions.checkNotNull(th);
        if (th instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        }
    }
}
