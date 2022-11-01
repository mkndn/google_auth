package com.google.common.base;

import java.io.Serializable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Pair implements Serializable {
    private static final long serialVersionUID = 747826592375603043L;
    public final Object first;
    public final Object second;

    protected Pair(Object obj, Object obj2) {
        this.first = obj;
        this.second = obj2;
    }

    public static Pair of(Object obj, Object obj2) {
        return new Pair(obj, obj2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            return Objects.equal(getFirst(), pair.getFirst()) && Objects.equal(getSecond(), pair.getSecond());
        }
        return false;
    }

    public Object getFirst() {
        return this.first;
    }

    public Object getSecond() {
        return this.second;
    }

    public int hashCode() {
        Object obj = this.first;
        int hashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.second;
        return (hashCode * 31) + (obj2 != null ? obj2.hashCode() : 0);
    }

    public String toString() {
        return "(" + getFirst() + ", " + getSecond() + ")";
    }
}
