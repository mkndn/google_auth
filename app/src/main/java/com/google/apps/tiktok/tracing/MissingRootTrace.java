package com.google.apps.tiktok.tracing;

import com.google.apps.tiktok.tracing.ErrorTrace;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class MissingRootTrace extends AbstractTrace implements ErrorTrace {
    static final ErrorTrace.MissingTraceException DISABLED_EXCEPTION = new ErrorTrace.TraceCauseCheckingDisabled();
    private final ErrorTrace.MissingTraceException exception;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MissingRootTrace() {
        super("", InsecureUuidGenerator.getInstance().nextUuid());
        if (TraceCheckingFlag.isEnabled()) {
            this.exception = new ErrorTrace.MissingTraceException();
        } else {
            this.exception = DISABLED_EXCEPTION;
        }
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public void addCpuTimeMs(int i) {
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public Trace createChildTrace(String str, SpanExtras spanExtras) {
        Tracer.checkTrace(true);
        return createChildTrace(str, spanExtras, true);
    }

    @Override // com.google.apps.tiktok.tracing.ErrorTrace
    public ErrorTrace.MissingTraceException getException() {
        return this.exception;
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public SpanExtras getExtras() {
        return SpanExtras.empty();
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
        if (z) {
            Tracer.checkTrace(true);
        }
        return new MissingTraceSpan(str, this, spanExtras, z);
    }
}
