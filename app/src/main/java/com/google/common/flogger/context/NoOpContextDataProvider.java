package com.google.common.flogger.context;

import java.io.Closeable;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class NoOpContextDataProvider extends ContextDataProvider {
    private static final ContextDataProvider NO_OP_INSTANCE = new NoOpContextDataProvider();
    private final ScopedLoggingContext noOpContext = new NoOpScopedLoggingContext();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class NoOpScopedLoggingContext extends ScopedLoggingContext implements Closeable {
        private final AtomicBoolean haveWarned;

        private NoOpScopedLoggingContext() {
            this.haveWarned = new AtomicBoolean();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }
    }

    NoOpContextDataProvider() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final ContextDataProvider getNoOpInstance() {
        return NO_OP_INSTANCE;
    }

    public String toString() {
        return "No-op Provider";
    }
}
