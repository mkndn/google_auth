package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.apps.authenticator.otp.AccountDb;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new GoogleSignInAccountCreator();
    public static Clock sClock = DefaultClock.getInstance();
    private String mDisplayName;
    private String mEmail;
    private long mExpirationTimeSecs;
    private Set mExtraRequestedScopes = new HashSet();
    private String mFamilyName;
    private String mGivenName;
    List mGrantedScopes;
    private String mId;
    private String mIdToken;
    private String mObfuscatedIdentifier;
    private Uri mPhotoUrl;
    private String mServerAuthCode;
    final int versionCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleSignInAccount(int i, String str, String str2, String str3, String str4, Uri uri, String str5, long j, String str6, List list, String str7, String str8) {
        this.versionCode = i;
        this.mId = str;
        this.mIdToken = str2;
        this.mEmail = str3;
        this.mDisplayName = str4;
        this.mPhotoUrl = uri;
        this.mServerAuthCode = str5;
        this.mExpirationTimeSecs = j;
        this.mObfuscatedIdentifier = str6;
        this.mGrantedScopes = list;
        this.mGivenName = str7;
        this.mFamilyName = str8;
    }

    public static GoogleSignInAccount create(String str, String str2, String str3, String str4, String str5, String str6, Uri uri, Long l, String str7, Set set) {
        Long l2;
        if (l != null) {
            l2 = l;
        } else {
            l2 = Long.valueOf(sClock.currentTimeMillis() / 1000);
        }
        return new GoogleSignInAccount(3, str, str2, str3, str4, uri, null, l2.longValue(), Preconditions.checkNotEmpty(str7), new ArrayList((Collection) Preconditions.checkNotNull(set)), str5, str6);
    }

    public static GoogleSignInAccount fromJsonString(String str) {
        Uri uri;
        String str2;
        String str3;
        String str4;
        String str5;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("photoUrl");
        if (!TextUtils.isEmpty(optString)) {
            uri = Uri.parse(optString);
        } else {
            uri = null;
        }
        long parseLong = Long.parseLong(jSONObject.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String optString2 = jSONObject.optString("id");
        if (jSONObject.has("tokenId")) {
            str2 = jSONObject.optString("tokenId");
        } else {
            str2 = null;
        }
        if (jSONObject.has(AccountDb.NAME_COLUMN)) {
            str3 = jSONObject.optString(AccountDb.NAME_COLUMN);
        } else {
            str3 = null;
        }
        if (jSONObject.has("displayName")) {
            str4 = jSONObject.optString("displayName");
        } else {
            str4 = null;
        }
        if (jSONObject.has("givenName")) {
            str5 = jSONObject.optString("givenName");
        } else {
            str5 = null;
        }
        return create(optString2, str2, str3, str4, str5, jSONObject.has("familyName") ? jSONObject.optString("familyName") : null, uri, Long.valueOf(parseLong), jSONObject.getString("obfuscatedIdentifier"), hashSet).setServerAuthCode(jSONObject.has("serverAuthCode") ? jSONObject.optString("serverAuthCode") : null);
    }

    private JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (getId() != null) {
                jSONObject.put("id", getId());
            }
            if (getIdToken() != null) {
                jSONObject.put("tokenId", getIdToken());
            }
            if (getEmail() != null) {
                jSONObject.put(AccountDb.NAME_COLUMN, getEmail());
            }
            if (getDisplayName() != null) {
                jSONObject.put("displayName", getDisplayName());
            }
            if (getGivenName() != null) {
                jSONObject.put("givenName", getGivenName());
            }
            if (getFamilyName() != null) {
                jSONObject.put("familyName", getFamilyName());
            }
            Uri photoUrl = getPhotoUrl();
            if (photoUrl != null) {
                jSONObject.put("photoUrl", photoUrl.toString());
            }
            if (getServerAuthCode() != null) {
                jSONObject.put("serverAuthCode", getServerAuthCode());
            }
            jSONObject.put("expirationTime", this.mExpirationTimeSecs);
            jSONObject.put("obfuscatedIdentifier", getObfuscatedIdentifier());
            JSONArray jSONArray = new JSONArray();
            List list = this.mGrantedScopes;
            Scope[] scopeArr = (Scope[]) list.toArray(new Scope[list.size()]);
            Arrays.sort(scopeArr, GoogleSignInAccount$$ExternalSyntheticLambda0.INSTANCE);
            for (Scope scope : scopeArr) {
                jSONArray.put(scope.getScopeUri());
            }
            jSONObject.put("grantedScopes", jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleSignInAccount)) {
            return false;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
        if (!googleSignInAccount.getObfuscatedIdentifier().equals(getObfuscatedIdentifier()) || !googleSignInAccount.getRequestedScopes().equals(getRequestedScopes())) {
            return false;
        }
        return true;
    }

    public Account getAccount() {
        if (this.mEmail == null) {
            return null;
        }
        return new Account(this.mEmail, "com.google");
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public long getExpirationTimeSecs() {
        return this.mExpirationTimeSecs;
    }

    public String getFamilyName() {
        return this.mFamilyName;
    }

    public String getGivenName() {
        return this.mGivenName;
    }

    public String getId() {
        return this.mId;
    }

    public String getIdToken() {
        return this.mIdToken;
    }

    public String getObfuscatedIdentifier() {
        return this.mObfuscatedIdentifier;
    }

    public Uri getPhotoUrl() {
        return this.mPhotoUrl;
    }

    public Set getRequestedScopes() {
        HashSet hashSet = new HashSet(this.mGrantedScopes);
        hashSet.addAll(this.mExtraRequestedScopes);
        return hashSet;
    }

    public String getServerAuthCode() {
        return this.mServerAuthCode;
    }

    public GoogleSignInAccount setServerAuthCode(String str) {
        this.mServerAuthCode = str;
        return this;
    }

    public String toJsonForStorage() {
        JSONObject jsonObject = toJsonObject();
        jsonObject.remove("serverAuthCode");
        return jsonObject.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        GoogleSignInAccountCreator.writeToParcel(this, parcel, i);
    }

    public int hashCode() {
        return ((getObfuscatedIdentifier().hashCode() + 527) * 31) + getRequestedScopes().hashCode();
    }
}
