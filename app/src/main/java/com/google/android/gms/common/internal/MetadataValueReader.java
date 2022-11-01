package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.wrappers.Wrappers;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MetadataValueReader {
    private static String sGoogleAppId;
    private static int sGooglePlayServicesVersion;
    private static boolean sInitialized;
    private static Object sLock = new Object();

    private static void ensureMetadataRead(Context context) {
        Bundle bundle;
        synchronized (sLock) {
            if (sInitialized) {
                return;
            }
            sInitialized = true;
            try {
                bundle = Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128).metaData;
            } catch (PackageManager.NameNotFoundException e) {
                Log.wtf("MetadataValueReader", "This should never happen.", e);
            }
            if (bundle == null) {
                return;
            }
            sGoogleAppId = bundle.getString("com.google.app.id");
            sGooglePlayServicesVersion = bundle.getInt("com.google.android.gms.version");
        }
    }

    public static int getGooglePlayServicesVersion(Context context) {
        ensureMetadataRead(context);
        return sGooglePlayServicesVersion;
    }
}
