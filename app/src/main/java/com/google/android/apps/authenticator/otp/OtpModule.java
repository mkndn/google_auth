package com.google.android.apps.authenticator.otp;

import android.content.Context;
import com.google.android.apps.authenticator.common.ApplicationContext;
import com.google.android.apps.authenticator.time.Clock;
import javax.inject.Singleton;

/* compiled from: PG */
/* loaded from: classes.dex */
public class OtpModule {
    @Singleton
    public AccountDb providesAccountDb(@ApplicationContext Context context) {
        return new AccountDb(context);
    }

    @Singleton
    public OtpSource providesOtpSource(AccountDb accountDb, TotpClock totpClock) {
        return new OtpProvider(accountDb, totpClock);
    }

    @Singleton
    public TotpClock providesTotpClock(@ApplicationContext Context context, Clock clock) {
        return new TotpClock(context, clock);
    }

    public TotpCountdownTask providesTotpCountdownTask(TotpCounter totpCounter, TotpClock totpClock) {
        return new TotpCountdownTask(totpCounter, totpClock, 100L);
    }

    public TotpCounter providesTotpCounter(OtpSource otpSource) {
        return otpSource.getTotpCounter();
    }
}
