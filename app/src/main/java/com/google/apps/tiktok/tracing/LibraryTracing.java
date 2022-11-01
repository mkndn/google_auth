package com.google.apps.tiktok.tracing;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class LibraryTracing {
    private LibraryTracing() {
    }

    public static LibraryTracing createForNonTikTok() {
        return new LibraryTracing() { // from class: com.google.apps.tiktok.tracing.LibraryTracing.1
            @Override // com.google.apps.tiktok.tracing.LibraryTracing
            public SpanEndSignal beginSpan(String str, TracingRestricted tracingRestricted) {
                return Tracer.beginSpan(str, tracingRestricted, SpanExtras.empty(), false);
            }

            @Override // com.google.apps.tiktok.tracing.LibraryTracing
            public void checkTrace() {
            }
        };
    }

    public static LibraryTracing createForTikTok() {
        return new LibraryTracing() { // from class: com.google.apps.tiktok.tracing.LibraryTracing.2
            @Override // com.google.apps.tiktok.tracing.LibraryTracing
            public SpanEndSignal beginSpan(String str, TracingRestricted tracingRestricted) {
                return Tracer.beginSpan(str, tracingRestricted);
            }

            @Override // com.google.apps.tiktok.tracing.LibraryTracing
            public void checkTrace() {
                Tracer.checkTrace();
            }
        };
    }

    public abstract SpanEndSignal beginSpan(String str, TracingRestricted tracingRestricted);

    public abstract void checkTrace();
}
