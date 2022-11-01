package com.google.android.gms.libs.punchclock.network;

import java.net.URL;
import java.net.URLConnection;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class HttpURLConnectionFactory {
    private static HttpURLConnectionFactory instance = new DefaultHttpURLConnectionFactory();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class DefaultHttpURLConnectionFactory extends HttpURLConnectionFactory {
        private DefaultHttpURLConnectionFactory() {
        }

        @Override // com.google.android.gms.libs.punchclock.network.HttpURLConnectionFactory
        public URLConnection openConnection(URL url, String str) {
            return url.openConnection();
        }
    }

    public static synchronized HttpURLConnectionFactory getInstance() {
        HttpURLConnectionFactory httpURLConnectionFactory;
        synchronized (HttpURLConnectionFactory.class) {
            httpURLConnectionFactory = instance;
        }
        return httpURLConnectionFactory;
    }

    public abstract URLConnection openConnection(URL url, String str);
}
