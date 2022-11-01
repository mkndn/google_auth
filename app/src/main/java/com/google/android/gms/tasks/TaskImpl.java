package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class TaskImpl extends Task {
    private volatile boolean mCanceled;
    private boolean mComplete;
    private Exception mException;
    private Object mResult;
    private final Object mLock = new Object();
    private final TaskCompletionListenerQueue mListenerQueue = new TaskCompletionListenerQueue();

    private void checkCompleteLocked() {
        Preconditions.checkState(this.mComplete, "Task is not yet complete");
    }

    private void checkNotCanceledLocked() {
        if (this.mCanceled) {
            throw new CancellationException("Task is already canceled.");
        }
    }

    private void checkNotCompleteLocked() {
        if (this.mComplete) {
            throw DuplicateTaskCompletionException.of(this);
        }
    }

    private void flushIfComplete() {
        synchronized (this.mLock) {
            if (this.mComplete) {
                this.mListenerQueue.flush(this);
            }
        }
    }

    @Override // com.google.android.gms.tasks.Task
    public Task addOnCanceledListener(Executor executor, OnCanceledListener onCanceledListener) {
        this.mListenerQueue.add(new OnCanceledCompletionListener(TaskTracing.traceExecutor(executor), onCanceledListener));
        flushIfComplete();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public Task addOnCompleteListener(OnCompleteListener onCompleteListener) {
        return addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public Task addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        this.mListenerQueue.add(new OnFailureCompletionListener(TaskTracing.traceExecutor(executor), onFailureListener));
        flushIfComplete();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public Task addOnSuccessListener(OnSuccessListener onSuccessListener) {
        return addOnSuccessListener(TaskExecutors.MAIN_THREAD, onSuccessListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public Task continueWith(Executor executor, Continuation continuation) {
        TaskImpl taskImpl = new TaskImpl();
        this.mListenerQueue.add(new ContinueWithCompletionListener(TaskTracing.traceExecutor(executor), continuation, taskImpl));
        flushIfComplete();
        return taskImpl;
    }

    @Override // com.google.android.gms.tasks.Task
    public Exception getException() {
        Exception exc;
        synchronized (this.mLock) {
            exc = this.mException;
        }
        return exc;
    }

    @Override // com.google.android.gms.tasks.Task
    public Object getResult() {
        Object obj;
        synchronized (this.mLock) {
            checkCompleteLocked();
            checkNotCanceledLocked();
            if (this.mException != null) {
                throw new RuntimeExecutionException(this.mException);
            }
            obj = this.mResult;
        }
        return obj;
    }

    @Override // com.google.android.gms.tasks.Task
    public boolean isCanceled() {
        return this.mCanceled;
    }

    @Override // com.google.android.gms.tasks.Task
    public boolean isComplete() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mComplete;
        }
        return z;
    }

    @Override // com.google.android.gms.tasks.Task
    public boolean isSuccessful() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mComplete && !this.mCanceled && this.mException == null;
        }
        return z;
    }

    public void setException(Exception exc) {
        Preconditions.checkNotNull(exc, "Exception must not be null");
        synchronized (this.mLock) {
            checkNotCompleteLocked();
            this.mComplete = true;
            this.mException = exc;
        }
        this.mListenerQueue.flush(this);
    }

    public void setResult(Object obj) {
        synchronized (this.mLock) {
            checkNotCompleteLocked();
            this.mComplete = true;
            this.mResult = obj;
        }
        this.mListenerQueue.flush(this);
    }

    public boolean trySetCanceled() {
        synchronized (this.mLock) {
            if (this.mComplete) {
                return false;
            }
            this.mComplete = true;
            this.mCanceled = true;
            this.mListenerQueue.flush(this);
            return true;
        }
    }

    public boolean trySetException(Exception exc) {
        Preconditions.checkNotNull(exc, "Exception must not be null");
        synchronized (this.mLock) {
            if (this.mComplete) {
                return false;
            }
            this.mComplete = true;
            this.mException = exc;
            this.mListenerQueue.flush(this);
            return true;
        }
    }

    public boolean trySetResult(Object obj) {
        synchronized (this.mLock) {
            if (this.mComplete) {
                return false;
            }
            this.mComplete = true;
            this.mResult = obj;
            this.mListenerQueue.flush(this);
            return true;
        }
    }

    @Override // com.google.android.gms.tasks.Task
    public Task addOnCompleteListener(Executor executor, OnCompleteListener onCompleteListener) {
        this.mListenerQueue.add(new OnCompleteCompletionListener(TaskTracing.traceExecutor(executor), onCompleteListener));
        flushIfComplete();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public Task addOnSuccessListener(Executor executor, OnSuccessListener onSuccessListener) {
        this.mListenerQueue.add(new OnSuccessCompletionListener(TaskTracing.traceExecutor(executor), onSuccessListener));
        flushIfComplete();
        return this;
    }
}
