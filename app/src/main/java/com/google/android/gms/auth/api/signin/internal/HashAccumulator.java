package com.google.android.gms.auth.api.signin.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public class HashAccumulator {
    static int PRIME = 31;
    private int mHash = 1;

    public HashAccumulator addBoolean(boolean z) {
        this.mHash = (PRIME * this.mHash) + (z ? 1 : 0);
        return this;
    }

    public HashAccumulator addObject(Object obj) {
        this.mHash = (PRIME * this.mHash) + (obj == null ? 0 : obj.hashCode());
        return this;
    }

    public int hash() {
        return this.mHash;
    }
}
