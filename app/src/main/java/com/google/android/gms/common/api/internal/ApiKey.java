package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApiKey {
    private final Api mApi;
    private final Api.ApiOptions mApiOptions;
    private final String mAttributionTag;
    private final int mHashCode;
    private final boolean mIsUnique = false;

    private ApiKey(Api api, Api.ApiOptions apiOptions, String str) {
        this.mApi = api;
        this.mApiOptions = apiOptions;
        this.mAttributionTag = str;
        this.mHashCode = Objects.hashCode(api, apiOptions, str);
    }

    public static ApiKey getSharedApiKey(Api api, Api.ApiOptions apiOptions, String str) {
        return new ApiKey(api, apiOptions, str);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApiKey)) {
            return false;
        }
        ApiKey apiKey = (ApiKey) obj;
        if (this.mIsUnique || apiKey.mIsUnique || !Objects.equal(this.mApi, apiKey.mApi) || !Objects.equal(this.mApiOptions, apiKey.mApiOptions) || !Objects.equal(this.mAttributionTag, apiKey.mAttributionTag)) {
            return false;
        }
        return true;
    }

    public String getApiName() {
        return this.mApi.getName();
    }

    public int hashCode() {
        return this.mHashCode;
    }
}
