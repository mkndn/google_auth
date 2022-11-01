package com.google.common.flogger;

import com.google.common.flogger.backend.LoggerBackend;
import com.google.common.flogger.backend.Platform;
import com.google.common.flogger.util.Checks;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleLogger extends AbstractLogger {
    private static final NoOp NO_OP = new NoOp();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Api extends LoggingApi {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Context extends GoogleLogContext implements Api {
        Context(Level level, boolean z) {
            super(level, z);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.flogger.LogContext
        public Api api() {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.flogger.LogContext
        public GoogleLogger getLogger() {
            return GoogleLogger.this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NoOp extends GoogleLoggingApi$NoOp implements Api {
        private NoOp() {
        }
    }

    GoogleLogger(LoggerBackend loggerBackend) {
        super(loggerBackend);
    }

    @Deprecated
    public static GoogleLogger forInjectedClassName(String str) {
        Checks.checkArgument(!str.isEmpty(), "injected class name is empty");
        return new GoogleLogger(Platform.getBackend(str.replace('/', '.')));
    }

    private Context newContext(Level level, boolean z) {
        return new Context(level, z);
    }

    @Override // com.google.common.flogger.AbstractLogger
    public Api at(Level level) {
        boolean isLoggable = isLoggable(level);
        boolean shouldForceLogging = Platform.shouldForceLogging(getName(), level, isLoggable);
        if (!isLoggable && !shouldForceLogging) {
            return NO_OP;
        }
        return newContext(level, shouldForceLogging);
    }
}
