package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BaseLifecycleHelper extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected static final int DIALOG_REQUEST_CODE = 2;
    protected final GoogleApiAvailability mApiAvailability;
    private final Handler mConnectionFailedHandler;
    protected final AtomicReference mFailingConnectionResult;
    protected GooglePlayServicesUpdatedReceiver mGmsUpdatedReceiver;
    protected volatile boolean mStarted;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ClientConnectionResult {
        private final ConnectionResult mConnectionResult;
        private final int mFailingClientId;

        ClientConnectionResult(ConnectionResult connectionResult, int i) {
            Preconditions.checkNotNull(connectionResult);
            this.mConnectionResult = connectionResult;
            this.mFailingClientId = i;
        }

        ConnectionResult getConnectionResult() {
            return this.mConnectionResult;
        }

        int getFailingClientId() {
            return this.mFailingClientId;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ConnectionFailedResolver implements Runnable {
        private final ClientConnectionResult mClientConnectionResult;

        ConnectionFailedResolver(ClientConnectionResult clientConnectionResult) {
            this.mClientConnectionResult = clientConnectionResult;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!BaseLifecycleHelper.this.mStarted) {
                return;
            }
            ConnectionResult connectionResult = this.mClientConnectionResult.getConnectionResult();
            if (connectionResult.hasResolution()) {
                BaseLifecycleHelper.this.mLifecycleFragment.startActivityForResult(GoogleApiActivity.getIntentForResolution(BaseLifecycleHelper.this.getActivity(), (PendingIntent) Preconditions.checkNotNull(connectionResult.getResolution()), this.mClientConnectionResult.getFailingClientId(), false), 1);
                return;
            }
            if (BaseLifecycleHelper.this.mApiAvailability.getErrorResolutionIntent(BaseLifecycleHelper.this.getActivity(), connectionResult.getErrorCode(), null) != null) {
                BaseLifecycleHelper.this.mApiAvailability.showErrorDialogFragment(BaseLifecycleHelper.this.getActivity(), BaseLifecycleHelper.this.mLifecycleFragment, connectionResult.getErrorCode(), 2, BaseLifecycleHelper.this);
            } else if (connectionResult.getErrorCode() == 18) {
                final Dialog showUpdatingDialog = BaseLifecycleHelper.this.mApiAvailability.showUpdatingDialog(BaseLifecycleHelper.this.getActivity(), BaseLifecycleHelper.this);
                BaseLifecycleHelper.this.mApiAvailability.registerCallbackOnUpdate(BaseLifecycleHelper.this.getActivity().getApplicationContext(), new GooglePlayServicesUpdatedReceiver.Callback() { // from class: com.google.android.gms.common.api.internal.BaseLifecycleHelper.ConnectionFailedResolver.1
                    @Override // com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver.Callback
                    public void onUpdated() {
                        BaseLifecycleHelper.this.markErrorsResolved();
                        if (showUpdatingDialog.isShowing()) {
                            showUpdatingDialog.dismiss();
                        }
                    }
                });
            } else {
                BaseLifecycleHelper.this.markErrorResolutionFailed(connectionResult, this.mClientConnectionResult.getFailingClientId());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseLifecycleHelper(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.mFailingConnectionResult = new AtomicReference(null);
        this.mConnectionFailedHandler = new TracingHandler(Looper.getMainLooper());
        this.mApiAvailability = googleApiAvailability;
    }

    private void addClientConnectionResultToBundle(Bundle bundle) {
        ClientConnectionResult clientConnectionResult = (ClientConnectionResult) this.mFailingConnectionResult.get();
        if (clientConnectionResult == null) {
            return;
        }
        bundle.putBoolean("resolving_error", true);
        bundle.putInt("failed_client_id", clientConnectionResult.getFailingClientId());
        bundle.putInt("failed_status", clientConnectionResult.getConnectionResult().getErrorCode());
        bundle.putParcelable("failed_resolution", clientConnectionResult.getConnectionResult().getResolution());
    }

    private static ClientConnectionResult clientConnectionResultFromBundle(Bundle bundle) {
        if (bundle.getBoolean("resolving_error", false)) {
            return new ClientConnectionResult(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1));
        }
        return null;
    }

    private int getFailingClientId(ClientConnectionResult clientConnectionResult) {
        if (clientConnectionResult == null) {
            return -1;
        }
        return clientConnectionResult.getFailingClientId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void markErrorResolutionFailed(ConnectionResult connectionResult, int i) {
        this.mFailingConnectionResult.set(null);
        GooglePlayServicesUpdatedReceiver googlePlayServicesUpdatedReceiver = this.mGmsUpdatedReceiver;
        if (googlePlayServicesUpdatedReceiver != null) {
            googlePlayServicesUpdatedReceiver.unregister();
            this.mGmsUpdatedReceiver = null;
        }
        onErrorResolutionFailed(connectionResult, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void markErrorsResolved() {
        this.mFailingConnectionResult.set(null);
        GooglePlayServicesUpdatedReceiver googlePlayServicesUpdatedReceiver = this.mGmsUpdatedReceiver;
        if (googlePlayServicesUpdatedReceiver != null) {
            googlePlayServicesUpdatedReceiver.unregister();
            this.mGmsUpdatedReceiver = null;
        }
        onErrorsResolved();
    }

    public void beginFailureResolution(ConnectionResult connectionResult, int i) {
        ClientConnectionResult clientConnectionResult = new ClientConnectionResult(connectionResult, i);
        if (BaseLifecycleHelper$$ExternalSyntheticBackportWithForwarding0.m(this.mFailingConnectionResult, null, clientConnectionResult)) {
            this.mConnectionFailedHandler.post(new ConnectionFailedResolver(clientConnectionResult));
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onActivityResult(int i, int i2, Intent intent) {
        ClientConnectionResult clientConnectionResult = (ClientConnectionResult) this.mFailingConnectionResult.get();
        switch (i) {
            case 1:
                if (i2 == -1) {
                    markErrorsResolved();
                    return;
                } else if (i2 == 0) {
                    if (clientConnectionResult == null) {
                        return;
                    }
                    markErrorResolutionFailed(new ConnectionResult(intent != null ? intent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13) : 13, null, clientConnectionResult.getConnectionResult().toString()), getFailingClientId(clientConnectionResult));
                    return;
                }
                break;
            case 2:
                int isGooglePlayServicesAvailable = this.mApiAvailability.isGooglePlayServicesAvailable(getActivity());
                if (isGooglePlayServicesAvailable == 0) {
                    markErrorsResolved();
                    return;
                } else if (clientConnectionResult == null) {
                    return;
                } else {
                    if (clientConnectionResult.getConnectionResult().getErrorCode() == 18 && isGooglePlayServicesAvailable == 18) {
                        return;
                    }
                }
                break;
        }
        if (clientConnectionResult != null) {
            markErrorResolutionFailed(clientConnectionResult.getConnectionResult(), clientConnectionResult.getFailingClientId());
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        markErrorResolutionFailed(new ConnectionResult(13, null), getFailingClientId((ClientConnectionResult) this.mFailingConnectionResult.get()));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mFailingConnectionResult.set(clientConnectionResultFromBundle(bundle));
        }
    }

    protected abstract void onErrorResolutionFailed(ConnectionResult connectionResult, int i);

    protected abstract void onErrorsResolved();

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        addClientConnectionResultToBundle(bundle);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }
}
