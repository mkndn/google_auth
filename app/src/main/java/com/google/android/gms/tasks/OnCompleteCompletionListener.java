package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class OnCompleteCompletionListener implements TaskCompletionListener {
    private final Executor mExecutor;
    private final Object mLock = new Object();
    private OnCompleteListener mOnComplete;

    public OnCompleteCompletionListener(Executor executor, OnCompleteListener onCompleteListener) {
        this.mExecutor = executor;
        this.mOnComplete = onCompleteListener;
    }

    @Override // com.google.android.gms.tasks.TaskCompletionListener
    public void onComplete(final Task task) {
        synchronized (this.mLock) {
            if (this.mOnComplete == null) {
                return;
            }
            this.mExecutor.execute(new Runnable() { // from class: com.google.android.gms.tasks.OnCompleteCompletionListener.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (OnCompleteCompletionListener.this.mLock) {
                        if (OnCompleteCompletionListener.this.mOnComplete != null) {
                            OnCompleteCompletionListener.this.mOnComplete.onComplete(task);
                        }
                    }
                }
            });
        }
    }
}
