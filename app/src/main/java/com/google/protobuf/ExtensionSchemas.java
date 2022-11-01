package com.google.protobuf;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ExtensionSchemas {
    private static final ExtensionSchema LITE_SCHEMA = new ExtensionSchemaLite();
    private static final ExtensionSchema FULL_SCHEMA = loadSchemaForFullRuntime();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ExtensionSchema full() {
        ExtensionSchema extensionSchema = FULL_SCHEMA;
        if (extensionSchema == null) {
            throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
        }
        return extensionSchema;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ExtensionSchema lite() {
        return LITE_SCHEMA;
    }

    private static ExtensionSchema loadSchemaForFullRuntime() {
        try {
            return (ExtensionSchema) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
