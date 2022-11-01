package com.google.android.libraries.performance.primes.metrics.battery;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.flogger.GoogleLogger;
import com.google.devrel.primes.hashing.Hashing;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import logs.proto.wireless.performance.mobile.BatteryMetric$HashedString;
import logs.proto.wireless.performance.mobile.BatteryMetric$Timer;

/* compiled from: PG */
/* loaded from: classes.dex */
final class HashingNameSanitizer {
    private static boolean disabledForTestOnly;
    final ConcurrentHashMap hashHashMap = new ConcurrentHashMap();
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/battery/HashingNameSanitizer");
    private static final Splitter SLASH_SPLITTER = Splitter.on('/');
    private static final Pattern SYSTEM_TASK_PATTERN = Pattern.compile("^(\\*[a-z]+\\*).*");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.metrics.battery.HashingNameSanitizer$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$libraries$performance$primes$metrics$battery$HashingNameSanitizer$NameType;

        static {
            int[] iArr = new int[NameType.values().length];
            $SwitchMap$com$google$android$libraries$performance$primes$metrics$battery$HashingNameSanitizer$NameType = iArr;
            try {
                iArr[NameType.WAKELOCK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$libraries$performance$primes$metrics$battery$HashingNameSanitizer$NameType[NameType.SYNC.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$libraries$performance$primes$metrics$battery$HashingNameSanitizer$NameType[NameType.JOB.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$libraries$performance$primes$metrics$battery$HashingNameSanitizer$NameType[NameType.PROCESS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$libraries$performance$primes$metrics$battery$HashingNameSanitizer$NameType[NameType.SENSOR.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum NameType {
        WAKELOCK,
        SYNC,
        JOB,
        PROCESS,
        SENSOR
    }

    static String sanitizeName(String str, NameType nameType) {
        switch (AnonymousClass1.$SwitchMap$com$google$android$libraries$performance$primes$metrics$battery$HashingNameSanitizer$NameType[nameType.ordinal()]) {
            case 1:
                return sanitizeWakelockName(str);
            case 2:
                return sanitizeSyncName(str);
            case 3:
                return "--";
            default:
                return str;
        }
    }

    static String sanitizeSyncName(String str) {
        List splitToList = SLASH_SPLITTER.splitToList(str);
        if (splitToList.size() != 3) {
            if (!disabledForTestOnly) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/battery/HashingNameSanitizer", "sanitizeSyncName", 55, "HashingNameSanitizer.java")).log("malformed sync name: %s", str);
                return "MALFORMED";
            }
            return "MALFORMED";
        }
        return (String) splitToList.get(0);
    }

    static String sanitizeWakelockName(String str) {
        Matcher matcher = SYSTEM_TASK_PATTERN.matcher(str);
        if (matcher.matches()) {
            if (str.startsWith("*sync*/")) {
                return "*sync*/" + sanitizeSyncName(str.substring("*sync*/".length()));
            }
            String group = matcher.group(1);
            if (!disabledForTestOnly) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/battery/HashingNameSanitizer", "sanitizeWakelockName", 76, "HashingNameSanitizer.java")).log("non-sync system task wakelock: %s", group);
            }
            return group;
        }
        if (!disabledForTestOnly) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/battery/HashingNameSanitizer", "sanitizeWakelockName", 81, "HashingNameSanitizer.java")).log("wakelock: %s", str);
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BatteryMetric$Timer hashRawTimerName(NameType nameType, BatteryMetric$Timer batteryMetric$Timer) {
        if (batteryMetric$Timer.getName().hasUnhashedName()) {
            BatteryMetric$HashedString.Builder builder = (BatteryMetric$HashedString.Builder) batteryMetric$Timer.getName().toBuilder();
            return (BatteryMetric$Timer) ((BatteryMetric$Timer.Builder) batteryMetric$Timer.toBuilder()).setName(builder.setHash(rawHashFor(builder.getUnhashedName(), nameType)).clearUnhashedName()).build();
        }
        return batteryMetric$Timer;
    }

    long rawHashFor(String str, NameType nameType) {
        long longValue = ((Long) Preconditions.checkNotNull(Hashing.hash(str))).longValue();
        if (!this.hashHashMap.containsKey(Long.valueOf(longValue))) {
            String sanitizeName = sanitizeName(str, nameType);
            Long hash = Hashing.hash(sanitizeName);
            if (!disabledForTestOnly) {
                GoogleLogger googleLogger = logger;
                ((GoogleLogger.Api) ((GoogleLogger.Api) googleLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/battery/HashingNameSanitizer", "rawHashFor", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLATFORM_OR_FEATURE_MEASURING_USER_ENGAGEMENT_VALUE, "HashingNameSanitizer.java")).log("Sanitized Hash: [%s] %s -> %d", nameType, sanitizeName, hash);
                ((GoogleLogger.Api) ((GoogleLogger.Api) googleLogger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/battery/HashingNameSanitizer", "rawHashFor", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CRITICAL_FLEET_MANAGEMENT_VALUE, "HashingNameSanitizer.java")).log("Raw Hash: [%s] %s -> %d", nameType, str, Long.valueOf(longValue));
            }
            if (hash != null) {
                this.hashHashMap.putIfAbsent(Long.valueOf(longValue), hash);
            }
        }
        return longValue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BatteryMetric$Timer sanitizeHashedTimerName(NameType nameType, BatteryMetric$Timer batteryMetric$Timer) {
        if (batteryMetric$Timer.getName().hasHash()) {
            BatteryMetric$HashedString.Builder builder = (BatteryMetric$HashedString.Builder) batteryMetric$Timer.getName().toBuilder();
            return (BatteryMetric$Timer) ((BatteryMetric$Timer.Builder) batteryMetric$Timer.toBuilder()).setName(builder.setHash(((Long) Preconditions.checkNotNull((Long) this.hashHashMap.get(Long.valueOf(builder.getHash())))).longValue())).build();
        }
        return batteryMetric$Timer;
    }
}
