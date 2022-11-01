package com.google.android.apps.authenticator.timesync;

import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.inject.Inject;

/* compiled from: PG */
/* loaded from: classes.dex */
public class NetworkTimeProvider {
    private static final String DATE_HEADER = "Date";
    private static final String LOG_TAG;
    static final URL TIME_SOURCE_URL;
    private final HttpURLConnectionFactory mUrlConnectionFactory;

    static {
        try {
            TIME_SOURCE_URL = new URL("https://www.google.com");
            LOG_TAG = NetworkTimeProvider.class.getSimpleName();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Inject
    public NetworkTimeProvider(HttpURLConnectionFactory httpURLConnectionFactory) {
        this.mUrlConnectionFactory = httpURLConnectionFactory;
    }

    public long getNetworkTime() {
        HttpURLConnectionFactory httpURLConnectionFactory = this.mUrlConnectionFactory;
        URL url = TIME_SOURCE_URL;
        HttpURLConnection openHttpUrl = httpURLConnectionFactory.openHttpUrl(url);
        openHttpUrl.setRequestMethod("HEAD");
        int responseCode = openHttpUrl.getResponseCode();
        if (responseCode != 200) {
            Log.d(LOG_TAG, String.format("URL %s returned %d", url, Integer.valueOf(responseCode)));
            throw new IOException(String.format("HTTP status code %d", Integer.valueOf(responseCode)));
        }
        long headerFieldDate = openHttpUrl.getHeaderFieldDate(DATE_HEADER, 0L);
        if (headerFieldDate == 0) {
            throw new IOException("Got missing or invalid date from header value " + openHttpUrl.getHeaderField(DATE_HEADER));
        }
        Log.d(LOG_TAG, String.format("Got date value %d", Long.valueOf(headerFieldDate)));
        return headerFieldDate;
    }
}
