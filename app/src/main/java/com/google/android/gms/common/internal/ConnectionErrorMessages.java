package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import androidx.core.os.ConfigurationCompat;
import com.google.android.gms.base.R$string;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConnectionErrorMessages {
    private static final SimpleArrayMap sCache = new SimpleArrayMap();
    private static Locale sCurrentLocale;

    public static String getAppName(Context context) {
        String packageName = context.getPackageName();
        try {
            return Wrappers.packageManager(context).getApplicationLabel(packageName).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            String str = context.getApplicationInfo().name;
            if (TextUtils.isEmpty(str)) {
                return packageName;
            }
            return str;
        }
    }

    public static String getDefaultNotificationChannelName(Context context) {
        return context.getResources().getString(R$string.common_google_play_services_notification_channel_name);
    }

    public static String getErrorDialogButtonMessage(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(R$string.common_google_play_services_install_button);
            case 2:
                return resources.getString(R$string.common_google_play_services_update_button);
            case 3:
                return resources.getString(R$string.common_google_play_services_enable_button);
            default:
                return resources.getString(17039370);
        }
    }

    public static String getErrorMessage(Context context, int i) {
        Resources resources = context.getResources();
        String appName = getAppName(context);
        switch (i) {
            case 1:
                return resources.getString(R$string.common_google_play_services_install_text, appName);
            case 2:
                return DeviceProperties.isWearableWithoutPlayStore(context) ? resources.getString(R$string.common_google_play_services_wear_update_text) : resources.getString(R$string.common_google_play_services_update_text, appName);
            case 3:
                return resources.getString(R$string.common_google_play_services_enable_text, appName);
            case 5:
                return getRemoteMessage(context, "common_google_play_services_invalid_account_text", appName);
            case 7:
                return getRemoteMessage(context, "common_google_play_services_network_error_text", appName);
            case 9:
                return resources.getString(R$string.common_google_play_services_unsupported_text, appName);
            case 16:
                return getRemoteMessage(context, "common_google_play_services_api_unavailable_text", appName);
            case 17:
                return getRemoteMessage(context, "common_google_play_services_sign_in_failed_text", appName);
            case 18:
                return resources.getString(R$string.common_google_play_services_updating_text, appName);
            case 20:
                return getRemoteMessage(context, "common_google_play_services_restricted_profile_text", appName);
            default:
                return resources.getString(com.google.android.gms.common.R$string.common_google_play_services_unknown_issue, appName);
        }
    }

    public static String getErrorNotificationMessage(Context context, int i) {
        if (i != 6 && i != 19) {
            return getErrorMessage(context, i);
        }
        return getRemoteMessage(context, "common_google_play_services_resolution_required_text", getAppName(context));
    }

    public static String getErrorNotificationTitle(Context context, int i) {
        String errorTitle;
        if (i == 6) {
            errorTitle = getRemoteString(context, "common_google_play_services_resolution_required_title");
        } else {
            errorTitle = getErrorTitle(context, i);
        }
        return errorTitle == null ? context.getResources().getString(R$string.common_google_play_services_notification_ticker) : errorTitle;
    }

    public static String getErrorTitle(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(R$string.common_google_play_services_install_title);
            case 2:
                return resources.getString(R$string.common_google_play_services_update_title);
            case 3:
                return resources.getString(R$string.common_google_play_services_enable_title);
            case 4:
            case 6:
            case 18:
                return null;
            case 5:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return getRemoteString(context, "common_google_play_services_invalid_account_title");
            case 7:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return getRemoteString(context, "common_google_play_services_network_error_title");
            case 8:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case 9:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            case 10:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case 11:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case 12:
            case 13:
            case 14:
            case 15:
            case 19:
            default:
                Log.e("GoogleApiAvailability", "Unexpected error code " + i);
                return null;
            case 16:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case 17:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return getRemoteString(context, "common_google_play_services_sign_in_failed_title");
            case 20:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return getRemoteString(context, "common_google_play_services_restricted_profile_title");
        }
    }

    private static String getRemoteMessage(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String remoteString = getRemoteString(context, str);
        if (remoteString == null) {
            remoteString = resources.getString(com.google.android.gms.common.R$string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, remoteString, str2);
    }

    private static String getRemoteString(Context context, String str) {
        SimpleArrayMap simpleArrayMap = sCache;
        synchronized (simpleArrayMap) {
            Locale locale = ConfigurationCompat.getLocales(context.getResources().getConfiguration()).get(0);
            if (!locale.equals(sCurrentLocale)) {
                simpleArrayMap.clear();
                sCurrentLocale = locale;
            }
            String str2 = (String) simpleArrayMap.get(str);
            if (str2 != null) {
                return str2;
            }
            Resources remoteResource = GooglePlayServicesUtil.getRemoteResource(context);
            if (remoteResource == null) {
                return null;
            }
            int identifier = remoteResource.getIdentifier(str, "string", "com.google.android.gms");
            if (identifier == 0) {
                Log.w("GoogleApiAvailability", "Missing resource: " + str);
                return null;
            }
            String string = remoteResource.getString(identifier);
            if (TextUtils.isEmpty(string)) {
                Log.w("GoogleApiAvailability", "Got empty resource: " + str);
                return null;
            }
            simpleArrayMap.put(str, string);
            return string;
        }
    }
}
