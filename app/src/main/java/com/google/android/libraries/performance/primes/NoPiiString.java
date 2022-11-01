package com.google.android.libraries.performance.primes;

import com.google.common.base.Strings;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NoPiiString {
    private final String value;

    private NoPiiString(String str) {
        this.value = str;
    }

    public static NoPiiString fromClass(Class cls) {
        return fromClass(null, cls);
    }

    public static NoPiiString fromFullyQualifiedClassName(Class cls) {
        return new NoPiiString(cls.getName());
    }

    public static String safeToString(NoPiiString noPiiString) {
        if (noPiiString == null) {
            return null;
        }
        return noPiiString.toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof NoPiiString) {
            return this.value.equals(((NoPiiString) obj).value);
        }
        return false;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return this.value;
    }

    public static NoPiiString fromClass(String str, Class cls) {
        if (!Strings.isNullOrEmpty(str)) {
            return new NoPiiString(str + cls.getSimpleName());
        }
        return new NoPiiString(cls.getSimpleName());
    }
}
