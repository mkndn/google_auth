package com.google.android.libraries.phenotype.client;

import android.net.Uri;
import androidx.collection.SimpleArrayMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DefaultHermeticFileOverrides implements HermeticFileOverrides {
    private final SimpleArrayMap map;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultHermeticFileOverrides(SimpleArrayMap simpleArrayMap) {
        this.map = simpleArrayMap;
    }

    @Override // com.google.android.libraries.phenotype.client.HermeticFileOverrides
    public String get(Uri uri, String str, String str2, String str3) {
        if (uri != null) {
            str = uri.toString();
        } else if (str == null) {
            return null;
        }
        SimpleArrayMap simpleArrayMap = (SimpleArrayMap) this.map.get(str);
        if (simpleArrayMap == null) {
            return null;
        }
        if (str2 != null) {
            str3 = str2 + str3;
        }
        return (String) simpleArrayMap.get(str3);
    }
}
