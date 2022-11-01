package googledata.experiments.mobile.gmscore.collection_basis_verifier.features;

import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionBasisVerifierFeaturesFlagsImpl implements CollectionBasisVerifierFeaturesFlags {
    public static final ProcessStablePhenotypeFlag enableAllFeatures;
    public static final ProcessStablePhenotypeFlag enableLogging;
    public static final ProcessStablePhenotypeFlag enableLoggingFieldNotAnnotated;
    public static final ProcessStablePhenotypeFlag enableLoggingUcNeverCollect;
    public static final ProcessStablePhenotypeFlag enableUsingLogVerifierResult;
    public static final ProcessStablePhenotypeFlag failureLogCooldownPeriodMs;
    public static final ProcessStablePhenotypeFlag maxStackTraceSize;
    public static final ProcessStablePhenotypeFlag minAppVersionCodeToLog;
    public static final ProcessStablePhenotypeFlag testPropagationSpeedCollectionBasis;
    public static final ProcessStablePhenotypeFlag testPropagationSpeedCollectionBasisV2;

    static {
        ProcessStablePhenotypeFlagFactory autoSubpackage = new ProcessStablePhenotypeFlagFactory("com.google.android.libraries.consentverifier").autoSubpackage();
        enableAllFeatures = autoSubpackage.createFlagRestricted("CollectionBasisVerifierFeatures__enable_all_features", false);
        enableLogging = autoSubpackage.createFlagRestricted("CollectionBasisVerifierFeatures__enable_logging", false);
        enableLoggingFieldNotAnnotated = autoSubpackage.createFlagRestricted("CollectionBasisVerifierFeatures__enable_logging_field_not_annotated", false);
        enableLoggingUcNeverCollect = autoSubpackage.createFlagRestricted("CollectionBasisVerifierFeatures__enable_logging_uc_never_collect", false);
        enableUsingLogVerifierResult = autoSubpackage.createFlagRestricted("CollectionBasisVerifierFeatures__enable_using_log_verifier_result", false);
        failureLogCooldownPeriodMs = autoSubpackage.createFlagRestricted("CollectionBasisVerifierFeatures__failure_log_cooldown_period_ms", 86400000L);
        maxStackTraceSize = autoSubpackage.createFlagRestricted("CollectionBasisVerifierFeatures__max_stack_trace_size", 1000L);
        minAppVersionCodeToLog = autoSubpackage.createFlagRestricted("CollectionBasisVerifierFeatures__min_app_version_code_to_log", -1L);
        testPropagationSpeedCollectionBasis = autoSubpackage.createFlagRestricted("CollectionBasisVerifierFeatures__test_propagation_speed_collection_basis", false);
        testPropagationSpeedCollectionBasisV2 = autoSubpackage.createFlagRestricted("CollectionBasisVerifierFeatures__test_propagation_speed_collection_basis_v2", false);
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierFeaturesFlags
    public boolean enableAllFeatures() {
        return ((Boolean) enableAllFeatures.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierFeaturesFlags
    public boolean enableLogging() {
        return ((Boolean) enableLogging.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierFeaturesFlags
    public boolean enableLoggingFieldNotAnnotated() {
        return ((Boolean) enableLoggingFieldNotAnnotated.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierFeaturesFlags
    public boolean enableLoggingUcNeverCollect() {
        return ((Boolean) enableLoggingUcNeverCollect.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierFeaturesFlags
    public boolean enableUsingLogVerifierResult() {
        return ((Boolean) enableUsingLogVerifierResult.get()).booleanValue();
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierFeaturesFlags
    public long failureLogCooldownPeriodMs() {
        return ((Long) failureLogCooldownPeriodMs.get()).longValue();
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierFeaturesFlags
    public long maxStackTraceSize() {
        return ((Long) maxStackTraceSize.get()).longValue();
    }

    @Override // googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierFeaturesFlags
    public long minAppVersionCodeToLog() {
        return ((Long) minAppVersionCodeToLog.get()).longValue();
    }
}
