package com.google.common.flogger.backend.android;

import android.util.Log;
import com.google.common.flogger.LogContext;
import com.google.common.flogger.android.AndroidLogTag;
import com.google.common.flogger.backend.BaseMessageFormatter;
import com.google.common.flogger.backend.LogData;
import com.google.common.flogger.backend.LoggerBackend;
import com.google.common.flogger.backend.MessageUtils;
import com.google.common.flogger.backend.MetadataHandler;
import com.google.common.flogger.backend.MetadataKeyValueHandlers;
import com.google.common.flogger.backend.MetadataProcessor;
import com.google.common.flogger.backend.Platform;
import com.google.common.flogger.backend.SimpleMessageFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SimpleAndroidLoggerBackend extends AbstractAndroidBackend {
    private static final Set DEFAULT_KEYS_TO_IGNORE;
    private static final MetadataHandler DEFAULT_METADATA_HANDLER;
    private static final Factory SINGLETON_DEFAULT_FACTORY;
    private final Level includeFormatArgumentsLevel;
    private final Set keysToIgnore;
    private final MetadataHandler metadataHandler;
    private final boolean prependLogSite;
    private final String tagName;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LogSiteBasedBackend extends AbstractAndroidBackend {
        private final boolean alwaysLog;
        private final boolean alwaysTruncate;
        private final Level includeFormatArgumentsLevel;
        private final Set keysToIgnore;
        private final MetadataHandler metadataHandler;
        private final String prefix;
        private final boolean prependLogSite;

        private LogSiteBasedBackend(String str, String str2, boolean z, boolean z2, Level level, boolean z3, Set set, MetadataHandler metadataHandler) {
            super(str2);
            this.prefix = str;
            this.alwaysTruncate = z;
            this.prependLogSite = z2;
            this.includeFormatArgumentsLevel = level;
            this.alwaysLog = z3;
            this.keysToIgnore = set;
            this.metadataHandler = metadataHandler;
        }

        private static String getOuterClass(String str) {
            int indexOf = str.indexOf(36, str.lastIndexOf(46));
            return indexOf < 0 ? str : str.substring(0, indexOf);
        }

        private String getTag(LogData logData) {
            String str = (String) logData.getMetadata().findValue(AndroidLogTag.TAG);
            if (str == null) {
                str = getLoggerName();
            }
            if (str == null) {
                str = getOuterClass(logData.getLogSite().getClassName());
            }
            return LogUtils.getValidTag(this.prefix, str, this.alwaysTruncate);
        }

        @Override // com.google.common.flogger.backend.LoggerBackend
        public boolean isLoggable(Level level) {
            return true;
        }

        @Override // com.google.common.flogger.backend.LoggerBackend
        public void log(LogData logData) {
            String tag = getTag(logData);
            if (isLoggable(logData.getLevel(), tag)) {
                SimpleAndroidLoggerBackend.log(logData, tag, this.prependLogSite, this.includeFormatArgumentsLevel, this.keysToIgnore, this.metadataHandler);
            }
        }

        private boolean isLoggable(Level level, String str) {
            if (this.alwaysLog) {
                return true;
            }
            int value = LogUtils.getAndroidLevel(level).getValue();
            return Log.isLoggable(str, value) || Log.isLoggable("all", value);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public LogSiteBasedBackend(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
            this(str, str2, z, z2, z3 ? Level.ALL : Level.OFF, z4, SimpleAndroidLoggerBackend.DEFAULT_KEYS_TO_IGNORE, SimpleAndroidLoggerBackend.DEFAULT_METADATA_HANDLER);
        }

        private LogSiteBasedBackend(String str, boolean z, boolean z2, Level level, Set set, MetadataHandler metadataHandler) {
            this(str, null, z, z2, level, false, set, metadataHandler);
        }
    }

    static {
        Set unmodifiableSet = Collections.unmodifiableSet(new HashSet(Arrays.asList(LogContext.Key.LOG_CAUSE, AndroidLogTag.TAG)));
        DEFAULT_KEYS_TO_IGNORE = unmodifiableSet;
        DEFAULT_METADATA_HANDLER = MetadataKeyValueHandlers.getDefaultHandler(unmodifiableSet);
        SINGLETON_DEFAULT_FACTORY = new Factory();
    }

    private SimpleAndroidLoggerBackend(String str, String str2, boolean z, boolean z2, Level level, Set set, MetadataHandler metadataHandler) {
        super(str2);
        this.tagName = LogUtils.getValidTag(str, str2, z);
        this.prependLogSite = z2;
        this.includeFormatArgumentsLevel = level;
        this.keysToIgnore = set;
        this.metadataHandler = metadataHandler;
    }

    private static String formatMessage(LogData logData, MetadataProcessor metadataProcessor, boolean z, boolean z2, Set set, MetadataHandler metadataHandler) {
        if (!(z || z2 || SimpleMessageFormatter.mustBeFormatted(logData, metadataProcessor, set))) {
            return SimpleMessageFormatter.getLiteralLogMessage(logData);
        }
        StringBuilder sb = new StringBuilder();
        if (z && MessageUtils.appendLogSite(logData.getLogSite(), sb)) {
            sb.append(" ");
        }
        if (z2 && logData.getTemplateContext() != null) {
            sb.append("(REDACTED) ");
            sb.append(logData.getTemplateContext().getMessage());
        } else {
            BaseMessageFormatter.appendFormattedMessage(logData, sb);
            SimpleMessageFormatter.appendContext(metadataProcessor, metadataHandler, sb);
        }
        return sb.toString();
    }

    @Override // com.google.common.flogger.backend.LoggerBackend
    public boolean isLoggable(Level level) {
        int value = LogUtils.getAndroidLevel(level).getValue();
        return Log.isLoggable(this.tagName, value) || Log.isLoggable("all", value);
    }

    @Override // com.google.common.flogger.backend.LoggerBackend
    public void log(LogData logData) {
        log(logData, this.tagName, this.prependLogSite, this.includeFormatArgumentsLevel, this.keysToIgnore, this.metadataHandler);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Factory implements AndroidBackendFactory {
        private final boolean allowSingleton;
        private final boolean alwaysTruncate;
        private final Level includeFormatArgumentsLevel;
        private final Set keysToIgnore;
        private volatile LogSiteBasedBackend logSiteBasedBackend;
        private final MetadataHandler metadataHandler;
        private final String prefix;
        private final boolean prependLogSite;

        public Factory() {
            this("", true, false, Level.ALL, false, SimpleAndroidLoggerBackend.DEFAULT_KEYS_TO_IGNORE, SimpleAndroidLoggerBackend.DEFAULT_METADATA_HANDLER);
        }

        private LogSiteBasedBackend getSingleton() {
            LogSiteBasedBackend logSiteBasedBackend = this.logSiteBasedBackend;
            if (logSiteBasedBackend == null) {
                synchronized (this) {
                    logSiteBasedBackend = this.logSiteBasedBackend;
                    if (logSiteBasedBackend == null) {
                        logSiteBasedBackend = new LogSiteBasedBackend(this.prefix, this.alwaysTruncate, this.prependLogSite, this.includeFormatArgumentsLevel, this.keysToIgnore, this.metadataHandler);
                        this.logSiteBasedBackend = logSiteBasedBackend;
                    }
                }
            }
            return logSiteBasedBackend;
        }

        @Override // com.google.common.flogger.backend.android.AndroidBackendFactory
        public LoggerBackend create(String str) {
            if (this.allowSingleton && str.contains(".")) {
                return getSingleton();
            }
            return new SimpleAndroidLoggerBackend(this.prefix, str, this.alwaysTruncate, this.prependLogSite, this.includeFormatArgumentsLevel, this.keysToIgnore, this.metadataHandler);
        }

        public Factory withIncludeFormatArguments(boolean z) {
            return new Factory(this.prefix, this.alwaysTruncate, this.prependLogSite, z ? Level.ALL : Level.OFF, this.allowSingleton, this.keysToIgnore, this.metadataHandler);
        }

        private Factory(String str, boolean z, boolean z2, Level level, boolean z3, Set set, MetadataHandler metadataHandler) {
            this.prefix = str;
            this.alwaysTruncate = z;
            this.prependLogSite = z2;
            this.includeFormatArgumentsLevel = level;
            this.allowSingleton = z3;
            this.keysToIgnore = set;
            this.metadataHandler = metadataHandler;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(LogData logData, String str, boolean z, Level level, Set set, MetadataHandler metadataHandler) {
        String formatMessage = formatMessage(logData, MetadataProcessor.forScopeAndLogSite(Platform.getInjectedMetadata(), logData.getMetadata()), z, logData.getLevel().intValue() < level.intValue(), set, metadataHandler);
        Throwable th = (Throwable) logData.getMetadata().findValue(LogContext.Key.LOG_CAUSE);
        Level level2 = logData.getLevel();
        switch (LogUtils.getAndroidLevel(level2).getValue()) {
            case 2:
                Log.v(str, formatMessage, th);
                return;
            case 3:
                Log.d(str, formatMessage, th);
                return;
            case 4:
                Log.i(str, formatMessage, th);
                return;
            case 5:
                Log.w(str, formatMessage, th);
                return;
            case 6:
                Log.e(str, formatMessage, th);
                return;
            default:
                Log.wtf(str, String.format(Locale.ROOT, "Level \"%d\" is not a valid level", Integer.valueOf(level2.intValue())));
                return;
        }
    }
}
