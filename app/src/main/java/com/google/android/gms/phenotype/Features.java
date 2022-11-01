package com.google.android.gms.phenotype;

import com.google.android.gms.common.Feature;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Features {
    public static final Feature[] ALL_FEATURES;
    public static final Feature GET_EXPERIMENT_TOKENS_API;
    public static final Feature GET_SERVING_VERSION_API;
    public static final Feature SET_DIMENSIONS_API;
    public static final Feature SET_RUNTIME_PROPERTIES_API;
    public static final Feature SYNC_AFTER_API;
    public static final Feature SYNC_AFTER_FOR_APPLICATION_API;

    static {
        Feature feature = new Feature("set_dimensions_api", 1L);
        SET_DIMENSIONS_API = feature;
        Feature feature2 = new Feature("get_serving_version_api", 1L);
        GET_SERVING_VERSION_API = feature2;
        Feature feature3 = new Feature("get_experiment_tokens_api", 1L);
        GET_EXPERIMENT_TOKENS_API = feature3;
        Feature feature4 = new Feature("sync_after_api", 1L);
        SYNC_AFTER_API = feature4;
        Feature feature5 = new Feature("sync_after_for_application_api", 1L);
        SYNC_AFTER_FOR_APPLICATION_API = feature5;
        Feature feature6 = new Feature("set_runtime_properties_api", 1L);
        SET_RUNTIME_PROPERTIES_API = feature6;
        ALL_FEATURES = new Feature[]{feature, feature2, feature3, feature4, feature5, feature6};
    }
}
