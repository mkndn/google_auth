package dagger.internal;

import dagger.Lazy;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class LazyBinding extends Binding {
    private static final Object NOT_PRESENT = new Object();
    private Binding delegate;
    private final String lazyKey;
    private final ClassLoader loader;

    public LazyBinding(String str, Object obj, ClassLoader classLoader, String str2) {
        super(str, null, false, obj);
        this.loader = classLoader;
        this.lazyKey = str2;
    }

    @Override // dagger.internal.Binding
    public void attach(Linker linker) {
        this.delegate = linker.requestBinding(this.lazyKey, this.requiredBy, this.loader);
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public Lazy get() {
        return new Lazy() { // from class: dagger.internal.LazyBinding.1
            private volatile Object cacheValue = LazyBinding.NOT_PRESENT;

            @Override // dagger.Lazy
            public Object get() {
                if (this.cacheValue == LazyBinding.NOT_PRESENT) {
                    synchronized (this) {
                        if (this.cacheValue == LazyBinding.NOT_PRESENT) {
                            this.cacheValue = LazyBinding.this.delegate.get();
                        }
                    }
                }
                return this.cacheValue;
            }
        };
    }

    @Override // dagger.internal.Binding
    public void injectMembers(Lazy lazy) {
        throw new UnsupportedOperationException();
    }
}
