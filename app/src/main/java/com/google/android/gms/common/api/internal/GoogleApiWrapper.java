package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.api.GoogleApi;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleApiWrapper extends DummyGoogleApiClient {
    private final GoogleApi mConnectionlessApi;

    public GoogleApiWrapper(GoogleApi googleApi) {
        super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
        this.mConnectionlessApi = googleApi;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public BaseImplementation$ApiMethodImpl enqueue(BaseImplementation$ApiMethodImpl baseImplementation$ApiMethodImpl) {
        return this.mConnectionlessApi.doRead(baseImplementation$ApiMethodImpl);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public BaseImplementation$ApiMethodImpl execute(BaseImplementation$ApiMethodImpl baseImplementation$ApiMethodImpl) {
        return this.mConnectionlessApi.doWrite(baseImplementation$ApiMethodImpl);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public Looper getLooper() {
        return this.mConnectionlessApi.getLooper();
    }
}
