package com.google.android.libraries.performance.primes.metrics.memory;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import android.os.StrictMode;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.base.Verify;
import com.google.common.flogger.GoogleLogger;
import com.google.common.io.Files;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.MemoryMetric$AndroidMemoryStats;
import logs.proto.wireless.performance.mobile.MemoryMetric$DeviceStats;
import logs.proto.wireless.performance.mobile.MemoryMetric$MemoryStats;
import logs.proto.wireless.performance.mobile.MemoryMetric$MemoryUsageMetric;
import logs.proto.wireless.performance.mobile.ProcessProto$ProcessStats;

/* compiled from: PG */
/* loaded from: classes.dex */
final class MemoryUsageCapture {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/memory/MemoryUsageCapture");
    private static Supplier otherPssGetter = Suppliers.memoize(MemoryUsageCapture$$ExternalSyntheticLambda1.INSTANCE);
    private final Context appContext;
    private final Provider configsProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProcStatus {
        Long anonRssKb;
        Long rssHwmKb;
        Long swapKb;
        Long totalRssKb;
        Long vmSizeKb;
        static final Pattern PROCFS_RSS_HIGH_WATERMARK_IN_KILOBYTES = Pattern.compile("VmHWM:\\s*(\\d+)\\s*kB");
        static final Pattern PROCFS_RSS_IN_KILOBYTES = Pattern.compile("VmRSS:\\s*(\\d+)\\s*kB");
        static final Pattern PROCFS_ANON_RSS_IN_KILOBYTES = Pattern.compile("RssAnon:\\s*(\\d+)\\s*kB");
        static final Pattern PROCFS_SWAP_IN_KILOBYTES = Pattern.compile("VmSwap:\\s*(\\d+)\\s*kB");
        static final Pattern PROCFS_VM_SIZE_IN_KILOBYTES = Pattern.compile("VmSize:\\s*(\\d+)\\s*kB");

        ProcStatus() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public MemoryUsageCapture(Provider provider, Context context) {
        this.configsProvider = provider;
        this.appContext = context;
    }

    private static void addDebugInfoToMemoryStats(MemoryMetric$AndroidMemoryStats.Builder builder, Debug.MemoryInfo memoryInfo) {
        int otherGraphicsPss;
        if (memoryInfo == null) {
            return;
        }
        builder.setDalvikPssKb(memoryInfo.dalvikPss).setNativePssKb(memoryInfo.nativePss).setOtherPssKb(memoryInfo.otherPss).setDalvikPrivateDirtyKb(memoryInfo.dalvikPrivateDirty).setNativePrivateDirtyKb(memoryInfo.nativePrivateDirty).setOtherPrivateDirtyKb(memoryInfo.otherPrivateDirty).setTotalPssByMemInfoKb(memoryInfo.getTotalPss());
        if (Build.VERSION.SDK_INT >= 19) {
            builder.setTotalPrivateCleanKb(memoryInfo.getTotalPrivateClean()).setTotalSwappablePssKb(memoryInfo.getTotalSwappablePss());
        }
        builder.setTotalSharedDirtyKb(memoryInfo.getTotalSharedDirty());
        if (Build.VERSION.SDK_INT >= 19 && (otherGraphicsPss = getOtherGraphicsPss(memoryInfo)) != -1) {
            builder.setOtherGraphicsPssKb(otherGraphicsPss);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Map<String, String> memoryStats = memoryInfo.getMemoryStats();
                Integer integer = toInteger(memoryStats.get("summary.code"));
                if (integer != null) {
                    builder.setSummaryCodeKb(integer.intValue());
                }
                Integer integer2 = toInteger(memoryStats.get("summary.stack"));
                if (integer2 != null) {
                    builder.setSummaryStackKb(integer2.intValue());
                }
                Integer integer3 = toInteger(memoryStats.get("summary.graphics"));
                if (integer3 != null) {
                    builder.setSummaryGraphicsKb(integer3.intValue());
                }
                Integer integer4 = toInteger(memoryStats.get("summary.system"));
                if (integer4 != null) {
                    builder.setSummarySystemKb(integer4.intValue());
                }
                Integer integer5 = toInteger(memoryStats.get("summary.java-heap"));
                if (integer5 != null) {
                    builder.setSummaryJavaHeapKb(integer5.intValue());
                }
                Integer integer6 = toInteger(memoryStats.get("summary.private-other"));
                if (integer6 != null) {
                    builder.setSummaryPrivateOtherKb(integer6.intValue());
                }
            } catch (NumberFormatException e) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atSevere()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/memory/MemoryUsageCapture", "addDebugInfoToMemoryStats", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMM_VIEW_PORT_LOGGING_VALUE, "MemoryUsageCapture.java")).log("failed to collect memory summary stats");
            }
        }
    }

    private static void addMemoryInfoToMemoryStats(MemoryMetric$AndroidMemoryStats.Builder builder, ActivityManager.MemoryInfo memoryInfo) {
        if (memoryInfo == null) {
            return;
        }
        builder.setAvailableMemoryKb((int) (memoryInfo.availMem >> 10)).setTotalMemoryMb((int) (memoryInfo.totalMem >> 20));
    }

    private static void addProcStatusToMemoryStats(MemoryMetric$AndroidMemoryStats.Builder builder, ProcStatus procStatus) {
        if (procStatus == null) {
            return;
        }
        if (procStatus.rssHwmKb != null) {
            builder.setRssHwmKb(procStatus.rssHwmKb.longValue());
        }
        if (procStatus.totalRssKb != null) {
            builder.setTotalRssKb(procStatus.totalRssKb.longValue());
        }
        if (procStatus.anonRssKb != null) {
            builder.setAnonRssKb(procStatus.anonRssKb.longValue());
        }
        if (procStatus.swapKb != null) {
            builder.setSwapKb(procStatus.swapKb.longValue());
        }
        if (procStatus.vmSizeKb != null) {
            builder.setVmSizeKb(procStatus.vmSizeKb.longValue());
        }
    }

    private static int getOtherGraphicsPss(Debug.MemoryInfo memoryInfo) {
        Method method = (Method) ((Optional) otherPssGetter.get()).orNull();
        if (method == null) {
            return -1;
        }
        try {
            return ((Integer) method.invoke(memoryInfo, 14)).intValue();
        } catch (Error | Exception e) {
            otherPssGetter = MemoryUsageCapture$$ExternalSyntheticLambda0.INSTANCE;
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atSevere()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/memory/MemoryUsageCapture", "getOtherGraphicsPss", 119, "MemoryUsageCapture.java")).log("MemoryInfo.getOtherPss(which) invocation failure");
            return -1;
        }
    }

    private static ProcStatus getProcStatus() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                return procStatusFromString(Files.asCharSource(new File("/proc/self/status"), Charset.defaultCharset()).read());
            } catch (IOException e) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atSevere()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/memory/MemoryUsageCapture", "getProcStatus", 249, "MemoryUsageCapture.java")).log("Error reading proc status");
                StrictMode.setThreadPolicy(allowThreadDiskReads);
                return null;
            }
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Optional lambda$static$0() {
        try {
            return Optional.of(Debug.MemoryInfo.class.getDeclaredMethod("getOtherPss", Integer.TYPE));
        } catch (Error e) {
            e = e;
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atSevere()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/memory/MemoryUsageCapture", "lambda$static$0", 94, "MemoryUsageCapture.java")).log("MemoryInfo.getOtherPss(which) failure");
            return Optional.absent();
        } catch (NoSuchMethodException e2) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withCause(e2)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/memory/MemoryUsageCapture", "lambda$static$0", 92, "MemoryUsageCapture.java")).log("MemoryInfo.getOtherPss(which) not found");
            return Optional.absent();
        } catch (Exception e3) {
            e = e3;
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atSevere()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/memory/MemoryUsageCapture", "lambda$static$0", 94, "MemoryUsageCapture.java")).log("MemoryInfo.getOtherPss(which) failure");
            return Optional.absent();
        }
    }

    static ProcStatus procStatusFromString(String str) {
        if (str.isEmpty()) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atSevere()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/memory/MemoryUsageCapture", "procStatusFromString", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_VERIFY_URL_VALUE, "MemoryUsageCapture.java")).log("Null or empty proc status");
            return null;
        }
        ProcStatus procStatus = new ProcStatus();
        procStatus.rssHwmKb = tryParseLong(ProcStatus.PROCFS_RSS_HIGH_WATERMARK_IN_KILOBYTES, str);
        procStatus.totalRssKb = tryParseLong(ProcStatus.PROCFS_RSS_IN_KILOBYTES, str);
        procStatus.anonRssKb = tryParseLong(ProcStatus.PROCFS_ANON_RSS_IN_KILOBYTES, str);
        procStatus.swapKb = tryParseLong(ProcStatus.PROCFS_SWAP_IN_KILOBYTES, str);
        procStatus.vmSizeKb = tryParseLong(ProcStatus.PROCFS_VM_SIZE_IN_KILOBYTES, str);
        return procStatus;
    }

    static MemoryMetric$AndroidMemoryStats toAndroidMemoryStats(Debug.MemoryInfo memoryInfo, ActivityManager.MemoryInfo memoryInfo2, ProcStatus procStatus) {
        MemoryMetric$AndroidMemoryStats.Builder newBuilder = MemoryMetric$AndroidMemoryStats.newBuilder();
        addDebugInfoToMemoryStats(newBuilder, memoryInfo);
        addMemoryInfoToMemoryStats(newBuilder, memoryInfo2);
        addProcStatusToMemoryStats(newBuilder, procStatus);
        return (MemoryMetric$AndroidMemoryStats) newBuilder.build();
    }

    private static Integer toInteger(String str) {
        if (str == null) {
            return null;
        }
        return Integer.valueOf(Integer.parseInt(str));
    }

    private static Long tryParseLong(Pattern pattern, String str) {
        Matcher matcher = pattern.matcher(str);
        try {
            if (matcher.find()) {
                return Long.valueOf(Long.parseLong((String) Verify.verifyNotNull(matcher.group(1))));
            }
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MemoryMetric$MemoryUsageMetric getMemoryUsageMetric(MemoryMetric$MemoryUsageMetric.MemoryEventCode memoryEventCode, int i, String str, String str2, boolean z) {
        Debug.MemoryInfo memoryInfo;
        ActivityManager.MemoryInfo memoryInfo2;
        MemoryConfigurations memoryConfigurations = (MemoryConfigurations) this.configsProvider.get();
        ThreadUtil.ensureBackgroundThread();
        if (z || memoryConfigurations.getCaptureDebugMetrics()) {
            memoryInfo = ProcessStats.getActivityManager(this.appContext).getProcessMemoryInfo(new int[]{i})[0];
        } else {
            memoryInfo = null;
        }
        if (memoryConfigurations.getCaptureMemoryInfo()) {
            memoryInfo2 = new ActivityManager.MemoryInfo();
            ProcessStats.getActivityManager(this.appContext).getMemoryInfo(memoryInfo2);
        } else {
            memoryInfo2 = null;
        }
        MemoryMetric$MemoryUsageMetric.Builder memoryEventCode2 = MemoryMetric$MemoryUsageMetric.newBuilder().setMemoryStats(MemoryMetric$MemoryStats.newBuilder().setAndroidMemoryStats(toAndroidMemoryStats(memoryInfo, memoryInfo2, z ? null : getProcStatus()))).setProcessStats(ProcessProto$ProcessStats.newBuilder().setAndroidProcessStats(ProcessStatsCapture.getAndroidProcessStats(str, this.appContext))).setDeviceStats(MemoryMetric$DeviceStats.newBuilder().setIsScreenOn(ProcessStats.isScreenOn(this.appContext))).setMemoryEventCode(memoryEventCode);
        if (str2 != null) {
            memoryEventCode2.setActivityName(str2);
        }
        return (MemoryMetric$MemoryUsageMetric) memoryEventCode2.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MemoryMetric$MemoryUsageMetric getMemoryUsageMetric(MemoryMetric$MemoryUsageMetric.MemoryEventCode memoryEventCode, String str, boolean z) {
        return getMemoryUsageMetric(memoryEventCode, Process.myPid(), null, str, z);
    }
}
