package com.google.common.flogger.backend;

import com.google.common.flogger.MetadataKey;
import com.google.common.flogger.util.Checks;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class MetadataProcessor {
    private static final MetadataProcessor EMPTY_PROCESSOR = new MetadataProcessor() { // from class: com.google.common.flogger.backend.MetadataProcessor.1
        @Override // com.google.common.flogger.backend.MetadataProcessor
        public int keyCount() {
            return 0;
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public Set keySet() {
            return Collections.emptySet();
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public void process(MetadataHandler metadataHandler, Object obj) {
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SimpleProcessor extends MetadataProcessor {
        private final Map map;

        private SimpleProcessor(Metadata metadata, Metadata metadata2) {
            super();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            addTo(linkedHashMap, metadata);
            addTo(linkedHashMap, metadata2);
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                if (((MetadataKey) entry.getKey()).canRepeat()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
            this.map = Collections.unmodifiableMap(linkedHashMap);
        }

        private static void addTo(Map map, Metadata metadata) {
            for (int i = 0; i < metadata.size(); i++) {
                MetadataKey key = metadata.getKey(i);
                Object obj = map.get(key);
                if (key.canRepeat()) {
                    List list = (List) obj;
                    if (list == null) {
                        list = new ArrayList();
                        map.put(key, list);
                    }
                    list.add(key.cast(metadata.getValue(i)));
                } else {
                    map.put(key, key.cast(metadata.getValue(i)));
                }
            }
        }

        private static void dispatch(MetadataKey metadataKey, Object obj, MetadataHandler metadataHandler, Object obj2) {
            if (metadataKey.canRepeat()) {
                metadataHandler.handleRepeated(metadataKey, ((List) obj).iterator(), obj2);
            } else {
                metadataHandler.handle(metadataKey, obj, obj2);
            }
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public int keyCount() {
            return this.map.size();
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public Set keySet() {
            return this.map.keySet();
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public void process(MetadataHandler metadataHandler, Object obj) {
            for (Map.Entry entry : this.map.entrySet()) {
                dispatch((MetadataKey) entry.getKey(), entry.getValue(), metadataHandler, obj);
            }
        }
    }

    private MetadataProcessor() {
    }

    public static MetadataProcessor forScopeAndLogSite(Metadata metadata, Metadata metadata2) {
        int size = metadata.size() + metadata2.size();
        if (size == 0) {
            return EMPTY_PROCESSOR;
        }
        if (size <= 28) {
            return getLightweightProcessor(metadata, metadata2);
        }
        return getSimpleProcessor(metadata, metadata2);
    }

    static MetadataProcessor getLightweightProcessor(Metadata metadata, Metadata metadata2) {
        return new LightweightProcessor(metadata, metadata2);
    }

    static MetadataProcessor getSimpleProcessor(Metadata metadata, Metadata metadata2) {
        return new SimpleProcessor(metadata, metadata2);
    }

    public abstract int keyCount();

    public abstract Set keySet();

    public abstract void process(MetadataHandler metadataHandler, Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LightweightProcessor extends MetadataProcessor {
        private final int keyCount;
        private final int[] keyMap;
        private final Metadata logged;
        private final Metadata scope;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class ValueIterator implements Iterator {
            private final MetadataKey key;
            private int mask;
            private int nextIndex;

            private ValueIterator(MetadataKey metadataKey, int i) {
                this.key = metadataKey;
                int i2 = i & 31;
                this.nextIndex = i2;
                this.mask = i >>> (i2 + 5);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.nextIndex >= 0;
            }

            @Override // java.util.Iterator
            public Object next() {
                Object cast = this.key.cast(LightweightProcessor.this.getValue(this.nextIndex));
                int i = this.mask;
                if (i != 0) {
                    int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i) + 1;
                    this.mask >>>= numberOfTrailingZeros;
                    this.nextIndex += numberOfTrailingZeros;
                } else {
                    this.nextIndex = -1;
                }
                return cast;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        private LightweightProcessor(Metadata metadata, Metadata metadata2) {
            super();
            this.scope = (Metadata) Checks.checkNotNull(metadata, "scope metadata");
            this.logged = (Metadata) Checks.checkNotNull(metadata2, "logged metadata");
            int size = metadata.size() + metadata2.size();
            Checks.checkArgument(size <= 28, "metadata size too large");
            int[] iArr = new int[size];
            this.keyMap = iArr;
            this.keyCount = prepareKeyMap(iArr);
        }

        private void dispatch(MetadataKey metadataKey, int i, MetadataHandler metadataHandler, Object obj) {
            if (!metadataKey.canRepeat()) {
                metadataHandler.handle(metadataKey, metadataKey.cast(getValue(i)), obj);
            } else {
                metadataHandler.handleRepeated(metadataKey, new ValueIterator(metadataKey, i), obj);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MetadataKey getKey(int i) {
            int size = this.scope.size();
            return i >= size ? this.logged.getKey(i - size) : this.scope.getKey(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Object getValue(int i) {
            int size = this.scope.size();
            return i >= size ? this.logged.getValue(i - size) : this.scope.getValue(i);
        }

        private int indexOf(MetadataKey metadataKey, int[] iArr, int i) {
            for (int i2 = 0; i2 < i; i2++) {
                if (metadataKey.equals(getKey(iArr[i2] & 31))) {
                    return i2;
                }
            }
            return -1;
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public int keyCount() {
            return this.keyCount;
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public Set keySet() {
            return new AbstractSet() { // from class: com.google.common.flogger.backend.MetadataProcessor.LightweightProcessor.1
                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator iterator() {
                    return new Iterator() { // from class: com.google.common.flogger.backend.MetadataProcessor.LightweightProcessor.1.1
                        private int i = 0;

                        @Override // java.util.Iterator
                        public boolean hasNext() {
                            return this.i < LightweightProcessor.this.keyCount;
                        }

                        @Override // java.util.Iterator
                        public MetadataKey next() {
                            LightweightProcessor lightweightProcessor = LightweightProcessor.this;
                            int[] iArr = LightweightProcessor.this.keyMap;
                            int i = this.i;
                            this.i = i + 1;
                            return lightweightProcessor.getKey(iArr[i] & 31);
                        }

                        @Override // java.util.Iterator
                        public void remove() {
                            throw new UnsupportedOperationException();
                        }
                    };
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return LightweightProcessor.this.keyCount;
                }
            };
        }

        @Override // com.google.common.flogger.backend.MetadataProcessor
        public void process(MetadataHandler metadataHandler, Object obj) {
            for (int i = 0; i < this.keyCount; i++) {
                int i2 = this.keyMap[i];
                dispatch(getKey(i2 & 31), i2, metadataHandler, obj);
            }
        }

        private int prepareKeyMap(int[] iArr) {
            int indexOf;
            long j = 0;
            int i = 0;
            int i2 = 0;
            while (i < iArr.length) {
                MetadataKey key = getKey(i);
                long bloomFilterMask = key.getBloomFilterMask() | j;
                if (bloomFilterMask == j && (indexOf = indexOf(key, iArr, i2)) != -1) {
                    iArr[indexOf] = key.canRepeat() ? iArr[indexOf] | (1 << (i + 4)) : i;
                } else {
                    iArr[i2] = i;
                    i2++;
                }
                i++;
                j = bloomFilterMask;
            }
            return i2;
        }
    }
}
