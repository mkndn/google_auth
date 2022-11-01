package com.google.android.libraries.consentverifier.logging;

import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.libraries.consentverifier.flags.Flags;
import com.google.protos.collection_basis_verifier.logging.TelemetryEnums$CollectionBasisVerifierEventType$Type;
import com.google.protos.collection_basis_verifier.logging.VerificationFailureLogOuterClass$VerificationFailureLog;
import googledata.experiments.mobile.gmscore.collection_basis_verifier.features.CollectionBasisVerifierRedactedFeatures;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ClearcutEventLogger implements CollectionBasisLogger {
    private final ClearcutLogger logger;

    public ClearcutEventLogger(ClearcutLogger clearcutLogger) {
        this.logger = clearcutLogger;
    }

    @Override // com.google.android.libraries.consentverifier.logging.CollectionBasisLogger
    public void logEvent(VerificationFailureLogOuterClass$VerificationFailureLog verificationFailureLogOuterClass$VerificationFailureLog) {
        if (verificationFailureLogOuterClass$VerificationFailureLog != null) {
            if (CollectionBasisVerifierRedactedFeatures.compiled() && Flags.reportFailuresToLogcat()) {
                new LogcatLogger().logEvent(verificationFailureLogOuterClass$VerificationFailureLog);
            }
            this.logger.newEvent(verificationFailureLogOuterClass$VerificationFailureLog).setEventCode(TelemetryEnums$CollectionBasisVerifierEventType$Type.VERIFICATION_FAILURE_LOG.getNumber()).log();
        }
    }
}
