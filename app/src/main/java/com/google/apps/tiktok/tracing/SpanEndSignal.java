package com.google.apps.tiktok.tracing;

import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.Closeable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpanEndSignal implements Runnable, Closeable {
    private boolean attachedToFuture;
    private boolean closed;
    private final boolean startedOnMain = ThreadUtil.isMainThread();
    private Trace trace;
    private Trace whileOpenTrace;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpanEndSignal(Trace trace) {
        this.trace = trace;
        this.whileOpenTrace = trace;
    }

    private void endInternal() {
        boolean z = true;
        this.closed = true;
        this.trace.setEndTime((this.startedOnMain && !this.attachedToFuture && ThreadUtil.isMainThread()) ? false : false);
        this.trace = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$run$0() {
        throw new IllegalStateException("Span was closed by an invalid call to SpanEndSignal.run()");
    }

    public ListenableFuture attachToFuture(ListenableFuture listenableFuture) {
        if (this.closed) {
            throw new IllegalStateException("Span was already closed. Did you attach it to a future after calling Tracer.endSpan()?");
        }
        if (this.attachedToFuture) {
            throw new IllegalStateException("Signal is already attached to future");
        }
        this.attachedToFuture = true;
        listenableFuture.addListener(this, MoreExecutors.directExecutor());
        return listenableFuture;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Trace trace = this.whileOpenTrace;
        this.whileOpenTrace = null;
        try {
            end();
        } finally {
            Tracer.endSpan(trace);
        }
    }

    void end() {
        if (this.attachedToFuture) {
            return;
        }
        if (this.closed) {
            throw new IllegalStateException("Span was already closed!");
        }
        endInternal();
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!this.closed && this.attachedToFuture) {
            endInternal();
        } else {
            ThreadUtil.postOnMainThread(SpanEndSignal$$ExternalSyntheticLambda0.INSTANCE);
        }
    }
}
