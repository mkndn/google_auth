package dagger.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BindingsGroup {
    private final Map bindings = new LinkedHashMap();

    public Binding contributeProvidesBinding(String str, ProvidesBinding providesBinding) {
        return put(str, providesBinding);
    }

    public final Set entrySet() {
        return this.bindings.entrySet();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Binding put(String str, Binding binding) {
        Binding binding2 = (Binding) this.bindings.put(str, binding);
        if (binding2 != null) {
            this.bindings.put(str, binding2);
            String valueOf = String.valueOf(binding2);
            throw new IllegalArgumentException("Duplicate:\n    " + valueOf + "\n    " + String.valueOf(binding));
        }
        return null;
    }

    public String toString() {
        String simpleName = getClass().getSimpleName();
        return simpleName + this.bindings.toString();
    }
}
