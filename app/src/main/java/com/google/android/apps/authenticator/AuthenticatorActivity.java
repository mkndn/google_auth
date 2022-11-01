package com.google.android.apps.authenticator;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.apps.authenticator.AuthenticatorActivity;
import com.google.android.apps.authenticator.auditlog.AuditLogConfig;
import com.google.android.apps.authenticator.auditlog.AuditLogTimerStarter;
import com.google.android.apps.authenticator.auditlog.ViewAuditLogActivity;
import com.google.android.apps.authenticator.backup.AuthenticatorBackupAgent;
import com.google.android.apps.authenticator.backup.BackupKeys;
import com.google.android.apps.authenticator.barcode.BarcodeCaptureActivity;
import com.google.android.apps.authenticator.barcode.BarcodeConditionChecker;
import com.google.android.apps.authenticator.howitworks.HowItWorksActivity;
import com.google.android.apps.authenticator.migration.MigrationIntroductionActivity;
import com.google.android.apps.authenticator.migration.OfflineMigration;
import com.google.android.apps.authenticator.otp.Account;
import com.google.android.apps.authenticator.otp.AccountDb;
import com.google.android.apps.authenticator.otp.AccountProcessor;
import com.google.android.apps.authenticator.otp.CheckCodeActivity;
import com.google.android.apps.authenticator.otp.InvalidAccountException;
import com.google.android.apps.authenticator.otp.OtpSource;
import com.google.android.apps.authenticator.otp.OtpSourceException;
import com.google.android.apps.authenticator.otp.PinInfo;
import com.google.android.apps.authenticator.otp.TotpClock;
import com.google.android.apps.authenticator.otp.TotpCountdownTask;
import com.google.android.apps.authenticator.otp.TotpCounter;
import com.google.android.apps.authenticator.testability.DaggerInjector;
import com.google.android.apps.authenticator.testability.DependencyInjector;
import com.google.android.apps.authenticator.testability.TestableActivity;
import com.google.android.apps.authenticator.testability.android.widget.Button;
import com.google.android.apps.authenticator.util.EmptySpaceClickableDragSortListView;
import com.google.android.apps.authenticator.util.HelpAndFeedback;
import com.google.android.apps.authenticator.util.Utilities;
import com.google.android.apps.authenticator2.R;
import com.google.android.libraries.material.fabtransition.FabTransitionController;
import com.google.android.libraries.material.speeddial.FloatingSpeedDialAdapter;
import com.google.android.libraries.material.speeddial.FloatingSpeedDialView;
import com.google.android.libraries.material.speeddial.FloatingSpeedDialViewHolder;
import com.google.android.libraries.material.speeddial.expandable.ExpandableFloatingActionButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.common.base.Preconditions;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.contrib.android.ProtoParsers;
import com.mobeta.android.dslv.DragSortController;
import com.mobeta.android.dslv.DragSortItemView;
import com.mobeta.android.dslv.DragSortListView;
import googledata.experiments.mobile.authenticator_android.features.HideCodesFeature;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AuthenticatorActivity extends TestableActivity {
    public static final String ACTION_SCAN_BARCODE = AuthenticatorActivity.class.getName() + ".ScanBarcode";
    static final int DIALOG_ID_CAMERA_NOT_AVAILABLE = 20;
    public static final int DIALOG_ID_INVALID_QR_CODE = 15;
    public static final int DIALOG_ID_INVALID_SECRET_IN_QR_CODE = 16;
    static final int DIALOG_ID_SAVE_KEY = 13;
    public static final int DIALOG_ID_UNSUPPORTED_MIGRATION_VERSION = 21;
    public static final String GOOGLE_PLAY_SERVICES_INSTALL_FROM_GOOGLE_PLAY = "market://details?id=com.google.android.gms";
    public static final String GOOGLE_PLAY_SERVICES_INSTALL_FROM_WEB_BROWSER = "https://play.google.com/store/apps/details?id=com.google.android.gms";
    private static final long HOTP_DISPLAY_TIMEOUT = 120000;
    private static final long HOTP_MIN_TIME_INTERVAL_BETWEEN_CODES = 5000;
    private static final String KEY_ADD_ACCOUNT_MENU_OPEN = "addAccountMenuOpen";
    private static final String KEY_FIRST_ACCOUNT_ADDED_NOTICE_DISPLAY_REQUIRED = "firstAccountAddedNoticeDisplayRequired";
    private static final String KEY_SAVE_KEY_DIALOG_PARAMS = "saveKeyDialogParams";
    private static final String LOCAL_TAG = "AuthenticatorActivity";
    private static final float PIN_TEXT_SCALEX_NORMAL = 1.0f;
    private static final float PIN_TEXT_SCALEX_UNDERSCORE = 0.87f;
    static final String PREF_KEY_LAST_LAUNCH_ACCOUNT_COUNT = "accountCountDuringLastMainPageLaunch";
    static final int SCAN_REQUEST = 31337;
    public static final long TOTP_COUNTDOWN_REFRESH_PERIOD_MILLIS = 100;
    protected AccountDb accountDb;
    Object actionMode;
    private ExpandableFloatingActionButton addAccountFab;
    private View addAccountScrim;
    private FloatingSpeedDialView addAccountSpeedDial;
    boolean darkModeEnabled;
    private boolean firstAccountAddedNoticeDisplayRequired;
    ContextMenu mostRecentContextMenu;
    boolean onboardingCompleted;
    @Inject
    OtpSource otpProvider;
    protected SharedPreferences preferences;
    private boolean saveKeyIntentConfirmationInProgress;
    private Toolbar toolbar;
    private TotpClock totpClock;
    private double totpCountdownPhase;
    private TotpCountdownTask totpCountdownTask;
    private TotpCounter totpCounter;
    private FabTransitionController transitionController;
    private PinListAdapter userAdapter;
    protected EmptySpaceClickableDragSortListView userList;
    protected PinInfo[] users = new PinInfo[0];

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class DragItemController extends DragSortController {
        private final Activity activity;
        private final EmptySpaceClickableDragSortListView dragSortListView;
        private Bitmap floatBitmap;
        private View floatView;
        private StartDraggingListener startDraggingListener;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public interface StartDraggingListener {
            void startDragging();
        }

        public DragItemController(EmptySpaceClickableDragSortListView emptySpaceClickableDragSortListView, Activity activity) {
            super(emptySpaceClickableDragSortListView, R.id.user_row_drag_handle, 0, 0);
            this.dragSortListView = emptySpaceClickableDragSortListView;
            this.activity = activity;
            setRemoveEnabled(false);
        }

        @Override // com.mobeta.android.dslv.SimpleFloatViewManager, com.mobeta.android.dslv.DragSortListView.FloatViewManager
        public View onCreateFloatView(int i) {
            EmptySpaceClickableDragSortListView emptySpaceClickableDragSortListView = this.dragSortListView;
            View childAt = emptySpaceClickableDragSortListView.getChildAt((i + emptySpaceClickableDragSortListView.getHeaderViewsCount()) - this.dragSortListView.getFirstVisiblePosition());
            if (childAt == null) {
                return null;
            }
            View findViewById = childAt.findViewById(R.id.user_row_layout);
            if (findViewById != null) {
                findViewById.setPressed(false);
                findViewById.setSelected(false);
                findViewById.setActivated(false);
            }
            childAt.setDrawingCacheEnabled(true);
            this.floatBitmap = Bitmap.createBitmap(childAt.getDrawingCache());
            childAt.setDrawingCacheEnabled(false);
            if (this.floatView == null) {
                this.floatView = this.activity.getLayoutInflater().inflate(R.layout.user_row_dragged, (ViewGroup) null);
            }
            ImageView imageView = (ImageView) this.floatView.findViewById(R.id.user_row_dragged_image);
            imageView.setImageBitmap(this.floatBitmap);
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(childAt.getWidth(), childAt.getHeight()));
            ((RelativeLayout.LayoutParams) imageView.getLayoutParams()).addRule(3, R.id.user_row_dragged_shadow_top);
            return this.floatView;
        }

        @Override // com.mobeta.android.dslv.SimpleFloatViewManager, com.mobeta.android.dslv.DragSortListView.FloatViewManager
        public void onDestroyFloatView(View view) {
            ImageView imageView = (ImageView) view.findViewById(R.id.user_row_dragged_image);
            if (imageView != null) {
                imageView.setImageDrawable(null);
            }
            this.floatBitmap.recycle();
            this.floatBitmap = null;
        }

        public void setStartDraggingListener(StartDraggingListener startDraggingListener) {
            this.startDraggingListener = startDraggingListener;
        }

        @Override // com.mobeta.android.dslv.DragSortController
        public boolean startDrag(int i, int i2, int i3) {
            return super.startDrag(i, i2, i3 + this.activity.getResources().getDimensionPixelSize(R.dimen.user_row_dragged_shadow_height));
        }

        @Override // com.mobeta.android.dslv.DragSortController
        public int startDragPosition(MotionEvent motionEvent) {
            StartDraggingListener startDraggingListener;
            ListAdapter adapter = this.dragSortListView.getAdapter();
            if (adapter != null) {
                boolean z = true;
                if (adapter.getCount() > 1) {
                    int startDragPosition = super.startDragPosition(motionEvent);
                    try {
                        if (AuthenticatorActivity.getMultiSelectListSingleCheckedItemPosition(this.dragSortListView) != startDragPosition) {
                            z = false;
                        }
                    } catch (IllegalStateException e) {
                        z = false;
                    }
                    if (!z) {
                        startDragPosition = -1;
                    }
                    if (startDragPosition != -1 && (startDraggingListener = this.startDraggingListener) != null) {
                        startDraggingListener.startDragging();
                    }
                    return startDragPosition;
                }
            }
            return -1;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class NextOtpButtonListener implements View.OnClickListener {
        private final PinInfo account;
        private final Handler handler;

        private NextOtpButtonListener(PinInfo pinInfo) {
            AuthenticatorActivity.this = r1;
            this.handler = new Handler();
            this.account = pinInfo;
        }

        private int findAccountPositionInList() {
            int length = AuthenticatorActivity.this.users.length;
            for (int i = 0; i < length; i++) {
                if (AuthenticatorActivity.this.users[i].equals(this.account)) {
                    return i;
                }
            }
            return -1;
        }

        /* renamed from: lambda$onClick$0$com-google-android-apps-authenticator-AuthenticatorActivity$NextOtpButtonListener */
        public /* synthetic */ void m49x6c331581() {
            this.account.setIsHotpCodeGenerationAllowed(true);
            AuthenticatorActivity.this.userAdapter.notifyDataSetChanged();
        }

        /* renamed from: lambda$onClick$1$com-google-android-apps-authenticator-AuthenticatorActivity$NextOtpButtonListener */
        public /* synthetic */ void m50x13aeef42(String str) {
            if (!str.equals(this.account.getPin())) {
                return;
            }
            this.account.setPin(AuthenticatorActivity.this.getString(R.string.empty_pin));
            AuthenticatorActivity.this.userAdapter.notifyDataSetChanged();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int findAccountPositionInList = findAccountPositionInList();
            if (findAccountPositionInList == -1) {
                throw new RuntimeException("Account not in list: " + String.valueOf(this.account));
            }
            try {
                AuthenticatorActivity.this.computeAndDisplayPin(this.account.getIndex(), findAccountPositionInList, true);
                final String pin = this.account.getPin();
                this.account.setIsHotpCodeGenerationAllowed(false);
                AuthenticatorActivity.this.userAdapter.notifyDataSetChanged();
                this.handler.postDelayed(new Runnable() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$NextOtpButtonListener$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AuthenticatorActivity.NextOtpButtonListener.this.m49x6c331581();
                    }
                }, 5000L);
                this.handler.postDelayed(new Runnable() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$NextOtpButtonListener$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AuthenticatorActivity.NextOtpButtonListener.this.m50x13aeef42(pin);
                    }
                }, AuthenticatorActivity.HOTP_DISPLAY_TIMEOUT);
            } catch (OtpSourceException e) {
                throw new RuntimeException("Failed to generate OTP for account", e);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class PinListAdapter extends ArrayAdapter {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PinListAdapter(Context context, int i, PinInfo[] pinInfoArr) {
            super(context, i, pinInfoArr);
            AuthenticatorActivity.this = r1;
        }

        /* JADX WARN: Code restructure failed: missing block: B:54:0x0069, code lost:
            if (getCount() >= 2) goto L11;
         */
        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = AuthenticatorActivity.this.getLayoutInflater();
            PinInfo pinInfo = (PinInfo) getItem(i);
            boolean z = true;
            Log.d(AuthenticatorActivity.LOCAL_TAG, String.format("Creating view for item #%s", Integer.valueOf(i)));
            if (view == null) {
                view = layoutInflater.inflate(R.layout.user_row, (ViewGroup) null);
                view.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            }
            TextView textView = (TextView) view.findViewById(R.id.pin_value);
            TextView textView2 = (TextView) view.findViewById(R.id.current_user);
            TextView textView3 = (TextView) view.findViewById(R.id.hidden_instructions);
            View findViewById = view.findViewById(R.id.next_otp);
            CountdownIndicator countdownIndicator = (CountdownIndicator) view.findViewById(R.id.countdown_icon);
            try {
                if (AuthenticatorActivity.getMultiSelectListSingleCheckedItemPosition(AuthenticatorActivity.this.userList) == i) {
                }
                z = false;
            } catch (IllegalStateException e) {
                z = false;
            }
            view.findViewById(R.id.user_row_drag_handle_image).setVisibility(z ? 0 : 8);
            if (pinInfo.isHotp()) {
                findViewById.setVisibility(0);
                findViewById.setEnabled(pinInfo.isHotpCodeGenerationAllowed());
                ((ViewGroup) view).setDescendantFocusability(393216);
                NextOtpButtonListener nextOtpButtonListener = new NextOtpButtonListener(pinInfo);
                findViewById.setOnClickListener(nextOtpButtonListener);
                findViewById.setBackground(null);
                view.setTag(nextOtpButtonListener);
                countdownIndicator.setVisibility(8);
            } else {
                if (HideCodesFeature.enableHideCodes()) {
                    textView3.setVisibility(pinInfo.isPinVisible() ? 8 : 0);
                    textView.setVisibility(pinInfo.isPinVisible() ? 0 : 8);
                } else {
                    textView.setVisibility(0);
                }
                findViewById.setVisibility(8);
                findViewById.setOnClickListener(null);
                view.setTag(null);
                countdownIndicator.setVisibility(0);
                countdownIndicator.setPhase(AuthenticatorActivity.this.totpCountdownPhase);
            }
            if (AuthenticatorActivity.this.getString(R.string.empty_pin).equals(pinInfo.getPin())) {
                textView.setTextScaleX(AuthenticatorActivity.PIN_TEXT_SCALEX_UNDERSCORE);
            } else {
                textView.setTextScaleX(1.0f);
            }
            textView.setText(Utilities.getStyledPincode(pinInfo.getPin()));
            textView3.setText(R.string.tap_to_show);
            textView2.setText(pinInfo.getIndex().toString());
            return view;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SaveKeyDialogParams implements Serializable {
        private final Integer counter;
        private final AccountDb.AccountIndex index;
        private final String secret;
        private final AccountDb.OtpType type;

        private SaveKeyDialogParams(AccountDb.AccountIndex accountIndex, String str, AccountDb.OtpType otpType, Integer num) {
            Preconditions.checkNotNull(accountIndex);
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(otpType);
            Preconditions.checkNotNull(num);
            this.index = accountIndex;
            this.secret = str;
            this.type = otpType;
            this.counter = num;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface SaveSecretFlowListener {
        void onDone();
    }

    public AuthenticatorActivity() {
        DaggerInjector.inject(this);
    }

    private void actionModeFinish() {
        ((ActionMode) this.actionMode).finish();
    }

    private void collapseAddAccountFab() {
        this.addAccountFab.collapse();
        this.transitionController.collapse();
    }

    public static void copyStringToClipboard(Context context, String str) {
        ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(context.getString(R.string.clipboard_label), str));
    }

    private Dialog createOkAlertDialog(int i, int i2, int i3) {
        MaterialAlertDialogBuilder positiveButton = new MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_GoogleMaterial_MaterialAlertDialog_Centered).setMessage(i2).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null);
        if (i != 0) {
            positiveButton.setTitle(i);
        }
        if (i3 != 0) {
            positiveButton.setIcon(i3);
        }
        return positiveButton.create();
    }

    public void dismissFirstAccountAddedNoticeDisplay() {
        setLastLaunchAccountCount(getAccountCount());
        this.firstAccountAddedNoticeDisplayRequired = false;
        refreshFirstAccountAddedNoticeDisplay();
        refreshOrientationState();
    }

    private void displayHowItWorksInstructions() {
        Intent intent = new Intent(this, HowItWorksActivity.class);
        intent.putExtra(HowItWorksActivity.KEY_FIRST_ONBOARDING_EXPERIENCE, !this.onboardingCompleted);
        startActivity(intent);
    }

    private void expandAddAccountFab() {
        this.addAccountFab.expand();
        this.transitionController.expandToFloatingSpeedDial(this.addAccountSpeedDial, this.addAccountScrim);
    }

    private int getAccountCount() {
        return this.accountDb.getAccounts().size();
    }

    private int getLastLaunchAccountCount() {
        return this.preferences.getInt(PREF_KEY_LAST_LAUNCH_ACCOUNT_COUNT, -1);
    }

    public static Intent getLaunchIntentActionScanBarcode(Context context, boolean z) {
        return new Intent(ACTION_SCAN_BARCODE).putExtra(BarcodeCaptureActivity.INTENT_EXTRA_START_FROM_ADD_ACCOUNT, z).setComponent(new ComponentName(context, AuthenticatorActivity.class));
    }

    public static int getMultiSelectListSingleCheckedItemPosition(AbsListView absListView) {
        Preconditions.checkState(absListView.getCheckedItemCount() == 1);
        SparseBooleanArray checkedItemPositions = absListView.getCheckedItemPositions();
        Preconditions.checkState(checkedItemPositions != null);
        int count = absListView.getCount();
        for (int i = 0; i < count; i++) {
            if (checkedItemPositions.get(i)) {
                return i;
            }
        }
        throw new IllegalStateException("No items checked");
    }

    private DialogInterface.OnClickListener getRenameClickListener(final Context context, final AccountDb.AccountIndex accountIndex, final EditText editText) {
        return new DialogInterface.OnClickListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity.4
            {
                AuthenticatorActivity.this = this;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                String trim = editText.getText().toString().trim();
                AccountDb.AccountIndex accountIndex2 = new AccountDb.AccountIndex(trim, accountIndex.getIssuer());
                if (!accountIndex2.getStrippedName().equals(accountIndex.getStrippedName())) {
                    if (AuthenticatorActivity.this.accountDb.findSimilarExistingIndex(accountIndex2) != null) {
                        Toast.makeText(context, R.string.error_exists, 1).show();
                        return;
                    }
                    AuthenticatorActivity.this.accountDb.rename(accountIndex, trim);
                    AuthenticatorActivity.this.dismissFirstAccountAddedNoticeDisplay();
                    AuthenticatorActivity.this.refreshView(true);
                }
            }
        };
    }

    private void interpretScanResult(Uri uri, boolean z) {
        if (z) {
            if (this.saveKeyIntentConfirmationInProgress) {
                Log.w(LOCAL_TAG, "Ignoring save key Intent: previous Intent not yet confirmed by user");
                return;
            }
            this.saveKeyIntentConfirmationInProgress = true;
        }
        processScannedSecret(uri, z);
    }

    public static boolean isCheckAccountKeyValueSupported(PinInfo pinInfo) {
        return pinInfo.isHotp();
    }

    public static boolean isRenameAccountAvailableSupported(PinInfo pinInfo) {
        return !AccountDb.GOOGLE_CORP_ACCOUNT_NAME.equals(pinInfo.getIndex().getName());
    }

    public static /* synthetic */ void lambda$showDialogWhenAccountAlreadyExists$10(AccountDb accountDb, Account account, Context context, SaveSecretFlowListener saveSecretFlowListener, DialogInterface dialogInterface, int i) {
        Toast.makeText(context, context.getString(R.string.secret_saved_with_name, accountDb.add(account).toString()), 1).show();
        dialogInterface.dismiss();
        saveSecretFlowListener.onDone();
    }

    public static /* synthetic */ void lambda$showDialogWhenAccountAlreadyExists$9(AccountDb accountDb, Account account, Context context, SaveSecretFlowListener saveSecretFlowListener, DialogInterface dialogInterface, int i) {
        accountDb.overwrite(account);
        Toast.makeText(context, R.string.secret_saved, 1).show();
        dialogInterface.dismiss();
        saveSecretFlowListener.onDone();
    }

    private void markDialogAsResultOfSaveKeyIntent(Dialog dialog) {
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                AuthenticatorActivity.this.m36x3368e510(dialogInterface);
            }
        });
    }

    private void onSaveKeyIntentConfirmationPromptDismissed() {
        this.saveKeyIntentConfirmationInProgress = false;
    }

    private void processScannedSecret(Uri uri, boolean z) {
        try {
            Account parseOtpAuthUrl = AccountProcessor.parseOtpAuthUrl(uri);
            AccountDb.AccountIndex accountIndex = new AccountDb.AccountIndex(parseOtpAuthUrl.name(), parseOtpAuthUrl.issuer());
            if (z) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(KEY_SAVE_KEY_DIALOG_PARAMS, new SaveKeyDialogParams(accountIndex, parseOtpAuthUrl.secret(), parseOtpAuthUrl.otpType(), parseOtpAuthUrl.counter()));
                showDialog(13, bundle);
                return;
            }
            saveSecretAndRefreshUserList(accountIndex, parseOtpAuthUrl.secret(), parseOtpAuthUrl.otpType(), parseOtpAuthUrl.counter());
        } catch (InvalidAccountException e) {
            Log.e(LOCAL_TAG, e.getMessage());
            showDialog(e.errorType.explanationDialogId);
        }
    }

    private void refreshFirstAccountAddedNoticeDisplay() {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        int[] iArr = {R.id.first_account_message_header, R.id.first_account_message_detail, R.id.first_account_message_button_done};
        ViewGroup.LayoutParams layoutParams = this.userList.getLayoutParams();
        if (this.firstAccountAddedNoticeDisplayRequired) {
            for (int i = 0; i < 3; i++) {
                findViewById(iArr[i]).setVisibility(0);
            }
            this.addAccountFab.setVisibility(8);
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.pincode_list_no_paddingTop);
            dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.pincode_list_no_paddingBottom);
            layoutParams.height = -2;
        } else {
            for (int i2 = 0; i2 < 3; i2++) {
                findViewById(iArr[i2]).setVisibility(8);
            }
            this.addAccountFab.setVisibility(0);
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.pincode_list_paddingTop);
            dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.pincode_list_paddingBottom);
            layoutParams.height = -1;
        }
        this.userList.setPadding(0, dimensionPixelSize, 0, dimensionPixelSize2);
        this.userList.setLayoutParams(layoutParams);
    }

    public void refreshMigrationButterbarDisplay() {
        int i;
        Button buttonById = getButtonById(R.id.recent_migration_butterbar);
        if (this.accountDb.auditLog.hasRecentExportEvents(AuditLogConfig.BUTTERBAR_LIFETIME_MS)) {
            i = R.string.audit_log_accounts_exported_butterbar_text;
        } else if (this.accountDb.auditLog.hasRecentImportEvents(AuditLogConfig.BUTTERBAR_LIFETIME_MS)) {
            i = R.string.audit_log_accounts_imported_butterbar_text;
        } else {
            buttonById.setVisibility(8);
            return;
        }
        buttonById.setText(getString(i));
        buttonById.setVisibility(0);
    }

    private void refreshOrientationState() {
        if (!getResources().getBoolean(R.bool.isTablet) && (getAccountCount() == 0 || this.firstAccountAddedNoticeDisplayRequired)) {
            setRequestedOrientation(12);
        } else {
            setRequestedOrientation(2);
        }
    }

    private void refreshToolbarAndStatusBarStyle() {
        if (Build.VERSION.SDK_INT >= 21) {
            if (this.users.length > 0 && this.darkModeEnabled) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.statusBarColorDark));
            } else {
                getWindow().setStatusBarColor(getResources().getColor(R.color.statusBarColor));
            }
        }
        if (this.darkModeEnabled) {
            this.toolbar.setBackgroundResource(R.color.actionBarColorDark);
            setToolbarOverflowIconColor(R.color.google_white);
            return;
        }
        this.toolbar.setBackgroundResource(R.color.actionBarColor);
        setToolbarOverflowIconColor(R.color.google_grey700);
    }

    public void refreshVerificationCodes() {
        refreshView();
        setTotpCountdownPhase(1.0d);
    }

    private void refreshView() {
        refreshView(false);
    }

    private void registerContextualActionBarForUserList() {
        this.userList.setChoiceMode(3);
        this.userList.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity.2
            {
                AuthenticatorActivity.this = this;
            }

            private void copyCodeToClipboard(PinInfo pinInfo) {
                AuthenticatorActivity.copyStringToClipboard(AuthenticatorActivity.this.userList.getContext(), pinInfo.getPin());
                Toast.makeText(AuthenticatorActivity.this.userList.getContext(), R.string.copied_to_clipboard_toast, 0).show();
            }

            private void updateCabForAccount(ActionMode actionMode, Menu menu, PinInfo pinInfo) {
                actionMode.setTitle(pinInfo.getIndex().getStrippedName());
                updateMenuForAccount(menu, pinInfo);
                copyCodeToClipboard(pinInfo);
            }

            private void updateMenuForAccount(Menu menu, PinInfo pinInfo) {
                MenuItem findItem = menu.findItem(R.id.copy);
                if (findItem != null) {
                    findItem.setVisible(false);
                }
                MenuItem findItem2 = menu.findItem(R.id.rename);
                if (findItem2 != null) {
                    findItem2.setVisible(AuthenticatorActivity.isRenameAccountAvailableSupported(pinInfo));
                }
                MenuItem findItem3 = menu.findItem(R.id.check_code);
                if (findItem3 != null) {
                    findItem3.setVisible(AuthenticatorActivity.isCheckAccountKeyValueSupported(pinInfo));
                }
            }

            @Override // android.view.ActionMode.Callback
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                if (AuthenticatorActivity.this.userList.getCheckedItemCount() == 0) {
                    return false;
                }
                if (AuthenticatorActivity.this.onContextItemSelected(menuItem, AuthenticatorActivity.getMultiSelectListSingleCheckedItemPosition(AuthenticatorActivity.this.userList))) {
                    actionMode.finish();
                    return true;
                }
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                AuthenticatorActivity.this.actionMode = actionMode;
                AuthenticatorActivity.this.getMenuInflater().inflate(R.menu.account_list_context, menu);
                if (AuthenticatorActivity.this.userList.getCheckedItemCount() > 0) {
                    updateCabForAccount(actionMode, menu, AuthenticatorActivity.this.users[AuthenticatorActivity.getMultiSelectListSingleCheckedItemPosition(AuthenticatorActivity.this.userList)]);
                    return true;
                }
                return true;
            }

            @Override // android.view.ActionMode.Callback
            public void onDestroyActionMode(ActionMode actionMode) {
                AuthenticatorActivity.this.actionMode = null;
            }

            @Override // android.widget.AbsListView.MultiChoiceModeListener
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long j, boolean z) {
                if (z) {
                    actionMode.setTitle(AuthenticatorActivity.this.users[i].getIndex().getStrippedName());
                    SparseBooleanArray checkedItemPositions = AuthenticatorActivity.this.userList.getCheckedItemPositions();
                    int count = AuthenticatorActivity.this.userList.getCount();
                    for (int i2 = 0; i2 < count; i2++) {
                        if (i2 != i && checkedItemPositions.get(i2)) {
                            AuthenticatorActivity.this.userList.setItemChecked(i2, false);
                        }
                    }
                    updateCabForAccount(actionMode, actionMode.getMenu(), AuthenticatorActivity.this.users[i]);
                    AuthenticatorActivity.this.userAdapter.notifyDataSetChanged();
                }
            }

            @Override // android.view.ActionMode.Callback
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }
        });
    }

    public static void saveSecret(Context context, AccountDb.AccountIndex accountIndex, String str, AccountDb.OtpType otpType, Integer num, SaveSecretFlowListener saveSecretFlowListener) {
        AccountDb accountDb = DependencyInjector.getAccountDb();
        Account build = Account.builder().setName(accountIndex.getName()).setIssuer(accountIndex.getIssuer()).setSecret(str).setOtpType(otpType).setCounter(num).build();
        if (accountDb.accountAlreadyExists(accountIndex) && !build.equals(accountDb.getAccountInfo(accountIndex))) {
            showDialogWhenAccountAlreadyExists(context, build, saveSecretFlowListener);
            return;
        }
        accountDb.add(build);
        Toast.makeText(context, R.string.secret_saved, 1).show();
        saveSecretFlowListener.onDone();
    }

    private void saveSecretAndRefreshUserList(AccountDb.AccountIndex accountIndex, String str, AccountDb.OtpType otpType, Integer num) {
        saveSecret(this, accountIndex, str, otpType, num, new SaveSecretFlowListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$$ExternalSyntheticLambda0
            @Override // com.google.android.apps.authenticator.AuthenticatorActivity.SaveSecretFlowListener
            public final void onDone() {
                AuthenticatorActivity.this.m46x8e6b683a();
            }
        });
    }

    private void scanBarcode(boolean z) {
        if (!BarcodeConditionChecker.isCameraAvailableOnDevice(this)) {
            showDialog(20);
            return;
        }
        Intent intent = new Intent(this, BarcodeCaptureActivity.class);
        intent.putExtra(BarcodeCaptureActivity.INTENT_EXTRA_START_FROM_ADD_ACCOUNT, z);
        startActivityForResult(intent, SCAN_REQUEST);
    }

    public void setAddAccountMenuLabelStyleWorkaround(FloatingSpeedDialViewHolder floatingSpeedDialViewHolder) {
        int i;
        ViewParent parent = floatingSpeedDialViewHolder.getFab().getParent();
        if (parent instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parent;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                if (viewGroup.getChildAt(i2) instanceof TextView) {
                    TextView textView = (TextView) viewGroup.getChildAt(i2);
                    if (this.darkModeEnabled) {
                        textView.setBackground(getResources().getDrawable(R.drawable.speed_dial_background_dark_mode));
                    }
                    if (this.darkModeEnabled) {
                        i = R.style.AuthenticatorTheme_SpeedDial_Label_Dark;
                    } else {
                        i = R.style.AuthenticatorTheme_SpeedDial_Label;
                    }
                    TextViewCompat.setTextAppearance(textView, i);
                    textView.setPadding((int) (getResources().getDisplayMetrics().density * 16.0f), (int) (getResources().getDisplayMetrics().density * 10.0f), (int) (getResources().getDisplayMetrics().density * 16.0f), (int) (getResources().getDisplayMetrics().density * 10.0f));
                    return;
                }
            }
        }
    }

    private void setLastLaunchAccountCount(int i) {
        this.preferences.edit().putInt(PREF_KEY_LAST_LAUNCH_ACCOUNT_COUNT, i).commit();
    }

    private void setToolbarOverflowIconColor(int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.toolbar.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, i), PorterDuff.Mode.SRC_IN);
        }
    }

    private void setTotpCountdownPhase(double d) {
        this.totpCountdownPhase = d;
        updateCountdownIndicators();
    }

    public void setTotpCountdownPhaseFromTimeTillNextValue(long j) {
        double d = j;
        double secondsToMillis = Utilities.secondsToMillis(this.totpCounter.getTimeStep());
        Double.isNaN(d);
        Double.isNaN(secondsToMillis);
        setTotpCountdownPhase(d / secondsToMillis);
    }

    private void setUpAddAccountFloatingActionButton() {
        this.addAccountSpeedDial = (FloatingSpeedDialView) findViewById(R.id.add_account_speed_dial);
        this.addAccountFab = (ExpandableFloatingActionButton) findViewById(R.id.add_account_fab);
        this.addAccountScrim = findViewById(R.id.add_account_scrim);
        this.transitionController = new FabTransitionController(this.addAccountFab);
        FloatingSpeedDialAdapter floatingSpeedDialAdapter = new FloatingSpeedDialAdapter(this) { // from class: com.google.android.apps.authenticator.AuthenticatorActivity.1
            {
                AuthenticatorActivity.this = this;
            }

            @Override // android.support.v7.widget.RecyclerView.Adapter
            public int getItemCount() {
                return AddAccountMenuItem.values().length;
            }

            @Override // android.support.v7.widget.RecyclerView.Adapter
            public void onBindViewHolder(FloatingSpeedDialViewHolder floatingSpeedDialViewHolder, int i) {
                AddAccountMenuItem addAccountMenuItem = AddAccountMenuItem.values()[i];
                floatingSpeedDialViewHolder.setLabel(addAccountMenuItem.labelId);
                AuthenticatorActivity.this.setAddAccountMenuLabelStyleWorkaround(floatingSpeedDialViewHolder);
                floatingSpeedDialViewHolder.getFab().setImageResource(addAccountMenuItem.iconId);
                floatingSpeedDialViewHolder.getFab().setClickable(false);
            }
        };
        floatingSpeedDialAdapter.setOnItemSelectedListener(new FloatingSpeedDialAdapter.OnItemSelectedListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$$ExternalSyntheticLambda5
            @Override // com.google.android.libraries.material.speeddial.FloatingSpeedDialAdapter.OnItemSelectedListener
            public final void onItemSelected(FloatingSpeedDialViewHolder floatingSpeedDialViewHolder, int i) {
                AuthenticatorActivity.this.m47x42864adb(floatingSpeedDialViewHolder, i);
            }
        });
        this.addAccountSpeedDial.setAdapter(floatingSpeedDialAdapter);
        this.addAccountFab.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AuthenticatorActivity.this.m48xfcfbeb5c(view);
            }
        });
        this.addAccountFab.bringToFront();
    }

    private void showAuditLog() {
        startActivity(new Intent(this, ViewAuditLogActivity.class));
    }

    private static void showDialogWhenAccountAlreadyExists(final Context context, final Account account, final SaveSecretFlowListener saveSecretFlowListener) {
        final AccountDb accountDb = DependencyInjector.getAccountDb();
        new MaterialAlertDialogBuilder(context, R.style.ThemeOverlay_GoogleMaterial_MaterialAlertDialog_Centered).setTitle(R.string.overwrite_alert_title).setMessage((CharSequence) context.getString(R.string.overwrite_alert_message, AccountDb.getFormattedNameFor(account.name(), account.issuer()))).setPositiveButton(R.string.overwrite_existing_account, new DialogInterface.OnClickListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$$ExternalSyntheticLambda13
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AuthenticatorActivity.lambda$showDialogWhenAccountAlreadyExists$9(AccountDb.this, account, context, saveSecretFlowListener, dialogInterface, i);
            }
        }).setNegativeButton(R.string.keep_both_accounts, new DialogInterface.OnClickListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$$ExternalSyntheticLambda14
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AuthenticatorActivity.lambda$showDialogWhenAccountAlreadyExists$10(AccountDb.this, account, context, saveSecretFlowListener, dialogInterface, i);
            }
        }).create().show();
    }

    private void showSettings() {
        startActivity(new Intent(this, DependencyInjector.getOptionalFeatures().getSettingsActivity()));
    }

    private void startMigration() {
        startActivity(new Intent(this, MigrationIntroductionActivity.class));
    }

    private void stopTotpCountdownTask() {
        TotpCountdownTask totpCountdownTask = this.totpCountdownTask;
        if (totpCountdownTask != null) {
            totpCountdownTask.stop();
            this.totpCountdownTask = null;
        }
    }

    private void switchUiMode() {
        this.darkModeEnabled = !this.darkModeEnabled;
        this.preferences.edit().putBoolean(BackupKeys.KEY_DARK_MODE_ENABLED, this.darkModeEnabled).commit();
        AuthenticatorBackupAgent.scheduleBackup(this);
        finish();
        overridePendingTransition(0, 0);
        startActivity(new Intent(this, getClass()));
        overridePendingTransition(0, 0);
    }

    private void toggleVisibility(PinInfo pinInfo, View view) {
        TextView textView = (TextView) view.findViewById(R.id.pin_value);
        TextView textView2 = (TextView) view.findViewById(R.id.hidden_instructions);
        if (!pinInfo.isHotp()) {
            textView.setVisibility(pinInfo.isPinVisible() ? 8 : 0);
            textView2.setVisibility(pinInfo.isPinVisible() ? 0 : 8);
            pinInfo.setPinVisible(pinInfo.isPinVisible() ? false : true);
        }
    }

    /* renamed from: unselectItemOnList */
    public void m43xf26f5998() {
        if (this.actionMode != null) {
            actionModeFinish();
            this.actionMode = null;
            try {
                this.userList.setItemChecked(getMultiSelectListSingleCheckedItemPosition(this.userList), false);
            } catch (IllegalStateException e) {
                Log.e(getString(R.string.app_name), LOCAL_TAG, e);
            }
        }
    }

    private void updateCodesAndStartTotpCountdownTask() {
        stopTotpCountdownTask();
        TotpCountdownTask totpCountdownTask = new TotpCountdownTask(this.totpCounter, this.totpClock, 100L);
        this.totpCountdownTask = totpCountdownTask;
        totpCountdownTask.setListener(new TotpCountdownTask.Listener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity.3
            {
                AuthenticatorActivity.this = this;
            }

            @Override // com.google.android.apps.authenticator.otp.TotpCountdownTask.Listener
            public void onTotpCountdown(long j) {
                if (AuthenticatorActivity.this.isFinishing()) {
                    return;
                }
                AuthenticatorActivity.this.setTotpCountdownPhaseFromTimeTillNextValue(j);
            }

            @Override // com.google.android.apps.authenticator.otp.TotpCountdownTask.Listener
            public void onTotpCounterValueChanged() {
                if (AuthenticatorActivity.this.isFinishing()) {
                    return;
                }
                AuthenticatorActivity.this.refreshVerificationCodes();
                AuthenticatorActivity.this.refreshMigrationButterbarDisplay();
            }
        });
        this.totpCountdownTask.startAndNotifyListener();
    }

    private void updateCountdownIndicators() {
        int childCount = this.userList.getChildCount();
        for (int i = 0; i < childCount; i++) {
            CountdownIndicator countdownIndicator = (CountdownIndicator) this.userList.getChildAt(i).findViewById(R.id.countdown_icon);
            if (countdownIndicator != null) {
                countdownIndicator.setPhase(this.totpCountdownPhase);
            }
        }
    }

    private void updateFirstAccountAddedNoticeDisplay() {
        int lastLaunchAccountCount = getLastLaunchAccountCount();
        if (lastLaunchAccountCount < 0) {
            lastLaunchAccountCount = getAccountCount();
        }
        int accountCount = getAccountCount();
        setLastLaunchAccountCount(accountCount);
        this.firstAccountAddedNoticeDisplayRequired = lastLaunchAccountCount == 0 && accountCount > 0;
        refreshFirstAccountAddedNoticeDisplay();
        refreshOrientationState();
    }

    private void validateAndSaveImportedAccounts(List list) {
        try {
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(AccountProcessor.processImportedAccount((OfflineMigration.OtpParameters) it.next()));
            }
            DependencyInjector.getAccountDb().addAll(arrayList);
            Toast.makeText(this, getResources().getQuantityString(R.plurals.accounts_imported, arrayList.size(), Integer.valueOf(arrayList.size())), 1).show();
            setLastLaunchAccountCount(getAccountCount());
            updateFirstAccountAddedNoticeDisplay();
            refreshView(true);
        } catch (InvalidAccountException e) {
            Log.e(LOCAL_TAG, e.getMessage());
            showDialog(e.errorType.explanationDialogId);
        }
    }

    public void computeAndDisplayPin(AccountDb.AccountIndex accountIndex, int i, boolean z) {
        PinInfo pinInfo = this.users[i];
        if (pinInfo == null) {
            PinInfo pinInfo2 = new PinInfo(accountIndex, this.accountDb.getType(accountIndex) == AccountDb.OtpType.HOTP);
            pinInfo2.setPin(getString(R.string.empty_pin));
            pinInfo2.setIsHotpCodeGenerationAllowed(true);
            pinInfo = pinInfo2;
        }
        if (!pinInfo.isHotp() || z) {
            pinInfo.setPin(this.otpProvider.getNextCode(accountIndex));
            pinInfo.setIsHotpCodeGenerationAllowed(true);
        }
        this.users[i] = pinInfo;
    }

    protected void handleIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if (ACTION_SCAN_BARCODE.equals(action)) {
            scanBarcode(intent.getBooleanExtra(BarcodeCaptureActivity.INTENT_EXTRA_START_FROM_ADD_ACCOUNT, false));
        } else if ("android.intent.action.VIEW".equals(action)) {
            interpretScanResult(intent.getData(), true);
        } else if (action == null || "android.intent.action.MAIN".equals(action)) {
            updateFirstAccountAddedNoticeDisplay();
        }
    }

    /* renamed from: lambda$markDialogAsResultOfSaveKeyIntent$14$com-google-android-apps-authenticator-AuthenticatorActivity */
    public /* synthetic */ void m36x3368e510(DialogInterface dialogInterface) {
        onSaveKeyIntentConfirmationPromptDismissed();
    }

    /* renamed from: lambda$onContextItemSelected$11$com-google-android-apps-authenticator-AuthenticatorActivity */
    public /* synthetic */ void m37x7b71c912(AccountDb.AccountIndex accountIndex, DialogInterface dialogInterface, int i) {
        this.accountDb.delete(accountIndex);
        dismissFirstAccountAddedNoticeDisplay();
        refreshView(true);
    }

    /* renamed from: lambda$onCreate$0$com-google-android-apps-authenticator-AuthenticatorActivity */
    public /* synthetic */ void m38x4e233713(AdapterView adapterView, View view, int i, long j) {
        DragSortItemView dragSortItemView = (DragSortItemView) view;
        if (HideCodesFeature.enableHideCodes()) {
            PinInfo pinInfo = this.users[i];
            EmptySpaceClickableDragSortListView emptySpaceClickableDragSortListView = this.userList;
            toggleVisibility(pinInfo, emptySpaceClickableDragSortListView.getChildAt((i + emptySpaceClickableDragSortListView.getHeaderViewsCount()) - this.userList.getFirstVisiblePosition()));
        }
        View view2 = null;
        for (int i2 = 0; i2 < dragSortItemView.getChildCount(); i2++) {
            if (dragSortItemView.getChildAt(i2) instanceof UserRowView) {
                view2 = dragSortItemView.getChildAt(i2);
            }
        }
        if (view2 == null) {
            return;
        }
        NextOtpButtonListener nextOtpButtonListener = (NextOtpButtonListener) view2.getTag();
        View findViewById = view2.findViewById(R.id.next_otp);
        if (nextOtpButtonListener != null && findViewById.isEnabled()) {
            nextOtpButtonListener.onClick(view2);
        }
        this.userList.sendAccessibilityEvent(4);
    }

    /* renamed from: lambda$onCreate$2$com-google-android-apps-authenticator-AuthenticatorActivity */
    public /* synthetic */ void m40xc30e7815(View view) {
        dismissFirstAccountAddedNoticeDisplay();
    }

    /* renamed from: lambda$onCreate$3$com-google-android-apps-authenticator-AuthenticatorActivity */
    public /* synthetic */ void m41x7d841896(int i, int i2) {
        this.userAdapter.notifyDataSetChanged();
    }

    /* renamed from: lambda$onCreate$4$com-google-android-apps-authenticator-AuthenticatorActivity */
    public /* synthetic */ void m42x37f9b917(int i, int i2) {
        if (i == i2) {
            return;
        }
        List accounts = this.accountDb.getAccounts();
        try {
            this.accountDb.swapId((AccountDb.AccountIndex) accounts.get(i), (AccountDb.AccountIndex) accounts.get(i2));
        } catch (AccountDb.AccountDbIdUpdateFailureException e) {
            Log.e(LOCAL_TAG, e.getMessage());
            Toast.makeText(getApplicationContext(), R.string.accounts_reorder_failed, 0).show();
        }
        PinInfo.swapIndex(this.users, i, i2);
    }

    /* renamed from: lambda$onCreateDialog$12$com-google-android-apps-authenticator-AuthenticatorActivity */
    public /* synthetic */ void m44xe22742f4(SaveKeyDialogParams saveKeyDialogParams, DialogInterface dialogInterface, int i) {
        saveSecretAndRefreshUserList(saveKeyDialogParams.index, saveKeyDialogParams.secret, saveKeyDialogParams.type, saveKeyDialogParams.counter);
    }

    /* renamed from: lambda$onCreateDialog$13$com-google-android-apps-authenticator-AuthenticatorActivity */
    public /* synthetic */ void m45x9c9ce375(int i, DialogInterface dialogInterface) {
        removeDialog(i);
        onSaveKeyIntentConfirmationPromptDismissed();
    }

    /* renamed from: lambda$saveSecretAndRefreshUserList$8$com-google-android-apps-authenticator-AuthenticatorActivity */
    public /* synthetic */ void m46x8e6b683a() {
        updateFirstAccountAddedNoticeDisplay();
        refreshView(true);
    }

    /* renamed from: lambda$setUpAddAccountFloatingActionButton$6$com-google-android-apps-authenticator-AuthenticatorActivity */
    public /* synthetic */ void m47x42864adb(FloatingSpeedDialViewHolder floatingSpeedDialViewHolder, int i) {
        collapseAddAccountFab();
        startActivity(AddAccountMenuItem.values()[i].onClick(this));
    }

    /* renamed from: lambda$setUpAddAccountFloatingActionButton$7$com-google-android-apps-authenticator-AuthenticatorActivity */
    public /* synthetic */ void m48xfcfbeb5c(View view) {
        if (this.addAccountFab.isExpanded()) {
            collapseAddAccountFab();
        } else {
            expandAddAccountFab();
        }
    }

    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i, intent);
        Log.i(getString(R.string.app_name), "AuthenticatorActivity: onActivityResult");
        if (i == SCAN_REQUEST) {
            switch (i2) {
                case 1:
                    String stringExtra = intent.getStringExtra(BarcodeCaptureActivity.INTENT_EXTRA_OTP_URL_VALUE);
                    if (stringExtra == null) {
                        showDialog(15);
                        return;
                    } else {
                        interpretScanResult(Uri.parse(stringExtra), false);
                        return;
                    }
                case 2:
                    List listTrusted = ProtoParsers.getListTrusted(intent, BarcodeCaptureActivity.INTENT_EXTRA_IMPORTED_OTP_PARAMETERS, OfflineMigration.OtpParameters.getDefaultInstance(), ExtensionRegistryLite.getGeneratedRegistry());
                    if (listTrusted == null) {
                        showDialog(15);
                        return;
                    } else {
                        validateAndSaveImportedAccounts(listTrusted);
                        return;
                    }
                case 3:
                    showDialog(21);
                    return;
                default:
                    return;
            }
        }
    }

    public void onAddAccountScrimClicked(View view) {
        collapseAddAccountFab();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.addAccountFab.isExpanded()) {
            collapseAddAccountFab();
        } else {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) {
        return onContextItemSelected(menuItem, ((AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo()).id);
    }

    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        int i;
        super.onCreate(bundle);
        getWindow().setFlags(8192, 8192);
        this.accountDb = DependencyInjector.getAccountDb();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.preferences = defaultSharedPreferences;
        boolean z = defaultSharedPreferences.getBoolean(BackupKeys.KEY_DARK_MODE_ENABLED, false);
        this.darkModeEnabled = z;
        if (z) {
            i = R.style.AuthenticatorTheme_NoTitleBar_Dark;
        } else {
            i = R.style.AuthenticatorTheme_NoTitleBar;
        }
        setTheme(i);
        setTitle(R.string.app_name);
        this.totpCounter = this.otpProvider.getTotpCounter();
        this.totpClock = this.otpProvider.getTotpClock();
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.authenticator_toolbar);
        this.toolbar = toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        if (getAccountCount() > 0) {
            this.preferences.edit().putBoolean(BackupKeys.KEY_ONBOARDING_COMPLETED, true).commit();
        } else {
            displayHowItWorksInstructions();
        }
        Object lastCustomNonConfigurationInstance = getLastCustomNonConfigurationInstance();
        if (lastCustomNonConfigurationInstance != null) {
            PinInfo[] pinInfoArr = (PinInfo[]) lastCustomNonConfigurationInstance;
            this.users = pinInfoArr;
            for (PinInfo pinInfo : pinInfoArr) {
                if (pinInfo.isHotp()) {
                    pinInfo.setIsHotpCodeGenerationAllowed(true);
                }
            }
        }
        if (bundle != null) {
            this.firstAccountAddedNoticeDisplayRequired = bundle.getBoolean(KEY_FIRST_ACCOUNT_ADDED_NOTICE_DISPLAY_REQUIRED, false);
        }
        this.userList = (EmptySpaceClickableDragSortListView) findViewById(R.id.user_list);
        registerContextualActionBarForUserList();
        this.userList.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$$ExternalSyntheticLambda7
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i2, long j) {
                AuthenticatorActivity.this.m38x4e233713(adapterView, view, i2, j);
            }
        });
        this.userList.setOnEmptySpaceClickListener(new EmptySpaceClickableDragSortListView.OnEmptySpaceClickListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$$ExternalSyntheticLambda8
            @Override // com.google.android.apps.authenticator.util.EmptySpaceClickableDragSortListView.OnEmptySpaceClickListener
            public final void onEmptySpaceClick() {
                AuthenticatorActivity.this.m39x898d794();
            }
        });
        refreshOrientationState();
        setUpAddAccountFloatingActionButton();
        if (bundle != null && bundle.getBoolean(KEY_ADD_ACCOUNT_MENU_OPEN, false)) {
            expandAddAccountFab();
        } else {
            collapseAddAccountFab();
        }
        findViewById(R.id.first_account_message_button_done).setOnClickListener(new View.OnClickListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AuthenticatorActivity.this.m40xc30e7815(view);
            }
        });
        PinListAdapter pinListAdapter = new PinListAdapter(this, R.layout.user_row, this.users);
        this.userAdapter = pinListAdapter;
        this.userList.setAdapter((ListAdapter) pinListAdapter);
        this.userList.setDropListener(new DragSortListView.DropListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$$ExternalSyntheticLambda10
            @Override // com.mobeta.android.dslv.DragSortListView.DropListener
            public final void drop(int i2, int i3) {
                AuthenticatorActivity.this.m41x7d841896(i2, i3);
            }
        });
        this.userList.setDragListener(new DragSortListView.DragListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$$ExternalSyntheticLambda11
            @Override // com.mobeta.android.dslv.DragSortListView.DragListener
            public final void drag(int i2, int i3) {
                AuthenticatorActivity.this.m42x37f9b917(i2, i3);
            }
        });
        DragItemController dragItemController = new DragItemController(this.userList, this);
        dragItemController.setStartDraggingListener(new DragItemController.StartDraggingListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$$ExternalSyntheticLambda12
            @Override // com.google.android.apps.authenticator.AuthenticatorActivity.DragItemController.StartDraggingListener
            public final void startDragging() {
                AuthenticatorActivity.this.m43xf26f5998();
            }
        });
        this.userList.setFloatViewManager(dragItemController);
        this.userList.setOnTouchListener(dragItemController);
        if (bundle == null) {
            DependencyInjector.getOptionalFeatures().onAuthenticatorActivityCreated(this);
            handleIntent(getIntent());
        }
        sendBroadcast(new Intent(this, AuditLogTimerStarter.class));
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        PinInfo pinInfo = this.users[(int) ((AdapterView.AdapterContextMenuInfo) contextMenuInfo).id];
        getMenuInflater().inflate(R.menu.account_list_context, contextMenu);
        contextMenu.setHeaderTitle(pinInfo.getIndex().getStrippedName());
        if (!isRenameAccountAvailableSupported(pinInfo)) {
            contextMenu.removeItem(R.id.rename);
        }
        if (!isCheckAccountKeyValueSupported(pinInfo)) {
            contextMenu.removeItem(R.id.check_code);
        }
        this.mostRecentContextMenu = contextMenu;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onMigrationButterbarClicked(View view) {
        showAuditLog();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(getString(R.string.app_name), "AuthenticatorActivity: onNewIntent");
        handleIntent(intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.how_it_works) {
            displayHowItWorksInstructions();
            return true;
        } else if (menuItem.getItemId() == R.id.switch_ui_mode) {
            switchUiMode();
            return true;
        } else if (menuItem.getItemId() == R.id.settings) {
            showSettings();
            return true;
        } else if (menuItem.getItemId() == R.id.migration) {
            startMigration();
            return true;
        } else if (menuItem.getItemId() == R.id.help_and_feedback) {
            HelpAndFeedback.launchHelpIntent(this);
            return true;
        } else {
            return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.users.length > 0) {
            menu.findItem(R.id.switch_ui_mode).setVisible(true);
            menu.findItem(R.id.switch_ui_mode).setTitle(this.darkModeEnabled ? R.string.switch_ui_mode_light : R.string.switch_ui_mode_dark);
        } else {
            menu.findItem(R.id.switch_ui_mode).setVisible(false);
        }
        return true;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Log.i(getString(R.string.app_name), "AuthenticatorActivity: onResume");
        this.darkModeEnabled = this.preferences.getBoolean(BackupKeys.KEY_DARK_MODE_ENABLED, false);
        this.onboardingCompleted = this.preferences.getBoolean(BackupKeys.KEY_ONBOARDING_COMPLETED, false);
        refreshToolbarAndStatusBarStyle();
    }

    @Override // androidx.activity.ComponentActivity
    public Object onRetainCustomNonConfigurationInstance() {
        return this.users;
    }

    @Override // androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(KEY_FIRST_ACCOUNT_ADDED_NOTICE_DISPLAY_REQUIRED, this.firstAccountAddedNoticeDisplayRequired);
        bundle.putBoolean(KEY_ADD_ACCOUNT_MENU_OPEN, this.addAccountFab.isExpanded());
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        updateCodesAndStartTotpCountdownTask();
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        stopTotpCountdownTask();
        super.onStop();
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(final int i, Bundle bundle) {
        switch (i) {
            case 13:
                final SaveKeyDialogParams saveKeyDialogParams = (SaveKeyDialogParams) bundle.getSerializable(KEY_SAVE_KEY_DIALOG_PARAMS);
                AlertDialog create = new MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_GoogleMaterial_MaterialAlertDialog_Centered).setTitle(R.string.save_key_message).setMessage((CharSequence) saveKeyDialogParams.index.toString()).setIcon(R.drawable.quantum_gm_ic_report_problem_googblue_24).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$$ExternalSyntheticLambda2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i2) {
                        AuthenticatorActivity.this.m44xe22742f4(saveKeyDialogParams, dialogInterface, i2);
                    }
                }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).create();
                create.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$$ExternalSyntheticLambda3
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        AuthenticatorActivity.this.m45x9c9ce375(i, dialogInterface);
                    }
                });
                return create;
            case 14:
            case 17:
            case 18:
            case 19:
            default:
                return null;
            case 15:
                Dialog createOkAlertDialog = createOkAlertDialog(R.string.error_title, R.string.error_qr, R.drawable.quantum_gm_ic_report_problem_googblue_24);
                markDialogAsResultOfSaveKeyIntent(createOkAlertDialog);
                return createOkAlertDialog;
            case 16:
                Dialog createOkAlertDialog2 = createOkAlertDialog(R.string.error_title, R.string.error_uri, R.drawable.quantum_gm_ic_report_problem_googblue_24);
                markDialogAsResultOfSaveKeyIntent(createOkAlertDialog2);
                return createOkAlertDialog2;
            case 20:
                return new MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_GoogleMaterial_MaterialAlertDialog_Centered).setTitle(R.string.camera_not_found_on_device_title).setMessage(R.string.camera_not_found_on_device_detail).setPositiveButton(R.string.close, (DialogInterface.OnClickListener) null).create();
            case 21:
                Dialog createOkAlertDialog3 = createOkAlertDialog(R.string.error_title, R.string.error_outdated_migration, R.drawable.quantum_gm_ic_report_problem_googblue_24);
                markDialogAsResultOfSaveKeyIntent(createOkAlertDialog3);
                return createOkAlertDialog3;
        }
    }

    public boolean onContextItemSelected(MenuItem menuItem, long j) {
        int i = (int) j;
        final AccountDb.AccountIndex index = this.users[i].getIndex();
        if (menuItem.getItemId() == R.id.copy) {
            copyStringToClipboard(this, this.users[i].getPin());
            Toast.makeText(this, R.string.copied_to_clipboard_toast, 0).show();
            return true;
        } else if (menuItem.getItemId() == R.id.check_code) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClass(this, CheckCodeActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
            return true;
        } else if (menuItem.getItemId() == R.id.rename) {
            View inflate = getLayoutInflater().inflate(R.layout.rename, (ViewGroup) findViewById(R.id.rename_root));
            EditText editText = (EditText) inflate.findViewById(R.id.rename_edittext);
            editText.setText(index.getStrippedName());
            new MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_GoogleMaterial_MaterialAlertDialog_Centered).setTitle(R.string.rename).setView(inflate).setPositiveButton(R.string.submit, getRenameClickListener(this, index, editText)).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
            return true;
        } else if (menuItem.getItemId() == R.id.delete) {
            new MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_GoogleMaterial_MaterialAlertDialog_Centered).setTitle((CharSequence) getString(R.string.remove_account_dialog_title, new Object[]{index})).setMessage((CharSequence) Utilities.getStyledTextFromHtml(getString(R.string.remove_account_dialog_message))).setPositiveButton(R.string.remove_account_dialog_button_remove, new DialogInterface.OnClickListener() { // from class: com.google.android.apps.authenticator.AuthenticatorActivity$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    AuthenticatorActivity.this.m37x7b71c912(index, dialogInterface, i2);
                }
            }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).setIcon(R.drawable.quantum_gm_ic_report_problem_googblue_24).show();
            return true;
        } else {
            return super.onContextItemSelected(menuItem);
        }
    }

    public void refreshView(boolean z) {
        List accounts = this.accountDb.getAccounts();
        int size = accounts.size();
        boolean z2 = z || this.users.length != size;
        if (z2) {
            this.users = new PinInfo[size];
        }
        for (int i = 0; i < size; i++) {
            try {
                computeAndDisplayPin((AccountDb.AccountIndex) accounts.get(i), i, false);
            } catch (OtpSourceException e) {
            }
        }
        if (z2) {
            if (this.actionMode != null) {
                actionModeFinish();
                this.actionMode = null;
            }
            PinListAdapter pinListAdapter = new PinListAdapter(this, R.layout.user_row, this.users);
            this.userAdapter = pinListAdapter;
            this.userList.setAdapter((ListAdapter) pinListAdapter);
        } else if (z) {
            this.userAdapter.notifyDataSetChanged();
        } else {
            for (int i2 = 0; i2 < this.users.length; i2++) {
                if (((PinInfo) this.userAdapter.getItem(i2)) != null) {
                    PinListAdapter pinListAdapter2 = this.userAdapter;
                    EmptySpaceClickableDragSortListView emptySpaceClickableDragSortListView = this.userList;
                    pinListAdapter2.getView(i2, emptySpaceClickableDragSortListView.getChildAt((emptySpaceClickableDragSortListView.getHeaderViewsCount() + i2) - this.userList.getFirstVisiblePosition()), this.userList);
                } else {
                    Log.w(LOCAL_TAG, String.format("Corrupted account detected at position #%s", Integer.valueOf(i2)));
                }
            }
        }
        refreshFirstAccountAddedNoticeDisplay();
        refreshMigrationButterbarDisplay();
        refreshOrientationState();
    }
}
