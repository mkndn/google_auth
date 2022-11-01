package dagger.internal;

import dagger.internal.Binding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Linker {
    private static final Object UNINITIALIZED = new Object();
    private final Linker base;
    private final ErrorHandler errorHandler;
    private final Loader plugin;
    private final Queue toLink = new ArrayQueue();
    private boolean attachSuccess = true;
    private final List errors = new ArrayList();
    private final Map bindings = new HashMap();
    private volatile Map linkedBindings = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class DeferredBinding extends Binding {
        final ClassLoader classLoader;
        final String deferredKey;
        final boolean mustHaveInjections;

        private DeferredBinding(String str, ClassLoader classLoader, Object obj, boolean z) {
            super(null, null, false, obj);
            this.deferredKey = str;
            this.classLoader = classLoader;
            this.mustHaveInjections = z;
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            throw new UnsupportedOperationException("Deferred bindings must resolve first.");
        }

        @Override // dagger.internal.Binding
        public void injectMembers(Object obj) {
            throw new UnsupportedOperationException("Deferred bindings must resolve first.");
        }

        @Override // dagger.internal.Binding
        public String toString() {
            return "DeferredBinding[deferredKey=" + this.deferredKey + "]";
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ErrorHandler {
        public static final ErrorHandler NULL = new ErrorHandler() { // from class: dagger.internal.Linker.ErrorHandler.1
            @Override // dagger.internal.Linker.ErrorHandler
            public void handleErrors(List list) {
            }
        };

        void handleErrors(List list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SingletonBinding extends Binding {
        private final Binding binding;
        private volatile Object onlyInstance;

        private SingletonBinding(Binding binding) {
            super(binding.provideKey, binding.membersKey, true, binding.requiredBy);
            this.onlyInstance = Linker.UNINITIALIZED;
            this.binding = binding;
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.binding.attach(linker);
        }

        @Override // dagger.internal.Binding
        public boolean dependedOn() {
            return this.binding.dependedOn();
        }

        @Override // dagger.internal.Binding, javax.inject.Provider
        public Object get() {
            if (this.onlyInstance == Linker.UNINITIALIZED) {
                synchronized (this) {
                    if (this.onlyInstance == Linker.UNINITIALIZED) {
                        this.onlyInstance = this.binding.get();
                    }
                }
            }
            return this.onlyInstance;
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            this.binding.getDependencies(set, set2);
        }

        @Override // dagger.internal.Binding
        public void injectMembers(Object obj) {
            this.binding.injectMembers(obj);
        }

        @Override // dagger.internal.Binding
        public boolean isCycleFree() {
            return this.binding.isCycleFree();
        }

        @Override // dagger.internal.Binding
        public boolean isLinked() {
            return this.binding.isLinked();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dagger.internal.Binding
        public boolean isSingleton() {
            return true;
        }

        @Override // dagger.internal.Binding
        public boolean isVisiting() {
            return this.binding.isVisiting();
        }

        @Override // dagger.internal.Binding
        public boolean library() {
            return this.binding.library();
        }

        @Override // dagger.internal.Binding
        public void setCycleFree(boolean z) {
            this.binding.setCycleFree(z);
        }

        @Override // dagger.internal.Binding
        public void setDependedOn(boolean z) {
            this.binding.setDependedOn(z);
        }

        @Override // dagger.internal.Binding
        public void setLibrary(boolean z) {
            this.binding.setLibrary(true);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dagger.internal.Binding
        public void setLinked() {
            this.binding.setLinked();
        }

        @Override // dagger.internal.Binding
        public void setVisiting(boolean z) {
            this.binding.setVisiting(z);
        }

        @Override // dagger.internal.Binding
        public String toString() {
            return "@Singleton/" + this.binding.toString();
        }
    }

    public Linker(Linker linker, Loader loader, ErrorHandler errorHandler) {
        if (loader == null) {
            throw new NullPointerException("plugin");
        }
        if (errorHandler == null) {
            throw new NullPointerException("errorHandler");
        }
        this.base = linker;
        this.plugin = loader;
        this.errorHandler = errorHandler;
    }

    private void addError(String str) {
        this.errors.add(str);
    }

    private void assertLockHeld() {
        if (!Thread.holdsLock(this)) {
            throw new AssertionError();
        }
    }

    private Binding createBinding(String str, Object obj, ClassLoader classLoader, boolean z) {
        String builtInBindingsKey = Keys.getBuiltInBindingsKey(str);
        if (builtInBindingsKey != null) {
            return new BuiltInBinding(str, obj, classLoader, builtInBindingsKey);
        }
        String lazyKey = Keys.getLazyKey(str);
        if (lazyKey != null) {
            return new LazyBinding(str, obj, classLoader, lazyKey);
        }
        String className = Keys.getClassName(str);
        if (className == null) {
            throw new Binding.InvalidBindingException(str, "is a generic class or an array and can only be bound with concrete type parameter(s) in a @Provides method.");
        }
        if (Keys.isAnnotated(str)) {
            throw new Binding.InvalidBindingException(str, "is a @Qualifier-annotated type and must be bound by a @Provides method.");
        }
        Binding atInjectBinding = this.plugin.getAtInjectBinding(str, className, classLoader, z);
        if (atInjectBinding != null) {
            return atInjectBinding;
        }
        throw new Binding.InvalidBindingException(className, "could not be bound with key " + str);
    }

    private void putBinding(Binding binding) {
        if (binding.provideKey != null) {
            putIfAbsent(this.bindings, binding.provideKey, binding);
        }
        if (binding.membersKey != null) {
            putIfAbsent(this.bindings, binding.membersKey, binding);
        }
    }

    private void putIfAbsent(Map map, Object obj, Object obj2) {
        Object put = map.put(obj, obj2);
        if (put != null) {
            map.put(obj, put);
        }
    }

    static Binding scope(Binding binding) {
        if (binding.isSingleton() && !(binding instanceof SingletonBinding)) {
            return new SingletonBinding(binding);
        }
        return binding;
    }

    public void installBindings(BindingsGroup bindingsGroup) {
        if (this.linkedBindings != null) {
            throw new IllegalStateException("Cannot install further bindings after calling linkAll().");
        }
        for (Map.Entry entry : bindingsGroup.entrySet()) {
            this.bindings.put(entry.getKey(), scope((Binding) entry.getValue()));
        }
    }

    public void linkRequested() {
        assertLockHeld();
        while (true) {
            Binding binding = (Binding) this.toLink.poll();
            if (binding != null) {
                if (binding instanceof DeferredBinding) {
                    DeferredBinding deferredBinding = (DeferredBinding) binding;
                    String str = deferredBinding.deferredKey;
                    boolean z = deferredBinding.mustHaveInjections;
                    if (this.bindings.containsKey(str)) {
                        continue;
                    } else {
                        try {
                            Binding createBinding = createBinding(str, binding.requiredBy, deferredBinding.classLoader, z);
                            createBinding.setLibrary(binding.library());
                            createBinding.setDependedOn(binding.dependedOn());
                            if (!str.equals(createBinding.provideKey) && !str.equals(createBinding.membersKey)) {
                                throw new IllegalStateException("Unable to create binding for " + str);
                                break;
                            }
                            Binding scope = scope(createBinding);
                            this.toLink.add(scope);
                            putBinding(scope);
                        } catch (Binding.InvalidBindingException e) {
                            String str2 = e.type;
                            String message = e.getMessage();
                            addError(str2 + " " + message + " required by " + String.valueOf(binding.requiredBy));
                            this.bindings.put(str, Binding.UNRESOLVED);
                        } catch (IllegalArgumentException e2) {
                            String message2 = e2.getMessage();
                            addError(message2 + " required by " + String.valueOf(binding.requiredBy));
                            this.bindings.put(str, Binding.UNRESOLVED);
                        } catch (UnsupportedOperationException e3) {
                            String message3 = e3.getMessage();
                            addError("Unsupported: " + message3 + " required by " + String.valueOf(binding.requiredBy));
                            this.bindings.put(str, Binding.UNRESOLVED);
                        } catch (RuntimeException e4) {
                            throw e4;
                        } catch (Exception e5) {
                            throw new RuntimeException(e5);
                        }
                    }
                } else {
                    this.attachSuccess = true;
                    binding.attach(this);
                    if (this.attachSuccess) {
                        binding.setLinked();
                    } else {
                        this.toLink.add(binding);
                    }
                }
            } else {
                try {
                    this.errorHandler.handleErrors(this.errors);
                    return;
                } finally {
                    this.errors.clear();
                }
            }
        }
    }

    public Binding requestBinding(String str, Object obj, ClassLoader classLoader) {
        return requestBinding(str, obj, classLoader, true, true);
    }

    public Binding requestBinding(String str, Object obj, ClassLoader classLoader, boolean z, boolean z2) {
        assertLockHeld();
        Linker linker = this;
        Binding binding = null;
        while (true) {
            if (linker == null) {
                break;
            }
            binding = (Binding) linker.bindings.get(str);
            if (binding == null) {
                linker = linker.base;
            } else if (linker != this && !binding.isLinked()) {
                throw new AssertionError();
            }
        }
        if (binding == null) {
            DeferredBinding deferredBinding = new DeferredBinding(str, classLoader, obj, z);
            deferredBinding.setLibrary(z2);
            deferredBinding.setDependedOn(true);
            this.toLink.add(deferredBinding);
            this.attachSuccess = false;
            return null;
        }
        if (!binding.isLinked()) {
            this.toLink.add(binding);
        }
        binding.setLibrary(z2);
        binding.setDependedOn(true);
        return binding;
    }
}
