package googledata.experiments.mobile.gmscore.collection_basis_verifier.features;

import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionBasisVerifierRedactedFeaturesFlagsImpl implements CollectionBasisVerifierRedactedFeaturesFlags {
    public static final ProcessStablePhenotypeFlag overrideEnableAllFeatures;
    public static final ProcessStablePhenotypeFlag overrideEnableLogging;
    public static final ProcessStablePhenotypeFlag overrideEnableLoggingFieldNotAnnotated;
    public static final ProcessStablePhenotypeFlag overrideEnableLoggingUcNeverCollect;
    public static final ProcessStablePhenotypeFlag overrideEnableUsingLogVerifierResult;
    public static final ProcessStablePhenotypeFlag reportFailuresToLogcat;

    static {
        ProcessStablePhenotypeFlagFactory autoSubpackage = new ProcessStablePhenotypeFlagFactory("com.google.android.libraries.consentverifier").autoSubpackage();
        overrideEnableAllFeatures = autoSubpackage.createFlagRestricted("CollectionBasisVerifierRedactedFeatures__override_enable_all_features", true);
        overrideEnableLogging = autoSubpackage.createFlagRestricted("CollectionBasisVerifierRedactedFeatures__override_enable_logging", true);
        overrideEnableLoggingFieldNotAnnotated = autoSubpackage.createFlagRestricted("CollectionBasisVerifierRedactedFeatures__override_enable_logging_field_not_annotated", true);
        overrideEnableLoggingUcNeverCollect = autoSubpackage.createFlagRestricted("CollectionBasisVerifierRedactedFeatures__override_enable_logging_uc_never_collect", true);
        overrideEnableUsingLogVerifierResult = autoSubpackage.createFlagRestricted("CollectionBasisVerifierRedactedFeatures__override_enable_using_log_verifier_result", true);
        reportFailuresToLogcat = autoSubpackage.createFlagRestricted("CollectionBasisVerifierRedactedFeatures__report_failures_to_logcat", true);
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierRedactedFeaturesFlags
    public boolean compiled() {
        return true;
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierRedactedFeaturesFlags
    public boolean overrideEnableAllFeatures() {
        return ((Boolean) overrideEnableAllFeatures.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierRedactedFeaturesFlags
    public boolean overrideEnableLogging() {
        return ((Boolean) overrideEnableLogging.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierRedactedFeaturesFlags
    public boolean overrideEnableLoggingFieldNotAnnotated() {
        return ((Boolean) overrideEnableLoggingFieldNotAnnotated.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierRedactedFeaturesFlags
    public boolean overrideEnableLoggingUcNeverCollect() {
        return ((Boolean) overrideEnableLoggingUcNeverCollect.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierRedactedFeaturesFlags
    public boolean overrideEnableUsingLogVerifierResult() {
        return ((Boolean) overrideEnableUsingLogVerifierResult.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierRedactedFeaturesFlags
    public boolean reportFailuresToLogcat() {
        return ((Boolean) reportFailuresToLogcat.get()).booleanValue();
    }
}
