package com.google.android.apps.authenticator.testability.accounts;

import android.accounts.Account;
import android.content.Context;
import com.google.android.apps.authenticator.common.ApplicationContext;
import com.google.android.apps.authenticator.testability.android.gms.GoogleAuthWrapper;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import javax.inject.Inject;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleAccountManagerImpl implements GoogleAccountManager {
    private final Context context;
    private final GoogleAuthWrapper googleAuthWrapper;

    @Inject
    public GoogleAccountManagerImpl(@ApplicationContext Context context, GoogleAuthWrapper googleAuthWrapper) {
        this.context = context;
        this.googleAuthWrapper = googleAuthWrapper;
    }

    @Override // com.google.android.apps.authenticator.testability.accounts.GoogleAccountManager
    public Collection listAllAccountNames() {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Account account : this.googleAuthWrapper.listGoogleAccounts(this.context)) {
            builder.add((Object) account.name);
        }
        return builder.build();
    }
}
