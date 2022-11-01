package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.GmsClientTracer;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.StrictModeUtils;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class GmsClientSupervisorImpl extends GmsClientSupervisor {
    private final Context applicationContext;
    private final long bindTimeoutMillis;
    private final HashMap connectionStatus = new HashMap();
    private final ConnectionTracker connectionTracker;
    private volatile Handler handler;
    private final HandlerCallback handlerCallback;
    private final long unbindDelayMillis;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class GmsClientConnectionStatus implements ServiceConnection, GmsClientTracer.AlreadyTraced {
        private IBinder binder;
        private ComponentName componentName;
        private final GmsClientSupervisor.ConnectionStatusConfig config;
        private boolean isBound;
        private final Map clientConnections = new HashMap();
        private int state = 2;

        public GmsClientConnectionStatus(GmsClientSupervisor.ConnectionStatusConfig connectionStatusConfig) {
            this.config = connectionStatusConfig;
        }

        public void addServiceConnection(ServiceConnection serviceConnection, ServiceConnection serviceConnection2, String str) {
            this.clientConnections.put(serviceConnection, serviceConnection2);
        }

        public void bindService(String str, Executor executor) {
            this.state = 3;
            StrictMode.VmPolicy permitUnsafeIntentLaunchPolicy = StrictModeUtils.setPermitUnsafeIntentLaunchPolicy();
            try {
                boolean bindService = GmsClientSupervisorImpl.this.connectionTracker.bindService(GmsClientSupervisorImpl.this.applicationContext, str, this.config.getStartServiceIntent(GmsClientSupervisorImpl.this.applicationContext), this, this.config.getBindFlags(), executor);
                this.isBound = bindService;
                if (bindService) {
                    GmsClientSupervisorImpl.this.handler.sendMessageDelayed(GmsClientSupervisorImpl.this.handler.obtainMessage(1, this.config), GmsClientSupervisorImpl.this.bindTimeoutMillis);
                } else {
                    this.state = 2;
                    try {
                        GmsClientSupervisorImpl.this.connectionTracker.unbindService(GmsClientSupervisorImpl.this.applicationContext, this);
                    } catch (IllegalArgumentException e) {
                    }
                }
            } finally {
                StrictMode.setVmPolicy(permitUnsafeIntentLaunchPolicy);
            }
        }

        public boolean containsGmsServiceConnection(ServiceConnection serviceConnection) {
            return this.clientConnections.containsKey(serviceConnection);
        }

        public IBinder getBinder() {
            return this.binder;
        }

        public ComponentName getComponentName() {
            return this.componentName;
        }

        public int getState() {
            return this.state;
        }

        public boolean hasNoGmsServiceConnections() {
            return this.clientConnections.isEmpty();
        }

        public boolean isBound() {
            return this.isBound;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (GmsClientSupervisorImpl.this.connectionStatus) {
                GmsClientSupervisorImpl.this.handler.removeMessages(1, this.config);
                this.binder = iBinder;
                this.componentName = componentName;
                for (ServiceConnection serviceConnection : this.clientConnections.values()) {
                    serviceConnection.onServiceConnected(componentName, iBinder);
                }
                this.state = 1;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (GmsClientSupervisorImpl.this.connectionStatus) {
                GmsClientSupervisorImpl.this.handler.removeMessages(1, this.config);
                this.binder = null;
                this.componentName = componentName;
                for (ServiceConnection serviceConnection : this.clientConnections.values()) {
                    serviceConnection.onServiceDisconnected(componentName);
                }
                this.state = 2;
            }
        }

        public void removeServiceConnection(ServiceConnection serviceConnection, String str) {
            this.clientConnections.remove(serviceConnection);
        }

        public void unbindService(String str) {
            GmsClientSupervisorImpl.this.handler.removeMessages(1, this.config);
            GmsClientSupervisorImpl.this.connectionTracker.unbindService(GmsClientSupervisorImpl.this.applicationContext, this);
            this.isBound = false;
            this.state = 2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class HandlerCallback implements Handler.Callback {
        private HandlerCallback() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    synchronized (GmsClientSupervisorImpl.this.connectionStatus) {
                        GmsClientSupervisor.ConnectionStatusConfig connectionStatusConfig = (GmsClientSupervisor.ConnectionStatusConfig) message.obj;
                        GmsClientConnectionStatus gmsClientConnectionStatus = (GmsClientConnectionStatus) GmsClientSupervisorImpl.this.connectionStatus.get(connectionStatusConfig);
                        if (gmsClientConnectionStatus != null && gmsClientConnectionStatus.hasNoGmsServiceConnections()) {
                            if (gmsClientConnectionStatus.isBound()) {
                                gmsClientConnectionStatus.unbindService("GmsClientSupervisor");
                            }
                            GmsClientSupervisorImpl.this.connectionStatus.remove(connectionStatusConfig);
                        }
                    }
                    return true;
                case 1:
                    synchronized (GmsClientSupervisorImpl.this.connectionStatus) {
                        GmsClientSupervisor.ConnectionStatusConfig connectionStatusConfig2 = (GmsClientSupervisor.ConnectionStatusConfig) message.obj;
                        GmsClientConnectionStatus gmsClientConnectionStatus2 = (GmsClientConnectionStatus) GmsClientSupervisorImpl.this.connectionStatus.get(connectionStatusConfig2);
                        if (gmsClientConnectionStatus2 != null && gmsClientConnectionStatus2.getState() == 3) {
                            Log.e("GmsClientSupervisor", "Timeout waiting for ServiceConnection callback " + String.valueOf(connectionStatusConfig2), new Exception());
                            ComponentName componentName = gmsClientConnectionStatus2.getComponentName();
                            if (componentName == null) {
                                componentName = connectionStatusConfig2.getComponentName();
                            }
                            if (componentName == null) {
                                componentName = new ComponentName((String) Preconditions.checkNotNull(connectionStatusConfig2.getPackage()), "unknown");
                            }
                            gmsClientConnectionStatus2.onServiceDisconnected(componentName);
                        }
                    }
                    return true;
                default:
                    return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GmsClientSupervisorImpl(Context context, Looper looper) {
        HandlerCallback handlerCallback = new HandlerCallback();
        this.handlerCallback = handlerCallback;
        this.applicationContext = context.getApplicationContext();
        this.handler = new TracingHandler(looper, handlerCallback);
        this.connectionTracker = ConnectionTracker.getInstance();
        this.unbindDelayMillis = GmsClientSupervisor.DEFAULT_UNBIND_DELAY_MILLIS;
        this.bindTimeoutMillis = 300000L;
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    protected boolean bindService(GmsClientSupervisor.ConnectionStatusConfig connectionStatusConfig, ServiceConnection serviceConnection, String str, Executor executor) {
        boolean isBound;
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        ServiceConnection traceServiceConnection = GmsClientTracer.traceServiceConnection(serviceConnection);
        synchronized (this.connectionStatus) {
            GmsClientConnectionStatus gmsClientConnectionStatus = (GmsClientConnectionStatus) this.connectionStatus.get(connectionStatusConfig);
            if (gmsClientConnectionStatus == null) {
                gmsClientConnectionStatus = new GmsClientConnectionStatus(connectionStatusConfig);
                gmsClientConnectionStatus.addServiceConnection(serviceConnection, traceServiceConnection, str);
                gmsClientConnectionStatus.bindService(str, executor);
                this.connectionStatus.put(connectionStatusConfig, gmsClientConnectionStatus);
            } else {
                this.handler.removeMessages(0, connectionStatusConfig);
                if (gmsClientConnectionStatus.containsGmsServiceConnection(serviceConnection)) {
                    throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + String.valueOf(connectionStatusConfig));
                }
                gmsClientConnectionStatus.addServiceConnection(serviceConnection, traceServiceConnection, str);
                switch (gmsClientConnectionStatus.getState()) {
                    case 1:
                        traceServiceConnection.onServiceConnected(gmsClientConnectionStatus.getComponentName(), gmsClientConnectionStatus.getBinder());
                        break;
                    case 2:
                        gmsClientConnectionStatus.bindService(str, executor);
                        break;
                }
            }
            isBound = gmsClientConnectionStatus.isBound();
        }
        return isBound;
    }

    @Override // com.google.android.gms.common.internal.GmsClientSupervisor
    protected void unbindService(GmsClientSupervisor.ConnectionStatusConfig connectionStatusConfig, ServiceConnection serviceConnection, String str) {
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.connectionStatus) {
            GmsClientConnectionStatus gmsClientConnectionStatus = (GmsClientConnectionStatus) this.connectionStatus.get(connectionStatusConfig);
            if (gmsClientConnectionStatus == null) {
                throw new IllegalStateException("Nonexistent connection status for service config: " + String.valueOf(connectionStatusConfig));
            } else if (!gmsClientConnectionStatus.containsGmsServiceConnection(serviceConnection)) {
                throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + String.valueOf(connectionStatusConfig));
            } else {
                gmsClientConnectionStatus.removeServiceConnection(serviceConnection, str);
                if (gmsClientConnectionStatus.hasNoGmsServiceConnections()) {
                    this.handler.sendMessageDelayed(this.handler.obtainMessage(0, connectionStatusConfig), this.unbindDelayMillis);
                }
            }
        }
    }
}
