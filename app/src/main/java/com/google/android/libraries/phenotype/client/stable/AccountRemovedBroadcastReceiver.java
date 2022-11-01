package com.google.android.libraries.phenotype.client.stable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccountRemovedBroadcastReceiver extends BroadcastReceiver {
    public static /* synthetic */ void lambda$onReceive$1(Context context, String str) {
        removeAccount(context, str);
    }

    public static /* synthetic */ Object lambda$onReceive$2(BroadcastReceiver.PendingResult pendingResult) {
        pendingResult.finish();
        return null;
    }

    public static void removeAccount(Context context, String str) {
        SharedPreferences preferences = PhenotypeStickyAccount.getPreferences(context);
        SharedPreferences.Editor editor = null;
        for (Map.Entry<String, ?> entry : preferences.getAll().entrySet()) {
            if ((entry.getValue() instanceof String) && entry.getValue().equals(str)) {
                if (editor == null) {
                    editor = preferences.edit();
                }
                editor.remove(entry.getKey());
            }
        }
        if (editor != null) {
            editor.commit();
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        if (!"android.accounts.action.ACCOUNT_REMOVED".equals(intent.getAction()) || !PhenotypeAccount.isSupportedAccountType(intent.getStringExtra("accountType"))) {
            return;
        }
        final String string = intent.getExtras().getString("authAccount");
        PhenotypeContext phenotypeContextFrom = PhenotypeContext.getPhenotypeContextFrom(context);
        if (phenotypeContextFrom == null) {
            return;
        }
        final BroadcastReceiver.PendingResult goAsync = goAsync();
        Futures.whenAllComplete(Futures.catching(PhenotypeAccountStore.removeAccountGlobally(phenotypeContextFrom, string), IOException.class, AccountRemovedBroadcastReceiver$$ExternalSyntheticLambda0.INSTANCE, MoreExecutors.directExecutor()), phenotypeContextFrom.getExecutor().submit(new Runnable() { // from class: com.google.android.libraries.phenotype.client.stable.AccountRemovedBroadcastReceiver$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AccountRemovedBroadcastReceiver.lambda$onReceive$1(context, string);
            }
        })).call(new Callable() { // from class: com.google.android.libraries.phenotype.client.stable.AccountRemovedBroadcastReceiver$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return AccountRemovedBroadcastReceiver.lambda$onReceive$2(goAsync);
            }
        }, MoreExecutors.directExecutor());
    }
}
