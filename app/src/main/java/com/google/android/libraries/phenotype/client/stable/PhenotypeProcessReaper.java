package com.google.android.libraries.phenotype.client.stable;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeProcessReaper implements ProcessReaper {
    private static boolean scheduled;
    private final Supplier executorProvider;
    private final Supplier isKillable;
    private final int pollingMinutes;

    public PhenotypeProcessReaper(Supplier supplier) {
        this(supplier, 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isKillable() {
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        try {
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            Log.i("PhenotypeProcessReaper", "Memory state is: " + runningAppProcessInfo.importance);
            return runningAppProcessInfo.importance >= 400;
        } catch (RuntimeException e) {
            Log.w("PhenotypeProcessReaper", "Failed to retrieve memory state, not killing process.", e);
            return false;
        }
    }

    private static void kill() {
        Log.i("PhenotypeProcessReaper", "Killing process to refresh experiment configuration");
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    private void scheduleRepeating(final Runnable runnable, final long j, final TimeUnit timeUnit, final ListeningScheduledExecutorService listeningScheduledExecutorService) {
        PhenotypeExecutor.crashOnFailure(listeningScheduledExecutorService.schedule(new Runnable(this) { // from class: com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper.1
            @Override // java.lang.Runnable
            public void run() {
                runnable.run();
                PhenotypeExecutor.crashOnFailure(listeningScheduledExecutorService.schedule((Runnable) this, j, timeUnit));
            }
        }, j, timeUnit));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$scheduleReap$0$com-google-android-libraries-phenotype-client-stable-PhenotypeProcessReaper  reason: not valid java name */
    public /* synthetic */ void m376x50b99ce3() {
        if (((Boolean) this.isKillable.get()).booleanValue()) {
            kill();
        }
    }

    @Override // com.google.android.libraries.phenotype.client.stable.ProcessReaper
    public void scheduleReap() {
        if (Build.VERSION.SDK_INT < 16) {
            return;
        }
        synchronized (PhenotypeProcessReaper.class) {
            if (!scheduled) {
                scheduleRepeating(new Runnable() { // from class: com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        PhenotypeProcessReaper.this.m376x50b99ce3();
                    }
                }, this.pollingMinutes, TimeUnit.MINUTES, (ListeningScheduledExecutorService) this.executorProvider.get());
                scheduled = true;
            }
        }
    }

    public PhenotypeProcessReaper(Supplier supplier, int i) {
        this(supplier, i, PhenotypeProcessReaper$$ExternalSyntheticLambda1.INSTANCE);
    }

    public PhenotypeProcessReaper(Supplier supplier, int i, Supplier supplier2) {
        this.executorProvider = supplier;
        this.pollingMinutes = Math.max(5, i);
        this.isKillable = supplier2;
    }
}
