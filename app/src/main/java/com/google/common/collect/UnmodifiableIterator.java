package com.google.common.collect;

import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class UnmodifiableIterator implements Iterator {
    @Override // java.util.Iterator
    @Deprecated
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
