package com.google.android.libraries.phenotype.client;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import com.google.android.gsf.Gservices;
import com.google.android.libraries.phenotype.client.FlagLoader;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class GservicesLoader implements FlagLoader {
    private static GservicesLoader loader;
    private final Context context;
    private final ContentObserver observer;

    private GservicesLoader() {
        this.context = null;
        this.observer = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void clearLoader() {
        Context context;
        synchronized (GservicesLoader.class) {
            GservicesLoader gservicesLoader = loader;
            if (gservicesLoader != null && (context = gservicesLoader.context) != null && gservicesLoader.observer != null) {
                context.getContentResolver().unregisterContentObserver(loader.observer);
            }
            loader = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GservicesLoader getLoader(Context context) {
        GservicesLoader gservicesLoader;
        synchronized (GservicesLoader.class) {
            if (loader == null) {
                loader = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new GservicesLoader(context) : new GservicesLoader();
            }
            gservicesLoader = loader;
        }
        return gservicesLoader;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getBooleanFlagOrFalse(String str) {
        String flag = getFlag(str);
        return flag != null && Gservices.TRUE_PATTERN.matcher(flag).matches();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getFlag$0$com-google-android-libraries-phenotype-client-GservicesLoader  reason: not valid java name */
    public /* synthetic */ String m365x5a255ae2(String str) {
        return Gservices.getString(this.context.getContentResolver(), str, null);
    }

    @Override // com.google.android.libraries.phenotype.client.FlagLoader
    public String getFlag(final String str) {
        if (this.context == null) {
            return null;
        }
        try {
            return (String) FlagLoader.CC.executeBinderAware(new FlagLoader.BinderAwareFunction() { // from class: com.google.android.libraries.phenotype.client.GservicesLoader$$ExternalSyntheticLambda0
                @Override // com.google.android.libraries.phenotype.client.FlagLoader.BinderAwareFunction
                public final Object execute() {
                    return GservicesLoader.this.m365x5a255ae2(str);
                }
            });
        } catch (IllegalStateException | NullPointerException | SecurityException e) {
            Log.e("GservicesLoader", "Unable to read GServices for: " + str, e);
            return null;
        }
    }

    private GservicesLoader(Context context) {
        this.context = context;
        ContentObserver contentObserver = new ContentObserver(this, null) { // from class: com.google.android.libraries.phenotype.client.GservicesLoader.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                PhenotypeFlag.invalidateProcessCache();
            }
        };
        this.observer = contentObserver;
        context.getContentResolver().registerContentObserver(Gservices.CONTENT_URI, true, contentObserver);
    }
}
