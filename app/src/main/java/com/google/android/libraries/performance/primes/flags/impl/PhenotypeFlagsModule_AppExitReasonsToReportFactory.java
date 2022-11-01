package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.ApplicationExitReasons;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_AppExitReasonsToReportFactory implements Factory {
    private final Provider contextProvider;

    public PhenotypeFlagsModule_AppExitReasonsToReportFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public static ApplicationExitReasons appExitReasonsToReport(Context context) {
        return (ApplicationExitReasons) Preconditions.checkNotNullFromProvides(PhenotypeFlagsModule.appExitReasonsToReport(context));
    }

    public static PhenotypeFlagsModule_AppExitReasonsToReportFactory create(Provider provider) {
        return new PhenotypeFlagsModule_AppExitReasonsToReportFactory(provider);
    }

    @Override // javax.inject.Provider
    public ApplicationExitReasons get() {
        return appExitReasonsToReport((Context) this.contextProvider.get());
    }
}
