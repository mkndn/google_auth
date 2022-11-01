package dagger.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class BuiltInBinding extends Binding {
    private final ClassLoader classLoader;
    private Binding delegate;
    private final String delegateKey;

    public BuiltInBinding(String str, Object obj, ClassLoader classLoader, String str2) {
        super(str, null, false, obj);
        this.classLoader = classLoader;
        this.delegateKey = str2;
    }

    @Override // dagger.internal.Binding
    public void attach(Linker linker) {
        this.delegate = linker.requestBinding(this.delegateKey, this.requiredBy, this.classLoader);
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public Object get() {
        return this.delegate;
    }

    @Override // dagger.internal.Binding
    public void injectMembers(Object obj) {
        throw new UnsupportedOperationException();
    }
}
