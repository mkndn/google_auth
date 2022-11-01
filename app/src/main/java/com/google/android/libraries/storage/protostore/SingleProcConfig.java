package com.google.android.libraries.storage.protostore;

/* compiled from: PG */
/* loaded from: classes.dex */
final class SingleProcConfig extends VariantConfig {
    private static final SingleProcConfig INSTANCE = new SingleProcConfig();

    private SingleProcConfig() {
    }

    public static SingleProcConfig getInstance() {
        return INSTANCE;
    }

    @Override // com.google.android.libraries.storage.protostore.VariantConfig
    public String factoryId() {
        return "singleproc";
    }
}
