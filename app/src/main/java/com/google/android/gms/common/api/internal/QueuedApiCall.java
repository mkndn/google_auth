package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.GoogleApi;

/* compiled from: PG */
/* loaded from: classes.dex */
public class QueuedApiCall {
    public final GoogleApi mGoogleApi;
    public final ApiCallRunner mRunner;
    public final int mSignOutCount;

    public QueuedApiCall(ApiCallRunner apiCallRunner, int i, GoogleApi googleApi) {
        this.mRunner = apiCallRunner;
        this.mSignOutCount = i;
        this.mGoogleApi = googleApi;
    }
}
