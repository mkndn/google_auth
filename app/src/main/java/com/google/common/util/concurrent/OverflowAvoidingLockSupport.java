package com.google.common.util.concurrent;

import java.util.concurrent.locks.LockSupport;

/* compiled from: PG */
/* loaded from: classes.dex */
final class OverflowAvoidingLockSupport {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void parkNanos(Object obj, long j) {
        LockSupport.parkNanos(obj, Math.min(j, 2147483647999999999L));
    }
}
