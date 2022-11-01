package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.KmsAeadKey;
import com.google.crypto.tink.proto.KmsAeadKeyFormat;

/* compiled from: PG */
/* loaded from: classes.dex */
public class KmsAeadKeyManager extends KeyTypeManager {
    /* JADX INFO: Access modifiers changed from: package-private */
    public KmsAeadKeyManager() {
        super(KmsAeadKey.class, new PrimitiveFactory(Aead.class) { // from class: com.google.crypto.tink.aead.KmsAeadKeyManager.1
        });
    }

    public static void register(boolean z) {
        Registry.registerKeyManager(new KmsAeadKeyManager(), z);
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.KmsAeadKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory keyFactory() {
        return new KeyTypeManager.KeyFactory(KmsAeadKeyFormat.class) { // from class: com.google.crypto.tink.aead.KmsAeadKeyManager.2
        };
    }
}
