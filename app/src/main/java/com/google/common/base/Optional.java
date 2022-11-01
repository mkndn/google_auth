package com.google.common.base;

import java.io.Serializable;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Optional implements Serializable {
    private static final long serialVersionUID = 0;

    public static Optional absent() {
        return Absent.withType();
    }

    public static Optional fromNullable(Object obj) {
        return obj == null ? absent() : new Present(obj);
    }

    public static Optional of(Object obj) {
        return new Present(Preconditions.checkNotNull(obj));
    }

    public abstract boolean equals(Object obj);

    public abstract Object get();

    public abstract int hashCode();

    public abstract boolean isPresent();

    public abstract Object or(Object obj);

    public abstract Object orNull();
}
