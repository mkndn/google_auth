package dagger.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Loader {
    private final Memoizer caches = new Memoizer(this) { // from class: dagger.internal.Loader.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dagger.internal.Memoizer
        public Memoizer create(final ClassLoader classLoader) {
            return new Memoizer(this) { // from class: dagger.internal.Loader.1.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // dagger.internal.Memoizer
                public Class create(String str) {
                    try {
                        return classLoader.loadClass(str);
                    } catch (ClassNotFoundException e) {
                        return Void.class;
                    }
                }
            };
        }
    };

    public abstract Binding getAtInjectBinding(String str, String str2, ClassLoader classLoader, boolean z);

    public abstract ModuleAdapter getModuleAdapter(Class cls);

    /* JADX INFO: Access modifiers changed from: protected */
    public Object instantiate(String str, ClassLoader classLoader) {
        try {
            Class loadClass = loadClass(classLoader, str);
            if (loadClass == Void.class) {
                return null;
            }
            return loadClass.newInstance();
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to initialize " + str, e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("Failed to initialize " + str, e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Class loadClass(ClassLoader classLoader, String str) {
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        return (Class) ((Memoizer) this.caches.get(classLoader)).get(str);
    }
}
