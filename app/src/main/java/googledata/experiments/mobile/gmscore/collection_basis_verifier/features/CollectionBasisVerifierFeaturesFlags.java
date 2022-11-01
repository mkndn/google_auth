package googledata.experiments.mobile.gmscore.collection_basis_verifier.features;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface CollectionBasisVerifierFeaturesFlags {
    boolean enableAllFeatures();

    boolean enableLogging();

    boolean enableLoggingFieldNotAnnotated();

    boolean enableLoggingUcNeverCollect();

    boolean enableUsingLogVerifierResult();

    long failureLogCooldownPeriodMs();

    long maxStackTraceSize();

    long minAppVersionCodeToLog();
}
