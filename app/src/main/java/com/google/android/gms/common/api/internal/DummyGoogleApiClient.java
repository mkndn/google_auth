package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.GoogleApiClient;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DummyGoogleApiClient extends GoogleApiClient {
    private final String mUnsupportedMessage;

    public DummyGoogleApiClient(String str) {
        this.mUnsupportedMessage = str;
    }
}
