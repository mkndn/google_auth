package com.google.apps.tiktok.tracing;

import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.libraries.stitch.flags.DefaultFalseFlag;
import com.google.android.libraries.stitch.flags.Flags;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.apps.tiktok.tracing.SuffixTree;
import com.google.common.base.Preconditions;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Locale;
import java.util.WeakHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Tracer {
    private static int asyncTraceSetCount;
    private static final int TRACE_DELIMITER_LENGTH = " -> ".length();
    static final DefaultFalseFlag ENABLE_SYSTRACE = new DefaultFalseFlag("tiktok_systrace");
    private static final WeakHashMap allThreadStates = new WeakHashMap();
    private static boolean runningWithoutTrace = false;
    private static final ThreadLocal CURRENT = new ThreadLocal() { // from class: com.google.apps.tiktok.tracing.Tracer.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public ThreadState initialValue() {
            ThreadState threadState = new ThreadState(ThreadUtil.isMainThread());
            Thread currentThread = Thread.currentThread();
            synchronized (Tracer.allThreadStates) {
                Tracer.allThreadStates.put(currentThread, threadState);
            }
            return threadState;
        }
    };
    private static final Deque traceQueue = new ArrayDeque();
    private static final Deque asyncCurrent = new ArrayDeque();
    private static final Object UNSET_ASYNC_TRACE = new Object();
    private static final Runnable TRACER_SET_ASYNC_RUNNABLE = Tracer$$ExternalSyntheticLambda4.INSTANCE;
    private static int asyncTraceSetAt = 0;
    private static final Runnable CLEAR_RUNNABLE = Tracer$$ExternalSyntheticLambda5.INSTANCE;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ApiHelperForSdk29 {
        public static boolean isTraceEnabled() {
            return android.os.Trace.isEnabled();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ThreadState {
        final boolean supportsAsyncTrace;
        boolean enableSystrace = false;
        Trace trace = null;
        TraceStorage externalStorage = null;
        int startCpuTimeMs = 0;

        ThreadState(boolean z) {
            this.supportsAsyncTrace = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TraceStorage {
        private Trace trace;
    }

    public static SpanEndSignal beginSpan(String str, TracingRestricted tracingRestricted) {
        return beginSpan(str, tracingRestricted, SpanExtras.empty());
    }

    private static void beginSystraceSection(String str) {
        if (str.length() > 127) {
            str = str.substring(0, AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_APP_USAGE_PERSONALIZATION_WW_VALUE);
        }
        android.os.Trace.beginSection(str);
    }

    public static void checkTrace() {
        checkTrace(false, null);
    }

    private static String compressExcessiveRepeatedTraces(String[] strArr) {
        SuffixTree.TandemRepeatRegion findExcessiveRepeatedRegion = TandemRepeat.findExcessiveRepeatedRegion(strArr);
        if (findExcessiveRepeatedRegion == null) {
            return "";
        }
        int i = (findExcessiveRepeatedRegion.end - findExcessiveRepeatedRegion.begin) * findExcessiveRepeatedRegion.numSeen;
        return String.format(Locale.US, "%s{%s}x%d%s", findExcessiveRepeatedRegion.begin > 0 ? TextUtils.join(" -> ", Arrays.copyOf(strArr, findExcessiveRepeatedRegion.begin)) + " -> " : "", TextUtils.join(" -> ", Arrays.copyOfRange(strArr, findExcessiveRepeatedRegion.begin, findExcessiveRepeatedRegion.end)), Integer.valueOf(findExcessiveRepeatedRegion.numSeen), findExcessiveRepeatedRegion.begin + i < strArr.length ? " -> " + TextUtils.join(" -> ", Arrays.copyOfRange(strArr, findExcessiveRepeatedRegion.begin + i, strArr.length)) : "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void endSpan(Trace trace) {
        Preconditions.checkNotNull(trace);
        ThreadState threadState = (ThreadState) CURRENT.get();
        Trace trace2 = threadState.trace;
        Preconditions.checkNotNull(trace2, "Tried to end span %s, but there was no active span", trace.getName());
        Preconditions.checkState(trace == trace2, "Tried to end span %s, but that span is not the current span. The current span is %s.", trace.getName(), trace2.getName());
        set(threadState, trace2.getParent());
    }

    private static void enterWithParents(Trace trace) {
        if (trace.getParent() != null) {
            enterWithParents(trace.getParent());
        }
        beginSystraceSection(trace.getName());
    }

    private static void exitWithParents(Trace trace) {
        android.os.Trace.endSection();
        if (trace.getParent() != null) {
            exitWithParents(trace.getParent());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Trace get() {
        return ((ThreadState) CURRENT.get()).trace;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Trace getOrCreateDebug() {
        Trace trace = get();
        if (trace == null) {
            return new MissingRootTrace();
        }
        return trace;
    }

    private static boolean isSystraceEnabled() {
        if (Build.VERSION.SDK_INT >= 29) {
            return ApiHelperForSdk29.isTraceEnabled();
        }
        if (Build.VERSION.SDK_INT >= 18) {
            return Flags.get(ENABLE_SYSTRACE);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$static$0() {
        Object remove = traceQueue.remove();
        if (remove == UNSET_ASYNC_TRACE) {
            asyncCurrent.pop();
        } else {
            asyncCurrent.push((Trace) remove);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$static$2() {
        set(null);
        traceQueue.clear();
        ThreadUtil.removeCallbacksOnMainThread(TRACER_SET_ASYNC_RUNNABLE);
        asyncTraceSetCount = 0;
        asyncTraceSetAt = 0;
        asyncCurrent.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Trace set(Trace trace) {
        return set((ThreadState) CURRENT.get(), trace);
    }

    private static void systrace(Trace trace, Trace trace2) {
        if (trace != null) {
            if (trace2 != null) {
                if (trace.getParent() == trace2) {
                    android.os.Trace.endSection();
                    return;
                } else if (trace == trace2.getParent()) {
                    beginSystraceSection(trace2.getName());
                    return;
                }
            }
            exitWithParents(trace);
        }
        if (trace2 != null) {
            enterWithParents(trace2);
        }
    }

    public static SpanEndSignal beginSpan(String str, TracingRestricted tracingRestricted, SpanExtras spanExtras) {
        return beginSpan(str, tracingRestricted, spanExtras, true);
    }

    private static Trace set(ThreadState threadState, Trace trace) {
        Trace trace2 = threadState.trace;
        if (trace2 == trace) {
            return trace;
        }
        if (trace2 == null) {
            threadState.enableSystrace = isSystraceEnabled();
        }
        if (threadState.enableSystrace) {
            systrace(trace2, trace);
        }
        if ((trace != null && trace.supportsCpuTime()) || (trace2 != null && trace2.supportsCpuTime())) {
            int currentThreadTimeMillis = (int) SystemClock.currentThreadTimeMillis();
            int i = currentThreadTimeMillis - threadState.startCpuTimeMs;
            if (i > 0 && trace2 != null && trace2.supportsCpuTime()) {
                trace2.addCpuTimeMs(i);
            }
            threadState.startCpuTimeMs = currentThreadTimeMillis;
        }
        threadState.trace = trace;
        TraceStorage traceStorage = threadState.externalStorage;
        if (traceStorage != null) {
            traceStorage.trace = trace;
        }
        return trace2;
    }

    public static SpanEndSignal beginSpan(String str, TracingRestricted tracingRestricted, SpanExtras spanExtras, boolean z) {
        Trace createChildTrace;
        Preconditions.checkNotNull(tracingRestricted);
        Trace trace = get();
        if (trace == null) {
            if (z) {
                checkTrace(true);
            }
            createChildTrace = new MissingTraceSpan(str, spanExtras, z);
        } else if (trace instanceof ErrorTrace) {
            createChildTrace = ((ErrorTrace) trace).createChildTrace(str, spanExtras, z);
        } else {
            createChildTrace = trace.createChildTrace(str, spanExtras);
        }
        set(createChildTrace);
        return new SpanEndSignal(createChildTrace);
    }

    private static IllegalStateException checkTrace(Trace trace) {
        if (trace == null) {
            return new IllegalStateException("Was supposed to have a trace - did you forget to propagate or create one? See http://go/tiktok-tracing for more details.");
        }
        if (trace instanceof ErrorTrace) {
            return new IllegalStateException("Was supposed to have a trace - did you forget to propagate or create one? See this exception's cause for the last place a trace was missing. See http://go/tiktok-tracing for more details.", ((ErrorTrace) trace).getException());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String traceName(Trace trace) {
        int i = 0;
        int i2 = 0;
        Trace trace2 = trace;
        while (trace2 != null) {
            i++;
            i2 += trace2.getName().length();
            trace2 = trace2.getParent();
            if (trace2 != null) {
                i2 += TRACE_DELIMITER_LENGTH;
            }
        }
        if (i > 250) {
            String[] strArr = new String[i];
            Trace trace3 = trace;
            for (int i3 = i - 1; i3 >= 0; i3--) {
                strArr[i3] = trace3.getName();
                trace3 = trace3.getParent();
            }
            String compressExcessiveRepeatedTraces = compressExcessiveRepeatedTraces(strArr);
            if (!compressExcessiveRepeatedTraces.isEmpty()) {
                return compressExcessiveRepeatedTraces;
            }
        }
        char[] cArr = new char[i2];
        while (trace != null) {
            String name = trace.getName();
            i2 -= name.length();
            name.getChars(0, name.length(), cArr, i2);
            trace = trace.getParent();
            if (trace != null) {
                int i4 = TRACE_DELIMITER_LENGTH;
                i2 -= i4;
                " -> ".getChars(0, i4, cArr, i2);
            }
        }
        return new String(cArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkTrace(boolean z) {
        checkTrace(z, null);
    }

    static void checkTrace(boolean z, String str) {
        IllegalStateException checkTrace;
        if (TraceCheckingFlag.isEnabled() && (checkTrace = checkTrace(get())) != null) {
            if (!z && !TraceCheckingFlag.logOnFailure()) {
                throw checkTrace;
            }
            if (str == null) {
                str = "Tracer";
            }
            Log.e(str, "Missing trace", checkTrace);
        }
    }
}
