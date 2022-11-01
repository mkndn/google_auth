package com.google.android.libraries.consentverifier.flags;

import googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierFeatures;
import googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierRedactedFeatures;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Flags {
    public static boolean enableAllFeatures() {
        if (CollectionBasisVerifierRedactedFeatures.compiled()) {
            return CollectionBasisVerifierRedactedFeatures.overrideEnableAllFeatures();
        }
        return CollectionBasisVerifierFeatures.enableAllFeatures();
    }

    public static boolean enableLogging() {
        if (CollectionBasisVerifierRedactedFeatures.compiled()) {
            return CollectionBasisVerifierRedactedFeatures.overrideEnableLogging();
        }
        return CollectionBasisVerifierFeatures.enableLogging();
    }

    public static boolean enableLoggingFieldNotAnnotated() {
        if (CollectionBasisVerifierRedactedFeatures.compiled()) {
            return CollectionBasisVerifierRedactedFeatures.overrideEnableLoggingFieldNotAnnotated();
        }
        return CollectionBasisVerifierFeatures.enableLoggingFieldNotAnnotated();
    }

    public static boolean enableLoggingUcNeverCollect() {
        if (CollectionBasisVerifierRedactedFeatures.compiled()) {
            return CollectionBasisVerifierRedactedFeatures.overrideEnableLoggingUcNeverCollect();
        }
        return CollectionBasisVerifierFeatures.enableLoggingUcNeverCollect();
    }

    public static boolean enableUsingLogVerifierResult() {
        if (CollectionBasisVerifierRedactedFeatures.compiled()) {
            return CollectionBasisVerifierRedactedFeatures.overrideEnableUsingLogVerifierResult();
        }
        return CollectionBasisVerifierFeatures.enableUsingLogVerifierResult();
    }

    public static long failureLogCooldownPeriodMs() {
        if (CollectionBasisVerifierRedactedFeatures.compiled()) {
            return 0L;
        }
        return CollectionBasisVerifierFeatures.failureLogCooldownPeriodMs();
    }

    public static long maxStackTraceSize() {
        if (CollectionBasisVerifierRedactedFeatures.compiled()) {
            return -1L;
        }
        return CollectionBasisVerifierFeatures.maxStackTraceSize();
    }

    public static long minAppVersionCodeToLog() {
        if (CollectionBasisVerifierRedactedFeatures.compiled()) {
            return -1L;
        }
        return CollectionBasisVerifierFeatures.minAppVersionCodeToLog();
    }

    public static boolean reportFailuresToLogcat() {
        if (CollectionBasisVerifierRedactedFeatures.compiled()) {
            return CollectionBasisVerifierRedactedFeatures.reportFailuresToLogcat();
        }
        return false;
    }
}
