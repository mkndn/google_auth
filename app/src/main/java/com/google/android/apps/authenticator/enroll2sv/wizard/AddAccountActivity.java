package com.google.android.apps.authenticator.enroll2sv.wizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.google.android.apps.authenticator.AuthenticatorActivity;
import com.google.android.apps.authenticator.howitworks.HowItWorksActivity;
import com.google.android.apps.authenticator.migration.MigrationImportActivity;
import com.google.android.apps.authenticator.otp.EnterKeyActivity;
import com.google.android.apps.authenticator.testability.DependencyInjector;
import com.google.android.apps.authenticator.testability.TestableActivity;
import com.google.android.apps.authenticator.util.HelpAndFeedback;
import com.google.android.apps.authenticator2.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AddAccountActivity extends TestableActivity {
    public void buttonImportClicked(View view) {
        startActivity(new Intent(this, MigrationImportActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.enroll2sv_add_account);
        setSupportActionBar((Toolbar) findViewById(R.id.add_account_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        findViewById(R.id.enroll2sv_choose_account_page_scan_barcode_layout).setOnClickListener(new View.OnClickListener() { // from class: com.google.android.apps.authenticator.enroll2sv.wizard.AddAccountActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AddAccountActivity addAccountActivity = AddAccountActivity.this;
                addAccountActivity.startActivity(AuthenticatorActivity.getLaunchIntentActionScanBarcode(addAccountActivity, true));
            }
        });
        findViewById(R.id.enroll2sv_choose_account_page_enter_key_layout).setOnClickListener(new View.OnClickListener() { // from class: com.google.android.apps.authenticator.enroll2sv.wizard.AddAccountActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AddAccountActivity.this.startActivity(new Intent(AddAccountActivity.this, EnterKeyActivity.class));
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_account, menu);
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.how_it_works) {
            startActivity(new Intent(this, HowItWorksActivity.class));
            return true;
        } else if (menuItem.getItemId() == R.id.settings) {
            startActivity(new Intent(this, DependencyInjector.getOptionalFeatures().getSettingsActivity()));
            return true;
        } else if (menuItem.getItemId() == R.id.help_and_feedback) {
            HelpAndFeedback.launchHelpIntent(this);
            return true;
        } else {
            return super.onOptionsItemSelected(menuItem);
        }
    }
}
