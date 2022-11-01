package com.google.android.gms.common.internal;

import android.content.Context;
import com.google.android.gms.common.internal.service.InternalTelemetryLoggingClient;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TelemetryLogging {
    public static TelemetryLoggingClient getClient(Context context) {
        return getClient(context, TelemetryLoggingOptions.DEFAULT_OPTIONS);
    }

    public static TelemetryLoggingClient getClient(Context context, TelemetryLoggingOptions telemetryLoggingOptions) {
        return new InternalTelemetryLoggingClient(context, telemetryLoggingOptions);
    }
}
