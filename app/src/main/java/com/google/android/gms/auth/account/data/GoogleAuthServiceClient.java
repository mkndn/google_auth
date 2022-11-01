package com.google.android.gms.auth.account.data;

import android.accounts.Account;
import android.os.Bundle;
import com.google.android.gms.tasks.Task;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface GoogleAuthServiceClient {
    Task getTokenWithDetails(Account account, String str, Bundle bundle);
}
