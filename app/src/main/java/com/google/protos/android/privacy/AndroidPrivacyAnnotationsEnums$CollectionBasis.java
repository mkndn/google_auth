package com.google.protos.android.privacy;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum AndroidPrivacyAnnotationsEnums$CollectionBasis implements Internal.EnumLite {
    CB_NONE(0),
    CB_RESERVED_FOR_TESTING_IN_PRODUCT_CONTROL(12),
    CB_GOOGLE_TOS_AND_PP(1),
    CB_CHECKBOX(2),
    CB_SUPPLEMENTAL_WEB_AND_APP_ACTIVITY_DEVICE_LEVEL(3),
    CB_PLAY_TOS(4),
    CB_GLOBAL_LOCATION(5),
    CB_LOCATION_REPORTING_DEVICE_LEVEL(7),
    CB_LOCATION_ACCURACY(8),
    CB_EARTHQUAKE_ALERTS(18),
    CB_WIFI_SCANNING(9),
    CB_GLOBAL_WIFI(10),
    CB_BACKUP_AND_RESTORE(11),
    CB_BACKUP_TO_GOOGLE(25),
    CB_WEAR_CLOUD_SYNC(13),
    CB_FIT_APP_USAGE_AND_DIAGNOSTICS(14),
    CB_GBOARD_USER_METRICS_SETTINGS(15),
    CB_DEVICE_CONTACTS_INFO(16),
    CB_FI_TOS(17),
    CB_FI_NETWORK_DIAGNOSTICS(19),
    CB_FIND_MY_DEVICE(20),
    CB_PAY_TOS(21),
    CB_WEB_AND_APP_ACTIVITY(22),
    CB_GLOBAL_BLUETOOTH(23),
    CB_BLUETOOTH_SCANNING(24),
    CB_UNICORN_SUPERVISION(26),
    CB_LOCATION_SHARING(27),
    CB_GMM_UGC_PUBLIC_POST(28),
    CB_LIQUID_BLUE_FEEDBACK(29),
    CB_GMM_UGC_CROWDSOURCE_DATA(36),
    CB_GMM_UGC_PUBLIC_RESPONSE(37),
    CB_PANELS_TOS(31),
    CB_DEVICE_APPS(30),
    CB_WINGDELIVERY_TOS(34),
    CB_OPENSKY_TOS(33),
    CB_STACK_COPY_TO_DRIVE(32),
    CB_PLAY_CONSOLE_TOS(35),
    CB_CHROME_IMAGE_DESCRIPTIONS(38),
    CB_CHROME_TOS(46),
    CB_NAVLOGS_NOTICE(39),
    CB_CARE_EULA_AND_PP(40),
    CB_MESSAGES_HADES_ON_DEVICE_ABUSE_DETECTION(41),
    CB_TACHYON_PHONE_NUMBER_IDENTITY(42),
    CB_NOW_PLAYING_CLOUD_SEARCH(43),
    CB_NOW_PLAYING(44),
    CB_CHROME_SYNC(45),
    CB_PAY_PHONE_NUMBER_DISCOVERABILITY(47),
    CB_PAY_GROUPS(48),
    CB_PAY_CONTACTS_SYNC(49),
    CB_CONSTELLATION_AUTO_VERIFICATION(50),
    CB_PHONE_NUMBER_GAIA_DISCOVERABILITY(51),
    CB_PHONE_NUMBER_REACHABILITY_GAIA(52),
    CB_PHONE_NUMBER_BETTER_ADS(53),
    CB_PHONE_NUMBER_ACCOUNT_SECURITY(54),
    CB_STREET_VIEW_VENDOR_CONTRACT(55),
    CB_MESSAGES_ASSISTANT_SUGGESTIONS(69),
    CB_MESSAGES_SMART_REPLY(68),
    CB_MESSAGES_SUGGESTED_ACTIONS(67),
    CB_MESSAGES_SUGGESTED_STICKERS(66),
    CB_MESSAGES_ALL_PREVIEW(64),
    CB_MESSAGES_WEB_LINK_PREVIEW(65),
    CB_MESSAGES_VERIFIED_SMS(63),
    CB_MESSAGES_HELP_MESSAGES(62),
    CB_MESSAGES_SAMSUNG_INTEGRATION(61),
    CB_MESSAGES_DEVICE_PAIRING(60),
    CB_MESSAGES_FI_SYNC(59),
    CB_MESSAGES_RCS_TOS(57),
    CB_MESSAGES_RCS_CONTROL(58),
    CB_MESSAGES_SUPERSORT(56);
    
    @Deprecated
    public static final int CB_BACKUP_AND_RESTORE_VALUE = 11;
    public static final int CB_BACKUP_TO_GOOGLE_VALUE = 25;
    public static final int CB_BLUETOOTH_SCANNING_VALUE = 24;
    public static final int CB_CARE_EULA_AND_PP_VALUE = 40;
    public static final int CB_CHECKBOX_VALUE = 2;
    public static final int CB_CHROME_IMAGE_DESCRIPTIONS_VALUE = 38;
    public static final int CB_CHROME_SYNC_VALUE = 45;
    public static final int CB_CHROME_TOS_VALUE = 46;
    public static final int CB_CONSTELLATION_AUTO_VERIFICATION_VALUE = 50;
    public static final int CB_DEVICE_APPS_VALUE = 30;
    public static final int CB_DEVICE_CONTACTS_INFO_VALUE = 16;
    public static final int CB_EARTHQUAKE_ALERTS_VALUE = 18;
    public static final int CB_FIND_MY_DEVICE_VALUE = 20;
    public static final int CB_FIT_APP_USAGE_AND_DIAGNOSTICS_VALUE = 14;
    public static final int CB_FI_NETWORK_DIAGNOSTICS_VALUE = 19;
    public static final int CB_FI_TOS_VALUE = 17;
    public static final int CB_GBOARD_USER_METRICS_SETTINGS_VALUE = 15;
    public static final int CB_GLOBAL_BLUETOOTH_VALUE = 23;
    public static final int CB_GLOBAL_LOCATION_VALUE = 5;
    public static final int CB_GLOBAL_WIFI_VALUE = 10;
    public static final int CB_GMM_UGC_CROWDSOURCE_DATA_VALUE = 36;
    public static final int CB_GMM_UGC_PUBLIC_POST_VALUE = 28;
    public static final int CB_GMM_UGC_PUBLIC_RESPONSE_VALUE = 37;
    public static final int CB_GOOGLE_TOS_AND_PP_VALUE = 1;
    public static final int CB_LIQUID_BLUE_FEEDBACK_VALUE = 29;
    public static final int CB_LOCATION_ACCURACY_VALUE = 8;
    public static final int CB_LOCATION_REPORTING_DEVICE_LEVEL_VALUE = 7;
    public static final int CB_LOCATION_SHARING_VALUE = 27;
    public static final int CB_MESSAGES_ALL_PREVIEW_VALUE = 64;
    public static final int CB_MESSAGES_ASSISTANT_SUGGESTIONS_VALUE = 69;
    public static final int CB_MESSAGES_DEVICE_PAIRING_VALUE = 60;
    public static final int CB_MESSAGES_FI_SYNC_VALUE = 59;
    public static final int CB_MESSAGES_HADES_ON_DEVICE_ABUSE_DETECTION_VALUE = 41;
    public static final int CB_MESSAGES_HELP_MESSAGES_VALUE = 62;
    public static final int CB_MESSAGES_RCS_CONTROL_VALUE = 58;
    public static final int CB_MESSAGES_RCS_TOS_VALUE = 57;
    public static final int CB_MESSAGES_SAMSUNG_INTEGRATION_VALUE = 61;
    public static final int CB_MESSAGES_SMART_REPLY_VALUE = 68;
    public static final int CB_MESSAGES_SUGGESTED_ACTIONS_VALUE = 67;
    public static final int CB_MESSAGES_SUGGESTED_STICKERS_VALUE = 66;
    public static final int CB_MESSAGES_SUPERSORT_VALUE = 56;
    public static final int CB_MESSAGES_VERIFIED_SMS_VALUE = 63;
    public static final int CB_MESSAGES_WEB_LINK_PREVIEW_VALUE = 65;
    public static final int CB_NAVLOGS_NOTICE_VALUE = 39;
    public static final int CB_NONE_VALUE = 0;
    public static final int CB_NOW_PLAYING_CLOUD_SEARCH_VALUE = 43;
    public static final int CB_NOW_PLAYING_VALUE = 44;
    public static final int CB_OPENSKY_TOS_VALUE = 33;
    public static final int CB_PANELS_TOS_VALUE = 31;
    public static final int CB_PAY_CONTACTS_SYNC_VALUE = 49;
    public static final int CB_PAY_GROUPS_VALUE = 48;
    public static final int CB_PAY_PHONE_NUMBER_DISCOVERABILITY_VALUE = 47;
    public static final int CB_PAY_TOS_VALUE = 21;
    public static final int CB_PHONE_NUMBER_ACCOUNT_SECURITY_VALUE = 54;
    public static final int CB_PHONE_NUMBER_BETTER_ADS_VALUE = 53;
    public static final int CB_PHONE_NUMBER_GAIA_DISCOVERABILITY_VALUE = 51;
    public static final int CB_PHONE_NUMBER_REACHABILITY_GAIA_VALUE = 52;
    public static final int CB_PLAY_CONSOLE_TOS_VALUE = 35;
    public static final int CB_PLAY_TOS_VALUE = 4;
    public static final int CB_RESERVED_FOR_TESTING_IN_PRODUCT_CONTROL_VALUE = 12;
    public static final int CB_STACK_COPY_TO_DRIVE_VALUE = 32;
    public static final int CB_STREET_VIEW_VENDOR_CONTRACT_VALUE = 55;
    public static final int CB_SUPPLEMENTAL_WEB_AND_APP_ACTIVITY_DEVICE_LEVEL_VALUE = 3;
    public static final int CB_TACHYON_PHONE_NUMBER_IDENTITY_VALUE = 42;
    public static final int CB_UNICORN_SUPERVISION_VALUE = 26;
    public static final int CB_WEAR_CLOUD_SYNC_VALUE = 13;
    public static final int CB_WEB_AND_APP_ACTIVITY_VALUE = 22;
    public static final int CB_WIFI_SCANNING_VALUE = 9;
    public static final int CB_WINGDELIVERY_TOS_VALUE = 34;
    private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasis.1
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public AndroidPrivacyAnnotationsEnums$CollectionBasis findValueByNumber(int i) {
            return AndroidPrivacyAnnotationsEnums$CollectionBasis.forNumber(i);
        }
    };
    private final int value;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class CollectionBasisVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new CollectionBasisVerifier();

        private CollectionBasisVerifier() {
        }

        @Override // com.google.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return AndroidPrivacyAnnotationsEnums$CollectionBasis.forNumber(i) != null;
        }
    }

    AndroidPrivacyAnnotationsEnums$CollectionBasis(int i) {
        this.value = i;
    }

    public static AndroidPrivacyAnnotationsEnums$CollectionBasis forNumber(int i) {
        switch (i) {
            case 0:
                return CB_NONE;
            case 1:
                return CB_GOOGLE_TOS_AND_PP;
            case 2:
                return CB_CHECKBOX;
            case 3:
                return CB_SUPPLEMENTAL_WEB_AND_APP_ACTIVITY_DEVICE_LEVEL;
            case 4:
                return CB_PLAY_TOS;
            case 5:
                return CB_GLOBAL_LOCATION;
            case 6:
            default:
                return null;
            case 7:
                return CB_LOCATION_REPORTING_DEVICE_LEVEL;
            case 8:
                return CB_LOCATION_ACCURACY;
            case 9:
                return CB_WIFI_SCANNING;
            case 10:
                return CB_GLOBAL_WIFI;
            case 11:
                return CB_BACKUP_AND_RESTORE;
            case 12:
                return CB_RESERVED_FOR_TESTING_IN_PRODUCT_CONTROL;
            case 13:
                return CB_WEAR_CLOUD_SYNC;
            case 14:
                return CB_FIT_APP_USAGE_AND_DIAGNOSTICS;
            case 15:
                return CB_GBOARD_USER_METRICS_SETTINGS;
            case 16:
                return CB_DEVICE_CONTACTS_INFO;
            case 17:
                return CB_FI_TOS;
            case 18:
                return CB_EARTHQUAKE_ALERTS;
            case 19:
                return CB_FI_NETWORK_DIAGNOSTICS;
            case 20:
                return CB_FIND_MY_DEVICE;
            case 21:
                return CB_PAY_TOS;
            case 22:
                return CB_WEB_AND_APP_ACTIVITY;
            case 23:
                return CB_GLOBAL_BLUETOOTH;
            case 24:
                return CB_BLUETOOTH_SCANNING;
            case 25:
                return CB_BACKUP_TO_GOOGLE;
            case 26:
                return CB_UNICORN_SUPERVISION;
            case 27:
                return CB_LOCATION_SHARING;
            case 28:
                return CB_GMM_UGC_PUBLIC_POST;
            case 29:
                return CB_LIQUID_BLUE_FEEDBACK;
            case 30:
                return CB_DEVICE_APPS;
            case 31:
                return CB_PANELS_TOS;
            case 32:
                return CB_STACK_COPY_TO_DRIVE;
            case 33:
                return CB_OPENSKY_TOS;
            case 34:
                return CB_WINGDELIVERY_TOS;
            case 35:
                return CB_PLAY_CONSOLE_TOS;
            case 36:
                return CB_GMM_UGC_CROWDSOURCE_DATA;
            case 37:
                return CB_GMM_UGC_PUBLIC_RESPONSE;
            case 38:
                return CB_CHROME_IMAGE_DESCRIPTIONS;
            case 39:
                return CB_NAVLOGS_NOTICE;
            case 40:
                return CB_CARE_EULA_AND_PP;
            case 41:
                return CB_MESSAGES_HADES_ON_DEVICE_ABUSE_DETECTION;
            case 42:
                return CB_TACHYON_PHONE_NUMBER_IDENTITY;
            case 43:
                return CB_NOW_PLAYING_CLOUD_SEARCH;
            case 44:
                return CB_NOW_PLAYING;
            case 45:
                return CB_CHROME_SYNC;
            case 46:
                return CB_CHROME_TOS;
            case 47:
                return CB_PAY_PHONE_NUMBER_DISCOVERABILITY;
            case 48:
                return CB_PAY_GROUPS;
            case 49:
                return CB_PAY_CONTACTS_SYNC;
            case 50:
                return CB_CONSTELLATION_AUTO_VERIFICATION;
            case 51:
                return CB_PHONE_NUMBER_GAIA_DISCOVERABILITY;
            case 52:
                return CB_PHONE_NUMBER_REACHABILITY_GAIA;
            case 53:
                return CB_PHONE_NUMBER_BETTER_ADS;
            case 54:
                return CB_PHONE_NUMBER_ACCOUNT_SECURITY;
            case 55:
                return CB_STREET_VIEW_VENDOR_CONTRACT;
            case 56:
                return CB_MESSAGES_SUPERSORT;
            case 57:
                return CB_MESSAGES_RCS_TOS;
            case 58:
                return CB_MESSAGES_RCS_CONTROL;
            case 59:
                return CB_MESSAGES_FI_SYNC;
            case 60:
                return CB_MESSAGES_DEVICE_PAIRING;
            case 61:
                return CB_MESSAGES_SAMSUNG_INTEGRATION;
            case 62:
                return CB_MESSAGES_HELP_MESSAGES;
            case 63:
                return CB_MESSAGES_VERIFIED_SMS;
            case 64:
                return CB_MESSAGES_ALL_PREVIEW;
            case 65:
                return CB_MESSAGES_WEB_LINK_PREVIEW;
            case 66:
                return CB_MESSAGES_SUGGESTED_STICKERS;
            case 67:
                return CB_MESSAGES_SUGGESTED_ACTIONS;
            case 68:
                return CB_MESSAGES_SMART_REPLY;
            case 69:
                return CB_MESSAGES_ASSISTANT_SUGGESTIONS;
        }
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return CollectionBasisVerifier.INSTANCE;
    }

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" number=").append(getNumber());
        return sb.append(" name=").append(name()).append('>').toString();
    }
}
