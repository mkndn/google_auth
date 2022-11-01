package dagger.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SetBinding extends Binding {
    private final List contributors;
    private final SetBinding parent;

    public SetBinding(SetBinding setBinding) {
        super(setBinding.provideKey, null, false, setBinding.requiredBy);
        this.parent = setBinding;
        setLibrary(setBinding.library());
        setDependedOn(setBinding.dependedOn());
        this.contributors = new ArrayList();
    }

    @Override // dagger.internal.Binding
    public void attach(Linker linker) {
        for (Binding binding : this.contributors) {
            binding.attach(linker);
        }
    }

    @Override // dagger.internal.Binding
    public void getDependencies(Set set, Set set2) {
        for (SetBinding setBinding = this; setBinding != null; setBinding = setBinding.parent) {
            set.addAll(setBinding.contributors);
        }
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public Set get() {
        ArrayList arrayList = new ArrayList();
        for (SetBinding setBinding = this; setBinding != null; setBinding = setBinding.parent) {
            int size = setBinding.contributors.size();
            for (int i = 0; i < size; i++) {
                Binding binding = (Binding) setBinding.contributors.get(i);
                Object obj = binding.get();
                if (binding.provideKey.equals(this.provideKey)) {
                    arrayList.addAll((Set) obj);
                } else {
                    arrayList.add(obj);
                }
            }
        }
        return Collections.unmodifiableSet(new LinkedHashSet(arrayList));
    }

    @Override // dagger.internal.Binding
    public void injectMembers(Set set) {
        throw new UnsupportedOperationException("Cannot inject members on a contributed Set<T>.");
    }

    @Override // dagger.internal.Binding
    public String toString() {
        StringBuilder sb = new StringBuilder("SetBinding[");
        boolean z = true;
        for (SetBinding setBinding = this; setBinding != null; setBinding = setBinding.parent) {
            int size = setBinding.contributors.size();
            int i = 0;
            while (i < size) {
                if (!z) {
                    sb.append(",");
                }
                sb.append(setBinding.contributors.get(i));
                i++;
                z = false;
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
