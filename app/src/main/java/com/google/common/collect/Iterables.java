package com.google.common.collect;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Iterables {
    private static Collection castOrCopyToCollection(Iterable iterable) {
        if (iterable instanceof Collection) {
            return (Collection) iterable;
        }
        return Lists.newArrayList(iterable.iterator());
    }

    public static Object getFirst(Iterable iterable, Object obj) {
        return Iterators.getNext(iterable.iterator(), obj);
    }

    public static Object getLast(Iterable iterable) {
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.isEmpty()) {
                throw new NoSuchElementException();
            }
            return getLastInNonemptyList(list);
        }
        return Iterators.getLast(iterable.iterator());
    }

    private static Object getLastInNonemptyList(List list) {
        return list.get(list.size() - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object[] toArray(Iterable iterable) {
        return castOrCopyToCollection(iterable).toArray();
    }
}
