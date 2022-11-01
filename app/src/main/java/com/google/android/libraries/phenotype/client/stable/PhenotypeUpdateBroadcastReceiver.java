package com.google.android.libraries.phenotype.client.stable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.libraries.phenotype.client.SafeHashMap;
import com.google.common.base.Pair;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeUpdateBroadcastReceiver extends BroadcastReceiver {
    private static final Object LOCK = new Object();
    static final SafeHashMap packageAndAccountCallbacks = SafeHashMap.newSafeHashMap();
    private static volatile boolean registered;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Callback {
        void onUpdateReceived(String str);
    }

    public static void registerCallback(Context context, Pair pair, Callback callback) {
        packageAndAccountCallbacks.putIfAbsent(pair, callback);
        if (!registered) {
            synchronized (LOCK) {
                if (!registered) {
                    if (PlatformVersion.isAtLeastT()) {
                        context.registerReceiver(new PhenotypeUpdateBroadcastReceiver(), new IntentFilter("com.google.android.gms.phenotype.UPDATE"), 2);
                    } else {
                        context.registerReceiver(new PhenotypeUpdateBroadcastReceiver(), new IntentFilter("com.google.android.gms.phenotype.UPDATE"));
                    }
                    registered = true;
                }
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("com.google.android.gms.phenotype.PACKAGE_NAME");
        if (stringExtra == null) {
            return;
        }
        for (Map.Entry entry : packageAndAccountCallbacks.entrySet()) {
            if (((String) ((Pair) entry.getKey()).getFirst()).equals(stringExtra)) {
                ((Callback) entry.getValue()).onUpdateReceived(stringExtra);
            }
        }
    }
}
