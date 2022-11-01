package com.google.common.flogger;

import com.google.android.apps.authenticator.migration.MigrationExportBarcodeActivity;
import com.google.common.flogger.backend.LogData;
import com.google.common.flogger.backend.LoggerBackend;
import com.google.common.flogger.backend.LoggingException;
import com.google.common.flogger.backend.MessageUtils;
import com.google.common.flogger.util.Checks;
import com.google.common.flogger.util.RecursionDepth;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractLogger {
    private final LoggerBackend backend;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractLogger(LoggerBackend loggerBackend) {
        this.backend = (LoggerBackend) Checks.checkNotNull(loggerBackend, "backend");
    }

    private static String formatTimestampIso8601(LogData logData) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date(TimeUnit.NANOSECONDS.toMillis(logData.getTimestampNanos())));
    }

    private void handleErrorRobustly(RuntimeException runtimeException, LogData logData) {
        try {
            this.backend.handleError(runtimeException, logData);
        } catch (LoggingException e) {
            throw e;
        } catch (RuntimeException e2) {
            reportError(e2.getClass().getName() + ": " + e2.getMessage(), logData);
            try {
                e2.printStackTrace(System.err);
            } catch (RuntimeException e3) {
            }
        }
    }

    private static void reportError(String str, LogData logData) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatTimestampIso8601(logData)).append(": logging error [");
        MessageUtils.appendLogSite(logData.getLogSite(), sb);
        sb.append("]: ").append(str);
        System.err.println(sb);
        System.err.flush();
    }

    public abstract LoggingApi at(Level level);

    public final LoggingApi atFine() {
        return at(Level.FINE);
    }

    public final LoggingApi atFinest() {
        return at(Level.FINEST);
    }

    public final LoggingApi atInfo() {
        return at(Level.INFO);
    }

    public final LoggingApi atSevere() {
        return at(Level.SEVERE);
    }

    public final LoggingApi atWarning() {
        return at(Level.WARNING);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getName() {
        return this.backend.getLoggerName();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isLoggable(Level level) {
        return this.backend.isLoggable(level);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void write(LogData logData) {
        Checks.checkNotNull(logData, MigrationExportBarcodeActivity.EXTRA_DATA);
        try {
            RecursionDepth enterLogStatement = RecursionDepth.enterLogStatement();
            if (enterLogStatement.getValue() <= 100) {
                this.backend.log(logData);
            } else {
                reportError("unbounded recursion in log statement", logData);
            }
            if (enterLogStatement != null) {
                enterLogStatement.close();
            }
        } catch (RuntimeException e) {
            handleErrorRobustly(e, logData);
        }
    }
}
