package com.google.android.gms.auth;

import com.google.android.gms.common.Feature;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Features {
    public static final Feature ACCOUNT_CAPABILITY_API;
    public static final Feature ACCOUNT_DATA_SERVICE;
    public static final Feature ACCOUNT_DATA_SERVICE_LEGACY;
    public static final Feature ACCOUNT_DATA_SERVICE_TOKEN;
    public static final Feature ACCOUNT_DATA_SERVICE_VISIBILITY;
    public static final Feature[] ALL_FEATURES;
    public static final Feature CONFIG_SYNC;
    public static final Feature DEVICE_ACCOUNT_API;
    public static final Feature GAIAID_PRIMARY_EMAIL_API;
    public static final Feature GOOGLE_AUTH_SERVICE_ACCOUNTS;
    public static final Feature GOOGLE_AUTH_SERVICE_TOKEN;
    public static final Feature HUB_MODE_API;
    public static final Feature WORK_ACCOUNT_CLIENT_IS_WHITELISTED;

    static {
        Feature feature = new Feature("account_capability_api", 1L);
        ACCOUNT_CAPABILITY_API = feature;
        Feature feature2 = new Feature("account_data_service", 6L);
        ACCOUNT_DATA_SERVICE = feature2;
        Feature feature3 = new Feature("account_data_service_legacy", 1L);
        ACCOUNT_DATA_SERVICE_LEGACY = feature3;
        Feature feature4 = new Feature("account_data_service_token", 7L);
        ACCOUNT_DATA_SERVICE_TOKEN = feature4;
        Feature feature5 = new Feature("account_data_service_visibility", 1L);
        ACCOUNT_DATA_SERVICE_VISIBILITY = feature5;
        Feature feature6 = new Feature("config_sync", 1L);
        CONFIG_SYNC = feature6;
        Feature feature7 = new Feature("device_account_api", 1L);
        DEVICE_ACCOUNT_API = feature7;
        Feature feature8 = new Feature("gaiaid_primary_email_api", 1L);
        GAIAID_PRIMARY_EMAIL_API = feature8;
        Feature feature9 = new Feature("google_auth_service_accounts", 2L);
        GOOGLE_AUTH_SERVICE_ACCOUNTS = feature9;
        Feature feature10 = new Feature("google_auth_service_token", 3L);
        GOOGLE_AUTH_SERVICE_TOKEN = feature10;
        Feature feature11 = new Feature("hub_mode_api", 1L);
        HUB_MODE_API = feature11;
        Feature feature12 = new Feature("work_account_client_is_whitelisted", 1L);
        WORK_ACCOUNT_CLIENT_IS_WHITELISTED = feature12;
        ALL_FEATURES = new Feature[]{feature, feature2, feature3, feature4, feature5, feature6, feature7, feature8, feature9, feature10, feature11, feature12};
    }
}
