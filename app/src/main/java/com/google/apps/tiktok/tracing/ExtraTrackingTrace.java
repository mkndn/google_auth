package com.google.apps.tiktok.tracing;

import com.google.common.base.Preconditions;
import java.util.UUID;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class ExtraTrackingTrace extends AbstractTrace {
    private final SpanExtras extras;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExtraTrackingTrace(String str, Trace trace, SpanExtras spanExtras) {
        super(str, trace);
        Preconditions.checkArgument(spanExtras.isFrozen());
        this.extras = spanExtras;
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public SpanExtras getExtras() {
        return this.extras;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExtraTrackingTrace(String str, UUID uuid, SpanExtras spanExtras) {
        super(str, uuid);
        Preconditions.checkArgument(spanExtras.isFrozen());
        this.extras = spanExtras;
    }
}
