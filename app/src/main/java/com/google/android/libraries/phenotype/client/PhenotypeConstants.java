package com.google.android.libraries.phenotype.client;

import android.content.Context;
import android.net.Uri;
import androidx.collection.ArrayMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeConstants {
    private static final ArrayMap uriByConfigPackageName = new ArrayMap();

    public static synchronized Uri getContentProviderUri(String str) {
        Uri uri;
        synchronized (PhenotypeConstants.class) {
            ArrayMap arrayMap = uriByConfigPackageName;
            uri = (Uri) arrayMap.get(str);
            if (uri == null) {
                uri = Uri.parse("content://com.google.android.gms.phenotype/" + Uri.encode(str));
                arrayMap.put(str, uri);
            }
        }
        return uri;
    }

    public static String getSubpackagedName(Context context, String str) {
        return getSubpackagedName(context, str, false);
    }

    public static String getSubpackagedName(Context context, String str, boolean z) {
        if (str.contains("#")) {
            throw new IllegalArgumentException("The passed in package cannot already have a subpackage: " + str);
        }
        return str + "#" + (z ? "@" : "") + context.getPackageName();
    }
}
