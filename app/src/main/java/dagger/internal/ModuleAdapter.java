package dagger.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ModuleAdapter {
    public final boolean complete;
    public final Class[] includes;
    public final String[] injectableTypes;
    public final boolean library;
    public final Class moduleClass;
    public final boolean overrides;
    public final Class[] staticInjections;

    /* JADX INFO: Access modifiers changed from: protected */
    public ModuleAdapter(Class cls, String[] strArr, Class[] clsArr, boolean z, Class[] clsArr2, boolean z2, boolean z3) {
        this.moduleClass = cls;
        this.injectableTypes = strArr;
        this.staticInjections = clsArr;
        this.overrides = z;
        this.includes = clsArr2;
        this.complete = z2;
        this.library = z3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ModuleAdapter) {
            return this.moduleClass.equals(((ModuleAdapter) obj).moduleClass);
        }
        return false;
    }

    public void getBindings(BindingsGroup bindingsGroup, Object obj) {
    }

    public final int hashCode() {
        return this.moduleClass.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object newModule() {
        throw new UnsupportedOperationException("No no-args constructor on " + getClass().getName());
    }
}
