package com.google.android.gms.auth;

import android.content.Intent;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GooglePlayServicesAvailabilityException extends UserRecoverableAuthException {
    private final int mConnectionStatusCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GooglePlayServicesAvailabilityException(int i, String str, Intent intent) {
        super(str, intent);
        this.mConnectionStatusCode = i;
    }
}
