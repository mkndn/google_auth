package com.google.android.apps.authenticator.otp;

import com.google.android.apps.authenticator.otp.AccountDb;
import com.google.android.apps.authenticator.otp.AutoValue_Account;
import com.google.android.apps.authenticator.util.Base32String;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Account {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public Account build() {
            if (issuer() != null && issuer().isEmpty()) {
                setIssuer(null);
            }
            if (isEncrypted() == null) {
                setIsEncrypted(0);
            }
            if (isEncrypted().intValue() == 0) {
                setSecret(Base32String.tryNormalizeEncoded(secret()));
            }
            return buildWithoutValidation();
        }

        abstract Account buildWithoutValidation();

        abstract Integer isEncrypted();

        abstract String issuer();

        abstract String secret();

        public abstract Builder setCounter(Integer num);

        public abstract Builder setIsEncrypted(Integer num);

        public abstract Builder setIssuer(String str);

        public abstract Builder setName(String str);

        public abstract Builder setOtpType(AccountDb.OtpType otpType);

        public abstract Builder setSecret(String str);
    }

    public static Builder builder() {
        return new AutoValue_Account.Builder();
    }

    public abstract Integer counter();

    public abstract Integer isEncrypted();

    public abstract String issuer();

    public abstract String name();

    public abstract AccountDb.OtpType otpType();

    public abstract String secret();
}
