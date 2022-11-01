package com.google.common.flogger.backend.android;

import com.google.common.flogger.backend.LoggerBackend;
import com.google.common.flogger.backend.android.SimpleAndroidLoggerBackend;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AlwaysLogBackend$Factory implements AndroidBackendFactory {
    private final boolean alwaysTruncate;
    private final String prefix;
    private final boolean prependLogSite;

    public AlwaysLogBackend$Factory() {
        this("", true, false);
    }

    @Override // com.google.common.flogger.backend.android.AndroidBackendFactory
    public LoggerBackend create(String str) {
        return new SimpleAndroidLoggerBackend.LogSiteBasedBackend(this.prefix, str, this.alwaysTruncate, this.prependLogSite, true, true);
    }

    private AlwaysLogBackend$Factory(String str, boolean z, boolean z2) {
        this.prefix = str;
        this.alwaysTruncate = z;
        this.prependLogSite = z2;
    }
}
