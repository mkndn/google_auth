package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesGcmSivKey;
import com.google.crypto.tink.proto.AesGcmSivKeyFormat;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AesGcmSivKeyManager extends KeyTypeManager {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AesGcmSivKeyManager() {
        super(AesGcmSivKey.class, new PrimitiveFactory(Aead.class) { // from class: com.google.crypto.tink.aead.AesGcmSivKeyManager.1
        });
    }

    private static boolean canUseAesGcmSive() {
        try {
            Cipher.getInstance("AES/GCM-SIV/NoPadding");
            return true;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat createKeyFormat(int i, KeyTemplate.OutputPrefixType outputPrefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat((AesGcmSivKeyFormat) AesGcmSivKeyFormat.newBuilder().setKeySize(i).build(), outputPrefixType);
    }

    public static void register(boolean z) {
        if (canUseAesGcmSive()) {
            Registry.registerKeyManager(new AesGcmSivKeyManager(), z);
        }
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesGcmSivKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory keyFactory() {
        return new KeyTypeManager.KeyFactory(AesGcmSivKeyFormat.class) { // from class: com.google.crypto.tink.aead.AesGcmSivKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map keyFormats() {
                HashMap hashMap = new HashMap();
                hashMap.put("AES128_GCM_SIV", AesGcmSivKeyManager.createKeyFormat(16, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("AES128_GCM_SIV_RAW", AesGcmSivKeyManager.createKeyFormat(16, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("AES256_GCM_SIV", AesGcmSivKeyManager.createKeyFormat(32, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("AES256_GCM_SIV_RAW", AesGcmSivKeyManager.createKeyFormat(32, KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }
}
