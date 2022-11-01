package dagger.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Preconditions {
    public static void checkBuilderRequirement(Object obj, Class cls) {
        if (obj == null) {
            throw new IllegalStateException(cls.getCanonicalName() + " must be set");
        }
    }

    public static Object checkNotNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    public static Object checkNotNullFromProvides(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        }
        return obj;
    }

    public static Object checkNotNull(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
        return obj;
    }
}
