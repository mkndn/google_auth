package com.google.android.gms.common.stats;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.android.gms.common.internal.GmsClientTracer;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ConnectionTracker {
    private static volatile ConnectionTracker sInstance;
    public ConcurrentHashMap connectionMap = new ConcurrentHashMap();
    private static final Object sSingletonLock = new Object();
    static boolean sIsPackageSide = BuildConstants.IS_PACKAGE_SIDE;

    private ConnectionTracker() {
    }

    private boolean bindService(Context context, Intent intent, ServiceConnection serviceConnection, int i, Executor executor) {
        if (PlatformVersion.isAtLeastQ() && executor != null) {
            return context.bindService(intent, i, executor, serviceConnection);
        }
        return context.bindService(intent, serviceConnection, i);
    }

    private boolean bindingToStoppedPackage(Context context, Intent intent) {
        ComponentName component = intent.getComponent();
        if (component == null) {
            return false;
        }
        return ClientLibraryUtils.isPackageStopped(context, component.getPackageName());
    }

    private static boolean enableWrapServiceConnection(ServiceConnection serviceConnection) {
        return !(serviceConnection instanceof GmsClientTracer.AlreadyTraced);
    }

    public static ConnectionTracker getInstance() {
        if (sInstance == null) {
            synchronized (sSingletonLock) {
                if (sInstance == null) {
                    sInstance = new ConnectionTracker();
                }
            }
        }
        return (ConnectionTracker) Preconditions.checkNotNull(sInstance);
    }

    private static void unbindServiceUnwrapped(Context context, ServiceConnection serviceConnection) {
        if (!GmsClientTracer.enableSafeUnbind()) {
            context.unbindService(serviceConnection);
            return;
        }
        try {
            context.unbindService(serviceConnection);
        } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException e) {
        }
    }

    public void unbindService(Context context, ServiceConnection serviceConnection) {
        if (enableWrapServiceConnection(serviceConnection) && this.connectionMap.containsKey(serviceConnection)) {
            try {
                unbindServiceUnwrapped(context, (ServiceConnection) this.connectionMap.get(serviceConnection));
                return;
            } finally {
                this.connectionMap.remove(serviceConnection);
            }
        }
        unbindServiceUnwrapped(context, serviceConnection);
    }

    public boolean bindService(Context context, String str, Intent intent, ServiceConnection serviceConnection, int i, Executor executor) {
        return bindService(context, str, intent, serviceConnection, i, true, executor);
    }

    private boolean bindService(Context context, String str, Intent intent, ServiceConnection serviceConnection, int i, boolean z, Executor executor) {
        if (z && bindingToStoppedPackage(context, intent)) {
            Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
            return false;
        } else if (enableWrapServiceConnection(serviceConnection)) {
            ServiceConnection traceServiceConnection = GmsClientTracer.traceServiceConnection(serviceConnection);
            ServiceConnection serviceConnection2 = (ServiceConnection) this.connectionMap.putIfAbsent(serviceConnection, traceServiceConnection);
            if (serviceConnection2 != null && traceServiceConnection != serviceConnection2) {
                String format = String.format("Duplicate binding with the same ServiceConnection: %s, %s, %s.", serviceConnection, str, intent.getAction());
                Log.w("ConnectionTracker", format);
                if (GmsClientTracer.enableThrowExceptionWhenDoubleBind()) {
                    throw new IllegalStateException(format);
                }
            }
            try {
                boolean bindService = bindService(context, intent, traceServiceConnection, i, executor);
                return bindService ? bindService : bindService;
            } finally {
                this.connectionMap.remove(serviceConnection, traceServiceConnection);
            }
        } else {
            return bindService(context, intent, serviceConnection, i, executor);
        }
    }
}
