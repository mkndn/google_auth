package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PendingResultUtil {
    private static final StatusConverter DEFAULT_STATUS_CONVERTER = new StatusConverter() { // from class: com.google.android.gms.common.internal.PendingResultUtil.1
        @Override // com.google.android.gms.common.internal.PendingResultUtil.StatusConverter
        public ApiException convert(Status status) {
            return ApiExceptionUtil.fromStatus(status);
        }
    };

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ResultConverter {
        Object convert(Result result);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface StatusConverter {
        ApiException convert(Status status);
    }

    public static Task toTask(PendingResult pendingResult, ResultConverter resultConverter) {
        return toTask(pendingResult, resultConverter, DEFAULT_STATUS_CONVERTER);
    }

    public static Task toVoidTask(PendingResult pendingResult) {
        return toTask(pendingResult, new ResultConverter() { // from class: com.google.android.gms.common.internal.PendingResultUtil.4
            @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
            public Void convert(Result result) {
                return null;
            }
        });
    }

    public static Task toTask(final PendingResult pendingResult, final ResultConverter resultConverter, final StatusConverter statusConverter) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener(new PendingResult.StatusListener() { // from class: com.google.android.gms.common.internal.PendingResultUtil.2
            @Override // com.google.android.gms.common.api.PendingResult.StatusListener
            public void onComplete(Status status) {
                if (status.isSuccess()) {
                    taskCompletionSource.setResult(resultConverter.convert(PendingResult.this.await(0L, TimeUnit.MILLISECONDS)));
                    return;
                }
                taskCompletionSource.setException(statusConverter.convert(status));
            }
        });
        return taskCompletionSource.getTask();
    }
}
