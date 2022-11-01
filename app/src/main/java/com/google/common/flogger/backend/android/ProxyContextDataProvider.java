package com.google.common.flogger.backend.android;

import com.google.common.flogger.backend.Metadata;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.flogger.context.Tags;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ProxyContextDataProvider extends ContextDataProvider {
    private static final ProxyContextDataProvider INSTANCE = new ProxyContextDataProvider(ContextDataProvider.getNoOpProvider());
    private final AtomicReference delegate;

    ProxyContextDataProvider(ContextDataProvider contextDataProvider) {
        this.delegate = new AtomicReference(contextDataProvider);
    }

    public static final ProxyContextDataProvider getInstance() {
        return INSTANCE;
    }

    @Override // com.google.common.flogger.context.ContextDataProvider
    public Metadata getMetadata() {
        return ((ContextDataProvider) this.delegate.get()).getMetadata();
    }

    @Override // com.google.common.flogger.context.ContextDataProvider
    public Tags getTags() {
        return ((ContextDataProvider) this.delegate.get()).getTags();
    }

    @Override // com.google.common.flogger.context.ContextDataProvider
    public boolean shouldForceLogging(String str, Level level, boolean z) {
        return ((ContextDataProvider) this.delegate.get()).shouldForceLogging(str, level, z);
    }
}
