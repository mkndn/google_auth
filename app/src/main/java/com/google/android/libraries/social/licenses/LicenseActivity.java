package com.google.android.libraries.social.licenses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LicenseActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onRestoreInstanceState$0$com-google-android-libraries-social-licenses-LicenseActivity  reason: not valid java name */
    public /* synthetic */ void m379xf7361129(int i, ScrollView scrollView) {
        Layout layout = ((TextView) findViewById(R$id.license_activity_textview)).getLayout();
        if (layout != null) {
            scrollView.scrollTo(0, layout.getLineTop(layout.getLineForOffset(i)));
        }
    }

    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.libraries_social_licenses_license_activity);
        License license = (License) getIntent().getParcelableExtra("license");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(license.getLibraryName());
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setLogo(null);
        }
        TextView textView = (TextView) findViewById(R$id.license_activity_textview);
        String licenseText = Licenses.getLicenseText(this, license);
        if (licenseText == null) {
            finish();
        } else {
            textView.setText(licenseText);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        final ScrollView scrollView = (ScrollView) findViewById(R$id.license_activity_scrollview);
        final int i = bundle.getInt("scroll_pos");
        if (i != 0) {
            scrollView.post(new Runnable() { // from class: com.google.android.libraries.social.licenses.LicenseActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LicenseActivity.this.m379xf7361129(i, scrollView);
                }
            });
        }
    }

    @Override // androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ScrollView scrollView = (ScrollView) findViewById(R$id.license_activity_scrollview);
        Layout layout = ((TextView) findViewById(R$id.license_activity_textview)).getLayout();
        if (layout != null) {
            bundle.putInt("scroll_pos", layout.getLineStart(layout.getLineForVertical(scrollView.getScrollY())));
        }
    }
}
