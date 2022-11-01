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
import android.os.Vibrator;
import com.google.android.apps.authenticator.testability.android.os.PowerManager;
import com.google.android.apps.authenticator.time.Clock;
import com.google.android.apps.authenticator.util.permissions.PermissionRequestor;
import dagger.internal.Binding;
import dagger.internal.BindingsGroup;
import dagger.internal.Linker;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AndroidDependenciesModule$$ModuleAdapter extends ModuleAdapter {
    private static final String[] INJECTS = new String[0];
    private static final Class[] STATIC_INJECTIONS = new Class[0];
    private static final Class[] INCLUDES = new Class[0];

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvideAccountManagerProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding applicationContext;
        private final AndroidDependenciesModule module;

        public ProvideAccountManagerProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("android.accounts.AccountManager", true, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "provideAccountManager");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.applicationContext = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", AndroidDependenciesModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public AccountManager get() {
            return this.module.provideAccountManager((Context) this.applicationContext.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.applicationContext);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvideAlarmManagerProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding applicationContext;
        private final AndroidDependenciesModule module;

        public ProvideAlarmManagerProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("android.app.AlarmManager", true, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "provideAlarmManager");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.applicationContext = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", AndroidDependenciesModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public AlarmManager get() {
            return this.module.provideAlarmManager((Context) this.applicationContext.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.applicationContext);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvideClockProvidesAdapter extends ProvidesBinding implements Provider {
        private final AndroidDependenciesModule module;

        public ProvideClockProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("com.google.android.apps.authenticator.time.Clock", true, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "provideClock");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public Clock get() {
            return this.module.provideClock();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvideCountDownLatchProvidesAdapter extends ProvidesBinding implements Provider {
        private final AndroidDependenciesModule module;

        public ProvideCountDownLatchProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("@javax.inject.Named(value=CountDownOne)/java.util.concurrent.CountDownLatch", false, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "provideCountDownLatch");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public CountDownLatch get() {
            return this.module.provideCountDownLatch();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvideDevicePolicyManagerProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding applicationContext;
        private final AndroidDependenciesModule module;

        public ProvideDevicePolicyManagerProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("android.app.admin.DevicePolicyManager", true, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "provideDevicePolicyManager");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.applicationContext = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", AndroidDependenciesModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public DevicePolicyManager get() {
            return this.module.provideDevicePolicyManager((Context) this.applicationContext.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.applicationContext);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvideKeyguardLockProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding keyguardManager;
        private final AndroidDependenciesModule module;

        public ProvideKeyguardLockProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("android.app.KeyguardManager$KeyguardLock", true, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "provideKeyguardLock");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.keyguardManager = linker.requestBinding("android.app.KeyguardManager", AndroidDependenciesModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public KeyguardManager.KeyguardLock get() {
            return this.module.provideKeyguardLock((KeyguardManager) this.keyguardManager.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.keyguardManager);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvideKeyguardManagerProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding applicationContext;
        private final AndroidDependenciesModule module;

        public ProvideKeyguardManagerProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("android.app.KeyguardManager", true, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "provideKeyguardManager");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.applicationContext = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", AndroidDependenciesModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public KeyguardManager get() {
            return this.module.provideKeyguardManager((Context) this.applicationContext.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.applicationContext);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvideNotificationBuilderProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding applicationContext;
        private final AndroidDependenciesModule module;

        public ProvideNotificationBuilderProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("android.app.Notification$Builder", false, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "provideNotificationBuilder");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.applicationContext = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", AndroidDependenciesModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public Notification.Builder get() {
            return this.module.provideNotificationBuilder((Context) this.applicationContext.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.applicationContext);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvideNotificationManagerProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding applicationContext;
        private final AndroidDependenciesModule module;

        public ProvideNotificationManagerProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("android.app.NotificationManager", true, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "provideNotificationManager");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.applicationContext = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", AndroidDependenciesModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public NotificationManager get() {
            return this.module.provideNotificationManager((Context) this.applicationContext.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.applicationContext);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvidePackageManagerProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding applicationContext;
        private final AndroidDependenciesModule module;

        public ProvidePackageManagerProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("android.content.pm.PackageManager", true, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "providePackageManager");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.applicationContext = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", AndroidDependenciesModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public PackageManager get() {
            return this.module.providePackageManager((Context) this.applicationContext.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.applicationContext);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvideSharedPreferencesProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding applicationContext;
        private final AndroidDependenciesModule module;

        public ProvideSharedPreferencesProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("android.content.SharedPreferences", true, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "provideSharedPreferences");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.applicationContext = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", AndroidDependenciesModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public SharedPreferences get() {
            return this.module.provideSharedPreferences((Context) this.applicationContext.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.applicationContext);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvideSingleThreadExecutorProvidesAdapter extends ProvidesBinding implements Provider {
        private final AndroidDependenciesModule module;

        public ProvideSingleThreadExecutorProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("@javax.inject.Named(value=SingleThreadExecutor)/java.util.concurrent.Executor", false, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "provideSingleThreadExecutor");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public Executor get() {
            return this.module.provideSingleThreadExecutor();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvidesAudioManagerProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding applicationContext;
        private final AndroidDependenciesModule module;

        public ProvidesAudioManagerProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("android.media.AudioManager", true, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "providesAudioManager");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.applicationContext = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", AndroidDependenciesModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public AudioManager get() {
            return this.module.providesAudioManager((Context) this.applicationContext.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.applicationContext);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvidesPermissionRequestorProvidesAdapter extends ProvidesBinding implements Provider {
        private final AndroidDependenciesModule module;

        public ProvidesPermissionRequestorProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("com.google.android.apps.authenticator.util.permissions.PermissionRequestor", true, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "providesPermissionRequestor");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public PermissionRequestor get() {
            return this.module.providesPermissionRequestor();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvidesPowerManagerProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding applicationContext;
        private final AndroidDependenciesModule module;

        public ProvidesPowerManagerProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("com.google.android.apps.authenticator.testability.android.os.PowerManager", true, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "providesPowerManager");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.applicationContext = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", AndroidDependenciesModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public PowerManager get() {
            return this.module.providesPowerManager((Context) this.applicationContext.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.applicationContext);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvidesVibratorProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding applicationContext;
        private final AndroidDependenciesModule module;

        public ProvidesVibratorProvidesAdapter(AndroidDependenciesModule androidDependenciesModule) {
            super("android.os.Vibrator", true, "com.google.android.apps.authenticator.common.AndroidDependenciesModule", "providesVibrator");
            this.module = androidDependenciesModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.applicationContext = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", AndroidDependenciesModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public Vibrator get() {
            return this.module.providesVibrator((Context) this.applicationContext.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.applicationContext);
        }
    }

    public AndroidDependenciesModule$$ModuleAdapter() {
        super(AndroidDependenciesModule.class, INJECTS, STATIC_INJECTIONS, false, INCLUDES, false, true);
    }

    @Override // dagger.internal.ModuleAdapter
    public void getBindings(BindingsGroup bindingsGroup, AndroidDependenciesModule androidDependenciesModule) {
        bindingsGroup.contributeProvidesBinding("android.accounts.AccountManager", new ProvideAccountManagerProvidesAdapter(androidDependenciesModule));
        bindingsGroup.contributeProvidesBinding("android.app.AlarmManager", new ProvideAlarmManagerProvidesAdapter(androidDependenciesModule));
        bindingsGroup.contributeProvidesBinding("android.content.SharedPreferences", new ProvideSharedPreferencesProvidesAdapter(androidDependenciesModule));
        bindingsGroup.contributeProvidesBinding("android.content.pm.PackageManager", new ProvidePackageManagerProvidesAdapter(androidDependenciesModule));
        bindingsGroup.contributeProvidesBinding("com.google.android.apps.authenticator.time.Clock", new ProvideClockProvidesAdapter(androidDependenciesModule));
        bindingsGroup.contributeProvidesBinding("android.app.KeyguardManager", new ProvideKeyguardManagerProvidesAdapter(androidDependenciesModule));
        bindingsGroup.contributeProvidesBinding("android.app.admin.DevicePolicyManager", new ProvideDevicePolicyManagerProvidesAdapter(androidDependenciesModule));
        bindingsGroup.contributeProvidesBinding("android.app.NotificationManager", new ProvideNotificationManagerProvidesAdapter(androidDependenciesModule));
        bindingsGroup.contributeProvidesBinding("android.app.Notification$Builder", new ProvideNotificationBuilderProvidesAdapter(androidDependenciesModule));
        bindingsGroup.contributeProvidesBinding("android.app.KeyguardManager$KeyguardLock", new ProvideKeyguardLockProvidesAdapter(androidDependenciesModule));
        bindingsGroup.contributeProvidesBinding("android.media.AudioManager", new ProvidesAudioManagerProvidesAdapter(androidDependenciesModule));
        bindingsGroup.contributeProvidesBinding("com.google.android.apps.authenticator.testability.android.os.PowerManager", new ProvidesPowerManagerProvidesAdapter(androidDependenciesModule));
        bindingsGroup.contributeProvidesBinding("android.os.Vibrator", new ProvidesVibratorProvidesAdapter(androidDependenciesModule));
        bindingsGroup.contributeProvidesBinding("@javax.inject.Named(value=CountDownOne)/java.util.concurrent.CountDownLatch", new ProvideCountDownLatchProvidesAdapter(androidDependenciesModule));
        bindingsGroup.contributeProvidesBinding("@javax.inject.Named(value=SingleThreadExecutor)/java.util.concurrent.Executor", new ProvideSingleThreadExecutorProvidesAdapter(androidDependenciesModule));
        bindingsGroup.contributeProvidesBinding("com.google.android.apps.authenticator.util.permissions.PermissionRequestor", new ProvidesPermissionRequestorProvidesAdapter(androidDependenciesModule));
    }

    @Override // dagger.internal.ModuleAdapter
    public AndroidDependenciesModule newModule() {
        return new AndroidDependenciesModule();
    }
}
