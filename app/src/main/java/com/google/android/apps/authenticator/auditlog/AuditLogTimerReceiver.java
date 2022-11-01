package com.google.android.apps.authenticator.auditlog;

import android.app.NotificationChannel;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.android.apps.authenticator.testability.DependencyInjector;
import com.google.android.apps.authenticator2.R;
import com.google.android.libraries.security.app.SaferPendingIntent;
import java.security.SecureRandom;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AuditLogTimerReceiver extends BroadcastReceiver {
    static final int INCREMENT_TIMER_REQUEST_CODE = 1;
    private static final String MIGRATION_NOTIFICATION_CHANNEL_ID = "migration_alerts";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum NotificationType {
        RECENT_EXPORT(R.string.audit_log_accounts_exported_notification_title, R.string.audit_log_accounts_exported_notification_text, R.drawable.export_icon_24dp),
        RECENT_IMPORT(R.string.audit_log_accounts_imported_notification_title, R.string.audit_log_accounts_imported_notification_text, R.drawable.import_icon_24dp);
        
        private final int iconResourceId;
        private final int textResourceId;
        private final int titleResourceId;

        NotificationType(int i, int i2, int i3) {
            this.titleResourceId = i;
            this.textResourceId = i2;
            this.iconResourceId = i3;
        }
    }

    private static boolean randomNotificationCheck() {
        return new SecureRandom().nextInt(8) == 0;
    }

    private static boolean trySendNotification(Context context, NotificationType notificationType, int i) {
        NotificationManagerCompat from = NotificationManagerCompat.from(context);
        if (from.areNotificationsEnabled()) {
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannel notificationChannel = new NotificationChannel(MIGRATION_NOTIFICATION_CHANNEL_ID, context.getString(R.string.migration_notification_channel_name), 2);
                notificationChannel.setDescription(context.getString(R.string.migration_notification_channel_description));
                from.createNotificationChannel(notificationChannel);
                if (from.getNotificationChannel(MIGRATION_NOTIFICATION_CHANNEL_ID).getImportance() == 0) {
                    return false;
                }
            }
            from.notify(i, new NotificationCompat.Builder(context, MIGRATION_NOTIFICATION_CHANNEL_ID).setSmallIcon(notificationType.iconResourceId).setContentTitle(context.getString(notificationType.titleResourceId)).setContentText(context.getString(notificationType.textResourceId)).setStyle(new NotificationCompat.BigTextStyle().bigText(context.getString(notificationType.textResourceId))).setPriority(-1).setContentIntent(SaferPendingIntent.getActivity(context, 0, new Intent(context, ViewAuditLogActivity.class), 1140850688)).setAutoCancel(true).build());
            return true;
        }
        return false;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        NotificationType notificationType;
        AuditLogDb auditLogDb = DependencyInjector.getAccountDb().auditLog;
        auditLogDb.incrementTimer();
        auditLogDb.deleteOutdatedEvents(AuditLogConfig.AUDIT_LOG_EVENT_LIFETIME_MS);
        if (!randomNotificationCheck()) {
            return;
        }
        if (auditLogDb.hasPendingExportNotifications()) {
            notificationType = NotificationType.RECENT_EXPORT;
        } else if (auditLogDb.hasPendingImportNotifications()) {
            notificationType = NotificationType.RECENT_IMPORT;
        } else {
            return;
        }
        if (trySendNotification(context, notificationType, (int) auditLogDb.getCurrentTimer())) {
            auditLogDb.clearPendingMigrationNotifications();
        }
    }
}
