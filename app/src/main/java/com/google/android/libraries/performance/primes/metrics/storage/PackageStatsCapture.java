package com.google.android.libraries.performance.primes.metrics.storage;

import android.content.Context;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Build;
import android.os.Process;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.common.flogger.GoogleLogger;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class PackageStatsCapture {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCapture");
    static final PackageStatsInvocation[] GETTER_INVOCATIONS = {new PackageStatsInvocation("getPackageSizeInfo", new Class[]{String.class, IPackageStatsObserver.class}), new PackageStatsInvocation("getPackageSizeInfo", new Class[]{String.class, Integer.TYPE, IPackageStatsObserver.class}), new PackageStatsInvocation("getPackageSizeInfoAsUser", new Class[]{String.class, Integer.TYPE, IPackageStatsObserver.class})};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PackageStatsCallback extends IPackageStatsObserver.Stub {
        private volatile PackageStats packageStats;
        private final Semaphore semaphore;

        private PackageStatsCallback() {
            this.semaphore = new Semaphore(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void acquireLock() {
            this.semaphore.acquire();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public PackageStats waitForStats(long j) {
            if (this.semaphore.tryAcquire(j, TimeUnit.MILLISECONDS)) {
                return this.packageStats;
            }
            ((GoogleLogger.Api) ((GoogleLogger.Api) PackageStatsCapture.logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCapture$PackageStatsCallback", "waitForStats", 63, "PackageStatsCapture.java")).log("Timeout while waiting for PackageStats callback");
            return null;
        }

        public void onGetStatsCompleted(PackageStats packageStats, boolean z) {
            if (z) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) PackageStatsCapture.logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCapture$PackageStatsCallback", "onGetStatsCompleted", 48, "PackageStatsCapture.java")).log("Success getting PackageStats: %s", packageStats);
                this.packageStats = packageStats;
            } else {
                ((GoogleLogger.Api) ((GoogleLogger.Api) PackageStatsCapture.logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCapture$PackageStatsCallback", "onGetStatsCompleted", 51, "PackageStatsCapture.java")).log("Failure getting PackageStats");
            }
            this.semaphore.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PackageStatsInvocation {
        private final String methodName;
        private final Class[] paramTypes;

        PackageStatsInvocation(String str, Class[] clsArr) {
            this.methodName = str;
            this.paramTypes = clsArr;
        }

        boolean invoke(PackageManager packageManager, String str, int i, IPackageStatsObserver iPackageStatsObserver) {
            try {
                packageManager.getClass().getMethod(this.methodName, this.paramTypes).invoke(packageManager, params(str, i, iPackageStatsObserver));
                return true;
            } catch (Error e) {
                e = e;
                ((GoogleLogger.Api) ((GoogleLogger.Api) PackageStatsCapture.logger.atInfo()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCapture$PackageStatsInvocation", "invoke", 119, "PackageStatsCapture.java")).log("%s for %s (%s) invocation", e.getClass().getSimpleName(), this.methodName, Arrays.asList(this.paramTypes));
                return false;
            } catch (NoSuchMethodException e2) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PackageStatsCapture.logger.atFine()).withCause(e2)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCapture$PackageStatsInvocation", "invoke", 113, "PackageStatsCapture.java")).log("PackageStats getter not found");
                return false;
            } catch (Exception e3) {
                e = e3;
                ((GoogleLogger.Api) ((GoogleLogger.Api) PackageStatsCapture.logger.atInfo()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCapture$PackageStatsInvocation", "invoke", 119, "PackageStatsCapture.java")).log("%s for %s (%s) invocation", e.getClass().getSimpleName(), this.methodName, Arrays.asList(this.paramTypes));
                return false;
            }
        }

        Object[] params(String str, int i, IPackageStatsObserver iPackageStatsObserver) {
            Class[] clsArr = this.paramTypes;
            if (clsArr.length == 2 && clsArr[0] == String.class && clsArr[1] == IPackageStatsObserver.class) {
                return new Object[]{str, iPackageStatsObserver};
            }
            if (clsArr.length == 3 && clsArr[0] == String.class && clsArr[1] == Integer.TYPE && this.paramTypes[2] == IPackageStatsObserver.class) {
                return new Object[]{str, Integer.valueOf(i), iPackageStatsObserver};
            }
            throw new IllegalArgumentException("Invalid parameter for PackageStatsInvocation.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PackageStats getPackageStats(Context context) {
        ThreadUtil.ensureBackgroundThread();
        if (Build.VERSION.SDK_INT >= 26) {
            return PackageStatsCaptureO.getPackageStats(context);
        }
        if (hasPkgPermission(context)) {
            return getPackageStatsUsingInternalAPI(context, 15000L, GETTER_INVOCATIONS);
        }
        ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCapture", "getPackageStats", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTACTS_ACCOUNT_TYPE_LOGGING_VALUE, "PackageStatsCapture.java")).log("%s required", "android.permission.GET_PACKAGE_SIZE");
        return null;
    }

    static PackageStats getPackageStatsUsingInternalAPI(Context context, long j, PackageStatsInvocation... packageStatsInvocationArr) {
        if (!isCallbackPresent()) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCapture", "getPackageStatsUsingInternalAPI", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_REFINING_EXPERIMENTS_VALUE, "PackageStatsCapture.java")).log("Callback implementation stripped by proguard.");
            return null;
        }
        PackageStatsCallback packageStatsCallback = new PackageStatsCallback();
        try {
            packageStatsCallback.acquireLock();
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            int myUid = Process.myUid();
            for (PackageStatsInvocation packageStatsInvocation : packageStatsInvocationArr) {
                if (packageStatsInvocation.invoke(packageManager, packageName, myUid, packageStatsCallback)) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atInfo()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCapture", "getPackageStatsUsingInternalAPI", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FMD_UNPAIR_VALUE, "PackageStatsCapture.java")).log("Success invoking PackageStats capture.");
                    return packageStatsCallback.waitForStats(j);
                }
            }
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCapture", "getPackageStatsUsingInternalAPI", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FIT_FUNCTIONAL_DEBUGGING_VALUE, "PackageStatsCapture.java")).log("Couldn't capture PackageStats.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    private static boolean isCallbackPresent() {
        try {
            return !Modifier.isAbstract(PackageStatsCallback.class.getMethod("onGetStatsCompleted", PackageStats.class, Boolean.TYPE).getModifiers());
        } catch (Error | Exception e) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/PackageStatsCapture", "isCallbackPresent", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_1P_APP_FUNCTIONAL_DEBUGGING_VALUE, "PackageStatsCapture.java")).log("failure");
            return false;
        }
    }

    private static boolean hasPkgPermission(Context context) {
        if (context.getPackageManager().checkPermission("android.permission.GET_PACKAGE_SIZE", context.getPackageName()) != 0 && context.checkCallingOrSelfPermission("android.permission.GET_PACKAGE_SIZE") != 0) {
            return false;
        }
        return true;
    }
}
