package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new ScopeCreator();
    private final String mScopeUri;
    final int mVersionCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Scope(int i, String str) {
        Preconditions.checkNotEmpty(str, "scopeUri must not be null or empty");
        this.mVersionCode = i;
        this.mScopeUri = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Scope) {
            return this.mScopeUri.equals(((Scope) obj).mScopeUri);
        }
        return false;
    }

    public String getScopeUri() {
        return this.mScopeUri;
    }

    public int hashCode() {
        return this.mScopeUri.hashCode();
    }

    public String toString() {
        return this.mScopeUri;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ScopeCreator.writeToParcel(this, parcel, i);
    }

    public Scope(String str) {
        this(1, str);
    }
}
