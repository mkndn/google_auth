package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesEaxKey;
import com.google.crypto.tink.proto.AesEaxKeyFormat;
import com.google.crypto.tink.proto.AesEaxParams;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AesEaxKeyManager extends KeyTypeManager {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AesEaxKeyManager() {
        super(AesEaxKey.class, new PrimitiveFactory(Aead.class) { // from class: com.google.crypto.tink.aead.AesEaxKeyManager.1
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat createKeyFormat(int i, int i2, KeyTemplate.OutputPrefixType outputPrefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat((AesEaxKeyFormat) AesEaxKeyFormat.newBuilder().setKeySize(i).setParams((AesEaxParams) AesEaxParams.newBuilder().setIvSize(i2).build()).build(), outputPrefixType);
    }

    public static void register(boolean z) {
        Registry.registerKeyManager(new AesEaxKeyManager(), z);
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesEaxKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory keyFactory() {
        return new KeyTypeManager.KeyFactory(AesEaxKeyFormat.class) { // from class: com.google.crypto.tink.aead.AesEaxKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map keyFormats() {
                HashMap hashMap = new HashMap();
                hashMap.put("AES128_EAX", AesEaxKeyManager.createKeyFormat(16, 16, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("AES128_EAX_RAW", AesEaxKeyManager.createKeyFormat(16, 16, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("AES256_EAX", AesEaxKeyManager.createKeyFormat(32, 16, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("AES256_EAX_RAW", AesEaxKeyManager.createKeyFormat(32, 16, KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }
}
