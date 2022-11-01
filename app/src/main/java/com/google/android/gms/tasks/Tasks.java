package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Tasks {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AwaitListener implements CombinedListener {
        private final CountDownLatch mLatch;

        private AwaitListener() {
            this.mLatch = new CountDownLatch(1);
        }

        public void await() {
            this.mLatch.await();
        }

        @Override // com.google.android.gms.tasks.OnCanceledListener
        public void onCanceled() {
            this.mLatch.countDown();
        }

        @Override // com.google.android.gms.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            this.mLatch.countDown();
        }

        @Override // com.google.android.gms.tasks.OnSuccessListener
        public void onSuccess(Object obj) {
            this.mLatch.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface CombinedListener extends OnSuccessListener, OnFailureListener, OnCanceledListener {
    }

    private static void addListener(Task task, CombinedListener combinedListener) {
        task.addOnSuccessListener(TaskExecutors.DIRECT, combinedListener);
        task.addOnFailureListener(TaskExecutors.DIRECT, combinedListener);
        task.addOnCanceledListener(TaskExecutors.DIRECT, combinedListener);
    }

    public static Object await(Task task) {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotNull(task, "Task must not be null");
        if (task.isComplete()) {
            return getResultOrThrowExecutionException(task);
        }
        AwaitListener awaitListener = new AwaitListener();
        addListener(task, awaitListener);
        awaitListener.await();
        return getResultOrThrowExecutionException(task);
    }

    public static Task forException(Exception exc) {
        TaskImpl taskImpl = new TaskImpl();
        taskImpl.setException(exc);
        return taskImpl;
    }

    public static Task forResult(Object obj) {
        TaskImpl taskImpl = new TaskImpl();
        taskImpl.setResult(obj);
        return taskImpl;
    }

    private static Object getResultOrThrowExecutionException(Task task) {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        if (task.isCanceled()) {
            throw new CancellationException("Task is already canceled");
        }
        throw new ExecutionException(task.getException());
    }
}
