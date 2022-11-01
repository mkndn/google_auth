package com.google.android.libraries.performance.primes.metrics.crash.applicationexit;

import android.app.ActivityManager;
import android.content.Context;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.inject.Inject;
import logs.proto.wireless.performance.mobile.ApplicationExitInfo;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ApplicationExitInfoCaptureImpl implements ApplicationExitInfoCapture {
    private final Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public ApplicationExitInfoCaptureImpl(Context context) {
        this.context = context;
    }

    private ApplicationExitInfo applicationExitInfoToProto(android.app.ApplicationExitInfo applicationExitInfo) {
        ApplicationExitInfo.Builder lowMemoryKillSupported = ApplicationExitInfo.newBuilder().setProcessName(applicationExitInfo.getProcessName()).setStatus(applicationExitInfo.getStatus()).setTimestampMillis(applicationExitInfo.getTimestamp()).setPssKb(applicationExitInfo.getPss()).setRssKb(applicationExitInfo.getRss()).setLowMemoryKillSupported(ActivityManager.isLowMemoryKillReportSupported());
        ApplicationExitInfo.Reason reasonEnum = toReasonEnum(applicationExitInfo.getReason());
        if (reasonEnum != null) {
            lowMemoryKillSupported.setReason(reasonEnum);
        }
        ApplicationExitInfo.Importance importanceEnum = toImportanceEnum(applicationExitInfo.getImportance());
        if (importanceEnum != null) {
            lowMemoryKillSupported.setImportance(importanceEnum);
        }
        return (ApplicationExitInfo) lowMemoryKillSupported.build();
    }

    private ApplicationExitInfo.Importance toImportanceEnum(int i) {
        switch (i) {
            case 100:
                return ApplicationExitInfo.Importance.FOREGROUND;
            case 125:
                return ApplicationExitInfo.Importance.FOREGROUND_SERVICE;
            case 200:
                return ApplicationExitInfo.Importance.VISIBLE;
            case UC_ADS_EXTERNAL_INTEGRATION_VALUE:
                return ApplicationExitInfo.Importance.PERCEPTIBLE;
            case UC_PLAY_CONSOLE_MEASURING_USER_ENGAGEMENT_VALUE:
                return ApplicationExitInfo.Importance.SERVICE;
            case UC_CARE_STUDIO_MEASURING_USER_ENGAGEMENT_VALUE:
                return ApplicationExitInfo.Importance.TOP_SLEEPING;
            case 350:
                return ApplicationExitInfo.Importance.CANT_SAVE_STATE;
            case UC_MESSAGES_SUPERSORT_PRODUCT_IMPROVEMENT_VALUE:
                return ApplicationExitInfo.Importance.CACHED;
            case 1000:
                return ApplicationExitInfo.Importance.GONE;
            default:
                return null;
        }
    }

    private ApplicationExitInfo.Reason toReasonEnum(int i) {
        switch (i) {
            case 0:
                return ApplicationExitInfo.Reason.UNKNOWN;
            case 1:
                return ApplicationExitInfo.Reason.EXIT_SELF;
            case 2:
                return ApplicationExitInfo.Reason.SIGNALED;
            case 3:
                return ApplicationExitInfo.Reason.LOW_MEMORY;
            case 4:
                return ApplicationExitInfo.Reason.CRASH;
            case 5:
                return ApplicationExitInfo.Reason.CRASH_NATIVE;
            case 6:
                return ApplicationExitInfo.Reason.ANR;
            case 7:
                return ApplicationExitInfo.Reason.INITIALIZATION_FAILURE;
            case 8:
                return ApplicationExitInfo.Reason.PERMISSION_CHANGE;
            case 9:
                return ApplicationExitInfo.Reason.EXCESSIVE_RESOURCE_USAGE;
            case 10:
                return ApplicationExitInfo.Reason.USER_REQUESTED;
            case 11:
                return ApplicationExitInfo.Reason.USER_STOPPED;
            case 12:
                return ApplicationExitInfo.Reason.DEPENDENCY_DIED;
            case 13:
                return ApplicationExitInfo.Reason.OTHER;
            default:
                return null;
        }
    }

    @Override // com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitInfoCapture
    public ImmutableList getApplicationExits(int i, int i2, String str, long j) {
        ActivityManager activityManager = (ActivityManager) this.context.getSystemService("activity");
        Preconditions.checkNotNull(activityManager);
        List<android.app.ApplicationExitInfo> historicalProcessExitReasons = activityManager.getHistoricalProcessExitReasons(this.context.getPackageName(), i, i2);
        ImmutableList.Builder builder = ImmutableList.builder();
        for (android.app.ApplicationExitInfo applicationExitInfo : historicalProcessExitReasons) {
            if (applicationExitInfo.getProcessName().equals(str) && applicationExitInfo.getTimestamp() == j) {
                break;
            }
            builder.add((Object) applicationExitInfoToProto(applicationExitInfo));
        }
        return builder.build();
    }
}
