package com.google.android.gms.auth;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TokenData extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new TokenDataCreator();
    private final Long mExpirationTimeSecs;
    private final List mGrantedScopes;
    private final boolean mIsCached;
    private final boolean mIsSnowballed;
    private final String mToken;
    final int mVersionCode;
    private final String scopeData;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TokenData(int i, String str, Long l, boolean z, boolean z2, List list, String str2) {
        this.mVersionCode = i;
        this.mToken = Preconditions.checkNotEmpty(str);
        this.mExpirationTimeSecs = l;
        this.mIsCached = z;
        this.mIsSnowballed = z2;
        this.mGrantedScopes = list;
        this.scopeData = str2;
    }

    public static TokenData fromWrappedBundle(Bundle bundle, String str) {
        ClassLoader classLoader = TokenData.class.getClassLoader();
        if (classLoader != null) {
            bundle.setClassLoader(classLoader);
        }
        Bundle bundle2 = bundle.getBundle(str);
        if (bundle2 == null) {
            return null;
        }
        if (classLoader != null) {
            bundle2.setClassLoader(classLoader);
        }
        return (TokenData) bundle2.getParcelable("TokenData");
    }

    public boolean equals(Object obj) {
        if (obj instanceof TokenData) {
            TokenData tokenData = (TokenData) obj;
            return TextUtils.equals(this.mToken, tokenData.mToken) && Objects.equal(this.mExpirationTimeSecs, tokenData.mExpirationTimeSecs) && this.mIsCached == tokenData.mIsCached && this.mIsSnowballed == tokenData.mIsSnowballed && Objects.equal(this.mGrantedScopes, tokenData.mGrantedScopes) && Objects.equal(this.scopeData, tokenData.scopeData);
        }
        return false;
    }

    public Long getExpirationTimeSecs() {
        return this.mExpirationTimeSecs;
    }

    public List getGrantedScopes() {
        return this.mGrantedScopes;
    }

    public String getScopeData() {
        return this.scopeData;
    }

    public String getToken() {
        return this.mToken;
    }

    public int hashCode() {
        return Objects.hashCode(this.mToken, this.mExpirationTimeSecs, Boolean.valueOf(this.mIsCached), Boolean.valueOf(this.mIsSnowballed), this.mGrantedScopes, this.scopeData);
    }

    public boolean isCached() {
        return this.mIsCached;
    }

    public boolean isSnowballed() {
        return this.mIsSnowballed;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TokenDataCreator.writeToParcel(this, parcel, i);
    }
}
