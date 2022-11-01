package dagger.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ProvidesBinding extends Binding {
    protected final String methodName;
    protected final String moduleClass;

    public ProvidesBinding(String str, boolean z, String str2, String str3) {
        super(str, null, z, new StringBuilder(str2.length() + str3.length() + 3).append(str2).append('.').append(str3).append("()").toString());
        this.moduleClass = str2;
        this.methodName = str3;
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public abstract Object get();

    @Override // dagger.internal.Binding
    public String toString() {
        String name = getClass().getName();
        String str = this.provideKey;
        String str2 = this.moduleClass;
        return name + "[key=" + str + " method=" + str2 + "." + this.methodName + "()]";
    }
}
