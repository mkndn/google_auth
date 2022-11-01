package com.google.android.apps.authenticator.testability;

import com.google.android.apps.authenticator.AuthenticatorActivity;
import com.google.android.apps.authenticator.settings.SettingsActivity;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MarketBuildOptionalFeatures implements OptionalFeatures {
    @Override // com.google.android.apps.authenticator.testability.OptionalFeatures
    public Class getSettingsActivity() {
        return SettingsActivity.class;
    }

    @Override // com.google.android.apps.authenticator.testability.OptionalFeatures
    public void onAuthenticatorActivityCreated(AuthenticatorActivity authenticatorActivity) {
    }
}
