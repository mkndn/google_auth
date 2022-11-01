package com.google.android.gms.auth;

import com.google.android.gms.common.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AuthLoggers {
    public static Logger newLogger(String... strArr) {
        return new Logger("Auth", strArr);
    }
}
