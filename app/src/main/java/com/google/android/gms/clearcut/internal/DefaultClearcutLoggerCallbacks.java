package com.google.android.gms.clearcut.internal;

import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DefaultClearcutLoggerCallbacks extends IClearcutLoggerCallbacks.Stub {
    @Override // com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks
    public void onForceUpload(Status status) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks
    public void onGetCollectForDebugExpiryTime(Status status, long j) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks
    public void onGetLogEventParcelables(Status status, LogEventParcelable[] logEventParcelableArr) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks
    public void onGetLogEventParcelablesDataBuffer(DataHolder dataHolder) {
        throw new UnsupportedOperationException();
    }

    public void onLogError(Status status) {
        throw new UnsupportedOperationException();
    }

    public void onLogEvent(Status status) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks
    public void onStartCollectForDebug(Status status, long j) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks
    public void onStopCollectForDebug(Status status) {
        throw new UnsupportedOperationException();
    }
}
