package com.google.android.libraries.storage.protostore.common;

import android.net.Uri;
import com.google.common.base.Strings;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Uris {
    public static Uri addSuffix(Uri uri, String str) {
        return uri.buildUpon().path(uri.getPath() + str).build();
    }

    public static String getExtension(Uri uri) {
        String nullToEmpty = Strings.nullToEmpty(uri.getLastPathSegment());
        int lastIndexOf = nullToEmpty.lastIndexOf(46);
        return lastIndexOf == -1 ? "" : nullToEmpty.substring(lastIndexOf + 1);
    }

    public static String getNameWithoutExtension(Uri uri) {
        String nullToEmpty = Strings.nullToEmpty(uri.getLastPathSegment());
        int lastIndexOf = nullToEmpty.lastIndexOf(46);
        return lastIndexOf == -1 ? nullToEmpty : nullToEmpty.substring(0, lastIndexOf);
    }
}
