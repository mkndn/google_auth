package com.google.common.flogger.backend.android;

import android.os.Build;
import android.util.Log;
import com.google.common.flogger.backend.LogData;
import com.google.common.flogger.backend.LoggerBackend;
import com.google.common.flogger.backend.android.SimpleAndroidLoggerBackend;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ProxyAndroidLoggerBackend extends AbstractAndroidBackend {
    private static final AtomicReference backendFactory = new AtomicReference();
    private static final AtomicLong bufferSize = new AtomicLong();
    private static final ConcurrentLinkedQueue logBuffer = new ConcurrentLinkedQueue();
    private volatile LoggerBackend backend;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LazyProxyQueueHolder {
        static final ConcurrentLinkedQueue backendlessProxyQueue = new ConcurrentLinkedQueue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LogMapping {
        private final LoggerBackend backend;
        private final LogData data;

        LogMapping(LoggerBackend loggerBackend, LogData logData) {
            this.backend = loggerBackend;
            this.data = logData;
        }

        LoggerBackend getBackend() {
            return this.backend;
        }

        LogData getData() {
            return this.data;
        }
    }

    private ProxyAndroidLoggerBackend(String str) {
        super(str);
        boolean z = true;
        boolean z2 = Build.FINGERPRINT == null || "robolectric".equals(Build.FINGERPRINT);
        boolean z3 = "goldfish".equals(Build.HARDWARE) || "ranchu".equals(Build.HARDWARE);
        if (!"eng".equals(Build.TYPE) && !"userdebug".equals(Build.TYPE)) {
            z = false;
        }
        if (!z2 && !z3) {
            if (z) {
                this.backend = new SimpleAndroidLoggerBackend.Factory().withIncludeFormatArguments(false).create(getLoggerName());
                return;
            } else {
                this.backend = null;
                return;
            }
        }
        this.backend = new AlwaysLogBackend$Factory().create(getLoggerName());
    }

    private void attachBackend() {
        this.backend = ((AndroidBackendFactory) backendFactory.get()).create(getLoggerName());
    }

    private static void attachBackends() {
        while (true) {
            ProxyAndroidLoggerBackend proxyAndroidLoggerBackend = (ProxyAndroidLoggerBackend) LazyProxyQueueHolder.backendlessProxyQueue.poll();
            if (proxyAndroidLoggerBackend != null) {
                proxyAndroidLoggerBackend.attachBackend();
            } else {
                flushBuffer();
                return;
            }
        }
    }

    public static LoggerBackend create(String str) {
        AtomicReference atomicReference = backendFactory;
        if (atomicReference.get() != null) {
            return ((AndroidBackendFactory) atomicReference.get()).create(str);
        }
        ProxyAndroidLoggerBackend proxyAndroidLoggerBackend = new ProxyAndroidLoggerBackend(str.replace('$', '.'));
        LazyProxyQueueHolder.backendlessProxyQueue.offer(proxyAndroidLoggerBackend);
        if (atomicReference.get() != null) {
            attachBackends();
        }
        return proxyAndroidLoggerBackend;
    }

    private static void flushBuffer() {
        while (true) {
            LogMapping logMapping = (LogMapping) logBuffer.poll();
            if (logMapping != null) {
                bufferSize.getAndDecrement();
                LoggerBackend backend = logMapping.getBackend();
                LogData data = logMapping.getData();
                if (data.wasForced() || backend.isLoggable(data.getLevel())) {
                    backend.log(data);
                }
            } else {
                return;
            }
        }
    }

    @Override // com.google.common.flogger.backend.android.AbstractAndroidBackend, com.google.common.flogger.backend.LoggerBackend
    public void handleError(RuntimeException runtimeException, LogData logData) {
        if (this.backend != null) {
            this.backend.handleError(runtimeException, logData);
        } else {
            Log.e("ProxyAndroidLoggerBackend", "Internal logging error before configuration", runtimeException);
        }
    }

    @Override // com.google.common.flogger.backend.LoggerBackend
    public boolean isLoggable(Level level) {
        if (this.backend != null) {
            return this.backend.isLoggable(level);
        }
        return true;
    }

    @Override // com.google.common.flogger.backend.LoggerBackend
    public void log(LogData logData) {
        if (this.backend != null) {
            this.backend.log(logData);
            return;
        }
        if (bufferSize.incrementAndGet() > 20) {
            logBuffer.poll();
            Log.w("ProxyAndroidLoggerBackend", "Too many Flogger logs received before configuration. Dropping old logs.");
        }
        logBuffer.offer(new LogMapping(this, logData));
        if (this.backend != null) {
            flushBuffer();
        }
    }
}
