package com.google.apps.tiktok.tracing;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExceptionTracer {
    private static boolean isEnabled = false;
    private static final WeakHashMap exceptions = new WeakHashMap();
    private static final WeakHashMap exceptionsWithTraceStack = new WeakHashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TracedException {
        private final TraceInfo info;
        private final Throwable t;

        TracedException(Throwable th, TraceInfo traceInfo) {
            this.t = th;
            this.info = traceInfo;
        }
    }

    public static TracedException getTracedException(Throwable th) {
        Preconditions.checkState(isEnabled, "Trace uncaught exception is disabled.");
        synchronized (exceptions) {
            Throwable th2 = th;
            while (th2 != null) {
                try {
                    if (exceptions.containsKey(th2)) {
                        break;
                    }
                    th2 = th2.getCause();
                } catch (Throwable th3) {
                    throw th3;
                }
            }
            if (th2 == null) {
                return null;
            }
            WeakHashMap weakHashMap = exceptions;
            TraceInfo traceInfo = (TraceInfo) weakHashMap.get(th2);
            weakHashMap.put(th, traceInfo);
            return new TracedException(th2, traceInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void reportException(Throwable th) {
        if (!isEnabled || isExceptionWithTraceStack(th) || getTracedException(th) != null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Trace trace = Tracer.get(); trace != null; trace = trace.getParent()) {
            arrayList.add(trace);
        }
        ImmutableList.Builder builderWithExpectedSize = ImmutableList.builderWithExpectedSize(arrayList.size());
        ImmutableList.Builder builderWithExpectedSize2 = ImmutableList.builderWithExpectedSize(arrayList.size());
        for (Trace trace2 : Lists.reverse(arrayList)) {
            builderWithExpectedSize2.add((Object) trace2.getName());
            builderWithExpectedSize.add((Object) trace2.getExtras());
        }
        TraceInfo traceInfo = new TraceInfo(builderWithExpectedSize2.build(), builderWithExpectedSize.build());
        WeakHashMap weakHashMap = exceptions;
        synchronized (weakHashMap) {
            weakHashMap.put(th, traceInfo);
        }
    }

    private static boolean isExceptionWithTraceStack(Throwable th) {
        Throwable th2;
        synchronized (exceptionsWithTraceStack) {
            th2 = th;
            while (th2 != null) {
                if (exceptionsWithTraceStack.containsKey(th2)) {
                    break;
                }
                th2 = th2.getCause();
            }
            exceptionsWithTraceStack.put(th, Boolean.valueOf(th2 != null));
        }
        return th2 != null;
    }
}
