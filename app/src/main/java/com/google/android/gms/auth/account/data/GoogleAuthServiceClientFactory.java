package com.google.android.gms.auth.account.data;

import android.content.Context;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleAuthServiceClientFactory {
    public static GoogleAuthServiceClient newInstance(Context context) {
        return new InternalGoogleAuthServiceClient(context);
    }
}
