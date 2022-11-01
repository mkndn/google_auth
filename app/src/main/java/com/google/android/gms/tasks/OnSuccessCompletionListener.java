package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class OnSuccessCompletionListener implements TaskCompletionListener {
    private final Executor mExecutor;
    private final Object mLock = new Object();
    private OnSuccessListener mOnSuccess;

    public OnSuccessCompletionListener(Executor executor, OnSuccessListener onSuccessListener) {
        this.mExecutor = executor;
        this.mOnSuccess = onSuccessListener;
    }

    @Override // com.google.android.gms.tasks.TaskCompletionListener
    public void onComplete(final Task task) {
        if (task.isSuccessful()) {
            synchronized (this.mLock) {
                if (this.mOnSuccess == null) {
                    return;
                }
                this.mExecutor.execute(new Runnable() { // from class: com.google.android.gms.tasks.OnSuccessCompletionListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (OnSuccessCompletionListener.this.mLock) {
                            if (OnSuccessCompletionListener.this.mOnSuccess != null) {
                                OnSuccessCompletionListener.this.mOnSuccess.onSuccess(task.getResult());
                            }
                        }
                    }
                });
            }
        }
    }
}
