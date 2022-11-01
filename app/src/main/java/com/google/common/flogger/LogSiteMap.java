package com.google.common.flogger;

import com.google.common.flogger.LogContext;
import com.google.common.flogger.backend.Metadata;
import com.google.common.flogger.util.Checks;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class LogSiteMap {
    private final ConcurrentHashMap concurrentMap = new ConcurrentHashMap();

    public final Object get(LogSiteKey logSiteKey, Metadata metadata) {
        Object obj = this.concurrentMap.get(logSiteKey);
        if (obj != null) {
            return obj;
        }
        Object checkNotNull = Checks.checkNotNull(initialValue(), "initial map value");
        Object putIfAbsent = this.concurrentMap.putIfAbsent(logSiteKey, checkNotNull);
        if (putIfAbsent != null) {
            return putIfAbsent;
        }
        addRemovalHook(logSiteKey, metadata);
        return checkNotNull;
    }

    protected abstract Object initialValue();

    private void addRemovalHook(final LogSiteKey logSiteKey, Metadata metadata) {
        int size = metadata.size();
        Runnable runnable = null;
        for (int i = 0; i < size; i++) {
            if (LogContext.Key.LOG_SITE_GROUPING_KEY.equals(metadata.getKey(i))) {
                Object value = metadata.getValue(i);
                if (value instanceof LoggingScope) {
                    if (runnable == null) {
                        runnable = new Runnable() { // from class: com.google.common.flogger.LogSiteMap.1
                            @Override // java.lang.Runnable
                            public void run() {
                                LogSiteMap.this.concurrentMap.remove(logSiteKey);
                            }
                        };
                    }
                    ((LoggingScope) value).onClose(runnable);
                }
            }
        }
    }
}
