package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleAuthUtil extends GoogleAuthUtilLight {
    public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
    public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
    public static final int DELEGATION_TYPE_CHILD_IMPERSONATION = 1;
    public static final String KEY_CALLER_UID = GoogleAuthUtilLight.KEY_CALLER_UID;
    public static final String KEY_ANDROID_PACKAGE_NAME = GoogleAuthUtilLight.KEY_ANDROID_PACKAGE_NAME;

    public static Account[] getAccounts(Context context, String str) {
        return GoogleAuthUtilLight.getAccounts(context, str);
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2, Bundle bundle) {
        return GoogleAuthUtilLight.getToken(context, str, str2, bundle);
    }

    @Deprecated
    public static void invalidateToken(Context context, String str) {
        GoogleAuthUtilLight.invalidateToken(context, str);
    }
}
