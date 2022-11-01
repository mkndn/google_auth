package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.google.android.gms.common.api.internal.LifecycleFragment;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class DialogRedirect implements DialogInterface.OnClickListener {
    public static DialogRedirect getInstance(final Activity activity, final Intent intent, final int i) {
        return new DialogRedirect() { // from class: com.google.android.gms.common.internal.DialogRedirect.1
            @Override // com.google.android.gms.common.internal.DialogRedirect
            public void redirect() {
                Intent intent2 = intent;
                if (intent2 != null) {
                    activity.startActivityForResult(intent2, i);
                }
            }
        };
    }

    private static void logActivityNotFound(ActivityNotFoundException activityNotFoundException) {
        Log.e("DialogRedirect", Build.FINGERPRINT.contains("generic") ? "Failed to start resolution intent. This may occur when resolving Google Play services connection issues on emulators with Google APIs but not Google Play Store." : "Failed to start resolution intent.", activityNotFoundException);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            try {
                redirect();
            } catch (ActivityNotFoundException e) {
                logActivityNotFound(e);
            }
        } finally {
            dialogInterface.dismiss();
        }
    }

    protected abstract void redirect();

    public static DialogRedirect getInstance(final Fragment fragment, final Intent intent, final int i) {
        return new DialogRedirect() { // from class: com.google.android.gms.common.internal.DialogRedirect.2
            @Override // com.google.android.gms.common.internal.DialogRedirect
            public void redirect() {
                Intent intent2 = intent;
                if (intent2 != null) {
                    fragment.startActivityForResult(intent2, i);
                }
            }
        };
    }

    public static DialogRedirect getInstance(final LifecycleFragment lifecycleFragment, final Intent intent, final int i) {
        return new DialogRedirect() { // from class: com.google.android.gms.common.internal.DialogRedirect.3
            @Override // com.google.android.gms.common.internal.DialogRedirect
            public void redirect() {
                Intent intent2 = intent;
                if (intent2 != null) {
                    lifecycleFragment.startActivityForResult(intent2, i);
                }
            }
        };
    }
}
