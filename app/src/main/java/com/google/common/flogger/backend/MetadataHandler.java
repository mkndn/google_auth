package com.google.common.flogger.backend;

import com.google.common.flogger.MetadataKey;
import com.google.common.flogger.util.Checks;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class MetadataHandler {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private final ValueHandler defaultHandler;
        private RepeatedValueHandler defaultRepeatedHandler;
        private final Map repeatedValueHandlers;
        private final Map singleValueHandlers;
        private static final ValueHandler IGNORE_VALUE = new ValueHandler() { // from class: com.google.common.flogger.backend.MetadataHandler.Builder.1
            @Override // com.google.common.flogger.backend.MetadataHandler.ValueHandler
            public void handle(MetadataKey metadataKey, Object obj, Object obj2) {
            }
        };
        private static final RepeatedValueHandler IGNORE_REPEATED_VALUE = new RepeatedValueHandler() { // from class: com.google.common.flogger.backend.MetadataHandler.Builder.2
            @Override // com.google.common.flogger.backend.MetadataHandler.RepeatedValueHandler
            public void handle(MetadataKey metadataKey, Iterator it, Object obj) {
            }
        };

        private Builder(ValueHandler valueHandler) {
            this.singleValueHandlers = new HashMap();
            this.repeatedValueHandlers = new HashMap();
            this.defaultRepeatedHandler = null;
            this.defaultHandler = (ValueHandler) Checks.checkNotNull(valueHandler, "default handler");
        }

        public Builder addHandler(MetadataKey metadataKey, ValueHandler valueHandler) {
            Checks.checkNotNull(metadataKey, "key");
            Checks.checkNotNull(valueHandler, "handler");
            this.repeatedValueHandlers.remove(metadataKey);
            this.singleValueHandlers.put(metadataKey, valueHandler);
            return this;
        }

        public Builder addRepeatedHandler(MetadataKey metadataKey, RepeatedValueHandler repeatedValueHandler) {
            Checks.checkNotNull(metadataKey, "key");
            Checks.checkNotNull(repeatedValueHandler, "handler");
            Checks.checkArgument(metadataKey.canRepeat(), "key must be repeating");
            this.singleValueHandlers.remove(metadataKey);
            this.repeatedValueHandlers.put(metadataKey, repeatedValueHandler);
            return this;
        }

        public MetadataHandler build() {
            return new MapBasedhandler(this);
        }

        void checkAndIgnore(MetadataKey metadataKey) {
            Checks.checkNotNull(metadataKey, "key");
            if (metadataKey.canRepeat()) {
                addRepeatedHandler(metadataKey, IGNORE_REPEATED_VALUE);
            } else {
                addHandler(metadataKey, IGNORE_VALUE);
            }
        }

        public Builder ignoring(Iterable iterable) {
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                checkAndIgnore((MetadataKey) it.next());
            }
            return this;
        }

        public Builder setDefaultRepeatedHandler(RepeatedValueHandler repeatedValueHandler) {
            this.defaultRepeatedHandler = (RepeatedValueHandler) Checks.checkNotNull(repeatedValueHandler, "handler");
            return this;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class MapBasedhandler extends MetadataHandler {
        private final ValueHandler defaultHandler;
        private final RepeatedValueHandler defaultRepeatedHandler;
        private final Map repeatedValueHandlers;
        private final Map singleValueHandlers;

        private MapBasedhandler(Builder builder) {
            HashMap hashMap = new HashMap();
            this.singleValueHandlers = hashMap;
            HashMap hashMap2 = new HashMap();
            this.repeatedValueHandlers = hashMap2;
            hashMap.putAll(builder.singleValueHandlers);
            hashMap2.putAll(builder.repeatedValueHandlers);
            this.defaultHandler = builder.defaultHandler;
            this.defaultRepeatedHandler = builder.defaultRepeatedHandler;
        }

        @Override // com.google.common.flogger.backend.MetadataHandler
        protected void handle(MetadataKey metadataKey, Object obj, Object obj2) {
            ValueHandler valueHandler = (ValueHandler) this.singleValueHandlers.get(metadataKey);
            if (valueHandler != null) {
                valueHandler.handle(metadataKey, obj, obj2);
            } else {
                this.defaultHandler.handle(metadataKey, obj, obj2);
            }
        }

        @Override // com.google.common.flogger.backend.MetadataHandler
        protected void handleRepeated(MetadataKey metadataKey, Iterator it, Object obj) {
            RepeatedValueHandler repeatedValueHandler = (RepeatedValueHandler) this.repeatedValueHandlers.get(metadataKey);
            if (repeatedValueHandler != null) {
                repeatedValueHandler.handle(metadataKey, it, obj);
            } else if (this.defaultRepeatedHandler != null && !this.singleValueHandlers.containsKey(metadataKey)) {
                this.defaultRepeatedHandler.handle(metadataKey, it, obj);
            } else {
                super.handleRepeated(metadataKey, it, obj);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface RepeatedValueHandler {
        void handle(MetadataKey metadataKey, Iterator it, Object obj);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ValueHandler {
        void handle(MetadataKey metadataKey, Object obj, Object obj2);
    }

    public static Builder builder(ValueHandler valueHandler) {
        return new Builder(valueHandler);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void handle(MetadataKey metadataKey, Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleRepeated(MetadataKey metadataKey, Iterator it, Object obj) {
        while (it.hasNext()) {
            handle(metadataKey, it.next(), obj);
        }
    }
}
