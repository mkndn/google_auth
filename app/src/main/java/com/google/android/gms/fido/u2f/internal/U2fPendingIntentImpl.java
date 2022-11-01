package com.google.android.gms.fido.u2f.internal;

import android.app.Activity;
import android.app.PendingIntent;
import com.google.android.gms.fido.u2f.U2fPendingIntent;

/* compiled from: PG */
/* loaded from: classes.dex */
public class U2fPendingIntentImpl implements U2fPendingIntent {
    private final PendingIntent pendingIntent;

    public U2fPendingIntentImpl(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    @Override // com.google.android.gms.fido.u2f.U2fPendingIntent
    public boolean hasPendingIntent() {
        return this.pendingIntent != null;
    }

    @Override // com.google.android.gms.fido.u2f.U2fPendingIntent
    public void launchPendingIntent(Activity activity, int i) {
        if (hasPendingIntent()) {
            activity.startIntentSenderForResult(this.pendingIntent.getIntentSender(), i, null, 0, 0, 0);
            return;
        }
        throw new IllegalStateException("No PendingIntent available");
    }
}
