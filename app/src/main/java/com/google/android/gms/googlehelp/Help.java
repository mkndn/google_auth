package com.google.android.gms.googlehelp;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.googlehelp.internal.common.GoogleHelpClient;
import com.google.android.gms.googlehelp.internal.common.GoogleHelpClientImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Help {
    public static final Api API;
    public static final Api.AbstractClientBuilder CLIENT_BUILDER;
    public static final Api.ClientKey CLIENT_KEY;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        CLIENT_KEY = clientKey;
        Api.AbstractClientBuilder abstractClientBuilder = new Api.AbstractClientBuilder() { // from class: com.google.android.gms.googlehelp.Help.1
            @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
            public GoogleHelpClientImpl buildClient(Context context, Looper looper, ClientSettings clientSettings, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new GoogleHelpClientImpl(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
            }
        };
        CLIENT_BUILDER = abstractClientBuilder;
        API = new Api("Help.API", abstractClientBuilder, clientKey);
    }

    public static GoogleHelpClient getClient(Activity activity) {
        return new GoogleHelpClient(activity);
    }

    public static GoogleHelpClient getClient(Context context) {
        return new GoogleHelpClient(context);
    }
}
