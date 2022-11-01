package com.google.crypto.tink;

import com.google.crypto.tink.internal.KeyTypeManager;

/* compiled from: PG */
/* loaded from: classes.dex */
class KeyManagerImpl implements KeyManager {
    private final KeyTypeManager keyTypeManager;
    private final Class primitiveClass;

    public KeyManagerImpl(KeyTypeManager keyTypeManager, Class cls) {
        if (!keyTypeManager.supportedPrimitives().contains(cls) && !Void.class.equals(cls)) {
            throw new IllegalArgumentException(String.format("Given internalKeyMananger %s does not support primitive class %s", keyTypeManager.toString(), cls.getName()));
        }
        this.keyTypeManager = keyTypeManager;
        this.primitiveClass = cls;
    }

    @Override // com.google.crypto.tink.KeyManager
    public final String getKeyType() {
        return this.keyTypeManager.getKeyType();
    }
}
