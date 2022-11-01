package com.google.android.gms.auth.api;

import android.os.Bundle;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AuthProxyOptions implements Api.ApiOptions {
    public static final AuthProxyOptions EMPTY_OPTION = new Builder().build();
    private final Bundle mAuthOptions;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder {
        private final Bundle mAuthOptions = new Bundle();

        public AuthProxyOptions build() {
            return new AuthProxyOptions(this.mAuthOptions);
        }
    }

    private AuthProxyOptions(Bundle bundle) {
        this.mAuthOptions = bundle;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AuthProxyOptions) {
            return Objects.checkBundlesEquality(this.mAuthOptions, ((AuthProxyOptions) obj).mAuthOptions);
        }
        return false;
    }

    public Bundle getAuthenticationOptions() {
        return new Bundle(this.mAuthOptions);
    }

    public int hashCode() {
        return Objects.hashCode(this.mAuthOptions);
    }
}
