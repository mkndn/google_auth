package dagger.internal;

import dagger.Lazy;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DoubleCheck implements Provider, Lazy {
    static final /* synthetic */ boolean $assertionsDisabled = true;
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider provider;

    private DoubleCheck(Provider provider) {
        if (!$assertionsDisabled && provider == null) {
            throw new AssertionError();
        }
        this.provider = provider;
    }

    public static Lazy lazy(Provider provider) {
        if (provider instanceof Lazy) {
            return (Lazy) provider;
        }
        return new DoubleCheck((Provider) Preconditions.checkNotNull(provider));
    }

    public static Provider provider(Provider provider) {
        Preconditions.checkNotNull(provider);
        if (provider instanceof DoubleCheck) {
            return provider;
        }
        return new DoubleCheck(provider);
    }

    private static Object reentrantCheck(Object obj, Object obj2) {
        if ((obj != UNINITIALIZED) && obj != obj2) {
            throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + obj2 + ". This is likely due to a circular dependency.");
        }
        return obj2;
    }

    @Override // javax.inject.Provider
    public Object get() {
        Object obj = this.instance;
        Object obj2 = UNINITIALIZED;
        if (obj == obj2) {
            synchronized (this) {
                obj = this.instance;
                if (obj == obj2) {
                    obj = this.provider.get();
                    this.instance = reentrantCheck(this.instance, obj);
                    this.provider = null;
                }
            }
        }
        return obj;
    }
}
