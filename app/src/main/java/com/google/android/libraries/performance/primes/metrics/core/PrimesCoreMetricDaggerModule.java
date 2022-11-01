package com.google.android.libraries.performance.primes.metrics.core;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.common.flogger.GoogleLogger;
import javax.inject.Singleton;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrimesCoreMetricDaggerModule {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/core/PrimesCoreMetricDaggerModule");

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static String provideVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        try {
            return packageManager.getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/core/PrimesCoreMetricDaggerModule", "provideVersionName", 87, "PrimesCoreMetricDaggerModule.java")).log("Failed to get PackageInfo for: %s", packageName);
            return null;
        }
    }
}
