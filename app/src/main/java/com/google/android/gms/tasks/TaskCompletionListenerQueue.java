package com.google.android.gms.tasks;

import java.util.ArrayDeque;
import java.util.Queue;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class TaskCompletionListenerQueue {
    private boolean mFlushing;
    private final Object mLock = new Object();
    private Queue mQueue;

    public void add(TaskCompletionListener taskCompletionListener) {
        synchronized (this.mLock) {
            if (this.mQueue == null) {
                this.mQueue = new ArrayDeque();
            }
            this.mQueue.add(taskCompletionListener);
        }
    }

    public void flush(Task task) {
        TaskCompletionListener taskCompletionListener;
        synchronized (this.mLock) {
            if (this.mQueue != null && !this.mFlushing) {
                this.mFlushing = true;
                while (true) {
                    synchronized (this.mLock) {
                        taskCompletionListener = (TaskCompletionListener) this.mQueue.poll();
                        if (taskCompletionListener == null) {
                            this.mFlushing = false;
                            return;
                        }
                    }
                    taskCompletionListener.onComplete(task);
                }
            }
        }
    }
}
