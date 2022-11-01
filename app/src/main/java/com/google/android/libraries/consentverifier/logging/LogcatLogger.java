package com.google.android.libraries.consentverifier.logging;

import android.util.Log;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.privacy.delphi.common.annotations.CollectionBasisSpecUtil;
import com.google.protos.collection_basis_verifier.logging.VerificationFailureLogOuterClass$VerificationFailureLog;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LogcatLogger implements CollectionBasisLogger {
    @Override // com.google.android.libraries.consentverifier.logging.CollectionBasisLogger
    public void logEvent(VerificationFailureLogOuterClass$VerificationFailureLog verificationFailureLogOuterClass$VerificationFailureLog) {
        if (verificationFailureLogOuterClass$VerificationFailureLog != null) {
            ImmutableList.Builder builder = ImmutableList.builder();
            builder.add((Object) "Collection basis failure detected:");
            if (verificationFailureLogOuterClass$VerificationFailureLog.hasAppName()) {
                builder.add((Object) ("app_name: " + verificationFailureLogOuterClass$VerificationFailureLog.getAppName()));
            }
            if (verificationFailureLogOuterClass$VerificationFailureLog.hasAppVersionCode()) {
                builder.add((Object) ("app_version_code: " + verificationFailureLogOuterClass$VerificationFailureLog.getAppVersionCode()));
            }
            if (verificationFailureLogOuterClass$VerificationFailureLog.hasProtoId()) {
                builder.add((Object) ("proto_id: " + verificationFailureLogOuterClass$VerificationFailureLog.getProtoId()));
            }
            if (verificationFailureLogOuterClass$VerificationFailureLog.hasFeatureId()) {
                builder.add((Object) ("feature_id: " + verificationFailureLogOuterClass$VerificationFailureLog.getFeatureId()));
            }
            if (verificationFailureLogOuterClass$VerificationFailureLog.hasDataLength()) {
                builder.add((Object) ("data_length: " + verificationFailureLogOuterClass$VerificationFailureLog.getDataLength()));
            }
            if (verificationFailureLogOuterClass$VerificationFailureLog.hasAnyUrl()) {
                builder.add((Object) ("any_url: " + verificationFailureLogOuterClass$VerificationFailureLog.getAnyUrl()));
            }
            if (verificationFailureLogOuterClass$VerificationFailureLog.hasVerificationFailure()) {
                builder.add((Object) ("verification_failure: " + verificationFailureLogOuterClass$VerificationFailureLog.getVerificationFailure().name()));
            }
            if (verificationFailureLogOuterClass$VerificationFailureLog.hasUseCase()) {
                builder.add((Object) ("use_case: " + verificationFailureLogOuterClass$VerificationFailureLog.getUseCase().name()));
            }
            if (verificationFailureLogOuterClass$VerificationFailureLog.hasBasisExpression()) {
                builder.add((Object) ("basis_expression: " + CollectionBasisSpecUtil.unpackBasisExpression(verificationFailureLogOuterClass$VerificationFailureLog.getBasisExpression())));
            }
            if (!verificationFailureLogOuterClass$VerificationFailureLog.getFieldPathList().isEmpty()) {
                builder.add((Object) ("field_path: " + Joiner.on(",").join(verificationFailureLogOuterClass$VerificationFailureLog.getFieldPathList())));
            }
            if (verificationFailureLogOuterClass$VerificationFailureLog.hasSettingName()) {
                builder.add((Object) ("setting_name: " + verificationFailureLogOuterClass$VerificationFailureLog.getSettingName()));
            }
            if (verificationFailureLogOuterClass$VerificationFailureLog.hasStackTrace()) {
                builder.add((Object) ("stack_trace: " + verificationFailureLogOuterClass$VerificationFailureLog.getStackTrace()));
            }
            Log.e("CBVerifier", Joiner.on("\n").join(builder.build()));
        }
    }
}
