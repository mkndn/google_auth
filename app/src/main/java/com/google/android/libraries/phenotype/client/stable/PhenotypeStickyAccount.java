package com.google.android.libraries.phenotype.client.stable;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.libraries.phenotype.client.SafeHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeStickyAccount {
    private static final SafeHashMap listenersByPackage = SafeHashMap.newSafeHashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Listener {
    }

    public static String getAccount(Context context, String str) {
        return getPreferences(context).getString(str, "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences("PhenotypeStickyAccount", 0);
    }

    public static void registerListener(String str, Listener listener) {
        listenersByPackage.putIfAbsent(str, listener);
    }
}
