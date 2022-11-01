package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.MethodInvocation;

/* compiled from: PG */
/* loaded from: classes.dex */
final class MethodInvocationMessage {
    final long batchPeriodMillis;
    final int configVersion;
    final int maxMethodsInBatch;
    final MethodInvocation methodInvocation;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MethodInvocationMessage(MethodInvocation methodInvocation, int i, long j, int i2) {
        this.methodInvocation = methodInvocation;
        this.configVersion = i;
        this.batchPeriodMillis = j;
        this.maxMethodsInBatch = i2;
    }
}
