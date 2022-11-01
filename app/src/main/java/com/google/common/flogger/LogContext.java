package com.google.common.flogger;

import com.google.common.flogger.LogSiteStats;
import com.google.common.flogger.MetadataKey;
import com.google.common.flogger.backend.LogData;
import com.google.common.flogger.backend.Metadata;
import com.google.common.flogger.backend.Platform;
import com.google.common.flogger.backend.TemplateContext;
import com.google.common.flogger.context.Tags;
import com.google.common.flogger.parser.MessageParser;
import com.google.common.flogger.util.CallerFinder;
import com.google.common.flogger.util.Checks;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class LogContext implements LoggingApi, LogData {
    private static final String LITERAL_VALUE_MESSAGE = new String();
    private Object[] args;
    private final Level level;
    private LogSite logSite;
    private MutableMetadata metadata;
    private TemplateContext templateContext;
    private final long timestampNanos;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Key {
        public static final MetadataKey LOG_CAUSE = MetadataKey.single("cause", Throwable.class);
        public static final MetadataKey LOG_EVERY_N = MetadataKey.single("ratelimit_count", Integer.class);
        public static final MetadataKey LOG_AT_MOST_EVERY = MetadataKey.single("ratelimit_period", LogSiteStats.RateLimitPeriod.class);
        public static final MetadataKey LOG_SITE_GROUPING_KEY = new MetadataKey("group_by", Object.class, true) { // from class: com.google.common.flogger.LogContext.Key.1
            @Override // com.google.common.flogger.MetadataKey
            public void emitRepeated(Iterator it, MetadataKey.KeyValueHandler keyValueHandler) {
                if (it.hasNext()) {
                    Object next = it.next();
                    if (!it.hasNext()) {
                        keyValueHandler.handle(getLabel(), next);
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append('[').append(next);
                    do {
                        sb.append(',').append(it.next());
                    } while (it.hasNext());
                    keyValueHandler.handle(getLabel(), sb.append(']').toString());
                }
            }
        };
        public static final MetadataKey WAS_FORCED = MetadataKey.single("forced", Boolean.class);
        public static final MetadataKey TAGS = new MetadataKey("tags", Tags.class, false) { // from class: com.google.common.flogger.LogContext.Key.2
            @Override // com.google.common.flogger.MetadataKey
            public void emit(Tags tags, MetadataKey.KeyValueHandler keyValueHandler) {
                for (Map.Entry entry : tags.asMap().entrySet()) {
                    if (!((Set) entry.getValue()).isEmpty()) {
                        for (Object obj : (Set) entry.getValue()) {
                            keyValueHandler.handle((String) entry.getKey(), obj);
                        }
                    } else {
                        keyValueHandler.handle((String) entry.getKey(), null);
                    }
                }
            }
        };
        public static final MetadataKey CONTEXT_STACK_SIZE = MetadataKey.single("stack_size", StackSize.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MutableMetadata extends Metadata {
        private Object[] keyValuePairs = new Object[8];
        private int keyValueCount = 0;

        MutableMetadata() {
        }

        private int indexOf(MetadataKey metadataKey) {
            for (int i = 0; i < this.keyValueCount; i++) {
                if (this.keyValuePairs[i + i].equals(metadataKey)) {
                    return i;
                }
            }
            return -1;
        }

        void addValue(MetadataKey metadataKey, Object obj) {
            int indexOf;
            if (!metadataKey.canRepeat() && (indexOf = indexOf(metadataKey)) != -1) {
                this.keyValuePairs[indexOf + indexOf + 1] = Checks.checkNotNull(obj, "metadata value");
                return;
            }
            int i = this.keyValueCount + 1;
            int i2 = i + i;
            Object[] objArr = this.keyValuePairs;
            if (i2 > objArr.length) {
                int length = objArr.length;
                this.keyValuePairs = Arrays.copyOf(objArr, length + length);
            }
            Object[] objArr2 = this.keyValuePairs;
            int i3 = this.keyValueCount;
            objArr2[i3 + i3] = Checks.checkNotNull(metadataKey, "metadata key");
            Object[] objArr3 = this.keyValuePairs;
            int i4 = this.keyValueCount;
            objArr3[i4 + i4 + 1] = Checks.checkNotNull(obj, "metadata value");
            this.keyValueCount++;
        }

        @Override // com.google.common.flogger.backend.Metadata
        public Object findValue(MetadataKey metadataKey) {
            int indexOf = indexOf(metadataKey);
            if (indexOf != -1) {
                return metadataKey.cast(this.keyValuePairs[indexOf + indexOf + 1]);
            }
            return null;
        }

        @Override // com.google.common.flogger.backend.Metadata
        public MetadataKey getKey(int i) {
            if (i >= this.keyValueCount) {
                throw new IndexOutOfBoundsException();
            }
            return (MetadataKey) this.keyValuePairs[i + i];
        }

        @Override // com.google.common.flogger.backend.Metadata
        public Object getValue(int i) {
            if (i >= this.keyValueCount) {
                throw new IndexOutOfBoundsException();
            }
            return this.keyValuePairs[i + i + 1];
        }

        void removeAllValues(MetadataKey metadataKey) {
            int i;
            int indexOf = indexOf(metadataKey);
            if (indexOf >= 0) {
                int i2 = indexOf + indexOf;
                int i3 = i2 + 2;
                while (true) {
                    i = this.keyValueCount;
                    if (i3 >= i + i) {
                        break;
                    }
                    Object obj = this.keyValuePairs[i3];
                    if (!obj.equals(metadataKey)) {
                        Object[] objArr = this.keyValuePairs;
                        objArr[i2] = obj;
                        objArr[i2 + 1] = objArr[i3 + 1];
                        i2 += 2;
                    }
                    i3 += 2;
                }
                this.keyValueCount = i - ((i3 - i2) >> 1);
                while (i2 < i3) {
                    this.keyValuePairs[i2] = null;
                    i2++;
                }
            }
        }

        @Override // com.google.common.flogger.backend.Metadata
        public int size() {
            return this.keyValueCount;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("Metadata{");
            for (int i = 0; i < size(); i++) {
                sb.append(" '").append(getKey(i)).append("': ").append(getValue(i));
            }
            return sb.append(" }").toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LogContext(Level level, boolean z) {
        this(level, z, Platform.getCurrentTimeNanos());
    }

    private void logImpl(String str, Object... objArr) {
        this.args = objArr;
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj instanceof LazyArg) {
                objArr[i] = ((LazyArg) obj).evaluate();
            }
        }
        if (str != LITERAL_VALUE_MESSAGE) {
            this.templateContext = new TemplateContext(getMessageParser(), str);
        }
        Tags injectedTags = Platform.getInjectedTags();
        if (!injectedTags.isEmpty()) {
            Tags tags = (Tags) getMetadata().findValue(Key.TAGS);
            if (tags != null) {
                injectedTags = injectedTags.merge(tags);
            }
            addMetadata(Key.TAGS, injectedTags);
        }
        getLogger().write(this);
    }

    private boolean shouldLog() {
        LogSiteKey logSiteKey;
        if (this.logSite == null) {
            this.logSite = (LogSite) Checks.checkNotNull(Platform.getCallerFinder().findLogSite(LogContext.class, 1), "logger backend must not return a null LogSite");
        }
        if (this.logSite != LogSite.INVALID) {
            logSiteKey = this.logSite;
            MutableMetadata mutableMetadata = this.metadata;
            if (mutableMetadata != null && mutableMetadata.size() > 0) {
                logSiteKey = specializeLogSiteKeyFromMetadata(logSiteKey, this.metadata);
            }
        } else {
            logSiteKey = null;
        }
        return postProcess(logSiteKey);
    }

    static LogSiteKey specializeLogSiteKeyFromMetadata(LogSiteKey logSiteKey, Metadata metadata) {
        Checks.checkNotNull(logSiteKey, "logSiteKey");
        int size = metadata.size();
        for (int i = 0; i < size; i++) {
            if (Key.LOG_SITE_GROUPING_KEY.equals(metadata.getKey(i))) {
                Object value = metadata.getValue(i);
                if (value instanceof LoggingScope) {
                    logSiteKey = ((LoggingScope) value).specialize(logSiteKey);
                } else {
                    logSiteKey = SpecializedLogSiteKey.of(logSiteKey, value);
                }
            }
        }
        return logSiteKey;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void addMetadata(MetadataKey metadataKey, Object obj) {
        if (this.metadata == null) {
            this.metadata = new MutableMetadata();
        }
        this.metadata.addValue(metadataKey, obj);
    }

    protected abstract LoggingApi api();

    @Override // com.google.common.flogger.backend.LogData
    public final Object[] getArguments() {
        if (this.templateContext == null) {
            throw new IllegalStateException("cannot get arguments unless a template context exists");
        }
        return this.args;
    }

    @Override // com.google.common.flogger.backend.LogData
    public final Level getLevel() {
        return this.level;
    }

    @Override // com.google.common.flogger.backend.LogData
    public final Object getLiteralArgument() {
        if (this.templateContext != null) {
            throw new IllegalStateException("cannot get literal argument if a template context exists");
        }
        return this.args[0];
    }

    @Override // com.google.common.flogger.backend.LogData
    public final LogSite getLogSite() {
        LogSite logSite = this.logSite;
        if (logSite == null) {
            throw new IllegalStateException("cannot request log site information prior to postProcess()");
        }
        return logSite;
    }

    protected abstract AbstractLogger getLogger();

    protected abstract MessageParser getMessageParser();

    @Override // com.google.common.flogger.backend.LogData
    public final Metadata getMetadata() {
        MutableMetadata mutableMetadata = this.metadata;
        return mutableMetadata != null ? mutableMetadata : Metadata.empty();
    }

    @Override // com.google.common.flogger.backend.LogData
    public final TemplateContext getTemplateContext() {
        return this.templateContext;
    }

    @Override // com.google.common.flogger.backend.LogData
    public final long getTimestampNanos() {
        return this.timestampNanos;
    }

    @Override // com.google.common.flogger.LoggingApi
    public final boolean isEnabled() {
        return wasForced() || getLogger().isLoggable(this.level);
    }

    @Override // com.google.common.flogger.LoggingApi
    public final void log(String str) {
        if (shouldLog()) {
            logImpl(LITERAL_VALUE_MESSAGE, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean postProcess(LogSiteKey logSiteKey) {
        MutableMetadata mutableMetadata = this.metadata;
        if (mutableMetadata != null) {
            if (logSiteKey != null) {
                Integer num = (Integer) mutableMetadata.findValue(Key.LOG_EVERY_N);
                LogSiteStats.RateLimitPeriod rateLimitPeriod = (LogSiteStats.RateLimitPeriod) this.metadata.findValue(Key.LOG_AT_MOST_EVERY);
                LogSiteStats statsForKey = LogSiteStats.getStatsForKey(logSiteKey, this.metadata);
                if (num != null && !statsForKey.incrementAndCheckInvocationCount(num.intValue())) {
                    return false;
                }
                if (rateLimitPeriod != null && !statsForKey.checkLastTimestamp(getTimestampNanos(), rateLimitPeriod)) {
                    return false;
                }
            }
            StackSize stackSize = (StackSize) this.metadata.findValue(Key.CONTEXT_STACK_SIZE);
            if (stackSize != null) {
                removeMetadata(Key.CONTEXT_STACK_SIZE);
                addMetadata(Key.LOG_CAUSE, new LogSiteStackTrace((Throwable) getMetadata().findValue(Key.LOG_CAUSE), stackSize, CallerFinder.getStackForCallerOf(LogContext.class, stackSize.getMaxDepth(), 1)));
            }
        }
        return true;
    }

    protected final void removeMetadata(MetadataKey metadataKey) {
        MutableMetadata mutableMetadata = this.metadata;
        if (mutableMetadata != null) {
            mutableMetadata.removeAllValues(metadataKey);
        }
    }

    @Override // com.google.common.flogger.backend.LogData
    public final boolean wasForced() {
        return this.metadata != null && Boolean.TRUE.equals(this.metadata.findValue(Key.WAS_FORCED));
    }

    public final LoggingApi with(MetadataKey metadataKey, Object obj) {
        Checks.checkNotNull(metadataKey, "metadata key");
        if (obj != null) {
            addMetadata(metadataKey, obj);
        }
        return api();
    }

    @Override // com.google.common.flogger.LoggingApi
    public final LoggingApi withCause(Throwable th) {
        return with(Key.LOG_CAUSE, th);
    }

    public final LoggingApi withInjectedLogSite(LogSite logSite) {
        if (this.logSite == null && logSite != null) {
            this.logSite = logSite;
        }
        return api();
    }

    protected LogContext(Level level, boolean z, long j) {
        this.metadata = null;
        this.logSite = null;
        this.templateContext = null;
        this.args = null;
        this.level = (Level) Checks.checkNotNull(level, "level");
        this.timestampNanos = j;
        if (z) {
            addMetadata(Key.WAS_FORCED, Boolean.TRUE);
        }
    }

    @Override // com.google.common.flogger.LoggingApi
    public final void log(String str, int i) {
        if (shouldLog()) {
            logImpl(str, Integer.valueOf(i));
        }
    }

    @Override // com.google.common.flogger.LoggingApi
    public final void log(String str, long j) {
        if (shouldLog()) {
            logImpl(str, Long.valueOf(j));
        }
    }

    @Override // com.google.common.flogger.LoggingApi
    public final LoggingApi withInjectedLogSite(String str, String str2, int i, String str3) {
        return withInjectedLogSite(LogSite.injectedLogSite(str, str2, i, str3));
    }

    @Override // com.google.common.flogger.LoggingApi
    public final void log(String str, Object obj) {
        if (shouldLog()) {
            logImpl(str, obj);
        }
    }

    @Override // com.google.common.flogger.LoggingApi
    public final void log(String str, Object obj, Object obj2) {
        if (shouldLog()) {
            logImpl(str, obj, obj2);
        }
    }

    @Override // com.google.common.flogger.LoggingApi
    public final void log(String str, Object obj, Object obj2, Object obj3) {
        if (shouldLog()) {
            logImpl(str, obj, obj2, obj3);
        }
    }
}
