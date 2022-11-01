package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.ApiCallRunner;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.api.internal.SignInCoordinator;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.service.TelemetryLoggingClientImpl;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleApiManager implements Handler.Callback {
    public static final int CALL_TYPE_BEST_EFFORT_WRITE = 2;
    public static final int CALL_TYPE_READ = 0;
    public static final int CALL_TYPE_REGISTER_LISTENER = 3;
    public static final int CALL_TYPE_UNREGISTER_LISTENER = 4;
    public static final int CALL_TYPE_WRITE = 1;
    private static GoogleApiManager instance;
    private final GoogleApiAvailability apiAvailability;
    private final GoogleApiAvailabilityCache apiAvailabilityCache;
    private volatile boolean autoResolveAvailabilityIssues;
    private final Context context;
    private final Handler handler;
    private TelemetryData telemetryData;
    private TelemetryLoggingClient telemetryLoggingClient;
    public static final Status SIGNED_OUT = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status SIGN_IN_REQUIRED = new Status(4, "The user must be signed in to make this API call.");
    private static final Object lock = new Object();
    private long resumeDelayMs = GmsClientSupervisor.DEFAULT_UNBIND_DELAY_MILLIS;
    private long resumeTimeoutMs = 120000;
    private long serviceConnectionTimeoutMs = 10000;
    private boolean telemetryUnavailable = false;
    private final AtomicInteger nextApiInstanceId = new AtomicInteger(1);
    private final AtomicInteger signOutCount = new AtomicInteger(0);
    private final Map apiMap = new ConcurrentHashMap(5, 0.75f, 1);
    private ConnectionlessLifecycleHelper activeLifecycleHelper = null;
    private final Set activeLifecycleHelperApis = new ArraySet();
    private final Set authenticatedApis = new ArraySet();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ClientConnection implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        private final ApiKey apiKey;
        private final Api.Client client;
        private final ConnectionlessInProgressCalls inProgressCalls;
        private final int instanceId;
        private boolean resuming;
        private final SignInCoordinator signInCoordinator;
        private final Queue methodQueue = new LinkedList();
        private final Set availabilityCallbacks = new HashSet();
        private final Map registeredListeners = new HashMap();
        private final List retryingFeatures = new ArrayList();
        private ConnectionResult opportunisticConnectionFailure = null;
        private int numMethodInvocationsLogged = 0;

        public ClientConnection(GoogleApi googleApi) {
            Api.Client buildApiClient = googleApi.buildApiClient(GoogleApiManager.this.handler.getLooper(), this);
            this.client = buildApiClient;
            this.apiKey = googleApi.getApiKey();
            this.inProgressCalls = new ConnectionlessInProgressCalls();
            this.instanceId = googleApi.getInstanceId();
            if (buildApiClient.requiresSignIn()) {
                this.signInCoordinator = googleApi.createSignInCoordinator(GoogleApiManager.this.context, GoogleApiManager.this.handler);
            } else {
                this.signInCoordinator = null;
            }
        }

        private void callAndClearAvailabilityCallbacks(ConnectionResult connectionResult) {
            String str;
            for (AvailabilityTaskWrapper availabilityTaskWrapper : this.availabilityCallbacks) {
                if (Objects.equal(connectionResult, ConnectionResult.RESULT_SUCCESS)) {
                    str = this.client.getEndpointPackageName();
                } else {
                    str = null;
                }
                availabilityTaskWrapper.setApiResult(this.apiKey, connectionResult, str);
            }
            this.availabilityCallbacks.clear();
        }

        private boolean checkFeaturesAndRun(ApiCallRunner apiCallRunner) {
            if (!(apiCallRunner instanceof ApiCallRunner.FeatureRunner)) {
                run(apiCallRunner);
                return true;
            }
            ApiCallRunner.FeatureRunner featureRunner = (ApiCallRunner.FeatureRunner) apiCallRunner;
            Feature unsatisfiedFeature = getUnsatisfiedFeature(featureRunner.getRequiredFeatures(this));
            if (unsatisfiedFeature == null) {
                run(apiCallRunner);
                return true;
            }
            String name = this.client.getClass().getName();
            String name2 = unsatisfiedFeature.getName();
            Log.w("GoogleApiManager", name + " could not execute call because it requires feature (" + name2 + ", " + unsatisfiedFeature.getVersion() + ").");
            if (!BuildConstants.IS_PACKAGE_SIDE && GoogleApiManager.this.autoResolveAvailabilityIssues && featureRunner.shouldAutoResolveMissingFeatures(this)) {
                onApiCallFailedForFeature(unsatisfiedFeature);
                return false;
            }
            featureRunner.reportFailure(new UnsupportedApiCallException(unsatisfiedFeature));
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void failAllEnqueuedMethods(Status status) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            failEnqueuedMethods(status, null, false);
        }

        private void failEnqueuedMethods(Status status, Exception exc, boolean z) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if ((status == null) == (exc == null)) {
                throw new IllegalArgumentException("Status XOR exception should be null");
            }
            Iterator it = this.methodQueue.iterator();
            while (it.hasNext()) {
                ApiCallRunner apiCallRunner = (ApiCallRunner) it.next();
                if (!z || apiCallRunner.type == 2) {
                    if (status != null) {
                        apiCallRunner.reportFailure(status);
                    } else {
                        apiCallRunner.reportFailure(exc);
                    }
                    it.remove();
                }
            }
        }

        private void failEnqueuedMethodsForFeature(Feature feature) {
            Feature[] requiredFeatures;
            ArrayList arrayList = new ArrayList(this.methodQueue.size());
            for (ApiCallRunner apiCallRunner : this.methodQueue) {
                if ((apiCallRunner instanceof ApiCallRunner.FeatureRunner) && (requiredFeatures = ((ApiCallRunner.FeatureRunner) apiCallRunner).getRequiredFeatures(this)) != null && ArrayUtils.contains(requiredFeatures, feature)) {
                    arrayList.add(apiCallRunner);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ApiCallRunner apiCallRunner2 = (ApiCallRunner) it.next();
                this.methodQueue.remove(apiCallRunner2);
                apiCallRunner2.reportFailure(new UnsupportedApiCallException(feature));
            }
        }

        private void flushQueue() {
            Iterator it = new ArrayList(this.methodQueue).iterator();
            while (it.hasNext()) {
                ApiCallRunner apiCallRunner = (ApiCallRunner) it.next();
                if (this.client.isConnected()) {
                    if (checkFeaturesAndRun(apiCallRunner)) {
                        this.methodQueue.remove(apiCallRunner);
                    }
                } else {
                    return;
                }
            }
        }

        private Feature getUnsatisfiedFeature(Feature[] featureArr) {
            if (featureArr == null || featureArr.length == 0) {
                return null;
            }
            Feature[] availableFeatures = getClient().getAvailableFeatures();
            if (availableFeatures == null) {
                availableFeatures = new Feature[0];
            }
            ArrayMap arrayMap = new ArrayMap(availableFeatures.length);
            for (Feature feature : availableFeatures) {
                arrayMap.put(feature.getName(), Long.valueOf(feature.getVersion()));
            }
            for (Feature feature2 : featureArr) {
                Long l = (Long) arrayMap.get(feature2.getName());
                if (l == null || l.longValue() < feature2.getVersion()) {
                    return feature2;
                }
            }
            return null;
        }

        private void onApiCallFailedForFeature(Feature feature) {
            FeatureApiKey featureApiKey = new FeatureApiKey(this.apiKey, feature);
            int indexOf = this.retryingFeatures.indexOf(featureApiKey);
            if (indexOf >= 0) {
                FeatureApiKey featureApiKey2 = (FeatureApiKey) this.retryingFeatures.get(indexOf);
                GoogleApiManager.this.handler.removeMessages(15, featureApiKey2);
                GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 15, featureApiKey2), GoogleApiManager.this.resumeDelayMs);
                return;
            }
            this.retryingFeatures.add(featureApiKey);
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 15, featureApiKey), GoogleApiManager.this.resumeDelayMs);
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 16, featureApiKey), GoogleApiManager.this.resumeTimeoutMs);
            ConnectionResult connectionResult = new ConnectionResult(2, null);
            if (!startResolutionWithLifecycleHelper(connectionResult)) {
                GoogleApiManager.this.showErrorNotificationIfNeeded(connectionResult, this.instanceId);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onConnectedInternal() {
            clearOpportunisticConnectionFailure();
            callAndClearAvailabilityCallbacks(ConnectionResult.RESULT_SUCCESS);
            stopResuming();
            Iterator it = this.registeredListeners.values().iterator();
            while (it.hasNext()) {
                RegisteredListener registeredListener = (RegisteredListener) it.next();
                if (getUnsatisfiedFeature(registeredListener.registerMethod.getRequiredFeatures()) != null) {
                    it.remove();
                } else {
                    try {
                        registeredListener.registerMethod.registerListener(this.client, new TaskCompletionSource());
                    } catch (DeadObjectException e) {
                        onConnectionSuspended(3);
                        this.client.disconnect("DeadObjectException thrown while calling register listener method.");
                    } catch (RemoteException e2) {
                        it.remove();
                    }
                }
            }
            flushQueue();
            resetServiceConnectionTimeout();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onConnectionSuspendedInternal(int i) {
            clearOpportunisticConnectionFailure();
            this.resuming = true;
            this.inProgressCalls.failAllCallsDueToConnectionSuspended(i, this.client.getLastDisconnectMessage());
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 9, this.apiKey), GoogleApiManager.this.resumeDelayMs);
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 11, this.apiKey), GoogleApiManager.this.resumeTimeoutMs);
            GoogleApiManager.this.apiAvailabilityCache.flush();
            for (RegisteredListener registeredListener : this.registeredListeners.values()) {
                registeredListener.onConnectionSuspended.run();
            }
        }

        private void resetServiceConnectionTimeout() {
            GoogleApiManager.this.handler.removeMessages(12, this.apiKey);
            GoogleApiManager.this.handler.sendMessageDelayed(GoogleApiManager.this.handler.obtainMessage(12, this.apiKey), GoogleApiManager.this.serviceConnectionTimeoutMs);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void retryCallsWithMissingFeatures(FeatureApiKey featureApiKey) {
            if (this.retryingFeatures.contains(featureApiKey) && !this.resuming) {
                if (!this.client.isConnected()) {
                    connect();
                } else {
                    flushQueue();
                }
            }
        }

        private void run(ApiCallRunner apiCallRunner) {
            apiCallRunner.trackAsInProgressCall(this.inProgressCalls, requiresSignIn());
            try {
                apiCallRunner.run(this);
            } catch (DeadObjectException e) {
                onConnectionSuspended(1);
                this.client.disconnect("DeadObjectException thrown while running ApiCallRunner.");
            }
        }

        private boolean startResolutionWithLifecycleHelper(ConnectionResult connectionResult) {
            synchronized (GoogleApiManager.lock) {
                if (GoogleApiManager.this.activeLifecycleHelper == null || !GoogleApiManager.this.activeLifecycleHelperApis.contains(this.apiKey)) {
                    return false;
                }
                GoogleApiManager.this.activeLifecycleHelper.beginFailureResolution(connectionResult, this.instanceId);
                return true;
            }
        }

        private Status statusForFailedConnection(ConnectionResult connectionResult) {
            return GoogleApiManager.statusForFailedConnection(this.apiKey, connectionResult);
        }

        private void stopResuming() {
            if (this.resuming) {
                GoogleApiManager.this.handler.removeMessages(11, this.apiKey);
                GoogleApiManager.this.handler.removeMessages(9, this.apiKey);
                this.resuming = false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void timeoutRetryingCallsForMissingFeatures(FeatureApiKey featureApiKey) {
            if (this.retryingFeatures.remove(featureApiKey)) {
                GoogleApiManager.this.handler.removeMessages(15, featureApiKey);
                GoogleApiManager.this.handler.removeMessages(16, featureApiKey);
                failEnqueuedMethodsForFeature(featureApiKey.feature);
            }
        }

        public void clearOpportunisticConnectionFailure() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.opportunisticConnectionFailure = null;
        }

        public void connect() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (!this.client.isConnected() && !this.client.isConnecting()) {
                try {
                    int clientAvailability = GoogleApiManager.this.apiAvailabilityCache.getClientAvailability(GoogleApiManager.this.context, this.client);
                    if (clientAvailability == 0) {
                        GoogleApiProgressCallbacks googleApiProgressCallbacks = new GoogleApiProgressCallbacks(this.client, this.apiKey);
                        if (this.client.requiresSignIn()) {
                            ((SignInCoordinator) Preconditions.checkNotNull(this.signInCoordinator)).startSignIn(googleApiProgressCallbacks);
                        }
                        try {
                            this.client.connect(googleApiProgressCallbacks);
                            return;
                        } catch (SecurityException e) {
                            onConnectionFailed(new ConnectionResult(10), e);
                            return;
                        }
                    }
                    ConnectionResult connectionResult = new ConnectionResult(clientAvailability, null);
                    String name = this.client.getClass().getName();
                    Log.w("GoogleApiManager", "The service for " + name + " is not available: " + String.valueOf(connectionResult));
                    onConnectionFailed(connectionResult);
                } catch (IllegalStateException e2) {
                    onConnectionFailed(new ConnectionResult(10), e2);
                }
            }
        }

        public void enqueue(ApiCallRunner apiCallRunner) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.client.isConnected()) {
                if (checkFeaturesAndRun(apiCallRunner)) {
                    resetServiceConnectionTimeout();
                    return;
                } else {
                    this.methodQueue.add(apiCallRunner);
                    return;
                }
            }
            this.methodQueue.add(apiCallRunner);
            ConnectionResult connectionResult = this.opportunisticConnectionFailure;
            if (connectionResult != null && connectionResult.hasResolution()) {
                onConnectionFailed(this.opportunisticConnectionFailure);
            } else {
                connect();
            }
        }

        public Api.Client getClient() {
            return this.client;
        }

        public int getInstanceId() {
            return this.instanceId;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int getNumMethodInvocationsLogged() {
            return this.numMethodInvocationsLogged;
        }

        public ConnectionResult getOpportunisticConnectionFailure() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            return this.opportunisticConnectionFailure;
        }

        public Map getRegisteredListeners() {
            return this.registeredListeners;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void incrementNumMethodInvocationsLogged() {
            this.numMethodInvocationsLogged++;
        }

        boolean isConnected() {
            return this.client.isConnected();
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public void onConnected(Bundle bundle) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                onConnectedInternal();
            } else {
                GoogleApiManager.this.handler.post(new Runnable() { // from class: com.google.android.gms.common.api.internal.GoogleApiManager.ClientConnection.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ClientConnection.this.onConnectedInternal();
                    }
                });
            }
        }

        @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
        public void onConnectionFailed(ConnectionResult connectionResult) {
            onConnectionFailed(connectionResult, null);
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public void onConnectionSuspended(final int i) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                onConnectionSuspendedInternal(i);
            } else {
                GoogleApiManager.this.handler.post(new Runnable() { // from class: com.google.android.gms.common.api.internal.GoogleApiManager.ClientConnection.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ClientConnection.this.onConnectionSuspendedInternal(i);
                    }
                });
            }
        }

        public void onSignInFailed(ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            Api.Client client = this.client;
            String name = client.getClass().getName();
            client.disconnect("onSignInFailed for " + name + " with " + String.valueOf(connectionResult));
            onConnectionFailed(connectionResult);
        }

        public void registerAvailabilityCallback(AvailabilityTaskWrapper availabilityTaskWrapper) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.availabilityCallbacks.add(availabilityTaskWrapper);
        }

        public boolean requiresSignIn() {
            return this.client.requiresSignIn();
        }

        public void resume() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.resuming) {
                connect();
            }
        }

        public void signOut() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            failAllEnqueuedMethods(GoogleApiManager.SIGNED_OUT);
            this.inProgressCalls.failAuthenticatedCalls();
            for (ListenerHolder$ListenerKey listenerHolder$ListenerKey : (ListenerHolder$ListenerKey[]) this.registeredListeners.keySet().toArray(new ListenerHolder$ListenerKey[0])) {
                enqueue(new ApiCallRunner.UnregisterListenerRunner(listenerHolder$ListenerKey, new TaskCompletionSource()));
            }
            callAndClearAvailabilityCallbacks(new ConnectionResult(4));
            if (this.client.isConnected()) {
                this.client.onUserSignOut(new BaseGmsClient.SignOutCallbacks() { // from class: com.google.android.gms.common.api.internal.GoogleApiManager.ClientConnection.4
                    @Override // com.google.android.gms.common.internal.BaseGmsClient.SignOutCallbacks
                    public void onSignOutComplete() {
                        GoogleApiManager.this.handler.post(new Runnable() { // from class: com.google.android.gms.common.api.internal.GoogleApiManager.ClientConnection.4.1
                            @Override // java.lang.Runnable
                            public void run() {
                                ClientConnection.this.client.disconnect(ClientConnection.this.client.getClass().getName() + " disconnecting because it was signed out.");
                            }
                        });
                    }
                });
            }
        }

        public void timeoutResuming() {
            Status status;
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.resuming) {
                stopResuming();
                if (GoogleApiManager.this.apiAvailability.isGooglePlayServicesAvailable(GoogleApiManager.this.context) == 18) {
                    status = new Status(21, "Connection timed out waiting for Google Play services update to complete.");
                } else {
                    status = new Status(22, "API failed to connect while resuming due to an unknown error.");
                }
                failAllEnqueuedMethods(status);
                this.client.disconnect("Timing out connection while resuming.");
            }
        }

        public boolean timeoutServiceConnection() {
            return timeoutServiceConnection(true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean timeoutServiceConnection(boolean z) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.client.isConnected() && this.registeredListeners.size() == 0) {
                if (this.inProgressCalls.containsCallInProgress()) {
                    if (z) {
                        resetServiceConnectionTimeout();
                    }
                    return false;
                }
                this.client.disconnect("Timing out service connection.");
                return true;
            }
            return false;
        }

        public void onConnectionFailed(ConnectionResult connectionResult, Exception exc) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            SignInCoordinator signInCoordinator = this.signInCoordinator;
            if (signInCoordinator != null) {
                signInCoordinator.stopSignIn();
            }
            clearOpportunisticConnectionFailure();
            GoogleApiManager.this.apiAvailabilityCache.flush();
            callAndClearAvailabilityCallbacks(connectionResult);
            if ((this.client instanceof TelemetryLoggingClientImpl) && connectionResult.getErrorCode() != 24) {
                GoogleApiManager.this.telemetryUnavailable = true;
                GoogleApiManager.this.handler.sendMessageDelayed(GoogleApiManager.this.handler.obtainMessage(19), 300000L);
            }
            if (connectionResult.getErrorCode() == 4) {
                failAllEnqueuedMethods(GoogleApiManager.SIGN_IN_REQUIRED);
            } else if (this.methodQueue.isEmpty()) {
                this.opportunisticConnectionFailure = connectionResult;
            } else if (exc != null) {
                failAllEnqueuedMethods(exc);
            } else if (!GoogleApiManager.this.autoResolveAvailabilityIssues) {
                failAllEnqueuedMethods(statusForFailedConnection(connectionResult));
            } else {
                failEnqueuedMethods(statusForFailedConnection(connectionResult), null, true);
                if (!this.methodQueue.isEmpty() && !startResolutionWithLifecycleHelper(connectionResult) && !GoogleApiManager.this.showErrorNotificationIfNeeded(connectionResult, this.instanceId)) {
                    if (connectionResult.getErrorCode() == 18) {
                        this.resuming = true;
                    }
                    if (this.resuming) {
                        GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 9, this.apiKey), GoogleApiManager.this.resumeDelayMs);
                    } else {
                        failAllEnqueuedMethods(statusForFailedConnection(connectionResult));
                    }
                }
            }
        }

        private void failAllEnqueuedMethods(Exception exc) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            failEnqueuedMethods(null, exc, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class FeatureApiKey {
        private final Feature feature;
        private final ApiKey key;

        private FeatureApiKey(ApiKey apiKey, Feature feature) {
            this.key = apiKey;
            this.feature = feature;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof FeatureApiKey)) {
                return false;
            }
            FeatureApiKey featureApiKey = (FeatureApiKey) obj;
            return Objects.equal(this.key, featureApiKey.key) && Objects.equal(this.feature, featureApiKey.feature);
        }

        public int hashCode() {
            return Objects.hashCode(this.key, this.feature);
        }

        public String toString() {
            return Objects.toStringHelper(this).add("key", this.key).add("feature", this.feature).toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class GoogleApiProgressCallbacks implements SignInCoordinator.SignInCallback, BaseGmsClient.ConnectionProgressReportCallbacks {
        private final ApiKey apiKey;
        private final Api.Client client;
        private IAccountAccessor resolvedAccountAccessor = null;
        private Set scopes = null;
        private boolean serviceBound = false;

        public GoogleApiProgressCallbacks(Api.Client client, ApiKey apiKey) {
            this.client = client;
            this.apiKey = apiKey;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void tryGetRemoteService() {
            IAccountAccessor iAccountAccessor;
            if (this.serviceBound && (iAccountAccessor = this.resolvedAccountAccessor) != null) {
                this.client.getRemoteService(iAccountAccessor, this.scopes);
            }
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
        public void onReportServiceBinding(final ConnectionResult connectionResult) {
            GoogleApiManager.this.handler.post(new Runnable() { // from class: com.google.android.gms.common.api.internal.GoogleApiManager.GoogleApiProgressCallbacks.1
                @Override // java.lang.Runnable
                public void run() {
                    ClientConnection clientConnection = (ClientConnection) GoogleApiManager.this.apiMap.get(GoogleApiProgressCallbacks.this.apiKey);
                    if (clientConnection == null) {
                        return;
                    }
                    if (connectionResult.isSuccess()) {
                        GoogleApiProgressCallbacks.this.serviceBound = true;
                        if (GoogleApiProgressCallbacks.this.client.requiresSignIn()) {
                            GoogleApiProgressCallbacks.this.tryGetRemoteService();
                            return;
                        }
                        try {
                            GoogleApiProgressCallbacks.this.client.getRemoteService(null, GoogleApiProgressCallbacks.this.client.getScopesForConnectionlessNonSignIn());
                            return;
                        } catch (SecurityException e) {
                            Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
                            GoogleApiProgressCallbacks.this.client.disconnect("Failed to get service from broker.");
                            clientConnection.onConnectionFailed(new ConnectionResult(10));
                            return;
                        }
                    }
                    clientConnection.onConnectionFailed(connectionResult);
                }
            });
        }

        @Override // com.google.android.gms.common.api.internal.SignInCoordinator.SignInCallback
        public void onSignInFailed(ConnectionResult connectionResult) {
            ClientConnection clientConnection = (ClientConnection) GoogleApiManager.this.apiMap.get(this.apiKey);
            if (clientConnection != null) {
                clientConnection.onSignInFailed(connectionResult);
            }
        }

        @Override // com.google.android.gms.common.api.internal.SignInCoordinator.SignInCallback
        public void onSignInSuccess(IAccountAccessor iAccountAccessor, Set set) {
            if (iAccountAccessor != null && set != null) {
                this.resolvedAccountAccessor = iAccountAccessor;
                this.scopes = set;
                tryGetRemoteService();
                return;
            }
            Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
            onSignInFailed(new ConnectionResult(4));
        }
    }

    private GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.autoResolveAvailabilityIssues = true;
        this.context = context;
        TracingHandler tracingHandler = new TracingHandler(looper, this);
        this.handler = tracingHandler;
        this.apiAvailability = googleApiAvailability;
        this.apiAvailabilityCache = new GoogleApiAvailabilityCache(googleApiAvailability);
        if (DeviceProperties.isAuto(context)) {
            this.autoResolveAvailabilityIssues = false;
        }
        tracingHandler.sendMessage(tracingHandler.obtainMessage(6));
    }

    private void checkApiAvailabilityInternal(AvailabilityTaskWrapper availabilityTaskWrapper) {
        for (ApiKey apiKey : availabilityTaskWrapper.getRequestedApis()) {
            ClientConnection clientConnection = (ClientConnection) this.apiMap.get(apiKey);
            if (clientConnection != null) {
                if (clientConnection.isConnected()) {
                    availabilityTaskWrapper.setApiResult(apiKey, ConnectionResult.RESULT_SUCCESS, clientConnection.getClient().getEndpointPackageName());
                } else {
                    ConnectionResult opportunisticConnectionFailure = clientConnection.getOpportunisticConnectionFailure();
                    if (opportunisticConnectionFailure != null) {
                        availabilityTaskWrapper.setApiResult(apiKey, opportunisticConnectionFailure, null);
                    } else {
                        clientConnection.registerAvailabilityCallback(availabilityTaskWrapper);
                        clientConnection.connect();
                    }
                }
            } else {
                availabilityTaskWrapper.setApiResult(apiKey, new ConnectionResult(13), null);
                return;
            }
        }
    }

    private void disconnectServiceInternal(DisconnectTaskWrapper disconnectTaskWrapper) {
        ApiKey apiKey = disconnectTaskWrapper.getApiKey();
        if (!this.apiMap.containsKey(apiKey)) {
            disconnectTaskWrapper.getTaskCompletionSource().setResult(false);
            return;
        }
        disconnectTaskWrapper.getTaskCompletionSource().setResult(Boolean.valueOf(((ClientConnection) this.apiMap.get(apiKey)).timeoutServiceConnection(false)));
    }

    private void executeInternal(QueuedApiCall queuedApiCall) {
        ClientConnection clientConnection = (ClientConnection) this.apiMap.get(queuedApiCall.mGoogleApi.getApiKey());
        if (clientConnection == null) {
            clientConnection = registerInternal(queuedApiCall.mGoogleApi);
        }
        if (clientConnection.requiresSignIn() && this.signOutCount.get() != queuedApiCall.mSignOutCount) {
            queuedApiCall.mRunner.reportFailure(SIGNED_OUT);
            clientConnection.signOut();
            return;
        }
        clientConnection.enqueue(queuedApiCall.mRunner);
    }

    public static GoogleApiManager getInstance(Context context) {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            if (instance == null) {
                instance = new GoogleApiManager(context.getApplicationContext(), GmsClientSupervisor.getOrStartHandlerThread().getLooper(), GoogleApiAvailability.getInstance());
            }
            googleApiManager = instance;
        }
        return googleApiManager;
    }

    private TelemetryLoggingClient getTelemetryLoggingClient() {
        if (this.telemetryLoggingClient == null) {
            this.telemetryLoggingClient = TelemetryLogging.getClient(this.context);
        }
        return this.telemetryLoggingClient;
    }

    private void initializeInternal() {
        if (this.context.getApplicationContext() instanceof Application) {
            BackgroundDetector.initialize((Application) this.context.getApplicationContext());
            BackgroundDetector.getInstance().addListener(new BackgroundDetector.BackgroundStateChangeListener() { // from class: com.google.android.gms.common.api.internal.GoogleApiManager.1
                @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
                public void onBackgroundStateChanged(boolean z) {
                    GoogleApiManager.this.handler.sendMessage(GoogleApiManager.this.handler.obtainMessage(1, Boolean.valueOf(z)));
                }
            });
            if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                this.serviceConnectionTimeoutMs = 300000L;
            }
        }
    }

    private void logAndResetTelemetryData() {
        TelemetryData telemetryData = this.telemetryData;
        if (telemetryData != null) {
            if (telemetryData.getTelemetryConfigVersion() > 0 || isClientTelemetryPossiblyEnabled()) {
                getTelemetryLoggingClient().log(telemetryData);
            }
            this.telemetryData = null;
        }
    }

    private void logMethodInvocationInternal(MethodInvocationMessage methodInvocationMessage) {
        if (methodInvocationMessage.batchPeriodMillis == 0) {
            getTelemetryLoggingClient().log(new TelemetryData(methodInvocationMessage.configVersion, Arrays.asList(methodInvocationMessage.methodInvocation)));
            return;
        }
        TelemetryData telemetryData = this.telemetryData;
        if (telemetryData != null) {
            List methodInvocations = telemetryData.getMethodInvocations();
            if (this.telemetryData.getTelemetryConfigVersion() == methodInvocationMessage.configVersion && (methodInvocations == null || methodInvocations.size() < methodInvocationMessage.maxMethodsInBatch)) {
                this.telemetryData.addMethodInvocation(methodInvocationMessage.methodInvocation);
            } else {
                this.handler.removeMessages(17);
                logAndResetTelemetryData();
            }
        }
        if (this.telemetryData == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(methodInvocationMessage.methodInvocation);
            this.telemetryData = new TelemetryData(methodInvocationMessage.configVersion, arrayList);
            Handler handler = this.handler;
            handler.sendMessageDelayed(handler.obtainMessage(17), methodInvocationMessage.batchPeriodMillis);
        }
    }

    private void maybeAddInvocationListener(TaskCompletionSource taskCompletionSource, int i, GoogleApi googleApi) {
        MethodInvocationLoggingListener maybeMakeListener;
        if (i != 0 && (maybeMakeListener = MethodInvocationLoggingListener.maybeMakeListener(this, i, googleApi.getApiKey())) != null) {
            Task task = taskCompletionSource.getTask();
            final Handler handler = this.handler;
            handler.getClass();
            task.addOnCompleteListener(new Executor() { // from class: com.google.android.gms.common.api.internal.GoogleApiManager$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Executor
                public final void execute(Runnable runnable) {
                    handler.post(runnable);
                }
            }, maybeMakeListener);
        }
    }

    private void onErrorsResolvedInternal() {
        for (ClientConnection clientConnection : this.apiMap.values()) {
            clientConnection.clearOpportunisticConnectionFailure();
            clientConnection.connect();
        }
    }

    private ClientConnection registerInternal(GoogleApi googleApi) {
        ApiKey apiKey = googleApi.getApiKey();
        ClientConnection clientConnection = (ClientConnection) this.apiMap.get(apiKey);
        if (clientConnection == null) {
            clientConnection = new ClientConnection(googleApi);
            this.apiMap.put(apiKey, clientConnection);
        }
        if (clientConnection.requiresSignIn()) {
            this.authenticatedApis.add(apiKey);
        }
        clientConnection.connect();
        return clientConnection;
    }

    public static void reportSignOut() {
        synchronized (lock) {
            GoogleApiManager googleApiManager = instance;
            if (googleApiManager != null) {
                googleApiManager.signOut();
            }
        }
    }

    private void signOutInternal() {
        for (ApiKey apiKey : this.authenticatedApis) {
            ClientConnection clientConnection = (ClientConnection) this.apiMap.remove(apiKey);
            if (clientConnection != null) {
                clientConnection.signOut();
            }
        }
        this.authenticatedApis.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Status statusForFailedConnection(ApiKey apiKey, ConnectionResult connectionResult) {
        String apiName = apiKey.getApiName();
        return new Status(connectionResult, "API: " + apiName + " is not available on this device. Connection failed with: " + String.valueOf(connectionResult));
    }

    public void execute(GoogleApi googleApi, int i, BaseImplementation$ApiMethodImpl baseImplementation$ApiMethodImpl) {
        ApiCallRunner.PendingResultApiCallRunner pendingResultApiCallRunner = new ApiCallRunner.PendingResultApiCallRunner(i, baseImplementation$ApiMethodImpl);
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(4, new QueuedApiCall(pendingResultApiCallRunner, this.signOutCount.get(), googleApi)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClientConnection getClientConnectionForKey(ApiKey apiKey) {
        return (ClientConnection) this.apiMap.get(apiKey);
    }

    public int getNextInstanceId() {
        return this.nextApiInstanceId.getAndIncrement();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                onBackgroundStateChangedInternal(((Boolean) message.obj).booleanValue());
                return true;
            case 2:
                checkApiAvailabilityInternal((AvailabilityTaskWrapper) message.obj);
                return true;
            case 3:
                onErrorsResolvedInternal();
                return true;
            case 4:
            case 8:
            case 13:
                executeInternal((QueuedApiCall) message.obj);
                return true;
            case 5:
                failEnqueuedInternal(message.arg1, (ConnectionResult) message.obj);
                return true;
            case 6:
                initializeInternal();
                return true;
            case 7:
                registerInternal((GoogleApi) message.obj);
                return true;
            case 9:
                if (!this.apiMap.containsKey(message.obj)) {
                    return true;
                }
                ((ClientConnection) this.apiMap.get(message.obj)).resume();
                return true;
            case 10:
                signOutInternal();
                return true;
            case 11:
                if (!this.apiMap.containsKey(message.obj)) {
                    return true;
                }
                ((ClientConnection) this.apiMap.get(message.obj)).timeoutResuming();
                return true;
            case 12:
                if (!this.apiMap.containsKey(message.obj)) {
                    return true;
                }
                ((ClientConnection) this.apiMap.get(message.obj)).timeoutServiceConnection();
                return true;
            case 14:
                disconnectServiceInternal((DisconnectTaskWrapper) message.obj);
                return true;
            case 15:
                FeatureApiKey featureApiKey = (FeatureApiKey) message.obj;
                if (!this.apiMap.containsKey(featureApiKey.key)) {
                    return true;
                }
                ((ClientConnection) this.apiMap.get(featureApiKey.key)).retryCallsWithMissingFeatures(featureApiKey);
                return true;
            case 16:
                FeatureApiKey featureApiKey2 = (FeatureApiKey) message.obj;
                if (!this.apiMap.containsKey(featureApiKey2.key)) {
                    return true;
                }
                ((ClientConnection) this.apiMap.get(featureApiKey2.key)).timeoutRetryingCallsForMissingFeatures(featureApiKey2);
                return true;
            case 17:
                logAndResetTelemetryData();
                return true;
            case 18:
                logMethodInvocationInternal((MethodInvocationMessage) message.obj);
                return true;
            case 19:
                this.telemetryUnavailable = false;
                return true;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + message.what);
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isClientTelemetryPossiblyEnabled() {
        if (this.telemetryUnavailable) {
            return false;
        }
        RootTelemetryConfiguration config = RootTelemetryConfigManager.getInstance().getConfig();
        if (config == null || config.getMethodInvocationTelemetryEnabled()) {
            int apkVersionAvailability = this.apiAvailabilityCache.getApkVersionAvailability(this.context, TelemetryLoggingClientImpl.MIN_APK_VERSION);
            return apkVersionAvailability == -1 || apkVersionAvailability == 0;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logMethodInvocation(MethodInvocation methodInvocation, int i, long j, int i2) {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(18, new MethodInvocationMessage(methodInvocation, i, j, i2)));
    }

    public void onErrorResolutionFailed(ConnectionResult connectionResult, int i) {
        if (!showErrorNotificationIfNeeded(connectionResult, i)) {
            Handler handler = this.handler;
            handler.sendMessage(handler.obtainMessage(5, i, 0, connectionResult));
        }
    }

    public void onErrorsResolved() {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(3));
    }

    public void register(GoogleApi googleApi) {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(7, googleApi));
    }

    public void registerLifecycleHelper(ConnectionlessLifecycleHelper connectionlessLifecycleHelper) {
        synchronized (lock) {
            if (this.activeLifecycleHelper != connectionlessLifecycleHelper) {
                this.activeLifecycleHelper = connectionlessLifecycleHelper;
                this.activeLifecycleHelperApis.clear();
            }
            this.activeLifecycleHelperApis.addAll(connectionlessLifecycleHelper.getManagedApiKeys());
        }
    }

    boolean showErrorNotificationIfNeeded(ConnectionResult connectionResult, int i) {
        return this.apiAvailability.showWrappedErrorNotification(this.context, connectionResult, i);
    }

    public void signOut() {
        this.signOutCount.incrementAndGet();
        Handler handler = this.handler;
        handler.sendMessageAtFrontOfQueue(handler.obtainMessage(10));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unregisterLifecycleHelper(ConnectionlessLifecycleHelper connectionlessLifecycleHelper) {
        synchronized (lock) {
            if (this.activeLifecycleHelper == connectionlessLifecycleHelper) {
                this.activeLifecycleHelper = null;
                this.activeLifecycleHelperApis.clear();
            }
        }
    }

    private void failEnqueuedInternal(int i, ConnectionResult connectionResult) {
        ClientConnection clientConnection;
        Iterator it = this.apiMap.values().iterator();
        while (true) {
            if (it.hasNext()) {
                clientConnection = (ClientConnection) it.next();
                if (clientConnection.getInstanceId() == i) {
                    break;
                }
            } else {
                clientConnection = null;
                break;
            }
        }
        if (clientConnection != null) {
            if (connectionResult.getErrorCode() == 13) {
                String errorString = this.apiAvailability.getErrorString(connectionResult.getErrorCode());
                clientConnection.failAllEnqueuedMethods(new Status(17, "Error resolution was canceled by the user, original error message: " + errorString + ": " + connectionResult.getErrorMessage()));
                return;
            }
            clientConnection.failAllEnqueuedMethods(statusForFailedConnection(clientConnection.apiKey, connectionResult));
            return;
        }
        Log.wtf("GoogleApiManager", "Could not find API instance " + i + " while trying to fail enqueued calls.", new Exception());
    }

    private void onBackgroundStateChangedInternal(boolean z) {
        this.serviceConnectionTimeoutMs = z ? 10000L : 300000L;
        this.handler.removeMessages(12);
        for (ApiKey apiKey : this.apiMap.keySet()) {
            Handler handler = this.handler;
            handler.sendMessageDelayed(handler.obtainMessage(12, apiKey), this.serviceConnectionTimeoutMs);
        }
    }

    public void execute(GoogleApi googleApi, int i, TaskApiCall taskApiCall, TaskCompletionSource taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        maybeAddInvocationListener(taskCompletionSource, taskApiCall.getMethodKey(), googleApi);
        ApiCallRunner.TaskRunner taskRunner = new ApiCallRunner.TaskRunner(i, taskApiCall, taskCompletionSource, statusExceptionMapper);
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(4, new QueuedApiCall(taskRunner, this.signOutCount.get(), googleApi)));
    }
}
