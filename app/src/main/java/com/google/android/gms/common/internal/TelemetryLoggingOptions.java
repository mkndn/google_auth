package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.Api;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TelemetryLoggingOptions implements Api.ApiOptions {
    public static final TelemetryLoggingOptions DEFAULT_OPTIONS = builder().build();
    private final String api;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder {
        private String api;

        private Builder() {
        }

        public TelemetryLoggingOptions build() {
            return new TelemetryLoggingOptions(this.api);
        }
    }

    private TelemetryLoggingOptions(String str) {
        this.api = str;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TelemetryLoggingOptions) {
            return Objects.equal(this.api, ((TelemetryLoggingOptions) obj).api);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.api);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        String str = this.api;
        if (str != null) {
            bundle.putString("api", str);
        }
        return bundle;
    }
}
