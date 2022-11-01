package com.google.android.gms.common.internal.service;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.clienttelemetry.Features;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: PG */
/* loaded from: classes.dex */
public class InternalTelemetryLoggingClient extends GoogleApi implements TelemetryLoggingClient {
    private static final Api API;
    private static final Api.ClientKey CLIENT_KEY;
    private static final Api.AbstractClientBuilder clientBuilder;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        CLIENT_KEY = clientKey;
        Api.AbstractClientBuilder abstractClientBuilder = new Api.AbstractClientBuilder() { // from class: com.google.android.gms.common.internal.service.InternalTelemetryLoggingClient.1
            @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
            public TelemetryLoggingClientImpl buildClient(Context context, Looper looper, ClientSettings clientSettings, TelemetryLoggingOptions telemetryLoggingOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
                return new TelemetryLoggingClientImpl(context, looper, clientSettings, telemetryLoggingOptions, connectionCallbacks, onConnectionFailedListener);
            }
        };
        clientBuilder = abstractClientBuilder;
        API = new Api("ClientTelemetry.API", abstractClientBuilder, clientKey);
    }

    public InternalTelemetryLoggingClient(Context context, TelemetryLoggingOptions telemetryLoggingOptions) {
        super(context, API, telemetryLoggingOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public static /* synthetic */ void lambda$log$0(TelemetryData telemetryData, TelemetryLoggingClientImpl telemetryLoggingClientImpl, TaskCompletionSource taskCompletionSource) {
        ((IClientTelemetryService) telemetryLoggingClientImpl.getService()).recordData(telemetryData);
        taskCompletionSource.setResult(null);
    }

    @Override // com.google.android.gms.common.internal.TelemetryLoggingClient
    public Task log(final TelemetryData telemetryData) {
        return doBestEffortWrite(TaskApiCall.builder().setFeatures(Features.CLIENT_TELEMETRY).setAutoResolveMissingFeatures(false).run(new RemoteCall() { // from class: com.google.android.gms.common.internal.service.InternalTelemetryLoggingClient$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                InternalTelemetryLoggingClient.lambda$log$0(TelemetryData.this, (TelemetryLoggingClientImpl) obj, (TaskCompletionSource) obj2);
            }
        }).build());
    }
}
