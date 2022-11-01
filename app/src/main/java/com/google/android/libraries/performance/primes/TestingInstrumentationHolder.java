package com.google.android.libraries.performance.primes;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TestingInstrumentationHolder {
    public static TestingInstrumentation instance = null;

    public static boolean isAppRunningUnderPrimesEndtoendTest() {
        return instance != null;
    }
}
