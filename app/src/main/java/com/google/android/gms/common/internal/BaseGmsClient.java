package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.IGmsCallbacks;
import com.google.android.gms.common.internal.IGmsServiceBroker;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BaseGmsClient {
    public static final int CONNECT_STATE_CONNECTED = 4;
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    public static final int CONNECT_STATE_LOCAL_CONNECTING = 3;
    public static final int CONNECT_STATE_REMOTE_CONNECTING = 2;
    private static final Feature[] EMPTY_FEATURES_ARRAY = new Feature[0];
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = {"service_esmobile", "service_googleme"};
    private long lastConnectedTime;
    private int lastFailedStatusCode;
    private long lastFailedTime;
    private int lastSuspendedCause;
    private long lastSuspendedTime;
    private final GoogleApiAvailabilityLight mApiAvailability;
    private volatile String mAttributionTag;
    private GmsServiceConnection mConnection;
    private final BaseConnectionCallbacks mConnectionCallbacks;
    private final BaseOnConnectionFailedListener mConnectionFailedListener;
    protected ConnectionProgressReportCallbacks mConnectionProgressReportCallbacks;
    private final Context mContext;
    GmsServiceEndpoint mCurrentServiceEndpoint;
    private final int mGCoreServiceId;
    final Handler mHandler;
    private final Looper mLooper;
    private final String mRealClientName;
    private IInterface mService;
    private IGmsServiceBroker mServiceBroker;
    private final GmsClientSupervisor mSupervisor;
    private volatile String lastDisconnectMessage = null;
    private final Object mLock = new Object();
    private final Object mServiceBrokerLock = new Object();
    private final ArrayList mCallbackProxyList = new ArrayList();
    private int mConnectState = 1;
    private ConnectionResult mRemoteServiceFailedResult = null;
    private boolean mLocalServiceFailedConnect = false;
    private volatile ConnectionInfo connectionInfo = null;
    protected AtomicInteger mDisconnectCount = new AtomicInteger(0);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class ApiServiceCallback extends CallbackProxy {
        public final Bundle resolution;
        public final int statusCode;

        protected ApiServiceCallback(int i, Bundle bundle) {
            super(true);
            this.statusCode = i;
            this.resolution = bundle;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.internal.BaseGmsClient.CallbackProxy
        public void deliverCallback(Boolean bool) {
            if (bool == null) {
                BaseGmsClient.this.setConnectState(1, null);
                return;
            }
            int i = this.statusCode;
            if (i == 0) {
                if (!handleServiceSuccess()) {
                    BaseGmsClient.this.setConnectState(1, null);
                    handleServiceFailure(new ConnectionResult(8, null));
                }
            } else if (i != 10 || !ClientLibraryUtils.isPackageSide()) {
                BaseGmsClient.this.setConnectState(1, null);
                Bundle bundle = this.resolution;
                handleServiceFailure(new ConnectionResult(this.statusCode, bundle != null ? (PendingIntent) bundle.getParcelable("pendingIntent") : null));
            } else {
                BaseGmsClient.this.setConnectState(1, null);
                handleServiceFailure(new ConnectionResult(this.statusCode, null));
            }
        }

        protected abstract void handleServiceFailure(ConnectionResult connectionResult);

        protected abstract boolean handleServiceSuccess();

        @Override // com.google.android.gms.common.internal.BaseGmsClient.CallbackProxy
        protected void onDeliverCallbackFailed() {
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface BaseConnectionCallbacks {
        public static final int CAUSE_DEAD_OBJECT_EXCEPTION = 3;
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(Bundle bundle);

        void onConnectionSuspended(int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface BaseOnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class CallbackHandler extends TracingHandler {
        public static final int NORMAL_CALLBACK = 2;
        public static final int ON_CONNECTION_SUSPENDED = 6;
        public static final int ON_LOCAL_NOT_AVAILABLE = 5;
        public static final int ON_NOT_AVAILABLE = 3;
        public static final int ON_POST_BIND = 7;
        public static final int ON_POST_INIT = 1;
        public static final int ON_REMOTE_NOT_AVAILABLE = 4;

        public CallbackHandler(Looper looper) {
            super(looper);
        }

        private void deliverCallbackFailed(Message message) {
            CallbackProxy callbackProxy = (CallbackProxy) message.obj;
            callbackProxy.onDeliverCallbackFailed();
            callbackProxy.unregister();
        }

        private boolean hasCallback(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 7;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ConnectionResult connectionResult;
            ConnectionResult connectionResult2;
            if (BaseGmsClient.this.mDisconnectCount.get() != message.arg1) {
                if (hasCallback(message)) {
                    deliverCallbackFailed(message);
                }
            } else if ((message.what == 1 || message.what == 7 || ((message.what == 4 && !BaseGmsClient.this.enableLocalFallback()) || message.what == 5)) && !BaseGmsClient.this.isConnecting()) {
                deliverCallbackFailed(message);
            } else {
                if (message.what == 4) {
                    BaseGmsClient.this.mRemoteServiceFailedResult = new ConnectionResult(message.arg2);
                    if (BaseGmsClient.this.isLocalServicePresent() && !BaseGmsClient.this.mLocalServiceFailedConnect) {
                        BaseGmsClient.this.setConnectState(3, null);
                        return;
                    }
                    if (BaseGmsClient.this.mRemoteServiceFailedResult != null) {
                        connectionResult2 = BaseGmsClient.this.mRemoteServiceFailedResult;
                    } else {
                        connectionResult2 = new ConnectionResult(8);
                    }
                    BaseGmsClient.this.mConnectionProgressReportCallbacks.onReportServiceBinding(connectionResult2);
                    BaseGmsClient.this.onConnectionFailed(connectionResult2);
                } else if (message.what == 5) {
                    if (BaseGmsClient.this.mRemoteServiceFailedResult != null) {
                        connectionResult = BaseGmsClient.this.mRemoteServiceFailedResult;
                    } else {
                        connectionResult = new ConnectionResult(8);
                    }
                    BaseGmsClient.this.mConnectionProgressReportCallbacks.onReportServiceBinding(connectionResult);
                    BaseGmsClient.this.onConnectionFailed(connectionResult);
                } else if (message.what == 3) {
                    ConnectionResult connectionResult3 = new ConnectionResult(message.arg2, message.obj instanceof PendingIntent ? (PendingIntent) message.obj : null);
                    BaseGmsClient.this.mConnectionProgressReportCallbacks.onReportServiceBinding(connectionResult3);
                    BaseGmsClient.this.onConnectionFailed(connectionResult3);
                } else if (message.what == 6) {
                    BaseGmsClient.this.setConnectState(5, null);
                    if (BaseGmsClient.this.mConnectionCallbacks != null) {
                        BaseGmsClient.this.mConnectionCallbacks.onConnectionSuspended(message.arg2);
                    }
                    BaseGmsClient.this.onConnectionSuspended(message.arg2);
                    BaseGmsClient.this.compareAndSetConnectState(5, 1, null);
                } else if (message.what == 2 && !BaseGmsClient.this.isConnected()) {
                    deliverCallbackFailed(message);
                } else if (hasCallback(message)) {
                    ((CallbackProxy) message.obj).deliverCallback();
                } else {
                    Log.wtf("GmsClient", "Don't know how to handle message: " + message.what, new Exception());
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class CallbackProxy {
        private boolean mCallbackDelivered = false;
        private Object mListener;

        public CallbackProxy(Object obj) {
            this.mListener = obj;
        }

        public void deliverCallback() {
            Object obj;
            synchronized (this) {
                obj = this.mListener;
                if (this.mCallbackDelivered) {
                    Log.w("GmsClient", "Callback proxy " + String.valueOf(this) + " being reused. This is not safe.");
                }
            }
            if (obj != null) {
                try {
                    deliverCallback(obj);
                } catch (RuntimeException e) {
                    onDeliverCallbackFailed();
                    throw e;
                }
            } else {
                onDeliverCallbackFailed();
            }
            synchronized (this) {
                this.mCallbackDelivered = true;
            }
            unregister();
        }

        protected abstract void deliverCallback(Object obj);

        protected abstract void onDeliverCallbackFailed();

        public void removeListener() {
            synchronized (this) {
                this.mListener = null;
            }
        }

        public void unregister() {
            removeListener();
            synchronized (BaseGmsClient.this.mCallbackProxyList) {
                BaseGmsClient.this.mCallbackProxyList.remove(this);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ConnectionProgressReportCallbacks {
        void onReportServiceBinding(ConnectionResult connectionResult);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GmsCallbacks extends IGmsCallbacks.Stub {
        private final int mDisconnectCount;
        private BaseGmsClient mGmsClient;

        public GmsCallbacks(BaseGmsClient baseGmsClient, int i) {
            this.mGmsClient = baseGmsClient;
            this.mDisconnectCount = i;
        }

        private void onCallbackComplete() {
            this.mGmsClient = null;
        }

        @Override // com.google.android.gms.common.internal.IGmsCallbacks
        public void onAccountValidationComplete(int i, Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }

        @Override // com.google.android.gms.common.internal.IGmsCallbacks
        public void onPostInitComplete(int i, IBinder iBinder, Bundle bundle) {
            Preconditions.checkNotNull(this.mGmsClient, "onPostInitComplete can be called only once per call to getRemoteService");
            this.mGmsClient.onPostInitHandler(i, iBinder, bundle, this.mDisconnectCount);
            onCallbackComplete();
        }

        @Override // com.google.android.gms.common.internal.IGmsCallbacks
        public void onPostInitCompleteWithConnectionInfo(int i, IBinder iBinder, ConnectionInfo connectionInfo) {
            BaseGmsClient baseGmsClient = this.mGmsClient;
            Preconditions.checkNotNull(baseGmsClient, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Preconditions.checkNotNull(connectionInfo);
            baseGmsClient.setConnectionInfo(connectionInfo);
            onPostInitComplete(i, iBinder, connectionInfo.getResolutionBundle());
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GmsServiceConnection implements ServiceConnection {
        private final int mDisconnectCount;

        public GmsServiceConnection(int i) {
            this.mDisconnectCount = i;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder == null) {
                BaseGmsClient.this.triggerServiceNotAvailable(16);
                return;
            }
            synchronized (BaseGmsClient.this.mServiceBrokerLock) {
                BaseGmsClient.this.mServiceBroker = IGmsServiceBroker.Stub.asInterface(iBinder);
            }
            BaseGmsClient.this.onPostServiceBindingHandler(0, null, this.mDisconnectCount);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (BaseGmsClient.this.mServiceBrokerLock) {
                BaseGmsClient.this.mServiceBroker = null;
            }
            BaseGmsClient.this.mHandler.sendMessage(BaseGmsClient.this.mHandler.obtainMessage(6, this.mDisconnectCount, 1));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        public LegacyClientCallbackAdapter() {
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
        public void onReportServiceBinding(ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                baseGmsClient.getRemoteService(null, baseGmsClient.getScopes());
            } else if (BaseGmsClient.this.mConnectionFailedListener != null) {
                BaseGmsClient.this.mConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PostInitCallback extends ApiServiceCallback {
        public final IBinder service;

        public PostInitCallback(int i, IBinder iBinder, Bundle bundle) {
            super(i, bundle);
            this.service = iBinder;
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ApiServiceCallback
        protected void handleServiceFailure(ConnectionResult connectionResult) {
            if (BaseGmsClient.this.mConnectionFailedListener != null) {
                BaseGmsClient.this.mConnectionFailedListener.onConnectionFailed(connectionResult);
            }
            BaseGmsClient.this.onConnectionFailed(connectionResult);
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ApiServiceCallback
        protected boolean handleServiceSuccess() {
            try {
                String interfaceDescriptor = ((IBinder) Preconditions.checkNotNull(this.service)).getInterfaceDescriptor();
                if (!BaseGmsClient.this.getServiceDescriptor().equals(interfaceDescriptor)) {
                    Log.w("GmsClient", "service descriptor mismatch: " + BaseGmsClient.this.getServiceDescriptor() + " vs. " + interfaceDescriptor);
                    return false;
                }
                IInterface createServiceInterface = BaseGmsClient.this.createServiceInterface(this.service);
                if (createServiceInterface == null || !(BaseGmsClient.this.compareAndSetConnectState(2, 4, createServiceInterface) || BaseGmsClient.this.compareAndSetConnectState(3, 4, createServiceInterface))) {
                    return false;
                }
                BaseGmsClient.this.mRemoteServiceFailedResult = null;
                Bundle connectionHint = BaseGmsClient.this.getConnectionHint();
                if (BaseGmsClient.this.mConnectionCallbacks != null) {
                    BaseGmsClient.this.mConnectionCallbacks.onConnected(connectionHint);
                    return true;
                }
                return true;
            } catch (RemoteException e) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PostServiceBindingCallback extends ApiServiceCallback {
        public PostServiceBindingCallback(int i, Bundle bundle) {
            super(i, bundle);
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ApiServiceCallback
        protected void handleServiceFailure(ConnectionResult connectionResult) {
            if (BaseGmsClient.this.enableLocalFallback() && BaseGmsClient.this.isLocalServicePresent()) {
                BaseGmsClient.this.triggerServiceNotAvailable(16);
                return;
            }
            BaseGmsClient.this.mConnectionProgressReportCallbacks.onReportServiceBinding(connectionResult);
            BaseGmsClient.this.onConnectionFailed(connectionResult);
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ApiServiceCallback
        protected boolean handleServiceSuccess() {
            BaseGmsClient.this.mConnectionProgressReportCallbacks.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return true;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface SignOutCallbacks {
        void onSignOutComplete();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseGmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.mLooper = (Looper) Preconditions.checkNotNull(looper, "Looper must not be null");
        this.mSupervisor = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.mApiAvailability = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.mHandler = new CallbackHandler(looper);
        this.mGCoreServiceId = i;
        this.mConnectionCallbacks = baseConnectionCallbacks;
        this.mConnectionFailedListener = baseOnConnectionFailedListener;
        this.mRealClientName = str;
    }

    private void bindServiceLocked() {
        GmsServiceEndpoint gmsServiceEndpoint;
        GmsServiceConnection gmsServiceConnection = this.mConnection;
        if (gmsServiceConnection != null && (gmsServiceEndpoint = this.mCurrentServiceEndpoint) != null) {
            Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + gmsServiceEndpoint.getStartAction() + " on " + this.mCurrentServiceEndpoint.getPackageName());
            this.mSupervisor.unbindService((String) Preconditions.checkNotNull(this.mCurrentServiceEndpoint.getStartAction()), this.mCurrentServiceEndpoint.getPackageName(), this.mCurrentServiceEndpoint.getBindFlags(), gmsServiceConnection, getRealClientName(), this.mCurrentServiceEndpoint.getUseDynamicLookup());
            this.mDisconnectCount.incrementAndGet();
        }
        GmsServiceConnection gmsServiceConnection2 = new GmsServiceConnection(this.mDisconnectCount.get());
        this.mConnection = gmsServiceConnection2;
        GmsServiceEndpoint serviceEndpoint = getServiceEndpoint();
        this.mCurrentServiceEndpoint = serviceEndpoint;
        if (serviceEndpoint.getUseDynamicLookup() && !ClientLibraryUtils.isPackageSide() && getMinApkVersion() < 17895000) {
            throw new IllegalStateException("Internal Error, the minimum apk version of this BaseGmsClient is too low to support dynamic lookup. Start service action: " + this.mCurrentServiceEndpoint.getStartAction());
        }
        if (!this.mSupervisor.bindService((String) Preconditions.checkNotNull(this.mCurrentServiceEndpoint.getStartAction()), this.mCurrentServiceEndpoint.getPackageName(), this.mCurrentServiceEndpoint.getBindFlags(), gmsServiceConnection2, getRealClientName(), this.mCurrentServiceEndpoint.getUseDynamicLookup(), getBindServiceExecutor())) {
            Log.w("GmsClient", "unable to connect to service: " + this.mCurrentServiceEndpoint.getStartAction() + " on " + this.mCurrentServiceEndpoint.getPackageName());
            onPostServiceBindingHandler(16, null, this.mDisconnectCount.get());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean compareAndSetConnectState(int i, int i2, IInterface iInterface) {
        synchronized (this.mLock) {
            if (this.mConnectState != i) {
                return false;
            }
            setConnectState(i2, iInterface);
            return true;
        }
    }

    private GmsServiceEndpoint getServiceEndpoint() {
        if (this.mConnectState == 3 && getLocalStartServiceAction() != null) {
            return new GmsServiceEndpoint(getContext().getPackageName(), getLocalStartServiceAction(), true, getServiceBindFlags(), false);
        }
        return new GmsServiceEndpoint(getStartServicePackage(), getStartServiceAction(), false, getServiceBindFlags(), getUseDynamicLookup());
    }

    private boolean isConnectingLocally() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mConnectState == 3;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLocalServicePresent() {
        if (this.mLocalServiceFailedConnect || TextUtils.isEmpty(getServiceDescriptor()) || TextUtils.isEmpty(getLocalStartServiceAction())) {
            return false;
        }
        try {
            Class.forName(getServiceDescriptor());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConnectState(int i, IInterface iInterface) {
        Preconditions.checkArgument((i == 4) == (iInterface != null));
        synchronized (this.mLock) {
            this.mConnectState = i;
            this.mService = iInterface;
            switch (i) {
                case 1:
                    unbindServiceLocked();
                    break;
                case 2:
                case 3:
                    bindServiceLocked();
                    break;
                case 4:
                    onConnectedLocked((IInterface) Preconditions.checkNotNull(iInterface));
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConnectionInfo(ConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
        if (usesClientTelemetry()) {
            ConnectionTelemetryConfiguration connectionTelemetryConfiguration = connectionInfo.getConnectionTelemetryConfiguration();
            RootTelemetryConfigManager.getInstance().updateConfig(connectionTelemetryConfiguration == null ? null : connectionTelemetryConfiguration.getRootTelemetryConfiguration());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void triggerServiceNotAvailable(int i) {
        int i2;
        if (isConnectingLocally()) {
            this.mLocalServiceFailedConnect = true;
            i2 = 5;
        } else {
            i2 = 4;
        }
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(i2, this.mDisconnectCount.get(), i));
    }

    private void unbindServiceLocked() {
        GmsServiceConnection gmsServiceConnection = this.mConnection;
        if (gmsServiceConnection != null) {
            this.mSupervisor.unbindService((String) Preconditions.checkNotNull(this.mCurrentServiceEndpoint.getStartAction()), this.mCurrentServiceEndpoint.getPackageName(), this.mCurrentServiceEndpoint.getBindFlags(), gmsServiceConnection, getRealClientName(), this.mCurrentServiceEndpoint.getUseDynamicLookup());
            this.mConnection = null;
        }
    }

    protected final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    protected abstract IInterface createServiceInterface(IBinder iBinder);

    public void disconnect() {
        this.mDisconnectCount.incrementAndGet();
        synchronized (this.mCallbackProxyList) {
            int size = this.mCallbackProxyList.size();
            for (int i = 0; i < size; i++) {
                ((CallbackProxy) this.mCallbackProxyList.get(i)).removeListener();
            }
            this.mCallbackProxyList.clear();
        }
        synchronized (this.mServiceBrokerLock) {
            this.mServiceBroker = null;
        }
        setConnectState(1, null);
    }

    protected boolean enableLocalFallback() {
        return false;
    }

    public Account getAccount() {
        throw null;
    }

    public final Account getAccountOrDefault() {
        Account account = getAccount();
        return account != null ? account : new Account("<<default account>>", "com.google");
    }

    public Feature[] getApiFeatures() {
        return EMPTY_FEATURES_ARRAY;
    }

    public String getAttributionTag() {
        return this.mAttributionTag;
    }

    public final Feature[] getAvailableFeatures() {
        ConnectionInfo connectionInfo = this.connectionInfo;
        if (connectionInfo == null) {
            return null;
        }
        return connectionInfo.getAvailableFeatures();
    }

    protected Executor getBindServiceExecutor() {
        throw null;
    }

    public Bundle getConnectionHint() {
        return null;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public String getEndpointPackageName() {
        GmsServiceEndpoint gmsServiceEndpoint;
        if (isConnected() && (gmsServiceEndpoint = this.mCurrentServiceEndpoint) != null) {
            return gmsServiceEndpoint.getPackageName();
        }
        throw new RuntimeException("Failed to connect when checking package");
    }

    public int getGCoreServiceId() {
        return this.mGCoreServiceId;
    }

    protected Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    public String getLastDisconnectMessage() {
        return this.lastDisconnectMessage;
    }

    protected String getLocalStartServiceAction() {
        return null;
    }

    public int getMinApkVersion() {
        throw null;
    }

    protected final String getRealClientName() {
        String str = this.mRealClientName;
        return str == null ? this.mContext.getClass().getName() : str;
    }

    public void getRemoteService(IAccountAccessor iAccountAccessor, Set set) {
        GetServiceRequest extraArgs = new GetServiceRequest(this.mGCoreServiceId, getAttributionTag()).setCallingPackage(this.mContext.getPackageName()).setExtraArgs(getGetServiceRequestExtraArgs());
        if (set != null) {
            extraArgs.setScopes(set);
        }
        if (requiresSignIn()) {
            extraArgs.setClientRequestedAccount(getAccountOrDefault()).setAuthenticatedAccount(iAccountAccessor);
        } else if (requiresAccount()) {
            extraArgs.setClientRequestedAccount(getAccount());
        }
        extraArgs.setClientRequiredFeatures(getRequiredFeatures());
        extraArgs.setClientApiFeatures(getApiFeatures());
        if (usesClientTelemetry()) {
            extraArgs.setRequestingTelemetryConfiguration(true);
        }
        try {
            synchronized (this.mServiceBrokerLock) {
                IGmsServiceBroker iGmsServiceBroker = this.mServiceBroker;
                if (iGmsServiceBroker != null) {
                    GmsClientTracer.getService(iGmsServiceBroker, new GmsCallbacks(this, this.mDisconnectCount.get()), extraArgs);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            triggerConnectionSuspended(3);
        } catch (RemoteException e2) {
            e = e2;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            onPostInitHandler(8, null, null, this.mDisconnectCount.get());
        } catch (SecurityException e3) {
            throw e3;
        } catch (RuntimeException e4) {
            e = e4;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            onPostInitHandler(8, null, null, this.mDisconnectCount.get());
        }
    }

    public Feature[] getRequiredFeatures() {
        throw null;
    }

    protected Set getScopes() {
        throw null;
    }

    public final IInterface getService() {
        IInterface iInterface;
        synchronized (this.mLock) {
            if (this.mConnectState == 5) {
                throw new DeadObjectException();
            }
            checkConnected();
            iInterface = (IInterface) Preconditions.checkNotNull(this.mService, "Client is connected but service is null");
        }
        return iInterface;
    }

    protected int getServiceBindFlags() {
        return GmsClientSupervisor.getDefaultBindFlags();
    }

    protected abstract String getServiceDescriptor();

    protected abstract String getStartServiceAction();

    protected String getStartServicePackage() {
        return "com.google.android.gms";
    }

    public ConnectionTelemetryConfiguration getTelemetryConfiguration() {
        ConnectionInfo connectionInfo = this.connectionInfo;
        if (connectionInfo == null) {
            return null;
        }
        return connectionInfo.getConnectionTelemetryConfiguration();
    }

    protected boolean getUseDynamicLookup() {
        return getMinApkVersion() >= 211700000;
    }

    public boolean hasConnectionInfo() {
        return this.connectionInfo != null;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mConnectState == 4;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.mLock) {
            int i = this.mConnectState;
            z = i == 2 || i == 3;
        }
        return z;
    }

    protected void onConnectedLocked(IInterface iInterface) {
        this.lastConnectedTime = System.currentTimeMillis();
    }

    protected void onConnectionFailed(ConnectionResult connectionResult) {
        this.lastFailedStatusCode = connectionResult.getErrorCode();
        this.lastFailedTime = System.currentTimeMillis();
    }

    protected void onConnectionSuspended(int i) {
        this.lastSuspendedCause = i;
        this.lastSuspendedTime = System.currentTimeMillis();
    }

    protected void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(1, i2, -1, new PostInitCallback(i, iBinder, bundle)));
    }

    protected void onPostServiceBindingHandler(int i, Bundle bundle, int i2) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(7, i2, -1, new PostServiceBindingCallback(i, bundle)));
    }

    public void onUserSignOut(SignOutCallbacks signOutCallbacks) {
        signOutCallbacks.onSignOutComplete();
    }

    public boolean requiresAccount() {
        return false;
    }

    public boolean requiresGooglePlayServices() {
        return true;
    }

    public boolean requiresSignIn() {
        return false;
    }

    public void setAttributionTag(String str) {
        this.mAttributionTag = str;
    }

    public void triggerConnectionSuspended(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(6, this.mDisconnectCount.get(), i));
    }

    public boolean usesClientTelemetry() {
        return false;
    }

    public void connect(ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        this.mConnectionProgressReportCallbacks = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        setConnectState(2, null);
    }

    public void disconnect(String str) {
        this.lastDisconnectMessage = str;
        disconnect();
    }
}
