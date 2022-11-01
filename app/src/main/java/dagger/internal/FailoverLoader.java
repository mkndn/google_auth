package dagger.internal;

import dagger.internal.loaders.ReflectiveAtInjectBinding;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FailoverLoader extends Loader {
    private final Memoizer loadedAdapters = new Memoizer() { // from class: dagger.internal.FailoverLoader.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dagger.internal.Memoizer
        public ModuleAdapter create(Class cls) {
            ModuleAdapter moduleAdapter = (ModuleAdapter) FailoverLoader.this.instantiate(cls.getName().concat("$$ModuleAdapter"), cls.getClassLoader());
            if (moduleAdapter == null) {
                throw new IllegalStateException("Module adapter for " + String.valueOf(cls) + " could not be loaded. Please ensure that code generation was run for this module.");
            }
            return moduleAdapter;
        }
    };
    private final Memoizer atInjectBindings = new Memoizer() { // from class: dagger.internal.FailoverLoader.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dagger.internal.Memoizer
        public AtInjectBindingInfo create(AtInjectBindingKey atInjectBindingKey) {
            return FailoverLoader.this.getAtInjectBindingInfo(atInjectBindingKey.classLoader, atInjectBindingKey.className);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AtInjectBindingInfo {
        private final Constructor adapterConstructor;
        private final ReflectiveAtInjectBinding.Factory reflectiveBindingFactory;

        AtInjectBindingInfo(Constructor constructor, ReflectiveAtInjectBinding.Factory factory) {
            this.adapterConstructor = constructor;
            this.reflectiveBindingFactory = factory;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AtInjectBindingKey {
        private final ClassLoader classLoader;
        private final String className;

        AtInjectBindingKey(ClassLoader classLoader, String str) {
            this.classLoader = classLoader;
            this.className = str;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof AtInjectBindingKey) {
                AtInjectBindingKey atInjectBindingKey = (AtInjectBindingKey) obj;
                return this.classLoader == atInjectBindingKey.classLoader && this.className.equals(atInjectBindingKey.className);
            }
            return false;
        }

        public int hashCode() {
            return this.className.hashCode();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AtInjectBindingInfo getAtInjectBindingInfo(ClassLoader classLoader, String str) {
        Class loadClass = loadClass(classLoader, str.concat("$$InjectAdapter"));
        if (!loadClass.equals(Void.class)) {
            try {
                return new AtInjectBindingInfo(loadClass.getConstructor(new Class[0]), null);
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("Couldn't find default constructor in the generated inject adapter for class " + str);
            }
        }
        Class loadClass2 = loadClass(classLoader, str);
        if (loadClass2.equals(Void.class)) {
            throw new IllegalStateException("Could not load class " + str);
        }
        if (loadClass2.isInterface()) {
            return new AtInjectBindingInfo(null, null);
        }
        return new AtInjectBindingInfo(null, ReflectiveAtInjectBinding.createFactory(loadClass2));
    }

    @Override // dagger.internal.Loader
    public Binding getAtInjectBinding(String str, String str2, ClassLoader classLoader, boolean z) {
        AtInjectBindingInfo atInjectBindingInfo = (AtInjectBindingInfo) this.atInjectBindings.get(new AtInjectBindingKey(classLoader, str2));
        if (atInjectBindingInfo.adapterConstructor != null) {
            try {
                return (Binding) atInjectBindingInfo.adapterConstructor.newInstance(new Object[0]);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not create an instance of the inject adapter for class " + str2, e);
            } catch (IllegalArgumentException e2) {
                throw new IllegalStateException("Could not create an instance of the inject adapter for class " + str2, e2);
            } catch (InstantiationException e3) {
                throw new IllegalStateException("Could not create an instance of the inject adapter for class " + str2, e3);
            } catch (InvocationTargetException e4) {
                throw new IllegalStateException("Could not create an instance of the inject adapter for class " + str2, e4);
            }
        } else if (atInjectBindingInfo.reflectiveBindingFactory == null) {
            return null;
        } else {
            return atInjectBindingInfo.reflectiveBindingFactory.create(z);
        }
    }

    @Override // dagger.internal.Loader
    public ModuleAdapter getModuleAdapter(Class cls) {
        return (ModuleAdapter) this.loadedAdapters.get(cls);
    }
}
