package com.google.android.apps.authenticator;

import android.net.http.HttpResponseCache;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import com.google.android.apps.authenticator.testability.DaggerInjector;
import com.google.android.apps.authenticator.testability.DependencyInjector;
import com.google.android.apps.authenticator.util.FileUtilities;
import com.google.android.libraries.performance.primes.Primes;
import com.google.android.libraries.performance.primes.PrimesApiProvider;
import com.google.android.libraries.performance.primes.PrimesConfigurations;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricTransmitter;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.security.prngfixes.PrngFixes;
import java.io.File;
import java.io.IOException;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AuthenticatorApplication extends MultiDexApplication {
    private static final long CACHE_SIZE = 1048576;
    private static final String PRIMES_LOG_SOURCE = "AUTHENTICATOR_ANDROID_PRIMES";
    private static final String TAG = "AuthenticatorApp";

    private void installCache() {
        if (HttpResponseCache.getInstalled() == null) {
            try {
                HttpResponseCache.install(new File(getApplicationContext().getCacheDir(), "http"), 1048576L);
            } catch (IOException e) {
                Log.w(TAG, "HTTP response cache installation failed:", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MetricTransmitter lambda$onCreate$0(ClearcutMetricTransmitter clearcutMetricTransmitter) {
        return clearcutMetricTransmitter;
    }

    protected void initDagger() {
        DaggerInjector.init(new AuthenticatorModule(this));
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        PhenotypeContext.setContext(this);
        PrngFixes.apply();
        try {
            FileUtilities.restrictAccessToOwnerOnly(getApplicationContext().getApplicationInfo().dataDir);
        } catch (Throwable th) {
        }
        installCache();
        DependencyInjector.configureForProductionIfNotConfigured(getApplicationContext());
        initDagger();
        final ClearcutMetricTransmitter build = ClearcutMetricTransmitter.newBuilder().setApplicationContext(this).setLogSource(PRIMES_LOG_SOURCE).build();
        Primes initialize = Primes.initialize(PrimesApiProvider.newInstance(this, new Provider() { // from class: com.google.android.apps.authenticator.AuthenticatorApplication$$ExternalSyntheticLambda0
            @Override // javax.inject.Provider
            public final Object get() {
                PrimesConfigurations build2;
                build2 = PrimesConfigurations.newBuilder().setMetricTransmitterProvider(new Provider() { // from class: com.google.android.apps.authenticator.AuthenticatorApplication$$ExternalSyntheticLambda1
                    @Override // javax.inject.Provider
                    public final Object get() {
                        return AuthenticatorApplication.lambda$onCreate$0(ClearcutMetricTransmitter.this);
                    }
                }).setMemoryConfigurationsProvider(AuthenticatorApplication$$ExternalSyntheticLambda2.INSTANCE).setTimerConfigurationsProvider(AuthenticatorApplication$$ExternalSyntheticLambda3.INSTANCE).setCrashConfigurationsProvider(AuthenticatorApplication$$ExternalSyntheticLambda4.INSTANCE).setNetworkConfigurationsProvider(AuthenticatorApplication$$ExternalSyntheticLambda5.INSTANCE).setStorageConfigurationsProvider(AuthenticatorApplication$$ExternalSyntheticLambda6.INSTANCE).setBatteryConfigurationsProvider(AuthenticatorApplication$$ExternalSyntheticLambda7.INSTANCE).build();
                return build2;
            }
        }));
        initialize.startMemoryMonitor();
        initialize.startCrashMonitor();
    }

    @Override // android.app.Application
    public void onTerminate() {
        DependencyInjector.close();
        super.onTerminate();
    }
}
