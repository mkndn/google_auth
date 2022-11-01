package com.google.android.apps.authenticator.timesync;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.apps.authenticator.otp.TotpClock;
import com.google.android.apps.authenticator.testability.DaggerInjector;
import com.google.android.apps.authenticator.timesync.SyncNowController;
import com.google.android.apps.authenticator2.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import javax.inject.Inject;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SyncNowActivity extends Activity implements SyncNowController.Presenter {
    private SyncNowController mController;
    @Inject
    NetworkTimeProvider mNetworkTimeProvider;
    private Dialog mProgressDialog;
    @Inject
    TotpClock mTotpClock;

    /* compiled from: PG */
    /* renamed from: com.google.android.apps.authenticator.timesync.SyncNowActivity$3  reason: invalid class name */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$apps$authenticator$timesync$SyncNowController$Result;

        static {
            int[] iArr = new int[SyncNowController.Result.values().length];
            $SwitchMap$com$google$android$apps$authenticator$timesync$SyncNowController$Result = iArr;
            try {
                iArr[SyncNowController.Result.TIME_ALREADY_CORRECT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$apps$authenticator$timesync$SyncNowController$Result[SyncNowController.Result.TIME_CORRECTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$apps$authenticator$timesync$SyncNowController$Result[SyncNowController.Result.ERROR_CONNECTIVITY_ISSUE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$apps$authenticator$timesync$SyncNowController$Result[SyncNowController.Result.CANCELLED_BY_USER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public SyncNowActivity() {
        DaggerInjector.inject(this);
    }

    private void dismissInProgressDialog() {
        Dialog dialog = this.mProgressDialog;
        if (dialog != null) {
            dialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    private void showInProgressDialog() {
        ProgressDialog show = ProgressDialog.show(this, getString(R.string.timesync_sync_now_progress_dialog_title), getString(R.string.timesync_sync_now_progress_dialog_details), true, true);
        this.mProgressDialog = show;
        show.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.google.android.apps.authenticator.timesync.SyncNowActivity.2
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                SyncNowActivity.this.mController.abort(SyncNowActivity.this);
            }
        });
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.mController.abort(this);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Object lastNonConfigurationInstance = getLastNonConfigurationInstance();
        if (lastNonConfigurationInstance != null) {
            this.mController = (SyncNowController) lastNonConfigurationInstance;
        } else {
            this.mController = new SyncNowController(this.mTotpClock, this.mNetworkTimeProvider);
        }
        this.mController.attach(this);
    }

    @Override // com.google.android.apps.authenticator.timesync.SyncNowController.Presenter
    public void onDone(SyncNowController.Result result) {
        MaterialAlertDialogBuilder message;
        if (isFinishing()) {
            return;
        }
        dismissInProgressDialog();
        switch (AnonymousClass3.$SwitchMap$com$google$android$apps$authenticator$timesync$SyncNowController$Result[result.ordinal()]) {
            case 1:
                message = new MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_GoogleMaterial_MaterialAlertDialog_Centered).setTitle(R.string.timesync_sync_now_time_already_correct_dialog_title).setMessage(R.string.timesync_sync_now_time_already_correct_dialog_details);
                break;
            case 2:
                message = new MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_GoogleMaterial_MaterialAlertDialog_Centered).setTitle(R.string.timesync_sync_now_time_corrected_dialog_title).setMessage(R.string.timesync_sync_now_time_corrected_dialog_details);
                break;
            case 3:
                message = new MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_GoogleMaterial_MaterialAlertDialog_Centered).setTitle(R.string.timesync_sync_now_connectivity_error_dialog_title).setMessage(R.string.timesync_sync_now_connectivity_error_dialog_details);
                break;
            case 4:
                finish();
                message = null;
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(result));
        }
        if (message == null) {
            return;
        }
        message.setCancelable(false).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.google.android.apps.authenticator.timesync.SyncNowActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                SyncNowActivity.this.finish();
            }
        }).create().show();
    }

    @Override // android.app.Activity
    public Object onRetainNonConfigurationInstance() {
        return this.mController;
    }

    @Override // com.google.android.apps.authenticator.timesync.SyncNowController.Presenter
    public void onStarted() {
        if (isFinishing()) {
            return;
        }
        showInProgressDialog();
    }

    @Override // android.app.Activity
    protected void onStop() {
        if (isFinishing()) {
            this.mController.detach(this);
        }
        super.onStop();
    }
}
