package com.google.android.libraries.gmstasks;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.gmstasks.TaskFutures;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TaskFutures {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TaggedFuture extends AbstractFuture {
        Object tag;

        TaggedFuture(Object obj) {
            this.tag = obj;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public void afterDone() {
            this.tag = null;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public String pendingToString() {
            Object obj = this.tag;
            if (obj == null) {
                return "";
            }
            return obj.toString();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public boolean set(Object obj) {
            return super.set(obj);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public boolean setException(Throwable th) {
            return super.setException(th);
        }
    }

    public static /* synthetic */ void lambda$toListenableFuture$0(TaggedFuture taggedFuture, Task task) {
        if (task.isCanceled()) {
            taggedFuture.cancel(false);
        } else if (task.isSuccessful()) {
            taggedFuture.set(task.getResult());
        } else {
            Exception exception = task.getException();
            if (exception != null) {
                taggedFuture.setException(exception);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public static ListenableFuture toListenableFuture(Task task) {
        final TaggedFuture taggedFuture = new TaggedFuture(task);
        task.addOnCompleteListener(MoreExecutors.directExecutor(), new OnCompleteListener() { // from class: com.google.android.libraries.gmstasks.TaskFutures$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task2) {
                TaskFutures.lambda$toListenableFuture$0(TaskFutures.TaggedFuture.this, task2);
            }
        });
        return taggedFuture;
    }
}
