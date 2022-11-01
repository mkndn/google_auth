package com.google.android.libraries.phenotype.client;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.libraries.phenotype.client.FlagLoader;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationContentLoader implements FlagLoader {
    private final Object cacheLock;
    private volatile Map cachedFlags;
    private final List listeners;
    private final ContentObserver observer;
    private final Runnable processCacheInvalidater;
    private final ContentResolver resolver;
    private final Uri uri;
    private static final Map loadersByUri = new ArrayMap();
    public static final String[] COLUMNS = {"key", "value"};

    private ConfigurationContentLoader(ContentResolver contentResolver, Uri uri, Runnable runnable) {
        ContentObserver contentObserver = new ContentObserver(null) { // from class: com.google.android.libraries.phenotype.client.ConfigurationContentLoader.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                ConfigurationContentLoader.this.invalidateCache();
            }
        };
        this.observer = contentObserver;
        this.cacheLock = new Object();
        this.listeners = new ArrayList();
        Preconditions.checkNotNull(contentResolver);
        Preconditions.checkNotNull(uri);
        this.resolver = contentResolver;
        this.uri = uri;
        this.processCacheInvalidater = runnable;
        contentResolver.registerContentObserver(uri, false, contentObserver);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void clearLoaderMap() {
        synchronized (ConfigurationContentLoader.class) {
            for (ConfigurationContentLoader configurationContentLoader : loadersByUri.values()) {
                configurationContentLoader.resolver.unregisterContentObserver(configurationContentLoader.observer);
            }
            loadersByUri.clear();
        }
    }

    public static ConfigurationContentLoader getLoader(ContentResolver contentResolver, Uri uri, Runnable runnable) {
        ConfigurationContentLoader configurationContentLoader;
        synchronized (ConfigurationContentLoader.class) {
            Map map = loadersByUri;
            configurationContentLoader = (ConfigurationContentLoader) map.get(uri);
            if (configurationContentLoader == null) {
                try {
                    ConfigurationContentLoader configurationContentLoader2 = new ConfigurationContentLoader(contentResolver, uri, runnable);
                    try {
                        map.put(uri, configurationContentLoader2);
                    } catch (SecurityException e) {
                    }
                    configurationContentLoader = configurationContentLoader2;
                } catch (SecurityException e2) {
                }
            }
        }
        return configurationContentLoader;
    }

    private void notifyConfigurationUpdatedListeners() {
        synchronized (this) {
            for (ConfigurationUpdatedListener configurationUpdatedListener : this.listeners) {
                configurationUpdatedListener.onConfigurationUpdated();
            }
        }
    }

    private Map readFlagsFromContentProvider() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                return (Map) FlagLoader.CC.executeBinderAware(new FlagLoader.BinderAwareFunction() { // from class: com.google.android.libraries.phenotype.client.ConfigurationContentLoader$$ExternalSyntheticLambda0
                    @Override // com.google.android.libraries.phenotype.client.FlagLoader.BinderAwareFunction
                    public final Object execute() {
                        return ConfigurationContentLoader.this.m364x4d429165();
                    }
                });
            } catch (SQLiteException | IllegalStateException | SecurityException e) {
                Log.e("ConfigurationContentLdr", "PhenotypeFlag unable to load ContentProvider, using default values");
                StrictMode.setThreadPolicy(allowThreadDiskReads);
                return null;
            }
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    public Map getFlags() {
        Map map = this.cachedFlags;
        if (map == null) {
            synchronized (this.cacheLock) {
                map = this.cachedFlags;
                if (map == null) {
                    map = readFlagsFromContentProvider();
                    this.cachedFlags = map;
                }
            }
        }
        return map != null ? map : Collections.emptyMap();
    }

    public void invalidateCache() {
        synchronized (this.cacheLock) {
            this.cachedFlags = null;
            this.processCacheInvalidater.run();
        }
        notifyConfigurationUpdatedListeners();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$readFlagsFromContentProvider$0$com-google-android-libraries-phenotype-client-ConfigurationContentLoader  reason: not valid java name */
    public /* synthetic */ Map m364x4d429165() {
        Map hashMap;
        Cursor query = this.resolver.query(this.uri, COLUMNS, null, null, null);
        if (query == null) {
            return Collections.emptyMap();
        }
        try {
            int count = query.getCount();
            if (count == 0) {
                return Collections.emptyMap();
            }
            if (count <= 256) {
                hashMap = new ArrayMap(count);
            } else {
                hashMap = new HashMap(count, 1.0f);
            }
            while (query.moveToNext()) {
                hashMap.put(query.getString(0), query.getString(1));
            }
            return hashMap;
        } finally {
            query.close();
        }
    }

    @Override // com.google.android.libraries.phenotype.client.FlagLoader
    public String getFlag(String str) {
        return (String) getFlags().get(str);
    }
}
