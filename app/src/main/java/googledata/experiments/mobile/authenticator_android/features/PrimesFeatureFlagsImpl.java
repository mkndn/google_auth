package googledata.experiments.mobile.authenticator_android.features;

import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesFeatureFlagsImpl implements PrimesFeatureFlags {
    public static final ProcessStablePhenotypeFlag enableBatteryMetric;
    public static final ProcessStablePhenotypeFlag enableCrashMetric;
    public static final ProcessStablePhenotypeFlag enableMemoryMetric;
    public static final ProcessStablePhenotypeFlag enableNetworkMetric;
    public static final ProcessStablePhenotypeFlag enablePackageMetric;
    public static final ProcessStablePhenotypeFlag enableTimerMetric;

    static {
        ProcessStablePhenotypeFlagFactory autoSubpackage = new ProcessStablePhenotypeFlagFactory("com.google.android.apps.authenticator").withLogSourceNames(ImmutableList.of((Object) "AUTHENTICATOR_ANDROID_PRIMES")).autoSubpackage();
        enableBatteryMetric = autoSubpackage.createFlagRestricted("6", false);
        enableCrashMetric = autoSubpackage.createFlagRestricted("3", true);
        enableMemoryMetric = autoSubpackage.createFlagRestricted("1", false);
        enableNetworkMetric = autoSubpackage.createFlagRestricted("4", false);
        enablePackageMetric = autoSubpackage.createFlagRestricted("5", false);
        enableTimerMetric = autoSubpackage.createFlagRestricted("2", false);
    }

    @Override // googledata.experiments.mobile.authenticator_android.features.PrimesFeatureFlags
    public boolean enableBatteryMetric() {
        return ((Boolean) enableBatteryMetric.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.authenticator_android.features.PrimesFeatureFlags
    public boolean enableCrashMetric() {
        return ((Boolean) enableCrashMetric.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.authenticator_android.features.PrimesFeatureFlags
    public boolean enableMemoryMetric() {
        return ((Boolean) enableMemoryMetric.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.authenticator_android.features.PrimesFeatureFlags
    public boolean enableNetworkMetric() {
        return ((Boolean) enableNetworkMetric.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.authenticator_android.features.PrimesFeatureFlags
    public boolean enablePackageMetric() {
        return ((Boolean) enablePackageMetric.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.authenticator_android.features.PrimesFeatureFlags
    public boolean enableTimerMetric() {
        return ((Boolean) enableTimerMetric.get()).booleanValue();
    }
}
