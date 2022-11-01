package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ConnectionlessInProgressCalls {
    private final Map mPendingResultsInProgress = Collections.synchronizedMap(new WeakHashMap());
    private final Map mTasksInProgress = Collections.synchronizedMap(new WeakHashMap());

    private void failCalls(boolean z, Status status) {
        HashMap hashMap;
        HashMap hashMap2;
        synchronized (this.mPendingResultsInProgress) {
            hashMap = new HashMap(this.mPendingResultsInProgress);
        }
        synchronized (this.mTasksInProgress) {
            hashMap2 = new HashMap(this.mTasksInProgress);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((BasePendingResult) entry.getKey()).forceFailureUnlessReady(status);
            }
        }
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((TaskCompletionSource) entry2.getKey()).trySetException(new ApiException(status));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void add(final BasePendingResult basePendingResult, boolean z) {
        this.mPendingResultsInProgress.put(basePendingResult, Boolean.valueOf(z));
        basePendingResult.addStatusListener(new PendingResult.StatusListener() { // from class: com.google.android.gms.common.api.internal.ConnectionlessInProgressCalls.1
            @Override // com.google.android.gms.common.api.PendingResult.StatusListener
            public void onComplete(Status status) {
                ConnectionlessInProgressCalls.this.mPendingResultsInProgress.remove(basePendingResult);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean containsCallInProgress() {
        return (this.mPendingResultsInProgress.isEmpty() && this.mTasksInProgress.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void failAllCallsDueToConnectionSuspended(int i, String str) {
        StringBuilder sb = new StringBuilder("The connection to Google Play services was lost");
        if (i == 1) {
            sb.append(" due to service disconnection.");
        } else if (i == 3) {
            sb.append(" due to dead object exception.");
        }
        if (str != null) {
            sb.append(" Last reason for disconnect: ").append(str);
        }
        failCalls(true, new Status(20, sb.toString()));
    }

    public void failAuthenticatedCalls() {
        failCalls(false, GoogleApiManager.SIGNED_OUT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void add(final TaskCompletionSource taskCompletionSource, boolean z) {
        this.mTasksInProgress.put(taskCompletionSource, Boolean.valueOf(z));
        taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.gms.common.api.internal.ConnectionlessInProgressCalls.2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task task) {
                ConnectionlessInProgressCalls.this.mTasksInProgress.remove(taskCompletionSource);
            }
        });
    }
}
