package com.google.android.libraries.social.licenses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.google.android.libraries.social.licenses.LicenseMenuFragment;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LicenseMenuActivity extends AppCompatActivity implements LicenseMenuFragment.LicenseSelectionListener {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.libraries_social_licenses_license_menu_activity);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (!(supportFragmentManager.findFragmentById(R$id.license_menu_fragment_container) instanceof LicenseMenuFragment)) {
            supportFragmentManager.beginTransaction().add(R$id.license_menu_fragment_container, new LicenseMenuFragment()).commitNow();
        }
    }

    @Override // com.google.android.libraries.social.licenses.LicenseMenuFragment.LicenseSelectionListener
    public void onLicenseSelected(License license) {
        Intent intent = new Intent(this, LicenseActivity.class);
        intent.putExtra("license", license);
        startActivity(intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
