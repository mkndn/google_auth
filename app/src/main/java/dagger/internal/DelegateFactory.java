package dagger.internal;

import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DelegateFactory implements Factory {
    private Provider delegate;

    public static void setDelegate(Provider provider, Provider provider2) {
        Preconditions.checkNotNull(provider2);
        DelegateFactory delegateFactory = (DelegateFactory) provider;
        if (delegateFactory.delegate != null) {
            throw new IllegalStateException();
        }
        delegateFactory.delegate = provider2;
    }

    @Override // javax.inject.Provider
    public Object get() {
        Provider provider = this.delegate;
        if (provider == null) {
            throw new IllegalStateException();
        }
        return provider.get();
    }
}
