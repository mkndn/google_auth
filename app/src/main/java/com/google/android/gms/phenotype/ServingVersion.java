package com.google.android.gms.phenotype;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ServingVersion {
    private final long servingVersion;

    private ServingVersion(long j) {
        this.servingVersion = j;
    }

    public static ServingVersion fromServer(long j) {
        return new ServingVersion(j);
    }
}
