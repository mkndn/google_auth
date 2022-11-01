package com.google.android.gms.common.api;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ApiException extends Exception {
    @Deprecated
    protected final Status mStatus;

    public Status getStatus() {
        return this.mStatus;
    }

    public int getStatusCode() {
        return this.mStatus.getStatusCode();
    }

    public ApiException(Status status) {
        super(status.getStatusCode() + ": " + (status.getStatusMessage() != null ? status.getStatusMessage() : ""));
        this.mStatus = status;
    }
}
