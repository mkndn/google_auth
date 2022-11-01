package com.google.android.gms.clearcut.internal;

import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Queue;

/* compiled from: PG */
/* loaded from: classes.dex */
final class LogEventQueue {
    private static LogEventQueue instance;
    private int sizeBytes = 0;
    private final Queue eventQueue = new ArrayDeque();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Entry {
        final int code;
        final LogEventParcelable event;
        int retries = 0;

        Entry(LogEventParcelable logEventParcelable, int i) {
            this.event = logEventParcelable;
            this.code = i;
        }
    }

    private LogEventQueue() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized LogEventQueue getInstance() {
        LogEventQueue logEventQueue;
        synchronized (LogEventQueue.class) {
            if (instance == null) {
                instance = new LogEventQueue();
            }
            logEventQueue = instance;
        }
        return logEventQueue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean add(LogEventParcelable logEventParcelable, int i) {
        Preconditions.checkNotNull(logEventParcelable.logEventBytes);
        int length = logEventParcelable.logEventBytes.length;
        if (this.eventQueue.size() >= ClearcutClientFlags.eventQueueMaxSize) {
            return false;
        }
        if (this.sizeBytes + length >= ClearcutClientFlags.eventQueueMaxBytes) {
            return false;
        }
        this.sizeBytes += length;
        this.eventQueue.add(new Entry(logEventParcelable, i));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Entry peek() {
        return (Entry) this.eventQueue.peek();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Entry poll() {
        Entry entry;
        entry = (Entry) this.eventQueue.poll();
        if (entry != null) {
            Preconditions.checkNotNull(entry.event.logEventBytes);
            this.sizeBytes -= entry.event.logEventBytes.length;
        }
        return entry;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Entry retry() {
        Entry entry = (Entry) this.eventQueue.peek();
        if (entry != null) {
            entry.retries++;
            if (entry.retries >= ClearcutClientFlags.eventQueueMaxRetries) {
                return (Entry) this.eventQueue.poll();
            }
        }
        return null;
    }
}
