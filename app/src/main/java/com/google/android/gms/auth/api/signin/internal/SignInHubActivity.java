package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.common.base.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SignInHubActivity extends FragmentActivity {
    private static boolean sInProgress = false;
    private boolean mActivityNotFoundExceptionWasThrown = false;
    private SignInConfiguration mSignInConfiguration;
    private int mSignInResultCode;
    private Intent mSignInResultData;
    private boolean mSigningInGoogleApiClients;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SignInLoaderCallbacks implements LoaderManager.LoaderCallbacks {
        private SignInLoaderCallbacks() {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader onCreateLoader(int i, Bundle bundle) {
            return new GoogleApiClientSignInLoader(SignInHubActivity.this, GoogleApiClient.getAllClients());
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader loader) {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader loader, Void r3) {
            SignInHubActivity signInHubActivity = SignInHubActivity.this;
            signInHubActivity.setResult(signInHubActivity.mSignInResultCode, SignInHubActivity.this.mSignInResultData);
            SignInHubActivity.this.finish();
        }
    }

    private void finishWhenGoogleApiClientsSignedIn() {
        getSupportLoaderManager().initLoader(0, null, new SignInLoaderCallbacks());
        sInProgress = false;
    }

    private void finishWithGoogleErrorResult(int i) {
        Status status = new Status(i);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
        sInProgress = false;
    }

    private void handleGoogleSignInResult(int i, Intent intent) {
        if (intent != null) {
            SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra("signInAccount");
            if (signInAccount != null && signInAccount.getGoogleSignInAccount() != null) {
                GoogleSignInAccount googleSignInAccount = signInAccount.getGoogleSignInAccount();
                GoogleSignInSession.getInstance(this).setLastGoogleSignInAccount(this.mSignInConfiguration.getGoogleConfig(), (GoogleSignInAccount) Preconditions.checkNotNull(googleSignInAccount));
                intent.removeExtra("signInAccount");
                intent.putExtra("googleSignInAccount", googleSignInAccount);
                this.mSigningInGoogleApiClients = true;
                this.mSignInResultCode = i;
                this.mSignInResultData = intent;
                finishWhenGoogleApiClientsSignedIn();
                return;
            } else if (intent.hasExtra("errorCode")) {
                int intExtra = intent.getIntExtra("errorCode", 8);
                if (intExtra == 13) {
                    intExtra = 12501;
                }
                finishWithGoogleErrorResult(intExtra);
                return;
            }
        }
        finishWithGoogleErrorResult(8);
    }

    private void startSignInActivity(String str) {
        Intent intent = new Intent(str);
        if (str.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN")) {
            intent.setPackage("com.google.android.gms");
        } else {
            intent.setPackage(getPackageName());
        }
        intent.putExtra("config", this.mSignInConfiguration);
        try {
            startActivityForResult(intent, 40962);
        } catch (ActivityNotFoundException e) {
            this.mActivityNotFoundExceptionWasThrown = true;
            Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
            finishWithGoogleErrorResult(17);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mActivityNotFoundExceptionWasThrown) {
            return;
        }
        setResult(0);
        switch (i) {
            case 40962:
                handleGoogleSignInResult(i2, intent);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String str = (String) Preconditions.checkNotNull(intent.getAction());
        if ("com.google.android.gms.auth.NO_IMPL".equals(str)) {
            finishWithGoogleErrorResult(12500);
        } else if (!str.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") && !str.equals("com.google.android.gms.auth.APPAUTH_SIGN_IN")) {
            Log.e("AuthSignInClient", "Unknown action: " + intent.getAction());
            finish();
        } else {
            SignInConfiguration signInConfiguration = (SignInConfiguration) ((Bundle) Preconditions.checkNotNull(intent.getBundleExtra("config"))).getParcelable("config");
            if (signInConfiguration == null) {
                Log.e("AuthSignInClient", "Activity started with invalid configuration.");
                setResult(0);
                finish();
                return;
            }
            this.mSignInConfiguration = signInConfiguration;
            if (bundle == null) {
                if (sInProgress) {
                    setResult(0);
                    finishWithGoogleErrorResult(12502);
                    return;
                }
                sInProgress = true;
                startSignInActivity(str);
                return;
            }
            boolean z = bundle.getBoolean("signingInGoogleApiClients");
            this.mSigningInGoogleApiClients = z;
            if (z) {
                this.mSignInResultCode = bundle.getInt("signInResultCode");
                this.mSignInResultData = (Intent) Preconditions.checkNotNull((Intent) bundle.getParcelable("signInResultData"));
                finishWhenGoogleApiClientsSignedIn();
            }
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        sInProgress = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.mSigningInGoogleApiClients);
        if (this.mSigningInGoogleApiClients) {
            bundle.putInt("signInResultCode", this.mSignInResultCode);
            bundle.putParcelable("signInResultData", this.mSignInResultData);
        }
    }
}
