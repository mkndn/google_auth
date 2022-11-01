package com.google.common.flogger.backend;

import com.google.common.flogger.MetadataKey;
import com.google.common.flogger.backend.MetadataHandler;
import java.util.Iterator;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetadataKeyValueHandlers {
    private static final MetadataHandler.ValueHandler EMIT_METADATA = new MetadataHandler.ValueHandler() { // from class: com.google.common.flogger.backend.MetadataKeyValueHandlers.1
        @Override // com.google.common.flogger.backend.MetadataHandler.ValueHandler
        public void handle(MetadataKey metadataKey, Object obj, MetadataKey.KeyValueHandler keyValueHandler) {
            metadataKey.safeEmit(obj, keyValueHandler);
        }
    };
    private static final MetadataHandler.RepeatedValueHandler EMIT_REPEATED_METADATA = new MetadataHandler.RepeatedValueHandler() { // from class: com.google.common.flogger.backend.MetadataKeyValueHandlers.2
        @Override // com.google.common.flogger.backend.MetadataHandler.RepeatedValueHandler
        public void handle(MetadataKey metadataKey, Iterator it, MetadataKey.KeyValueHandler keyValueHandler) {
            metadataKey.safeEmitRepeated(it, keyValueHandler);
        }
    };

    public static MetadataHandler.Builder getDefaultBuilder(Set set) {
        return MetadataHandler.builder(getDefaultValueHandler()).setDefaultRepeatedHandler(getDefaultRepeatedValueHandler()).ignoring(set);
    }

    public static MetadataHandler getDefaultHandler(Set set) {
        return getDefaultBuilder(set).build();
    }

    public static MetadataHandler.RepeatedValueHandler getDefaultRepeatedValueHandler() {
        return EMIT_REPEATED_METADATA;
    }

    public static MetadataHandler.ValueHandler getDefaultValueHandler() {
        return EMIT_METADATA;
    }
}
