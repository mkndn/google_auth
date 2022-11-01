package com.google.android.apps.authenticator.settings;

import android.os.Bundle;
import com.google.android.apps.authenticator.testability.TestablePreferenceActivity;
import com.google.android.apps.authenticator2.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SettingsActivity extends TestablePreferenceActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.apps.authenticator.settings.AppCompatPreferenceActivity, android.preference.PreferenceActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.preferences);
    }
}
