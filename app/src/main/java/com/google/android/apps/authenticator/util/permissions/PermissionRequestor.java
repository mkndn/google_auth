package com.google.android.apps.authenticator.util.permissions;

import android.app.Activity;
import android.content.Context;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface PermissionRequestor {
    int checkSelfPermission(Context context, String str);

    void requestPermissions(Activity activity, String[] strArr, int i);

    boolean shouldShowRequestPermissionRationale(Activity activity, String str);
}
