package com.google.apps.tiktok.tracing;

import com.google.common.base.Preconditions;
import java.util.UUID;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class AbstractTrace implements Trace {
    private final String name;
    private final Trace parent;
    private final UUID uuid;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractTrace(String str, Trace trace) {
        this.name = (String) Preconditions.checkNotNull(str);
        this.parent = trace;
        this.uuid = trace.getRootTraceId();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Tracer.endSpan(this);
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final String getName() {
        return this.name;
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final Trace getParent() {
        return this.parent;
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final UUID getRootTraceId() {
        return this.uuid;
    }

    public final String toString() {
        return Tracer.traceName(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractTrace(String str, UUID uuid) {
        this.name = (String) Preconditions.checkNotNull(str);
        this.parent = null;
        this.uuid = uuid;
    }
}
