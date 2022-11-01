package com.google.common.collect;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Serialization;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ImmutableSetMultimap extends ImmutableMultimap implements Multimap {
    private static final long serialVersionUID = 0;
    private final transient ImmutableSet emptySet;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends ImmutableMultimap.Builder {
        public ImmutableSetMultimap build() {
            Collection entrySet = this.builderMap.entrySet();
            if (this.keyComparator != null) {
                entrySet = Ordering.from(this.keyComparator).onKeys().immutableSortedCopy(entrySet);
            }
            return ImmutableSetMultimap.fromMapEntries(entrySet, this.valueComparator);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SetFieldSettersHolder {
        static final Serialization.FieldSetter EMPTY_SET_FIELD_SETTER = Serialization.getFieldSetter(ImmutableSetMultimap.class, "emptySet");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableSetMultimap(ImmutableMap immutableMap, int i, Comparator comparator) {
        super(immutableMap, i);
        this.emptySet = emptySet(comparator);
    }

    private static ImmutableSet emptySet(Comparator comparator) {
        if (comparator == null) {
            return ImmutableSet.of();
        }
        return ImmutableSortedSet.emptySet(comparator);
    }

    static ImmutableSetMultimap fromMapEntries(Collection collection, Comparator comparator) {
        if (collection.isEmpty()) {
            return of();
        }
        ImmutableMap.Builder builder = new ImmutableMap.Builder(collection.size());
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            ImmutableSet valueSet = valueSet(comparator, (Collection) entry.getValue());
            if (!valueSet.isEmpty()) {
                builder.put(key, valueSet);
                i += valueSet.size();
            }
        }
        return new ImmutableSetMultimap(builder.buildOrThrow(), i, comparator);
    }

    public static ImmutableSetMultimap of() {
        return EmptyImmutableSetMultimap.INSTANCE;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        int readInt = objectInputStream.readInt();
        if (readInt < 0) {
            throw new InvalidObjectException("Invalid key count " + readInt);
        }
        ImmutableMap.Builder builder = ImmutableMap.builder();
        int i = 0;
        for (int i2 = 0; i2 < readInt; i2++) {
            Object readObject = objectInputStream.readObject();
            int readInt2 = objectInputStream.readInt();
            if (readInt2 > 0) {
                ImmutableSet.Builder valuesBuilder = valuesBuilder(comparator);
                for (int i3 = 0; i3 < readInt2; i3++) {
                    valuesBuilder.add(objectInputStream.readObject());
                }
                ImmutableSet build = valuesBuilder.build();
                if (build.size() == readInt2) {
                    builder.put(readObject, build);
                    i += readInt2;
                } else {
                    throw new InvalidObjectException("Duplicate key-value pairs exist for key " + readObject);
                }
            } else {
                throw new InvalidObjectException("Invalid value count " + readInt2);
            }
        }
        try {
            ImmutableMultimap.FieldSettersHolder.MAP_FIELD_SETTER.set(this, builder.buildOrThrow());
            ImmutableMultimap.FieldSettersHolder.SIZE_FIELD_SETTER.set(this, i);
            SetFieldSettersHolder.EMPTY_SET_FIELD_SETTER.set(this, emptySet(comparator));
        } catch (IllegalArgumentException e) {
            throw ((InvalidObjectException) new InvalidObjectException(e.getMessage()).initCause(e));
        }
    }

    private static ImmutableSet valueSet(Comparator comparator, Collection collection) {
        if (comparator == null) {
            return ImmutableSet.copyOf(collection);
        }
        return ImmutableSortedSet.copyOf(comparator, collection);
    }

    private static ImmutableSet.Builder valuesBuilder(Comparator comparator) {
        if (comparator == null) {
            return new ImmutableSet.Builder();
        }
        return new ImmutableSortedSet.Builder(comparator);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(valueComparator());
        Serialization.writeMultimap(this, objectOutputStream);
    }

    Comparator valueComparator() {
        ImmutableSet immutableSet = this.emptySet;
        if (!(immutableSet instanceof ImmutableSortedSet)) {
            return null;
        }
        return ((ImmutableSortedSet) immutableSet).comparator();
    }
}
