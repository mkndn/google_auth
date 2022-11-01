package com.google.android.apps.authenticator.otp;

import com.google.android.apps.authenticator.otp.AccountDb;
import com.google.android.apps.authenticator.util.Utilities;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class OtpProvider implements OtpSource {
    public static final int DEFAULT_INTERVAL = 30;
    private static final int PIN_LENGTH = 6;
    private static final int REFLECTIVE_PIN_LENGTH = 9;
    private final AccountDb mAccountDb;
    private final TotpClock mTotpClock;
    private final TotpCounter mTotpCounter;

    public OtpProvider(int i, AccountDb accountDb, TotpClock totpClock) {
        this.mAccountDb = accountDb;
        this.mTotpCounter = new TotpCounter(i);
        this.mTotpClock = totpClock;
    }

    private String computePin(String str, long j, byte[] bArr) {
        if (str != null && str.length() != 0) {
            try {
                PasscodeGenerator passcodeGenerator = new PasscodeGenerator(AccountDb.getSigningOracle(str), bArr == null ? 6 : 9);
                if (bArr == null) {
                    return passcodeGenerator.generateResponseCode(j);
                }
                return passcodeGenerator.generateResponseCode(j, bArr);
            } catch (GeneralSecurityException e) {
                throw new OtpSourceException("Crypto failure", e);
            }
        }
        throw new OtpSourceException("Null or empty secret");
    }

    private String getCurrentCode(AccountDb.AccountIndex accountIndex, byte[] bArr) {
        long j;
        if (accountIndex == null) {
            throw new OtpSourceException("No account");
        }
        AccountDb.OtpType type = this.mAccountDb.getType(accountIndex);
        String secret = getSecret(accountIndex);
        if (type == AccountDb.OtpType.TOTP) {
            j = this.mTotpCounter.getValueAtTime(Utilities.millisToSeconds(this.mTotpClock.nowMillis()));
        } else if (type == AccountDb.OtpType.HOTP) {
            this.mAccountDb.incrementCounter(accountIndex);
            j = this.mAccountDb.getCounter(accountIndex).longValue();
        } else {
            j = 0;
        }
        return computePin(secret, j, bArr);
    }

    @Override // com.google.android.apps.authenticator.otp.OtpSource
    public List enumerateAccounts() {
        return this.mAccountDb.getAccounts();
    }

    @Override // com.google.android.apps.authenticator.otp.OtpSource
    public String getNextCode(AccountDb.AccountIndex accountIndex) {
        return getCurrentCode(accountIndex, null);
    }

    String getSecret(AccountDb.AccountIndex accountIndex) {
        return this.mAccountDb.getSecret(accountIndex);
    }

    @Override // com.google.android.apps.authenticator.otp.OtpSource
    public TotpClock getTotpClock() {
        return this.mTotpClock;
    }

    @Override // com.google.android.apps.authenticator.otp.OtpSource
    public TotpCounter getTotpCounter() {
        return this.mTotpCounter;
    }

    @Override // com.google.android.apps.authenticator.otp.OtpSource
    public String respondToChallenge(AccountDb.AccountIndex accountIndex, String str) {
        if (str == null) {
            return getCurrentCode(accountIndex, null);
        }
        try {
            return getCurrentCode(accountIndex, str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public OtpProvider(AccountDb accountDb, TotpClock totpClock) {
        this(30, accountDb, totpClock);
    }
}
