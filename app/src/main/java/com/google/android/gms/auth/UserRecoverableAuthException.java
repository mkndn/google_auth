package com.google.android.gms.auth;

import android.content.Intent;

/* compiled from: PG */
/* loaded from: classes.dex */
public class UserRecoverableAuthException extends GoogleAuthException {
    private final Intent intent;

    public UserRecoverableAuthException(String str, Intent intent) {
        super(str);
        this.intent = intent;
    }
}
