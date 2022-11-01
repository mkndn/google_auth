package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.SortedSet;

/* compiled from: PG */
/* loaded from: classes.dex */
final class SortedIterables {
    public static Comparator comparator(SortedSet sortedSet) {
        Comparator comparator = sortedSet.comparator();
        if (comparator == null) {
            return Ordering.natural();
        }
        return comparator;
    }

    public static boolean hasSameComparator(Comparator comparator, Iterable iterable) {
        Comparator comparator2;
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(iterable);
        if (iterable instanceof SortedSet) {
            comparator2 = comparator((SortedSet) iterable);
        } else if (iterable instanceof SortedIterable) {
            comparator2 = ((SortedIterable) iterable).comparator();
        } else {
            return false;
        }
        return comparator.equals(comparator2);
    }
}
