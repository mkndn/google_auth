package com.google.android.gms.libs.platform;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PendingIntentCompat {
    public static final int FLAG_IMMUTABLE = immutableFlag();
    public static final int FLAG_MUTABLE = mutableFlag();

    public static PendingIntent getActivity(Context context, int i, Intent intent, int i2) {
        return PendingIntent.getActivity(context, i, intent, i2);
    }

    private static int immutableFlag() {
        return BuildVersionCompat.isAtLeastM() ? 67108864 : 0;
    }

    private static int mutableFlag() {
        return BuildVersionCompat.isAtLeastS() ? 33554432 : 0;
    }
}
