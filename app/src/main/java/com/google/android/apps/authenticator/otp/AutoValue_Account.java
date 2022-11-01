package com.google.android.apps.authenticator.otp;

import com.google.android.apps.authenticator.otp.Account;
import com.google.android.apps.authenticator.otp.AccountDb;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_Account extends Account {
    private final Integer counter;
    private final Integer isEncrypted;
    private final String issuer;
    private final String name;
    private final AccountDb.OtpType otpType;
    private final String secret;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends Account.Builder {
        private Integer counter;
        private Integer isEncrypted;
        private String issuer;
        private String name;
        private AccountDb.OtpType otpType;
        private String secret;

        @Override // com.google.android.apps.authenticator.otp.Account.Builder
        Account buildWithoutValidation() {
            if (this.name != null && this.secret != null && this.otpType != null) {
                return new AutoValue_Account(this.name, this.issuer, this.secret, this.isEncrypted, this.otpType, this.counter);
            }
            StringBuilder sb = new StringBuilder();
            if (this.name == null) {
                sb.append(" name");
            }
            if (this.secret == null) {
                sb.append(" secret");
            }
            if (this.otpType == null) {
                sb.append(" otpType");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.apps.authenticator.otp.Account.Builder
        Integer isEncrypted() {
            return this.isEncrypted;
        }

        @Override // com.google.android.apps.authenticator.otp.Account.Builder
        String issuer() {
            return this.issuer;
        }

        @Override // com.google.android.apps.authenticator.otp.Account.Builder
        String secret() {
            String str = this.secret;
            if (str == null) {
                throw new IllegalStateException("Property \"secret\" has not been set");
            }
            return str;
        }

        @Override // com.google.android.apps.authenticator.otp.Account.Builder
        public Account.Builder setCounter(Integer num) {
            this.counter = num;
            return this;
        }

        @Override // com.google.android.apps.authenticator.otp.Account.Builder
        public Account.Builder setIsEncrypted(Integer num) {
            this.isEncrypted = num;
            return this;
        }

        @Override // com.google.android.apps.authenticator.otp.Account.Builder
        public Account.Builder setIssuer(String str) {
            this.issuer = str;
            return this;
        }

        @Override // com.google.android.apps.authenticator.otp.Account.Builder
        public Account.Builder setName(String str) {
            if (str == null) {
                throw new NullPointerException("Null name");
            }
            this.name = str;
            return this;
        }

        @Override // com.google.android.apps.authenticator.otp.Account.Builder
        public Account.Builder setOtpType(AccountDb.OtpType otpType) {
            if (otpType == null) {
                throw new NullPointerException("Null otpType");
            }
            this.otpType = otpType;
            return this;
        }

        @Override // com.google.android.apps.authenticator.otp.Account.Builder
        public Account.Builder setSecret(String str) {
            if (str == null) {
                throw new NullPointerException("Null secret");
            }
            this.secret = str;
            return this;
        }
    }

    private AutoValue_Account(String str, String str2, String str3, Integer num, AccountDb.OtpType otpType, Integer num2) {
        this.name = str;
        this.issuer = str2;
        this.secret = str3;
        this.isEncrypted = num;
        this.otpType = otpType;
        this.counter = num2;
    }

    @Override // com.google.android.apps.authenticator.otp.Account
    public Integer counter() {
        return this.counter;
    }

    public boolean equals(Object obj) {
        String str;
        Integer num;
        if (obj == this) {
            return true;
        }
        if (obj instanceof Account) {
            Account account = (Account) obj;
            if (this.name.equals(account.name()) && ((str = this.issuer) != null ? str.equals(account.issuer()) : account.issuer() == null) && this.secret.equals(account.secret()) && ((num = this.isEncrypted) != null ? num.equals(account.isEncrypted()) : account.isEncrypted() == null) && this.otpType.equals(account.otpType())) {
                Integer num2 = this.counter;
                if (num2 == null) {
                    if (account.counter() == null) {
                        return true;
                    }
                } else if (num2.equals(account.counter())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.google.android.apps.authenticator.otp.Account
    public Integer isEncrypted() {
        return this.isEncrypted;
    }

    @Override // com.google.android.apps.authenticator.otp.Account
    public String issuer() {
        return this.issuer;
    }

    @Override // com.google.android.apps.authenticator.otp.Account
    public String name() {
        return this.name;
    }

    @Override // com.google.android.apps.authenticator.otp.Account
    public AccountDb.OtpType otpType() {
        return this.otpType;
    }

    @Override // com.google.android.apps.authenticator.otp.Account
    public String secret() {
        return this.secret;
    }

    public String toString() {
        String str = this.name;
        String str2 = this.issuer;
        String str3 = this.secret;
        Integer num = this.isEncrypted;
        String valueOf = String.valueOf(this.otpType);
        return "Account{name=" + str + ", issuer=" + str2 + ", secret=" + str3 + ", isEncrypted=" + num + ", otpType=" + valueOf + ", counter=" + this.counter + "}";
    }

    public int hashCode() {
        int hashCode = (this.name.hashCode() ^ 1000003) * 1000003;
        String str = this.issuer;
        int hashCode2 = (((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.secret.hashCode()) * 1000003;
        Integer num = this.isEncrypted;
        int hashCode3 = (((hashCode2 ^ (num == null ? 0 : num.hashCode())) * 1000003) ^ this.otpType.hashCode()) * 1000003;
        Integer num2 = this.counter;
        return hashCode3 ^ (num2 != null ? num2.hashCode() : 0);
    }
}
