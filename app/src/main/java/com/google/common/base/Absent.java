package com.google.common.base;

/* compiled from: PG */
/* loaded from: classes.dex */
final class Absent extends Optional {
    static final Absent INSTANCE = new Absent();
    private static final long serialVersionUID = 0;

    private Absent() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Optional withType() {
        return INSTANCE;
    }

    @Override // com.google.common.base.Optional
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.google.common.base.Optional
    public Object get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.common.base.Optional
    public int hashCode() {
        return 2040732332;
    }

    @Override // com.google.common.base.Optional
    public boolean isPresent() {
        return false;
    }

    @Override // com.google.common.base.Optional
    public Object or(Object obj) {
        return Preconditions.checkNotNull(obj, "use Optional.orNull() instead of Optional.or(null)");
    }

    @Override // com.google.common.base.Optional
    public Object orNull() {
        return null;
    }

    public String toString() {
        return "Optional.absent()";
    }
}
