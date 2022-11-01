package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PendingResults {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ImmediateFailedResult extends BasePendingResult {
        private final Result mResult;

        public ImmediateFailedResult(GoogleApiClient googleApiClient, Result result) {
            super(googleApiClient);
            this.mResult = result;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public Result createFailedResult(Status status) {
            return this.mResult;
        }
    }

    public static PendingResult immediateFailedResult(Result result, GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull(result, "Result must not be null");
        Preconditions.checkArgument(!result.getStatus().isSuccess(), "Status code must not be SUCCESS");
        ImmediateFailedResult immediateFailedResult = new ImmediateFailedResult(googleApiClient, result);
        immediateFailedResult.setResult(result);
        return immediateFailedResult;
    }

    public static PendingResult immediatePendingResult(Status status, GoogleApiClient googleApiClient) {
        Preconditions.checkNotNull(status, "Result must not be null");
        StatusPendingResult statusPendingResult = new StatusPendingResult(googleApiClient);
        statusPendingResult.setResult(status);
        return statusPendingResult;
    }
}
