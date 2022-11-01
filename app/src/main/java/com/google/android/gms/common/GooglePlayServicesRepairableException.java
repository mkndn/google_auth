package com.google.android.gms.common;

import android.content.Intent;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GooglePlayServicesRepairableException extends UserRecoverableException {
    private final int mConnectionStatusCode;

    public GooglePlayServicesRepairableException(int i, String str, Intent intent) {
        super(str, intent);
        this.mConnectionStatusCode = i;
    }

    public int getConnectionStatusCode() {
        return this.mConnectionStatusCode;
    }
}
