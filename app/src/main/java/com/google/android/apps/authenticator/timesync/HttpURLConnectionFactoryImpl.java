package com.google.android.apps.authenticator.timesync;

import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: PG */
/* loaded from: classes.dex */
public class HttpURLConnectionFactoryImpl implements HttpURLConnectionFactory {
    @Override // com.google.android.apps.authenticator.timesync.HttpURLConnectionFactory
    public HttpURLConnection openHttpUrl(URL url) {
        return (HttpURLConnection) url.openConnection();
    }
}
