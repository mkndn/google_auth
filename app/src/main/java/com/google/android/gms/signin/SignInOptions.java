package com.google.android.gms.signin;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SignInOptions implements Api.ApiOptions {
    public static final SignInOptions DEFAULT = new Builder().build();
    private final Long authApiSignInModuleVersion;
    private final boolean forceCodeForRefreshToken;
    private final String hostedDomain;
    private final boolean idTokenRequested;
    private final String logSessionId;
    private final boolean offlineAccessRequested;
    private final Long realClientLibraryVersion;
    private final String serverClientId;
    private final boolean waitForAccessTokenRefresh;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private Long authApiSignInModuleVersion;
        private boolean forceCodeForRefreshToken;
        private String hostedDomain;
        private boolean idTokenRequested;
        private String logSessionId;
        private boolean offlineAccessRequested;
        private Long realClientLibraryVersion;
        private String serverClientId;
        private boolean waitForAccessTokenRefresh;

        public SignInOptions build() {
            return new SignInOptions(this.offlineAccessRequested, this.idTokenRequested, this.serverClientId, this.forceCodeForRefreshToken, this.hostedDomain, this.logSessionId, this.waitForAccessTokenRefresh, this.authApiSignInModuleVersion, this.realClientLibraryVersion);
        }
    }

    private SignInOptions(boolean z, boolean z2, String str, boolean z3, String str2, String str3, boolean z4, Long l, Long l2) {
        this.offlineAccessRequested = z;
        this.idTokenRequested = z2;
        this.serverClientId = str;
        this.forceCodeForRefreshToken = z3;
        this.waitForAccessTokenRefresh = z4;
        this.hostedDomain = str2;
        this.logSessionId = str3;
        this.authApiSignInModuleVersion = l;
        this.realClientLibraryVersion = l2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SignInOptions) {
            SignInOptions signInOptions = (SignInOptions) obj;
            return this.offlineAccessRequested == signInOptions.offlineAccessRequested && this.idTokenRequested == signInOptions.idTokenRequested && Objects.equal(this.serverClientId, signInOptions.serverClientId) && this.forceCodeForRefreshToken == signInOptions.forceCodeForRefreshToken && this.waitForAccessTokenRefresh == signInOptions.waitForAccessTokenRefresh && Objects.equal(this.hostedDomain, signInOptions.hostedDomain) && Objects.equal(this.logSessionId, signInOptions.logSessionId) && Objects.equal(this.authApiSignInModuleVersion, signInOptions.authApiSignInModuleVersion) && Objects.equal(this.realClientLibraryVersion, signInOptions.realClientLibraryVersion);
        }
        return false;
    }

    public Long getAuthApiSignInModuleVersion() {
        return this.authApiSignInModuleVersion;
    }

    public String getHostedDomain() {
        return this.hostedDomain;
    }

    public String getLogSessionId() {
        return this.logSessionId;
    }

    public Long getRealClientLibraryVersion() {
        return this.realClientLibraryVersion;
    }

    public String getServerClientId() {
        return this.serverClientId;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.offlineAccessRequested), Boolean.valueOf(this.idTokenRequested), this.serverClientId, Boolean.valueOf(this.forceCodeForRefreshToken), Boolean.valueOf(this.waitForAccessTokenRefresh), this.hostedDomain, this.logSessionId, this.authApiSignInModuleVersion, this.realClientLibraryVersion);
    }

    public boolean isForceCodeForRefreshToken() {
        return this.forceCodeForRefreshToken;
    }

    public boolean isIdTokenRequested() {
        return this.idTokenRequested;
    }

    public boolean isOfflineAccessRequested() {
        return this.offlineAccessRequested;
    }

    public boolean waitForAccessTokenRefresh() {
        return this.waitForAccessTokenRefresh;
    }
}
