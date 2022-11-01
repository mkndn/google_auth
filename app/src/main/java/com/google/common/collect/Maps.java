package com.google.common.collect;

import com.google.common.base.Function;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Maps {

    /* compiled from: PG */
    /* renamed from: com.google.common.collect.Maps$1  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass1 extends TransformedIterator {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.TransformedIterator
        public /* bridge */ /* synthetic */ Object transform(Object obj) {
            throw null;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    enum EntryFunction implements Function {
        KEY { // from class: com.google.common.collect.Maps.EntryFunction.1
            @Override // com.google.common.base.Function
            public Object apply(Map.Entry entry) {
                return entry.getKey();
            }
        },
        VALUE { // from class: com.google.common.collect.Maps.EntryFunction.2
            @Override // com.google.common.base.Function
            public Object apply(Map.Entry entry) {
                return entry.getValue();
            }
        };

        /* synthetic */ EntryFunction(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    static int capacity(int i) {
        if (i < 3) {
            CollectPreconditions.checkNonnegative(i, "expectedSize");
            return i + 1;
        } else if (i < 1073741824) {
            double d = i;
            Double.isNaN(d);
            return (int) Math.ceil(d / 0.75d);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean equalsImpl(Map map, Object obj) {
        if (map == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return map.entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Function keyFunction() {
        return EntryFunction.KEY;
    }

    public static HashMap newHashMapWithExpectedSize(int i) {
        return new HashMap(capacity(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toStringImpl(Map map) {
        StringBuilder append = Collections2.newStringBuilderForCollection(map.size()).append('{');
        boolean z = true;
        for (Map.Entry entry : map.entrySet()) {
            if (!z) {
                append.append(", ");
            }
            append.append(entry.getKey()).append('=').append(entry.getValue());
            z = false;
        }
        return append.append('}').toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Function valueFunction() {
        return EntryFunction.VALUE;
    }
}
