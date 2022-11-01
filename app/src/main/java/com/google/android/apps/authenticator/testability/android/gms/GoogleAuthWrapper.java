package com.google.android.apps.authenticator.testability.android.gms;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleAuthWrapper {
    private static final String TAG = "GoogleAuthWrapper";

    public String getToken(Context context, String str, String str2, Bundle bundle) {
        return GoogleAuthUtil.getToken(context, str, str2, bundle);
    }

    public void invalidateToken(Context context, String str) {
        GoogleAuthUtil.invalidateToken(context, str);
    }

    public Account[] listGoogleAccounts(Context context) {
        Account[] accountArr = new Account[0];
        try {
            return GoogleAuthUtil.getAccounts(context, "com.google");
        } catch (RemoteException | GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | SecurityException e) {
            Log.d(TAG, "Error retrieving accounts from GmsCore.");
            return accountArr;
        }
    }
}
