package com.google.android.libraries.storage.file.backends;

import android.accounts.Account;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface AccountManager {
    ListenableFuture getAccountId(Account account);
}
