package com.google.android.libraries.phenotype.client;

/* compiled from: PG */
/* loaded from: classes.dex */
final class PhenotypeContextTestMode {
    private static final Object LOCK = new Object();
    private static boolean testMode = false;
    private static volatile boolean testModeRead = false;
    private static volatile FirstFlagReadHere testModeReadStackTrace = null;
    private static volatile boolean contextRead = false;
    private static volatile FirstFlagReadHere contextReadStackTrace = null;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class FirstFlagReadHere extends Exception {
        private FirstFlagReadHere() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setContextRead() {
        contextRead = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setContextReadStackTraceIfNotSet() {
        if (contextReadStackTrace == null) {
            contextReadStackTrace = new FirstFlagReadHere();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setTestModeReadStackTraceIfNotSet() {
        if (testModeReadStackTrace == null) {
            testModeReadStackTrace = new FirstFlagReadHere();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean testMode() {
        boolean z;
        synchronized (LOCK) {
            z = testMode;
        }
        return z;
    }
}
