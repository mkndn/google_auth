package com.google.android.libraries.performance.primes.sampling;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.libraries.clock.Clock;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.common.flogger.GoogleLogger;
import javax.inject.Inject;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PersistentRateLimiting {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/sampling/PersistentRateLimiting");
    private final Context application;
    private final Clock clock;
    private final Provider sharedPrefs;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public PersistentRateLimiting(Context context, Clock clock, Provider provider) {
        this.application = context;
        this.clock = clock;
        this.sharedPrefs = provider;
    }

    public boolean hasRecentTimeStamp(String str, long j) {
        ThreadUtil.ensureBackgroundThread();
        if (DirectBootUtils.isUserUnlocked(this.application)) {
            long readTimeStamp = readTimeStamp(str);
            long elapsedRealtime = this.clock.elapsedRealtime();
            if (elapsedRealtime < readTimeStamp) {
                if (!((SharedPreferences) this.sharedPrefs.get()).edit().remove(str).commit()) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/sampling/PersistentRateLimiting", "hasRecentTimeStamp", 50, "PersistentRateLimiting.java")).log("Failure storing timestamp to SharedPreferences");
                }
                readTimeStamp = -1;
            }
            return readTimeStamp != -1 && elapsedRealtime <= readTimeStamp + j;
        }
        return false;
    }

    public long readTimeStamp(String str) {
        if (DirectBootUtils.isUserUnlocked(this.application)) {
            return ((SharedPreferences) this.sharedPrefs.get()).getLong(str, -1L);
        }
        return -1L;
    }

    public boolean writeTimeStamp(String str) {
        return DirectBootUtils.isUserUnlocked(this.application) && ((SharedPreferences) this.sharedPrefs.get()).edit().putLong(str, this.clock.elapsedRealtime()).commit();
    }
}
