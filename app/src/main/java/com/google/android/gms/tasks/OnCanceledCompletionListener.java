package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
class OnCanceledCompletionListener implements TaskCompletionListener {
    private final Executor mExecutor;
    private final Object mLock = new Object();
    private OnCanceledListener mOnCanceled;

    public OnCanceledCompletionListener(Executor executor, OnCanceledListener onCanceledListener) {
        this.mExecutor = executor;
        this.mOnCanceled = onCanceledListener;
    }

    @Override // com.google.android.gms.tasks.TaskCompletionListener
    public void onComplete(Task task) {
        if (task.isCanceled()) {
            synchronized (this.mLock) {
                if (this.mOnCanceled == null) {
                    return;
                }
                this.mExecutor.execute(new Runnable() { // from class: com.google.android.gms.tasks.OnCanceledCompletionListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (OnCanceledCompletionListener.this.mLock) {
                            if (OnCanceledCompletionListener.this.mOnCanceled != null) {
                                OnCanceledCompletionListener.this.mOnCanceled.onCanceled();
                            }
                        }
                    }
                });
            }
        }
    }
}
