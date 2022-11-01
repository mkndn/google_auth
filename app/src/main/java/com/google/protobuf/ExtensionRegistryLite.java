package com.google.protobuf;

import androidx.core.internal.view.SupportMenu;
import com.google.protobuf.GeneratedMessageLite;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ExtensionRegistryLite {
    private static volatile ExtensionRegistryLite emptyRegistry;
    private static volatile ExtensionRegistryLite generatedRegistry;
    private final Map extensionsByNumber;
    private static volatile boolean eagerlyParseMessageSets = false;
    private static boolean doFullRuntimeInheritanceCheck = true;
    static final ExtensionRegistryLite EMPTY_REGISTRY_LITE = new ExtensionRegistryLite(true);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ObjectIntPair {
        private final int number;
        private final Object object;

        ObjectIntPair(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public boolean equals(Object obj) {
            if (obj instanceof ObjectIntPair) {
                ObjectIntPair objectIntPair = (ObjectIntPair) obj;
                return this.object == objectIntPair.object && this.number == objectIntPair.number;
            }
            return false;
        }

        public int hashCode() {
            return (System.identityHashCode(this.object) * SupportMenu.USER_MASK) + this.number;
        }
    }

    ExtensionRegistryLite() {
        this.extensionsByNumber = new HashMap();
    }

    public static ExtensionRegistryLite getEmptyRegistry() {
        ExtensionRegistryLite extensionRegistryLite = emptyRegistry;
        if (extensionRegistryLite == null) {
            synchronized (ExtensionRegistryLite.class) {
                extensionRegistryLite = emptyRegistry;
                if (extensionRegistryLite == null) {
                    if (doFullRuntimeInheritanceCheck) {
                        extensionRegistryLite = ExtensionRegistryFactory.createEmpty();
                    } else {
                        extensionRegistryLite = EMPTY_REGISTRY_LITE;
                    }
                    emptyRegistry = extensionRegistryLite;
                }
            }
        }
        return extensionRegistryLite;
    }

    public static ExtensionRegistryLite getGeneratedRegistry() {
        ExtensionRegistryLite loadGeneratedRegistry;
        ExtensionRegistryLite extensionRegistryLite = generatedRegistry;
        if (extensionRegistryLite != null) {
            return extensionRegistryLite;
        }
        synchronized (ExtensionRegistryLite.class) {
            ExtensionRegistryLite extensionRegistryLite2 = generatedRegistry;
            if (extensionRegistryLite2 != null) {
                return extensionRegistryLite2;
            }
            if (doFullRuntimeInheritanceCheck) {
                loadGeneratedRegistry = ExtensionRegistryFactory.loadGeneratedRegistry();
            } else {
                loadGeneratedRegistry = loadGeneratedRegistry();
            }
            generatedRegistry = loadGeneratedRegistry;
            return loadGeneratedRegistry;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ExtensionRegistryLite loadGeneratedRegistry() {
        return GeneratedExtensionRegistryLoader.load(ExtensionRegistryLite.class);
    }

    public GeneratedMessageLite.GeneratedExtension findLiteExtensionByNumber(MessageLite messageLite, int i) {
        return (GeneratedMessageLite.GeneratedExtension) this.extensionsByNumber.get(new ObjectIntPair(messageLite, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExtensionRegistryLite(boolean z) {
        this.extensionsByNumber = Collections.emptyMap();
    }
}
