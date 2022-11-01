package com.google.protobuf;

import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LazyField extends LazyFieldLite {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class LazyEntry implements Map.Entry {
        private Map.Entry entry;

        private LazyEntry(Map.Entry entry) {
            this.entry = entry;
        }

        public LazyField getField() {
            return (LazyField) this.entry.getValue();
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.entry.getKey();
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            LazyField lazyField = (LazyField) this.entry.getValue();
            if (lazyField == null) {
                return null;
            }
            return lazyField.getValue();
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            if (!(obj instanceof MessageLite)) {
                throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
            }
            return ((LazyField) this.entry.getValue()).setValue((MessageLite) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class LazyIterator implements Iterator {
        private Iterator iterator;

        public LazyIterator(Iterator it) {
            this.iterator = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public void remove() {
            this.iterator.remove();
        }

        @Override // java.util.Iterator
        public Map.Entry next() {
            Map.Entry entry = (Map.Entry) this.iterator.next();
            if (entry.getValue() instanceof LazyField) {
                return new LazyEntry(entry);
            }
            return entry;
        }
    }

    @Override // com.google.protobuf.LazyFieldLite
    public boolean equals(Object obj) {
        throw null;
    }

    public MessageLite getValue() {
        throw null;
    }

    @Override // com.google.protobuf.LazyFieldLite
    public int hashCode() {
        throw null;
    }

    public String toString() {
        throw null;
    }
}
