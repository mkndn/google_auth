package com.google.android.gms.phenotype;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeCore {
    public static final int ERROR_ALREADY_REGISTERED = 29506;
    public static final int ERROR_API_NOT_SUPPORTED_IN_DIRECT_BOOT = 29508;
    public static final int ERROR_CONFIGURATION_SNAPSHOT_TOO_LARGE = 29513;
    public static final int ERROR_FEATURE_NOT_ENABLED = 29514;
    public static final int ERROR_FILE_IO = 29511;
    public static final int ERROR_FLAG_CORRUPTION = 29512;
    public static final int ERROR_GET_PSEUDONYMOUS_ID_FAILED = 29509;
    public static final int ERROR_INVALID_ARGUMENT = 29500;
    public static final int ERROR_NO_EXPERIMENT_TOKENS = 29505;
    public static final int ERROR_PACKAGE_NOT_REGISTERED = 29503;
    public static final int ERROR_PARTIAL_REGISTRATION_SUCCESS = 29507;
    public static final int ERROR_SET_PSEUDONYMOUS_ID_FAILED = 29510;
    public static final int ERROR_STALE_SNAPSHOT_TOKEN = 29501;
    public static final int ERROR_SYNC_FAILED = 29504;
    public static final int OVERRIDE_COMMIT_STORAGE_TYPE = -1000;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static String getSnapshotTokenForCommitCurrent(String str, String str2) {
        return "CURRENT:" + str2 + ":" + str;
    }
}
