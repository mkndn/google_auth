package com.google.android.libraries.performance.primes.metrics.crash.applicationexit;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApplicationExitInfoCaptureImpl_Factory implements Factory {
    private final Provider applicationProvider;

    public ApplicationExitInfoCaptureImpl_Factory(Provider provider) {
        this.applicationProvider = provider;
    }

    public static ApplicationExitInfoCaptureImpl_Factory create(Provider provider) {
        return new ApplicationExitInfoCaptureImpl_Factory(provider);
    }

    public static ApplicationExitInfoCaptureImpl newInstance(Context context) {
        return new ApplicationExitInfoCaptureImpl(context);
    }

    @Override // javax.inject.Provider
    public ApplicationExitInfoCaptureImpl get() {
        return newInstance((Context) this.applicationProvider.get());
    }
}
