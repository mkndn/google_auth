package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ConnectionlessLifecycleHelper extends BaseLifecycleHelper {
    private final ArraySet mManagedApiKeys;
    private final GoogleApiManager mManager;

    private ConnectionlessLifecycleHelper(LifecycleFragment lifecycleFragment, GoogleApiManager googleApiManager) {
        this(lifecycleFragment, googleApiManager, GoogleApiAvailability.getInstance());
    }

    public static void initialize(Activity activity, GoogleApiManager googleApiManager, ApiKey apiKey) {
        initializeWithFragment(getFragment(activity), googleApiManager, apiKey);
    }

    private void manageApi(ApiKey apiKey) {
        Preconditions.checkNotNull(apiKey, "ApiKey cannot be null");
        this.mManagedApiKeys.add(apiKey);
    }

    private void registerManagedApiKeys() {
        if (!this.mManagedApiKeys.isEmpty()) {
            this.mManager.registerLifecycleHelper(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArraySet getManagedApiKeys() {
        return this.mManagedApiKeys;
    }

    @Override // com.google.android.gms.common.api.internal.BaseLifecycleHelper
    protected void onErrorResolutionFailed(ConnectionResult connectionResult, int i) {
        this.mManager.onErrorResolutionFailed(connectionResult, i);
    }

    @Override // com.google.android.gms.common.api.internal.BaseLifecycleHelper
    protected void onErrorsResolved() {
        this.mManager.onErrorsResolved();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onResume() {
        super.onResume();
        registerManagedApiKeys();
    }

    @Override // com.google.android.gms.common.api.internal.BaseLifecycleHelper, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        registerManagedApiKeys();
    }

    @Override // com.google.android.gms.common.api.internal.BaseLifecycleHelper, com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.mManager.unregisterLifecycleHelper(this);
    }

    private static void initializeWithFragment(LifecycleFragment lifecycleFragment, GoogleApiManager googleApiManager, ApiKey apiKey) {
        ConnectionlessLifecycleHelper connectionlessLifecycleHelper = (ConnectionlessLifecycleHelper) lifecycleFragment.getCallbackOrNull("ConnectionlessLifecycleHelper", ConnectionlessLifecycleHelper.class);
        if (connectionlessLifecycleHelper == null) {
            connectionlessLifecycleHelper = new ConnectionlessLifecycleHelper(lifecycleFragment, googleApiManager);
        }
        connectionlessLifecycleHelper.manageApi(apiKey);
        googleApiManager.registerLifecycleHelper(connectionlessLifecycleHelper);
    }

    ConnectionlessLifecycleHelper(LifecycleFragment lifecycleFragment, GoogleApiManager googleApiManager, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment, googleApiAvailability);
        this.mManagedApiKeys = new ArraySet();
        this.mManager = googleApiManager;
        this.mLifecycleFragment.addCallback("ConnectionlessLifecycleHelper", this);
    }
}
