package com.google.android.apps.authenticator.auditlog;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.google.android.apps.authenticator.testability.android.os.PowerManager;
import com.google.android.libraries.security.app.SaferPendingIntent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AuditLogTimerStarter extends BroadcastReceiver {
    static PendingIntent getTimerReceiverIntent(Context context) {
        return getTimerReceiverIntent(context, 0);
    }

    private static void startAlarmManagerForAuditLogTimer(Context context) {
        ((AlarmManager) context.getSystemService("alarm")).setInexactRepeating(3, AuditLogConfig.TIMER_INCREMENT_INTERVAL_MS + SystemClock.elapsedRealtime(), AuditLogConfig.TIMER_INCREMENT_INTERVAL_MS, getTimerReceiverIntent(context));
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (getTimerReceiverIntent(context, PowerManager.ON_AFTER_RELEASE) == null) {
            startAlarmManagerForAuditLogTimer(context);
        }
    }

    private static PendingIntent getTimerReceiverIntent(Context context, int i) {
        return SaferPendingIntent.getBroadcast(context, 1, new Intent(context, AuditLogTimerReceiver.class), i | 67108864);
    }
}
