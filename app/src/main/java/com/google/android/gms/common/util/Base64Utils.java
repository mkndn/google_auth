package com.google.android.gms.common.util;

import android.util.Base64;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Base64Utils {
    public static String encode(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return Base64.encodeToString(bArr, 0);
    }
}
