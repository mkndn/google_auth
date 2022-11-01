package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.SortedSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ImmutableSet extends ImmutableCollection implements Set {
    private transient ImmutableList asList;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder extends ImmutableCollection.ArrayBasedBuilder {
        private int hashCode;
        Object[] hashTable;

        public Builder() {
            super(4);
        }

        private void addDeduping(Object obj) {
            this.hashTable.getClass();
            int length = this.hashTable.length - 1;
            int hashCode = obj.hashCode();
            int smear = Hashing.smear(hashCode);
            while (true) {
                int i = smear & length;
                Object[] objArr = this.hashTable;
                Object obj2 = objArr[i];
                if (obj2 != null) {
                    if (obj2.equals(obj)) {
                        return;
                    }
                    smear = i + 1;
                } else {
                    objArr[i] = obj;
                    this.hashCode += hashCode;
                    super.add(obj);
                    return;
                }
            }
        }

        public ImmutableSet build() {
            ImmutableSet construct;
            switch (this.size) {
                case 0:
                    return ImmutableSet.of();
                case 1:
                    Object obj = this.contents[0];
                    obj.getClass();
                    return ImmutableSet.of(obj);
                default:
                    if (this.hashTable == null || ImmutableSet.chooseTableSize(this.size) != this.hashTable.length) {
                        construct = ImmutableSet.construct(this.size, this.contents);
                        this.size = construct.size();
                    } else {
                        Object[] copyOf = ImmutableSet.shouldTrim(this.size, this.contents.length) ? Arrays.copyOf(this.contents, this.size) : this.contents;
                        int i = this.hashCode;
                        Object[] objArr = this.hashTable;
                        construct = new RegularImmutableSet(copyOf, i, objArr, objArr.length - 1, this.size);
                    }
                    this.forceCopy = true;
                    this.hashTable = null;
                    return construct;
            }
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder add(Object obj) {
            Preconditions.checkNotNull(obj);
            if (this.hashTable != null && ImmutableSet.chooseTableSize(this.size) <= this.hashTable.length) {
                addDeduping(obj);
                return this;
            }
            this.hashTable = null;
            super.add(obj);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder
        public Builder add(Object... objArr) {
            if (this.hashTable != null) {
                for (Object obj : objArr) {
                    add(obj);
                }
            } else {
                super.add(objArr);
            }
            return this;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        Object readResolve() {
            return ImmutableSet.copyOf(this.elements);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int chooseTableSize(int i) {
        int max = Math.max(i, 2);
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1);
            while (true) {
                highestOneBit += highestOneBit;
                double d = highestOneBit;
                Double.isNaN(d);
                if (d * 0.7d >= max) {
                    return highestOneBit;
                }
            }
        } else {
            Preconditions.checkArgument(max < 1073741824, "collection too large");
            return 1073741824;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ImmutableSet construct(int i, Object... objArr) {
        switch (i) {
            case 0:
                return of();
            case 1:
                Object obj = objArr[0];
                obj.getClass();
                return of(obj);
            default:
                int chooseTableSize = chooseTableSize(i);
                Object[] objArr2 = new Object[chooseTableSize];
                int i2 = chooseTableSize - 1;
                int i3 = 0;
                int i4 = 0;
                for (int i5 = 0; i5 < i; i5++) {
                    Object checkElementNotNull = ObjectArrays.checkElementNotNull(objArr[i5], i5);
                    int hashCode = checkElementNotNull.hashCode();
                    int smear = Hashing.smear(hashCode);
                    while (true) {
                        int i6 = smear & i2;
                        Object obj2 = objArr2[i6];
                        if (obj2 != null) {
                            if (obj2.equals(checkElementNotNull)) {
                                break;
                            }
                            smear++;
                        } else {
                            objArr[i4] = checkElementNotNull;
                            objArr2[i6] = checkElementNotNull;
                            i3 += hashCode;
                            i4++;
                        }
                    }
                }
                Arrays.fill(objArr, i4, i, (Object) null);
                if (i4 == 1) {
                    Object obj3 = objArr[0];
                    obj3.getClass();
                    return new SingletonImmutableSet(obj3);
                } else if (chooseTableSize(i4) < chooseTableSize / 2) {
                    return construct(i4, objArr);
                } else {
                    if (shouldTrim(i4, objArr.length)) {
                        objArr = Arrays.copyOf(objArr, i4);
                    }
                    return new RegularImmutableSet(objArr, i3, objArr2, i2, i4);
                }
        }
    }

    public static ImmutableSet copyOf(Collection collection) {
        if ((collection instanceof ImmutableSet) && !(collection instanceof SortedSet)) {
            ImmutableSet immutableSet = (ImmutableSet) collection;
            if (!immutableSet.isPartialView()) {
                return immutableSet;
            }
        }
        Object[] array = collection.toArray();
        return construct(array.length, array);
    }

    public static ImmutableSet of() {
        return RegularImmutableSet.EMPTY;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldTrim(int i, int i2) {
        return i < (i2 >> 1) + (i2 >> 2);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList asList() {
        ImmutableList immutableList = this.asList;
        if (immutableList == null) {
            ImmutableList createAsList = createAsList();
            this.asList = createAsList;
            return createAsList;
        }
        return immutableList;
    }

    ImmutableList createAsList() {
        return ImmutableList.asImmutableList(toArray());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof ImmutableSet) && isHashCodeFast() && ((ImmutableSet) obj).isHashCodeFast() && hashCode() != obj.hashCode()) {
            return false;
        }
        return Sets.equalsImpl(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    boolean isHashCodeFast() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public abstract UnmodifiableIterator iterator();

    @Override // com.google.common.collect.ImmutableCollection
    Object writeReplace() {
        return new SerializedForm(toArray());
    }

    public static ImmutableSet of(Object obj) {
        return new SingletonImmutableSet(obj);
    }

    public static ImmutableSet of(Object obj, Object obj2, Object obj3, Object obj4) {
        return construct(4, obj, obj2, obj3, obj4);
    }

    @SafeVarargs
    public static ImmutableSet of(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object... objArr) {
        Preconditions.checkArgument(objArr.length <= 2147483641, "the total number of elements must fit in an int");
        int length = objArr.length + 6;
        Object[] objArr2 = new Object[length];
        objArr2[0] = obj;
        objArr2[1] = obj2;
        objArr2[2] = obj3;
        objArr2[3] = obj4;
        objArr2[4] = obj5;
        objArr2[5] = obj6;
        System.arraycopy(objArr, 0, objArr2, 6, objArr.length);
        return construct(length, objArr2);
    }

    public static ImmutableSet copyOf(Object[] objArr) {
        switch (objArr.length) {
            case 0:
                return of();
            case 1:
                return of(objArr[0]);
            default:
                return construct(objArr.length, (Object[]) objArr.clone());
        }
    }
}
