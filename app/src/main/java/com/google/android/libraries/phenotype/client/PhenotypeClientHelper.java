package com.google.android.libraries.phenotype.client;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import com.google.android.apps.authenticator.testability.android.os.PowerManager;
import com.google.common.base.Optional;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeClientHelper {
    static volatile Optional isValidContentProvider = Optional.absent();
    private static final Object isValidContentProviderLock = new Object();

    private static boolean isGmsCorePreinstalled(Context context) {
        try {
            return (context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).flags & AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_SYSTEM_HEALTH_FUNCTIONAL_DEBUGGING_WW_VALUE) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean validateContentProvider(Context context, Uri uri) {
        int i;
        boolean z;
        String authority = uri.getAuthority();
        boolean z2 = false;
        if (!"com.google.android.gms.phenotype".equals(authority)) {
            Log.e("PhenotypeClientHelper", authority + " is an unsupported authority. Only com.google.android.gms.phenotype authority is supported.");
            return false;
        } else if (isValidContentProvider.isPresent()) {
            return ((Boolean) isValidContentProvider.get()).booleanValue();
        } else {
            synchronized (isValidContentProviderLock) {
                if (isValidContentProvider.isPresent()) {
                    return ((Boolean) isValidContentProvider.get()).booleanValue();
                }
                if (!"com.google.android.gms".equals(context.getPackageName())) {
                    PackageManager packageManager = context.getPackageManager();
                    if (Build.VERSION.SDK_INT < 29) {
                        i = 0;
                    } else {
                        i = PowerManager.ACQUIRE_CAUSES_WAKEUP;
                    }
                    ProviderInfo resolveContentProvider = packageManager.resolveContentProvider("com.google.android.gms.phenotype", i);
                    z = resolveContentProvider != null && "com.google.android.gms".equals(resolveContentProvider.packageName);
                } else {
                    z = true;
                }
                if (z && isGmsCorePreinstalled(context)) {
                    z2 = true;
                }
                isValidContentProvider = Optional.of(Boolean.valueOf(z2));
                return ((Boolean) isValidContentProvider.get()).booleanValue();
            }
        }
    }
}
