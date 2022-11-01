package com.google.crypto.tink.mac;

import com.google.crypto.tink.Mac;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
class MacWrapper implements PrimitiveWrapper {
    private static final Logger logger = Logger.getLogger(MacWrapper.class.getName());
    private static final byte[] FORMAT_VERSION = {0};

    MacWrapper() {
    }

    public static void register() {
        Registry.registerPrimitiveWrapper(new MacWrapper());
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class getPrimitiveClass() {
        return Mac.class;
    }
}
