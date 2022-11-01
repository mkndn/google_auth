package com.google.android.apps.authenticator.migration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.google.android.apps.authenticator.AuthenticatorActivity;
import com.google.android.apps.authenticator.testability.TestableActivity;
import com.google.android.apps.authenticator2.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MigrationImportActivity extends TestableActivity {
    public void buttonScanClicked(View view) {
        Intent intent = new Intent(this, AuthenticatorActivity.class);
        intent.setAction(AuthenticatorActivity.ACTION_SCAN_BARCODE);
        startActivity(intent);
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
        setContentView(R.layout.migration_import_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.migration_import_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
