package com.google.android.gms.common;

import com.google.common.collect.ImmutableList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleSourceStampsResult {
    private final boolean allowed;
    private final String errorMessage;
    private final ImmutableList stampList;
    private final Status status;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Status {
        DEFAULT,
        VERSION_TOO_LOW,
        STAMP_NOT_NEEDED,
        NO_STAMP,
        CANNOT_VERIFY,
        UNKNOWN_STAMP,
        MULTIPLE_SIGNERS_INVALID,
        APK_NOT_SIGNED,
        SIGNING_CERT_MISMATCH,
        GENERIC_ERROR
    }

    private GoogleSourceStampsResult(boolean z, Status status, String str, ImmutableList immutableList) {
        this.allowed = z;
        this.status = status;
        this.errorMessage = str;
        this.stampList = immutableList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GoogleSourceStampsResult allowed(ImmutableList immutableList) {
        return new GoogleSourceStampsResult(true, Status.DEFAULT, null, immutableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GoogleSourceStampsResult apkNotSigned(ImmutableList immutableList) {
        return new GoogleSourceStampsResult(false, Status.APK_NOT_SIGNED, "The APK that we checked the stamp on is not signed with a valid v2 or v3 signing certificate.", immutableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GoogleSourceStampsResult cannotVerify() {
        return new GoogleSourceStampsResult(false, Status.CANNOT_VERIFY, "Cannot verify the source stamp on the apk.", ImmutableList.of());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GoogleSourceStampsResult genericError(String str, ImmutableList immutableList) {
        return new GoogleSourceStampsResult(false, Status.GENERIC_ERROR, str, immutableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GoogleSourceStampsResult multipleSignersInvalid(ImmutableList immutableList) {
        return new GoogleSourceStampsResult(false, Status.MULTIPLE_SIGNERS_INVALID, "The APK that we checked the stamp on has more than one signer.", immutableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GoogleSourceStampsResult signingCertMismatch(ImmutableList immutableList) {
        return new GoogleSourceStampsResult(false, Status.SIGNING_CERT_MISMATCH, "The certificate that signed APK is different than the certificate provided by the platform.", immutableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GoogleSourceStampsResult unknownStamp(ImmutableList immutableList) {
        return new GoogleSourceStampsResult(false, Status.UNKNOWN_STAMP, "The APK is stamped with an unknown stamp.", immutableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GoogleSourceStampsResult versionTooLowStampNotChecked() {
        return new GoogleSourceStampsResult(true, Status.VERSION_TOO_LOW, null, ImmutableList.of());
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public Status getStatus() {
        return this.status;
    }

    public boolean isAllowed() {
        return this.allowed;
    }
}
