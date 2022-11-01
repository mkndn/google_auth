package com.google.android.gms.common.logging;

import android.util.Log;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Logger {
    private final GmsLogger mGmsLogger;
    private final int mLogLevel;
    private final String mPrefix;
    private final String mTag;

    private Logger(String str, String str2) {
        this.mPrefix = str2;
        this.mTag = str;
        this.mGmsLogger = new GmsLogger(str);
        int i = 2;
        while (i <= 7 && !Log.isLoggable(this.mTag, i)) {
            i++;
        }
        this.mLogLevel = i;
    }

    private static String compilePrefix(String... strArr) {
        if (strArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (String str : strArr) {
            if (sb.length() > 1) {
                sb.append(",");
            }
            sb.append(str);
        }
        sb.append(']').append(' ');
        return sb.toString();
    }

    public void d(String str, Object... objArr) {
        if (isLoggable(3)) {
            Log.d(this.mTag, format(str, objArr));
        }
    }

    public void e(String str, Throwable th, Object... objArr) {
        Log.e(this.mTag, format(str, objArr), th);
    }

    protected String format(String str, Object... objArr) {
        if (objArr != null && objArr.length > 0) {
            str = String.format(Locale.US, str, objArr);
        }
        return this.mPrefix.concat(str);
    }

    public boolean isLoggable(int i) {
        return this.mLogLevel <= i;
    }

    public void w(String str, Object... objArr) {
        Log.w(this.mTag, format(str, objArr));
    }

    public void e(String str, Object... objArr) {
        Log.e(this.mTag, format(str, objArr));
    }

    public Logger(String str, String... strArr) {
        this(str, compilePrefix(strArr));
    }
}
