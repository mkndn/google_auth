package com.google.android.apps.authenticator.timesync;

import javax.inject.Singleton;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TimeSyncModule {
    public HttpURLConnectionFactory providesHttpURLConnectionFactory() {
        return new HttpURLConnectionFactoryImpl();
    }

    @Singleton
    public NetworkTimeProvider providesNetworkTimeProvider(HttpURLConnectionFactory httpURLConnectionFactory) {
        return new NetworkTimeProvider(httpURLConnectionFactory);
    }
}
