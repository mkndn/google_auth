package com.google.android.gms.googlehelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Looper;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.googlehelp.internal.common.GoogleHelpClient;
import com.google.android.gms.googlehelp.internal.common.GoogleHelpClientImpl;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import com.google.common.base.Supplier;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleHelpLauncher {
    public final Activity activity;
    private final Object chimeraActivity = null;
    private final Supplier clientSupplier;

    public GoogleHelpLauncher(final Activity activity) {
        this.activity = activity;
        this.clientSupplier = new Supplier() { // from class: com.google.android.gms.googlehelp.GoogleHelpLauncher$$ExternalSyntheticLambda2
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return GoogleHelpLauncher.lambda$new$0(activity);
            }
        };
    }

    private boolean canAnyActivityHandleIntent(Intent intent) {
        return !this.activity.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
    }

    public static /* synthetic */ GoogleHelpClient lambda$new$0(Activity activity) {
        return Help.getClient(activity);
    }

    void handlePlayServicesUnavailable(int i, GoogleHelp googleHelp) {
        final Intent data = new Intent("android.intent.action.VIEW").setData(googleHelp.getFallbackSupportUri());
        if (i != 7 && canAnyActivityHandleIntent(data)) {
            new TracingHandler(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.android.gms.googlehelp.GoogleHelpLauncher$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    GoogleHelpLauncher.this.m219x7f8fe345(data);
                }
            });
        } else {
            GooglePlayServicesUtil.showErrorDialogFragment(i, this.activity, 0);
        }
    }

    public int isGooglePlayServicesAvailable() {
        return GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.activity, GoogleHelpClientImpl.getStaticMinApkVersion());
    }

    /* renamed from: lambda$handlePlayServicesUnavailable$1$com-google-android-gms-googlehelp-GoogleHelpLauncher */
    public /* synthetic */ void m219x7f8fe345(Intent intent) {
        this.activity.startActivity(intent);
    }

    public void launch(Intent intent) {
        validateHelpIntent(intent);
        int isGooglePlayServicesAvailable = isGooglePlayServicesAvailable();
        if (isGooglePlayServicesAvailable == 0) {
            ((GoogleHelpClient) this.clientSupplier.get()).startHelp(intent);
        } else {
            handlePlayServicesUnavailable(isGooglePlayServicesAvailable, (GoogleHelp) intent.getParcelableExtra("EXTRA_GOOGLE_HELP"));
        }
    }

    void validateHelpIntent(Intent intent) {
        if (intent.getAction().equals("com.google.android.gms.googlehelp.HELP") && intent.hasExtra("EXTRA_GOOGLE_HELP")) {
            return;
        }
        throw new IllegalArgumentException("The intent you are trying to launch is not GoogleHelp intent! This class only supports GoogleHelp intents.");
    }
}
