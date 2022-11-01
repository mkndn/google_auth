package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacKey;
import com.google.crypto.tink.proto.HmacKeyFormat;
import com.google.crypto.tink.proto.HmacParams;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HmacKeyManager extends KeyTypeManager {
    public HmacKeyManager() {
        super(HmacKey.class, new PrimitiveFactory(Mac.class) { // from class: com.google.crypto.tink.mac.HmacKeyManager.1
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat createKeyFormat(int i, int i2, HashType hashType, KeyTemplate.OutputPrefixType outputPrefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat((HmacKeyFormat) HmacKeyFormat.newBuilder().setParams((HmacParams) HmacParams.newBuilder().setHash(hashType).setTagSize(i2).build()).setKeySize(i).build(), outputPrefixType);
    }

    public static void register(boolean z) {
        Registry.registerKeyManager(new HmacKeyManager(), z);
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus() {
        return TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory keyFactory() {
        return new KeyTypeManager.KeyFactory(HmacKeyFormat.class) { // from class: com.google.crypto.tink.mac.HmacKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map keyFormats() {
                HashMap hashMap = new HashMap();
                hashMap.put("HMAC_SHA256_128BITTAG", HmacKeyManager.createKeyFormat(32, 16, HashType.SHA256, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("HMAC_SHA256_128BITTAG_RAW", HmacKeyManager.createKeyFormat(32, 16, HashType.SHA256, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("HMAC_SHA256_256BITTAG", HmacKeyManager.createKeyFormat(32, 32, HashType.SHA256, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("HMAC_SHA256_256BITTAG_RAW", HmacKeyManager.createKeyFormat(32, 32, HashType.SHA256, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("HMAC_SHA512_128BITTAG", HmacKeyManager.createKeyFormat(64, 16, HashType.SHA512, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("HMAC_SHA512_128BITTAG_RAW", HmacKeyManager.createKeyFormat(64, 16, HashType.SHA512, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("HMAC_SHA512_256BITTAG", HmacKeyManager.createKeyFormat(64, 32, HashType.SHA512, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("HMAC_SHA512_256BITTAG_RAW", HmacKeyManager.createKeyFormat(64, 32, HashType.SHA512, KeyTemplate.OutputPrefixType.RAW));
                hashMap.put("HMAC_SHA512_512BITTAG", HmacKeyManager.createKeyFormat(64, 64, HashType.SHA512, KeyTemplate.OutputPrefixType.TINK));
                hashMap.put("HMAC_SHA512_512BITTAG_RAW", HmacKeyManager.createKeyFormat(64, 64, HashType.SHA512, KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }
        };
    }
}
