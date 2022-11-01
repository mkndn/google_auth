package com.google.common.collect;

import java.util.ListIterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class UnmodifiableListIterator extends UnmodifiableIterator implements ListIterator {
    @Override // java.util.ListIterator
    @Deprecated
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    @Deprecated
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
