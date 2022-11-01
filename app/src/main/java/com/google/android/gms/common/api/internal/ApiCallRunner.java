package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ApiCallRunner {
    public final int type;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class FeatureRunner extends ApiCallRunner {
        public FeatureRunner(int i) {
            super(i);
        }

        public abstract Feature[] getRequiredFeatures(GoogleApiManager.ClientConnection clientConnection);

        public abstract boolean shouldAutoResolveMissingFeatures(GoogleApiManager.ClientConnection clientConnection);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UnregisterListenerRunner extends ListenerApiCallRunner {
        public final ListenerHolder$ListenerKey unregisterKey;

        public UnregisterListenerRunner(ListenerHolder$ListenerKey listenerHolder$ListenerKey, TaskCompletionSource taskCompletionSource) {
            super(4, taskCompletionSource);
            this.unregisterKey = listenerHolder$ListenerKey;
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.ListenerApiCallRunner
        public void doExecute(GoogleApiManager.ClientConnection clientConnection) {
            RegisteredListener registeredListener = (RegisteredListener) clientConnection.getRegisteredListeners().remove(this.unregisterKey);
            if (registeredListener != null) {
                registeredListener.unregisterMethod.unregisterListener(clientConnection.getClient(), this.completionSource);
                registeredListener.registerMethod.clearListener();
                return;
            }
            this.completionSource.trySetResult(false);
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.FeatureRunner
        public Feature[] getRequiredFeatures(GoogleApiManager.ClientConnection clientConnection) {
            RegisteredListener registeredListener = (RegisteredListener) clientConnection.getRegisteredListeners().get(this.unregisterKey);
            if (registeredListener == null) {
                return null;
            }
            return registeredListener.registerMethod.getRequiredFeatures();
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.ListenerApiCallRunner, com.google.android.gms.common.api.internal.ApiCallRunner
        public /* bridge */ /* synthetic */ void reportFailure(Status status) {
            super.reportFailure(status);
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.FeatureRunner
        public boolean shouldAutoResolveMissingFeatures(GoogleApiManager.ClientConnection clientConnection) {
            RegisteredListener registeredListener = (RegisteredListener) clientConnection.getRegisteredListeners().get(this.unregisterKey);
            return registeredListener != null && registeredListener.registerMethod.shouldAutoResolveMissingFeatures();
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.ListenerApiCallRunner, com.google.android.gms.common.api.internal.ApiCallRunner
        public /* bridge */ /* synthetic */ void trackAsInProgressCall(ConnectionlessInProgressCalls connectionlessInProgressCalls, boolean z) {
            super.trackAsInProgressCall(connectionlessInProgressCalls, z);
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.ListenerApiCallRunner, com.google.android.gms.common.api.internal.ApiCallRunner
        public /* bridge */ /* synthetic */ void reportFailure(Exception exc) {
            super.reportFailure(exc);
        }
    }

    public ApiCallRunner(int i) {
        this.type = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Status createFailureStatus(RemoteException remoteException) {
        StringBuilder sb = new StringBuilder();
        sb.append(remoteException.getClass().getSimpleName()).append(": ").append(remoteException.getLocalizedMessage());
        return new Status(19, sb.toString());
    }

    public abstract void reportFailure(Status status);

    public abstract void reportFailure(Exception exc);

    public abstract void run(GoogleApiManager.ClientConnection clientConnection);

    public abstract void trackAsInProgressCall(ConnectionlessInProgressCalls connectionlessInProgressCalls, boolean z);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class ListenerApiCallRunner extends FeatureRunner {
        protected final TaskCompletionSource completionSource;

        public ListenerApiCallRunner(int i, TaskCompletionSource taskCompletionSource) {
            super(i);
            this.completionSource = taskCompletionSource;
        }

        protected abstract void doExecute(GoogleApiManager.ClientConnection clientConnection);

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public void reportFailure(Status status) {
            this.completionSource.trySetException(new ApiException(status));
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public final void run(GoogleApiManager.ClientConnection clientConnection) {
            try {
                doExecute(clientConnection);
            } catch (DeadObjectException e) {
                reportFailure(ApiCallRunner.createFailureStatus(e));
                throw e;
            } catch (RemoteException e2) {
                reportFailure(ApiCallRunner.createFailureStatus(e2));
            } catch (RuntimeException e3) {
                reportFailure(e3);
            }
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public void trackAsInProgressCall(ConnectionlessInProgressCalls connectionlessInProgressCalls, boolean z) {
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public void reportFailure(Exception exc) {
            this.completionSource.trySetException(exc);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TaskRunner extends FeatureRunner {
        private final TaskCompletionSource completionSource;
        private final TaskApiCall method;
        private final StatusExceptionMapper statusExceptionMapper;

        public TaskRunner(int i, TaskApiCall taskApiCall, TaskCompletionSource taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
            super(i);
            this.completionSource = taskCompletionSource;
            this.method = taskApiCall;
            this.statusExceptionMapper = statusExceptionMapper;
            if (i == 2 && taskApiCall.shouldAutoResolveMissingFeatures()) {
                throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
            }
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.FeatureRunner
        public Feature[] getRequiredFeatures(GoogleApiManager.ClientConnection clientConnection) {
            return this.method.getFeatures();
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public void reportFailure(Status status) {
            this.completionSource.trySetException(this.statusExceptionMapper.getException(status));
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public void run(GoogleApiManager.ClientConnection clientConnection) {
            try {
                this.method.doExecute(clientConnection.getClient(), this.completionSource);
            } catch (DeadObjectException e) {
                throw e;
            } catch (RemoteException e2) {
                reportFailure(ApiCallRunner.createFailureStatus(e2));
            } catch (RuntimeException e3) {
                reportFailure(e3);
            }
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner.FeatureRunner
        public boolean shouldAutoResolveMissingFeatures(GoogleApiManager.ClientConnection clientConnection) {
            return this.method.shouldAutoResolveMissingFeatures();
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public void trackAsInProgressCall(ConnectionlessInProgressCalls connectionlessInProgressCalls, boolean z) {
            connectionlessInProgressCalls.add(this.completionSource, z);
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public void reportFailure(Exception exc) {
            this.completionSource.trySetException(exc);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class PendingResultApiCallRunner extends ApiCallRunner {
        protected final BaseImplementation$ApiMethodImpl apiMethod;

        public PendingResultApiCallRunner(int i, BaseImplementation$ApiMethodImpl baseImplementation$ApiMethodImpl) {
            super(i);
            this.apiMethod = (BaseImplementation$ApiMethodImpl) Preconditions.checkNotNull(baseImplementation$ApiMethodImpl, "Null methods are not runnable.");
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public void reportFailure(Status status) {
            try {
                this.apiMethod.setFailedResult(status);
            } catch (IllegalStateException e) {
                Log.w("ApiCallRunner", "Exception reporting failure", e);
            }
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public void run(GoogleApiManager.ClientConnection clientConnection) {
            try {
                this.apiMethod.run(clientConnection.getClient());
            } catch (RuntimeException e) {
                reportFailure(e);
            }
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public void trackAsInProgressCall(ConnectionlessInProgressCalls connectionlessInProgressCalls, boolean z) {
            connectionlessInProgressCalls.add(this.apiMethod, z);
        }

        @Override // com.google.android.gms.common.api.internal.ApiCallRunner
        public void reportFailure(Exception exc) {
            String simpleName = exc.getClass().getSimpleName();
            try {
                this.apiMethod.setFailedResult(new Status(10, simpleName + ": " + exc.getLocalizedMessage()));
            } catch (IllegalStateException e) {
                Log.w("ApiCallRunner", "Exception reporting failure", e);
            }
        }
    }
}
