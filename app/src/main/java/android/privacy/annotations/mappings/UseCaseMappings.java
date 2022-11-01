package android.privacy.annotations.mappings;

import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasis;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasisSpec;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;

/* compiled from: PG */
/* loaded from: classes.dex */
public class UseCaseMappings {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: android.privacy.annotations.mappings.UseCaseMappings$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase;

        static {
            int[] iArr = new int[AndroidPrivacyAnnotationsEnums$CollectionUseCase.values().length];
            $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase = iArr;
            try {
                iArr[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DELEGATED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_NEVER_COLLECT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PROTO_METADATA.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_RESERVED_FOR_TESTING_IN_PRODUCT_CONTROL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_INTERNAL_DESCRIPTION.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_1P_APP_PROVISION_OF_SERVICE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_3P_APP_PROVISION_OF_SERVICE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SERVICE_OR_API_FUNCTIONAL_DEBUGGING.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SERVICE_OR_API_PRODUCT_IMPROVEMENT.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SERVICE_OR_API_PRODUCT_DEVELOPMENT.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SERVICE_OR_API_MEASURING_USER_ENGAGEMENT.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PRIMES_APP_HEALTH.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CHIME_FUNCTIONAL_DEBUGGING.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CHIME_PRODUCT_IMPROVEMENT.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CHIME_MEASURING_USER_ENGAGEMENT.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_1P_APP_FUNCTIONAL_DEBUGGING.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_1P_APP_PRODUCT_IMPROVEMENT.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_1P_APP_PRODUCT_DEVELOPMENT.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_1P_APP_MEASURING_USER_ENGAGEMENT.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNBRANDED_1P_APP_FUNCTIONAL_DEBUGGING.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNBRANDED_1P_APP_PRODUCT_IMPROVEMENT.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNBRANDED_1P_APP_PRODUCT_DEVELOPMENT.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNBRANDED_1P_APP_MEASURING_USER_ENGAGEMENT.ordinal()] = 24;
            } catch (NoSuchFieldError e24) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLATFORM_OR_FEATURE_FUNCTIONAL_DEBUGGING.ordinal()] = 25;
            } catch (NoSuchFieldError e25) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLATFORM_OR_FEATURE_PRODUCT_IMPROVEMENT.ordinal()] = 26;
            } catch (NoSuchFieldError e26) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLATFORM_OR_FEATURE_PRODUCT_DEVELOPMENT.ordinal()] = 27;
            } catch (NoSuchFieldError e27) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLATFORM_OR_FEATURE_MEASURING_USER_ENGAGEMENT.ordinal()] = 28;
            } catch (NoSuchFieldError e28) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CRITICAL_FLEET_MANAGEMENT.ordinal()] = 29;
            } catch (NoSuchFieldError e29) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_EXPERIMENT_TARGETING.ordinal()] = 30;
            } catch (NoSuchFieldError e30) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_REFINING_EXPERIMENTS.ordinal()] = 31;
            } catch (NoSuchFieldError e31) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DEVICE_FINGERPRINT.ordinal()] = 32;
            } catch (NoSuchFieldError e32) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DEVICE_INTEGRITY.ordinal()] = 33;
            } catch (NoSuchFieldError e33) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLATFORM_INTEGRITY.ordinal()] = 34;
            } catch (NoSuchFieldError e34) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_APP_INTEGRITY.ordinal()] = 35;
            } catch (NoSuchFieldError e35) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ACCOUNT_INTEGRITY.ordinal()] = 36;
            } catch (NoSuchFieldError e36) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FRAUD_SPAM_ABUSE_PREVENTION.ordinal()] = 37;
            } catch (NoSuchFieldError e37) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DROIDGUARD_VERDICT_INPUT.ordinal()] = 38;
            } catch (NoSuchFieldError e38) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLAY_MALWARE_DETECTION.ordinal()] = 39;
            } catch (NoSuchFieldError e39) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SIDELOAD_MALWARE_DETECTION.ordinal()] = 40;
            } catch (NoSuchFieldError e40) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SERVICE_ABUSE_PREVENTION.ordinal()] = 41;
            } catch (NoSuchFieldError e41) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CRITICAL_FUNCTIONAL_FLEET_ISSUES.ordinal()] = 42;
            } catch (NoSuchFieldError e42) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTEXTUALIZATION.ordinal()] = 43;
            } catch (NoSuchFieldError e43) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTEXTUALIZATION_RECENT_ACTIVITY.ordinal()] = 44;
            } catch (NoSuchFieldError e44) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTEXTUALIZATION_DEVICE_CAPABILITIES.ordinal()] = 45;
            } catch (NoSuchFieldError e45) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTEXTUALIZATION_DEVICE_STATE.ordinal()] = 46;
            } catch (NoSuchFieldError e46) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTEXTUALIZATION_LANGUAGE_OR_LOCALE.ordinal()] = 47;
            } catch (NoSuchFieldError e47) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTEXTUALIZATION_COUNTRY.ordinal()] = 48;
            } catch (NoSuchFieldError e48) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTEXTUALIZATION_USER_DATA.ordinal()] = 49;
            } catch (NoSuchFieldError e49) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTEXTUALIZATION_NO_USER_DATA.ordinal()] = 50;
            } catch (NoSuchFieldError e50) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ANDROID_ECOSYSTEM_HEALTH.ordinal()] = 51;
            } catch (NoSuchFieldError e51) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_1P_APP_FUNCTIONAL_DEBUGGING.ordinal()] = 52;
            } catch (NoSuchFieldError e52) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_1P_APP_PRODUCT_IMPROVEMENT.ordinal()] = 53;
            } catch (NoSuchFieldError e53) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_1P_APP_PRODUCT_DEVELOPMENT.ordinal()] = 54;
            } catch (NoSuchFieldError e54) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_1P_APP_MEASURING_USER_ENGAGEMENT.ordinal()] = 55;
            } catch (NoSuchFieldError e55) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SDK_FUNCTIONAL_DEBUGGING.ordinal()] = 56;
            } catch (NoSuchFieldError e56) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SDK_PRODUCT_IMPROVEMENT.ordinal()] = 57;
            } catch (NoSuchFieldError e57) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SDK_PRODUCT_DEVELOPMENT.ordinal()] = 58;
            } catch (NoSuchFieldError e58) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SDK_MEASURING_USER_ENGAGEMENT.ordinal()] = 59;
            } catch (NoSuchFieldError e59) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FIT_FUNCTIONAL_DEBUGGING.ordinal()] = 60;
            } catch (NoSuchFieldError e60) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FIT_PRODUCT_IMPROVEMENT.ordinal()] = 61;
            } catch (NoSuchFieldError e61) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FIT_PRODUCT_DEVELOPMENT.ordinal()] = 62;
            } catch (NoSuchFieldError e62) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FIT_MEASURING_USER_ENGAGEMENT.ordinal()] = 63;
            } catch (NoSuchFieldError e63) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GBOARD_FUNCTIONAL_DEBUGGING.ordinal()] = 64;
            } catch (NoSuchFieldError e64) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GBOARD_PRODUCT_IMPROVEMENT.ordinal()] = 65;
            } catch (NoSuchFieldError e65) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GBOARD_PRODUCT_DEVELOPMENT.ordinal()] = 66;
            } catch (NoSuchFieldError e66) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GBOARD_MEASURING_USER_ENGAGEMENT.ordinal()] = 67;
            } catch (NoSuchFieldError e67) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLATFORM_OR_FEATURE_PRODUCT_IMPROVEMENT_WW.ordinal()] = 68;
            } catch (NoSuchFieldError e68) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLATFORM_OR_FEATURE_FUNCTIONAL_DEBUGGING_WW.ordinal()] = 69;
            } catch (NoSuchFieldError e69) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLATFORM_OR_FEATURE_PRODUCT_DEVELOPMENT_WW.ordinal()] = 70;
            } catch (NoSuchFieldError e70) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLATFORM_OR_FEATURE_MEASURING_USER_ENGAGEMENT_WW.ordinal()] = 71;
            } catch (NoSuchFieldError e71) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SERVICE_OR_API_FUNCTIONAL_DEBUGGING_WW.ordinal()] = 72;
            } catch (NoSuchFieldError e72) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SERVICE_OR_API_PRODUCT_IMPROVEMENT_WW.ordinal()] = 73;
            } catch (NoSuchFieldError e73) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SERVICE_OR_API_PRODUCT_DEVELOPMENT_WW.ordinal()] = 74;
            } catch (NoSuchFieldError e74) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SERVICE_OR_API_MEASURING_USER_ENGAGEMENT_WW.ordinal()] = 75;
            } catch (NoSuchFieldError e75) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNBRANDED_1P_APP_FUNCTIONAL_DEBUGGING_WW.ordinal()] = 76;
            } catch (NoSuchFieldError e76) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNBRANDED_1P_APP_PRODUCT_IMPROVEMENT_WW.ordinal()] = 77;
            } catch (NoSuchFieldError e77) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNBRANDED_1P_APP_PRODUCT_DEVELOPMENT_WW.ordinal()] = 78;
            } catch (NoSuchFieldError e78) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNBRANDED_1P_APP_MEASURING_USER_ENGAGEMENT_WW.ordinal()] = 79;
            } catch (NoSuchFieldError e79) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_1P_APP_FUNCTIONAL_DEBUGGING_WW.ordinal()] = 80;
            } catch (NoSuchFieldError e80) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_1P_APP_PRODUCT_IMPROVEMENT_WW.ordinal()] = 81;
            } catch (NoSuchFieldError e81) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_1P_APP_PRODUCT_DEVELOPMENT_WW.ordinal()] = 82;
            } catch (NoSuchFieldError e82) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_1P_APP_MEASURING_USER_ENGAGEMENT_WW.ordinal()] = 83;
            } catch (NoSuchFieldError e83) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_APP_USAGE_FOREGROUND_USER_BEHAVIOR_WW.ordinal()] = 84;
            } catch (NoSuchFieldError e84) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_APP_USAGE_SYSTEM_HEALTH_WW.ordinal()] = 85;
            } catch (NoSuchFieldError e85) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_APP_USAGE_PERSONALIZATION_WW.ordinal()] = 86;
            } catch (NoSuchFieldError e86) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SYSTEM_HEALTH_INTERACTION_WITH_PRODUCT_WW.ordinal()] = 87;
            } catch (NoSuchFieldError e87) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SYSTEM_HEALTH_FUNCTIONAL_DEBUGGING_WW.ordinal()] = 88;
            } catch (NoSuchFieldError e88) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLATFORM_MARKET_STATISTICS_WW.ordinal()] = 89;
            } catch (NoSuchFieldError e89) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTEXTUALIZATION_NO_USER_DATA_WW.ordinal()] = 90;
            } catch (NoSuchFieldError e90) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CROSS_PRODUCT_PERSONALIZATION_FOOTPRINTS.ordinal()] = 91;
            } catch (NoSuchFieldError e91) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SEARCH_HISTORY.ordinal()] = 92;
            } catch (NoSuchFieldError e92) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNBRANDED_CROSS_PRODUCT_PERSONALIZATION_FOOTPRINTS.ordinal()] = 93;
            } catch (NoSuchFieldError e93) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_BROWSING_HISTORY.ordinal()] = 94;
            } catch (NoSuchFieldError e94) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GPP_SIDELOADED_UNSAFE_APP_DETECTION.ordinal()] = 95;
            } catch (NoSuchFieldError e95) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GPP_UNSAFE_APP_DETECTION.ordinal()] = 96;
            } catch (NoSuchFieldError e96) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GPP_UPLOAD_UNSAFE_APP.ordinal()] = 97;
            } catch (NoSuchFieldError e97) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_3P_APP_DEVICE_INTEGRITY.ordinal()] = 98;
            } catch (NoSuchFieldError e98) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_VERIFY_URL.ordinal()] = 99;
            } catch (NoSuchFieldError e99) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FMD_RING.ordinal()] = 100;
            } catch (NoSuchFieldError e100) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FMD_LOCATE.ordinal()] = 101;
            } catch (NoSuchFieldError e101) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FMD_LOCK.ordinal()] = 102;
            } catch (NoSuchFieldError e102) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FMD_UNPAIR.ordinal()] = 103;
            } catch (NoSuchFieldError e103) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FMD_LOCATE_ACCESSORY.ordinal()] = 104;
            } catch (NoSuchFieldError e104) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FMD_IDENTIFY_DEVICE_STATE_AND_CAPABILITY.ordinal()] = 105;
            } catch (NoSuchFieldError e105) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ENX_OPT_OUT_DEIDENTIFIED_TELEMETRY.ordinal()] = 106;
            } catch (NoSuchFieldError e106) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_1P_HW_FUNCTIONAL_DEBUGGING_WW.ordinal()] = 107;
            } catch (NoSuchFieldError e107) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_1P_HW_PRODUCT_IMPROVEMENT_WW.ordinal()] = 108;
            } catch (NoSuchFieldError e108) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_1P_HW_PRODUCT_DEVELOPMENT_WW.ordinal()] = 109;
            } catch (NoSuchFieldError e109) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_1P_HW_MEASURING_USER_ENGAGEMENT_WW.ordinal()] = 110;
            } catch (NoSuchFieldError e110) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FOREGROUND_LOCATION.ordinal()] = 111;
            } catch (NoSuchFieldError e111) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_IP_LOCATION.ordinal()] = 112;
            } catch (NoSuchFieldError e112) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_LOCATION_HISTORY.ordinal()] = 113;
            } catch (NoSuchFieldError e113) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_LOCATION_HISTORY_USER_EDIT.ordinal()] = 114;
            } catch (NoSuchFieldError e114) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_LOCATION_ACCURACY.ordinal()] = 115;
            } catch (NoSuchFieldError e115) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_LOCATION_ACCURACY_WIFI.ordinal()] = 116;
            } catch (NoSuchFieldError e116) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_LOCATION_HISTORY_CONSENT_CHANGE.ordinal()] = 117;
            } catch (NoSuchFieldError e117) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_EARTHQUAKE_ALERTING.ordinal()] = 118;
            } catch (NoSuchFieldError e118) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_EARTHQUAKE_DETECTION.ordinal()] = 119;
            } catch (NoSuchFieldError e119) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FIT_APP_OR_API_FUNCTIONAL_DEBUGGING.ordinal()] = 120;
            } catch (NoSuchFieldError e120) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FIT_APP_OR_API_PRODUCT_IMPROVEMENT.ordinal()] = 121;
            } catch (NoSuchFieldError e121) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FIT_APP_OR_API_PRODUCT_DEVELOPMENT.ordinal()] = 122;
            } catch (NoSuchFieldError e122) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FIT_APP_OR_API_MEASURING_USER_ENGAGEMENT.ordinal()] = 123;
            } catch (NoSuchFieldError e123) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FCM_FUNCTIONAL_DEBUGGING.ordinal()] = 124;
            } catch (NoSuchFieldError e124) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_1P_HW_DEVICE_MANAGEMENT.ordinal()] = 125;
            } catch (NoSuchFieldError e125) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_BACKUP_USER_DATA.ordinal()] = 126;
            } catch (NoSuchFieldError e126) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_RESTORE_USER_DATA.ordinal()] = 127;
            } catch (NoSuchFieldError e127) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_BACKUP_MANAGEMENT.ordinal()] = 128;
            } catch (NoSuchFieldError e128) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_WEAR_CLOUD_SYNC.ordinal()] = 129;
            } catch (NoSuchFieldError e129) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTACTS_ACCOUNT_TYPE_LOGGING.ordinal()] = 130;
            } catch (NoSuchFieldError e130) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_IN_PRODUCT_PERSONALIZATION.ordinal()] = 131;
            } catch (NoSuchFieldError e131) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_NEARBY_MESSAGES.ordinal()] = 132;
            } catch (NoSuchFieldError e132) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FAST_PAIR.ordinal()] = 133;
            } catch (NoSuchFieldError e133) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_NEARBY_SHARING.ordinal()] = 134;
            } catch (NoSuchFieldError e134) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_USER_AUTHENTICATION.ordinal()] = 135;
            } catch (NoSuchFieldError e135) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GOOGLE_CONTACTS_SYNC.ordinal()] = 136;
            } catch (NoSuchFieldError e136) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PEOPLE_DETAILS_SYNC.ordinal()] = 137;
            } catch (NoSuchFieldError e137) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DEVICE_CONTACT_INFO_COLLECTION.ordinal()] = 138;
            } catch (NoSuchFieldError e138) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_WIFI_NETWORK_SCORING.ordinal()] = 139;
            } catch (NoSuchFieldError e139) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_POPULATED_SERVER_SIDE.ordinal()] = 140;
            } catch (NoSuchFieldError e140) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FI_FUNCTIONAL_DEBUGGING.ordinal()] = 141;
            } catch (NoSuchFieldError e141) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FI_PRODUCT_IMPROVEMENT.ordinal()] = 142;
            } catch (NoSuchFieldError e142) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FI_MEASURING_USER_ENGAGEMENT.ordinal()] = 143;
            } catch (NoSuchFieldError e143) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FI_CELL_TOWER_HISTORY.ordinal()] = 144;
            } catch (NoSuchFieldError e144) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FI_PROVISION_OF_SERVICE.ordinal()] = 145;
            } catch (NoSuchFieldError e145) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ADS_TARGETING.ordinal()] = 146;
            } catch (NoSuchFieldError e146) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ADS_DELIVERY.ordinal()] = 147;
            } catch (NoSuchFieldError e147) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ADS_GENERAL_TARGETING.ordinal()] = 148;
            } catch (NoSuchFieldError e148) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ADS_PERSONALIZATION.ordinal()] = 149;
            } catch (NoSuchFieldError e149) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ADS_MEASUREMENT.ordinal()] = 150;
            } catch (NoSuchFieldError e150) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ADS_EXTERNAL_INTEGRATION.ordinal()] = 151;
            } catch (NoSuchFieldError e151) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ABUSE_CONTENT_CLASSIFICATION_VERDICTS.ordinal()] = 152;
            } catch (NoSuchFieldError e152) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_BRELLA_FUNCTIONAL_DEBUGGING.ordinal()] = 153;
            } catch (NoSuchFieldError e153) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_BRELLA_FEDERATED_COMPUTE.ordinal()] = 154;
            } catch (NoSuchFieldError e154) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PAY_FUNCTIONAL_DEBUGGING.ordinal()] = 155;
            } catch (NoSuchFieldError e155) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PAY_PRODUCT_IMPROVEMENT.ordinal()] = 156;
            } catch (NoSuchFieldError e156) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PAY_MEASURING_USER_ENGAGEMENT.ordinal()] = 157;
            } catch (NoSuchFieldError e157) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PAY_PROVISION_OF_SERVICE.ordinal()] = 158;
            } catch (NoSuchFieldError e158) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PAY_DEVICE_FINGERPRINT.ordinal()] = 159;
            } catch (NoSuchFieldError e159) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNICORN_SETUP.ordinal()] = 160;
            } catch (NoSuchFieldError e160) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNICORN_MANAGEMENT.ordinal()] = 161;
            } catch (NoSuchFieldError e161) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNICORN_ACTIVITY_SUPERVISION.ordinal()] = 162;
            } catch (NoSuchFieldError e162) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FAMILY_ADMIN.ordinal()] = 163;
            } catch (NoSuchFieldError e163) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FAMILY_1P_INTEGRATION.ordinal()] = 164;
            } catch (NoSuchFieldError e164) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DIGITAL_WELLBEING_FUNCTIONAL_DEBUGGING.ordinal()] = 165;
            } catch (NoSuchFieldError e165) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DIGITAL_WELLBEING_MEASURING_USER_ENGAGEMENT.ordinal()] = 166;
            } catch (NoSuchFieldError e166) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DIGITAL_WELLBEING_PRODUCT_IMPROVEMENT.ordinal()] = 167;
            } catch (NoSuchFieldError e167) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MARKETING_ENGAGEMENT_GROWTH_COMMS.ordinal()] = 168;
            } catch (NoSuchFieldError e168) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MARKETING_ENGAGEMENT_GROWTH_FREQ_CAP.ordinal()] = 169;
            } catch (NoSuchFieldError e169) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_AUDIT_RECORD.ordinal()] = 170;
            } catch (NoSuchFieldError e170) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_LOCATION_SHARING.ordinal()] = 171;
            } catch (NoSuchFieldError e171) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMM_PUBLIC_PHOTO.ordinal()] = 172;
            } catch (NoSuchFieldError e172) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMM_PUBLIC_VIDEO.ordinal()] = 173;
            } catch (NoSuchFieldError e173) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMM_USER_INITIATED_FEEDBACK.ordinal()] = 174;
            } catch (NoSuchFieldError e174) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_LOCATION_HISTORY_BOTTOM_SHEET_CONSENT_TOGGLE.ordinal()] = 175;
            } catch (NoSuchFieldError e175) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_DPC_CLOUD_API_PROVISION_OF_SERVICE.ordinal()] = 176;
            } catch (NoSuchFieldError e176) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_DPC_CLOUD_API_CONFIGURE_APP_POLICIES.ordinal()] = 177;
            } catch (NoSuchFieldError e177) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_DPC_CLOUD_API_REPORT_DEVICE_INFO.ordinal()] = 178;
            } catch (NoSuchFieldError e178) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_DPC_CLOUD_API_SET_UP_DEVICE_MANAGEMENT.ordinal()] = 179;
            } catch (NoSuchFieldError e179) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_DPC_CLOUD_API_RUN_DEVICE_COMMANDS.ordinal()] = 180;
            } catch (NoSuchFieldError e180) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_DPC_CLOUD_API_FUNCTIONAL_DEBUGGING.ordinal()] = 181;
            } catch (NoSuchFieldError e181) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLAY_APP_PROVISION_OF_SERVICE.ordinal()] = 182;
            } catch (NoSuchFieldError e182) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTEXTUALIZATION_1P_INSTALLED_APPS.ordinal()] = 183;
            } catch (NoSuchFieldError e183) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTEXTUALIZATION_DEFAULT_HANDLER_APPS.ordinal()] = 184;
            } catch (NoSuchFieldError e184) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DEVICE_APPS.ordinal()] = 185;
            } catch (NoSuchFieldError e185) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PANELS_APP_PROVISION_OF_SERVICE.ordinal()] = 186;
            } catch (NoSuchFieldError e186) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PANELS_APP_FUNCTIONAL_DEBUGGING.ordinal()] = 187;
            } catch (NoSuchFieldError e187) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PANELS_APP_IN_APP_DATA.ordinal()] = 188;
            } catch (NoSuchFieldError e188) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FCM_MESSAGE_DELIVERY.ordinal()] = 189;
            } catch (NoSuchFieldError e189) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_STACK_COPY_TO_DRIVE.ordinal()] = 190;
            } catch (NoSuchFieldError e190) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PAY_FRAUD_SPAM_ABUSE_PREVENTION.ordinal()] = 191;
            } catch (NoSuchFieldError e191) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMM_OWNER_RESPONSE.ordinal()] = 192;
            } catch (NoSuchFieldError e192) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMM_PUBLIC_REVIEW.ordinal()] = 193;
            } catch (NoSuchFieldError e193) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMM_VOTE.ordinal()] = 194;
            } catch (NoSuchFieldError e194) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_OAUTH_GAIA_SIGNIN.ordinal()] = 195;
            } catch (NoSuchFieldError e195) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_OPENSKY_PROVISION_OF_SERVICE.ordinal()] = 196;
            } catch (NoSuchFieldError e196) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_OPENSKY_FUNCTIONAL_DEBUGGING.ordinal()] = 197;
            } catch (NoSuchFieldError e197) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_OPENSKY_PRODUCT_IMPROVEMENT.ordinal()] = 198;
            } catch (NoSuchFieldError e198) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_OPENSKY_MEASURING_USER_ENGAGEMENT.ordinal()] = 199;
            } catch (NoSuchFieldError e199) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_WINGDELIVERY_PROVISION_OF_SERVICE.ordinal()] = 200;
            } catch (NoSuchFieldError e200) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_WINGDELIVERY_FUNCTIONAL_DEBUGGING.ordinal()] = 201;
            } catch (NoSuchFieldError e201) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_WINGDELIVERY_PRODUCT_IMPROVEMENT.ordinal()] = 202;
            } catch (NoSuchFieldError e202) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_WINGDELIVERY_MEASURING_USER_ENGAGEMENT.ordinal()] = 203;
            } catch (NoSuchFieldError e203) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLAY_CONSOLE_PROVISION_OF_SERVICE.ordinal()] = 204;
            } catch (NoSuchFieldError e204) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLAY_CONSOLE_FUNCTIONAL_DEBUGGING.ordinal()] = 205;
            } catch (NoSuchFieldError e205) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLAY_CONSOLE_PRODUCT_IMPROVEMENT.ordinal()] = 206;
            } catch (NoSuchFieldError e206) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLAY_CONSOLE_MEASURING_USER_ENGAGEMENT.ordinal()] = 207;
            } catch (NoSuchFieldError e207) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_KEY_EXCHANGE.ordinal()] = 208;
            } catch (NoSuchFieldError e208) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DROIDGUARD_ATTESTATION.ordinal()] = 209;
            } catch (NoSuchFieldError e209) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_BINARY_TRANSPARENCY.ordinal()] = 210;
            } catch (NoSuchFieldError e210) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CHIME_NOTIFICATION_MANAGEMENT.ordinal()] = 211;
            } catch (NoSuchFieldError e211) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNBRANDED_TASKMATE_PROVISION_OF_SERVICE.ordinal()] = 212;
            } catch (NoSuchFieldError e212) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CHROME_IMAGE_DESCRIPTIONS.ordinal()] = 213;
            } catch (NoSuchFieldError e213) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CROWDSOURCE_PUBLIC_PHOTO.ordinal()] = 214;
            } catch (NoSuchFieldError e214) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CROWDSOURCE_CONTRIBUTED_AUDIO.ordinal()] = 215;
            } catch (NoSuchFieldError e215) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMM_UGC_PUBLIC_REPORT.ordinal()] = 216;
            } catch (NoSuchFieldError e216) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HADES_REQUEST_MODEL_AND_DATA_UPDATES.ordinal()] = 217;
            } catch (NoSuchFieldError e217) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HADES_USAGE_INFO.ordinal()] = 218;
            } catch (NoSuchFieldError e218) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ARES_CONTENT_ABUSE_REPORT.ordinal()] = 219;
            } catch (NoSuchFieldError e219) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ARES_TAKEDOWN_APPEALS.ordinal()] = 220;
            } catch (NoSuchFieldError e220) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FMD_ERASE_DEVICE.ordinal()] = 221;
            } catch (NoSuchFieldError e221) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_LOCATION_TIME_ZONE.ordinal()] = 222;
            } catch (NoSuchFieldError e222) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMM_NAVIGATION.ordinal()] = 223;
            } catch (NoSuchFieldError e223) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CARE_STUDIO_PROVISION_OF_SERVICE.ordinal()] = 224;
            } catch (NoSuchFieldError e224) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CARE_STUDIO_PRODUCT_IMPROVEMENT.ordinal()] = 225;
            } catch (NoSuchFieldError e225) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CARE_STUDIO_FUNCTIONAL_DEBUGGING.ordinal()] = 226;
            } catch (NoSuchFieldError e226) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CARE_STUDIO_MEASURING_USER_ENGAGEMENT.ordinal()] = 227;
            } catch (NoSuchFieldError e227) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMM_VIEW_PORT_LOGGING.ordinal()] = 228;
            } catch (NoSuchFieldError e228) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HADES_OPRF_BLINDED_USER_CONTENT.ordinal()] = 229;
            } catch (NoSuchFieldError e229) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_TACHYON_PHONE_NUMBER_IDENTITY.ordinal()] = 230;
            } catch (NoSuchFieldError e230) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MDI_INFINITE_DATA.ordinal()] = 231;
            } catch (NoSuchFieldError e231) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_NOW_PLAYING_CLOUD_SEARCH.ordinal()] = 232;
            } catch (NoSuchFieldError e232) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_NOW_PLAYING.ordinal()] = 233;
            } catch (NoSuchFieldError e233) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MEET_USER_ENGAGEMENT.ordinal()] = 234;
            } catch (NoSuchFieldError e234) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMS_CORE_CHROME_SYNC.ordinal()] = 235;
            } catch (NoSuchFieldError e235) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ODLH_NEWFIE_STORE_VISITS.ordinal()] = 236;
            } catch (NoSuchFieldError e236) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ODLH_WIFI_PLACE_VISITS.ordinal()] = 237;
            } catch (NoSuchFieldError e237) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ODLH_ACTIVITY_TRIPS.ordinal()] = 238;
            } catch (NoSuchFieldError e238) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ODLH_LIVE_BUSYNESS.ordinal()] = 239;
            } catch (NoSuchFieldError e239) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ODLH_HISTORICAL_BUSYNESS.ordinal()] = 240;
            } catch (NoSuchFieldError e240) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CHROME_AUTH.ordinal()] = 241;
            } catch (NoSuchFieldError e241) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CHROME_WEB_BROWSING.ordinal()] = 242;
            } catch (NoSuchFieldError e242) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_3P_PASSWORD_LEAK_CHECK.ordinal()] = 243;
            } catch (NoSuchFieldError e243) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PAY_PHONE_NUMBER_DISCOVERABILITY.ordinal()] = 244;
            } catch (NoSuchFieldError e244) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PAY_GROUPS.ordinal()] = 245;
            } catch (NoSuchFieldError e245) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PAY_CONTACTS_SYNC.ordinal()] = 246;
            } catch (NoSuchFieldError e246) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PHONE_NUMBER_ACCOUNT_SECURITY.ordinal()] = 247;
            } catch (NoSuchFieldError e247) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONSTELLATION_VERIFICATION.ordinal()] = 248;
            } catch (NoSuchFieldError e248) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PHONE_NUMBER_GAIA_REACHABILITY.ordinal()] = 249;
            } catch (NoSuchFieldError e249) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PHONE_NUMBER_GAIA_DISCOVERABILITY.ordinal()] = 250;
            } catch (NoSuchFieldError e250) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PHONE_NUMBER_BROAD_USE_CONSENT.ordinal()] = 251;
            } catch (NoSuchFieldError e251) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SEMANTIC_LOCATION.ordinal()] = 252;
            } catch (NoSuchFieldError e252) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GMM_UGC_PUBLIC_POST_Q_AND_A.ordinal()] = 253;
            } catch (NoSuchFieldError e253) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_STREET_VIEW_LOCATION_GPS.ordinal()] = 254;
            } catch (NoSuchFieldError e254) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_STREET_VIEW_IMAGERY.ordinal()] = 255;
            } catch (NoSuchFieldError e255) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_STREET_VIEW_HARDWARE_TELEMETRY_AND_PERFORMANCE.ordinal()] = 256;
            } catch (NoSuchFieldError e256) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GOOGLE_STREET_VIEW_MANAGEMENT.ordinal()] = 257;
            } catch (NoSuchFieldError e257) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CROWDSOURCE_GLIDE_TYPING.ordinal()] = 258;
            } catch (NoSuchFieldError e258) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CROWDSOURCE_CONTRIBUTED_TEXT_RESPONSE.ordinal()] = 259;
            } catch (NoSuchFieldError e259) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CROWDSOURCE_CONTRIBUTED_OPTION_RESPONSE.ordinal()] = 260;
            } catch (NoSuchFieldError e260) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_ODLH_REQUEST_DEDUPLICATION.ordinal()] = 261;
            } catch (NoSuchFieldError e261) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CHROME_SYNC_PASSWORD_CREDENTIAL_GROUPING.ordinal()] = 262;
            } catch (NoSuchFieldError e262) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_ASSISTANT_SUGGESTIONS_PRODUCT_IMPROVEMENT.ordinal()] = 263;
            } catch (NoSuchFieldError e263) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_ASSISTANT_SUGGESTIONS_USER_ENGAGEMENT.ordinal()] = 264;
            } catch (NoSuchFieldError e264) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_ASSISTANT_SUGGESTIONS_PROVISION_OF_SERVICE.ordinal()] = 265;
            } catch (NoSuchFieldError e265) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_SMART_REPLY_PRODUCT_IMPROVEMENT.ordinal()] = 266;
            } catch (NoSuchFieldError e266) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_SMART_REPLY_USER_ENGAGEMENT.ordinal()] = 267;
            } catch (NoSuchFieldError e267) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_SUGGESTED_ACTIONS_USER_ENGAGEMENT.ordinal()] = 268;
            } catch (NoSuchFieldError e268) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_SUGGESTED_ACTIONS_PRODUCT_IMPROVEMENT.ordinal()] = 269;
            } catch (NoSuchFieldError e269) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_SUGGESTED_STICKERS_USER_ENGAGEMENT.ordinal()] = 270;
            } catch (NoSuchFieldError e270) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_SUGGESTED_STICKERS_PRODUCT_IMPROVEMENT.ordinal()] = 271;
            } catch (NoSuchFieldError e271) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_LINK_SPAM_ABUSE_PREVENTION.ordinal()] = 272;
            } catch (NoSuchFieldError e272) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_LINK_PREVIEW_PROVISION_OF_SERVICE.ordinal()] = 273;
            } catch (NoSuchFieldError e273) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_ALL_PREVIEW_PROVISION_OF_SERVICE.ordinal()] = 274;
            } catch (NoSuchFieldError e274) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_PREVIEW_USER_ENGAGEMENT.ordinal()] = 275;
            } catch (NoSuchFieldError e275) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_PREVIEW_PRODUCT_IMPROVEMENT.ordinal()] = 276;
            } catch (NoSuchFieldError e276) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_VERIFIED_SMS_PROVISION_OF_SERVICE.ordinal()] = 277;
            } catch (NoSuchFieldError e277) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_VERIFIED_SMS_FUNCTIONAL_DEBUGGING.ordinal()] = 278;
            } catch (NoSuchFieldError e278) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_VERIFIED_SMS_PRODUCT_IMPROVEMENT.ordinal()] = 279;
            } catch (NoSuchFieldError e279) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_PRODUCT_IMPROVEMENT.ordinal()] = 280;
            } catch (NoSuchFieldError e280) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_USER_ENGAGEMENT.ordinal()] = 281;
            } catch (NoSuchFieldError e281) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_CMC_PROVISION_OF_SERVICE.ordinal()] = 282;
            } catch (NoSuchFieldError e282) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_CMC_USER_ENGAGEMENT.ordinal()] = 283;
            } catch (NoSuchFieldError e283) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_CMC_PRODUCT_IMPROVEMENT.ordinal()] = 284;
            } catch (NoSuchFieldError e284) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_DEVICE_PAIRING_PRODUCT_IMPROVEMENT.ordinal()] = 285;
            } catch (NoSuchFieldError e285) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_DEVICE_PAIRING_PROVISION_OF_SERVICE.ordinal()] = 286;
            } catch (NoSuchFieldError e286) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_FI_SYNC_PRODUCT_IMPROVEMENT.ordinal()] = 287;
            } catch (NoSuchFieldError e287) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_FI_SYNC_PROVISION_OF_SERVICE.ordinal()] = 288;
            } catch (NoSuchFieldError e288) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_RCS_PRODUCT_IMPROVEMENT.ordinal()] = 289;
            } catch (NoSuchFieldError e289) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_RCS_PROVISION_OF_SERVICE.ordinal()] = 290;
            } catch (NoSuchFieldError e290) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_RCS_USER_ENGAGEMENT.ordinal()] = 291;
            } catch (NoSuchFieldError e291) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_SUPERSORT_USER_ENGAGEMENT.ordinal()] = 292;
            } catch (NoSuchFieldError e292) {
            }
            try {
                $SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_MESSAGES_SUPERSORT_PRODUCT_IMPROVEMENT.ordinal()] = 293;
            } catch (NoSuchFieldError e293) {
            }
        }
    }

    public static AndroidPrivacyAnnotationsEnums$CollectionBasisSpec getCollectionBasisSpecs(AndroidPrivacyAnnotationsEnums$CollectionUseCase androidPrivacyAnnotationsEnums$CollectionUseCase) {
        return new UseCaseMappings().getCollectionBasisSpecsForUseCase(androidPrivacyAnnotationsEnums$CollectionUseCase);
    }

    public AndroidPrivacyAnnotationsEnums$CollectionBasisSpec getCollectionBasisSpecsForUseCase(AndroidPrivacyAnnotationsEnums$CollectionUseCase androidPrivacyAnnotationsEnums$CollectionUseCase) {
        switch (AnonymousClass1.$SwitchMap$com$google$protos$android$privacy$AndroidPrivacyAnnotationsEnums$CollectionUseCase[androidPrivacyAnnotationsEnums$CollectionUseCase.ordinal()]) {
            case 1:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 2:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 3:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NONE).build();
            case 4:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 5:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_RESERVED_FOR_TESTING_IN_PRODUCT_CONTROL).build();
            case 6:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 7:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 8:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 9:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 10:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 11:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 12:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 13:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 14:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 15:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 16:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 17:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 18:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 19:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 20:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 21:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 22:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 23:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 24:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 25:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 26:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 27:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 28:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 29:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 30:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 31:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 32:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 33:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 34:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 35:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 36:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 37:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 38:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 39:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 40:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 41:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 42:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 43:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 44:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 45:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 46:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 47:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 48:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 49:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 50:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 51:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 52:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 53:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 54:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 55:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 56:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 57:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 58:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 59:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 60:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FIT_APP_USAGE_AND_DIAGNOSTICS).build();
            case 61:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FIT_APP_USAGE_AND_DIAGNOSTICS).build();
            case 62:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FIT_APP_USAGE_AND_DIAGNOSTICS).build();
            case 63:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FIT_APP_USAGE_AND_DIAGNOSTICS).build();
            case 64:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setAndSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GBOARD_USER_METRICS_SETTINGS).build()).build();
            case 65:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setAndSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GBOARD_USER_METRICS_SETTINGS).build()).build();
            case 66:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setAndSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GBOARD_USER_METRICS_SETTINGS).build()).build();
            case 67:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setAndSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GBOARD_USER_METRICS_SETTINGS).build()).build();
            case 68:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 69:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 70:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 71:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 72:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 73:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 74:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 75:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 76:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 77:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 78:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 79:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 80:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 81:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 82:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 83:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 84:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 85:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 86:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 87:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 88:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 89:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 90:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 91:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_WEB_AND_APP_ACTIVITY).build();
            case 92:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_WEB_AND_APP_ACTIVITY).build();
            case 93:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_SUPPLEMENTAL_WEB_AND_APP_ACTIVITY_DEVICE_LEVEL).build();
            case 94:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_SUPPLEMENTAL_WEB_AND_APP_ACTIVITY_DEVICE_LEVEL).build();
            case 95:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 96:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PLAY_TOS).build();
            case 97:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 98:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 99:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 100:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FIND_MY_DEVICE).build();
            case 101:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FIND_MY_DEVICE).build();
            case 102:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FIND_MY_DEVICE).build();
            case 103:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FIND_MY_DEVICE).build();
            case 104:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FIND_MY_DEVICE).build();
            case 105:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FIND_MY_DEVICE).build();
            case 106:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 107:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 108:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 109:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 110:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case 111:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GLOBAL_LOCATION).build();
            case 112:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 113:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_LOCATION_REPORTING_DEVICE_LEVEL).build();
            case 114:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 115:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_LOCATION_ACCURACY).build();
            case 116:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setAndSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GLOBAL_LOCATION).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_LOCATION_ACCURACY).addOrSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_WIFI_SCANNING).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GLOBAL_WIFI).build()).build()).build();
            case 117:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 118:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setOrSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_EARTHQUAKE_ALERTS).addAndSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_LOCATION_ACCURACY).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_LOCATION_REPORTING_DEVICE_LEVEL).build()).build()).build();
            case 119:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_LOCATION_ACCURACY).build();
            case 120:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setOrSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FIT_APP_USAGE_AND_DIAGNOSTICS).build()).build();
            case 121:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setOrSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FIT_APP_USAGE_AND_DIAGNOSTICS).build()).build();
            case 122:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setOrSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FIT_APP_USAGE_AND_DIAGNOSTICS).build()).build();
            case 123:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setOrSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FIT_APP_USAGE_AND_DIAGNOSTICS).build()).build();
            case 124:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 125:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 126:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_BACKUP_TO_GOOGLE).build();
            case UC_APP_USAGE_PERSONALIZATION_WW_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 128:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_SYSTEM_HEALTH_FUNCTIONAL_DEBUGGING_WW_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_WEAR_CLOUD_SYNC).build();
            case UC_PLATFORM_MARKET_STATISTICS_WW_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHECKBOX).build();
            case UC_CONTEXTUALIZATION_NO_USER_DATA_WW_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_SERVICE_OR_API_PRODUCT_DEVELOPMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setOrSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_BLUETOOTH_SCANNING).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GLOBAL_BLUETOOTH).build()).build();
            case UC_PLATFORM_OR_FEATURE_PRODUCT_DEVELOPMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setOrSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_BLUETOOTH_SCANNING).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GLOBAL_BLUETOOTH).build()).build();
            case UC_PLATFORM_OR_FEATURE_MEASURING_USER_ENGAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setOrSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_BLUETOOTH_SCANNING).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GLOBAL_BLUETOOTH).build()).build();
            case UC_CRITICAL_FLEET_MANAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_1P_APP_FUNCTIONAL_DEBUGGING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_1P_APP_PRODUCT_IMPROVEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_1P_APP_PRODUCT_DEVELOPMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_DEVICE_CONTACTS_INFO).build();
            case UC_1P_APP_MEASURING_USER_ENGAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_WIFI_SCANNING).build();
            case UC_CONTEXTUALIZATION_NO_USER_DATA_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NONE).build();
            case UC_ANDROID_ECOSYSTEM_HEALTH_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FI_TOS).build();
            case UC_DEVICE_FINGERPRINT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FI_TOS).build();
            case UC_DEVICE_INTEGRITY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FI_TOS).build();
            case UC_PLATFORM_INTEGRITY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FI_NETWORK_DIAGNOSTICS).build();
            case UC_APP_INTEGRITY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FI_TOS).build();
            case UC_FRAUD_SPAM_ABUSE_PREVENTION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_DROIDGUARD_VERDICT_INPUT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_PLAY_MALWARE_DETECTION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 149:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_HEADLESS_1P_APP_FUNCTIONAL_DEBUGGING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_HEADLESS_1P_APP_PRODUCT_IMPROVEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_HEADLESS_1P_APP_PRODUCT_DEVELOPMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_HEADLESS_1P_APP_MEASURING_USER_ENGAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_SERVICE_OR_API_MEASURING_USER_ENGAGEMENT_WW_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_UNBRANDED_1P_APP_MEASURING_USER_ENGAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PAY_TOS).build();
            case UC_UNBRANDED_1P_APP_PRODUCT_DEVELOPMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PAY_TOS).build();
            case UC_CROSS_PRODUCT_PERSONALIZATION_FOOTPRINTS_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PAY_TOS).build();
            case UC_SERVICE_ABUSE_PREVENTION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PAY_TOS).build();
            case UC_EXPERIMENT_TARGETING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PAY_TOS).build();
            case UC_REFINING_EXPERIMENTS_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_GBOARD_FUNCTIONAL_DEBUGGING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_GBOARD_PRODUCT_IMPROVEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_UNICORN_SUPERVISION).build();
            case UC_GBOARD_PRODUCT_DEVELOPMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_GBOARD_MEASURING_USER_ENGAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_GPP_SIDELOADED_UNSAFE_APP_DETECTION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_SUPPLEMENTAL_WEB_AND_APP_ACTIVITY_DEVICE_LEVEL).build();
            case UC_GPP_UNSAFE_APP_DETECTION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_SUPPLEMENTAL_WEB_AND_APP_ACTIVITY_DEVICE_LEVEL).build();
            case UC_INTERNAL_DESCRIPTION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_SUPPLEMENTAL_WEB_AND_APP_ACTIVITY_DEVICE_LEVEL).build();
            case UC_FMD_RING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_FMD_LOCATE_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_FMD_LOCK_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_FMD_UNPAIR_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_LOCATION_SHARING).build();
            case UC_ENX_OPT_OUT_DEIDENTIFIED_TELEMETRY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GMM_UGC_PUBLIC_POST).build();
            case UC_1P_HW_FUNCTIONAL_DEBUGGING_WW_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GMM_UGC_PUBLIC_POST).build();
            case UC_1P_HW_PRODUCT_IMPROVEMENT_WW_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GMM_UGC_PUBLIC_POST).build();
            case UC_1P_HW_PRODUCT_DEVELOPMENT_WW_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_1P_HW_MEASURING_USER_ENGAGEMENT_WW_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_FIT_FUNCTIONAL_DEBUGGING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_FIT_PRODUCT_IMPROVEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_FIT_PRODUCT_DEVELOPMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_FIT_MEASURING_USER_ENGAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_FMD_IDENTIFY_DEVICE_STATE_AND_CAPABILITY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_FOREGROUND_LOCATION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PLAY_TOS).build();
            case UC_LOCATION_HISTORY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PLAY_TOS).build();
            case UC_LOCATION_HISTORY_USER_EDIT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PLAY_TOS).build();
            case UC_LOCATION_ACCURACY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_DEVICE_APPS).build();
            case UC_LOCATION_ACCURACY_WIFI_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PANELS_TOS).build();
            case UC_EARTHQUAKE_ALERTING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PANELS_TOS).build();
            case UC_FIT_APP_OR_API_FUNCTIONAL_DEBUGGING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PANELS_TOS).build();
            case UC_FIT_APP_OR_API_PRODUCT_IMPROVEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_FIT_APP_OR_API_PRODUCT_DEVELOPMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_STACK_COPY_TO_DRIVE).build();
            case UC_FIT_APP_OR_API_MEASURING_USER_ENGAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PAY_TOS).build();
            case UC_FCM_FUNCTIONAL_DEBUGGING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GMM_UGC_PUBLIC_RESPONSE).build();
            case UC_1P_HW_DEVICE_MANAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GMM_UGC_PUBLIC_POST).build();
            case UC_1P_APP_PROVISION_OF_SERVICE_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_BACKUP_USER_DATA_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_GPP_UPLOAD_UNSAFE_APP_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_OPENSKY_TOS).build();
            case UC_RESERVED_FOR_TESTING_IN_PRODUCT_CONTROL_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_OPENSKY_TOS).build();
            case 198:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_OPENSKY_TOS).build();
            case UC_SDK_FUNCTIONAL_DEBUGGING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_OPENSKY_TOS).build();
            case 200:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_WINGDELIVERY_TOS).build();
            case UC_SDK_PRODUCT_DEVELOPMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_WINGDELIVERY_TOS).build();
            case UC_SDK_MEASURING_USER_ENGAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_WINGDELIVERY_TOS).build();
            case UC_3P_APP_PROVISION_OF_SERVICE_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_WINGDELIVERY_TOS).build();
            case UC_WEAR_CLOUD_SYNC_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PLAY_CONSOLE_TOS).build();
            case UC_CHIME_MEASURING_USER_ENGAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PLAY_CONSOLE_TOS).build();
            case UC_CHIME_FUNCTIONAL_DEBUGGING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PLAY_CONSOLE_TOS).build();
            case UC_CHIME_PRODUCT_IMPROVEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PLAY_CONSOLE_TOS).build();
            case UC_CONTACTS_ACCOUNT_TYPE_LOGGING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_IN_PRODUCT_PERSONALIZATION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_NEARBY_MESSAGES_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_FAST_PAIR_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_NEARBY_SHARING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_USER_AUTHENTICATION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHROME_IMAGE_DESCRIPTIONS).build();
            case UC_GOOGLE_CONTACTS_SYNC_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_DEVICE_CONTACT_INFO_COLLECTION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_PEOPLE_DETAILS_SYNC_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GMM_UGC_CROWDSOURCE_DATA).build();
            case UC_UNBRANDED_CROSS_PRODUCT_PERSONALIZATION_FOOTPRINTS_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_HADES_ON_DEVICE_ABUSE_DETECTION).build();
            case UC_WIFI_NETWORK_SCORING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_HADES_ON_DEVICE_ABUSE_DETECTION).build();
            case UC_3P_APP_DEVICE_INTEGRITY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_VERIFY_URL_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 221:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_FIND_MY_DEVICE).build();
            case UC_IP_LOCATION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GLOBAL_LOCATION).build();
            case UC_POPULATED_SERVER_SIDE_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NAVLOGS_NOTICE).build();
            case UC_FI_FUNCTIONAL_DEBUGGING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CARE_EULA_AND_PP).build();
            case UC_FI_PRODUCT_IMPROVEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CARE_EULA_AND_PP).build();
            case UC_EARTHQUAKE_DETECTION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CARE_EULA_AND_PP).build();
            case UC_FI_MEASURING_USER_ENGAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CARE_EULA_AND_PP).build();
            case UC_ADS_TARGETING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_ADS_MEASUREMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_HADES_ON_DEVICE_ABUSE_DETECTION).build();
            case UC_ADS_EXTERNAL_INTEGRATION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_TACHYON_PHONE_NUMBER_IDENTITY).build();
            case UC_ABUSE_CONTENT_CLASSIFICATION_VERDICTS_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case 232:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NOW_PLAYING_CLOUD_SEARCH).build();
            case UC_FI_CELL_TOWER_HISTORY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NOW_PLAYING).build();
            case UC_PAY_FUNCTIONAL_DEBUGGING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_PAY_PRODUCT_IMPROVEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHROME_SYNC).build();
            case UC_PAY_MEASURING_USER_ENGAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NONE).build();
            case UC_FMD_LOCATE_ACCESSORY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NONE).build();
            case UC_SEARCH_HISTORY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NONE).build();
            case UC_BROWSING_HISTORY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NONE).build();
            case UC_RESTORE_USER_DATA_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NONE).build();
            case UC_LOCATION_HISTORY_CONSENT_CHANGE_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHROME_TOS).build();
            case UC_UNICORN_SETUP_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CHROME_TOS).build();
            case UC_UNICORN_MANAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_UNICORN_ACTIVITY_SUPERVISION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PAY_PHONE_NUMBER_DISCOVERABILITY).build();
            case UC_ADS_DELIVERY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PAY_GROUPS).build();
            case UC_ADS_GENERAL_TARGETING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PAY_CONTACTS_SYNC).build();
            case UC_ADS_PERSONALIZATION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PHONE_NUMBER_ACCOUNT_SECURITY).build();
            case 248:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_CONSTELLATION_AUTO_VERIFICATION).build();
            case 249:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PHONE_NUMBER_REACHABILITY_GAIA).build();
            case UC_FAMILY_ADMIN_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PHONE_NUMBER_REACHABILITY_GAIA).build();
            case UC_FAMILY_1P_INTEGRATION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_PHONE_NUMBER_BETTER_ADS).build();
            case UC_DIGITAL_WELLBEING_FUNCTIONAL_DEBUGGING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GLOBAL_LOCATION).build();
            case UC_DIGITAL_WELLBEING_MEASURING_USER_ENGAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GMM_UGC_PUBLIC_POST).build();
            case UC_DIGITAL_WELLBEING_PRODUCT_IMPROVEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_STREET_VIEW_VENDOR_CONTRACT).build();
            case 255:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_STREET_VIEW_VENDOR_CONTRACT).build();
            case 256:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_STREET_VIEW_VENDOR_CONTRACT).build();
            case UC_GMM_PUBLIC_PHOTO_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_STREET_VIEW_VENDOR_CONTRACT).build();
            case UC_GMM_PUBLIC_VIDEO_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_GMM_USER_INITIATED_FEEDBACK_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_MARKETING_ENGAGEMENT_GROWTH_COMMS_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_LOCATION_HISTORY_BOTTOM_SHEET_CONSENT_TOGGLE_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NONE).build();
            case UC_FI_PROVISION_OF_SERVICE_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_GOOGLE_TOS_AND_PP).build();
            case UC_HEADLESS_DPC_CLOUD_API_PROVISION_OF_SERVICE_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_ASSISTANT_SUGGESTIONS).build();
            case UC_HEADLESS_DPC_CLOUD_API_FUNCTIONAL_DEBUGGING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_ASSISTANT_SUGGESTIONS).build();
            case UC_PAY_PROVISION_OF_SERVICE_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_ASSISTANT_SUGGESTIONS).build();
            case UC_PAY_DEVICE_FINGERPRINT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_SMART_REPLY).build();
            case UC_BACKUP_MANAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_SMART_REPLY).build();
            case UC_PLAY_APP_PROVISION_OF_SERVICE_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_SUGGESTED_ACTIONS).build();
            case UC_CONTEXTUALIZATION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_SUGGESTED_ACTIONS).build();
            case UC_CONTEXTUALIZATION_RECENT_ACTIVITY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_SUGGESTED_STICKERS).build();
            case UC_CONTEXTUALIZATION_DEVICE_CAPABILITIES_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_SUGGESTED_STICKERS).build();
            case UC_CONTEXTUALIZATION_DEVICE_STATE_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_WEB_LINK_PREVIEW).build();
            case UC_CONTEXTUALIZATION_LANGUAGE_OR_LOCALE_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_WEB_LINK_PREVIEW).build();
            case UC_CONTEXTUALIZATION_COUNTRY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_ALL_PREVIEW).build();
            case UC_CONTEXTUALIZATION_USER_DATA_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setOrSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_ALL_PREVIEW).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_WEB_LINK_PREVIEW).build()).build();
            case UC_DEVICE_APPS_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setOrSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_ALL_PREVIEW).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_WEB_LINK_PREVIEW).build()).build();
            case UC_CONTEXTUALIZATION_1P_INSTALLED_APPS_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_VERIFIED_SMS).build();
            case UC_CONTEXTUALIZATION_DEFAULT_HANDLER_APPS_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_VERIFIED_SMS).build();
            case UC_HEADLESS_DPC_CLOUD_API_CONFIGURE_APP_POLICIES_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_VERIFIED_SMS).build();
            case UC_FCM_MESSAGE_DELIVERY_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_HELP_MESSAGES).build();
            case UC_PANELS_APP_PROVISION_OF_SERVICE_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_HELP_MESSAGES).build();
            case UC_PANELS_APP_FUNCTIONAL_DEBUGGING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_SAMSUNG_INTEGRATION).build();
            case UC_PANELS_APP_IN_APP_DATA_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_SAMSUNG_INTEGRATION).build();
            case UC_STACK_COPY_TO_DRIVE_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_SAMSUNG_INTEGRATION).build();
            case UC_HEADLESS_DPC_CLOUD_API_REPORT_DEVICE_INFO_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_DEVICE_PAIRING).build();
            case UC_HEADLESS_DPC_CLOUD_API_SET_UP_DEVICE_MANAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_DEVICE_PAIRING).build();
            case UC_HEADLESS_DPC_CLOUD_API_RUN_DEVICE_COMMANDS_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_FI_SYNC).build();
            case UC_PAY_FRAUD_SPAM_ABUSE_PREVENTION_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_FI_SYNC).build();
            case UC_CHIME_NOTIFICATION_MANAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setOrSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_RCS_CONTROL).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_RCS_TOS).build()).build();
            case UC_OPENSKY_PROVISION_OF_SERVICE_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_RCS_CONTROL).build();
            case UC_OPENSKY_FUNCTIONAL_DEBUGGING_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setOrSpec((AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.newBuilder().addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_RCS_CONTROL).addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_RCS_TOS).build()).build();
            case UC_OPENSKY_MEASURING_USER_ENGAGEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_SUPERSORT).build();
            case UC_OPENSKY_PRODUCT_IMPROVEMENT_VALUE:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_MESSAGES_SUPERSORT).build();
            default:
                return (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.newBuilder().setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NONE).build();
        }
    }
}
