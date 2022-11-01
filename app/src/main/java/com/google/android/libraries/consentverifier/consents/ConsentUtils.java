package com.google.android.libraries.consentverifier.consents;

import android.content.Context;
import android.provider.Settings;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
class ConsentUtils {
    private static final TimeUnit DEFAULT_QUERY_TIMEOUT_TIME_UNIT = TimeUnit.MILLISECONDS;
    private final Context context;

    /* JADX INFO: Access modifiers changed from: protected */
    public ConsentUtils(Context context) {
        this.context = context.getApplicationContext();
    }

    protected int getGlobalSettingInt(String str) {
        return Settings.Global.getInt(this.context.getContentResolver(), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isGlobalSettingIntEnabled(String str) {
        return getGlobalSettingInt(str) == 1;
    }
}
