package com.google.crypto.tink.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrimitiveFactory {
    private final Class clazz;

    public PrimitiveFactory(Class cls) {
        this.clazz = cls;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Class getPrimitiveClass() {
        return this.clazz;
    }
}
