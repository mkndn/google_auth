package dagger.internal;

import dagger.Lazy;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InstanceFactory implements Factory, Lazy {
    private static final InstanceFactory NULL_INSTANCE_FACTORY = new InstanceFactory(null);
    private final Object instance;

    private InstanceFactory(Object obj) {
        this.instance = obj;
    }

    public static Factory create(Object obj) {
        return new InstanceFactory(Preconditions.checkNotNull(obj, "instance cannot be null"));
    }

    @Override // javax.inject.Provider
    public Object get() {
        return this.instance;
    }
}
