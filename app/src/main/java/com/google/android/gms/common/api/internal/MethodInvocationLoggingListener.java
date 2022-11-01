package com.google.android.gms.common.api.internal;

import android.os.SystemClock;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.CallingContext;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class MethodInvocationLoggingListener implements OnCompleteListener {
    private final ApiKey apiKey;
    private final String callingEntryPoint;
    private final String callingModuleId;
    private final GoogleApiManager googleApiManager;
    private final int methodKey;
    private final long methodStartElapsedRealtime;
    private final long methodStartTimeMillis;

    MethodInvocationLoggingListener(GoogleApiManager googleApiManager, int i, ApiKey apiKey, long j, long j2, String str, String str2) {
        this.googleApiManager = googleApiManager;
        this.methodKey = i;
        this.apiKey = apiKey;
        this.methodStartTimeMillis = j;
        this.methodStartElapsedRealtime = j2;
        this.callingModuleId = str;
        this.callingEntryPoint = str2;
    }

    private static ConnectionTelemetryConfiguration getConfigIfShouldLogMethodInvocation(GoogleApiManager.ClientConnection clientConnection, BaseGmsClient baseGmsClient, int i) {
        ConnectionTelemetryConfiguration telemetryConfiguration = baseGmsClient.getTelemetryConfiguration();
        if (telemetryConfiguration == null || !methodInvocationTelemetryEnabled(telemetryConfiguration, i) || clientConnection.getNumMethodInvocationsLogged() >= telemetryConfiguration.getMaxMethodInvocationsLogged()) {
            return null;
        }
        return telemetryConfiguration;
    }

    private static boolean isExpectedStatusCode(int i) {
        return i == 0 || i == 7;
    }

    private MethodInvocation makeMethodInvocation(Task task, boolean z, int i) {
        int i2;
        int i3;
        long j;
        long j2;
        int i4;
        String str;
        if (!task.isSuccessful()) {
            if (task.isCanceled()) {
                i2 = 100;
                i3 = -1;
            } else {
                Exception exception = task.getException();
                if (exception instanceof ApiException) {
                    Status status = ((ApiException) exception).getStatus();
                    int statusCode = status.getStatusCode();
                    ConnectionResult connectionResult = status.getConnectionResult();
                    i3 = connectionResult == null ? -1 : connectionResult.getErrorCode();
                    i2 = statusCode;
                } else {
                    i2 = 101;
                    i3 = -1;
                }
            }
        } else {
            i2 = 0;
            i3 = 0;
        }
        if (z) {
            j = this.methodStartTimeMillis;
            j2 = System.currentTimeMillis();
            i4 = (int) (SystemClock.elapsedRealtime() - this.methodStartElapsedRealtime);
        } else {
            j = 0;
            j2 = 0;
            i4 = -1;
        }
        int i5 = this.methodKey;
        String str2 = this.callingModuleId;
        if (this.callingEntryPoint == null || isExpectedStatusCode(i2)) {
            str = null;
        } else {
            str = this.callingEntryPoint;
        }
        return new MethodInvocation(i5, i2, i3, j, j2, str2, str, i, i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MethodInvocationLoggingListener maybeMakeListener(GoogleApiManager googleApiManager, int i, ApiKey apiKey) {
        boolean z;
        String callingModuleId;
        if (googleApiManager.isClientTelemetryPossiblyEnabled()) {
            RootTelemetryConfiguration config = RootTelemetryConfigManager.getInstance().getConfig();
            if (config == null) {
                z = true;
            } else if (!config.getMethodInvocationTelemetryEnabled()) {
                return null;
            } else {
                z = config.getMethodTimingTelemetryEnabled();
                GoogleApiManager.ClientConnection clientConnectionForKey = googleApiManager.getClientConnectionForKey(apiKey);
                if (clientConnectionForKey != null) {
                    if (!(clientConnectionForKey.getClient() instanceof BaseGmsClient)) {
                        return null;
                    }
                    BaseGmsClient baseGmsClient = (BaseGmsClient) clientConnectionForKey.getClient();
                    if (baseGmsClient.hasConnectionInfo() && !baseGmsClient.isConnecting()) {
                        ConnectionTelemetryConfiguration configIfShouldLogMethodInvocation = getConfigIfShouldLogMethodInvocation(clientConnectionForKey, baseGmsClient, i);
                        if (configIfShouldLogMethodInvocation == null) {
                            return null;
                        }
                        clientConnectionForKey.incrementNumMethodInvocationsLogged();
                        z = configIfShouldLogMethodInvocation.getMethodTimingTelemetryEnabled();
                    }
                }
            }
            if (BuildConstants.IS_PACKAGE_SIDE) {
                CallingContext.ModuleInfo callingModuleInfo = CallingContext.getCallingModuleInfo();
                if (callingModuleInfo == null || !callingModuleInfo.getTelemetryDisabled()) {
                    long currentTimeMillis = z ? System.currentTimeMillis() : 0L;
                    long elapsedRealtime = z ? SystemClock.elapsedRealtime() : 0L;
                    if (callingModuleInfo != null) {
                        callingModuleId = callingModuleInfo.getCallingModuleId();
                    } else {
                        callingModuleId = null;
                    }
                    return new MethodInvocationLoggingListener(googleApiManager, i, apiKey, currentTimeMillis, elapsedRealtime, callingModuleId, callingModuleInfo != null ? callingModuleInfo.getCallingEntryPoint() : null);
                }
                return null;
            }
            return new MethodInvocationLoggingListener(googleApiManager, i, apiKey, z ? System.currentTimeMillis() : 0L, z ? SystemClock.elapsedRealtime() : 0L, null, null);
        }
        return null;
    }

    private static boolean methodInvocationTelemetryEnabled(ConnectionTelemetryConfiguration connectionTelemetryConfiguration, int i) {
        if (connectionTelemetryConfiguration.getMethodInvocationTelemetryEnabled()) {
            int[] methodInvocationMethodKeyAllowlist = connectionTelemetryConfiguration.getMethodInvocationMethodKeyAllowlist();
            if (methodInvocationMethodKeyAllowlist == null) {
                int[] methodInvocationMethodKeyDisallowlist = connectionTelemetryConfiguration.getMethodInvocationMethodKeyDisallowlist();
                if (methodInvocationMethodKeyDisallowlist == null) {
                    return true;
                }
                return !ArrayUtils.contains(methodInvocationMethodKeyDisallowlist, i);
            }
            return ArrayUtils.contains(methodInvocationMethodKeyAllowlist, i);
        }
        return false;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public void onComplete(Task task) {
        GoogleApiManager.ClientConnection clientConnectionForKey;
        int i;
        int i2;
        int i3;
        if (this.googleApiManager.isClientTelemetryPossiblyEnabled()) {
            RootTelemetryConfiguration config = RootTelemetryConfigManager.getInstance().getConfig();
            if ((config == null || config.getMethodInvocationTelemetryEnabled()) && (clientConnectionForKey = this.googleApiManager.getClientConnectionForKey(this.apiKey)) != null && (clientConnectionForKey.getClient() instanceof BaseGmsClient)) {
                BaseGmsClient baseGmsClient = (BaseGmsClient) clientConnectionForKey.getClient();
                boolean z = true;
                boolean z2 = this.methodStartTimeMillis > 0;
                int gCoreServiceId = baseGmsClient.getGCoreServiceId();
                if (config != null) {
                    z2 &= config.getMethodTimingTelemetryEnabled();
                    i2 = config.getBatchPeriodMillis();
                    i = config.getMaxMethodInvocationsInBatch();
                    int version = config.getVersion();
                    if (!baseGmsClient.hasConnectionInfo() || baseGmsClient.isConnecting()) {
                        i3 = version;
                    } else {
                        ConnectionTelemetryConfiguration configIfShouldLogMethodInvocation = getConfigIfShouldLogMethodInvocation(clientConnectionForKey, baseGmsClient, this.methodKey);
                        if (configIfShouldLogMethodInvocation == null) {
                            return;
                        }
                        z = (!configIfShouldLogMethodInvocation.getMethodTimingTelemetryEnabled() || this.methodStartTimeMillis <= 0) ? false : false;
                        i = configIfShouldLogMethodInvocation.getMaxMethodInvocationsLogged();
                        i3 = version;
                        z2 = z;
                    }
                } else {
                    i = 100;
                    i2 = 5000;
                    i3 = 0;
                }
                this.googleApiManager.logMethodInvocation(makeMethodInvocation(task, z2, gCoreServiceId), i3, i2, i);
            }
        }
    }
}
