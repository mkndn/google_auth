package dagger.internal;

import dagger.MembersInjector;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Binding implements Provider, MembersInjector {
    private static final int CYCLE_FREE = 8;
    private static final int DEPENDED_ON = 16;
    protected static final boolean IS_SINGLETON = true;
    private static final int LIBRARY = 32;
    private static final int LINKED = 2;
    protected static final boolean NOT_SINGLETON = false;
    private static final int SINGLETON = 1;
    public static final Binding UNRESOLVED = new Binding(null, null, false, null) { // from class: dagger.internal.Binding.1
        @Override // dagger.internal.Binding, javax.inject.Provider
        public Object get() {
            throw new AssertionError("Unresolved binding should never be called to inject.");
        }

        @Override // dagger.internal.Binding
        public void injectMembers(Object obj) {
            throw new AssertionError("Unresolved binding should never be called to inject.");
        }
    };
    private static final int VISITING = 4;
    private int bits;
    public final String membersKey;
    public final String provideKey;
    public final Object requiredBy;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class InvalidBindingException extends RuntimeException {
        public final String type;

        public InvalidBindingException(String str, String str2) {
            super(str2);
            this.type = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Binding(String str, String str2, boolean z, Object obj) {
        if (z && str == null) {
            throw new InvalidBindingException(Keys.getClassName(str2), "is exclusively members injected and therefore cannot be scoped");
        }
        this.provideKey = str;
        this.membersKey = str2;
        this.requiredBy = obj;
        this.bits = z ? 1 : 0;
    }

    public void attach(Linker linker) {
    }

    public boolean dependedOn() {
        if ((this.bits & 16) != 0) {
            return IS_SINGLETON;
        }
        return false;
    }

    @Override // javax.inject.Provider
    public Object get() {
        throw new UnsupportedOperationException("No injectable constructor on " + getClass().getName());
    }

    public void getDependencies(Set set, Set set2) {
    }

    public void injectMembers(Object obj) {
    }

    public boolean isCycleFree() {
        if ((this.bits & 8) != 0) {
            return IS_SINGLETON;
        }
        return false;
    }

    public boolean isLinked() {
        if ((this.bits & 2) != 0) {
            return IS_SINGLETON;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSingleton() {
        if ((this.bits & 1) != 0) {
            return IS_SINGLETON;
        }
        return false;
    }

    public boolean isVisiting() {
        if ((this.bits & 4) != 0) {
            return IS_SINGLETON;
        }
        return false;
    }

    public boolean library() {
        if ((this.bits & 32) != 0) {
            return IS_SINGLETON;
        }
        return false;
    }

    public void setCycleFree(boolean z) {
        this.bits = z ? this.bits | 8 : this.bits & (-9);
    }

    public void setDependedOn(boolean z) {
        this.bits = z ? this.bits | 16 : this.bits & (-17);
    }

    public void setLibrary(boolean z) {
        this.bits = z ? this.bits | 32 : this.bits & (-33);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLinked() {
        this.bits |= 2;
    }

    public void setVisiting(boolean z) {
        this.bits = z ? this.bits | 4 : this.bits & (-5);
    }

    public String toString() {
        String simpleName = getClass().getSimpleName();
        String str = this.provideKey;
        return simpleName + "[provideKey=\"" + str + "\", memberskey=\"" + this.membersKey + "\"]";
    }
}
