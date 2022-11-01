package com.google.common.flogger.backend;

import com.google.common.flogger.LogContext;
import java.util.Collections;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SimpleMessageFormatter {
    private static final LogMessageFormatter DEFAULT_FORMATTER;
    private static final Set DEFAULT_KEYS_TO_IGNORE;

    static {
        Set singleton = Collections.singleton(LogContext.Key.LOG_CAUSE);
        DEFAULT_KEYS_TO_IGNORE = singleton;
        DEFAULT_FORMATTER = newFormatter(singleton);
    }

    public static StringBuilder appendContext(MetadataProcessor metadataProcessor, MetadataHandler metadataHandler, StringBuilder sb) {
        KeyValueFormatter keyValueFormatter = new KeyValueFormatter("[CONTEXT ", " ]", sb);
        metadataProcessor.process(metadataHandler, keyValueFormatter);
        keyValueFormatter.done();
        return sb;
    }

    public static String getLiteralLogMessage(LogData logData) {
        return MessageUtils.safeToString(logData.getLiteralArgument());
    }

    public static boolean mustBeFormatted(LogData logData, MetadataProcessor metadataProcessor, Set set) {
        if (logData.getTemplateContext() == null && metadataProcessor.keyCount() <= set.size() && set.containsAll(metadataProcessor.keySet())) {
            return false;
        }
        return true;
    }

    private static LogMessageFormatter newFormatter(Set set) {
        return new LogMessageFormatter(set) { // from class: com.google.common.flogger.backend.SimpleMessageFormatter.1
            private final MetadataHandler handler;
            final /* synthetic */ Set val$keysToIgnore;

            {
                this.val$keysToIgnore = set;
                this.handler = MetadataKeyValueHandlers.getDefaultHandler(set);
            }
        };
    }
}
