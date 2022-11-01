package com.google.android.apps.authenticator.util;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import com.google.android.apps.authenticator2.R;
import com.google.android.gms.feedback.FeedbackOptions;
import com.google.android.gms.feedback.ThemeSettings;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.GoogleHelpLauncher;

/* compiled from: PG */
/* loaded from: classes.dex */
public class HelpAndFeedback {
    private static final String ACTION_OPEN_SOURCES = "com.google.android.apps.authenticator.settings.OPEN_SOURCE_NOTICES";
    private static final String DEFAULT_CONTEXT = "default_catchall";
    private static final Uri FALLBACK_URL = Uri.parse("https://support.google.com/accounts/topic/6072104");

    public static void launchHelpIntent(Activity activity) {
        Resources resources = activity.getResources();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(resources.getString(R.string.terms_page_url)));
        Intent intent2 = new Intent("android.intent.action.VIEW");
        intent2.setData(Uri.parse(resources.getString(R.string.privacy_page_url)));
        new GoogleHelpLauncher(activity).launch(new GoogleHelp(DEFAULT_CONTEXT).setFallbackSupportUri(FALLBACK_URL).setThemeSettings(new ThemeSettings().setTheme(1)).setFeedbackOptions(new FeedbackOptions.Builder().setScreenshot(GoogleHelp.getScreenshot(activity)).build(), activity.getCacheDir()).addAdditionalOverflowMenuItem(0, resources.getString(R.string.terms_preference_title), intent).addAdditionalOverflowMenuItem(1, resources.getString(R.string.privacy_preference_title), intent2).addAdditionalOverflowMenuItem(2, resources.getString(R.string.opensource_licenses_preference_title), new Intent(ACTION_OPEN_SOURCES)).buildHelpIntent());
    }
}
