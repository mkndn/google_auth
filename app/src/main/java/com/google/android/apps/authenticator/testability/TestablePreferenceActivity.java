package com.google.android.apps.authenticator.testability;

import android.content.ComponentName;
import android.content.Intent;
import com.google.android.apps.authenticator.settings.AppCompatPreferenceActivity;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TestablePreferenceActivity extends AppCompatPreferenceActivity {
    @Override // android.preference.PreferenceActivity
    protected boolean isValidFragment(String str) {
        return false;
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        StartActivityListener startActivityListener = DependencyInjector.getStartActivityListener();
        if (startActivityListener != null && startActivityListener.onStartActivityInvoked(this, intent)) {
            return;
        }
        super.startActivity(intent);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        StartActivityListener startActivityListener = DependencyInjector.getStartActivityListener();
        if (startActivityListener != null && startActivityListener.onStartActivityInvoked(this, intent)) {
            return;
        }
        super.startActivityForResult(intent, i);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ComponentName startService(Intent intent) {
        StartServiceListener startServiceListener = DependencyInjector.getStartServiceListener();
        if (startServiceListener == null || !startServiceListener.onStartServiceInvoked(this, intent)) {
            return super.startService(intent);
        }
        return null;
    }
}
