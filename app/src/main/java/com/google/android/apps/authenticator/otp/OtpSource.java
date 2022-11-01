package com.google.android.apps.authenticator.otp;

import com.google.android.apps.authenticator.otp.AccountDb;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface OtpSource {
    List enumerateAccounts();

    String getNextCode(AccountDb.AccountIndex accountIndex);

    TotpClock getTotpClock();

    TotpCounter getTotpCounter();

    String respondToChallenge(AccountDb.AccountIndex accountIndex, String str);
}
