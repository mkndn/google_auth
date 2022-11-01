package com.google.android.apps.authenticator.api;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import com.google.android.apps.authenticator.AuthenticatorActivity;
import com.google.android.apps.authenticator.testability.DaggerInjector;
import com.google.android.apps.authenticator.testability.TestableActivity;
import com.google.android.apps.authenticator.util.IntentUtils;
import com.google.android.apps.authenticator2.R;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.fido.Fido;
import com.google.android.gms.fido.u2f.U2fPendingIntent;
import com.google.android.gms.fido.u2f.U2fPrivilegedApiClient;
import com.google.android.gms.fido.u2f.api.common.BrowserRegisterRequestParams;
import com.google.android.gms.fido.u2f.api.common.BrowserRequestParams;
import com.google.android.gms.fido.u2f.api.common.BrowserSignRequestParams;
import com.google.android.gms.fido.u2f.api.common.ChannelIdValue;
import com.google.android.gms.fido.u2f.api.common.ErrorCode;
import com.google.android.gms.fido.u2f.api.common.ErrorResponseData;
import com.google.android.gms.fido.u2f.api.common.ResponseData;
import com.google.android.gms.fido.u2f.api.messagebased.ParseException;
import com.google.android.gms.fido.u2f.api.messagebased.RequestType;
import com.google.android.gms.fido.u2f.api.messagebased.WrappedRequestMessage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.common.base.Preconditions;
import javax.inject.Inject;
import org.json.JSONObject;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AuthenticateActivity extends TestableActivity {
    private static final String EXTRA_ACTIVITY_RESULT_DATA = "resultData";
    static final String EXTRA_REFERRER = "referrer";
    static final int GMS_CORE_VERSION_U2F_API = 10272000;
    private static final String KEY_WRAPPED_REQUEST_MESSAGE = "WrappedRequestMessage";
    static final int REQUEST_CODE_REGISTER = 0;
    static final int REQUEST_CODE_SIGN = 1;
    public static final int RESULT_FAILED = 1;
    private static final String TAG = "AuthenticateActivity";
    private AlertDialog mAlertDialog;
    private U2fPrivilegedApiClient mApiClient;
    @Inject
    FacetIdCalculator mFacetIdCalculator;
    private GoogleApiAvailability mGoogleApiAvailability = GoogleApiAvailability.getInstance();
    @Inject
    IntentExtraParser mIntentParser;
    @Inject
    PackageSignatureFingerprintProvider mPackageSignatureFingerprintProvider;
    private boolean mU2fApiSupported;
    private WrappedRequestMessage mWrappedRequestMessage;

    /* compiled from: PG */
    /* renamed from: com.google.android.apps.authenticator.api.AuthenticateActivity$4  reason: invalid class name */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$gms$fido$u2f$api$messagebased$RequestType;

        static {
            int[] iArr = new int[RequestType.values().length];
            $SwitchMap$com$google$android$gms$fido$u2f$api$messagebased$RequestType = iArr;
            try {
                iArr[RequestType.REGISTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$gms$fido$u2f$api$messagebased$RequestType[RequestType.SIGN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public AuthenticateActivity() {
        DaggerInjector.inject(this);
    }

    private WrappedRequestMessage recreateWrappedRequestMessageFromSavedState(Bundle bundle) {
        return WrappedRequestMessage.fromBundle(bundle.getBundle(KEY_WRAPPED_REQUEST_MESSAGE));
    }

    private void returnErrorCodeForCurrentRequest(ErrorCode errorCode) {
        returnResult(this.mWrappedRequestMessage.buildResponse(new ErrorResponseData(errorCode)).toJSONObject());
    }

    private void returnResult(JSONObject jSONObject) {
        Log.d(TAG, String.format("Returning from activity with result: %s", jSONObject));
        Intent intent = getIntent();
        intent.putExtra(EXTRA_ACTIVITY_RESULT_DATA, jSONObject.toString());
        setActivityResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i2) {
            case -1:
                ResponseData responseData = (ResponseData) intent.getParcelableExtra("RESPONSE_EXTRA");
                if (responseData instanceof ErrorResponseData) {
                    Log.e(TAG, "AuthenticateActivity encounter error: " + responseData.toString());
                } else {
                    Log.e(TAG, "AuthenticateActivity successful ResponseData: " + responseData.toString());
                }
                returnResult(this.mWrappedRequestMessage.buildResponse(responseData).toJSONObject());
                return;
            case 0:
                Log.d(TAG, "Returning activity canceled");
                setActivityResult(0, getIntent());
                finish();
                return;
            default:
                if (i2 != 1) {
                    Log.d(TAG, String.format("Got result %d", Integer.valueOf(i2)));
                }
                returnErrorCodeForCurrentRequest(ErrorCode.OTHER_ERROR);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Task registerIntent;
        super.onCreate(bundle);
        Intent intent = (Intent) Preconditions.checkNotNull(getIntent());
        this.mU2fApiSupported = this.mGoogleApiAvailability.getApkVersion(this) >= GMS_CORE_VERSION_U2F_API;
        Log.d(TAG, "Gmscore version: " + this.mGoogleApiAvailability.getApkVersion(this));
        if (!this.mU2fApiSupported) {
            if (this.mAlertDialog == null) {
                this.mAlertDialog = new MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_GoogleMaterial_MaterialAlertDialog_Centered).create();
            }
            this.mAlertDialog.setMessage(Html.fromHtml(getString(R.string.update_google_play_services_for_security_key)));
            this.mAlertDialog.setButton(-1, getString(R.string.update_button), new DialogInterface.OnClickListener() { // from class: com.google.android.apps.authenticator.api.AuthenticateActivity.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        AuthenticateActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(AuthenticatorActivity.GOOGLE_PLAY_SERVICES_INSTALL_FROM_GOOGLE_PLAY)));
                    } catch (ActivityNotFoundException e) {
                        AuthenticateActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(AuthenticatorActivity.GOOGLE_PLAY_SERVICES_INSTALL_FROM_WEB_BROWSER)));
                    }
                    AuthenticateActivity.this.finish();
                }
            });
            this.mAlertDialog.setButton(-2, getString(R.string.not_update_button), new DialogInterface.OnClickListener() { // from class: com.google.android.apps.authenticator.api.AuthenticateActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    AuthenticateActivity.this.finish();
                }
            });
            this.mAlertDialog.show();
            return;
        }
        if (bundle != null) {
            this.mWrappedRequestMessage = recreateWrappedRequestMessageFromSavedState(bundle);
        } else {
            try {
                this.mWrappedRequestMessage = new WrappedRequestMessage(this.mIntentParser.readRequestFromIntent(intent));
            } catch (ParseException e) {
                Log.e(TAG, "Couldn't parse request", e);
                returnResult(e.formatAsErrorResponse());
                return;
            }
        }
        String callingPackage = getCallingPackage();
        if (callingPackage == null) {
            Log.e(TAG, "Authenticator has not been launched with startIntentForResult!");
            returnErrorCodeForCurrentRequest(ErrorCode.BAD_REQUEST);
            return;
        }
        try {
            if (this.mFacetIdCalculator.isKnownBrowser(callingPackage, this.mPackageSignatureFingerprintProvider.getSigningCertificateFingerprints(callingPackage))) {
                Log.d(TAG, callingPackage + " is a valid browser.");
                try {
                    BrowserRequestParams browserRequestParams = this.mWrappedRequestMessage.getBrowserRequestParams(Uri.parse(IntentUtils.getMandatoryExtraString(intent, EXTRA_REFERRER)), ChannelIdValue.UNAVAILABLE);
                    if (this.mApiClient == null) {
                        this.mApiClient = Fido.getU2fPrivilegedApiClient(this);
                    }
                    switch (AnonymousClass4.$SwitchMap$com$google$android$gms$fido$u2f$api$messagebased$RequestType[this.mWrappedRequestMessage.getRequestType().ordinal()]) {
                        case 1:
                            registerIntent = this.mApiClient.getRegisterIntent((BrowserRegisterRequestParams) browserRequestParams);
                            break;
                        case 2:
                            registerIntent = this.mApiClient.getSignIntent((BrowserSignRequestParams) browserRequestParams);
                            break;
                        default:
                            String str = "Unsupported request type " + String.valueOf(this.mWrappedRequestMessage.getRequestType());
                            Log.d(TAG, str);
                            throw new RuntimeException(str);
                    }
                    registerIntent.addOnSuccessListener(new OnSuccessListener() { // from class: com.google.android.apps.authenticator.api.AuthenticateActivity.3
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public void onSuccess(U2fPendingIntent u2fPendingIntent) {
                            if (u2fPendingIntent.hasPendingIntent()) {
                                try {
                                    u2fPendingIntent.launchPendingIntent(AuthenticateActivity.this, 0);
                                } catch (IntentSender.SendIntentException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                    });
                    return;
                } catch (IntentUtils.IntentParsingException e2) {
                    Log.e(TAG, String.format("Invalid intent provided %s", intent), e2);
                    returnErrorCodeForCurrentRequest(ErrorCode.BAD_REQUEST);
                    return;
                }
            }
            Log.e(TAG, callingPackage + " is not a valid browser!");
            returnErrorCodeForCurrentRequest(ErrorCode.BAD_REQUEST);
        } catch (PackageManager.NameNotFoundException e3) {
            Log.e(TAG, String.format("Caller is unknown: %s", callingPackage), e3);
            returnErrorCodeForCurrentRequest(ErrorCode.BAD_REQUEST);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                Intent parentActivityIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, parentActivityIntent)) {
                    TaskStackBuilder.create(this).addNextIntentWithParentStack(parentActivityIntent).startActivities();
                    return true;
                }
                NavUtils.navigateUpTo(this, parentActivityIntent);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        WrappedRequestMessage wrappedRequestMessage = this.mWrappedRequestMessage;
        if (wrappedRequestMessage != null) {
            bundle.putBundle(KEY_WRAPPED_REQUEST_MESSAGE, wrappedRequestMessage.toBundle());
        }
    }

    void setAlertDialog(AlertDialog alertDialog) {
        this.mAlertDialog = alertDialog;
    }

    void setFacetIdCalculator(FacetIdCalculator facetIdCalculator) {
        this.mFacetIdCalculator = facetIdCalculator;
    }

    void setGoogleApiAvailability(GoogleApiAvailability googleApiAvailability) {
        this.mGoogleApiAvailability = googleApiAvailability;
    }

    void setU2fPrivilegedApiClient(U2fPrivilegedApiClient u2fPrivilegedApiClient) {
        this.mApiClient = u2fPrivilegedApiClient;
    }
}
