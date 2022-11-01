package com.google.android.apps.authenticator.util.permissions;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import com.google.common.base.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
public class RuntimePermissionRequestor implements PermissionRequestor {
    @Override // com.google.android.apps.authenticator.util.permissions.PermissionRequestor
    public int checkSelfPermission(Context context, String str) {
        Preconditions.checkArgument(Build.VERSION.SDK_INT >= 23);
        return ActivityCompat.checkSelfPermission(context, str);
    }

    @Override // com.google.android.apps.authenticator.util.permissions.PermissionRequestor
    public void requestPermissions(Activity activity, String[] strArr, int i) {
        Preconditions.checkArgument(Build.VERSION.SDK_INT >= 23);
        ActivityCompat.requestPermissions(activity, strArr, i);
    }

    @Override // com.google.android.apps.authenticator.util.permissions.PermissionRequestor
    public boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
        Preconditions.checkArgument(Build.VERSION.SDK_INT >= 23);
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, str);
    }
}
