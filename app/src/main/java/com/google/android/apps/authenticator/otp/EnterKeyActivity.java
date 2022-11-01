package com.google.android.apps.authenticator.otp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import com.google.android.apps.authenticator.AuthenticatorActivity;
import com.google.android.apps.authenticator.backup.BackupKeys;
import com.google.android.apps.authenticator.otp.AccountDb;
import com.google.android.apps.authenticator.otp.EnterKeyActivity;
import com.google.android.apps.authenticator.testability.TestableActivity;
import com.google.android.apps.authenticator.util.Base32String;
import com.google.android.apps.authenticator2.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

/* compiled from: PG */
/* loaded from: classes.dex */
public class EnterKeyActivity extends TestableActivity implements TextWatcher {
    static final int DIALOG_ID_INVALID_DEVICE = 1;
    private static final int MIN_KEY_BYTES = 10;
    private EditText accountName;
    private TextInputLayout accountNameInputLayout;
    private AutoCompleteTextView accountTypeDropdown;
    private String[] accountTypeOptions;
    private final View.OnClickListener addButtonEnterKeyOnClickListener = new AnonymousClass1();
    private EditText keyEntryField;
    private TextInputLayout keyEntryFieldInputLayout;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.apps.authenticator.otp.EnterKeyActivity$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$0$com-google-android-apps-authenticator-otp-EnterKeyActivity$1  reason: not valid java name */
        public /* synthetic */ void m80xb9c350a0() {
            Intent intent = new Intent(EnterKeyActivity.this, AuthenticatorActivity.class);
            intent.addFlags(67108864);
            EnterKeyActivity.this.startActivity(intent);
            EnterKeyActivity.this.finish();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AccountDb.OtpType otpType;
            if (EnterKeyActivity.this.accountTypeDropdown.getText().toString().equals(EnterKeyActivity.this.getString(R.string.time_based_otp_type))) {
                otpType = AccountDb.OtpType.TOTP;
            } else {
                otpType = AccountDb.OtpType.HOTP;
            }
            if (EnterKeyActivity.this.validateFieldsAndUpdateStatus(true)) {
                AuthenticatorActivity.saveSecret(EnterKeyActivity.this, new AccountDb.AccountIndex(EnterKeyActivity.this.accountName.getText().toString(), null), EnterKeyActivity.this.getEnteredKey(), otpType, AccountDb.DEFAULT_HOTP_COUNTER, new AuthenticatorActivity.SaveSecretFlowListener() { // from class: com.google.android.apps.authenticator.otp.EnterKeyActivity$1$$ExternalSyntheticLambda0
                    @Override // com.google.android.apps.authenticator.AuthenticatorActivity.SaveSecretFlowListener
                    public final void onDone() {
                        EnterKeyActivity.AnonymousClass1.this.m80xb9c350a0();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getEnteredKey() {
        return this.keyEntryField.getText().toString().replace('1', 'I').replace('0', 'O');
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        validateFieldsAndUpdateStatus(false);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        getWindow().setFlags(8192, 8192);
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(BackupKeys.KEY_DARK_MODE_ENABLED, false)) {
            setTheme(R.style.AuthenticatorTheme_NoActionBar_Dark);
        } else {
            setTheme(R.style.AuthenticatorTheme_NoActionBar);
        }
        super.onCreate(bundle);
        setContentView(R.layout.enter_key);
        setSupportActionBar((Toolbar) findViewById(R.id.enter_key_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.keyEntryField = (EditText) findViewById(R.id.key_value);
        this.accountName = (EditText) findViewById(R.id.account_name);
        this.keyEntryFieldInputLayout = (TextInputLayout) findViewById(R.id.key_value_input_layout);
        this.accountNameInputLayout = (TextInputLayout) findViewById(R.id.account_name_input_layout);
        this.accountTypeOptions = new String[]{getString(R.string.time_based_otp_type), getString(R.string.counter_based_otp_type)};
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.enter_key_exposed_dropdown);
        this.accountTypeDropdown = autoCompleteTextView;
        autoCompleteTextView.setAdapter(new ArrayAdapter(this, R.layout.enter_key_account_type_list_item, this.accountTypeOptions));
        this.accountTypeDropdown.setThreshold(Integer.MAX_VALUE);
        this.accountTypeDropdown.setText((CharSequence) getString(R.string.time_based_otp_type), false);
        this.accountNameInputLayout.setErrorEnabled(true);
        this.keyEntryFieldInputLayout.setErrorEnabled(true);
        this.accountName.addTextChangedListener(this);
        this.keyEntryField.addTextChangedListener(this);
        findViewById(R.id.add_account_button_enter_key).setOnClickListener(this.addButtonEnterKeyOnClickListener);
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i, Bundle bundle) {
        switch (i) {
            case 1:
                return new MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_GoogleMaterial_MaterialAlertDialog_Centered).setIcon(R.drawable.quantum_gm_ic_report_problem_googblue_24).setTitle(R.string.error_title).setMessage(R.string.error_invalid_device).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).create();
            default:
                return super.onCreateDialog(i, bundle);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            default:
                return true;
        }
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean validateFieldsAndUpdateStatus(boolean z) {
        boolean z2;
        if (this.accountName.getText().toString().isEmpty() && z) {
            this.accountNameInputLayout.setError(getString(R.string.enter_account_name_missing));
            z2 = true;
        } else {
            this.accountNameInputLayout.setError(null);
            z2 = false;
        }
        try {
            if (Base32String.decode(getEnteredKey()).length < 10) {
                this.keyEntryFieldInputLayout.setError(z ? getString(R.string.enter_key_too_short) : null);
                z2 = true;
            } else {
                this.keyEntryFieldInputLayout.setError(null);
            }
        } catch (Base32String.DecodingException e) {
            this.keyEntryFieldInputLayout.setError(getString(R.string.enter_key_illegal_char));
            z2 = true;
        }
        return !z2;
    }
}
