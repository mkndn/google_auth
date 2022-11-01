package com.google.android.libraries.storage.file.backends;

import android.accounts.Account;
import com.google.android.libraries.storage.file.common.internal.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccountSerialization {
    public static final Account SHARED_ACCOUNT = new Account("shared", "mobstore");

    public static Account deserialize(String str) {
        if (isSharedAccount(str)) {
            return SHARED_ACCOUNT;
        }
        int indexOf = str.indexOf(58);
        Preconditions.checkArgument(indexOf >= 0, "Malformed account", new Object[0]);
        return new Account(str.substring(indexOf + 1), str.substring(0, indexOf));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSharedAccount(Account account) {
        return SHARED_ACCOUNT.equals(account);
    }

    public static String serialize(Account account) {
        validate(account);
        if (isSharedAccount(account)) {
            return "shared";
        }
        String str = account.type;
        return str + ":" + account.name;
    }

    private static void validate(Account account) {
        Preconditions.checkArgument(account.type.indexOf(58) == -1, "Account type contains ':'.", new Object[0]);
        Preconditions.checkArgument(account.type.indexOf(47) == -1, "Account type contains '/'.", new Object[0]);
        Preconditions.checkArgument(account.name.indexOf(47) == -1, "Account name contains '/'.", new Object[0]);
    }

    static boolean isSharedAccount(String str) {
        return "shared".equals(str);
    }
}
