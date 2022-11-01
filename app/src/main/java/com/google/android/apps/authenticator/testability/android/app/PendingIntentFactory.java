package com.google.android.apps.authenticator.testability.android.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PendingIntentFactory {
    public PendingIntent getActivity(Context context, int i, Intent intent, int i2) {
        return PendingIntent.getActivity(context, i, intent, i2);
    }

    public PendingIntent getService(Context context, int i, Intent intent, int i2) {
        return PendingIntent.getService(context, i, intent, i2);
    }
}
