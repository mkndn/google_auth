package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesGcmKey;
import com.google.crypto.tink.proto.AesGcmKeyFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AesGcmKeyManager extends KeyTypeManager {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AesGcmKeyManager() {
        super(AesGcmKey.class, new PrimitiveFactory(Aead.class) { // from class: com.google.crypto.tink.aead.AesGcmKeyManager.1
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat createKeyFormat(int i, KeyTemplate.OutputPrefixType outputPrefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat((AesGcmKeyFormat) AesGcmKeyFormat.newBuilder().setKeySize(i).build(), outputPrefixType);
    }

    public static void register(boolean z) {
        Registry.registerKeyManager(new AesGcmKeyManager(), z);
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus() {
        return TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory keyFactory() {
        return new KeyTypeManager.KeyFactory(AesGcmKeyFormat.class) { // from class: com.google.crypto.tink.aead.AesGcmKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map keyFormats() {
                HashMap hashMap = new HashMap();
                hashMap.put("AES128_GCM", AesGcmKeyManager.createKeyFormat(16, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("AES128_GCM_RAW", AesGcmKeyManager.createKeyFormat(16, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("AES256_GCM", AesGcmKeyManager.createKeyFormat(32, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("AES256_GCM_RAW", AesGcmKeyManager.createKeyFormat(32, KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }
}
