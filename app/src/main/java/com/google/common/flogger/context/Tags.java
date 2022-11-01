package com.google.common.flogger.context;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Tags {
    private final LightweightTagMap map;
    private static final Comparator VALUE_COMPARATOR = new Comparator() { // from class: com.google.common.flogger.context.Tags.1
        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            Type of = Type.of(obj);
            Type of2 = Type.of(obj2);
            return of == of2 ? of.compare(obj, obj2) : of.compareTo(of2);
        }
    };
    private static final Comparator KEY_VALUE_COMPARATOR = new Comparator() { // from class: com.google.common.flogger.context.Tags.2
        @Override // java.util.Comparator
        public int compare(KeyValuePair keyValuePair, KeyValuePair keyValuePair2) {
            int compareTo = keyValuePair.key.compareTo(keyValuePair2.key);
            if (compareTo == 0) {
                if (keyValuePair.value == null) {
                    return keyValuePair2.value != null ? -1 : 0;
                } else if (keyValuePair2.value != null) {
                    return Tags.VALUE_COMPARATOR.compare(keyValuePair.value, keyValuePair2.value);
                } else {
                    return 1;
                }
            }
            return compareTo;
        }
    };
    private static final Tags EMPTY_TAGS = new Tags(new LightweightTagMap(Collections.emptyList()));

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class KeyValuePair {
        private final String key;
        private final Object value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class LightweightTagMap extends AbstractMap {
        private static final Comparator ENTRY_COMPARATOR = new Comparator() { // from class: com.google.common.flogger.context.Tags.LightweightTagMap.1
            @Override // java.util.Comparator
            public int compare(Object obj, Object obj2) {
                return ((String) ((Map.Entry) obj).getKey()).compareTo((String) ((Map.Entry) obj2).getKey());
            }
        };
        private static final int[] singletonOffsets = {1, 2};
        private final Object[] array;
        private final int[] offsets;
        private final Set entrySet = new SortedArraySet(-1);
        private Integer hashCode = null;
        private String toString = null;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public class SortedArraySet extends AbstractSet {
            final int index;

            SortedArraySet(int i) {
                this.index = i;
            }

            private Comparator getComparator() {
                return this.index == -1 ? LightweightTagMap.ENTRY_COMPARATOR : Tags.VALUE_COMPARATOR;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return Arrays.binarySearch(LightweightTagMap.this.array, getStart(), getEnd(), obj, getComparator()) >= 0;
            }

            int getEnd() {
                return LightweightTagMap.this.offsets[this.index + 1];
            }

            int getStart() {
                if (this.index == -1) {
                    return 0;
                }
                return LightweightTagMap.this.offsets[this.index];
            }

            Object getValue(int i) {
                return LightweightTagMap.this.array[getStart() + i];
            }

            Object[] getValuesArray() {
                return LightweightTagMap.this.array;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator iterator() {
                return new Iterator() { // from class: com.google.common.flogger.context.Tags.LightweightTagMap.SortedArraySet.1
                    private int n = 0;

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.n < SortedArraySet.this.size();
                    }

                    @Override // java.util.Iterator
                    public Object next() {
                        int i = this.n;
                        if (i < SortedArraySet.this.size()) {
                            Object obj = LightweightTagMap.this.array[SortedArraySet.this.getStart() + i];
                            this.n = i + 1;
                            return obj;
                        }
                        throw new NoSuchElementException();
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return getEnd() - getStart();
            }
        }

        LightweightTagMap(LightweightTagMap lightweightTagMap, LightweightTagMap lightweightTagMap2) {
            int size = lightweightTagMap.size() + lightweightTagMap2.size();
            Object[] objArr = new Object[lightweightTagMap.getTotalElementCount() + lightweightTagMap2.getTotalElementCount()];
            int[] iArr = new int[size + 1];
            this.array = adjustOffsetsAndMaybeResize(objArr, iArr, mergeTagMaps(lightweightTagMap, lightweightTagMap2, size, objArr, iArr));
            this.offsets = maybeResizeOffsetsArray(iArr);
        }

        private static Object[] adjustOffsetsAndMaybeResize(Object[] objArr, int[] iArr, int i) {
            Object[] objArr2;
            int i2 = iArr[0];
            int i3 = i2 - i;
            if (i3 == 0) {
                return objArr;
            }
            for (int i4 = 0; i4 <= i; i4++) {
                iArr[i4] = iArr[i4] - i3;
            }
            int i5 = iArr[i];
            int i6 = i5 - i;
            if (mustResize(objArr.length, i5)) {
                objArr2 = new Object[i5];
                System.arraycopy(objArr, 0, objArr2, 0, i);
            } else {
                objArr2 = objArr;
            }
            System.arraycopy(objArr, i2, objArr2, i, i6);
            return objArr2;
        }

        private int copyEntryAndValues(Map.Entry entry, int i, int i2, Object[] objArr, int[] iArr) {
            SortedArraySet sortedArraySet = (SortedArraySet) entry.getValue();
            int end = sortedArraySet.getEnd() - sortedArraySet.getStart();
            System.arraycopy(sortedArraySet.getValuesArray(), sortedArraySet.getStart(), objArr, i2, end);
            objArr[i] = newEntry((String) entry.getKey(), i);
            int i3 = i2 + end;
            iArr[i + 1] = i3;
            return i3;
        }

        private Map.Entry getEntryOrNull(int i) {
            if (i < this.offsets[0]) {
                return (Map.Entry) this.array[i];
            }
            return null;
        }

        private int getTotalElementCount() {
            return this.offsets[size()];
        }

        private static Object[] maybeResizeElementArray(Object[] objArr, int i) {
            return mustResize(objArr.length, i) ? Arrays.copyOf(objArr, i) : objArr;
        }

        private static int[] maybeResizeOffsetsArray(int[] iArr) {
            int i = iArr[0] + 1;
            return mustResize(iArr.length, i) ? Arrays.copyOf(iArr, i) : iArr;
        }

        private static boolean mustResize(int i, int i2) {
            return i > 16 && i * 9 > i2 * 10;
        }

        private Map.Entry newEntry(String str, int i) {
            return new AbstractMap.SimpleImmutableEntry(str, new SortedArraySet(i));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set entrySet() {
            return this.entrySet;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int hashCode() {
            if (this.hashCode == null) {
                this.hashCode = Integer.valueOf(super.hashCode());
            }
            return this.hashCode.intValue();
        }

        @Override // java.util.AbstractMap
        public String toString() {
            if (this.toString == null) {
                this.toString = super.toString();
            }
            return this.toString;
        }

        private int mergeTagMaps(LightweightTagMap lightweightTagMap, LightweightTagMap lightweightTagMap2, int i, Object[] objArr, int[] iArr) {
            int i2;
            int copyEntryAndValues;
            iArr[0] = i;
            int i3 = i;
            Map.Entry entryOrNull = lightweightTagMap.getEntryOrNull(0);
            Map.Entry entryOrNull2 = lightweightTagMap2.getEntryOrNull(0);
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (true) {
                if (entryOrNull != null || entryOrNull2 != null) {
                    int i7 = entryOrNull == null ? 1 : entryOrNull2 == null ? -1 : 0;
                    if (i7 == 0 && (i7 = ((String) entryOrNull.getKey()).compareTo((String) entryOrNull2.getKey())) == 0) {
                        objArr[i4] = newEntry((String) entryOrNull.getKey(), i4);
                        i4++;
                        i3 = mergeValues((SortedArraySet) entryOrNull.getValue(), (SortedArraySet) entryOrNull2.getValue(), objArr, i3);
                        iArr[i4] = i3;
                        i5++;
                        entryOrNull = lightweightTagMap.getEntryOrNull(i5);
                        i6++;
                        entryOrNull2 = lightweightTagMap2.getEntryOrNull(i6);
                    }
                    if (i7 < 0) {
                        i2 = i4 + 1;
                        copyEntryAndValues = copyEntryAndValues(entryOrNull, i4, i3, objArr, iArr);
                        i5++;
                        entryOrNull = lightweightTagMap.getEntryOrNull(i5);
                    } else {
                        i2 = i4 + 1;
                        copyEntryAndValues = copyEntryAndValues(entryOrNull2, i4, i3, objArr, iArr);
                        i6++;
                        entryOrNull2 = lightweightTagMap2.getEntryOrNull(i6);
                    }
                    i3 = copyEntryAndValues;
                    i4 = i2;
                } else {
                    return i4;
                }
            }
        }

        private static int countMapEntries(List list) {
            Iterator it = list.iterator();
            String str = null;
            int i = 0;
            while (it.hasNext()) {
                KeyValuePair keyValuePair = (KeyValuePair) it.next();
                if (!keyValuePair.key.equals(str)) {
                    str = keyValuePair.key;
                    i++;
                }
            }
            return i;
        }

        private static int mergeValues(SortedArraySet sortedArraySet, SortedArraySet sortedArraySet2, Object[] objArr, int i) {
            int i2;
            Object obj;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i3 < sortedArraySet.size() || i4 < sortedArraySet2.size()) {
                    int i5 = i3 == sortedArraySet.size() ? 1 : i4 == sortedArraySet2.size() ? -1 : 0;
                    if (i5 == 0) {
                        i5 = Tags.VALUE_COMPARATOR.compare(sortedArraySet.getValue(i3), sortedArraySet2.getValue(i4));
                    }
                    if (i5 < 0) {
                        i2 = i3 + 1;
                        obj = sortedArraySet.getValue(i3);
                    } else {
                        int i6 = i4 + 1;
                        Object value = sortedArraySet2.getValue(i4);
                        if (i5 == 0) {
                            i3++;
                        }
                        i2 = i3;
                        obj = value;
                        i4 = i6;
                    }
                    objArr[i] = obj;
                    i3 = i2;
                    i++;
                } else {
                    return i;
                }
            }
        }

        private int makeTagMap(List list, int i, Object[] objArr, int[] iArr) {
            Iterator it = list.iterator();
            int i2 = 0;
            int i3 = i;
            String str = null;
            Object obj = null;
            while (it.hasNext()) {
                KeyValuePair keyValuePair = (KeyValuePair) it.next();
                if (!keyValuePair.key.equals(str)) {
                    str = keyValuePair.key;
                    objArr[i2] = newEntry(str, i2);
                    iArr[i2] = i3;
                    i2++;
                    obj = null;
                }
                if (keyValuePair.value != null && !keyValuePair.value.equals(obj)) {
                    obj = keyValuePair.value;
                    objArr[i3] = obj;
                    i3++;
                }
            }
            if (i2 != i) {
                throw new ConcurrentModificationException("corrupted tag map");
            }
            iArr[i] = i3;
            return i3;
        }

        LightweightTagMap(List list) {
            int countMapEntries = countMapEntries(list);
            Object[] objArr = new Object[list.size() + countMapEntries];
            int[] iArr = new int[countMapEntries + 1];
            this.array = maybeResizeElementArray(objArr, makeTagMap(list, countMapEntries, objArr, iArr));
            this.offsets = iArr;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    enum Type {
        BOOLEAN { // from class: com.google.common.flogger.context.Tags.Type.1
            @Override // com.google.common.flogger.context.Tags.Type
            int compare(Object obj, Object obj2) {
                return ((Boolean) obj).compareTo((Boolean) obj2);
            }
        },
        STRING { // from class: com.google.common.flogger.context.Tags.Type.2
            @Override // com.google.common.flogger.context.Tags.Type
            int compare(Object obj, Object obj2) {
                return ((String) obj).compareTo((String) obj2);
            }
        },
        LONG { // from class: com.google.common.flogger.context.Tags.Type.3
            @Override // com.google.common.flogger.context.Tags.Type
            int compare(Object obj, Object obj2) {
                return ((Long) obj).compareTo((Long) obj2);
            }
        },
        DOUBLE { // from class: com.google.common.flogger.context.Tags.Type.4
            @Override // com.google.common.flogger.context.Tags.Type
            int compare(Object obj, Object obj2) {
                return ((Double) obj).compareTo((Double) obj2);
            }
        };

        /* JADX INFO: Access modifiers changed from: private */
        public static Type of(Object obj) {
            if (obj instanceof String) {
                return STRING;
            }
            if (obj instanceof Boolean) {
                return BOOLEAN;
            }
            if (obj instanceof Long) {
                return LONG;
            }
            if (obj instanceof Double) {
                return DOUBLE;
            }
            throw new AssertionError("invalid tag type: " + obj.getClass());
        }

        abstract int compare(Object obj, Object obj2);
    }

    private Tags(LightweightTagMap lightweightTagMap) {
        this.map = lightweightTagMap;
    }

    public static Tags empty() {
        return EMPTY_TAGS;
    }

    public Map asMap() {
        return this.map;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Tags) && ((Tags) obj).map.equals(this.map);
    }

    public int hashCode() {
        return this.map.hashCode() ^ (-1);
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public Tags merge(Tags tags) {
        if (tags.isEmpty()) {
            return this;
        }
        if (isEmpty()) {
            return tags;
        }
        return new Tags(new LightweightTagMap(this.map, tags.map));
    }

    public String toString() {
        return this.map.toString();
    }
}
