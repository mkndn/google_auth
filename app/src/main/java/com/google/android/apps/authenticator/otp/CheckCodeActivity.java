package com.google.android.apps.authenticator.otp;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;
import com.google.android.apps.authenticator.otp.AccountDb;
import com.google.android.apps.authenticator.testability.DependencyInjector;
import com.google.android.apps.authenticator.util.Base32String;
import com.google.android.apps.authenticator.util.Utilities;
import com.google.android.apps.authenticator2.R;
import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CheckCodeActivity extends AppCompatActivity {
    private TextView mCheckCodeTextView;
    private TextView mCodeTextView;
    private TextView mCounterValue;

    static String getCheckCode(String str) {
        byte[] decode = Base32String.decode(str);
        Mac mac = Mac.getInstance("HMACSHA1");
        mac.init(new SecretKeySpec(decode, ""));
        return new PasscodeGenerator(mac).generateResponseCode(0L);
    }

    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView(R.layout.check_code);
        setSupportActionBar((Toolbar) findViewById(R.id.enter_key_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.mCodeTextView = (TextView) findViewById(R.id.code_value);
        this.mCheckCodeTextView = (TextView) findViewById(R.id.check_code);
        this.mCounterValue = (TextView) findViewById(R.id.counter_value);
        AccountDb.AccountIndex accountIndex = (AccountDb.AccountIndex) getIntent().getExtras().getSerializable("index");
        AccountDb accountDb = DependencyInjector.getAccountDb();
        if (accountDb.getType(accountIndex) == AccountDb.OtpType.HOTP) {
            this.mCounterValue.setText(accountDb.getCounter(accountIndex).toString());
            findViewById(R.id.counter_area).setVisibility(0);
        } else {
            findViewById(R.id.counter_area).setVisibility(8);
        }
        String str2 = null;
        try {
            str = getCheckCode(accountDb.getSecret(accountIndex));
        } catch (Base32String.DecodingException e) {
            str2 = getString(R.string.decoding_exception);
            str = null;
        } catch (GeneralSecurityException e2) {
            str2 = getString(R.string.general_security_exception);
            str = null;
        }
        if (str2 != null) {
            this.mCheckCodeTextView.setText(str2);
            return;
        }
        this.mCodeTextView.setText(Utilities.getStyledPincode(str));
        this.mCheckCodeTextView.setText(Utilities.getStyledTextFromHtml(String.format(getString(R.string.check_code), TextUtils.htmlEncode(accountIndex.getStrippedName()))));
        this.mCheckCodeTextView.setVisibility(0);
        findViewById(R.id.code_area).setVisibility(0);
        if (Build.VERSION.SDK_INT < 21) {
            findViewById(R.id.toolbar_shadow).setVisibility(0);
        } else {
            findViewById(R.id.toolbar_shadow).setVisibility(8);
        }
    }
}
