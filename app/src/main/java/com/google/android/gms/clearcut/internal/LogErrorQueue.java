package com.google.android.gms.clearcut.internal;

import androidx.collection.ArrayMap;
import androidx.core.util.Pair;
import com.google.common.math.IntMath;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogErrorQueue {
    private static LogErrorQueue instance;
    private int overflowErrorCount = 0;
    private final Map errorMap = new ArrayMap();

    private LogErrorQueue() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized LogErrorQueue getInstance() {
        LogErrorQueue logErrorQueue;
        synchronized (LogErrorQueue.class) {
            if (instance == null) {
                instance = new LogErrorQueue();
            }
            logErrorQueue = instance;
        }
        return logErrorQueue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void add(LogErrorParcelable logErrorParcelable) {
        Pair pair = new Pair(logErrorParcelable.logSourceName, Integer.valueOf(logErrorParcelable.clearcutStatusCode));
        LogErrorParcelable logErrorParcelable2 = (LogErrorParcelable) this.errorMap.get(pair);
        if (logErrorParcelable2 != null) {
            logErrorParcelable2.errorCount = IntMath.saturatedAdd(logErrorParcelable2.errorCount, logErrorParcelable.errorCount);
        } else if (this.errorMap.size() >= 100) {
            this.overflowErrorCount = IntMath.saturatedAdd(this.overflowErrorCount, logErrorParcelable.errorCount);
        } else {
            this.errorMap.put(pair, logErrorParcelable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized BatchedLogErrorParcelable getAndClearErrors() {
        ArrayList arrayList;
        arrayList = new ArrayList(this.errorMap.values());
        if (this.overflowErrorCount > 0) {
            arrayList.add(new LogErrorParcelable("UNKNOWN", 1002, this.overflowErrorCount));
            this.overflowErrorCount = 0;
        }
        this.errorMap.clear();
        return new BatchedLogErrorParcelable(arrayList);
    }
}
