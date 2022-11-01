package com.google.android.apps.authenticator.common;

import android.accounts.AccountManager;
import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import com.google.android.apps.authenticator.testability.android.os.PowerManager;
import com.google.android.apps.authenticator.time.Clock;
import com.google.android.apps.authenticator.time.SystemWallClock;
import com.google.android.apps.authenticator.util.permissions.AutoGrantPermissionRequestor;
import com.google.android.apps.authenticator.util.permissions.PermissionRequestor;
import com.google.android.apps.authenticator.util.permissions.RuntimePermissionRequestor;
import java.net.Authenticator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.inject.Singleton;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AndroidDependenciesModule {
    @Singleton
    public AccountManager provideAccountManager(@ApplicationContext Context context) {
        return (AccountManager) context.getSystemService("account");
    }

    @Singleton
    public AlarmManager provideAlarmManager(@ApplicationContext Context context) {
        return (AlarmManager) context.getSystemService("alarm");
    }

    @Singleton
    public Clock provideClock() {
        return new SystemWallClock();
    }

    public CountDownLatch provideCountDownLatch() {
        return new CountDownLatch(1);
    }

    @Singleton
    public DevicePolicyManager provideDevicePolicyManager(@ApplicationContext Context context) {
        return (DevicePolicyManager) context.getSystemService("device_policy");
    }

    @Singleton
    public KeyguardManager.KeyguardLock provideKeyguardLock(KeyguardManager keyguardManager) {
        return keyguardManager.newKeyguardLock(Authenticator.class.getSimpleName());
    }

    @Singleton
    public KeyguardManager provideKeyguardManager(@ApplicationContext Context context) {
        return (KeyguardManager) context.getSystemService("keyguard");
    }

    public Notification.Builder provideNotificationBuilder(@ApplicationContext Context context) {
        return new Notification.Builder(context);
    }

    @Singleton
    public NotificationManager provideNotificationManager(@ApplicationContext Context context) {
        return (NotificationManager) context.getSystemService("notification");
    }

    @Singleton
    public PackageManager providePackageManager(@ApplicationContext Context context) {
        return context.getPackageManager();
    }

    @Singleton
    public SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public Executor provideSingleThreadExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Singleton
    public AudioManager providesAudioManager(@ApplicationContext Context context) {
        return (AudioManager) context.getSystemService("audio");
    }

    @Singleton
    public PermissionRequestor providesPermissionRequestor() {
        if (Build.VERSION.SDK_INT < 23) {
            return new AutoGrantPermissionRequestor();
        }
        return new RuntimePermissionRequestor();
    }

    @Singleton
    public Vibrator providesVibrator(@ApplicationContext Context context) {
        return (Vibrator) context.getSystemService("vibrator");
    }

    @Singleton
    public PowerManager providesPowerManager(@ApplicationContext Context context) {
        return PowerManager.wrap((android.os.PowerManager) context.getSystemService("power"));
    }
}
