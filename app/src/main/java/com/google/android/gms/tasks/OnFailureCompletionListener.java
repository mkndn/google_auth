package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
class OnFailureCompletionListener implements TaskCompletionListener {
    private final Executor mExecutor;
    private final Object mLock = new Object();
    private OnFailureListener mOnFailure;

    public OnFailureCompletionListener(Executor executor, OnFailureListener onFailureListener) {
        this.mExecutor = executor;
        this.mOnFailure = onFailureListener;
    }

    @Override // com.google.android.gms.tasks.TaskCompletionListener
    public void onComplete(final Task task) {
        if (!task.isSuccessful() && !task.isCanceled()) {
            synchronized (this.mLock) {
                if (this.mOnFailure == null) {
                    return;
                }
                this.mExecutor.execute(new Runnable() { // from class: com.google.android.gms.tasks.OnFailureCompletionListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (OnFailureCompletionListener.this.mLock) {
                            if (OnFailureCompletionListener.this.mOnFailure != null) {
                                OnFailureCompletionListener.this.mOnFailure.onFailure((Exception) Preconditions.checkNotNull(task.getException()));
                            }
                        }
                    }
                });
            }
        }
    }
}
