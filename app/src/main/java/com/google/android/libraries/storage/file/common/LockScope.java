package com.google.android.libraries.storage.file.common;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LockScope {
    private final ConcurrentMap lockMap;

    @Deprecated
    public LockScope() {
        this(new ConcurrentHashMap());
    }

    private LockScope(ConcurrentMap concurrentMap) {
        this.lockMap = concurrentMap;
    }
}
