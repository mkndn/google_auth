package com.google.crypto.tink;

import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.protobuf.MessageLite;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Registry {
    private static final Logger logger = Logger.getLogger(Registry.class.getName());
    private static final AtomicReference keyManagerRegistry = new AtomicReference(new KeyManagerRegistry());
    private static final ConcurrentMap keyDeriverMap = new ConcurrentHashMap();
    private static final ConcurrentMap newKeyAllowedMap = new ConcurrentHashMap();
    private static final ConcurrentMap catalogueMap = new ConcurrentHashMap();
    private static final ConcurrentMap primitiveWrapperMap = new ConcurrentHashMap();
    private static final ConcurrentMap keyTemplateMap = new ConcurrentHashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    interface KeyDeriverContainer {
    }

    private Registry() {
    }

    private static KeyDeriverContainer createDeriverFor(final KeyTypeManager keyTypeManager) {
        return new KeyDeriverContainer() { // from class: com.google.crypto.tink.Registry.1
        };
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0044, code lost:
        if (((com.google.crypto.tink.KeyManagerRegistry) com.google.crypto.tink.Registry.keyManagerRegistry.get()).typeUrlExists(r3) == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0046, code lost:
        r4 = r4.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0052, code lost:
        if (r4.hasNext() == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0054, code lost:
        r5 = (java.util.Map.Entry) r4.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        if (com.google.crypto.tink.Registry.keyTemplateMap.containsKey(r5.getKey()) == false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008f, code lost:
        throw new java.security.GeneralSecurityException("Attempted to register a new key template " + ((java.lang.String) r5.getKey()) + " from an existing key manager of type " + r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0090, code lost:
        r3 = r4.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009c, code lost:
        if (r3.hasNext() == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x009e, code lost:
        r4 = (java.util.Map.Entry) r3.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00ae, code lost:
        if (com.google.crypto.tink.Registry.keyTemplateMap.containsKey(r4.getKey()) != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00cf, code lost:
        throw new java.security.GeneralSecurityException("Attempted overwrite of a registered key template " + ((java.lang.String) r4.getKey()));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static synchronized void ensureKeyManagerInsertable(String str, Map map, boolean z) {
        synchronized (Registry.class) {
            if (z) {
                try {
                    ConcurrentMap concurrentMap = newKeyAllowedMap;
                    if (concurrentMap.containsKey(str) && !((Boolean) concurrentMap.get(str)).booleanValue()) {
                        throw new GeneralSecurityException("New keys are already disallowed for key type " + str);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public static synchronized void registerKeyManager(KeyTypeManager keyTypeManager, boolean z) {
        synchronized (Registry.class) {
            if (keyTypeManager == null) {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
            AtomicReference atomicReference = keyManagerRegistry;
            KeyManagerRegistry keyManagerRegistry2 = new KeyManagerRegistry((KeyManagerRegistry) atomicReference.get());
            keyManagerRegistry2.registerKeyManager(keyTypeManager);
            String keyType = keyTypeManager.getKeyType();
            ensureKeyManagerInsertable(keyType, z ? keyTypeManager.keyFactory().keyFormats() : Collections.emptyMap(), z);
            if (!((KeyManagerRegistry) atomicReference.get()).typeUrlExists(keyType)) {
                keyDeriverMap.put(keyType, createDeriverFor(keyTypeManager));
                if (z) {
                    registerKeyTemplates(keyType, keyTypeManager.keyFactory().keyFormats());
                }
            }
            newKeyAllowedMap.put(keyType, Boolean.valueOf(z));
            atomicReference.set(keyManagerRegistry2);
        }
    }

    private static void registerKeyTemplates(String str, Map map) {
        for (Map.Entry entry : map.entrySet()) {
            keyTemplateMap.put((String) entry.getKey(), KeyTemplate.create(str, ((MessageLite) ((KeyTypeManager.KeyFactory.KeyFormat) entry.getValue()).keyFormat).toByteArray(), ((KeyTypeManager.KeyFactory.KeyFormat) entry.getValue()).outputPrefixType));
        }
    }

    public static synchronized void registerPrimitiveWrapper(PrimitiveWrapper primitiveWrapper) {
        synchronized (Registry.class) {
            if (primitiveWrapper == null) {
                throw new IllegalArgumentException("wrapper must be non-null");
            }
            Class primitiveClass = primitiveWrapper.getPrimitiveClass();
            ConcurrentMap concurrentMap = primitiveWrapperMap;
            if (concurrentMap.containsKey(primitiveClass)) {
                PrimitiveWrapper primitiveWrapper2 = (PrimitiveWrapper) concurrentMap.get(primitiveClass);
                if (!primitiveWrapper.getClass().getName().equals(primitiveWrapper2.getClass().getName())) {
                    logger.logp(Level.WARNING, "com.google.crypto.tink.Registry", "registerPrimitiveWrapper", "Attempted overwrite of a registered PrimitiveWrapper for type " + String.valueOf(primitiveClass));
                    throw new GeneralSecurityException(String.format("PrimitiveWrapper for primitive (%s) is already registered to be %s, cannot be re-registered with %s", primitiveClass.getName(), primitiveWrapper2.getClass().getName(), primitiveWrapper.getClass().getName()));
                }
            }
            concurrentMap.put(primitiveClass, primitiveWrapper);
        }
    }
}
