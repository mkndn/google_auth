package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import com.google.android.chimera.BoundService;
import com.google.android.gms.common.util.ClientLibraryUtils;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class GmsClientSupervisor {
    public static final long DEFAULT_UNBIND_DELAY_MILLIS = 5000;
    static HandlerThread handlerThread;
    private static GmsClientSupervisorImpl singletonInstance;
    public static final int DEFAULT_BIND_FLAGS = 4225;
    private static int defaultBindFlags = DEFAULT_BIND_FLAGS;
    private static final Object singletonLock = new Object();
    private static boolean useHandlerThreadForCallbacks = false;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ConnectionStatusConfig {
        private static final Uri CONTENT_PROVIDER_AUTHORITY = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").build();
        private final String action;
        private final int bindFlags;
        private final ComponentName componentName;
        private final String packageName;
        private final boolean useDynamicLookup;

        public ConnectionStatusConfig(ComponentName componentName, int i) {
            this.action = null;
            this.packageName = null;
            this.componentName = (ComponentName) Preconditions.checkNotNull(componentName);
            this.bindFlags = i;
            this.useDynamicLookup = false;
        }

        private Intent resolveStartServiceIntentDynamic(Context context) {
            Bundle bundle;
            Bundle bundle2 = new Bundle();
            bundle2.putString("serviceActionBundleKey", this.action);
            Intent intent = null;
            try {
                bundle = context.getContentResolver().call(CONTENT_PROVIDER_AUTHORITY, "serviceIntentCall", (String) null, bundle2);
            } catch (IllegalArgumentException e) {
                Log.w("ConnectionStatusConfig", "Dynamic intent resolution failed: " + String.valueOf(e));
                bundle = null;
            }
            if (bundle != null) {
                intent = (Intent) bundle.getParcelable("serviceResponseIntentKey");
            }
            if (intent == null) {
                Log.w("ConnectionStatusConfig", "Dynamic lookup for intent failed for action: " + this.action);
            }
            return intent;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ConnectionStatusConfig) {
                ConnectionStatusConfig connectionStatusConfig = (ConnectionStatusConfig) obj;
                return Objects.equal(this.action, connectionStatusConfig.action) && Objects.equal(this.packageName, connectionStatusConfig.packageName) && Objects.equal(this.componentName, connectionStatusConfig.componentName) && this.bindFlags == connectionStatusConfig.bindFlags && this.useDynamicLookup == connectionStatusConfig.useDynamicLookup;
            }
            return false;
        }

        public int getBindFlags() {
            return this.bindFlags;
        }

        public ComponentName getComponentName() {
            return this.componentName;
        }

        public String getPackage() {
            return this.packageName;
        }

        public int hashCode() {
            return Objects.hashCode(this.action, this.packageName, this.componentName, Integer.valueOf(this.bindFlags), Boolean.valueOf(this.useDynamicLookup));
        }

        public String toString() {
            String str = this.action;
            if (str == null) {
                Preconditions.checkNotNull(this.componentName);
                return this.componentName.flattenToString();
            }
            return str;
        }

        public Intent getStartServiceIntent(Context context) {
            Intent resolveStartServiceIntentDynamic;
            if (this.action != null) {
                if (ClientLibraryUtils.isPackageSide()) {
                    resolveStartServiceIntentDynamic = BoundService.getStartIntent(context, this.action);
                } else {
                    resolveStartServiceIntentDynamic = this.useDynamicLookup ? resolveStartServiceIntentDynamic(context) : null;
                }
                if (resolveStartServiceIntentDynamic == null) {
                    return new Intent(this.action).setPackage(this.packageName);
                }
                return resolveStartServiceIntentDynamic;
            }
            return new Intent().setComponent(this.componentName);
        }

        public ConnectionStatusConfig(String str, String str2, int i, boolean z) {
            this.action = Preconditions.checkNotEmpty(str);
            this.packageName = Preconditions.checkNotEmpty(str2);
            this.componentName = null;
            this.bindFlags = i;
            this.useDynamicLookup = z;
        }
    }

    public static int getDefaultBindFlags() {
        return defaultBindFlags;
    }

    public static GmsClientSupervisor getInstance(Context context) {
        Looper mainLooper;
        synchronized (singletonLock) {
            if (singletonInstance == null) {
                Context applicationContext = context.getApplicationContext();
                if (useHandlerThreadForCallbacks) {
                    mainLooper = getOrStartHandlerThread().getLooper();
                } else {
                    mainLooper = context.getMainLooper();
                }
                singletonInstance = new GmsClientSupervisorImpl(applicationContext, mainLooper);
            }
        }
        return singletonInstance;
    }

    public static HandlerThread getOrStartHandlerThread() {
        synchronized (singletonLock) {
            HandlerThread handlerThread2 = handlerThread;
            if (handlerThread2 != null) {
                return handlerThread2;
            }
            HandlerThread handlerThread3 = new HandlerThread("GoogleApiHandler", 9);
            handlerThread = handlerThread3;
            handlerThread3.start();
            return handlerThread;
        }
    }

    public boolean bindService(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        return bindService(new ConnectionStatusConfig(componentName, getDefaultBindFlags()), serviceConnection, str, null);
    }

    protected abstract boolean bindService(ConnectionStatusConfig connectionStatusConfig, ServiceConnection serviceConnection, String str, Executor executor);

    public void unbindService(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        unbindService(new ConnectionStatusConfig(componentName, getDefaultBindFlags()), serviceConnection, str);
    }

    protected abstract void unbindService(ConnectionStatusConfig connectionStatusConfig, ServiceConnection serviceConnection, String str);

    public boolean bindService(String str, String str2, int i, ServiceConnection serviceConnection, String str3, boolean z, Executor executor) {
        return bindService(new ConnectionStatusConfig(str, str2, i, z), serviceConnection, str3, executor);
    }

    public void unbindService(String str, String str2, int i, ServiceConnection serviceConnection, String str3, boolean z) {
        unbindService(new ConnectionStatusConfig(str, str2, i, z), serviceConnection, str3);
    }
}
