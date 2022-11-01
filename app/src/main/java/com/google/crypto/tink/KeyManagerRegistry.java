package com.google.crypto.tink;

import com.google.crypto.tink.internal.KeyTypeManager;
import java.security.GeneralSecurityException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
final class KeyManagerRegistry {
    private static final Logger logger = Logger.getLogger(KeyManagerRegistry.class.getName());
    private final ConcurrentMap keyManagerMap;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface KeyManagerContainer {
        Class getImplementingClass();

        KeyManager getUntypedKeyManager();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyManagerRegistry() {
        this.keyManagerMap = new ConcurrentHashMap();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x006a, code lost:
        r7.keyManagerMap.putIfAbsent(r0, r8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private synchronized void registerKeyManagerContainer(KeyManagerContainer keyManagerContainer, boolean z) {
        String keyType = keyManagerContainer.getUntypedKeyManager().getKeyType();
        KeyManagerContainer keyManagerContainer2 = (KeyManagerContainer) this.keyManagerMap.get(keyType);
        if (keyManagerContainer2 != null && !keyManagerContainer2.getImplementingClass().equals(keyManagerContainer.getImplementingClass())) {
            logger.logp(Level.WARNING, "com.google.crypto.tink.KeyManagerRegistry", "registerKeyManagerContainer", "Attempted overwrite of a registered key manager for key type " + keyType);
            throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", keyType, keyManagerContainer2.getImplementingClass().getName(), keyManagerContainer.getImplementingClass().getName()));
        }
        this.keyManagerMap.put(keyType, keyManagerContainer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void registerKeyManager(KeyTypeManager keyTypeManager) {
        if (!keyTypeManager.fipsStatus().isCompatible()) {
            throw new GeneralSecurityException("failed to register key manager " + String.valueOf(keyTypeManager.getClass()) + " as it is not FIPS compatible.");
        }
        registerKeyManagerContainer(createContainerFor(keyTypeManager), false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean typeUrlExists(String str) {
        return this.keyManagerMap.containsKey(str);
    }

    private static KeyManagerContainer createContainerFor(final KeyTypeManager keyTypeManager) {
        return new KeyManagerContainer() { // from class: com.google.crypto.tink.KeyManagerRegistry.2
            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Class getImplementingClass() {
                return KeyTypeManager.this.getClass();
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public KeyManager getUntypedKeyManager() {
                KeyTypeManager keyTypeManager2 = KeyTypeManager.this;
                return new KeyManagerImpl(keyTypeManager2, keyTypeManager2.firstSupportedPrimitiveClass());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyManagerRegistry(KeyManagerRegistry keyManagerRegistry) {
        this.keyManagerMap = new ConcurrentHashMap(keyManagerRegistry.keyManagerMap);
    }
}
