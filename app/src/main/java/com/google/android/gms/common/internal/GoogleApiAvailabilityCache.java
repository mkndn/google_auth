package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleApiAvailabilityCache {
    private GoogleApiAvailabilityLight apiAvailability;
    private final SparseIntArray apiAvailabilityCache;

    public GoogleApiAvailabilityCache() {
        this(GoogleApiAvailability.getInstance());
    }

    public void flush() {
        this.apiAvailabilityCache.clear();
    }

    public int getApkVersionAvailability(Context context, int i) {
        return this.apiAvailabilityCache.get(i, -1);
    }

    public int getClientAvailability(Context context, Api.Client client) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(client);
        if (client.requiresGooglePlayServices()) {
            return resolveApkVersionAvailability(context, client.getMinApkVersion());
        }
        return 0;
    }

    public int resolveApkVersionAvailability(Context context, int i) {
        int apkVersionAvailability = getApkVersionAvailability(context, i);
        if (apkVersionAvailability != -1) {
            return apkVersionAvailability;
        }
        int i2 = 0;
        while (true) {
            if (i2 < this.apiAvailabilityCache.size()) {
                int keyAt = this.apiAvailabilityCache.keyAt(i2);
                if (keyAt > i && this.apiAvailabilityCache.get(keyAt) == 0) {
                    apkVersionAvailability = 0;
                    break;
                }
                i2++;
            } else {
                break;
            }
        }
        if (apkVersionAvailability == -1) {
            apkVersionAvailability = this.apiAvailability.isGooglePlayServicesAvailable(context, i);
        }
        this.apiAvailabilityCache.put(i, apkVersionAvailability);
        return apkVersionAvailability;
    }

    public GoogleApiAvailabilityCache(GoogleApiAvailabilityLight googleApiAvailabilityLight) {
        this.apiAvailabilityCache = new SparseIntArray();
        Preconditions.checkNotNull(googleApiAvailabilityLight);
        this.apiAvailability = googleApiAvailabilityLight;
    }
}
