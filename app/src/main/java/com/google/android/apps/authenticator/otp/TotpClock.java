package com.google.android.apps.authenticator.otp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.android.apps.authenticator.time.Clock;
import com.google.android.apps.authenticator.util.Utilities;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TotpClock implements Clock {
    static final String PREFERENCE_KEY_OFFSET_MINUTES = "timeCorrectionMinutes";
    private Integer mCachedCorrectionMinutes;
    private final Object mLock = new Object();
    private final SharedPreferences mPreferences;
    private final Clock mSystemWallClock;

    public TotpClock(Context context, Clock clock) {
        this.mSystemWallClock = clock;
        this.mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public Clock getSystemWallClock() {
        return this.mSystemWallClock;
    }

    public int getTimeCorrectionMinutes() {
        int intValue;
        synchronized (this.mLock) {
            if (this.mCachedCorrectionMinutes == null) {
                this.mCachedCorrectionMinutes = Integer.valueOf(this.mPreferences.getInt(PREFERENCE_KEY_OFFSET_MINUTES, 0));
            }
            intValue = this.mCachedCorrectionMinutes.intValue();
        }
        return intValue;
    }

    @Override // com.google.android.apps.authenticator.time.Clock
    public long nowMillis() {
        return this.mSystemWallClock.nowMillis() + (getTimeCorrectionMinutes() * Utilities.MINUTE_IN_MILLIS);
    }

    public void setTimeCorrectionMinutes(int i) {
        synchronized (this.mLock) {
            this.mPreferences.edit().putInt(PREFERENCE_KEY_OFFSET_MINUTES, i).commit();
            this.mCachedCorrectionMinutes = null;
        }
    }
}
