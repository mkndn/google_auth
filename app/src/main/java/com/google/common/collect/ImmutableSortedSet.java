package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.NavigableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ImmutableSortedSet extends ImmutableSortedSetFauxverideShim implements NavigableSet, SortedIterable {
    final transient Comparator comparator;
    transient ImmutableSortedSet descendingSet;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends ImmutableSet.Builder {
        private final Comparator comparator;

        public Builder(Comparator comparator) {
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
        }

        @Override // com.google.common.collect.ImmutableSet.Builder
        public ImmutableSortedSet build() {
            ImmutableSortedSet construct = ImmutableSortedSet.construct(this.comparator, this.size, this.contents);
            this.size = construct.size();
            this.forceCopy = true;
            return construct;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder add(Object obj) {
            super.add(obj);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder
        public Builder add(Object... objArr) {
            super.add(objArr);
            return this;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Comparator comparator;
        final Object[] elements;

        public SerializedForm(Comparator comparator, Object[] objArr) {
            this.comparator = comparator;
            this.elements = objArr;
        }

        Object readResolve() {
            return new Builder(this.comparator).add(this.elements).build();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableSortedSet(Comparator comparator) {
        this.comparator = comparator;
    }

    static ImmutableSortedSet construct(Comparator comparator, int i, Object... objArr) {
        if (i == 0) {
            return emptySet(comparator);
        }
        ObjectArrays.checkElementsNotNull(objArr, i);
        Arrays.sort(objArr, 0, i, comparator);
        int i2 = 1;
        for (int i3 = 1; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (comparator.compare(obj, objArr[i2 - 1]) != 0) {
                objArr[i2] = obj;
                i2++;
            }
        }
        Arrays.fill(objArr, i2, i, (Object) null);
        if (i2 < (objArr.length >> 1)) {
            objArr = Arrays.copyOf(objArr, i2);
        }
        return new RegularImmutableSortedSet(ImmutableList.asImmutableList(objArr, i2), comparator);
    }

    public static ImmutableSortedSet copyOf(Comparator comparator, Iterable iterable) {
        Preconditions.checkNotNull(comparator);
        if (SortedIterables.hasSameComparator(comparator, iterable) && (iterable instanceof ImmutableSortedSet)) {
            ImmutableSortedSet immutableSortedSet = (ImmutableSortedSet) iterable;
            if (!immutableSortedSet.isPartialView()) {
                return immutableSortedSet;
            }
        }
        Object[] array = Iterables.toArray(iterable);
        return construct(comparator, array.length, array);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RegularImmutableSortedSet emptySet(Comparator comparator) {
        if (Ordering.natural().equals(comparator)) {
            return RegularImmutableSortedSet.NATURAL_EMPTY_SET;
        }
        return new RegularImmutableSortedSet(ImmutableList.of(), comparator);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override // java.util.NavigableSet
    public Object ceiling(Object obj) {
        return Iterables.getFirst(tailSet(obj, true), null);
    }

    @Override // java.util.SortedSet, com.google.common.collect.SortedIterable
    public Comparator comparator() {
        return this.comparator;
    }

    abstract ImmutableSortedSet createDescendingSet();

    @Override // java.util.NavigableSet
    public abstract UnmodifiableIterator descendingIterator();

    @Override // java.util.NavigableSet
    public ImmutableSortedSet descendingSet() {
        ImmutableSortedSet immutableSortedSet = this.descendingSet;
        if (immutableSortedSet == null) {
            ImmutableSortedSet createDescendingSet = createDescendingSet();
            this.descendingSet = createDescendingSet;
            createDescendingSet.descendingSet = this;
            return createDescendingSet;
        }
        return immutableSortedSet;
    }

    @Override // java.util.SortedSet
    public Object first() {
        return iterator().next();
    }

    @Override // java.util.NavigableSet
    public Object floor(Object obj) {
        return Iterators.getNext(headSet(obj, true).descendingIterator(), null);
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public ImmutableSortedSet headSet(Object obj) {
        return headSet(obj, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ImmutableSortedSet headSetImpl(Object obj, boolean z);

    @Override // java.util.NavigableSet
    public Object higher(Object obj) {
        return Iterables.getFirst(tailSet(obj, false), null);
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public abstract UnmodifiableIterator iterator();

    @Override // java.util.SortedSet
    public Object last() {
        return descendingIterator().next();
    }

    @Override // java.util.NavigableSet
    public Object lower(Object obj) {
        return Iterators.getNext(headSet(obj, false).descendingIterator(), null);
    }

    @Override // java.util.NavigableSet
    @Deprecated
    public final Object pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    @Deprecated
    public final Object pollLast() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public ImmutableSortedSet subSet(Object obj, Object obj2) {
        return subSet(obj, true, obj2, false);
    }

    abstract ImmutableSortedSet subSetImpl(Object obj, boolean z, Object obj2, boolean z2);

    @Override // java.util.NavigableSet, java.util.SortedSet
    public ImmutableSortedSet tailSet(Object obj) {
        return tailSet(obj, true);
    }

    abstract ImmutableSortedSet tailSetImpl(Object obj, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public int unsafeCompare(Object obj, Object obj2) {
        return unsafeCompare(this.comparator, obj, obj2);
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    Object writeReplace() {
        return new SerializedForm(this.comparator, toArray());
    }

    static int unsafeCompare(Comparator comparator, Object obj, Object obj2) {
        return comparator.compare(obj, obj2);
    }

    @Override // java.util.NavigableSet
    public ImmutableSortedSet headSet(Object obj, boolean z) {
        return headSetImpl(Preconditions.checkNotNull(obj), z);
    }

    @Override // java.util.NavigableSet
    public ImmutableSortedSet subSet(Object obj, boolean z, Object obj2, boolean z2) {
        Preconditions.checkNotNull(obj);
        Preconditions.checkNotNull(obj2);
        Preconditions.checkArgument(this.comparator.compare(obj, obj2) <= 0);
        return subSetImpl(obj, z, obj2, z2);
    }

    @Override // java.util.NavigableSet
    public ImmutableSortedSet tailSet(Object obj, boolean z) {
        return tailSetImpl(Preconditions.checkNotNull(obj), z);
    }

    public static ImmutableSortedSet copyOf(Comparator comparator, Collection collection) {
        return copyOf(comparator, (Iterable) collection);
    }
}
