package com.google.android.libraries.phenotype.client;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.collection.ArrayMap;
import com.google.android.libraries.directboot.DirectBootUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SharedPreferencesLoader implements FlagLoader {
    private static final Map loadersByName = new ArrayMap();
    private final Object cacheLock;
    private volatile Map cachedFlags;
    private final SharedPreferences.OnSharedPreferenceChangeListener changeListener;
    private final List listeners;
    private final Runnable processCacheInvalidater;
    private final SharedPreferences sharedPreferences;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void clearLoaderMap() {
        synchronized (SharedPreferencesLoader.class) {
            for (SharedPreferencesLoader sharedPreferencesLoader : loadersByName.values()) {
                sharedPreferencesLoader.sharedPreferences.unregisterOnSharedPreferenceChangeListener(sharedPreferencesLoader.changeListener);
            }
            loadersByName.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SharedPreferencesLoader getLoader(Context context, String str, Runnable runnable) {
        SharedPreferencesLoader sharedPreferencesLoader;
        if (isSharedPreferencesAccessible(context, str)) {
            synchronized (SharedPreferencesLoader.class) {
                Map map = loadersByName;
                sharedPreferencesLoader = (SharedPreferencesLoader) map.get(str);
                if (sharedPreferencesLoader == null) {
                    sharedPreferencesLoader = new SharedPreferencesLoader(getSharedPreferences(context, str), runnable);
                    map.put(str, sharedPreferencesLoader);
                }
            }
            return sharedPreferencesLoader;
        }
        return null;
    }

    private static SharedPreferences getSharedPreferences(Context context, String str) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (str.startsWith("direct_boot:")) {
                if (DirectBootUtils.supportsDirectBoot()) {
                    context = context.createDeviceProtectedStorageContext();
                }
                return context.getSharedPreferences(str.substring("direct_boot:".length()), 0);
            }
            return context.getSharedPreferences(str, 0);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    private void invalidateCache() {
        synchronized (this.cacheLock) {
            this.cachedFlags = null;
            this.processCacheInvalidater.run();
        }
        notifyConfigurationUpdatedListeners();
    }

    private static boolean isSharedPreferencesAccessible(Context context, String str) {
        if (!DirectBootUtils.supportsDirectBoot() || str.startsWith("direct_boot:")) {
            return true;
        }
        return DirectBootUtils.isUserUnlocked(context);
    }

    private void notifyConfigurationUpdatedListeners() {
        synchronized (this) {
            for (ConfigurationUpdatedListener configurationUpdatedListener : this.listeners) {
                configurationUpdatedListener.onConfigurationUpdated();
            }
        }
    }

    @Override // com.google.android.libraries.phenotype.client.FlagLoader
    public Object getFlag(String str) {
        Map<String, ?> map = this.cachedFlags;
        if (map == null) {
            synchronized (this.cacheLock) {
                map = this.cachedFlags;
                if (map == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    Map<String, ?> all = this.sharedPreferences.getAll();
                    this.cachedFlags = all;
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    map = all;
                }
            }
        }
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-google-android-libraries-phenotype-client-SharedPreferencesLoader  reason: not valid java name */
    public /* synthetic */ void m370xe52c4b2e(SharedPreferences sharedPreferences, String str) {
        invalidateCache();
    }

    private SharedPreferencesLoader(SharedPreferences sharedPreferences, Runnable runnable) {
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.google.android.libraries.phenotype.client.SharedPreferencesLoader$$ExternalSyntheticLambda0
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences2, String str) {
                SharedPreferencesLoader.this.m370xe52c4b2e(sharedPreferences2, str);
            }
        };
        this.changeListener = onSharedPreferenceChangeListener;
        this.cacheLock = new Object();
        this.listeners = new ArrayList();
        this.sharedPreferences = sharedPreferences;
        this.processCacheInvalidater = runnable;
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }
}
