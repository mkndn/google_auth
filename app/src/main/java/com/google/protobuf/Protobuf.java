package com.google.protobuf;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class Protobuf {
    private static final Protobuf INSTANCE = new Protobuf();
    private final ConcurrentMap schemaCache = new ConcurrentHashMap();
    private final SchemaFactory schemaFactory = new ManifestSchemaFactory();

    private Protobuf() {
    }

    public static Protobuf getInstance() {
        return INSTANCE;
    }

    public void mergeFrom(Object obj, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        schemaFor(obj).mergeFrom(obj, reader, extensionRegistryLite);
    }

    public Schema registerSchema(Class cls, Schema schema) {
        Internal.checkNotNull(cls, "messageType");
        Internal.checkNotNull(schema, "schema");
        return (Schema) this.schemaCache.putIfAbsent(cls, schema);
    }

    public Schema schemaFor(Class cls) {
        Schema registerSchema;
        Internal.checkNotNull(cls, "messageType");
        Schema schema = (Schema) this.schemaCache.get(cls);
        return (schema != null || (registerSchema = registerSchema(cls, (schema = this.schemaFactory.createSchema(cls)))) == null) ? schema : registerSchema;
    }

    public Schema schemaFor(Object obj) {
        return schemaFor((Class) obj.getClass());
    }
}
