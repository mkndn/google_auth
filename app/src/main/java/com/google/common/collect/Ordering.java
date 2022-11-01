package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Comparator;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Ordering implements Comparator {
    public static Ordering from(Comparator comparator) {
        if (comparator instanceof Ordering) {
            return (Ordering) comparator;
        }
        return new ComparatorOrdering(comparator);
    }

    public static Ordering natural() {
        return NaturalOrdering.INSTANCE;
    }

    @Override // java.util.Comparator
    public abstract int compare(Object obj, Object obj2);

    public ImmutableList immutableSortedCopy(Iterable iterable) {
        return ImmutableList.sortedCopyOf(this, iterable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Ordering onKeys() {
        return onResultOf(Maps.keyFunction());
    }

    public Ordering onResultOf(Function function) {
        return new ByFunctionOrdering(function, this);
    }
}
