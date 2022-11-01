package com.google.android.apps.authenticator.otp;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InvalidAccountException extends Exception {
    public final ErrorType errorType;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum ErrorType {
        INVALID_OR_MISSING_SCHEME(15),
        INVALID_OR_MISSING_COUNTER(15),
        INVALID_OR_MISSING_AUTHORITY(15),
        INVALID_OR_MISSING_SECRET(16),
        MISSING_USER(15),
        UNSUPPORTED_ALGORITHM(21),
        UNSUPPORTED_DIGIT_COUNT(21);
        
        public final int explanationDialogId;

        ErrorType(int i) {
            this.explanationDialogId = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InvalidAccountException(ErrorType errorType) {
        super("Failed to add account: " + errorType.name());
        this.errorType = errorType;
    }
}
