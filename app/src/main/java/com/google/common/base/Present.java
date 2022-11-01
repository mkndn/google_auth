package com.google.common.base;

/* compiled from: PG */
/* loaded from: classes.dex */
final class Present extends Optional {
    private static final long serialVersionUID = 0;
    private final Object reference;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Present(Object obj) {
        this.reference = obj;
    }

    @Override // com.google.common.base.Optional
    public boolean equals(Object obj) {
        if (obj instanceof Present) {
            return this.reference.equals(((Present) obj).reference);
        }
        return false;
    }

    @Override // com.google.common.base.Optional
    public Object get() {
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public int hashCode() {
        return this.reference.hashCode() + 1502476572;
    }

    @Override // com.google.common.base.Optional
    public boolean isPresent() {
        return true;
    }

    @Override // com.google.common.base.Optional
    public Object or(Object obj) {
        Preconditions.checkNotNull(obj, "use Optional.orNull() instead of Optional.or(null)");
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public Object orNull() {
        return this.reference;
    }

    public String toString() {
        return "Optional.of(" + this.reference + ")";
    }
}
