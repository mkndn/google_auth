package com.google.android.libraries.backup;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BackupKeyPredicates {
    private static void addAnnotatedFieldValues(Class cls, Class cls2, Set set) {
        for (Field field : cls2.getDeclaredFields()) {
            addFieldValueIfAnnotated(cls, field, set);
        }
    }

    private static void addFieldValueIfAnnotated(Class cls, Field field, Set set) {
        if (field.isAnnotationPresent(cls) && field.getType().equals(String.class)) {
            try {
                set.add((String) field.get(null));
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public static BackupKeyPredicate buildPredicateFromAnnotatedFieldsIn(Class cls, Class... clsArr) {
        return in(getAnnotatedFieldValues(cls, clsArr));
    }

    private static Set getAnnotatedFieldValues(Class cls, Class... clsArr) {
        HashSet hashSet = new HashSet();
        for (Class cls2 : clsArr) {
            addAnnotatedFieldValues(cls, cls2, hashSet);
        }
        return hashSet;
    }

    public static BackupKeyPredicate in(final Collection collection) {
        if (collection == null) {
            throw new NullPointerException("Null collection given.");
        }
        return new BackupKeyPredicate() { // from class: com.google.android.libraries.backup.BackupKeyPredicates.1
            @Override // com.google.android.libraries.backup.BackupKeyPredicate
            public boolean shouldBeBackedUp(String str) {
                return collection.contains(str);
            }
        };
    }
}
