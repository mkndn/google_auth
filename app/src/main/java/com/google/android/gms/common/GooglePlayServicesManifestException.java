package com.google.android.gms.common;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GooglePlayServicesManifestException extends IllegalStateException {
    private final int actualVersion;

    public GooglePlayServicesManifestException(int i, String str) {
        super(str);
        this.actualVersion = i;
    }
}
