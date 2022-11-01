package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import com.google.android.libraries.performance.primes.metrics.crash.CrashRecordingTimeouts;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_RecordingTimeoutsFactory implements Factory {
    private final Provider contextProvider;

    public PhenotypeFlagsModule_RecordingTimeoutsFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public static PhenotypeFlagsModule_RecordingTimeoutsFactory create(Provider provider) {
        return new PhenotypeFlagsModule_RecordingTimeoutsFactory(provider);
    }

    public static CrashRecordingTimeouts recordingTimeouts(Context context) {
        return (CrashRecordingTimeouts) Preconditions.checkNotNullFromProvides(PhenotypeFlagsModule.recordingTimeouts(context));
    }

    @Override // javax.inject.Provider
    public CrashRecordingTimeouts get() {
        return recordingTimeouts((Context) this.contextProvider.get());
    }
}
