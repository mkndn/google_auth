package com.google.android.apps.authenticator.otp;

import android.net.Uri;
import com.google.android.apps.authenticator.migration.OfflineMigration;
import com.google.android.apps.authenticator.migration.OfflineMigrationEnums;
import com.google.android.apps.authenticator.otp.Account;
import com.google.android.apps.authenticator.otp.AccountDb;
import com.google.android.apps.authenticator.otp.InvalidAccountException;
import com.google.android.apps.authenticator.util.Base32String;
import com.google.common.base.Ascii;
import com.google.common.base.Strings;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccountProcessor {
    private static final String COUNTER_PARAM = "counter";
    private static final String HOTP = "hotp";
    private static final String ISSUER_PARAM = "issuer";
    private static final String OTP_SCHEME = "otpauth";
    private static final String SECRET_PARAM = "secret";
    private static final String TOTP = "totp";

    private AccountProcessor() {
    }

    public static Account parseOtpAuthUrl(Uri uri) {
        AccountDb.OtpType otpType;
        Integer valueOf;
        String scheme = uri.getScheme();
        String path = uri.getPath();
        String authority = uri.getAuthority();
        if (scheme != null && OTP_SCHEME.equals(Ascii.toLowerCase(scheme))) {
            if (TOTP.equals(authority)) {
                otpType = AccountDb.OtpType.TOTP;
                valueOf = AccountDb.DEFAULT_HOTP_COUNTER;
            } else if (HOTP.equals(authority)) {
                otpType = AccountDb.OtpType.HOTP;
                String queryParameter = uri.getQueryParameter(COUNTER_PARAM);
                if (queryParameter != null) {
                    try {
                        valueOf = Integer.valueOf(Integer.parseInt(queryParameter));
                    } catch (NumberFormatException e) {
                        throw new InvalidAccountException(InvalidAccountException.ErrorType.INVALID_OR_MISSING_COUNTER);
                    }
                } else {
                    valueOf = AccountDb.DEFAULT_HOTP_COUNTER;
                }
            } else {
                throw new InvalidAccountException(InvalidAccountException.ErrorType.INVALID_OR_MISSING_AUTHORITY);
            }
            String validateAndGetNameInPath = validateAndGetNameInPath(path);
            if (Strings.isNullOrEmpty(validateAndGetNameInPath)) {
                throw new InvalidAccountException(InvalidAccountException.ErrorType.MISSING_USER);
            }
            String queryParameter2 = uri.getQueryParameter("issuer");
            String queryParameter3 = uri.getQueryParameter(SECRET_PARAM);
            if (Strings.isNullOrEmpty(queryParameter3)) {
                throw new InvalidAccountException(InvalidAccountException.ErrorType.INVALID_OR_MISSING_SECRET);
            }
            if (AccountDb.getSigningOracle(queryParameter3) == null) {
                throw new InvalidAccountException(InvalidAccountException.ErrorType.INVALID_OR_MISSING_SECRET);
            }
            return Account.builder().setName(validateAndGetNameInPath).setIssuer(queryParameter2).setSecret(queryParameter3).setOtpType(otpType).setCounter(valueOf).build();
        }
        throw new InvalidAccountException(InvalidAccountException.ErrorType.INVALID_OR_MISSING_SCHEME);
    }

    public static Account processImportedAccount(OfflineMigration.OtpParameters otpParameters) {
        AccountDb.OtpType otpType;
        if (otpParameters.hasName() && !otpParameters.getName().isEmpty()) {
            if (otpParameters.hasSecret() && !otpParameters.getSecret().isEmpty()) {
                if (AccountDb.getSigningOracle(Base32String.encode(otpParameters.getSecret().toByteArray())) == null) {
                    throw new InvalidAccountException(InvalidAccountException.ErrorType.INVALID_OR_MISSING_SECRET);
                }
                if (otpParameters.getType() == OfflineMigrationEnums.OtpType.HOTP && !otpParameters.hasCounter()) {
                    throw new InvalidAccountException(InvalidAccountException.ErrorType.INVALID_OR_MISSING_COUNTER);
                }
                if (otpParameters.getDigits() != OfflineMigrationEnums.DigitCount.DIGIT_COUNT_UNSPECIFIED && otpParameters.getDigits() != OfflineMigrationEnums.DigitCount.SIX) {
                    throw new InvalidAccountException(InvalidAccountException.ErrorType.UNSUPPORTED_DIGIT_COUNT);
                }
                if (otpParameters.getAlgorithm() != OfflineMigrationEnums.Algorithm.ALGORITHM_UNSPECIFIED && otpParameters.getAlgorithm() != OfflineMigrationEnums.Algorithm.SHA1) {
                    throw new InvalidAccountException(InvalidAccountException.ErrorType.UNSUPPORTED_ALGORITHM);
                }
                Account.Builder secret = Account.builder().setName(otpParameters.getName()).setIssuer(otpParameters.getIssuer()).setSecret(Base32String.encode(otpParameters.getSecret().toByteArray()));
                if (otpParameters.getType() == OfflineMigrationEnums.OtpType.HOTP) {
                    otpType = AccountDb.OtpType.HOTP;
                } else {
                    otpType = AccountDb.OtpType.TOTP;
                }
                return secret.setOtpType(otpType).setCounter(Integer.valueOf((int) otpParameters.getCounter())).build();
            }
            throw new InvalidAccountException(InvalidAccountException.ErrorType.INVALID_OR_MISSING_SECRET);
        }
        throw new InvalidAccountException(InvalidAccountException.ErrorType.MISSING_USER);
    }

    private static String validateAndGetNameInPath(String str) {
        if (str != null && str.startsWith("/")) {
            return str.substring(1).trim();
        }
        return "";
    }
}
