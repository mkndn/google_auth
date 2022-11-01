package com.google.apps.tiktok.tracing;

import com.google.apps.tiktok.tracing.ErrorTrace;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class MissingTraceSpan extends ExtraTrackingTrace implements ErrorTrace {
    private final Exception exception;
    private final boolean logged;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MissingTraceSpan(String str, ErrorTrace errorTrace, SpanExtras spanExtras, boolean z) {
        super(str, errorTrace, spanExtras);
        this.exception = errorTrace.getException();
        this.logged = z;
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public void addCpuTimeMs(int i) {
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public Trace createChildTrace(String str, SpanExtras spanExtras) {
        return createChildTrace(str, spanExtras, true);
    }

    @Override // com.google.apps.tiktok.tracing.ErrorTrace
    public Exception getException() {
        return this.exception;
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public void setEndTime(boolean z) {
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public boolean supportsCpuTime() {
        return false;
    }

    @Override // com.google.apps.tiktok.tracing.ErrorTrace
    public Trace createChildTrace(String str, SpanExtras spanExtras, boolean z) {
        boolean z2 = true;
        if (z && !this.logged) {
            Tracer.checkTrace(true);
        }
        if ((!z || this.logged) && !this.logged) {
            z2 = false;
        }
        return new MissingTraceSpan(str, this, spanExtras, z2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MissingTraceSpan(String str, SpanExtras spanExtras, boolean z) {
        super(str, InsecureUuidGenerator.getInstance().nextUuid(), spanExtras);
        if (TraceCheckingFlag.isEnabled()) {
            this.exception = new ErrorTrace.MissingTraceException();
        } else {
            this.exception = MissingRootTrace.DISABLED_EXCEPTION;
        }
        this.logged = z;
    }
}
