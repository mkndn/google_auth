package com.google.android.apps.authenticator.migration;

/* compiled from: PG */
/* loaded from: classes.dex */
public class UnsupportedMigrationVersionException extends Exception {
    static final int MAX_KNOWN_VERSION = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnsupportedMigrationVersionException(int i) {
        super(String.format("Unsupported migration payload version: found %d, max supported version %d", Integer.valueOf(i), 1));
    }
}
