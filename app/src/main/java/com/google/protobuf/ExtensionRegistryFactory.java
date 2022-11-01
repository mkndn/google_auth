package com.google.protobuf;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExtensionRegistryFactory {
    static final Class EXTENSION_REGISTRY_CLASS = reflectExtensionRegistry();

    public static ExtensionRegistryLite createEmpty() {
        ExtensionRegistryLite invokeSubclassFactory = invokeSubclassFactory("getEmptyRegistry");
        return invokeSubclassFactory != null ? invokeSubclassFactory : ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
    }

    private static final ExtensionRegistryLite invokeSubclassFactory(String str) {
        Class cls = EXTENSION_REGISTRY_CLASS;
        if (cls == null) {
            return null;
        }
        try {
            return (ExtensionRegistryLite) cls.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ExtensionRegistryLite loadGeneratedRegistry() {
        ExtensionRegistryLite invokeSubclassFactory = invokeSubclassFactory("loadGeneratedRegistry");
        if (invokeSubclassFactory != null) {
            return invokeSubclassFactory;
        }
        ExtensionRegistryLite loadGeneratedRegistry = ExtensionRegistryLite.loadGeneratedRegistry();
        return loadGeneratedRegistry != null ? loadGeneratedRegistry : createEmpty();
    }

    static Class reflectExtensionRegistry() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
