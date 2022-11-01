package com.google.android.gms.common.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RootTelemetryConfigManager {
    private RootTelemetryConfiguration config;
    private static RootTelemetryConfigManager instance = null;
    private static final RootTelemetryConfiguration NO_TELEMETRY_CONFIG = new RootTelemetryConfiguration(0, false, false, 0, 0);

    private RootTelemetryConfigManager() {
    }

    public static synchronized RootTelemetryConfigManager getInstance() {
        RootTelemetryConfigManager rootTelemetryConfigManager;
        synchronized (RootTelemetryConfigManager.class) {
            if (instance == null) {
                instance = new RootTelemetryConfigManager();
            }
            rootTelemetryConfigManager = instance;
        }
        return rootTelemetryConfigManager;
    }

    public RootTelemetryConfiguration getConfig() {
        return this.config;
    }

    public synchronized void updateConfig(RootTelemetryConfiguration rootTelemetryConfiguration) {
        if (rootTelemetryConfiguration == null) {
            this.config = NO_TELEMETRY_CONFIG;
        } else {
            RootTelemetryConfiguration rootTelemetryConfiguration2 = this.config;
            if (rootTelemetryConfiguration2 == null || rootTelemetryConfiguration2.getVersion() < rootTelemetryConfiguration.getVersion()) {
                this.config = rootTelemetryConfiguration;
            }
        }
    }
}
