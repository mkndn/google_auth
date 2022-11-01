package com.google.apps.tiktok.tracing;

import java.io.Closeable;
import java.util.UUID;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public interface Trace extends Closeable {
    void addCpuTimeMs(int i);

    Trace createChildTrace(String str, SpanExtras spanExtras);

    SpanExtras getExtras();

    String getName();

    Trace getParent();

    UUID getRootTraceId();

    void setEndTime(boolean z);

    boolean supportsCpuTime();
}
