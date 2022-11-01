package googledata.experiments.mobile.authenticator_android.features;

import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HideCodesFeatureFlagsImpl implements HideCodesFeatureFlags {
    public static final ProcessStablePhenotypeFlag enableHideCodes = new ProcessStablePhenotypeFlagFactory("com.google.android.apps.authenticator").withLogSourceNames(ImmutableList.of((Object) "AUTHENTICATOR_ANDROID_PRIMES")).autoSubpackage().createFlagRestricted("8", false);

    @Override // googledata.experiments.mobile.authenticator_android.features.HideCodesFeatureFlags
    public boolean enableHideCodes() {
        return ((Boolean) enableHideCodes.get()).booleanValue();
    }
}
