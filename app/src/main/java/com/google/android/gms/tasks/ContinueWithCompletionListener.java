package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
class ContinueWithCompletionListener implements TaskCompletionListener {
    private final Continuation mContinuation;
    private final TaskImpl mContinuationTask;
    private final Executor mExecutor;

    public ContinueWithCompletionListener(Executor executor, Continuation continuation, TaskImpl taskImpl) {
        this.mExecutor = executor;
        this.mContinuation = continuation;
        this.mContinuationTask = taskImpl;
    }

    @Override // com.google.android.gms.tasks.TaskCompletionListener
    public void onComplete(final Task task) {
        this.mExecutor.execute(new Runnable() { // from class: com.google.android.gms.tasks.ContinueWithCompletionListener.1
            @Override // java.lang.Runnable
            public void run() {
                if (task.isCanceled()) {
                    ContinueWithCompletionListener.this.mContinuationTask.trySetCanceled();
                    return;
                }
                try {
                    ContinueWithCompletionListener.this.mContinuationTask.setResult(ContinueWithCompletionListener.this.mContinuation.then(task));
                } catch (RuntimeExecutionException e) {
                    if (e.getCause() instanceof Exception) {
                        ContinueWithCompletionListener.this.mContinuationTask.setException((Exception) e.getCause());
                    } else {
                        ContinueWithCompletionListener.this.mContinuationTask.setException(e);
                    }
                } catch (Exception e2) {
                    ContinueWithCompletionListener.this.mContinuationTask.setException(e2);
                }
            }
        });
    }
}
