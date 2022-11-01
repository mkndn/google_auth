package com.google.android.apps.authenticator.otp;

import android.os.Handler;
import com.google.android.apps.authenticator.time.Clock;
import com.google.android.apps.authenticator.util.Utilities;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TotpCountdownTask implements Runnable {
    private final Clock mClock;
    private final TotpCounter mCounter;
    private final Handler mHandler = new Handler();
    private long mLastSeenCounterValue = Long.MIN_VALUE;
    private Listener mListener;
    private final long mRemainingTimeNotificationPeriod;
    private boolean mShouldStop;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Listener {
        void onTotpCountdown(long j);

        void onTotpCounterValueChanged();
    }

    public TotpCountdownTask(TotpCounter totpCounter, TotpClock totpClock, long j) {
        this.mCounter = totpCounter;
        this.mClock = totpClock;
        this.mRemainingTimeNotificationPeriod = j;
    }

    private void fireTotpCountdown(long j) {
        Listener listener = this.mListener;
        if (listener != null && !this.mShouldStop) {
            listener.onTotpCountdown(j);
        }
    }

    private void fireTotpCounterValueChanged() {
        Listener listener = this.mListener;
        if (listener != null && !this.mShouldStop) {
            listener.onTotpCounterValueChanged();
        }
    }

    private long getCounterValue(long j) {
        return this.mCounter.getValueAtTime(Utilities.millisToSeconds(j));
    }

    private long getCounterValueAge(long j) {
        return j - Utilities.secondsToMillis(this.mCounter.getValueStartTime(getCounterValue(j)));
    }

    private long getTimeTillNextCounterValue(long j) {
        return Utilities.secondsToMillis(this.mCounter.getValueStartTime(getCounterValue(j) + 1)) - j;
    }

    private void scheduleNextInvocation() {
        long counterValueAge = getCounterValueAge(this.mClock.nowMillis());
        long j = this.mRemainingTimeNotificationPeriod;
        this.mHandler.postDelayed(this, j - (counterValueAge % j));
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.mShouldStop) {
            return;
        }
        long nowMillis = this.mClock.nowMillis();
        long counterValue = getCounterValue(nowMillis);
        if (this.mLastSeenCounterValue != counterValue) {
            this.mLastSeenCounterValue = counterValue;
            fireTotpCounterValueChanged();
        }
        fireTotpCountdown(getTimeTillNextCounterValue(nowMillis));
        scheduleNextInvocation();
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void startAndNotifyListener() {
        if (this.mShouldStop) {
            throw new IllegalStateException("Task already stopped and cannot be restarted.");
        }
        run();
    }

    public void stop() {
        this.mShouldStop = true;
    }
}
