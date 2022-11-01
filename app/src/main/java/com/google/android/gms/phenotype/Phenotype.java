package com.google.android.gms.phenotype;

import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.phenotype.internal.PhenotypeApiImpl;
import com.google.android.gms.phenotype.internal.PhenotypeClientImpl;
import com.google.android.libraries.phenotype.client.PhenotypeConstants;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Phenotype {
    @Deprecated
    public static final Api API;
    private static final Api.AbstractClientBuilder CLIENT_BUILDER;
    private static final Api.ClientKey CLIENT_KEY;
    @Deprecated
    public static final PhenotypeApi PhenotypeApi;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        CLIENT_KEY = clientKey;
        Api.AbstractClientBuilder abstractClientBuilder = new Api.AbstractClientBuilder() { // from class: com.google.android.gms.phenotype.Phenotype.1
            @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
            public PhenotypeClientImpl buildClient(Context context, Looper looper, ClientSettings clientSettings, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new PhenotypeClientImpl(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
            }
        };
        CLIENT_BUILDER = abstractClientBuilder;
        API = new Api("Phenotype.API", abstractClientBuilder, clientKey);
        PhenotypeApi = new PhenotypeApiImpl();
    }

    public static Uri getContentProviderUri(String str) {
        return PhenotypeConstants.getContentProviderUri(str);
    }

    public static PhenotypeClient getInstance(Context context) {
        return new PhenotypeClient(context);
    }
}
