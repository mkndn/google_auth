package com.google.android.libraries.phenotype.client.api;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeRuntimeException extends RuntimeException {
    public static final int ERROR_ALREADY_REGISTERED = 29506;
    public static final int ERROR_API_NOT_SUPPORTED_IN_DIRECT_BOOT = 29508;
    public static final int ERROR_GET_PSEUDONYMOUS_ID_FAILED = 29509;
    public static final int ERROR_INVALID_ARGUMENT = 29500;
    public static final int ERROR_NO_EXPERIMENT_TOKENS = 29505;
    public static final int ERROR_PACKAGE_NOT_REGISTERED = 29503;
    public static final int ERROR_PARTIAL_REGISTRATION_SUCCESS = 29507;
    public static final int ERROR_SET_PSEUDONYMOUS_ID_FAILED = 29510;
    public static final int ERROR_STALE_SNAPSHOT_TOKEN = 29501;
    public static final int ERROR_SYNC_FAILED = 29504;
    private final int errorCode;
    private final String errorMessage;

    public int getErrorCode() {
        return this.errorCode;
    }

    public PhenotypeRuntimeException(int i, String str, Throwable th) {
        super(str != null ? i + ": " + str : String.valueOf(i), th);
        this.errorCode = i;
        this.errorMessage = str;
    }
}
