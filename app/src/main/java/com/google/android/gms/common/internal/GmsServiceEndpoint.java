package com.google.android.gms.common.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GmsServiceEndpoint {
    private final int bindFlags;
    private final boolean localService;
    private final String packageName;
    private final String startAction;
    private final boolean useDynamicLookup;

    public GmsServiceEndpoint(String str, String str2, boolean z, int i, boolean z2) {
        this.packageName = str;
        this.startAction = str2;
        this.localService = z;
        this.bindFlags = i;
        this.useDynamicLookup = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBindFlags() {
        return this.bindFlags;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getStartAction() {
        return this.startAction;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getUseDynamicLookup() {
        return this.useDynamicLookup;
    }
}
