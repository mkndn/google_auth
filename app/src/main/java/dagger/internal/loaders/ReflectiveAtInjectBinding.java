package dagger.internal.loaders;

import dagger.internal.Binding;
import dagger.internal.Keys;
import dagger.internal.Linker;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReflectiveAtInjectBinding extends Binding {
    private final Constructor constructor;
    private final Binding[] fieldBindings;
    private final Field[] fields;
    private final String[] keys;
    private final ClassLoader loader;
    private final Binding[] parameterBindings;
    private final Class supertype;
    private Binding supertypeBinding;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Factory {
        private final Constructor constructor;
        private final Field[] fields;
        private final String[] keys;
        private final String membersKey;
        private final int parameterCount;
        private final String provideKey;
        private final boolean singleton;
        private final Class supertype;
        private final Class type;

        private Factory(String str, String str2, boolean z, Class cls, Field[] fieldArr, Constructor constructor, int i, Class cls2, String[] strArr) {
            this.provideKey = str;
            this.membersKey = str2;
            this.singleton = z;
            this.type = cls;
            this.fields = fieldArr;
            this.constructor = constructor;
            this.parameterCount = i;
            this.supertype = cls2;
            this.keys = strArr;
        }

        public ReflectiveAtInjectBinding create(boolean z) {
            if (z && this.constructor == null && this.fields.length == 0) {
                throw new Binding.InvalidBindingException(this.type.getName(), "has no injectable members. Do you want to add an injectable constructor?");
            }
            return new ReflectiveAtInjectBinding(this.provideKey, this.membersKey, this.singleton, this.type, this.fields, this.constructor, this.parameterCount, this.supertype, this.keys);
        }
    }

    private ReflectiveAtInjectBinding(String str, String str2, boolean z, Class cls, Field[] fieldArr, Constructor constructor, int i, Class cls2, String[] strArr) {
        super(str, str2, z, cls);
        this.constructor = constructor;
        this.fields = fieldArr;
        this.supertype = cls2;
        this.keys = strArr;
        this.parameterBindings = new Binding[i];
        this.fieldBindings = new Binding[fieldArr.length];
        this.loader = cls.getClassLoader();
    }

    public static Factory createFactory(Class cls) {
        Constructor[] constructorsForType;
        String str;
        int i;
        Class cls2;
        boolean isAnnotationPresent = cls.isAnnotationPresent(Singleton.class);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Class cls3 = cls;
        while (true) {
            int i2 = 0;
            if (cls3 != Object.class) {
                Field[] declaredFields = cls3.getDeclaredFields();
                int length = declaredFields.length;
                while (i2 < length) {
                    Field field = declaredFields[i2];
                    if (field.isAnnotationPresent(Inject.class) && !Modifier.isStatic(field.getModifiers())) {
                        if ((field.getModifiers() & 2) == 0) {
                            field.setAccessible(true);
                            arrayList2.add(field);
                            arrayList.add(Keys.get(field.getGenericType(), field.getAnnotations(), field));
                        } else {
                            throw new IllegalStateException("Can't inject private field: " + String.valueOf(field));
                        }
                    }
                    i2++;
                }
                cls3 = cls3.getSuperclass();
            } else {
                Constructor constructor = null;
                for (Constructor constructor2 : getConstructorsForType(cls)) {
                    if (constructor2.isAnnotationPresent(Inject.class)) {
                        if (constructor == null) {
                            constructor = constructor2;
                        } else {
                            throw new Binding.InvalidBindingException(cls.getName(), "has too many injectable constructors");
                        }
                    }
                }
                if (constructor == null && !arrayList2.isEmpty()) {
                    try {
                        constructor = cls.getDeclaredConstructor(new Class[0]);
                    } catch (NoSuchMethodException e) {
                    }
                }
                if (constructor != null) {
                    if ((constructor.getModifiers() & 2) != 0) {
                        throw new IllegalStateException("Can't inject private constructor: " + String.valueOf(constructor));
                    }
                    str = Keys.get(cls);
                    constructor.setAccessible(true);
                    Type[] genericParameterTypes = constructor.getGenericParameterTypes();
                    int length2 = genericParameterTypes.length;
                    if (length2 != 0) {
                        Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
                        while (i2 < genericParameterTypes.length) {
                            arrayList.add(Keys.get(genericParameterTypes[i2], parameterAnnotations[i2], constructor));
                            i2++;
                        }
                    }
                    i = length2;
                } else if (isAnnotationPresent) {
                    throw new IllegalArgumentException("No injectable constructor on @Singleton " + cls.getName());
                } else {
                    str = null;
                    i = 0;
                }
                Class superclass = cls.getSuperclass();
                if (superclass != null) {
                    if (!Keys.isPlatformType(superclass.getName())) {
                        arrayList.add(Keys.getMembersKey(superclass));
                    } else {
                        cls2 = null;
                        return new Factory(str, Keys.getMembersKey(cls), isAnnotationPresent, cls, (Field[]) arrayList2.toArray(new Field[arrayList2.size()]), constructor, i, cls2, (String[]) arrayList.toArray(new String[arrayList.size()]));
                    }
                }
                cls2 = superclass;
                return new Factory(str, Keys.getMembersKey(cls), isAnnotationPresent, cls, (Field[]) arrayList2.toArray(new Field[arrayList2.size()]), constructor, i, cls2, (String[]) arrayList.toArray(new String[arrayList.size()]));
            }
        }
    }

    private static Constructor[] getConstructorsForType(Class cls) {
        return cls.getDeclaredConstructors();
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public Object get() {
        if (this.constructor == null) {
            throw new UnsupportedOperationException();
        }
        Object[] objArr = new Object[this.parameterBindings.length];
        int i = 0;
        while (true) {
            Binding[] bindingArr = this.parameterBindings;
            if (i < bindingArr.length) {
                objArr[i] = bindingArr[i].get();
                i++;
            } else {
                try {
                    Object newInstance = this.constructor.newInstance(objArr);
                    injectMembers(newInstance);
                    return newInstance;
                } catch (IllegalAccessException e) {
                    throw new AssertionError(e);
                } catch (InstantiationException e2) {
                    throw new RuntimeException(e2);
                } catch (InvocationTargetException e3) {
                    Throwable cause = e3.getCause();
                    if (cause instanceof RuntimeException) {
                        throw ((RuntimeException) cause);
                    }
                    throw new RuntimeException(cause);
                }
            }
        }
    }

    @Override // dagger.internal.Binding
    public void getDependencies(Set set, Set set2) {
        Binding[] bindingArr = this.parameterBindings;
        if (bindingArr != null) {
            Collections.addAll(set, bindingArr);
        }
        Collections.addAll(set2, this.fieldBindings);
        Binding binding = this.supertypeBinding;
        if (binding != null) {
            set2.add(binding);
        }
    }

    @Override // dagger.internal.Binding
    public void injectMembers(Object obj) {
        int i = 0;
        while (true) {
            try {
                Field[] fieldArr = this.fields;
                if (i >= fieldArr.length) {
                    break;
                }
                fieldArr[i].set(obj, this.fieldBindings[i].get());
                i++;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
        Binding binding = this.supertypeBinding;
        if (binding != null) {
            binding.injectMembers(obj);
        }
    }

    @Override // dagger.internal.Binding
    public String toString() {
        return this.provideKey != null ? this.provideKey : this.membersKey;
    }

    @Override // dagger.internal.Binding
    public void attach(Linker linker) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            Field[] fieldArr = this.fields;
            if (i2 >= fieldArr.length) {
                break;
            }
            Binding[] bindingArr = this.fieldBindings;
            if (bindingArr[i2] == null) {
                bindingArr[i2] = linker.requestBinding(this.keys[i3], fieldArr[i2], this.loader);
            }
            i3++;
            i2++;
        }
        if (this.constructor != null) {
            while (true) {
                Binding[] bindingArr2 = this.parameterBindings;
                if (i >= bindingArr2.length) {
                    break;
                }
                if (bindingArr2[i] == null) {
                    bindingArr2[i] = linker.requestBinding(this.keys[i3], this.constructor, this.loader);
                }
                i3++;
                i++;
            }
        }
        if (this.supertype != null && this.supertypeBinding == null) {
            this.supertypeBinding = linker.requestBinding(this.keys[i3], this.membersKey, this.loader, false, true);
        }
    }
}
