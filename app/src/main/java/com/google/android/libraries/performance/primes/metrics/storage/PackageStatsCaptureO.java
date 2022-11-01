package com.google.android.libraries.performance.primes.metrics.storage;

import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Process;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.common.flogger.GoogleLogger;
import java.io.IOException;
import java.util.UUID;

/* compiled from: PG */
/* loaded from: classes.dex */
final class PackageStatsCaptureO {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCaptureO");

    private static void addStorageToPackageStats(PackageStats packageStats, StorageStats storageStats, boolean z) {
        if (z) {
            packageStats.codeSize += storageStats.getAppBytes();
            packageStats.dataSize += storageStats.getDataBytes() - storageStats.getCacheBytes();
            packageStats.cacheSize += storageStats.getCacheBytes();
            return;
        }
        packageStats.externalCodeSize += storageStats.getAppBytes();
        packageStats.externalDataSize += storageStats.getDataBytes() - storageStats.getCacheBytes();
        packageStats.externalCacheSize += storageStats.getCacheBytes();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PackageStats getPackageStats(Context context) {
        UUID uuid;
        ThreadUtil.ensureBackgroundThread();
        StorageManager storageManager = (StorageManager) context.getSystemService(StorageManager.class);
        if (storageManager == null) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atSevere()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCaptureO", "getPackageStats", 30, "PackageStatsCaptureO.java")).log("StorageManager is not available");
            return null;
        }
        try {
            try {
                StorageStatsManager storageStatsManager = (StorageStatsManager) context.getSystemService(StorageStatsManager.class);
                String packageName = context.getPackageName();
                PackageStats packageStats = new PackageStats(packageName);
                for (StorageVolume storageVolume : storageManager.getStorageVolumes()) {
                    if (storageVolume.getState().equals("mounted") && (uuid = getUuid(storageVolume)) != null) {
                        try {
                            addStorageToPackageStats(packageStats, storageStatsManager.queryStatsForPackage(uuid, packageName, Process.myUserHandle()), StorageManager.UUID_DEFAULT.equals(uuid));
                        } catch (PackageManager.NameNotFoundException | IOException | RuntimeException e) {
                            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCaptureO", "getPackageStats", 48, "PackageStatsCaptureO.java")).log("queryStatsForPackage() call failed");
                        }
                    }
                }
                return packageStats;
            } catch (Error e2) {
                e = e2;
                ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCaptureO", "getPackageStats", 55, "PackageStatsCaptureO.java")).log("StorageStatsManager is not available");
                return null;
            }
        } catch (RuntimeException e3) {
            e = e3;
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCaptureO", "getPackageStats", 55, "PackageStatsCaptureO.java")).log("StorageStatsManager is not available");
            return null;
        }
    }

    private static UUID getUuid(StorageVolume storageVolume) {
        String uuid = storageVolume.getUuid();
        if ("1AEF-1A1E".equals(uuid)) {
            return null;
        }
        try {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCaptureO", "getUuid", 72, "PackageStatsCaptureO.java")).log("UUID for %s", uuid);
            return uuid == null ? StorageManager.UUID_DEFAULT : UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCaptureO", "getUuid", 77, "PackageStatsCaptureO.java")).log("Invalid UUID format: '%s'", uuid);
            return null;
        }
    }
}
