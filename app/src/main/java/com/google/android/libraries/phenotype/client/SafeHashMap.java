package com.google.android.libraries.phenotype.client;

import android.os.Build;
import com.google.common.collect.ForwardingMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SafeHashMap extends ForwardingMap {
    private static final boolean IS_LOLLIPOP;
    private final Map delegateMap;

    static {
        IS_LOLLIPOP = Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22;
    }

    private SafeHashMap(Map map) {
        this.delegateMap = map;
    }

    public static SafeHashMap newSafeHashMap() {
        if (IS_LOLLIPOP) {
            return new SafeHashMap(Collections.synchronizedMap(new HashMap()));
        }
        return new SafeHashMap(new ConcurrentHashMap());
    }

    @Override // java.util.Map
    public Object putIfAbsent(Object obj, Object obj2) {
        if (IS_LOLLIPOP) {
            synchronized (this.delegateMap) {
                if (!this.delegateMap.containsKey(obj)) {
                    return this.delegateMap.put(obj, obj2);
                }
                return this.delegateMap.get(obj);
            }
        }
        return ((ConcurrentHashMap) this.delegateMap).putIfAbsent(obj, obj2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
    public Map delegate() {
        return this.delegateMap;
    }
}
