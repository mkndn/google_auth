package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.view.View;
import androidx.collection.ArraySet;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.signin.SignInOptions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClientSettings {
    private final Account account;
    private final Set allRequestedScopes;
    private final int gravityForPopups;
    private Integer mSessionId;
    private final Map optionalApiSettingsMap;
    private final String realClientClassName;
    private final String realClientPackageName;
    private final Set requiredScopes;
    private final boolean signInClientDisconnectFixEnabled;
    private final SignInOptions signInOptions;
    private final View viewForPopups;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private Account account;
        private boolean isSignInClientDisconnectFixEnabled;
        private Map optionalApiSettingsMap;
        private String realClientClassName;
        private String realClientPackageName;
        private ArraySet requiredScopes;
        private View viewForPopups;
        private int gravityForPopups = 0;
        private SignInOptions signInOptions = SignInOptions.DEFAULT;

        public Builder addAllRequiredScopes(Collection collection) {
            if (this.requiredScopes == null) {
                this.requiredScopes = new ArraySet();
            }
            this.requiredScopes.addAll(collection);
            return this;
        }

        public ClientSettings build() {
            return new ClientSettings(this.account, this.requiredScopes, this.optionalApiSettingsMap, this.gravityForPopups, this.viewForPopups, this.realClientPackageName, this.realClientClassName, this.signInOptions, this.isSignInClientDisconnectFixEnabled);
        }

        public Builder setAccount(Account account) {
            this.account = account;
            return this;
        }

        public Builder setRealClientClassName(String str) {
            this.realClientClassName = str;
            return this;
        }

        public Builder setRealClientPackageName(String str) {
            this.realClientPackageName = str;
            return this;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OptionalApiSettings {
        public final Set mScopes;
    }

    public ClientSettings(Account account, Set set, Map map, int i, View view, String str, String str2, SignInOptions signInOptions, boolean z) {
        this.account = account;
        Set emptySet = set == null ? Collections.emptySet() : Collections.unmodifiableSet(set);
        this.requiredScopes = emptySet;
        map = map == null ? Collections.emptyMap() : map;
        this.optionalApiSettingsMap = map;
        this.viewForPopups = view;
        this.gravityForPopups = i;
        this.realClientPackageName = str;
        this.realClientClassName = str2;
        this.signInOptions = signInOptions == null ? SignInOptions.DEFAULT : signInOptions;
        this.signInClientDisconnectFixEnabled = z;
        HashSet hashSet = new HashSet(emptySet);
        for (OptionalApiSettings optionalApiSettings : map.values()) {
            hashSet.addAll(optionalApiSettings.mScopes);
        }
        this.allRequestedScopes = Collections.unmodifiableSet(hashSet);
    }

    public Account getAccount() {
        return this.account;
    }

    @Deprecated
    public String getAccountName() {
        Account account = this.account;
        if (account != null) {
            return account.name;
        }
        return null;
    }

    public Account getAccountOrDefault() {
        Account account = this.account;
        return account != null ? account : new Account("<<default account>>", "com.google");
    }

    public Set getAllRequestedScopes() {
        return this.allRequestedScopes;
    }

    public Set getApplicableScopes(Api api) {
        OptionalApiSettings optionalApiSettings = (OptionalApiSettings) this.optionalApiSettingsMap.get(api);
        if (optionalApiSettings != null && !optionalApiSettings.mScopes.isEmpty()) {
            HashSet hashSet = new HashSet(this.requiredScopes);
            hashSet.addAll(optionalApiSettings.mScopes);
            return hashSet;
        }
        return this.requiredScopes;
    }

    public Integer getClientSessionId() {
        return this.mSessionId;
    }

    public String getRealClientClassName() {
        return this.realClientClassName;
    }

    public String getRealClientPackageName() {
        return this.realClientPackageName;
    }

    public Set getRequiredScopes() {
        return this.requiredScopes;
    }

    public SignInOptions getSignInOptions() {
        return this.signInOptions;
    }

    public void setClientSessionId(Integer num) {
        this.mSessionId = num;
    }
}
