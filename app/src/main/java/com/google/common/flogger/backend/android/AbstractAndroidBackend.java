package com.google.common.flogger.backend.android;

import android.util.Log;
import com.google.common.flogger.backend.LogData;
import com.google.common.flogger.backend.LoggerBackend;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractAndroidBackend extends LoggerBackend {
    private final String name;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractAndroidBackend(String str) {
        this.name = str;
    }

    @Override // com.google.common.flogger.backend.LoggerBackend
    public String getLoggerName() {
        return this.name;
    }

    @Override // com.google.common.flogger.backend.LoggerBackend
    public void handleError(RuntimeException runtimeException, LogData logData) {
        Log.e("AbstractAndroidBackend", "Internal logging error", runtimeException);
    }
}
