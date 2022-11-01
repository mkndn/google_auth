package com.google.android.gms.tasks;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TaskCompletionSource {
    private final TaskImpl mTask = new TaskImpl();

    public Task getTask() {
        return this.mTask;
    }

    public void setException(Exception exc) {
        this.mTask.setException(exc);
    }

    public void setResult(Object obj) {
        this.mTask.setResult(obj);
    }

    public boolean trySetException(Exception exc) {
        return this.mTask.trySetException(exc);
    }

    public boolean trySetResult(Object obj) {
        return this.mTask.trySetResult(obj);
    }
}
