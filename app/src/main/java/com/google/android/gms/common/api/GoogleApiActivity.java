package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.libs.platform.PendingIntentCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleApiActivity extends Activity implements DialogInterface.OnCancelListener {
    protected static final int DIALOG_REQUEST_CODE = 2;
    protected static final int RESOLUTION_REQUEST_CODE = 1;
    protected static final int RESOLUTION_STATUS_NOT_STARTED = 0;
    protected static final int RESOLUTION_STATUS_STARTED = 1;
    protected int mResolutionStatus = 0;

    public static Intent getIntentForResolution(Context context, PendingIntent pendingIntent, int i, boolean z) {
        Intent intent = new Intent(context, GoogleApiActivity.class);
        intent.putExtra("pending_intent", pendingIntent);
        intent.putExtra("failing_client_id", i);
        intent.putExtra("notify_manager", z);
        return intent;
    }

    private static void logActivityNotFound(ActivityNotFoundException activityNotFoundException, PendingIntent pendingIntent) {
        String str = "Activity not found while launching " + String.valueOf(pendingIntent) + ".";
        if (Build.FINGERPRINT.contains("generic")) {
            str = str + " This may occur when resolving Google Play services connection issues on emulators with Google APIs but not Google Play Store.";
        }
        Log.e("GoogleApiActivity", str, activityNotFoundException);
    }

    private void notifyManager(int i, GoogleApiManager googleApiManager) {
        switch (i) {
            case -1:
                googleApiManager.onErrorsResolved();
                return;
            case 0:
                googleApiManager.onErrorResolutionFailed(new ConnectionResult(13, null), getIntent().getIntExtra("failing_client_id", -1));
                return;
            default:
                return;
        }
    }

    private void startResolution() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Log.e("GoogleApiActivity", "Activity started without extras");
            finish();
            return;
        }
        PendingIntent pendingIntent = (PendingIntent) extras.get("pending_intent");
        Integer num = (Integer) extras.get("error_code");
        if (pendingIntent == null && num == null) {
            Log.e("GoogleApiActivity", "Activity started without resolution");
            finish();
        } else if (pendingIntent != null) {
            try {
                startIntentSenderForResult(pendingIntent.getIntentSender(), 1, null, 0, 0, 0);
                this.mResolutionStatus = 1;
            } catch (ActivityNotFoundException e) {
                if (extras.getBoolean("notify_manager", true)) {
                    GoogleApiManager.getInstance(this).onErrorResolutionFailed(new ConnectionResult(22, null), getIntent().getIntExtra("failing_client_id", -1));
                } else {
                    logActivityNotFound(e, pendingIntent);
                }
                this.mResolutionStatus = 1;
                finish();
            } catch (IntentSender.SendIntentException e2) {
                Log.e("GoogleApiActivity", "Failed to launch pendingIntent", e2);
                finish();
            }
        } else {
            GoogleApiAvailability.getInstance().showErrorDialogFragment(this, ((Integer) Preconditions.checkNotNull(num)).intValue(), 2, this);
            this.mResolutionStatus = 1;
        }
    }

    public static PendingIntent wrapPendingIntent(Context context, PendingIntent pendingIntent, int i) {
        return wrapPendingIntent(context, pendingIntent, i, true);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            boolean booleanExtra = getIntent().getBooleanExtra("notify_manager", true);
            this.mResolutionStatus = 0;
            setResultCodeAndData(i2, intent);
            if (booleanExtra) {
                notifyManager(i2, GoogleApiManager.getInstance(this));
            }
        } else if (i == 2) {
            this.mResolutionStatus = 0;
            setResultCodeAndData(i2, intent);
        }
        finish();
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.mResolutionStatus = 0;
        setResult(0);
        finish();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mResolutionStatus = bundle.getInt("resolution");
        }
        if (this.mResolutionStatus != 1) {
            startResolution();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("resolution", this.mResolutionStatus);
        super.onSaveInstanceState(bundle);
    }

    protected void setResultCodeAndData(int i, Intent intent) {
        setResult(i, intent);
    }

    public static PendingIntent wrapPendingIntent(Context context, PendingIntent pendingIntent, int i, boolean z) {
        return PendingIntentCompat.getActivity(context, 0, getIntentForResolution(context, pendingIntent, i, z), PendingIntentCompat.FLAG_MUTABLE | 134217728);
    }
}
