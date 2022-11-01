package com.google.android.gms.libs.platform;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ContextCompat extends androidx.core.content.ContextCompat {
    static int makeTiramisuFlags(int i) {
        if (BuildVersionCompat.isAtLeastT()) {
            if ((i & 6) != 0) {
                return i;
            }
            return i | 2;
        }
        return i;
    }

    @Deprecated
    public static Intent registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (BuildVersionCompat.isAtLeastT()) {
            return context.registerReceiver(broadcastReceiver, intentFilter, makeTiramisuFlags(0));
        }
        return context.registerReceiver(broadcastReceiver, intentFilter);
    }
}
