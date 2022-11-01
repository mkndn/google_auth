package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_EnableAlwaysOnJankRecordingFactory implements Factory {
    private final Provider contextProvider;

    public PhenotypeFlagsModule_EnableAlwaysOnJankRecordingFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public static PhenotypeFlagsModule_EnableAlwaysOnJankRecordingFactory create(Provider provider) {
        return new PhenotypeFlagsModule_EnableAlwaysOnJankRecordingFactory(provider);
    }

    public static boolean enableAlwaysOnJankRecording(Context context) {
        return PhenotypeFlagsModule.enableAlwaysOnJankRecording(context);
    }

    @Override // javax.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(enableAlwaysOnJankRecording((Context) this.contextProvider.get()));
    }
}
