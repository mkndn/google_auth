package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GooglePlayServicesUpdatedReceiver extends BroadcastReceiver {
    private final Callback mCallback;
    Context mContext;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Callback {
        public abstract void onUpdated();
    }

    public GooglePlayServicesUpdatedReceiver(Callback callback) {
        this.mCallback = callback;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        Uri data = intent.getData();
        if (data != null) {
            str = data.getSchemeSpecificPart();
        } else {
            str = null;
        }
        if ("com.google.android.gms".equals(str)) {
            this.mCallback.onUpdated();
            unregister();
        }
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public synchronized void unregister() {
        Context context = this.mContext;
        if (context != null) {
            context.unregisterReceiver(this);
        }
        this.mContext = null;
    }
}
