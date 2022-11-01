package com.google.android.apps.authenticator.migration;

import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.google.android.apps.authenticator.auditlog.ViewAuditLogActivity;
import com.google.android.apps.authenticator.testability.TestableActivity;
import com.google.android.apps.authenticator2.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MigrationIntroductionActivity extends TestableActivity {
    private static final int AUTHENTICATION_ACTIVITY_REQUEST_CODE = 1;

    private void proceedToExportAccountSelection() {
        startActivity(new Intent(this, MigrationExportActivity.class));
    }

    public void buttonAuditLogClicked(View view) {
        startActivity(new Intent(this, ViewAuditLogActivity.class));
    }

    public void buttonExportClicked(View view) {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService("keyguard");
        if (Build.VERSION.SDK_INT >= 23 && keyguardManager.isDeviceSecure()) {
            startActivityForResult(keyguardManager.createConfirmDeviceCredentialIntent(getString(R.string.migration_export_biometric_prompt_title), getString(R.string.migration_export_biometric_prompt_subtitle)), 1);
        } else {
            proceedToExportAccountSelection();
        }
    }

    public void buttonImportClicked(View view) {
        startActivity(new Intent(this, MigrationImportActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            proceedToExportAccountSelection();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getResources().getBoolean(R.bool.isTablet)) {
            setRequestedOrientation(2);
        } else {
            setRequestedOrientation(12);
        }
        setContentView(R.layout.migration_introduction_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.migration_introduction_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
