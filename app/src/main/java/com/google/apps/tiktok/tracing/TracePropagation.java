package com.google.apps.tiktok.tracing;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TracePropagation {
    private static long nextIntentId = Math.abs(new Random().nextInt()) << 30;
    static final Map ACTIVE_TRACES = new HashMap();

    public static AsyncCallable propagateAsyncCallable(final AsyncCallable asyncCallable) {
        Preconditions.checkNotNull(asyncCallable);
        final Trace orCreateDebug = Tracer.getOrCreateDebug();
        return new AsyncCallable() { // from class: com.google.apps.tiktok.tracing.TracePropagation.5
            @Override // com.google.common.util.concurrent.AsyncCallable
            public ListenableFuture call() {
                Trace trace = Tracer.set(Trace.this);
                try {
                    return asyncCallable.call();
                } finally {
                    Tracer.set(trace);
                }
            }

            public String toString() {
                return "propagating=[" + String.valueOf(asyncCallable) + "]";
            }
        };
    }

    public static AsyncFunction propagateAsyncFunction(final AsyncFunction asyncFunction) {
        final Trace orCreateDebug = Tracer.getOrCreateDebug();
        return new AsyncFunction() { // from class: com.google.apps.tiktok.tracing.TracePropagation.7
            @Override // com.google.common.util.concurrent.AsyncFunction
            public ListenableFuture apply(Object obj) {
                Trace trace = Tracer.set(Trace.this);
                try {
                    return asyncFunction.apply(obj);
                } finally {
                }
            }

            public String toString() {
                return "propagating=[" + String.valueOf(asyncFunction) + "]";
            }
        };
    }
}
