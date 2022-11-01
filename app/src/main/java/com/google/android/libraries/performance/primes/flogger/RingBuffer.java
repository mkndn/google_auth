package com.google.android.libraries.performance.primes.flogger;

import java.util.concurrent.atomic.AtomicReferenceArray;

/* compiled from: PG */
/* loaded from: classes.dex */
final class RingBuffer {
    private final AtomicReferenceArray buffer;
    private final int overflowWraparound;
    private final int size;
    private volatile int volatileNext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RingBuffer(int i) {
        this.size = i;
        this.buffer = new AtomicReferenceArray(i);
        this.overflowWraparound = (Integer.MAX_VALUE % i) + i + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LogInvocation[] getSnapshot(int i, int i2) {
        int i3;
        if (i2 > 0) {
            int max = Math.max(i - Math.min(i2, this.size - 1), 0);
            int i4 = i - max;
            if (i4 <= 0) {
                return new LogInvocation[0];
            }
            LogInvocation[] logInvocationArr = new LogInvocation[i4];
            for (int i5 = 0; i5 < i4; i5++) {
                logInvocationArr[i5] = (LogInvocation) this.buffer.get((i5 + max) % this.size);
            }
            int i6 = this.volatileNext;
            if (i6 >= i) {
                i3 = i6 - i;
            } else {
                i3 = (i6 - this.overflowWraparound) + 1 + (Integer.MAX_VALUE - i);
            }
            int i7 = (i3 - (this.size - i4)) + 1;
            if (i7 >= i4) {
                return new LogInvocation[0];
            }
            if (i7 > 0) {
                int i8 = i4 - i7;
                LogInvocation[] logInvocationArr2 = new LogInvocation[i8];
                System.arraycopy(logInvocationArr, i7, logInvocationArr2, 0, i8);
                return logInvocationArr2;
            }
            return logInvocationArr;
        }
        return new LogInvocation[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTimestamp() {
        return this.volatileNext;
    }
}
