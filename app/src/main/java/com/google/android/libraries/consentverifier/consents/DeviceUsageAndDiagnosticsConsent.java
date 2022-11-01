package com.google.android.libraries.consentverifier.consents;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.HandlerThread;
import android.provider.Settings;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import com.google.android.libraries.consentverifier.CollectionBasisContext;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
class DeviceUsageAndDiagnosticsConsent implements Consent {
    private static volatile DeviceUsageAndDiagnosticsConsent cachedConsent;
    private static final Object cachedConsentLock = new Object();
    private final ConsentUtils consentUtils;
    private Optional consentValue = Optional.absent();
    private final Object consentValueLock = new Object();
    private final Context context;

    DeviceUsageAndDiagnosticsConsent(Context context, ConsentUtils consentUtils) {
        this.context = context.getApplicationContext();
        this.consentUtils = consentUtils;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DeviceUsageAndDiagnosticsConsent getConsentFrom(CollectionBasisContext collectionBasisContext, ConsentUtils consentUtils) {
        if (cachedConsent == null) {
            synchronized (cachedConsentLock) {
                if (cachedConsent == null) {
                    DeviceUsageAndDiagnosticsConsent deviceUsageAndDiagnosticsConsent = new DeviceUsageAndDiagnosticsConsent(collectionBasisContext.context().getApplicationContext(), consentUtils);
                    deviceUsageAndDiagnosticsConsent.registerForChangeNotification();
                    cachedConsent = deviceUsageAndDiagnosticsConsent;
                }
            }
        }
        return cachedConsent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateConsentValue() {
        boolean isGlobalSettingIntEnabled;
        synchronized (this.consentValueLock) {
            try {
                try {
                    isGlobalSettingIntEnabled = this.consentUtils.isGlobalSettingIntEnabled("multi_cb");
                    this.consentValue = Optional.of(Boolean.valueOf(isGlobalSettingIntEnabled));
                } catch (Settings.SettingNotFoundException e) {
                    return false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return isGlobalSettingIntEnabled;
    }

    void registerForChangeNotification() {
        if (Build.VERSION.SDK_INT >= 17) {
            HandlerThread handlerThread = new HandlerThread("CheckboxObserverThread");
            handlerThread.start();
            ContentObserver contentObserver = new ContentObserver(new TracingHandler(handlerThread.getLooper())) { // from class: com.google.android.libraries.consentverifier.consents.DeviceUsageAndDiagnosticsConsent.1
                @Override // android.database.ContentObserver
                public void onChange(boolean z, Uri uri) {
                    DeviceUsageAndDiagnosticsConsent.this.updateConsentValue();
                }
            };
            this.context.getContentResolver().registerContentObserver(Settings.Global.getUriFor("multi_cb"), false, contentObserver);
            return;
        }
        this.consentValue = Optional.of(true);
    }

    @Override // com.google.android.libraries.consentverifier.consents.Consent
    public boolean verify() {
        boolean booleanValue;
        synchronized (this.consentValueLock) {
            booleanValue = this.consentValue.isPresent() ? ((Boolean) this.consentValue.get()).booleanValue() : updateConsentValue();
        }
        return booleanValue;
    }
}
