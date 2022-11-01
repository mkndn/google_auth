package com.google.android.libraries.performance.primes.metrics.startup;

import com.google.android.libraries.performance.primes.NoPiiString;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StartupMeasure {
    private static final StartupMeasure instance = new StartupMeasure();
    private volatile long appAttachBaseContextAt;
    private volatile long appAttachBaseContextFinishedAt;
    private volatile long appClassLoadedAt;
    private volatile long appOnCreateAt;
    private volatile long appOnCreateFinishedAt;
    private volatile long firstAppInteractiveAt;
    private volatile long firstOnActivityInitAt;
    private volatile long onDrawBasedFirstDrawnAt;
    private volatile long onDrawFrontOfQueueBasedFirstDrawnAt;
    private volatile long preDrawBasedFirstDrawnAt;
    private volatile long preDrawFrontOfQueueBasedFirstDrawnAt;
    private volatile boolean startedByUser;
    private volatile NoPiiString startupAccountableComponentName;
    private final TimestampsRecorded timestampsRecorded = new TimestampsRecorded();
    private final StartupActivityInfo firstActivity = new StartupActivityInfo();
    private final StartupActivityInfo lastActivity = new StartupActivityInfo();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class StartupActivityInfo {
        volatile Long createdAt;
        volatile String name;
        volatile Long resumedAt;
        volatile Long startedAt;

        StartupActivityInfo() {
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TimestampsRecorded {
        volatile boolean appAttachBaseContext;
        volatile boolean appAttachBaseContextFinished;
        volatile boolean appClassLoaded;
        volatile boolean appOnCreate;
        volatile boolean appOnCreateFinished;
        volatile boolean firstAppInteractive;
        volatile boolean firstOnActivityInit;
        volatile boolean onDrawBasedFirstDrawn;
        volatile boolean onDrawFrontOfQueueBasedFirstDrawn;
        volatile boolean preDrawBasedFirstDrawn;
        volatile boolean preDrawFrontOfQueueBasedFirstDrawn;

        TimestampsRecorded() {
        }
    }

    StartupMeasure() {
    }

    public static StartupMeasure get() {
        return instance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getAppAttachBaseContextAt() {
        return this.appAttachBaseContextAt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getAppAttachBaseContextFinishedAt() {
        return this.appAttachBaseContextFinishedAt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getAppClassLoadedAt() {
        return this.appClassLoadedAt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getAppOnCreateAt() {
        return this.appOnCreateAt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getAppOnCreateFinishedAt() {
        return this.appOnCreateFinishedAt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StartupActivityInfo getFirstActivity() {
        return this.firstActivity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getFirstAppInteractiveAt() {
        return this.firstAppInteractiveAt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getFirstOnActivityInitAt() {
        return this.firstOnActivityInitAt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StartupActivityInfo getLastActivity() {
        return this.lastActivity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getOnDrawBasedFirstDrawnAt() {
        return this.onDrawBasedFirstDrawnAt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getOnDrawFrontOfQueueBasedFirstDrawnAt() {
        return this.onDrawFrontOfQueueBasedFirstDrawnAt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getPreDrawBasedFirstDrawnAt() {
        return this.preDrawBasedFirstDrawnAt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getPreDrawFrontOfQueueBasedFirstDrawnAt() {
        return this.preDrawFrontOfQueueBasedFirstDrawnAt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NoPiiString getStartupAccountableComponent() {
        return this.startupAccountableComponentName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimestampsRecorded getTimestampsRecorded() {
        return this.timestampsRecorded;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isColdStartup() {
        return this.startedByUser;
    }
}
