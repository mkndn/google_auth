package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.util.PlatformVersion;

/* compiled from: PG */
/* loaded from: classes.dex */
public class InstantApps {
    private static Context sApplicationContext;
    private static Boolean sIsInstantApp;

    public static synchronized boolean isInstantApp(Context context) {
        Boolean bool;
        synchronized (InstantApps.class) {
            Context applicationContext = context.getApplicationContext();
            Context context2 = sApplicationContext;
            if (context2 != null && (bool = sIsInstantApp) != null && context2 == applicationContext) {
                return bool.booleanValue();
            }
            sIsInstantApp = null;
            if (PlatformVersion.isAtLeastO()) {
                sIsInstantApp = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
            } else {
                try {
                    context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                    sIsInstantApp = true;
                } catch (ClassNotFoundException e) {
                    sIsInstantApp = false;
                }
            }
            sApplicationContext = applicationContext;
            return sIsInstantApp.booleanValue();
        }
    }
}
