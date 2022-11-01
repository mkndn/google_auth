package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.apps.authenticator.otp.AccountDb;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleSignInOptions extends AbstractSafeParcelable implements Api.ApiOptions, ReflectedParcelable {
    public static final Parcelable.Creator CREATOR;
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
    public static final GoogleSignInOptions DEFAULT_SIGN_IN;
    public static final Scope SCOPE_GAMES;
    public static final Scope SCOPE_GAMES_LITE;
    private static Comparator SCOPE_ORDER;
    private Account mAccount;
    private ArrayList mExtensions;
    private Map mExtensionsMap;
    private final boolean mForceCodeForRefreshToken;
    private String mHostedDomain;
    private boolean mIdTokenRequested;
    private String mLogSessionId;
    private final ArrayList mScopes;
    private final boolean mServerAuthCodeRequested;
    private String mServerClientId;
    final int versionCode;
    public static final Scope SCOPE_PROFILE = new Scope("profile");
    public static final Scope SCOPE_EMAIL = new Scope(AccountDb.NAME_COLUMN);
    public static final Scope SCOPE_OPEN_ID = new Scope("openid");

    static {
        Scope scope = new Scope("https://www.googleapis.com/auth/games_lite");
        SCOPE_GAMES_LITE = scope;
        SCOPE_GAMES = new Scope("https://www.googleapis.com/auth/games");
        DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
        DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(scope, new Scope[0]).build();
        CREATOR = new GoogleSignInOptionsCreator();
        SCOPE_ORDER = new Comparator() { // from class: com.google.android.gms.auth.api.signin.GoogleSignInOptions.1
            @Override // java.util.Comparator
            public int compare(Scope scope2, Scope scope3) {
                return scope2.getScopeUri().compareTo(scope3.getScopeUri());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map convertExtensionsListToMap(List list) {
        HashMap hashMap = new HashMap();
        if (list == null) {
            return hashMap;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            GoogleSignInOptionsExtensionParcelable googleSignInOptionsExtensionParcelable = (GoogleSignInOptionsExtensionParcelable) it.next();
            hashMap.put(Integer.valueOf(googleSignInOptionsExtensionParcelable.getType()), googleSignInOptionsExtensionParcelable);
        }
        return hashMap;
    }

    public static GoogleSignInOptions fromJsonString(String str) {
        Account account;
        String str2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String optString = jSONObject.has("accountName") ? jSONObject.optString("accountName") : null;
        if (!TextUtils.isEmpty(optString)) {
            account = new Account(optString, "com.google");
        } else {
            account = null;
        }
        ArrayList arrayList = new ArrayList(hashSet);
        boolean z = jSONObject.getBoolean("idTokenRequested");
        boolean z2 = jSONObject.getBoolean("serverAuthRequested");
        boolean z3 = jSONObject.getBoolean("forceCodeForRefreshToken");
        if (jSONObject.has("serverClientId")) {
            str2 = jSONObject.optString("serverClientId");
        } else {
            str2 = null;
        }
        return new GoogleSignInOptions(3, arrayList, account, z, z2, z3, str2, jSONObject.has("hostedDomain") ? jSONObject.optString("hostedDomain") : null, new HashMap(), (String) null);
    }

    private JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.mScopes, SCOPE_ORDER);
            Iterator it = this.mScopes.iterator();
            while (it.hasNext()) {
                jSONArray.put(((Scope) it.next()).getScopeUri());
            }
            jSONObject.put("scopes", jSONArray);
            Account account = this.mAccount;
            if (account != null) {
                jSONObject.put("accountName", account.name);
            }
            jSONObject.put("idTokenRequested", this.mIdTokenRequested);
            jSONObject.put("forceCodeForRefreshToken", this.mForceCodeForRefreshToken);
            jSONObject.put("serverAuthRequested", this.mServerAuthCodeRequested);
            if (!TextUtils.isEmpty(this.mServerClientId)) {
                jSONObject.put("serverClientId", this.mServerClientId);
            }
            if (!TextUtils.isEmpty(this.mHostedDomain)) {
                jSONObject.put("hostedDomain", this.mHostedDomain);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.mExtensions.size() <= 0 && googleSignInOptions.mExtensions.size() <= 0 && this.mScopes.size() == googleSignInOptions.getScopes().size() && this.mScopes.containsAll(googleSignInOptions.getScopes())) {
                Account account = this.mAccount;
                if (account == null) {
                    if (googleSignInOptions.getAccount() != null) {
                        return false;
                    }
                } else if (!account.equals(googleSignInOptions.getAccount())) {
                    return false;
                }
                if (TextUtils.isEmpty(this.mServerClientId)) {
                    if (!TextUtils.isEmpty(googleSignInOptions.getServerClientId())) {
                        return false;
                    }
                } else if (!this.mServerClientId.equals(googleSignInOptions.getServerClientId())) {
                    return false;
                }
                if (this.mForceCodeForRefreshToken == googleSignInOptions.isForceCodeForRefreshToken() && this.mIdTokenRequested == googleSignInOptions.isIdTokenRequested() && this.mServerAuthCodeRequested == googleSignInOptions.isServerAuthCodeRequested()) {
                    return TextUtils.equals(this.mLogSessionId, googleSignInOptions.getLogSessionId());
                }
                return false;
            }
            return false;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public Account getAccount() {
        return this.mAccount;
    }

    public ArrayList getExtensions() {
        return this.mExtensions;
    }

    public String getHostedDomain() {
        return this.mHostedDomain;
    }

    public String getLogSessionId() {
        return this.mLogSessionId;
    }

    public ArrayList getScopes() {
        return new ArrayList(this.mScopes);
    }

    public String getServerClientId() {
        return this.mServerClientId;
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.mScopes.iterator();
        while (it.hasNext()) {
            arrayList.add(((Scope) it.next()).getScopeUri());
        }
        Collections.sort(arrayList);
        return new HashAccumulator().addObject(arrayList).addObject(this.mAccount).addObject(this.mServerClientId).addBoolean(this.mForceCodeForRefreshToken).addBoolean(this.mIdTokenRequested).addBoolean(this.mServerAuthCodeRequested).addObject(this.mLogSessionId).hash();
    }

    public boolean isForceCodeForRefreshToken() {
        return this.mForceCodeForRefreshToken;
    }

    public boolean isIdTokenRequested() {
        return this.mIdTokenRequested;
    }

    public boolean isServerAuthCodeRequested() {
        return this.mServerAuthCodeRequested;
    }

    public String toJson() {
        return toJsonObject().toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        GoogleSignInOptionsCreator.writeToParcel(this, parcel, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleSignInOptions(int i, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, ArrayList arrayList2, String str3) {
        this(i, arrayList, account, z, z2, z3, str, str2, convertExtensionsListToMap(arrayList2), str3);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private Account mAccount;
        private Map mExtensions;
        private boolean mForceCodeForRefreshToken;
        private String mHostedDomain;
        private boolean mIdTokenRequested;
        private String mLogSessionId;
        private Set mScopes;
        private boolean mServerAuthCodeRequested;
        private String mServerClientId;

        public Builder() {
            this.mScopes = new HashSet();
            this.mExtensions = new HashMap();
        }

        public GoogleSignInOptions build() {
            if (this.mScopes.contains(GoogleSignInOptions.SCOPE_GAMES) && this.mScopes.contains(GoogleSignInOptions.SCOPE_GAMES_LITE)) {
                this.mScopes.remove(GoogleSignInOptions.SCOPE_GAMES_LITE);
            }
            if (this.mIdTokenRequested && (this.mAccount == null || !this.mScopes.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(3, new ArrayList(this.mScopes), this.mAccount, this.mIdTokenRequested, this.mServerAuthCodeRequested, this.mForceCodeForRefreshToken, this.mServerClientId, this.mHostedDomain, this.mExtensions, this.mLogSessionId);
        }

        public Builder requestId() {
            this.mScopes.add(GoogleSignInOptions.SCOPE_OPEN_ID);
            return this;
        }

        public Builder requestProfile() {
            this.mScopes.add(GoogleSignInOptions.SCOPE_PROFILE);
            return this;
        }

        public Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.mScopes.add(scope);
            this.mScopes.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public Builder setLogSessionId(String str) {
            this.mLogSessionId = str;
            return this;
        }

        public Builder(GoogleSignInOptions googleSignInOptions) {
            this.mScopes = new HashSet();
            this.mExtensions = new HashMap();
            Preconditions.checkNotNull(googleSignInOptions);
            this.mScopes = new HashSet(googleSignInOptions.mScopes);
            this.mServerAuthCodeRequested = googleSignInOptions.mServerAuthCodeRequested;
            this.mForceCodeForRefreshToken = googleSignInOptions.mForceCodeForRefreshToken;
            this.mIdTokenRequested = googleSignInOptions.mIdTokenRequested;
            this.mServerClientId = googleSignInOptions.mServerClientId;
            this.mAccount = googleSignInOptions.mAccount;
            this.mHostedDomain = googleSignInOptions.mHostedDomain;
            this.mExtensions = GoogleSignInOptions.convertExtensionsListToMap(googleSignInOptions.mExtensions);
            this.mLogSessionId = googleSignInOptions.mLogSessionId;
        }
    }

    private GoogleSignInOptions(int i, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map map, String str3) {
        this.versionCode = i;
        this.mScopes = arrayList;
        this.mAccount = account;
        this.mIdTokenRequested = z;
        this.mServerAuthCodeRequested = z2;
        this.mForceCodeForRefreshToken = z3;
        this.mServerClientId = str;
        this.mHostedDomain = str2;
        this.mExtensions = new ArrayList(map.values());
        this.mExtensionsMap = map;
        this.mLogSessionId = str3;
    }
}
