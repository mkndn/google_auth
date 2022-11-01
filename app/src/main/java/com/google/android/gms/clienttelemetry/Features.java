package com.google.android.gms.clienttelemetry;

import com.google.android.gms.common.Feature;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Features {
    public static final Feature[] ALL_FEATURES;
    public static final Feature CLIENT_TELEMETRY;

    static {
        Feature feature = new Feature("CLIENT_TELEMETRY", 1L);
        CLIENT_TELEMETRY = feature;
        ALL_FEATURES = new Feature[]{feature};
    }
}
