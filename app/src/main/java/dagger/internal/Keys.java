package dagger.internal;

import dagger.Lazy;
import dagger.MembersInjector;
import java.lang.annotation.Annotation;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;
import javax.inject.Provider;
import javax.inject.Qualifier;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Keys {
    private static final String PROVIDER_PREFIX = Provider.class.getCanonicalName() + "<";
    private static final String MEMBERS_INJECTOR_PREFIX = MembersInjector.class.getCanonicalName() + "<";
    private static final String LAZY_PREFIX = Lazy.class.getCanonicalName() + "<";
    private static final String SET_PREFIX = Set.class.getCanonicalName() + "<";
    private static final Memoizer IS_QUALIFIER_ANNOTATION = new Memoizer() { // from class: dagger.internal.Keys.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // dagger.internal.Memoizer
        public Boolean create(Class cls) {
            return Boolean.valueOf(cls.isAnnotationPresent(Qualifier.class));
        }
    };

    private static Type boxIfPrimitive(Type type) {
        return type == Byte.TYPE ? Byte.class : type == Short.TYPE ? Short.class : type == Integer.TYPE ? Integer.class : type == Long.TYPE ? Long.class : type == Character.TYPE ? Character.class : type == Boolean.TYPE ? Boolean.class : type == Float.TYPE ? Float.class : type == Double.TYPE ? Double.class : type == Void.TYPE ? Void.class : type;
    }

    private static String extractKey(String str, int i, String str2, String str3) {
        return str2 + str.substring(i + str3.length(), str.length() - 1);
    }

    public static String get(Type type) {
        return get(type, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getBuiltInBindingsKey(String str) {
        int startOfType = startOfType(str);
        String str2 = PROVIDER_PREFIX;
        if (substringStartsWith(str, startOfType, str2)) {
            return extractKey(str, startOfType, str.substring(0, startOfType), str2);
        }
        String str3 = MEMBERS_INJECTOR_PREFIX;
        if (substringStartsWith(str, startOfType, str3)) {
            return extractKey(str, startOfType, "members/", str3);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getLazyKey(String str) {
        int startOfType = startOfType(str);
        String str2 = LAZY_PREFIX;
        if (substringStartsWith(str, startOfType, str2)) {
            return extractKey(str, startOfType, str.substring(0, startOfType), str2);
        }
        return null;
    }

    public static String getMembersKey(Class cls) {
        return "members/".concat(cls.getName());
    }

    public static boolean isAnnotated(String str) {
        return str.startsWith("@");
    }

    public static boolean isPlatformType(String str) {
        return str.startsWith("java.") || str.startsWith("javax.") || str.startsWith("android.");
    }

    private static int startOfType(String str) {
        if (str.startsWith("@")) {
            return str.lastIndexOf(47) + 1;
        }
        return 0;
    }

    private static boolean substringStartsWith(String str, int i, String str2) {
        return str.regionMatches(i, str2, 0, str2.length());
    }

    private static void typeToString(Type type, StringBuilder sb, boolean z) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (cls.isArray()) {
                typeToString(cls.getComponentType(), sb, false);
                sb.append("[]");
            } else if (cls.isPrimitive()) {
                if (z) {
                    throw new UnsupportedOperationException("Uninjectable type " + cls.getName());
                }
                sb.append(cls.getName());
            } else {
                sb.append(cls.getName());
            }
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            typeToString(parameterizedType.getRawType(), sb, true);
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            sb.append("<");
            for (int i = 0; i < actualTypeArguments.length; i++) {
                if (i != 0) {
                    sb.append(", ");
                }
                typeToString(actualTypeArguments[i], sb, true);
            }
            sb.append(">");
        } else if (type instanceof GenericArrayType) {
            typeToString(((GenericArrayType) type).getGenericComponentType(), sb, false);
            sb.append("[]");
        } else {
            throw new UnsupportedOperationException("Uninjectable type " + String.valueOf(type));
        }
    }

    private static Annotation extractQualifier(Annotation[] annotationArr, Object obj) {
        Annotation annotation = null;
        for (Annotation annotation2 : annotationArr) {
            if (((Boolean) IS_QUALIFIER_ANNOTATION.get(annotation2.annotationType())).booleanValue()) {
                if (annotation == null) {
                    annotation = annotation2;
                } else {
                    throw new IllegalArgumentException("Too many qualifier annotations on " + String.valueOf(obj));
                }
            }
        }
        return annotation;
    }

    private static String get(Type type, Annotation annotation) {
        Type boxIfPrimitive = boxIfPrimitive(type);
        if (annotation == null && (boxIfPrimitive instanceof Class)) {
            Class cls = (Class) boxIfPrimitive;
            if (!cls.isArray()) {
                return cls.getName();
            }
        }
        StringBuilder sb = new StringBuilder();
        if (annotation != null) {
            sb.append(annotation).append("/");
        }
        typeToString(boxIfPrimitive, sb, true);
        return sb.toString();
    }

    public static String getClassName(String str) {
        int lastIndexOf;
        if (str.startsWith("@") || str.startsWith("members/")) {
            lastIndexOf = str.lastIndexOf(47) + 1;
        } else {
            lastIndexOf = 0;
        }
        if (str.indexOf(60, lastIndexOf) != -1 || str.indexOf(91, lastIndexOf) != -1) {
            return null;
        }
        return str.substring(lastIndexOf);
    }

    public static String get(Type type, Annotation[] annotationArr, Object obj) {
        return get(type, extractQualifier(annotationArr, obj));
    }
}
