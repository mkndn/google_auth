package com.google.android.libraries.performance.primes.flogger;

import com.google.devrel.primes.hashing.Hashing;
import java.util.ArrayList;
import java.util.Collections;
import java.util.WeakHashMap;
import javax.inject.Inject;
import javax.inject.Singleton;
import logs.proto.wireless.performance.mobile.SystemHealthProto$DebugLogs;

/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class RecentLogs {
    private final WeakHashMap bufferCache;
    private final ThreadLocal threadBuffer;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LogAndThread {
        final LogInvocation log;
        final int thread;

        LogAndThread(LogInvocation logInvocation, int i) {
            this.log = logInvocation;
            this.thread = i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TimestampCollection {
        static final TimestampCollection EMPTY = new TimestampCollection(new RingBuffer[0]);
        final RingBuffer[] buffers;
        final int[] timestamps;

        TimestampCollection(RingBuffer[] ringBufferArr) {
            this.buffers = ringBufferArr;
            this.timestamps = new int[ringBufferArr.length];
            for (int i = 0; i < ringBufferArr.length; i++) {
                this.timestamps[i] = ringBufferArr[i].getTimestamp();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public RecentLogs() {
        this(20);
    }

    private Long getMessageId(LogInvocation logInvocation) {
        String className = logInvocation.getClassName();
        String methodName = logInvocation.getMethodName();
        return Hashing.hash(className + "." + methodName + ":" + logInvocation.getLineNumber());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$getSnapshot$0(LogAndThread logAndThread, LogAndThread logAndThread2) {
        return (logAndThread.log.getTimestampNanos() > logAndThread2.log.getTimestampNanos() ? 1 : (logAndThread.log.getTimestampNanos() == logAndThread2.log.getTimestampNanos() ? 0 : -1));
    }

    public SystemHealthProto$DebugLogs getSnapshot(TimestampCollection timestampCollection, int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < timestampCollection.buffers.length; i2++) {
            for (LogInvocation logInvocation : timestampCollection.buffers[i2].getSnapshot(timestampCollection.timestamps[i2], i)) {
                arrayList.add(new LogAndThread(logInvocation, i2));
            }
        }
        Collections.sort(arrayList, RecentLogs$$ExternalSyntheticLambda0.INSTANCE);
        SystemHealthProto$DebugLogs.Builder newBuilder = SystemHealthProto$DebugLogs.newBuilder();
        long j = 0;
        for (int max = Math.max(arrayList.size() - i, 0); max < arrayList.size(); max++) {
            LogAndThread logAndThread = (LogAndThread) arrayList.get(max);
            LogInvocation logInvocation2 = logAndThread.log;
            int i3 = logAndThread.thread;
            Long messageId = getMessageId(logInvocation2);
            if (messageId != null) {
                newBuilder.addMessageId(messageId.longValue());
                long timestampNanos = logInvocation2.getTimestampNanos() / 1000000;
                newBuilder.addTimestampMs(timestampNanos - j);
                newBuilder.addThreadId(i3);
                j = timestampNanos;
            }
        }
        return (SystemHealthProto$DebugLogs) newBuilder.build();
    }

    public TimestampCollection getTimestamp() {
        synchronized (this.bufferCache) {
            if (this.bufferCache.isEmpty()) {
                return TimestampCollection.EMPTY;
            }
            return new TimestampCollection((RingBuffer[]) this.bufferCache.values().toArray(new RingBuffer[0]));
        }
    }

    RecentLogs(final int i) {
        this.bufferCache = new WeakHashMap();
        this.threadBuffer = new ThreadLocal() { // from class: com.google.android.libraries.performance.primes.flogger.RecentLogs.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public RingBuffer initialValue() {
                RingBuffer ringBuffer = new RingBuffer(i);
                Thread currentThread = Thread.currentThread();
                synchronized (RecentLogs.this.bufferCache) {
                    RecentLogs.this.bufferCache.put(currentThread, ringBuffer);
                }
                return ringBuffer;
            }
        };
    }
}
