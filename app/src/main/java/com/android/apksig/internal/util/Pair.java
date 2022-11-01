package com.android.apksig.internal.util;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Pair {
    private final Object mFirst;
    private final Object mSecond;

    private Pair(Object obj, Object obj2) {
        this.mFirst = obj;
        this.mSecond = obj2;
    }

    public static Pair of(Object obj, Object obj2) {
        return new Pair(obj, obj2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pair pair = (Pair) obj;
        Object obj2 = this.mFirst;
        if (obj2 == null) {
            if (pair.mFirst != null) {
                return false;
            }
        } else if (!obj2.equals(pair.mFirst)) {
            return false;
        }
        Object obj3 = this.mSecond;
        if (obj3 == null) {
            if (pair.mSecond != null) {
                return false;
            }
        } else if (!obj3.equals(pair.mSecond)) {
            return false;
        }
        return true;
    }

    public Object getFirst() {
        return this.mFirst;
    }

    public Object getSecond() {
        return this.mSecond;
    }

    public int hashCode() {
        Object obj = this.mFirst;
        int hashCode = ((obj == null ? 0 : obj.hashCode()) + 31) * 31;
        Object obj2 = this.mSecond;
        return hashCode + (obj2 != null ? obj2.hashCode() : 0);
    }
}
