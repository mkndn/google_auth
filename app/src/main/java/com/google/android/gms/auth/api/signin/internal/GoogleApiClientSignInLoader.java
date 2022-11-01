package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.util.Log;
import androidx.loader.content.AsyncTaskLoader;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleApiClientSignInLoader extends AsyncTaskLoader implements SignInConnectionListener {
    private Set mGoogleApiClients;
    private Semaphore mSignInSemaphore;

    public GoogleApiClientSignInLoader(Context context, Set set) {
        super(context);
        this.mSignInSemaphore = new Semaphore(0);
        this.mGoogleApiClients = set;
    }

    @Override // androidx.loader.content.Loader
    protected void onStartLoading() {
        this.mSignInSemaphore.drainPermits();
        forceLoad();
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public Void loadInBackground() {
        int i = 0;
        for (GoogleApiClient googleApiClient : this.mGoogleApiClients) {
            if (googleApiClient.maybeSignIn(this)) {
                i++;
            }
        }
        try {
            this.mSignInSemaphore.tryAcquire(i, 5L, TimeUnit.SECONDS);
            return null;
        } catch (InterruptedException e) {
            Log.i("GACSignInLoader", "Unexpected InterruptedException", e);
            Thread.currentThread().interrupt();
            return null;
        }
    }
}
