package com.google.android.gms.identity.common.logging.util;

import android.util.Base64;
import java.util.Random;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogSessions {
    private static final Random RANDOM = new Random();

    public static String generateId() {
        byte[] bArr = new byte[16];
        RANDOM.nextBytes(bArr);
        return Base64.encodeToString(bArr, 11);
    }
}
