package com.google.android.libraries.phenotype.client.stable;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessStableFlagCache {
    private Supplier cacheLoader;
    private final Object cacheLock = new Object();
    private volatile Map cachedFlags;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProcessStableFlagCache(Supplier supplier) {
        this.cacheLoader = (Supplier) Preconditions.checkNotNull(supplier);
    }

    public boolean applyIfCompatible(Map map, boolean z) {
        Preconditions.checkNotNull(map);
        synchronized (this.cacheLock) {
            if (this.cachedFlags != null && !z) {
                return this.cachedFlags.equals(map);
            }
            this.cachedFlags = map;
            this.cacheLoader = null;
            return true;
        }
    }

    public Object get(String str) {
        Object obj;
        Map map = this.cachedFlags;
        if (map == null) {
            synchronized (this.cacheLock) {
                Map map2 = this.cachedFlags;
                if (map2 == null) {
                    map2 = (Map) Preconditions.checkNotNull((Map) this.cacheLoader.get());
                    this.cachedFlags = map2;
                    this.cacheLoader = null;
                }
                obj = map2.get(str);
            }
            return obj;
        }
        return map.get(str);
    }
}
