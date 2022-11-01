package com.google.android.libraries.phenotype.client.stable;

import android.util.Log;
import com.google.common.base.Function;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AccountRemovedBroadcastReceiver$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ AccountRemovedBroadcastReceiver$$ExternalSyntheticLambda0 INSTANCE = new AccountRemovedBroadcastReceiver$$ExternalSyntheticLambda0();

    private /* synthetic */ AccountRemovedBroadcastReceiver$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        Object valueOf;
        valueOf = Integer.valueOf(Log.w("AccountRemovedRecv", "Failed to remove account snapshot: ", (IOException) obj));
        return valueOf;
    }
}
