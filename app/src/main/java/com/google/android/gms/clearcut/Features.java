package com.google.android.gms.clearcut;

import com.google.android.gms.common.Feature;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Features {
    public static final Feature[] ALL_FEATURES;
    public static final Feature LOG_ERROR;

    static {
        Feature feature = new Feature("log_error", 1L);
        LOG_ERROR = feature;
        ALL_FEATURES = new Feature[]{feature};
    }
}
