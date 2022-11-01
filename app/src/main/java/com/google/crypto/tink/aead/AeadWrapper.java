package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AeadWrapper implements PrimitiveWrapper {
    private static final Logger logger = Logger.getLogger(AeadWrapper.class.getName());

    AeadWrapper() {
    }

    public static void register() {
        Registry.registerPrimitiveWrapper(new AeadWrapper());
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class getPrimitiveClass() {
        return Aead.class;
    }
}
